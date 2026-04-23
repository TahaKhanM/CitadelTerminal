/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.PartialFunction$;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Chars$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.VirtualFile;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005Ec\u0001B\u0001\u0003\u0001-\u0011qBQ1uG\"\u001cv.\u001e:dK\u001aKG.\u001a\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003\tI!a\u0004\u0002\u0003\u0015M{WO]2f\r&dW\r\u0003\u0005\u0012\u0001\t\u0015\r\u0011\"\u0001\u0013\u0003\u00111\u0017\u000e\\3\u0016\u0003M\u0001\"\u0001F\f\u000e\u0003UQ!A\u0006\u0004\u0002\u0005%|\u0017B\u0001\r\u0016\u00051\t%m\u001d;sC\u000e$h)\u001b7f\u0011!Q\u0002A!A!\u0002\u0013\u0019\u0012!\u00024jY\u0016\u0004\u0003\u0002\u0003\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\u0002\u0011\r|g\u000e^3oiB\u00022AH\u0010\"\u001b\u0005A\u0011B\u0001\u0011\t\u0005\u0015\t%O]1z!\tq\"%\u0003\u0002$\u0011\t!1\t[1s\u0011\u0015)\u0003\u0001\"\u0001'\u0003\u0019a\u0014N\\5u}Q\u0019q\u0005K\u0015\u0011\u00055\u0001\u0001\"B\t%\u0001\u0004\u0019\u0002\"\u0002\u000f%\u0001\u0004i\u0002\"B\u0013\u0001\t\u0003YCCA\u0014-\u0011\u0015i#\u00061\u0001\u0014\u0003\u0015yf-\u001b7f\u0011\u0015)\u0003\u0001\"\u00010)\r9\u0003'\u000f\u0005\u0006c9\u0002\rAM\u0001\u000bg>,(oY3OC6,\u0007CA\u001a7\u001d\tqB'\u0003\u00026\u0011\u00051\u0001K]3eK\u001aL!a\u000e\u001d\u0003\rM#(/\u001b8h\u0015\t)\u0004\u0002C\u0003;]\u0001\u00071(\u0001\u0002dgB\u0019AhP\u0011\u000f\u0005yi\u0014B\u0001 \t\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Q!\u0003\u0007M+\u0017O\u0003\u0002?\u0011!)Q\u0005\u0001C\u0001\u0007R\u0019q\u0005R#\t\u000bE\u0011\u0005\u0019A\n\t\u000bi\u0012\u0005\u0019A\u001e\t\u000f\u001d\u0003!\u0019!C\u0001\u0011\u000691m\u001c8uK:$X#A\u000f\t\r)\u0003\u0001\u0015!\u0003\u001e\u0003!\u0019wN\u001c;f]R\u0004\u0003b\u0002'\u0001\u0005\u0004%\t!T\u0001\u0007Y\u0016tw\r\u001e5\u0016\u00039\u0003\"AH(\n\u0005AC!aA%oi\"1!\u000b\u0001Q\u0001\n9\u000bq\u0001\\3oORD\u0007\u0005C\u0003U\u0001\u0011\u0005Q*A\u0003ti\u0006\u0014H\u000fC\u0003W\u0001\u0011\u0005q+A\bjgN+GNZ\"p]R\f\u0017N\\3e+\u0005A\u0006C\u0001\u0010Z\u0013\tQ\u0006BA\u0004C_>dW-\u00198\t\u000bq\u0003A\u0011I/\u0002\u0015%$WM\u001c;jM&,'\u000f\u0006\u0002_CB\u0019ad\u0018\u001a\n\u0005\u0001D!AB(qi&|g\u000eC\u0003c7\u0002\u00071-A\u0002q_N\u0004\"!\u00043\n\u0005\u0015\u0014!\u0001\u0003)pg&$\u0018n\u001c8\t\u000b\u001d\u0004A\u0011\u00025\u0002\u0017\rD\u0017M]!u\u0013N,u\n\u0014\u000b\u0003S>$\"\u0001\u00176\t\u000b-4\u0007\u0019\u00017\u0002\u0003A\u0004BAH7\"1&\u0011a\u000e\u0003\u0002\n\rVt7\r^5p]FBQ\u0001\u001d4A\u00029\u000b1!\u001b3y\u0011\u0015\u0011\b\u0001\"\u0001t\u0003-I7\u000fT5oK\n\u0013X-Y6\u0015\u0005a#\b\"\u00029r\u0001\u0004q\u0005\"\u0002<\u0001\t\u00039\u0018aC5t\u000b:$wJ\u001a'j]\u0016$\"\u0001\u0017=\t\u000bA,\b\u0019\u0001(\t\u000bi\u0004A\u0011A>\u0002\u001b%\u001c\u0018\t^#oI>3G*\u001b8f)\tAF\u0010C\u0003qs\u0002\u0007a\nC\u0003\u007f\u0001\u0011\u0005q0\u0001\u000bdC2\u001cW\u000f\\1uK2Kg.Z%oI&\u001cWm\u001d\u000b\u0005\u0003\u0003\t\u0019\u0001E\u0002\u001f?9CQAO?A\u0002uA!\"a\u0002\u0001\u0011\u000b\u0007I\u0011BA\u0005\u0003-a\u0017N\\3J]\u0012L7-Z:\u0016\u0005\u0005\u0005\u0001BCA\u0007\u0001!\u0005\t\u0015)\u0003\u0002\u0002\u0005aA.\u001b8f\u0013:$\u0017nY3tA!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0011\u0001\u00047j]\u0016$vn\u00144gg\u0016$Hc\u0001(\u0002\u0016!9\u0011qCA\b\u0001\u0004q\u0015!B5oI\u0016D\b\u0002CA\u000e\u0001\u0001\u0007I\u0011B'\u0002\u00111\f7\u000f\u001e'j]\u0016D\u0011\"a\b\u0001\u0001\u0004%I!!\t\u0002\u00191\f7\u000f\u001e'j]\u0016|F%Z9\u0015\t\u0005\r\u0012\u0011\u0006\t\u0004=\u0005\u0015\u0012bAA\u0014\u0011\t!QK\\5u\u0011%\tY#!\b\u0002\u0002\u0003\u0007a*A\u0002yIEBq!a\f\u0001A\u0003&a*A\u0005mCN$H*\u001b8fA!9\u00111\u0007\u0001\u0005\u0002\u0005U\u0012\u0001D8gMN,G\u000fV8MS:,Gc\u0001(\u00028!9\u0011\u0011HA\u0019\u0001\u0004q\u0015AB8gMN,G\u000fC\u0004\u0002>\u0001!\t%a\u0010\u0002\r\u0015\fX/\u00197t)\rA\u0016\u0011\t\u0005\t\u0003\u0007\nY\u00041\u0001\u0002F\u0005!A\u000f[1u!\rq\u0012qI\u0005\u0004\u0003\u0013B!aA!os\"9\u0011Q\n\u0001\u0005B\u0005=\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00039\u0003")
public class BatchSourceFile
extends SourceFile {
    private final AbstractFile file;
    private final char[] content;
    private final int length;
    private int[] lineIndices;
    private int lastLine;
    private volatile boolean bitmap$0;

    private int[] lineIndices$lzycompute() {
        BatchSourceFile batchSourceFile = this;
        synchronized (batchSourceFile) {
            if (!this.bitmap$0) {
                this.lineIndices = this.calculateLineIndices(this.content());
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.lineIndices;
        }
    }

    @Override
    public AbstractFile file() {
        return this.file;
    }

    @Override
    public char[] content() {
        return this.content;
    }

    @Override
    public int length() {
        return this.length;
    }

    public int start() {
        return 0;
    }

    @Override
    public boolean isSelfContained() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Option<String> identifier(Position pos) {
        Some<String> some;
        if (pos.isDefined()) {
            SourceFile sourceFile = pos.source();
            if (sourceFile != null && sourceFile.equals(this) && pos.point() != -1) {
                some = new Some<String>(new String((char[])Predef$.MODULE$.charArrayOps((char[])Predef$.MODULE$.charArrayOps(this.content()).drop(pos.point())).takeWhile(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ BatchSourceFile $outer;

                    public final boolean apply(char c) {
                        return this.$outer.scala$reflect$internal$util$BatchSourceFile$$isOK$1(c);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })));
                return some;
            }
        }
        some = super.identifier(pos);
        return some;
    }

    private boolean charAtIsEOL(int idx, Function1<Object, Object> p) {
        return idx < this.length() && this.notCRLF0$1(idx) && BoxesRunTime.unboxToBoolean(p.apply(BoxesRunTime.boxToCharacter(this.content()[idx])));
    }

    @Override
    public boolean isLineBreak(int idx) {
        return this.charAtIsEOL(idx, (Function1<Object, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(char c) {
                return Chars$.MODULE$.isLineBreakChar(c);
            }
        }));
    }

    @Override
    public boolean isEndOfLine(int idx) {
        return Predef$.MODULE$.charArrayOps(this.content()).isDefinedAt(idx) && PartialFunction$.MODULE$.cond(BoxesRunTime.boxToCharacter(this.content()[idx]), new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final <A1, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                boolean bl = 13 == x1 ? true : 10 == x1;
                Boolean bl2 = bl ? BoxesRunTime.boxToBoolean(true) : function1.apply(BoxesRunTime.boxToCharacter(x1));
                return (B1)bl2;
            }

            public final boolean isDefinedAt(char x1) {
                boolean bl = '\r' == x1 ? true : '\n' == x1;
                boolean bl2 = bl;
                return bl2;
            }
        });
    }

    public boolean isAtEndOfLine(int idx) {
        return this.charAtIsEOL(idx, (Function1<Object, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(char x0$1) {
                boolean bl;
                switch (x0$1) {
                    default: {
                        bl = false;
                        break;
                    }
                    case '\n': 
                    case '\r': {
                        bl = true;
                    }
                }
                return bl;
            }
        }));
    }

    public int[] calculateLineIndices(char[] cs) {
        ArrayBuffer buf = new ArrayBuffer();
        buf.$plus$eq(BoxesRunTime.boxToInteger(0));
        Predef$ predef$ = Predef$.MODULE$;
        int n = cs.length;
        Range range2 = Range$.MODULE$.apply(0, n);
        if (!range2.isEmpty()) {
            int n2 = range2.start();
            while (true) {
                int n3;
                Object object = this.isAtEndOfLine(n3 = n2) ? buf.$plus$eq(BoxesRunTime.boxToInteger(n3 + 1)) : BoxedUnit.UNIT;
                if (n2 == range2.lastElement()) break;
                n2 += range2.step();
            }
        }
        buf.$plus$eq(BoxesRunTime.boxToInteger(cs.length));
        return (int[])buf.toArray(ClassTag$.MODULE$.Int());
    }

    private int[] lineIndices() {
        return this.bitmap$0 ? this.lineIndices : this.lineIndices$lzycompute();
    }

    @Override
    public int lineToOffset(int index) {
        return this.lineIndices()[index];
    }

    private int lastLine() {
        return this.lastLine;
    }

    private void lastLine_$eq(int x$1) {
        this.lastLine = x$1;
    }

    @Override
    public int offsetToLine(int offset) {
        int[] lines2 = this.lineIndices();
        this.lastLine_$eq(this.findLine$1(0, lines2.length, this.lastLine(), offset, lines2));
        return this.lastLine();
    }

    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof BatchSourceFile) {
            BatchSourceFile batchSourceFile = (BatchSourceFile)that;
            String string2 = this.file().path();
            String string3 = batchSourceFile.file().path();
            bl = !(string2 != null ? !string2.equals(string3) : string3 != null) && this.start() == batchSourceFile.start();
        } else {
            bl = false;
        }
        return bl;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$.hash(this.file().path()) + this.start();
    }

    public final boolean scala$reflect$internal$util$BatchSourceFile$$isOK$1(char c) {
        return Chars$.MODULE$.isIdentifierPart(c) || Chars$.MODULE$.isOperatorPart(c);
    }

    private final boolean notCRLF0$1(int idx$1) {
        return this.content()[idx$1] != '\r' || !Predef$.MODULE$.charArrayOps(this.content()).isDefinedAt(idx$1 + 1) || this.content()[idx$1 + 1] != '\n';
    }

    private final int findLine$1(int lo, int hi, int mid, int offset$2, int[] lines$1) {
        int n;
        block3: {
            while (true) {
                if (mid < lo || hi < mid) {
                    n = mid;
                    break block3;
                }
                if (offset$2 < lines$1[mid]) {
                    int n2 = mid - 1;
                    mid = (lo + mid - 1) / 2;
                    hi = n2;
                    continue;
                }
                if (offset$2 < lines$1[mid + 1]) break;
                int n3 = mid + 1;
                mid = (mid + 1 + hi) / 2;
                lo = n3;
            }
            n = mid;
        }
        return n;
    }

    /*
     * Unable to fully structure code
     */
    public BatchSourceFile(AbstractFile file, char[] content0) {
        this.file = file;
        super();
        if (content0.length == 0) ** GOTO lbl-1000
        var4_3 = BoxesRunTime.unboxToChar(Predef$.MODULE$.charArrayOps(content0).last());
        var3_4 = Predef$.MODULE$;
        if (RichChar$.MODULE$.isWhitespace$extension(var4_3)) {
            v0 = content0;
        } else lbl-1000:
        // 2 sources

        {
            v0 = (char[])Predef$.MODULE$.charArrayOps(content0).$colon$plus(BoxesRunTime.boxToCharacter('\n'), ClassTag$.MODULE$.Char());
        }
        this.content = v0;
        this.length = this.content().length;
        this.lastLine = 0;
    }

    public BatchSourceFile(AbstractFile _file) {
        this(_file, _file.toCharArray());
    }

    public BatchSourceFile(String sourceName, Seq<Object> cs) {
        this((AbstractFile)new VirtualFile(sourceName), (char[])cs.toArray(ClassTag$.MODULE$.Char()));
    }

    public BatchSourceFile(AbstractFile file, Seq<Object> cs) {
        this(file, (char[])cs.toArray(ClassTag$.MODULE$.Char()));
    }
}

