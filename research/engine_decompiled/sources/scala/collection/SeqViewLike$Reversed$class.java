/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.SeqViewLike;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.runtime.ObjectRef;

public abstract class SeqViewLike$Reversed$class {
    public static Iterator iterator(SeqViewLike.Reversed $this) {
        return SeqViewLike$Reversed$class.createReversedIterator($this);
    }

    public static int length(SeqViewLike.Reversed $this) {
        return $this.scala$collection$SeqViewLike$Reversed$$$outer().length();
    }

    public static Object apply(SeqViewLike.Reversed $this, int idx) {
        return $this.scala$collection$SeqViewLike$Reversed$$$outer().apply($this.length() - 1 - idx);
    }

    public static final String viewIdentifier(SeqViewLike.Reversed $this) {
        return "R";
    }

    private static Iterator createReversedIterator(SeqViewLike.Reversed $this) {
        ObjectRef<Nil$> lst = ObjectRef.create(Nil$.MODULE$);
        $this.scala$collection$SeqViewLike$Reversed$$$outer().foreach(new Serializable($this, lst){
            public static final long serialVersionUID = 0L;
            private final ObjectRef lst$1;

            public final void apply(A elem) {
                this.lst$1.elem = ((List)this.lst$1.elem).$colon$colon(elem);
            }
            {
                this.lst$1 = lst$1;
            }
        });
        return ((List)lst.elem).iterator();
    }

    public static void $init$(SeqViewLike.Reversed $this) {
    }
}

