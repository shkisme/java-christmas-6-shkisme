package christmas.dto;

public record BenefitsDto(boolean isApply, int beforeTotalPrice, int totalBenefits, int afterTotalPrice,
                          BenefitsDetailsDto benefitsDetailsDto, PresentsDto presents) {

    public static BenefitsDto of(boolean isApply, int beforeTotalPrice, int totalBenefits, int afterTotalPrice,
                                 BenefitsDetailsDto benefitsDetails, PresentsDto presents) {
        return new BenefitsDto(isApply, beforeTotalPrice, totalBenefits, afterTotalPrice,
                benefitsDetails, presents);
    }
}
