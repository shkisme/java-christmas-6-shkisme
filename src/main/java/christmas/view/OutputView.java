package christmas.view;

import christmas.model.order.Order;
import christmas.model.order.Orders;

public class OutputView {

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printResultMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printOrders(Orders orders) {
        for (Order order : orders.getOrders()) {
            System.out.println(order.getMenuName() + " " + order.getCount() + "개");
        }
    }
}
