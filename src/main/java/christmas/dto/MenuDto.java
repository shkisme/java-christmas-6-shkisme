package christmas.dto;

public record MenuDto(String name, int count) {

    public static MenuDto of(String name, int count) {
        return new MenuDto(name, count);
    }
}
