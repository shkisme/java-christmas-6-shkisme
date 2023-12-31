package christmas.view;

import christmas.dto.BenefitsDetailsDto;
import christmas.dto.PresentsDto;
import christmas.model.badge.Badge;
import christmas.model.order.Orders;
import java.util.Optional;

public interface OutputView {

    String LINE_SEPARATOR = System.lineSeparator();

    void printResultMessage(int date);

    void printOrders(Orders orders);

    void printBeforePrice(int beforePrice);

    void printPresentations(PresentsDto dto);

    void printBenefitsDetails(boolean isApply, BenefitsDetailsDto dto);

    void printTotalBenefits(boolean isApply, int totalBenefits);

    void printAfterPrice(int afterPrice);

    void printBadge(Optional<Badge> badge);
}
