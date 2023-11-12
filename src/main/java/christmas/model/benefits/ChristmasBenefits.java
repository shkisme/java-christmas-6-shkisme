package christmas.model.benefits;

import static java.time.DayOfWeek.SUNDAY;

import christmas.model.order.Orders;
import java.time.LocalDate;

public class ChristmasBenefits implements Benefits {

    @Override
    public int getDayBenefits(LocalDate localDate) {
        if (25 < localDate.getDayOfMonth()) {
            return 0;
        }
        return 1000 + (localDate.getDayOfMonth() - 1) * 100;
    }

    @Override
    public int getWeekdayBenefits(Orders orders) {
        return orders.countByMenuType("디저트") * 2023;
    }

    @Override
    public int getWeekendDayBenefits(Orders orders) {
        return orders.countByMenuType("메인") * 2023;
    }

    @Override
    public int getSpecialDayBenefits(LocalDate localDate) {
        if (localDate.getDayOfWeek() == SUNDAY || localDate.getDayOfMonth() == 25) {
            return 1000;
        }
        return 0;
    }

    @Override
    public int getTotalBenefits(Orders orders) {
        return 0;
    }
}
