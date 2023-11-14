package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.DUPLICATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_TYPE;
import static java.util.Collections.unmodifiableList;

import christmas.exception.InvalidOrderException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {
    static final int MIN_COUNT = 1;
    static final int MAX_COUNT = 20;
    private static final String RESTRICTION_MENU_NAME = "음료";
    private static final int BENEFITS_PRICE = 10_000;
    private static final int PRESENTS_PRICE = 120_000;

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateType(orders);
        validateDuplicate(orders);
        validateCount(orders);
        this.orders = orders;
    }

    private void validateType(List<Order> orders) {
        boolean isAllDrink = orders.stream().allMatch(order -> order.isType(RESTRICTION_MENU_NAME));
        if (isAllDrink) {
            throw new InvalidOrderException(INVALID_TYPE);
        }
    }

    private void validateDuplicate(List<Order> orders) {
        Set<Order> nonDuplicateOrder = new HashSet<>(orders);
        if (nonDuplicateOrder.size() != orders.size()) {
            throw new InvalidOrderException(DUPLICATE);
        }
    }

    private void validateCount(List<Order> orders) {
        int menuCountSum = getMenuCountSum(orders);
        if (menuCountSum < MIN_COUNT || MAX_COUNT < menuCountSum) {
            throw new InvalidOrderException(INVALID_COUNT);
        }
    }

    private int getMenuCountSum(List<Order> orders) {
        return orders.stream()
                .map(Order::getCount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getPrice() {
        return orders.stream()
                .map(Order::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean hasBenefits() {
        return BENEFITS_PRICE <= getPrice();
    }

    public boolean hasPresents() {
        return PRESENTS_PRICE <= getPrice();
    }

    public int countByMenuType(String menuType) {
        return orders.stream()
                .filter(order -> order.isType(menuType))
                .map(Order::getCount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<Order> getOrders() {
        return unmodifiableList(orders);
    }
}
