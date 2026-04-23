/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.TriState$;

@ScalaSignature(bytes="\u0006\u0001\u00014A!\u0001\u0002\u0003\u0017\tAAK]5Ti\u0006$XM\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\t)a!\u0001\u0005j]R,'O\\1m\u0015\t9\u0001\"A\u0004sK\u001adWm\u0019;\u000b\u0003%\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011QBD\u0007\u0002\u0011%\u0011q\u0002\u0003\u0002\u0007\u0003:Lh+\u00197\t\u0011E\u0001!Q1A\u0005\u0002I\tQA^1mk\u0016,\u0012a\u0005\t\u0003\u001bQI!!\u0006\u0005\u0003\u0007%sG\u000f\u0003\u0005\u0018\u0001\t\u0005\t\u0015!\u0003\u0014\u0003\u00191\u0018\r\\;fA!a\u0011\u0004\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u00055\u00051A(\u001b8jiz\"\"aG\u000f\u0011\u0005q\u0001Q\"\u0001\u0002\t\u000bEA\u0002\u0019A\n\t\u000b}\u0001A\u0011\u0001\u0011\u0002\u000f%\u001c8J\\8x]V\t\u0011\u0005\u0005\u0002\u000eE%\u00111\u0005\u0003\u0002\b\u0005>|G.Z1o\u0011\u0015)\u0003\u0001\"\u0001!\u00031\u0011wn\u001c7fC:4\u0016\r\\;f\u0011\u001d9\u0003!!A\u0005B!\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002'!9!\u0006AA\u0001\n\u0003Z\u0013AB3rk\u0006d7\u000f\u0006\u0002\"Y!9Q&KA\u0001\u0002\u0004q\u0013a\u0001=%cA\u0011QbL\u0005\u0003a!\u00111!\u00118z\u000f\u0015\u0011$\u0001#\u00014\u0003!!&/[*uCR,\u0007C\u0001\u000f5\r\u0015\t!\u0001#\u00016'\t!d\u0007\u0005\u0002\u000eo%\u0011\u0001\b\u0003\u0002\u0007\u0003:L(+\u001a4\t\u000be!D\u0011\u0001\u001e\u0015\u0003MBQ\u0001\u0010\u001b\u0005\u0004u\n\u0011CY8pY\u0016\fg\u000eV8Ue&\u001cF/\u0019;f)\tYb\bC\u0003@w\u0001\u0007\u0011%A\u0001c\u0011\u001d\tEG1A\u0005\u0002\t\u000bq!\u00168l]><h.F\u0001\u001c\u0011\u0019!E\u0007)A\u00057\u0005AQK\\6o_^t\u0007\u0005C\u0004Gi\t\u0007I\u0011\u0001\"\u0002\u000b\u0019\u000bGn]3\t\r!#\u0004\u0015!\u0003\u001c\u0003\u00191\u0015\r\\:fA!9!\n\u000eb\u0001\n\u0003\u0011\u0015\u0001\u0002+sk\u0016Da\u0001\u0014\u001b!\u0002\u0013Y\u0012!\u0002+sk\u0016\u0004\u0003\"\u0002(5\t\u000by\u0015!E5t\u0017:|wO\u001c\u0013fqR,gn]5p]R\u0011\u0011\u0005\u0015\u0005\u0006#6\u0003\raG\u0001\u0006IQD\u0017n\u001d\u0005\u0006'R\")\u0001V\u0001\u0017E>|G.Z1o-\u0006dW/\u001a\u0013fqR,gn]5p]R\u0011\u0011%\u0016\u0005\u0006#J\u0003\ra\u0007\u0005\b/R\n\t\u0011\"\u0002Y\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005!J\u0006\"B)W\u0001\u0004Y\u0002bB.5\u0003\u0003%)\u0001X\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$\"!X0\u0015\u0005\u0005r\u0006bB\u0017[\u0003\u0003\u0005\rA\f\u0005\u0006#j\u0003\ra\u0007")
public final class TriState {
    private final int value;

    public static boolean equals$extension(int n, Object object) {
        return TriState$.MODULE$.equals$extension(n, object);
    }

    public static int hashCode$extension(int n) {
        return TriState$.MODULE$.hashCode$extension(n);
    }

    public static boolean booleanValue$extension(int n) {
        return TriState$.MODULE$.booleanValue$extension(n);
    }

    public static boolean isKnown$extension(int n) {
        return TriState$.MODULE$.isKnown$extension(n);
    }

    public static int True() {
        return TriState$.MODULE$.True();
    }

    public static int False() {
        return TriState$.MODULE$.False();
    }

    public static int Unknown() {
        return TriState$.MODULE$.Unknown();
    }

    public static int booleanToTriState(boolean bl) {
        return TriState$.MODULE$.booleanToTriState(bl);
    }

    public int value() {
        return this.value;
    }

    public boolean isKnown() {
        return TriState$.MODULE$.isKnown$extension(this.value());
    }

    public boolean booleanValue() {
        return TriState$.MODULE$.booleanValue$extension(this.value());
    }

    public int hashCode() {
        return TriState$.MODULE$.hashCode$extension(this.value());
    }

    public boolean equals(Object x$1) {
        return TriState$.MODULE$.equals$extension(this.value(), x$1);
    }

    public TriState(int value) {
        this.value = value;
    }
}

