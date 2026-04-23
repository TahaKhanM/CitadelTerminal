/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Trees;
import scala.reflect.macros.Enclosures$EnclosureException$;
import scala.reflect.macros.Universe;
import scala.reflect.macros.blackbox.Context;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\t-c\u0001C\u0001\u0003!\u0003\r\t!C\u0014\u0003\u0015\u0015s7\r\\8tkJ,7O\u0003\u0002\u0004\t\u00051Q.Y2s_NT!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006+\u00011\tAF\u0001\u0011[\u0006\u001c'o\\!qa2L7-\u0019;j_:,\u0012a\u0006\t\u00031ei\u0011\u0001A\u0005\u00035m\u0011A\u0001\u0016:fK&\u0011AD\u0001\u0002\b\u00032L\u0017m]3t\u0011\u0015q\u0002A\"\u0001 \u0003=)gn\u00197pg&tw-T1de>\u001cX#\u0001\u0011\u0011\u0007\u0005\"sE\u0004\u0002\fE%\u00111EB\u0001\ba\u0006\u001c7.Y4f\u0013\t)cE\u0001\u0003MSN$(BA\u0012\u0007!\tA3&D\u0001*\u0015\tQ#!\u0001\u0005cY\u0006\u001c7NY8y\u0013\ta\u0013FA\u0004D_:$X\r\u001f;\t\u000b9\u0002a\u0011A\u0018\u0002#\u0015t7\r\\8tS:<\u0007k\\:ji&|g.F\u00011!\tA\u0012'\u0003\u000237\tA\u0001k\\:ji&|g\u000eC\u00035\u0001\u0019\u0005a#A\bf]\u000edwn]5oO6+G\u000f[8eQ\u0011\u0019d'O\u001e\u0011\u0005-9\u0014B\u0001\u001d\u0007\u0005)!W\r\u001d:fG\u0006$X\rZ\u0011\u0002u\u0005A6ML3oG2|7/\u001b8h)J,W-L:us2,\u0007%\u0011)Jg\u0002\n'/\u001a\u0011o_^\u0004C-\u001a9sK\u000e\fG/\u001a3<A\r|gn];mi\u0002\"\b.\u001a\u0011tG\u0006d\u0017\rZ8dA\u0019|'\u000fI7pe\u0016\u0004\u0013N\u001c4pe6\fG/[8oC\u0005a\u0014A\u0002\u001a/cEr\u0003\u0007C\u0003?\u0001\u0019\u0005a#\u0001\bf]\u000edwn]5oO\u000ec\u0017m]:)\tu2\u0014h\u000f\u0005\u0006\u0003\u00021\tAQ\u0001\rK:\u001cGn\\:j]\u001e$UMZ\u000b\u0002\u0007B\u0011Ai\u0012\b\u00031\u0015K!AR\u0016\u0002\u0011Ut\u0017N^3sg\u0016L!\u0001S%\u0003\r\u0011+g\rR3g\u0013\tQ5JA\u0003Ue\u0016,7O\u0003\u0002M\t\u0005\u0019\u0011\r]5)\t\u00013\u0014h\u000f\u0005\u0006\u001f\u00021\t\u0001U\u0001\u0012K:\u001cGn\\:j]\u001e$V-\u001c9mCR,W#A)\u0011\u0005\u0011\u0013\u0016BA*J\u0005!!V-\u001c9mCR,\u0007\u0006\u0002(7smBQA\u0016\u0001\u0007\u0002]\u000bQ\"\u001a8dY>\u001c\u0018N\\4J[BdW#\u0001-\u0011\u0005\u0011K\u0016B\u0001.J\u0005\u001dIU\u000e\u001d7EK\u001aDC!\u0016\u001c:w!)Q\f\u0001D\u0001=\u0006\u0001RM\\2m_NLgn\u001a)bG.\fw-Z\u000b\u0002?B\u0011A\tY\u0005\u0003C&\u0013!\u0002U1dW\u0006<W\rR3gQ\u0011af'O\u001e\t\u000b\u0011\u0004a\u0011A3\u0002\u001b\u0015t7\r\\8tS:<WK\\5u+\u00051\u0007C\u0001\rh\u0013\tA7DA\bD_6\u0004\u0018\u000e\\1uS>tWK\\5uQ\u0011\u0019g'O\u001e\t\u000b-\u0004a\u0011\u00017\u0002\u0019\u0015t7\r\\8tS:<'+\u001e8\u0016\u00035\u0004\"\u0001\u00078\n\u0005=\\\"a\u0001*v]\"\"!NN\u001d<\r\u0011\u0011\b\u0001Q:\u0003%\u0015s7\r\\8tkJ,W\t_2faRLwN\\\n\u0005cR<(\u0010\u0005\u0002\"k&\u0011aO\n\u0002\n\u000bb\u001cW\r\u001d;j_:\u0004\"a\u0003=\n\u0005e4!a\u0002)s_\u0012,8\r\u001e\t\u0003\u0017mL!\u0001 \u0004\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011y\f(Q3A\u0005\u0002}\f\u0001\"\u001a=qK\u000e$X\rZ\u000b\u0003\u0003\u0003\u0001D!a\u0001\u0002\u0016A1\u0011QAA\u0006\u0003#q1aCA\u0004\u0013\r\tIAB\u0001\u0007!J,G-\u001a4\n\t\u00055\u0011q\u0002\u0002\u0006\u00072\f7o\u001d\u0006\u0004\u0003\u00131\u0001\u0003BA\n\u0003+a\u0001\u0001\u0002\u0007\u0002\u0018\u0005e\u0011\u0011!A\u0001\u0006\u0003\t)CA\u0002`IEB!\"a\u0007r\u0005#\u0005\u000b\u0011BA\u000f\u0003%)\u0007\u0010]3di\u0016$\u0007\u0005\r\u0003\u0002 \u0005\r\u0002CBA\u0003\u0003\u0017\t\t\u0003\u0005\u0003\u0002\u0014\u0005\rB\u0001DA\f\u00033\t\t\u0011!A\u0003\u0002\u0005\u0015\u0012\u0003BA\u0014\u0003[\u00012aCA\u0015\u0013\r\tYC\u0002\u0002\b\u001d>$\b.\u001b8h!\rY\u0011qF\u0005\u0004\u0003c1!aA!os\"Q\u0011QG9\u0003\u0016\u0004%\t!a\u000e\u0002\u001d\u0015t7\r\\8tS:<GK]3fgV\u0011\u0011\u0011\b\t\u0004C\u0011:\u0002BCA\u001fc\nE\t\u0015!\u0003\u0002:\u0005yQM\\2m_NLgn\u001a+sK\u0016\u001c\b\u0005C\u0004\u0002BE$\t!a\u0011\u0002\rqJg.\u001b;?)\u0019\t)%a\u0012\u0002RA\u0011\u0001$\u001d\u0005\b}\u0006}\u0002\u0019AA%a\u0011\tY%a\u0014\u0011\r\u0005\u0015\u00111BA'!\u0011\t\u0019\"a\u0014\u0005\u0019\u0005]\u0011qIA\u0001\u0002\u0003\u0015\t!!\n\t\u0011\u0005U\u0012q\ba\u0001\u0003sA\u0011\"!\u0016r\u0003\u0003%\t!a\u0016\u0002\t\r|\u0007/\u001f\u000b\u0007\u0003\u000b\nI&a\u0017\t\u0013y\f\u0019\u0006%AA\u0002\u0005%\u0003BCA\u001b\u0003'\u0002\n\u00111\u0001\u0002:!I\u0011qL9\u0012\u0002\u0013\u0005\u0011\u0011M\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019\u0007\r\u0003\u0002f\u0005U\u0004CBA4\u0003c\n\u0019(\u0004\u0002\u0002j)!\u00111NA7\u0003\u0011a\u0017M\\4\u000b\u0005\u0005=\u0014\u0001\u00026bm\u0006LA!!\u0004\u0002jA!\u00111CA;\t1\t9\"!\u0018\u0002\u0002\u0003\u0005)\u0011AA\u0013\u0011%\tI(]I\u0001\n\u0003\tY(\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005u$\u0006BA\u001d\u0003\u007fZ#!!!\u0011\t\u0005\r\u0015QR\u0007\u0003\u0003\u000bSA!a\"\u0002\n\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u00173\u0011AC1o]>$\u0018\r^5p]&!\u0011qRAC\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\n\u0003'\u000b\u0018\u0011!C!\u0003+\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAL!\u0011\t9'!'\n\t\u0005m\u0015\u0011\u000e\u0002\u0007'R\u0014\u0018N\\4\t\u0013\u0005}\u0015/!A\u0005\u0002\u0005\u0005\u0016\u0001\u00049s_\u0012,8\r^!sSRLXCAAR!\rY\u0011QU\u0005\u0004\u0003O3!aA%oi\"I\u00111V9\u0002\u0002\u0013\u0005\u0011QV\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ti#a,\t\u0015\u0005E\u0016\u0011VA\u0001\u0002\u0004\t\u0019+A\u0002yIEB\u0011\"!.r\u0003\u0003%\t%a.\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!/\u0011\r\u0005m\u0016\u0011YA\u0017\u001b\t\tiLC\u0002\u0002@\u001a\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019-!0\u0003\u0011%#XM]1u_JD\u0011\"a2r\u0003\u0003%\t!!3\u0002\u0011\r\fg.R9vC2$B!a3\u0002RB\u00191\"!4\n\u0007\u0005=gAA\u0004C_>dW-\u00198\t\u0015\u0005E\u0016QYA\u0001\u0002\u0004\ti\u0003C\u0005\u0002VF\f\t\u0011\"\u0011\u0002X\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002$\"I\u00111\\9\u0002\u0002\u0013\u0005\u0013Q\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005-\u0017q\u001c\u0005\u000b\u0003c\u000bI.!AA\u0002\u00055\u0002\u0006B97sm:\u0011\"!:\u0001\u0003\u0003E\t!a:\u0002%\u0015s7\r\\8tkJ,W\t_2faRLwN\u001c\t\u00041\u0005%h\u0001\u0003:\u0001\u0003\u0003E\t!a;\u0014\u000b\u0005%\u0018Q\u001e>\u0011\u0015\u0005=\u0018Q_A}\u0003s\t)%\u0004\u0002\u0002r*\u0019\u00111\u001f\u0004\u0002\u000fI,h\u000e^5nK&!\u0011q_Ay\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u0019\u0005\u0003w\fy\u0010\u0005\u0004\u0002\u0006\u0005-\u0011Q \t\u0005\u0003'\ty\u0010\u0002\u0007\u0002\u0018\u0005%\u0018\u0011!A\u0001\u0006\u0003\t)\u0003\u0003\u0005\u0002B\u0005%H\u0011\u0001B\u0002)\t\t9\u000f\u0003\u0006\u0003\b\u0005%\u0018\u0011!C#\u0005\u0013\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003/C!B!\u0004\u0002j\u0006\u0005I\u0011\u0011B\b\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\t)E!\u0005\u0003\u001c!9aPa\u0003A\u0002\tM\u0001\u0007\u0002B\u000b\u00053\u0001b!!\u0002\u0002\f\t]\u0001\u0003BA\n\u00053!A\"a\u0006\u0003\u0012\u0005\u0005\t\u0011!B\u0001\u0003KA\u0001\"!\u000e\u0003\f\u0001\u0007\u0011\u0011\b\u0005\u000b\u0005?\tI/!A\u0005\u0002\n\u0005\u0012aB;oCB\u0004H.\u001f\u000b\u0005\u0005G\u0011)\u0005\r\u0003\u0003&\tU\u0002#B\u0006\u0003(\t-\u0012b\u0001B\u0015\r\t1q\n\u001d;j_:\u0004ra\u0003B\u0017\u0005c\u00119$C\u0002\u00030\u0019\u0011a\u0001V;qY\u0016\u0014\u0004CBA4\u0003c\u0012\u0019\u0004\u0005\u0003\u0002\u0014\tUB\u0001DA\f\u0005;\t\t\u0011!A\u0003\u0002\u0005\u0015\u0002C\u0002B\u001d\u0005\u007f\u0011\t%\u0004\u0002\u0003<)!!QHA_\u0003%IW.\\;uC\ndW-C\u0002&\u0005w\u00012\u0001\u0012B\"\u0013\tQ\u0012\n\u0003\u0006\u0003H\tu\u0011\u0011!a\u0001\u0003\u000b\n1\u0001\u001f\u00131Q\u0015\tION\u001d<\u0001")
public interface Enclosures {
    public Trees.TreeApi macroApplication();

    public List<Context> enclosingMacros();

    public Position enclosingPosition();

    public Trees.TreeApi enclosingMethod();

    public Trees.TreeApi enclosingClass();

    public Trees.DefDefApi enclosingDef();

    public Trees.TemplateApi enclosingTemplate();

    public Trees.ImplDefApi enclosingImpl();

    public Trees.PackageDefApi enclosingPackage();

    public Universe.CompilationUnitContextApi enclosingUnit();

    public Universe.RunContextApi enclosingRun();

    public Enclosures$EnclosureException$ EnclosureException();

    public class EnclosureException
    extends Exception
    implements Product,
    Serializable {
        private final Class<?> expected;
        private final List<Trees.TreeApi> enclosingTrees;
        public final /* synthetic */ Context $outer;

        public Class<?> expected() {
            return this.expected;
        }

        public List<Trees.TreeApi> enclosingTrees() {
            return this.enclosingTrees;
        }

        public EnclosureException copy(Class<?> expected, List<Trees.TreeApi> enclosingTrees) {
            return new EnclosureException(this.scala$reflect$macros$Enclosures$EnclosureException$$$outer(), expected, enclosingTrees);
        }

        public Class<?> copy$default$1() {
            return this.expected();
        }

        public List<Trees.TreeApi> copy$default$2() {
            return this.enclosingTrees();
        }

        @Override
        public String productPrefix() {
            return "EnclosureException";
        }

        @Override
        public int productArity() {
            return 2;
        }

        @Override
        public Object productElement(int x$1) {
            java.io.Serializable serializable;
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 1: {
                    serializable = this.enclosingTrees();
                    break;
                }
                case 0: {
                    serializable = this.expected();
                }
            }
            return serializable;
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof EnclosureException;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof EnclosureException)) return false;
            if (((EnclosureException)x$1).scala$reflect$macros$Enclosures$EnclosureException$$$outer() != this.scala$reflect$macros$Enclosures$EnclosureException$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            EnclosureException enclosureException = (EnclosureException)x$1;
            Class<?> clazz = this.expected();
            Class<?> clazz2 = enclosureException.expected();
            if (clazz == null) {
                if (clazz2 != null) {
                    return false;
                }
            } else if (!clazz.equals(clazz2)) return false;
            List<Trees.TreeApi> list2 = this.enclosingTrees();
            List<Trees.TreeApi> list3 = enclosureException.enclosingTrees();
            if (list2 == null) {
                if (list3 != null) {
                    return false;
                }
            } else if (!((Object)list2).equals(list3)) return false;
            if (!enclosureException.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ Context scala$reflect$macros$Enclosures$EnclosureException$$$outer() {
            return this.$outer;
        }

        public EnclosureException(Context $outer, Class<?> expected, List<Trees.TreeApi> enclosingTrees) {
            this.expected = expected;
            this.enclosingTrees = enclosingTrees;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Couldn't find a tree of type ", " among enclosing trees ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{expected, enclosingTrees})));
            Product$class.$init$(this);
        }
    }
}

