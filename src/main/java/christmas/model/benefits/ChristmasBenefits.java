package christmas.model.benefits;

import christmas.model.order.Orders;
import java.time.LocalDate;

public class ChristmasBenefits implements Benefits {

    @Override
    public int getDayBenefits(LocalDate localDate) {
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
    public int getSpecialBenefits(Orders orders) {
        return 0;
    }

    @Override
    public int getTotalBenefits(Orders orders) {
        return 0;
    }
}
