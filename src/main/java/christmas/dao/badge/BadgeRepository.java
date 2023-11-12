package christmas.dao.badge;

import christmas.model.badge.Badge;
import java.util.List;
import java.util.Optional;

public interface BadgeRepository {

    List<Badge> findAll();

    Optional<Badge> findByName(String name);
}
