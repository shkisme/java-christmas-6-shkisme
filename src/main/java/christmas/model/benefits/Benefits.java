package christmas.model.benefits;

import christmas.model.order.Orders;

public interface Benefits {

    int getWeekdayBenefits(Orders orders);

    int getSpecialBenefits(Orders orders);

    int getPresentationBenefits(Orders orders);

    int getTotalBenefits(Orders orders);
}
