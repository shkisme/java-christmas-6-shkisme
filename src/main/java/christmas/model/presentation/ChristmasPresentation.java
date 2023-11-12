package christmas.model.presentation;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;

public class ChristmasPresentation implements Presentation {
    private final Menu presentation;

    public ChristmasPresentation(Menu presentation) {
        this.presentation = presentation;
    }

    @Override
    public String getPresentationName() {
        return presentation.getName();
    }

    @Override
    public int getBenefit() {
        return presentation.getPrice();
    }

    @Override
    public boolean isPresentation(Orders orders) {
        return 120_000 <= orders.getTotalPrice();
    }
}
