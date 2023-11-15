package christmas.exception;

public class InvalidOrderException extends InvalidException {

    public enum InvalidOrderError {
        INVALID_ORDER("유효하지 않은 주문입니다."),
        ;
        private final String message;

        InvalidOrderError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public InvalidOrderException(InvalidOrderError error) {
        super(error.message);
    }
}
