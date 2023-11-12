package christmas.model.order;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Orders {
    private static final List<DayOfWeek> weekday = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SUNDAY);

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

    public int getTotalPrice() {
        return orders.stream()
                .map(Order::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isNoBenefits() {
        return getTotalPrice() < 10_000;
    }

    public boolean isWeekday() {
        return weekday.contains(orderDate.getDayOfWeek());
    }

    public int countByMenuType(String menuType) {
        return (int) orders.stream()
                .filter(order -> order.isType(menuType))
                .count();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
