package io.r2.j8p.t3_localization;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

/**
 * Create and manage date- and time-based events by using LocalDate, LocalTime, LocalDateTime, Instant, Period,
 * and Duration, including a combination of date and time in a single object
 *
 * @author robymus <r@r2.io>
 */
public class LocalDateAndTime {

    LocalDate localDate() {
        LocalDate d1 = LocalDate.of(2016, 12, 03);
        LocalDate d2 = LocalDate.now();
        LocalDate d3 = LocalDate.ofEpochDay(12345);
        LocalDate d4 = LocalDate.ofYearDay(2016, 35);
        d1.isBefore(d2);
        d2.isLeapYear();
        d3.lengthOfMonth();
        d3.getYear();
        LocalDate d5 = d4.plus(3, ChronoUnit.DAYS);
        return d1;
    }

    LocalTime localTime() {
        LocalTime t1 = LocalTime.now();
        LocalTime t2 = LocalTime.of(23, 55, 12);
        LocalTime t3 = LocalTime.ofSecondOfDay(3424);
        t1.isBefore(t2);
        t1.getMinute();
        t3.plusHours(3);
        return t1;
    }

    LocalDateTime localDateTime() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.ofEpochSecond(1123123, 0, ZoneOffset.UTC);
        LocalDateTime dt3 = localTime().atDate(localDate());
        dt1.getSecond();
        dt2.plusWeeks(4);
        return dt3;
    }

    void instant() {
        Instant i1 = Instant.now();
        Instant i2 = Instant.ofEpochMilli(4242232323L);
        i1.get(ChronoField.MINUTE_OF_HOUR);
        LocalTime.from(i2);
        i2.isAfter(i1);
    }

    /**
     * Period is based on conceptual day, will add exact days, taking DST into consideration
     */
    void period() {
        Period p1 = Period.ofDays(3);
        Period p2 = Period.between(localDate(), LocalDate.now());
        p1.plusDays(3);
        p1.plus(p2);
        p1.getDays();
        p2.negated();
        p2.addTo(LocalDate.now());

        LocalDateTime.now().minus(p1);
    }

    /**
     * Duration is based on time (number of seconds)
     */
    void duration() {
        Duration d1 = Duration.ofHours(24);
        Duration d2 = Duration.between(localTime(), LocalTime.now());
        d1.isNegative();
        d2.getSeconds();
        d2.get(ChronoUnit.MINUTES);
        d1.plusHours(3);

        Instant.now().plus(d1);
    }


}
