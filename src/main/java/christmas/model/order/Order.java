package christmas.model.order;

import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;
import static christmas.model.order.Orders.MAX_COUNT;
import static christmas.model.order.Orders.MIN_COUNT;

import christmas.exception.InvalidOrderException;
import christmas.model.menu.Menu;
import java.util.Objects;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        validateCount(count);
        this.menu = menu;
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < MIN_COUNT || MAX_COUNT < count) {
            throw new InvalidOrderException(INVALID_COUNT);
        }
    }

    public int getPrice() {
        return menu.getPrice() * count;
    }

    public boolean isType(String type) {
        return menu.isType(type);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
