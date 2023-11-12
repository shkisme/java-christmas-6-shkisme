package christmas.model.benefits;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;

public class ChristmasBenefits implements Benefits {

    public int getChristmasDayBenefits() {
        return 0;
    }

    @Override
    public int getWeekdayBenefits(Orders orders) {
        return 0;
    }

    @Override
    public int getSpecialBenefits(Orders orders) {
        return 0;
    }

    public Menu getPresentationMenu() {
        return null;
    }

    public boolean isPresentation(Orders orders) {
        return 120_000 <= orders.getTotalPrice();
    }

    @Override
    public int getTotalBenefits(Orders orders) {
        return 0;
    }
}
