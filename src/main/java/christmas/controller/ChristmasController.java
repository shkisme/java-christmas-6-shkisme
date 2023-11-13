package christmas.controller;

import static christmas.exception.InvalidMenuException.InvalidMenuError.NOT_EXIST;

import christmas.dao.badge.BadgeRepository;
import christmas.dao.menu.MenuRepository;
import christmas.dto.BenefitsDto;
import christmas.dto.MenuDto;
import christmas.exception.InvalidMenuException;
import christmas.model.badge.Badge;
import christmas.model.benefits.Benefits;
import christmas.model.benefits.ChristmasBenefits;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.model.order.Orders;
import christmas.model.presents.ChristmasPresents;
import christmas.model.presents.Presents;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Optional;

public class ChristmasController {
    private static final String PRESENTATION_MENU_NAME = "샴페인";

    private final InputView inputView;
    private final OutputView outputView;
    private final MenuRepository menuRepository;
    private final BadgeRepository badgeRepository;

    public ChristmasController(InputView inputView, OutputView outputView,
                               MenuRepository menuRepository, BadgeRepository badgeRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuRepository = menuRepository;
        this.badgeRepository = badgeRepository;
    }

    public void run() {
        outputView.printStartMessage();
        OrderDate orderDate = readDate();
        Orders orders = readOrders();

        Presents presents = createPresentation(orders);
        Benefits benefits = new ChristmasBenefits(presents, orders, orderDate);

        printOrders(orders);
        printBenefits(benefits, presents);
    }

    private OrderDate readDate() {
        int date = inputView.readDate();
        return OrderDate.of(2023, 12, date);
    }

    private Orders readOrders() {
        while (true) {
            List<MenuDto> menuDtos = inputView.readMenu();
            try {
                return generateOrders(menuDtos);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Orders generateOrders(List<MenuDto> menuDtos) {
        List<Order> orders = menuDtos.stream()
                .map(menuDto -> {
                    Menu menu = findMenuByName(menuDto.name());
                    return new Order(menu, menuDto.count());
                })
                .toList();
        return new Orders(orders);
    }

    private Presents createPresentation(Orders orders) {
        Menu presentation = findMenuByName(PRESENTATION_MENU_NAME);
        return new ChristmasPresents(List.of(presentation), orders);
    }

    private Menu findMenuByName(String name) {
        return menuRepository.findByName(name)
                .orElseThrow(() -> new InvalidMenuException(NOT_EXIST));
    }

    private void printOrders(Orders orders) {
        outputView.printResultMessage();
        outputView.printOrders(orders);
    }

    private void printBenefits(Benefits benefits, Presents presents) {
        outputView.printBenefits(BenefitsDto.of(benefits.isApply(), benefits, presents));
        outputView.printBadge(findBadgeByBenefits(benefits));
    }

    private Optional<Badge> findBadgeByBenefits(Benefits benefits) {
        if (benefits.isApply()) {
            return badgeRepository.findByPrice(benefits.getTotalBenefits());
        }
        return Optional.empty();
    }
}
