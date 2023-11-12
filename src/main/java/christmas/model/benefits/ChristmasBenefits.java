package christmas.model.benefits;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;

public class ChristmasBenefits implements Benefits {

    private final Menu presentation;

    public ChristmasBenefits(Menu presentation) {
        this.presentation = presentation;
    }

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

    @Override
    public int getPresentationBenefits(Orders orders) {
        return 0;
    }

    @Override
    public int getTotalBenefits(Orders orders) {
        return 0;
    }
}
