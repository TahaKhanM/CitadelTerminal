/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NoOpOwnerOnlyChmod$;

@ScalaSignature(bytes="\u0006\u0001):Q!\u0001\u0002\t\u0002-\t!CT8Pa>;h.\u001a:P]2L8\t[7pI*\u00111\u0001B\u0001\u0005kRLGN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A\"D\u0007\u0002\u0005\u0019)aB\u0001E\u0001\u001f\t\u0011bj\\(q\u001f^tWM](oYf\u001c\u0005.\\8e'\ri\u0001\u0003\u0006\t\u0003#Ii\u0011\u0001C\u0005\u0003'!\u0011a!\u00118z%\u00164\u0007C\u0001\u0007\u0016\u0013\t1\"A\u0001\bPo:,'o\u00148ms\u000eCWn\u001c3\t\u000baiA\u0011A\r\u0002\rqJg.\u001b;?)\u0005Y\u0001\"B\u000e\u000e\t\u0003b\u0012!B2i[>$GCA\u000f!!\t\tb$\u0003\u0002 \u0011\t!QK\\5u\u0011\u0015\t#\u00041\u0001#\u0003\u00111\u0017\u000e\\3\u0011\u0005\rBS\"\u0001\u0013\u000b\u0005\u00152\u0013AA5p\u0015\u00059\u0013\u0001\u00026bm\u0006L!!\u000b\u0013\u0003\t\u0019KG.\u001a")
public final class NoOpOwnerOnlyChmod {
    public static void chmodOrCreateEmpty(File file) {
        NoOpOwnerOnlyChmod$.MODULE$.chmodOrCreateEmpty(file);
    }

    public static void chmodAndWrite(File file, byte[] byArray) {
        NoOpOwnerOnlyChmod$.MODULE$.chmodAndWrite(file, byArray);
    }

    public static void chmod(File file) {
        NoOpOwnerOnlyChmod$.MODULE$.chmod(file);
    }
}

