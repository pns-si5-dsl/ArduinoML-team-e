package kernel.generator;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.state.transitions.TimeWaiting;
import kernel.model.state.transitions.Transition;

public class Generator implements Visitor<StringBuffer> {

    private enum CONTEXTS {
        INIT,
        SETUP,
        LOOP
    }

    private final StringBuffer generatedCode;
    private CONTEXTS context; //the context of the generator, so the visitor knows which code to execute
    private final int delayTimeAfterStateMachine;

    public Generator(){
        this.generatedCode = new StringBuffer();
        delayTimeAfterStateMachine = 250;
    }


    private void write(String str, int numberLines, int numberTabs){
        for(int i = 0; i < numberLines; i++){
            generatedCode.append("\n");
        }
        for(int i = 0; i < numberTabs; i++){
            generatedCode.append("\t");
        }
        generatedCode.append(String.format("%s",str));
    }
    private void write(String str){
        write(str, 1,0);
    }
    private void write(String str, int numberLines){
        write(str, numberLines,0);
    }

    public StringBuffer getGeneratedCode(){
        return generatedCode;
    }

    @Override
    public void visit(App app) {
        // *Entry-point* of the Generator => Main will call app.accept() -> redirected here

        //The context of the generator is the INIT state (with constants and global variables)
        context = CONTEXTS.INIT;

        // Header
        write("// Arduino code generated from a model");
        write("// Copyright Team-E all right reserved");

        // Static Constants
        write("const int debounceDelay = 50; //Delay for button debounce",3);
        // Static Global Vars
        write("unsigned long debounceTime; //Timer for button debounce");
        write("unsigned long whileTimer; //Timer used in while loops into the state machine");
        write("enum ENUM_STATE { //States (into an enum) of the state machine",2);
        String sep ="";
        for(State state: app.getStates()){
            write(sep,0,0);
            state.accept(this);
            sep = ", ";
        }
        write("};");
        write("ENUM_STATE state; //Current state of the state machine",2);

        // Dynamic Constants && Global Vars
        for(Brick brick: app.getBricks()){
            brick.accept(this); // name + pin-numbers && states
        }

        // Now we want our bricks to do their setup code
        context = CONTEXTS.SETUP;

        // Setup function
        write("void setup(){",3);
        write("debounceTime = 0; //Obvious init",1,1);
        write("whileTimer = 0; //Obvious Init",1,1);
        write(""); //EOL
        for(Brick brick: app.getBricks()){
            brick.accept(this); // Init all bricks pinMode (INPUT or OUTPUT)
        }
        app.getInitial().accept(this); //Init default state
        write("}");

        // Finally, we want to do the loop code
        context = CONTEXTS.LOOP;

        // Loop function
        write("void loop(){",3);
        write("if ((millis() - debounceTime) > debounceDelay) { //Debounce Statement",1,1);
        write("debounceTime = millis(); //Update debounce time",2,2);

        write("switch(state) {",2,2);
        for(State state: app.getStates()){
            state.accept(this); //Visit state machine
        }
        write("} //End of State machine",1,2);
        write(String.format("delay(%d);",delayTimeAfterStateMachine),1,2);
        write("} //End of Debounce Statement",1,1);
        write("} //End of loop() function");
    }

    private void initConstBrick(Brick brick) {
        write(String.format("const int %s = %d;", brick.getName(), brick.getPin()),2);
    }

    @Override
    public void visit(Actuator actuator) {
        if(context == CONTEXTS.INIT){
            initConstBrick(actuator);
        }
        else if(context == CONTEXTS.SETUP){
            write(String.format("pinMode(%s, OUTPUT); // [Actuator, PIN : %d]", actuator.getName(), actuator.getPin()),1,1);
        }
    }

    @Override
    public void visit(Sensor sensor) {
        if(context == CONTEXTS.INIT){
            initConstBrick(sensor);
            write(String.format("int %sState; //Current state of the Sensor (current reading)", sensor.getName()));
            //write(String.format("int %sLastState; //Last state of the Sensor (last registered reading)", sensor.getName()));
        }
        else if(context == CONTEXTS.SETUP){
            write(String.format("pinMode(%s, INPUT); // [Sensor, PIN : %d]", sensor.getName(), sensor.getPin()),1,1);
            //write(String.format("%sLastState = LOW; //Default latest state of the Sensor, obviously OFF", sensor.getName()),1,1);
        }
    }

    @Override
    public void visit(State state) {
        if(context == CONTEXTS.INIT) {
            write(state.getName(),1,1);
        }
        else if(context == CONTEXTS.SETUP){
            write(String.format("state = %s; // Default state when entering the state-machine",state.getName()),2,1);
        }
        else if(context == CONTEXTS.LOOP){
            write(String.format("case %s:", state.getName()),1,3);
            for (Action action : state.getActions()) {
                action.accept(this);
            }

            //While loop around transitions to cover n transitions
            write("whileTimer = millis();",1,4);
            write("while(1) {",1,4);
            for (Transition transition : state.getTransitions()) {
                transition.accept(this);
            }
            write("}",1,4);
            write("break;", 1,4);
        }
    }

    @Override
    public void visit(OutputAction action) {
        write(String.format("digitalWrite(%s,%s);", action.getActuator().getName(), action.getValue()),1,4);
    }

    @Override
    public void visit(Transition transition) {
        //TODO : On ne rentre PAS ENCORE ici car transition est une Upper-class à InputWaiting && TimeWaiting
    }

    @Override
    public void visit(InputWaiting check) {
        write(String.format("%sState = digitalRead(%s);",check.getSensor().getName(),check.getSensor().getName()),1,5);
        write(String.format("if( %sState == %s) {", check.getSensor().getName(), check.getValue()),1,5);
        write(String.format("state = %s;", check.getNext().getName()),1,6);
        write("break;", 1,6);
        write("}",1,5);
    }

    @Override
    public void visit(TimeWaiting noCheck) {
        write(String.format("if ((millis() - whileTimer) > %d) {;", noCheck.getTimeout()),1,5);
        write(String.format("state = %s;", noCheck.getNext().getName()),1,6);
        write("break;", 1,6);
        write("}",1,5);
    }
}
