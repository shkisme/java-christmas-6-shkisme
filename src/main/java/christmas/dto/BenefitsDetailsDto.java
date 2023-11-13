package christmas.dto;

import christmas.model.benefits.Benefits;

public record BenefitsDetailsDto(int dayBenefits,
                                 int weekdayBenefits, int weekendDayBenefits,
                                 int specialDayBenefits, int presentationBenefits, boolean isBenefits) {
    public static BenefitsDetailsDto of(Benefits benefits, boolean isBenefits) {
        return new BenefitsDetailsDto(benefits.getDayBenefits(), benefits.getWeekdayBenefits(),
                benefits.getWeekendDayBenefits(), benefits.getSpecialDayBenefits(), benefits.getPresentationBenefits(),
                isBenefits);
    }
}
