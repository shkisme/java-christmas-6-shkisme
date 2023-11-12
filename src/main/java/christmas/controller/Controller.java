package christmas.controller;

import christmas.dao.badge.BadgeRepository;
import christmas.dao.menu.MenuRepository;
import christmas.dto.MenuDto;
import christmas.model.benefits.Benefits;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import christmas.model.presentation.Presentation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Benefits benefits;
    private final Presentation presentation;
    private final MenuRepository menuRepository;
    private final BadgeRepository badgeRepository;

    public Controller(InputView inputView, OutputView outputView, Benefits benefits, Presentation presentation,
                      MenuRepository menuRepository, BadgeRepository badgeRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefits = benefits;
        this.presentation = presentation;
        this.menuRepository = menuRepository;
        this.badgeRepository = badgeRepository;
    }

    public void run() {
        outputView.printStartMessage();
        LocalDate orderDate = getOrderDate();
        Orders orders = getOrders(orderDate);
        printResult(orders);
    }

    private LocalDate getOrderDate() {
        int date = inputView.readDate();
        return LocalDate.of(2023, 12, date);
    }

    private Orders getOrders(LocalDate orderDate) {
        while (true) {
            List<MenuDto> menuDtos = inputView.readMenu();
            try {
                return generateOrders(menuDtos, orderDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Orders generateOrders(List<MenuDto> menuDtos, LocalDate orderDate) {
        List<Order> orders = menuDtos.stream()
                .map(menuDto -> {
                    Menu menu = findMenuByName(menuDto.name());
                    return new Order(menu, menuDto.count());
                })
                .toList();
        return new Orders(orders, orderDate);
    }

    private Menu findMenuByName(String name) {
        return menuRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("메뉴판에 없는 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    private void printResult(Orders orders) {
        outputView.printResultMessage();
        if (orders.isNoBenefits()) {
            outputView.printNoEventResult(orders);
            return;
        }
        outputView.printOrders(orders);
        int beforePrice = orders.getTotalPrice();
        outputView.printBeforeTotalPrice(beforePrice);
        outputView.printPresentationMenu(presentation.getPresentationName(), presentation.isPresentation(orders));
        outputView.printDayBenefits(benefits.getDayBenefits(orders.getOrderDate()));
        printWeekOrWeekendBenefits(orders);
        outputView.printSpecialDayBenefits(benefits.getSpecialDayBenefits(orders.getOrderDate()));
        outputView.printPresentationBenefits(presentation.getBenefit(orders));
        outputView.printTotalBenefits(benefits.getTotalBenefits(orders) + presentation.getBenefit(orders));
        outputView.printAfterTotalPrice(beforePrice - benefits.getTotalBenefits(orders));
        outputView.printBadge(
                badgeRepository.findByPrice(benefits.getTotalBenefits(orders) + presentation.getBenefit(orders)));
    }

    private void printWeekOrWeekendBenefits(Orders orders) {
        if (orders.isWeekday()) {
            outputView.printWeekdayBenefits(benefits.getWeekdayBenefits(orders));
            return;
        }
        outputView.printWeekendDayBenefits(benefits.getWeekendDayBenefits(orders));
    }
}
