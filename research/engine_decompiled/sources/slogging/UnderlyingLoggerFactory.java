/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.UnderlyingLogger;

@ScalaSignature(bytes="\u0006\u0001\u00192q!\u0001\u0002\u0011\u0002\u0007\u0005QAA\fV]\u0012,'\u000f\\=j]\u001edunZ4fe\u001a\u000b7\r^8ss*\t1!\u0001\u0005tY><w-\u001b8h\u0007\u0001\u0019\"\u0001\u0001\u0004\u0011\u0005\u001dQQ\"\u0001\u0005\u000b\u0003%\tQa]2bY\u0006L!a\u0003\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015i\u0001\u0001\"\u0001\u000f\u0003\u0019!\u0013N\\5uIQ\tq\u0002\u0005\u0002\b!%\u0011\u0011\u0003\u0003\u0002\u0005+:LG\u000fC\u0003\u0014\u0001\u0019\u0005A#A\nhKR,f\u000eZ3sYfLgn\u001a'pO\u001e,'\u000f\u0006\u0002\u00163A\u0011acF\u0007\u0002\u0005%\u0011\u0001D\u0001\u0002\u0011+:$WM\u001d7zS:<Gj\\4hKJDQA\u0007\nA\u0002m\tAA\\1nKB\u0011Ad\b\b\u0003\u000fuI!A\b\u0005\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0013E\u0001\u0004TiJLgn\u001a\u0006\u0003=!AQa\t\u0001\u0005\u0002\u0011\nQ!\u00199qYf$\u0012!\n\t\u0003-\u0001\u0001")
public interface UnderlyingLoggerFactory {
    public UnderlyingLogger getUnderlyingLogger(String var1);

    public UnderlyingLoggerFactory apply();
}

