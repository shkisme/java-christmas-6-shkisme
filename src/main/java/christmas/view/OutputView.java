package christmas.view;

import christmas.dto.BenefitsDto;
import christmas.model.badge.Badge;
import christmas.model.order.Orders;
import java.util.Optional;

public interface OutputView {

    void printStartMessage();

    void printResultMessage();

    void printOrders(Orders orders);

    void printBenefits(BenefitsDto benefits);

    void printBadge(Optional<Badge> badge);
}
