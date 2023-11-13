package christmas.model.benefits;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;
import christmas.model.presentation.Presentation;
import java.util.List;

public class ChristmasBenefits implements Benefits {
    private final Presentation presentation;
    private final Orders orders;

    public ChristmasBenefits(Presentation presentation, Orders orders) {
        this.presentation = presentation;
        this.orders = orders;
    }

    @Override
    public int getBeforeTotalPrice() {
        return orders.getTotalPrice();
    }

    @Override
    public int getDayBenefits() {
        if (isApplicable() && orders.isDayBeforeOfEqual(25)) {
            return 1000 + (orders.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    @Override
    public boolean isApplicable() {
        return orders.hasBenefits();
    }

    @Override
    public int getWeekdayBenefits() {
        if (isApplicable() && isWeekday()) {
            return orders.countByMenuType("디저트") * 2023;
        }
        return 0;
    }

    @Override
    public int getWeekendDayBenefits() {
        if (isApplicable() && !isWeekday()) {
            return orders.countByMenuType("메인") * 2023;
        }
        return 0;
    }

    private boolean isWeekday() {
        return orders.isWeekday();
    }

    @Override
    public int getSpecialDayBenefits() {
        if (isApplicable() && orders.isSpecialDay()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public int getTotalBenefits() {
        return getTotalBenefitsWithoutPresentation() + getPresentationBenefits();
    }

    @Override
    public int getAfterTotalPrice() {
        return orders.getTotalPrice() - getTotalBenefitsWithoutPresentation();
    }

    private int getTotalBenefitsWithoutPresentation() {
        return getDayBenefits() + getWeekdayBenefits() + getWeekendDayBenefits() + getSpecialDayBenefits();
    }

    @Override
    public List<Menu> getPresentations() {
        return presentation.getPresentations();
    }

    @Override
    public int getPresentationBenefits() {
        return presentation.getBenefits();
    }
}
