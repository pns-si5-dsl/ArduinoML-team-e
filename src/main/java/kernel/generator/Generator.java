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

    public Generator(){
        this.generatedCode = new StringBuffer();
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
        write("int state; //State of the state machine (from 1 to n)",2);

        // Dynamic Constants && Global Vars
        for(Brick brick: app.getBricks()){
            brick.accept(this); // name + pin-numbers && states
        }

        // Now we want our bricks to do their setup code
        context = CONTEXTS.SETUP;

        // Setup function
        write("void setup(){",3);
        write("debounceTime = 0; //Obvious init",1,1);
        write("state = 1; // 1 is the default state when entering the state-machine",2,1);
        for(Brick brick: app.getBricks()){
            brick.accept(this); // Init all bricks pinMode (INPUT or OUTPUT)
        }
        write("}");

        // Finally, we want to do the loop code
        context = CONTEXTS.LOOP;

        // Loop function
        write("void loop(){",3);
        write("if ((millis() - debounceTime) > debounceDelay) { //Debounce Statement",1,1);

        write("debounceTime = millis(); //Update debounce time",2,2);

        //Update sensors values
        write("//Update sensor values",2,2);
        for(Brick brick: app.getBricks()){
            brick.accept(this); // Init all bricks pinMode (INPUT or OUTPUT)
        }


        write("switch(state) {",2,2);
        //Manually setting the numbers of the states because I don't want to touch the model builder for now => Testing purposes
        //TODO : Do this in the model builder while translating ANTLR
        for(int i = 1; i < app.getStates().size(); i++){
            app.getStates().get(i).setNumber(i);
        }
        for(State state: app.getStates()){
            state.accept(this); //Visit state machine
        }
        write("} //End of State machine",1,2);
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
            write(String.format("pinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()),2,1);
        }
    }

    @Override
    public void visit(Sensor sensor) {
        if(context == CONTEXTS.INIT){
            initConstBrick(sensor);
            write(String.format("int %sState; //Current state of the Sensor (current reading)", sensor.getName()));
            write(String.format("int %sLastState; //Last state of the Sensor (last registered reading)", sensor.getName()));
        }
        else if(context == CONTEXTS.SETUP){
            write(String.format("pinMode(%d, INPUT);  // %s [Sensor]", sensor.getPin(), sensor.getName()),2,1);
            write(String.format("%sLastState = LOW; //Default latest state of the Sensor, obviously OFF", sensor.getName()),1,1);
        }
        else if(context == CONTEXTS.LOOP){
            write(String.format("%sState = digitalRead(%s);",sensor.getName(),sensor.getName()),1,2);
        }
    }

    @Override
    public void visit(State state) {
        write(String.format("case %d: // [State : %s]", state.getNumber(), state.getName()),1,3);
        for (Action action : state.getActions()) {
            action.accept(this);
        }

        for (Transition transition : state.getTransitions()) {
            transition.accept(this);
        }
        write("break;", 1,4);
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
        write(String.format("if( %sState == %s) {", check.getSensor().getName(), check.getValue()),1,4);
        write(String.format("state = %d;", check.getNext().getNumber()),1,5);
        write("}",1,4);
    }

    @Override
    public void visit(TimeWaiting noCheck) {
        write(String.format("delay(%d);", noCheck.getTimeout()),1,4);
        write(String.format("state = %d;", noCheck.getNext().getNumber()),1,4);
    }
}
