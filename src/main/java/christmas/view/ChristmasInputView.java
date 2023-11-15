package christmas.view;

import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_DATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.MenuDto;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidOrderException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChristmasInputView implements InputView {
    private static final String MENU_FORMAT = "([가-힣a-zA-Z]+)-([0-9]+)";
    private static final Pattern MENU = Pattern.compile(MENU_FORMAT);
    private static final String MENU_DELIMITER = ",";
    private static final String COUNT_DELIMITER = "-";

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int MAX_MENU_COUNT = 20;
    private static final int MENU_NAME_POSITION = 0;
    private static final int MENU_COUNT_POSITION = 1;

    @Override
    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("<예약 규칙>");
        System.out.println("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        System.out.println("음료만 주문할 수 없습니다.");
        System.out.println("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n");
    }

    @Override
    public int readDate() {
        while (true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine().trim();
            try {
                validateDate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateDate(String input) {
        validateNumber(input);
        validateRange(input);
    }

    private void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidDateException(INVALID_DATE);
        }
    }

    private void validateRange(String input) {
        int date = Integer.parseInt(input);
        if (date < MIN_DATE || MAX_DATE < date) {
            throw new InvalidDateException(INVALID_DATE);
        }
    }

    @Override
    public List<MenuDto> readMenu() {
        while (true) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine().trim();
            try {
                validateMenu(input);
                return getMenuDtos(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateMenu(String input) {
        validateFormat(input);
        validateCount(input);
        validateDuplicate(input);
    }

    private void validateFormat(String input) {
        Arrays.stream(input.split(MENU_DELIMITER))
                .forEach(menu -> {
                    Matcher matcher = MENU.matcher(menu);
                    if (!matcher.matches()) {
                        throw new InvalidOrderException(INVALID_ORDER);
                    }
                });
    }

    private void validateCount(String input) {
        int menuCountSum = getMenuCountSum(input);
        if (MAX_MENU_COUNT < menuCountSum) {
            throw new InvalidOrderException(INVALID_ORDER);
        }
    }

    private int getMenuCountSum(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER))
                .map(menu -> validateNonZero(menu[MENU_COUNT_POSITION]))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int validateNonZero(String input) {
        int value = Integer.parseInt(input);
        if (value == 0) {
            throw new InvalidOrderException(INVALID_ORDER);
        }
        return value;
    }

    private void validateDuplicate(String input) {
        List<String> menuNames = getMenuNames(input);
        Set<String> nonDuplicateMenuNames = new HashSet<>(menuNames);
        if (menuNames.size() != nonDuplicateMenuNames.size()) {
            throw new InvalidOrderException(INVALID_ORDER);
        }
    }

    private List<String> getMenuNames(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER)[MENU_NAME_POSITION])
                .toList();
    }

    private List<MenuDto> getMenuDtos(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER))
                .map(menu -> MenuDto.of(menu[MENU_NAME_POSITION], Integer.parseInt(menu[MENU_COUNT_POSITION])))
                .toList();
    }
}
