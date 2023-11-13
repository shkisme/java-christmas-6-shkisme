package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.presentation.Presentation;

public record BenefitsDetailsDto(int dayBenefits,
                                 int weekdayBenefits, int weekendDayBenefits,
                                 int specialDayBenefits, int presentationBenefits) {
    public static BenefitsDetailsDto of(Benefits benefits, Presentation presentation) {
        return new BenefitsDetailsDto(benefits.getDayBenefits(), benefits.getWeekdayBenefits(),
                benefits.getWeekendDayBenefits(), benefits.getSpecialDayBenefits(), presentation.getBenefits());
    }
}
