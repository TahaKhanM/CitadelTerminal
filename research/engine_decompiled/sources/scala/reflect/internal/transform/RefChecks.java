/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;

@ScalaSignature(bytes="\u0006\u0001A2q!\u0001\u0002\u0011\u0002\u0007\u00051BA\u0005SK\u001a\u001c\u0005.Z2lg*\u00111\u0001B\u0001\niJ\fgn\u001d4pe6T!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u000fI,g\r\\3di*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u000f\u001b\u0005A\u0011BA\b\t\u0005\u0019\te.\u001f*fM\")\u0011\u0003\u0001C\u0001%\u00051A%\u001b8ji\u0012\"\u0012a\u0005\t\u0003\u001bQI!!\u0006\u0005\u0003\tUs\u0017\u000e\u001e\u0005\b/\u0001\u0011\rQ\"\u0001\u0019\u0003\u00199Gn\u001c2bYV\t\u0011\u0004\u0005\u0002\u001b75\tA!\u0003\u0002\u001d\t\tY1+_7c_2$\u0016M\u00197f\u0011\u0015q\u0002\u0001\"\u0001 \u00035!(/\u00198tM>\u0014X.\u00138g_R\u0019\u0001e\n\u0018\u0011\u0005\u0005\u001acB\u0001\u0012\u0017\u001b\u0005\u0001\u0011B\u0001\u0013&\u0005\u0011!\u0016\u0010]3\n\u0005\u0019\"!!\u0002+za\u0016\u001c\b\"\u0002\u0015\u001e\u0001\u0004I\u0013aA:z[B\u0011\u0011EK\u0005\u0003W1\u0012aaU=nE>d\u0017BA\u0017\u0005\u0005\u001d\u0019\u00160\u001c2pYNDQaL\u000fA\u0002\u0001\n!\u0001\u001e9")
public interface RefChecks {
    public SymbolTable global();

    public Types.Type transformInfo(Symbols.Symbol var1, Types.Type var2);
}

