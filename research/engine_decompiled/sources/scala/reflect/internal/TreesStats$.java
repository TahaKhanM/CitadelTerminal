/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Seq;
import scala.collection.mutable.WrappedArray;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class TreesStats$ {
    public static final TreesStats$ MODULE$;
    private final Statistics.QuantMap<Class<?>, Statistics.Counter> nodeByType;

    static {
        new TreesStats$();
    }

    public Statistics.QuantMap<Class<?>, Statistics.Counter> nodeByType() {
        return this.nodeByType;
    }

    private TreesStats$() {
        MODULE$ = this;
        Predef$.less.colon.less less2 = Predef$.MODULE$.$conforms();
        Serializable serializable = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Statistics.Counter apply() {
                return Statistics$.MODULE$.newCounter("", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
            }
        };
        WrappedArray<Object> wrappedArray = Predef$.MODULE$.wrapRefArray((Object[])new String[0]);
        Statistics$ statistics$ = Statistics$.MODULE$;
        this.nodeByType = new Statistics.QuantMap("#created tree nodes by type", (Seq<String>)wrappedArray, (Function0<Statistics.Counter>)((Object)serializable), less2);
    }
}

