/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.collection.mutable.ObservableSet;
import scala.collection.mutable.Undoable;

public abstract class ObservableSet$class {
    public static ObservableSet $plus$eq(ObservableSet $this, Object elem) {
        if (!$this.contains(elem)) {
            $this.scala$collection$mutable$ObservableSet$$super$$plus$eq(elem);
            $this.publish(new Undoable($this, elem){
                private final /* synthetic */ ObservableSet $outer;

                public void undo() {
                    this.$outer.$minus$eq(this.elem());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    super(elem$1);
                }
            });
        }
        return $this;
    }

    public static ObservableSet $minus$eq(ObservableSet $this, Object elem) {
        if ($this.contains(elem)) {
            $this.scala$collection$mutable$ObservableSet$$super$$minus$eq(elem);
            $this.publish(new Undoable($this, elem){
                private final /* synthetic */ ObservableSet $outer;

                public void undo() {
                    this.$outer.$plus$eq(this.elem());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    super(elem$2);
                }
            });
        }
        return $this;
    }

    public static void clear(ObservableSet $this) {
        $this.scala$collection$mutable$ObservableSet$$super$clear();
        $this.publish(new Undoable($this){

            public void undo() {
                throw new UnsupportedOperationException("cannot undo");
            }
        });
    }

    public static void $init$(ObservableSet $this) {
    }
}

