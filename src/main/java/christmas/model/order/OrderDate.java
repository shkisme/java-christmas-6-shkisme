package christmas.model.order;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class OrderDate {
    private static final List<DayOfWeek> WEEKDAY = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SUNDAY);
    private static final List<LocalDate> STAR_DAY = List.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
    );

    private final LocalDate date;

    public OrderDate(LocalDate date) {
        this.date = date;
    }

    public static OrderDate of(int year, int month, int date) {
        return new OrderDate(LocalDate.of(year, month, date));
    }

    public boolean isWeekday() {
        return WEEKDAY.contains(date.getDayOfWeek());
    }

    public boolean isStarDay() {
        return STAR_DAY.contains(date);
    }

    public boolean isBeforeOrEqual(int day) {
        return date.getDayOfMonth() <= day;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }
}
