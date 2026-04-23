/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.reflect.io.IOStats$;

@ScalaSignature(bytes="\u0006\u00019:a!\u0001\u0002\t\u0002\tA\u0011aB%P'R\fGo\u001d\u0006\u0003\u0007\u0011\t!![8\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mCB\u0011\u0011BC\u0007\u0002\u0005\u001911B\u0001E\u0001\u00051\u0011q!S(Ti\u0006$8o\u0005\u0002\u000b\u001bA\u0011abD\u0007\u0002\r%\u0011\u0001C\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000bIQA\u0011\u0001\u000b\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001\u0003\u0005\b-)\u0011\r\u0011\"\u0001\u0018\u0003=1\u0017\u000e\\3Fq&\u001cHo]\"pk:$X#\u0001\r\u0011\u0005e\tcB\u0001\u000e \u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0011)H/\u001b7\u000b\u0005y!\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0001Z\u0012AC*uCRL7\u000f^5dg&\u0011!e\t\u0002\b\u0007>,h\u000e^3s\u0015\t\u00013\u0004\u0003\u0004&\u0015\u0001\u0006I\u0001G\u0001\u0011M&dW-\u0012=jgR\u001c8i\\;oi\u0002Bqa\n\u0006C\u0002\u0013\u0005q#\u0001\u000bgS2,\u0017j\u001d#je\u0016\u001cGo\u001c:z\u0007>,h\u000e\u001e\u0005\u0007S)\u0001\u000b\u0011\u0002\r\u0002+\u0019LG.Z%t\t&\u0014Xm\u0019;pef\u001cu.\u001e8uA!91F\u0003b\u0001\n\u00039\u0012a\u00044jY\u0016L5OR5mK\u000e{WO\u001c;\t\r5R\u0001\u0015!\u0003\u0019\u0003A1\u0017\u000e\\3Jg\u001aKG.Z\"pk:$\b\u0005")
public final class IOStats {
    public static Statistics.Counter fileIsFileCount() {
        return IOStats$.MODULE$.fileIsFileCount();
    }

    public static Statistics.Counter fileIsDirectoryCount() {
        return IOStats$.MODULE$.fileIsDirectoryCount();
    }

    public static Statistics.Counter fileExistsCount() {
        return IOStats$.MODULE$.fileExistsCount();
    }
}

