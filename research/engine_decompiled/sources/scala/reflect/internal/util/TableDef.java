/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.PartialFunction$class;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.GenIterable;
import scala.collection.GenIterable$class;
import scala.collection.GenSeq;
import scala.collection.GenSeq$class;
import scala.collection.GenSeqLike$class;
import scala.collection.GenTraversable;
import scala.collection.GenTraversable$class;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterable$class;
import scala.collection.IterableLike$class;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Parallelizable$class;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.Seq$class;
import scala.collection.SeqLike;
import scala.collection.SeqLike$class;
import scala.collection.SeqView;
import scala.collection.Traversable;
import scala.collection.Traversable$class;
import scala.collection.TraversableLike;
import scala.collection.TraversableLike$class;
import scala.collection.TraversableOnce;
import scala.collection.TraversableOnce$class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.generic.GenericTraversableTemplate$class;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParSeq;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.TableDef$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;
import scala.runtime.Tuple2Zipped$;
import scala.runtime.Tuple2Zipped$Ops$;

@ScalaSignature(bytes="\u0006\u0001\teh\u0001B\u0001\u0003\u0001-\u0011\u0001\u0002V1cY\u0016$UM\u001a\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\u000b\u0004\u0019\u0005}7C\u0001\u0001\u000e!\tqq\"D\u0001\t\u0013\t\u0001\u0002B\u0001\u0004B]f\u0014VM\u001a\u0005\t%\u0001\u0011\t\u0011)A\u0005'\u0005)qlY8mgB\u0019a\u0002\u0006\f\n\u0005UA!A\u0003\u001fsKB,\u0017\r^3e}A!q#IAo\u001d\tA\u0012$D\u0001\u0003\u000f\u0015Q\"\u0001#\u0001\u001c\u0003!!\u0016M\u00197f\t\u00164\u0007C\u0001\r\u001d\r\u0015\t!\u0001#\u0001\u001e'\taR\u0002C\u0003 9\u0011\u0005\u0001%\u0001\u0004=S:LGO\u0010\u000b\u00027\u0019!!\u0005\b!$\u0005\u0019\u0019u\u000e\\;n]V\u0011AeP\n\u0005C5)\u0003\u0006\u0005\u0002\u000fM%\u0011q\u0005\u0003\u0002\b!J|G-^2u!\tq\u0011&\u0003\u0002+\u0011\ta1+\u001a:jC2L'0\u00192mK\"AA&\tBK\u0002\u0013\u0005Q&\u0001\u0003oC6,W#\u0001\u0018\u0011\u0005=\u0012dB\u0001\b1\u0013\t\t\u0004\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003gQ\u0012aa\u0015;sS:<'BA\u0019\t\u0011!1\u0014E!E!\u0002\u0013q\u0013!\u00028b[\u0016\u0004\u0003\u0002\u0003\u001d\"\u0005+\u0007I\u0011A\u001d\u0002\u0003\u0019,\u0012A\u000f\t\u0005\u001dmjT)\u0003\u0002=\u0011\tIa)\u001e8di&|g.\r\t\u0003}}b\u0001\u0001\u0002\u0004AC!\u0015\r!\u0011\u0002\u0002)F\u0011!)\u0012\t\u0003\u001d\rK!\u0001\u0012\u0005\u0003\u000f9{G\u000f[5oOB\u0011aBR\u0005\u0003\u000f\"\u00111!\u00118z\u0011!I\u0015E!E!\u0002\u0013Q\u0014A\u00014!\u0011!Y\u0015E!f\u0001\n\u0003a\u0015\u0001\u00027fMR,\u0012!\u0014\t\u0003\u001d9K!a\u0014\u0005\u0003\u000f\t{w\u000e\\3b]\"A\u0011+\tB\tB\u0003%Q*A\u0003mK\u001a$\b\u0005C\u0003 C\u0011\u00051\u000b\u0006\u0003U-^C\u0006cA+\"{5\tA\u0004C\u0003-%\u0002\u0007a\u0006C\u00039%\u0002\u0007!\bC\u0003L%\u0002\u0007Q\nC\u0003[C\u0011\u00051,\u0001\u0005nCb<\u0016\u000e\u001a;i)\tav\f\u0005\u0002\u000f;&\u0011a\f\u0003\u0002\u0004\u0013:$\b\"\u00021Z\u0001\u0004\t\u0017!B3mK6\u001c\bc\u00012f{9\u0011abY\u0005\u0003I\"\tq\u0001]1dW\u0006<W-\u0003\u0002gO\n\u00191+Z9\u000b\u0005\u0011D\u0001\"B5\"\t\u0003Q\u0017A\u00034pe6\fGo\u00159fGR\u0011af\u001b\u0005\u0006A\"\u0004\r!\u0019\u0005\u0006[\u0006\"\tE\\\u0001\ti>\u001cFO]5oOR\tq\u000e\u0005\u0002qk6\t\u0011O\u0003\u0002sg\u0006!A.\u00198h\u0015\u0005!\u0018\u0001\u00026bm\u0006L!aM9\t\u000f]\f\u0013\u0011!C\u0001q\u0006!1m\u001c9z+\tIH\u0010F\u0003{{z\f\t\u0001E\u0002VCm\u0004\"A\u0010?\u0005\u000b\u00013(\u0019A!\t\u000f12\b\u0013!a\u0001]!9\u0001H\u001eI\u0001\u0002\u0004y\b\u0003\u0002\b<w\u0016Cqa\u0013<\u0011\u0002\u0003\u0007Q\nC\u0005\u0002\u0006\u0005\n\n\u0011\"\u0001\u0002\b\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BA\u0005\u0003?)\"!a\u0003+\u00079\nia\u000b\u0002\u0002\u0010A!\u0011\u0011CA\u000e\u001b\t\t\u0019B\u0003\u0003\u0002\u0016\u0005]\u0011!C;oG\",7m[3e\u0015\r\tI\u0002C\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u000f\u0003'\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019\u0001\u00151\u0001b\u0001\u0003\"I\u00111E\u0011\u0012\u0002\u0013\u0005\u0011QE\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\t9#a\u000b\u0016\u0005\u0005%\"f\u0001\u001e\u0002\u000e\u00111\u0001)!\tC\u0002\u0005C\u0011\"a\f\"#\u0003%\t!!\r\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111GA\u001c+\t\t)DK\u0002N\u0003\u001b!a\u0001QA\u0017\u0005\u0004\t\u0005\"CA\u001eC\u0005\u0005I\u0011IA\u001f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000eC\u0005\u0002B\u0005\n\t\u0011\"\u0001\u0002D\u0005a\u0001O]8ek\u000e$\u0018I]5usV\tA\fC\u0005\u0002H\u0005\n\t\u0011\"\u0001\u0002J\u0005q\u0001O]8ek\u000e$X\t\\3nK:$HcA#\u0002L!I\u0011QJA#\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\n\u0004\"CA)C\u0005\u0005I\u0011IA*\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA+!\u0015\t9&!\u0018F\u001b\t\tIFC\u0002\u0002\\!\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty&!\u0017\u0003\u0011%#XM]1u_JD\u0011\"a\u0019\"\u0003\u0003%\t!!\u001a\u0002\u0011\r\fg.R9vC2$2!TA4\u0011%\ti%!\u0019\u0002\u0002\u0003\u0007Q\tC\u0005\u0002l\u0005\n\t\u0011\"\u0011\u0002n\u0005A\u0001.Y:i\u0007>$W\rF\u0001]\u0011%\t\t(IA\u0001\n\u0003\n\u0019(\u0001\u0004fcV\fGn\u001d\u000b\u0004\u001b\u0006U\u0004\"CA'\u0003_\n\t\u00111\u0001F\u000f%\tI\bHA\u0001\u0012\u0003\tY(\u0001\u0004D_2,XN\u001c\t\u0004+\u0006ud\u0001\u0003\u0012\u001d\u0003\u0003E\t!a \u0014\t\u0005uT\u0002\u000b\u0005\b?\u0005uD\u0011AAB)\t\tY\b\u0003\u0005n\u0003{\n\t\u0011\"\u0012o\u0011)\tI)! \u0002\u0002\u0013\u0005\u00151R\u0001\u0006CB\u0004H._\u000b\u0005\u0003\u001b\u000b\u0019\n\u0006\u0005\u0002\u0010\u0006U\u0015qSAN!\u0011)\u0016%!%\u0011\u0007y\n\u0019\n\u0002\u0004A\u0003\u000f\u0013\r!\u0011\u0005\u0007Y\u0005\u001d\u0005\u0019\u0001\u0018\t\u000fa\n9\t1\u0001\u0002\u001aB)abOAI\u000b\"11*a\"A\u00025C!\"a(\u0002~\u0005\u0005I\u0011QAQ\u0003\u001d)h.\u00199qYf,B!a)\u00026R!\u0011QUA\\!\u0015q\u0011qUAV\u0013\r\tI\u000b\u0003\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f9\tiKLAY\u001b&\u0019\u0011q\u0016\u0005\u0003\rQ+\b\u000f\\34!\u0015q1(a-F!\rq\u0014Q\u0017\u0003\u0007\u0001\u0006u%\u0019A!\t\u0015\u0005e\u0016QTA\u0001\u0002\u0004\tY,A\u0002yIA\u0002B!V\u0011\u00024\"Q\u0011qXA?\u0003\u0003%I!!1\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003\u0007\u00042\u0001]Ac\u0013\r\t9-\u001d\u0002\u0007\u001f\nTWm\u0019;\t\u000f\u0005%E\u0004\"\u0001\u0002LV!\u0011QZAj)\u0011\ty-!6\u0011\ta\u0001\u0011\u0011\u001b\t\u0004}\u0005MGA\u0002!\u0002J\n\u0007\u0011\t\u0003\u0005\u0002X\u0006%\u0007\u0019AAm\u0003\u0011\u0019w\u000e\\:\u0011\t9!\u00121\u001c\t\u0005+\u0006\n\t\u000eE\u0002?\u0003?$Q\u0001\u0011\u0001C\u0002\u0005Caa\b\u0001\u0005\u0002\u0005\rH\u0003BAs\u0003O\u0004B\u0001\u0007\u0001\u0002^\"1!#!9A\u0002MAq!a;\u0001\t\u0003\ti/\u0001\u0004%i&dG-\u001a\u000b\u0005\u0003K\fy\u000fC\u0004\u0002r\u0006%\b\u0019\u0001\f\u0002\t9,\u0007\u0010\u001e\u0005\n\u0003/\u0004\u0001\u0019!C\u0005\u0003k,\"!a>\u0011\t\t\fIPF\u0005\u0004\u0003w<'\u0001\u0002'jgRD\u0011\"a@\u0001\u0001\u0004%IA!\u0001\u0002\u0011\r|Gn]0%KF$BAa\u0001\u0003\nA\u0019aB!\u0002\n\u0007\t\u001d\u0001B\u0001\u0003V]&$\bBCA'\u0003{\f\t\u00111\u0001\u0002x\"A!Q\u0002\u0001!B\u0013\t90A\u0003d_2\u001c\b\u0005C\u0004\u0003\u0012\u0001!\tAa\u0005\u0002\u0015\u0011,g-Y;miN+\u0007\u000fF\u0002p\u0005+AqAa\u0006\u0003\u0010\u0001\u0007A,A\u0003j]\u0012,\u0007\u0010C\u0004\u0003\u001c\u0001!\tA!\b\u0002\u0011M,\u0007/\u00114uKJ$2A\fB\u0010\u0011\u001d\u0011\tC!\u0007A\u0002q\u000b\u0011!\u001b\u0005\b\u0005K\u0001A\u0011\u0001B\u0014\u0003%\u0019X\r],jIRD7/\u0006\u0002\u0003*A)!1\u0006B\u001996\u0011!Q\u0006\u0006\u0005\u0005_\tI&A\u0005j[6,H/\u00192mK&!!1\u0007B\u0017\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\b\u0005o\u0001A\u0011\u0001B\u001d\u0003!\u0019w\u000e\u001c(b[\u0016\u001cXC\u0001B\u001e!\u0015\u0011YC!\u0010/\u0013\u0011\tYP!\f\t\u000f\t\u0005\u0003\u0001\"\u0001\u0003D\u0005a1m\u001c7Gk:\u001cG/[8ogV\u0011!Q\t\t\u0007\u0005W\u0011iDa\u0012\u0011\u000b9Y\u0014Q\\#\t\u000f\t-\u0003\u0001\"\u0001\u0003N\u0005A1m\u001c7BaBd\u0017\u0010\u0006\u0003\u0003P\tE\u0003#\u0002B\u0016\u0005{)\u0005\u0002\u0003B*\u0005\u0013\u0002\r!!8\u0002\u0005\u0015d\u0007b\u0002B,\u0001\u0011\u0005!\u0011L\u0001\be\u0016$H\u000b[5t)\u0011\u0011YF!\u0018\u000e\u0003\u0001A\u0011Ba\u0018\u0003V\u0011\u0005\rA!\u0019\u0002\t\t|G-\u001f\t\u0006\u001d\t\r$1A\u0005\u0004\u0005KB!\u0001\u0003\u001fcs:\fW.\u001a \u0007\r\t%\u0004\u0001\u0001B6\u0005\u0015!\u0016M\u00197f'\u0015\u00119'\u0004B7!\u0011\u0011W-!8\t\u0017\tE$q\rBC\u0002\u0013\u0005!1O\u0001\u0005e><8/\u0006\u0002\u0003n!Y!q\u000fB4\u0005\u0003\u0005\u000b\u0011\u0002B7\u0003\u0015\u0011xn^:!\u0011\u001dy\"q\rC\u0001\u0005w\"BA! \u0003\u0000A!!1\fB4\u0011!\u0011\tH!\u001fA\u0002\t5\u0004\u0002\u0003BB\u0005O\"\tA!\"\u0002\u0011%$XM]1u_J,\"Aa\"\u0011\r\u0005]\u0013QLAo\u0011!\tIIa\u001a\u0005\u0002\t-E\u0003BAo\u0005\u001bCqAa\u0006\u0003\n\u0002\u0007A\f\u0003\u0005\u0003\u0012\n\u001dD\u0011AA\"\u0003\u0019aWM\\4uQ\"A!Q\u0013B4\t\u0003\u00119*A\u0006nCb\u001cu\u000e\\,jIRDGc\u0001/\u0003\u001a\"9!1\u0014BJ\u0001\u00041\u0012aA2pY\"A!q\u0014B4\t\u0003\u0011I$A\u0003ta\u0016\u001c7\u000f\u0003\u0006\u0003$\n\u001d$\u0019!C\u0001\u0005K\u000b\u0011bY8m/&$G\u000f[:\u0016\u0005\t\u001d\u0006#\u0002B\u0016\u0005{a\u0006\"\u0003BV\u0005O\u0002\u000b\u0011\u0002BT\u0003)\u0019w\u000e\\,jIRD7\u000f\t\u0005\n\u0005_\u00139G1A\u0005\u00025\n\u0011B]8x\r>\u0014X.\u0019;\t\u0011\tM&q\rQ\u0001\n9\n!B]8x\r>\u0014X.\u0019;!\u0011%\u00119La\u001aC\u0002\u0013\u0005Q&\u0001\u0006iK\u0006$gi\u001c:nCRD\u0001Ba/\u0003h\u0001\u0006IAL\u0001\fQ\u0016\fGMR8s[\u0006$\b\u0005\u0003\u0006\u0003@\n\u001d$\u0019!C\u0001\u0005\u0003\f\u0001\"\u0019:h\u0019&\u001cHo]\u000b\u0003\u0005\u0007\u0004b!a\u0016\u0003F\n=\u0013b\u00014\u0002Z!I!\u0011\u001aB4A\u0003%!1Y\u0001\nCJ<G*[:ug\u0002B!B!4\u0003h\t\u0007I\u0011\u0001B\u001d\u0003\u001dAW-\u00193feND\u0011B!5\u0003h\u0001\u0006IAa\u000f\u0002\u0011!,\u0017\rZ3sg\u0002B\u0001B!6\u0003h\u0011\u0005!q[\u0001\u000f[.4uN]7biN#(/\u001b8h)\rq#\u0011\u001c\u0005\t\u00057\u0014\u0019\u000e1\u0001\u0003^\u0006!1/\u001a9g!\u0011q1\b\u0018\u0018\t\u0011\t\u0005(q\rC\u0001\u0005G\fa\u0002^8G_Jl\u0017\r\u001e;fIN+\u0017/\u0006\u0002\u0003fB)\u0011q\u000bBc]!A!\u0011\u001eB4\t\u0003\u0011I$\u0001\u0005bY2$vnU3r\u0011\u001di'q\rC!\u0005[$\u0012A\f\u0005\b\u0005c\u0004A\u0011\u0001Bz\u0003\u0015!\u0018M\u00197f)\u0011\u0011iH!>\t\u0011\tE$q\u001ea\u0001\u0005[Ba!\u001c\u0001\u0005B\t5\b")
public class TableDef<T> {
    private List<Column<T>> scala$reflect$internal$util$TableDef$$cols;

    public static <T> TableDef<T> apply(Seq<Column<T>> seq) {
        return TableDef$.MODULE$.apply(seq);
    }

    public TableDef<T> $tilde(Column<T> next2) {
        return this.retThis((Function0<BoxedUnit>)((Object)new Serializable(this, next2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TableDef $outer;
            private final Column next$1;

            public final void apply() {
                this.apply$mcV$sp();
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$internal$util$TableDef$$cols_$eq(this.$outer.scala$reflect$internal$util$TableDef$$cols().$colon$plus(this.next$1, List$.MODULE$.canBuildFrom()));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.next$1 = next$1;
            }
        }));
    }

    public List<Column<T>> scala$reflect$internal$util$TableDef$$cols() {
        return this.scala$reflect$internal$util$TableDef$$cols;
    }

    public void scala$reflect$internal$util$TableDef$$cols_$eq(List<Column<T>> x$1) {
        this.scala$reflect$internal$util$TableDef$$cols = x$1;
    }

    public String defaultSep(int index) {
        return index > this.scala$reflect$internal$util$TableDef$$cols().size() - 2 ? "" : " ";
    }

    public String sepAfter(int i) {
        return this.defaultSep(i);
    }

    public IndexedSeq<Object> sepWidths() {
        return this.scala$reflect$internal$util$TableDef$$cols().indices().map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TableDef $outer;

            public final int apply(int i) {
                return this.$outer.sepAfter(i).length();
            }

            public int apply$mcII$sp(int i) {
                return this.$outer.sepAfter(i).length();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, IndexedSeq$.MODULE$.canBuildFrom());
    }

    public List<String> colNames() {
        return this.scala$reflect$internal$util$TableDef$$cols().map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final String apply(Column<T> x$1) {
                return x$1.name();
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public List<Function1<T, Object>> colFunctions() {
        return this.scala$reflect$internal$util$TableDef$$cols().map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final Function1<T, Object> apply(Column<T> x$2) {
                return x$2.f();
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public List<Object> colApply(T el) {
        return this.colFunctions().map(new Serializable(this, el){
            public static final long serialVersionUID = 0L;
            private final Object el$1;

            public final Object apply(Function1<T, Object> f) {
                return f.apply(this.el$1);
            }
            {
                this.el$1 = el$1;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public TableDef<T> retThis(Function0<BoxedUnit> body2) {
        body2.apply$mcV$sp();
        return this;
    }

    public Table table(Seq<T> rows) {
        return new Table(this, rows);
    }

    public String toString() {
        return this.scala$reflect$internal$util$TableDef$$cols().mkString("TableDef(", ", ", ")");
    }

    public TableDef(Seq<Column<T>> _cols) {
        this.scala$reflect$internal$util$TableDef$$cols = _cols.toList();
    }

    public class Table
    implements Seq<T> {
        private final Seq<T> rows;
        private final List<Object> colWidths;
        private final String rowFormat;
        private final String headFormat;
        private final Seq<List<Object>> argLists;
        private final List<String> headers;
        public final /* synthetic */ TableDef $outer;

        @Override
        public GenericCompanion<Seq> companion() {
            return Seq$class.companion(this);
        }

        @Override
        public Seq<T> seq() {
            return Seq$class.seq(this);
        }

        @Override
        public Seq<T> thisCollection() {
            return SeqLike$class.thisCollection(this);
        }

        @Override
        public Seq toCollection(Object repr) {
            return SeqLike$class.toCollection(this, repr);
        }

        @Override
        public Combiner<T, ParSeq<T>> parCombiner() {
            return SeqLike$class.parCombiner(this);
        }

        @Override
        public int lengthCompare(int len) {
            return SeqLike$class.lengthCompare(this, len);
        }

        @Override
        public boolean isEmpty() {
            return SeqLike$class.isEmpty(this);
        }

        @Override
        public int size() {
            return SeqLike$class.size(this);
        }

        @Override
        public int segmentLength(Function1<T, Object> p, int from2) {
            return SeqLike$class.segmentLength(this, p, from2);
        }

        @Override
        public int indexWhere(Function1<T, Object> p, int from2) {
            return SeqLike$class.indexWhere(this, p, from2);
        }

        @Override
        public int lastIndexWhere(Function1<T, Object> p, int end) {
            return SeqLike$class.lastIndexWhere(this, p, end);
        }

        @Override
        public Iterator<Seq<T>> permutations() {
            return SeqLike$class.permutations(this);
        }

        @Override
        public Iterator<Seq<T>> combinations(int n) {
            return SeqLike$class.combinations(this, n);
        }

        @Override
        public Object reverse() {
            return SeqLike$class.reverse(this);
        }

        @Override
        public <B, That> That reverseMap(Function1<T, B> f, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.reverseMap(this, f, bf);
        }

        @Override
        public Iterator<T> reverseIterator() {
            return SeqLike$class.reverseIterator(this);
        }

        @Override
        public <B> boolean startsWith(GenSeq<B> that, int offset) {
            return SeqLike$class.startsWith(this, that, offset);
        }

        @Override
        public <B> boolean endsWith(GenSeq<B> that) {
            return SeqLike$class.endsWith(this, that);
        }

        @Override
        public <B> int indexOfSlice(GenSeq<B> that) {
            return SeqLike$class.indexOfSlice(this, that);
        }

        @Override
        public <B> int indexOfSlice(GenSeq<B> that, int from2) {
            return SeqLike$class.indexOfSlice(this, that, from2);
        }

        @Override
        public <B> int lastIndexOfSlice(GenSeq<B> that) {
            return SeqLike$class.lastIndexOfSlice(this, that);
        }

        @Override
        public <B> int lastIndexOfSlice(GenSeq<B> that, int end) {
            return SeqLike$class.lastIndexOfSlice(this, that, end);
        }

        @Override
        public <B> boolean containsSlice(GenSeq<B> that) {
            return SeqLike$class.containsSlice(this, that);
        }

        @Override
        public <A1> boolean contains(A1 elem) {
            return SeqLike$class.contains(this, elem);
        }

        @Override
        public <B, That> That union(GenSeq<B> that, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.union(this, that, bf);
        }

        @Override
        public Object diff(GenSeq that) {
            return SeqLike$class.diff(this, that);
        }

        @Override
        public Object intersect(GenSeq that) {
            return SeqLike$class.intersect(this, that);
        }

        @Override
        public Object distinct() {
            return SeqLike$class.distinct(this);
        }

        @Override
        public <B, That> That patch(int from2, GenSeq<B> patch2, int replaced, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.patch(this, from2, patch2, replaced, bf);
        }

        @Override
        public <B, That> That updated(int index, B elem, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.updated(this, index, elem, bf);
        }

        @Override
        public <B, That> That $plus$colon(B elem, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.$plus$colon(this, elem, bf);
        }

        @Override
        public <B, That> That $colon$plus(B elem, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.$colon$plus(this, elem, bf);
        }

        @Override
        public <B, That> That padTo(int len, B elem, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)SeqLike$class.padTo(this, len, elem, bf);
        }

        @Override
        public <B> boolean corresponds(GenSeq<B> that, Function2<T, B, Object> p) {
            return SeqLike$class.corresponds(this, that, p);
        }

        @Override
        public Object sortWith(Function2 lt) {
            return SeqLike$class.sortWith(this, lt);
        }

        @Override
        public Object sortBy(Function1 f, Ordering ord) {
            return SeqLike$class.sortBy(this, f, ord);
        }

        @Override
        public Object sorted(Ordering ord) {
            return SeqLike$class.sorted(this, ord);
        }

        @Override
        public Seq<T> toSeq() {
            return SeqLike$class.toSeq(this);
        }

        @Override
        public Range indices() {
            return SeqLike$class.indices(this);
        }

        @Override
        public Object view() {
            return SeqLike$class.view(this);
        }

        @Override
        public SeqView<T, Seq<T>> view(int from2, int until2) {
            return SeqLike$class.view(this, from2, until2);
        }

        @Override
        public boolean isDefinedAt(int idx) {
            return GenSeqLike$class.isDefinedAt(this, idx);
        }

        @Override
        public int prefixLength(Function1<T, Object> p) {
            return GenSeqLike$class.prefixLength(this, p);
        }

        @Override
        public int indexWhere(Function1<T, Object> p) {
            return GenSeqLike$class.indexWhere(this, p);
        }

        @Override
        public <B> int indexOf(B elem) {
            return GenSeqLike$class.indexOf(this, elem);
        }

        @Override
        public <B> int indexOf(B elem, int from2) {
            return GenSeqLike$class.indexOf(this, elem, from2);
        }

        @Override
        public <B> int lastIndexOf(B elem) {
            return GenSeqLike$class.lastIndexOf(this, elem);
        }

        @Override
        public <B> int lastIndexOf(B elem, int end) {
            return GenSeqLike$class.lastIndexOf(this, elem, end);
        }

        @Override
        public int lastIndexWhere(Function1<T, Object> p) {
            return GenSeqLike$class.lastIndexWhere(this, p);
        }

        @Override
        public <B> boolean startsWith(GenSeq<B> that) {
            return GenSeqLike$class.startsWith(this, that);
        }

        @Override
        public int hashCode() {
            return GenSeqLike$class.hashCode(this);
        }

        @Override
        public boolean equals(Object that) {
            return GenSeqLike$class.equals(this, that);
        }

        @Override
        public <U> void foreach(Function1<T, U> f) {
            IterableLike$class.foreach(this, f);
        }

        @Override
        public boolean forall(Function1<T, Object> p) {
            return IterableLike$class.forall(this, p);
        }

        @Override
        public boolean exists(Function1<T, Object> p) {
            return IterableLike$class.exists(this, p);
        }

        @Override
        public Option<T> find(Function1<T, Object> p) {
            return IterableLike$class.find(this, p);
        }

        @Override
        public <B> B foldRight(B z, Function2<T, B, B> op) {
            return (B)IterableLike$class.foldRight(this, z, op);
        }

        @Override
        public <B> B reduceRight(Function2<T, B, B> op) {
            return (B)IterableLike$class.reduceRight(this, op);
        }

        @Override
        public Iterable<T> toIterable() {
            return IterableLike$class.toIterable(this);
        }

        @Override
        public Iterator<T> toIterator() {
            return IterableLike$class.toIterator(this);
        }

        @Override
        public T head() {
            return IterableLike$class.head(this);
        }

        @Override
        public Object slice(int from2, int until2) {
            return IterableLike$class.slice(this, from2, until2);
        }

        @Override
        public Object take(int n) {
            return IterableLike$class.take(this, n);
        }

        @Override
        public Object drop(int n) {
            return IterableLike$class.drop(this, n);
        }

        @Override
        public Object takeWhile(Function1 p) {
            return IterableLike$class.takeWhile(this, p);
        }

        @Override
        public Iterator<Seq<T>> grouped(int size2) {
            return IterableLike$class.grouped(this, size2);
        }

        @Override
        public Iterator<Seq<T>> sliding(int size2) {
            return IterableLike$class.sliding(this, size2);
        }

        @Override
        public Iterator<Seq<T>> sliding(int size2, int step) {
            return IterableLike$class.sliding(this, size2, step);
        }

        @Override
        public Object takeRight(int n) {
            return IterableLike$class.takeRight(this, n);
        }

        @Override
        public Object dropRight(int n) {
            return IterableLike$class.dropRight(this, n);
        }

        @Override
        public <B> void copyToArray(Object xs, int start, int len) {
            IterableLike$class.copyToArray(this, xs, start, len);
        }

        @Override
        public <A1, B, That> That zip(GenIterable<B> that, CanBuildFrom<Seq<T>, Tuple2<A1, B>, That> bf) {
            return (That)IterableLike$class.zip(this, that, bf);
        }

        @Override
        public <B, A1, That> That zipAll(GenIterable<B> that, A1 thisElem, B thatElem, CanBuildFrom<Seq<T>, Tuple2<A1, B>, That> bf) {
            return (That)IterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
        }

        @Override
        public <A1, That> That zipWithIndex(CanBuildFrom<Seq<T>, Tuple2<A1, Object>, That> bf) {
            return (That)IterableLike$class.zipWithIndex(this, bf);
        }

        @Override
        public <B> boolean sameElements(GenIterable<B> that) {
            return IterableLike$class.sameElements(this, that);
        }

        @Override
        public Stream<T> toStream() {
            return IterableLike$class.toStream(this);
        }

        @Override
        public boolean canEqual(Object that) {
            return IterableLike$class.canEqual(this, that);
        }

        @Override
        public Builder<T, Seq<T>> newBuilder() {
            return GenericTraversableTemplate$class.newBuilder(this);
        }

        @Override
        public <B> Builder<B, Seq<B>> genericBuilder() {
            return GenericTraversableTemplate$class.genericBuilder(this);
        }

        @Override
        public <A1, A2> Tuple2<Seq<A1>, Seq<A2>> unzip(Function1<T, Tuple2<A1, A2>> asPair) {
            return GenericTraversableTemplate$class.unzip(this, asPair);
        }

        @Override
        public <A1, A2, A3> Tuple3<Seq<A1>, Seq<A2>, Seq<A3>> unzip3(Function1<T, Tuple3<A1, A2, A3>> asTriple) {
            return GenericTraversableTemplate$class.unzip3(this, asTriple);
        }

        @Override
        public GenTraversable flatten(Function1 asTraversable) {
            return GenericTraversableTemplate$class.flatten(this, asTraversable);
        }

        @Override
        public GenTraversable transpose(Function1 asTraversable) {
            return GenericTraversableTemplate$class.transpose(this, asTraversable);
        }

        @Override
        public Object repr() {
            return TraversableLike$class.repr(this);
        }

        @Override
        public final boolean isTraversableAgain() {
            return TraversableLike$class.isTraversableAgain(this);
        }

        @Override
        public boolean hasDefiniteSize() {
            return TraversableLike$class.hasDefiniteSize(this);
        }

        @Override
        public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus(this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.$plus$plus$colon((TraversableLike)this, that, bf);
        }

        @Override
        public <B, That> That map(Function1<T, B> f, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.map(this, f, bf);
        }

        @Override
        public <B, That> That flatMap(Function1<T, GenTraversableOnce<B>> f, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.flatMap(this, f, bf);
        }

        @Override
        public Object filter(Function1 p) {
            return TraversableLike$class.filter(this, p);
        }

        @Override
        public Object filterNot(Function1 p) {
            return TraversableLike$class.filterNot(this, p);
        }

        @Override
        public <B, That> That collect(PartialFunction<T, B> pf, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.collect(this, pf, bf);
        }

        @Override
        public Tuple2<Seq<T>, Seq<T>> partition(Function1<T, Object> p) {
            return TraversableLike$class.partition(this, p);
        }

        @Override
        public <K> Map<K, Seq<T>> groupBy(Function1<T, K> f) {
            return TraversableLike$class.groupBy(this, f);
        }

        @Override
        public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Seq<T>, B, That> cbf) {
            return (That)TraversableLike$class.scan(this, z, op, cbf);
        }

        @Override
        public <B, That> That scanLeft(B z, Function2<B, T, B> op, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.scanLeft(this, z, op, bf);
        }

        @Override
        public <B, That> That scanRight(B z, Function2<T, B, B> op, CanBuildFrom<Seq<T>, B, That> bf) {
            return (That)TraversableLike$class.scanRight(this, z, op, bf);
        }

        @Override
        public Option<T> headOption() {
            return TraversableLike$class.headOption(this);
        }

        @Override
        public Object tail() {
            return TraversableLike$class.tail(this);
        }

        @Override
        public T last() {
            return TraversableLike$class.last(this);
        }

        @Override
        public Option<T> lastOption() {
            return TraversableLike$class.lastOption(this);
        }

        @Override
        public Object init() {
            return TraversableLike$class.init(this);
        }

        @Override
        public Object sliceWithKnownDelta(int from2, int until2, int delta) {
            return TraversableLike$class.sliceWithKnownDelta(this, from2, until2, delta);
        }

        @Override
        public Object sliceWithKnownBound(int from2, int until2) {
            return TraversableLike$class.sliceWithKnownBound(this, from2, until2);
        }

        @Override
        public Object dropWhile(Function1 p) {
            return TraversableLike$class.dropWhile(this, p);
        }

        @Override
        public Tuple2<Seq<T>, Seq<T>> span(Function1<T, Object> p) {
            return TraversableLike$class.span(this, p);
        }

        @Override
        public Tuple2<Seq<T>, Seq<T>> splitAt(int n) {
            return TraversableLike$class.splitAt(this, n);
        }

        @Override
        public Iterator<Seq<T>> tails() {
            return TraversableLike$class.tails(this);
        }

        @Override
        public Iterator<Seq<T>> inits() {
            return TraversableLike$class.inits(this);
        }

        @Override
        public Traversable<T> toTraversable() {
            return TraversableLike$class.toTraversable(this);
        }

        @Override
        public <Col> Col to(CanBuildFrom<Nothing$, T, Col> cbf) {
            return (Col)TraversableLike$class.to(this, cbf);
        }

        @Override
        public String stringPrefix() {
            return TraversableLike$class.stringPrefix(this);
        }

        @Override
        public FilterMonadic<T, Seq<T>> withFilter(Function1<T, Object> p) {
            return TraversableLike$class.withFilter(this, p);
        }

        @Override
        public Parallel par() {
            return Parallelizable$class.par(this);
        }

        @Override
        public List<T> reversed() {
            return TraversableOnce$class.reversed(this);
        }

        @Override
        public boolean nonEmpty() {
            return TraversableOnce$class.nonEmpty(this);
        }

        @Override
        public int count(Function1<T, Object> p) {
            return TraversableOnce$class.count(this, p);
        }

        @Override
        public <B> Option<B> collectFirst(PartialFunction<T, B> pf) {
            return TraversableOnce$class.collectFirst(this, pf);
        }

        @Override
        public <B> B $div$colon(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.$div$colon(this, z, op);
        }

        @Override
        public <B> B $colon$bslash(B z, Function2<T, B, B> op) {
            return (B)TraversableOnce$class.$colon$bslash(this, z, op);
        }

        @Override
        public <B> B foldLeft(B z, Function2<B, T, B> op) {
            return (B)TraversableOnce$class.foldLeft(this, z, op);
        }

        @Override
        public <B> B reduceLeft(Function2<B, T, B> op) {
            return (B)TraversableOnce$class.reduceLeft(this, op);
        }

        @Override
        public <B> Option<B> reduceLeftOption(Function2<B, T, B> op) {
            return TraversableOnce$class.reduceLeftOption(this, op);
        }

        @Override
        public <B> Option<B> reduceRightOption(Function2<T, B, B> op) {
            return TraversableOnce$class.reduceRightOption(this, op);
        }

        @Override
        public <A1> A1 reduce(Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.reduce(this, op);
        }

        @Override
        public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return TraversableOnce$class.reduceOption(this, op);
        }

        @Override
        public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
            return (A1)TraversableOnce$class.fold(this, z, op);
        }

        @Override
        public <B> B aggregate(Function0<B> z, Function2<B, T, B> seqop, Function2<B, B, B> combop) {
            return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
        }

        @Override
        public <B> B sum(Numeric<B> num) {
            return (B)TraversableOnce$class.sum(this, num);
        }

        @Override
        public <B> B product(Numeric<B> num) {
            return (B)TraversableOnce$class.product(this, num);
        }

        @Override
        public <B> T min(Ordering<B> cmp) {
            return TraversableOnce$class.min(this, cmp);
        }

        @Override
        public <B> T max(Ordering<B> cmp) {
            return TraversableOnce$class.max(this, cmp);
        }

        @Override
        public <B> T maxBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.maxBy(this, f, cmp);
        }

        @Override
        public <B> T minBy(Function1<T, B> f, Ordering<B> cmp) {
            return TraversableOnce$class.minBy(this, f, cmp);
        }

        @Override
        public <B> void copyToBuffer(Buffer<B> dest) {
            TraversableOnce$class.copyToBuffer(this, dest);
        }

        @Override
        public <B> void copyToArray(Object xs, int start) {
            TraversableOnce$class.copyToArray(this, xs, start);
        }

        @Override
        public <B> void copyToArray(Object xs) {
            TraversableOnce$class.copyToArray(this, xs);
        }

        @Override
        public <B> Object toArray(ClassTag<B> evidence$1) {
            return TraversableOnce$class.toArray(this, evidence$1);
        }

        @Override
        public List<T> toList() {
            return TraversableOnce$class.toList(this);
        }

        @Override
        public IndexedSeq<T> toIndexedSeq() {
            return TraversableOnce$class.toIndexedSeq(this);
        }

        @Override
        public <B> Buffer<B> toBuffer() {
            return TraversableOnce$class.toBuffer(this);
        }

        @Override
        public <B> Set<B> toSet() {
            return TraversableOnce$class.toSet(this);
        }

        @Override
        public Vector<T> toVector() {
            return TraversableOnce$class.toVector(this);
        }

        @Override
        public <T, U> Map<T, U> toMap(Predef$.less.colon.less<T, Tuple2<T, U>> ev) {
            return TraversableOnce$class.toMap(this, ev);
        }

        @Override
        public String mkString(String start, String sep, String end) {
            return TraversableOnce$class.mkString(this, start, sep, end);
        }

        @Override
        public String mkString(String sep) {
            return TraversableOnce$class.mkString(this, sep);
        }

        @Override
        public String mkString() {
            return TraversableOnce$class.mkString(this);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return TraversableOnce$class.addString(this, b, start, sep, end);
        }

        @Override
        public StringBuilder addString(StringBuilder b, String sep) {
            return TraversableOnce$class.addString(this, b, sep);
        }

        @Override
        public StringBuilder addString(StringBuilder b) {
            return TraversableOnce$class.addString(this, b);
        }

        @Override
        public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
            return PartialFunction$class.orElse(this, that);
        }

        @Override
        public <C> PartialFunction<Object, C> andThen(Function1<T, C> k) {
            return PartialFunction$class.andThen(this, k);
        }

        @Override
        public Function1<Object, Option<T>> lift() {
            return PartialFunction$class.lift(this);
        }

        @Override
        public Object applyOrElse(Object x, Function1 function1) {
            return PartialFunction$class.applyOrElse(this, x, function1);
        }

        @Override
        public <U> Function1<Object, Object> runWith(Function1<T, U> action) {
            return PartialFunction$class.runWith(this, action);
        }

        @Override
        public boolean apply$mcZD$sp(double v1) {
            return Function1$class.apply$mcZD$sp(this, v1);
        }

        @Override
        public double apply$mcDD$sp(double v1) {
            return Function1$class.apply$mcDD$sp(this, v1);
        }

        @Override
        public float apply$mcFD$sp(double v1) {
            return Function1$class.apply$mcFD$sp(this, v1);
        }

        @Override
        public int apply$mcID$sp(double v1) {
            return Function1$class.apply$mcID$sp(this, v1);
        }

        @Override
        public long apply$mcJD$sp(double v1) {
            return Function1$class.apply$mcJD$sp(this, v1);
        }

        @Override
        public void apply$mcVD$sp(double v1) {
            Function1$class.apply$mcVD$sp(this, v1);
        }

        @Override
        public boolean apply$mcZF$sp(float v1) {
            return Function1$class.apply$mcZF$sp(this, v1);
        }

        @Override
        public double apply$mcDF$sp(float v1) {
            return Function1$class.apply$mcDF$sp(this, v1);
        }

        @Override
        public float apply$mcFF$sp(float v1) {
            return Function1$class.apply$mcFF$sp(this, v1);
        }

        @Override
        public int apply$mcIF$sp(float v1) {
            return Function1$class.apply$mcIF$sp(this, v1);
        }

        @Override
        public long apply$mcJF$sp(float v1) {
            return Function1$class.apply$mcJF$sp(this, v1);
        }

        @Override
        public void apply$mcVF$sp(float v1) {
            Function1$class.apply$mcVF$sp(this, v1);
        }

        @Override
        public boolean apply$mcZI$sp(int v1) {
            return Function1$class.apply$mcZI$sp(this, v1);
        }

        @Override
        public double apply$mcDI$sp(int v1) {
            return Function1$class.apply$mcDI$sp(this, v1);
        }

        @Override
        public float apply$mcFI$sp(int v1) {
            return Function1$class.apply$mcFI$sp(this, v1);
        }

        @Override
        public int apply$mcII$sp(int v1) {
            return Function1$class.apply$mcII$sp(this, v1);
        }

        @Override
        public long apply$mcJI$sp(int v1) {
            return Function1$class.apply$mcJI$sp(this, v1);
        }

        @Override
        public void apply$mcVI$sp(int v1) {
            Function1$class.apply$mcVI$sp(this, v1);
        }

        @Override
        public boolean apply$mcZJ$sp(long v1) {
            return Function1$class.apply$mcZJ$sp(this, v1);
        }

        @Override
        public double apply$mcDJ$sp(long v1) {
            return Function1$class.apply$mcDJ$sp(this, v1);
        }

        @Override
        public float apply$mcFJ$sp(long v1) {
            return Function1$class.apply$mcFJ$sp(this, v1);
        }

        @Override
        public int apply$mcIJ$sp(long v1) {
            return Function1$class.apply$mcIJ$sp(this, v1);
        }

        @Override
        public long apply$mcJJ$sp(long v1) {
            return Function1$class.apply$mcJJ$sp(this, v1);
        }

        @Override
        public void apply$mcVJ$sp(long v1) {
            Function1$class.apply$mcVJ$sp(this, v1);
        }

        @Override
        public <A> Function1<A, T> compose(Function1<A, Object> g) {
            return Function1$class.compose(this, g);
        }

        public Seq<T> rows() {
            return this.rows;
        }

        @Override
        public Iterator<T> iterator() {
            return this.rows().iterator();
        }

        @Override
        public T apply(int index) {
            return this.rows().apply(index);
        }

        @Override
        public int length() {
            return this.rows().length();
        }

        public int maxColWidth(Column<T> col) {
            String string2 = col.name();
            return BoxesRunTime.unboxToInt(((TraversableOnce)((TraversableLike)((SeqLike)this.rows().map(col.f(), Seq$.MODULE$.canBuildFrom())).$plus$colon(string2, Seq$.MODULE$.canBuildFrom())).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Object x$4) {
                    return x$4.toString().length();
                }
            }, Seq$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$));
        }

        public List<String> specs() {
            return this.scala$reflect$internal$util$TableDef$Table$$$outer().scala$reflect$internal$util$TableDef$$cols().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final String apply(Column<T> x$5) {
                    return x$5.formatSpec(this.$outer.rows());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public List<Object> colWidths() {
            return this.colWidths;
        }

        public String rowFormat() {
            return this.rowFormat;
        }

        public String headFormat() {
            return this.headFormat;
        }

        public Seq<List<Object>> argLists() {
            return this.argLists;
        }

        public List<String> headers() {
            return this.headers;
        }

        public String mkFormatString(Function1<Object, String> sepf) {
            return ((TraversableOnce)this.specs().zipWithIndex(List$.MODULE$.canBuildFrom()).map(new Serializable(this, sepf){
                public static final long serialVersionUID = 0L;
                private final Function1 sepf$1;

                public final String apply(Tuple2<String, Object> x0$1) {
                    if (x0$1 != null) {
                        return new StringBuilder().append((Object)x0$1._1()).append(this.sepf$1.apply(BoxesRunTime.boxToInteger(x0$1._2$mcI$sp()))).toString();
                    }
                    throw new MatchError(x0$1);
                }
                {
                    this.sepf$1 = sepf$1;
                }
            }, List$.MODULE$.canBuildFrom())).mkString();
        }

        public Seq<String> toFormattedSeq() {
            return this.argLists().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final String apply(List<Object> xs) {
                    String string2 = this.$outer.rowFormat();
                    Predef$ predef$ = Predef$.MODULE$;
                    return new StringOps(string2).format(xs);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
        }

        public List<String> allToSeq() {
            return this.headers().$plus$plus(this.toFormattedSeq(), List$.MODULE$.canBuildFrom());
        }

        @Override
        public String toString() {
            return this.allToSeq().mkString("\n");
        }

        public /* synthetic */ TableDef scala$reflect$internal$util$TableDef$Table$$$outer() {
            return this.$outer;
        }

        public Table(TableDef<T> $outer, Seq<T> rows) {
            this.rows = rows;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Function1$class.$init$(this);
            PartialFunction$class.$init$(this);
            TraversableOnce$class.$init$(this);
            Parallelizable$class.$init$(this);
            TraversableLike$class.$init$(this);
            GenericTraversableTemplate$class.$init$(this);
            GenTraversable$class.$init$(this);
            Traversable$class.$init$(this);
            GenIterable$class.$init$(this);
            IterableLike$class.$init$(this);
            Iterable$class.$init$(this);
            GenSeqLike$class.$init$(this);
            GenSeq$class.$init$(this);
            SeqLike$class.$init$(this);
            Seq$class.$init$(this);
            this.colWidths = $outer.scala$reflect$internal$util$TableDef$$cols().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final int apply(Column<T> col) {
                    return this.$outer.maxColWidth(col);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            this.rowFormat = this.mkFormatString((Function1<Object, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final String apply(int i) {
                    return this.$outer.scala$reflect$internal$util$TableDef$Table$$$outer().sepAfter(i);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            this.headFormat = this.mkFormatString((Function1<Object, String>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final String apply(int i) {
                    Predef$ predef$ = Predef$.MODULE$;
                    return new StringOps(" ").$times(BoxesRunTime.unboxToInt(this.$outer.scala$reflect$internal$util$TableDef$Table$$$outer().sepWidths().apply(i)));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            this.argLists = rows.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Table $outer;

                public final List<Object> apply(T el) {
                    return this.$outer.scala$reflect$internal$util$TableDef$Table$$$outer().colApply(el);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Seq$.MODULE$.canBuildFrom());
            String[] stringArray = new String[2];
            String string2 = this.headFormat();
            Predef$ predef$ = Predef$.MODULE$;
            stringArray[0] = new StringOps(string2).format($outer.colNames());
            stringArray[1] = ((TraversableOnce)Tuple2Zipped$.MODULE$.map$extension(Tuple2Zipped$Ops$.MODULE$.zipped$extension(Predef$.MODULE$.tuple2ToZippedOps(new Tuple2<List<Object>, IndexedSeq<Object>>(this.colWidths(), $outer.sepWidths())), Predef$.MODULE$.$conforms(), Predef$.MODULE$.$conforms()), new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final String apply(int w1, int w2) {
                    Predef$ predef$ = Predef$.MODULE$;
                    Predef$ predef$2 = Predef$.MODULE$;
                    return new StringBuilder().append((Object)new StringOps("-").$times(w1)).append((Object)new StringOps(" ").$times(w2)).toString();
                }
            }, List$.MODULE$.canBuildFrom())).mkString();
            this.headers = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])stringArray));
        }
    }

    public static class Column<T>
    implements Product,
    Serializable {
        private final String name;
        private final Function1<T, Object> f;
        private final boolean left;

        public String name() {
            return this.name;
        }

        public Function1<T, Object> f() {
            return this.f;
        }

        public boolean left() {
            return this.left;
        }

        public int maxWidth(Seq<T> elems) {
            String string2 = this.name();
            return BoxesRunTime.unboxToInt(((TraversableOnce)((TraversableLike)((SeqLike)elems.map(this.f(), Seq$.MODULE$.canBuildFrom())).$plus$colon(string2, Seq$.MODULE$.canBuildFrom())).map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final int apply(Object x$7) {
                    return x$7.toString().length();
                }
            }, Seq$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$));
        }

        public String formatSpec(Seq<T> elems) {
            String justify = this.left() ? "-" : "";
            return new StringBuilder().append((Object)"%").append((Object)justify).append(BoxesRunTime.boxToInteger(this.maxWidth(elems))).append((Object)"s").toString();
        }

        public String toString() {
            String justify = this.left() ? "<<" : ">>";
            return new StringBuilder().append((Object)justify).append((Object)"(").append((Object)this.name()).append((Object)")").toString();
        }

        public <T> Column<T> copy(String name, Function1<T, Object> f, boolean left) {
            return new Column<T>(name, f, left);
        }

        public <T> String copy$default$1() {
            return this.name();
        }

        public <T> Function1<T, Object> copy$default$2() {
            return this.f();
        }

        public <T> boolean copy$default$3() {
            return this.left();
        }

        @Override
        public String productPrefix() {
            return "Column";
        }

        @Override
        public int productArity() {
            return 3;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 2: {
                    object = BoxesRunTime.boxToBoolean(this.left());
                    break;
                }
                case 1: {
                    object = this.f();
                    break;
                }
                case 0: {
                    object = this.name();
                }
            }
            return object;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof Column;
        }

        public int hashCode() {
            return Statics.finalizeHash(Statics.mix(Statics.mix(Statics.mix(-889275714, Statics.anyHash(this.name())), Statics.anyHash(this.f())), this.left() ? 1231 : 1237), 3);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof Column)) return false;
            boolean bl = true;
            if (!bl) return false;
            Column column = (Column)x$1;
            String string2 = this.name();
            String string3 = column.name();
            if (string2 == null) {
                if (string3 != null) {
                    return false;
                }
            } else if (!string2.equals(string3)) return false;
            Function1<T, Object> function1 = this.f();
            Function1<T, Object> function12 = column.f();
            if (function1 == null) {
                if (function12 != null) {
                    return false;
                }
            } else if (!function1.equals(function12)) return false;
            if (this.left() != column.left()) return false;
            if (!column.canEqual(this)) return false;
            return true;
        }

        public Column(String name, Function1<T, Object> f, boolean left) {
            this.name = name;
            this.f = f;
            this.left = left;
            Product$class.$init$(this);
        }
    }
}

