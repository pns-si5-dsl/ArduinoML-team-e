package kernel.generator;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;
import kernel.model.state.State;
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
        write("if ((millis() - debounceTime) > debounceDelay) { //Debounce Statement",2,1);
        for(State state: app.getStates()){
            state.accept(this);
        }
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
    }

    @Override
    public void visit(State state) {

    }

    @Override
    public void visit(OutputAction action) {

    }

    @Override
    public void visit(Transition transition) {

    }

    @Override
    public void visit(InputWaiting check) {

    }

    @Override
    public void visit(TimeWaiting noCheck) {

    }
}
