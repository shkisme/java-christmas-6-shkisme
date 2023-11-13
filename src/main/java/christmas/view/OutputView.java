package christmas.view;

import christmas.dto.BenefitsDetailsDto;
import christmas.model.badge.Badge;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

public class OutputView {

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printResultMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printNoEventResult(Orders orders) {
        printBeforeTotalPrice(orders.getTotalPrice());
        printPresentationMenu(Optional.empty());
        System.out.println("\n<혜택 내역>\n없음");
        printTotalBenefits(0);
        printAfterTotalPrice(orders.getTotalPrice());
        printBadge(Optional.empty());
    }

    public void printOrders(Orders orders) {
        System.out.println("\n<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            System.out.println(order.getMenuName() + " " + order.getCount() + "개");
        }
    }

    public void printBeforeTotalPrice(int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(getFormattedPrice(totalPrice));
    }

    public void printPresentationMenu(Optional<String> presentationName) {
        System.out.println("\n<증정 메뉴>");
        if (presentationName.isEmpty()) {
            System.out.println("없음");
            return;
        }
        System.out.println(presentationName.get() + " 1개");
    }

    public void printBenefitsDetails(BenefitsDetailsDto benefitsDetailsDto) {
        System.out.println("\n<혜택 내역>");
        printDayBenefits(benefitsDetailsDto.dayBenefits());
        printWeekdayOrWeekendDayBenefits(benefitsDetailsDto.weekdayBenefits(), benefitsDetailsDto.weekendDayBenefits());
        printSpecialDayBenefits(benefitsDetailsDto.specialDayBenefits());
        printPresentationBenefits(benefitsDetailsDto.presentationBenefits());
    }

    public void printDayBenefits(int dayBenefits) {
        System.out.print("크리스마스 디데이 할인: ");
        printDiscountPrice(dayBenefits);
    }

    private void printWeekdayOrWeekendDayBenefits(int weekdayBenefits, int weekendDayBenefits) {
        if (weekendDayBenefits == 0) {
            printWeekdayBenefits(weekdayBenefits);
            return;
        }
        printWeekendDayBenefits(weekendDayBenefits);
    }

    public void printWeekdayBenefits(int weekdayBenefits) {
        System.out.print("평일 할인: ");
        printDiscountPrice(weekdayBenefits);
    }

    public void printWeekendDayBenefits(int weekendDayBenefits) {
        System.out.print("주말 할인: ");
        printDiscountPrice(weekendDayBenefits);
    }

    public void printSpecialDayBenefits(int specialDayBenefits) {
        System.out.print("특별 할인: ");
        printDiscountPrice(specialDayBenefits);
    }

    public void printPresentationBenefits(int benefit) {
        System.out.print("증정 이벤트: ");
        printDiscountPrice(benefit);
    }

    public void printAfterTotalPrice(int totalPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(getFormattedPrice(totalPrice));
    }

    public void printTotalBenefits(int totalBenefits) {
        System.out.println("\n<총혜택 금액>");
        printDiscountPrice(totalBenefits);
    }

    private void printDiscountPrice(int price) {
        if (price == 0) {
            System.out.println("없음");
            return;
        }
        System.out.println("-" + getFormattedPrice(price));
    }

    private String getFormattedPrice(int totalPrice) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(totalPrice) + "원";
    }

    public void printBadge(Optional<Badge> badge) {
        System.out.println("\n<12월 이벤트 배지>");
        if (badge.isEmpty()) {
            System.out.println("없음");
            return;
        }
        System.out.println(badge.get().getName());
    }
}
