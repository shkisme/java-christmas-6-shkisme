package christmas;

import christmas.controller.Controller;
import christmas.dao.badge.BadgeEnumRepository;
import christmas.dao.menu.MenuEnumRepository;
import christmas.dao.menu.MenuRepository;
import christmas.model.benefits.ChristmasBenefits;
import christmas.model.menu.Menu;
import christmas.model.presentation.ChristmasPresentation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuEnumRepository();
        Menu presentation = menuRepository.findByName("샴페인")
                .orElseThrow(IllegalAccessError::new);
        Controller controller = new Controller(new InputView(), new OutputView(),
                new ChristmasBenefits(), new ChristmasPresentation(presentation),
                new MenuEnumRepository(), new BadgeEnumRepository());
        controller.run();
    }
}
