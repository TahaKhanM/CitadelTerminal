/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.generic.Growable;
import scala.collection.immutable.List;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Set;
import scala.collection.mutable.SetLike;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.ParSet$;
import scala.collection.script.Include;
import scala.collection.script.Message;
import scala.collection.script.Remove;
import scala.collection.script.Reset;
import scala.collection.script.Script;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public abstract class SetLike$class {
    public static Builder newBuilder(SetLike $this) {
        return (Builder)$this.empty();
    }

    public static Combiner parCombiner(SetLike $this) {
        return ParSet$.MODULE$.newCombiner();
    }

    public static boolean add(SetLike $this, Object elem) {
        boolean r = $this.contains(elem);
        $this.$plus$eq(elem);
        return !r;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean remove(SetLike $this, Object elem) {
        void var2_2;
        boolean r = $this.contains(elem);
        $this.$minus$eq(elem);
        return (boolean)var2_2;
    }

    public static void update(SetLike $this, Object elem, boolean included) {
        if (included) {
            $this.$plus$eq(elem);
        } else {
            $this.$minus$eq(elem);
        }
    }

    public static void retain(SetLike $this, Function1 p) {
        List these1 = $this.toList();
        while (!these1.isEmpty()) {
            Object a = these1.head();
            Object object = BoxesRunTime.unboxToBoolean(p.apply(a)) ? BoxedUnit.UNIT : $this.$minus$eq(a);
            these1 = (List)these1.tail();
        }
        return;
    }

    public static void clear(SetLike $this) {
        List these1 = $this.toList();
        while (!these1.isEmpty()) {
            Object a = these1.head();
            $this.$minus$eq(a);
            these1 = (List)these1.tail();
        }
        return;
    }

    public static Set clone(SetLike $this) {
        return (Set)((Growable)$this.empty()).$plus$plus$eq(((Set)$this.repr()).seq());
    }

    public static Set result(SetLike $this) {
        return (Set)$this.repr();
    }

    public static Set $plus(SetLike $this, Object elem) {
        return (Set)$this.clone().$plus$eq((Object)elem);
    }

    public static Set $plus(SetLike $this, Object elem1, Object elem2, Seq elems) {
        return (Set)$this.clone().$plus$eq((Object)elem1).$plus$eq(elem2).$plus$plus$eq(elems);
    }

    public static Set $plus$plus(SetLike $this, GenTraversableOnce xs) {
        return (Set)$this.clone().$plus$plus$eq(xs.seq());
    }

    public static Set $minus(SetLike $this, Object elem) {
        return (Set)$this.clone().$minus$eq((Object)elem);
    }

    public static Set $minus(SetLike $this, Object elem1, Object elem2, Seq elems) {
        return (Set)$this.clone().$minus$eq((Object)elem1).$minus$eq(elem2).$minus$minus$eq(elems);
    }

    public static Set $minus$minus(SetLike $this, GenTraversableOnce xs) {
        return (Set)$this.clone().$minus$minus$eq(xs.seq());
    }

    public static void $less$less(SetLike $this, Message cmd) {
        block6: {
            block3: {
                block5: {
                    block4: {
                        block2: {
                            if (!(cmd instanceof Include)) break block2;
                            Include include = (Include)cmd;
                            $this.$plus$eq(include.elem());
                            break block3;
                        }
                        if (!(cmd instanceof Remove)) break block4;
                        Remove remove = (Remove)cmd;
                        $this.$minus$eq(remove.elem());
                        break block3;
                    }
                    if (!(cmd instanceof Reset)) break block5;
                    $this.clear();
                    break block3;
                }
                if (!(cmd instanceof Script)) break block6;
                Script script = (Script)cmd;
                script.iterator().foreach(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SetLike $outer;

                    public final void apply(Message<A> cmd) {
                        this.$outer.$less$less(cmd);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            return;
        }
        throw new UnsupportedOperationException(new StringBuilder().append((Object)"message ").append(cmd).append((Object)" not understood").toString());
    }

    public static void $init$(SetLike $this) {
    }
}

