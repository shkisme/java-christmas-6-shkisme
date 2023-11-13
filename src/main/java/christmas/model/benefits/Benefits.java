package christmas.model.benefits;

import christmas.model.menu.Menu;
import java.util.List;

public interface Benefits {

    int getBeforeTotalPrice();

    int getDayBenefits();

    boolean isApply();

    int getWeekdayBenefits();

    int getWeekendBenefits();

    int getSpecialDayBenefits();

    int getAfterTotalPrice();

    int getTotalBenefits();

    List<Menu> getPresents();

    int getPresentBenefits();

    boolean isPresentsApply();
}
