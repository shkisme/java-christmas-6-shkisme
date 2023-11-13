package christmas.model.benefits;

import christmas.model.order.OrderDate;
import christmas.model.order.Orders;
import christmas.model.presents.Presents;

public class ChristmasBenefits implements Benefits {
    private static final int CHRISTMAS_DAY = 25;
    private static final String WEEKDAY_MENU_NAME = "디저트";
    private static final String WEEKEND_MENU_NAME = "메인";

    private final Presents presents;
    private final Orders orders;
    private final OrderDate orderDate;

    public ChristmasBenefits(Presents presents, Orders orders, OrderDate orderDate) {
        this.presents = presents;
        this.orders = orders;
        this.orderDate = orderDate;
    }

    @Override
    public int getBeforeTotalPrice() {
        return orders.getTotalPrice();
    }

    @Override
    public int getDayBenefits() {
        if (orderDate.isBeforeOrEqual(CHRISTMAS_DAY)) {
            return 1000 + (orderDate.getDayOfMonth() - 1) * 100;
        }
        return 0;
    }

    @Override
    public int getWeekdayBenefits() {
        if (isWeekday()) {
            return orders.countByMenuType(WEEKDAY_MENU_NAME) * 2023;
        }
        return 0;
    }

    @Override
    public int getWeekendBenefits() {
        if (!isWeekday()) {
            return orders.countByMenuType(WEEKEND_MENU_NAME) * 2023;
        }
        return 0;
    }

    @Override
    public int getSpecialDayBenefits() {
        if (orderDate.isSpecialDay()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public boolean isApply() {
        return orders.hasBenefits();
    }

    private boolean isWeekday() {
        return orderDate.isWeekday();
    }

    @Override
    public int getTotalBenefits() {
        return getTotalBenefitsWithoutPresents() + presents.getBenefits();
    }

    @Override
    public int getAfterTotalPrice() {
        return getBeforeTotalPrice() - getTotalBenefitsWithoutPresents();
    }

    private int getTotalBenefitsWithoutPresents() {
        return getDayBenefits() + getWeekdayBenefits() + getWeekendBenefits() + getSpecialDayBenefits();
    }
}
