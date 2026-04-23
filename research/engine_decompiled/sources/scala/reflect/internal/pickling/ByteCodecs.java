/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.pickling.ByteCodecs$;

@ScalaSignature(bytes="\u0006\u0001q:Q!\u0001\u0002\t\u0002-\t!BQ=uK\u000e{G-Z2t\u0015\t\u0019A!\u0001\u0005qS\u000e\\G.\u001b8h\u0015\t)a!\u0001\u0005j]R,'O\\1m\u0015\t9\u0001\"A\u0004sK\u001adWm\u0019;\u000b\u0003%\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\r\u001b5\t!AB\u0003\u000f\u0005!\u0005qB\u0001\u0006CsR,7i\u001c3fGN\u001c\"!\u0004\t\u0011\u0005E\u0011R\"\u0001\u0005\n\u0005MA!AB!osJ+g\rC\u0003\u0016\u001b\u0011\u0005a#\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017!)\u0001$\u0004C\u00013\u0005I\u0011M^8jIj+'o\u001c\u000b\u00035\u0001\u00022!E\u000e\u001e\u0013\ta\u0002BA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0012=%\u0011q\u0004\u0003\u0002\u0005\u0005f$X\rC\u0003\"/\u0001\u0007!$A\u0002te\u000eDQaI\u0007\u0005\u0002\u0011\naB]3hK:,'/\u0019;f5\u0016\u0014x\u000e\u0006\u0002&QA\u0011\u0011CJ\u0005\u0003O!\u00111!\u00138u\u0011\u0015\t#\u00051\u0001\u001b\u0011\u0015QS\u0002\"\u0001,\u0003))gnY8eKb\"xn\u000e\u000b\u000351BQ!I\u0015A\u0002iAQAL\u0007\u0005\u0002=\n!\u0002Z3d_\u0012,w\u0007^89)\r)\u0003'\r\u0005\u0006C5\u0002\rA\u0007\u0005\u0006e5\u0002\r!J\u0001\u0007gJ\u001cG.\u001a8\t\u000bQjA\u0011A\u001b\u0002\r\u0015t7m\u001c3f)\tQb\u0007C\u00038g\u0001\u0007!$\u0001\u0002yg\")\u0011(\u0004C\u0001u\u00051A-Z2pI\u0016$\"!J\u001e\t\u000b]B\u0004\u0019\u0001\u000e")
public final class ByteCodecs {
    public static int decode(byte[] byArray) {
        return ByteCodecs$.MODULE$.decode(byArray);
    }

    public static byte[] encode(byte[] byArray) {
        return ByteCodecs$.MODULE$.encode(byArray);
    }

    public static int decode7to8(byte[] byArray, int n) {
        return ByteCodecs$.MODULE$.decode7to8(byArray, n);
    }

    public static byte[] encode8to7(byte[] byArray) {
        return ByteCodecs$.MODULE$.encode8to7(byArray);
    }

    public static int regenerateZero(byte[] byArray) {
        return ByteCodecs$.MODULE$.regenerateZero(byArray);
    }

    public static byte[] avoidZero(byte[] byArray) {
        return ByteCodecs$.MODULE$.avoidZero(byArray);
    }
}

