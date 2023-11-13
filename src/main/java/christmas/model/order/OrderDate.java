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
    private static final List<DayOfWeek> weekday = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, SUNDAY);
    private static final int SPECIAL_DAY = 25;

    private final LocalDate date;

    public OrderDate(LocalDate date) {
        this.date = date;
    }

    public static OrderDate of(int year, int month, int date) {
        return new OrderDate(LocalDate.of(year, month, date));
    }

    public boolean isWeekday() {
        return weekday.contains(date.getDayOfWeek());
    }

    public boolean isSpecialDay() {
        return isDayEqual(SUNDAY) || isDayEqual(SPECIAL_DAY);
    }

    private boolean isDayEqual(DayOfWeek dayOfWeek) {
        return date.getDayOfWeek() == dayOfWeek;
    }

    private boolean isDayEqual(int day) {
        return date.getDayOfMonth() == day;
    }

    public boolean isBeforeOrEqual(int day) {
        return date.getDayOfMonth() <= day;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }
}
