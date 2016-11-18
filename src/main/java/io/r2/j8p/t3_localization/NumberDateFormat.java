package io.r2.j8p.t3_localization;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Format dates, numbers, and currency values for localization with the NumberFormat and DateFormat classes,
 * including number and date format patterns
 *
 * @author robymus <r@r2.io>
 */
public class NumberDateFormat {


    String[] java_text_DateFormat() {
        LocalDateTime ld = LocalDateTime.of(2011, 12, 13, 7, 8, 9);
        ZoneOffset z = ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset()/1000);
        Date d = Date.from(ld.toInstant(z));

        DateFormat fd2 = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
        DateFormat fdt1 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US);
        DateFormat ft = DateFormat.getTimeInstance(DateFormat.SHORT);

        // ft.parse("12:12");

        return new String[] {
                fd2.format(d),
                fdt1.format(d),
                ft.format(d)
        };
    }

    String[] java_time_DateFormatter() {
        LocalDate d = LocalDate.of(2011, 12, 13);
        LocalTime t = LocalTime.of(7, 8, 9);
        LocalDateTime dt = d.atTime(t);

        DateTimeFormatter fd1 = DateTimeFormatter.ISO_DATE;
        DateTimeFormatter fd2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.GERMANY);
        DateTimeFormatter fdt1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)
                .withLocale(Locale.US);
        DateTimeFormatter ft = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        DateTimeFormatter fp = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate d2 = LocalDate.parse("1998-12-12", fp);
        return new String[] {
                d.format(fd1),
                d.format(fd2),
                d2.format(fd2),
                dt.format(fdt1),
                t.format(ft)
        };
    }

    String[] java_text_NumberFormat() {

        NumberFormat fn = NumberFormat.getNumberInstance(Locale.FRANCE);
        NumberFormat fc = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat fp = NumberFormat.getPercentInstance(Locale.KOREA);
        NumberFormat fi = NumberFormat.getIntegerInstance(Locale.UK);

        return new String[] {
                fn.format(1234567.891011),
                fc.format(5666777.23),
                fp.format(0.9432),
                fi.format(987654321)
        };
    }
}
