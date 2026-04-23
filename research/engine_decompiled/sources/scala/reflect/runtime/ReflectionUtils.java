/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function1;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.io.AbstractFile;
import scala.reflect.runtime.ReflectionUtils$;

@ScalaSignature(bytes="\u0006\u0001\t\rq!B\u0001\u0003\u0011\u0003I\u0011a\u0004*fM2,7\r^5p]V#\u0018\u000e\\:\u000b\u0005\r!\u0011a\u0002:v]RLW.\u001a\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AC\u0006\u000e\u0003\t1Q\u0001\u0004\u0002\t\u00025\u0011qBU3gY\u0016\u001cG/[8o+RLGn]\n\u0003\u00179\u0001\"a\u0004\t\u000e\u0003\u0019I!!\u0005\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015\u00192\u0002\"\u0001\u0015\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0002C\u0003\u0017\u0017\u0011\u0005q#A\bv]^\u0014\u0018\r\u001d+ie><\u0018M\u00197f)\tAr\u0004\u0005\u0002\u001a99\u0011qBG\u0005\u00037\u0019\tq\u0001]1dW\u0006<W-\u0003\u0002\u001e=\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u00037\u0019AQ\u0001I\u000bA\u0002a\t\u0011\u0001\u001f\u0005\u0006E-!\taI\u0001\u000ek:<(/\u00199IC:$G.\u001a:\u0016\u0005\u0011RCCA\u00134!\u0011ya\u0005\u0007\u0015\n\u0005\u001d2!a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0011\u0005%RC\u0002\u0001\u0003\u0006W\u0005\u0012\r\u0001\f\u0002\u0002)F\u0011Q\u0006\r\t\u0003\u001f9J!a\f\u0004\u0003\u000f9{G\u000f[5oOB\u0011q\"M\u0005\u0003e\u0019\u00111!\u00118z\u0011\u0015!\u0014\u00051\u0001&\u0003\t\u0001h\rC\u00037\u0017\u0011\u0005q'\u0001\u0003tQ><HC\u0001\u001d@!\tIDH\u0004\u0002\u0010u%\u00111HB\u0001\u0007!J,G-\u001a4\n\u0005ur$AB*ue&twM\u0003\u0002<\r!)\u0001)\u000ea\u0001\u0003\u0006\u00111\r\u001c\t\u0003\u0005\u001ek\u0011a\u0011\u0006\u0003\t\u0016\u000bA\u0001\\1oO*\ta)\u0001\u0003kCZ\f\u0017B\u0001%D\u0005-\u0019E.Y:t\u0019>\fG-\u001a:\t\u000b)[A\u0011A&\u0002/M$\u0018\r^5d'&tw\r\\3u_:Len\u001d;b]\u000e,Gc\u0001\bM\u001b\")\u0001)\u0013a\u0001\u0003\")a*\u0013a\u0001q\u0005I1\r\\1tg:\u000bW.\u001a\u0005\u0006\u0015.!\t\u0001\u0015\u000b\u0003\u001dECQAU(A\u0002M\u000bQa\u00197buj\u0004$\u0001\u0016-\u0011\u0007e*v+\u0003\u0002W}\t)1\t\\1tgB\u0011\u0011\u0006\u0017\u0003\n3F\u000b\t\u0011!A\u0003\u00021\u00121a\u0018\u00133\u0011\u0015Y6\u0002\"\u0001]\u0003YIgN\\3s'&tw\r\\3u_:Len\u001d;b]\u000e,Gc\u0001\b^?\")aL\u0017a\u0001\u001d\u0005)q.\u001e;fe\")aJ\u0017a\u0001q!)\u0011m\u0003C\u0001E\u0006)\u0012n\u001d+sC&$\u0018*\u001c9mK6,g\u000e^1uS>tGCA2g!\tyA-\u0003\u0002f\r\t9!i\\8mK\u0006t\u0007\"B4a\u0001\u0004A\u0014\u0001\u00034jY\u0016t\u0015-\\3\t\u000b%\\A\u0011\u00016\u00027M\u001c\u0017\r\\1d'\"|W\u000f\u001c3oi2{\u0017\rZ\"mCN\u001ch-\u001b7f)\t\u00197\u000eC\u0003hQ\u0002\u0007\u0001\bC\u0003n\u0017\u0011\u0005a.A\ftG\u0006d\u0017mY*i_VdGM\u001c;M_\u0006$7\t\\1tgR\u00111m\u001c\u0005\u0006a2\u0004\r!]\u0001\u0005]\u0006lW\r\u0005\u0002sqB\u00111O^\u0007\u0002i*\u0011Q\u000fB\u0001\tS:$XM\u001d8bY&\u0011q\u000f\u001e\u0002\f'fl'm\u001c7UC\ndW-\u0003\u0002zu\n!a*Y7f\u0013\tYHOA\u0003OC6,7oB\u0003~\u0017!\u0005a0\u0001\tQe&l\u0017\u000e^5wK>\u0013\u0018I\u001d:bsB\u0019q0!\u0001\u000e\u0003-1q!a\u0001\f\u0011\u0003\t)A\u0001\tQe&l\u0017\u000e^5wK>\u0013\u0018I\u001d:bsN\u0019\u0011\u0011\u0001\b\t\u000fM\t\t\u0001\"\u0001\u0002\nQ\ta\u0010\u0003\u0005\u0002\u000e\u0005\u0005A\u0011AA\b\u0003\u001d)h.\u00199qYf$2aYA\t\u0011!\t\u0019\"a\u0003A\u0002\u0005U\u0011A\u00026dY\u0006T(\u0010\r\u0003\u0002\u0018\u0005u\u0001#\u0002\"\u0002\u001a\u0005m\u0011B\u0001,D!\rI\u0013Q\u0004\u0003\f\u0003?\t\t\"!A\u0001\u0002\u000b\u0005AFA\u0002`IQ2a!a\t\f\u0001\u0005\u0015\"AC#oG2|7/\u001a3J]V!\u0011qEA '\r\t\tC\u0004\u0005\f\u0003W\t\tC!A!\u0002\u0013\ti#A\u0005f]\u000edwn];sKB9q\"a\f\u00024\u0005u\u0012bAA\u0019\r\tIa)\u001e8di&|g.\r\u0019\u0005\u0003k\tI\u0004E\u0003C\u00033\t9\u0004E\u0002*\u0003s!1\"a\u000f\u0002*\u0005\u0005\t\u0011!B\u0001Y\t\u0019q\fJ\u001b\u0011\u0007%\ny\u0004\u0002\u0004,\u0003C\u0011\r\u0001\f\u0005\b'\u0005\u0005B\u0011AA\")\u0011\t)%a\u0012\u0011\u000b}\f\t#!\u0010\t\u0011\u0005-\u0012\u0011\ta\u0001\u0003\u0013\u0002raDA\u0018\u0003\u0017\ni\u0004\r\u0003\u0002N\u0005E\u0003#\u0002\"\u0002\u001a\u0005=\u0003cA\u0015\u0002R\u0011Y\u00111HA$\u0003\u0003\u0005\tQ!\u0001-\u0011!\ti!!\t\u0005\u0002\u0005UC\u0003BA,\u0003;\u0002RaDA-\u0003{I1!a\u0017\u0007\u0005\u0019y\u0005\u000f^5p]\"A\u00111CA*\u0001\u0004\ty\u0006\r\u0003\u0002b\u0005\u0015\u0004#\u0002\"\u0002\u001a\u0005\r\u0004cA\u0015\u0002f\u0011Y\u0011qMA/\u0003\u0003\u0005\tQ!\u0001-\u0005\ryFEN\u0004\b\u0003WZ\u0001\u0012AA7\u0003A)en\u00197pg\u0016$\u0017J\\'fi\"|G\rE\u0002\u0000\u0003_2q!!\u001d\f\u0011\u0003\t\u0019H\u0001\tF]\u000edwn]3e\u0013:lU\r\u001e5pIN!\u0011qNA;!\u0015y\u0018\u0011EA<!\u0011\tI(! \u000e\u0005\u0005m$BA\u0003D\u0013\u0011\ty(a\u001f\u0003\r5+G\u000f[8e\u0011\u001d\u0019\u0012q\u000eC\u0001\u0003\u0007#\"!!\u001c\b\u000f\u0005\u001d5\u0002#\u0001\u0002\n\u0006)RI\\2m_N,G-\u00138D_:\u001cHO];di>\u0014\bcA@\u0002\f\u001a9\u0011QR\u0006\t\u0002\u0005=%!F#oG2|7/\u001a3J]\u000e{gn\u001d;sk\u000e$xN]\n\u0005\u0003\u0017\u000b\t\nE\u0003\u0000\u0003C\t\u0019\n\r\u0003\u0002\u0016\u0006u\u0005CBA=\u0003/\u000bY*\u0003\u0003\u0002\u001a\u0006m$aC\"p]N$(/^2u_J\u00042!KAO\t-\ty*!)\u0002\u0002\u0003\u0005)\u0011\u0001\u0017\u0003\u0005}\u0002\u0004BCAR\u0003K\u000b\t\u0011!\u0001\u0002*\u0006AA%\u00198p]\u001a,h\u000e\u0003\u0004\u0002(.\u0001\u0011\u0011V\u0001\u0018y1|7-\u00197!%\u00164G.Z2uS>tW\u000b^5mgzZ\u0001\u0001C\u0004\u0014\u0003\u0017#\t!!,\u0015\u0005\u0005%uaBAY\u0017!\u0005\u00111W\u0001\u0010\u000b:\u001cGn\\:fI&s7\t\\1tgB\u0019q0!.\u0007\u000f\u0005]6\u0002#\u0001\u0002:\nyQI\\2m_N,G-\u00138DY\u0006\u001c8o\u0005\u0003\u00026\u0006m\u0006#B@\u0002\"\u0005u\u0006\u0007BA`\u0003\u0007\u0004RAQA\r\u0003\u0003\u00042!KAb\t-\ty*!2\u0002\u0002\u0003\u0005)\u0011\u0001\u0017\t\u0015\u0005\r\u0016qYA\u0001\u0002\u0003\tI\u000b\u0003\u0004\u0002(.\u0001\u0011\u0011\u0016\u0005\b'\u0005UF\u0011AAf)\t\t\u0019lB\u0004\u0002P.A\t!!5\u0002#\u0015s7\r\\8tK\u0012Le\u000eU1dW\u0006<W\rE\u0002\u0000\u0003'4q!!6\f\u0011\u0003\t9NA\tF]\u000edwn]3e\u0013:\u0004\u0016mY6bO\u0016\u001cB!a5\u0002ZB)q0!\t\u0002\\B\u0019!)!8\n\u0007\u0005}7IA\u0004QC\u000e\\\u0017mZ3\t\u000fM\t\u0019\u000e\"\u0001\u0002dR\u0011\u0011\u0011\u001b\u0005\b\u0003O\\A\u0011AAu\u00039\t7o]8dS\u0006$X\r\u001a$jY\u0016$B!a;\u0002xB!\u0011Q^Az\u001b\t\tyOC\u0002\u0002r\u0012\t!![8\n\t\u0005U\u0018q\u001e\u0002\r\u0003\n\u001cHO]1di\u001aKG.\u001a\u0005\b%\u0006\u0015\b\u0019AA}a\u0011\tY0a@\u0011\te*\u0016Q \t\u0004S\u0005}Ha\u0003B\u0001\u0003o\f\t\u0011!A\u0003\u00021\u00121a\u0018\u00138\u0001")
public final class ReflectionUtils {
    public static AbstractFile associatedFile(Class<?> clazz) {
        return ReflectionUtils$.MODULE$.associatedFile(clazz);
    }

    public static boolean scalacShouldntLoadClass(Names.Name name) {
        return ReflectionUtils$.MODULE$.scalacShouldntLoadClass(name);
    }

    public static boolean scalacShouldntLoadClassfile(String string2) {
        return ReflectionUtils$.MODULE$.scalacShouldntLoadClassfile(string2);
    }

    public static boolean isTraitImplementation(String string2) {
        return ReflectionUtils$.MODULE$.isTraitImplementation(string2);
    }

    public static Object innerSingletonInstance(Object object, String string2) {
        return ReflectionUtils$.MODULE$.innerSingletonInstance(object, string2);
    }

    public static Object staticSingletonInstance(Class<?> clazz) {
        return ReflectionUtils$.MODULE$.staticSingletonInstance(clazz);
    }

    public static Object staticSingletonInstance(ClassLoader classLoader, String string2) {
        return ReflectionUtils$.MODULE$.staticSingletonInstance(classLoader, string2);
    }

    public static String show(ClassLoader classLoader) {
        return ReflectionUtils$.MODULE$.show(classLoader);
    }

    public static <T> PartialFunction<Throwable, T> unwrapHandler(PartialFunction<Throwable, T> partialFunction) {
        return ReflectionUtils$.MODULE$.unwrapHandler(partialFunction);
    }

    public static Throwable unwrapThrowable(Throwable throwable) {
        return ReflectionUtils$.MODULE$.unwrapThrowable(throwable);
    }

    public static class EnclosedIn<T> {
        private final Function1<Class<?>, T> enclosure;

        public Option<T> unapply(Class<?> jclazz) {
            return Option$.MODULE$.apply(this.enclosure.apply(jclazz));
        }

        public EnclosedIn(Function1<Class<?>, T> enclosure) {
            this.enclosure = enclosure;
        }
    }
}

