/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.transform.Erasure;
import scala.reflect.internal.transform.PostErasure;
import scala.reflect.internal.transform.RefChecks;
import scala.reflect.internal.transform.UnCurry;

@ScalaSignature(bytes="\u0006\u0001\u00055d!C\u0001\u0003!\u0003\r\taCA3\u0005)!&/\u00198tM>\u0014Xn\u001d\u0006\u0003\u0007\u0011\t\u0011\u0002\u001e:b]N4wN]7\u000b\u0005\u00151\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mC\u000e\u00011C\u0001\u0001\r!\tia\"D\u0001\t\u0013\ty\u0001B\u0001\u0004B]f\u0014VM\u001a\u0005\u0006#\u0001!\tAE\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0001\"!\u0004\u000b\n\u0005UA!\u0001B+oSR4Aa\u0006\u0001\u00051\t!A*\u0019>z+\tI\u0012e\u0005\u0002\u0017\u0019!A1D\u0006B\u0001J\u0003%A$\u0001\u0002paB\u0019Q\"H\u0010\n\u0005yA!\u0001\u0003\u001fcs:\fW.\u001a \u0011\u0005\u0001\nC\u0002\u0001\u0003\u0006EY\u0011\ra\t\u0002\u0002)F\u0011Ae\n\t\u0003\u001b\u0015J!A\n\u0005\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002K\u0005\u0003S!\u00111!\u00118z\u0011\u0015Yc\u0003\"\u0001-\u0003\u0019a\u0014N\\5u}Q\u0011Qf\f\t\u0004]YyR\"\u0001\u0001\t\rmQC\u00111\u0001\u001d\u0011%\td\u00031AA\u0002\u0013%!'A\u0003wC2,X-F\u0001 \u0011%!d\u00031AA\u0002\u0013%Q'A\u0005wC2,Xm\u0018\u0013fcR\u00111C\u000e\u0005\boM\n\t\u00111\u0001 \u0003\rAH%\r\u0005\u0007sY\u0001\u000b\u0015B\u0010\u0002\rY\fG.^3!\u0011\u001dYd\u00031A\u0005\nq\n!bX5t\t\u00164\u0017N\\3e+\u0005i\u0004CA\u0007?\u0013\ty\u0004BA\u0004C_>dW-\u00198\t\u000f\u00053\u0002\u0019!C\u0005\u0005\u0006qq,[:EK\u001aLg.\u001a3`I\u0015\fHCA\nD\u0011\u001d9\u0004)!AA\u0002uBa!\u0012\f!B\u0013i\u0014aC0jg\u0012+g-\u001b8fI\u0002BQa\u0012\f\u0005\u0002q\n\u0011\"[:EK\u001aLg.\u001a3\t\u000b%3B\u0011\u0001\u001a\u0002\u000b\u0019|'oY3\t\u000f-\u0003!\u0019!C\u0005\u0019\u0006i!/\u001a4DQ\u0016\u001c7n\u001d'buf,\u0012!\u0014\t\u0004]Yq%cA(\r'\u001a!\u0001+\u0015\u0001O\u00051a$/\u001a4j]\u0016lWM\u001c;?\u0011\u0019\u0011\u0006\u0001)A\u0005\u001b\u0006q!/\u001a4DQ\u0016\u001c7n\u001d'buf\u0004\u0003C\u0001+V\u001b\u0005\u0011\u0011B\u0001,\u0003\u0005%\u0011VMZ\"iK\u000e\\7\u000fC\u0004Y\u001f\n\u0007I\u0011A-\u0002\r\u001ddwNY1m+\u0005q\u0003bB.\u0001\u0005\u0004%I\u0001X\u0001\fk:\u001cWO\u001d:z\u0019\u0006T\u00180F\u0001^!\rqcC\u0018\n\u0004?2\u0011g\u0001\u0002)a\u0001yCa!\u0019\u0001!\u0002\u0013i\u0016\u0001D;oGV\u0014(/\u001f'buf\u0004\u0003C\u0001+d\u0013\t!'AA\u0004V]\u000e+(O]=\t\u000fa{&\u0019!C\u00013\"9q\r\u0001b\u0001\n\u0013A\u0017aC3sCN,(/\u001a'buf,\u0012!\u001b\t\u0004]YQ'cA6\r]\u001a!\u0001\u000b\u001c\u0001k\u0011\u0019i\u0007\u0001)A\u0005S\u0006aQM]1tkJ,G*\u0019>zAA\u0011Ak\\\u0005\u0003a\n\u0011q!\u0012:bgV\u0014X\rC\u0004YW\n\u0007I\u0011A-\t\u000fM\u0004!\u0019!C\u0005i\u0006y\u0001o\\:u\u000bJ\f7/\u001e:f\u0019\u0006T\u00180F\u0001v!\rqcC\u001e\n\u0004o2Qh\u0001\u0002)y\u0001YDa!\u001f\u0001!\u0002\u0013)\u0018\u0001\u00059pgR,%/Y:ve\u0016d\u0015M_=!!\t!60\u0003\u0002}\u0005\tY\u0001k\\:u\u000bJ\f7/\u001e:f\u0011\u001dAvO1A\u0005\u0002eCaa \u0001\u0005\u0002\u0005\u0005\u0011!\u0003:fM\u000eCWmY6t+\u0005q\u0005bBA\u0003\u0001\u0011\u0005\u0011qA\u0001\bk:\u001cWO\u001d:z+\u0005q\u0006bBA\u0006\u0001\u0011\u0005\u0011QB\u0001\bKJ\f7/\u001e:f+\u0005Q\u0007bBA\t\u0001\u0011\u0005\u00111C\u0001\fa>\u001cH/\u0012:bgV\u0014X-F\u0001w\u0011\u001d\t9\u0002\u0001C\u0001\u00033\tq\u0002\u001e:b]N4wN]7fIRK\b/\u001a\u000b\u0005\u00037\tY\u0004\r\u0003\u0002\u001e\u0005\r\u0002\u0003BA\u0010\u0003gq1!!\t~!\r\u0001\u00131\u0005\u0003\r\u0003K\t)\"!A\u0001\u0002\u000b\u0005\u0011q\u0005\u0002\t?f\u001ad\u0006^=qKF\u0019A%!\u000b\u0013\u000b\u0005-b/!\f\u0007\u000bA\u0003\u0001!!\u000b\u0011\u00075\ty#C\u0002\u00022!\u0011\u0011bU5oO2,Go\u001c8\n\t\u0005U\u0012q\u0007\u0002\u0005)f\u0004X-C\u0002\u0002:\u0011\u0011Q\u0001V=qKND\u0001\"!\u0010\u0002\u0016\u0001\u0007\u0011qH\u0001\u0004gfl\u0007c\u0001\u0018\u0002B%!\u00111IA#\u0005\u0019\u0019\u00160\u001c2pY&\u0019\u0011q\t\u0003\u0003\u000fMKXNY8mg\"9\u0011q\u0003\u0001\u0005\u0002\u0005-C\u0003BA'\u0003?\u0002D!a\u0014\u0002VA!\u0011\u0011KA\u001a\u001d\r\t\u0019& \t\u0004A\u0005UC\u0001DA,\u0003\u0013\n\t\u0011!A\u0003\u0002\u0005e#\u0001C07g9\"\u0018\u0010]3\u0012\u0007\u0011\nYFE\u0003\u0002^Y\fiCB\u0003Q\u0001\u0001\tY\u0006\u0003\u0005\u0002b\u0005%\u0003\u0019AA2\u0003\r!\b/\u001a\t\u0004]\u0005M\u0002\u0003BA4\u0003Sj\u0011\u0001B\u0005\u0004\u0003W\"!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface Transforms {
    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$refChecksLazy_$eq(Lazy var1);

    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq(Lazy var1);

    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq(Lazy var1);

    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq(Lazy var1);

    public Lazy<Object> scala$reflect$internal$transform$Transforms$$refChecksLazy();

    public Lazy<Object> scala$reflect$internal$transform$Transforms$$uncurryLazy();

    public Lazy<Object> scala$reflect$internal$transform$Transforms$$erasureLazy();

    public Lazy<Object> scala$reflect$internal$transform$Transforms$$postErasureLazy();

    public RefChecks refChecks();

    public UnCurry uncurry();

    public Erasure erasure();

    public PostErasure postErasure();

    public Types.Type transformedType(Symbols.Symbol var1);

    public Types.Type transformedType(Types.Type var1);

    public class Lazy<T> {
        private final Function0<T> op;
        private T value;
        private boolean _isDefined;
        public final /* synthetic */ SymbolTable $outer;

        private T value() {
            return this.value;
        }

        private void value_$eq(T x$1) {
            this.value = x$1;
        }

        private boolean _isDefined() {
            return this._isDefined;
        }

        private void _isDefined_$eq(boolean x$1) {
            this._isDefined = x$1;
        }

        public boolean isDefined() {
            return this._isDefined();
        }

        public T force() {
            if (!this.isDefined()) {
                this.value_$eq(this.op.apply());
                this._isDefined_$eq(true);
            }
            return this.value();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$transform$Transforms$Lazy$$$outer() {
            return this.$outer;
        }

        public Lazy(SymbolTable $outer, Function0<T> op) {
            this.op = op;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this._isDefined = false;
        }
    }
}

