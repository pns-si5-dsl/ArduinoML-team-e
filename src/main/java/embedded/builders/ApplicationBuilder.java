package embedded.builders;

import embedded.annotations.ArduinoML;
import embedded.annotations.Initial;
import embedded.annotations.Input;
import embedded.annotations.Output;
import embedded.interfaces.Builder;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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
     * The pin used by app's output or input
     */
    private Map<Integer, Brick> pinRegistered = new HashMap<>();

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

                                // check if the pin already attributed
                                Brick before =  this.pinRegistered.put(sensor.getPin(), sensor);
                                if(before != null)
                                    throw new IllegalStateException(
                                        String.format(
                                                "The pin '%s' is attributed to multiple bricks ('%s' and '%s').",
                                                sensor.getPin(),
                                                before.getName(),
                                                sensor.getName()
                                        )
                                    );
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

                                // check if the pin already attributed
                                Brick before =  this.pinRegistered.put(actuator.getPin(), actuator);
                                if(before != null)
                                    throw new IllegalStateException(
                                            String.format(
                                                    "The pin '%s' is attributed to multiple bricks ('%s' and '%s').",
                                                    actuator.getPin(),
                                                    before.getName(),
                                                    actuator.getName()
                                            )
                                    );
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
            states.put(method.getName(), new StateBuilder(method.getName()));
        }
    }

    /**
     * Identify and register the initial state
     */
    private void initInitial() {
        // Collect method with @Initial annotation
        List<Method> initialMethods = Arrays
            .stream(embeddedApplication.getClass().getDeclaredMethods())
            .filter(methods ->
                    Arrays.stream(methods.getAnnotationsByType(Initial.class))
                            .findAny()
                            .isPresent())
            .collect(Collectors.toList());

        // Check if an @Initial method is defined
        if(initialMethods.isEmpty())
            throw new IllegalArgumentException(
                String.format(
                        "The application '%s' does not define a initial state. " +
                        "Please use the @Initial at the top of the wanted initial state to define it as the one.",
                        application.getName()
                )
            );

        // Check if there is multiple @Initial
        if(initialMethods.size() > 1)
            throw new IllegalArgumentException(
                    String.format(
                            "The application '%s' must define only one initial state.",
                            application.getName()
                    )
            );

        // Register initial state
        Method initialOne = initialMethods.remove(0);
        application.setInitial(this.states.get(initialOne.getName()).getState());
    }

    /**
     * Builds the states of the application.
     */
    private void buildStates() {
        // For each method.
        for(Method method : embeddedApplication.getClass().getDeclaredMethods()){
            // Annotated as a state.
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

            application.getStates().add(state);
        }
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
        initInitial();
        buildStates();
        return application;
    }
}
