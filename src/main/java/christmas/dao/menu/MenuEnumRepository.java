package christmas.dao.menu;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuData;
import java.util.Arrays;
import java.util.Optional;

public class MenuEnumRepository implements MenuRepository {

    @Override
    public Optional<Menu> findByName(String name) {
        return Arrays.stream(MenuData.values())
                .filter(menuData -> menuData.isName(name))
                .findFirst()
                .map(menuData -> new Menu(menuData.getName(), menuData.getPrice(), menuData.getType()));
    }
}
