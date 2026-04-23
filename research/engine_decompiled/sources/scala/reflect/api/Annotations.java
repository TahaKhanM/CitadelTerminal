/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Option;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.collection.immutable.ListMap;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Constants;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u0001\t]a!C\u0001\u0003!\u0003\r\t!\u0003B\b\u0005-\teN\\8uCRLwN\\:\u000b\u0005\r!\u0011aA1qS*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LG\u000fB\u0003\u0016\u0001\t\u0005aC\u0001\u0006B]:|G/\u0019;j_:\f\"a\u0006\u000e\u0011\u0005-A\u0012BA\r\u0007\u0005\u0011qU\u000f\u001c7\u0013\u0007mQQD\u0002\u0003\u001d\u0001\u0001Q\"\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004C\u0001\u0010 \u001b\u0005\u0001aa\u0002\u0011\u0001!\u0003\r\t!\t\u0002\u000e\u0003:tw\u000e^1uS>t\u0017\t]5\u0014\u0005}Q\u0001\"B\b \t\u0003\u0001\u0002\"\u0002\u0013 \t\u0003)\u0013\u0001\u0002;sK\u0016,\u0012A\n\t\u0003=\u001dJ!\u0001K\u0015\u0003\tQ\u0013X-Z\u0005\u0003U\t\u0011Q\u0001\u0016:fKNDQ\u0001L\u0010\u0007\u00025\n1\u0001\u001e9f+\u0005q\u0003C\u0001\u00100\u0013\t\u0001\u0014G\u0001\u0003UsB,\u0017B\u0001\u001a\u0003\u0005\u0015!\u0016\u0010]3tQ\u0011YCgN\u001d\u0011\u0005-)\u0014B\u0001\u001c\u0007\u0005)!W\r\u001d:fG\u0006$X\rZ\u0011\u0002q\u00051Rk]3!AR\u0014X-\u001a\u0018ua\u0016\u0004\u0007%\u001b8ti\u0016\fG-I\u0001;\u0003\u0019\u0011d&M\u0019/a!)Ah\bD\u0001{\u0005I1oY1mC\u0006\u0013xm]\u000b\u0002}A\u0019qH\u0011\u0014\u000f\u0005-\u0001\u0015BA!\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0011#\u0003\t1K7\u000f\u001e\u0006\u0003\u0003\u001aACa\u000f\u001bGs\u0005\nq)\u0001\u0011Vg\u0016\u0004\u0003\r\u001e:fK:\u001a\u0007.\u001b7ee\u0016tg\u0006^1jY\u0002\u0004\u0013N\\:uK\u0006$\u0007\"B% \r\u0003Q\u0015\u0001\u00036bm\u0006\f%oZ:\u0016\u0003-\u0003B\u0001T)T16\tQJ\u0003\u0002O\u001f\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003!\u001a\t!bY8mY\u0016\u001cG/[8o\u0013\t\u0011VJA\u0004MSN$X*\u00199\u0011\u0005y!\u0016BA+W\u0005\u0011q\u0015-\\3\n\u0005]\u0013!!\u0002(b[\u0016\u001c\bC\u0001\u0010Z\t\u0015Q\u0006A!\u0001\\\u00051Q\u0015M^1Be\u001e,X.\u001a8u#\t9BLE\u0002^\u0015y3A\u0001\b\u0001\u00019B\u0011ad\u0018\u0004\bA\u0002\u0001\n1%\u0001b\u0005=Q\u0015M^1Be\u001e,X.\u001a8u\u0003BL7CA0\u000bQ\u0011yFgY\u001d\"\u0003\u0011\fQ'V:fA\u0001\feN\\8uCRLwN\u001c\u0018ue\u0016,\u0007\r\t;pA%t7\u000f]3di\u0002\ngN\\8uCRLwN\u001c\u0011be\u001e,X.\u001a8ug\"\"\u0011\fN2:Q\u0011AEGR\u001d\t\u000f!\u0004!\u0019!D\u0001S\u0006Q\u0011I\u001c8pi\u0006$\u0018n\u001c8\u0016\u0003)\u0004\"AH6\u0007\u000b1\u0004\u0011\u0011A7\u0003'\u0005sgn\u001c;bi&|g.\u0012=ue\u0006\u001cGo\u001c:\u0014\u0005-T\u0001\"B8l\t\u0003\u0001\u0018A\u0002\u001fj]&$h\bF\u0001k\u0011\u0015\u00118\u000e\"\u0001t\u0003\u0015\t\u0007\u000f\u001d7z)\t!X\u000f\u0005\u0002\u001f)!)A%\u001da\u0001M!)!o\u001bD\u0001oR!A\u000f_={\u0011\u0015ac\u000f1\u0001/\u0011\u0015ad\u000f1\u0001?\u0011\u0015Ie\u000f1\u0001LQ\u00111H\u0007`\u001d\"\u0003u\f1&V:fA\u0001\f\u0007\u000f\u001d7zQQ\u0014X-\u001a\u001e!)J,W-\u000b\u001e!\u0003:tw\u000e^1uS>t\u0007\rI5ogR,\u0017\r\u001a\u0005\u0007\u007f.4\t!!\u0001\u0002\u000fUt\u0017\r\u001d9msR!\u00111AA\b!\u0015Y\u0011QAA\u0005\u0013\r\t9A\u0002\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r-\tYA\f L\u0013\r\tiA\u0002\u0002\u0007)V\u0004H.Z\u001a\t\r\u0005Ea\u00101\u0001u\u0003\r\tgN\u001c\u0015\u0005}R\u001a\u0017\b\u0003\u0005\u0002\u0018\u00011\tBBA\r\u0003A\tgN\\8uCRLwN\u001c+p)J,W\rF\u0002'\u00037Aq!!\u0005\u0002\u0016\u0001\u0007A\u000f\u0003\u0005\u0002 \u00011\tBBA\u0011\u0003A!(/Z3U_\u0006sgn\u001c;bi&|g\u000eF\u0002u\u0003GAa\u0001JA\u000f\u0001\u00041CaBA\u0014\u0001\t\u0005\u0011\u0011\u0006\u0002\u0010\u0019&$XM]1m\u0003J<W/\\3oiF\u0019q#a\u000b\u0013\u000b\u00055\u0012q\u0006-\u0007\u000bq\u0001\u0001!a\u000b\u0011\u0007y\t\tDB\u0005\u00024\u0001\u0001\n1%\u0001\u00026\t\u0011B*\u001b;fe\u0006d\u0017I]4v[\u0016tG/\u00119j'\r\t\tD\u0003\u0005\t\u0003s\t\tD\"\u0001\u0002<\u0005)a/\u00197vKV\u0011\u0011Q\b\t\u0004=\u0005}\u0012\u0002BA!\u0003\u0007\u0012\u0001bQ8ogR\fg\u000e^\u0005\u0004\u0003\u000b\u0012!!C\"p]N$\u0018M\u001c;tQ\u0015\t9\u0004N2:Q\u0015\t\t\u0004N2:Q\u0015\t)\u0003N2:\u0011%\ty\u0005\u0001b\u0001\u000e\u0003\t\t&A\bMSR,'/\u00197Be\u001e,X.\u001a8u+\t\t\u0019\u0006E\u0002\u001f\u0003+2q!a\u0016\u0001\u0003\u0003\tIF\u0001\rMSR,'/\u00197Be\u001e,X.\u001a8u\u000bb$(/Y2u_J\u001c2!!\u0016\u000b\u0011\u001dy\u0017Q\u000bC\u0001\u0003;\"\"!a\u0015\t\u000fI\f)F\"\u0001\u0002bQ!\u00111MA3!\rq\u0012Q\u0005\u0005\t\u0003s\ty\u00061\u0001\u0002>!*\u0011q\f\u001bds!9q0!\u0016\u0007\u0002\u0005-D\u0003BA7\u0003_\u0002RaCA\u0003\u0003{A\u0001\"!\u001d\u0002j\u0001\u0007\u00111M\u0001\u0004CJ<\u0007&BA5i\rL\u0004&BA+i\rL\u0004&BA'i\rLDaBA>\u0001\t\u0005\u0011Q\u0010\u0002\u000e\u0003J\u0014\u0018-_!sOVlWM\u001c;\u0012\u0007]\tyHE\u0003\u0002\u0002\u0006\r\u0005LB\u0003\u001d\u0001\u0001\ty\bE\u0002\u001f\u0003\u000b3\u0011\"a\"\u0001!\u0003\r\n!!#\u0003!\u0005\u0013(/Y=Be\u001e,X.\u001a8u\u0003BL7cAAC\u0015!A\u0011QRAC\r\u0003\ty)\u0001\u0003be\u001e\u001cXCAAI!\u0011Y\u00111\u0013-\n\u0007\u0005UeAA\u0003BeJ\f\u0017\u0010K\u0003\u0002\fR\u001a\u0017\bK\u0003\u0002\u0006R\u001a\u0017\bK\u0003\u0002zQ\u001a\u0017\bC\u0005\u0002 \u0002\u0011\rQ\"\u0001\u0002\"\u0006i\u0011I\u001d:bs\u0006\u0013x-^7f]R,\"!a)\u0011\u0007y\t)KB\u0004\u0002(\u0002\t\t!!+\u0003-\u0005\u0013(/Y=Be\u001e,X.\u001a8u\u000bb$(/Y2u_J\u001c2!!*\u000b\u0011\u001dy\u0017Q\u0015C\u0001\u0003[#\"!a)\t\u000fI\f)K\"\u0001\u00022R!\u00111WA[!\rq\u0012\u0011\u0010\u0005\t\u0003\u001b\u000by\u000b1\u0001\u0002\u0012\"*\u0011q\u0016\u001bds!9q0!*\u0007\u0002\u0005mF\u0003BA_\u0003\u007f\u0003RaCA\u0003\u0003#C\u0001\"!\u001d\u0002:\u0002\u0007\u00111\u0017\u0015\u0006\u0003s#4-\u000f\u0015\u0006\u0003K#4-\u000f\u0015\u0006\u0003;#4-\u000f\u0003\b\u0003\u0013\u0004!\u0011AAf\u00059qUm\u001d;fI\u0006\u0013x-^7f]R\f2aFAg%\u0015\ty-!5Y\r\u0015a\u0002\u0001AAg!\rq\u00121\u001b\u0004\n\u0003+\u0004\u0001\u0013aI\u0001\u0003/\u0014\u0011CT3ti\u0016$\u0017I]4v[\u0016tG/\u00119j'\r\t\u0019N\u0003\u0005\t\u00037\f\u0019N\"\u0001\u0002^\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u0016\u0003QDS!!75GfBS!a55GfBS!a25GfB\u0011\"a:\u0001\u0005\u00045\t!!;\u0002\u001d9+7\u000f^3e\u0003J<W/\\3oiV\u0011\u00111\u001e\t\u0004=\u00055haBAx\u0001\u0005\u0005\u0011\u0011\u001f\u0002\u0018\u001d\u0016\u001cH/\u001a3Be\u001e,X.\u001a8u\u000bb$(/Y2u_J\u001c2!!<\u000b\u0011\u001dy\u0017Q\u001eC\u0001\u0003k$\"!a;\t\u000fI\fiO\"\u0001\u0002zR!\u00111`A\u007f!\rq\u0012q\u0019\u0005\b\u00037\f9\u00101\u0001uQ\u0015\t9\u0010N2:\u0011\u001dy\u0018Q\u001eD\u0001\u0005\u0007!BA!\u0002\u0003\bA!1\"!\u0002u\u0011!\t\tH!\u0001A\u0002\u0005m\b&\u0002B\u0001i\rL\u0004&BAwi\rL\u0004&BAsi\rL\u0004\u0003\u0002B\t\u0005'i\u0011AA\u0005\u0004\u0005+\u0011!\u0001C+oSZ,'o]3")
public interface Annotations {
    public AnnotationExtractor Annotation();

    public Trees.TreeApi annotationToTree(AnnotationApi var1);

    public AnnotationApi treeToAnnotation(Trees.TreeApi var1);

    public LiteralArgumentExtractor LiteralArgument();

    public ArrayArgumentExtractor ArrayArgument();

    public NestedArgumentExtractor NestedArgument();

    public interface AnnotationApi {
        public Trees.TreeApi tree();

        public Types.TypeApi tpe();

        public List<Trees.TreeApi> scalaArgs();

        public ListMap<Names.NameApi, JavaArgumentApi> javaArgs();

        public /* synthetic */ Annotations scala$reflect$api$Annotations$AnnotationApi$$$outer();
    }

    public interface JavaArgumentApi {
    }

    public interface ArrayArgumentApi {
        public JavaArgumentApi[] args();
    }

    public interface NestedArgumentApi {
        public AnnotationApi annotation();
    }

    public interface LiteralArgumentApi {
        public Constants.ConstantApi value();
    }

    public static abstract class AnnotationExtractor {
        public final /* synthetic */ Universe $outer;

        public AnnotationApi apply(Trees.TreeApi tree) {
            return this.scala$reflect$api$Annotations$AnnotationExtractor$$$outer().treeToAnnotation(tree);
        }

        public abstract AnnotationApi apply(Types.TypeApi var1, List<Trees.TreeApi> var2, ListMap<Names.NameApi, JavaArgumentApi> var3);

        public abstract Option<Tuple3<Types.TypeApi, List<Trees.TreeApi>, ListMap<Names.NameApi, JavaArgumentApi>>> unapply(AnnotationApi var1);

        public /* synthetic */ Universe scala$reflect$api$Annotations$AnnotationExtractor$$$outer() {
            return this.$outer;
        }

        public AnnotationExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class ArrayArgumentExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract ArrayArgumentApi apply(JavaArgumentApi[] var1);

        public abstract Option<JavaArgumentApi[]> unapply(ArrayArgumentApi var1);

        public /* synthetic */ Universe scala$reflect$api$Annotations$ArrayArgumentExtractor$$$outer() {
            return this.$outer;
        }

        public ArrayArgumentExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class NestedArgumentExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract NestedArgumentApi apply(AnnotationApi var1);

        public abstract Option<AnnotationApi> unapply(NestedArgumentApi var1);

        public /* synthetic */ Universe scala$reflect$api$Annotations$NestedArgumentExtractor$$$outer() {
            return this.$outer;
        }

        public NestedArgumentExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class LiteralArgumentExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract LiteralArgumentApi apply(Constants.ConstantApi var1);

        public abstract Option<Constants.ConstantApi> unapply(LiteralArgumentApi var1);

        public /* synthetic */ Universe scala$reflect$api$Annotations$LiteralArgumentExtractor$$$outer() {
            return this.$outer;
        }

        public LiteralArgumentExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

