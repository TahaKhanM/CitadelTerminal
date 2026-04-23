/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Phase;
import scala.reflect.internal.settings.MutableSettings;

@ScalaSignature(bytes="\u0006\u0001m2\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\u0002\u000f\u0002\t%\u0016\fX/\u001b:fI*\u00111\u0001B\u0001\tS:$XM\u001d8bY*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001A\n\u0003\u0001)\u0001\"a\u0003\u0007\u000e\u0003\u0019I!!\u0004\u0004\u0003\r\u0005s\u0017PU3g\u0011\u0015y\u0001\u0001\"\u0001\u0011\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0003\u0005\u0002\f%%\u00111C\u0002\u0002\u0005+:LG\u000fC\u0003\u0016\u0001\u0019\u0005a#\u0001\u0007qS\u000e\\G.\u001a:QQ\u0006\u001cX-F\u0001\u0018!\tA\u0012$D\u0001\u0003\u0013\tQ\"AA\u0003QQ\u0006\u001cX\rC\u0003\u001d\u0001\u0019\u0005a#\u0001\u0007fe\u0006\u001cXO]3QQ\u0006\u001cX\rC\u0003\u001f\u0001\u0019\u0005q$\u0001\u0005tKR$\u0018N\\4t+\u0005\u0001\u0003CA\u0011$\u001b\u0005\u0011#B\u0001\u0010\u0003\u0013\t!#EA\bNkR\f'\r\\3TKR$\u0018N\\4t\u0011\u00151\u0003\u0001\"\u0001(\u000391wN]%oi\u0016\u0014\u0018m\u0019;jm\u0016,\u0012\u0001\u000b\t\u0003\u0017%J!A\u000b\u0004\u0003\u000f\t{w\u000e\\3b]\"\"Q\u0005L\u00182!\tYQ&\u0003\u0002/\r\tQA-\u001a9sK\u000e\fG/\u001a3\"\u0003A\nQ)\u00138uKJ\f7\r^5wK\u0002J7\u000fI5na2,W.\u001a8uK\u0012\u0004s/\u001b;iA\u0005\u00043-^:u_6\u0004s\t\\8cC2\\\u0004\u0005\u001e5jg\u00022G.Y4!SN\u0004\u0013n\u001a8pe\u0016$\u0017%\u0001\u001a\u0002\rIr\u0013'\r\u00181\u0011\u0015!\u0004\u0001\"\u0001(\u0003-1wN]*dC2\fGm\\2)\tMbc'M\u0011\u0002o\u0005\u00115kY1mC\u0012|7\rI5tA%l\u0007\u000f\\3nK:$X\r\u001a\u0011xSRD\u0007%\u0019\u0011dkN$x.\u001c\u0011HY>\u0014\u0017\r\\\u001e!i\"L7\u000f\t4mC\u001e\u0004\u0013n\u001d\u0011jO:|'/\u001a3\u0011\u0005aI\u0014B\u0001\u001e\u0003\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3")
public interface Required {
    public Phase picklerPhase();

    public Phase erasurePhase();

    public MutableSettings settings();

    public boolean forInteractive();

    public boolean forScaladoc();
}

