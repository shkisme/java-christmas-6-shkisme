package christmas.dao.badge;

import christmas.model.badge.Badge;
import java.util.Optional;

public interface BadgeRepository {

    Optional<Badge> findByPrice(int price);
}
