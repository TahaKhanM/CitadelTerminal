/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001!2A!\u0001\u0002\u0003\u000b\tQQ*\u0019;dQ\u0016\u0013(o\u001c:\u000b\u0003\r\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\rA\u0011qa\u0003\b\u0003\u0011%i\u0011AA\u0005\u0003\u0015\t\tq\u0001]1dW\u0006<W-\u0003\u0002\r\u001b\t\u0001\"+\u001e8uS6,W\t_2faRLwN\u001c\u0006\u0003\u0015\tA\u0001b\u0004\u0001\u0003\u0002\u0003\u0006I\u0001E\u0001\u0004_\nT\u0007C\u0001\u0005\u0012\u0013\t\u0011\"AA\u0002B]fDQ\u0001\u0006\u0001\u0005\u0002U\ta\u0001P5oSRtDC\u0001\f\u0018!\tA\u0001\u0001C\u0003\u0010'\u0001\u0007\u0001\u0003\u0003\u0005\u001a\u0001!\u0015\r\u0011\"\u0003\u001b\u0003%y'M[*ue&tw-F\u0001\u001c!\ta\u0012%D\u0001\u001e\u0015\tqr$\u0001\u0003mC:<'\"\u0001\u0011\u0002\t)\fg/Y\u0005\u0003Eu\u0011aa\u0015;sS:<\u0007\u0002\u0003\u0013\u0001\u0011\u0003\u0005\u000b\u0015B\u000e\u0002\u0015=\u0014'n\u0015;sS:<\u0007\u0005C\u0003'\u0001\u0011\u0005s%\u0001\u0006hKRlUm]:bO\u0016$\u0012a\u0007")
public final class MatchError
extends RuntimeException {
    private final Object obj;
    private String objString;
    private volatile boolean bitmap$0;

    private String objString$lzycompute() {
        MatchError matchError = this;
        synchronized (matchError) {
            if (!this.bitmap$0) {
                this.objString = this.obj == null ? "null" : this.liftedTree1$1();
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.objString;
        }
    }

    private String objString() {
        return this.bitmap$0 ? this.objString : this.objString$lzycompute();
    }

    @Override
    public String getMessage() {
        return this.objString();
    }

    private final String ofClass$1() {
        return new StringBuilder().append((Object)"of class ").append((Object)this.obj.getClass().getName()).toString();
    }

    private final String liftedTree1$1() {
        String string2;
        try {
            string2 = new StringBuilder().append((Object)this.obj.toString()).append((Object)" (").append((Object)this.ofClass$1()).append((Object)")").toString();
        }
        catch (Throwable throwable) {
            string2 = new StringBuilder().append((Object)"an instance ").append((Object)this.ofClass$1()).toString();
        }
        return string2;
    }

    public MatchError(Object obj) {
        this.obj = obj;
    }
}

