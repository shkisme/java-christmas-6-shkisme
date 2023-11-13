package christmas.model.presentation;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import java.util.Optional;

public class ChristmasPresentation implements Presentation {
    private final Menu presentation;
    private final Orders orders;

    public ChristmasPresentation(Menu presentation, Orders orders) {
        this.presentation = presentation;
        this.orders = orders;
    }

    @Override
    public Optional<String> getPresentationName() {
        if (isPresentation()) {
            return Optional.ofNullable(presentation.getName());
        }
        return Optional.empty();
    }

    @Override
    public int getBenefits() {
        if (isPresentation()) {
            return presentation.getPrice();
        }
        return 0;
    }

    private boolean isPresentation() {
        return 120_000 <= orders.getTotalPrice();
    }
}
