package christmas.controller;

import static christmas.exception.InvalidMenuException.InvalidMenuError.NOT_EXIST;

import christmas.dao.badge.BadgeRepository;
import christmas.dao.menu.MenuRepository;
import christmas.dto.BenefitsDetailsDto;
import christmas.dto.MenuDto;
import christmas.dto.PresentsDto;
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
import christmas.model.price.Price;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Optional;

public class ChristmasController {
    private static final String PRESENTS_MENU_NAME = "샴페인";

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

        Presents presents = createPresentation();
        Benefits benefits = new ChristmasBenefits(orders, orderDate);

        printOrders(orders);
        printBenefits(orders, benefits, presents);
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

    private Presents createPresentation() {
        Menu presentation = findMenuByName(PRESENTS_MENU_NAME);
        return new ChristmasPresents(List.of(presentation));
    }

    private Menu findMenuByName(String name) {
        return menuRepository.findByName(name)
                .orElseThrow(() -> new InvalidMenuException(NOT_EXIST));
    }

    private void printOrders(Orders orders) {
        outputView.printResultMessage();
        outputView.printOrders(orders);
    }

    private void printBenefits(Orders orders, Benefits benefits, Presents presents) {
        Price price = new Price(orders.getPrice(), benefits.getTotalBenefits(), presents.getPrice());
        boolean isBenefitsApply = orders.hasBenefits();
        boolean isPresentsApply = orders.hasPresents();

        outputView.printBeforePrice(price.getBeforePrice());
        outputView.printPresentations(PresentsDto.of(isPresentsApply, presents.getMenus()));
        outputView.printBenefitsDetails(isBenefitsApply, BenefitsDetailsDto.of(benefits, presents));
        outputView.printTotalBenefits(isBenefitsApply, price.getTotalBenefits());
        outputView.printAfterPrice(price.getAfterPrice());
        outputView.printBadge(findBadgeByBenefits(isBenefitsApply, price.getTotalBenefits()));
    }

    private Optional<Badge> findBadgeByBenefits(boolean hasBenefits, int totalBenefits) {
        if (hasBenefits) {
            return badgeRepository.findByPrice(totalBenefits);
        }
        return Optional.empty();
    }
}
