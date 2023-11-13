package christmas;

import christmas.controller.ChristmasController;
import christmas.dao.badge.BadgeEnumRepository;
import christmas.dao.badge.BadgeRepository;
import christmas.dao.menu.MenuEnumRepository;
import christmas.dao.menu.MenuRepository;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController(getInputView(), getOutputView(),
                getMenuRepository(), getBadgeRepository());
        christmasController.run();
    }

    private static InputView getInputView() {
        return new ChristmasInputView();
    }

    private static OutputView getOutputView() {
        return new ChristmasOutputView();
    }

    private static MenuRepository getMenuRepository() {
        return new MenuEnumRepository();
    }

    private static BadgeRepository getBadgeRepository() {
        return new BadgeEnumRepository();
    }
}
