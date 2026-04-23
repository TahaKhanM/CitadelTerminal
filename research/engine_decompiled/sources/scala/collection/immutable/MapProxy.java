/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function1;
import scala.Tuple2;
import scala.collection.GenTraversableOnce;
import scala.collection.MapProxyLike;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005MbaB\u0001\u0003!\u0003\r\t!\u0003\u0002\t\u001b\u0006\u0004\bK]8ys*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)2AC\u000b '\u0011\u00011bD\u0011\u0011\u00051iQ\"\u0001\u0004\n\u000591!AB!osJ+g\r\u0005\u0003\u0011#MqR\"\u0001\u0002\n\u0005I\u0011!aA'baB\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0018\u0005\u0005\t\u0015C\u0001\r\u001c!\ta\u0011$\u0003\u0002\u001b\r\t9aj\u001c;iS:<\u0007C\u0001\u0007\u001d\u0013\tibAA\u0002B]f\u0004\"\u0001F\u0010\u0005\r\u0001\u0002AQ1\u0001\u0018\u0005\u0005\u0011\u0005#\u0002\u0012$'yyQ\"\u0001\u0003\n\u0005\u0011\"!\u0001D'baB\u0013x\u000e_=MS.,\u0007\"\u0002\u0014\u0001\t\u00039\u0013A\u0002\u0013j]&$H\u0005F\u0001)!\ta\u0011&\u0003\u0002+\r\t!QK\\5u\u0011\u0015a\u0003\u0001\"\u0011.\u0003\u0011\u0011X\r\u001d:\u0016\u00039\u0002B\u0001\u0005\u0001\u0014=!)\u0001\u0007\u0001C\u0005c\u0005Aa.Z<Qe>D\u00180\u0006\u00023kQ\u00111\u0007\u000f\t\u0005!\u0001\u0019B\u0007\u0005\u0002\u0015k\u0011)ag\fb\u0001o\t\u0011!)M\t\u0003=mAQ!O\u0018A\u0002i\nqA\\3x'\u0016dg\r\u0005\u0003\u0011#M!\u0004\"\u0002\u001f\u0001\t\u0003j\u0013!B3naRL\b\"\u0002 \u0001\t\u0003z\u0014aB;qI\u0006$X\rZ\u000b\u0003\u0001\u000e#2!\u0011#G!\u0011\u0001\u0002a\u0005\"\u0011\u0005Q\u0019E!\u0002\u001c>\u0005\u00049\u0004\"B#>\u0001\u0004\u0019\u0012aA6fs\")q)\u0010a\u0001\u0005\u0006)a/\u00197vK\")\u0011\n\u0001C!\u0015\u00061A%\\5okN$\"AL&\t\u000b\u0015C\u0005\u0019A\n\t\u000b5\u0003A\u0011\t(\u0002\u000b\u0011\u0002H.^:\u0016\u0005=\u0013FC\u0001)T!\u0011\u0001\u0012cE)\u0011\u0005Q\u0011F!\u0002\u001cM\u0005\u00049\u0004\"\u0002+M\u0001\u0004)\u0016AA6w!\u0011aakE)\n\u0005]3!A\u0002+va2,'\u0007C\u0003N\u0001\u0011\u0005\u0013,\u0006\u0002[;R!1LX1d!\u0011\u0001\u0002a\u0005/\u0011\u0005QiF!\u0002\u001cY\u0005\u00049\u0004\"B0Y\u0001\u0004\u0001\u0017!B3mK6\f\u0004\u0003\u0002\u0007W'qCQA\u0019-A\u0002\u0001\fQ!\u001a7f[JBQ\u0001\u001a-A\u0002\u0015\fQ!\u001a7f[N\u00042\u0001\u00044a\u0013\t9gA\u0001\u0006=e\u0016\u0004X-\u0019;fIzBQ!\u001b\u0001\u0005B)\f!\u0002\n9mkN$\u0003\u000f\\;t+\tYg\u000e\u0006\u0002m_B!\u0001\u0003A\nn!\t!b\u000eB\u00037Q\n\u0007q\u0007C\u0003qQ\u0002\u0007\u0011/\u0001\u0002ygB\u0019!E\u001d;\n\u0005M$!AE$f]R\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016\u0004B\u0001\u0004,\u0014[\")a\u000f\u0001C!o\u000611.Z=TKR,\u0012\u0001\u001f\t\u0004!e\u001c\u0012B\u0001>\u0003\u0005\r\u0019V\r\u001e\u0005\u0006y\u0002!\t%`\u0001\u000bM&dG/\u001a:LKf\u001cHCA\b\u007f\u0011\u0019y8\u00101\u0001\u0002\u0002\u0005\t\u0001\u000f\u0005\u0004\r\u0003\u0007\u0019\u0012qA\u0005\u0004\u0003\u000b1!!\u0003$v]\u000e$\u0018n\u001c82!\ra\u0011\u0011B\u0005\u0004\u0003\u00171!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003\u001f\u0001A\u0011IA\t\u0003%i\u0017\r\u001d,bYV,7/\u0006\u0003\u0002\u0014\u0005eA\u0003BA\u000b\u0003;\u0001R\u0001E\t\u0014\u0003/\u00012\u0001FA\r\t\u001d\tY\"!\u0004C\u0002]\u0011\u0011a\u0011\u0005\t\u0003?\ti\u00011\u0001\u0002\"\u0005\ta\r\u0005\u0004\r\u0003\u0007q\u0012q\u0003\u0015\b\u0001\u0005\u0015\u00121FA\u0018!\ra\u0011qE\u0005\u0004\u0003S1!A\u00033faJ,7-\u0019;fI\u0006\u0012\u0011QF\u0001F!J|\u00070_5oO\u0002J7\u000f\t3faJ,7-\u0019;fI\u0002\"W/\u001a\u0011u_\u0002b\u0017mY6!_\u001a\u0004So]3!C:$\u0007eY8na&dWM]\u0017mKZ,G\u000eI:vaB|'\u000f\u001e\u0018\"\u0005\u0005E\u0012A\u0002\u001a/cEr\u0003\u0007")
public interface MapProxy<A, B>
extends Map<A, B>,
MapProxyLike<A, B, Map<A, B>> {
    @Override
    public MapProxy<A, B> repr();

    @Override
    public MapProxy<A, B> empty();

    @Override
    public <B1> MapProxy<A, B1> updated(A var1, B1 var2);

    @Override
    public MapProxy<A, B> $minus(A var1);

    @Override
    public <B1> Map<A, B1> $plus(Tuple2<A, B1> var1);

    @Override
    public <B1> MapProxy<A, B1> $plus(Tuple2<A, B1> var1, Tuple2<A, B1> var2, Seq<Tuple2<A, B1>> var3);

    @Override
    public <B1> MapProxy<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> var1);

    @Override
    public Set<A> keySet();

    @Override
    public Map<A, B> filterKeys(Function1<A, Object> var1);

    @Override
    public <C> Map<A, C> mapValues(Function1<B, C> var1);
}

