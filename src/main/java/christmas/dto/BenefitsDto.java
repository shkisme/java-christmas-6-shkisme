package christmas.dto;

import christmas.model.benefits.Benefits;

public record BenefitsDto(int beforeTotalPrice, PresentationsDto presentations,
                          BenefitsDetailsDto benefitsDetailsDto,
                          int totalBenefits, int afterTotalPrice) {

    public static BenefitsDto of(boolean isApply, Benefits benefits) {
        return new BenefitsDto(benefits.getBeforeTotalPrice(),
                PresentationsDto.of(benefits.isPresentApply(), benefits.getPresentations()),
                BenefitsDetailsDto.of(isApply, benefits),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice());
    }
}
