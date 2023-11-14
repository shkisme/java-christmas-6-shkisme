package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.DUPLICATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.ApplicationTest;
import christmas.model.menu.Menu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest extends ApplicationTest {
    private static final int BENEFITS_PRICE = 10_000;
    private static final int PRESENTATION_PRICE = 120_000;

    private Menu 샴페인;
    private Menu 초코케이크;
    private Menu 아이스크림;

    @BeforeEach
    void setUp() {
        샴페인 = menuRepository.findByName("샴페인").get();
        초코케이크 = menuRepository.findByName("초코케이크").get();
        아이스크림 = menuRepository.findByName("아이스크림").get();
    }

    @Test
    @DisplayName("모든 주문이 음료일 경우 에러가 발생한다.")
    public void 모든_주문이_음료일_경우_에러가_발생한다() {
        //given
        Order 음료주문 = new Order(샴페인, 1);

        //when & then
        assertThatThrownBy(() -> new Orders(List.of(음료주문)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_TYPE.getMessage());
    }

    @Test
    @DisplayName("음료가 아닌 다른 메뉴가 포함되어 있다면 주문 생성한다.")
    public void 음료가_아닌_다른_메뉴가_포함되어_있다면_주문_생성한다() {
        //given
        Order 음료주문 = new Order(샴페인, 1);
        Order 디저트주문 = new Order(초코케이크, 1);

        //when & then
        assertDoesNotThrow(() -> new Orders(List.of(음료주문, 디저트주문)));
    }

    @Test
    @DisplayName("중복 메뉴가 있다면 에러가 발생한다.")
    public void 중복_메뉴가_있다면_에러가_발생한다() {
        //given
        Order 초코케이크주문 = new Order(초코케이크, 1);
        Order 중복주문 = new Order(초코케이크, 2);

        //when & then
        assertThatThrownBy(() -> new Orders(List.of(초코케이크주문, 중복주문)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("주문된 메뉴의 개수 합이 최대값을 초과하면 에러가 발생한다.")
    public void 주문된_메뉴의_개수_합이_최대값을_초과하면_에러가_발생한다() {
        //given
        Order 음료주문 = new Order(샴페인, 1);
        Order 초코케이크주문 = new Order(초코케이크, 20);

        //when & then
        assertThatThrownBy(() -> new Orders(List.of(음료주문, 초코케이크주문)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_COUNT.getMessage());
    }

    @Test
    @DisplayName("총 주문 금액을 계산한다.")
    public void 총_주문_금액을_계산한다() {
        //given
        Order 음료주문 = new Order(샴페인, 1);
        Order 디저트주문 = new Order(초코케이크, 1);
        Orders orders = new Orders(List.of(음료주문, 디저트주문));

        //when
        int totalPrice = orders.getPrice();

        //then
        assertThat(totalPrice).isEqualTo(샴페인.getPrice() + 초코케이크.getPrice());
    }

    @Test
    @DisplayName("혜택을 받는지 판단한다.")
    public void 혜택을_받는지_판단한다() {
        //given
        Order 아이스크림주문 = new Order(아이스크림, 1);
        Orders noBenefitsOrders = new Orders(List.of(아이스크림주문));

        Order 초코케이크주문 = new Order(초코케이크, 1);
        Orders benefitsOrders = new Orders(List.of(초코케이크주문));

        //when
        boolean expectedFalse = noBenefitsOrders.hasBenefits();
        boolean expectedTrue = benefitsOrders.hasBenefits();

        //then
        assertThat(expectedFalse).isFalse();
        assertThat(noBenefitsOrders.getPrice()).isLessThan(BENEFITS_PRICE);
        assertThat(expectedTrue).isTrue();
        assertThat(benefitsOrders.getPrice()).isGreaterThan(BENEFITS_PRICE);
    }

    @Test
    @DisplayName("증정을 받는지 판단한다.")
    public void 증정을_받는지_판단한다() {
        //given
        Order 아이스크림주문 = new Order(아이스크림, 1);
        Orders noPresentsOrders = new Orders(List.of(아이스크림주문));

        Order 초코케이크주문 = new Order(초코케이크, 10);
        Orders presentsOrders = new Orders(List.of(초코케이크주문));

        //when
        boolean expectedFalse = noPresentsOrders.hasPresents();
        boolean expectedTrue = presentsOrders.hasPresents();

        //then
        assertThat(expectedFalse).isFalse();
        assertThat(noPresentsOrders.getPrice()).isLessThan(PRESENTATION_PRICE);
        assertThat(expectedTrue).isTrue();
        assertThat(presentsOrders.getPrice()).isGreaterThan(PRESENTATION_PRICE);
    }

    @Test
    @DisplayName("주문한 메뉴의 종류별 개수를 센다.")
    public void 주문한_메뉴의_종류별_개수를_센다() {
        //given
        Order 아이스크림주문 = new Order(아이스크림, 1);
        Order 초코케이크주문 = new Order(초코케이크, 10);
        Order 샴페인주문 = new Order(샴페인, 1);
        Orders orders = new Orders(List.of(아이스크림주문, 초코케이크주문, 샴페인주문));

        //when
        int 디저트개수 = orders.countByMenuType("디저트");
        int 음료개수 = orders.countByMenuType("음료");

        //then
        assertThat(디저트개수).isEqualTo(11);
        assertThat(음료개수).isEqualTo(1);
    }
}
