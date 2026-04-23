/*
 * Decompiled with CFR 0.152.
 */
package scala.util.control;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import scala.util.control.TailCalls$;
import scala.util.control.TailCalls$TailRec$;

@ScalaSignature(bytes="\u0006\u0001\r5r!B\u0001\u0003\u0011\u0003I\u0011!\u0003+bS2\u001c\u0015\r\u001c7t\u0015\t\u0019A!A\u0004d_:$(o\u001c7\u000b\u0005\u00151\u0011\u0001B;uS2T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tQ1\"D\u0001\u0003\r\u0015a!\u0001#\u0001\u000e\u0005%!\u0016-\u001b7DC2d7o\u0005\u0002\f\u001dA\u0011q\u0002E\u0007\u0002\r%\u0011\u0011C\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000bMYA\u0011\u0001\u000b\u0002\rqJg.\u001b;?)\u0005Ia!\u0002\f\f\u0003\u00039\"a\u0002+bS2\u0014VmY\u000b\u00031}\u0019\"!\u0006\b\t\u000bM)B\u0011\u0001\u000e\u0015\u0003m\u00012\u0001H\u000b\u001e\u001b\u0005Y\u0001C\u0001\u0010 \u0019\u0001!a\u0001I\u000b\u0005\u0006\u0004\t#!A!\u0012\u0005\t*\u0003CA\b$\u0013\t!cAA\u0004O_RD\u0017N\\4\u0011\u0005=1\u0013BA\u0014\u0007\u0005\r\te.\u001f\u0005\u0006SU!)AK\u0001\u0004[\u0006\u0004XCA\u0016/)\ta\u0003\u0007E\u0002\u001d+5\u0002\"A\b\u0018\u0005\u000b=B#\u0019A\u0011\u0003\u0003\tCQ!\r\u0015A\u0002I\n\u0011A\u001a\t\u0005\u001fMjR&\u0003\u00025\r\tIa)\u001e8di&|g.\r\u0005\u0006mU!)aN\u0001\bM2\fG/T1q+\tA4\b\u0006\u0002:yA\u0019A$\u0006\u001e\u0011\u0005yYD!B\u00186\u0005\u0004\t\u0003\"B\u00196\u0001\u0004i\u0004\u0003B\b4;eBQaP\u000b\u0005\u0006\u0001\u000baA]3tk6,W#A!\u0011\t\t+\u0005*\b\b\u0003\u001f\rK!\u0001\u0012\u0004\u0002\u000fA\f7m[1hK&\u0011ai\u0012\u0002\u0007\u000b&$\b.\u001a:\u000b\u0005\u00113\u0001cA\bJ7%\u0011!J\u0002\u0002\n\rVt7\r^5p]BB#A\u0010'\u0011\u00055\u0003V\"\u0001(\u000b\u0005=3\u0011AC1o]>$\u0018\r^5p]&\u0011\u0011K\u0014\u0002\bi\u0006LGN]3d\u0011\u0015\u0019V\u0003\"\u0002U\u0003\u0019\u0011Xm];miV\tQ\u0004\u000b\u0002S\u0019\u001a!qk\u0003%Y\u0005\u0011\u0019\u0015\r\u001c7\u0016\u0005ec6\u0003\u0002,[;\u0002\u00042\u0001H\u000b\\!\tqB\fB\u0003!-\n\u0007\u0011\u0005\u0005\u0002\u0010=&\u0011qL\u0002\u0002\b!J|G-^2u!\ty\u0011-\u0003\u0002c\r\ta1+\u001a:jC2L'0\u00192mK\"AAM\u0016BK\u0002\u0013\u0005Q-\u0001\u0003sKN$X#\u00014\u0011\u0007=I%\f\u0003\u0005i-\nE\t\u0015!\u0003g\u0003\u0015\u0011Xm\u001d;!\u0011\u0015\u0019b\u000b\"\u0001k)\tYG\u000eE\u0002\u001d-nCQ\u0001Z5A\u0002\u0019DqA\u001c,\u0002\u0002\u0013\u0005q.\u0001\u0003d_BLXC\u00019t)\t\tH\u000fE\u0002\u001d-J\u0004\"AH:\u0005\u000b\u0001j'\u0019A\u0011\t\u000f\u0011l\u0007\u0013!a\u0001kB\u0019q\"\u0013<\u0011\u0007q)\"\u000fC\u0004y-F\u0005I\u0011A=\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0019!0a\u0002\u0016\u0003mT#A\u001a?,\u0003u\u00042A`A\u0002\u001b\u0005y(bAA\u0001\u001d\u0006IQO\\2iK\u000e\\W\rZ\u0005\u0004\u0003\u000by(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)\u0001e\u001eb\u0001C!I\u00111\u0002,\u0002\u0002\u0013\u0005\u0013QB\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005=\u0001\u0003BA\t\u00037i!!a\u0005\u000b\t\u0005U\u0011qC\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u001a\u0005!!.\u0019<b\u0013\u0011\ti\"a\u0005\u0003\rM#(/\u001b8h\u0011%\t\tCVA\u0001\n\u0003\t\u0019#\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002&A\u0019q\"a\n\n\u0007\u0005%bAA\u0002J]RD\u0011\"!\fW\u0003\u0003%\t!a\f\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019Q%!\r\t\u0015\u0005M\u00121FA\u0001\u0002\u0004\t)#A\u0002yIEB\u0011\"a\u000eW\u0003\u0003%\t%!\u000f\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u000f\u0011\u000b\u0005u\u00121I\u0013\u000e\u0005\u0005}\"bAA!\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u0013q\b\u0002\t\u0013R,'/\u0019;pe\"I\u0011\u0011\n,\u0002\u0002\u0013\u0005\u00111J\u0001\tG\u0006tW)];bYR!\u0011QJA*!\ry\u0011qJ\u0005\u0004\u0003#2!a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003g\t9%!AA\u0002\u0015B\u0011\"a\u0016W\u0003\u0003%\t%!\u0017\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\n\t\u0013\u0005uc+!A\u0005B\u0005}\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005=\u0001\"CA2-\u0006\u0005I\u0011IA3\u0003\u0019)\u0017/^1mgR!\u0011QJA4\u0011%\t\u0019$!\u0019\u0002\u0002\u0003\u0007QeB\u0005\u0002l-\t\t\u0011#\u0005\u0002n\u0005!1)\u00197m!\ra\u0012q\u000e\u0004\t/.\t\t\u0011#\u0005\u0002rM!\u0011q\u000e\ba\u0011\u001d\u0019\u0012q\u000eC\u0001\u0003k\"\"!!\u001c\t\u0015\u0005u\u0013qNA\u0001\n\u000b\ny\u0006\u0003\u0006\u0002|\u0005=\u0014\u0011!CA\u0003{\nQ!\u00199qYf,B!a \u0002\u0006R!\u0011\u0011QAD!\u0011ab+a!\u0011\u0007y\t)\t\u0002\u0004!\u0003s\u0012\r!\t\u0005\bI\u0006e\u0004\u0019AAE!\u0011y\u0011*a#\u0011\tq)\u00121\u0011\u0005\u000b\u0003\u001f\u000by'!A\u0005\u0002\u0006E\u0015aB;oCB\u0004H._\u000b\u0005\u0003'\u000b\t\u000b\u0006\u0003\u0002\u0016\u0006\r\u0006#B\b\u0002\u0018\u0006m\u0015bAAM\r\t1q\n\u001d;j_:\u0004BaD%\u0002\u001eB!A$FAP!\rq\u0012\u0011\u0015\u0003\u0007A\u00055%\u0019A\u0011\t\u0015\u0005\u0015\u0016QRA\u0001\u0002\u0004\t9+A\u0002yIA\u0002B\u0001\b,\u0002 \"Q\u00111VA8\u0003\u0003%I!!,\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003_\u0003B!!\u0005\u00022&!\u00111WA\n\u0005\u0019y%M[3di\u001a1\u0011qW\u0006I\u0003s\u0013A\u0001R8oKV!\u00111XAa'\u0019\t),!0^AB!A$FA`!\rq\u0012\u0011\u0019\u0003\u0007A\u0005U&\u0019A\u0011\t\u0017\u0005\u0015\u0017Q\u0017BK\u0002\u0013\u0005\u0011qY\u0001\u0006m\u0006dW/Z\u000b\u0003\u0003\u007fC1\"a3\u00026\nE\t\u0015!\u0003\u0002@\u00061a/\u00197vK\u0002BqaEA[\t\u0003\ty\r\u0006\u0003\u0002R\u0006M\u0007#\u0002\u000f\u00026\u0006}\u0006\u0002CAc\u0003\u001b\u0004\r!a0\t\u00139\f),!A\u0005\u0002\u0005]W\u0003BAm\u0003?$B!a7\u0002bB)A$!.\u0002^B\u0019a$a8\u0005\r\u0001\n)N1\u0001\"\u0011)\t)-!6\u0011\u0002\u0003\u0007\u0011Q\u001c\u0005\nq\u0006U\u0016\u0013!C\u0001\u0003K,B!a:\u0002lV\u0011\u0011\u0011\u001e\u0016\u0004\u0003\u007fcHA\u0002\u0011\u0002d\n\u0007\u0011\u0005\u0003\u0006\u0002\f\u0005U\u0016\u0011!C!\u0003\u001bA!\"!\t\u00026\u0006\u0005I\u0011AA\u0012\u0011)\ti#!.\u0002\u0002\u0013\u0005\u00111\u001f\u000b\u0004K\u0005U\bBCA\u001a\u0003c\f\t\u00111\u0001\u0002&!Q\u0011qGA[\u0003\u0003%\t%!\u000f\t\u0015\u0005%\u0013QWA\u0001\n\u0003\tY\u0010\u0006\u0003\u0002N\u0005u\b\"CA\u001a\u0003s\f\t\u00111\u0001&\u0011)\t9&!.\u0002\u0002\u0013\u0005\u0013\u0011\f\u0005\u000b\u0003;\n),!A\u0005B\u0005}\u0003BCA2\u0003k\u000b\t\u0011\"\u0011\u0003\u0006Q!\u0011Q\nB\u0004\u0011%\t\u0019Da\u0001\u0002\u0002\u0003\u0007QeB\u0005\u0003\f-\t\t\u0011#\u0005\u0003\u000e\u0005!Ai\u001c8f!\ra\"q\u0002\u0004\n\u0003o[\u0011\u0011!E\t\u0005#\u0019BAa\u0004\u000fA\"91Ca\u0004\u0005\u0002\tUAC\u0001B\u0007\u0011)\tiFa\u0004\u0002\u0002\u0013\u0015\u0013q\f\u0005\u000b\u0003w\u0012y!!A\u0005\u0002\nmQ\u0003\u0002B\u000f\u0005G!BAa\b\u0003&A)A$!.\u0003\"A\u0019aDa\t\u0005\r\u0001\u0012IB1\u0001\"\u0011!\t)M!\u0007A\u0002\t\u0005\u0002BCAH\u0005\u001f\t\t\u0011\"!\u0003*U!!1\u0006B\u0019)\u0011\u0011iCa\r\u0011\u000b=\t9Ja\f\u0011\u0007y\u0011\t\u0004\u0002\u0004!\u0005O\u0011\r!\t\u0005\u000b\u0003K\u00139#!AA\u0002\tU\u0002#\u0002\u000f\u00026\n=\u0002BCAV\u0005\u001f\t\t\u0011\"\u0003\u0002.\u001a1!1H\u0006I\u0005{\u0011AaQ8oiV1!q\bB)\u0005\u000b\u001abA!\u000f\u0003Bu\u0003\u0007\u0003\u0002\u000f\u0016\u0005\u0007\u00022A\bB#\t\u0019y#\u0011\bb\u0001C!Y!\u0011\nB\u001d\u0005+\u0007I\u0011\u0001B&\u0003\u0005\tWC\u0001B'!\u0011aRCa\u0014\u0011\u0007y\u0011\t\u0006\u0002\u0004!\u0005s\u0011\r!\t\u0005\f\u0005+\u0012ID!E!\u0002\u0013\u0011i%\u0001\u0002bA!Q\u0011G!\u000f\u0003\u0016\u0004%\tA!\u0017\u0016\u0005\tm\u0003CB\b4\u0005\u001f\u0012\t\u0005C\u0006\u0003`\te\"\u0011#Q\u0001\n\tm\u0013A\u00014!\u0011\u001d\u0019\"\u0011\bC\u0001\u0005G\"bA!\u001a\u0003h\t%\u0004c\u0002\u000f\u0003:\t=#1\t\u0005\t\u0005\u0013\u0012\t\u00071\u0001\u0003N!9\u0011G!\u0019A\u0002\tm\u0003\"\u00038\u0003:\u0005\u0005I\u0011\u0001B7+\u0019\u0011yG!\u001e\u0003zQ1!\u0011\u000fB>\u0005\u007f\u0002r\u0001\bB\u001d\u0005g\u00129\bE\u0002\u001f\u0005k\"a\u0001\tB6\u0005\u0004\t\u0003c\u0001\u0010\u0003z\u00111qFa\u001bC\u0002\u0005B!B!\u0013\u0003lA\u0005\t\u0019\u0001B?!\u0011aRCa\u001d\t\u0013E\u0012Y\u0007%AA\u0002\t\u0005\u0005CB\b4\u0005g\u0012\u0019\t\u0005\u0003\u001d+\t]\u0004\"\u0003=\u0003:E\u0005I\u0011\u0001BD+\u0019\u0011II!$\u0003\u0010V\u0011!1\u0012\u0016\u0004\u0005\u001bbHA\u0002\u0011\u0003\u0006\n\u0007\u0011\u0005\u0002\u00040\u0005\u000b\u0013\r!\t\u0005\u000b\u0005'\u0013I$%A\u0005\u0002\tU\u0015AD2paf$C-\u001a4bk2$HEM\u000b\u0007\u0005/\u0013YJ!(\u0016\u0005\te%f\u0001B.y\u00121\u0001E!%C\u0002\u0005\"aa\fBI\u0005\u0004\t\u0003BCA\u0006\u0005s\t\t\u0011\"\u0011\u0002\u000e!Q\u0011\u0011\u0005B\u001d\u0003\u0003%\t!a\t\t\u0015\u00055\"\u0011HA\u0001\n\u0003\u0011)\u000bF\u0002&\u0005OC!\"a\r\u0003$\u0006\u0005\t\u0019AA\u0013\u0011)\t9D!\u000f\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\u000b\u0003\u0013\u0012I$!A\u0005\u0002\t5F\u0003BA'\u0005_C\u0011\"a\r\u0003,\u0006\u0005\t\u0019A\u0013\t\u0015\u0005]#\u0011HA\u0001\n\u0003\nI\u0006\u0003\u0006\u0002^\te\u0012\u0011!C!\u0003?B!\"a\u0019\u0003:\u0005\u0005I\u0011\tB\\)\u0011\tiE!/\t\u0013\u0005M\"QWA\u0001\u0002\u0004)s!\u0003B_\u0017\u0005\u0005\t\u0012\u0003B`\u0003\u0011\u0019uN\u001c;\u0011\u0007q\u0011\tMB\u0005\u0003<-\t\t\u0011#\u0005\u0003DN!!\u0011\u0019\ba\u0011\u001d\u0019\"\u0011\u0019C\u0001\u0005\u000f$\"Aa0\t\u0015\u0005u#\u0011YA\u0001\n\u000b\ny\u0006\u0003\u0006\u0002|\t\u0005\u0017\u0011!CA\u0005\u001b,bAa4\u0003V\neGC\u0002Bi\u00057\u0014y\u000eE\u0004\u001d\u0005s\u0011\u0019Na6\u0011\u0007y\u0011)\u000e\u0002\u0004!\u0005\u0017\u0014\r!\t\t\u0004=\teGAB\u0018\u0003L\n\u0007\u0011\u0005\u0003\u0005\u0003J\t-\u0007\u0019\u0001Bo!\u0011aRCa5\t\u000fE\u0012Y\r1\u0001\u0003bB1qb\rBj\u0005G\u0004B\u0001H\u000b\u0003X\"Q\u0011q\u0012Ba\u0003\u0003%\tIa:\u0016\r\t%(q\u001fB\u0000)\u0011\u0011Yo!\u0001\u0011\u000b=\t9J!<\u0011\u000f=\u0011yOa=\u0003z&\u0019!\u0011\u001f\u0004\u0003\rQ+\b\u000f\\33!\u0011aRC!>\u0011\u0007y\u00119\u0010\u0002\u0004!\u0005K\u0014\r!\t\t\u0007\u001fM\u0012)Pa?\u0011\tq)\"Q \t\u0004=\t}HAB\u0018\u0003f\n\u0007\u0011\u0005\u0003\u0006\u0002&\n\u0015\u0018\u0011!a\u0001\u0007\u0007\u0001r\u0001\bB\u001d\u0005k\u0014i\u0010\u0003\u0006\u0002,\n\u0005\u0017\u0011!C\u0005\u0003[Cqa!\u0003\f\t\u0003\u0019Y!\u0001\u0005uC&d7-\u00197m+\u0011\u0019iaa\u0005\u0015\t\r=1Q\u0003\t\u00059U\u0019\t\u0002E\u0002\u001f\u0007'!a\u0001IB\u0004\u0005\u0004\t\u0003\u0002\u00033\u0004\b\u0011\u0005\raa\u0006\u0011\u000b=\u0019Iba\u0004\n\u0007\rmaA\u0001\u0005=Eft\u0017-\\3?\u0011\u001d\u0019yb\u0003C\u0001\u0007C\tA\u0001Z8oKV!11EB\u0015)\u0011\u0019)ca\u000b\u0011\tq)2q\u0005\t\u0004=\r%BA\u0002\u0011\u0004\u001e\t\u0007\u0011\u0005C\u0004T\u0007;\u0001\raa\n")
public final class TailCalls {
    public static <A> TailRec<A> done(A a) {
        return TailCalls$.MODULE$.done(a);
    }

    public static <A> TailRec<A> tailcall(Function0<TailRec<A>> function0) {
        return TailCalls$.MODULE$.tailcall(function0);
    }

    public static class Call<A>
    extends TailRec<A>
    implements Product,
    Serializable {
        private final Function0<TailRec<A>> rest;

        public Function0<TailRec<A>> rest() {
            return this.rest;
        }

        public <A> Call<A> copy(Function0<TailRec<A>> rest) {
            return new Call<A>(rest);
        }

        public <A> Function0<TailRec<A>> copy$default$1() {
            return this.rest();
        }

        @Override
        public String productPrefix() {
            return "Call";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.rest();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof Call;
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
            if (!(x$1 instanceof Call)) return false;
            boolean bl = true;
            if (!bl) return false;
            Call call = (Call)x$1;
            Function0<TailRec<A>> function0 = this.rest();
            Function0<TailRec<A>> function02 = call.rest();
            if (function0 == null) {
                if (function02 != null) {
                    return false;
                }
            } else if (!function0.equals(function02)) return false;
            if (!call.canEqual(this)) return false;
            return true;
        }

        public Call(Function0<TailRec<A>> rest) {
            this.rest = rest;
            Product$class.$init$(this);
        }
    }

    public static class Done<A>
    extends TailRec<A>
    implements Product,
    Serializable {
        private final A value;

        public A value() {
            return this.value;
        }

        public <A> Done<A> copy(A value) {
            return new Done<A>(value);
        }

        public <A> A copy$default$1() {
            return this.value();
        }

        @Override
        public String productPrefix() {
            return "Done";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.value();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof Done;
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
            boolean bl;
            if (this == x$1) return true;
            if (!(x$1 instanceof Done)) return false;
            boolean bl2 = true;
            if (!bl2) return false;
            Done done = (Done)x$1;
            A a = done.value();
            A a2 = this.value();
            if (a2 == a) {
                bl = true;
            } else {
                if (a2 == null) {
                    return false;
                }
                bl = a2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)a2, a) : (a2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)a2, a) : a2.equals(a));
            }
            if (!bl) return false;
            if (!done.canEqual(this)) return false;
            return true;
        }

        public Done(A value) {
            this.value = value;
            Product$class.$init$(this);
        }
    }

    public static class Cont<A, B>
    extends TailRec<B>
    implements Product,
    Serializable {
        private final TailRec<A> a;
        private final Function1<A, TailRec<B>> f;

        public TailRec<A> a() {
            return this.a;
        }

        public Function1<A, TailRec<B>> f() {
            return this.f;
        }

        public <A, B> Cont<A, B> copy(TailRec<A> a, Function1<A, TailRec<B>> f) {
            return new Cont<A, B>(a, f);
        }

        public <A, B> TailRec<A> copy$default$1() {
            return this.a();
        }

        public <A, B> Function1<A, TailRec<B>> copy$default$2() {
            return this.f();
        }

        @Override
        public String productPrefix() {
            return "Cont";
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
                    object = this.f();
                    break;
                }
                case 0: {
                    object = this.a();
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
            return x$1 instanceof Cont;
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
            if (!(x$1 instanceof Cont)) return false;
            boolean bl = true;
            if (!bl) return false;
            Cont cont = (Cont)x$1;
            TailRec<A> tailRec = this.a();
            TailRec<A> tailRec2 = cont.a();
            if (tailRec == null) {
                if (tailRec2 != null) {
                    return false;
                }
            } else if (!tailRec.equals(tailRec2)) return false;
            Function1<A, TailRec<B>> function1 = this.f();
            Function1<A, TailRec<B>> function12 = cont.f();
            if (function1 == null) {
                if (function12 != null) {
                    return false;
                }
            } else if (!function1.equals(function12)) return false;
            if (!cont.canEqual(this)) return false;
            return true;
        }

        public Cont(TailRec<A> a, Function1<A, TailRec<B>> f) {
            this.a = a;
            this.f = f;
            Product$class.$init$(this);
        }
    }

    public static abstract class TailRec<A> {
        public final <B> TailRec<B> map(Function1<A, B> f) {
            block5: {
                TailRec tailRec;
                block3: {
                    Serializable serializable;
                    block4: {
                        block2: {
                            serializable = new Serializable(this, f){
                                public static final long serialVersionUID = 0L;
                                public final Function1 f$1;

                                public final Call<B> apply(A a) {
                                    return new Call<A>(new Serializable(this, a){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ TailRec$$anonfun$map$1 $outer;
                                        private final Object a$1;

                                        public final Done<B> apply() {
                                            return new Done<R>(this.$outer.f$1.apply(this.a$1));
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                            this.a$1 = a$1;
                                        }
                                    });
                                }
                                {
                                    this.f$1 = f$1;
                                }
                            };
                            if (!(this instanceof Done)) break block2;
                            Done x21 = (Done)this;
                            tailRec = new Call(new Serializable(this, (Function1)((Object)serializable), x21){
                                public static final long serialVersionUID = 0L;
                                private final Function1 f$2;
                                private final Done x2$1;

                                public final TailRec<B> apply() {
                                    return (TailRec)this.f$2.apply(this.x2$1.value());
                                }
                                {
                                    void var3_3;
                                    this.f$2 = f$2;
                                    this.x2$1 = var3_3;
                                }
                            });
                            break block3;
                        }
                        if (!(this instanceof Call)) break block4;
                        Call x31 = (Call)this;
                        tailRec = new Cont(x31, serializable);
                        break block3;
                    }
                    if (!(this instanceof Cont)) break block5;
                    Cont x51 = (Cont)this;
                    tailRec = new Cont(x51.a(), new Serializable(this, (Function1)((Object)serializable), x51){
                        public static final long serialVersionUID = 0L;
                        private final Function1 f$2;
                        private final Cont x5$1;

                        public final TailRec<B> apply(Object x) {
                            TailRec<B> tailRec;
                            block5: {
                                TailRec tailRec2;
                                block3: {
                                    Function1 function1;
                                    block4: {
                                        block2: {
                                            function1 = this.f$2;
                                            tailRec = this.x5$1.f().apply(x);
                                            if (!(tailRec instanceof Done)) break block2;
                                            Done x21 = (Done)tailRec;
                                            tailRec2 = new Call<A>(new /* invalid duplicate definition of identical inner class */);
                                            break block3;
                                        }
                                        if (!(tailRec instanceof Call)) break block4;
                                        Call x31 = (Call)tailRec;
                                        tailRec2 = new Cont<A, B>(x31, function1);
                                        break block3;
                                    }
                                    if (!(tailRec instanceof Cont)) break block5;
                                    Cont x51 = (Cont)tailRec;
                                    tailRec2 = new Cont<A, B>(x51.a(), new /* invalid duplicate definition of identical inner class */);
                                }
                                return tailRec2;
                            }
                            throw new MatchError(tailRec);
                        }
                        {
                            void var3_3;
                            this.f$2 = f$2;
                            this.x5$1 = var3_3;
                        }
                    });
                }
                return tailRec;
            }
            throw new MatchError(this);
        }

        public final <B> TailRec<B> flatMap(Function1<A, TailRec<B>> f) {
            block5: {
                TailRec tailRec;
                block3: {
                    block4: {
                        block2: {
                            if (!(this instanceof Done)) break block2;
                            Done done = (Done)this;
                            tailRec = new Call(new /* invalid duplicate definition of identical inner class */);
                            break block3;
                        }
                        if (!(this instanceof Call)) break block4;
                        Call call = (Call)this;
                        tailRec = new Cont<A, B>(call, f);
                        break block3;
                    }
                    if (!(this instanceof Cont)) break block5;
                    Cont cont = (Cont)this;
                    tailRec = new Cont(cont.a(), new /* invalid duplicate definition of identical inner class */);
                }
                return tailRec;
            }
            throw new MatchError(this);
        }

        public final Either<Function0<TailRec<A>>, A> resume() {
            block9: {
                TailRec tailRec;
                block10: {
                    TailRec tailRec2;
                    while (true) {
                        TailRec tailRec3;
                        TailRec this_;
                        Cont cont;
                        block14: {
                            Either either2;
                            block12: {
                                block13: {
                                    block11: {
                                        if (!(this_ instanceof Done)) break block11;
                                        Done done = (Done)this_;
                                        Object a = done.value();
                                        either2 = new Right(a);
                                        break block12;
                                    }
                                    if (!(this_ instanceof Call)) break block13;
                                    Call call = (Call)this_;
                                    Function0 function0 = call.rest();
                                    either2 = new Left(function0);
                                    break block12;
                                }
                                if (!(this_ instanceof Cont)) break block9;
                                cont = (Cont)this_;
                                tailRec = cont.a();
                                if (tailRec instanceof Done) {
                                    Done done = (Done)tailRec;
                                    this_ = cont.f().apply(done.value());
                                    continue;
                                }
                                if (!(tailRec instanceof Call)) break block14;
                                Call call = (Call)tailRec;
                                Serializable serializable = new Serializable(this_, call, cont){
                                    public static final long serialVersionUID = 0L;
                                    private final Call x3$1;
                                    private final Cont x4$2;

                                    public final TailRec<A> apply() {
                                        TailRec<A> tailRec;
                                        block5: {
                                            TailRec tailRec2;
                                            block3: {
                                                Function1<A, TailRec<B>> function1;
                                                block4: {
                                                    block2: {
                                                        function1 = this.x4$2.f();
                                                        tailRec = this.x3$1.rest().apply();
                                                        if (!(tailRec instanceof Done)) break block2;
                                                        Done x21 = (Done)tailRec;
                                                        tailRec2 = new Call<A>(new /* invalid duplicate definition of identical inner class */);
                                                        break block3;
                                                    }
                                                    if (!(tailRec instanceof Call)) break block4;
                                                    Call x31 = (Call)tailRec;
                                                    tailRec2 = new Cont<A, B>(x31, function1);
                                                    break block3;
                                                }
                                                if (!(tailRec instanceof Cont)) break block5;
                                                Cont x51 = (Cont)tailRec;
                                                tailRec2 = new Cont<A, B>(x51.a(), new /* invalid duplicate definition of identical inner class */);
                                            }
                                            return tailRec2;
                                        }
                                        throw new MatchError(tailRec);
                                    }
                                    {
                                        void var3_3;
                                        this.x3$1 = x3$1;
                                        this.x4$2 = var3_3;
                                    }
                                };
                                either2 = new Left(serializable);
                            }
                            return either2;
                        }
                        if (!(tailRec instanceof Cont)) break block10;
                        Cont cont2 = (Cont)tailRec;
                        Serializable serializable = new Serializable(this_, cont2, cont){
                            public static final long serialVersionUID = 0L;
                            private final Cont x4$1;
                            private final Cont x4$2;

                            public final TailRec<A> apply(Object x) {
                                TailRec<B> tailRec;
                                block5: {
                                    TailRec tailRec2;
                                    block3: {
                                        Function1<A, TailRec<B>> function1;
                                        block4: {
                                            block2: {
                                                function1 = this.x4$2.f();
                                                tailRec = this.x4$1.f().apply(x);
                                                if (!(tailRec instanceof Done)) break block2;
                                                Done x21 = (Done)tailRec;
                                                tailRec2 = new Call<A>(new /* invalid duplicate definition of identical inner class */);
                                                break block3;
                                            }
                                            if (!(tailRec instanceof Call)) break block4;
                                            Call x31 = (Call)tailRec;
                                            tailRec2 = new Cont<A, B>(x31, function1);
                                            break block3;
                                        }
                                        if (!(tailRec instanceof Cont)) break block5;
                                        Cont x51 = (Cont)tailRec;
                                        tailRec2 = new Cont<A, B>(x51.a(), new /* invalid duplicate definition of identical inner class */);
                                    }
                                    return tailRec2;
                                }
                                throw new MatchError(tailRec);
                            }
                            {
                                void var3_3;
                                this.x4$1 = x4$1;
                                this.x4$2 = var3_3;
                            }
                        };
                        tailRec2 = cont2.a();
                        if (tailRec2 instanceof Done) {
                            Done x21 = (Done)tailRec2;
                            tailRec3 = new Call(new /* invalid duplicate definition of identical inner class */);
                        } else if (tailRec2 instanceof Call) {
                            Call x31 = (Call)tailRec2;
                            tailRec3 = new Cont(x31, serializable);
                        } else {
                            if (!(tailRec2 instanceof Cont)) break;
                            Cont x51 = (Cont)tailRec2;
                            tailRec3 = new Cont(x51.a(), new /* invalid duplicate definition of identical inner class */);
                        }
                        this_ = tailRec3;
                    }
                    throw new MatchError(tailRec2);
                }
                throw new MatchError(tailRec);
            }
            throw new MatchError(this_);
        }

        public final A result() {
            block11: {
                TailRec tailRec;
                block12: {
                    TailRec tailRec2;
                    while (true) {
                        TailRec tailRec3;
                        Cont cont;
                        TailRec<Object> this_;
                        block13: {
                            TailRec tailRec4;
                            block17: {
                                TailRec tailRec5;
                                block15: {
                                    Function1 function1;
                                    block16: {
                                        block14: {
                                            if (this_ instanceof Done) {
                                                Done done = (Done)this_;
                                                Object a = done.value();
                                                return a;
                                            }
                                            if (this_ instanceof Call) {
                                                Call call = (Call)this_;
                                                this_ = call.rest().apply();
                                                continue;
                                            }
                                            if (!(this_ instanceof Cont)) break block11;
                                            cont = (Cont)this_;
                                            tailRec = cont.a();
                                            if (tailRec instanceof Done) {
                                                Done done = (Done)tailRec;
                                                this_ = cont.f().apply(done.value());
                                                continue;
                                            }
                                            if (!(tailRec instanceof Call)) break block13;
                                            Call call = (Call)tailRec;
                                            function1 = cont.f();
                                            tailRec4 = call.rest().apply();
                                            if (!(tailRec4 instanceof Done)) break block14;
                                            Done x21 = (Done)tailRec4;
                                            tailRec5 = new Call(new /* invalid duplicate definition of identical inner class */);
                                            break block15;
                                        }
                                        if (!(tailRec4 instanceof Call)) break block16;
                                        Call x31 = (Call)tailRec4;
                                        tailRec5 = new Cont(x31, function1);
                                        break block15;
                                    }
                                    if (!(tailRec4 instanceof Cont)) break block17;
                                    Cont x51 = (Cont)tailRec4;
                                    tailRec5 = new Cont(x51.a(), new /* invalid duplicate definition of identical inner class */);
                                }
                                this_ = tailRec5;
                                continue;
                            }
                            throw new MatchError(tailRec4);
                        }
                        if (!(tailRec instanceof Cont)) break block12;
                        Cont cont2 = (Cont)tailRec;
                        Serializable serializable = new Serializable(this_, cont2, cont){
                            public static final long serialVersionUID = 0L;
                            private final Cont x4$3;
                            private final Cont x4$4;

                            public final TailRec<A> apply(Object x) {
                                TailRec<B> tailRec;
                                block5: {
                                    TailRec tailRec2;
                                    block3: {
                                        Function1<A, TailRec<B>> function1;
                                        block4: {
                                            block2: {
                                                function1 = this.x4$4.f();
                                                tailRec = this.x4$3.f().apply(x);
                                                if (!(tailRec instanceof Done)) break block2;
                                                Done x21 = (Done)tailRec;
                                                tailRec2 = new Call<A>(new /* invalid duplicate definition of identical inner class */);
                                                break block3;
                                            }
                                            if (!(tailRec instanceof Call)) break block4;
                                            Call x31 = (Call)tailRec;
                                            tailRec2 = new Cont<A, B>(x31, function1);
                                            break block3;
                                        }
                                        if (!(tailRec instanceof Cont)) break block5;
                                        Cont x51 = (Cont)tailRec;
                                        tailRec2 = new Cont<A, B>(x51.a(), new /* invalid duplicate definition of identical inner class */);
                                    }
                                    return tailRec2;
                                }
                                throw new MatchError(tailRec);
                            }
                            {
                                void var3_3;
                                this.x4$3 = x4$3;
                                this.x4$4 = var3_3;
                            }
                        };
                        tailRec2 = cont2.a();
                        if (tailRec2 instanceof Done) {
                            Done x22 = (Done)tailRec2;
                            tailRec3 = new Call(new /* invalid duplicate definition of identical inner class */);
                        } else if (tailRec2 instanceof Call) {
                            Call x32 = (Call)tailRec2;
                            tailRec3 = new Cont(x32, serializable);
                        } else {
                            if (!(tailRec2 instanceof Cont)) break;
                            Cont x52 = (Cont)tailRec2;
                            tailRec3 = new Cont(x52.a(), new /* invalid duplicate definition of identical inner class */);
                        }
                        this_ = tailRec3;
                    }
                    throw new MatchError(tailRec2);
                }
                throw new MatchError(tailRec);
            }
            throw new MatchError(this_);
        }
    }
}

