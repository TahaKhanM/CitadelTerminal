/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.internal.StdCreators$FixedMirrorTreeCreator$;
import scala.reflect.internal.StdCreators$FixedMirrorTypeCreator$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\tma!C\u0001\u0003!\u0003\r\t!\u0003B\n\u0005-\u0019F\u000fZ\"sK\u0006$xN]:\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0001\"a\u0003\n\n\u0005M1!\u0001B+oSR4A!\u0006\u0001A-\t1b)\u001b=fI6K'O]8s)J,Wm\u0011:fCR|'o\u0005\u0003\u0015/u\u0001\u0003C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u0005\u0003\r\t\u0007/[\u0005\u00039e\u00111\u0002\u0016:fK\u000e\u0013X-\u0019;peB\u00111BH\u0005\u0003?\u0019\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\fC%\u0011!E\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tIQ\u0011)\u001a!C\u0001K\u00051Q.\u001b:s_J,\u0012A\n\t\u00041\u001dJ\u0013B\u0001\u0015\u001a\u0005\u0019i\u0015N\u001d:pe6\t\u0001\u0001\u0003\u0005,)\tE\t\u0015!\u0003'\u0003\u001di\u0017N\u001d:pe\u0002B\u0001\"\f\u000b\u0003\u0016\u0004%\tAL\u0001\u0005iJ,W-F\u00010!\tI\u0003'\u0003\u00022e\t!AK]3f\u0013\t\u0019$AA\u0003Ue\u0016,7\u000f\u0003\u00056)\tE\t\u0015!\u00030\u0003\u0015!(/Z3!\u0011\u00159D\u0003\"\u00019\u0003\u0019a\u0014N\\5u}Q\u0019\u0011HO\u001e\u0011\u0005%\"\u0002\"\u0002\u00137\u0001\u00041\u0003\"B\u00177\u0001\u0004y\u0003\"B\u001f\u0015\t\u0003q\u0014!B1qa2LXCA D)\t\u0001E\u000b\u0005\u0002B%B\u0011!i\u0011\u0007\u0001\t\u0015!EH1\u0001F\u0005\u0005)\u0016C\u0001$J!\tYq)\u0003\u0002I\r\t9aj\u001c;iS:<'c\u0001&M\u001f\u001a!1\n\u0006\u0001J\u00051a$/\u001a4j]\u0016lWM\u001c;?!\tAR*\u0003\u0002O3\tAQK\\5wKJ\u001cX\r\u0005\u0002\f!&\u0011\u0011K\u0002\u0002\n'&tw\r\\3u_:L!!M*\n\u0005MJ\u0002\"B+=\u0001\u00041\u0016!A7\u0011\u0007a9\u0013\tC\u0004Y)\u0005\u0005I\u0011A-\u0002\t\r|\u0007/\u001f\u000b\u0004si[\u0006b\u0002\u0013X!\u0003\u0005\rA\n\u0005\b[]\u0003\n\u00111\u00010\u0011\u001diF#%A\u0005\u0002y\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001`U\t1\u0003mK\u0001b!\t\u0011w-D\u0001d\u0015\t!W-A\u0005v]\u000eDWmY6fI*\u0011aMB\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00015d\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\bUR\t\n\u0011\"\u0001l\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u001c\u0016\u0003_\u0001DqA\u001c\u000b\u0002\u0002\u0013\u0005s.A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002aB\u0011\u0011O^\u0007\u0002e*\u00111\u000f^\u0001\u0005Y\u0006twMC\u0001v\u0003\u0011Q\u0017M^1\n\u0005]\u0014(AB*ue&tw\rC\u0004z)\u0005\u0005I\u0011\u0001>\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003m\u0004\"a\u0003?\n\u0005u4!aA%oi\"Aq\u0010FA\u0001\n\u0003\t\t!\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\r\u0011\u0011\u0002\t\u0004\u0017\u0005\u0015\u0011bAA\u0004\r\t\u0019\u0011I\\=\t\u0011\u0005-a0!AA\u0002m\f1\u0001\u001f\u00132\u0011%\ty\u0001FA\u0001\n\u0003\n\t\"A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0002\u0005\u0004\u0002\u0016\u0005m\u00111A\u0007\u0003\u0003/Q1!!\u0007\u0007\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003;\t9B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011%\t\t\u0003FA\u0001\n\u0003\t\u0019#\u0001\u0005dC:,\u0015/^1m)\u0011\t)#a\u000b\u0011\u0007-\t9#C\u0002\u0002*\u0019\u0011qAQ8pY\u0016\fg\u000e\u0003\u0006\u0002\f\u0005}\u0011\u0011!a\u0001\u0003\u0007A\u0011\"a\f\u0015\u0003\u0003%\t%!\r\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012a\u001f\u0005\n\u0003k!\u0012\u0011!C!\u0003o\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002a\"I\u00111\b\u000b\u0002\u0002\u0013\u0005\u0013QH\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0012q\b\u0005\u000b\u0003\u0017\tI$!AA\u0002\u0005\rq!CA\"\u0001\u0005\u0005\t\u0012AA#\u0003Y1\u0015\u000e_3e\u001b&\u0014(o\u001c:Ue\u0016,7I]3bi>\u0014\bcA\u0015\u0002H\u0019AQ\u0003AA\u0001\u0012\u0003\tIeE\u0003\u0002H\u0005-\u0003\u0005E\u0004\u0002N\u0005MceL\u001d\u000e\u0005\u0005=#bAA)\r\u00059!/\u001e8uS6,\u0017\u0002BA+\u0003\u001f\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83\u0011\u001d9\u0014q\tC\u0001\u00033\"\"!!\u0012\t\u0015\u0005U\u0012qIA\u0001\n\u000b\n9\u0004C\u0005>\u0003\u000f\n\t\u0011\"!\u0002`Q)\u0011(!\u0019\u0002d!1A%!\u0018A\u0002\u0019Ba!LA/\u0001\u0004y\u0003BCA4\u0003\u000f\n\t\u0011\"!\u0002j\u00059QO\\1qa2LH\u0003BA6\u0003o\u0002RaCA7\u0003cJ1!a\u001c\u0007\u0005\u0019y\u0005\u000f^5p]B)1\"a\u001d'_%\u0019\u0011Q\u000f\u0004\u0003\rQ+\b\u000f\\33\u0011%\tI(!\u001a\u0002\u0002\u0003\u0007\u0011(A\u0002yIA2a!! \u0001\u0001\u0006}$A\u0006$jq\u0016$W*\u001b:s_J$\u0016\u0010]3De\u0016\fGo\u001c:\u0014\r\u0005m\u0014\u0011Q\u000f!!\rA\u00121Q\u0005\u0004\u0003\u000bK\"a\u0003+za\u0016\u001c%/Z1u_JD\u0011\u0002JA>\u0005+\u0007I\u0011A\u0013\t\u0013-\nYH!E!\u0002\u00131\u0003bCAG\u0003w\u0012)\u001a!C\u0001\u0003\u001f\u000b1\u0001\u001e9f+\t\t\t\nE\u0002*\u0003'KA!!&\u0002\u0018\n!A+\u001f9f\u0013\r\tIJ\u0001\u0002\u0006)f\u0004Xm\u001d\u0005\f\u0003;\u000bYH!E!\u0002\u0013\t\t*\u0001\u0003ua\u0016\u0004\u0003bB\u001c\u0002|\u0011\u0005\u0011\u0011\u0015\u000b\u0007\u0003G\u000b)+a*\u0011\u0007%\nY\b\u0003\u0004%\u0003?\u0003\rA\n\u0005\t\u0003\u001b\u000by\n1\u0001\u0002\u0012\"9Q(a\u001f\u0005\u0002\u0005-V\u0003BAW\u0003g#B!a,\u0002@B!\u0011\u0011WA^!\r\u0011\u00151\u0017\u0003\b\t\u0006%&\u0019AA[#\r1\u0015q\u0017\n\u0005\u0003scuJ\u0002\u0004L\u0003w\u0002\u0011qW\u0005\u0005\u0003+\u000bi,C\u0002\u0002\u001afAq!VAU\u0001\u0004\t\t\r\u0005\u0003\u0019O\u0005E\u0006\"\u0003-\u0002|\u0005\u0005I\u0011AAc)\u0019\t\u0019+a2\u0002J\"AA%a1\u0011\u0002\u0003\u0007a\u0005\u0003\u0006\u0002\u000e\u0006\r\u0007\u0013!a\u0001\u0003#C\u0001\"XA>#\u0003%\tA\u0018\u0005\nU\u0006m\u0014\u0013!C\u0001\u0003\u001f,\"!!5+\u0007\u0005E\u0005\r\u0003\u0005o\u0003w\n\t\u0011\"\u0011p\u0011!I\u00181PA\u0001\n\u0003Q\b\"C@\u0002|\u0005\u0005I\u0011AAm)\u0011\t\u0019!a7\t\u0013\u0005-\u0011q[A\u0001\u0002\u0004Y\bBCA\b\u0003w\n\t\u0011\"\u0011\u0002\u0012!Q\u0011\u0011EA>\u0003\u0003%\t!!9\u0015\t\u0005\u0015\u00121\u001d\u0005\u000b\u0003\u0017\ty.!AA\u0002\u0005\r\u0001BCA\u0018\u0003w\n\t\u0011\"\u0011\u00022!Q\u0011QGA>\u0003\u0003%\t%a\u000e\t\u0015\u0005m\u00121PA\u0001\n\u0003\nY\u000f\u0006\u0003\u0002&\u00055\bBCA\u0006\u0003S\f\t\u00111\u0001\u0002\u0004\u001dI\u0011\u0011\u001f\u0001\u0002\u0002#\u0005\u00111_\u0001\u0017\r&DX\rZ'jeJ|'\u000fV=qK\u000e\u0013X-\u0019;peB\u0019\u0011&!>\u0007\u0013\u0005u\u0004!!A\t\u0002\u0005]8#BA{\u0003s\u0004\u0003#CA'\u0003'2\u0013\u0011SAR\u0011\u001d9\u0014Q\u001fC\u0001\u0003{$\"!a=\t\u0015\u0005U\u0012Q_A\u0001\n\u000b\n9\u0004C\u0005>\u0003k\f\t\u0011\"!\u0003\u0004Q1\u00111\u0015B\u0003\u0005\u000fAa\u0001\nB\u0001\u0001\u00041\u0003\u0002CAG\u0005\u0003\u0001\r!!%\t\u0015\u0005\u001d\u0014Q_A\u0001\n\u0003\u0013Y\u0001\u0006\u0003\u0003\u000e\tE\u0001#B\u0006\u0002n\t=\u0001CB\u0006\u0002t\u0019\n\t\n\u0003\u0006\u0002z\t%\u0011\u0011!a\u0001\u0003G\u0003BA!\u0006\u0003\u00185\t!!C\u0002\u0003\u001a\t\u00111bU=nE>dG+\u00192mK\u0002")
public interface StdCreators {
    public StdCreators$FixedMirrorTreeCreator$ FixedMirrorTreeCreator();

    public StdCreators$FixedMirrorTypeCreator$ FixedMirrorTypeCreator();

    public class FixedMirrorTreeCreator
    extends TreeCreator
    implements Product {
        private final Mirror<SymbolTable> mirror;
        private final Trees.Tree tree;
        public final /* synthetic */ SymbolTable $outer;

        public Mirror<SymbolTable> mirror() {
            return this.mirror;
        }

        public Trees.Tree tree() {
            return this.tree;
        }

        @Override
        public <U extends Universe> Trees.TreeApi apply(Mirror<U> m) {
            if (m == this.mirror()) {
                return this.tree();
            }
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Expr defined in ", " cannot be migrated to other mirrors."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.mirror()})));
        }

        public FixedMirrorTreeCreator copy(Mirror<SymbolTable> mirror, Trees.Tree tree) {
            return new FixedMirrorTreeCreator(this.scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer(), mirror, tree);
        }

        public Mirror<SymbolTable> copy$default$1() {
            return this.mirror();
        }

        public Trees.Tree copy$default$2() {
            return this.tree();
        }

        @Override
        public String productPrefix() {
            return "FixedMirrorTreeCreator";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    object = this.tree();
                    break;
                }
                case 0: {
                    object = this.mirror();
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
            return x$1 instanceof FixedMirrorTreeCreator;
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
            if (!(x$1 instanceof FixedMirrorTreeCreator)) return false;
            if (((FixedMirrorTreeCreator)x$1).scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer() != this.scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            FixedMirrorTreeCreator fixedMirrorTreeCreator = (FixedMirrorTreeCreator)x$1;
            Mirror<SymbolTable> mirror = this.mirror();
            Mirror<SymbolTable> mirror2 = fixedMirrorTreeCreator.mirror();
            if (mirror == null) {
                if (mirror2 != null) {
                    return false;
                }
            } else if (!mirror.equals(mirror2)) return false;
            Trees.Tree tree = this.tree();
            Trees.Tree tree2 = fixedMirrorTreeCreator.tree();
            if (tree == null) {
                if (tree2 != null) {
                    return false;
                }
            } else if (!((Object)tree).equals(tree2)) return false;
            if (!fixedMirrorTreeCreator.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$StdCreators$FixedMirrorTreeCreator$$$outer() {
            return this.$outer;
        }

        public FixedMirrorTreeCreator(SymbolTable $outer, Mirror<SymbolTable> mirror, Trees.Tree tree) {
            this.mirror = mirror;
            this.tree = tree;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }

    public class FixedMirrorTypeCreator
    extends TypeCreator
    implements Product {
        private final Mirror<SymbolTable> mirror;
        private final Types.Type tpe;
        public final /* synthetic */ SymbolTable $outer;

        public Mirror<SymbolTable> mirror() {
            return this.mirror;
        }

        public Types.Type tpe() {
            return this.tpe;
        }

        @Override
        public <U extends Universe> Types.TypeApi apply(Mirror<U> m) {
            if (m == this.mirror()) {
                return this.tpe();
            }
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Type tag defined in ", " cannot be migrated to other mirrors."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.mirror()})));
        }

        public FixedMirrorTypeCreator copy(Mirror<SymbolTable> mirror, Types.Type tpe) {
            return new FixedMirrorTypeCreator(this.scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer(), mirror, tpe);
        }

        public Mirror<SymbolTable> copy$default$1() {
            return this.mirror();
        }

        public Types.Type copy$default$2() {
            return this.tpe();
        }

        @Override
        public String productPrefix() {
            return "FixedMirrorTypeCreator";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            Object object;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    object = this.tpe();
                    break;
                }
                case 0: {
                    object = this.mirror();
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
            return x$1 instanceof FixedMirrorTypeCreator;
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
            if (!(x$1 instanceof FixedMirrorTypeCreator)) return false;
            if (((FixedMirrorTypeCreator)x$1).scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer() != this.scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            FixedMirrorTypeCreator fixedMirrorTypeCreator = (FixedMirrorTypeCreator)x$1;
            Mirror<SymbolTable> mirror = this.mirror();
            Mirror<SymbolTable> mirror2 = fixedMirrorTypeCreator.mirror();
            if (mirror == null) {
                if (mirror2 != null) {
                    return false;
                }
            } else if (!mirror.equals(mirror2)) return false;
            Types.Type type = this.tpe();
            Types.Type type2 = fixedMirrorTypeCreator.tpe();
            if (type == null) {
                if (type2 != null) {
                    return false;
                }
            } else if (!type.equals(type2)) return false;
            if (!fixedMirrorTypeCreator.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$StdCreators$FixedMirrorTypeCreator$$$outer() {
            return this.$outer;
        }

        public FixedMirrorTypeCreator(SymbolTable $outer, Mirror<SymbolTable> mirror, Types.Type tpe) {
            this.mirror = mirror;
            this.tpe = tpe;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

