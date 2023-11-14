package christmas.view;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import christmas.dto.BenefitsDetailsDto;
import christmas.dto.PresentsDto;
import christmas.model.badge.Badge;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

public class ChristmasOutputView implements OutputView {
    private static final String MENU_MESSAGE = "%s %d개";
    private static final String NOT_EXIST_MESSAGE = "없음";

    @Override
    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("<예약 규칙>");
        System.out.println("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("음료만 주문할 수 없습니다.");
        System.out.println("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n");
    }

    @Override
    public void printResultMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Override
    public void printOrders(Orders orders) {
        System.out.println("\n<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            System.out.println(MENU_MESSAGE.formatted(order.getMenuName(), order.getCount()));
        }
    }

    @Override
    public void printBeforePrice(int beforePrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(getFormattedPrice(beforePrice));
    }

    @Override
    public void printPresentations(PresentsDto dto) {
        System.out.println("\n<증정 메뉴>");
        if (!dto.isApply()) {
            System.out.println(NOT_EXIST_MESSAGE);
            return;
        }
        dto.menus().stream()
                .collect(groupingBy(Menu::getName, counting()))
                .forEach((name, count) -> System.out.println(MENU_MESSAGE.formatted(name, count)));
    }

    @Override
    public void printBenefitsDetails(boolean isApply, BenefitsDetailsDto dto) {
        System.out.println("\n<혜택 내역>");
        if (!isApply) {
            System.out.println(NOT_EXIST_MESSAGE);
            return;
        }
        printDayBenefits(dto.dayBenefits());
        printWeekdayOrWeekendBenefits(dto.weekdayBenefits(), dto.weekendBenefits());
        printSpecialDayBenefits(dto.specialDayBenefits());
        printPresentationBenefits(dto.presentsBenefits());
    }

    private void printDayBenefits(int dayBenefits) {
        System.out.print("크리스마스 디데이 할인: ");
        printDiscountPrice(dayBenefits);
    }

    private void printWeekdayOrWeekendBenefits(int weekdayBenefits, int weekendDayBenefits) {
        if (weekendDayBenefits == 0) {
            printWeekdayBenefits(weekdayBenefits);
            return;
        }
        printWeekendDayBenefits(weekendDayBenefits);
    }

    private void printWeekdayBenefits(int weekdayBenefits) {
        System.out.print("평일 할인: ");
        printDiscountPrice(weekdayBenefits);
    }

    private void printWeekendDayBenefits(int weekendDayBenefits) {
        System.out.print("주말 할인: ");
        printDiscountPrice(weekendDayBenefits);
    }

    private void printSpecialDayBenefits(int specialDayBenefits) {
        System.out.print("특별 할인: ");
        printDiscountPrice(specialDayBenefits);
    }

    private void printPresentationBenefits(int benefit) {
        System.out.print("증정 이벤트: ");
        printDiscountPrice(benefit);
    }

    @Override
    public void printAfterPrice(int afterPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(getFormattedPrice(afterPrice));
    }

    @Override
    public void printTotalBenefits(boolean isApply, int totalBenefits) {
        System.out.println("\n<총혜택 금액>");
        if (isApply) {
            printDiscountPrice(totalBenefits);
            return;
        }
        printDiscountPrice(0);
    }

    private void printDiscountPrice(int price) {
        if (price == 0) {
            System.out.println(getFormattedPrice(price));
            return;
        }
        System.out.println("-" + getFormattedPrice(price));
    }

    private String getFormattedPrice(int totalPrice) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(totalPrice) + "원";
    }

    @Override
    public void printBadge(Optional<Badge> badge) {
        System.out.println("\n<12월 이벤트 배지>");
        if (badge.isEmpty()) {
            System.out.println(NOT_EXIST_MESSAGE);
            return;
        }
        System.out.println(badge.get().getName());
    }
}
