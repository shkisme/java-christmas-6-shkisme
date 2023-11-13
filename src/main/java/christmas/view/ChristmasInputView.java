package christmas.view;

import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_NUMBER;
import static christmas.exception.InvalidDateException.InvalidDateError.INVALID_RANGE;
import static christmas.exception.InvalidMenuException.InvalidMenuError.INVALID_FORMAT;
import static christmas.exception.InvalidMenuException.InvalidMenuError.ZERO_COUNT;
import static christmas.exception.InvalidOrderException.InvalidOrderError.DUPLICATE;
import static christmas.exception.InvalidOrderException.InvalidOrderError.INVALID_COUNT;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.MenuDto;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidMenuException;
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
            throw new InvalidDateException(INVALID_NUMBER);
        }
    }

    private void validateRange(String input) {
        int date = Integer.parseInt(input);
        if (date < MIN_DATE || MAX_DATE < date) {
            throw new InvalidDateException(INVALID_RANGE);
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
                        throw new InvalidMenuException(INVALID_FORMAT);
                    }
                });
    }

    private void validateCount(String input) {
        int menuCountSum = getMenuCountSum(input);
        if (MAX_MENU_COUNT < menuCountSum) {
            throw new InvalidOrderException(INVALID_COUNT);
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
            throw new InvalidMenuException(ZERO_COUNT);
        }
        return value;
    }

    private void validateDuplicate(String input) {
        List<String> menuNames = getMenuNames(input);
        Set<String> nonDuplicateMenuNames = new HashSet<>(menuNames);
        if (menuNames.size() != nonDuplicateMenuNames.size()) {
            throw new InvalidOrderException(DUPLICATE);
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
