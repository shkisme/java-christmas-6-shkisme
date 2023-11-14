package christmas.view;

import christmas.dto.MenuDto;
import java.util.List;

public interface InputView {

    void printStartMessage();

    int readDate();

    List<MenuDto> readMenu();
}
