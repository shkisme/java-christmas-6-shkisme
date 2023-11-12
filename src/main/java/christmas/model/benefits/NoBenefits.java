package christmas.model.benefits;

import christmas.model.order.Orders;
import java.time.LocalDate;

public class NoBenefits implements Benefits {
    @Override
    public int getDayBenefits(LocalDate localDate) {
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
    public int getTotalBenefits(Orders orders) {
        return 0;
    }
}
