package christmas.dto;

import christmas.model.benefits.Benefits;

public record BenefitsDetailsDto(int dayBenefits,
                                 int weekdayBenefits, int weekendDayBenefits,
                                 int specialDayBenefits, int presentationBenefits) {
    public static BenefitsDetailsDto from(Benefits benefits) {
        return new BenefitsDetailsDto(benefits.getDayBenefits(), benefits.getWeekdayBenefits(),
                benefits.getWeekendDayBenefits(), benefits.getSpecialDayBenefits(), benefits.getPresentationBenefits());
    }
}
