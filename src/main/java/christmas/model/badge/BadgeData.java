package christmas.model.badge;

public enum BadgeData {
    별(5_000),
    트리(10_000),
    산타(20_000),
    ;

    private final int price;

    BadgeData(int price) {
        this.price = price;
    }

    public boolean isMatch(int price) {
        return this.price <= price;
    }

    public int getPrice() {
        return price;
    }
}
