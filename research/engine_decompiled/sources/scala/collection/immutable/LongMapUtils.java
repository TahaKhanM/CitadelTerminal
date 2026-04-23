/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.LongMap;
import scala.collection.immutable.LongMapUtils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001Q;a!\u0001\u0002\t\u0002\tA\u0011\u0001\u0004'p]\u001el\u0015\r]+uS2\u001c(BA\u0002\u0005\u0003%IW.\\;uC\ndWM\u0003\u0002\u0006\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u001d\tQa]2bY\u0006\u0004\"!\u0003\u0006\u000e\u0003\t1aa\u0003\u0002\t\u0002\ta!\u0001\u0004'p]\u001el\u0015\r]+uS2\u001c8c\u0001\u0006\u000e#A\u0011abD\u0007\u0002\r%\u0011\u0001C\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0005IAbBA\n\u0017\u001b\u0005!\"BA\u000b\u0005\u0003\u001d9WM\\3sS\u000eL!a\u0006\u000b\u0002\u001b\tKGo\u00149fe\u0006$\u0018n\u001c8t\u0013\tI\"D\u0001\u0003M_:<'BA\f\u0015\u0011\u0015a\"\u0002\"\u0001\u001f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0005\t\u000b\u0001RA\u0011A\u0011\u0002\u0015\t\u0014\u0018M\\2i\u001b\u0006\u001c8\u000eF\u0002#I%\u0002\"AD\u0012\n\u0005e1\u0001\"B\u0013 \u0001\u00041\u0013!A5\u0011\u0005\u001dBS\"\u0001\u0006\n\u0005eA\u0002\"\u0002\u0016 \u0001\u00041\u0013!\u00016\t\u000b1RA\u0011A\u0017\u0002\t)|\u0017N\\\u000b\u0003]Q\"RaL\u001f@\u0003\u000e\u00032!\u0003\u00193\u0013\t\t$AA\u0004M_:<W*\u00199\u0011\u0005M\"D\u0002\u0001\u0003\u0006k-\u0012\rA\u000e\u0002\u0002)F\u0011qG\u000f\t\u0003\u001daJ!!\u000f\u0004\u0003\u000f9{G\u000f[5oOB\u0011abO\u0005\u0003y\u0019\u00111!\u00118z\u0011\u0015q4\u00061\u0001'\u0003\t\u0001\u0018\u0007C\u0003AW\u0001\u0007q&\u0001\u0002uc!)!i\u000ba\u0001M\u0005\u0011\u0001O\r\u0005\u0006\t.\u0002\raL\u0001\u0003iJBQA\u0012\u0006\u0005\u0002\u001d\u000b1AY5o+\tA5\nF\u0003J\u0019:\u0003&\u000bE\u0002\na)\u0003\"aM&\u0005\u000bU*%\u0019\u0001\u001c\t\u000b5+\u0005\u0019\u0001\u0014\u0002\rA\u0014XMZ5y\u0011\u0015yU\t1\u0001'\u0003\u0011i\u0017m]6\t\u000bE+\u0005\u0019A%\u0002\t1,g\r\u001e\u0005\u0006'\u0016\u0003\r!S\u0001\u0006e&<\u0007\u000e\u001e")
public final class LongMapUtils {
    public static String bitString$default$2() {
        return LongMapUtils$.MODULE$.bitString$default$2();
    }

    public static long highestOneBit(long l) {
        return LongMapUtils$.MODULE$.highestOneBit(l);
    }

    public static String bitString(long l, String string2) {
        return LongMapUtils$.MODULE$.bitString(l, string2);
    }

    public static IndexedSeq<Object> bits(long l) {
        return LongMapUtils$.MODULE$.bits(l);
    }

    public static long complement(long l) {
        return LongMapUtils$.MODULE$.complement(l);
    }

    public static boolean shorter(long l, long l2) {
        return LongMapUtils$.MODULE$.shorter(l, l2);
    }

    public static boolean unsignedCompare(long l, long l2) {
        return LongMapUtils$.MODULE$.unsignedCompare(l, l2);
    }

    public static boolean hasMatch(long l, long l2, long l3) {
        return LongMapUtils$.MODULE$.hasMatch(l, l2, l3);
    }

    public static long mask(long l, long l2) {
        return LongMapUtils$.MODULE$.mask(l, l2);
    }

    public static boolean zero(long l, long l2) {
        return LongMapUtils$.MODULE$.zero(l, l2);
    }

    public static <T> LongMap<T> bin(long l, long l2, LongMap<T> longMap, LongMap<T> longMap2) {
        return LongMapUtils$.MODULE$.bin(l, l2, longMap, longMap2);
    }

    public static <T> LongMap<T> join(long l, LongMap<T> longMap, long l2, LongMap<T> longMap2) {
        return LongMapUtils$.MODULE$.join(l, longMap, l2, longMap2);
    }

    public static long branchMask(long l, long l2) {
        return LongMapUtils$.MODULE$.branchMask(l, l2);
    }
}

