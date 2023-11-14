package christmas.dao;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.ApplicationTest;
import christmas.model.menu.Menu;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuEnumRepositoryTest extends ApplicationTest {

    @Test
    @DisplayName("이름으로 메뉴를 조회한다.")
    public void 이름으로_메뉴를_조회한다() {
        //given
        String existName = "샴페인";

        //when
        Optional<Menu> findMenu = menuRepository.findByName(existName);

        //then
        assertThat(findMenu).isNotEmpty();
        assertThat(findMenu.get().getName()).isEqualTo(existName);
    }

    @Test
    @DisplayName("이름에 해당하는 메뉴가 없을 경우 빈 값을 반환한다.")
    public void 이름에_해당하는_메뉴가_없을_경우_빈_값을_반환한다() {
        //given
        String nonExistName = "없음";

        //when
        Optional<Menu> findMenu = menuRepository.findByName(nonExistName);

        //then
        assertThat(findMenu).isEmpty();
    }
}
