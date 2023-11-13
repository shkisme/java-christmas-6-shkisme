package christmas.model.presentation;

import static java.util.Collections.unmodifiableList;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import java.util.List;

public class ChristmasPresentation implements Presentation {
    private final List<Menu> presentations;
    private final Orders orders;

    public ChristmasPresentation(List<Menu> presentations, Orders orders) {
        this.presentations = presentations;
        this.orders = orders;
    }

    @Override
    public List<Menu> getMenus() {
        return unmodifiableList(presentations);
    }

    @Override
    public int getBenefits() {
        return presentations.stream()
                .map(Menu::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public boolean isApply() {
        return orders.hasPresentations();
    }
}
