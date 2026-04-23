/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Function0;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.macros.Universe;

@ScalaSignature(bytes="\u0006\u0001\u00055a!C\u0001\u0003!\u0003\r\t!CA\u0005\u0005%Ie\u000e^3s]\u0006d7O\u0003\u0002\u0004\t\u00051Q.Y2s_NT!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0005\b+\u0001\u0011\rQ\"\u0001\u0017\u0003!Ig\u000e^3s]\u0006dW#A\f\u0011\u0005aIR\"\u0001\u0001\u0007\u000fi\u0001\u0001\u0013aA\u00017\t\u00112i\u001c8uKb$\u0018J\u001c;fe:\fG.\u00119j'\rI\"\u0002\b\t\u0003;\u0011r!\u0001\u0007\u0010\n\u0005}\u0001\u0013\u0001C;oSZ,'o]3\n\u0005\u0005\u0012#aB\"p]R,\u0007\u0010\u001e\u0006\u0003G\t\t\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003K\u0019\u0012\u0001#T1de>Le\u000e^3s]\u0006d\u0017\t]5\n\u0005\u001d\u0012!\u0001C+oSZ,'o]3\t\u000b=IB\u0011\u0001\t\t\u000b)Jb\u0011A\u0016\u0002\u001d\u0015t7\r\\8tS:<wj\u001e8feV\tA\u0006\u0005\u0002\u0019[%\u0011af\f\u0002\u0007'fl'm\u001c7\n\u0005A\u0012!aB!mS\u0006\u001cXm\u001d\u0004\bee\u0001\n1%\u00014\u00051!&/\u00198tM>\u0014X.\u00119j'\t\t$\u0002C\u00036c\u0019\u0005a'A\u0003sK\u000e,(\u000f\u0006\u00028uA\u0011\u0001\u0004O\u0005\u0003s=\u0012A\u0001\u0016:fK\")1\b\u000ea\u0001o\u0005!AO]3f\u0011\u0015i\u0014G\"\u0001?\u0003\u001d!WMZ1vYR$\"aN \t\u000bmb\u0004\u0019A\u001c\t\u000b\u0005Kb\u0011\u0001\"\u0002\u0013Q\u0014\u0018M\\:g_JlGCA\"L)\t9D\tC\u0003F\u0001\u0002\u0007a)A\u0006ue\u0006t7OZ8s[\u0016\u0014\b#B\u0006Ho%;\u0014B\u0001%\u0007\u0005%1UO\\2uS>t'\u0007\u0005\u0002Kc5\t\u0011\u0004C\u0003<\u0001\u0002\u0007qGB\u0004N3A\u0005\u0019\u0013\u0001(\u0003%QK\b/\u001b8h)J\fgn\u001d4pe6\f\u0005/[\n\u0004\u0019*I\u0005\"\u0002)M\r\u0003\t\u0016aB1u\u001f^tWM]\u000b\u0003%Z#\"a\u00153\u0015\u0005Q{\u0006CA+W\u0019\u0001!QaV(C\u0002a\u0013\u0011\u0001V\t\u00033r\u0003\"a\u0003.\n\u0005m3!a\u0002(pi\"Lgn\u001a\t\u0003\u0017uK!A\u0018\u0004\u0003\u0007\u0005s\u0017\u0010\u0003\u0004a\u001f\u0012\u0005\r!Y\u0001\u0003_B\u00042a\u00032U\u0013\t\u0019gA\u0001\u0005=Eft\u0017-\\3?\u0011\u0015)w\n1\u0001-\u0003\u0015ywO\\3s\u0011\u0015\u0001FJ\"\u0001h+\tA7\u000eF\u0002j]>$\"A\u001b7\u0011\u0005U[G!B,g\u0005\u0004A\u0006B\u00021g\t\u0003\u0007Q\u000eE\u0002\fE*DQa\u000f4A\u0002]BQ!\u001a4A\u00021BQ!\u001d'\u0007\u0002-\nAbY;se\u0016tGoT<oKJDQa\u001d'\u0007\u0002Q\f\u0011\u0002^=qK\u000eDWmY6\u0015\u0005]*\b\"B\u001es\u0001\u00049\u0004\"B<\u001a\r\u0003A\u0018a\u0004;za&tw\r\u0016:b]N4wN]7\u0015\u0005elHCA\u001c{\u0011\u0015)e\u000f1\u0001|!\u0015Yqi\u000e?8!\tQE\nC\u0003<m\u0002\u0007q\u0007C\u0003x3\u0019\u0005q\u0010\u0006\u0004\u0002\u0002\u0005\u0015\u0011q\u0001\u000b\u0004o\u0005\r\u0001\"B#\u007f\u0001\u0004Y\b\"B\u001e\u007f\u0001\u00049\u0004\"B3\u007f\u0001\u0004a\u0003cAA\u0006A5\t!\u0005")
public interface Internals {
    public ContextInternalApi internal();

    public interface ContextInternalApi
    extends Universe.MacroInternalApi {
        public Symbols.SymbolApi enclosingOwner();

        public Trees.TreeApi transform(Trees.TreeApi var1, Function2<Trees.TreeApi, TransformApi, Trees.TreeApi> var2);

        public Trees.TreeApi typingTransform(Trees.TreeApi var1, Function2<Trees.TreeApi, TypingTransformApi, Trees.TreeApi> var2);

        public Trees.TreeApi typingTransform(Trees.TreeApi var1, Symbols.SymbolApi var2, Function2<Trees.TreeApi, TypingTransformApi, Trees.TreeApi> var3);

        public /* synthetic */ Internals scala$reflect$macros$Internals$ContextInternalApi$$$outer();

        /*
         * Illegal identifiers - consider using --renameillegalidents true
         */
        public interface TransformApi {
            public Trees.TreeApi recur(Trees.TreeApi var1);

            public Trees.TreeApi default(Trees.TreeApi var1);
        }

        public interface TypingTransformApi
        extends TransformApi {
            public <T> T atOwner(Symbols.SymbolApi var1, Function0<T> var2);

            public <T> T atOwner(Trees.TreeApi var1, Symbols.SymbolApi var2, Function0<T> var3);

            public Symbols.SymbolApi currentOwner();

            public Trees.TreeApi typecheck(Trees.TreeApi var1);
        }
    }
}

