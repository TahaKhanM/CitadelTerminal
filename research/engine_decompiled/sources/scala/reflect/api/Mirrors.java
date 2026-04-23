/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.collection.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.Symbols;
import scala.reflect.api.Types;

@ScalaSignature(bytes="\u0006\u0001\u0005Ef!C\u0001\u0003!\u0003\r\t!CAV\u0005\u001di\u0015N\u001d:peNT!a\u0001\u0003\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\t\u0015)\u0002A!\u0001\u0017\u0005\u0019i\u0015N\u001d:peF\u0011qC\u0007\t\u0003\u0017aI!!\u0007\u0004\u0003\t9+H\u000e\u001c\t\u00047qiR\"\u0001\u0002\n\u0005U\u0011Q\"\u0001\u0001\t\u000f}\u0001!\u0019!D\u0001A\u0005Q!o\\8u\u001b&\u0014(o\u001c:\u0016\u0003\u0005\u0002\"!\b\u000b\u0005\u000b\r\u0002!\u0011\u0001\u0013\u0003\u0019I+h\u000e^5nK\u000ec\u0017m]:\u0012\u0005]Qaa\u0002\u0014\u0001!\u0003\r\na\n\u0002\u0010%VtG/[7f\u00072\f7o]!qSN\u0011QE\u0003\u0004\bS\u0001\u0001\n1%\u0001+\u00059Ien\u001d;b]\u000e,W*\u001b:s_J\u001c\"\u0001\u000b\u0006\t\u000b1Bc\u0011A\u0017\u0002\u0011%t7\u000f^1oG\u0016,\u0012A\f\t\u0003\u0017=J!\u0001\r\u0004\u0003\u0007\u0005s\u0017\u0010C\u00033Q\u0019\u00051'\u0001\u0004ts6\u0014w\u000e\\\u000b\u0002iA\u0011Q$N\u0005\u0003m]\u00121b\u00117bgN\u001c\u00160\u001c2pY&\u0011\u0001H\u0001\u0002\b'fl'm\u001c7t\u0011\u0015Q\u0004F\"\u0001<\u00031\u0011XM\u001a7fGR4\u0015.\u001a7e)\ta4\u000b\u0005\u0002\u001e{\u00199a\b\u0001I\u0001$\u0003y$a\u0003$jK2$W*\u001b:s_J\u001c\"!\u0010\u0006\t\u000b\u0005kd\u0011A\u0017\u0002\u0011I,7-Z5wKJDQAM\u001f\u0007\u0002\r+\u0012\u0001\u0012\t\u0003;\u0015K!AR\u001c\u0003\u0015Q+'/\\*z[\n|G\u000eC\u0003I{\u0019\u0005Q&A\u0002hKRDQAS\u001f\u0007\u0002-\u000b1a]3u)\t\tB\nC\u0003N\u0013\u0002\u0007a&A\u0003wC2,X\rC\u0003P{\u0019\u0005\u0001+\u0001\u0003cS:$GC\u0001\u001fR\u0011\u0015\u0011f\n1\u0001/\u0003-qWm\u001e*fG\u0016Lg/\u001a:\t\u000bQK\u0004\u0019\u0001#\u0002\u000b\u0019LW\r\u001c3\t\u000bYCc\u0011A,\u0002\u001bI,g\r\\3di6+G\u000f[8e)\tAV\u000e\u0005\u0002\u001e3\u001a9!\f\u0001I\u0001$\u0003Y&\u0001D'fi\"|G-T5se>\u00148CA-\u000b\u0011\u0015\t\u0015L\"\u0001.\u0011\u0015\u0011\u0014L\"\u0001_+\u0005y\u0006CA\u000fa\u0013\t\twG\u0001\u0007NKRDw\u000eZ*z[\n|G\u000eC\u0003d3\u001a\u0005A-A\u0003baBd\u0017\u0010\u0006\u0002/K\")aM\u0019a\u0001O\u0006!\u0011M]4t!\rY\u0001NL\u0005\u0003S\u001a\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0011\u0015y\u0015L\"\u0001l)\tAF\u000eC\u0003SU\u0002\u0007a\u0006C\u0003o+\u0002\u0007q,\u0001\u0004nKRDw\u000e\u001a\u0005\u0006a\"2\t!]\u0001\re\u00164G.Z2u\u00072\f7o\u001d\u000b\u0004e\u0006]\u0001CA\u000ft\r\u001d!\b\u0001%A\u0012\u0002U\u00141b\u00117bgNl\u0015N\u001d:peN\u00191O\u0003<\u0011\u0005u9ha\u0002=\u0001!\u0003\r\n!\u001f\u0002\u000f)\u0016l\u0007\u000f\\1uK6K'O]8s'\t9(\u0002C\u0003|o\u001a\u0005A0\u0001\u0005jgN#\u0018\r^5d+\u0005i\bCA\u0006\u007f\u0013\tyhAA\u0004C_>dW-\u00198\t\rI:h\u0011AA\u0002+\t\t)\u0001E\u0002\u001e\u0003\u000fI1!!\u00038\u0005\u0019\u0019\u00160\u001c2pY\")!g\u001dD!g!9\u0011qB:\u0007\u0002\u0005E\u0011A\u0005:fM2,7\r^\"p]N$(/^2u_J$2\u0001WA\n\u0011\u001d\t)\"!\u0004A\u0002}\u000b1bY8ogR\u0014Xo\u0019;pe\"1\u0011\u0011D8A\u0002Q\n1a\u00197t\u0011\u001d\ti\u0002\u000bD\u0001\u0003?\tQB]3gY\u0016\u001cG/T8ek2,G\u0003BA\u0011\u0003k\u00012!HA\u0012\r%\t)\u0003\u0001I\u0001$\u0003\t9C\u0001\u0007N_\u0012,H.Z'jeJ|'o\u0005\u0003\u0002$)1\bb\u0002\u001a\u0002$\u0019\u0005\u00131F\u000b\u0003\u0003[\u00012!HA\u0018\u0013\r\t\td\u000e\u0002\r\u001b>$W\u000f\\3Ts6\u0014w\u000e\u001c\u0005\u0007Y\u0005\rb\u0011A\u0017\t\u0011\u0005]\u00121\u0004a\u0001\u0003[\t1!\\8e\r%\tY\u0004\u0001I\u0001$\u0003\tiD\u0001\tSK\u001adWm\u0019;jm\u0016l\u0015N\u001d:peN\u0019\u0011\u0011\b\u000e\t\u000f\u0015\tID\"\u0001\u0002BU!\u00111IA-)\u0011\t)%!\u001a\u0015\t\u0005\u001d\u0013\u0011\n\t\u0003;!B!\"a\u0013\u0002@\u0005\u0005\t9AA'\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003\u001f\n\t&!\u0016\u000e\u0003\u0011I1!a\u0015\u0005\u0005!\u0019E.Y:t)\u0006<\u0007\u0003BA,\u00033b\u0001\u0001\u0002\u0005\u0002\\\u0005}\"\u0019AA/\u0005\u0005!\u0016cAA0]A\u00191\"!\u0019\n\u0007\u0005\rdAA\u0004O_RD\u0017N\\4\t\u0011\u0005\u001d\u0014q\ba\u0001\u0003+\n1a\u001c2k\u0011\u001d\u0001\u0018\u0011\bD\u0001\u0003W\"2A]A7\u0011\u001d\tI\"!\u001bA\u0002QB\u0001\"!\b\u0002:\u0019\u0005\u0011\u0011\u000f\u000b\u0005\u0003C\t\u0019\b\u0003\u0005\u00028\u0005=\u0004\u0019AA\u0017\r%\t9\b\u0001I\u0001$\u0003\tIHA\u0007Sk:$\u0018.\\3NSJ\u0014xN]\n\u0006\u0003kR\u00121\u0010\t\u0004;\u0005e\u0002\u0002CA@\u0003k2\t!!!\u0002\u0019I,h\u000e^5nK\u000ec\u0017m]:\u0015\t\u0005\r\u0015Q\u0011\t\u0003;\tB\u0001\"a\"\u0002~\u0001\u0007\u0011\u0011R\u0001\u0004iB,\u0007cA\u000f\u0002\f&!\u0011QRAH\u0005\u0011!\u0016\u0010]3\n\u0007\u0005E%AA\u0003UsB,7\u000f\u0003\u0005\u0002\u0000\u0005Ud\u0011AAK)\u0011\t\u0019)a&\t\u000f\u0005e\u00111\u0013a\u0001i!A\u00111TA;\r\u0003\ti*A\u0006dY\u0006\u001c8oU=nE>dGc\u0001\u001b\u0002 \"A\u0011\u0011UAM\u0001\u0004\t\u0019)A\u0003si\u000ed7\u000f\u0003\u0005\u0002&\u0006Ud\u0011AAT\u00031iw\u000eZ;mKNKXNY8m)\u0011\ti#!+\t\u0011\u0005\u0005\u00161\u0015a\u0001\u0003\u0007\u00032aGAW\u0013\r\tyK\u0001\u0002\t+:Lg/\u001a:tK\u0002")
public interface Mirrors {
    public Mirror rootMirror();

    public interface FieldMirror {
        public Object receiver();

        public Symbols.TermSymbolApi symbol();

        public Object get();

        public void set(Object var1);

        public FieldMirror bind(Object var1);
    }

    public interface ClassMirror
    extends TemplateMirror {
        @Override
        public Symbols.ClassSymbolApi symbol();

        public MethodMirror reflectConstructor(Symbols.MethodSymbolApi var1);
    }

    public interface MethodMirror {
        public Object receiver();

        public Symbols.MethodSymbolApi symbol();

        public Object apply(Seq<Object> var1);

        public MethodMirror bind(Object var1);
    }

    public interface ModuleMirror
    extends TemplateMirror {
        @Override
        public Symbols.ModuleSymbolApi symbol();

        public Object instance();
    }

    public interface RuntimeMirror
    extends ReflectiveMirror {
        public Object runtimeClass(Types.TypeApi var1);

        public Object runtimeClass(Symbols.ClassSymbolApi var1);

        public Symbols.ClassSymbolApi classSymbol(Object var1);

        public Symbols.ModuleSymbolApi moduleSymbol(Object var1);
    }

    public interface InstanceMirror {
        public Object instance();

        public Symbols.ClassSymbolApi symbol();

        public FieldMirror reflectField(Symbols.TermSymbolApi var1);

        public MethodMirror reflectMethod(Symbols.MethodSymbolApi var1);

        public ClassMirror reflectClass(Symbols.ClassSymbolApi var1);

        public ModuleMirror reflectModule(Symbols.ModuleSymbolApi var1);
    }

    public interface TemplateMirror {
        public boolean isStatic();

        public Symbols.SymbolApi symbol();
    }

    public interface RuntimeClassApi {
    }

    public interface ReflectiveMirror {
        public <T> InstanceMirror reflect(T var1, ClassTag<T> var2);

        public ClassMirror reflectClass(Symbols.ClassSymbolApi var1);

        public ModuleMirror reflectModule(Symbols.ModuleSymbolApi var1);
    }
}

