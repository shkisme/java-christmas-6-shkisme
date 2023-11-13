package christmas.dto;

import christmas.model.menu.Menu;
import java.util.List;

public record PresentationsDto(boolean isApply, List<Menu> menus) {

    public static PresentationsDto of(boolean isApply, List<Menu> presentations) {
        return new PresentationsDto(isApply, presentations);
    }
}
