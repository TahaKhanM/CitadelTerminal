/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversableOnce;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.script.End$;
import scala.collection.script.Include;
import scala.collection.script.Index;
import scala.collection.script.Message;
import scala.collection.script.NoLo$;
import scala.collection.script.Remove;
import scala.collection.script.Reset;
import scala.collection.script.Script;
import scala.collection.script.Start$;
import scala.collection.script.Update;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

public abstract class BufferLike$class {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void remove(Buffer $this, int n, int count2) {
        Predef$ predef$ = Predef$.MODULE$;
        Range$ range$ = Range$.MODULE$;
        Range range2 = new Range(0, count2, 1);
        if (range2.isEmpty()) return;
        int i1 = range2.start();
        while (true) {
            $this.remove(n);
            if (i1 == range2.lastElement()) {
                return;
            }
            i1 += range2.step();
        }
    }

    public static Buffer $minus$eq(Buffer $this, Object x) {
        int i = $this.indexOf(x);
        BoxedUnit boxedUnit = i != -1 ? $this.remove(i) : BoxedUnit.UNIT;
        return $this;
    }

    public static Buffer $plus$plus$eq$colon(Buffer $this, TraversableOnce xs) {
        $this.insertAll(0, xs.toTraversable());
        return $this;
    }

    public static void append(Buffer $this, Seq elems) {
        $this.appendAll(elems);
    }

    public static void appendAll(Buffer $this, TraversableOnce xs) {
        $this.$plus$plus$eq(xs);
    }

    public static void prepend(Buffer $this, Seq elems) {
        $this.prependAll(elems);
    }

    public static void prependAll(Buffer $this, TraversableOnce xs) {
        $this.$plus$plus$eq$colon(xs);
    }

    public static void insert(Buffer $this, int n, Seq elems) {
        $this.insertAll(n, elems);
    }

    public static void trimStart(Buffer $this, int n) {
        $this.remove(0, n);
    }

    public static void trimEnd(Buffer $this, int n) {
        int n2 = $this.length() - n;
        Predef$ predef$ = Predef$.MODULE$;
        $this.remove(RichInt$.MODULE$.max$extension(n2, 0), n);
    }

    public static void $less$less(Buffer $this, Message cmd) {
        block20: {
            block8: {
                block19: {
                    block18: {
                        Remove remove;
                        boolean bl;
                        block17: {
                            block16: {
                                block15: {
                                    block14: {
                                        Update update2;
                                        boolean bl2;
                                        block13: {
                                            block12: {
                                                block11: {
                                                    Include include;
                                                    boolean bl3;
                                                    block10: {
                                                        block9: {
                                                            block7: {
                                                                bl3 = false;
                                                                include = null;
                                                                bl2 = false;
                                                                update2 = null;
                                                                bl = false;
                                                                remove = null;
                                                                if (!(cmd instanceof Include)) break block7;
                                                                bl3 = true;
                                                                include = (Include)cmd;
                                                                if (!Start$.MODULE$.equals(include.location())) break block7;
                                                                $this.prepend(Predef$.MODULE$.genericWrapArray(new Object[]{include.elem()}));
                                                                break block8;
                                                            }
                                                            if (!bl3 || !End$.MODULE$.equals(include.location())) break block9;
                                                            $this.append(Predef$.MODULE$.genericWrapArray(new Object[]{include.elem()}));
                                                            break block8;
                                                        }
                                                        if (!bl3 || !(include.location() instanceof Index)) break block10;
                                                        Index index = (Index)include.location();
                                                        $this.insert(index.n(), Predef$.MODULE$.genericWrapArray(new Object[]{include.elem()}));
                                                        break block8;
                                                    }
                                                    if (!bl3 || !NoLo$.MODULE$.equals(include.location())) break block11;
                                                    $this.$plus$eq(include.elem());
                                                    break block8;
                                                }
                                                if (!(cmd instanceof Update)) break block12;
                                                bl2 = true;
                                                update2 = (Update)cmd;
                                                if (!Start$.MODULE$.equals(update2.location())) break block12;
                                                $this.update(0, update2.elem());
                                                break block8;
                                            }
                                            if (!bl2 || !End$.MODULE$.equals(update2.location())) break block13;
                                            $this.update($this.length() - 1, update2.elem());
                                            break block8;
                                        }
                                        if (!bl2 || !(update2.location() instanceof Index)) break block14;
                                        Index index = (Index)update2.location();
                                        $this.update(index.n(), update2.elem());
                                        break block8;
                                    }
                                    if (!(cmd instanceof Remove)) break block15;
                                    bl = true;
                                    remove = (Remove)cmd;
                                    if (!Start$.MODULE$.equals(remove.location())) break block15;
                                    Object a = remove.elem();
                                    Object r = $this.apply(BoxesRunTime.boxToInteger(0));
                                    if (r == a ? true : (r == null ? false : (r instanceof Number ? BoxesRunTime.equalsNumObject((Number)r, a) : (r instanceof Character ? BoxesRunTime.equalsCharObject((Character)r, a) : r.equals(a))))) {
                                        $this.remove(0);
                                    }
                                    break block8;
                                }
                                if (!bl || !End$.MODULE$.equals(remove.location())) break block16;
                                Object a = remove.elem();
                                Object r = $this.apply(BoxesRunTime.boxToInteger($this.length() - 1));
                                if (r == a ? true : (r == null ? false : (r instanceof Number ? BoxesRunTime.equalsNumObject((Number)r, a) : (r instanceof Character ? BoxesRunTime.equalsCharObject((Character)r, a) : r.equals(a))))) {
                                    $this.remove($this.length() - 1);
                                }
                                break block8;
                            }
                            if (!bl || !(remove.location() instanceof Index)) break block17;
                            Index index = (Index)remove.location();
                            Object a = remove.elem();
                            Object r = $this.apply(BoxesRunTime.boxToInteger(index.n()));
                            if (r == a ? true : (r == null ? false : (r instanceof Number ? BoxesRunTime.equalsNumObject((Number)r, a) : (r instanceof Character ? BoxesRunTime.equalsCharObject((Character)r, a) : r.equals(a))))) {
                                $this.remove(index.n());
                            }
                            break block8;
                        }
                        if (!bl || !NoLo$.MODULE$.equals(remove.location())) break block18;
                        $this.$minus$eq(remove.elem());
                        break block8;
                    }
                    if (!(cmd instanceof Reset)) break block19;
                    $this.clear();
                    break block8;
                }
                if (!(cmd instanceof Script)) break block20;
                Script script = (Script)cmd;
                script.iterator().foreach(new Serializable($this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Buffer $outer;

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

    public static String stringPrefix(Buffer $this) {
        return "Buffer";
    }

    public static Seq readOnly(Buffer $this) {
        return $this.toSeq();
    }

    public static Buffer $plus$plus(Buffer $this, GenTraversableOnce xs) {
        return (Buffer)$this.clone().$plus$plus$eq(xs.seq());
    }

    public static Buffer $minus(Buffer $this, Object elem) {
        return $this.clone().$minus$eq(elem);
    }

    public static Buffer $minus(Buffer $this, Object elem1, Object elem2, Seq elems) {
        return (Buffer)$this.clone().$minus$eq(elem1).$minus$eq((Object)elem2).$minus$minus$eq(elems);
    }

    public static Buffer $minus$minus(Buffer $this, GenTraversableOnce xs) {
        return (Buffer)$this.clone().$minus$minus$eq(xs.seq());
    }

    public static Buffer clone(Buffer $this) {
        Builder bf = $this.newBuilder();
        bf.$plus$plus$eq($this);
        return (Buffer)bf.result();
    }

    public static void $init$(Buffer $this) {
    }
}

