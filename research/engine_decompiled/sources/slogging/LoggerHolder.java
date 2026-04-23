/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001\u00152q!\u0001\u0002\u0011\u0002\u0007\u0005QA\u0001\u0007M_\u001e<WM\u001d%pY\u0012,'OC\u0001\u0004\u0003!\u0019Hn\\4hS:<7\u0001A\n\u0003\u0001\u0019\u0001\"a\u0002\u0006\u000e\u0003!Q\u0011!C\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0017!\u0011a!\u00118z%\u00164\u0007\"B\u0007\u0001\t\u0003q\u0011A\u0002\u0013j]&$H\u0005F\u0001\u0010!\t9\u0001#\u0003\u0002\u0012\u0011\t!QK\\5u\u0011\u001d\u0019\u0002A1A\u0005\u0016Q\t!\u0002\\8hO\u0016\u0014h*Y7f+\u0005)\u0002C\u0001\f\u001a\u001d\t9q#\u0003\u0002\u0019\u0011\u00051\u0001K]3eK\u001aL!AG\u000e\u0003\rM#(/\u001b8h\u0015\tA\u0002\u0002\u0003\u0004\u001e\u0001\u0001\u0006i!F\u0001\fY><w-\u001a:OC6,\u0007\u0005C\u0003 \u0001\u0019E\u0001%\u0001\u0004m_\u001e<WM]\u000b\u0002CA\u0011!eI\u0007\u0002\u0005%\u0011AE\u0001\u0002\u0007\u0019><w-\u001a:")
public interface LoggerHolder {
    public void slogging$LoggerHolder$_setter_$loggerName_$eq(String var1);

    public String loggerName();

    public UnderlyingLogger logger();
}

