/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros.whitebox;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.macros.whitebox.Context$ImplicitCandidate$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0005faB\u0001\u0003!\u0003\r\ta\u0003\u0002\b\u0007>tG/\u001a=u\u0015\t\u0019A!\u0001\u0005xQ&$XMY8y\u0015\t)a!\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\u0007\u0011!\tia\"D\u0001\t\u0013\ty\u0001B\u0001\u0004B]f\u0014VM\u001a\t\u0003#Qi\u0011A\u0005\u0006\u0003'\u0011\t\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003\u0003IAQA\u0006\u0001\u0005\u0002]\ta\u0001J5oSR$C#\u0001\r\u0011\u00055I\u0012B\u0001\u000e\t\u0005\u0011)f.\u001b;\t\u000bq\u0001a\u0011A\u000f\u0002\u0015=\u0004XM\\'bGJ|7/F\u0001\u001f!\ry\"%\n\b\u0003\u001b\u0001J!!\t\u0005\u0002\u000fA\f7m[1hK&\u00111\u0005\n\u0002\u0005\u0019&\u001cHO\u0003\u0002\"\u0011A\u0011a\u0005A\u0007\u0002\u0005!)\u0001\u0006\u0001D\u0001;\u0005yQM\\2m_NLgnZ'bGJ|7O\u0002\u0003+\u0001\u0001[#!E%na2L7-\u001b;DC:$\u0017\u000eZ1uKN!\u0011\u0006\u0004\u00170!\tiQ&\u0003\u0002/\u0011\t9\u0001K]8ek\u000e$\bCA\u00071\u0013\t\t\u0004B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0003\u00054S\tU\r\u0011\"\u00015\u0003\r\u0001(/Z\u000b\u0002kA\u0011agN\u0007\u0002\u0001%\u0011\u0001(\u000f\u0002\u0005)f\u0004X-\u0003\u0002;\t\t9\u0011\t\\5bg\u0016\u001c\b\u0002\u0003\u001f*\u0005#\u0005\u000b\u0011B\u001b\u0002\tA\u0014X\r\t\u0005\t}%\u0012)\u001a!C\u0001\u007f\u0005\u00191/_7\u0016\u0003\u0001\u0003\"AN!\n\u0005\tK$AB*z[\n|G\u000e\u0003\u0005ES\tE\t\u0015!\u0003A\u0003\u0011\u0019\u00180\u001c\u0011\t\u0011\u0019K#Q3A\u0005\u0002Q\n!\u0001\u001d;\t\u0011!K#\u0011#Q\u0001\nU\n1\u0001\u001d;!\u0011!Q\u0015F!f\u0001\n\u0003Y\u0015\u0001\u0002;sK\u0016,\u0012\u0001\u0014\t\u0003m5K!AT\u001d\u0003\tQ\u0013X-\u001a\u0005\t!&\u0012\t\u0012)A\u0005\u0019\u0006)AO]3fA!)!+\u000bC\u0001'\u00061A(\u001b8jiz\"R\u0001V+W/b\u0003\"AN\u0015\t\u000bM\n\u0006\u0019A\u001b\t\u000by\n\u0006\u0019\u0001!\t\u000b\u0019\u000b\u0006\u0019A\u001b\t\u000b)\u000b\u0006\u0019\u0001'\t\u000fiK\u0013\u0011!C\u00017\u0006!1m\u001c9z)\u0015!F,\u00180`\u0011\u001d\u0019\u0014\f%AA\u0002UBqAP-\u0011\u0002\u0003\u0007\u0001\tC\u0004G3B\u0005\t\u0019A\u001b\t\u000f)K\u0006\u0013!a\u0001\u0019\"9\u0011-KI\u0001\n\u0003\u0011\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0002G*\u0012Q\u0007Z\u0016\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u001b\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mO\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000f9L\u0013\u0013!C\u0001_\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00019+\u0005\u0001#\u0007b\u0002:*#\u0003%\tAY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0011\u001d!\u0018&%A\u0005\u0002U\fabY8qs\u0012\"WMZ1vYR$C'F\u0001wU\taE\rC\u0004yS\u0005\u0005I\u0011I=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Q\bcA>\u0002\u00025\tAP\u0003\u0002~}\u0006!A.\u00198h\u0015\u0005y\u0018\u0001\u00026bm\u0006L1!a\u0001}\u0005\u0019\u0019FO]5oO\"I\u0011qA\u0015\u0002\u0002\u0013\u0005\u0011\u0011B\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0017\u00012!DA\u0007\u0013\r\ty\u0001\u0003\u0002\u0004\u0013:$\b\"CA\nS\u0005\u0005I\u0011AA\u000b\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0006\u0002\u001eA\u0019Q\"!\u0007\n\u0007\u0005m\u0001BA\u0002B]fD!\"a\b\u0002\u0012\u0005\u0005\t\u0019AA\u0006\u0003\rAH%\r\u0005\n\u0003GI\u0013\u0011!C!\u0003K\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003O\u0001b!!\u000b\u00020\u0005]QBAA\u0016\u0015\r\ti\u0003C\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0019\u0003W\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0005\n\u0003kI\u0013\u0011!C\u0001\u0003o\t\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003s\ty\u0004E\u0002\u000e\u0003wI1!!\u0010\t\u0005\u001d\u0011un\u001c7fC:D!\"a\b\u00024\u0005\u0005\t\u0019AA\f\u0011%\t\u0019%KA\u0001\n\u0003\n)%\u0001\u0005iCND7i\u001c3f)\t\tY\u0001C\u0005\u0002J%\n\t\u0011\"\u0011\u0002L\u0005AAo\\*ue&tw\rF\u0001{\u0011%\ty%KA\u0001\n\u0003\n\t&\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003s\t\u0019\u0006\u0003\u0006\u0002 \u00055\u0013\u0011!a\u0001\u0003/9\u0011\"a\u0016\u0001\u0003\u0003E\t!!\u0017\u0002#%k\u0007\u000f\\5dSR\u001c\u0015M\u001c3jI\u0006$X\rE\u00027\u000372\u0001B\u000b\u0001\u0002\u0002#\u0005\u0011QL\n\u0006\u00037\nyf\f\t\n\u0003C\n9'\u000e!6\u0019Rk!!a\u0019\u000b\u0007\u0005\u0015\u0004\"A\u0004sk:$\u0018.\\3\n\t\u0005%\u00141\r\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004b\u0002*\u0002\\\u0011\u0005\u0011Q\u000e\u000b\u0003\u00033B!\"!\u0013\u0002\\\u0005\u0005IQIA&\u0011)\t\u0019(a\u0017\u0002\u0002\u0013\u0005\u0015QO\u0001\u0006CB\u0004H.\u001f\u000b\n)\u0006]\u0014\u0011PA>\u0003{BaaMA9\u0001\u0004)\u0004B\u0002 \u0002r\u0001\u0007\u0001\t\u0003\u0004G\u0003c\u0002\r!\u000e\u0005\u0007\u0015\u0006E\u0004\u0019\u0001'\t\u0015\u0005\u0005\u00151LA\u0001\n\u0003\u000b\u0019)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u0015\u0011\u0013\t\u0006\u001b\u0005\u001d\u00151R\u0005\u0004\u0003\u0013C!AB(qi&|g\u000eE\u0004\u000e\u0003\u001b+\u0004)\u000e'\n\u0007\u0005=\u0005B\u0001\u0004UkBdW\r\u000e\u0005\n\u0003'\u000by(!AA\u0002Q\u000b1\u0001\u001f\u00131\u0011\u001d\t9\n\u0001D\u0001\u00033\u000bQb\u001c9f]&k\u0007\u000f\\5dSR\u001cXCAAN!\ry\"\u0005\u0016\u0005\b\u0003?\u0003a\u0011AAM\u0003I)gn\u00197pg&tw-S7qY&\u001c\u0017\u000e^:")
public interface Context
extends scala.reflect.macros.blackbox.Context {
    public List<Context> openMacros();

    public List<Context> enclosingMacros();

    public Context$ImplicitCandidate$ ImplicitCandidate();

    public List<ImplicitCandidate> openImplicits();

    public List<ImplicitCandidate> enclosingImplicits();

    public class ImplicitCandidate
    implements Product,
    Serializable {
        private final Types.TypeApi pre;
        private final Symbols.SymbolApi sym;
        private final Types.TypeApi pt;
        private final Trees.TreeApi tree;
        public final /* synthetic */ Context $outer;

        public Types.TypeApi pre() {
            return this.pre;
        }

        public Symbols.SymbolApi sym() {
            return this.sym;
        }

        public Types.TypeApi pt() {
            return this.pt;
        }

        public Trees.TreeApi tree() {
            return this.tree;
        }

        public ImplicitCandidate copy(Types.TypeApi pre, Symbols.SymbolApi sym, Types.TypeApi pt, Trees.TreeApi tree) {
            return new ImplicitCandidate(this.scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer(), pre, sym, pt, tree);
        }

        public Types.TypeApi copy$default$1() {
            return this.pre();
        }

        public Symbols.SymbolApi copy$default$2() {
            return this.sym();
        }

        public Types.TypeApi copy$default$3() {
            return this.pt();
        }

        public Trees.TreeApi copy$default$4() {
            return this.tree();
        }

        @Override
        public String productPrefix() {
            return "ImplicitCandidate";
        }

        @Override
        public int productArity() {
            return 4;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 3: {
                    object = this.tree();
                    break;
                }
                case 2: {
                    object = this.pt();
                    break;
                }
                case 1: {
                    object = this.sym();
                    break;
                }
                case 0: {
                    object = this.pre();
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
            return x$1 instanceof ImplicitCandidate;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof ImplicitCandidate)) return false;
            if (((ImplicitCandidate)x$1).scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer() != this.scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            ImplicitCandidate implicitCandidate = (ImplicitCandidate)x$1;
            Types.TypeApi typeApi = this.pre();
            Types.TypeApi typeApi2 = implicitCandidate.pre();
            if (typeApi == null) {
                if (typeApi2 != null) {
                    return false;
                }
            } else if (!typeApi.equals(typeApi2)) return false;
            Symbols.SymbolApi symbolApi = this.sym();
            Symbols.SymbolApi symbolApi2 = implicitCandidate.sym();
            if (symbolApi == null) {
                if (symbolApi2 != null) {
                    return false;
                }
            } else if (!symbolApi.equals(symbolApi2)) return false;
            Types.TypeApi typeApi3 = this.pt();
            Types.TypeApi typeApi4 = implicitCandidate.pt();
            if (typeApi3 == null) {
                if (typeApi4 != null) {
                    return false;
                }
            } else if (!typeApi3.equals(typeApi4)) return false;
            Trees.TreeApi treeApi = this.tree();
            Trees.TreeApi treeApi2 = implicitCandidate.tree();
            if (treeApi == null) {
                if (treeApi2 != null) {
                    return false;
                }
            } else if (!treeApi.equals(treeApi2)) return false;
            if (!implicitCandidate.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ Context scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer() {
            return this.$outer;
        }

        public ImplicitCandidate(Context $outer, Types.TypeApi pre, Symbols.SymbolApi sym, Types.TypeApi pt, Trees.TreeApi tree) {
            this.pre = pre;
            this.sym = sym;
            this.pt = pt;
            this.tree = tree;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

