package christmas.view;

import christmas.model.order.Order;
import christmas.model.order.Orders;
import java.text.NumberFormat;
import java.util.Locale;

public class OutputView {

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printResultMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printNoEventResult(Orders orders) {
        printOrders(orders);
        printBeforeTotalPrice(orders.getTotalPrice());
        System.out.println("\n<증정 메뉴>\n없음");
        System.out.println("\n<혜택 내역>\n없음");
        System.out.println("\n<총혜택 금액>\n0원");
        printAfterTotalPrice(orders.getTotalPrice());
        System.out.println("\n12월 이벤트 배지\n없음");
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

    public void printPresentationMenu(String presentationName, boolean isPresentation) {
        System.out.println("\n<증정 메뉴>");
        if (isPresentation) {
            System.out.println(presentationName + " 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printDayBenefits(int dayBenefits) {
        System.out.println("\n<혜택 내역>");
        System.out.print("크리스마스 디데이 할인: ");
        printPrice(dayBenefits);
    }

    public void printAfterTotalPrice(int totalPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(getFormattedPrice(totalPrice));
    }

    public void printWeekdayBenefits(int weekdayBenefits) {
        System.out.print("평일 할인: ");
        printPrice(weekdayBenefits);
    }

    public void printWeekendDayBenefits(int weekendDayBenefits) {
        System.out.print("주말 할인: ");
        printPrice(weekendDayBenefits);
    }

    public void printSpecialDayBenefits(int specialDayBenefits) {
        System.out.print("특별 할인: ");
        printPrice(specialDayBenefits);
    }

    private void printPrice(int price) {
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
}
