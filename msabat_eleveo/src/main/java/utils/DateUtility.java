package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtility {

    /**
     * @return date of next monday in format EEEE, MMMM d, yyyy
     */
    static public String getNextMondayDay() {
        LocalDate currentDate = LocalDate.now();
        LocalDate thisWeekMonday = currentDate.with(DayOfWeek.MONDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);

        return  thisWeekMonday.plusWeeks(1).format(formatter);
    }
}
