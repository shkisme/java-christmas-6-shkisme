package christmas.dto;

import christmas.model.benefits.Benefits;

public record BenefitsDto(int beforeTotalPrice, PresentsDto presents,
                          BenefitsDetailsDto benefitsDetailsDto,
                          int totalBenefits, int afterTotalPrice) {

    public static BenefitsDto of(boolean isApply, Benefits benefits) {
        return new BenefitsDto(benefits.getBeforeTotalPrice(),
                PresentsDto.of(benefits.isPresentsApply(), benefits.getPresents()),
                BenefitsDetailsDto.of(isApply, benefits),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice());
    }
}
