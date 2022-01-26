package external.arduinoML.builder;

public enum ReferenceType {
    ACTUATOR("Actuator"),
    SENSOR("Sensor"),
    STATE("State");

    private final String name;

    ReferenceType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
