/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.JavaUniverse;
import scala.reflect.macros.blackbox.Context;
import scala.reflect.runtime.Macros$;

@ScalaSignature(bytes="\u0006\u0001]:a!\u0001\u0002\t\u0002\u0019A\u0011AB'bGJ|7O\u0003\u0002\u0004\t\u00059!/\u001e8uS6,'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.\u0019\t\u0003\u0013)i\u0011A\u0001\u0004\u0007\u0017\tA\tA\u0002\u0007\u0003\r5\u000b7M]8t'\tQQ\u0002\u0005\u0002\u000f\u001f5\ta!\u0003\u0002\u0011\r\t1\u0011I\\=SK\u001aDQA\u0005\u0006\u0005\u0002Q\ta\u0001P5oSRt4\u0001\u0001\u000b\u0002\u0011!)aC\u0003C\u0001/\u0005i1-\u001e:sK:$X*\u001b:s_J$\"\u0001G\u000e\u0011\u0007e)\u0013F\u0004\u0002\u001b71\u0001\u0001\"\u0002\u000f\u0016\u0001\u0004i\u0012!A2\u0011\u0005y\u0019S\"A\u0010\u000b\u0005\u0001\n\u0013\u0001\u00032mC\u000e\\'m\u001c=\u000b\u0005\t\"\u0011AB7bGJ|7/\u0003\u0002%?\t91i\u001c8uKb$\u0018B\u0001\u0014(\u0005\u0011)\u0005\u0010\u001d:\n\u0005!\n#aB!mS\u0006\u001cXm\u001d\t\u0003UEr!a\u000b\u0018\u000f\u0005%a\u0013BA\u0017\u0003\u0003\u001d\u0001\u0018mY6bO\u0016L!a\f\u0019\u0002\u0011Ut\u0017N^3sg\u0016T!!\f\u0002\n\u0005I\u001a$AB'jeJ|'/\u0003\u00025k\ta!*\u0019<b+:Lg/\u001a:tK*\u0011a\u0007B\u0001\u0004CBL\u0007")
public final class Macros {
    public static Exprs.Expr<JavaUniverse.JavaMirror> currentMirror(Context context) {
        return Macros$.MODULE$.currentMirror(context);
    }
}

