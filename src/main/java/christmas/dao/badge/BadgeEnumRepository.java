package christmas.dao.badge;

import christmas.model.badge.Badge;
import java.util.List;
import java.util.Optional;

public class BadgeEnumRepository implements BadgeRepository {
    @Override
    public List<Badge> findAll() {
        return null;
    }

    @Override
    public Optional<Badge> findByName(String name) {
        return Optional.empty();
    }
}
