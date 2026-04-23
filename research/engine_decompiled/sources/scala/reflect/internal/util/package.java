/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.StringContext;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.reflect.internal.util.StripMarginInterpolator$class;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001)<Q!\u0001\u0002\t\u0002-\tq\u0001]1dW\u0006<WM\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\t)a!\u0001\u0005j]R,'O\\1m\u0015\t9\u0001\"A\u0004sK\u001adWm\u0019;\u000b\u0003%\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\r\u001b5\t!AB\u0003\u000f\u0005!\u0005qBA\u0004qC\u000e\\\u0017mZ3\u0014\u00055\u0001\u0002CA\t\u0013\u001b\u0005A\u0011BA\n\t\u0005\u0019\te.\u001f*fM\")Q#\u0004C\u0001-\u00051A(\u001b8jiz\"\u0012a\u0003\u0005\b15\u0011\r\u0011\"\u0001\u001a\u0003%a\u0015n\u001d;PM:KG.F\u0001\u001b!\rYR\u0004\t\b\u0003#qI!!\u0001\u0005\n\u0005yy\"\u0001\u0002'jgRT!!\u0001\u0005\u0011\u0007mi\u0012\u0005\u0005\u0002\u0012E%\u00111\u0005\u0003\u0002\b\u001d>$\b.\u001b8h\u0011\u0019)S\u0002)A\u00055\u0005QA*[:u\u001f\u001at\u0015\u000e\u001c\u0011\t\u000b\u001djA\u0011\u0001\u0015\u0002\u0011\u0005tGMR1mg\u0016$\"!\u000b\u0017\u0011\u0005EQ\u0013BA\u0016\t\u0005\u001d\u0011un\u001c7fC:DQ!\f\u0014A\u00029\nAAY8esB\u0011\u0011cL\u0005\u0003a!\u0011A!\u00168ji\")!'\u0004C\u0005g\u0005Y1\u000f[8si\u0016tg*Y7f)\t!4\b\u0005\u00026q9\u0011\u0011CN\u0005\u0003o!\ta\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011q\u0007\u0003\u0005\u0006yE\u0002\r\u0001N\u0001\u0005]\u0006lW\rC\u0003?\u001b\u0011\u0005q(\u0001\u000btQ>\u0014Ho\u00117bgN|e-\u00138ti\u0006t7-\u001a\u000b\u0003i\u0001CQ!Q\u001fA\u0002A\t\u0011\u0001\u001f\u0005\u0006\u00076!\t\u0001R\u0001\u000bg\"|'\u000f^\"mCN\u001cHC\u0001\u001bF\u0011\u00151%\t1\u0001H\u0003\u0015\u0019G.\u0019>{a\tAU\nE\u00026\u0013.K!A\u0013\u001e\u0003\u000b\rc\u0017m]:\u0011\u00051kE\u0002\u0001\u0003\n\u001d\u0016\u000b\t\u0011!A\u0003\u0002=\u00131a\u0018\u00132#\t\t\u0003\u000b\u0005\u0002\u0012#&\u0011!\u000b\u0003\u0002\u0004\u0003:Lh\u0001\u0002+\u000e\u0003U\u00131d\u0015;sS:<7i\u001c8uKb$8\u000b\u001e:ja6\u000b'oZ5o\u001fB\u001c8cA*\u0011-B\u0011AbV\u0005\u00031\n\u0011qc\u0015;sSBl\u0015M]4j]&sG/\u001a:q_2\fGo\u001c:\t\u0011i\u001b&Q1A\u0005\u0002m\u000bQb\u001d;sS:<7i\u001c8uKb$X#\u0001/\u0011\u0005Ei\u0016B\u00010\t\u00055\u0019FO]5oO\u000e{g\u000e^3yi\"A\u0001m\u0015B\u0001B\u0003%A,\u0001\btiJLgnZ\"p]R,\u0007\u0010\u001e\u0011\t\u000bU\u0019F\u0011\u00012\u0015\u0005\r,\u0007C\u00013T\u001b\u0005i\u0001\"\u0002.b\u0001\u0004a\u0006bB4\u000e\u0003\u0003%\u0019\u0001[\u0001\u001c'R\u0014\u0018N\\4D_:$X\r\u001f;TiJL\u0007/T1sO&tw\n]:\u0015\u0005\rL\u0007\"\u0002.g\u0001\u0004a\u0006")
public final class package {
    public static StringContextStripMarginOps StringContextStripMarginOps(StringContext stringContext) {
        return package$.MODULE$.StringContextStripMarginOps(stringContext);
    }

    public static String shortClass(Class<?> clazz) {
        return package$.MODULE$.shortClass(clazz);
    }

    public static String shortClassOfInstance(Object object) {
        return package$.MODULE$.shortClassOfInstance(object);
    }

    public static boolean andFalse(BoxedUnit boxedUnit) {
        return package$.MODULE$.andFalse(boxedUnit);
    }

    public static List<List<Nothing$>> ListOfNil() {
        return package$.MODULE$.ListOfNil();
    }

    public static class StringContextStripMarginOps
    implements StripMarginInterpolator {
        private final StringContext stringContext;

        @Override
        public final String sm(Seq<Object> args) {
            return StripMarginInterpolator$class.sm(this, args);
        }

        @Override
        public StringContext stringContext() {
            return this.stringContext;
        }

        public StringContextStripMarginOps(StringContext stringContext) {
            this.stringContext = stringContext;
            StripMarginInterpolator$class.$init$(this);
        }
    }
}

