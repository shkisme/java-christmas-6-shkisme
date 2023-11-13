package christmas.exception;

public class InvalidMenuException extends InvalidException {

    public enum InvalidMenuError {
        INVALID_FORMAT("메뉴의 형식이 유효하지 않습니다."),
        ZERO_COUNT("메뉴의 개수로 0은 유효하지 않습니다."),
        NOT_EXIST("메뉴판에 없어 유효하지 않습니다."),
        ;

        private final String message;

        InvalidMenuError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public InvalidMenuException(InvalidMenuError error) {
        super(error.message);
    }
}
