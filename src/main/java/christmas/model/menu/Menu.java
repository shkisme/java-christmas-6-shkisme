package christmas.model.menu;

import java.util.Objects;

public class Menu {
    private final String name;
    private final int price;
    private final String type;

    public Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public boolean isType(String type) {
        return Objects.equals(this.type, type);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
