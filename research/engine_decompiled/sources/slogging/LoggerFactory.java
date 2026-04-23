/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.LoggerFactory$;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001\t:Q!\u0001\u0002\t\u0002\u0015\tQ\u0002T8hO\u0016\u0014h)Y2u_JL(\"A\u0002\u0002\u0011MdwnZ4j]\u001e\u001c\u0001\u0001\u0005\u0002\u0007\u000f5\t!AB\u0003\t\u0005!\u0005\u0011BA\u0007M_\u001e<WM\u001d$bGR|'/_\n\u0003\u000f)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0007\"B\t\b\t\u0003\u0011\u0012A\u0002\u001fj]&$h\bF\u0001\u0006\u0011\u0015!r\u0001\"\u0001\u0016\u0003%9W\r\u001e'pO\u001e,'\u000f\u0006\u0002\u00173A\u0011aaF\u0005\u00031\t\u0011a\u0001T8hO\u0016\u0014\b\"\u0002\u000e\u0014\u0001\u0004Y\u0012\u0001\u00028b[\u0016\u0004\"\u0001H\u0010\u000f\u0005-i\u0012B\u0001\u0010\r\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001%\t\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005ya\u0001")
public final class LoggerFactory {
    public static UnderlyingLogger getLogger(String string2) {
        return LoggerFactory$.MODULE$.getLogger(string2);
    }
}

