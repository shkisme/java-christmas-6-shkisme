package christmas.dto;

import christmas.model.menu.Menu;
import java.util.List;

public record PresentsDto(boolean isApply, List<Menu> menus) {

    public static PresentsDto of(boolean isApply, List<Menu> presents) {
        return new PresentsDto(isApply, presents);
    }
}
