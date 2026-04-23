/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import java.io.ObjectStreamException;
import scala.Equals;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.StringOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs$Expr$;
import scala.reflect.api.Exprs$Expr$class;
import scala.reflect.api.Mirror;
import scala.reflect.api.Mirrors;
import scala.reflect.api.SerializedExpr;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.package$;

@ScalaSignature(bytes="\u0006\u0001\u0005\rh\u0001C\u0001\u0003!\u0003\r\t!\u0003\u001d\u0003\u000b\u0015C\bO]:\u000b\u0005\r!\u0011aA1qS*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LGOB\u0004\u0016\u0001A\u0005\u0019\u0011\u0001\f\u0003\t\u0015C\bO]\u000b\u0003/\u0001\u001bB\u0001\u0006\u0006\u00197A\u00111\"G\u0005\u00035\u0019\u0011a!R9vC2\u001c\bCA\u0006\u001d\u0013\tibA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0003\u0010)\u0011\u0005\u0001\u0003C\u0004!)\t\u0007i\u0011A\u0011\u0002\r5L'O]8s+\u0005\u0011\u0003CA\u0012%\u001b\u0005\u0001\u0011BA\u0013'\u0005\u0019i\u0015N\u001d:pe&\u0011qE\u0001\u0002\b\u001b&\u0014(o\u001c:t\u0011\u0015ICC\"\u0001+\u0003\tIg.\u0006\u0002,_Q\u0011AF\u0012\t\u0004[Qy\u0004C\u0001\u00180\u0019\u0001!Q\u0001\r\u0015C\u0002E\u0012\u0011!V\t\u0003eU\u0002\"aC\u001a\n\u0005Q2!a\u0002(pi\"Lgn\u001a\n\u0004mabd\u0001B\u001c\u0015\u0001U\u0012A\u0002\u0010:fM&tW-\\3oiz\u0002\"!\u000f\u001e\u000e\u0003\tI!a\u000f\u0002\u0003\u0011Us\u0017N^3sg\u0016\u0004\"aC\u001f\n\u0005y2!!C*j]\u001edW\r^8o!\tq\u0003\t\u0002\u0004B)\u0011\u0015\rA\u0011\u0002\u0002)F\u0011!g\u0011\t\u0003\u0017\u0011K!!\u0012\u0004\u0003\u0007\u0005s\u0017\u0010C\u0003HQ\u0001\u0007\u0001*A\u0006pi\",'/T5se>\u0014\bcA\u001dJ[%\u0011QE\u0001\u0005\u0006\u0017R1\t\u0001T\u0001\u0005iJ,W-F\u0001N!\t\u0019c*\u0003\u0002P!\n!AK]3f\u0013\t\t&AA\u0003Ue\u0016,7\u000fC\u0003T)\u0019\u0005A+\u0001\u0006ti\u0006$\u0018n\u0019+za\u0016,\u0012!\u0016\t\u0003GYK!a\u0016-\u0003\tQK\b/Z\u0005\u00033\n\u0011Q\u0001V=qKNDQa\u0017\u000b\u0007\u0002Q\u000b!\"Y2uk\u0006dG+\u001f9f\u0011\u0015iFC\"\u0001_\u0003\u0019\u0019\b\u000f\\5dKV\tq\bK\u0002]A\u001a\u0004\"!\u00193\u000e\u0003\tT!a\u0019\u0004\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002fE\ny1m\\7qS2,G+[7f\u001f:d\u00170I\u0001h\u0003=\u001a\b\u000f\\5dK\u0002jWo\u001d;!E\u0016\u0004SM\\2m_N,G\rI<ji\"Lg\u000eI1!e\u0016Lg-\u001f\u0011|{\u0002\u0012Gn\\2l\u0011\u001dIGC1A\u0007\u0002y\u000bQA^1mk\u0016D3\u0001\u001b1lC\u0005a\u0017aP2b]:|G\u000fI;tK\u00022\u0018\r\\;fA\u0015D8-\u001a9uA\u0019|'\u000fI:jO:\fG/\u001e:fg\u0002zg\rI7bGJ|\u0007%[7qY\u0016lWM\u001c;bi&|gn\u001d\u0005\u0006]R!\te\\\u0001\tG\u0006tW)];bYR\u0011\u0001o\u001d\t\u0003\u0017EL!A\u001d\u0004\u0003\u000f\t{w\u000e\\3b]\")A/\u001ca\u0001\u0007\u0006\t\u0001\u0010C\u0003w)\u0011\u0005s/\u0001\u0004fcV\fGn\u001d\u000b\u0003abDQ\u0001^;A\u0002\rCQA\u001f\u000b\u0005Bm\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002yB\u00111\"`\u0005\u0003}\u001a\u00111!\u00138u\u0011\u001d\t\t\u0001\u0006C!\u0003\u0007\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u000b\u0001B!a\u0002\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0005\u0003\u0017\ti!\u0001\u0003mC:<'BAA\b\u0003\u0011Q\u0017M^1\n\t\u0005M\u0011\u0011\u0002\u0002\u0007'R\u0014\u0018N\\4\b\u000f\u0005]\u0001\u0001#\u0001\u0002\u001a\u0005!Q\t\u001f9s!\r\u0019\u00131\u0004\u0004\u0007+\u0001A\t!!\b\u0014\t\u0005m!b\u0007\u0005\t\u0003C\tY\u0002\"\u0001\u0002$\u00051A(\u001b8jiz\"\"!!\u0007\t\u0011\u0005\u001d\u00121\u0004C\u0001\u0003S\tQ!\u00199qYf,B!a\u000b\u00024Q1\u0011QFA\"\u0003\u000f\"B!a\f\u00026A!1\u0005FA\u0019!\rq\u00131\u0007\u0003\u0007\u0003\u0006\u0015\"\u0019\u0001\"\t\u0015\u0005]\u0012QEA\u0001\u0002\b\tI$\u0001\u0006fm&$WM\\2fIE\u0002RaIA\u001e\u0003cIA!!\u0010\u0002@\tYq+Z1l)f\u0004X\rV1h\u0013\r\t\tE\u0001\u0002\t)f\u0004X\rV1hg\"9\u0001%!\nA\u0002\u0005\u0015\u0003cA\u001dJG!A\u0011\u0011JA\u0013\u0001\u0004\tY%A\u0003ue\u0016,7\rE\u0002:\u0003\u001bJ1!a\u0014\u0003\u0005-!&/Z3De\u0016\fGo\u001c:\t\u0011\u0005M\u00131\u0004C\u0001\u0003+\nq!\u001e8baBd\u00170\u0006\u0003\u0002X\u0005\u001dD\u0003BA-\u0003?\u0002BaCA.\u001b&\u0019\u0011Q\f\u0004\u0003\r=\u0003H/[8o\u0011!\t\t'!\u0015A\u0002\u0005\r\u0014\u0001B3yaJ\u0004Ba\t\u000b\u0002fA\u0019a&a\u001a\u0005\r\u0005\u000b\tF1\u0001C\r\u0019\tY\u0007\u0001\u0003\u0002n\tAQ\t\u001f9s\u00136\u0004H.\u0006\u0003\u0002p\u0005U4#BA5\u0015\u0005E\u0004\u0003B\u0012\u0015\u0003g\u00022ALA;\t\u001d\t\u0015\u0011\u000eCC\u0002\tC\u0011\u0002IA5\u0005\u000b\u0007I\u0011A\u0011\t\u0015\u0005m\u0014\u0011\u000eB\u0001B\u0003%!%A\u0004nSJ\u0014xN\u001d\u0011\t\u0017\u0005%\u0013\u0011\u000eBC\u0002\u0013\u0005\u0011qP\u000b\u0003\u0003\u0017B1\"a!\u0002j\t\u0005\t\u0015!\u0003\u0002L\u00051AO]3fG\u0002B1\"a\"\u0002j\t\r\t\u0015a\u0003\u0002\n\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u000b\r\nY$a\u001d\t\u0011\u0005\u0005\u0012\u0011\u000eC\u0001\u0003\u001b#b!a$\u0002\u0016\u0006]E\u0003BAI\u0003'\u0003RaIA5\u0003gB\u0001\"a\"\u0002\f\u0002\u000f\u0011\u0011\u0012\u0005\u0007A\u0005-\u0005\u0019\u0001\u0012\t\u0011\u0005%\u00131\u0012a\u0001\u0003\u0017Bq!KA5\t\u0003\tY*\u0006\u0003\u0002\u001e\u0006\rF\u0003BAP\u0003W\u0003R!!)\u0015\u0003g\u00022ALAR\t\u001d\u0001\u0014\u0011\u0014b\u0001\u0003K\u000b2AMAT%\u0011\tI\u000b\u000f\u001f\u0007\r]\nI\u0007AAT\u0011\u001d9\u0015\u0011\u0014a\u0001\u0003[\u0003B!O%\u0002\"\"I1*!\u001b\t\u0006\u0004%\t\u0001\u0014\u0005\u000b\u0003g\u000bI\u0007#A!B\u0013i\u0015!\u0002;sK\u0016\u0004\u0003\"C*\u0002j!\u0015\r\u0011\"\u0001U\u0011)\tI,!\u001b\t\u0002\u0003\u0006K!V\u0001\fgR\fG/[2UsB,\u0007\u0005\u0003\u0004\\\u0003S\"\t\u0001\u0016\u0005\b;\u0006%D\u0011AA`+\t\t\u0019\b\u0003\u0006j\u0003SB)\u0019!C\u0001\u0003\u007fC1\"!2\u0002j!\u0005\t\u0015)\u0003\u0002t\u00051a/\u00197vK\u0002B\u0001\"!3\u0002j\u0011%\u00111Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002\u0015!2\u0011qYAh\u0003C\u0004RaCAi\u0003+L1!a5\u0007\u0005\u0019!\bN]8xgB!\u0011q[Ao\u001b\t\tIN\u0003\u0003\u0002\\\u00065\u0011AA5p\u0013\u0011\ty.!7\u0003+=\u0013'.Z2u'R\u0014X-Y7Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u0011Q\u001b")
public interface Exprs {
    public Exprs$Expr$ Expr();

    public interface Expr<T>
    extends Equals,
    Serializable {
        public Mirror mirror();

        public <U extends Universe> Expr<T> in(Mirror<U> var1);

        public Trees.TreeApi tree();

        public Types.TypeApi staticType();

        public Types.TypeApi actualType();

        public T splice();

        public T value();

        @Override
        public boolean canEqual(Object var1);

        @Override
        public boolean equals(Object var1);

        public int hashCode();

        public String toString();

        public /* synthetic */ Exprs scala$reflect$api$Exprs$Expr$$$outer();
    }

    public class ExprImpl<T>
    implements Expr<T> {
        private final Mirror mirror;
        private final TreeCreator treec;
        private final TypeTags.WeakTypeTag<T> evidence$2;
        private Trees.TreeApi tree;
        private Types.TypeApi staticType;
        private T value;
        public final /* synthetic */ Universe $outer;
        private volatile byte bitmap$0;

        private Trees.TreeApi tree$lzycompute() {
            ExprImpl exprImpl = this;
            synchronized (exprImpl) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    this.tree = this.treec().apply(this.mirror());
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.tree;
            }
        }

        private Types.TypeApi staticType$lzycompute() {
            ExprImpl exprImpl = this;
            synchronized (exprImpl) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    TypeTags.WeakTypeTag<T> weakTypeTag = this.evidence$2;
                    Predef$ predef$ = Predef$.MODULE$;
                    this.staticType = weakTypeTag.tpe();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.staticType;
            }
        }

        private Object value$lzycompute() {
            ExprImpl exprImpl = this;
            synchronized (exprImpl) {
                block4: {
                    if ((byte)(this.bitmap$0 & 4) != 0) break block4;
                    String string2 = "\n      |the value you're calling is only meant to be used in cross-stage path-dependent types.\n      |if you want to splice the underlying expression, use `<your expr>.splice`.\n      |if you want to get a value of the underlying expression, add scala-compiler.jar to the classpath,\n      |import `scala.tools.reflect.Eval` and call `<your expr>.eval` instead.".trim();
                    Predef$ predef$ = Predef$.MODULE$;
                    // ** MonitorExit[this] (shouldn't be in output)
                    throw new UnsupportedOperationException(new StringOps(string2).stripMargin());
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.value;
            }
        }

        @Override
        public boolean canEqual(Object x) {
            return Exprs$Expr$class.canEqual(this, x);
        }

        @Override
        public boolean equals(Object x) {
            return Exprs$Expr$class.equals(this, x);
        }

        @Override
        public int hashCode() {
            return Exprs$Expr$class.hashCode(this);
        }

        @Override
        public String toString() {
            return Exprs$Expr$class.toString(this);
        }

        @Override
        public Mirror mirror() {
            return this.mirror;
        }

        public TreeCreator treec() {
            return this.treec;
        }

        @Override
        public <U extends Universe> Expr<T> in(Mirror<U> otherMirror) {
            TypeTags.WeakTypeTag<T> weakTypeTag = this.evidence$2;
            Predef$ predef$ = Predef$.MODULE$;
            TypeTags.WeakTypeTag<T> tag1 = weakTypeTag.in(otherMirror);
            return ((Universe)otherMirror.universe()).Expr().apply(otherMirror, this.treec(), tag1);
        }

        @Override
        public Trees.TreeApi tree() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.tree$lzycompute() : this.tree;
        }

        @Override
        public Types.TypeApi staticType() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.staticType$lzycompute() : this.staticType;
        }

        @Override
        public Types.TypeApi actualType() {
            return this.tree().tpe();
        }

        @Override
        public T splice() {
            String string2 = "\n      |the function you're calling has not been spliced by the compiler.\n      |this means there is a cross-stage evaluation involved, and it needs to be invoked explicitly.\n      |if you're sure this is not an oversight, add scala-compiler.jar to the classpath,\n      |import `scala.tools.reflect.Eval` and call `<your expr>.eval` instead.".trim();
            Predef$ predef$ = Predef$.MODULE$;
            throw new UnsupportedOperationException(new StringOps(string2).stripMargin());
        }

        @Override
        public T value() {
            return (T)((byte)(this.bitmap$0 & 4) == 0 ? this.value$lzycompute() : this.value);
        }

        private Object writeReplace() throws ObjectStreamException {
            TypeTags.WeakTypeTag<T> weakTypeTag = this.evidence$2;
            Predef$ predef$ = Predef$.MODULE$;
            return new SerializedExpr(this.treec(), weakTypeTag.in(((Mirrors)((Object)package$.MODULE$.universe())).rootMirror()));
        }

        public /* synthetic */ Universe scala$reflect$api$Exprs$ExprImpl$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Exprs scala$reflect$api$Exprs$Expr$$$outer() {
            return this.scala$reflect$api$Exprs$ExprImpl$$$outer();
        }

        public ExprImpl(Universe $outer, Mirror mirror, TreeCreator treec, TypeTags.WeakTypeTag<T> evidence$2) {
            this.mirror = mirror;
            this.treec = treec;
            this.evidence$2 = evidence$2;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Exprs$Expr$class.$init$(this);
        }
    }
}

