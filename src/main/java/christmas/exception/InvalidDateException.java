package christmas.exception;

public class InvalidDateException extends InvalidException {

    public enum InvalidDateError {
        INVALID_NUMBER("문자가 입력되어 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
        INVALID_RANGE("날짜의 범위가 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        private final String message;

        InvalidDateError(String message) {
            this.message = message;
        }
    }

    public InvalidDateException(InvalidDateError error) {
        super(error.message);
    }
}
