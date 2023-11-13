package christmas.model.presents;

import static java.util.Collections.unmodifiableList;

import christmas.model.menu.Menu;
import java.util.List;

public class ChristmasPresents implements Presents {
    private final List<Menu> presents;

    public ChristmasPresents(List<Menu> presents) {
        this.presents = presents;
    }

    @Override
    public int getPrice() {
        return presents.stream()
                .map(Menu::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public List<Menu> getMenus() {
        return unmodifiableList(presents);
    }
}
