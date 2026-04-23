/*
 * Decompiled with CFR 0.152.
 */
package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePartial;
import org.joda.time.convert.AbstractConverter;
import org.joda.time.convert.DurationConverter;
import org.joda.time.convert.InstantConverter;
import org.joda.time.convert.IntervalConverter;
import org.joda.time.convert.PartialConverter;
import org.joda.time.convert.PeriodConverter;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class StringConverter
extends AbstractConverter
implements InstantConverter,
PartialConverter,
DurationConverter,
PeriodConverter,
IntervalConverter {
    static final StringConverter INSTANCE = new StringConverter();

    protected StringConverter() {
    }

    @Override
    public long getInstantMillis(Object object, Chronology chronology) {
        String string2 = (String)object;
        DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeParser();
        return dateTimeFormatter.withChronology(chronology).parseMillis(string2);
    }

    @Override
    public int[] getPartialValues(ReadablePartial readablePartial, Object object, Chronology chronology, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter.getZone() != null) {
            chronology = chronology.withZone(dateTimeFormatter.getZone());
        }
        long l = dateTimeFormatter.withChronology(chronology).parseMillis((String)object);
        return chronology.get(readablePartial, l);
    }

    @Override
    public long getDurationMillis(Object object) {
        int n;
        String string2 = (String)object;
        String string3 = string2;
        int n2 = string3.length();
        if (n2 < 4 || string3.charAt(0) != 'P' && string3.charAt(0) != 'p' || string3.charAt(1) != 'T' && string3.charAt(1) != 't' || string3.charAt(n2 - 1) != 'S' && string3.charAt(n2 - 1) != 's') {
            throw new IllegalArgumentException("Invalid format: \"" + string2 + '\"');
        }
        string3 = string3.substring(2, n2 - 1);
        int n3 = -1;
        boolean bl = false;
        for (int i = 0; i < string3.length(); ++i) {
            if (string3.charAt(i) >= '0' && string3.charAt(i) <= '9') continue;
            if (i == 0 && string3.charAt(0) == '-') {
                bl = true;
                continue;
            }
            if (i > (bl ? 1 : 0) && string3.charAt(i) == '.' && n3 == -1) {
                n3 = i;
                continue;
            }
            throw new IllegalArgumentException("Invalid format: \"" + string2 + '\"');
        }
        long l = 0L;
        long l2 = 0L;
        int n4 = n = bl ? 1 : 0;
        if (n3 > 0) {
            l2 = Long.parseLong(string3.substring(n, n3));
            if ((string3 = string3.substring(n3 + 1)).length() != 3) {
                string3 = (string3 + "000").substring(0, 3);
            }
            l = Integer.parseInt(string3);
        } else {
            l2 = bl ? Long.parseLong(string3.substring(n, string3.length())) : Long.parseLong(string3);
        }
        if (bl) {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(-l2, 1000), -l);
        }
        return FieldUtils.safeAdd(FieldUtils.safeMultiply(l2, 1000), l);
    }

    @Override
    public void setInto(ReadWritablePeriod readWritablePeriod, Object object, Chronology chronology) {
        String string2 = (String)object;
        PeriodFormatter periodFormatter = ISOPeriodFormat.standard();
        readWritablePeriod.clear();
        int n = periodFormatter.parseInto(readWritablePeriod, string2, 0);
        if (n < string2.length()) {
            if (n < 0) {
                periodFormatter.withParseType(readWritablePeriod.getPeriodType()).parseMutablePeriod(string2);
            }
            throw new IllegalArgumentException("Invalid format: \"" + string2 + '\"');
        }
    }

    @Override
    public void setInto(ReadWritableInterval readWritableInterval, Object object, Chronology chronology) {
        DateTime dateTime;
        String string2 = (String)object;
        int n = string2.indexOf(47);
        if (n < 0) {
            throw new IllegalArgumentException("Format requires a '/' separator: " + string2);
        }
        String string3 = string2.substring(0, n);
        if (string3.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + string2);
        }
        String string4 = string2.substring(n + 1);
        if (string4.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + string2);
        }
        DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeParser();
        dateTimeFormatter = dateTimeFormatter.withChronology(chronology);
        PeriodFormatter periodFormatter = ISOPeriodFormat.standard();
        long l = 0L;
        long l2 = 0L;
        Period period = null;
        Chronology chronology2 = null;
        char c = string3.charAt(0);
        if (c == 'P' || c == 'p') {
            period = periodFormatter.withParseType(this.getPeriodType(string3)).parsePeriod(string3);
        } else {
            dateTime = dateTimeFormatter.parseDateTime(string3);
            l = dateTime.getMillis();
            chronology2 = dateTime.getChronology();
        }
        c = string4.charAt(0);
        if (c == 'P' || c == 'p') {
            if (period != null) {
                throw new IllegalArgumentException("Interval composed of two durations: " + string2);
            }
            period = periodFormatter.withParseType(this.getPeriodType(string4)).parsePeriod(string4);
            chronology = chronology != null ? chronology : chronology2;
            l2 = chronology.add(period, l, 1);
        } else {
            dateTime = dateTimeFormatter.parseDateTime(string4);
            l2 = dateTime.getMillis();
            chronology2 = chronology2 != null ? chronology2 : dateTime.getChronology();
            Chronology chronology3 = chronology = chronology != null ? chronology : chronology2;
            if (period != null) {
                l = chronology.add(period, l2, -1);
            }
        }
        readWritableInterval.setInterval(l, l2);
        readWritableInterval.setChronology(chronology);
    }

    @Override
    public Class<?> getSupportedType() {
        return String.class;
    }
}

