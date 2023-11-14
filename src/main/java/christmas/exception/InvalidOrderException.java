package christmas.exception;

public class InvalidOrderException extends InvalidException {

    public enum InvalidOrderError {
        INVALID_COUNT("메뉴의 개수 범위가 유효하지 않습니다."),
        DUPLICATE("메뉴가 중복되어 유효하지 않습니다."),
        INVALID_TYPE("음료만 주문하여 유효하지 않습니다."),
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
