/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.Function1;
import scala.PartialFunction;
import scala.Serializable;
import scala.Tuple2;
import slogging.LogLevel;
import slogging.NullLogger$;
import slogging.UnderlyingLogger;

public final class FilterLogger$ {
    public static final FilterLogger$ MODULE$;
    private final PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> nullFilter;
    private PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> slogging$FilterLogger$$_filter;

    static {
        new FilterLogger$();
    }

    public PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> nullFilter() {
        return this.nullFilter;
    }

    public PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> slogging$FilterLogger$$_filter() {
        return this.slogging$FilterLogger$$_filter;
    }

    private void slogging$FilterLogger$$_filter_$eq(PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> x$1) {
        this.slogging$FilterLogger$$_filter = x$1;
    }

    public PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> filter() {
        return this.slogging$FilterLogger$$_filter();
    }

    public synchronized void filter_$eq(PartialFunction<Tuple2<LogLevel, String>, UnderlyingLogger> f) {
        this.slogging$FilterLogger$$_filter_$eq(f);
    }

    private FilterLogger$() {
        MODULE$ = this;
        this.nullFilter = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Tuple2<LogLevel, String>, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                NullLogger$ nullLogger$ = NullLogger$.MODULE$;
                return (B1)nullLogger$;
            }

            public final boolean isDefinedAt(Tuple2<LogLevel, String> x1) {
                Tuple2<LogLevel, String> tuple2 = x1;
                boolean bl = true;
                return bl;
            }
        };
        this.slogging$FilterLogger$$_filter = this.nullFilter();
    }
}

