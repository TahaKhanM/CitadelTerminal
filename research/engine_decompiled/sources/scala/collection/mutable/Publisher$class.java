/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Function1;
import scala.Serializable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.MultiMap;
import scala.collection.mutable.MultiMap$class;
import scala.collection.mutable.Publisher;
import scala.collection.mutable.Publisher$;
import scala.collection.mutable.Set;
import scala.collection.mutable.Subscriber;
import scala.runtime.BoxesRunTime;

public abstract class Publisher$class {
    public static void subscribe(Publisher $this, Subscriber sub) {
        $this.subscribe(sub, new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(Evt event) {
                return true;
            }
        });
    }

    public static void subscribe(Publisher $this, Subscriber sub, Function1 filter2) {
        ((MultiMap)((Object)$this.scala$collection$mutable$Publisher$$filters())).addBinding(sub, filter2);
    }

    public static void suspendSubscription(Publisher $this, Subscriber sub) {
        $this.scala$collection$mutable$Publisher$$suspended().$plus$eq(sub);
    }

    public static void activateSubscription(Publisher $this, Subscriber sub) {
        $this.scala$collection$mutable$Publisher$$suspended().$minus$eq(sub);
    }

    public static void removeSubscription(Publisher $this, Subscriber sub) {
        $this.scala$collection$mutable$Publisher$$filters().$minus$eq(sub);
    }

    public static void removeSubscriptions(Publisher $this) {
        $this.scala$collection$mutable$Publisher$$filters().clear();
    }

    public static void publish(Publisher $this, Object event) {
        $this.scala$collection$mutable$Publisher$$filters().keys().foreach(new Serializable($this, event){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Publisher $outer;
            public final Object event$1;

            public final void apply(Subscriber<Evt, Publisher> sub) {
                if (!this.$outer.scala$collection$mutable$Publisher$$suspended().contains(sub) && ((MultiMap)((Object)this.$outer.scala$collection$mutable$Publisher$$filters())).entryExists(sub, new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ Publisher$.anonfun.publish.1 $outer;

                    public final boolean apply(Function1<Evt, Object> p) {
                        return BoxesRunTime.unboxToBoolean(p.apply(this.$outer.event$1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })) {
                    sub.notify(this.$outer.self(), this.event$1);
                }
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.event$1 = event$1;
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean equals(Publisher $this, Object obj) {
        if (!(obj instanceof Publisher)) return false;
        Publisher publisher = (Publisher)obj;
        HashMap hashMap = $this.scala$collection$mutable$Publisher$$filters();
        HashMap hashMap2 = publisher.scala$collection$mutable$Publisher$$filters();
        if (hashMap == null) {
            if (hashMap2 != null) {
                return false;
            }
        } else if (!((Object)hashMap).equals(hashMap2)) return false;
        HashSet hashSet = $this.scala$collection$mutable$Publisher$$suspended();
        HashSet hashSet2 = publisher.scala$collection$mutable$Publisher$$suspended();
        if (hashSet == null) {
            if (hashSet2 == null) return true;
            return false;
        } else {
            if (!((Object)hashSet).equals(hashSet2)) return false;
            return true;
        }
    }

    public static void $init$(Publisher $this) {
        $this.scala$collection$mutable$Publisher$_setter_$self_$eq($this);
        $this.scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$filters_$eq((HashMap)((Object)new MultiMap<Subscriber<Evt, Publisher>, Function1<Evt, Object>>($this){

            public Set<Function1<Evt, Object>> makeSet() {
                return MultiMap$class.makeSet(this);
            }

            public MultiMap addBinding(Object key, Object value) {
                return MultiMap$class.addBinding(this, key, value);
            }

            public MultiMap removeBinding(Object key, Object value) {
                return MultiMap$class.removeBinding(this, key, value);
            }

            public boolean entryExists(Object key, Function1 p) {
                return MultiMap$class.entryExists(this, key, p);
            }
            {
                MultiMap$class.$init$(this);
            }
        }));
        $this.scala$collection$mutable$Publisher$_setter_$scala$collection$mutable$Publisher$$suspended_$eq(new HashSet());
    }
}

