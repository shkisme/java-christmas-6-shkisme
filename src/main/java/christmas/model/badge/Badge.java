package christmas.model.badge;

public class Badge {

    private final String name;

    public Badge(String name) {
        this.name = name;
    }

    public static Badge from(BadgeData badgeData) {
        return new Badge(badgeData.toString());
    }

    public String getName() {
        return name;
    }
}
