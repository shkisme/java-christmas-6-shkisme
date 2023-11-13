package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.presents.Presents;

public record BenefitsDto(
        boolean isApply, int beforeTotalPrice, int totalBenefits, int afterTotalPrice,
        BenefitsDetailsDto benefitsDetailsDto, PresentsDto presents
) {

    public static BenefitsDto of(boolean isApply, Benefits benefits, Presents presents) {
        return new BenefitsDto(isApply,
                benefits.getBeforeTotalPrice(),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice(),
                BenefitsDetailsDto.of(benefits, presents),
                PresentsDto.of(presents.isApply(), presents.getMenus())
        );
    }
}
