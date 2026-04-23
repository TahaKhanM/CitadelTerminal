/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.StringContext$;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.StringOps$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005Mf\u0001B\u0001\u0003\u0001\u0016\u0011Qb\u0015;sS:<7i\u001c8uKb$(\"A\u0002\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\u0002\u0006\u000e!\t9\u0001\"D\u0001\u0003\u0013\tI!A\u0001\u0004B]f\u0014VM\u001a\t\u0003\u000f-I!\u0001\u0004\u0002\u0003\u000fA\u0013x\u000eZ;diB\u0011qAD\u0005\u0003\u001f\t\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"\u0005\u0001\u0003\u0016\u0004%\tAE\u0001\u0006a\u0006\u0014Ho]\u000b\u0002'A\u0019q\u0001\u0006\f\n\u0005U\u0011!A\u0003\u001fsKB,\u0017\r^3e}A\u0011qC\u0007\b\u0003\u000faI!!\u0007\u0002\u0002\rA\u0013X\rZ3g\u0013\tYBD\u0001\u0004TiJLgn\u001a\u0006\u00033\tA\u0001B\b\u0001\u0003\u0012\u0003\u0006IaE\u0001\u0007a\u0006\u0014Ho\u001d\u0011\t\u000b\u0001\u0002A\u0011A\u0011\u0002\rqJg.\u001b;?)\t\u00113\u0005\u0005\u0002\b\u0001!)\u0011c\ba\u0001'!)Q\u0005\u0001C\u0001M\u0005a1\r[3dW2+gn\u001a;igR\u0011qE\u000b\t\u0003\u000f!J!!\u000b\u0002\u0003\tUs\u0017\u000e\u001e\u0005\u0006W\u0011\u0002\r\u0001L\u0001\u0005CJ<7\u000fE\u0002.aMr!a\u0002\u0018\n\u0005=\u0012\u0011a\u00029bG.\fw-Z\u0005\u0003cI\u00121aU3r\u0015\ty#\u0001\u0005\u0002\bi%\u0011QG\u0001\u0002\u0004\u0003:L\b\"B\u001c\u0001\t\u0003A\u0014!A:\u0015\u0005YI\u0004\"B\u00167\u0001\u0004Q\u0004cA\u0004\u0015g!)A\b\u0001C\u0001{\u0005\u0019!/Y<\u0015\u0005Yq\u0004\"B\u0016<\u0001\u0004Q\u0004\"\u0002!\u0001\t\u0003\t\u0015\u0001F:uC:$\u0017M\u001d3J]R,'\u000f]8mCR|'\u000fF\u0002\u0017\u0005\u001eCQaQ A\u0002\u0011\u000bq\u0001\u001d:pG\u0016\u001c8\u000f\u0005\u0003\b\u000bZ1\u0012B\u0001$\u0003\u0005%1UO\\2uS>t\u0017\u0007C\u0003,\u007f\u0001\u0007A\u0006\u0003\u0004J\u0001\t%\tAS\u0001\u0002MV\u00111\n\u0015\u000b\u0003-1CQa\u000b%A\u00025\u00032a\u0002\u000bO!\ty\u0005\u000b\u0004\u0001\u0005\u000bEC%\u0019\u0001*\u0003\u0003\u0005\u000b\"aM\u001a\t\u000fQ\u0003\u0011\u0011!C!+\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0016\t\u0003/rk\u0011\u0001\u0017\u0006\u00033j\u000bA\u0001\\1oO*\t1,\u0001\u0003kCZ\f\u0017BA\u000eY\u0011\u001dq\u0006!!A\u0005\u0002}\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u0019\t\u0003\u000f\u0005L!A\u0019\u0002\u0003\u0007%sG\u000fC\u0004e\u0001\u0005\u0005I\u0011A3\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u00111G\u001a\u0005\bO\u000e\f\t\u00111\u0001a\u0003\rAH%\r\u0005\bS\u0002\t\t\u0011\"\u0011k\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A6\u0011\u00071|7'D\u0001n\u0015\tq'!\u0001\u0006d_2dWm\u0019;j_:L!\u0001]7\u0003\u0011%#XM]1u_JDqA\u001d\u0001\u0002\u0002\u0013\u00051/\u0001\u0005dC:,\u0015/^1m)\t!x\u000f\u0005\u0002\bk&\u0011aO\u0001\u0002\b\u0005>|G.Z1o\u0011\u001d9\u0017/!AA\u0002MBq!\u001f\u0001\u0002\u0002\u0013\u0005#0\u0001\u0005iCND7i\u001c3f)\u0005\u0001\u0007b\u0002?\u0001\u0003\u0003%\t%`\u0001\ti>\u001cFO]5oOR\ta\u000b\u0003\u0005\u0000\u0001\u0005\u0005I\u0011IA\u0001\u0003\u0019)\u0017/^1mgR\u0019A/a\u0001\t\u000f\u001dt\u0018\u0011!a\u0001g\u001d9\u0011q\u0001\u0002\t\u0002\u0005%\u0011!D*ue&twmQ8oi\u0016DH\u000fE\u0002\b\u0003\u00171a!\u0001\u0002\t\u0002\u000551\u0003BA\u0006\r5Aq\u0001IA\u0006\t\u0003\t\t\u0002\u0006\u0002\u0002\n\u00199\u0011QCA\u0006\u0001\u0005]!AF%om\u0006d\u0017\u000eZ#tG\u0006\u0004X-\u0012=dKB$\u0018n\u001c8\u0014\t\u0005M\u0011\u0011\u0004\t\u0004[\u0005m\u0011bAA\u000fe\tA\u0012\n\u001c7fO\u0006d\u0017I]4v[\u0016tG/\u0012=dKB$\u0018n\u001c8\t\u0015\u0005\u0005\u00121\u0003B\u0001B\u0003%a#A\u0002tiJD!\"!\n\u0002\u0014\t\u0015\r\u0011\"\u0001`\u0003\u0015Ig\u000eZ3y\u0011)\tI#a\u0005\u0003\u0002\u0003\u0006I\u0001Y\u0001\u0007S:$W\r\u001f\u0011\t\u000f\u0001\n\u0019\u0002\"\u0001\u0002.Q1\u0011qFA\u001a\u0003k\u0001B!!\r\u0002\u00145\u0011\u00111\u0002\u0005\b\u0003C\tY\u00031\u0001\u0017\u0011\u001d\t)#a\u000bA\u0002\u0001Dc!!\u000e\u0002:\u0005}\u0002cA\u0004\u0002<%\u0019\u0011Q\b\u0002\u0003\u001d\u0011,\u0007O]3dCR,GMT1nKF:q$!\u0011\u0002H\u0005%\u0004cA\u0004\u0002D%\u0019\u0011Q\t\u0002\u0003\rMKXNY8mc%\u0019\u0013\u0011JA(\u0003/\n\t\u0006\u0006\u0003\u0002B\u0005-\u0003BBA'\t\u0001\u0007a#\u0001\u0003oC6,\u0017\u0002BA)\u0003'\nQ!\u00199qYfT1!!\u0016\u0003\u0003\u0019\u0019\u00160\u001c2pYFJ1%!\u0017\u0002f\u0005\u001d\u0014Q\u000b\b\u0005\u00037\n)G\u0004\u0003\u0002^\u0005\rTBAA0\u0015\r\t\t\u0007B\u0001\u0007yI|w\u000e\u001e \n\u0003\rI1!!\u0016\u0003c\u0019!\u00131LA2\u0007E*Q%a\u001b\u0002n=\u0011\u0011QN\u0011\u0003\u0003_\n1!\u001b3y\u0011!\t\u0019(a\u0003\u0005\u0002\u0005U\u0014\u0001\u0004;sK\u0006$Xi]2ba\u0016\u001cHc\u0001\f\u0002x!9\u0011\u0011EA9\u0001\u00041\u0002\u0002CA>\u0003\u0017!\t!! \u0002\u001dA\u0014xnY3tg\u0016\u001b8-\u00199fgR\u0019a#a \t\u000f\u0005\u0005\u0012\u0011\u0010a\u0001-!A\u00111QA\u0006\t\u0013\t))A\u0007ue\u0016\fG/R:dCB,7\u000f\r\u000b\u0006-\u0005\u001d\u0015\u0011\u0012\u0005\b\u0003C\t\t\t1\u0001\u0017\u0011\u001d\tY)!!A\u0002Q\faa\u001d;sS\u000e$\bBCA)\u0003\u0017\t\t\u0011\"!\u0002\u0010R\u0019!%!%\t\rE\ti\t1\u0001\u0014\u0011)\t)*a\u0003\u0002\u0002\u0013\u0005\u0015qS\u0001\u000bk:\f\u0007\u000f\u001d7z'\u0016\fH\u0003BAM\u0003G\u0003RaBAN\u0003?K1!!(\u0003\u0005\u0019y\u0005\u000f^5p]B!A.!)\u0017\u0013\t\tT\u000eC\u0005\u0002&\u0006M\u0015\u0011!a\u0001E\u0005\u0019\u0001\u0010\n\u0019\t\u0015\u0005%\u00161BA\u0001\n\u0013\tY+A\u0006sK\u0006$'+Z:pYZ,GCAAW!\r9\u0016qV\u0005\u0004\u0003cC&AB(cU\u0016\u001cG\u000f")
public class StringContext
implements Product,
Serializable {
    private final Seq<String> parts;

    public static Option<Seq<String>> unapplySeq(StringContext stringContext) {
        return StringContext$.MODULE$.unapplySeq(stringContext);
    }

    public static StringContext apply(Seq<String> seq) {
        return StringContext$.MODULE$.apply(seq);
    }

    public static String processEscapes(String string2) {
        return StringContext$.MODULE$.processEscapes(string2);
    }

    public static String treatEscapes(String string2) {
        return StringContext$.MODULE$.treatEscapes(string2);
    }

    public Seq<String> parts() {
        return this.parts;
    }

    public void checkLengths(Seq<Object> args) {
        if (this.parts().length() != args.length() + 1) {
            throw new IllegalArgumentException(new StringBuilder().append((Object)"wrong number of arguments (").append(BoxesRunTime.boxToInteger(args.length())).append((Object)") for interpolated string with ").append(BoxesRunTime.boxToInteger(this.parts().length())).append((Object)" parts").toString());
        }
    }

    public String s(Seq<Object> args) {
        return this.standardInterpolator((Function1<String, String>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final String apply(String str) {
                return StringContext$.MODULE$.treatEscapes(str);
            }
        }), args);
    }

    public String raw(Seq<Object> args) {
        return this.standardInterpolator((Function1<String, String>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final String apply(String x) {
                return Predef$.MODULE$.identity(x);
            }
        }), args);
    }

    public String standardInterpolator(Function1<String, String> process, Seq<Object> args) {
        this.checkLengths(args);
        Iterator pi = this.parts().iterator();
        Iterator ai = args.iterator();
        java.lang.StringBuilder bldr = new java.lang.StringBuilder(process.apply((String)pi.next()));
        while (ai.hasNext()) {
            bldr.append(ai.next());
            bldr.append(process.apply((String)pi.next()));
        }
        return bldr.toString();
    }

    @Override
    public String productPrefix() {
        return "StringContext";
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
        return this.parts();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof StringContext;
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
        if (!(x$1 instanceof StringContext)) return false;
        boolean bl = true;
        if (!bl) return false;
        StringContext stringContext = (StringContext)x$1;
        Seq<String> seq = this.parts();
        Seq<String> seq2 = stringContext.parts();
        if (seq == null) {
            if (seq2 != null) {
                return false;
            }
        } else if (!seq.equals(seq2)) return false;
        if (!stringContext.canEqual(this)) return false;
        return true;
    }

    public StringContext(Seq<String> parts) {
        this.parts = parts;
        Product$class.$init$(this);
    }

    public static class InvalidEscapeException
    extends IllegalArgumentException {
        private final int index;

        public int index() {
            return this.index;
        }

        public InvalidEscapeException(String str, int index) {
            String string2;
            this.index = index;
            StringContext stringContext = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"invalid escape ", " index ", " in \"", "\". Use \\\\\\\\ for literal \\\\."}));
            Object[] objectArray = new Object[3];
            Predef$.MODULE$.require(index >= 0 && index < str.length());
            if (index == str.length() - 1) {
                string2 = "at terminal";
            } else {
                Object[] objectArray2 = new Object[2];
                Predef$ predef$ = Predef$.MODULE$;
                objectArray2[0] = BoxesRunTime.boxToCharacter(StringOps$.MODULE$.apply$extension(str, index + 1));
                objectArray2[1] = "[\\b, \\t, \\n, \\f, \\r, \\\\, \\\", \\']";
                string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"'\\\\", "' not one of ", " at"})).s(Predef$.MODULE$.genericWrapArray(objectArray2));
            }
            objectArray[0] = string2;
            objectArray[1] = BoxesRunTime.boxToInteger(index);
            objectArray[2] = str;
            super(stringContext.s(Predef$.MODULE$.genericWrapArray(objectArray)));
        }
    }
}

