package backend.resource;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public final class DateHelper {

    public static Date[] getMondayAndSaturday(@NotNull Date date) {
        YearMonth month = YearMonth.now();
        LocalDate currentDate = month.atDay(date.toLocalDate().getDayOfMonth());
        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
        LocalDate saturday = currentDate.with(DayOfWeek.SATURDAY);

        Date[] dates = new Date[2];
        dates[0] = Date.valueOf(monday);
        dates[1] = Date.valueOf(saturday);
        return dates;
    }

    //check for usage!!!
    public static Date addNDays(@NotNull Date date, int n) {
        if (n == 0)
            return date;
        return Date.valueOf(date.toLocalDate().plusDays(n));
    }
}
