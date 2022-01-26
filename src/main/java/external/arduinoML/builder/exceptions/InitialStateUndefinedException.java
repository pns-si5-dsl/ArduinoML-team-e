package external.arduinoML.builder.exceptions;

public class InitialStateUndefinedException extends RuntimeException {
    public InitialStateUndefinedException() {
        super("No initial state was found in the program.");
    }
}
