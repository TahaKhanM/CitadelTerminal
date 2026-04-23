/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.DurationConversions;
import scala.concurrent.duration.FiniteDuration;

public abstract class DurationConversions$class {
    public static FiniteDuration nanoseconds(DurationConversions $this) {
        return $this.durationIn(TimeUnit.NANOSECONDS);
    }

    public static FiniteDuration nanos(DurationConversions $this) {
        return $this.nanoseconds();
    }

    public static FiniteDuration nanosecond(DurationConversions $this) {
        return $this.nanoseconds();
    }

    public static FiniteDuration nano(DurationConversions $this) {
        return $this.nanoseconds();
    }

    public static FiniteDuration microseconds(DurationConversions $this) {
        return $this.durationIn(TimeUnit.MICROSECONDS);
    }

    public static FiniteDuration micros(DurationConversions $this) {
        return $this.microseconds();
    }

    public static FiniteDuration microsecond(DurationConversions $this) {
        return $this.microseconds();
    }

    public static FiniteDuration micro(DurationConversions $this) {
        return $this.microseconds();
    }

    public static FiniteDuration milliseconds(DurationConversions $this) {
        return $this.durationIn(TimeUnit.MILLISECONDS);
    }

    public static FiniteDuration millis(DurationConversions $this) {
        return $this.milliseconds();
    }

    public static FiniteDuration millisecond(DurationConversions $this) {
        return $this.milliseconds();
    }

    public static FiniteDuration milli(DurationConversions $this) {
        return $this.milliseconds();
    }

    public static FiniteDuration seconds(DurationConversions $this) {
        return $this.durationIn(TimeUnit.SECONDS);
    }

    public static FiniteDuration second(DurationConversions $this) {
        return $this.seconds();
    }

    public static FiniteDuration minutes(DurationConversions $this) {
        return $this.durationIn(TimeUnit.MINUTES);
    }

    public static FiniteDuration minute(DurationConversions $this) {
        return $this.minutes();
    }

    public static FiniteDuration hours(DurationConversions $this) {
        return $this.durationIn(TimeUnit.HOURS);
    }

    public static FiniteDuration hour(DurationConversions $this) {
        return $this.hours();
    }

    public static FiniteDuration days(DurationConversions $this) {
        return $this.durationIn(TimeUnit.DAYS);
    }

    public static FiniteDuration day(DurationConversions $this) {
        return $this.days();
    }

    public static Object nanoseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.nanoseconds());
    }

    public static Object nanos(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.nanoseconds(c, ev);
    }

    public static Object nanosecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.nanoseconds(c, ev);
    }

    public static Object nano(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.nanoseconds(c, ev);
    }

    public static Object microseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.microseconds());
    }

    public static Object micros(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.microseconds(c, ev);
    }

    public static Object microsecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.microseconds(c, ev);
    }

    public static Object micro(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.microseconds(c, ev);
    }

    public static Object milliseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.milliseconds());
    }

    public static Object millis(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.milliseconds(c, ev);
    }

    public static Object millisecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.milliseconds(c, ev);
    }

    public static Object milli(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.milliseconds(c, ev);
    }

    public static Object seconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.seconds());
    }

    public static Object second(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.seconds(c, ev);
    }

    public static Object minutes(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.minutes());
    }

    public static Object minute(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.minutes(c, ev);
    }

    public static Object hours(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.hours());
    }

    public static Object hour(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.hours(c, ev);
    }

    public static Object days(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return ev.convert($this.days());
    }

    public static Object day(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
        return $this.days(c, ev);
    }

    public static void $init$(DurationConversions $this) {
    }
}

