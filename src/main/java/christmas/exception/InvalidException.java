package christmas.exception;

public class InvalidException extends IllegalArgumentException {
    protected static final String ERROR_MESSAGE = "[ERROR] ";

    public InvalidException(String message) {
        super(ERROR_MESSAGE + message);
    }
}
