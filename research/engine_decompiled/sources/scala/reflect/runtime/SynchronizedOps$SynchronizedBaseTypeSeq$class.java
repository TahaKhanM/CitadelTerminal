/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function1;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.runtime.Gil;
import scala.reflect.runtime.SynchronizedOps;
import scala.reflect.runtime.SynchronizedOps$SynchronizedBaseTypeSeq$;
import scala.runtime.BoxesRunTime;

public abstract class SynchronizedOps$SynchronizedBaseTypeSeq$class {
    public static Types.Type apply(SynchronizedOps.SynchronizedBaseTypeSeq $this, int i) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final int i$1;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(this.i$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$1 = i$1;
            }
        });
    }

    public static Types.Type rawElem(SynchronizedOps.SynchronizedBaseTypeSeq $this, int i) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final int i$2;

            public final Types.Type apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(this.i$2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$2 = i$2;
            }
        });
    }

    public static Symbols.Symbol typeSymbol(SynchronizedOps.SynchronizedBaseTypeSeq $this, int i) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, i){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final int i$3;

            public final Symbols.Symbol apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(this.i$3);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.i$3 = i$3;
            }
        });
    }

    public static List toList(SynchronizedOps.SynchronizedBaseTypeSeq $this) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;

            public final List<Types.Type> apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static BaseTypeSeqs.BaseTypeSeq copy(SynchronizedOps.SynchronizedBaseTypeSeq $this, Types.Type head2, int offset) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, head2, offset){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final Types.Type head$1;
            private final int offset$1;

            public final BaseTypeSeqs.BaseTypeSeq apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(this.head$1, this.offset$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.head$1 = head$1;
                this.offset$1 = offset$1;
            }
        });
    }

    public static BaseTypeSeqs.BaseTypeSeq map(SynchronizedOps.SynchronizedBaseTypeSeq $this, Function1 f) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final Function1 f$1;

            public final BaseTypeSeqs.BaseTypeSeq apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(this.f$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$1 = f$1;
            }
        });
    }

    public static boolean exists(SynchronizedOps.SynchronizedBaseTypeSeq $this, Function1 p) {
        return BoxesRunTime.unboxToBoolean(((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            public final Function1 p$1;

            public final boolean apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(this.p$1);
            }

            public boolean apply$mcZ$sp() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(this.p$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.p$1 = p$1;
            }
        }));
    }

    public static int maxDepth(SynchronizedOps.SynchronizedBaseTypeSeq $this) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;

            public final int apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$maxDepthOfElems();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }).depth();
    }

    public static String toString(SynchronizedOps.SynchronizedBaseTypeSeq $this) {
        return ((Gil)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer())).gilSynchronized(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;

            public final String apply() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public static BaseTypeSeqs.BaseTypeSeq lateMap(SynchronizedOps.SynchronizedBaseTypeSeq $this, Function1 f) {
        return $this.map(f).toList().exists((Function1<Types.Type, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Types.Type x$2) {
                return x$2 instanceof Types.RefinedType;
            }
        })) ? new SynchronizedOps.SynchronizedBaseTypeSeq($this, f){
            private final /* synthetic */ SynchronizedOps.SynchronizedBaseTypeSeq $outer;
            private final int maxDepth;
            private volatile boolean bitmap$0;

            private int maxDepth$lzycompute() {
                SynchronizedOps$SynchronizedBaseTypeSeq$.anon.2 var1_1 = this;
                synchronized (var1_1) {
                    if (!this.bitmap$0) {
                        this.maxDepth = SynchronizedOps$SynchronizedBaseTypeSeq$class.maxDepth(this);
                        this.bitmap$0 = true;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.maxDepth;
                }
            }

            public int maxDepth() {
                return this.bitmap$0 ? this.maxDepth : this.maxDepth$lzycompute();
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(int i) {
                return super.apply(i);
            }

            public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(int i) {
                return super.rawElem(i);
            }

            public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(int i) {
                return super.typeSymbol(i);
            }

            public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList() {
                return super.toList();
            }

            public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(Types.Type head2, int offset) {
                return super.copy(head2, offset);
            }

            public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(Function1 f) {
                return super.map(f);
            }

            public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(Function1 p) {
                return super.exists(p);
            }

            public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$maxDepthOfElems() {
                return super.maxDepthOfElems();
            }

            public /* synthetic */ String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString() {
                return super.toString();
            }

            public Types.Type apply(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.apply(this, i);
            }

            public Types.Type rawElem(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.rawElem(this, i);
            }

            public Symbols.Symbol typeSymbol(int i) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.typeSymbol(this, i);
            }

            public List<Types.Type> toList() {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.toList(this);
            }

            public BaseTypeSeqs.BaseTypeSeq copy(Types.Type head2, int offset) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.copy(this, head2, offset);
            }

            public BaseTypeSeqs.BaseTypeSeq map(Function1<Types.Type, Types.Type> f) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.map(this, f);
            }

            public boolean exists(Function1<Types.Type, Object> p) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.exists(this, p);
            }

            public String toString() {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.toString(this);
            }

            public BaseTypeSeqs.BaseTypeSeq lateMap(Function1<Types.Type, Types.Type> f) {
                return SynchronizedOps$SynchronizedBaseTypeSeq$class.lateMap(this, f);
            }

            public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer() {
                return this.$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                super((SymbolTable)((Object)$outer.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer()), (BaseTypeSeqs.BaseTypeSeq)((Object)$outer), f$2);
                SynchronizedOps$SynchronizedBaseTypeSeq$class.$init$(this);
            }
        } : new BaseTypeSeqs.MappedBaseTypeSeq((SymbolTable)((Object)$this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer()), (BaseTypeSeqs.BaseTypeSeq)((Object)$this), f);
    }

    public static void $init$(SynchronizedOps.SynchronizedBaseTypeSeq $this) {
    }
}

