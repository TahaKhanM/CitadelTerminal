/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Phase;

@ScalaSignature(bytes="\u0006\u0001)2\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005!\u0001\u0003\u0002\r%\u00164G.Z2u'\u0016$X\u000f\u001d\u0006\u0003\u0007\u0011\tqA];oi&lWM\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0014\u0005\u0001I\u0001C\u0001\u0006\u000e\u001b\u0005Y!B\u0001\u0007\u0005\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\b\f\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3\t\u000bA\u0001A\u0011\u0001\n\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u0005\t\u0003)Ui\u0011AB\u0005\u0003-\u0019\u0011A!\u00168ji\"9\u0001\u0004\u0001b\u0001\n\u0003J\u0012a\u00039iCN,w+\u001b;i\u0013\u0012,\u0012A\u0007\t\u0004)mi\u0012B\u0001\u000f\u0007\u0005\u0015\t%O]1z!\tQa$\u0003\u0002 \u0017\t)\u0001\u000b[1tK\"1\u0011\u0005\u0001Q\u0001\ni\tA\u0002\u001d5bg\u0016<\u0016\u000e\u001e5JI\u0002Bqa\t\u0001C\u0002\u0013\u0005C%\u0001\u0007dkJ\u0014XM\u001c;Sk:LE-F\u0001&!\t!b%\u0003\u0002(\r\t\u0019\u0011J\u001c;\t\r%\u0002\u0001\u0015!\u0003&\u00035\u0019WO\u001d:f]R\u0014VO\\%eA\u0001")
public interface ReflectSetup {
    public void scala$reflect$runtime$ReflectSetup$_setter_$phaseWithId_$eq(Phase[] var1);

    public void scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(int var1);

    public Phase[] phaseWithId();

    public int currentRunId();
}

