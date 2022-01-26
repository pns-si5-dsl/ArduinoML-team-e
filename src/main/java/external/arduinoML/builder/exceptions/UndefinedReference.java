package external.arduinoML.builder.exceptions;

public class UndefinedReference extends RuntimeException {
    public UndefinedReference(ReferenceType type, String var, int line) {
        super(type.toString()+" '"+var+"' (line "+line+"): Undefined!");
    }
}
