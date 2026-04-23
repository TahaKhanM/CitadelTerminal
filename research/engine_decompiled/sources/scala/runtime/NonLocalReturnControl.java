/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.control.ControlThrowable;
import scala.util.control.NoStackTrace$class;

@ScalaSignature(bytes="\u0006\u0001}2A!\u0001\u0002\u0001\u000f\t)bj\u001c8M_\u000e\fGNU3ukJt7i\u001c8ue>d'BA\u0002\u0005\u0003\u001d\u0011XO\u001c;j[\u0016T\u0011!B\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tAaeE\u0002\u0001\u0013E\u0001\"A\u0003\b\u000f\u0005-aQ\"\u0001\u0003\n\u00055!\u0011a\u00029bG.\fw-Z\u0005\u0003\u001fA\u0011\u0011\u0002\u00165s_^\f'\r\\3\u000b\u00055!\u0001C\u0001\n\u0018\u001b\u0005\u0019\"B\u0001\u000b\u0016\u0003\u001d\u0019wN\u001c;s_2T!A\u0006\u0003\u0002\tU$\u0018\u000e\\\u0005\u00031M\u0011\u0001cQ8oiJ|G\u000e\u00165s_^\f'\r\\3\t\u0011i\u0001!Q1A\u0005\u0002m\t1a[3z+\u0005a\u0002CA\u0006\u001e\u0013\tqBA\u0001\u0004B]f\u0014VM\u001a\u0005\tA\u0001\u0011\t\u0011)A\u00059\u0005!1.Z=!\u0011!\u0011\u0003A!b\u0001\n\u0003\u0019\u0013!\u0002<bYV,W#\u0001\u0013\u0011\u0005\u00152C\u0002\u0001\u0003\nO\u0001\u0001\u000b\u0011!AC\u0002!\u0012\u0011\u0001V\t\u0003S1\u0002\"a\u0003\u0016\n\u0005-\"!a\u0002(pi\"Lgn\u001a\t\u0003\u00175J!A\f\u0003\u0003\u0007\u0005s\u0017\u0010\u000b\u0002'aA\u00111\"M\u0005\u0003e\u0011\u00111b\u001d9fG&\fG.\u001b>fI\"AA\u0007\u0001B\u0001B\u0003%A%\u0001\u0004wC2,X\r\t\u0005\u0006m\u0001!\taN\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007aR4\bE\u0002:\u0001\u0011j\u0011A\u0001\u0005\u00065U\u0002\r\u0001\b\u0005\u0006EU\u0002\r\u0001\n\u0005\u0006{\u0001!)EP\u0001\u0011M&dG.\u00138Ti\u0006\u001c7\u000e\u0016:bG\u0016$\u0012!\u0003")
public class NonLocalReturnControl<T>
extends Throwable
implements ControlThrowable {
    private final Object key;
    public final T value;

    @Override
    public /* synthetic */ Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
        return super.fillInStackTrace();
    }

    public Object key() {
        return this.key;
    }

    public T value() {
        return this.value;
    }

    @Override
    public final Throwable fillInStackTrace() {
        return this;
    }

    public boolean value$mcZ$sp() {
        return BoxesRunTime.unboxToBoolean(this.value());
    }

    public byte value$mcB$sp() {
        return BoxesRunTime.unboxToByte(this.value());
    }

    public char value$mcC$sp() {
        return BoxesRunTime.unboxToChar(this.value());
    }

    public double value$mcD$sp() {
        return BoxesRunTime.unboxToDouble(this.value());
    }

    public float value$mcF$sp() {
        return BoxesRunTime.unboxToFloat(this.value());
    }

    public int value$mcI$sp() {
        return BoxesRunTime.unboxToInt(this.value());
    }

    public long value$mcJ$sp() {
        return BoxesRunTime.unboxToLong(this.value());
    }

    public short value$mcS$sp() {
        return BoxesRunTime.unboxToShort(this.value());
    }

    public void value$mcV$sp() {
        this.value();
    }

    public boolean specInstance$() {
        return false;
    }

    public NonLocalReturnControl(Object key, T value) {
        this.key = key;
        this.value = value;
        NoStackTrace$class.$init$(this);
    }
}

