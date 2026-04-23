/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.runtime.BoxedUnit;
import scala.runtime.Null$;

@ScalaSignature(bytes="\u0006\u0001\u0005-a\u0001C\u0001\u0003!\u0003\r\n!C@\u0003\u0013\u0015C\bO]+uS2\u001c(BA\u0002\u0005\u0003\u0019i\u0017m\u0019:pg*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001A\"\u0001\u0011\u0003-a\u0017\u000e^3sC2tU\u000f\u001c7\u0016\u0003E\u00012AE\n\u0018\u001b\u0005\u0001\u0011B\u0001\u000b\u0016\u0005\u0011)\u0005\u0010\u001d:\n\u0005Y\u0011!aB!mS\u0006\u001cXm\u001d\t\u0003\u0017aI!!\u0007\u0004\u0003\t9+H\u000e\u001c\u0015\u0005\u001dmq\u0002\u0005\u0005\u0002\f9%\u0011QD\u0002\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017%A\u0010\u0002/U\u001bX\rI9vCNL\u0017/^8uKN\u0004\u0013N\\:uK\u0006$\u0017%A\u0011\u0002\rIr\u0013'\r\u00181\u0011\u0015\u0019\u0003A\"\u0001%\u0003-a\u0017\u000e^3sC2,f.\u001b;\u0016\u0003\u0015\u00022AE\n'!\tYq%\u0003\u0002)\r\t!QK\\5uQ\u0011\u00113D\b\u0011\t\u000b-\u0002a\u0011\u0001\u0017\u0002\u00171LG/\u001a:bYR\u0013X/Z\u000b\u0002[A\u0019!c\u0005\u0018\u0011\u0005-y\u0013B\u0001\u0019\u0007\u0005\u001d\u0011un\u001c7fC:DCAK\u000e\u001fA!)1\u0007\u0001D\u0001Y\u0005aA.\u001b;fe\u0006dg)\u00197tK\"\"!g\u0007\u0010!\u0011\u00151\u0004A\"\u00018\u0003\u001da\u0017\u000e^3sC2$\"!\f\u001d\t\u000be*\u0004\u0019\u0001\u0018\u0002\u0003aDC!N\u000e\u001fA!)a\u0007\u0001D\u0001yQ\u0011Q(\u0011\t\u0004%Mq\u0004CA\u0006@\u0013\t\u0001eA\u0001\u0003CsR,\u0007\"B\u001d<\u0001\u0004q\u0004\u0006B\u001e\u001c=\u0001BQA\u000e\u0001\u0007\u0002\u0011#\"!R%\u0011\u0007I\u0019b\t\u0005\u0002\f\u000f&\u0011\u0001J\u0002\u0002\u0006'\"|'\u000f\u001e\u0005\u0006s\r\u0003\rA\u0012\u0015\u0005\u0007nq\u0002\u0005C\u00037\u0001\u0019\u0005A\n\u0006\u0002N#B\u0019!c\u0005(\u0011\u0005-y\u0015B\u0001)\u0007\u0005\rIe\u000e\u001e\u0005\u0006s-\u0003\rA\u0014\u0015\u0005\u0017nq\u0002\u0005C\u00037\u0001\u0019\u0005A\u000b\u0006\u0002V3B\u0019!c\u0005,\u0011\u0005-9\u0016B\u0001-\u0007\u0005\u0011auN\\4\t\u000be\u001a\u0006\u0019\u0001,)\tM[b\u0004\t\u0005\u0006m\u00011\t\u0001\u0018\u000b\u0003;\u0006\u00042AE\n_!\tYq,\u0003\u0002a\r\t)a\t\\8bi\")\u0011h\u0017a\u0001=\"\"1l\u0007\u0010!\u0011\u00151\u0004A\"\u0001e)\t)\u0017\u000eE\u0002\u0013'\u0019\u0004\"aC4\n\u0005!4!A\u0002#pk\ndW\rC\u0003:G\u0002\u0007a\r\u000b\u0003d7y\u0001\u0003\"\u0002\u001c\u0001\r\u0003aGCA7v!\r\u00112C\u001c\t\u0003_Jt!a\u00039\n\u0005E4\u0011A\u0002)sK\u0012,g-\u0003\u0002ti\n11\u000b\u001e:j]\u001eT!!\u001d\u0004\t\u000beZ\u0007\u0019\u00018)\t-\\b\u0004\t\u0005\u0006m\u00011\t\u0001\u001f\u000b\u0003sv\u00042AE\n{!\tY10\u0003\u0002}\r\t!1\t[1s\u0011\u0015It\u000f1\u0001{Q\u001198D\b\u0011\u0011\t\u0005\u0005\u0011qA\u0007\u0003\u0003\u0007Q1!!\u0002\u0003\u0003!\u0011G.Y2lE>D\u0018\u0002BA\u0005\u0003\u0007\u0011qaQ8oi\u0016DH\u000f")
public interface ExprUtils {
    public Exprs.Expr<Null$> literalNull();

    public Exprs.Expr<BoxedUnit> literalUnit();

    public Exprs.Expr<Object> literalTrue();

    public Exprs.Expr<Object> literalFalse();

    public Exprs.Expr<Object> literal(boolean var1);

    public Exprs.Expr<Object> literal(byte var1);

    public Exprs.Expr<Object> literal(short var1);

    public Exprs.Expr<Object> literal(int var1);

    public Exprs.Expr<Object> literal(long var1);

    public Exprs.Expr<Object> literal(float var1);

    public Exprs.Expr<Object> literal(double var1);

    public Exprs.Expr<String> literal(String var1);

    public Exprs.Expr<Object> literal(char var1);
}

