package christmas;

import christmas.controller.Controller;
import christmas.dao.badge.BadgeEnumRepository;
import christmas.dao.menu.MenuEnumRepository;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new InputView(), new OutputView(),
                new MenuEnumRepository(), new BadgeEnumRepository());
        controller.run();
    }
}
