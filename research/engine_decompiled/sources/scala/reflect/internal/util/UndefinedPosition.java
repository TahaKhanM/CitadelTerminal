/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.Position;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001I2Q!\u0001\u0002\u0002\"-\u0011\u0011#\u00168eK\u001aLg.\u001a3Q_NLG/[8o\u0015\t\u0019A!\u0001\u0003vi&d'BA\u0003\u0007\u0003!Ig\u000e^3s]\u0006d'BA\u0004\t\u0003\u001d\u0011XM\u001a7fGRT\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e\u001d5\t!!\u0003\u0002\u0010\u0005\tA\u0001k\\:ji&|g\u000eC\u0003\u0012\u0001\u0011\u0005!#\u0001\u0004=S:LGO\u0010\u000b\u0002'A\u0011Q\u0002\u0001\u0005\u0006+\u0001!)EF\u0001\nSN$UMZ5oK\u0012,\u0012a\u0006\t\u00031ei\u0011\u0001C\u0005\u00035!\u0011qAQ8pY\u0016\fg\u000eC\u0003\u001d\u0001\u0011\u0005c#A\u0004jgJ\u000bgnZ3\t\u000by\u0001A\u0011I\u0010\u0002\rM|WO]2f+\u0005\u0001cBA\u0007\"\u0013\t\u0011#!\u0001\u0007O_N{WO]2f\r&dW\rC\u0003%\u0001\u0011\u0005S%A\u0003ti\u0006\u0014H/F\u0001'!\tAr%\u0003\u0002)\u0011\t9aj\u001c;iS:<\u0007\"\u0002\u0016\u0001\t\u0003*\u0013!\u00029pS:$\b\"\u0002\u0017\u0001\t\u0003*\u0013aA3oI&\u001a\u0001A\f\u0019\n\u0005=\u0012!a\u0002$bW\u0016\u0004vn\u001d\u0006\u0003c\t\t!BT8Q_NLG/[8o\u0001")
public abstract class UndefinedPosition
extends Position {
    @Override
    public final boolean isDefined() {
        return false;
    }

    @Override
    public boolean isRange() {
        return false;
    }

    @Override
    public NoSourceFile$ source() {
        return NoSourceFile$.MODULE$;
    }

    public Nothing$ start() {
        return this.fail("start");
    }

    public Nothing$ point() {
        return this.fail("point");
    }

    public Nothing$ end() {
        return this.fail("end");
    }
}

