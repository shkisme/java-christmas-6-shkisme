package christmas.model.order;

import christmas.model.menu.Menu;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public boolean isType(String type) {
        return menu.isType(type);
    }
}
