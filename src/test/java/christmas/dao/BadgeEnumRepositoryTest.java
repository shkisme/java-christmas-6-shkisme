package christmas.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.ApplicationTest;
import christmas.model.badge.Badge;
import christmas.model.badge.BadgeData;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeEnumRepositoryTest extends ApplicationTest {

    @ParameterizedTest
    @CsvSource(value = {
            "5_000, 별",
            "5_500, 별",
            "10_000, 트리",
            "15_000, 트리",
            "20_000, 산타",
            "25_000, 산타"
    })
    @DisplayName("가격에 해당하는 배지를 반환한다.")
    public void 가격에_해당하는_배지를_반환한다(int price, BadgeData badgeData) {
        //when
        Optional<Badge> findBadge = badgeRepository.findByPrice(price);

        //then
        assertThat(findBadge).isNotEmpty();
        assertEquals(badgeData.toString(), findBadge.get().getName());
    }

    @Test
    @DisplayName("가격에 해당하는 배지가 없다면 빈 값을 반환한다.")
    public void 가격에_해당하는_배지가_없다면_빈_값을_반환한다() {
        //given
        int zero = 0;

        //when
        Optional<Badge> findBadge = badgeRepository.findByPrice(zero);

        //then
        assertThat(findBadge).isEmpty();
    }
}
