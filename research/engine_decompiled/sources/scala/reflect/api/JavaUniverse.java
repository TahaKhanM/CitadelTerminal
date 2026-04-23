/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirrors;

@ScalaSignature(bytes="\u0006\u0001y3q!\u0001\u0002\u0011\u0002\u0007\u0005\u0011B\u0001\u0007KCZ\fWK\\5wKJ\u001cXM\u0003\u0002\u0004\t\u0005\u0019\u0011\r]5\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0003\u0013\ti!A\u0001\u0005V]&4XM]:f\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\u0013'5\ta!\u0003\u0002\u0015\r\t!QK\\5u\u000b\u00111\u0002\u0001A\f\u0003\u0019I+h\u000e^5nK\u000ec\u0017m]:1\u0005a\u0011\u0003cA\r\u001fA5\t!D\u0003\u0002\u001c9\u0005!A.\u00198h\u0015\u0005i\u0012\u0001\u00026bm\u0006L!a\b\u000e\u0003\u000b\rc\u0017m]:\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\nGU\t\t\u0011!A\u0003\u0002\u0011\u00121a\u0018\u00132#\t)\u0003\u0006\u0005\u0002\u0013M%\u0011qE\u0002\u0002\b\u001d>$\b.\u001b8h!\t\u0011\u0012&\u0003\u0002+\r\t\u0019\u0011I\\=\t\u000f1\u0002!\u0019!C\u0002[\u0005y!+\u001e8uS6,7\t\\1tgR\u000bw-F\u0001/!\ry\u0003GM\u0007\u0002\t%\u0011\u0011\u0007\u0002\u0002\t\u00072\f7o\u001d+bOB\u00111'F\u0007\u0002\u0001!1Q\u0007\u0001Q\u0001\n9\n\u0001CU;oi&lWm\u00117bgN$\u0016m\u001a\u0011\u0005\u000b]\u0002!\u0011\t\u001d\u0003\r5K'O]8s#\tID\b\u0005\u0002\u0013u%\u00111H\u0002\u0002\u0005\u001dVdG\u000e\u0005\u00024{\u00199a\b\u0001I\u0001\u0004\u0003y$A\u0003&bm\u0006l\u0015N\u001d:peN\u0019Q\b\u0011\"\u0011\u0007-\t5'\u0003\u00028\u0005A\u00111gQ\u0005\u0003\t\u0016\u0013QBU;oi&lW-T5se>\u0014\u0018B\u0001$\u0003\u0005\u001di\u0015N\u001d:peNDQaD\u001f\u0005\u0002AAq!S\u001fC\u0002\u001b\u0005!*A\u0006dY\u0006\u001c8\u000fT8bI\u0016\u0014X#A&\u0011\u0005ea\u0015BA'\u001b\u0005-\u0019E.Y:t\u0019>\fG-\u001a:\t\u000b=kD\u0011\t)\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0015\t\u0003%Vs!AE*\n\u0005Q3\u0011A\u0002)sK\u0012,g-\u0003\u0002W/\n11\u000b\u001e:j]\u001eT!\u0001\u0016\u0004\t\u000be\u0003a\u0011\u0001.\u0002\u001bI,h\u000e^5nK6K'O]8s)\tYF\f\u0005\u00024m!)Q\f\u0017a\u0001\u0017\u0006\u00111\r\u001c")
public interface JavaUniverse {
    public void scala$reflect$api$JavaUniverse$_setter_$RuntimeClassTag_$eq(ClassTag var1);

    public ClassTag<Class<?>> RuntimeClassTag();

    public JavaMirror runtimeMirror(ClassLoader var1);

    public interface JavaMirror
    extends Mirrors.RuntimeMirror {
        public ClassLoader classLoader();

        public String toString();

        public /* synthetic */ JavaUniverse scala$reflect$api$JavaUniverse$JavaMirror$$$outer();
    }
}

