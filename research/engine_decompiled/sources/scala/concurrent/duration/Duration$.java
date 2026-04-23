/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;

public final class Duration$
implements Serializable {
    public static final Duration$ MODULE$;
    private final double maxPreciseDouble;
    private final List<Tuple2<TimeUnit, String>> timeUnitLabels;
    private final Map<TimeUnit, String> timeUnitName;
    private final Map<String, TimeUnit> timeUnit;
    private final long \u00b5s_per_ns;
    private final long ms_per_ns;
    private final long s_per_ns;
    private final long min_per_ns;
    private final long h_per_ns;
    private final long d_per_ns;
    private final FiniteDuration Zero;
    private final Duration.Infinite Undefined;
    private final Duration.Infinite Inf;
    private final Duration.Infinite MinusInf;

    static {
        new Duration$();
    }

    public Duration apply(double length, TimeUnit unit) {
        return this.fromNanos((double)unit.toNanos(1L) * length);
    }

    public FiniteDuration apply(long length, TimeUnit unit) {
        return new FiniteDuration(length, unit);
    }

    public FiniteDuration apply(long length, String unit) {
        return new FiniteDuration(length, (TimeUnit)((Object)this.timeUnit().apply(unit)));
    }

    public Duration apply(String s2) {
        block5: {
            Duration duration;
            block3: {
                String s1;
                block4: {
                    block2: {
                        Predef$ predef$ = Predef$.MODULE$;
                        s1 = (String)new StringOps(s2).filterNot((Function1)((Object)new Serializable(){
                            public static final long serialVersionUID = 0L;

                            public final boolean apply(char x$1) {
                                Predef$ predef$ = Predef$.MODULE$;
                                return RichChar$.MODULE$.isWhitespace$extension(x$1);
                            }
                        }));
                        boolean bl = "Inf".equals(s1) ? true : ("PlusInf".equals(s1) ? true : "+Inf".equals(s1));
                        if (!bl) break block2;
                        duration = this.Inf();
                        break block3;
                    }
                    boolean bl = "MinusInf".equals(s1) ? true : "-Inf".equals(s1);
                    if (!bl) break block4;
                    duration = this.MinusInf();
                    break block3;
                }
                Predef$ predef$ = Predef$.MODULE$;
                String string2 = (String)new StringOps(s1).reverse();
                Predef$ predef$2 = Predef$.MODULE$;
                String string3 = (String)new StringOps(string2).takeWhile((Function1)((Object)new Serializable(){
                    public static final long serialVersionUID = 0L;

                    public final boolean apply(char x$2) {
                        Predef$ predef$ = Predef$.MODULE$;
                        return RichChar$.MODULE$.isLetter$extension(x$2);
                    }
                }));
                Predef$ predef$3 = Predef$.MODULE$;
                String unitName = (String)new StringOps(string3).reverse();
                Option option = this.timeUnit().get(unitName);
                if (!(option instanceof Some)) break block5;
                Some some = (Some)option;
                Predef$ predef$4 = Predef$.MODULE$;
                String valueStr = (String)new StringOps(s1).dropRight(unitName.length());
                double valueD = Double.parseDouble(valueStr);
                duration = valueD >= -9.007199254740992E15 && valueD <= 9.007199254740992E15 ? this.apply(valueD, (TimeUnit)((Object)some.x())) : this.apply(Long.parseLong(valueStr), (TimeUnit)((Object)some.x()));
            }
            return duration;
        }
        throw new NumberFormatException(new StringBuilder().append((Object)"format error ").append((Object)s2).toString());
    }

    public List<String> scala$concurrent$duration$Duration$$words(String s2) {
        return Predef$.MODULE$.refArrayOps((Object[])s2.trim().split("\\s+")).toList();
    }

    public List<String> scala$concurrent$duration$Duration$$expandLabels(String labels) {
        List<String> list2 = this.scala$concurrent$duration$Duration$$words(labels);
        if (list2 instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)list2;
            Tuple2 tuple2 = new Tuple2($colon$colon.head(), $colon$colon.tl$1());
            String hd = (String)tuple2._1();
            List<String> rest = tuple2._2();
            return rest.flatMap(new Serializable(){
                public static final long serialVersionUID = 0L;

                public final List<String> apply(String s2) {
                    return List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{s2, new StringBuilder().append((Object)s2).append((Object)"s").toString()}));
                }
            }, List$.MODULE$.canBuildFrom()).$colon$colon(hd);
        }
        throw new MatchError(list2);
    }

    public Map<TimeUnit, String> timeUnitName() {
        return this.timeUnitName;
    }

    public Map<String, TimeUnit> timeUnit() {
        return this.timeUnit;
    }

    public Option<Tuple2<Object, TimeUnit>> unapply(String s2) {
        Option option;
        Option option2 = this.liftedTree1$1(s2);
        if (option2.isEmpty()) {
            option = None$.MODULE$;
        } else {
            Duration duration = (Duration)option2.get();
            option = MODULE$.unapply(duration);
        }
        return option;
    }

    public Option<Tuple2<Object, TimeUnit>> unapply(Duration d) {
        return d.isFinite() ? new Some<Tuple2<Long, TimeUnit>>(new Tuple2<Long, TimeUnit>(BoxesRunTime.boxToLong(d.length()), d.unit())) : None$.MODULE$;
    }

    public Duration fromNanos(double nanos) {
        Duration duration;
        if (Predef$.MODULE$.double2Double(nanos).isInfinite()) {
            duration = nanos > 0.0 ? this.Inf() : this.MinusInf();
        } else if (Predef$.MODULE$.double2Double(nanos).isNaN()) {
            duration = this.Undefined();
        } else {
            if (nanos > (double)Long.MAX_VALUE || nanos < (double)Long.MIN_VALUE) {
                throw new IllegalArgumentException(new StringBuilder().append((Object)"trying to construct too large duration with ").append(BoxesRunTime.boxToDouble(nanos)).append((Object)"ns").toString());
            }
            duration = this.fromNanos((long)(nanos + 0.5));
        }
        return duration;
    }

    public FiniteDuration fromNanos(long nanos) {
        return nanos % 86400000000000L == 0L ? this.apply(nanos / 86400000000000L, TimeUnit.DAYS) : (nanos % 3600000000000L == 0L ? this.apply(nanos / 3600000000000L, TimeUnit.HOURS) : (nanos % 60000000000L == 0L ? this.apply(nanos / 60000000000L, TimeUnit.MINUTES) : (nanos % 1000000000L == 0L ? this.apply(nanos / 1000000000L, TimeUnit.SECONDS) : (nanos % 1000000L == 0L ? this.apply(nanos / 1000000L, TimeUnit.MILLISECONDS) : (nanos % 1000L == 0L ? this.apply(nanos / 1000L, TimeUnit.MICROSECONDS) : this.apply(nanos, TimeUnit.NANOSECONDS))))));
    }

    public FiniteDuration Zero() {
        return this.Zero;
    }

    public Duration.Infinite Undefined() {
        return this.Undefined;
    }

    public Duration.Infinite Inf() {
        return this.Inf;
    }

    public Duration.Infinite MinusInf() {
        return this.MinusInf;
    }

    public FiniteDuration create(long length, TimeUnit unit) {
        return this.apply(length, unit);
    }

    public Duration create(double length, TimeUnit unit) {
        return this.apply(length, unit);
    }

    public FiniteDuration create(long length, String unit) {
        return this.apply(length, unit);
    }

    public Duration create(String s2) {
        return this.apply(s2);
    }

    private Object readResolve() {
        return MODULE$;
    }

    private final Option liftedTree1$1(String s$1) {
        Option option;
        try {
            option = new Some<Duration>(this.apply(s$1));
        }
        catch (RuntimeException runtimeException) {
            option = None$.MODULE$;
        }
        return option;
    }

    private Duration$() {
        MODULE$ = this;
        Tuple2[] tuple2Array = new Tuple2[7];
        TimeUnit timeUnit = Predef$.MODULE$.ArrowAssoc(TimeUnit.DAYS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[0] = new Tuple2<TimeUnit, String>(timeUnit, "d day");
        TimeUnit timeUnit2 = Predef$.MODULE$.ArrowAssoc(TimeUnit.HOURS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[1] = new Tuple2<TimeUnit, String>(timeUnit2, "h hour");
        TimeUnit timeUnit3 = Predef$.MODULE$.ArrowAssoc(TimeUnit.MINUTES);
        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[2] = new Tuple2<TimeUnit, String>(timeUnit3, "min minute");
        TimeUnit timeUnit4 = Predef$.MODULE$.ArrowAssoc(TimeUnit.SECONDS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$4 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[3] = new Tuple2<TimeUnit, String>(timeUnit4, "s sec second");
        TimeUnit timeUnit5 = Predef$.MODULE$.ArrowAssoc(TimeUnit.MILLISECONDS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$5 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[4] = new Tuple2<TimeUnit, String>(timeUnit5, "ms milli millisecond");
        TimeUnit timeUnit6 = Predef$.MODULE$.ArrowAssoc(TimeUnit.MICROSECONDS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$6 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[5] = new Tuple2<TimeUnit, String>(timeUnit6, "\u00b5s micro microsecond");
        TimeUnit timeUnit7 = Predef$.MODULE$.ArrowAssoc(TimeUnit.NANOSECONDS);
        Predef$ArrowAssoc$ predef$ArrowAssoc$7 = Predef$ArrowAssoc$.MODULE$;
        tuple2Array[6] = new Tuple2<TimeUnit, String>(timeUnit7, "ns nano nanosecond");
        this.timeUnitLabels = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
        this.timeUnitName = this.timeUnitLabels.toMap(Predef$.MODULE$.$conforms()).mapValues(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply(String s2) {
                return Duration$.MODULE$.scala$concurrent$duration$Duration$$words(s2).last();
            }
        }).toMap(Predef$.MODULE$.$conforms());
        this.timeUnit = ((TraversableOnce)this.timeUnitLabels.flatMap(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final List<Tuple2<String, TimeUnit>> apply(Tuple2<TimeUnit, String> x0$1) {
                if (x0$1 != null) {
                    return Duration$.MODULE$.scala$concurrent$duration$Duration$$expandLabels(x0$1._2()).map(new Serializable(this, x0$1){
                        public static final long serialVersionUID = 0L;
                        private final Tuple2 x1$1;

                        public final Tuple2<String, TimeUnit> apply(String x$5) {
                            T1 T1 = this.x1$1._1();
                            String string2 = Predef$.MODULE$.ArrowAssoc(x$5);
                            Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                            return new Tuple2<String, TimeUnit>(string2, (TimeUnit)((Object)T1));
                        }
                        {
                            this.x1$1 = x1$1;
                        }
                    }, List$.MODULE$.canBuildFrom());
                }
                throw new MatchError(x0$1);
            }
        }, List$.MODULE$.canBuildFrom())).toMap(Predef$.MODULE$.$conforms());
        this.Zero = new FiniteDuration(0L, TimeUnit.DAYS);
        this.Undefined = new Duration.Infinite(){

            public String toString() {
                return "Duration.Undefined";
            }

            public boolean equals(Object other) {
                return false;
            }

            public Duration $plus(Duration other) {
                return this;
            }

            public Duration $minus(Duration other) {
                return this;
            }

            public Duration $times(double factor) {
                return this;
            }

            public Duration $div(double factor) {
                return this;
            }

            public double $div(Duration other) {
                return Double.NaN;
            }

            public int compare(Duration other) {
                return other == this ? 0 : 1;
            }

            public Duration unary_$minus() {
                return this;
            }

            public double toUnit(TimeUnit unit) {
                return Double.NaN;
            }

            private Object readResolve() {
                return Duration$.MODULE$.Undefined();
            }
        };
        this.Inf = new Duration.Infinite(){

            public String toString() {
                return "Duration.Inf";
            }

            public int compare(Duration other) {
                int n = other == Duration$.MODULE$.Undefined() ? -1 : (other == this ? 0 : 1);
                return n;
            }

            public Duration unary_$minus() {
                return Duration$.MODULE$.MinusInf();
            }

            public double toUnit(TimeUnit unit) {
                return Double.POSITIVE_INFINITY;
            }

            private Object readResolve() {
                return Duration$.MODULE$.Inf();
            }
        };
        this.MinusInf = new Duration.Infinite(){

            public String toString() {
                return "Duration.MinusInf";
            }

            public int compare(Duration other) {
                return other == this ? 0 : -1;
            }

            public Duration unary_$minus() {
                return Duration$.MODULE$.Inf();
            }

            public double toUnit(TimeUnit unit) {
                return Double.NEGATIVE_INFINITY;
            }

            private Object readResolve() {
                return Duration$.MODULE$.MinusInf();
            }
        };
    }
}

