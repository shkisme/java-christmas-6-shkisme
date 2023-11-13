package christmas.exception;

public class InvalidOrderException extends InvalidException {

    public enum InvalidOrderError {
        INVALID_COUNT("메뉴의 개수 합이 20을 넘는 주문은 유효하지 않습니다. 다시 입력해 주세요."),
        DUPLICATE("메뉴가 중복되어 유효하지 않은 주문입니다. 다시 입력해 주세요."),
        ORDER_RESTRICTIONS("음료만 주문하여 유효하지 않은 주문입니다. 다시 입력해 주세요."),
        ;
        private final String message;

        InvalidOrderError(String message) {
            this.message = message;
        }
    }

    public InvalidOrderException(InvalidOrderError error) {
        super(error.message);
    }
}
