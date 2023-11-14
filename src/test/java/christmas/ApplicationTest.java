package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_NUMBER;
import static christmas.exception.InvalidMenuException.InvalidMenuError.INVALID_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.dao.badge.BadgeEnumRepository;
import christmas.dao.badge.BadgeRepository;
import christmas.dao.menu.MenuEnumRepository;
import christmas.dao.menu.MenuRepository;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends NsTest {
    protected static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    protected static final String LINE_SEPARATOR = System.lineSeparator();

    protected final MenuRepository menuRepository = new MenuEnumRepository();
    protected final BadgeRepository badgeRepository = new BadgeEnumRepository();

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
            assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_NUMBER.getMessage());
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains(ERROR_MESSAGE_PREFIX, INVALID_FORMAT.getMessage());
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
