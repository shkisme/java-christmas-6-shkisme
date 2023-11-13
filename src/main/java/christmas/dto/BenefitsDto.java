package christmas.dto;

import christmas.model.benefits.Benefits;
import christmas.model.menu.Menu;
import java.util.List;

public record BenefitsDto(int beforeTotalPrice, List<Menu> presentations,
                          BenefitsDetailsDto benefitsDetailsDto,
                          int totalBenefits, int afterTotalPrice) {

    public static BenefitsDto from(Benefits benefits) {
        return new BenefitsDto(benefits.getBeforeTotalPrice(),
                benefits.getPresentations(),
                BenefitsDetailsDto.from(benefits),
                benefits.getTotalBenefits(),
                benefits.getAfterTotalPrice());
    }
}
