/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.reflect.internal.util.Origins;
import scala.runtime.BoxedUnit;
import scala.sys.package$;

public final class Origins$ {
    public static final Origins$ MODULE$;
    private final HashMap<String, Origins> scala$reflect$internal$util$Origins$$counters;
    private final String thisClass;

    static {
        new Origins$();
    }

    public HashMap<String, Origins> scala$reflect$internal$util$Origins$$counters() {
        return this.scala$reflect$internal$util$Origins$$counters;
    }

    private String thisClass() {
        return this.thisClass;
    }

    public Origins lookup(String tag, Function1<String, Origins> orElse) {
        return this.scala$reflect$internal$util$Origins$$counters().getOrElseUpdate(tag, (Function0<Origins>)((Object)new Serializable(tag, orElse){
            public static final long serialVersionUID = 0L;
            private final String tag$1;
            private final Function1 orElse$1;

            public final Origins apply() {
                return (Origins)this.orElse$1.apply(this.tag$1);
            }
            {
                this.tag$1 = tag$1;
                this.orElse$1 = orElse$1;
            }
        }));
    }

    public Origins register(Origins x) {
        this.scala$reflect$internal$util$Origins$$counters().update(x.tag(), x);
        return x;
    }

    public boolean scala$reflect$internal$util$Origins$$preCutoff(StackTraceElement el) {
        String string2 = el.getClassName();
        String string3 = this.thisClass();
        return !(string2 == null ? string3 != null : !string2.equals(string3)) || el.getClassName().startsWith("java.lang.");
    }

    public Origins.OriginId scala$reflect$internal$util$Origins$$findCutoff() {
        StackTraceElement cutoff = (StackTraceElement)Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()).dropWhile(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final boolean apply(StackTraceElement el) {
                return Origins$.MODULE$.scala$reflect$internal$util$Origins$$preCutoff(el);
            }
        })).head();
        return new Origins.OriginId(cutoff.getClassName(), cutoff.getMethodName());
    }

    public Origins apply(String tag) {
        return this.scala$reflect$internal$util$Origins$$counters().getOrElseUpdate(tag, (Function0<Origins>)((Object)new Serializable(tag){
            public static final long serialVersionUID = 0L;
            private final String tag$2;

            public final Origins.OneLine apply() {
                return new Origins.OneLine(this.tag$2, Origins$.MODULE$.scala$reflect$internal$util$Origins$$findCutoff());
            }
            {
                this.tag$2 = tag$2;
            }
        }));
    }

    public Origins apply(String tag, int frames) {
        return this.scala$reflect$internal$util$Origins$$counters().getOrElseUpdate(tag, (Function0<Origins>)((Object)new Serializable(tag, frames){
            public static final long serialVersionUID = 0L;
            private final String tag$3;
            private final int frames$1;

            public final Origins.MultiLine apply() {
                return new Origins.MultiLine(this.tag$3, Origins$.MODULE$.scala$reflect$internal$util$Origins$$findCutoff(), this.frames$1);
            }
            {
                this.tag$3 = tag$3;
                this.frames$1 = frames$1;
            }
        }));
    }

    private Origins$() {
        MODULE$ = this;
        this.scala$reflect$internal$util$Origins$$counters = (HashMap)HashMap$.MODULE$.apply(Nil$.MODULE$);
        this.thisClass = this.getClass().getName();
        package$.MODULE$.addShutdownHook((Function0<BoxedUnit>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply() {
                Origins$.MODULE$.scala$reflect$internal$util$Origins$$counters().values().foreach(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final void apply(Origins x$4) {
                        x$4.purge();
                    }
                });
            }

            public void apply$mcV$sp() {
                Origins$.MODULE$.scala$reflect$internal$util$Origins$$counters().values().foreach(new /* invalid duplicate definition of identical inner class */);
            }
        }));
        Predef$ predef$ = Predef$.MODULE$;
    }
}

