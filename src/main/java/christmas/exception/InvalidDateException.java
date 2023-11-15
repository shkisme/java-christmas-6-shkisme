package christmas.exception;

public class InvalidDateException extends InvalidException {

    public enum InvalidDateError {
        INVALID_DATE("유효하지 않은 날짜입니다."),
        ;

        private final String message;

        InvalidDateError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public InvalidDateException(InvalidDateError error) {
        super(error.message);
    }
}
