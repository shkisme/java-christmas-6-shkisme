package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.ORDER_RESTRICTIONS;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.exception.InvalidOrderException;
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
            throw new InvalidOrderException(ORDER_RESTRICTIONS);
        }
    }

    public int getTotalPrice() {
        return orders.stream()
                .map(Order::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean hasBenefits() {
        return 10_000 <= getTotalPrice();
    }

    public boolean isWeekday() {
        return weekday.contains(orderDate.getDayOfWeek());
    }

    public int countByMenuType(String menuType) {
        return orders.stream()
                .filter(order -> order.isType(menuType))
                .map(Order::getCount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isSpecialDay() {
        return isDayEqual(SUNDAY) || isDayEqual(25);
    }

    private boolean isDayEqual(DayOfWeek dayOfWeek) {
        return orderDate.getDayOfWeek() == dayOfWeek;
    }

    private boolean isDayEqual(int day) {
        return orderDate.getDayOfMonth() == day;
    }

    public boolean isDayBeforeOfEqual(int day) {
        return orderDate.getDayOfMonth() <= day;
    }

    public int getDayOfMonth() {
        return orderDate.getDayOfMonth();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
