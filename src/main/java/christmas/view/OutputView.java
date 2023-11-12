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

    public void printOrders(Orders orders) {
        System.out.println("\n<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            System.out.println(order.getMenuName() + " " + order.getCount() + "개");
        }
    }

    public void printBeforeTotalPrice(int totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(getFormattedPrice(totalPrice) + "원");
    }

    private String getFormattedPrice(int totalPrice) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(totalPrice);
    }
}
