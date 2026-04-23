/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NoFile$;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.Position;

@ScalaSignature(bytes="\u0006\u0001A;Q!\u0001\u0002\t\u0002-\tABT8T_V\u00148-\u001a$jY\u0016T!a\u0001\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001D\u0007\u000e\u0003\t1QA\u0004\u0002\t\u0002=\u0011ABT8T_V\u00148-\u001a$jY\u0016\u001c\"!\u0004\t\u0011\u00051\t\u0012B\u0001\n\u0003\u0005)\u0019v.\u001e:dK\u001aKG.\u001a\u0005\u0006)5!\t!F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-AQaF\u0007\u0005\u0002a\tqaY8oi\u0016tG/F\u0001\u001a!\rQ2$H\u0007\u0002\u0011%\u0011A\u0004\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u00035yI!a\b\u0005\u0003\t\rC\u0017M\u001d\u0005\u0006C5!\tAI\u0001\u0005M&dW-F\u0001$\u001d\taA%\u0003\u0002&\u0005\u00051aj\u001c$jY\u0016DQaJ\u0007\u0005\u0002!\n1\"[:MS:,'I]3bWR\u0011\u0011\u0006\f\t\u00035)J!a\u000b\u0005\u0003\u000f\t{w\u000e\\3b]\")QF\na\u0001]\u0005\u0019\u0011\u000e\u001a=\u0011\u0005iy\u0013B\u0001\u0019\t\u0005\rIe\u000e\u001e\u0005\u0006e5!\taM\u0001\fSN,e\u000eZ(g\u0019&tW\r\u0006\u0002*i!)Q&\ra\u0001]!)a'\u0004C\u0001o\u0005y\u0011n]*fY\u001a\u001cuN\u001c;bS:,G-F\u0001*\u0011\u0015IT\u0002\"\u0001;\u0003\u0019aWM\\4uQV\ta\u0006C\u0003=\u001b\u0011\u0005Q(\u0001\u0007pM\u001a\u001cX\r\u001e+p\u0019&tW\r\u0006\u0002/}!)qh\u000fa\u0001]\u00051qN\u001a4tKRDQ!Q\u0007\u0005\u0002\t\u000bA\u0002\\5oKR{wJ\u001a4tKR$\"AL\"\t\u000b\u0011\u0003\u0005\u0019\u0001\u0018\u0002\u000b%tG-\u001a=\t\u000b\u0019kA\u0011I$\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\u0013\t\u0003\u0013:k\u0011A\u0013\u0006\u0003\u00172\u000bA\u0001\\1oO*\tQ*\u0001\u0003kCZ\f\u0017BA(K\u0005\u0019\u0019FO]5oO\u0002")
public final class NoSourceFile {
    public static String toString() {
        return NoSourceFile$.MODULE$.toString();
    }

    public static int lineToOffset(int n) {
        return NoSourceFile$.MODULE$.lineToOffset(n);
    }

    public static int offsetToLine(int n) {
        return NoSourceFile$.MODULE$.offsetToLine(n);
    }

    public static int length() {
        return NoSourceFile$.MODULE$.length();
    }

    public static boolean isSelfContained() {
        return NoSourceFile$.MODULE$.isSelfContained();
    }

    public static boolean isEndOfLine(int n) {
        return NoSourceFile$.MODULE$.isEndOfLine(n);
    }

    public static boolean isLineBreak(int n) {
        return NoSourceFile$.MODULE$.isLineBreak(n);
    }

    public static NoFile$ file() {
        return NoSourceFile$.MODULE$.file();
    }

    public static char[] content() {
        return NoSourceFile$.MODULE$.content();
    }

    public static Option<String> identifier(Position position) {
        return NoSourceFile$.MODULE$.identifier(position);
    }

    public static int skipWhitespace(int n) {
        return NoSourceFile$.MODULE$.skipWhitespace(n);
    }

    public static String lineToString(int n) {
        return NoSourceFile$.MODULE$.lineToString(n);
    }

    public static String path() {
        return NoSourceFile$.MODULE$.path();
    }

    public static Position positionInUltimateSource(Position position) {
        return NoSourceFile$.MODULE$.positionInUltimateSource(position);
    }

    public static Position position(int n) {
        return NoSourceFile$.MODULE$.position(n);
    }
}

