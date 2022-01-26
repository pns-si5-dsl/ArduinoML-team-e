package internal.builders;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.lang.reflect.Method;
import java.util.*;

public class ApplicationBuilder {
    /**
     * The embedded application.
     */
    private final EmbeddedApplication embeddedApplication;

    /**
     * The application under construction.
     */
    private final App application;

    public final Map<String, StateBuilder> statesMap;

    /**
     * The state under construction.
     */
    private StateBuilder currentStateBuilder;

    /**
     * Constructs an application builder.
     *
     * @param embeddedApplication The embedded application.
     */
    public ApplicationBuilder(EmbeddedApplication embeddedApplication) {
        this.embeddedApplication = embeddedApplication;
        this.application = new App();
        statesMap = new HashMap<>();
    }

    /**
     * fill the stateMap
     */
    private void initStates() {
        for (Method method : embeddedApplication.getClass().getDeclaredMethods()) {
            Arrays
                .stream(method.getAnnotationsByType(internal.annotations.State.class))
                .findFirst()
                .ifPresent((annotation) -> statesMap.put(method.getName(), new StateBuilder(method.getName())));

        }
    }

    /**
     * Initializes the application.
     */
    private void buildApplication() {
        if (embeddedApplication.getClass().isAnnotationPresent(ArduinoML.class)) {
            Optional<ArduinoML> annotation = Arrays.stream(embeddedApplication.getClass().getAnnotationsByType(ArduinoML.class)).findFirst();

            // Extract name from the annotation.
            if (annotation.isPresent() && !annotation.get().name().isEmpty()) {
                application.setName(annotation.get().name());
            }

            // Extract name from the class.
            else {
                application.setName(embeddedApplication.getClass().getSimpleName());
            }
        } else {
            throw new IllegalStateException("The application must use the @" + ArduinoML.class.getSimpleName() + "() annotation.");
        }
    }

    /**
     * Initializes the sensors of the application.
     */
    private void buildSensors() {
        // For each field.
        Arrays
            .stream(embeddedApplication.getClass().getDeclaredFields())
            .forEach(field -> {
                // Annotated as a sensor.
                Arrays
                    .stream(field.getAnnotationsByType(Input.class))
                    .findFirst()
                    .ifPresent(annotation -> {
                        // Validate the sensor.
                        if (annotation.pin() >= 0) {
                            field.setAccessible(true);
                            try {
                                Sensor sensor = new Sensor();
                                sensor.setName(field.getName());
                                sensor.setPin(annotation.pin());
                                field.set(embeddedApplication, sensor);
                                application.getBricks().add(sensor);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("An error occurred while building the '" + field.getName() + "' sensor.", e);
                            }
                        } else {
                            throw new IllegalStateException("The '" + field.getName() + "' sensor is invalid.");
                        }
                    });
            });
    }

    /**
     * Initializes the actuators of the application.
     */
    private void buildActuators() {
        // For each field.
        Arrays
            .stream(embeddedApplication.getClass().getDeclaredFields())
            .forEach(field -> {
                // Annotated as an actuator.
                Arrays
                    .stream(field.getAnnotationsByType(Output.class))
                    .findFirst()
                    .ifPresent(annotation -> {
                        // Validate the actuator.
                        if (annotation.pin() >= 0) {
                            field.setAccessible(true);
                            try {
                                Actuator actuator = new Actuator();
                                actuator.setName(field.getName());
                                actuator.setPin(annotation.pin());
                                field.set(embeddedApplication, actuator);
                                application.getBricks().add(actuator);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("An error occurred while building the '" + field.getName() + "' actuator.", e);
                            }
                        } else {
                            throw new IllegalStateException("The '" + field.getName() + "' actuator is invalid.");
                        }
                    });
            });
    }

    /**
     * Initializes the states of the application.
     */
    private void buildStates() {
        // For each method.
        Arrays
            .stream(embeddedApplication.getClass().getDeclaredMethods())
            .forEach(method -> {
                // Annotated as a state.
                Arrays
                    .stream(method.getAnnotationsByType(internal.annotations.State.class))
                    .findFirst()
                    .ifPresent(annotation -> {
                        StateBuilder builder = statesMap.get(method.getName());
                        currentStateBuilder = builder;

                        // Build the state.
                        method.setAccessible(true);
                        try {
                            method.invoke(embeddedApplication);
                        } catch (Exception e) {
                            throw new IllegalStateException("An error occurred while building the '" + method.getName() + "' state.", e);
                        }

                        State state = builder.build();

                        // Set the initial state.
                        if (annotation.initial()) {
                            application.setInitial(state);
                        }

                        application.getStates().add(state);
                    });
            });
    }

    /**
     * Adds an action to the current state.
     */
    public void addActionToCurrentState(Builder<? extends Action> builder) {
        currentStateBuilder.addAction(builder);
    }

    /**
     * Adds a transition to the current state.
     */
    public void addTransitionToCurrentState(Builder<? extends Transition> builder) {
        currentStateBuilder.addTransition(builder);
    }

    /**
     * Builds the application using the embedded DSL.
     *
     * @return The application built using the embedded DSL.
     */
    public App build() {
        buildApplication();
        buildActuators();
        buildSensors();
        initStates();
        buildStates();
        return application;
    }

    public StateBuilder getCurrentStateBuilder() {
        return this.currentStateBuilder;
    }

    public boolean hasState(String stateName) {
        return this.statesMap.containsKey(stateName);
    }

    public State getState(String nextStateName) {
        return this.statesMap.get(nextStateName).getState();
    }
}
