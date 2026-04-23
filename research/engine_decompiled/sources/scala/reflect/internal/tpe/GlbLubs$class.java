/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.LinearSeqOptimized;
import scala.collection.SeqLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordered$class;
import scala.math.Ordering$Int$;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.TypesStats$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.GlbLubs$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;
import scala.reflect.internal.util.TableDef;
import scala.reflect.internal.util.TableDef$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.sys.package$;

public abstract class GlbLubs$class {
    private static void printLubMatrix(SymbolTable $this, Map btsMap, int depth) {
        List sorted2 = (List)btsMap.toList().sortWith((Function2)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Tuple2<Types.Type, List<Types.Type>> x, Tuple2<Types.Type, List<Types.Type>> y) {
                return x._1().typeSymbol().isLess(y._1().typeSymbol());
            }
        }));
        int maxSeqLength = BoxesRunTime.unboxToInt(((TraversableOnce)sorted2.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Tuple2<Types.Type, List<Types.Type>> x$1) {
                return ((SeqLike)x$1._2()).size();
            }
        }, List$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$));
        List padded = sorted2.map(new Serializable($this, maxSeqLength){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final int maxSeqLength$1;

            public final List<Types.Type> apply(Tuple2<Types.Type, List<Types.Type>> x$2) {
                return ((SeqLike)x$2._2()).padTo(this.maxSeqLength$1, this.$outer.NoType(), List$.MODULE$.canBuildFrom());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.maxSeqLength$1 = maxSeqLength$1;
            }
        }, List$.MODULE$.canBuildFrom());
        List transposed = (List)padded.transpose((Function1)Predef$.MODULE$.$conforms());
        List columns = $this.mapWithIndex(sorted2, new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final TableDef.Column<List<Types.Type>> apply(Tuple2<Types.Type, List<Types.Type>> x0$1, int x1$1) {
                Tuple2<Tuple2<Types.Type, List<Types.Type>>, Integer> tuple2 = new Tuple2<Tuple2<Types.Type, List<Types.Type>>, Integer>(x0$1, BoxesRunTime.boxToInteger(x1$1));
                if (tuple2._1() != null) {
                    return new TableDef.Column<List<Types.Type>>(GlbLubs$class.str$1(this.$outer, tuple2._1()._1()), (Function1<List<Types.Type>, Object>)((Object)new Serializable(this, tuple2){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ GlbLubs$.anonfun.4 $outer;
                        private final Tuple2 x1$2;

                        public final String apply(List<Types.Type> xs) {
                            return GlbLubs$class.str$1(this.$outer.$outer, xs.apply(this.x1$2._2$mcI$sp()));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.x1$2 = x1$2;
                        }
                    }), true);
                }
                throw new MatchError(tuple2);
            }

            public /* synthetic */ SymbolTable scala$reflect$internal$tpe$GlbLubs$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        TableDef tableDef = TableDef$.MODULE$.apply(columns);
        TableDef.Table formatted = tableDef.table(transposed);
        Predef$.MODULE$.println(new StringBuilder().append((Object)"** Depth is ").append(new Depth(depth)).append((Object)"\n").append(formatted).toString());
    }

    public static List findRecursiveBounds(SymbolTable $this, List ts) {
        List list2;
        if (ts.isEmpty()) {
            list2 = Nil$.MODULE$;
        } else {
            Symbols.Symbol sym = ((Types.Type)ts.head()).typeSymbol();
            Serializable serializable = new Serializable($this, ts){
                public static final long serialVersionUID = 0L;
                public final List ts$3;

                public final List<Types.Type> apply() {
                    return this.ts$3;
                }
                {
                    this.ts$3 = ts$3;
                }
            };
            boolean bl = ((LinearSeqOptimized)ts.tail()).forall(new Serializable($this, sym){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol sym$1;

                public final boolean apply(Types.Type x$3) {
                    Symbols.Symbol symbol = x$3.typeSymbol();
                    Symbols.Symbol symbol2 = this.sym$1;
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                }
                {
                    this.sym$1 = sym$1;
                }
            });
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append(serializable.ts$3).toString());
            }
            list2 = sym.typeParams().flatMap(new Serializable($this, sym){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol sym$1;

                public final List<Tuple2<Symbols.Symbol, Symbols.Symbol>> apply(Symbols.Symbol p) {
                    return this.sym$1.typeParams().withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, p){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol p$1;

                        public final boolean apply(Symbols.Symbol in) {
                            return in.info().bounds().contains(this.p$1);
                        }
                        {
                            this.p$1 = p$1;
                        }
                    })).map(new Serializable(this, p){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol p$1;

                        public final Tuple2<Symbols.Symbol, Symbols.Symbol> apply(Symbols.Symbol in) {
                            Symbols.Symbol symbol = Predef$.MODULE$.ArrowAssoc(this.p$1);
                            Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                            return new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol, in);
                        }
                        {
                            this.p$1 = p$1;
                        }
                    }, List$.MODULE$.canBuildFrom());
                }
                {
                    this.sym$1 = sym$1;
                }
            }, List$.MODULE$.canBuildFrom());
        }
        return list2;
    }

    private static boolean willViolateRecursiveBounds(SymbolTable $this, Types.Type tp, List ts, List tsElimSub) {
        boolean bl;
        block6: {
            block2: {
                Option option;
                block5: {
                    boolean bl2;
                    block4: {
                        block3: {
                            Types.Type type;
                            Symbols.Symbol typeSym = ((Types.Type)ts.head()).typeSymbol();
                            if (!GlbLubs$class.isRecursive$1($this, ts, typeSym)) break block2;
                            option = $this.transposeSafe(tsElimSub.map(new Serializable($this){
                                public static final long serialVersionUID = 0L;

                                public final List<Types.Type> apply(Types.Type x$5) {
                                    return x$5.normalize().typeArgs();
                                }
                            }, List$.MODULE$.canBuildFrom()));
                            if (!(option instanceof Some)) break block3;
                            Some some = (Some)option;
                            if (tp instanceof Types.ExistentialType) {
                                Types.ExistentialType existentialType = (Types.ExistentialType)tp;
                                type = existentialType.underlying();
                            } else {
                                type = tp;
                            }
                            List<Types.Type> mergedTypeArgs = type.typeArgs();
                            bl2 = $this.exists3(typeSym.typeParams(), mergedTypeArgs, (List)some.x(), new Serializable($this, ts){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ SymbolTable $outer;
                                private final List ts$1;

                                public final boolean apply(Symbols.Symbol param2, Types.Type arg, List<Types.Type> lubbedArgs) {
                                    boolean isExistential = arg.typeSymbol().isExistentiallyBound();
                                    boolean isInFBound = GlbLubs$class.fbounds$1(this.$outer, this.ts$1).contains(param2);
                                    boolean wasLubbed = !lubbedArgs.exists((Function1<Types.Type, Object>)((Object)new Serializable(this, arg){
                                        public static final long serialVersionUID = 0L;
                                        private final Types.Type arg$1;

                                        public final boolean apply(Types.Type x$6) {
                                            return x$6.$eq$colon$eq(this.arg$1);
                                        }
                                        {
                                            this.arg$1 = arg$1;
                                        }
                                    }));
                                    return !isExistential && isInFBound && wasLubbed;
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                    this.ts$1 = ts$1;
                                }
                            });
                            break block4;
                        }
                        if (!None$.MODULE$.equals(option)) break block5;
                        bl2 = false;
                    }
                    if (!bl2) break block2;
                    bl = true;
                    break block6;
                }
                throw new MatchError(option);
            }
            bl = false;
        }
        return bl;
    }

    public static List lubList(SymbolTable $this, List ts, int depth) {
        IntRef lubListDepth = IntRef.create(Depth$.MODULE$.Zero());
        List initialBTSes = ts.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final List<Types.Type> apply(Types.Type x$14) {
                return x$14.baseTypeSeq().toList();
            }
        }, List$.MODULE$.canBuildFrom());
        if ($this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
            GlbLubs$class.printLubMatrix($this, ((TraversableOnce)ts.zip(initialBTSes, List$.MODULE$.canBuildFrom())).toMap(Predef$.MODULE$.$conforms()), depth);
        }
        return GlbLubs$class.loop$1($this, Nil$.MODULE$, initialBTSes, lubListDepth, ts, depth);
    }

    private static Symbols.Symbol minSym(SymbolTable $this, List tps) {
        Symbols.Symbol symbol = ((Types.Type)tps.head()).typeSymbol();
        return ((TraversableOnce)tps.tail()).$div$colon(symbol, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Symbols.Symbol sym1, Types.Type tp2) {
                return tp2.typeSymbol().isLess(sym1) ? tp2.typeSymbol() : sym1;
            }
        });
    }

    public static List spanningTypes(SymbolTable $this, List ts) {
        block4: {
            List list2;
            block3: {
                block2: {
                    Some<List> some = List$.MODULE$.unapplySeq(ts);
                    if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block2;
                    list2 = Nil$.MODULE$;
                    break block3;
                }
                if (!(ts instanceof $colon$colon)) break block4;
                $colon$colon $colon$colon = ($colon$colon)ts;
                Types.Type type = (Types.Type)$colon$colon.head();
                list2 = $this.spanningTypes((List)$colon$colon.tl$1().filter((Function1)((Object)new Serializable($this, $colon$colon){
                    public static final long serialVersionUID = 0L;
                    private final $colon$colon x3$1;

                    public final boolean apply(Types.Type t) {
                        return !((Types.Type)this.x3$1.head()).typeSymbol().isSubClass(t.typeSymbol());
                    }
                    {
                        this.x3$1 = x3$1;
                    }
                }))).$colon$colon(type);
            }
            return list2;
        }
        throw new MatchError(ts);
    }

    private static List elimSuper(SymbolTable $this, List ts) {
        block8: {
            GenTraversable<Nothing$> genTraversable;
            block6: {
                List<Types.Type> list2;
                block7: {
                    block5: {
                        Some<List> some = List$.MODULE$.unapplySeq(ts);
                        if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block5;
                        genTraversable = Nil$.MODULE$;
                        break block6;
                    }
                    Some<List> some = List$.MODULE$.unapplySeq(ts);
                    if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(1) != 0) break block7;
                    Types.Type t = (Types.Type)((LinearSeqOptimized)some.get()).apply(0);
                    genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{t}));
                    break block6;
                }
                if (!(ts instanceof $colon$colon)) break block8;
                $colon$colon $colon$colon = ($colon$colon)ts;
                List<Types.Type> rest = GlbLubs$class.elimSuper($this, (List)$colon$colon.tl$1().filter((Function1)((Object)new Serializable($this, $colon$colon){
                    public static final long serialVersionUID = 0L;
                    private final $colon$colon x4$1;

                    public final boolean apply(Types.Type t1) {
                        return !((Types.Type)this.x4$1.head()).$less$colon$less(t1);
                    }
                    {
                        this.x4$1 = x4$1;
                    }
                })));
                if (rest.exists((Function1<Types.Type, Object>)((Object)new Serializable($this, $colon$colon){
                    public static final long serialVersionUID = 0L;
                    private final $colon$colon x4$1;

                    public final boolean apply(Types.Type t1) {
                        return t1.$less$colon$less((Types.Type)this.x4$1.head());
                    }
                    {
                        this.x4$1 = x4$1;
                    }
                }))) {
                    list2 = rest;
                } else {
                    Types.Type type = (Types.Type)$colon$colon.head();
                    list2 = rest.$colon$colon(type);
                }
                genTraversable = list2;
            }
            return genTraversable;
        }
        throw new MatchError(ts);
    }

    private static List elimSub(SymbolTable $this, List ts, int depth) {
        while (true) {
            List<Types.Type> ts1;
            block5: {
                List list2;
                block4: {
                    List ts0;
                    block3: {
                        if (!(ts0 = GlbLubs$class.elimSub0$1($this, ts, depth)).isEmpty() && !((SeqLike)ts0.tail()).isEmpty()) break block3;
                        list2 = ts0;
                        break block4;
                    }
                    ts1 = ts0.mapConserve(new Serializable($this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;

                        public final Types.Type apply(Types.Type t) {
                            return this.$outer.elimAnonymousClass(t.dealiasWiden());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    });
                    if (ts1 != ts0) break block5;
                    list2 = ts0;
                }
                return list2;
            }
            ts = ts1;
        }
    }

    private static Tuple2 stripExistentialsAndTypeVars(SymbolTable $this, List ts) {
        List quantified = ts.flatMap(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final List<Symbols.Symbol> apply(Types.Type x0$3) {
                List list2;
                if (x0$3 instanceof Types.ExistentialType) {
                    Types.ExistentialType existentialType = (Types.ExistentialType)x0$3;
                    list2 = existentialType.quantified();
                } else {
                    list2 = Nil$.MODULE$;
                }
                return list2;
            }
        }, List$.MODULE$.canBuildFrom());
        List<Types.Type> strippedTypes = ts.mapConserve(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Types.Type apply(Types.Type tp) {
                return GlbLubs$class.stripType$1(this.$outer, tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        return new Tuple2(strippedTypes, quantified);
    }

    public static boolean sameWeakLubAsLub(SymbolTable $this, List tps) {
        $colon$colon $colon$colon;
        boolean bl = ((Object)Nil$.MODULE$).equals(tps) ? true : (tps instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)tps).tl$1()) ? !BoxesRunTime.unboxToBoolean($this.typeHasAnnotations().apply((Types.Type)$colon$colon.head())) : !tps.exists($this.typeHasAnnotations()) && !tps.forall(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return this.$outer.definitions().isNumericValueType(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        return bl;
    }

    public static Types.Type weakLub(SymbolTable $this, List tps) {
        return tps.isEmpty() ? $this.definitions().NothingTpe() : (tps.forall(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type tp) {
                return this.$outer.definitions().isNumericValueType(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }) ? $this.numericLub(tps) : (tps.exists($this.typeHasAnnotations()) ? $this.annotationsLub((Types.Type)$this.lub(tps.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.Type x$19) {
                return x$19.withoutAnnotations();
            }
        }, List$.MODULE$.canBuildFrom())), tps) : $this.lub(tps)));
    }

    public static Types.Type numericLub(SymbolTable $this, List ts) {
        return ts.reduceLeft(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final Types.Type apply(Types.Type t1, Types.Type t2) {
                return this.$outer.isNumericSubType(t1, t2) ? t2 : (this.$outer.isNumericSubType(t2, t1) ? t1 : this.$outer.definitions().IntTpe());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static HashMap lubResults(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$GlbLubs$$_lubResults();
    }

    public static HashMap glbResults(SymbolTable $this) {
        return $this.scala$reflect$internal$tpe$GlbLubs$$_glbResults();
    }

    public static Types.Type lub(SymbolTable $this, List ts) {
        Tuple2<Object, Object> start;
        $colon$colon $colon$colon;
        Types.Type type;
        if (((Object)Nil$.MODULE$).equals(ts)) {
            type = $this.definitions().NothingTpe();
        } else if (ts instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)ts).tl$1())) {
            type = (Types.Type)$colon$colon.head();
        } else {
            Types.Type type2;
            $colon$colon $colon$colon2;
            Tuple2<Object, Object> tuple2;
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.Counter counter = TypesStats$.MODULE$.lubCount();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                    counter.value_$eq(counter.value() + 1);
                }
            }
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.lubNanos()) : null;
            } else {
                tuple2 = null;
            }
            start = tuple2;
            Types.Type res = $this.lub(ts, $this.lubDepth(ts));
            List list2 = (List)((SeqLike)ts.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final int apply(Types.Type x$20) {
                    return x$20.typeParams().size();
                }
            }, List$.MODULE$.canBuildFrom())).distinct();
            if (list2 instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon2 = ($colon$colon)list2).tl$1()) && res.typeParams().size() != BoxesRunTime.unboxToInt($colon$colon2.head())) {
                Types.Type type3 = res.typeConstructor();
                Serializable serializable = new Serializable($this, res, ts){
                    public static final long serialVersionUID = 0L;
                    private final Types.Type res$1;
                    private final List ts$5;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Stripping type args from lub because ", " is not consistent with ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.res$1, this.ts$5}));
                    }
                    {
                        this.res$1 = res$1;
                        this.ts$5 = ts$5;
                    }
                };
                $this.log((Function0<Object>)((Object)new Serializable($this, (Function0)((Object)serializable), type3){
                    public static final long serialVersionUID = 0L;
                    private final Function0 msg$1;
                    private final Object result$1;

                    public final String apply() {
                        return new StringBuilder().append((Object)((String)this.msg$1.apply())).append((Object)": ").append(this.result$1).toString();
                    }
                    {
                        this.msg$1 = msg$1;
                        this.result$1 = result$1;
                    }
                }));
                type2 = type3;
            } else {
                type2 = res;
            }
            type = type2;
        }
        return type;
        finally {
            $this.lubResults().clear();
            $this.glbResults().clear();
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                    timerStack.pop(start);
                }
            }
        }
    }

    public static Types.Type lub(SymbolTable $this, List ts, int depth) {
        if ($this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
            Predef$.MODULE$.println(new StringBuilder().append((Object)$this.indent()).append((Object)"lub of ").append(ts).append((Object)" at depth ").append(new Depth(depth)).toString());
            $this.indent_$eq(new StringBuilder().append((Object)$this.indent()).append((Object)"  ").toString());
            Predef$.MODULE$.assert($this.indent().length() <= 100);
        }
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = TypesStats$.MODULE$.nestedLubCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        Types.Type res = GlbLubs$class.lub0$1($this, ts, depth);
        if ($this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
            String string2 = $this.indent();
            Predef$ predef$ = Predef$.MODULE$;
            $this.indent_$eq(new StringOps(string2).stripSuffix("  "));
            Predef$.MODULE$.println(new StringBuilder().append((Object)$this.indent()).append((Object)"lub of ").append(ts).append((Object)" is ").append(res).toString());
        }
        return res;
    }

    public static Types.Type glb(SymbolTable $this, List ts) {
        Tuple2<Object, Object> start;
        Types.Type type;
        List list2 = GlbLubs$class.elimSuper($this, ts);
        Some<List> some = List$.MODULE$.unapplySeq(list2);
        if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0) {
            type = $this.definitions().AnyTpe();
        } else {
            Some<List> some2 = List$.MODULE$.unapplySeq(list2);
            if (!some2.isEmpty() && some2.get() != null && ((LinearSeqOptimized)some2.get()).lengthCompare(1) == 0) {
                Types.Type t;
                type = t = (Types.Type)((LinearSeqOptimized)some2.get()).apply(0);
            } else {
                Tuple2<Object, Object> tuple2;
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.Counter counter = TypesStats$.MODULE$.lubCount();
                    if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                        counter.value_$eq(counter.value() + 1);
                    }
                }
                if (Statistics$.MODULE$.canEnable()) {
                    Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                    tuple2 = Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null ? timerStack.push(TypesStats$.MODULE$.lubNanos()) : null;
                } else {
                    tuple2 = null;
                }
                start = tuple2;
                type = $this.glbNorm(list2, $this.lubDepth(list2));
            }
        }
        return type;
        finally {
            $this.lubResults().clear();
            $this.glbResults().clear();
            if (Statistics$.MODULE$.canEnable()) {
                Statistics.TimerStack timerStack = TypesStats$.MODULE$.typeOpsStack();
                if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && timerStack != null) {
                    timerStack.pop(start);
                }
            }
        }
    }

    public static Types.Type glb(SymbolTable $this, List ts, int depth) {
        Types.Type t;
        Some<List> some;
        List list2 = GlbLubs$class.elimSuper($this, ts);
        Some<List> some2 = List$.MODULE$.unapplySeq(list2);
        Types.Type type = !some2.isEmpty() && some2.get() != null && ((LinearSeqOptimized)some2.get()).lengthCompare(0) == 0 ? $this.definitions().AnyTpe() : (!(some = List$.MODULE$.unapplySeq(list2)).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 ? (t = (Types.Type)((LinearSeqOptimized)some.get()).apply(0)) : $this.glbNorm(list2, depth));
        return type;
    }

    public static Types.Type glbNorm(SymbolTable $this, List ts, int depth) {
        if (Statistics$.MODULE$.canEnable()) {
            Statistics.Counter counter = TypesStats$.MODULE$.nestedLubCount();
            if (Statistics$.MODULE$.scala$reflect$internal$util$Statistics$$_enabled() && counter != null) {
                counter.value_$eq(counter.value() + 1);
            }
        }
        return GlbLubs$class.glb0$1($this, ts, ts, depth);
    }

    private static List matchingBounds(SymbolTable $this, List tps, List tparams2) {
        return tps.map(new Serializable($this, tps, tparams2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List tps$1;
            private final List tparams$1;

            public final List<Types.Type> apply(Types.Type tp) {
                return GlbLubs$class.getBounds$1(this.$outer, tp, this.tps$1, this.tparams$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tps$1 = tps$1;
                this.tparams$1 = tparams$1;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    private static List matchingInstTypes(SymbolTable $this, List tps, List tparams2) {
        return tps.map(new Serializable($this, tps, tparams2){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List tps$2;
            private final List tparams$2;

            public final Types.Type apply(Types.Type tp) {
                return GlbLubs$class.transformResultType$1(this.$outer, tp, this.tps$2, this.tparams$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tps$2 = tps$2;
                this.tparams$2 = tparams$2;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    private static List matchingRestypes(SymbolTable $this, List tps, List pts) {
        return tps.map(new Serializable($this, tps, pts){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final List tps$3;
            private final List pts$1;

            public final Types.Type apply(Types.Type x0$4) {
                block4: {
                    Types.Type type;
                    block3: {
                        block2: {
                            Types.MethodType methodType;
                            if (!(x0$4 instanceof Types.MethodType) || !this.$outer.isSameTypes((methodType = (Types.MethodType)x0$4).paramTypes(), this.pts$1)) break block2;
                            type = methodType.resultType();
                            break block3;
                        }
                        if (!(x0$4 instanceof Types.NullaryMethodType)) break block4;
                        Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)x0$4;
                        if (!this.pts$1.isEmpty()) break block4;
                        type = nullaryMethodType.resultType();
                    }
                    return type;
                }
                throw new Types.NoCommonType(this.$outer, this.tps$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tps$3 = tps$3;
                this.pts$1 = pts$1;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static final String str$1(SymbolTable $this, Types.Type tp) {
        String string2;
        Types.Type type = tp;
        Types$NoType$ types$NoType$ = $this.NoType();
        if (!(type != null ? !type.equals(types$NoType$) : types$NoType$ != null)) {
            string2 = "";
        } else {
            String s2 = String.valueOf(tp).replaceAll("[\\w.]+\\.(\\w+)", "$1");
            if (s2.length() < 60) {
                string2 = s2;
            } else {
                Predef$ predef$ = Predef$.MODULE$;
                string2 = new StringBuilder().append((Object)((String)new StringOps(s2).take(57))).append((Object)"...").toString();
            }
        }
        return string2;
    }

    public static final List fbounds$1(SymbolTable $this, List ts$1) {
        return $this.findRecursiveBounds(ts$1).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(Tuple2<Symbols.Symbol, Symbols.Symbol> x$4) {
                return x$4._2();
            }
        }, List$.MODULE$.canBuildFrom());
    }

    private static final boolean isRecursive$1(SymbolTable $this, List ts$1, Symbols.Symbol typeSym$1) {
        List list2 = GlbLubs$class.fbounds$1($this, ts$1);
        return typeSym$1.typeParams().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, list2){
            public static final long serialVersionUID = 0L;
            private final List eta$0$1$1;

            public final boolean apply(Object elem) {
                return this.eta$0$1$1.contains(elem);
            }
            {
                this.eta$0$1$1 = eta$0$1$1;
            }
        }));
    }

    private static final boolean isHotForTs$1(SymbolTable $this, List xs, List ts$4) {
        return ts$4.exists(new Serializable($this, xs){
            public static final long serialVersionUID = 0L;
            private final List xs$1;

            public final boolean apply(Types.Type x$7) {
                List<Symbols.Symbol> list2 = x$7.typeParams();
                List<A> list3 = this.xs$1.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Symbols.Symbol apply(Types.Type x$8) {
                        return x$8.typeSymbol();
                    }
                }, List$.MODULE$.canBuildFrom());
                return !(list2 != null ? !((Object)list2).equals(list3) : list3 != null);
            }
            {
                this.xs$1 = xs$1;
            }
        });
    }

    public static final Types.Type elimHigherOrderTypeParam$1(SymbolTable $this, Types.Type tp, List ts$4) {
        Types.Type type;
        Types.TypeRef typeRef;
        if (tp instanceof Types.TypeRef && (typeRef = (Types.TypeRef)tp).args().nonEmpty() && GlbLubs$class.isHotForTs$1($this, typeRef.args(), ts$4)) {
            Types.Type type2 = tp.typeConstructor();
            Serializable serializable = new Serializable($this, tp){
                public static final long serialVersionUID = 0L;
                private final Types.Type tp$1;

                public final String apply() {
                    return new StringBuilder().append((Object)"Retracting dummies from ").append(this.tp$1).append((Object)" in lublist").toString();
                }
                {
                    this.tp$1 = tp$1;
                }
            };
            $this.log((Function0<Object>)((Object)new /* invalid duplicate definition of identical inner class */));
            type = type2;
        } else {
            type = tp;
        }
        return type;
    }

    private static final List loop$1(SymbolTable $this, List pretypes, List tsBts, IntRef lubListDepth$1, List ts$4, int depth$2) {
        List ts0;
        while (true) {
            block10: {
                List list2;
                block9: {
                    block8: {
                        lubListDepth$1.elem = Depth$.MODULE$.incr$extension1(lubListDepth$1.elem);
                        if (!tsBts.isEmpty() && !tsBts.exists($this.typeListIsEmpty())) break block8;
                        list2 = pretypes.reverse();
                        break block9;
                    }
                    if (!((SeqLike)tsBts.tail()).isEmpty()) break block10;
                    list2 = ((List)pretypes.reverse()).$plus$plus((GenTraversableOnce)tsBts.head(), List$.MODULE$.canBuildFrom());
                }
                return list2;
            }
            ts0 = tsBts.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(List<Types.Type> x$9) {
                    return x$9.head();
                }
            }, List$.MODULE$.canBuildFrom());
            if (!(ts0 instanceof $colon$colon)) break;
            $colon$colon $colon$colon = ($colon$colon)ts0;
            boolean bl = $colon$colon.tl$1().forall(new Serializable($this, $colon$colon){
                public static final long serialVersionUID = 0L;
                private final $colon$colon x2$1;

                public final boolean apply(Types.Type x$10) {
                    Symbols.Symbol symbol = x$10.typeSymbol();
                    Symbols.Symbol symbol2 = ((Types.Type)this.x2$1.head()).typeSymbol();
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                }
                {
                    this.x2$1 = x2$1;
                }
            });
            if (bl) {
                List tails2 = tsBts.map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final List<Types.Type> apply(List<Types.Type> x$11) {
                        return (List)x$11.tail();
                    }
                }, List$.MODULE$.canBuildFrom());
                List<Types.Type> ts1 = GlbLubs$class.elimSub($this, ts0, depth$2).map(new Serializable($this, ts$4){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final List ts$4;

                    public final Types.Type apply(Types.Type tp) {
                        return GlbLubs$class.elimHigherOrderTypeParam$1(this.$outer, tp, this.ts$4);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.ts$4 = ts$4;
                    }
                }, List$.MODULE$.canBuildFrom());
                Types.Type type = $this.mergePrefixAndArgs(ts1, Variance$.MODULE$.Covariant(), depth$2);
                if ($this.NoType().equals(type)) {
                    tsBts = tails2;
                    continue;
                }
                MutableSettings.SettingValue settingValue = $this.scala$reflect$internal$tpe$GlbLubs$$strictInference();
                MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                if (BoxesRunTime.unboxToBoolean(settingValue.value()) && GlbLubs$class.willViolateRecursiveBounds($this, type, ts0, ts1)) {
                    $this.log((Function0<Object>)((Object)new Serializable($this, type){
                        public static final long serialVersionUID = 0L;
                        private final Types.Type x1$3;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Breaking recursion in lublist, advancing frontier and discaring merged prefix/args from ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.x1$3}));
                        }
                        {
                            this.x1$3 = x1$3;
                        }
                    }));
                    tsBts = tails2;
                    continue;
                }
                tsBts = tails2;
                pretypes = pretypes.$colon$colon(type);
                continue;
            }
            Symbols.Symbol sym = GlbLubs$class.minSym($this, ts0);
            List<List<Types.Type>> newtps = tsBts.map(new Serializable($this, sym){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol sym$2;

                public final List<Types.Type> apply(List<Types.Type> ts) {
                    Symbols.Symbol symbol = ts.head().typeSymbol();
                    Symbols.Symbol symbol2 = this.sym$2;
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null) ? (List)ts.tail() : ts;
                }
                {
                    this.sym$2 = sym$2;
                }
            }, List$.MODULE$.canBuildFrom());
            if ($this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
                String str = ((TraversableOnce)newtps.zipWithIndex(List$.MODULE$.canBuildFrom()).map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final String apply(Tuple2<List<Types.Type>, Object> x0$2) {
                        if (x0$2 != null) {
                            return ((TraversableOnce)x0$2._1().map(new Serializable(this){
                                public static final long serialVersionUID = 0L;

                                public final String apply(Types.Type x$13) {
                                    return new StringBuilder().append((Object)"        ").append(x$13).append((Object)"\n").toString();
                                }
                            }, List$.MODULE$.canBuildFrom())).mkString(new StringBuilder().append((Object)"   (").append(BoxesRunTime.boxToInteger(x0$2._2$mcI$sp())).append((Object)")\n").toString(), "", "\n");
                        }
                        throw new MatchError(x0$2);
                    }
                }, List$.MODULE$.canBuildFrom())).mkString("");
                Predef$.MODULE$.println(new StringBuilder().append((Object)"Frontier(\n").append((Object)str).append((Object)")").toString());
                GlbLubs$class.printLubMatrix($this, ((TraversableOnce)ts$4.zip(tsBts, List$.MODULE$.canBuildFrom())).toMap(Predef$.MODULE$.$conforms()), lubListDepth$1.elem);
            }
            tsBts = newtps;
        }
        throw new MatchError(ts0);
    }

    private static final List elimSub0$1(SymbolTable $this, List ts, int depth$1) {
        block8: {
            GenTraversable<Nothing$> genTraversable;
            block6: {
                List<Types.Type> list2;
                block7: {
                    block5: {
                        Some<List> some = List$.MODULE$.unapplySeq(ts);
                        if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block5;
                        genTraversable = Nil$.MODULE$;
                        break block6;
                    }
                    Some<List> some = List$.MODULE$.unapplySeq(ts);
                    if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(1) != 0) break block7;
                    Types.Type t = (Types.Type)((LinearSeqOptimized)some.get()).apply(0);
                    genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{t}));
                    break block6;
                }
                if (!(ts instanceof $colon$colon)) break block8;
                $colon$colon $colon$colon = ($colon$colon)ts;
                List<Types.Type> rest = GlbLubs$class.elimSub0$1($this, (List)$colon$colon.tl$1().filter((Function1)((Object)new Serializable($this, depth$1, $colon$colon){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final int depth$1;
                    private final $colon$colon x4$2;

                    public final boolean apply(Types.Type t1) {
                        return !this.$outer.isSubType(t1, (Types.Type)this.x4$2.head(), Depth$.MODULE$.decr$extension1(this.depth$1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.depth$1 = depth$1;
                        this.x4$2 = x4$2;
                    }
                })), depth$1);
                if (rest.exists((Function1<Types.Type, Object>)((Object)new Serializable($this, depth$1, $colon$colon){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final int depth$1;
                    private final $colon$colon x4$2;

                    public final boolean apply(Types.Type t1) {
                        return this.$outer.isSubType((Types.Type)this.x4$2.head(), t1, Depth$.MODULE$.decr$extension1(this.depth$1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.depth$1 = depth$1;
                        this.x4$2 = x4$2;
                    }
                }))) {
                    list2 = rest;
                } else {
                    Types.Type type = (Types.Type)$colon$colon.head();
                    list2 = rest.$colon$colon(type);
                }
                genTraversable = list2;
            }
            return genTraversable;
        }
        throw new MatchError(ts);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final Types.Type stripType$1(SymbolTable $this, Types.Type tp) {
        Types.TypeVar typeVar;
        while (true) {
            if (tp instanceof Types.ExistentialType) {
                Types.ExistentialType existentialType = (Types.ExistentialType)tp;
                return existentialType.underlying();
            }
            if (!(tp instanceof Types.TypeVar)) return tp;
            typeVar = (Types.TypeVar)tp;
            if (!typeVar.instValid()) break;
            tp = typeVar.constr().inst();
        }
        if (!typeVar.untouchable()) throw $this.abort(new StringBuilder().append((Object)"trying to do lub/glb of typevar ").append(tp).toString());
        return typeVar;
    }

    private static final Types.Type lub0$1(SymbolTable $this, List ts0, int depth$3) {
        Option<Types.Type> option;
        block12: {
            Types.Type type;
            block3: {
                Types.Type type2;
                block11: {
                    List list2;
                    block10: {
                        block9: {
                            $colon$colon $colon$colon;
                            boolean bl;
                            block8: {
                                block7: {
                                    block6: {
                                        block5: {
                                            block4: {
                                                Types.Type t;
                                                block2: {
                                                    bl = false;
                                                    $colon$colon = null;
                                                    list2 = GlbLubs$class.elimSub($this, ts0, depth$3);
                                                    Some<List> some = List$.MODULE$.unapplySeq(list2);
                                                    if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block2;
                                                    type = $this.definitions().NothingTpe();
                                                    break block3;
                                                }
                                                Some<List> some = List$.MODULE$.unapplySeq(list2);
                                                if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(1) != 0) break block4;
                                                type = t = (Types.Type)((LinearSeqOptimized)some.get()).apply(0);
                                                break block3;
                                            }
                                            if (!(list2 instanceof $colon$colon)) break block5;
                                            bl = true;
                                            $colon$colon = ($colon$colon)list2;
                                            if (!($colon$colon.head() instanceof Types.PolyType)) break block5;
                                            Types.PolyType polyType = (Types.PolyType)$colon$colon.head();
                                            List<Symbols.Symbol> tparams1 = $this.map2(polyType.typeParams(), (List)GlbLubs$class.matchingBounds($this, $colon$colon, polyType.typeParams()).transpose((Function1)Predef$.MODULE$.$conforms()), new Serializable($this, depth$3){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ SymbolTable $outer;
                                                private final int depth$3;

                                                public final Symbols.Symbol apply(Symbols.Symbol tparam, List<Types.Type> bounds) {
                                                    return tparam.cloneSymbol().setInfo(this.$outer.glb(bounds, this.depth$3));
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                    this.depth$3 = depth$3;
                                                }
                                            });
                                            type = new Types.PolyType($this, tparams1, GlbLubs$class.lub0$1($this, GlbLubs$class.matchingInstTypes($this, $colon$colon, tparams1), depth$3));
                                            break block3;
                                        }
                                        if (!bl || !($colon$colon.head() instanceof Types.MethodType)) break block6;
                                        Types.MethodType methodType = (Types.MethodType)$colon$colon.head();
                                        type = new Types.MethodType($this, methodType.params(), GlbLubs$class.lub0$1($this, GlbLubs$class.matchingRestypes($this, $colon$colon, methodType.paramTypes()), depth$3));
                                        break block3;
                                    }
                                    if (!bl || !($colon$colon.head() instanceof Types.NullaryMethodType)) break block7;
                                    type = new Types.NullaryMethodType($this, GlbLubs$class.lub0$1($this, GlbLubs$class.matchingRestypes($this, $colon$colon, Nil$.MODULE$), depth$3));
                                    break block3;
                                }
                                if (!bl || !($colon$colon.head() instanceof Types.TypeBounds)) break block8;
                                type = $this.TypeBounds().apply($this.glb($colon$colon.map(new Serializable($this){
                                    public static final long serialVersionUID = 0L;

                                    public final Types.Type apply(Types.Type x$21) {
                                        return x$21.bounds().lo();
                                    }
                                }, List$.MODULE$.canBuildFrom()), depth$3), $this.lub($colon$colon.map(new Serializable($this){
                                    public static final long serialVersionUID = 0L;

                                    public final Types.Type apply(Types.Type x$22) {
                                        return x$22.bounds().hi();
                                    }
                                }, List$.MODULE$.canBuildFrom()), depth$3));
                                break block3;
                            }
                            if (!bl || !($colon$colon.head() instanceof Types.AnnotatedType)) break block9;
                            type = $this.annotationsLub(GlbLubs$class.lub0$1($this, $colon$colon.map(new Serializable($this){
                                public static final long serialVersionUID = 0L;

                                public final Types.Type apply(Types.Type x$23) {
                                    return x$23.withoutAnnotations();
                                }
                            }, List$.MODULE$.canBuildFrom()), depth$3), $colon$colon);
                            break block3;
                        }
                        option = $this.lubResults().get(new Tuple2<Depth, List>(new Depth(depth$3), list2));
                        if (!(option instanceof Some)) break block10;
                        Some some = (Some)option;
                        type2 = (Types.Type)some.x();
                        break block11;
                    }
                    if (!None$.MODULE$.equals(option)) break block12;
                    $this.lubResults().update(new Tuple2<Depth, List>(new Depth(depth$3), list2), $this.definitions().AnyTpe());
                    Types.Type res = Depth$.MODULE$.isNegative$extension(depth$3) ? $this.definitions().AnyTpe() : GlbLubs$class.lub1$1($this, list2, depth$3);
                    $this.lubResults().update(new Tuple2<Depth, List>(new Depth(depth$3), list2), res);
                    type2 = res;
                }
                type = type2;
            }
            return type;
        }
        throw new MatchError(option);
    }

    public static final boolean excludeFromLub$1(SymbolTable $this, Symbols.Symbol sym, Types.Type lubThisType$1, List narrowts$1, int depth$3) {
        return sym.isClass() || sym.isConstructor() || !sym.isPublic() || $this.definitions().isGetClass(sym) || sym.isFinal() || narrowts$1.exists(new Serializable($this, lubThisType$1, sym, depth$3){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Types.Type lubThisType$1;
            private final Symbols.Symbol sym$3;
            private final int depth$3;

            public final boolean apply(Types.Type t) {
                return !GlbLubs$class.refines$1(this.$outer, t, this.sym$3, this.lubThisType$1, this.depth$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.lubThisType$1 = lubThisType$1;
                this.sym$3 = sym$3;
                this.depth$3 = depth$3;
            }
        });
    }

    private static final Types.TypeBounds lubBounds$1(SymbolTable $this, List bnds, int depth$3) {
        return $this.TypeBounds().apply($this.glb(bnds.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.TypeBounds x$27) {
                return x$27.lo();
            }
        }, List$.MODULE$.canBuildFrom()), Depth$.MODULE$.decr$extension1(depth$3)), $this.lub(bnds.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.TypeBounds x$28) {
                return x$28.hi();
            }
        }, List$.MODULE$.canBuildFrom()), Depth$.MODULE$.decr$extension1(depth$3)));
    }

    public static final Symbols.Symbol lubsym$1(SymbolTable $this, Symbols.Symbol proto, Types.Type lubRefined$1, Types.Type lubThisType$1, List narrowts$1, int depth$3) {
        Symbols.Symbol symbol;
        Types.Type prototp = lubThisType$1.memberInfo(proto);
        List syms = narrowts$1.map(new Serializable($this, lubThisType$1, proto, prototp){
            public static final long serialVersionUID = 0L;
            public final Types.Type lubThisType$1;
            private final Symbols.Symbol proto$1;
            public final Types.Type prototp$1;

            public final Symbols.Symbol apply(Types.Type t) {
                return t.nonPrivateMember(this.proto$1.name()).filter((Function1)((Object)new Serializable(this, t){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ GlbLubs$.anonfun.23 $outer;
                    private final Types.Type t$1;

                    public final boolean apply(Symbols.Symbol sym) {
                        return sym.tpe().matches(this.$outer.prototp$1.substThis(this.$outer.lubThisType$1.typeSymbol(), this.t$1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.t$1 = t$1;
                    }
                }));
            }
            {
                this.lubThisType$1 = lubThisType$1;
                this.proto$1 = proto$1;
                this.prototp$1 = prototp$1;
            }
        }, List$.MODULE$.canBuildFrom());
        if (syms.contains($this.NoSymbol())) {
            symbol = $this.NoSymbol();
        } else {
            List<Types.Type> symtypes = $this.map2(narrowts$1, syms, new Serializable($this, lubThisType$1){
                public static final long serialVersionUID = 0L;
                private final Types.Type lubThisType$1;

                public final Types.Type apply(Types.Type t, Symbols.Symbol sym) {
                    return t.memberInfo(sym).substThis(t.typeSymbol(), this.lubThisType$1);
                }
                {
                    this.lubThisType$1 = lubThisType$1;
                }
            });
            if (proto.isTerm()) {
                symbol = proto.cloneSymbol(lubRefined$1.typeSymbol()).setInfoOwnerAdjusted($this.lub(symtypes, Depth$.MODULE$.decr$extension1(depth$3)));
            } else if (((LinearSeqOptimized)symtypes.tail()).forall(new Serializable($this, symtypes){
                public static final long serialVersionUID = 0L;
                private final List symtypes$1;

                public final boolean apply(Types.Type x$26) {
                    return ((Types.Type)this.symtypes$1.head()).$eq$colon$eq(x$26);
                }
                {
                    this.symtypes$1 = symtypes$1;
                }
            })) {
                symbol = proto.cloneSymbol(lubRefined$1.typeSymbol()).setInfoOwnerAdjusted(symtypes.head());
            } else {
                Symbols.Symbol qual$1 = lubRefined$1.typeSymbol();
                Names.TypeName x$37 = proto.name().toTypeName();
                Position x$38 = proto.pos();
                long x$39 = qual$1.newAbstractType$default$3();
                symbol = qual$1.newAbstractType(x$37, x$38, x$39).setInfoOwnerAdjusted(GlbLubs$class.lubBounds$1($this, symtypes.map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final Types.TypeBounds apply(Types.Type x$29) {
                        return x$29.bounds();
                    }
                }, List$.MODULE$.canBuildFrom()), depth$3));
            }
        }
        return symbol;
    }

    public static final boolean refines$1(SymbolTable $this, Types.Type tp, Symbols.Symbol sym, Types.Type lubThisType$1, int depth$3) {
        List<Symbols.Symbol> syms = tp.nonPrivateMember(sym.name()).alternatives();
        return !syms.isEmpty() && syms.forall((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, lubThisType$1, tp, sym, depth$3){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Types.Type lubThisType$1;
            private final Types.Type tp$2;
            private final Symbols.Symbol sym$4;
            private final int depth$3;

            public final boolean apply(Symbols.Symbol alt) {
                Symbols.Symbol symbol = alt;
                Symbols.Symbol symbol2 = this.sym$4;
                return (symbol == null ? symbol2 != null : !symbol.equals(symbol2)) && !this.$outer.specializesSym(this.lubThisType$1, this.sym$4, this.tp$2, alt, this.depth$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.lubThisType$1 = lubThisType$1;
                this.tp$2 = tp$2;
                this.sym$4 = sym$4;
                this.depth$3 = depth$3;
            }
        }));
    }

    private static final Types.Type lub1$1(SymbolTable $this, List ts0, int depth$3) {
        Tuple2 tuple2 = GlbLubs$class.stripExistentialsAndTypeVars($this, ts0);
        if (tuple2 != null) {
            Types.Type type;
            Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
            List ts = (List)tuple22._1();
            List tparams2 = (List)tuple22._2();
            List<Types.Type> lubBaseTypes = $this.lubList(ts, depth$3);
            List<Types.Type> lubParents = $this.spanningTypes(lubBaseTypes);
            Symbols.Symbol lubOwner = $this.commonOwner(ts);
            Types.Type lubBase = $this.intersectionType(lubParents, lubOwner);
            if ($this.phase().erasedTypes() || Depth$.MODULE$.isZero$extension(depth$3)) {
                type = lubBase;
            } else {
                boolean ok;
                Types.Type lubRefined = $this.refinedType(lubParents, lubOwner);
                Types.Type lubThisType = lubRefined.typeSymbol().thisType();
                List narrowts = ts.map(new Serializable($this){
                    public static final long serialVersionUID = 0L;

                    public final Types.Type apply(Types.Type x$25) {
                        return x$25.narrow();
                    }
                }, List$.MODULE$.canBuildFrom());
                lubBase.nonPrivateMembers().withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable($this, lubThisType, narrowts, depth$3){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Types.Type lubThisType$1;
                    private final List narrowts$1;
                    private final int depth$3;

                    public final boolean apply(Symbols.Symbol sym) {
                        return !GlbLubs$class.excludeFromLub$1(this.$outer, sym, this.lubThisType$1, this.narrowts$1, this.depth$3);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.lubThisType$1 = lubThisType$1;
                        this.narrowts$1 = narrowts$1;
                        this.depth$3 = depth$3;
                    }
                })).foreach(new Serializable($this, lubRefined, lubThisType, narrowts, depth$3){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ SymbolTable $outer;
                    public final Types.Type lubRefined$1;
                    public final Types.Type lubThisType$1;
                    private final List narrowts$1;
                    public final int depth$3;

                    public final Object apply(Symbols.Symbol sym) {
                        Object object;
                        try {
                            BoxedUnit boxedUnit;
                            Serializable serializable = new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ GlbLubs$.anonfun.26 $outer;

                                public final void apply(Symbols.Symbol x$30) {
                                    this.$outer.$outer.addMember(this.$outer.lubThisType$1, this.$outer.lubRefined$1, x$30, this.$outer.depth$3);
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            };
                            Symbols.Symbol symbol = GlbLubs$class.lubsym$1(this.$outer, sym, this.lubRefined$1, this.lubThisType$1, this.narrowts$1, this.depth$3);
                            if (symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                                serializable.apply(symbol);
                                boxedUnit = BoxedUnit.UNIT;
                            } else {
                                boxedUnit = BoxedUnit.UNIT;
                            }
                            object = symbol;
                        }
                        catch (Types.NoCommonType noCommonType) {
                            object = BoxedUnit.UNIT;
                        }
                        return object;
                    }

                    public /* synthetic */ SymbolTable scala$reflect$internal$tpe$GlbLubs$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.lubRefined$1 = lubRefined$1;
                        this.lubThisType$1 = lubThisType$1;
                        this.narrowts$1 = narrowts$1;
                        this.depth$3 = depth$3;
                    }
                });
                type = lubRefined.decls().isEmpty() ? lubBase : ((ok = ts.forall(new Serializable($this, lubBase, lubRefined, depth$3){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final Types.Type lubBase$1;
                    private final Types.Type lubRefined$1;
                    private final int depth$3;

                    public final boolean apply(Types.Type t) {
                        boolean bl;
                        if (this.$outer.isSubType(t, this.lubRefined$1, this.depth$3)) {
                            bl = true;
                        } else {
                            MutableSettings.SettingValue settingValue = this.$outer.settings().debug();
                            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                            if (BoxesRunTime.unboxToBoolean(settingValue.value()) || this.$outer.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
                                Console$.MODULE$.println(new StringBuilder().append((Object)"Malformed lub: ").append(this.lubRefined$1).append((Object)"\n").append((Object)"Argument ").append(t).append((Object)" does not conform.  Falling back to ").append(this.lubBase$1).toString());
                            }
                            bl = false;
                        }
                        return bl;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.lubBase$1 = lubBase$1;
                        this.lubRefined$1 = lubRefined$1;
                        this.depth$3 = depth$3;
                    }
                })) ? lubRefined : lubBase);
            }
            Types.Type lubType = type;
            return $this.existentialAbstraction(tparams2, $this.dropIllegalStarTypes().apply(lubType));
        }
        throw new MatchError(tuple2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final Types.Type glb0$1(SymbolTable $this, List ts0, List ts$6, int depth$4) {
        Types.Type type;
        boolean bl = false;
        $colon$colon $colon$colon = null;
        Some<List> some = List$.MODULE$.unapplySeq(ts0);
        if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0) {
            return $this.definitions().AnyTpe();
        }
        Some<List> some2 = List$.MODULE$.unapplySeq(ts0);
        if (!some2.isEmpty() && some2.get() != null && ((LinearSeqOptimized)some2.get()).lengthCompare(1) == 0) {
            Types.Type t = (Types.Type)((LinearSeqOptimized)some2.get()).apply(0);
            return t;
        }
        if (ts0 instanceof $colon$colon) {
            bl = true;
            $colon$colon = ($colon$colon)ts0;
            if ($colon$colon.head() instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)$colon$colon.head();
                List<Symbols.Symbol> tparams1 = $this.map2(polyType.typeParams(), (List)GlbLubs$class.matchingBounds($this, $colon$colon, polyType.typeParams()).transpose((Function1)Predef$.MODULE$.$conforms()), new Serializable($this, depth$4){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;
                    private final int depth$4;

                    public final Symbols.Symbol apply(Symbols.Symbol tparam, List<Types.Type> bounds) {
                        return tparam.cloneSymbol().setInfo(this.$outer.lub(bounds, this.depth$4));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.depth$4 = depth$4;
                    }
                });
                return new Types.PolyType($this, tparams1, $this.glbNorm(GlbLubs$class.matchingInstTypes($this, $colon$colon, tparams1), depth$4));
            }
        }
        if (bl && $colon$colon.head() instanceof Types.MethodType) {
            Types.MethodType methodType = (Types.MethodType)$colon$colon.head();
            return new Types.MethodType($this, methodType.params(), $this.glbNorm(GlbLubs$class.matchingRestypes($this, $colon$colon, methodType.paramTypes()), depth$4));
        }
        if (bl && $colon$colon.head() instanceof Types.NullaryMethodType) {
            return new Types.NullaryMethodType($this, $this.glbNorm(GlbLubs$class.matchingRestypes($this, $colon$colon, Nil$.MODULE$), depth$4));
        }
        if (bl && $colon$colon.head() instanceof Types.TypeBounds) {
            return $this.TypeBounds().apply($this.lub($colon$colon.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Types.Type x$31) {
                    return x$31.bounds().lo();
                }
            }, List$.MODULE$.canBuildFrom()), depth$4), $this.glb($colon$colon.map(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Types.Type x$32) {
                    return x$32.bounds().hi();
                }
            }, List$.MODULE$.canBuildFrom()), depth$4));
        }
        Option<Types.Type> option = $this.glbResults().get(new Tuple2<Depth, List>(new Depth(depth$4), ts0));
        if (option instanceof Some) {
            Some some3 = (Some)option;
            type = (Types.Type)some3.x();
            return type;
        } else {
            $this.glbResults().update(new Tuple2<Depth, List>(new Depth(depth$4), ts0), $this.definitions().NothingTpe());
            Types.Type res = Depth$.MODULE$.isNegative$extension(depth$4) ? $this.definitions().NothingTpe() : GlbLubs$class.glb1$1($this, ts0, ts$6, depth$4);
            $this.glbResults().update(new Tuple2<Depth, List>(new Depth(depth$4), ts0), res);
            type = res;
        }
        return type;
    }

    public static final List refinedToParents$1(SymbolTable $this, Types.Type t) {
        List list2;
        if (t instanceof Types.RefinedType) {
            Types.RefinedType refinedType = (Types.RefinedType)t;
            list2 = refinedType.parents().flatMap(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final List<Types.Type> apply(Types.Type t) {
                    return GlbLubs$class.refinedToParents$1(this.$outer, t);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        } else {
            list2 = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{t}));
        }
        return list2;
    }

    public static final List refinedToDecls$1(SymbolTable $this, Types.Type t) {
        List list2;
        if (t instanceof Types.RefinedType) {
            List<Object> list3;
            Types.RefinedType refinedType = (Types.RefinedType)t;
            List dss = refinedType.parents().flatMap(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final List<Scopes.Scope> apply(Types.Type t) {
                    return GlbLubs$class.refinedToDecls$1(this.$outer, t);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            if (refinedType.decls().isEmpty()) {
                list3 = dss;
            } else {
                Scopes.Scope scope = refinedType.decls();
                list3 = dss.$colon$colon(scope);
            }
            list2 = list3;
        } else {
            list2 = Nil$.MODULE$;
        }
        return list2;
    }

    public static final boolean isTypeBound$1(SymbolTable $this, Types.Type tp) {
        boolean bl = tp instanceof Types.TypeBounds;
        return bl;
    }

    private static final Types.TypeBounds glbBounds$1(SymbolTable $this, List bnds, int depth$4) {
        Types.Type hi;
        Types.Type lo = $this.lub(bnds.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.Type x$35) {
                return x$35.bounds().lo();
            }
        }, List$.MODULE$.canBuildFrom()), Depth$.MODULE$.decr$extension1(depth$4));
        if (lo.$less$colon$less(hi = $this.glb(bnds.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Types.Type apply(Types.Type x$36) {
                return x$36.bounds().hi();
            }
        }, List$.MODULE$.canBuildFrom()), Depth$.MODULE$.decr$extension1(depth$4)))) {
            return $this.TypeBounds().apply(lo, hi);
        }
        throw $this.GlbFailure();
    }

    public static final Symbols.Symbol glbsym$1(SymbolTable $this, Symbols.Symbol proto, List ts$2, Types.Type glbRefined$1, Types.Type glbThisType$1, int depth$4) {
        Types.Type type;
        Types.Type prototp = glbThisType$1.memberInfo(proto);
        List<Symbols.Symbol> syms = ts$2.flatMap(new Serializable($this, glbThisType$1, proto, prototp){
            public static final long serialVersionUID = 0L;
            public final Types.Type glbThisType$1;
            private final Symbols.Symbol proto$2;
            public final Types.Type prototp$2;

            public final List<Symbols.Symbol> apply(Types.Type t) {
                return t.nonPrivateMember(this.proto$2.name()).alternatives().withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ GlbLubs$.anonfun.32 $outer;

                    public final boolean apply(Symbols.Symbol alt) {
                        return this.$outer.glbThisType$1.memberInfo(alt).matches(this.$outer.prototp$2);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Symbols.Symbol apply(Symbols.Symbol alt) {
                        return alt;
                    }
                }, List$.MODULE$.canBuildFrom());
            }
            {
                this.glbThisType$1 = glbThisType$1;
                this.proto$2 = proto$2;
                this.prototp$2 = prototp$2;
            }
        }, List$.MODULE$.canBuildFrom());
        List<Types.Type> symtypes = syms.map(new Serializable($this, glbThisType$1){
            public static final long serialVersionUID = 0L;
            private final Types.Type glbThisType$1;

            public final Types.Type apply(Symbols.Symbol sym) {
                return this.glbThisType$1.memberInfo(sym);
            }
            {
                this.glbThisType$1 = glbThisType$1;
            }
        }, List$.MODULE$.canBuildFrom());
        Predef$.MODULE$.assert(!symtypes.isEmpty());
        Symbols.Symbol symbol = proto.cloneSymbol(glbRefined$1.typeSymbol());
        if (proto.isTerm()) {
            type = $this.glb(symtypes, Depth$.MODULE$.decr$extension1(depth$4));
        } else {
            List symbounds = (List)symtypes.filter((Function1)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final boolean apply(Types.Type tp) {
                    return GlbLubs$class.isTypeBound$1(this.$outer, tp);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            ObjectRef<Types.TypeBounds> result2 = ObjectRef.create(symbounds.isEmpty() ? $this.TypeBounds().empty() : GlbLubs$class.glbBounds$1($this, symbounds, depth$4));
            symtypes.withFilter((Function1<Types.Type, Object>)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final boolean apply(Types.Type t) {
                    return !GlbLubs$class.isTypeBound$1(this.$outer, t);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })).foreach(new Serializable($this, result2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;
                private final ObjectRef result$1;

                public final void apply(Types.Type t) {
                    if (((Types.Type)this.result$1.elem).bounds().containsType(t)) {
                        this.result$1.elem = t;
                        return;
                    }
                    throw this.$outer.GlbFailure();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.result$1 = result$1;
                }
            });
            type = (Types.Type)result2.elem;
        }
        return symbol.setInfoOwnerAdjusted(type);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static final Types.Type glb1$1(SymbolTable $this, List ts0, List ts$6, int depth$4) {
        Throwable throwable;
        Types.Type type;
        Throwable throwable2;
        block12: {
            block11: {
                try {
                    Tuple2 tuple2 = GlbLubs$class.stripExistentialsAndTypeVars($this, ts0);
                    if (tuple2 != null) {
                        Types.Type type2;
                        Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                        List ts = (List)tuple22._1();
                        List tparams2 = (List)tuple22._2();
                        Symbols.Symbol glbOwner = $this.commonOwner(ts);
                        List<Types.Type> ts1 = ts.flatMap(new Serializable($this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ SymbolTable $outer;

                            public final List<Types.Type> apply(Types.Type t) {
                                return GlbLubs$class.refinedToParents$1(this.$outer, t);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        Types.Type glbBase = $this.intersectionType(ts1, glbOwner);
                        if ($this.phase().erasedTypes() || Depth$.MODULE$.isZero$extension(depth$4)) {
                            type2 = glbBase;
                        } else {
                            Types.Type glbRefined = $this.refinedType(ts1, glbOwner);
                            Types.Type glbThisType = glbRefined.typeSymbol().thisType();
                            Depth depth = new Depth($this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit());
                            Depth depth2 = new Depth($this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth());
                            if (Ordered$class.$less(depth2, depth)) {
                                try {
                                    int n = $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth();
                                    Depth$ depth$ = Depth$.MODULE$;
                                    $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(depth$.incr$extension0(n, 1));
                                    List dss = ts.flatMap(new Serializable($this){
                                        public static final long serialVersionUID = 0L;
                                        private final /* synthetic */ SymbolTable $outer;

                                        public final List<Scopes.Scope> apply(Types.Type t) {
                                            return GlbLubs$class.refinedToDecls$1(this.$outer, t);
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                        }
                                    }, List$.MODULE$.canBuildFrom());
                                    Serializable serializable = new Serializable($this, ts, glbRefined, glbThisType, depth$4){
                                        public static final long serialVersionUID = 0L;
                                        public final /* synthetic */ SymbolTable $outer;
                                        public final List ts$2;
                                        public final Types.Type glbRefined$1;
                                        public final Types.Type glbThisType$1;
                                        public final int depth$4;

                                        public final void apply(Scopes.Scope ds) {
                                            ds.iterator().foreach(new Serializable(this){
                                                public static final long serialVersionUID = 0L;
                                                private final /* synthetic */ GlbLubs$.anonfun.38 $outer;

                                                public final void apply(Symbols.Symbol sym) {
                                                    if (new Depth(this.$outer.$outer.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth()).$less(new Depth(this.$outer.scala$reflect$internal$tpe$GlbLubs$$anonfun$$$outer().scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit())) && !this.$outer.$outer.specializesSym(this.$outer.glbThisType$1, sym, this.$outer.depth$4)) {
                                                        try {
                                                            GlbLubs$.anonfun.38 var2_2 = this.$outer;
                                                            var2_2.$outer.addMember(this.$outer.glbThisType$1, this.$outer.glbRefined$1, GlbLubs$class.glbsym$1(this.$outer.scala$reflect$internal$tpe$GlbLubs$$anonfun$$$outer(), sym, this.$outer.ts$2, this.$outer.glbRefined$1, this.$outer.glbThisType$1, this.$outer.depth$4), this.$outer.depth$4);
                                                        }
                                                        catch (Types.NoCommonType noCommonType) {}
                                                    }
                                                }
                                                {
                                                    if ($outer == null) {
                                                        throw null;
                                                    }
                                                    this.$outer = $outer;
                                                }
                                            });
                                        }

                                        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$GlbLubs$$anonfun$$$outer() {
                                            return this.$outer;
                                        }
                                        {
                                            if ($outer == null) {
                                                throw null;
                                            }
                                            this.$outer = $outer;
                                            this.ts$2 = ts$2;
                                            this.glbRefined$1 = glbRefined$1;
                                            this.glbThisType$1 = glbThisType$1;
                                            this.depth$4 = depth$4;
                                        }
                                    };
                                    List list2 = dss;
                                    while (!((AbstractIterable)list2).isEmpty()) {
                                        Object a = ((AbstractIterable)list2).head();
                                        Scopes.Scope scope = (Scopes.Scope)a;
                                        scope.iterator().foreach(new /* invalid duplicate definition of identical inner class */);
                                        list2 = (List)list2.tail();
                                    }
                                    int n2 = $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth();
                                    Depth$ depth$2 = Depth$.MODULE$;
                                    $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(depth$2.decr$extension0(n2, 1));
                                }
                                catch (Throwable throwable3) {
                                    int n = $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth();
                                    Depth$ depth$ = Depth$.MODULE$;
                                    $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(depth$.decr$extension0(n, 1));
                                    throwable2 = throwable3;
                                    break block11;
                                }
                            }
                            type2 = glbRefined.decls().isEmpty() ? glbBase : glbRefined;
                        }
                        Types.Type glbType = type2;
                        type = $this.existentialAbstraction(tparams2, glbType);
                        return type;
                    }
                    throwable2 = new MatchError(tuple2);
                }
                catch (Throwable throwable4) {
                    Throwable throwable3 = $this.GlbFailure();
                    throwable = throwable3;
                    if (throwable3 == null) throw throwable2;
                    break block12;
                }
            }
            if ((throwable = $this.GlbFailure()) == null) {
                throw throwable2;
            }
        }
        if (!throwable.equals(throwable2)) throw throwable2;
        if (ts$6.forall(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final boolean apply(Types.Type t) {
                return this.$outer.definitions().NullTpe().$less$colon$less(t);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })) {
            type = $this.definitions().NullTpe();
            return type;
        }
        type = $this.definitions().NothingTpe();
        return type;
    }

    public static final List getBounds$1(SymbolTable $this, Types.Type tp, List tps$1, List tparams$1) {
        while (true) {
            Types.PolyType polyType;
            if (tp instanceof Types.PolyType && $this.sameLength((polyType = (Types.PolyType)tp).typeParams(), tparams$1)) {
                return polyType.typeParams().map(new Serializable($this, tparams$1, polyType){
                    public static final long serialVersionUID = 0L;
                    private final List tparams$1;
                    private final Types.PolyType x2$2;

                    public final Types.Type apply(Symbols.Symbol tparam) {
                        return tparam.info().substSym(this.x2$2.typeParams(), this.tparams$1);
                    }
                    {
                        this.tparams$1 = tparams$1;
                        this.x2$2 = x2$2;
                    }
                }, List$.MODULE$.canBuildFrom());
            }
            if (tp == tp.normalize()) break;
            tp = tp.normalize();
        }
        throw new Types.NoCommonType($this, tps$1);
    }

    public static final Types.Type transformResultType$1(SymbolTable $this, Types.Type tp, List tps$2, List tparams$2) {
        while (true) {
            Types.PolyType polyType;
            if (tp instanceof Types.PolyType && $this.sameLength((polyType = (Types.PolyType)tp).typeParams(), tparams$2)) {
                return polyType.resultType().substSym(polyType.typeParams(), tparams$2);
            }
            if (tp == tp.normalize()) break;
            tp = tp.normalize();
        }
        throw new Types.NoCommonType($this, tps$2);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(package$.MODULE$.props().contains("scalac.debug.lub"));
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$strictInference_$eq($this.settings().strictInference());
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(new HashMap());
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(new HashMap());
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(new Throwable());
        $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(Depth$.MODULE$.Zero());
        Depth$ depth$ = Depth$.MODULE$;
        $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(2 < -3 ? depth$.AnyDepth() : 2);
    }
}

