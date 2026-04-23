/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.NullLoggerFactory$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;

@ScalaSignature(bytes="\u0006\u0001\u0015:Q!\u0001\u0002\t\u0002\u0015\t\u0011CT;mY2{wmZ3s\r\u0006\u001cGo\u001c:z\u0015\u0005\u0019\u0011\u0001C:m_\u001e<\u0017N\\4\u0004\u0001A\u0011aaB\u0007\u0002\u0005\u0019)\u0001B\u0001E\u0001\u0013\t\tb*\u001e7m\u0019><w-\u001a:GC\u000e$xN]=\u0014\u0007\u001dQ\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003\rEI!A\u0005\u0002\u0003/UsG-\u001a:ms&tw\rT8hO\u0016\u0014h)Y2u_JL\b\"\u0002\u000b\b\t\u0003)\u0012A\u0002\u001fj]&$h\bF\u0001\u0006\u0011\u00159r\u0001\"\u0011\u0019\u0003M9W\r^+oI\u0016\u0014H._5oO2{wmZ3s)\tIB\u0004\u0005\u0002\u00075%\u00111D\u0001\u0002\u0011+:$WM\u001d7zS:<Gj\\4hKJDQ!\b\fA\u0002y\tAA\\1nKB\u0011qD\t\b\u0003\u0017\u0001J!!\t\u0007\u0002\rA\u0013X\rZ3g\u0013\t\u0019CE\u0001\u0004TiJLgn\u001a\u0006\u0003C1\u0001")
public final class NullLoggerFactory {
    public static UnderlyingLoggerFactory apply() {
        return NullLoggerFactory$.MODULE$.apply();
    }

    public static UnderlyingLogger getUnderlyingLogger(String string2) {
        return NullLoggerFactory$.MODULE$.getUnderlyingLogger(string2);
    }
}

