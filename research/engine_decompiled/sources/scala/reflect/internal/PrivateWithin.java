/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.reflect.Member;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Symbols;

@ScalaSignature(bytes="\u0006\u0001M3\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\u0002\u0015\u0002\u000e!JLg/\u0019;f/&$\b.\u001b8\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0001\"a\u0003\n\n\u0005M1!\u0001B+oSRDQ!\u0006\u0001\u0005\u0002Y\t\u0001\u0004\u001d:pa\u0006<\u0017\r^3QC\u000e\\\u0017mZ3C_VtG-\u0019:z)\r\tr#\f\u0005\u00061Q\u0001\r!G\u0001\u0002GB\u0012!\u0004\n\t\u00047\u0001\u0012S\"\u0001\u000f\u000b\u0005uq\u0012\u0001\u00027b]\u001eT\u0011aH\u0001\u0005U\u00064\u0018-\u0003\u0002\"9\t)1\t\\1tgB\u00111\u0005\n\u0007\u0001\t%)s#!A\u0001\u0002\u000b\u0005aEA\u0002`IE\n\"a\n\u0016\u0011\u0005-A\u0013BA\u0015\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aC\u0016\n\u000512!aA!os\")a\u0006\u0006a\u0001_\u0005!1/_7t!\rY\u0001GM\u0005\u0003c\u0019\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?!\t\u0019D'D\u0001\u0001\u0013\t)dG\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0003o\t\u0011qaU=nE>d7\u000fC\u0003\u0016\u0001\u0011\u0005\u0011\bF\u0002\u0012u\u0005CQa\u000f\u001dA\u0002q\n\u0011!\u001c\t\u0003{}j\u0011A\u0010\u0006\u0003\u000bqI!\u0001\u0011 \u0003\r5+WNY3s\u0011\u0015q\u0003\b1\u00010\u0011\u0015)\u0002\u0001\"\u0001D)\r\tBI\u0013\u0005\u0006\u000b\n\u0003\rAR\u0001\u0007U\u001ad\u0017mZ:\u0011\u0005\u001dCU\"\u0001\u0002\n\u0005%\u0013!\u0001\u0004&bm\u0006\f5m\u0019$mC\u001e\u001c\b\"\u0002\u0018C\u0001\u0004y\u0003\"\u0002'\u0001\t\u0003i\u0015\u0001G:fiB\u000b7m[1hK\u0006\u001b7-Z:t\u0005>,h\u000eZ1ssR\u0011!G\u0014\u0005\u0006\u001f.\u0003\rAM\u0001\u0004gfl\u0007CA$R\u0013\t\u0011&AA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007")
public interface PrivateWithin {
    public void propagatePackageBoundary(Class<?> var1, Seq<Symbols.Symbol> var2);

    public void propagatePackageBoundary(Member var1, Seq<Symbols.Symbol> var2);

    public void propagatePackageBoundary(int var1, Seq<Symbols.Symbol> var2);

    public Symbols.Symbol setPackageAccessBoundary(Symbols.Symbol var1);
}

