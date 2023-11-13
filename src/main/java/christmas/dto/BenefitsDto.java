package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.menu.Menu;
import java.util.List;

public record BenefitsDto(int beforeTotalPrice, List<Menu> presentations,
                          BenefitsDetailsDto benefitsDetailsDto,
                          int totalBenefits, int afterTotalPrice) {

    public static BenefitsDto of(Benefits benefits, boolean isBenefits) {
        return new BenefitsDto(benefits.getBeforeTotalPrice(),
                benefits.getPresentations(),
                BenefitsDetailsDto.of(benefits, isBenefits),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice());
    }
}
