package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_NUMBER;
import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_RANGE;
import static christmas.exception.InvalidMenuException.InvalidMenuError.INVALID_FORMAT;
import static christmas.exception.InvalidMenuException.InvalidMenuError.NOT_EXIST;
import static christmas.exception.InvalidMenuException.InvalidMenuError.ZERO_COUNT;
import static christmas.exception.InvalidOrderException.InvalidOrderError.DUPLICATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasInputViewTest extends ApplicationTest {

    @Test
    @DisplayName("정상 입력 테스트")
    public void 정상_입력_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).doesNotContain(ERROR_MESSAGE_PREFIX);
        });
    }

    @Nested
    @DisplayName("날짜 입력 테스트")
    class InputDateTest {

        @DisplayName("날짜로 문자열이 주어지면 에러가 발생한다.")
        public void 날짜로_문자열이_주어지면_에러가_발생한다() {
            assertSimpleTest(() -> {
                runException("a");
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_NUMBER.getMessage());
            });
        }

        @ParameterizedTest
        @CsvSource(value = {
                "-1",
                "0",
                "32",
                "40",
        })
        @DisplayName("날짜의 범위가 유효하지 않을 경우 에러가 발생한다.")
        public void 날짜의_범위가_유효하지_않을_경우_에러가_발생한다(String invalidValue) {
            assertSimpleTest(() -> {
                runException(invalidValue);
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_RANGE.getMessage());
            });
        }
    }

    @Nested
    @DisplayName("메뉴 입력 테스트")
    class InputMenuTest {

        @ParameterizedTest
        @CsvSource(value = {
                "제로콜라-a",
                "제로콜라=1",
                "제로콜라_1",
                "제로콜라-1개",
        })
        @DisplayName("메뉴 형식이 맞지 않으면 에러가 발생한다.")
        public void 메뉴_형식이_맞지_않으면_에러가_발생한다(String invalidValue) {
            assertSimpleTest(() -> {
                runException("3", invalidValue);
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_FORMAT.getMessage());
            });
        }

        @Test
        @DisplayName("메뉴의 개수가 0이면 에러가 발생한다.")
        public void 메뉴의_개수가_0이면_에러가_발생한다() {
            assertSimpleTest(() -> {
                runException("3", "제로콜라-0");
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, ZERO_COUNT.getMessage());
            });
        }

        @ParameterizedTest
        @CsvSource(value = {
                "제로콜라-21",
                "제로콜라-1,샴페인-20",
                "제로콜라-1,샴페인-2,초코케이크-20",
        }, delimiter = ':')
        @DisplayName("메뉴의 개수 총 합이 최대값을 초과하면 에러가 발생한다.")
        public void 메뉴의_개수_총_합이_최대값을_초과하면_에러가_발생한다(String invalidValue) {
            assertSimpleTest(() -> {
                runException("3", invalidValue);
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_COUNT.getMessage());
            });
        }

        @Test
        @DisplayName("메뉴가 중복되면 에러가 발생한다.")
        public void 메뉴가_중복되면_에러가_발생한다() {
            assertSimpleTest(() -> {
                runException("3", "초코케이크-1,초코케이크-2");
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, DUPLICATE.getMessage());
            });
        }

        @Test
        @DisplayName("메뉴 데이터가 없다면 에러가 발생한다.")
        public void 메뉴_데이터가_없다면_에러가_발생한다() {
            assertSimpleTest(() -> {
                runException("3", "없는메뉴-1,초코케이크-2");
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, NOT_EXIST.getMessage());
            });
        }

        @Test
        @DisplayName("음료만 입력하면 에러가 발생한다.")
        public void 음료만_입력하면_에러가_발생한다() {
            assertSimpleTest(() -> {
                runException("3", "샴페인-1,제로콜라-2");
                assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_TYPE.getMessage());
            });
        }
    }
}
