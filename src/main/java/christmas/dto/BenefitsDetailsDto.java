package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.presents.Presents;

public record BenefitsDetailsDto(int dayBenefits,
                                 int weekdayBenefits, int weekendBenefits,
                                 int specialDayBenefits, int presentsBenefits) {
    public static BenefitsDetailsDto of(Benefits benefits, Presents presents) {
        return new BenefitsDetailsDto(benefits.getDayBenefits(), benefits.getWeekdayBenefits(),
                benefits.getWeekendBenefits(), benefits.getSpecialDayBenefits(), presents.getPrice());
    }
}
