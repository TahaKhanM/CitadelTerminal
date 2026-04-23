/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function2;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

@ScalaSignature(bytes="\u0006\u0001U4\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\"\u001d\u0002\u0017\u000bbL7\u000f^3oi&\fGn]!oIN[w\u000e\\3ng*\u00111\u0001B\u0001\tS:$XM\u001d8bY*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LG\u000fC\u0003\u0016\u0001\u0011\u0005a#\u0001\neKJLg/\u001a$sKND7k[8mK6\u001cHCA\f%!\rA2D\b\b\u0003\u0017eI!A\u0007\u0004\u0002\u000fA\f7m[1hK&\u0011A$\b\u0002\u0005\u0019&\u001cHO\u0003\u0002\u001b\rA\u0011q\u0004I\u0007\u0002\u0001%\u0011\u0011E\t\u0002\u0007'fl'm\u001c7\n\u0005\r\u0012!aB*z[\n|Gn\u001d\u0005\u0006KQ\u0001\raF\u0001\biB\f'/Y7t\u0011\u00159\u0003\u0001\"\u0001)\u00039I7OU1x!\u0006\u0014\u0018-\\3uKJ$\"!\u000b\u0017\u0011\u0005-Q\u0013BA\u0016\u0007\u0005\u001d\u0011un\u001c7fC:DQ!\f\u0014A\u0002y\t1a]=n\u0011\u0015y\u0003\u0001\"\u00031\u0003\u0001*\u00070[:uK:$\u0018.\u00197C_VtGm]#yG2,H-\u001b8h\u0011&$G-\u001a8\u0015\u0005Ej\u0004\u0003\u0002\u001a6=ar!aC\u001a\n\u0005Q2\u0011A\u0002)sK\u0012,g-\u0003\u00027o\t\u0019Q*\u00199\u000b\u0005Q2\u0001CA\u0010:\u0013\tQ4H\u0001\u0003UsB,\u0017B\u0001\u001f\u0003\u0005\u0015!\u0016\u0010]3t\u0011\u0015qd\u00061\u0001\u0018\u0003\u0019A\u0017\u000e\u001a3f]\")\u0001\t\u0001C\u0003\u0003\u0006!R\r_5ti\u0016tG/[1m)J\fgn\u001d4pe6,\"A\u0011$\u0015\t\r#f\u000b\u0017\u000b\u0003\t>\u0003\"!\u0012$\r\u0001\u0011)qi\u0010b\u0001\u0011\n\tA+\u0005\u0002J\u0019B\u00111BS\u0005\u0003\u0017\u001a\u0011qAT8uQ&tw\r\u0005\u0002\f\u001b&\u0011aJ\u0002\u0002\u0004\u0003:L\b\"\u0002)@\u0001\u0004\t\u0016aB2sK\u0006$xN\u001d\t\u0006\u0017I;\u0002\bR\u0005\u0003'\u001a\u0011\u0011BR;oGRLwN\u001c\u001a\t\u000bU{\u0004\u0019A\f\u0002\u000fI\fwoU=ng\")qk\u0010a\u0001q\u0005\u0011A\u000f\u001d\u0005\b3~\u0002\n\u00111\u0001\u001f\u0003!\u0011\u0018m^(x]\u0016\u0014\b\"B.\u0001\t\u000ba\u0016a\u00039bG.\u001c\u00160\u001c2pYN$B\u0001O/_?\")aH\u0017a\u0001/!)qK\u0017a\u0001q!9\u0011L\u0017I\u0001\u0002\u0004q\u0002bB1\u0001#\u0003%)AY\u0001\u001fKbL7\u000f^3oi&\fG\u000e\u0016:b]N4wN]7%I\u00164\u0017-\u001e7uIM*\"a\u00198\u0016\u0003\u0011T#AH3,\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0013Ut7\r[3dW\u0016$'BA6\u0007\u0003)\tgN\\8uCRLwN\\\u0005\u0003[\"\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u00159\u0005M1\u0001I\u0011\u001d\u0001\b!%A\u0005\u0006\r\fQ\u0003]1dWNKXNY8mg\u0012\"WMZ1vYR$3\u0007\u0005\u0002sg6\t!!\u0003\u0002u\u0005\tY1+_7c_2$\u0016M\u00197f\u0001")
public interface ExistentialsAndSkolems {
    public List<Symbols.Symbol> deriveFreshSkolems(List<Symbols.Symbol> var1);

    public boolean isRawParameter(Symbols.Symbol var1);

    public <T> T existentialTransform(List<Symbols.Symbol> var1, Types.Type var2, Symbols.Symbol var3, Function2<List<Symbols.Symbol>, Types.Type, T> var4);

    public <T> Symbols.Symbol existentialTransform$default$3();

    public Types.Type packSymbols(List<Symbols.Symbol> var1, Types.Type var2, Symbols.Symbol var3);

    public Symbols.Symbol packSymbols$default$3();
}

