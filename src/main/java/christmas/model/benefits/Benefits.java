package christmas.model.benefits;

import christmas.model.order.Orders;
import java.time.LocalDate;

public interface Benefits {

    int getDayBenefits(LocalDate localDate);

    int getWeekdayBenefits(Orders orders);

    int getWeekendDayBenefits(Orders orders);

    int getSpecialDayBenefits(LocalDate localDate);

    int getTotalBenefits(Orders orders);
}
