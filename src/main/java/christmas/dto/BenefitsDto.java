package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.menu.Menu;
import java.util.List;

public record BenefitsDto(int beforeTotalPrice, List<Menu> presentations,
                          BenefitsDetailsDto benefitsDetailsDto,
                          int totalBenefits, int afterTotalPrice) {

    public static BenefitsDto of(boolean isApply, Benefits benefits) {
        return new BenefitsDto(benefits.getBeforeTotalPrice(),
                benefits.getPresentations(),
                BenefitsDetailsDto.of(isApply, benefits),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice());
    }
}
