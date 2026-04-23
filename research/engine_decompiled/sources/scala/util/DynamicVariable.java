/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function0;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t4A!\u0001\u0002\u0001\u000f\tyA)\u001f8b[&\u001cg+\u0019:jC\ndWM\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0003\u0011E\u0019\"\u0001A\u0005\u0011\u0005)YQ\"\u0001\u0003\n\u00051!!AB!osJ+g\r\u0003\u0005\u000f\u0001\t\u0005\t\u0015!\u0003\u0010\u0003\u0011Ig.\u001b;\u0011\u0005A\tB\u0002\u0001\u0003\u0006%\u0001\u0011\ra\u0005\u0002\u0002)F\u0011Ac\u0006\t\u0003\u0015UI!A\u0006\u0003\u0003\u000f9{G\u000f[5oOB\u0011!\u0002G\u0005\u00033\u0011\u00111!\u00118z\u0011\u0015Y\u0002\u0001\"\u0001\u001d\u0003\u0019a\u0014N\\5u}Q\u0011Qd\b\t\u0004=\u0001yQ\"\u0001\u0002\t\u000b9Q\u0002\u0019A\b\t\u000f\u0005\u0002!\u0019!C\u0005E\u0005\u0011A\u000f\\\u000b\u0002GI\u0011A\u0005\u000b\u0004\u0005K\u0019\u00021E\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0003\u0004(\u0001\u0001\u0006IaI\u0001\u0004i2\u0004\u0003cA\u0015/\u001f5\t!F\u0003\u0002,Y\u0005!A.\u00198h\u0015\u0005i\u0013\u0001\u00026bm\u0006L!a\f\u0016\u0003-%s\u0007.\u001a:ji\u0006\u0014G.\u001a+ie\u0016\fG\rT8dC2DQ!\r\u0013\u0005BI\nA\"\u001b8ji&\fGNV1mk\u0016$\u0012a\r\n\u0004i=Ia\u0001B\u00136\u0001M2AA\u000e\u0014\u0003o\t)A%\u00198p]N\u0011Q\u0007\u000b\u0005\u00067U\"\t!\u000f\u000b\u0002uA\u0011\u0001#\u000e\u0005\u0006cU\"\t\u0005\u0010\u000b\u0002{I\u0019ahD\u0005\u0007\t\u0015*\u0004!\u0010\u0005\u0006\u0001\u0002!\t!Q\u0001\u0006m\u0006dW/Z\u000b\u0002\u001f!)1\t\u0001C\u0001\t\u0006Iq/\u001b;i-\u0006dW/Z\u000b\u0003\u000b\"#\"AR(\u0015\u0005\u001dS\u0005C\u0001\tI\t\u0015I%I1\u0001\u0014\u0005\u0005\u0019\u0006BB&C\t\u0003\u0007A*A\u0003uQVt7\u000eE\u0002\u000b\u001b\u001eK!A\u0014\u0003\u0003\u0011q\u0012\u0017P\\1nKzBQ\u0001\u0015\"A\u0002=\taA\\3xm\u0006d\u0007\"\u0002*\u0001\t\u0003\u0019\u0016!\u0003<bYV,w\fJ3r)\t!v\u000b\u0005\u0002\u000b+&\u0011a\u000b\u0002\u0002\u0005+:LG\u000fC\u0003Q#\u0002\u0007q\u0002C\u0003Z\u0001\u0011\u0005#,\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0006C\u0001/`\u001d\tQQ,\u0003\u0002_\t\u00051\u0001K]3eK\u001aL!\u0001Y1\u0003\rM#(/\u001b8h\u0015\tqF\u0001")
public class DynamicVariable<T> {
    public final T scala$util$DynamicVariable$$init;
    private final InheritableThreadLocal<T> tl;

    private InheritableThreadLocal<T> tl() {
        return this.tl;
    }

    public T value() {
        return this.tl().get();
    }

    /*
     * WARNING - void declaration
     */
    public <S> S withValue(T newval, Function0<S> thunk) {
        S s2;
        T oldval = this.value();
        this.tl().set(newval);
        try {
            s2 = thunk.apply();
        }
        catch (Throwable throwable) {
            void var3_3;
            this.tl().set(var3_3);
            throw throwable;
        }
        this.tl().set(oldval);
        return s2;
    }

    public void value_$eq(T newval) {
        this.tl().set(newval);
    }

    public String toString() {
        return new StringBuilder().append((Object)"DynamicVariable(").append(this.value()).append((Object)")").toString();
    }

    public DynamicVariable(T init2) {
        this.scala$util$DynamicVariable$$init = init2;
        this.tl = new InheritableThreadLocal<T>(this){
            private final /* synthetic */ DynamicVariable $outer;

            public T initialValue() {
                return this.$outer.scala$util$DynamicVariable$$init;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }
}

