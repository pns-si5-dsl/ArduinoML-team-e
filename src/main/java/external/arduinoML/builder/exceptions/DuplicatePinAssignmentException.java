package external.arduinoML.builder.exceptions;

public class DuplicatePinAssignmentException extends RuntimeException {
    public DuplicatePinAssignmentException(int pin, int line) {
        super("'PIN"+pin+"' (line "+line+"): Already assigned!");
    }
}
