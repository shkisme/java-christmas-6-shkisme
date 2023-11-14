package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasOutputViewTest extends ApplicationTest {

    @Test
    @DisplayName("안내 문구를 출력한다.")
    public void 안내_문구를_출력한다() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                    "<예약 규칙>",
                    "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.",
                    "음료만 주문할 수 없습니다.",
                    "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.",
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
            );
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
