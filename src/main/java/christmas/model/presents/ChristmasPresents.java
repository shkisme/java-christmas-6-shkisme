package christmas.model.presents;

import static java.util.Collections.unmodifiableList;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import java.util.List;

public class ChristmasPresents implements Presents {
    private final List<Menu> presents;
    private final Orders orders;

    public ChristmasPresents(List<Menu> presents, Orders orders) {
        this.presents = presents;
        this.orders = orders;
    }

    @Override
    public int getBenefits() {
        return presents.stream()
                .map(Menu::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Menu> getMenus() {
        return unmodifiableList(presents);
    }
}
