package Models;

import java.util.Calendar;//преобразования вид даты и времени в более красивый вид

public class DataUtils {
    public static String getDateFromCalendar(Calendar c) {
        String result = "";
        if (c.get(Calendar.DAY_OF_MONTH) < 10) {  // берем день даты
            result += "0";
        }
        result += c.get(Calendar.DAY_OF_MONTH) + ".";
        if (c.get(Calendar.MONTH) < 9) {
            result += "0";
        }
        result += (c.get(Calendar.MONTH) + 1) + "." + // берем месяц даты
                c.get(Calendar.YEAR) + " ";

        if (c.get(Calendar.HOUR_OF_DAY) < 10) { // берем час
            result += "0";
        }
        result += c.get(Calendar.HOUR_OF_DAY) + ":";

        if (c.get(Calendar.MINUTE) < 10) { // берем минуты
            result += "0";
        }
        result += c.get(Calendar.MINUTE);
        return result; // бередаем данные
    }

}