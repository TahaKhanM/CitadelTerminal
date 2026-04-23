/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.TreesStats$;
import scala.reflect.internal.util.Statistics;

@ScalaSignature(bytes="\u0006\u0001u:Q!\u0001\u0002\t\u0002%\t!\u0002\u0016:fKN\u001cF/\u0019;t\u0015\t\u0019A!\u0001\u0005j]R,'O\\1m\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u000b\u00175\t!AB\u0003\r\u0005!\u0005QB\u0001\u0006Ue\u0016,7o\u0015;biN\u001c\"a\u0003\b\u0011\u0005=\u0001R\"\u0001\u0004\n\u0005E1!AB!osJ+g\rC\u0003\u0014\u0017\u0011\u0005A#\u0001\u0004=S:LGO\u0010\u000b\u0002\u0013!9ac\u0003b\u0001\n\u00039\u0012A\u00038pI\u0016\u0014\u0015\u0010V=qKV\t\u0001\u0004\u0005\u0003\u001a?\tBdB\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\u0003\u0003\u0011)H/\u001b7\n\u0005yY\u0012AC*uCRL7\u000f^5dg&\u0011\u0001%\t\u0002\t#V\fg\u000e^'ba*\u0011ad\u0007\u0019\u0003G5\u00022\u0001J\u0015,\u001b\u0005)#B\u0001\u0014(\u0003\u0011a\u0017M\\4\u000b\u0003!\nAA[1wC&\u0011!&\n\u0002\u0006\u00072\f7o\u001d\t\u0003Y5b\u0001\u0001B\u0005/\u0001\u0005\u0005\t\u0011!B\u0001c\t\u0019q\fJ\u0019\n\u0005A\n\u0013A\u00038fo\nK8\t\\1tgF\u0011!'\u000e\t\u0003\u001fMJ!\u0001\u000e\u0004\u0003\u000f9{G\u000f[5oOB\u0011qBN\u0005\u0003o\u0019\u00111!\u00118z!\tI\u0012(\u0003\u0002;C\t91i\\;oi\u0016\u0014\bB\u0002\u001f\fA\u0003%\u0001$A\u0006o_\u0012,')\u001f+za\u0016\u0004\u0003")
public final class TreesStats {
    public static Statistics.QuantMap<Class<?>, Statistics.Counter> nodeByType() {
        return TreesStats$.MODULE$.nodeByType();
    }
}

