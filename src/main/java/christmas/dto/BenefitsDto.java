package christmas.dto;

import christmas.model.price.Price;

public record BenefitsDto(boolean isApply, int beforePrice, int totalBenefits, int afterPrice,
                          BenefitsDetailsDto benefitsDetailsDto, PresentsDto presents) {

    public static BenefitsDto of(boolean isApply, Price price,
                                 BenefitsDetailsDto benefitsDetails, PresentsDto presents) {
        return new BenefitsDto(isApply, price.getBeforePrice(), price.getTotalBenefits(), price.getAfterPrice(),
                benefitsDetails, presents);
    }
}
