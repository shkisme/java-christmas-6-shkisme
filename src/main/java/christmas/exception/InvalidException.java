package christmas.exception;

public class InvalidException extends IllegalArgumentException {
    protected static final String ERROR_MESSAGE = "[ERROR] ";
    protected static final String RETRY_MESSAGE = " 다시 입력해 주세요.";

    public InvalidException(String message) {
        super(ERROR_MESSAGE + message + RETRY_MESSAGE);
    }
}
