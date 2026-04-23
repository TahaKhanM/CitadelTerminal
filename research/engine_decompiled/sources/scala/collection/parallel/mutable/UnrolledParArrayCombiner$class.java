/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.mutable.DoublingUnrolledBuffer;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.UnrolledParArrayCombiner;
import scala.collection.parallel.package$;
import scala.reflect.ClassTag$;

public abstract class UnrolledParArrayCombiner$class {
    public static UnrolledParArrayCombiner $plus$eq(UnrolledParArrayCombiner $this, Object elem) {
        $this.buff().$plus$eq(elem);
        return $this;
    }

    public static ParArray result(UnrolledParArrayCombiner $this) {
        ArraySeq arrayseq = new ArraySeq($this.size());
        Object[] array = arrayseq.array();
        $this.combinerTaskSupport().executeAndWaitResult(new UnrolledParArrayCombiner.CopyUnrolledToArray($this, array, 0, $this.size()));
        return new ParArray(arrayseq);
    }

    public static void clear(UnrolledParArrayCombiner $this) {
        $this.buff().clear();
    }

    public static void sizeHint(UnrolledParArrayCombiner $this, int sz) {
        $this.buff().lastPtr().next_$eq(new UnrolledBuffer.Unrolled<Object>(0, new Object[sz], null, $this.buff(), ClassTag$.MODULE$.Any()));
        $this.buff().lastPtr_$eq($this.buff().lastPtr().next());
    }

    public static Combiner combine(UnrolledParArrayCombiner $this, Combiner other) {
        block3: {
            block2: {
                if (other == $this) break block2;
                if (!(other instanceof UnrolledParArrayCombiner)) break block3;
                UnrolledParArrayCombiner unrolledParArrayCombiner = (UnrolledParArrayCombiner)other;
                $this.buff().concat(unrolledParArrayCombiner.buff());
            }
            return $this;
        }
        throw package$.MODULE$.unsupportedop("Cannot combine with combiner of different type.");
    }

    public static int size(UnrolledParArrayCombiner $this) {
        return $this.buff().size();
    }

    public static void $init$(UnrolledParArrayCombiner $this) {
        $this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(new DoublingUnrolledBuffer<Object>(ClassTag$.MODULE$.Any()));
    }
}

