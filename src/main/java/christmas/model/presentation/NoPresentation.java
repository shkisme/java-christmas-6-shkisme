package christmas.model.presentation;

import christmas.model.order.Orders;

public class NoPresentation implements Presentation {
    @Override
    public String getPresentationName() {
        return null;
    }

    @Override
    public int getBenefit() {
        return 0;
    }

    @Override
    public boolean isPresentation(Orders orders) {
        return false;
    }
}
