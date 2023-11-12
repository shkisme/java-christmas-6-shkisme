package christmas.model.order;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Orders {
    private final List<Order> orders;
    private final LocalDate orderDate;

    public Orders(List<Order> orders, LocalDate orderDate) {
        validateOrder(orders);
        this.orders = orders;
        this.orderDate = orderDate;
    }

    private void validateOrder(List<Order> orders) {
        boolean isAllDrink = orders.stream()
                .allMatch(order -> order.isType("음료"));
        if (isAllDrink) {
            throw new IllegalArgumentException("음료만 주문하여 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getTotalPrice() {
        return orders.stream()
                .map(Order::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
