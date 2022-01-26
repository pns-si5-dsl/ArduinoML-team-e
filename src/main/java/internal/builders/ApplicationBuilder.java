package internal.builders;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.interfaces.Builder;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApplicationBuilder {
    /**
     * The embedded application.
     */
    private final EmbeddedApplication embeddedApplication;

    /**
     * The application under construction.
     */
    private final App application;

    /**
     * The state builders of the application.
     */
    public final Map<String, StateBuilder> states;

    /**
     * The state under construction.
     */
    private StateBuilder currentStateBuilder;

    /**
     * Constructs an application builder.
     * @param embeddedApplication The embedded application.
     */
    public ApplicationBuilder(EmbeddedApplication embeddedApplication) {
        this.embeddedApplication = embeddedApplication;
        application = new App();
        states = new HashMap<>();
    }

    /**
     * Initializes the application.
     */
    private void initApplication() {
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
    private void initSensors() {
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
    private void initActuators() {
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
    private void initStates() {
        // For each method.
        for (Method method : embeddedApplication.getClass().getDeclaredMethods()) {
            // Annotated as a state.
            Arrays
                .stream(method.getAnnotationsByType(internal.annotations.State.class))
                .findFirst()
                .ifPresent(annotation -> states.put(method.getName(), new StateBuilder(method.getName())));
        }
    }

    /**
     * Builds the states of the application.
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
                        StateBuilder builder = states.get(method.getName());
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
     * Indicates whether the application defines a specific state.
     * @param stateName The name of the state.
     * @return {@code true} if the application defines the requested state; {@code false} otherwise.
     */
    public boolean hasState(String stateName) {
        return states.containsKey(stateName);
    }

    /**
     * Returns a specific state of the application.
     * @param stateName The name of the state.
     * @return The requested state, if found.
     */
    public State getState(String stateName) {
        if (!states.containsKey(stateName)) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' state does not exist. " +
                    "Please make sure to define the '%s' state.",
                    stateName,
                    stateName
                )
            );

        }
        return states.get(stateName).getState();
    }

    /**
     * Returns the current state builder.
     * @return The current state builder.
     */
    public StateBuilder getCurrentStateBuilder() {
        return currentStateBuilder;
    }

    /**
     * Adds an action builder to the current state.
     * @param actionBuilder The action builder to be added.
     */
    public void addActionToCurrentState(Builder<? extends Action> actionBuilder) {
        currentStateBuilder.addAction(actionBuilder);
    }

    /**
     * Adds a transition builder to the current state.
     * @param transitionBuilder The transition builder to be added.
     */
    public void addTransitionToCurrentState(Builder<? extends Transition> transitionBuilder) {
        currentStateBuilder.addTransition(transitionBuilder);
    }

    /**
     * Builds the application.
     * @return The built application.
     */
    public App build() {
        initApplication();
        initActuators();
        initSensors();
        initStates();
        buildStates();
        return application;
    }
}
