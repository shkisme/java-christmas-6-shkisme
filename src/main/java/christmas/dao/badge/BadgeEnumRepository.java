package christmas.dao.badge;

import christmas.model.badge.Badge;
import christmas.model.badge.BadgeData;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class BadgeEnumRepository implements BadgeRepository {

    @Override
    public Optional<Badge> findByPrice(int price) {
        return Arrays.stream(BadgeData.values())
                .filter(badgeData -> badgeData.isMatch(price))
                .max(Comparator.comparingInt(BadgeData::getPrice))
                .map(Badge::from);
    }
}
