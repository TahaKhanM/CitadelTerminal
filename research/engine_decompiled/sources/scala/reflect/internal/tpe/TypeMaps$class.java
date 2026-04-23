/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Serializable;
import scala.Some;
import scala.collection.LinearSeqOptimized;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.Set$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;

public abstract class TypeMaps$class {
    public static boolean etaExpandKeepsStar(SymbolTable $this) {
        return false;
    }

    public static TypeMaps.TypeMap rawToExistential(SymbolTable $this) {
        return new TypeMaps.TypeMap($this){
            private Set<Symbols.Symbol> expanded;
            private final /* synthetic */ SymbolTable $outer;

            private Set<Symbols.Symbol> expanded() {
                return this.expanded;
            }

            private void expanded_$eq(Set<Symbols.Symbol> x$1) {
                this.expanded = x$1;
            }

            public Types.Type apply(Types.Type tp) {
                Types.Type type;
                Types.TypeRef typeRef;
                Some<List<Types.Type>> some;
                if (tp instanceof Types.TypeRef && !(some = List$.MODULE$.unapplySeq((typeRef = (Types.TypeRef)tp).args())).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0 && this.$outer.isRawIfWithoutArgs(typeRef.sym())) {
                    Types.Type type2;
                    if (this.expanded().contains(typeRef.sym())) {
                        type2 = this.$outer.definitions().AnyRefTpe();
                    } else {
                        this.expanded_$eq((Set)this.expanded().$plus(typeRef.sym()));
                        List<Symbols.Symbol> eparams = this.mapOver(this.$outer.typeParamsToExistentials(typeRef.sym()));
                        type2 = this.$outer.existentialAbstraction(eparams, this.$outer.typeRef(this.apply(typeRef.pre()), typeRef.sym(), eparams.map(new Serializable(this){
                            public static final long serialVersionUID = 0L;

                            public final Types.Type apply(Symbols.Symbol x$4) {
                                return x$4.tpe();
                            }
                        }, List$.MODULE$.canBuildFrom())));
                    }
                    type = type2;
                } else {
                    type = this.mapOver(tp);
                }
                return type;
                finally {
                    this.expanded_$eq((Set)this.expanded().$minus(typeRef.sym()));
                }
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super($outer);
                this.expanded = (Set)Set$.MODULE$.apply(Nil$.MODULE$);
            }
        };
    }

    public static boolean isPossiblePrefix(SymbolTable $this, Symbols.Symbol clazz) {
        return clazz.isClass() && !clazz.isPackageClass();
    }

    public static boolean skipPrefixOf(SymbolTable $this, Types.Type pre, Symbols.Symbol clazz) {
        return pre == $this.NoType() || pre == $this.NoPrefix() || !$this.isPossiblePrefix(clazz);
    }

    public static TypeMaps.AsSeenFromMap newAsSeenFromMap(SymbolTable $this, Types.Type pre, Symbols.Symbol clazz) {
        return new TypeMaps.AsSeenFromMap($this, pre, clazz);
    }

    public static void $init$(SymbolTable $this) {
    }
}

