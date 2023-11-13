package christmas.model.benefits;

import christmas.model.order.Orders;

public class ChristmasBenefits implements Benefits {
    private final Orders orders;

    public ChristmasBenefits(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int getDayBenefits() {
        if (orders.isDayBeforeOfEqual(25)) {
            return 1000 + (orders.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    @Override
    public int getWeekdayBenefits() {
        if (isWeekday()) {
            return orders.countByMenuType("디저트") * 2023;
        }
        return 0;
    }

    @Override
    public int getWeekendDayBenefits() {
        if (!isWeekday()) {
            return orders.countByMenuType("메인") * 2023;
        }
        return 0;
    }

    private boolean isWeekday() {
        return orders.isWeekday();
    }

    @Override
    public int getSpecialDayBenefits() {
        if (orders.isSpecialDay()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public int getTotalBenefits() {
        return getDayBenefits() + getWeekdayBenefits() + getWeekendDayBenefits() + getSpecialDayBenefits();
    }
}
