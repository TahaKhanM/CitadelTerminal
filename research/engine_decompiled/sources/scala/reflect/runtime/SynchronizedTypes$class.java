/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.lang.ref.Reference;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.WeakHashMap;
import scala.collection.mutable.WeakHashMap$;
import scala.ref.WeakReference;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.ThreadLocalStorage;
import scala.runtime.BoxesRunTime;

public abstract class SynchronizedTypes$class {
    public static CommonOwners.CommonOwnerMap commonOwnerMap(SymbolTable $this) {
        return new CommonOwners.CommonOwnerMap((scala.reflect.internal.SymbolTable)((Object)$this));
    }

    public static Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock(SymbolTable $this) {
        return new Object();
    }

    public static Types.Type unique(SymbolTable $this, Types.Type tp) {
        Object object = $this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock();
        synchronized (object) {
            Types.Type type;
            if ($this.isCompilerUniverse()) {
                type = $this.scala$reflect$runtime$SynchronizedTypes$$super$unique(tp);
            } else {
                Types.Type result2;
                Option inCache = $this.scala$reflect$runtime$SynchronizedTypes$$uniques().get(tp);
                Types.Type type2 = result2 = inCache.isDefined() ? (Types.Type)((Reference)inCache.get()).get() : null;
                if (result2 != null) {
                    type = result2;
                } else {
                    $this.scala$reflect$runtime$SynchronizedTypes$$uniques().update(tp, new java.lang.ref.WeakReference<Types.Type>(tp));
                    type = tp;
                }
            }
            Types.Type type3 = type;
            return type3;
        }
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply() {
                return 0;
            }

            public int apply$mcI$sp() {
                return 0;
            }
        });
    }

    public static int skolemizationLevel(SymbolTable $this) {
        return BoxesRunTime.unboxToInt($this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel().get());
    }

    public static void skolemizationLevel_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel().set(BoxesRunTime.boxToInteger(value));
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final TypeConstraints.UndoLog apply() {
                return new TypeConstraints.UndoLog((scala.reflect.internal.SymbolTable)((Object)this.$outer));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static TypeConstraints.UndoLog undoLog(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_undoLog().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> apply() {
                return ((scala.reflect.internal.SymbolTable)((Object)this.$outer)).perRunCaches().newWeakMap();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static WeakHashMap intersectionWitness(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply() {
                return 0;
            }

            public int apply$mcI$sp() {
                return 0;
            }
        });
    }

    public static int subsametypeRecursions(SymbolTable $this) {
        return BoxesRunTime.unboxToInt($this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions().get());
    }

    public static void subsametypeRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions().set(BoxesRunTime.boxToInteger(value));
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashSet<TypeComparers.SubTypePair> apply() {
                return new HashSet<TypeComparers.SubTypePair>();
            }
        });
    }

    public static HashSet pendingSubTypes(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply() {
                return 0;
            }

            public int apply$mcI$sp() {
                return 0;
            }
        });
    }

    public static int basetypeRecursions(SymbolTable $this) {
        return BoxesRunTime.unboxToInt($this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions().get());
    }

    public static void basetypeRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions().set(BoxesRunTime.boxToInteger(value));
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashSet<Types.Type> apply() {
                return new HashSet<Types.Type>();
            }
        });
    }

    public static HashSet pendingBaseTypes(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> apply() {
                return new HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>();
            }
        });
    }

    public static HashMap lubResults(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_lubResults().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> apply() {
                return new HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>();
            }
        });
    }

    public static HashMap glbResults(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_glbResults().get();
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final String apply() {
                return "";
            }
        });
    }

    public static String indent(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_indent().get();
    }

    public static void indent_$eq(SymbolTable $this, String value) {
        $this.scala$reflect$runtime$SynchronizedTypes$$_indent().set(value);
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply() {
                return 0;
            }

            public int apply$mcI$sp() {
                return 0;
            }
        });
    }

    public static int toStringRecursions(SymbolTable $this) {
        return BoxesRunTime.unboxToInt($this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions().get());
    }

    public static void toStringRecursions_$eq(SymbolTable $this, int value) {
        $this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions().set(BoxesRunTime.boxToInteger(value));
    }

    public static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects(SymbolTable $this) {
        return $this.mkThreadLocalStorage(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final HashSet<Types.Type> apply() {
                return new HashSet<Types.Type>();
            }
        });
    }

    public static HashSet toStringSubjects(SymbolTable $this) {
        return $this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects().get();
    }

    public static void defineUnderlyingOfSingleType(SymbolTable $this, Types.SingleType tpe) {
        $this.gilSynchronized(new Serializable($this, tpe){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final Types.SingleType tpe$1;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(this.tpe$1);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(this.tpe$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tpe$1 = tpe$1;
            }
        });
    }

    public static void defineBaseTypeSeqOfCompoundType(SymbolTable $this, Types.CompoundType tpe) {
        $this.gilSynchronized(new Serializable($this, tpe){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final Types.CompoundType tpe$2;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(this.tpe$2);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(this.tpe$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tpe$2 = tpe$2;
            }
        });
    }

    public static void defineBaseClassesOfCompoundType(SymbolTable $this, Types.CompoundType tpe) {
        $this.gilSynchronized(new Serializable($this, tpe){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final Types.CompoundType tpe$3;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(this.tpe$3);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(this.tpe$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tpe$3 = tpe$3;
            }
        });
    }

    public static void defineParentsOfTypeRef(SymbolTable $this, Types.TypeRef tpe) {
        $this.gilSynchronized(new Serializable($this, tpe){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final Types.TypeRef tpe$4;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(this.tpe$4);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(this.tpe$4);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tpe$4 = tpe$4;
            }
        });
    }

    public static void defineBaseTypeSeqOfTypeRef(SymbolTable $this, Types.TypeRef tpe) {
        $this.gilSynchronized(new Serializable($this, tpe){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;
            public final Types.TypeRef tpe$5;

            public final void apply() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(this.tpe$5);
            }

            public void apply$mcV$sp() {
                this.$outer.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(this.tpe$5);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.tpe$5 = tpe$5;
            }
        });
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq((WeakHashMap)WeakHashMap$.MODULE$.apply(Nil$.MODULE$));
    }
}

