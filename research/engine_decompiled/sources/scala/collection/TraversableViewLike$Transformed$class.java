/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.TraversableViewLike;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BooleanRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public abstract class TraversableViewLike$Transformed$class {
    public static Object underlying(TraversableViewLike.Transformed $this) {
        return $this.scala$collection$TraversableViewLike$Transformed$$$outer().underlying();
    }

    public static final String viewIdString(TraversableViewLike.Transformed $this) {
        return new StringBuilder().append((Object)$this.scala$collection$TraversableViewLike$Transformed$$$outer().viewIdString()).append((Object)$this.viewIdentifier()).toString();
    }

    public static Option headOption(TraversableViewLike.Transformed $this) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            Option option;
            Object object = new Object();
            try {
                $this.foreach(new Serializable($this, object){
                    public static final long serialVersionUID = 0L;
                    private final Object nonLocalReturnKey1$1;

                    public final Nothing$ apply(B x) {
                        throw new NonLocalReturnControl<Some<B>>(this.nonLocalReturnKey1$1, new Some<B>(x));
                    }
                    {
                        this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                    }
                });
                option = None$.MODULE$;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                option = (Option)nonLocalReturnControl.value();
            }
            return option;
        }
        throw nonLocalReturnControl;
    }

    public static Option lastOption(TraversableViewLike.Transformed $this) {
        BooleanRef empty = BooleanRef.create(true);
        ObjectRef<Object> result2 = ObjectRef.create(null);
        $this.foreach(new Serializable($this, empty, result2){
            public static final long serialVersionUID = 0L;
            private final BooleanRef empty$1;
            private final ObjectRef result$1;

            public final void apply(B x) {
                this.empty$1.elem = false;
                this.result$1.elem = x;
            }
            {
                void var3_3;
                this.empty$1 = empty$1;
                this.result$1 = var3_3;
            }
        });
        return empty.elem ? None$.MODULE$ : new Some(result2.elem);
    }

    public static String stringPrefix(TraversableViewLike.Transformed $this) {
        return $this.scala$collection$TraversableViewLike$Transformed$$$outer().stringPrefix();
    }

    public static String toString(TraversableViewLike.Transformed $this) {
        return $this.viewToString();
    }

    public static void $init$(TraversableViewLike.Transformed $this) {
    }
}

