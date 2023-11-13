package christmas.model.benefits;

public interface Benefits {

    int getBeforeTotalPrice();

    int getDayBenefits();

    boolean isApply();

    int getWeekdayBenefits();

    int getWeekendBenefits();

    int getSpecialDayBenefits();

    int getAfterTotalPrice();

    int getTotalBenefits();
}
