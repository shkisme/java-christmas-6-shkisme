package christmas.model.presents;

import christmas.model.menu.Menu;
import java.util.List;

public interface Presents {

    List<Menu> getMenus();

    int getPrice();
}
