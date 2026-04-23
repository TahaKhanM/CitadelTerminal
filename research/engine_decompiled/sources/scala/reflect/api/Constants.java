/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(bytes="\u0006\u000153\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\"\u0013\u0002\n\u0007>t7\u000f^1oiNT!a\u0001\u0003\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\t\u0015)\u0002A!\u0001\u0017\u0005!\u0019uN\\:uC:$\u0018CA\f\u001b!\tY\u0001$\u0003\u0002\u001a\r\t!a*\u001e7m%\rY\"\"\b\u0004\u00059\u0001\u0001!D\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002\u001f?5\t\u0001AB\u0003!\u0001\u0005\u0005\u0011EA\u0006D_:\u001cH/\u00198u\u0003BL7CA\u0010\u000b\u0011\u0015\u0019s\u0004\"\u0001%\u0003\u0019a\u0014N\\5u}Q\tQ\u0004C\u0004'?\t\u0007i\u0011A\u0014\u0002\u000bY\fG.^3\u0016\u0003!\u0002\"aC\u0015\n\u0005)2!aA!os\")Af\bD\u0001[\u0005\u0019A\u000f]3\u0016\u00039\u0002\"AH\u0018\n\u0005A\n$\u0001\u0002+za\u0016L!A\r\u0002\u0003\u000bQK\b/Z:\t\u000fQ\u0002!\u0019!D\u0001k\u0005A1i\u001c8ti\u0006tG/F\u00017!\tqrGB\u00039\u0001\u0005\u0005\u0011HA\tD_:\u001cH/\u00198u\u000bb$(/Y2u_J\u001c\"a\u000e\u0006\t\u000b\r:D\u0011A\u001e\u0015\u0003YBQ!P\u001c\u0007\u0002y\nQ!\u00199qYf$\"a\u0010!\u0011\u0005y!\u0002\"\u0002\u0014=\u0001\u0004A\u0003\"\u0002\"8\r\u0003\u0019\u0015aB;oCB\u0004H.\u001f\u000b\u0003\t\u001e\u00032aC#)\u0013\t1eA\u0001\u0004PaRLwN\u001c\u0005\u0006\u0011\u0006\u0003\raP\u0001\u0004CJ<\u0007C\u0001&L\u001b\u0005\u0011\u0011B\u0001'\u0003\u0005!)f.\u001b<feN,\u0007")
public interface Constants {
    public ConstantExtractor Constant();

    public static abstract class ConstantApi {
        public final /* synthetic */ Universe $outer;

        public abstract Object value();

        public abstract Types.TypeApi tpe();

        public /* synthetic */ Universe scala$reflect$api$Constants$ConstantApi$$$outer() {
            return this.$outer;
        }

        public ConstantApi(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }

    public static abstract class ConstantExtractor {
        public final /* synthetic */ Universe $outer;

        public abstract ConstantApi apply(Object var1);

        public abstract Option<Object> unapply(ConstantApi var1);

        public /* synthetic */ Universe scala$reflect$api$Constants$ConstantExtractor$$$outer() {
            return this.$outer;
        }

        public ConstantExtractor(Universe $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

