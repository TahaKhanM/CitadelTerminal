/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Position$;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;

@ScalaSignature(bytes="\u0006\u0001U4Q!\u0001\u0002\u0002\u0002-\u0011!bU8ve\u000e,g)\u001b7f\u0015\t\u0019A!\u0001\u0003vi&d'BA\u0003\u0007\u0003!Ig\u000e^3s]\u0006d'BA\u0004\t\u0003\u001d\u0011XM\u001a7fGRT\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e\u001d5\t\u0001\"\u0003\u0002\u0010\u0011\t1\u0011I\\=SK\u001aDQ!\u0005\u0001\u0005\u0002I\ta\u0001P5oSRtD#A\n\u0011\u0005Q\u0001Q\"\u0001\u0002\t\u000bY\u0001a\u0011A\f\u0002\u000f\r|g\u000e^3oiV\t\u0001\u0004E\u0002\u000e3mI!A\u0007\u0005\u0003\u000b\u0005\u0013(/Y=\u0011\u00055a\u0012BA\u000f\t\u0005\u0011\u0019\u0005.\u0019:\t\u000b}\u0001a\u0011\u0001\u0011\u0002\t\u0019LG.Z\u000b\u0002CA\u0011!%J\u0007\u0002G)\u0011AEB\u0001\u0003S>L!AJ\u0012\u0003\u0019\u0005\u00137\u000f\u001e:bGR4\u0015\u000e\\3\t\u000b!\u0002a\u0011A\u0015\u0002\u0017%\u001cH*\u001b8f\u0005J,\u0017m\u001b\u000b\u0003U5\u0002\"!D\u0016\n\u00051B!a\u0002\"p_2,\u0017M\u001c\u0005\u0006]\u001d\u0002\raL\u0001\u0004S\u0012D\bCA\u00071\u0013\t\t\u0004BA\u0002J]RDQa\r\u0001\u0007\u0002Q\n1\"[:F]\u0012|e\rT5oKR\u0011!&\u000e\u0005\u0006]I\u0002\ra\f\u0005\u0006o\u00011\t\u0001O\u0001\u0010SN\u001cV\r\u001c4D_:$\u0018-\u001b8fIV\t!\u0006C\u0003;\u0001\u0019\u00051(\u0001\u0004mK:<G\u000f[\u000b\u0002_!)Q\b\u0001C\u0001}\u0005A\u0001o\\:ji&|g\u000e\u0006\u0002@\u0005B\u0011A\u0003Q\u0005\u0003\u0003\n\u0011\u0001\u0002U8tSRLwN\u001c\u0005\u0006\u0007r\u0002\raL\u0001\u0007_\u001a47/\u001a;\t\u000b\u0015\u0003a\u0011\u0001$\u0002\u0019=4gm]3u)>d\u0015N\\3\u0015\u0005=:\u0005\"B\"E\u0001\u0004y\u0003\"B%\u0001\r\u0003Q\u0015\u0001\u00047j]\u0016$vn\u00144gg\u0016$HCA\u0018L\u0011\u0015a\u0005\n1\u00010\u0003\u0015Ig\u000eZ3y\u0011\u0015q\u0005\u0001\"\u0001P\u0003a\u0001xn]5uS>t\u0017J\\+mi&l\u0017\r^3T_V\u00148-\u001a\u000b\u0003\u007fACQ!P'A\u0002}BQA\u0015\u0001\u0005BM\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002)B\u0011Q\u000b\u0017\b\u0003\u001bYK!a\u0016\u0005\u0002\rA\u0013X\rZ3g\u0013\tI&L\u0001\u0004TiJLgn\u001a\u0006\u0003/\"AQ\u0001\u0018\u0001\u0005\u0002u\u000bA\u0001]1uQV\tA\u000bC\u0003`\u0001\u0011\u0005\u0001-\u0001\u0007mS:,Gk\\*ue&tw\r\u0006\u0002UC\")AJ\u0018a\u0001_!)1\r\u0001C\u0003I\u0006q1o[5q/\"LG/Z:qC\u000e,GCA\u0018f\u0011\u0015\u0019%\r1\u00010Q\t\u0011w\r\u0005\u0002iW6\t\u0011N\u0003\u0002k\u0011\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051L'a\u0002;bS2\u0014Xm\u0019\u0005\u0006]\u0002!\ta\\\u0001\u000bS\u0012,g\u000e^5gS\u0016\u0014HC\u00019t!\ri\u0011\u000fV\u0005\u0003e\"\u0011aa\u00149uS>t\u0007\"\u0002;n\u0001\u0004y\u0014a\u00019pg\u0002")
public abstract class SourceFile {
    public abstract char[] content();

    public abstract AbstractFile file();

    public abstract boolean isLineBreak(int var1);

    public abstract boolean isEndOfLine(int var1);

    public abstract boolean isSelfContained();

    public abstract int length();

    public Position position(int offset) {
        boolean bl = offset < this.length();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            AbstractFile abstractFile = this.file();
            Predef$ predef$2 = Predef$.MODULE$;
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(abstractFile, ": ")).append(BoxesRunTime.boxToInteger(offset)).append((Object)" >= ").append(BoxesRunTime.boxToInteger(this.length())).toString()).toString());
        }
        return Position$.MODULE$.offset(this, offset);
    }

    public abstract int offsetToLine(int var1);

    public abstract int lineToOffset(int var1);

    public Position positionInUltimateSource(Position position) {
        return position;
    }

    public String toString() {
        return this.file().name();
    }

    public String path() {
        return this.file().path();
    }

    public String lineToString(int index) {
        int start;
        int end;
        for (end = start = this.lineToOffset(index); end < this.length() && !this.isEndOfLine(end); ++end) {
        }
        return new String(this.content(), start, end - start);
    }

    public final int skipWhitespace(int offset) {
        while (true) {
            char c = this.content()[offset];
            Predef$ predef$ = Predef$.MODULE$;
            if (!RichChar$.MODULE$.isWhitespace$extension(c)) break;
            ++offset;
        }
        return offset;
    }

    public Option<String> identifier(Position pos) {
        return None$.MODULE$;
    }
}

