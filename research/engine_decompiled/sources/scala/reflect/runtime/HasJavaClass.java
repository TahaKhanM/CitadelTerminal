/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001y2Q!\u0001\u0002\u0001\t!\u0011A\u0002S1t\u0015\u00064\u0018m\u00117bgNT!a\u0001\u0003\u0002\u000fI,h\u000e^5nK*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\fWCA\u0005\u0018'\t\u0001!\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001aD\u0001b\u0004\u0001\u0003\u0006\u0004%\t!E\u0001\tO\u0016$8\t\\1{u\u000e\u0001Q#\u0001\n\u0011\t-\u0019R\u0003I\u0005\u0003)\u0019\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005Y9B\u0002\u0001\u0003\u00061\u0001\u0011\r!\u0007\u0002\u0002\u0015F\u0011!$\b\t\u0003\u0017mI!\u0001\b\u0004\u0003\u000f9{G\u000f[5oOB\u00111BH\u0005\u0003?\u0019\u00111!\u00118za\t\t#\u0006E\u0002#O%j\u0011a\t\u0006\u0003I\u0015\nA\u0001\\1oO*\ta%\u0001\u0003kCZ\f\u0017B\u0001\u0015$\u0005\u0015\u0019E.Y:t!\t1\"\u0006B\u0005,Y\u0005\u0005\t\u0011!B\u00013\t!q\f\n\u001b5\u0011!i\u0003A!A!\u0002\u0013q\u0013!C4fi\u000ec\u0017M\u001f>!!\u0011Y1#F\u00181\u0005A\u0012\u0004c\u0001\u0012(cA\u0011aC\r\u0003\nW1\n\t\u0011!A\u0003\u0002eAQ\u0001\u000e\u0001\u0005\u0002U\na\u0001P5oSRtDC\u0001\u001c9!\r9\u0004!F\u0007\u0002\u0005!)qb\ra\u0001sA!1bE\u000b;a\tYT\bE\u0002#Oq\u0002\"AF\u001f\u0005\u0013-B\u0014\u0011!A\u0001\u0006\u0003I\u0002")
public class HasJavaClass<J> {
    private final Function1<J, Class<?>> getClazz;

    public Function1<J, Class<?>> getClazz() {
        return this.getClazz;
    }

    public HasJavaClass(Function1<J, Class<?>> getClazz) {
        this.getClazz = getClazz;
    }
}

