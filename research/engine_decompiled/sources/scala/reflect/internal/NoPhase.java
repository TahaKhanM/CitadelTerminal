/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;

@ScalaSignature(bytes="\u0006\u00011:Q!\u0001\u0002\t\u0002%\tqAT8QQ\u0006\u001cXM\u0003\u0002\u0004\t\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011!bC\u0007\u0002\u0005\u0019)AB\u0001E\u0001\u001b\t9aj\u001c)iCN,7CA\u0006\u000f!\tQq\"\u0003\u0002\u0011\u0005\t)\u0001\u000b[1tK\")!c\u0003C\u0001'\u00051A(\u001b8jiz\"\u0012!\u0003\u0005\u0006+-!\tAF\u0001\u0005]\u0006lW-F\u0001\u0018!\tAR$D\u0001\u001a\u0015\tQ2$\u0001\u0003mC:<'\"\u0001\u000f\u0002\t)\fg/Y\u0005\u0003=e\u0011aa\u0015;sS:<\u0007\"\u0002\u0011\f\t\u0003\n\u0013aD6fKB\u001cH+\u001f9f!\u0006\u0014\u0018-\\:\u0016\u0003\t\u0002\"a\t\u0013\u000e\u0003\u0019I!!\n\u0004\u0003\u000f\t{w\u000e\\3b]\")qe\u0003C\u0001Q\u0005\u0019!/\u001e8\u0015\u0003%\u0002\"a\t\u0016\n\u0005-2!\u0001B+oSR\u0004")
public final class NoPhase {
    public static void run() {
        NoPhase$.MODULE$.run();
    }

    public static boolean keepsTypeParams() {
        return NoPhase$.MODULE$.keepsTypeParams();
    }

    public static String name() {
        return NoPhase$.MODULE$.name();
    }

    public static boolean equals(Object object) {
        return NoPhase$.MODULE$.equals(object);
    }

    public static int hashCode() {
        return NoPhase$.MODULE$.hashCode();
    }

    public static String toString() {
        return NoPhase$.MODULE$.toString();
    }

    public static boolean refChecked() {
        return NoPhase$.MODULE$.refChecked();
    }

    public static boolean flatClasses() {
        return NoPhase$.MODULE$.flatClasses();
    }

    public static boolean erasedTypes() {
        return NoPhase$.MODULE$.erasedTypes();
    }

    public static boolean specialized() {
        return NoPhase$.MODULE$.specialized();
    }

    public static boolean checkable() {
        return NoPhase$.MODULE$.checkable();
    }

    public static String description() {
        return NoPhase$.MODULE$.description();
    }

    public static Iterator<Phase> iterator() {
        return NoPhase$.MODULE$.iterator();
    }

    public static boolean hasNext() {
        return NoPhase$.MODULE$.hasNext();
    }

    public static Phase next() {
        return NoPhase$.MODULE$.next();
    }

    public static long flagMask() {
        return NoPhase$.MODULE$.flagMask();
    }

    public static long fmask() {
        return NoPhase$.MODULE$.fmask();
    }

    public static long newFlags() {
        return NoPhase$.MODULE$.newFlags();
    }

    public static long nextFlags() {
        return NoPhase$.MODULE$.nextFlags();
    }

    public static int id() {
        return NoPhase$.MODULE$.id();
    }

    public static Phase prev() {
        return NoPhase$.MODULE$.prev();
    }
}

