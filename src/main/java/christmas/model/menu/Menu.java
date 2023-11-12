package christmas.model.menu;

public class Menu {
    private final String name;
    private final int price;
    private final String type;

    public Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu from(MenuData menuData) {
        return new Menu(menuData.getName(),
                menuData.getPrice(),
                menuData.getType());
    }
}
