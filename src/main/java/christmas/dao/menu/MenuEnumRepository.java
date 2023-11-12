package christmas.dao.menu;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuData;
import java.util.Arrays;
import java.util.List;

public class MenuEnumRepository implements MenuRepository {
    @Override
    public List<Menu> findAll() {
        return null;
    }

    @Override
    public Menu findByName(String name) {
        return Arrays.stream(MenuData.values())
                .filter(menuData -> menuData.isName(name))
                .findFirst()
                .map(Menu::from)
                .orElseThrow(IllegalArgumentException::new);
    }
}
