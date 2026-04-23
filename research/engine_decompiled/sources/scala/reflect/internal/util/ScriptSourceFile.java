/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.BatchSourceFile;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.ScriptSourceFile$;
import scala.reflect.io.AbstractFile;

@ScalaSignature(bytes="\u0006\u0001Y;Q!\u0001\u0002\t\u0002-\t\u0001cU2sSB$8k\\;sG\u00164\u0015\u000e\\3\u000b\u0005\r!\u0011\u0001B;uS2T!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u000fI,g\r\\3di*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u00051iQ\"\u0001\u0002\u0007\u000b9\u0011\u0001\u0012A\b\u0003!M\u001b'/\u001b9u'>,(oY3GS2,7CA\u0007\u0011!\t\t\"#D\u0001\t\u0013\t\u0019\u0002B\u0001\u0004B]f\u0014VM\u001a\u0005\u0006+5!\tAF\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-AQ\u0001G\u0007\u0005\u0002e\tA\u0002[3bI\u0016\u0014H*\u001a8hi\"$\"AG\u000f\u0011\u0005EY\u0012B\u0001\u000f\t\u0005\rIe\u000e\u001e\u0005\u0006=]\u0001\raH\u0001\u0003GN\u00042!\u0005\u0011#\u0013\t\t\u0003BA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0012G%\u0011A\u0005\u0003\u0002\u0005\u0007\"\f'\u000fC\u0003'\u001b\u0011\u0005q%A\u0003baBd\u0017\u0010F\u0002)\u0015J\u0003\"\u0001D\u0015\u0007\t9\u0011\u0001AK\n\u0003S-\u0002\"\u0001\u0004\u0017\n\u00055\u0012!a\u0004\"bi\u000eD7k\\;sG\u00164\u0015\u000e\\3\t\u0011=J#\u0011!Q\u0001\n-\n!\"\u001e8eKJd\u00170\u001b8h\u0011!\t\u0014F!A!\u0002\u0013y\u0012aB2p]R,g\u000e\u001e\u0005\tg%\u0012)\u0019!C!i\u0005)1\u000f^1siV\t!\u0004\u0003\u00057S\t\u0005\t\u0015!\u0003\u001b\u0003\u0019\u0019H/\u0019:uA!)Q#\u000bC\u0001qQ!\u0001&\u000f\u001e<\u0011\u0015ys\u00071\u0001,\u0011\u0015\tt\u00071\u0001 \u0011\u0015\u0019t\u00071\u0001\u001b\u0011\u0015i\u0014\u0006\"\u0011?\u0003=I7oU3mM\u000e{g\u000e^1j]\u0016$W#A \u0011\u0005E\u0001\u0015BA!\t\u0005\u001d\u0011un\u001c7fC:DQaQ\u0015\u0005B\u0011\u000b\u0001\u0004]8tSRLwN\\%o+2$\u0018.\\1uKN{WO]2f)\t)\u0005\n\u0005\u0002\r\r&\u0011qI\u0001\u0002\t!>\u001c\u0018\u000e^5p]\")\u0011J\u0011a\u0001\u000b\u0006\u0019\u0001o\\:\t\u000b-+\u0003\u0019\u0001'\u0002\t\u0019LG.\u001a\t\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001f\u001a\t!![8\n\u0005Es%\u0001D!cgR\u0014\u0018m\u0019;GS2,\u0007\"B\u0019&\u0001\u0004y\u0002\"\u0002\u0014\u000e\t\u0003!FC\u0001\u0015V\u0011\u0015y3\u000b1\u0001,\u0001")
public class ScriptSourceFile
extends BatchSourceFile {
    private final BatchSourceFile underlying;
    private final int start;

    public static ScriptSourceFile apply(BatchSourceFile batchSourceFile) {
        return ScriptSourceFile$.MODULE$.apply(batchSourceFile);
    }

    public static ScriptSourceFile apply(AbstractFile abstractFile, char[] cArray) {
        return ScriptSourceFile$.MODULE$.apply(abstractFile, cArray);
    }

    public static int headerLength(char[] cArray) {
        return ScriptSourceFile$.MODULE$.headerLength(cArray);
    }

    @Override
    public int start() {
        return this.start;
    }

    @Override
    public boolean isSelfContained() {
        return false;
    }

    @Override
    public Position positionInUltimateSource(Position pos) {
        return pos.isDefined() ? pos.withSource(this.underlying).withShift(this.start()) : super.positionInUltimateSource(pos);
    }

    public ScriptSourceFile(BatchSourceFile underlying, char[] content, int start) {
        this.underlying = underlying;
        this.start = start;
        super(underlying.file(), content);
    }
}

