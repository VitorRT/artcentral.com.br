package br.com.artcentral.mvc.system.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class DateUtils {
    public static String formatDateAndTime(LocalDate date, LocalTime time) {
        DateTime now = new DateTime();
        DateTime dateTime = new DateTime(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());
        Period period = new Period(dateTime, now);

        if(period.getYears() > 0) {
            if(period.getYears() == 1)
                return "há 1 ano";
            return String.format("há %d anos", period.getWeeks());
        }
        else if (period.getWeeks() > 0) {
            if(period.getWeeks() == 1)
                return "há 1 semana";
            return String.format("há %d semanas", period.getWeeks());
        } else if (period.getDays() > 0) {
            if(period.getDays() == 1)
                return "há 1 dia";
            return String.format("há %d dias", period.getDays());
        } else if (period.getHours() > 0) {
            if(period.getHours() == 1)
                return "há 1 hora";
            return String.format("há %d horas", period.getHours());
        } else if (period.getMinutes() > 0) {
            if(period.getMinutes() == 1)
                return "há 1 minuto";
            return String.format("há %d minutos", period.getMinutes());
        } else {
            return "agora";
        }
    }
}
