package external.arduinoML.builder.exceptions;

public enum ReferenceType {
    ACTUATOR("Actuator"),
    SENSOR("Sensor"),
    STATE("State");

    private final String name;

    private ReferenceType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
