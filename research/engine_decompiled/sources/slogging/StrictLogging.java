/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.LoggerHolder;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001y1q!\u0001\u0002\u0011\u0002\u0007\u0005QAA\u0007TiJL7\r\u001e'pO\u001eLgn\u001a\u0006\u0002\u0007\u0005A1\u000f\\8hO&twm\u0001\u0001\u0014\u0007\u00011A\u0002\u0005\u0002\b\u00155\t\u0001BC\u0001\n\u0003\u0015\u00198-\u00197b\u0013\tY\u0001B\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001b9i\u0011AA\u0005\u0003\u001f\t\u0011A\u0002T8hO\u0016\u0014\bj\u001c7eKJDQ!\u0005\u0001\u0005\u0002I\ta\u0001J5oSR$C#A\n\u0011\u0005\u001d!\u0012BA\u000b\t\u0005\u0011)f.\u001b;\t\u000f]\u0001!\u0019!C\t1\u00051An\\4hKJ,\u0012!\u0007\t\u0003\u001biI!a\u0007\u0002\u0003\r1{wmZ3s\u0011\u0019i\u0002\u0001)A\u00053\u00059An\\4hKJ\u0004\u0003")
public interface StrictLogging
extends LoggerHolder {
    public void slogging$StrictLogging$_setter_$logger_$eq(UnderlyingLogger var1);

    @Override
    public UnderlyingLogger logger();
}

