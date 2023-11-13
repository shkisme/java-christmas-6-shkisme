package christmas.model.presentation;

import christmas.model.menu.Menu;
import java.util.List;

public interface Presentation {

    List<Menu> getPresentations();

    int getBenefits();
}
