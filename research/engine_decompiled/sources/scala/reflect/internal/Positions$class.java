/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple2$mcII$sp;
import scala.collection.AbstractIterable;
import scala.collection.GenTraversable;
import scala.collection.LinearSeqOptimized;
import scala.collection.SeqLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering$Int$;
import scala.reflect.ClassTag$;
import scala.reflect.api.Trees;
import scala.reflect.internal.Positions;
import scala.reflect.internal.Positions$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Trees;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Position$;
import scala.reflect.internal.util.SourceFile;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;

public abstract class Positions$class {
    public static boolean useOffsetPositions(SymbolTable $this) {
        return true;
    }

    public static Position wrappingPos(SymbolTable $this, Position position, List trees) {
        return $this.wrappingPos(position, trees, true);
    }

    public static Position wrappingPos(SymbolTable $this, Position position, List trees, boolean focus) {
        List ranged;
        Position position2 = $this.useOffsetPositions() ? position : ((ranged = (List)trees.filter((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Trees.Tree x$1) {
                return x$1.pos().isRange();
            }
        }))).isEmpty() ? (focus ? position.focus() : position) : Position$.MODULE$.range(position.source(), BoxesRunTime.unboxToInt(((TraversableOnce)ranged.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Trees.Tree x$2) {
                return x$2.pos().start();
            }
        }, List$.MODULE$.canBuildFrom())).min(Ordering$Int$.MODULE$)), position.point(), BoxesRunTime.unboxToInt(((TraversableOnce)ranged.map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final int apply(Trees.Tree x$3) {
                return x$3.pos().end();
            }
        }, List$.MODULE$.canBuildFrom())).max(Ordering$Int$.MODULE$))));
        return position2;
    }

    public static Position wrappingPos(SymbolTable $this, List trees) {
        Position headpos = ((Trees.Tree)trees.head()).pos();
        return !$this.useOffsetPositions() && headpos.isDefined() ? $this.wrappingPos(headpos, (List<Trees.Tree>)trees) : headpos;
    }

    public static void ensureNonOverlapping(SymbolTable $this, Trees.Tree tree, List others) {
        $this.ensureNonOverlapping(tree, others, true);
    }

    public static void ensureNonOverlapping(SymbolTable $this, Trees.Tree tree, List others, boolean focus) {
        if ($this.useOffsetPositions()) {
            return;
        }
        if (Positions$class.isOverlapping$1($this, (Position)tree.rawatt().pos(), others)) {
            List children2;
            List list2 = children2 = tree.children();
            while (!((AbstractIterable)list2).isEmpty()) {
                Trees.Tree tree2 = (Trees.Tree)((AbstractIterable)list2).head();
                $this.ensureNonOverlapping(tree2, others, focus);
                list2 = (List)list2.tail();
            }
            if (tree.pos().isOpaqueRange()) {
                Position wpos = $this.wrappingPos((Position)tree.rawatt().pos(), children2, focus);
                tree.setPos(Positions$class.isOverlapping$1($this, wpos, others) ? ((Position)tree.rawatt().pos()).makeTransparent() : wpos);
            }
        }
    }

    public static Position rangePos(SymbolTable $this, SourceFile source, int start, int point, int end) {
        return $this.useOffsetPositions() ? Position$.MODULE$.offset(source, point) : Position$.MODULE$.range(source, start, point, end);
    }

    public static void validatePositions(SymbolTable $this, Trees.Tree tree) {
        if ($this.useOffsetPositions()) {
            return;
        }
        if (!$this.isPastTyper()) {
            Positions$class.validate$1($this, tree, tree, tree);
        }
    }

    public static List solidDescendants(SymbolTable $this, Trees.Tree tree) {
        return tree.pos().isTransparent() ? tree.children().flatMap(new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;

            public final List<Trees.Tree> apply(Trees.Tree tree) {
                return this.$outer.solidDescendants(tree);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom()) : List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{tree}));
    }

    private static Positions.Range free(SymbolTable $this, int lo, int hi) {
        return new Positions.Range($this, Position$.MODULE$.range(null, lo, lo, hi), $this.EmptyTree());
    }

    public static Positions.Range scala$reflect$internal$Positions$$maxFree(SymbolTable $this) {
        return Positions$class.free($this, 0, Integer.MAX_VALUE);
    }

    private static List maybeFree(SymbolTable $this, int lo, int hi) {
        return lo < hi ? List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Positions.Range[]{Positions$class.free($this, lo, hi)})) : Nil$.MODULE$;
    }

    public static List scala$reflect$internal$Positions$$insert(SymbolTable $this, List rs, Trees.Tree t, ListBuffer conflicting) {
        block7: {
            List<Positions.Range> list2;
            block6: {
                List<Positions.Range> list3;
                block5: {
                    Some<List> some = List$.MODULE$.unapplySeq(rs);
                    if (some.isEmpty() || some.get() == null || ((LinearSeqOptimized)some.get()).lengthCompare(0) != 0) break block5;
                    Predef$.MODULE$.assert(conflicting.nonEmpty());
                    list2 = rs;
                    break block6;
                }
                if (!(rs instanceof $colon$colon)) break block7;
                $colon$colon $colon$colon = ($colon$colon)rs;
                Predef$.MODULE$.assert(!t.pos().isTransparent());
                if (((Positions.Range)$colon$colon.head()).isFree() && ((Positions.Range)$colon$colon.head()).pos().includes(t.pos())) {
                    List list4 = Positions$class.maybeFree($this, t.pos().end(), ((Positions.Range)$colon$colon.head()).pos().end());
                    GenTraversable genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Positions.Range[]{new Positions.Range($this, t.pos(), t)}));
                    List list5 = Positions$class.maybeFree($this, ((Positions.Range)$colon$colon.head()).pos().start(), t.pos().start());
                    list3 = $colon$colon.tl$1().$colon$colon$colon(list5).$colon$colon$colon(genTraversable).$colon$colon$colon(list4);
                } else {
                    Object object = !((Positions.Range)$colon$colon.head()).isFree() && ((Positions.Range)$colon$colon.head()).pos().overlaps(t.pos()) ? conflicting.$plus$eq(((Positions.Range)$colon$colon.head()).tree()) : BoxedUnit.UNIT;
                    Positions.Range range2 = (Positions.Range)$colon$colon.head();
                    list3 = Positions$class.scala$reflect$internal$Positions$$insert($this, $colon$colon.tl$1(), t, conflicting).$colon$colon(range2);
                }
                list2 = list3;
            }
            return list2;
        }
        throw new MatchError(rs);
    }

    private static List replace(SymbolTable $this, List ts, Trees.Tree t, List replacement) {
        List<Object> list2;
        Object a = ts.head();
        if (!(a != null ? !a.equals(t) : t != null)) {
            list2 = ((List)ts.tail()).$colon$colon$colon(replacement);
        } else {
            Trees.Tree tree = (Trees.Tree)ts.head();
            list2 = Positions$class.replace($this, (List)ts.tail(), t, replacement).$colon$colon(tree);
        }
        return list2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List findOverlapping(SymbolTable $this, List cts) {
        List list2;
        NonLocalReturnControl nonLocalReturnControl;
        block7: {
            Object object = new Object();
            try {
                ObjectRef<GenTraversable> ranges = ObjectRef.create(List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Positions.Range[]{$this.scala$reflect$internal$Positions$$maxFree()})));
                Serializable serializable = new Serializable($this, ranges, object){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ SymbolTable $outer;
                    public final ObjectRef ranges$1;
                    public final Object nonLocalReturnKey1$1;

                    public final void apply(Trees.Tree ct) {
                        if (ct.pos().isOpaqueRange()) {
                            ListBuffer<A> conflicting = new ListBuffer<A>();
                            this.ranges$1.elem = Positions$class.scala$reflect$internal$Positions$$insert(this.$outer, (List)this.ranges$1.elem, ct, conflicting);
                            if (conflicting.nonEmpty()) {
                                throw new NonLocalReturnControl<List<A>>(this.nonLocalReturnKey1$1, conflicting.toList().map(new Serializable(this, ct){
                                    public static final long serialVersionUID = 0L;
                                    private final Trees.Tree ct$1;

                                    public final Tuple2<Trees.Tree, Trees.Tree> apply(Trees.Tree t) {
                                        return new Tuple2<Trees.Tree, Trees.Tree>(t, this.ct$1);
                                    }
                                    {
                                        this.ct$1 = ct$1;
                                    }
                                }, List$.MODULE$.canBuildFrom()));
                            }
                        }
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.ranges$1 = ranges$1;
                        this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                    }
                };
                List list3 = cts;
                while (!((AbstractIterable)list3).isEmpty()) {
                    Object a = ((AbstractIterable)list3).head();
                    Trees.Tree tree = (Trees.Tree)a;
                    if (((Position)tree.rawatt().pos()).isOpaqueRange()) {
                        ListBuffer conflicting1 = new ListBuffer();
                        ranges.elem = Positions$class.scala$reflect$internal$Positions$$insert($this, (List)ranges.elem, tree, conflicting1);
                        if (conflicting1.nonEmpty()) {
                            nonLocalReturnControl = new NonLocalReturnControl(object, conflicting1.toList().map(new /* invalid duplicate definition of identical inner class */, List$.MODULE$.canBuildFrom()));
                            if (nonLocalReturnControl.key() != object) {
                                throw nonLocalReturnControl;
                            }
                            break block7;
                        }
                    }
                    list3 = (List)list3.tail();
                }
                list2 = Nil$.MODULE$;
                return list2;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) throw nonLocalReturnControl;
            }
        }
        list2 = (List)nonLocalReturnControl.value();
        return list2;
    }

    /*
     * Unable to fully structure code
     */
    public static void scala$reflect$internal$Positions$$setChildrenPos(SymbolTable $this, Position pos, List trees) {
        try {
            var7_3 = trees;
            while (!var7_3.isEmpty()) {
                var3_4 = var7_3.head();
                var5_6 = (Trees.Tree)var3_4;
                if (var5_6.isEmpty() || !var5_6.canHaveAttrs()) ** GOTO lbl-1000
                v0 = (Position)var5_6.rawatt().pos();
                var4_5 = $this.NoPosition();
                if (!(v0 != null ? v0.equals(var4_5) == false : var4_5 != null)) {
                    children1 = var5_6.children();
                    if (children1.isEmpty()) {
                        v1 = var5_6.setPos(pos.focus());
                    } else {
                        Positions$class.scala$reflect$internal$Positions$$setChildrenPos($this, pos, children1);
                        v1 = var5_6.setPos($this.wrappingPos(pos, children1));
                    }
                } else lbl-1000:
                // 2 sources

                {
                    v1 = BoxedUnit.UNIT;
                }
                var7_3 = (List)var7_3.tail();
            }
            return;
        }
        catch (Exception var8_8) {
            $this.inform(new StringBuilder().append((Object)"error while set children pos ").append(pos).append((Object)" of ").append(trees).toString());
            throw var8_8;
        }
    }

    public static Positions.PosAssigner posAssigner(SymbolTable $this) {
        return new Positions.DefaultPosAssigner($this);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Trees.Tree atPos(SymbolTable $this, Position pos, Trees.Tree tree) {
        Trees.Tree tree2;
        block5: {
            block4: {
                Object object;
                block6: {
                    if ($this.useOffsetPositions() || !pos.isOpaqueRange()) break block5;
                    if (tree.isEmpty() || !tree.canHaveAttrs()) break block6;
                    Position position = tree.pos();
                    NoPosition$ noPosition$ = $this.NoPosition();
                    if (position != null ? !position.equals(noPosition$) : noPosition$ != null) break block6;
                    tree.setPos(pos);
                    List<Trees.Tree> children2 = tree.children();
                    if (children2.nonEmpty()) {
                        if (((SeqLike)children2.tail()).isEmpty()) {
                            object = $this.atPos(pos, children2.head());
                            break block4;
                        } else {
                            Positions$class.scala$reflect$internal$Positions$$setChildrenPos($this, pos, children2);
                            object = BoxedUnit.UNIT;
                        }
                        break block4;
                    } else {
                        object = BoxedUnit.UNIT;
                    }
                    break block4;
                }
                object = BoxedUnit.UNIT;
            }
            tree2 = tree;
            return tree2;
        }
        $this.posAssigner().pos_$eq(pos);
        ((Trees.Traverser)((Object)$this.posAssigner())).traverse(tree);
        tree2 = tree;
        return tree2;
    }

    private static final boolean isOverlapping$1(SymbolTable $this, Position pos, List others$1) {
        return pos.isRange() && others$1.exists(new Serializable($this, pos){
            public static final long serialVersionUID = 0L;
            private final Position pos$1;

            public final boolean apply(Trees.Tree x$4) {
                return this.pos$1.overlaps(x$4.pos());
            }
            {
                this.pos$1 = pos$1;
            }
        });
    }

    public static final void reportTree$1(SymbolTable $this, String prefix, Trees.Tree tree) {
        String source = tree.pos().isDefined() ? tree.pos().source() : "";
        $this.inform(new StringBuilder().append((Object)"== ").append((Object)prefix).append((Object)" tree [").append(BoxesRunTime.boxToInteger(tree.id())).append((Object)"] of type ").append((Object)tree.productPrefix()).append((Object)" at ").append((Object)tree.pos().show()).append((Object)source).toString());
        $this.inform("");
        $this.inform($this.treeStatus(tree, $this.treeStatus$default$2()));
        $this.inform("");
    }

    private static final void positionError$1(SymbolTable $this, String msg, Function0 body2, Trees.Tree tree$2) {
        $this.inform(new StringBuilder().append((Object)"======= Position error\n").append((Object)msg).toString());
        body2.apply$mcV$sp();
        $this.inform(new StringBuilder().append((Object)"\nWhile validating #").append(BoxesRunTime.boxToInteger(tree$2.id())).toString());
        $this.inform($this.treeStatus(tree$2, $this.treeStatus$default$2()));
        $this.inform("\nChildren:");
        List list2 = tree$2.children();
        while (!((AbstractIterable)list2).isEmpty()) {
            Trees.Tree tree = (Trees.Tree)((AbstractIterable)list2).head();
            $this.inform(new StringBuilder().append((Object)"  ").append((Object)$this.treeStatus(tree, tree$2)).toString());
            list2 = (List)list2.tail();
        }
        $this.inform("=======");
        throw new Positions.ValidateException($this, msg);
    }

    public static final void validate$1(SymbolTable $this, Trees.Tree tree, Trees.Tree encltree, Trees.Tree tree$2) {
        block10: {
            block11: {
                block12: {
                    if (tree.isEmpty() || !tree.canHaveAttrs()) break block10;
                    MutableSettings.SettingValue settingValue = $this.settings().Yposdebug();
                    MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                    if (!BoxesRunTime.unboxToBoolean(settingValue.value())) break block11;
                    MutableSettings.SettingValue settingValue2 = $this.settings().verbose();
                    MutableSettings$ mutableSettings$2 = MutableSettings$.MODULE$;
                    if (BoxesRunTime.unboxToBoolean(settingValue2.value())) break block12;
                    MutableSettings.SettingValue settingValue3 = $this.settings().Yrangepos();
                    MutableSettings$ mutableSettings$3 = MutableSettings$.MODULE$;
                    if (!BoxesRunTime.unboxToBoolean(settingValue3.value())) break block11;
                }
                Predef$ predef$ = Predef$.MODULE$;
                $this.inform(new StringOps("[%10s] %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{"validate", $this.treeStatus(tree, encltree)})));
            }
            if (!((Position)tree.rawatt().pos()).isDefined()) {
                Positions$class.positionError$1($this, new StringBuilder().append((Object)"Unpositioned tree #").append(BoxesRunTime.boxToInteger(tree.id())).toString(), (Function0)((Object)new Serializable($this, tree, encltree){
                    public static final long serialVersionUID = 0L;
                    public final /* synthetic */ SymbolTable $outer;
                    private final Trees.Tree tree$1;
                    public final Trees.Tree encltree$1;

                    public final void apply() {
                        this.apply$mcV$sp();
                    }

                    public void apply$mcV$sp() {
                        Predef$ predef$ = Predef$.MODULE$;
                        this.$outer.inform(new StringOps("%15s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{"unpositioned", this.$outer.treeStatus(this.tree$1, this.encltree$1)})));
                        Predef$ predef$2 = Predef$.MODULE$;
                        this.$outer.inform(new StringOps("%15s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{"enclosing", this.$outer.treeStatus(this.encltree$1, this.$outer.treeStatus$default$2())})));
                        Serializable serializable = new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ Positions$.anonfun.validate.1.1 $outer;

                            public final void apply(Trees.Tree t) {
                                Predef$ predef$ = Predef$.MODULE$;
                                this.$outer.$outer.inform(new StringOps("%15s %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{"sibling", this.$outer.$outer.treeStatus(t, this.$outer.encltree$1)})));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        };
                        List list2 = this.encltree$1.children();
                        while (!((AbstractIterable)list2).isEmpty()) {
                            A a = ((AbstractIterable)list2).head();
                            serializable.apply((Trees.Tree)a);
                            list2 = (List)list2.tail();
                        }
                    }

                    public /* synthetic */ SymbolTable scala$reflect$internal$Positions$$anonfun$$$outer() {
                        return this.$outer;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tree$1 = tree$1;
                        this.encltree$1 = encltree$1;
                    }
                }), tree$2);
            }
            if (((Position)tree.rawatt().pos()).isRange()) {
                List<Tuple2<Trees.Tree, Trees.Tree>> list2;
                Some<List<Tuple2<Trees.Tree, Trees.Tree>>> some;
                if (!((Position)encltree.rawatt().pos()).isRange()) {
                    Positions$class.positionError$1($this, new StringBuilder().append((Object)"Synthetic tree [").append(BoxesRunTime.boxToInteger(encltree.id())).append((Object)"] contains nonsynthetic tree [").append(BoxesRunTime.boxToInteger(tree.id())).append((Object)"]").toString(), (Function0)((Object)new Serializable($this, tree, encltree){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;
                        private final Trees.Tree tree$1;
                        private final Trees.Tree encltree$1;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            Positions$class.reportTree$1(this.$outer, "Enclosing", this.encltree$1);
                            Positions$class.reportTree$1(this.$outer, "Enclosed", this.tree$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tree$1 = tree$1;
                            this.encltree$1 = encltree$1;
                        }
                    }), tree$2);
                }
                if (!encltree.pos().includes(tree.pos())) {
                    Positions$class.positionError$1($this, new StringBuilder().append((Object)"Enclosing tree [").append(BoxesRunTime.boxToInteger(encltree.id())).append((Object)"] does not include tree [").append(BoxesRunTime.boxToInteger(tree.id())).append((Object)"]").toString(), (Function0)((Object)new Serializable($this, tree, encltree){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ SymbolTable $outer;
                        private final Trees.Tree tree$1;
                        private final Trees.Tree encltree$1;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            Positions$class.reportTree$1(this.$outer, "Enclosing", this.encltree$1);
                            Positions$class.reportTree$1(this.$outer, "Enclosed", this.tree$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tree$1 = tree$1;
                            this.encltree$1 = encltree$1;
                        }
                    }), tree$2);
                }
                if (!(some = List$.MODULE$.unapplySeq(list2 = $this.findOverlapping(tree.children().flatMap(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SymbolTable $outer;

                    public final List<Trees.Tree> apply(Trees.Tree tree) {
                        return this.$outer.solidDescendants(tree);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom())))).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 0) {
                } else {
                    Positions$class.positionError$1($this, new StringBuilder().append((Object)"Overlapping trees ").append((Object)((TraversableOnce)list2.map(new Serializable($this){
                        public static final long serialVersionUID = 0L;

                        public final Tuple2<Object, Object> apply(Tuple2<Trees.Tree, Trees.Tree> x0$1) {
                            if (x0$1 != null) {
                                return new Tuple2$mcII$sp(x0$1._1().id(), x0$1._2().id());
                            }
                            throw new MatchError(x0$1);
                        }
                    }, List$.MODULE$.canBuildFrom())).mkString("", ", ", "")).toString(), (Function0)((Object)new Serializable($this, tree, list2){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ SymbolTable $outer;
                        private final Trees.Tree tree$1;
                        private final List x1$1;

                        public final void apply() {
                            this.apply$mcV$sp();
                        }

                        public void apply$mcV$sp() {
                            Positions$class.reportTree$1(this.$outer, "Ancestor", this.tree$1);
                            this.x1$1.withFilter(new Serializable(this){
                                public static final long serialVersionUID = 0L;

                                public final boolean apply(Tuple2<Trees.Tree, Trees.Tree> check$ifrefutable$1) {
                                    boolean bl = check$ifrefutable$1 != null;
                                    return bl;
                                }
                            }).foreach(new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ Positions$.anonfun.validate.1.4 $outer;

                                public final void apply(Tuple2<Trees.Tree, Trees.Tree> x$6) {
                                    if (x$6 != null) {
                                        Positions$class.reportTree$1(this.$outer.$outer, "First overlapping", x$6._1());
                                        Positions$class.reportTree$1(this.$outer.$outer, "Second overlapping", x$6._2());
                                        return;
                                    }
                                    throw new MatchError(x$6);
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            });
                        }

                        public /* synthetic */ SymbolTable scala$reflect$internal$Positions$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.tree$1 = tree$1;
                            this.x1$1 = x1$1;
                        }
                    }), tree$2);
                }
            }
            List list3 = tree.children().flatMap(new Serializable($this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ SymbolTable $outer;

                public final List<Trees.Tree> apply(Trees.Tree tree) {
                    return this.$outer.solidDescendants(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
            while (!((AbstractIterable)list3).isEmpty()) {
                Trees.Tree tree2 = (Trees.Tree)((AbstractIterable)list3).head();
                Positions$class.validate$1($this, tree2, tree, tree$2);
                list3 = (List)list3.tail();
            }
        }
    }

    public static void $init$(SymbolTable $this) {
        $this.scala$reflect$internal$Positions$_setter_$NoPosition_$eq(NoPosition$.MODULE$);
        $this.scala$reflect$internal$Positions$_setter_$PositionTag_$eq(ClassTag$.MODULE$.apply(Position.class));
    }
}

