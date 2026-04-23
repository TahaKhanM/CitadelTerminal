/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.reflect.ScalaSignature;
import slogging.FilterLoggerFactory$;
import slogging.UnderlyingLogger;
import slogging.UnderlyingLoggerFactory;

@ScalaSignature(bytes="\u0006\u0001\u0015:Q!\u0001\u0002\t\u0002\u0015\t1CR5mi\u0016\u0014Hj\\4hKJ4\u0015m\u0019;pefT\u0011aA\u0001\tg2|wmZ5oO\u000e\u0001\u0001C\u0001\u0004\b\u001b\u0005\u0011a!\u0002\u0005\u0003\u0011\u0003I!a\u0005$jYR,'\u000fT8hO\u0016\u0014h)Y2u_JL8cA\u0004\u000b!A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u0004\"AB\t\n\u0005I\u0011!aF+oI\u0016\u0014H._5oO2{wmZ3s\r\u0006\u001cGo\u001c:z\u0011\u0015!r\u0001\"\u0001\u0016\u0003\u0019a\u0014N\\5u}Q\tQ\u0001C\u0003\u0018\u000f\u0011\u0005\u0003$A\nhKR,f\u000eZ3sYfLgn\u001a'pO\u001e,'\u000f\u0006\u0002\u001a9A\u0011aAG\u0005\u00037\t\u0011\u0001#\u00168eKJd\u00170\u001b8h\u0019><w-\u001a:\t\u000bu1\u0002\u0019\u0001\u0010\u0002\t9\fW.\u001a\t\u0003?\tr!a\u0003\u0011\n\u0005\u0005b\u0011A\u0002)sK\u0012,g-\u0003\u0002$I\t11\u000b\u001e:j]\u001eT!!\t\u0007")
public final class FilterLoggerFactory {
    public static UnderlyingLoggerFactory apply() {
        return FilterLoggerFactory$.MODULE$.apply();
    }

    public static UnderlyingLogger getUnderlyingLogger(String string2) {
        return FilterLoggerFactory$.MODULE$.getUnderlyingLogger(string2);
    }
}

