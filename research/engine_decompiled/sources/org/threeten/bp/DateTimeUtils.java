/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

public final class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static Instant toInstant(Date utilDate) {
        return Instant.ofEpochMilli(utilDate.getTime());
    }

    public static Date toDate(Instant instant) {
        try {
            return new Date(instant.toEpochMilli());
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static Instant toInstant(Calendar calendar) {
        return Instant.ofEpochMilli(calendar.getTimeInMillis());
    }

    public static ZonedDateTime toZonedDateTime(Calendar calendar) {
        Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
        ZoneId zone = DateTimeUtils.toZoneId(calendar.getTimeZone());
        return ZonedDateTime.ofInstant(instant, zone);
    }

    public static GregorianCalendar toGregorianCalendar(ZonedDateTime zdt) {
        TimeZone zone = DateTimeUtils.toTimeZone(zdt.getZone());
        GregorianCalendar cal = new GregorianCalendar(zone);
        cal.setGregorianChange(new Date(Long.MIN_VALUE));
        cal.setFirstDayOfWeek(2);
        cal.setMinimalDaysInFirstWeek(4);
        try {
            cal.setTimeInMillis(zdt.toInstant().toEpochMilli());
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
        return cal;
    }

    public static ZoneId toZoneId(TimeZone timeZone) {
        return ZoneId.of(timeZone.getID(), ZoneId.SHORT_IDS);
    }

    public static TimeZone toTimeZone(ZoneId zoneId) {
        String tzid = zoneId.getId();
        if (tzid.startsWith("+") || tzid.startsWith("-")) {
            tzid = "GMT" + tzid;
        } else if (tzid.equals("Z")) {
            tzid = "UTC";
        }
        return TimeZone.getTimeZone(tzid);
    }

    public static LocalDate toLocalDate(java.sql.Date sqlDate) {
        return LocalDate.of(sqlDate.getYear() + 1900, sqlDate.getMonth() + 1, sqlDate.getDate());
    }

    public static java.sql.Date toSqlDate(LocalDate date) {
        return new java.sql.Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth());
    }

    public static LocalTime toLocalTime(Time sqlTime) {
        return LocalTime.of(sqlTime.getHours(), sqlTime.getMinutes(), sqlTime.getSeconds());
    }

    public static Time toSqlTime(LocalTime time) {
        return new Time(time.getHour(), time.getMinute(), time.getSecond());
    }

    public static Timestamp toSqlTimestamp(LocalDateTime dateTime) {
        return new Timestamp(dateTime.getYear() - 1900, dateTime.getMonthValue() - 1, dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), dateTime.getNano());
    }

    public static LocalDateTime toLocalDateTime(Timestamp sqlTimestamp) {
        return LocalDateTime.of(sqlTimestamp.getYear() + 1900, sqlTimestamp.getMonth() + 1, sqlTimestamp.getDate(), sqlTimestamp.getHours(), sqlTimestamp.getMinutes(), sqlTimestamp.getSeconds(), sqlTimestamp.getNanos());
    }

    public static Timestamp toSqlTimestamp(Instant instant) {
        try {
            Timestamp ts = new Timestamp(instant.getEpochSecond() * 1000L);
            ts.setNanos(instant.getNano());
            return ts;
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static Instant toInstant(Timestamp sqlTimestamp) {
        return Instant.ofEpochSecond(sqlTimestamp.getTime() / 1000L, sqlTimestamp.getNanos());
    }
}

