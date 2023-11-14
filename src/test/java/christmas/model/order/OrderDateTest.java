package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderDateTest {

    @ParameterizedTest
    @CsvSource(value = {
            "2023, 12, 4, true",
            "2023, 12, 5, true",
            "2023, 12, 6, true",
            "2023, 12, 7, true",
            "2023, 12, 8, false",
            "2023, 12, 9, false",
            "2023, 12, 10, true"
    })
    @DisplayName("평일인지 주말인지 판단한다.")
    public void 평일인지_주말인지_판단한다(int year, int month, int date, boolean expected) {
        //given
        OrderDate orderDate = OrderDate.of(year, month, date);

        //when
        boolean isWeekday = orderDate.isWeekday();

        //then
        assertThat(isWeekday).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2023, 12, 4, false",
            "2023, 12, 5, false",
            "2023, 12, 17, true",
            "2023, 12, 25, true"
    })
    @DisplayName("일요일이거나 크리스마스날인지 판단한다.")
    public void 일요일이거나_크리스마스날인지_판단한다(int year, int month, int date, boolean expected) {
        //given
        OrderDate orderDate = OrderDate.of(year, month, date);

        //when
        boolean isSpecialDay = orderDate.isSpecialDay();

        //then
        assertThat(isSpecialDay).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3, false",
            "17, false",
            "25, true",
            "26, true",
            "30, true",
    })
    @DisplayName("날짜의 시점을 판단한다.")
    public void 날짜의_시점을_판단한다(int date, boolean expected) {
        //given
        OrderDate orderDate = OrderDate.of(2023, 12, 25);

        //when
        boolean isDayBeforeOrEqual = orderDate.isBeforeOrEqual(date);

        //then
        assertThat(isDayBeforeOrEqual).isEqualTo(expected);
    }
}
