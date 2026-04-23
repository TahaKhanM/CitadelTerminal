/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Option;
import scala.Predef$;
import scala.collection.immutable.StringOps;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.FatalError;
import scala.reflect.internal.MissingRequirementError$;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001i3A!\u0001\u0002\u0001\u0013\t9R*[:tS:<'+Z9vSJ,W.\u001a8u\u000bJ\u0014xN\u001d\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0002\n\u00055\u0011!A\u0003$bi\u0006dWI\u001d:pe\"Iq\u0002\u0001B\u0001B\u0003%\u0001\u0003G\u0001\u0004[N<\u0007CA\t\u0016\u001d\t\u00112#D\u0001\u0007\u0013\t!b!\u0001\u0004Qe\u0016$WMZ\u0005\u0003-]\u0011aa\u0015;sS:<'B\u0001\u000b\u0007\u0013\tyA\u0002C\u0003\u001b\u0001\u0011%1$\u0001\u0004=S:LGO\u0010\u000b\u00039u\u0001\"a\u0003\u0001\t\u000b=I\u0002\u0019\u0001\t\t\u000b}\u0001A\u0011\u0001\u0011\u0002\u0007I,\u0017/F\u0001\u0011\u000f\u0015\u0011#\u0001#\u0001$\u0003]i\u0015n]:j]\u001e\u0014V-];je\u0016lWM\u001c;FeJ|'\u000f\u0005\u0002\fI\u0019)\u0011A\u0001E\u0001KM\u0019AEJ\u0015\u0011\u0005I9\u0013B\u0001\u0015\u0007\u0005\u0019\te.\u001f*fMB\u0011!CK\u0005\u0003W\u0019\u0011AbU3sS\u0006d\u0017N_1cY\u0016DQA\u0007\u0013\u0005\u00025\"\u0012a\t\u0005\b_\u0011\u0012\r\u0011\"\u00031\u0003\u0019\u0019XO\u001a4jqV\t\u0011\u0007\u0005\u00023o5\t1G\u0003\u00025k\u0005!A.\u00198h\u0015\u00051\u0014\u0001\u00026bm\u0006L!AF\u001a\t\re\"\u0003\u0015!\u00032\u0003\u001d\u0019XO\u001a4jq\u0002BQa\u000f\u0013\u0005\u0002q\naa]5h]\u0006dGCA\u001fA!\t\u0011b(\u0003\u0002@\r\t9aj\u001c;iS:<\u0007\"B\b;\u0001\u0004\u0001\u0002\"\u0002\"%\t\u0003\u0019\u0015\u0001\u00038pi\u001a{WO\u001c3\u0015\u0005u\"\u0005\"B\u0010B\u0001\u0004\u0001\u0002\"\u0002$%\t\u00039\u0015aB;oCB\u0004H.\u001f\u000b\u0003\u0011.\u00032AE%\u0011\u0013\tQeA\u0001\u0004PaRLwN\u001c\u0005\u0006\u0019\u0016\u0003\r!T\u0001\u0002qB\u0011a*\u0015\b\u0003%=K!\u0001\u0015\u0004\u0002\u000fA\f7m[1hK&\u0011!k\u0015\u0002\n)\"\u0014xn^1cY\u0016T!\u0001\u0015\u0004\t\u000fU#\u0013\u0011!C\u0005-\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\u00059\u0006C\u0001\u001aY\u0013\tI6G\u0001\u0004PE*,7\r\u001e")
public class MissingRequirementError
extends FatalError {
    public static Option<String> unapply(Throwable throwable) {
        return MissingRequirementError$.MODULE$.unapply(throwable);
    }

    public static Nothing$ notFound(String string2) {
        return MissingRequirementError$.MODULE$.notFound(string2);
    }

    public static Nothing$ signal(String string2) {
        return MissingRequirementError$.MODULE$.signal(string2);
    }

    public String req() {
        String string2;
        if (super.msg().endsWith(MissingRequirementError$.MODULE$.scala$reflect$internal$MissingRequirementError$$suffix())) {
            String string3 = super.msg();
            Predef$ predef$ = Predef$.MODULE$;
            string2 = (String)new StringOps(string3).dropRight(MissingRequirementError$.MODULE$.scala$reflect$internal$MissingRequirementError$$suffix().length());
        } else {
            string2 = super.msg();
        }
        return string2;
    }

    public MissingRequirementError(String msg) {
        super(msg);
    }
}

