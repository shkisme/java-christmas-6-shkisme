package christmas.model.benefits;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.ApplicationTest;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.model.order.Orders;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BenefitsTest extends ApplicationTest {

    private Benefits benefits;

    @Nested
    @DisplayName("크리스마스 혜택 테스트")
    class ChristmasBenefitsTest {

        private Order 초코케이크주문;
        private Order 바비큐립주문;

        @BeforeEach
        void setUp() {
            Menu 초코케이크 = menuRepository.findByName("초코케이크").get();
            Menu 바비큐립 = menuRepository.findByName("바비큐립").get();
            초코케이크주문 = new Order(초코케이크, 1);
            바비큐립주문 = new Order(바비큐립, 1);
        }


        @ParameterizedTest
        @CsvSource(value = {
                "1, 1000",
                "2, 1100",
                "5, 1400",
                "25, 3400",
                "26, 0",
                "31, 0",
        })
        @DisplayName("디데이 할인 혜택을 계산한다.")
        public void 디데이_할인_혜택을_계산한다(int date, int result) {
            //given
            Orders orders = new Orders(List.of(초코케이크주문));
            OrderDate orderDate = OrderDate.of(2023, 12, date);
            benefits = new ChristmasBenefits(orders, orderDate);

            //when
            int dayBenefits = benefits.getDayBenefits();

            //then
            assertEquals(dayBenefits, result);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "4, 2023",
                "5, 2023",
                "6, 2023",
                "7, 2023",
                "8, 0",
                "9, 0",
                "10, 2023",
        })
        @DisplayName("평일 할인 혜택을 계산한다.")
        public void 평일_할인_혜택을_계산한다(int date, int result) {
            //given
            Orders orders = new Orders(List.of(초코케이크주문, 바비큐립주문));
            OrderDate orderDate = OrderDate.of(2023, 12, date);
            benefits = new ChristmasBenefits(orders, orderDate);

            //when
            int weekdayBenefits = benefits.getWeekdayBenefits();

            //then
            assertEquals(weekdayBenefits, result);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "4, 0",
                "5, 0",
                "6, 0",
                "7, 0",
                "8, 2023",
                "9, 2023",
                "10, 0",
        })
        @DisplayName("주말 할인 혜택을 계산한다.")
        public void 주말_할인_혜택을_계산한다(int date, int result) {
            //given
            Orders orders = new Orders(List.of(초코케이크주문, 바비큐립주문));
            OrderDate orderDate = OrderDate.of(2023, 12, date);
            benefits = new ChristmasBenefits(orders, orderDate);

            //when
            int weekendBenefits = benefits.getWeekendBenefits();

            //then
            assertEquals(weekendBenefits, result);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "4, 0",
                "5, 0",
                "6, 0",
                "7, 0",
                "8, 0",
                "9, 0",
                "10, 1000",
                "17, 1000",
                "25, 1000",
        })
        @DisplayName("특별 할인 혜택을 계산한다.")
        public void 특별_할인_혜택을_계산한다(int date, int result) {
            //given
            Orders orders = new Orders(List.of(초코케이크주문, 바비큐립주문));
            OrderDate orderDate = OrderDate.of(2023, 12, date);
            benefits = new ChristmasBenefits(orders, orderDate);

            //when
            int specialDayBenefits = benefits.getSpecialDayBenefits();

            //then
            assertEquals(specialDayBenefits, result);
        }

        @Test
        @DisplayName("총 할인 혜택을 계산한다.")
        public void 총_할인_혜택을_계산한다() {
            //given
            Orders orders = new Orders(List.of(초코케이크주문, 바비큐립주문));
            OrderDate orderDate = OrderDate.of(2023, 12, 25);
            benefits = new ChristmasBenefits(orders, orderDate);

            int expectDayBenefits = 3400;
            int expectWeekdayBenefits = 2023;
            int expectWeekendBenefits = 0;
            int expectSpecialDayBenefits = 1000;

            //when
            int totalBenefits = benefits.getTotalBenefits();

            //then
            assertThat(totalBenefits)
                    .isEqualTo(expectDayBenefits + expectWeekdayBenefits + expectWeekendBenefits
                            + expectSpecialDayBenefits);
        }
    }
}
