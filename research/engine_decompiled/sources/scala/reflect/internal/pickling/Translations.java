/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;

@ScalaSignature(bytes="\u0006\u0001)3\u0001\"\u0001\u0002\u0011\u0002\u0007\u00051B\u0012\u0002\r)J\fgn\u001d7bi&|gn\u001d\u0006\u0003\u0007\u0011\t\u0001\u0002]5dW2Lgn\u001a\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055qQ\"\u0001\u0005\n\u0005=A!AB!osJ+g\rC\u0003\u0012\u0001\u0011\u0005!#\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011Q\u0002F\u0005\u0003+!\u0011A!\u00168ji\")q\u0003\u0001C\u00011\u0005\u0019\u0012n\u001d+sK\u0016\u001c\u00160\u001c2pYBK7m\u001b7fIR\u0011\u0011\u0004\b\t\u0003\u001biI!a\u0007\u0005\u0003\u000f\t{w\u000e\\3b]\")QD\u0006a\u0001=\u0005!1m\u001c3f!\tiq$\u0003\u0002!\u0011\t\u0019\u0011J\u001c;\t\u000b]\u0001A\u0011\u0001\u0012\u0015\u0005e\u0019\u0003\"\u0002\u0013\"\u0001\u0004)\u0013\u0001\u0002;sK\u0016\u0004\"AJ\u0014\u000e\u0003\u0001I!\u0001K\u0015\u0003\tQ\u0013X-Z\u0005\u0003U\u0011\u0011Q\u0001\u0016:fKNDQ\u0001\f\u0001\u0005\u00025\n!\u0002]5dW2,'\u000fV1h)\tqb\u0006C\u00030W\u0001\u0007A\"A\u0002sK\u001aDQ\u0001\f\u0001\u0005\u0002E\"\"A\b\u001a\t\u000bM\u0002\u0004\u0019\u0001\u001b\u0002\u0007MLX\u000e\u0005\u0002'k%\u0011ag\u000e\u0002\u0007'fl'm\u001c7\n\u0005a\"!aB*z[\n|Gn\u001d\u0005\u0006Y\u0001!\tA\u000f\u000b\u0003=mBQ\u0001P\u001dA\u0002u\n1\u0001\u001e9f!\t1c(\u0003\u0002@\u0001\n!A+\u001f9f\u0013\t\tEAA\u0003UsB,7\u000fC\u0003D\u0001\u0011\u0005A)A\u0007qS\u000e\\G.\u001a:Tk\n$\u0016m\u001a\u000b\u0003=\u0015CQ\u0001\n\"A\u0002\u0015\u0002\"a\u0012%\u000e\u0003\u0011I!!\u0013\u0003\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a")
public interface Translations {
    public boolean isTreeSymbolPickled(int var1);

    public boolean isTreeSymbolPickled(Trees.Tree var1);

    public int picklerTag(Object var1);

    public int picklerTag(Symbols.Symbol var1);

    public int picklerTag(Types.Type var1);

    public int picklerSubTag(Trees.Tree var1);
}

