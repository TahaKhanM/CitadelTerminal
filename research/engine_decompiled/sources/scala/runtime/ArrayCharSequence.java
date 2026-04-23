/*
 * Decompiled with CFR 0.152.
 */
package scala.runtime;

import scala.math.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\r3A!\u0001\u0002\u0003\u000f\t\t\u0012I\u001d:bs\u000eC\u0017M]*fcV,gnY3\u000b\u0005\r!\u0011a\u0002:v]RLW.\u001a\u0006\u0002\u000b\u0005)1oY1mC\u000e\u00011c\u0001\u0001\t!A\u0011\u0011BD\u0007\u0002\u0015)\u00111\u0002D\u0001\u0005Y\u0006twMC\u0001\u000e\u0003\u0011Q\u0017M^1\n\u0005=Q!AB(cU\u0016\u001cG\u000f\u0005\u0002\n#%\u0011!C\u0003\u0002\r\u0007\"\f'oU3rk\u0016t7-\u001a\u0005\t)\u0001\u0011)\u0019!C\u0001+\u0005\u0011\u0001p]\u000b\u0002-A\u0019q\u0003\u0007\u000e\u000e\u0003\u0011I!!\u0007\u0003\u0003\u000b\u0005\u0013(/Y=\u0011\u0005]Y\u0012B\u0001\u000f\u0005\u0005\u0011\u0019\u0005.\u0019:\t\u0011y\u0001!\u0011!Q\u0001\nY\t1\u0001_:!\u0011!\u0001\u0003A!A!\u0002\u0013\t\u0013!B:uCJ$\bCA\f#\u0013\t\u0019CAA\u0002J]RD\u0001\"\n\u0001\u0003\u0002\u0003\u0006I!I\u0001\u0004K:$\u0007\"B\u0014\u0001\t\u0003A\u0013A\u0002\u001fj]&$h\b\u0006\u0003*W1j\u0003C\u0001\u0016\u0001\u001b\u0005\u0011\u0001\"\u0002\u000b'\u0001\u00041\u0002\"\u0002\u0011'\u0001\u0004\t\u0003\"B\u0013'\u0001\u0004\t\u0003\"B\u0018\u0001\t\u0003\u0001\u0014A\u00027f]\u001e$\b\u000eF\u0001\"\u0011\u0015\u0011\u0004\u0001\"\u00014\u0003\u0019\u0019\u0007.\u0019:BiR\u0011!\u0004\u000e\u0005\u0006kE\u0002\r!I\u0001\u0006S:$W\r\u001f\u0005\u0006o\u0001!\t\u0001O\u0001\fgV\u00147+Z9vK:\u001cW\rF\u0002\u0011smBQA\u000f\u001cA\u0002\u0005\naa\u001d;beR\u0004\u0004\"\u0002\u001f7\u0001\u0004\t\u0013\u0001B3oIBBQA\u0010\u0001\u0005B}\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0001B\u0011\u0011\"Q\u0005\u0003\u0005*\u0011aa\u0015;sS:<\u0007")
public final class ArrayCharSequence
implements CharSequence {
    private final char[] xs;
    private final int start;
    private final int end;

    public char[] xs() {
        return this.xs;
    }

    @Override
    public int length() {
        return package$.MODULE$.max(0, this.end - this.start);
    }

    @Override
    public char charAt(int index) {
        if (0 <= index && index < this.length()) {
            return this.xs()[this.start + index];
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public CharSequence subSequence(int start0, int end0) {
        ArrayCharSequence arrayCharSequence;
        if (start0 < 0) {
            throw new ArrayIndexOutOfBoundsException(start0);
        }
        if (end0 > this.length()) {
            throw new ArrayIndexOutOfBoundsException(end0);
        }
        if (end0 <= start0) {
            arrayCharSequence = new ArrayCharSequence(this.xs(), 0, 0);
        } else {
            int newlen = end0 - start0;
            int start1 = this.start + start0;
            arrayCharSequence = new ArrayCharSequence(this.xs(), start1, start1 + newlen);
        }
        return arrayCharSequence;
    }

    @Override
    public String toString() {
        int end;
        int start = package$.MODULE$.max(this.start, 0);
        return start >= (end = package$.MODULE$.min(this.xs().length, start + this.length())) ? "" : new String(this.xs(), start, end - start);
    }

    public ArrayCharSequence(char[] xs, int start, int end) {
        this.xs = xs;
        this.start = start;
        this.end = end;
    }
}

