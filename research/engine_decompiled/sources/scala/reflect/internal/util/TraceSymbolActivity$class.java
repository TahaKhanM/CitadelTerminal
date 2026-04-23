/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.GenMap;
import scala.collection.Iterable$;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.Map$;
import scala.collection.mutable.Set;
import scala.collection.mutable.Set$;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering;
import scala.math.Ordering$Int$;
import scala.reflect.internal.Names;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.util.TraceSymbolActivity;
import scala.reflect.internal.util.TraceSymbolActivity$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.sys.package$;

public abstract class TraceSymbolActivity$class {
    public static void recordSymbolsInTree(TraceSymbolActivity $this, Trees.Tree tree) {
        if ($this.scala$reflect$internal$util$TraceSymbolActivity$$enabled()) {
            $this.allTrees().$plus$eq(tree);
        }
    }

    public static void recordNewSymbol(TraceSymbolActivity $this, Symbols.Symbol sym) {
        if ($this.scala$reflect$internal$util$TraceSymbolActivity$$enabled() && sym.id() > 1) {
            $this.allSymbols().update(BoxesRunTime.boxToInteger(sym.id()), sym);
            int n = sym.owner().id();
            $this.allChildren().update(BoxesRunTime.boxToInteger(n), ((List)$this.allChildren().apply(BoxesRunTime.boxToInteger(n))).$colon$colon(BoxesRunTime.boxToInteger(sym.id())));
        }
    }

    public static void recordNewSymbolOwner(TraceSymbolActivity $this, Symbols.Symbol sym, Symbols.Symbol newOwner) {
        if ($this.scala$reflect$internal$util$TraceSymbolActivity$$enabled()) {
            int sid = sym.id();
            int oid = sym.owner().id();
            int nid = newOwner.id();
            Phase phase = $this.global().phase();
            Integer n = Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(oid));
            Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
            $this.prevOwners().update(BoxesRunTime.boxToInteger(sid), ((List)$this.prevOwners().apply(BoxesRunTime.boxToInteger(sid))).$colon$colon(new Tuple2<Integer, Phase>(n, phase)));
            $this.allChildren().update(BoxesRunTime.boxToInteger(oid), (List<Object>)((TraversableLike)$this.allChildren().apply(BoxesRunTime.boxToInteger(oid))).filterNot(new Serializable($this, sid){
                public static final long serialVersionUID = 0L;
                private final int sid$1;

                public final boolean apply(int x$1) {
                    return this.apply$mcZI$sp(x$1);
                }

                public boolean apply$mcZI$sp(int x$1) {
                    return x$1 == this.sid$1;
                }
                {
                    this.sid$1 = sid$1;
                }
            }));
            $this.allChildren().update(BoxesRunTime.boxToInteger(nid), ((List)$this.allChildren().apply(BoxesRunTime.boxToInteger(nid))).$colon$colon(BoxesRunTime.boxToInteger(sid)));
        }
    }

    public static Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase(TraceSymbolActivity $this) {
        return $this.global().findPhaseWithName("erasure");
    }

    /*
     * WARNING - void declaration
     */
    public static String scala$reflect$internal$util$TraceSymbolActivity$$signature(TraceSymbolActivity $this, int id) {
        String string2;
        Phase phase = $this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase();
        SymbolTable symbolTable = $this.global();
        Phase saved1 = symbolTable.pushPhase(phase);
        try {
            string2 = ((Symbols.Symbol)$this.allSymbols().apply(BoxesRunTime.boxToInteger(id))).defString();
            symbolTable.popPhase(saved1);
        }
        catch (Throwable throwable) {
            void var4_4;
            symbolTable.popPhase((Phase)var4_4);
            throw throwable;
        }
        return string2;
    }

    public static String scala$reflect$internal$util$TraceSymbolActivity$$dashes(TraceSymbolActivity $this, Object s2) {
        String string2 = String.valueOf(s2);
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(string2).map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final char apply(char x$2) {
                return '-';
            }
        }, Predef$.MODULE$.StringCanBuildFrom());
    }

    public static void scala$reflect$internal$util$TraceSymbolActivity$$show(TraceSymbolActivity $this, Object s1, Seq ss) {
        Predef$ predef$ = Predef$.MODULE$;
        String string2 = new StringOps("%-12s").format(Predef$.MODULE$.genericWrapArray(new Object[]{s1}));
        Predef$.MODULE$.println(((TraversableOnce)ss.$plus$colon(string2, Seq$.MODULE$.canBuildFrom())).mkString(" "));
    }

    private static void showHeader(TraceSymbolActivity $this, Object s1, Seq ss) {
        TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$show($this, s1, ss);
        TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$show($this, TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$dashes($this, s1), ss.map(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TraceSymbolActivity $outer;

            public final String apply(Object s2) {
                return TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$dashes(this.$outer, s2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, Seq$.MODULE$.canBuildFrom()));
    }

    public static void scala$reflect$internal$util$TraceSymbolActivity$$showSym(TraceSymbolActivity $this, Symbols.Symbol sym) {
        try {
            Predef$ predef$ = Predef$.MODULE$;
            String string2 = new StringOps("%s#%s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{TraceSymbolActivity$class.prefix$1($this, sym), sym.accurateKindString(), sym.name().decode()}));
            Predef$ predef$2 = Predef$.MODULE$;
            Console$.MODULE$.println(string2);
        }
        catch (Throwable throwable) {
            String string3 = new StringBuilder().append((Object)TraceSymbolActivity$class.prefix$1($this, sym)).append((Object)" failed: ").append(throwable).toString();
            Predef$ predef$ = Predef$.MODULE$;
            Console$.MODULE$.println(string3);
        }
        List list2 = (List)((SeqLike)$this.allChildren().apply(BoxesRunTime.boxToInteger(sym.id()))).sorted(Ordering$Int$.MODULE$);
        while (!((AbstractIterable)list2).isEmpty()) {
            int n = BoxesRunTime.unboxToInt(((AbstractIterable)list2).head());
            TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$showIdAndRemove($this, n);
            list2 = (List)list2.tail();
        }
    }

    public static void scala$reflect$internal$util$TraceSymbolActivity$$showIdAndRemove(TraceSymbolActivity $this, int id) {
        Option option = $this.allSymbols().remove(BoxesRunTime.boxToInteger(id));
        if (!option.isEmpty()) {
            Symbols.Symbol symbol = (Symbols.Symbol)option.get();
            TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$showSym($this, symbol);
        }
    }

    public static String scala$reflect$internal$util$TraceSymbolActivity$$symbolStr(TraceSymbolActivity $this, int id) {
        String string2;
        if (id == 1) {
            string2 = "NoSymbol";
        } else {
            Symbols.Symbol sym = (Symbols.Symbol)$this.allSymbols().apply(BoxesRunTime.boxToInteger(id));
            string2 = new StringBuilder().append((Object)sym.accurateKindString()).append((Object)" ").append((Object)sym.name().decode()).toString();
        }
        return string2;
    }

    public static String scala$reflect$internal$util$TraceSymbolActivity$$ownerStr(TraceSymbolActivity $this, int id) {
        Symbols.Symbol sym = (Symbols.Symbol)$this.allSymbols().apply(BoxesRunTime.boxToInteger(id));
        return new StringBuilder().append((Object)sym.name().decode()).append((Object)"#").append(BoxesRunTime.boxToInteger(sym.id())).toString();
    }

    public static List scala$reflect$internal$util$TraceSymbolActivity$$freq(TraceSymbolActivity $this, Traversable xs, Function1 fn) {
        scala.collection.immutable.Map ys = xs.groupBy(fn).mapValues(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Traversable<Object> x$4) {
                return x$4.size();
            }
        });
        return (List)ys.toList().sortBy((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Tuple2<Object, Object> x$5) {
                return -x$5._2$mcI$sp();
            }
        }), (Ordering)Ordering$Int$.MODULE$);
    }

    private static void showMapFreq(TraceSymbolActivity $this, Map xs, Function1 showFn) {
        Object object = ((List)xs.mapValues(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Traversable<?> x$6) {
                return x$6.size();
            }
        }).toList().sortBy((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Tuple2<Object, Object> x$7) {
                return -x$7._2$mcI$sp();
            }
        }), (Ordering)Ordering$Int$.MODULE$)).take(100);
        while (!((AbstractIterable)object).isEmpty()) {
            Tuple2 tuple2 = (Tuple2)((AbstractIterable)object).head();
            if (tuple2 != null) {
                TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$show($this, BoxesRunTime.boxToInteger(tuple2._2$mcI$sp()), Predef$.MODULE$.genericWrapArray(new Object[]{showFn.apply(tuple2._1())}));
                object = (List)((AbstractTraversable)object).tail();
                continue;
            }
            throw new MatchError(tuple2);
        }
        Predef$ predef$ = Predef$.MODULE$;
        Console$.MODULE$.println("\n");
    }

    private static void showFreq(TraceSymbolActivity $this, Traversable xs, Function1 groupFn, Function1 showFn) {
        TraceSymbolActivity$class.showMapFreq($this, (Map)xs.toList().groupBy(groupFn), showFn);
    }

    public static void showAllSymbols(TraceSymbolActivity $this) {
        if ($this.scala$reflect$internal$util$TraceSymbolActivity$$enabled()) {
            $this.scala$reflect$internal$util$TraceSymbolActivity$$enabled_$eq(false);
            $this.allSymbols().update(BoxesRunTime.boxToInteger(1), $this.global().NoSymbol());
            String string2 = new StringBuilder().append((Object)"").append(BoxesRunTime.boxToInteger($this.allSymbols().size())).append((Object)" symbols created.").toString();
            Predef$ predef$ = Predef$.MODULE$;
            Console$.MODULE$.println(string2);
            Predef$ predef$2 = Predef$.MODULE$;
            Console$.MODULE$.println("");
            TraceSymbolActivity$class.showHeader($this, "descendants", Predef$.MODULE$.genericWrapArray(new Object[]{"symbol"}));
            TraceSymbolActivity$class.showFreq($this, $this.allSymbols().values().flatMap(new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final List<Symbols.Symbol> apply(Symbols.Symbol x$8) {
                    return x$8.ownerChain().drop(1);
                }
            }, Iterable$.MODULE$.canBuildFrom()), (Function1)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final int apply(Symbols.Symbol x$9) {
                    return x$9.id();
                }
            }), (Function1)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TraceSymbolActivity $outer;

                public final String apply(int id) {
                    return TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$symbolStr(this.$outer, id);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            TraceSymbolActivity$class.showHeader($this, "children", Predef$.MODULE$.genericWrapArray(new Object[]{"symbol"}));
            TraceSymbolActivity$class.showMapFreq($this, $this.allChildren(), (Function1)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TraceSymbolActivity $outer;

                public final String apply(int id) {
                    return TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$symbolStr(this.$outer, id);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            if ($this.prevOwners().nonEmpty()) {
                TraceSymbolActivity$class.showHeader($this, "prev owners", Predef$.MODULE$.genericWrapArray(new Object[]{"symbol"}));
                TraceSymbolActivity$class.showMapFreq($this, $this.prevOwners(), (Function1)((Object)new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ TraceSymbolActivity $outer;

                    public final String apply(int k) {
                        Tuple2<Integer, NoPhase$> tuple2 = new Tuple2<Integer, NoPhase$>(BoxesRunTime.boxToInteger(((Symbols.Symbol)this.$outer.allSymbols().apply(BoxesRunTime.boxToInteger(k))).owner().id()), NoPhase$.MODULE$);
                        List<A> owners = ((List)this.$outer.prevOwners().apply(BoxesRunTime.boxToInteger(k))).$colon$colon(tuple2).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TraceSymbolActivity$.anonfun.showAllSymbols.6 $outer;

                            public final String apply(Tuple2<Object, Phase> x0$2) {
                                block4: {
                                    String string2;
                                    block3: {
                                        block2: {
                                            if (x0$2 == null || !((Object)NoPhase$.MODULE$).equals(x0$2._2())) break block2;
                                            string2 = new StringBuilder().append((Object)"-> owned by ").append((Object)TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$ownerStr(this.$outer.$outer, x0$2._1$mcI$sp())).toString();
                                            break block3;
                                        }
                                        if (x0$2 == null) break block4;
                                        Predef$ predef$ = Predef$.MODULE$;
                                        string2 = new StringOps("-> owned by %s (until %s)").format(Predef$.MODULE$.genericWrapArray(new Object[]{TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$ownerStr(this.$outer.$outer, x0$2._1$mcI$sp()), x0$2._2()}));
                                    }
                                    return string2;
                                }
                                throw new MatchError(x0$2);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        String string2 = TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$signature(this.$outer, k);
                        return owners.$colon$colon(string2).mkString("\n                ");
                    }

                    public /* synthetic */ TraceSymbolActivity scala$reflect$internal$util$TraceSymbolActivity$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
            }
            GenMap nameFreq = $this.allSymbols().values().toList().groupBy((Function1)((Object)new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final Names.Name apply(Symbols.Symbol x$12) {
                    return x$12.name();
                }
            }));
            Object[] objectArray = new Object[2];
            Predef$ predef$3 = Predef$.MODULE$;
            objectArray[0] = new StringOps("%-15s").format(Predef$.MODULE$.genericWrapArray(new Object[]{"name"}));
            objectArray[1] = "owners";
            TraceSymbolActivity$class.showHeader($this, "frequency", Predef$.MODULE$.genericWrapArray(objectArray));
            TraceSymbolActivity$class.showMapFreq($this, (Map)nameFreq, (Function1)((Object)new Serializable($this, (scala.collection.immutable.Map)nameFreq){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TraceSymbolActivity $outer;
                private final scala.collection.immutable.Map nameFreq$1;

                public final String apply(Names.Name name) {
                    Predef$ predef$ = Predef$.MODULE$;
                    Object[] objectArray = new Object[2];
                    objectArray[0] = name.decode();
                    List owners = TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$freq(this.$outer, (Traversable)this.nameFreq$1.apply(name), (Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Symbols.Symbol apply(Symbols.Symbol x$13) {
                            return x$13.owner();
                        }
                    }));
                    Predef$ predef$2 = Predef$.MODULE$;
                    objectArray[1] = new StringOps("%4s owners (%s)").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(owners.size()), new StringBuilder().append((Object)((TraversableOnce)((List)owners.take(3)).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final String apply(Tuple2<Symbols.Symbol, Object> x0$3) {
                            if (x0$3 != null) {
                                return new StringBuilder().append(x0$3._2$mcI$sp()).append((Object)"/").append(x0$3._1()).toString();
                            }
                            throw new MatchError(x0$3);
                        }
                    }, List$.MODULE$.canBuildFrom())).mkString(", ")).append((Object)", ...").toString()}));
                    return new StringOps("%-15s %s").format(Predef$.MODULE$.genericWrapArray(objectArray));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.nameFreq$1 = nameFreq$1;
                }
            }));
            List list2 = (List)$this.allSymbols().keys().toList().sorted((Ordering)Ordering$Int$.MODULE$);
            while (!((AbstractIterable)list2).isEmpty()) {
                int n = BoxesRunTime.unboxToInt(((AbstractIterable)list2).head());
                TraceSymbolActivity$class.scala$reflect$internal$util$TraceSymbolActivity$$showIdAndRemove($this, n);
                list2 = (List)list2.tail();
            }
            return;
        }
    }

    private static final String prefix$1(TraceSymbolActivity $this, Symbols.Symbol sym$1) {
        Predef$ predef$ = Predef$.MODULE$;
        return new StringBuilder().append((Object)new StringOps("  ").$times(sym$1.ownerChain().length() - 1)).append(BoxesRunTime.boxToInteger(sym$1.id())).toString();
    }

    public static void $init$(TraceSymbolActivity $this) {
        $this.scala$reflect$internal$util$TraceSymbolActivity$$enabled_$eq($this.global().traceSymbolActivity());
        Object object = $this.scala$reflect$internal$util$TraceSymbolActivity$$enabled() && $this.global().isCompilerUniverse() ? package$.MODULE$.addShutdownHook((Function0<BoxedUnit>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ TraceSymbolActivity $outer;

            public final void apply() {
                this.$outer.showAllSymbols();
            }

            public void apply$mcV$sp() {
                this.$outer.showAllSymbols();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })) : BoxedUnit.UNIT;
        $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allSymbols_$eq((scala.collection.mutable.Map)Map$.MODULE$.apply(Nil$.MODULE$));
        $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allChildren_$eq(((scala.collection.mutable.Map)Map$.MODULE$.apply(Nil$.MODULE$)).withDefaultValue(Nil$.MODULE$));
        $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$prevOwners_$eq(((scala.collection.mutable.Map)Map$.MODULE$.apply(Nil$.MODULE$)).withDefaultValue(Nil$.MODULE$));
        $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allTrees_$eq((Set)Set$.MODULE$.apply(Nil$.MODULE$));
    }
}

