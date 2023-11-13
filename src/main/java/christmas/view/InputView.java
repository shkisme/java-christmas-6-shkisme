package christmas.view;

import christmas.dto.MenuDto;
import java.util.List;

public interface InputView {

    int readDate();

    List<MenuDto> readMenu();
}
