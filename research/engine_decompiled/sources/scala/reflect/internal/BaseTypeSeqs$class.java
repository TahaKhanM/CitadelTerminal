/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Array$;
import scala.MatchError;
import scala.collection.AbstractIterable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ListBuffer;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;

public abstract class BaseTypeSeqs$class {
    public static BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(SymbolTable $this, List parents2, Types.Type[] elems) {
        return new BaseTypeSeqs.BaseTypeSeq($this, parents2, elems);
    }

    public static BaseTypeSeqs.BaseTypeSeq baseTypeSingletonSeq(SymbolTable $this, Types.Type tp) {
        return $this.newBaseTypeSeq(Nil$.MODULE$, (Types.Type[])((Object[])new Types.Type[]{tp}));
    }

    public static BaseTypeSeqs.BaseTypeSeq compoundBaseTypeSeq(SymbolTable $this, Types.Type tp) {
        Symbols.Symbol tsym = tp.typeSymbol();
        List parents2 = tp.parents();
        ListBuffer buf = new ListBuffer();
        buf.$plus$eq(tsym.tpe_$times());
        int btsSize = 1;
        if (parents2.nonEmpty()) {
            int nparents = parents2.length();
            BaseTypeSeqs.BaseTypeSeq[] pbtss = new BaseTypeSeqs.BaseTypeSeq[nparents];
            int[] index = new int[nparents];
            IntRef i = IntRef.create(0);
            List list2 = parents2;
            while (!((AbstractIterable)list2).isEmpty()) {
                BaseTypeSeqs.BaseTypeSeq parentBts1 = ((Types.Type)((AbstractIterable)list2).head()).dealias().baseTypeSeq();
                pbtss[i.elem] = parentBts1 == $this.undetBaseTypeSeq() ? $this.definitions().AnyClass().info().baseTypeSeq() : parentBts1;
                index[i.elem] = 0;
                ++i.elem;
                list2 = (List)list2.tail();
            }
            Symbols.Symbol minSym2 = $this.NoSymbol();
            while (true) {
                Symbols.NoSymbol noSymbol = minSym2;
                Symbols.ClassSymbol classSymbol = $this.definitions().AnyClass();
                if (!(noSymbol == null ? classSymbol != null : !noSymbol.equals(classSymbol))) break;
                minSym2 = BaseTypeSeqs$class.nextTypeSymbol$1($this, 0, pbtss, index);
                i.elem = 1;
                while (i.elem < nparents) {
                    Symbols.Symbol nextSym = BaseTypeSeqs$class.nextTypeSymbol$1($this, i.elem, pbtss, index);
                    if (nextSym.isLess(minSym2)) {
                        minSym2 = nextSym;
                    }
                    ++i.elem;
                }
                ObjectRef<Nil$> minTypes = ObjectRef.create(Nil$.MODULE$);
                i.elem = 0;
                while (i.elem < nparents) {
                    Symbols.Symbol symbol = BaseTypeSeqs$class.nextTypeSymbol$1($this, i.elem, pbtss, index);
                    if (!(symbol != null ? !symbol.equals(minSym2) : minSym2 != null)) {
                        Types.Type type = BaseTypeSeqs$class.nextRawElem$1($this, i.elem, pbtss, index);
                        if (type instanceof Types.RefinedType) {
                            Types.RefinedType refinedType = (Types.RefinedType)type;
                            List list3 = refinedType.parents();
                            while (!((AbstractIterable)list3).isEmpty()) {
                                Types.Type type2 = (Types.Type)((AbstractIterable)list3).head();
                                if (!BaseTypeSeqs$class.alreadyInMinTypes$1($this, type2, minTypes)) {
                                    List list4 = (List)minTypes.elem;
                                    minTypes.elem = new $colon$colon<Types.Type>(type2, list4);
                                }
                                list3 = (List)list3.tail();
                            }
                        } else if (BaseTypeSeqs$class.alreadyInMinTypes$1($this, type, minTypes)) {
                        } else {
                            List list5 = (List)minTypes.elem;
                            minTypes.elem = new $colon$colon<Types.Type>(type, list5);
                        }
                        index[i.elem] = index[i.elem] + 1;
                    }
                    ++i.elem;
                }
                buf.$plus$eq($this.intersectionType((List)minTypes.elem));
                ++btsSize;
            }
        }
        Types.Type[] elems = new Types.Type[btsSize];
        buf.copyToArray(elems, 0);
        return $this.newBaseTypeSeq(parents2, elems);
    }

    private static final Symbols.Symbol nextTypeSymbol$1(SymbolTable $this, int i, BaseTypeSeqs.BaseTypeSeq[] pbtss$1, int[] index$1) {
        int j = index$1[i];
        BaseTypeSeqs.BaseTypeSeq pbts = pbtss$1[i];
        return j < pbts.length() ? pbts.typeSymbol(j) : $this.definitions().AnyClass();
    }

    private static final Types.Type nextRawElem$1(SymbolTable $this, int i, BaseTypeSeqs.BaseTypeSeq[] pbtss$1, int[] index$1) {
        int j = index$1[i];
        BaseTypeSeqs.BaseTypeSeq pbts = pbtss$1[i];
        return j < pbts.length() ? pbts.rawElem(j) : $this.definitions().AnyTpe();
    }

    private static final boolean loop$1(SymbolTable $this, List tps, Types.Type tp$1) {
        while (true) {
            $colon$colon $colon$colon;
            block5: {
                boolean bl;
                block4: {
                    block3: {
                        if (!((Object)Nil$.MODULE$).equals(tps)) break block3;
                        bl = false;
                        break block4;
                    }
                    if (!(tps instanceof $colon$colon)) break;
                    $colon$colon = ($colon$colon)tps;
                    if (!tp$1.$eq$colon$eq((Types.Type)$colon$colon.head())) break block5;
                    bl = true;
                }
                return bl;
            }
            tps = $colon$colon.tl$1();
        }
        throw new MatchError(tps);
    }

    public static final boolean alreadyInMinTypes$1(SymbolTable $this, Types.Type tp, ObjectRef minTypes$1) {
        return BaseTypeSeqs$class.loop$1($this, (List)minTypes$1.elem, tp);
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$BaseTypeSeqs$_setter_$undetBaseTypeSeq_$eq($this.newBaseTypeSeq(Nil$.MODULE$, (Types.Type[])Array$.MODULE$.apply(Nil$.MODULE$, $this.TypeTagg())));
        $this.scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(new Throwable());
    }
}

