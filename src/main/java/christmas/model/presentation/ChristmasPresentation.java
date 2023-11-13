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
    public List<Menu> getPresentations() {
        if (isPresentations()) {
            return unmodifiableList(presentations);
        }
        return List.of();
    }

    @Override
    public int getBenefits() {
        if (isPresentations()) {
            return presentations.stream()
                    .map(Menu::getPrice)
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return 0;
    }

    private boolean isPresentations() {
        return 120_000 <= orders.getTotalPrice();
    }
}
