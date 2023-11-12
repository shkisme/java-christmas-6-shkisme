package christmas.dao.menu;

import christmas.model.menu.Menu;
import java.util.List;
import java.util.Optional;

public interface MenuRepository {

    List<Menu> findAll();

    Optional<Menu> findByName(String name);
}
