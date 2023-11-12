package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.MenuDto;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String MENU_FORMAT = "([가-힣a-zA-Z]+)-([0-9]+)";
    private static final Pattern MENU = Pattern.compile(MENU_FORMAT);
    private static final String MENU_DELIMITER = ",";
    private static final String COUNT_DELIMITER = "-";

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
            throw new IllegalArgumentException("문자가 입력되어 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateRange(String input) {
        int date = Integer.parseInt(input);
        if (date < 1 || 31 < date) {
            throw new IllegalArgumentException("날짜의 범위가 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

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
                        throw new IllegalArgumentException("메뉴의 형식이 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }
                });
    }

    private void validateCount(String input) {
        int menuCountSum = getMenuCountSum(input);
        if (20 < menuCountSum) {
            throw new IllegalArgumentException("메뉴의 개수 합이 20을 넘는 주문은 유효하지 않습니다. 다시 입력해 주세요.");
        }
    }

    private int getMenuCountSum(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER))
                .map(menu -> validateNonZero(menu[1]))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int validateNonZero(String input) {
        int value = Integer.parseInt(input);
        if (value == 0) {
            throw new IllegalArgumentException("메뉴의 개수가 0인 주문은 유효하지 않습니다. 다시 입력해 주세요.");
        }
        return value;
    }

    private void validateDuplicate(String input) {
        List<String> menuNames = getMenuNames(input);
        Set<String> nonDuplicateMenuNames = new HashSet<>(menuNames);
        if (menuNames.size() != nonDuplicateMenuNames.size()) {
            throw new IllegalArgumentException("메뉴가 중복되어 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private List<String> getMenuNames(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER)[0])
                .toList();
    }

    private List<MenuDto> getMenuDtos(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .map(menu -> menu.split(COUNT_DELIMITER))
                .map(menu -> MenuDto.of(menu[0], Integer.parseInt(menu[1])))
                .toList();
    }
}
