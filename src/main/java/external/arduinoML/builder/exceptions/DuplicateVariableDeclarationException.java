package external.arduinoML.builder.exceptions;

public class DuplicateVariableDeclarationException extends RuntimeException {
    public DuplicateVariableDeclarationException(String var, int line) {
        super("'"+var+"' (line "+line+"): Already defined!");
    }
}
