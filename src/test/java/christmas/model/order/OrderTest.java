package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.ApplicationTest;
import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest extends ApplicationTest {

    @Test
    @DisplayName("주문 개수가 최소값 미만이면 에러가 발생한다.")
    public void 주문_개수가_최소값_미만이면_에러가_발생한다() {
        //given
        Menu 초코케이크 = menuRepository.findByName("초코케이크").get();

        //when & then
        assertThatThrownBy(() -> new Order(초코케이크, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_COUNT.getMessage());
    }

    @Test
    @DisplayName("주문 개수가 최대값 초과면 에러가 발생한다.")
    public void 주문_개수가_최대값_초과면_에러가_발생한다() {
        //given
        Menu 초코케이크 = menuRepository.findByName("초코케이크").get();

        //when & then
        assertThatThrownBy(() -> new Order(초코케이크, 21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_COUNT.getMessage());
    }
}
