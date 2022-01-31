package kernel.generator;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.state.transitions.condition.TimeWaiting;
import kernel.model.state.transitions.Transition;
import kernel.model.values.BINARY_OPERATOR;

public class Generator implements Visitor<StringBuffer> {

    // Gives a context to the generator => Indicate which step of the generation process we are currently in
    // -> Provides a way for the visitor of objects (actuator, sensor, states, ...) to generate the corresponding code
    private enum CONTEXTS {
        INIT,
        SETUP,
        LOOP
    }

    private final StringBuffer generatedCode; // Result
    private CONTEXTS context; // The context of the generator, so the visitor knows which code to execute
    private final int delayTimeAfterStateMachine; //Time used in the end-delay
    private String bufferComposedCondition;

    public Generator(){
        this.generatedCode = new StringBuffer();
        delayTimeAfterStateMachine = 250;
    }


    // Shortcut to append lines of codes to the result string
    //  -> Also provides an easy way to add EOL and Tabs for better readability of the generated code
    private void write(String str, int numberLines, int numberTabs){
        generatedCode.append("\n".repeat(Math.max(0, numberLines))); //Write n EOL
        generatedCode.append("\t".repeat(Math.max(0, numberTabs))); //Write n Tabs
        generatedCode.append(String.format("%s",str));
    }
    //Shortcuts used in the INIT
    private void write(String str){
        write(str, 1,0);
    }
    private void write(String str, int numberLines){
        write(str, numberLines,0);
    }


    public StringBuffer getGeneratedCode(){
        return generatedCode;
    }

    /**
     * Visitor of the App object => Entry-point, called by the Main with app.accept()
     * @param app the object that represents our domain-model, built by the parser & lexer
     */
    @Override
    public void visit(App app) {

        //The context of the generator is the INIT state (with constants and global variables)
        context = CONTEXTS.INIT;

        // Header
        write("// Arduino code generated from a model",0);
        write("// Copyright Team-E all right reserved");

        // Static Constants
        write("const int debounceDelay = 50; //Delay for button debounce",3);
        // Static Global Vars
        write("unsigned long debounceTime; //Timer for button debounce");
        write("unsigned long whileTimer; //Timer used in while loops into the state machine");

        // Dynamic Constants && Global Vars
        // States definitions
        write("enum ENUM_STATE { //States (into an enum) of the state machine",2);
        String sep ="";
        for(State state: app.getStates()){
            write(sep,0,0);
            state.accept(this);
            sep = ", ";
        }
        write("};");
        write("ENUM_STATE state; //Current state of the state machine",2);
        // Bricks definitions (name + pin number)
        for(Brick brick: app.getBricks()){
            brick.accept(this);
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
        write("if((millis() - debounceTime) > debounceDelay) { //Debounce Statement",1,1);
        write("debounceTime = millis(); //Update debounce time",2,2);

        write("switch(state) {",2,2);
        for(State state: app.getStates()){
            state.accept(this); //Visit state machine
        }
        write("} //End of State machine",1,2);
        write(String.format("delay(%d); //Delay for hardware synchronization",delayTimeAfterStateMachine),1,2);
        write("} //End of Debounce Statement",1,1);
        write("} //End of loop() function");
    }

    //Common init code for actuators and sensors
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
        }
        else if(context == CONTEXTS.SETUP){
            write(String.format("pinMode(%s, INPUT); // [Sensor, PIN : %d]", sensor.getName(), sensor.getPin()),1,1);
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
            write(String.format("case %s:", state.getName()),2,3);
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
        // The transition is composed by one condition and one state
        // -> corresponding to the next state to go if the condition is met

        //IF the condition is an InputWaiting, it will need to first update the sensor state
        bufferComposedCondition = ""; //Init of the buffer string
        transition.getCondition().accept(this); //digitalRead()
        write(String.format("if%s {",bufferComposedCondition),1,5); //Create the if() block => Trick for CompositeCheck

        //Then the state transition is the same code whatever the condition
        write(String.format("state = %s;", transition.getNext().getName()),1,6);
        write("break;", 1,6); //break the inside-while(1) loop
        write("}",1,5);
    }

    @Override
    public void visit(InputWaiting inCheck) {
        //Write the condition based on an Input

        //Read the input: latest value to update
        write(String.format("%sState = digitalRead(%s);",inCheck.getSensor().getName(),inCheck.getSensor().getName()),1,5);
        //Check the value
        bufferComposedCondition += String.format("(%sState == %s)", inCheck.getSensor().getName(), inCheck.getValue());
    }

    @Override
    public void visit(TimeWaiting timeCheck) {
        //Write the condition based on Time => Wait X seconds
        bufferComposedCondition += String.format("((millis() - whileTimer) > %d)", timeCheck.getTimeout());
    }

    @Override
    public void visit(CompositeCheck compCheck) {
        //IF the condition is a composite:
        // It will call left and right visitors, adding the operator, and encapsulates into brackets
        bufferComposedCondition += "( ";
        compCheck.getLeft().accept(this); //Left condition
        bufferComposedCondition += String.format(" %s ",writeOperator(compCheck.getOperator())); //Operator
        compCheck.getRight().accept(this); //Right condition
        bufferComposedCondition += " )";
    }

    private String writeOperator(BINARY_OPERATOR op){
        if(op == BINARY_OPERATOR.AND){
            return "&&";
        }
        if(op == BINARY_OPERATOR.OR){
            return "||";
        }
        throw new RuntimeException("Unknown Binary Operator");
    }
}
