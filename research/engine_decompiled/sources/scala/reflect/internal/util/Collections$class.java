/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.GenTraversable;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.LinkedHashMap$;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Set;
import scala.collection.mutable.Set$;
import scala.reflect.internal.util.Collections;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public abstract class Collections$class {
    public static final boolean corresponds3(Collections $this, List xs1, List xs2, List xs3, Function3 f) {
        boolean bl;
        block2: {
            while (true) {
                if (xs1.isEmpty()) {
                    bl = xs2.isEmpty() && xs3.isEmpty();
                    break block2;
                }
                if (xs2.isEmpty() || xs3.isEmpty() || !BoxesRunTime.unboxToBoolean(f.apply(xs1.head(), xs2.head(), xs3.head()))) break;
                xs3 = (List)xs3.tail();
                xs2 = (List)xs2.tail();
                xs1 = (List)xs1.tail();
            }
            bl = false;
        }
        return bl;
    }

    public static final boolean mexists(Collections $this, List xss, Function1 p) {
        return xss.exists(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$1;

            public final boolean apply(List<A> x$1) {
                return x$1.exists(this.p$1);
            }
            {
                this.p$1 = p$1;
            }
        });
    }

    public static final boolean mforall(Collections $this, List xss, Function1 p) {
        return xss.forall(new Serializable($this, p){
            public static final long serialVersionUID = 0L;
            private final Function1 p$2;

            public final boolean apply(List<A> x$2) {
                return x$2.forall(this.p$2);
            }
            {
                this.p$2 = p$2;
            }
        });
    }

    public static final List mmap(Collections $this, List xss, Function1 f) {
        return xss.map(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$1;

            public final List<B> apply(List<A> x$3) {
                return x$3.map(this.f$1, List$.MODULE$.canBuildFrom());
            }
            {
                this.f$1 = f$1;
            }
        }, List$.MODULE$.canBuildFrom());
    }

    public static final Option mfind(Collections $this, List xss, Function1 p) {
        ObjectRef<Object> res = ObjectRef.create(null);
        $this.mforeach(xss, new Serializable($this, res, p){
            public static final long serialVersionUID = 0L;
            private final ObjectRef res$1;
            private final Function1 p$3;

            public final void apply(A x) {
                if ((Option)this.res$1.elem == null && BoxesRunTime.unboxToBoolean(this.p$3.apply(x))) {
                    this.res$1.elem = new Some<A>(x);
                }
            }
            {
                this.res$1 = res$1;
                this.p$3 = p$3;
            }
        });
        return (Option)res.elem == null ? None$.MODULE$ : (Option)res.elem;
    }

    public static final void mforeach(Collections $this, List xss, Function1 f) {
        Serializable serializable = new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            public final Function1 f$2;

            public final void apply(List<A> x$4) {
                Function1 function1 = this.f$2;
                List list2 = x$4;
                while (!((AbstractIterable)list2).isEmpty()) {
                    function1.apply(((AbstractIterable)list2).head());
                    list2 = (List)list2.tail();
                }
            }
            {
                this.f$2 = f$2;
            }
        };
        List list2 = xss;
        while (!((AbstractIterable)list2).isEmpty()) {
            List list3 = (List)((AbstractIterable)list2).head();
            Function1 function1 = serializable.f$2;
            List list4 = list3;
            while (!((AbstractIterable)list4).isEmpty()) {
                function1.apply(((AbstractIterable)list4).head());
                list4 = (List)list4.tail();
            }
            list2 = (List)list2.tail();
        }
    }

    public static final void mforeach(Collections $this, Traversable xss, Function1 f) {
        xss.foreach(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$3;

            public final void apply(Traversable<A> x$5) {
                x$5.foreach(this.f$3);
            }
            {
                this.f$3 = f$3;
            }
        });
    }

    /*
     * WARNING - void declaration
     */
    public static final List mapList(Collections $this, List as, Function1 f) {
        Nil$ nil$;
        if (as == Nil$.MODULE$) {
            nil$ = Nil$.MODULE$;
        } else {
            void var3_3;
            $colon$colon<Nothing$> head2;
            $colon$colon<Nothing$> tail = head2 = new $colon$colon<Nothing$>((Nothing$)f.apply(as.head()), Nil$.MODULE$);
            for (List rest = (List)as.tail(); rest != Nil$.MODULE$; rest = (List)rest.tail()) {
                $colon$colon<Nothing$> next2 = new $colon$colon<Nothing$>((Nothing$)f.apply(rest.head()), Nil$.MODULE$);
                tail.tl_$eq(next2);
                tail = next2;
            }
            nil$ = var3_3;
        }
        return nil$;
    }

    public static final Option collectFirst(Collections $this, List as, PartialFunction pf) {
        return Collections$class.loop$1($this, as, pf);
    }

    /*
     * WARNING - void declaration
     */
    public static final List map2(Collections $this, List xs1, List xs2, Function2 f) {
        ListBuffer lb = new ListBuffer();
        List ys1 = xs1;
        List ys2 = xs2;
        while (!ys1.isEmpty() && !ys2.isEmpty()) {
            List list2;
            List list3;
            void var4_4;
            var4_4.$plus$eq(f.apply(list3.head(), list2.head()));
            list3 = (List)list3.tail();
            list2 = (List)list2.tail();
        }
        return lb.toList();
    }

    public static final List map2Conserve(Collections $this, List xs, List ys, Function2 f) {
        return Collections$class.loop$2($this, null, xs, xs, ys, f);
    }

    public static final List map3(Collections $this, List xs1, List xs2, List xs3, Function3 f) {
        List list2;
        if (xs1.isEmpty() || xs2.isEmpty() || xs3.isEmpty()) {
            list2 = Nil$.MODULE$;
        } else {
            Object r = f.apply(xs1.head(), xs2.head(), xs3.head());
            list2 = $this.map3((List)xs1.tail(), (List)xs2.tail(), (List)xs3.tail(), f).$colon$colon(r);
        }
        return list2;
    }

    public static final List flatMap2(Collections $this, List xs1, List xs2, Function2 f) {
        ListBuffer lb = null;
        List ys1 = xs1;
        List ys2 = xs2;
        while (!ys1.isEmpty() && !ys2.isEmpty()) {
            Object object;
            List cs = (List)f.apply(ys1.head(), ys2.head());
            if (cs != Nil$.MODULE$) {
                if (lb == null) {
                    lb = new ListBuffer();
                }
                object = lb.$plus$plus$eq((TraversableOnce)cs);
            } else {
                object = BoxedUnit.UNIT;
            }
            ys1 = (List)ys1.tail();
            ys2 = (List)ys2.tail();
        }
        return lb == null ? Nil$.MODULE$ : lb.result();
    }

    public static final List flatCollect(Collections $this, List elems, PartialFunction pf) {
        ListBuffer lb = new ListBuffer();
        elems.withFilter(new Serializable($this, pf){
            public static final long serialVersionUID = 0L;
            private final PartialFunction pf$2;

            public final boolean apply(A x) {
                return this.pf$2.isDefinedAt(x);
            }
            {
                this.pf$2 = pf$2;
            }
        }).foreach(new Serializable($this, lb, pf){
            public static final long serialVersionUID = 0L;
            private final ListBuffer lb$1;
            private final PartialFunction pf$2;

            public final ListBuffer<B> apply(A x) {
                return this.lb$1.$plus$plus$eq((TraversableOnce)this.pf$2.apply(x));
            }
            {
                this.lb$1 = lb$1;
                this.pf$2 = pf$2;
            }
        });
        return lb.toList();
    }

    public static final List distinctBy(Collections $this, List xs, Function1 f) {
        ListBuffer buf = new ListBuffer();
        Set seen = (Set)Set$.MODULE$.apply(Nil$.MODULE$);
        Serializable serializable = new Serializable($this, buf, seen, f){
            public static final long serialVersionUID = 0L;
            public final ListBuffer buf$1;
            public final Set seen$1;
            public final Function1 f$5;

            public final Object apply(A x) {
                Object object;
                R y = this.f$5.apply(x);
                if (this.seen$1.apply(y)) {
                    object = BoxedUnit.UNIT;
                } else {
                    this.buf$1.$plus$eq((Object)x);
                    object = this.seen$1.$plus$eq(y);
                }
                return object;
            }
            {
                this.buf$1 = buf$1;
                this.seen$1 = seen$1;
                this.f$5 = f$5;
            }
        };
        List list2 = xs;
        while (!((AbstractIterable)list2).isEmpty()) {
            Object object;
            Object a = ((AbstractIterable)list2).head();
            Object y1 = serializable.f$5.apply(a);
            if (serializable.seen$1.apply(y1)) {
                object = BoxedUnit.UNIT;
            } else {
                serializable.buf$1.$plus$eq(a);
                object = serializable.seen$1.$plus$eq(y1);
            }
            list2 = (List)list2.tail();
        }
        return buf.toList();
    }

    public static final boolean flattensToEmpty(Collections $this, Seq xss) {
        boolean bl;
        block2: {
            while (true) {
                if (xss.isEmpty()) {
                    bl = true;
                    break block2;
                }
                if (!((SeqLike)xss.head()).isEmpty()) break;
                xss = (Seq)xss.tail();
            }
            bl = false;
        }
        return bl;
    }

    public static final void foreachWithIndex(Collections $this, List xs, Function2 f) {
        int index = 0;
        List ys = xs;
        while (!ys.isEmpty()) {
            f.apply(ys.head(), BoxesRunTime.boxToInteger(index));
            ys = (List)ys.tail();
            ++index;
        }
        return;
    }

    public static final Object findOrElse(Collections $this, TraversableOnce xs, Function1 p, Function0 orElse) {
        Option option = xs.find(p);
        return !option.isEmpty() ? option.get() : orElse.apply();
    }

    public static final Map mapFrom(Collections $this, List xs, Function1 f) {
        return (Map)Predef$.MODULE$.Map().apply(xs.map(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$6;

            public final Tuple2<A, B> apply(A x) {
                return new Tuple2<A, R>(x, this.f$6.apply(x));
            }
            {
                this.f$6 = f$6;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    public static final LinkedHashMap linkedMapFrom(Collections $this, List xs, Function1 f) {
        return (LinkedHashMap)LinkedHashMap$.MODULE$.apply(xs.map(new Serializable($this, f){
            public static final long serialVersionUID = 0L;
            private final Function1 f$7;

            public final Tuple2<A, B> apply(A x) {
                return new Tuple2<A, R>(x, this.f$7.apply(x));
            }
            {
                this.f$7 = f$7;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    /*
     * WARNING - void declaration
     */
    public static final List mapWithIndex(Collections $this, List xs, Function2 f) {
        ListBuffer lb = new ListBuffer();
        boolean index = false;
        List ys = xs;
        while (!ys.isEmpty()) {
            void var5_4;
            List list2;
            void var3_3;
            var3_3.$plus$eq(f.apply(list2.head(), BoxesRunTime.boxToInteger((int)var5_4)));
            list2 = (List)list2.tail();
            ++var5_4;
        }
        return lb.toList();
    }

    /*
     * WARNING - void declaration
     */
    public static final Map collectMap2(Collections $this, List xs1, List xs2, Function2 p) {
        if (xs1.isEmpty() || xs2.isEmpty()) {
            return (Map)Predef$.MODULE$.Map().apply(Nil$.MODULE$);
        }
        Builder buf = Map$.MODULE$.newBuilder();
        List ys1 = xs1;
        List ys2 = xs2;
        while (!ys1.isEmpty() && !ys2.isEmpty()) {
            void var4_4;
            List list2;
            Object x2;
            List list3;
            Object x1 = list3.head();
            Object object = BoxesRunTime.unboxToBoolean(p.apply(x1, x2 = list2.head())) ? var4_4.$plus$eq(new Tuple2(x1, x2)) : BoxedUnit.UNIT;
            list3 = (List)list3.tail();
            list2 = (List)list2.tail();
        }
        return (Map)buf.result();
    }

    public static final void foreach2(Collections $this, List xs1, List xs2, Function2 f) {
        List ys1 = xs1;
        List ys2 = xs2;
        while (!ys1.isEmpty() && !ys2.isEmpty()) {
            f.apply(ys1.head(), ys2.head());
            ys1 = (List)ys1.tail();
            ys2 = (List)ys2.tail();
        }
        return;
    }

    public static final void foreach3(Collections $this, List xs1, List xs2, List xs3, Function3 f) {
        List ys1 = xs1;
        List ys2 = xs2;
        List ys3 = xs3;
        while (!(ys1.isEmpty() || ys2.isEmpty() || ys3.isEmpty())) {
            f.apply(ys1.head(), ys2.head(), ys3.head());
            ys1 = (List)ys1.tail();
            ys2 = (List)ys2.tail();
            ys3 = (List)ys3.tail();
        }
        return;
    }

    public static final boolean exists2(Collections $this, List xs1, List xs2, Function2 f) {
        List ys1 = xs1;
        List ys2 = xs2;
        while (!ys1.isEmpty() && !ys2.isEmpty()) {
            List list2;
            List list3;
            if (BoxesRunTime.unboxToBoolean(f.apply(list3.head(), list2.head()))) {
                return true;
            }
            list3 = (List)list3.tail();
            list2 = (List)list2.tail();
        }
        return false;
    }

    public static final boolean exists3(Collections $this, List xs1, List xs2, List xs3, Function3 f) {
        List ys1 = xs1;
        List ys2 = xs2;
        List ys3 = xs3;
        while (!(ys1.isEmpty() || ys2.isEmpty() || ys3.isEmpty())) {
            List list2;
            List list3;
            List list4;
            if (BoxesRunTime.unboxToBoolean(f.apply(list4.head(), list3.head(), list2.head()))) {
                return true;
            }
            list4 = (List)list4.tail();
            list3 = (List)list3.tail();
            list2 = (List)list2.tail();
        }
        return false;
    }

    public static final boolean forall3(Collections $this, List xs1, List xs2, List xs3, Function3 f) {
        List ys1 = xs1;
        List ys2 = xs2;
        List ys3 = xs3;
        while (true) {
            List list2;
            List list3;
            List list4;
            if (ys1.isEmpty() || ys2.isEmpty() || ys3.isEmpty()) {
                return true;
            }
            if (!BoxesRunTime.unboxToBoolean(f.apply(list4.head(), list3.head(), list2.head()))) break;
            list4 = (List)list4.tail();
            list3 = (List)list3.tail();
            list2 = (List)list2.tail();
        }
        return false;
    }

    public static final Option sequence(Collections $this, List as) {
        return as.exists(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Option<A> x$7) {
                return x$7.isEmpty();
            }
        }) ? None$.MODULE$ : new Some<GenTraversable>(as.flatten((Function1)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Iterable<A> apply(Option<A> xo) {
                return Option$.MODULE$.option2Iterable(xo);
            }
        })));
    }

    public static final Option transposeSafe(Collections $this, List ass) {
        Option option;
        try {
            option = new Some<GenTraversable>(ass.transpose((Function1)Predef$.MODULE$.$conforms()));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            option = None$.MODULE$;
        }
        return option;
    }

    private static final Option loop$1(Collections $this, List rest, PartialFunction pf$1) {
        while (true) {
            $colon$colon $colon$colon;
            boolean bl;
            block5: {
                Option option;
                block4: {
                    block3: {
                        bl = false;
                        $colon$colon = null;
                        if (!((Object)Nil$.MODULE$).equals(rest)) break block3;
                        option = None$.MODULE$;
                        break block4;
                    }
                    if (!(rest instanceof $colon$colon)) break block5;
                    bl = true;
                    $colon$colon = ($colon$colon)rest;
                    if (!pf$1.isDefinedAt($colon$colon.head())) break block5;
                    option = new Some(pf$1.apply($colon$colon.head()));
                }
                return option;
            }
            if (!bl) break;
            rest = $colon$colon.tl$1();
        }
        throw new MatchError(rest);
    }

    private static final List loop$2(Collections $this, ListBuffer mapped, List unchanged, List pending0, List pending1, Function2 f$4) {
        while (!pending0.isEmpty() && !pending1.isEmpty()) {
            List tail1;
            Object head01;
            Object head00 = pending0.head();
            Object head1 = f$4.apply(head00, head01 = pending1.head());
            if (head1 == head00) {
                pending1 = (List)pending1.tail();
                pending0 = (List)pending0.tail();
                continue;
            }
            ListBuffer b = mapped == null ? new ListBuffer() : mapped;
            for (List xc = unchanged; xc != pending0 && xc != pending1; xc = (List)xc.tail()) {
                b.$plus$eq(xc.head());
            }
            b.$plus$eq(head1);
            List tail0 = (List)pending0.tail();
            pending1 = tail1 = (List)pending1.tail();
            pending0 = tail0;
            unchanged = tail0;
            mapped = b;
        }
        return mapped == null ? unchanged : mapped.prependToList(unchanged);
    }

    public static void $init$(Collections $this) {
    }
}

