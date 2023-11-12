package christmas.model.menu;

import static christmas.model.menu.MenuType.디저트;
import static christmas.model.menu.MenuType.메인;
import static christmas.model.menu.MenuType.애피타이저;
import static christmas.model.menu.MenuType.음료;

import java.util.Objects;

public enum MenuData {
    // 애피타이저
    양송이스프(6_000, 애피타이저),
    타파스(5_500, 애피타이저),
    시저샐러드(8_000, 애피타이저),
    // 메인
    티본스테이크(55_000, 메인),
    바비큐립(54_000, 메인),
    해산물파스타(35_000, 메인),
    크리스마스파스타(25_000, 메인),
    // 디저트
    초코케이크(15_000, 디저트),
    아이스크림(5_000, 디저트),
    // 음료
    제로콜라(3_000, 음료),
    레드와인(60_000, 음료),
    샴페인(25_000, 음료),
    ;

    private final int price;
    private final MenuType type;

    MenuData(int price, MenuType type) {
        this.price = price;
        this.type = type;
    }

    public boolean isName(String name) {
        return Objects.equals(getName(), name);
    }

    public String getName() {
        return this.toString();
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type.toString();
    }
}
