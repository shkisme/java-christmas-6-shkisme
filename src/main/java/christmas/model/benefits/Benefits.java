package christmas.model.benefits;

import christmas.model.menu.Menu;
import christmas.model.order.Orders;

public interface Benefits {

    int getWeekdayBenefits(Orders orders);

    int getSpecialBenefits(Orders orders);



    int getTotalBenefits(Orders orders);
}
