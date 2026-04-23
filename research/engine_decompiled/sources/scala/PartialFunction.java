/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Function1$class;
import scala.None$;
import scala.Option;
import scala.PartialFunction$;
import scala.PartialFunction$class;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractPartialFunction;

@ScalaSignature(bytes="\u0006\u0001\tEf\u0001C\u0001\u0003!\u0003\r\t!\u00022\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:T\u0011aA\u0001\u0006g\u000e\fG.Y\u0002\u0001+\r1\u0001CG\n\u0004\u0001\u001dY\u0001C\u0001\u0005\n\u001b\u0005\u0011\u0011B\u0001\u0006\u0003\u0005\u0019\te.\u001f*fMB!\u0001\u0002\u0004\b\u001a\u0013\ti!AA\u0005Gk:\u001cG/[8ocA\u0011q\u0002\u0005\u0007\u0001\t\u0019\t\u0002\u0001#b\u0001%\t\t\u0011)\u0005\u0002\u0014-A\u0011\u0001\u0002F\u0005\u0003+\t\u0011qAT8uQ&tw\r\u0005\u0002\t/%\u0011\u0001D\u0001\u0002\u0004\u0003:L\bCA\b\u001b\t\u0019Y\u0002\u0001\"b\u0001%\t\t!\tC\u0003\u001e\u0001\u0011\u0005a$\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011\u0001\u0002I\u0005\u0003C\t\u0011A!\u00168ji\")1\u0005\u0001D\u0001I\u0005Y\u0011n\u001d#fM&tW\rZ!u)\t)\u0003\u0006\u0005\u0002\tM%\u0011qE\u0001\u0002\b\u0005>|G.Z1o\u0011\u0015I#\u00051\u0001\u000f\u0003\u0005A\b\"B\u0016\u0001\t\u0003a\u0013AB8s\u000b2\u001cX-F\u0002.aQ\"\"AL\u001c\u0011\t!\u0001qf\r\t\u0003\u001fA\"Q!\r\u0016C\u0002I\u0012!!Q\u0019\u0012\u0005Mq\u0001CA\b5\t\u0015)$F1\u00017\u0005\t\u0011\u0015'\u0005\u0002\u001a-!)\u0001H\u000ba\u0001]\u0005!A\u000f[1u\u0011\u0015Q\u0004\u0001\"\u0011<\u0003\u001d\tg\u000e\u001a+iK:,\"\u0001P \u0015\u0005u\n\u0005\u0003\u0002\u0005\u0001\u001dy\u0002\"aD \u0005\u000b\u0001K$\u0019\u0001\n\u0003\u0003\rCQAQ\u001dA\u0002\r\u000b\u0011a\u001b\t\u0005\u00111Ib\bC\u0003F\u0001\u0011\u0005a)\u0001\u0003mS\u001a$X#A$\u0011\t!aa\u0002\u0013\t\u0004\u0011%K\u0012B\u0001&\u0003\u0005\u0019y\u0005\u000f^5p]\")A\n\u0001C\u0001\u001b\u0006Y\u0011\r\u001d9ms>\u0013X\t\\:f+\rq5\u000b\u0015\u000b\u0004\u001fF#\u0006CA\bQ\t\u0015)4J1\u00017\u0011\u0015I3\n1\u0001S!\ty1\u000bB\u00032\u0017\n\u0007!\u0007C\u0003V\u0017\u0002\u0007a+A\u0004eK\u001a\fW\u000f\u001c;\u0011\t!a!k\u0014\u0005\u00061\u0002!\t!W\u0001\beVtw+\u001b;i+\tQ\u0006\r\u0006\u0002\\9B!\u0001\u0002\u0004\b&\u0011\u0015iv\u000b1\u0001_\u0003\u0019\t7\r^5p]B!\u0001\u0002D\r`!\ty\u0001\rB\u0003b/\n\u0007!CA\u0001V!\u0011A\u0001AD\r\b\u000b\u0011\u0014\u0001\u0012A3\u0002\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004\"\u0001\u00034\u0007\u000b\u0005\u0011\u0001\u0012A4\u0014\u0005\u0019<\u0001\"B5g\t\u0003Q\u0017A\u0002\u001fj]&$h\bF\u0001f\r\u0011ag\rB7\u0003\r=\u0013X\t\\:f+\rq\u0017o]\n\u0004W\u001ey\u0007\u0003\u0002\u0005\u0001aJ\u0004\"aD9\u0005\rEY\u0007R1\u0001\u0013!\ty1\u000f\u0002\u0004\u001cW\u0012\u0015\rA\u0005\u0005\tk.\u0014\t\u0011)A\u0005_\u0006\u0011a-\r\u0005\to.\u0014\t\u0011)A\u0005_\u0006\u0011aM\r\u0005\u0006S.$\t!\u001f\u000b\u0004url\b\u0003B>laJl\u0011A\u001a\u0005\u0006kb\u0004\ra\u001c\u0005\u0006ob\u0004\ra\u001c\u0005\u0006G-$\ta \u000b\u0004K\u0005\u0005\u0001\"B\u0015\u007f\u0001\u0004\u0001\bbBA\u0003W\u0012\u0005\u0011qA\u0001\u0006CB\u0004H.\u001f\u000b\u0004e\u0006%\u0001BB\u0015\u0002\u0004\u0001\u0007\u0001\u000f\u0003\u0004MW\u0012\u0005\u0013QB\u000b\u0007\u0003\u001f\tY\"a\u0005\u0015\r\u0005E\u0011qCA\u0010!\ry\u00111\u0003\u0003\bk\u0005-!\u0019AA\u000b#\t\u0011h\u0003C\u0004*\u0003\u0017\u0001\r!!\u0007\u0011\u0007=\tY\u0002B\u00042\u0003\u0017\u0011\r!!\b\u0012\u0005M\u0001\bbB+\u0002\f\u0001\u0007\u0011\u0011\u0005\t\u0007\u00111\tI\"!\u0005\t\r-ZG\u0011IA\u0013+\u0019\t9#!\f\u00022Q!\u0011\u0011FA\u001a!\u0019Y8.a\u000b\u00020A\u0019q\"!\f\u0005\u000fE\n\u0019C1\u0001\u0002\u001eA\u0019q\"!\r\u0005\u000fU\n\u0019C1\u0001\u0002\u0016!9\u0001(a\tA\u0002\u0005U\u0002C\u0002\u0005\u0001\u0003W\ty\u0003\u0003\u0004;W\u0012\u0005\u0013\u0011H\u000b\u0005\u0003w\t\t\u0005\u0006\u0003\u0002>\u0005\r\u0003#B>la\u0006}\u0002cA\b\u0002B\u00111\u0001)a\u000eC\u0002IAqAQA\u001c\u0001\u0004\t)\u0005E\u0003\t\u0019I\fyD\u0002\u0004\u0002J\u0019$\u00111\n\u0002\b\u0003:$G\u000b[3o+!\ti%a\u0015\u0002b\u0005]3#BA$\u000f\u0005=\u0003C\u0002\u0005\u0001\u0003#\n)\u0006E\u0002\u0010\u0003'\"q!EA$\u0011\u000b\u0007!\u0003E\u0002\u0010\u0003/\"q\u0001QA$\t\u000b\u0007!\u0003C\u0006\u0002\\\u0005\u001d#\u0011!Q\u0001\n\u0005u\u0013A\u00019g!\u0019A\u0001!!\u0015\u0002`A\u0019q\"!\u0019\u0005\rm\t9E1\u0001\u0013\u0011)\u0011\u0015q\tB\u0001B\u0003%\u0011Q\r\t\u0007\u00111\ty&!\u0016\t\u000f%\f9\u0005\"\u0001\u0002jQ1\u00111NA7\u0003_\u0002\u0012b_A$\u0003#\ny&!\u0016\t\u0011\u0005m\u0013q\ra\u0001\u0003;BqAQA4\u0001\u0004\t)\u0007C\u0004$\u0003\u000f\"\t!a\u001d\u0015\u0007\u0015\n)\bC\u0004*\u0003c\u0002\r!!\u0015\t\u0011\u0005\u0015\u0011q\tC\u0001\u0003s\"B!!\u0016\u0002|!9\u0011&a\u001eA\u0002\u0005E\u0003b\u0002'\u0002H\u0011\u0005\u0013qP\u000b\u0007\u0003\u0003\u000by)!\"\u0015\r\u0005\r\u00151RAJ!\ry\u0011Q\u0011\u0003\t\u0003\u000f\u000biH1\u0001\u0002\n\n\u00111)M\t\u0004\u0003+2\u0002bB\u0015\u0002~\u0001\u0007\u0011Q\u0012\t\u0004\u001f\u0005=EaB\u0019\u0002~\t\u0007\u0011\u0011S\t\u0004'\u0005E\u0003bB+\u0002~\u0001\u0007\u0011Q\u0013\t\u0007\u00111\ti)a!\t\u0011\u0005ee\r)A\u0005\u00037\u000b1BZ1mY\n\f7m[0qMB!\u0001\u0002\u0001\f\u0017\u0011\u001d\tyJ\u001aC\u0005\u0003C\u000bQb\u00195fG.4\u0015\r\u001c7cC\u000e\\W\u0003BAR\u0003S+\"!!*\u0011\u000b!\u0001a#a*\u0011\u0007=\tI\u000b\u0002\u0004\u001c\u0003;\u0013\rA\u0005\u0005\b\u0003[3G\u0011BAX\u0003A1\u0017\r\u001c7cC\u000e\\wjY2veJ,G-\u0006\u0003\u00022\u0006]FcA\u0013\u00024\"9\u0011&a+A\u0002\u0005U\u0006cA\b\u00028\u001211$a+C\u0002I1a!a/g\t\u0005u&A\u0002'jMR,G-\u0006\u0004\u0002@\u0006=\u0017Q[\n\u0005\u0003s\u000b\t\r\u0005\u0005\u0002D\u0006%\u0017QZAi\u001b\t\t)MC\u0002\u0002H\n\tqA];oi&lW-\u0003\u0003\u0002L\u0006\u0015'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA\u0019q\"a4\u0005\u000fE\tI\f#b\u0001%A!\u0001\"SAj!\ry\u0011Q\u001b\u0003\b7\u0005eFQ1\u0001\u0013\u0011-\tY&!/\u0003\u0006\u0004%\t!!7\u0016\u0005\u0005m\u0007C\u0002\u0005\u0001\u0003\u001b\f\u0019\u000eC\u0006\u0002`\u0006e&\u0011!Q\u0001\n\u0005m\u0017a\u00019gA!9\u0011.!/\u0005\u0002\u0005\rH\u0003BAs\u0003O\u0004ra_A]\u0003\u001b\f\u0019\u000e\u0003\u0005\u0002\\\u0005\u0005\b\u0019AAn\u0011!\t)!!/\u0005\u0002\u0005-H\u0003BAi\u0003[Dq!KAu\u0001\u0004\tiM\u0002\u0004\u0002r\u001a$\u00111\u001f\u0002\t+:d\u0017N\u001a;fIV1\u0011Q_A\u0000\u0005\u0007\u0019B!a<\u0002xBA\u00111YA}\u0003{\u0014\t!\u0003\u0003\u0002|\u0006\u0015'aF!cgR\u0014\u0018m\u0019;QCJ$\u0018.\u00197Gk:\u001cG/[8o!\ry\u0011q \u0003\u0007#\u0005=(\u0019\u0001\n\u0011\u0007=\u0011\u0019\u0001\u0002\u0004\u001c\u0003_\u0014\rA\u0005\u0005\f\u0005\u000f\tyO!A!\u0002\u0013\u0011I!A\u0001g!\u0019AA\"!@\u0003\fA!\u0001\"\u0013B\u0001\u0011\u001dI\u0017q\u001eC\u0001\u0005\u001f!BA!\u0005\u0003\u0014A910a<\u0002~\n\u0005\u0001\u0002\u0003B\u0004\u0005\u001b\u0001\rA!\u0003\t\u000f\r\ny\u000f\"\u0001\u0003\u0018Q\u0019QE!\u0007\t\u000f%\u0012)\u00021\u0001\u0002~\"9A*a<\u0005B\tuQC\u0002B\u0010\u0005W\u0011\u0019\u0003\u0006\u0004\u0003\"\t\u001d\"q\u0006\t\u0004\u001f\t\rBaB\u001b\u0003\u001c\t\u0007!QE\t\u0004\u0005\u00031\u0002bB\u0015\u0003\u001c\u0001\u0007!\u0011\u0006\t\u0004\u001f\t-BaB\u0019\u0003\u001c\t\u0007!QF\t\u0004'\u0005u\bbB+\u0003\u001c\u0001\u0007!\u0011\u0007\t\u0007\u00111\u0011IC!\t\t\u000f\u0015\u000by\u000f\"\u0011\u00036U\u0011!\u0011\u0002\u0005\t\u0005s1G\u0011\u0001\u0002\u0003<\u0005AQO\u001c7jMR,G-\u0006\u0004\u0003>\t\r#q\t\u000b\u0005\u0005\u007f\u0011I\u0005\u0005\u0004\t\u0001\t\u0005#Q\t\t\u0004\u001f\t\rCAB\t\u00038\t\u0007!\u0003E\u0002\u0010\u0005\u000f\"aa\u0007B\u001c\u0005\u0004\u0011\u0002\u0002\u0003B\u0004\u0005o\u0001\rAa\u0013\u0011\r!a!\u0011\tB'!\u0011A\u0011J!\u0012\t\u000f\u0005\u0015a\r\"\u0001\u0003RU1!1\u000bB-\u0005;\"BA!\u0016\u0003`A1\u0001\u0002\u0001B,\u00057\u00022a\u0004B-\t\u0019\t\"q\nb\u0001%A\u0019qB!\u0018\u0005\rm\u0011yE1\u0001\u0013\u0011!\u00119Aa\u0014A\u0002\t\u0005\u0004C\u0002\u0005\r\u0005/\u0012Y\u0006\u0003\u0005\u0003f\u0019\u0004\u000b\u0011\u0002B4\u0003)\u0019wN\\:u\r\u0006d7/\u001a\t\u0005\u001111R\u0005\u0003\u0005\u0003l\u0019\u0004\u000b\u0011\u0002B7\u0003!)W\u000e\u001d;z?B4\u0007\u0003\u0002\u0005\u0001-MAqA!\u001dg\t\u0003\u0011\u0019(A\u0003f[B$\u00180\u0006\u0004\u0003v\tm$qP\u000b\u0003\u0005o\u0002b\u0001\u0003\u0001\u0003z\tu\u0004cA\b\u0003|\u00111\u0011Ca\u001cC\u0002I\u00012a\u0004B@\t\u0019Y\"q\u000eb\u0001%!9!1\u00114\u0005\u0002\t\u0015\u0015\u0001B2p]\u0012,BAa\"\u0003\u0012R!!\u0011\u0012BK)\r)#1\u0012\u0005\t\u00037\u0012\t\t1\u0001\u0003\u000eB)\u0001\u0002\u0001BHKA\u0019qB!%\u0005\u000f\tM%\u0011\u0011b\u0001%\t\tA\u000bC\u0004*\u0005\u0003\u0003\rAa$\t\u000f\tee\r\"\u0001\u0003\u001c\u000691m\u001c8e\u001fB$XC\u0002BO\u0005[\u0013)\u000b\u0006\u0003\u0003 \n=F\u0003\u0002BQ\u0005O\u0003B\u0001C%\u0003$B\u0019qB!*\u0005\r\u0005\u00149J1\u0001\u0013\u0011!\tYFa&A\u0002\t%\u0006C\u0002\u0005\u0001\u0005W\u0013\u0019\u000bE\u0002\u0010\u0005[#qAa%\u0003\u0018\n\u0007!\u0003C\u0004*\u0005/\u0003\rAa+")
public interface PartialFunction<A, B>
extends Function1<A, B> {
    public boolean isDefinedAt(A var1);

    public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> var1);

    @Override
    public <C> PartialFunction<A, C> andThen(Function1<B, C> var1);

    public Function1<A, Option<B>> lift();

    public <A1 extends A, B1> B1 applyOrElse(A1 var1, Function1<A1, B1> var2);

    public <U> Function1<A, Object> runWith(Function1<B, U> var1);

    public static class OrElse<A, B>
    implements PartialFunction<A, B> {
        private final PartialFunction<A, B> f1;
        private final PartialFunction<A, B> f2;

        @Override
        public Function1<A, Option<B>> lift() {
            return PartialFunction$class.lift(this);
        }

        @Override
        public <U> Function1<A, Object> runWith(Function1<B, U> action) {
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
        public <A> Function1<A, B> compose(Function1<A, A> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public String toString() {
            return Function1$class.toString(this);
        }

        @Override
        public boolean isDefinedAt(A x) {
            return this.f1.isDefinedAt(x) || this.f2.isDefinedAt(x);
        }

        @Override
        public B apply(A x) {
            return this.f1.applyOrElse(x, this.f2);
        }

        @Override
        public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
            Object z = this.f1.applyOrElse(x, PartialFunction$.MODULE$.scala$PartialFunction$$checkFallback());
            return (B1)(PartialFunction$.MODULE$.scala$PartialFunction$$fallbackOccurred(z) ? this.f2.applyOrElse(x, function1) : z);
        }

        @Override
        public <A1 extends A, B1> OrElse<A1, B1> orElse(PartialFunction<A1, B1> that) {
            return new OrElse<A, B>(this.f1, this.f2.orElse(that));
        }

        @Override
        public <C> OrElse<A, C> andThen(Function1<B, C> k) {
            return new OrElse<A, C>(this.f1.andThen(k), this.f2.andThen(k));
        }

        public OrElse(PartialFunction<A, B> f1, PartialFunction<A, B> f2) {
            this.f1 = f1;
            this.f2 = f2;
            Function1$class.$init$(this);
            PartialFunction$class.$init$(this);
        }
    }

    public static class Lifted<A, B>
    extends AbstractFunction1<A, Option<B>> {
        private final PartialFunction<A, B> pf;

        public PartialFunction<A, B> pf() {
            return this.pf;
        }

        @Override
        public Option<B> apply(A x) {
            Object z = this.pf().applyOrElse(x, PartialFunction$.MODULE$.scala$PartialFunction$$checkFallback());
            return PartialFunction$.MODULE$.scala$PartialFunction$$fallbackOccurred(z) ? None$.MODULE$ : new Some(z);
        }

        public Lifted(PartialFunction<A, B> pf) {
            this.pf = pf;
        }
    }

    public static class AndThen<A, B, C>
    implements PartialFunction<A, C> {
        private final PartialFunction<A, B> pf;
        private final Function1<B, C> k;

        @Override
        public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction<A1, B1> that) {
            return PartialFunction$class.orElse(this, that);
        }

        @Override
        public <C> PartialFunction<A, C> andThen(Function1<C, C> k) {
            return PartialFunction$class.andThen(this, k);
        }

        @Override
        public Function1<A, Option<C>> lift() {
            return PartialFunction$class.lift(this);
        }

        @Override
        public <U> Function1<A, Object> runWith(Function1<C, U> action) {
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
        public <A> Function1<A, C> compose(Function1<A, A> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public String toString() {
            return Function1$class.toString(this);
        }

        @Override
        public boolean isDefinedAt(A x) {
            return this.pf.isDefinedAt(x);
        }

        @Override
        public C apply(A x) {
            return this.k.apply(this.pf.apply(x));
        }

        @Override
        public <A1 extends A, C1> C1 applyOrElse(A1 x, Function1<A1, C1> function1) {
            Object z = this.pf.applyOrElse(x, PartialFunction$.MODULE$.scala$PartialFunction$$checkFallback());
            return (C1)(PartialFunction$.MODULE$.scala$PartialFunction$$fallbackOccurred(z) ? function1.apply(x) : this.k.apply(z));
        }

        public AndThen(PartialFunction<A, B> pf, Function1<B, C> k) {
            this.pf = pf;
            this.k = k;
            Function1$class.$init$(this);
            PartialFunction$class.$init$(this);
        }
    }

    public static class Unlifted<A, B>
    extends AbstractPartialFunction<A, B> {
        private final Function1<A, Option<B>> f;

        @Override
        public boolean isDefinedAt(A x) {
            return this.f.apply(x).isDefined();
        }

        @Override
        public <A1 extends A, B1> B1 applyOrElse(A1 x, Function1<A1, B1> function1) {
            Option<B> z = this.f.apply(x);
            return (B1)(z.isEmpty() ? function1.apply(x) : z.get());
        }

        @Override
        public Function1<A, Option<B>> lift() {
            return this.f;
        }

        public Unlifted(Function1<A, Option<B>> f) {
            this.f = f;
        }
    }
}

