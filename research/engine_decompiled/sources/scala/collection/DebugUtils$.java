/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import java.util.NoSuchElementException;
import scala.Array$;
import scala.Function1;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public final class DebugUtils$ {
    public static final DebugUtils$ MODULE$;

    static {
        new DebugUtils$();
    }

    public Nothing$ unsupported(String msg) {
        throw new UnsupportedOperationException(msg);
    }

    public Nothing$ noSuchElement(String msg) {
        throw new NoSuchElementException(msg);
    }

    public Nothing$ indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(index)).toString());
    }

    public Nothing$ illegalArgument(String msg) {
        throw new IllegalArgumentException(msg);
    }

    public String buildString(Function1<Function1<Object, BoxedUnit>, BoxedUnit> closure) {
        ObjectRef<String> output = ObjectRef.create("");
        closure.apply((Function1<Object, BoxedUnit>)((Object)new Serializable(output){
            public static final long serialVersionUID = 0L;
            private final ObjectRef output$1;

            public final void apply(Object x$1) {
                this.output$1.elem = new StringBuilder().append((Object)((String)this.output$1.elem)).append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x$1), "\n")).toString();
            }
            {
                this.output$1 = output$1;
            }
        }));
        return (String)output.elem;
    }

    public <T> String arrayString(Object array, int from2, int until2) {
        return Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.genericArrayOps(Predef$.MODULE$.genericArrayOps(array).slice(from2, until2)).map(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply(T x0$1) {
                String string2 = x0$1 == null ? "n/a" : String.valueOf(x0$1);
                return string2;
            }
        }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString(" | ");
    }

    private DebugUtils$() {
        MODULE$ = this;
    }
}

