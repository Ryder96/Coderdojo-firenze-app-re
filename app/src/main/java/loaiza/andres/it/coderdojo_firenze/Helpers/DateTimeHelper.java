package loaiza.andres.it.coderdojo_firenze.Helpers;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Garu on 26/12/2014.
 */
public class DateTimeHelper {
    private static DateFormatSymbols _dfs;

    public static Date getDateFromString(String string) {
        try {
            DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return m_ISO8601Local.parse(string);
        } catch (ParseException e) {
            return new Date(System.currentTimeMillis());
        }
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String formatHour(String string) {
        return (string.length() == 1) ? "0" + string : string;
    }

    public static String formatHour(int val) {
        return formatHour(String.valueOf(val));
    }

    private static DateFormatSymbols getDateInstance() { // dat singleton <3
        if (_dfs == null)
            _dfs = new DateFormatSymbols(Locale.ITALIAN); // Locale.getDefault() would be a slower solution
        return _dfs;
    }

    public static String getDayName(int day) {
        return getDateInstance().getWeekdays()[day];
    }

    public static String getMonthName(int month) {
        return getDateInstance().getMonths()[month];
    }

}
