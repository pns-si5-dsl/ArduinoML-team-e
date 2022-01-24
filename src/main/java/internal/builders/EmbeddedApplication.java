package internal.builders;

import internal.annotations.ArduinoML;
import internal.models.Actuator;
import internal.models.Sensor;
import kernel.model.App;

import java.util.Arrays;
import java.util.Optional;

// TODO: Use an annotation processor at compile time to avoid having to inherit this class manually.
public class EmbeddedApplication {
    private final App application;

    public EmbeddedApplication() {
        this.application = new App();
    }

    public InstructionBuilder set(Actuator actuator) {
        return new InstructionBuilder(actuator);
    }

    public TimedTransitionBuilder after(long millis) {
        return new TimedTransitionBuilder(millis);
    }

    public ConditionalTransitionBuilder when(Sensor sensor) {
        return new ConditionalTransitionBuilder(sensor);
    }

    private void initApplication() {
        if (this.getClass().isAnnotationPresent(ArduinoML.class)) {
            Optional<ArduinoML> annotation = Arrays.stream(this.getClass().getAnnotationsByType(ArduinoML.class)).findFirst();

            // Extract name from annotation.
            if (annotation.isPresent() && !annotation.get().name().isEmpty()) {
                this.application.setName(annotation.get().name());
            }

            // Extract name from class.
            else {
                this.application.setName(this.getClass().getSimpleName());
            }
        } else {
            throw new IllegalStateException("The application must use the @ArduinoML() annotation.");
        }
    }

    private void initSensors() {
        // TODO: Detect sensors and add them to the application.
    }

    private void initActuators() {
        // TODO: Detect actuator and add them to the application.
    }

    private void initStates() {
        // TODO: Detect state methods, make them public and run them to build the states using the builders.
        // For this purpose, we must pass a reference to the application to the builders.
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
