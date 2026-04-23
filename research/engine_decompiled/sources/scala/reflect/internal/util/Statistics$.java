/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Iterable$;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordered;
import scala.reflect.internal.util.Statistics;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;

public final class Statistics$ {
    public static final Statistics$ MODULE$;
    private boolean scala$reflect$internal$util$Statistics$$_enabled;
    private final HashMap<String, Statistics.Quantity> scala$reflect$internal$util$Statistics$$qs;
    private final boolean canEnable;
    private final boolean hotEnabled;

    static {
        new Statistics$();
    }

    public final void incCounter(Statistics.Counter c) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && c != null) {
            c.value_$eq(c.value() + 1);
        }
    }

    public final void incCounter(Statistics.Counter c, int delta) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && c != null) {
            c.value_$eq(c.value() + delta);
        }
    }

    public final <K> void incCounter(Statistics.QuantMap<K, Statistics.Counter> ctrs, K key) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && ctrs != null) {
            Statistics.Counter counter = ctrs.apply(key);
            counter.value_$eq(counter.value() + 1);
        }
    }

    public final Tuple2<Object, Object> startCounter(Statistics.SubCounter sc) {
        return this.scala$reflect$internal$util$Statistics$$_enabled() && sc != null ? sc.start() : null;
    }

    public final void stopCounter(Statistics.SubCounter sc, Tuple2<Object, Object> start) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && sc != null) {
            sc.stop(start);
        }
    }

    public final Tuple2<Object, Object> startTimer(Statistics.Timer tm) {
        return this.scala$reflect$internal$util$Statistics$$_enabled() && tm != null ? tm.start() : null;
    }

    public final void stopTimer(Statistics.Timer tm, Tuple2<Object, Object> start) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && tm != null) {
            tm.stop(start);
        }
    }

    public final Tuple2<Object, Object> pushTimer(Statistics.TimerStack timers, Function0<Statistics.StackableTimer> timer) {
        return this.scala$reflect$internal$util$Statistics$$_enabled() && timers != null ? timers.push(timer.apply()) : null;
    }

    public final void popTimer(Statistics.TimerStack timers, Tuple2<Object, Object> prev) {
        if (this.scala$reflect$internal$util$Statistics$$_enabled() && timers != null) {
            timers.pop(prev);
        }
    }

    public Statistics.Counter newCounter(String prefix, Seq<String> phases) {
        return new Statistics.Counter(prefix, phases);
    }

    public Statistics.Counter newRelCounter(String prefix, Statistics.Counter ctr) {
        return new Statistics.RelCounter(prefix, ctr);
    }

    public Statistics.SubCounter newSubCounter(String prefix, Statistics.Counter ctr) {
        return new Statistics.SubCounter(prefix, ctr);
    }

    public Statistics.Timer newTimer(String prefix, Seq<String> phases) {
        return new Statistics.Timer(prefix, phases);
    }

    public Statistics.Timer newSubTimer(String prefix, Statistics.Timer timer) {
        return new Statistics.SubTimer(prefix, timer);
    }

    public Statistics.StackableTimer newStackableTimer(String prefix, Statistics.Timer timer) {
        return new Statistics.StackableTimer(prefix, timer);
    }

    public Statistics.View newView(String prefix, Seq<String> phases, Function0<Object> quant) {
        return new Statistics.View(prefix, phases, quant);
    }

    public <K, V> Statistics.QuantMap<K, V> newQuantMap(String prefix, Seq<String> phases, Function0<V> initValue, Function1<V, Ordered<V>> evidence$1) {
        return new Statistics.QuantMap(prefix, phases, initValue, evidence$1);
    }

    public <V> Statistics.QuantMap<Class<?>, V> newByClass(String prefix, Seq<String> phases, Function0<V> initValue, Function1<V, Ordered<V>> evidence$2) {
        return new Statistics.QuantMap(prefix, phases, initValue, evidence$2);
    }

    public Statistics.TimerStack newTimerStack() {
        return new Statistics.TimerStack();
    }

    public Iterable<Statistics.Quantity> allQuantities() {
        return this.scala$reflect$internal$util$Statistics$$qs().withFilter((Function1<String, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<String, Statistics.Quantity> check$ifrefutable$1) {
                boolean bl = check$ifrefutable$1 != null;
                return bl;
            }
        })).withFilter((Function1<Tuple2<String, Statistics.Quantity>, Object>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<String, Statistics.Quantity> x$2) {
                if (x$2 != null) {
                    Statistics.Quantity quantity = x$2._2().underlying();
                    Statistics.Quantity quantity2 = x$2._2();
                    return !(quantity != null ? !quantity.equals(quantity2) : quantity2 != null);
                }
                throw new MatchError(x$2);
            }
        })).flatMap(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final List<Statistics.Quantity> apply(Tuple2<String, Statistics.Quantity> x$3) {
                if (x$3 != null) {
                    Statistics.Quantity quantity = x$3._2();
                    return x$3._2().children().toList().$colon$colon(quantity).withFilter((Function1<Statistics.Quantity, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Statistics.Quantity r) {
                            String string2 = r.prefix();
                            Predef$ predef$ = Predef$.MODULE$;
                            return new StringOps(string2).nonEmpty();
                        }
                    })).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Statistics.Quantity apply(Statistics.Quantity r) {
                            return r;
                        }
                    }, List$.MODULE$.canBuildFrom());
                }
                throw new MatchError(x$3);
            }
        }, Iterable$.MODULE$.canBuildFrom());
    }

    public String scala$reflect$internal$util$Statistics$$showPercent(long x, long base) {
        String string2;
        if (base == 0L) {
            string2 = "";
        } else {
            double arg$macro$8 = (double)x / (double)base * (double)100;
            string2 = new StringOps(" (%2.1f%%)").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(arg$macro$8)}));
        }
        return string2;
    }

    public boolean scala$reflect$internal$util$Statistics$$_enabled() {
        return this.scala$reflect$internal$util$Statistics$$_enabled;
    }

    private void scala$reflect$internal$util$Statistics$$_enabled_$eq(boolean x$1) {
        this.scala$reflect$internal$util$Statistics$$_enabled = x$1;
    }

    public HashMap<String, Statistics.Quantity> scala$reflect$internal$util$Statistics$$qs() {
        return this.scala$reflect$internal$util$Statistics$$qs;
    }

    public final boolean canEnable() {
        return this.canEnable;
    }

    public final boolean hotEnabled() {
        return false;
    }

    public boolean enabled() {
        return this.scala$reflect$internal$util$Statistics$$_enabled();
    }

    public void enabled_$eq(boolean cond) {
        if (cond && !this.scala$reflect$internal$util$Statistics$$_enabled()) {
            long start = System.nanoTime();
            LongRef total2 = LongRef.create(0L);
            Predef$ predef$ = Predef$.MODULE$;
            Range.Inclusive inclusive2 = Range$.MODULE$.inclusive(1, 10000);
            if (!inclusive2.isEmpty()) {
                int n = inclusive2.start();
                while (true) {
                    long time1 = System.nanoTime();
                    total2.elem += System.nanoTime() - time1;
                    if (n == inclusive2.lastElement()) break;
                    n += inclusive2.step();
                }
            }
            long total22 = System.nanoTime() - start;
            String string2 = new StringBuilder().append((Object)"Enabling statistics, measuring overhead = ").append(BoxesRunTime.boxToDouble((double)total2.elem / 10000.0)).append((Object)"ns to ").append(BoxesRunTime.boxToDouble((double)total22 / 10000.0)).append((Object)"ns per timer").toString();
            Predef$ predef$2 = Predef$.MODULE$;
            Console$.MODULE$.println(string2);
            this.scala$reflect$internal$util$Statistics$$_enabled_$eq(true);
        }
    }

    private Statistics$() {
        MODULE$ = this;
        this.scala$reflect$internal$util$Statistics$$_enabled = false;
        this.scala$reflect$internal$util$Statistics$$qs = new HashMap();
        this.canEnable = this.scala$reflect$internal$util$Statistics$$_enabled();
    }
}

