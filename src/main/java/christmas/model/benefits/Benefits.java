package christmas.model.benefits;

import christmas.model.menu.Menu;
import java.util.List;

public interface Benefits {

    int getBeforeTotalPrice();

    int getDayBenefits();

    boolean isApplicable();

    int getWeekdayBenefits();

    int getWeekendDayBenefits();

    int getSpecialDayBenefits();

    int getAfterTotalPrice();

    int getTotalBenefits();

    List<Menu> getPresentations();

    int getPresentationBenefits();
}
