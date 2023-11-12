package christmas.model.order;

import java.time.LocalDate;
import java.util.List;

public class Orders {
    private final List<Order> orders;
    private final LocalDate orderDate;

    public Orders(List<Order> orders, LocalDate orderDate) {
        this.orders = orders;
        this.orderDate = orderDate;
    }
}
