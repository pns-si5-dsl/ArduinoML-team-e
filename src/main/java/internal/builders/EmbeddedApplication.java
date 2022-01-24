package internal.builders;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.annotations.State;
import internal.models.Actuator;
import internal.models.Sensor;
import kernel.model.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// TODO: Use an annotation processor at compile time to avoid having to inherit this class manually.
public class EmbeddedApplication {
    private final App application;

    public EmbeddedApplication() {
        this.application = new App();
    }

    public InstructionBuilder set(Actuator actuator) {
        return new InstructionBuilder(application, actuator);
    }

    public ConditionalTransitionBuilder when(Sensor sensor) {
        return new ConditionalTransitionBuilder(application, sensor);
    }

    public TimedTransitionBuilder after(long millis) {
        return new TimedTransitionBuilder(application, millis);
    }

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

    private void initSensors() {
        List<kernel.model.component.Sensor> sensors = new ArrayList<>();

        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> {
            Arrays.stream(field.getAnnotationsByType(Input.class)).forEach(input -> {
                kernel.model.component.Sensor sensor = new kernel.model.component.Sensor();
                sensor.setName(field.getName());
                sensor.setPin(input.pin());
                sensors.add(sensor);
            });
        });

        application.getBricks().addAll(sensors);
    }

    private void initActuators() {
        List<kernel.model.component.Actuator> actuators = new ArrayList<>();

        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> {
            Arrays.stream(field.getAnnotationsByType(Output.class)).forEach(output -> {
                kernel.model.component.Actuator actuator = new kernel.model.component.Actuator();
                actuator.setName(field.getName());
                actuator.setPin(output.pin());
                actuators.add(actuator);
            });
        });

        application.getBricks().addAll(actuators);
    }

    private void initStates() {
        Arrays.stream(this.getClass().getDeclaredMethods()).forEach(method -> {
            Arrays.stream(method.getAnnotationsByType(State.class)).forEach(state -> {
                method.setAccessible(true);
                try {
                    method.invoke(this);
                } catch (Exception e) {
                    throw new IllegalStateException("An error occurred during the construction of the `" + method.getName() + "` state.", e);
                }

                // TODO: Detect state methods, make them public and run them to build the states using the builders.
            });
        });
    }

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
