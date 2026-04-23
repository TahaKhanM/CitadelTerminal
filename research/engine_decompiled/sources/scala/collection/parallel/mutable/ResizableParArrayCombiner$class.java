/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel.mutable;

import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.mutable.ExposedArrayBuffer;
import scala.collection.parallel.mutable.ExposedArraySeq;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ResizableParArrayCombiner;
import scala.collection.parallel.mutable.ResizableParArrayCombiner$;
import scala.runtime.BoxesRunTime;

public abstract class ResizableParArrayCombiner$class {
    public static void sizeHint(ResizableParArrayCombiner $this, int sz) {
        if ($this.chain().length() == 1) {
            ((ExposedArrayBuffer)$this.chain().apply(0)).sizeHint(sz);
        }
    }

    public static final ResizableParArrayCombiner newLazyCombiner(ResizableParArrayCombiner $this, ArrayBuffer c) {
        return ResizableParArrayCombiner$.MODULE$.apply(c);
    }

    public static ParArray allocateAndCopy(ResizableParArrayCombiner $this) {
        ParArray parArray;
        if ($this.chain().size() > 1) {
            ArraySeq arrayseq = new ArraySeq($this.size());
            Object[] array = arrayseq.array();
            $this.combinerTaskSupport().executeAndWaitResult(new ResizableParArrayCombiner.CopyChainToArray($this, array, 0, $this.size()));
            parArray = new ParArray(arrayseq);
        } else {
            parArray = new ParArray(new ExposedArraySeq(((ExposedArrayBuffer)$this.chain().apply(0)).internalArray(), $this.size()));
        }
        return parArray;
    }

    public static String toString(ResizableParArrayCombiner $this) {
        return new StringBuilder().append((Object)"ResizableParArrayCombiner(").append(BoxesRunTime.boxToInteger($this.size())).append((Object)"): ").toString();
    }

    public static void $init$(ResizableParArrayCombiner $this) {
    }
}

