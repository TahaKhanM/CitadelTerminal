/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import slogging.LoggingUtils$;

@ScalaSignature(bytes="\u0006\u0001U:Q!\u0001\u0002\t\u0002\u0015\tA\u0002T8hO&tw-\u0016;jYNT\u0011aA\u0001\tg2|wmZ5oO\u000e\u0001\u0001C\u0001\u0004\b\u001b\u0005\u0011a!\u0002\u0005\u0003\u0011\u0003I!\u0001\u0004'pO\u001eLgnZ+uS2\u001c8CA\u0004\u000b!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fM\")\u0011c\u0002C\u0001%\u00051A(\u001b8jiz\"\u0012!\u0002\u0005\u0006)\u001d!\t!F\u0001\u0012CJ<7O\u0011:bG.,GOR8s[\u0006$Hc\u0001\f\u001e?A\u0011qC\u0007\b\u0003\u0017aI!!\u0007\u0007\u0002\rA\u0013X\rZ3g\u0013\tYBD\u0001\u0004TiJLgn\u001a\u0006\u000331AQAH\nA\u0002Y\t1!\\:h\u0011\u0015\u00013\u00031\u0001\"\u0003\u0011\t'oZ:\u0011\u0007\tRSF\u0004\u0002$Q9\u0011AeJ\u0007\u0002K)\u0011a\u0005B\u0001\u0007yI|w\u000e\u001e \n\u00035I!!\u000b\u0007\u0002\u000fA\f7m[1hK&\u00111\u0006\f\u0002\u0004'\u0016\f(BA\u0015\r!\tYa&\u0003\u00020\u0019\t\u0019\u0011I\\=\t\u000bE:A\u0011\u0001\u001a\u0002!\u0005\u0014xm]*ue&twMR8s[\u0006$Hc\u0001\f4i!)a\u0004\ra\u0001-!)\u0001\u0005\ra\u0001C\u0001")
public final class LoggingUtils {
    public static String argsStringFormat(String string2, Seq<Object> seq) {
        return LoggingUtils$.MODULE$.argsStringFormat(string2, seq);
    }

    public static String argsBracketFormat(String string2, Seq<Object> seq) {
        return LoggingUtils$.MODULE$.argsBracketFormat(string2, seq);
    }
}

