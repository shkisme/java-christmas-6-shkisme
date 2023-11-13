package christmas.exception;

public class InvalidMenuException extends InvalidException {

    public enum InvalidMenuError {
        INVALID_FORMAT("메뉴의 형식이 유효하지 않은 주문입니다. 다시 입력해 주세요."),
        ZERO_COUNT("메뉴의 개수가 0인 주문은 유효하지 않습니다. 다시 입력해 주세요."),
        NOT_EXIST("메뉴판에 없는 유효하지 않은 주문입니다. 다시 입력해 주세요."),
        ;

        private final String message;

        InvalidMenuError(String message) {
            this.message = message;
        }
    }

    public InvalidMenuException(InvalidMenuError error) {
        super(error.message);
    }
}
