/*
 * Decompiled with CFR 0.152.
 */
package scala.text;

import java.io.Writer;
import scala.MatchError;
import scala.Some;
import scala.Tuple3;
import scala.collection.LinearSeqOptimized;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.text.DocBreak$;
import scala.text.DocCons;
import scala.text.DocGroup;
import scala.text.DocNest;
import scala.text.DocNil$;
import scala.text.DocText;
import scala.text.Document$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ScalaSignature(bytes="\u0006\u0001)4Q!\u0001\u0002\u0002\u0002\u001d\u0011\u0001\u0002R8dk6,g\u000e\u001e\u0006\u0003\u0007\u0011\tA\u0001^3yi*\tQ!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001A\u0001CA\u0005\u000b\u001b\u0005!\u0011BA\u0006\u0005\u0005\u0019\te.\u001f*fM\")Q\u0002\u0001C\u0001\u001d\u00051A(\u001b8jiz\"\u0012a\u0004\t\u0003!\u0001i\u0011A\u0001\u0005\u0006%\u0001!\taE\u0001\rI\r|Gn\u001c8%G>dwN\u001c\u000b\u0003\u001fQAQ!F\tA\u0002=\t!\u0001\u001b3\t\u000bI\u0001A\u0011A\f\u0015\u0005=A\u0002\"B\u000b\u0017\u0001\u0004I\u0002C\u0001\u000e\u001e\u001d\tI1$\u0003\u0002\u001d\t\u00051\u0001K]3eK\u001aL!AH\u0010\u0003\rM#(/\u001b8h\u0015\taB\u0001C\u0003\"\u0001\u0011\u0005!%\u0001\t%G>dwN\u001c\u0013eSZ$3m\u001c7p]R\u0011qb\t\u0005\u0006+\u0001\u0002\ra\u0004\u0005\u0006C\u0001!\t!\n\u000b\u0003\u001f\u0019BQ!\u0006\u0013A\u0002eAQ\u0001\u000b\u0001\u0005\u0002%\naAZ8s[\u0006$Hc\u0001\u0016.eA\u0011\u0011bK\u0005\u0003Y\u0011\u0011A!\u00168ji\")af\na\u0001_\u0005)q/\u001b3uQB\u0011\u0011\u0002M\u0005\u0003c\u0011\u00111!\u00138u\u0011\u0015\u0019t\u00051\u00015\u0003\u00199(/\u001b;feB\u0011QGO\u0007\u0002m)\u0011q\u0007O\u0001\u0003S>T\u0011!O\u0001\u0005U\u00064\u0018-\u0003\u0002<m\t1qK]5uKJDC\u0001A\u001fA\u0005B\u0011\u0011BP\u0005\u0003\u007f\u0011\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u0005\t\u0015a\u0007+iSN\u00043\r\\1tg\u0002:\u0018\u000e\u001c7!E\u0016\u0004#/Z7pm\u0016$g&I\u0001D\u0003\u0019\u0011d&M\u0019/a\u001d)QI\u0001E\u0001\r\u0006AAi\\2v[\u0016tG\u000f\u0005\u0002\u0011\u000f\u001a)\u0011A\u0001E\u0001\u0011N\u0011q\t\u0003\u0005\u0006\u001b\u001d#\tA\u0013\u000b\u0002\r\")Aj\u0012C\u0001\u001b\u0006)Q-\u001c9usV\taJ\u0004\u0002\u0011\u001f&\u0011\u0001KA\u0001\u0007\t>\u001cg*\u001b7\t\u000bI;E\u0011A*\u0002\u000b\t\u0014X-Y6\u0016\u0003Qs!\u0001E+\n\u0005Y\u0013\u0011\u0001\u0003#pG\n\u0013X-Y6\t\u000b\r9E\u0011\u0001-\u0015\u0005=I\u0006\"\u0002.X\u0001\u0004I\u0012!A:\t\u000bq;E\u0011A/\u0002\u000b\u001d\u0014x.\u001e9\u0015\u0005=q\u0006\"B0\\\u0001\u0004y\u0011!\u00013\t\u000b\u0005<E\u0011\u00012\u0002\t9,7\u000f\u001e\u000b\u0004\u001f\r,\u0007\"\u00023a\u0001\u0004y\u0013!A5\t\u000b}\u0003\u0007\u0019A\b)\t\u001dktMQ\u0011\u0002Q\u0006aB\u000b[5tA=\u0014'.Z2uA]LG\u000e\u001c\u0011cK\u0002\u0012X-\\8wK\u0012t\u0003\u0006\u0002#>O\n\u0003")
public abstract class Document {
    public static Document nest(int n, Document document) {
        return Document$.MODULE$.nest(n, document);
    }

    public static Document group(Document document) {
        return Document$.MODULE$.group(document);
    }

    public static Document text(String string2) {
        return Document$.MODULE$.text(string2);
    }

    public static DocBreak$ break() {
        return Document$.MODULE$.break();
    }

    public static DocNil$ empty() {
        return Document$.MODULE$.empty();
    }

    public Document $colon$colon(Document hd) {
        return new DocCons(hd, this);
    }

    public Document $colon$colon(String hd) {
        return new DocCons(new DocText(hd), this);
    }

    public Document $colon$div$colon(Document hd) {
        DocBreak$ docBreak$ = DocBreak$.MODULE$;
        return this.$colon$colon(docBreak$).$colon$colon(hd);
    }

    public Document $colon$div$colon(String hd) {
        DocBreak$ docBreak$ = DocBreak$.MODULE$;
        return this.$colon$colon(docBreak$).$colon$colon(hd);
    }

    public void format(int width, Writer writer) {
        Tuple3<Integer, Boolean, DocGroup> tuple3 = new Tuple3<Integer, Boolean, DocGroup>(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToBoolean(false), new DocGroup(this));
        this.fmt$1(0, Nil$.MODULE$.$colon$colon(tuple3), width, writer);
    }

    private final boolean fits$1(int w, List state) {
        while (true) {
            $colon$colon $colon$colon;
            boolean bl;
            block13: {
                boolean bl2;
                block11: {
                    block12: {
                        block10: {
                            bl = false;
                            $colon$colon = null;
                            if (w >= 0) break block10;
                            bl2 = false;
                            break block11;
                        }
                        Some<List> some = List$.MODULE$.unapplySeq(state);
                        if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block12;
                        bl2 = true;
                        break block11;
                    }
                    if (state instanceof $colon$colon) {
                        bl = true;
                        $colon$colon = ($colon$colon)state;
                        if ($colon$colon.head() != null && DocNil$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) {
                            state = $colon$colon.tl$1();
                            continue;
                        }
                    }
                    if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocCons) {
                        DocCons docCons = (DocCons)((Tuple3)$colon$colon.head())._3();
                        Tuple3 tuple3 = new Tuple3(((Tuple3)$colon$colon.head())._1(), ((Tuple3)$colon$colon.head())._2(), docCons.hd());
                        Tuple3 tuple32 = new Tuple3(((Tuple3)$colon$colon.head())._1(), ((Tuple3)$colon$colon.head())._2(), docCons.tl());
                        state = $colon$colon.tl$1().$colon$colon(tuple32).$colon$colon(tuple3);
                        continue;
                    }
                    if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocText) {
                        DocText docText = (DocText)((Tuple3)$colon$colon.head())._3();
                        state = $colon$colon.tl$1();
                        w -= docText.txt().length();
                        continue;
                    }
                    if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocNest) {
                        DocNest docNest = (DocNest)((Tuple3)$colon$colon.head())._3();
                        Tuple3 tuple3 = new Tuple3(BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(((Tuple3)$colon$colon.head())._1()) + docNest.indent()), ((Tuple3)$colon$colon.head())._2(), docNest.doc());
                        state = $colon$colon.tl$1().$colon$colon(tuple3);
                        continue;
                    }
                    if (bl && $colon$colon.head() != null && !BoxesRunTime.unboxToBoolean(((Tuple3)$colon$colon.head())._2()) && DocBreak$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) {
                        state = $colon$colon.tl$1();
                        --w;
                        continue;
                    }
                    if (!bl || $colon$colon.head() == null || !BoxesRunTime.unboxToBoolean(((Tuple3)$colon$colon.head())._2()) || !DocBreak$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) break block13;
                    bl2 = true;
                }
                return bl2;
            }
            if (!bl || $colon$colon.head() == null || !(((Tuple3)$colon$colon.head())._3() instanceof DocGroup)) break;
            DocGroup docGroup = (DocGroup)((Tuple3)$colon$colon.head())._3();
            Tuple3 tuple3 = new Tuple3(((Tuple3)$colon$colon.head())._1(), BoxesRunTime.boxToBoolean(false), docGroup.doc());
            state = $colon$colon.tl$1().$colon$colon(tuple3);
        }
        throw new MatchError(state);
    }

    private final void spaces$1(int n, Writer writer$1) {
        int rem;
        for (rem = n; rem >= 16; rem -= 16) {
            writer$1.write("                ");
        }
        if (rem >= 8) {
            writer$1.write("        ");
            rem -= 8;
        }
        if (rem >= 4) {
            writer$1.write("    ");
            rem -= 4;
        }
        if (rem >= 2) {
            writer$1.write("  ");
            rem -= 2;
        }
        if (rem == 1) {
            writer$1.write(" ");
        }
    }

    private final void fmt$1(int k, List state, int width$1, Writer writer$1) {
        while (true) {
            boolean bl = false;
            $colon$colon $colon$colon = null;
            Some<List> some = List$.MODULE$.unapplySeq(state);
            if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0) {
                break;
            }
            if (state instanceof $colon$colon) {
                bl = true;
                $colon$colon = ($colon$colon)state;
                if ($colon$colon.head() != null && DocNil$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) {
                    state = $colon$colon.tl$1();
                    continue;
                }
            }
            if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocCons) {
                DocCons docCons = (DocCons)((Tuple3)$colon$colon.head())._3();
                Tuple3 tuple3 = new Tuple3(((Tuple3)$colon$colon.head())._1(), ((Tuple3)$colon$colon.head())._2(), docCons.hd());
                Tuple3 tuple32 = new Tuple3(((Tuple3)$colon$colon.head())._1(), ((Tuple3)$colon$colon.head())._2(), docCons.tl());
                state = $colon$colon.tl$1().$colon$colon(tuple32).$colon$colon(tuple3);
                continue;
            }
            if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocText) {
                DocText docText = (DocText)((Tuple3)$colon$colon.head())._3();
                writer$1.write(docText.txt());
                state = $colon$colon.tl$1();
                k += docText.txt().length();
                continue;
            }
            if (bl && $colon$colon.head() != null && ((Tuple3)$colon$colon.head())._3() instanceof DocNest) {
                DocNest docNest = (DocNest)((Tuple3)$colon$colon.head())._3();
                Tuple3 tuple3 = new Tuple3(BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(((Tuple3)$colon$colon.head())._1()) + docNest.indent()), ((Tuple3)$colon$colon.head())._2(), docNest.doc());
                state = $colon$colon.tl$1().$colon$colon(tuple3);
                continue;
            }
            if (bl && $colon$colon.head() != null && BoxesRunTime.unboxToBoolean(((Tuple3)$colon$colon.head())._2()) && DocBreak$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) {
                writer$1.write("\n");
                this.spaces$1(BoxesRunTime.unboxToInt(((Tuple3)$colon$colon.head())._1()), writer$1);
                state = $colon$colon.tl$1();
                k = BoxesRunTime.unboxToInt(((Tuple3)$colon$colon.head())._1());
                continue;
            }
            if (bl && $colon$colon.head() != null && !BoxesRunTime.unboxToBoolean(((Tuple3)$colon$colon.head())._2()) && DocBreak$.MODULE$.equals(((Tuple3)$colon$colon.head())._3())) {
                writer$1.write(" ");
                state = $colon$colon.tl$1();
                ++k;
                continue;
            }
            if (!bl || $colon$colon.head() == null || !(((Tuple3)$colon$colon.head())._3() instanceof DocGroup)) break;
            DocGroup docGroup = (DocGroup)((Tuple3)$colon$colon.head())._3();
            Tuple3 tuple3 = new Tuple3(((Tuple3)$colon$colon.head())._1(), BoxesRunTime.boxToBoolean(false), docGroup.doc());
            boolean fitsFlat = this.fits$1(width$1 - k, $colon$colon.tl$1().$colon$colon(tuple3));
            Tuple3 tuple33 = new Tuple3(((Tuple3)$colon$colon.head())._1(), BoxesRunTime.boxToBoolean(!fitsFlat), docGroup.doc());
            state = $colon$colon.tl$1().$colon$colon(tuple33);
        }
    }
}

