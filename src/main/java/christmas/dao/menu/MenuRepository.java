package christmas.dao.menu;

import christmas.model.menu.Menu;
import java.util.Optional;

public interface MenuRepository {

    Optional<Menu> findByName(String name);
}
