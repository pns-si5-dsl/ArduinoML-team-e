package internal.builders;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.annotations.State;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

import java.util.Arrays;
import java.util.Optional;

// TODO: Use an annotation processor at compile time to avoid having to inherit this class manually.
public class EmbeddedApplication {
    /**
     * The low signal constant.
     * This attribute avoids the need to import the signal enum.
     */
    protected static final SIGNAL LOW = SIGNAL.LOW;

    /**
     * The high signal constant.
     * This attribute avoids the need to import the signal enum.
     */
    protected static final SIGNAL HIGH = SIGNAL.HIGH;

    /**
     * The application built using the embedded DSL.
     */
    private final App application;

    /**
     * Constructs an embedded application.
     */
    public EmbeddedApplication() {
        this.application = new App();
    }

    /**
     * Starts an instruction on an actuator.
     * @param actuator The actuator to be controlled.
     * @return A builder to complete the instruction.
     */
    public InstructionBuilder set(Actuator actuator) {
        return new InstructionBuilder(application, actuator);
    }

    /**
     * Starts a transition triggered by a sensor.
     * @param sensor The sensor to be monitored.
     * @return A builder to complete the transition.
     */
    public ConditionalTransitionBuilder when(Sensor sensor) {
        return new ConditionalTransitionBuilder(application, sensor);
    }

    /**
     * Starts a transition triggered by a timer.
     * @param timeout The timeout in milliseconds.
     * @return The builder to complete the transition.
     */
    public TimedTransitionBuilder after(int timeout) {
        return new TimedTransitionBuilder(application, timeout);
    }

    /**
     * Initializes the application.
     */
    private void initApplication() {
        if (this.getClass().isAnnotationPresent(ArduinoML.class)) {
            Optional<ArduinoML> annotation = Arrays.stream(this.getClass().getAnnotationsByType(ArduinoML.class)).findFirst();

            // Extract name from the annotation.
            if (annotation.isPresent() && !annotation.get().name().isEmpty()) {
                this.application.setName(annotation.get().name());
            }

            // Extract name from the class.
            else {
                this.application.setName(this.getClass().getSimpleName());
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
            .stream(this.getClass().getDeclaredFields())
            .forEach(field -> {
                // Annotated as a sensor.
                Arrays
                    .stream(field.getAnnotationsByType(Input.class))
                    .findFirst()
                    .ifPresent(input -> {
                        // Validate the sensor.
                        if (input.pin() >= 0) {
                            field.setAccessible(true);
                            try {
                                Sensor sensor = new Sensor();
                                sensor.setName(field.getName());
                                sensor.setPin(input.pin());
                                field.set(this, sensor);
                                application.getBricks().add(sensor);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("An error occurred while initializing the '" + field.getName() + "' sensor.", e);
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
            .stream(this.getClass().getDeclaredFields())
            .forEach(field -> {
                // Annotated as an actuator.
                Arrays
                    .stream(field.getAnnotationsByType(Output.class))
                    .findFirst()
                    .ifPresent(output -> {
                        // Validate the actuator.
                        if (output.pin() >= 0) {
                            field.setAccessible(true);
                            try {
                                Actuator actuator = new Actuator();
                                actuator.setName(field.getName());
                                actuator.setPin(output.pin());
                                field.set(this, actuator);
                                application.getBricks().add(actuator);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("An error occurred while initializing the '" + field.getName() + "' actuator.", e);
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
        Arrays
            .stream(this.getClass().getDeclaredMethods())
            .forEach(method -> {
                // Annotated as a state.
                Arrays
                    .stream(method.getAnnotationsByType(State.class))
                    .findFirst()
                    .ifPresent(state -> {
                        method.setAccessible(true);
                        try {
                            method.invoke(this);
                        } catch (Exception e) {
                            throw new IllegalStateException("An error occurred while initializing the '" + method.getName() + "' state.", e);
                        }
                    });
            });
    }

    /**
     * Builds the application using the embedded DSL.
     * @return The application built using the embedded DSL.
     */
    public App build() {
        initApplication();
        initSensors();
        initActuators();
        initStates();
        return application;
    }

    public String generateCode() {
        // TODO: Generate Arduino code using the kernel.
        return "";
    }
}
