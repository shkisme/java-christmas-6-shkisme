package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.ORDER_RESTRICTIONS;
import static java.util.Collections.unmodifiableList;

import christmas.exception.InvalidOrderException;
import java.util.List;

public class Orders {
    private static final String RESTRICTION_MENU_NAME = "음료";
    private static final int BENEFITS_PRICE = 10_000;
    private static final int PRESENTATION_PRICE = 120_000;

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    private void validateOrders(List<Order> orders) {
        boolean isAllDrink = orders.stream().allMatch(order -> order.isType(RESTRICTION_MENU_NAME));
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
        return BENEFITS_PRICE <= getTotalPrice();
    }

    public boolean hasPresentations() {
        return PRESENTATION_PRICE <= getTotalPrice();
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
