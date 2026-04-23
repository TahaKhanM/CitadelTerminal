/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.mutable.ObservableMap;
import scala.collection.mutable.Undoable;

public abstract class ObservableMap$class {
    public static ObservableMap $plus$eq(ObservableMap $this, Tuple2 kv) {
        block2: {
            Option option;
            block5: {
                block4: {
                    Object value;
                    Object key;
                    block3: {
                        if (kv == null) break block2;
                        Tuple2 tuple2 = new Tuple2(kv._1(), kv._2());
                        key = tuple2._1();
                        value = tuple2._2();
                        option = $this.get(key);
                        if (!None$.MODULE$.equals(option)) break block3;
                        $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
                        $this.publish(new Undoable($this, key, value){
                            private final /* synthetic */ ObservableMap $outer;
                            private final Object key$1;

                            public void undo() {
                                this.$outer.$minus$eq(this.key$1);
                            }
                            {
                                void var3_3;
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.key$1 = key$1;
                                super(new Tuple2<ObservableMap<A, B>, void>(key$1, var3_3));
                            }
                        });
                        break block4;
                    }
                    if (!(option instanceof Some)) break block5;
                    Some some = (Some)option;
                    $this.scala$collection$mutable$ObservableMap$$super$$plus$eq(kv);
                    $this.publish(new Undoable($this, key, value, some){
                        private final /* synthetic */ ObservableMap $outer;
                        private final Object key$1;
                        private final Some x2$1;

                        public void undo() {
                            this.$outer.$plus$eq(new Tuple2<Object, A>(this.key$1, this.x2$1.x()));
                        }
                        {
                            void var3_3;
                            void var4_4;
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.key$1 = key$1;
                            this.x2$1 = var4_4;
                            super(new Tuple2<ObservableMap<A, B>, void>(key$1, var3_3));
                        }
                    });
                }
                return $this;
            }
            throw new MatchError(option);
        }
        throw new MatchError(kv);
    }

    public static ObservableMap $minus$eq(ObservableMap $this, Object key) {
        Option option;
        block4: {
            block3: {
                block2: {
                    option = $this.get(key);
                    if (!None$.MODULE$.equals(option)) break block2;
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                $this.scala$collection$mutable$ObservableMap$$super$$minus$eq(key);
                $this.publish(new Undoable($this, some, key){
                    private final /* synthetic */ ObservableMap $outer;
                    private final Some x2$2;
                    private final Object key$2;

                    public void undo() {
                        this.$outer.update(this.key$2, this.x2$2.x());
                    }
                    {
                        void var3_3;
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.x2$2 = x2$2;
                        this.key$2 = var3_3;
                        super(new Tuple2<void, A>(var3_3, ((Some)((Object)x2$2)).x()));
                    }
                });
            }
            return $this;
        }
        throw new MatchError(option);
    }

    public static void clear(ObservableMap $this) {
        $this.scala$collection$mutable$ObservableMap$$super$clear();
        $this.publish(new Undoable($this){

            public void undo() {
                throw new UnsupportedOperationException("cannot undo");
            }
        });
    }

    public static void $init$(ObservableMap $this) {
    }
}

