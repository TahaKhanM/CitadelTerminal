/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.StringBuilder;
import scala.compat.Platform$;
import scala.math.Ordering$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.BaseTypeSeqsStats$;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005Mg!C\u0001\u0003!\u0003\r\t!CAg\u00051\u0011\u0015m]3UsB,7+Z9t\u0015\t\u0019A!\u0001\u0005j]R,'O\\1m\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000b=\u0001A\u0011\u0001\t\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0002CA\u0006\u0013\u0013\t\u0019bA\u0001\u0003V]&$\b\"B\u000b\u0001\t#1\u0012A\u00048fo\n\u000b7/\u001a+za\u0016\u001cV-\u001d\u000b\u0006/\u0005-\u0013Q\n\t\u00031ei\u0011\u0001\u0001\u0004\u00055\u0001\u00011DA\u0006CCN,G+\u001f9f'\u0016\f8CA\r\u000b\u0011%i\u0012D!B\u0001B\u0003%a$A\u0017tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mI\t\u000b7/\u001a+za\u0016\u001cV-]:%IA\f'/\u001a8ug\u0002\u00022a\b\u0012&\u001d\tY\u0001%\u0003\u0002\"\r\u00059\u0001/Y2lC\u001e,\u0017BA\u0012%\u0005\u0011a\u0015n\u001d;\u000b\u0005\u00052\u0001C\u0001\r'\u0013\t9\u0003F\u0001\u0003UsB,\u0017BA\u0015\u0003\u0005\u0015!\u0016\u0010]3t\u0011%Y\u0013D!B\u0001B\u0003%A&A\u0016tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mI\t\u000b7/\u001a+za\u0016\u001cV-]:%I\u0015dW-\\:!!\rYQ&J\u0005\u0003]\u0019\u0011Q!\u0011:sCfDa\u0001M\r\u0005\u0012\u0011\t\u0014A\u0002\u001fj]&$h\bF\u0002\u0018eQBQaM\u0018A\u0002y\tq\u0001]1sK:$8\u000fC\u00036_\u0001\u0007A&A\u0003fY\u0016l7\u000fC\u000383\u0011\u0005\u0001(\u0001\u0004mK:<G\u000f[\u000b\u0002sA\u00111BO\u0005\u0003w\u0019\u00111!\u00138u\u0011\u001di\u0014D1A\u0005\ny\nq\u0001]3oI&tw-F\u0001@!\t\u0001U)D\u0001B\u0015\t\u00115)A\u0004nkR\f'\r\\3\u000b\u0005\u00113\u0011AC2pY2,7\r^5p]&\u0011a)\u0011\u0002\u0007\u0005&$8+\u001a;\t\r!K\u0002\u0015!\u0003@\u0003!\u0001XM\u001c3j]\u001e\u0004\u0003\"\u0002&\u001a\t\u0003Y\u0015!B1qa2LHCA\u0013M\u0011\u0015i\u0015\n1\u0001:\u0003\u0005I\u0007\"B(\u001a\t\u0003\u0001\u0016a\u0002:bo\u0016cW-\u001c\u000b\u0003KECQ!\u0014(A\u0002eBQaU\r\u0005\u0002Q\u000b!\u0002^=qKNKXNY8m)\t)&\f\u0005\u0002\u0019-&\u0011q\u000b\u0017\u0002\u0007'fl'm\u001c7\n\u0005e\u0013!aB*z[\n|Gn\u001d\u0005\u0006\u001bJ\u0003\r!\u000f\u0005\u00069f!\t!X\u0001\u0007i>d\u0015n\u001d;\u0016\u0003yAQaX\r\u0005\u0002\u0001\fAaY8qsR\u0019q#Y2\t\u000b\tt\u0006\u0019A\u0013\u0002\t!,\u0017\r\u001a\u0005\u0006Iz\u0003\r!O\u0001\u0007_\u001a47/\u001a;\t\u000b\u0019LB\u0011A4\u0002\u000fA\u0014X\r]3oIR\u0011q\u0003\u001b\u0005\u0006S\u0016\u0004\r!J\u0001\u0003iBDQa[\r\u0005\u00021\f!\"\u001e9eCR,\u0007*Z1e)\t9R\u000eC\u0003jU\u0002\u0007Q\u0005C\u0003p3\u0011\u0005\u0001/A\u0002nCB$\"aF9\t\u000bIt\u0007\u0019A:\u0002\u0003\u0019\u0004Ba\u0003;&K%\u0011QO\u0002\u0002\n\rVt7\r^5p]FBQa^\r\u0005\u0002a\fq\u0001\\1uK6\u000b\u0007\u000f\u0006\u0002\u0018s\")!O\u001ea\u0001g\")10\u0007C\u0001y\u00061Q\r_5tiN$2!`A\u0001!\tYa0\u0003\u0002\u0000\r\t9!i\\8mK\u0006t\u0007bBA\u0002u\u0002\u0007\u0011QA\u0001\u0002aB!1\u0002^\u0013~\u0011)\tI!\u0007EC\u0002\u0013\u0005\u00111B\u0001\t[\u0006DH)\u001a9uQV\u0011\u0011Q\u0002\t\u0005\u0003\u001f\t\t\"D\u0001\u0003\u0013\r\t\u0019B\u0001\u0002\u0006\t\u0016\u0004H\u000f\u001b\u0005\u000b\u0003/I\u0002\u0012!Q!\n\u00055\u0011!C7bq\u0012+\u0007\u000f\u001e5!\u0011\u001d\tY\"\u0007C\t\u0003\u0017\tq\"\\1y\t\u0016\u0004H\u000f[(g\u000b2,Wn\u001d\u0005\b\u0003?IB\u0011IA\u0011\u0003!!xn\u0015;sS:<GCAA\u0012!\u0011\t)#a\u000b\u000f\u0007-\t9#C\u0002\u0002*\u0019\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0017\u0003_\u0011aa\u0015;sS:<'bAA\u0015\r!9\u00111G\r\u0005\n\u0005U\u0012!\u0003;za\u0016,%O]8s)\u0011\t9$!\u0010\u0011\u0007-\tI$C\u0002\u0002<\u0019\u0011qAT8uQ&tw\r\u0003\u0005\u0002@\u0005E\u0002\u0019AA\u0012\u0003\ri7o\u001a\u0005\f\u0003\u0007J\"Q!b\u0001\n\u0003\u0001Q,\u0001\u0017tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mI\t\u000b7/\u001a+za\u0016\u001cV-]:%IA\f'/\u001a8ug\"a\u0011qI\r\u0003\u0006\u000b\u0007I\u0011\u0001\u0001\u0002J\u0005Q3oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000e\n\"bg\u0016$\u0016\u0010]3TKF\u001cH\u0005J3mK6\u001cX#\u0001\u0017\t\u000bM\"\u0002\u0019\u0001\u0010\t\u000bU\"\u0002\u0019\u0001\u0017\t\u0013\u0005E\u0003A1A\u0005\u0002\u0005M\u0013\u0001E;oI\u0016$()Y:f)f\u0004XmU3r+\u00059\u0002bBA,\u0001\u0001\u0006IaF\u0001\u0012k:$W\r\u001e\"bg\u0016$\u0016\u0010]3TKF\u0004\u0003bBA.\u0001\u0011\u0005\u0011QL\u0001\u0015E\u0006\u001cX\rV=qKNKgn\u001a7fi>t7+Z9\u0015\u0007]\ty\u0006\u0003\u0004j\u00033\u0002\r!\n\u0005\b\u0003G\u0002A\u0011AA3\u0003M\u0019w.\u001c9pk:$')Y:f)f\u0004XmU3r)\r9\u0012q\r\u0005\u0007S\u0006\u0005\u0004\u0019A\u0013\u0007\r\u0005-\u0004\u0001AA7\u0005Ei\u0015\r\u001d9fI\n\u000b7/\u001a+za\u0016\u001cV-]\n\u0004\u0003S:\u0002BCA9\u0003S\u0012\t\u0011)A\u0005/\u0005!qN]5h\u0011%\u0011\u0018\u0011\u000eB\u0001B\u0003%1\u000fC\u00041\u0003S\"\t!a\u001e\u0015\r\u0005e\u00141PA?!\rA\u0012\u0011\u000e\u0005\b\u0003c\n)\b1\u0001\u0018\u0011\u0019\u0011\u0018Q\u000fa\u0001g\"9!*!\u001b\u0005B\u0005\u0005EcA\u0013\u0002\u0004\"1Q*a A\u0002eBqaTA5\t\u0003\n9\tF\u0002&\u0003\u0013Ca!TAC\u0001\u0004I\u0004bB*\u0002j\u0011\u0005\u0013Q\u0012\u000b\u0004+\u0006=\u0005BB'\u0002\f\u0002\u0007\u0011\b\u0003\u0004]\u0003S\"\t%\u0018\u0005\b?\u0006%D\u0011IAK)\u00159\u0012qSAM\u0011\u0019\u0011\u00171\u0013a\u0001K!1A-a%A\u0002eBqa\\A5\t\u0003\ni\nF\u0002\u0018\u0003?Cq!!)\u0002\u001c\u0002\u00071/A\u0001h\u0011\u001d9\u0018\u0011\u000eC!\u0003K#2aFAT\u0011\u001d\t\t+a)A\u0002MDqa_A5\t\u0003\nY\u000bF\u0002~\u0003[C\u0001\"a\u0001\u0002*\u0002\u0007\u0011Q\u0001\u0005\t\u00037\tI\u0007\"\u0015\u0002\f!A\u0011qDA5\t\u0003\n\t\u0003C\u0005\u00026\u0002\u0011\r\u0011\"\u0001\u00028\u0006\t2)_2mS\u000eLe\u000e[3sSR\fgnY3\u0016\u0005\u0005e\u0006\u0003BA^\u0003\u000bl!!!0\u000b\t\u0005}\u0016\u0011Y\u0001\u0005Y\u0006twM\u0003\u0002\u0002D\u0006!!.\u0019<b\u0013\u0011\t9-!0\u0003\u0013QC'o\\<bE2,\u0007\u0002CAf\u0001\u0001\u0006I!!/\u0002%\rK8\r\\5d\u0013:DWM]5uC:\u001cW\r\t\t\u0005\u0003\u001f\ty-C\u0002\u0002R\n\u00111bU=nE>dG+\u00192mK\u0002")
public interface BaseTypeSeqs {
    public void scala$reflect$internal$BaseTypeSeqs$_setter_$undetBaseTypeSeq_$eq(BaseTypeSeq var1);

    public void scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(Throwable var1);

    public BaseTypeSeq newBaseTypeSeq(List<Types.Type> var1, Types.Type[] var2);

    public BaseTypeSeq undetBaseTypeSeq();

    public BaseTypeSeq baseTypeSingletonSeq(Types.Type var1);

    public BaseTypeSeq compoundBaseTypeSeq(Types.Type var1);

    public Throwable CyclicInheritance();

    public class BaseTypeSeq {
        private final List<Types.Type> scala$reflect$internal$BaseTypeSeqs$$parents;
        private final Types.Type[] scala$reflect$internal$BaseTypeSeqs$$elems;
        private final BitSet pending;
        private int maxDepth;
        public final /* synthetic */ SymbolTable $outer;
        private volatile boolean bitmap$0;

        private int maxDepth$lzycompute() {
            BaseTypeSeq baseTypeSeq2 = this;
            synchronized (baseTypeSeq2) {
                if (!this.bitmap$0) {
                    this.maxDepth = this.maxDepthOfElems();
                    this.bitmap$0 = true;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.maxDepth;
            }
        }

        public List<Types.Type> scala$reflect$internal$BaseTypeSeqs$$parents() {
            return this.scala$reflect$internal$BaseTypeSeqs$$parents;
        }

        public Types.Type[] scala$reflect$internal$BaseTypeSeqs$$elems() {
            return this.scala$reflect$internal$BaseTypeSeqs$$elems;
        }

        public int length() {
            return this.scala$reflect$internal$BaseTypeSeqs$$elems().length;
        }

        private BitSet pending() {
            return this.pending;
        }

        public Types.Type apply(int i) {
            Nothing$ nothing$;
            block13: {
                Types.RefinedType refinedType;
                block12: {
                    Throwable throwable;
                    block10: {
                        block8: {
                            Types.Type type;
                            block9: {
                                Types.Type type2;
                                block11: {
                                    Types.Type type3;
                                    block7: {
                                        if (this.pending().contains(i)) {
                                            this.pending().clear();
                                            throw this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().CyclicInheritance();
                                        }
                                        type2 = this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
                                        if (!(type2 instanceof Types.RefinedType)) break block11;
                                        refinedType = (Types.RefinedType)type2;
                                        this.pending().$plus$eq(i);
                                        type3 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().mergePrefixAndArgs(refinedType.parents(), Variance$.MODULE$.Contravariant(), this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().lubDepth(refinedType.parents()));
                                        if (!this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().NoType().equals(type3)) break block7;
                                        nothing$ = this.typeError(new StringBuilder().append((Object)"no common type instance of base types ").append((Object)refinedType.parents().mkString(", and ")).append((Object)" exists.").toString());
                                        throwable = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().CyclicInheritance();
                                        if (throwable == null) break block8;
                                        break block10;
                                    }
                                    try {
                                        this.pending().update(BoxesRunTime.boxToInteger(i), false);
                                        this.scala$reflect$internal$BaseTypeSeqs$$elems()[i] = type3;
                                        type = type3;
                                        break block9;
                                    }
                                    catch (Throwable throwable2) {
                                        throwable = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().CyclicInheritance();
                                        if (throwable != null) break block10;
                                    }
                                }
                                type = type2;
                            }
                            return type;
                        }
                        if (nothing$ == null) break block12;
                        break block13;
                    }
                    if (!throwable.equals(nothing$)) break block13;
                }
                throw this.typeError(new StringBuilder().append((Object)"computing the common type instance of base types ").append((Object)refinedType.parents().mkString(", and ")).append((Object)" leads to a cycle.").toString());
            }
            throw nothing$;
        }

        public Types.Type rawElem(int i) {
            return this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
        }

        public Symbols.Symbol typeSymbol(int i) {
            Symbols.Symbol symbol;
            Types.RefinedType refinedType;
            Types.Type type = this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
            if (type instanceof Types.RefinedType && (refinedType = (Types.RefinedType)type).parents() instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)refinedType.parents();
                symbol = ((Types.Type)$colon$colon.head()).typeSymbol();
            } else {
                symbol = type.typeSymbol();
            }
            return symbol;
        }

        public List<Types.Type> toList() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).toList();
        }

        public BaseTypeSeq copy(Types.Type head2, int offset) {
            Types.Type[] arr = new Types.Type[this.scala$reflect$internal$BaseTypeSeqs$$elems().length + offset];
            int n = this.scala$reflect$internal$BaseTypeSeqs$$elems().length;
            Types.Type[] typeArray = this.scala$reflect$internal$BaseTypeSeqs$$elems();
            Platform$ platform$ = Platform$.MODULE$;
            System.arraycopy(typeArray, 0, arr, offset, n);
            arr[0] = head2;
            return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().newBaseTypeSeq(this.scala$reflect$internal$BaseTypeSeqs$$parents(), arr);
        }

        public BaseTypeSeq prepend(Types.Type tp) {
            return this.copy(tp, 1);
        }

        public BaseTypeSeq updateHead(Types.Type tp) {
            return this.copy(tp, 0);
        }

        public BaseTypeSeq map(Function1<Types.Type, Types.Type> f) {
            int len = this.length();
            Types.Type[] arr = new Types.Type[len];
            for (int i = 0; i < len; ++i) {
                arr[i] = f.apply(this.scala$reflect$internal$BaseTypeSeqs$$elems()[i]);
            }
            return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().newBaseTypeSeq(this.scala$reflect$internal$BaseTypeSeqs$$parents(), arr);
        }

        public BaseTypeSeq lateMap(Function1<Types.Type, Types.Type> f) {
            return new MappedBaseTypeSeq(this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer(), this, f);
        }

        public boolean exists(Function1<Types.Type, Object> p) {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).exists(p);
        }

        public int maxDepth() {
            return this.bitmap$0 ? this.maxDepth : this.maxDepth$lzycompute();
        }

        public int maxDepthOfElems() {
            IntRef d = IntRef.create(Depth$.MODULE$.Zero());
            Predef$ predef$ = Predef$.MODULE$;
            int n = this.length();
            Range range2 = Range$.MODULE$.apply(1, n);
            if (!range2.isEmpty()) {
                int n2 = range2.start();
                while (true) {
                    d.elem = Depth$.MODULE$.max$extension(d.elem, this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().typeDepth(this.scala$reflect$internal$BaseTypeSeqs$$elems()[n2]));
                    if (n2 == range2.lastElement()) break;
                    n2 += range2.step();
                }
            }
            return d.elem;
        }

        public String toString() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).mkString("BTS(", ",", ")");
        }

        private Nothing$ typeError(String msg) {
            throw new Types.TypeError(this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer(), new StringBuilder().append((Object)"the type intersection ").append((Object)this.scala$reflect$internal$BaseTypeSeqs$$parents().mkString(" with ")).append((Object)" is malformed").append((Object)"\n --- because ---\n").append((Object)msg).toString());
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer() {
            return this.$outer;
        }

        public BaseTypeSeq(SymbolTable $outer, List<Types.Type> parents2, Types.Type[] elems) {
            this.scala$reflect$internal$BaseTypeSeqs$$parents = parents2;
            this.scala$reflect$internal$BaseTypeSeqs$$elems = elems;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.Counter counter = BaseTypeSeqsStats$.MODULE$.baseTypeSeqCount();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + 1);
                }
            }
            if (Statistics$.MODULE$.canEnable()) {
                int n = elems.length;
                Statistics.Counter counter = BaseTypeSeqsStats$.MODULE$.baseTypeSeqLenTotal();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + n);
                }
            }
            this.pending = new BitSet(this.length());
        }
    }

    public class MappedBaseTypeSeq
    extends BaseTypeSeq {
        private final BaseTypeSeq orig;
        public final Function1<Types.Type, Types.Type> scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f;

        @Override
        public Types.Type apply(int i) {
            return this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f.apply(this.orig.apply(i));
        }

        @Override
        public Types.Type rawElem(int i) {
            return this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f.apply(this.orig.rawElem(i));
        }

        @Override
        public Symbols.Symbol typeSymbol(int i) {
            return this.orig.typeSymbol(i);
        }

        @Override
        public List<Types.Type> toList() {
            return this.orig.toList().map(this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f, List$.MODULE$.canBuildFrom());
        }

        @Override
        public BaseTypeSeq copy(Types.Type head2, int offset) {
            return this.orig.map(this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f).copy(head2, offset);
        }

        @Override
        public BaseTypeSeq map(Function1<Types.Type, Types.Type> g) {
            return this.lateMap(g);
        }

        @Override
        public BaseTypeSeq lateMap(Function1<Types.Type, Types.Type> g) {
            return this.orig.lateMap((Function1<Types.Type, Types.Type>)((Object)new Serializable(this, g){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ MappedBaseTypeSeq $outer;
                private final Function1 g$1;

                public final Types.Type apply(Types.Type x) {
                    return (Types.Type)this.g$1.apply(this.$outer.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f.apply(x));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.g$1 = g$1;
                }
            }));
        }

        @Override
        public boolean exists(Function1<Types.Type, Object> p) {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).exists(new Serializable(this, p){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ MappedBaseTypeSeq $outer;
                private final Function1 p$1;

                public final boolean apply(Types.Type x) {
                    return BoxesRunTime.unboxToBoolean(this.p$1.apply(this.$outer.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f.apply(x)));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.p$1 = p$1;
                }
            });
        }

        @Override
        public int maxDepthOfElems() {
            return ((Depth)Predef$.MODULE$.genericArrayOps(Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ MappedBaseTypeSeq $outer;

                public final int apply(Types.Type x) {
                    return this.$outer.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$$outer().typeDepth(this.$outer.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f.apply(x));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(Depth.class)))).max(Ordering$.MODULE$.ordered(Predef$.MODULE$.$conforms()))).depth();
        }

        @Override
        public String toString() {
            return Predef$.MODULE$.refArrayOps((Object[])this.scala$reflect$internal$BaseTypeSeqs$$elems()).mkString("MBTS(", ",", ")");
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$$outer() {
            return this.$outer;
        }

        public MappedBaseTypeSeq(SymbolTable $outer, BaseTypeSeq orig, Function1<Types.Type, Types.Type> f) {
            this.orig = orig;
            this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$f = f;
            super($outer, orig.scala$reflect$internal$BaseTypeSeqs$$parents().map(f, List$.MODULE$.canBuildFrom()), orig.scala$reflect$internal$BaseTypeSeqs$$elems());
        }
    }
}

