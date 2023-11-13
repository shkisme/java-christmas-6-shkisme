package christmas.dto;

import christmas.model.benefits.Benefits;

public record BenefitsDetailsDto(boolean isApply, int dayBenefits,
                                 int weekdayBenefits, int weekendBenefits,
                                 int specialDayBenefits, int presentationBenefits) {
    public static BenefitsDetailsDto of(boolean isApply, Benefits benefits) {
        return new BenefitsDetailsDto(isApply, benefits.getDayBenefits(), benefits.getWeekdayBenefits(),
                benefits.getWeekendBenefits(), benefits.getSpecialDayBenefits(), benefits.getPresentationBenefits());
    }
}
