/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.ViewMkString;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;

public abstract class ViewMkString$class {
    public static Seq thisSeq(ViewMkString $this) {
        return ((ArrayBuffer)new ArrayBuffer().$plus$plus$eq((TraversableOnce)((Object)$this))).result();
    }

    public static String mkString(ViewMkString $this) {
        return ((TraversableOnce)((Object)$this)).mkString("");
    }

    public static String mkString(ViewMkString $this, String sep) {
        return ((TraversableOnce)((Object)$this)).mkString("", sep, "");
    }

    public static String mkString(ViewMkString $this, String start, String sep, String end) {
        return $this.thisSeq().addString(new StringBuilder(), start, sep, end).toString();
    }

    public static StringBuilder addString(ViewMkString $this, StringBuilder b, String start, String sep, String end) {
        BooleanRef first = BooleanRef.create(true);
        b.append(start);
        ((GenericTraversableTemplate)((Object)$this)).foreach(new Serializable($this, first, b, sep){
            public static final long serialVersionUID = 0L;
            private final BooleanRef first$1;
            private final StringBuilder b$1;
            private final String sep$1;

            public final StringBuilder apply(A x) {
                java.io.Serializable serializable;
                if (this.first$1.elem) {
                    this.first$1.elem = false;
                    serializable = BoxedUnit.UNIT;
                } else {
                    serializable = this.b$1.append(this.sep$1);
                }
                return this.b$1.append(x);
            }
            {
                void var4_4;
                void var3_3;
                this.first$1 = first$1;
                this.b$1 = var3_3;
                this.sep$1 = var4_4;
            }
        });
        b.append(end);
        return b;
    }

    public static void $init$(ViewMkString $this) {
    }
}

