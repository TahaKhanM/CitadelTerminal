/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.transform;

import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.transform.UnCurry$DesugaredParameterType$;
import scala.reflect.internal.transform.UnCurry$VarargsSymbolAttachment$;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005UeaB\u0001\u0003!\u0003\r\ta\u0003\u0002\b+:\u001cUO\u001d:z\u0015\t\u0019A!A\u0005ue\u0006t7OZ8s[*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g\u0011\u0015\t\u0002\u0001\"\u0001\u0013\u0003\u0019!\u0013N\\5uIQ\t1\u0003\u0005\u0002\u000e)%\u0011Q\u0003\u0003\u0002\u0005+:LG\u000fC\u0004\u0018\u0001\t\u0007i\u0011\u0001\r\u0002\r\u001ddwNY1m+\u0005I\u0002C\u0001\u000e\u001c\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3\u0007\ty\u0001\u0001i\b\u0002\u0018-\u0006\u0014\u0018M]4t'fl'm\u001c7BiR\f7\r[7f]R\u001cB!\b\u0007!GA\u0011Q\"I\u0005\u0003E!\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\u000eI%\u0011Q\u0005\u0003\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\tOu\u0011)\u001a!C\u0001Q\u0005aa/\u0019:be\u001elU\r\u001e5pIV\t\u0011\u0006\u0005\u0002+Y9\u00111FF\u0007\u0002\u0001%\u0011QF\f\u0002\u0007'fl'm\u001c7\n\u0005=\"!aB*z[\n|Gn\u001d\u0005\tcu\u0011\t\u0012)A\u0005S\u0005ia/\u0019:be\u001elU\r\u001e5pI\u0002BQaM\u000f\u0005\u0002Q\na\u0001P5oSRtDCA\u001b7!\tYS\u0004C\u0003(e\u0001\u0007\u0011\u0006C\u00049;\u0005\u0005I\u0011A\u001d\u0002\t\r|\u0007/\u001f\u000b\u0003kiBqaJ\u001c\u0011\u0002\u0003\u0007\u0011\u0006C\u0004=;E\u0005I\u0011A\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taH\u000b\u0002*\u007f-\n\u0001\t\u0005\u0002B\r6\t!I\u0003\u0002D\t\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u000b\"\t!\"\u00198o_R\fG/[8o\u0013\t9%IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016Dq!S\u000f\u0002\u0002\u0013\u0005#*A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0017B\u0011A*U\u0007\u0002\u001b*\u0011ajT\u0001\u0005Y\u0006twMC\u0001Q\u0003\u0011Q\u0017M^1\n\u0005Ik%AB*ue&tw\rC\u0004U;\u0005\u0005I\u0011A+\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003Y\u0003\"!D,\n\u0005aC!aA%oi\"9!,HA\u0001\n\u0003Y\u0016A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u00039~\u0003\"!D/\n\u0005yC!aA!os\"9\u0001-WA\u0001\u0002\u00041\u0016a\u0001=%c!9!-HA\u0001\n\u0003\u001a\u0017a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003\u0011\u00042!\u001a5]\u001b\u00051'BA4\t\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003S\u001a\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0005\bWv\t\t\u0011\"\u0001m\u0003!\u0019\u0017M\\#rk\u0006dGCA7q!\tia.\u0003\u0002p\u0011\t9!i\\8mK\u0006t\u0007b\u00021k\u0003\u0003\u0005\r\u0001\u0018\u0005\bev\t\t\u0011\"\u0011t\u0003!A\u0017m\u001d5D_\u0012,G#\u0001,\t\u000fUl\u0012\u0011!C!m\u0006AAo\\*ue&tw\rF\u0001L\u0011\u001dAX$!A\u0005Be\fa!Z9vC2\u001cHCA7{\u0011\u001d\u0001w/!AA\u0002q;q\u0001 \u0001\u0002\u0002#\u0005Q0A\fWCJ\f'oZ:Ts6\u0014w\u000e\\!ui\u0006\u001c\u0007.\\3oiB\u00111F \u0004\b=\u0001\t\t\u0011#\u0001\u0000'\u0011q\u0018\u0011A\u0012\u0011\r\u0005\r\u0011\u0011B\u00156\u001b\t\t)AC\u0002\u0002\b!\tqA];oi&lW-\u0003\u0003\u0002\f\u0005\u0015!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oc!11G C\u0001\u0003\u001f!\u0012! \u0005\bkz\f\t\u0011\"\u0012w\u0011%\t)B`A\u0001\n\u0003\u000b9\"A\u0003baBd\u0017\u0010F\u00026\u00033AaaJA\n\u0001\u0004I\u0003\"CA\u000f}\u0006\u0005I\u0011QA\u0010\u0003\u001d)h.\u00199qYf$B!!\t\u0002(A!Q\"a\t*\u0013\r\t)\u0003\u0003\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\u0005%\u00121DA\u0001\u0002\u0004)\u0014a\u0001=%a!9\u0011Q\u0006\u0001\u0005\n\u0005=\u0012aC3ya\u0006tG-\u00117jCN$B!!\r\u0002<A\u0019!&a\r\n\t\u0005U\u0012q\u0007\u0002\u0005)f\u0004X-C\u0002\u0002:\u0011\u0011Q\u0001V=qKND\u0001\"!\u0010\u0002,\u0001\u0007\u0011\u0011G\u0001\u0003iBD\u0011\"!\u0011\u0001\u0005\u0004%\t!a\u0011\u0002\u000fUt7-\u001e:ssV\u0011\u0011Q\t\t\u0004U\u0005\u001d\u0013\u0002BA%\u0003\u0017\u0012q\u0001V=qK6\u000b\u0007/\u0003\u0003\u0002N\u0005=#\u0001\u0003+za\u0016l\u0015\r]:\u000b\u0007\u0005EC!A\u0002ua\u0016D\u0001\"!\u0016\u0001A\u0003%\u0011QI\u0001\tk:\u001cWO\u001d:zA\u001d9\u0011\u0011\f\u0001\t\u0002\u0005m\u0013A\u0006#fgV<\u0017M]3e!\u0006\u0014\u0018-\\3uKJ$\u0016\u0010]3\u0011\u0007-\niFB\u0004\u0002`\u0001A\t!!\u0019\u0003-\u0011+7/^4be\u0016$\u0007+\u0019:b[\u0016$XM\u001d+za\u0016\u001c2!!\u0018\r\u0011\u001d\u0019\u0014Q\fC\u0001\u0003K\"\"!a\u0017\t\u0011\u0005u\u0011Q\fC\u0001\u0003S\"B!a\u001b\u0002nA)Q\"a\t\u00022!A\u0011\u0011KA4\u0001\u0004\t\t\u0004C\u0005\u0002r\u0001\u0011\r\u0011\"\u0003\u0002D\u0005YQO\\2veJLH+\u001f9f\u0011!\t)\b\u0001Q\u0001\n\u0005\u0015\u0013\u0001D;oGV\u0014(/\u001f+za\u0016\u0004\u0003bBA=\u0001\u0011%\u00111P\u0001\u0013m\u0006\u0014\u0018M]4G_J<\u0018M\u001d3feNKX\u000eF\u0004*\u0003{\n\t)!\"\t\u000f\u0005}\u0014q\u000fa\u0001S\u0005a1-\u001e:sK:$8\t\\1tg\"9\u00111QA<\u0001\u0004I\u0013aB8sS\u001e\u001c\u00160\u001c\u0005\t\u0003\u000f\u000b9\b1\u0001\u00022\u00059a.Z<J]\u001a|\u0007bBAF\u0001\u0011\u0005\u0011QR\u0001\u000eiJ\fgn\u001d4pe6LeNZ8\u0015\r\u0005E\u0012qRAJ\u0011\u001d\t\t*!#A\u0002%\n1a]=n\u0011!\ti$!#A\u0002\u0005E\u0002")
public interface UnCurry {
    public void scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq(TypeMaps.TypeMap var1);

    public void scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq(TypeMaps.TypeMap var1);

    public SymbolTable global();

    public UnCurry$VarargsSymbolAttachment$ VarargsSymbolAttachment();

    public TypeMaps.TypeMap uncurry();

    public UnCurry$DesugaredParameterType$ DesugaredParameterType();

    public TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType();

    public Types.Type transformInfo(Symbols.Symbol var1, Types.Type var2);

    public class VarargsSymbolAttachment
    implements Product,
    Serializable {
        private final Symbols.Symbol varargMethod;
        public final /* synthetic */ UnCurry $outer;

        public Symbols.Symbol varargMethod() {
            return this.varargMethod;
        }

        public VarargsSymbolAttachment copy(Symbols.Symbol varargMethod) {
            return new VarargsSymbolAttachment(this.scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer(), varargMethod);
        }

        public Symbols.Symbol copy$default$1() {
            return this.varargMethod();
        }

        @Override
        public String productPrefix() {
            return "VarargsSymbolAttachment";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            switch (x$1) {
                default: {
                    throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
                }
                case 0: 
            }
            return this.varargMethod();
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof VarargsSymbolAttachment;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            if (!(x$1 instanceof VarargsSymbolAttachment)) return false;
            if (((VarargsSymbolAttachment)x$1).scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer() != this.scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer()) return false;
            boolean bl = true;
            if (!bl) return false;
            VarargsSymbolAttachment varargsSymbolAttachment = (VarargsSymbolAttachment)x$1;
            Symbols.Symbol symbol = this.varargMethod();
            Symbols.Symbol symbol2 = varargsSymbolAttachment.varargMethod();
            if (symbol == null) {
                if (symbol2 != null) {
                    return false;
                }
            } else if (!symbol.equals(symbol2)) return false;
            if (!varargsSymbolAttachment.canEqual(this)) return false;
            return true;
        }

        public /* synthetic */ UnCurry scala$reflect$internal$transform$UnCurry$VarargsSymbolAttachment$$$outer() {
            return this.$outer;
        }

        public VarargsSymbolAttachment(UnCurry $outer, Symbols.Symbol varargMethod) {
            this.varargMethod = varargMethod;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Product$class.$init$(this);
        }
    }
}

