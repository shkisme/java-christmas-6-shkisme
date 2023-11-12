package christmas.dao.menu;

import christmas.model.menu.Menu;
import java.util.List;

public interface MenuRepository {

    List<Menu> findAll();

    Menu findByName(String name);
}
