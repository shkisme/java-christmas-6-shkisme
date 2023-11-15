package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_DATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApplicationTest extends NsTest {
    protected static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    protected static final String LINE_SEPARATOR = System.lineSeparator();

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    @DisplayName("크리스마스 입력 테스트")
    class ChristmasInputViewTest {
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

            @Test
            @DisplayName("날짜로 문자열이 주어지면 에러가 발생한다.")
            public void 날짜로_문자열이_주어지면_에러가_발생한다() {
                assertSimpleTest(() -> {
                    runException("a");
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_DATE.getMessage());
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
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_DATE.getMessage());
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
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
                });
            }

            @Test
            @DisplayName("메뉴의 개수가 0이면 에러가 발생한다.")
            public void 메뉴의_개수가_0이면_에러가_발생한다() {
                assertSimpleTest(() -> {
                    runException("3", "제로콜라-0");
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
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
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
                });
            }

            @Test
            @DisplayName("메뉴가 중복되면 에러가 발생한다.")
            public void 메뉴가_중복되면_에러가_발생한다() {
                assertSimpleTest(() -> {
                    runException("3", "초코케이크-1,초코케이크-2");
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
                });
            }

            @Test
            @DisplayName("메뉴 데이터가 없다면 에러가 발생한다.")
            public void 메뉴_데이터가_없다면_에러가_발생한다() {
                assertSimpleTest(() -> {
                    runException("3", "없는메뉴-1,초코케이크-2");
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
                });
            }

            @Test
            @DisplayName("음료만 입력하면 에러가 발생한다.")
            public void 음료만_입력하면_에러가_발생한다() {
                assertSimpleTest(() -> {
                    runException("3", "샴페인-1,제로콜라-2");
                    assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_ORDER.getMessage());
                });
            }
        }
    }

    @Nested
    @DisplayName("크리스마스 출력 테스트")
    class ChristmasOutputViewTest {
        @Test
        @DisplayName("안내 문구를 출력한다.")
        public void 안내_문구를_출력한다() {
            assertSimpleTest(() -> {
                run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
                assertThat(output()).contains("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
            });
        }

        @Test
        @DisplayName("적용된 이벤트가 하나도 없을 경우를 출력한다.")
        public void 적용된_이벤트가_하나도_없을_경우를_출력한다() {
            assertSimpleTest(() -> {
                run("26", "타파스-1,제로콜라-1");
                assertThat(output()).contains(
                        "<할인 전 총주문 금액>" + LINE_SEPARATOR + "8,500원",
                        "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                        "<혜택 내역>" + LINE_SEPARATOR + "없음",
                        "<총혜택 금액>" + LINE_SEPARATOR + "0원",
                        "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,500원",
                        "<12월 이벤트 배지>" + LINE_SEPARATOR + "없음"
                );
            });
        }

        @Test
        @DisplayName("주문 메뉴를 출력한다.")
        public void 주문_메뉴를_출력한다() {
            assertSimpleTest(() -> {
                run("26", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
                assertThat(output()).contains(
                        "<주문 메뉴>"
                                + LINE_SEPARATOR + "티본스테이크 1개"
                                + LINE_SEPARATOR + "바비큐립 1개"
                                + LINE_SEPARATOR + "초코케이크 2개"
                                + LINE_SEPARATOR + "제로콜라 1개"
                );
            });
        }

        @Test
        @DisplayName("예상되는 이벤트 결과를 출력한다.")
        public void 예상되는_이벤트_결과를_출력한다() {
            assertSimpleTest(() -> {
                run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
                assertThat(output()).contains(
                        "<할인 전 총주문 금액>" + LINE_SEPARATOR + "142,000원",
                        "<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
                        "<혜택 내역>"
                                + LINE_SEPARATOR + "크리스마스 디데이 할인: -1,200원"
                                + LINE_SEPARATOR + "평일 할인: -4,046원"
                                + LINE_SEPARATOR + "특별 할인: -1,000원"
                                + LINE_SEPARATOR + "증정 이벤트: -25,000원",
                        "<총혜택 금액>" + LINE_SEPARATOR + "-31,246원",
                        "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원",
                        "<12월 이벤트 배지>" + LINE_SEPARATOR + "산타"
                );
            });
        }
    }
}
