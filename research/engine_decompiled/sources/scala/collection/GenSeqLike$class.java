/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Serializable;
import scala.collection.GenSeq;
import scala.collection.GenSeqLike;
import scala.collection.generic.CanBuildFrom;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.MurmurHash3$;

public abstract class GenSeqLike$class {
    public static boolean isDefinedAt(GenSeqLike $this, int idx) {
        return idx >= 0 && idx < $this.length();
    }

    public static int prefixLength(GenSeqLike $this, Function1 p) {
        return $this.segmentLength(p, 0);
    }

    public static int indexWhere(GenSeqLike $this, Function1 p) {
        return $this.indexWhere(p, 0);
    }

    public static int indexOf(GenSeqLike $this, Object elem) {
        return $this.indexOf(elem, 0);
    }

    public static int indexOf(GenSeqLike $this, Object elem, int from2) {
        return $this.indexWhere(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$1;

            public final boolean apply(A x$1) {
                Object object = this.elem$1;
                return object == x$1 ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, x$1) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, x$1) : object.equals(x$1))));
            }
            {
                this.elem$1 = elem$1;
            }
        }, from2);
    }

    public static int lastIndexOf(GenSeqLike $this, Object elem) {
        return $this.lastIndexWhere(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$2;

            public final boolean apply(A x$2) {
                Object object = this.elem$2;
                return object == x$2 ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, x$2) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, x$2) : object.equals(x$2))));
            }
            {
                this.elem$2 = elem$2;
            }
        });
    }

    public static int lastIndexOf(GenSeqLike $this, Object elem, int end) {
        return $this.lastIndexWhere(new Serializable($this, elem){
            public static final long serialVersionUID = 0L;
            private final Object elem$3;

            public final boolean apply(A x$3) {
                Object object = this.elem$3;
                return object == x$3 ? true : (object == null ? false : (object instanceof Number ? BoxesRunTime.equalsNumObject((Number)object, x$3) : (object instanceof Character ? BoxesRunTime.equalsCharObject((Character)object, x$3) : object.equals(x$3))));
            }
            {
                this.elem$3 = elem$3;
            }
        }, end);
    }

    public static int lastIndexWhere(GenSeqLike $this, Function1 p) {
        return $this.lastIndexWhere(p, $this.length() - 1);
    }

    public static boolean startsWith(GenSeqLike $this, GenSeq that) {
        return $this.startsWith(that, 0);
    }

    public static Object union(GenSeqLike $this, GenSeq that, CanBuildFrom bf) {
        return $this.$plus$plus(that, bf);
    }

    public static int hashCode(GenSeqLike $this) {
        return MurmurHash3$.MODULE$.seqHash($this.seq());
    }

    public static boolean equals(GenSeqLike $this, Object that) {
        GenSeq genSeq;
        boolean bl = that instanceof GenSeq ? (genSeq = (GenSeq)that).canEqual($this) && $this.sameElements(genSeq) : false;
        return bl;
    }

    public static void $init$(GenSeqLike $this) {
    }
}

