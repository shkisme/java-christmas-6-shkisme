package christmas.dao.badge;

import static java.util.Comparator.comparingInt;

import christmas.model.badge.Badge;
import christmas.model.badge.BadgeData;
import java.util.Arrays;
import java.util.Optional;

public class BadgeEnumRepository implements BadgeRepository {

    @Override
    public Optional<Badge> findByPrice(int price) {
        return Arrays.stream(BadgeData.values())
                .filter(badgeData -> badgeData.isMatch(price))
                .max(comparingInt(BadgeData::getPrice))
                .map(badgeData -> new Badge(badgeData.toString()));
    }
}
