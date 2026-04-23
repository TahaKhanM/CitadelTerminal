/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.BetaApi;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@BetaApi(value="This is going to be replaced with LocalDate from threetenbp")
public final class Date
implements Comparable<Date>,
Serializable {
    private static final Pattern FORMAT_REGEXP = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)");
    private static final long serialVersionUID = 8067099123096783929L;
    private final int year;
    private final int month;
    private final int dayOfMonth;

    private Date(int year, int month, int dayOfMonth) {
        Preconditions.checkArgument(year > 0, "Invalid year: " + year);
        Preconditions.checkArgument(month > 0 && month <= 12, "Invalid month: " + month);
        Preconditions.checkArgument(dayOfMonth > 0 && dayOfMonth <= 31, "Invalid day: " + dayOfMonth);
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public static Date fromYearMonthDay(int year, int month, int dayOfMonth) {
        return new Date(year, month, dayOfMonth);
    }

    public static Date parseDate(String date) {
        Matcher matcher = FORMAT_REGEXP.matcher(date);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date: " + date);
        }
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int dayOfMonth = Integer.parseInt(matcher.group(3));
        return new Date(year, month, dayOfMonth);
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public String toString() {
        return String.format("%04d-%02d-%02d", this.year, this.month, this.dayOfMonth);
    }

    StringBuilder toString(StringBuilder b) {
        return b.append(this.toString());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Date that = (Date)o;
        return this.year == that.year && this.month == that.month && this.dayOfMonth == that.dayOfMonth;
    }

    public int hashCode() {
        return Objects.hash(this.year, this.month, this.dayOfMonth);
    }

    @Override
    public int compareTo(Date other) {
        int r = Integer.compare(this.year, other.year);
        if (r == 0 && (r = Integer.compare(this.month, other.month)) == 0) {
            r = Integer.compare(this.dayOfMonth, other.dayOfMonth);
        }
        return r;
    }
}

