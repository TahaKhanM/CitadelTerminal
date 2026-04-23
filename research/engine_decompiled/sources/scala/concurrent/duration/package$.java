/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.Tuple2;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration;
import scala.runtime.BoxesRunTime;

public final class package$ {
    public static final package$ MODULE$;
    private final TimeUnit DAYS;
    private final TimeUnit HOURS;
    private final TimeUnit MICROSECONDS;
    private final TimeUnit MILLISECONDS;
    private final TimeUnit MINUTES;
    private final TimeUnit NANOSECONDS;
    private final TimeUnit SECONDS;

    static {
        new package$();
    }

    public final TimeUnit DAYS() {
        return TimeUnit.DAYS;
    }

    public final TimeUnit HOURS() {
        return TimeUnit.HOURS;
    }

    public final TimeUnit MICROSECONDS() {
        return TimeUnit.MICROSECONDS;
    }

    public final TimeUnit MILLISECONDS() {
        return TimeUnit.MILLISECONDS;
    }

    public final TimeUnit MINUTES() {
        return TimeUnit.MINUTES;
    }

    public final TimeUnit NANOSECONDS() {
        return TimeUnit.NANOSECONDS;
    }

    public final TimeUnit SECONDS() {
        return TimeUnit.SECONDS;
    }

    public Duration pairIntToDuration(Tuple2<Object, TimeUnit> p) {
        return Duration$.MODULE$.apply((long)p._1$mcI$sp(), p._2());
    }

    public FiniteDuration pairLongToDuration(Tuple2<Object, TimeUnit> p) {
        return Duration$.MODULE$.apply(p._1$mcJ$sp(), p._2());
    }

    public Tuple2<Object, TimeUnit> durationToPair(Duration d) {
        return new Tuple2<Object, TimeUnit>(BoxesRunTime.boxToLong(d.length()), d.unit());
    }

    public int DurationInt(int n) {
        return n;
    }

    public long DurationLong(long n) {
        return n;
    }

    public double DurationDouble(double d) {
        return d;
    }

    public int IntMult(int i) {
        return i;
    }

    public long LongMult(long i) {
        return i;
    }

    public double DoubleMult(double f) {
        return f;
    }

    private package$() {
        MODULE$ = this;
    }
}

