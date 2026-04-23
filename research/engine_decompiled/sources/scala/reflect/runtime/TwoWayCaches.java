/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ScalaSignature;
import scala.reflect.runtime.SymbolTable;
import scala.reflect.runtime.TwoWayCaches$TwoWayCache$;
import scala.reflect.runtime.TwoWayCaches$TwoWayCache$SomeRef$;

@ScalaSignature(bytes="\u0006\u0001\u0005-aAC\u0001\u0003!\u0003\r\tA\u0001\u0005\u0002\u0004\taAk^8XCf\u001c\u0015m\u00195fg*\u00111\u0001B\u0001\beVtG/[7f\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006\u001c\"\u0001A\u0005\u0011\u0005)YQ\"\u0001\u0004\n\u000511!AB!osJ+g\rC\u0003\u000f\u0001\u0011\u0005\u0001#\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\t\u0002C\u0001\u0006\u0013\u0013\t\u0019bA\u0001\u0003V]&$h\u0001B\u000b\u0001\u0001Y\u00111\u0002V<p/\u0006L8)Y2iKV\u0019qcH\u0015\u0014\u0005QI\u0001\"B\r\u0015\t\u0003Q\u0012A\u0002\u001fj]&$h\bF\u0001\u001c!\u0011aB#\b\u0015\u000e\u0003\u0001\u0001\"AH\u0010\r\u0001\u0011)\u0001\u0005\u0006b\u0001C\t\t!*\u0005\u0002#KA\u0011!bI\u0005\u0003I\u0019\u0011qAT8uQ&tw\r\u0005\u0002\u000bM%\u0011qE\u0002\u0002\u0004\u0003:L\bC\u0001\u0010*\t\u0015QCC1\u0001\"\u0005\u0005\u0019\u0006b\u0002\u0017\u0015\u0005\u0004%I!L\u0001\u000bi>\u001c6-\u00197b\u001b\u0006\u0004X#\u0001\u0018\u0011\t=\"TDN\u0007\u0002a)\u0011\u0011GM\u0001\b[V$\u0018M\u00197f\u0015\t\u0019d!\u0001\u0006d_2dWm\u0019;j_:L!!\u000e\u0019\u0003\u0017]+\u0017m\u001b%bg\"l\u0015\r\u001d\t\u0004oyBS\"\u0001\u001d\u000b\u0005eR\u0014a\u0001:fM*\u00111\bP\u0001\u0005Y\u0006twMC\u0001>\u0003\u0011Q\u0017M^1\n\u0005}B$!D,fC.\u0014VMZ3sK:\u001cW\r\u0003\u0004B)\u0001\u0006IAL\u0001\fi>\u001c6-\u00197b\u001b\u0006\u0004\b\u0005C\u0004D)\t\u0007I\u0011\u0002#\u0002\u0013Q|'*\u0019<b\u001b\u0006\u0004X#A#\u0011\t=\"\u0004F\u0012\t\u0004oyj\u0002B\u0002%\u0015A\u0003%Q)\u0001\u0006u_*\u000bg/Y'ba\u0002BQA\u0013\u000b\u0005\u0002-\u000bQ!\u001a8uKJ$2!\u0005'O\u0011\u0015i\u0015\n1\u0001\u001e\u0003\u0005Q\u0007\"B(J\u0001\u0004A\u0013!A:\b\u000bE#\u0002\u0012\u0002*\u0002\u000fM{W.\u001a*fMB\u00111\u000bV\u0007\u0002)\u0019)Q\u000b\u0006E\u0005-\n91k\\7f%\u001647C\u0001+\n\u0011\u0015IB\u000b\"\u0001Y)\u0005\u0011\u0006\"\u0002.U\t\u0003Y\u0016aB;oCB\u0004H._\u000b\u00039\u0006$\"!X2\u0011\u0007)q\u0006-\u0003\u0002`\r\t1q\n\u001d;j_:\u0004\"AH1\u0005\u000b\tL&\u0019A\u0011\u0003\u0003QCQ\u0001Z-A\u0002\u0015\faa\u001c9u%\u00164\u0007c\u0001\u0006_MB\u0019qG\u00101\t\u000b!$B\u0011A5\u0002\u000fQ|7kY1mCR\u0011!\u000e\u001d\u000b\u0003Q-Da\u0001\\4\u0005\u0002\u0004i\u0017\u0001\u00022pIf\u00042A\u00038)\u0013\tygA\u0001\u0005=Eft\u0017-\\3?\u0011\u0015\tx\r1\u0001\u001e\u0003\rYW-\u001f\u0005\u0006gR!\t\u0001^\u0001\u0007i>T\u0015M^1\u0015\u0005UDHCA\u000fw\u0011\u0019a'\u000f\"a\u0001oB\u0019!B\\\u000f\t\u000bE\u0014\b\u0019\u0001\u0015\t\u000bi$B\u0011A>\u0002\u0019Q|'*\u0019<b\u001fB$\u0018n\u001c8\u0015\u0007q\f\t\u0001\u0006\u0002~}B\u0019!BX\u000f\t\r1LH\u00111\u0001\u0000!\rQa. \u0005\u0006cf\u0004\r\u0001\u000b\t\u0005\u0003\u000b\t9!D\u0001\u0003\u0013\r\tIA\u0001\u0002\f'fl'm\u001c7UC\ndW\r")
public interface TwoWayCaches {

    public static class TwoWayCache<J, S> {
        private final WeakHashMap<J, WeakReference<S>> scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap;
        private final WeakHashMap<S, WeakReference<J>> scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap;
        private volatile TwoWayCaches$TwoWayCache$SomeRef$ SomeRef$module;
        public final /* synthetic */ SymbolTable $outer;

        private TwoWayCaches$TwoWayCache$SomeRef$ scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef$lzycompute() {
            TwoWayCache twoWayCache = this;
            synchronized (twoWayCache) {
                if (this.SomeRef$module == null) {
                    this.SomeRef$module = new TwoWayCaches$TwoWayCache$SomeRef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SomeRef$module;
            }
        }

        public WeakHashMap<J, WeakReference<S>> scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap() {
            return this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap;
        }

        public WeakHashMap<S, WeakReference<J>> scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap() {
            return this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap;
        }

        public void enter(J j, S s2) {
            this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer().gilSynchronized(new Serializable(this, j, s2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TwoWayCache $outer;
                private final Object j$1;
                private final Object s$1;

                public final void apply() {
                    this.apply$mcV$sp();
                }

                public void apply$mcV$sp() {
                    this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap().update(this.j$1, new WeakReference<Object>(this.s$1));
                    this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap().update(this.s$1, new WeakReference<Object>(this.j$1));
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.j$1 = j$1;
                    this.s$1 = var3_3;
                }
            });
        }

        public TwoWayCaches$TwoWayCache$SomeRef$ scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef() {
            return this.SomeRef$module == null ? this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef$lzycompute() : this.SomeRef$module;
        }

        public S toScala(J key, Function0<S> body2) {
            return (S)this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer().gilSynchronized(new Serializable(this, key, body2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TwoWayCache $outer;
                private final Object key$1;
                private final Function0 body$1;

                public final S apply() {
                    Object object;
                    Option<WeakReference<T>> option = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap().get(this.key$1);
                    Option<T> option2 = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef().unapply(option);
                    if (option2.isEmpty()) {
                        R result2 = this.body$1.apply();
                        this.$outer.enter(this.key$1, result2);
                        object = result2;
                    } else {
                        object = option2.get();
                    }
                    return (S)object;
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.key$1 = key$1;
                    this.body$1 = var3_3;
                }
            });
        }

        public J toJava(S key, Function0<J> body2) {
            return (J)this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer().gilSynchronized(new Serializable(this, key, body2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TwoWayCache $outer;
                private final Object key$2;
                private final Function0 body$2;

                public final J apply() {
                    Object object;
                    Option<WeakReference<T>> option = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap().get(this.key$2);
                    Option<T> option2 = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef().unapply(option);
                    if (option2.isEmpty()) {
                        R result2 = this.body$2.apply();
                        this.$outer.enter(result2, this.key$2);
                        object = result2;
                    } else {
                        object = option2.get();
                    }
                    return (J)object;
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.key$2 = key$2;
                    this.body$2 = var3_3;
                }
            });
        }

        public Option<J> toJavaOption(S key, Function0<Option<J>> body2) {
            return (Option)this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer().gilSynchronized(new Serializable(this, key, body2){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ TwoWayCache $outer;
                public final Object key$3;
                private final Function0 body$3;

                public final Option<J> apply() {
                    Option option;
                    Option<WeakReference<T>> option2 = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap().get(this.key$3);
                    Option<T> option3 = this.$outer.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$SomeRef().unapply(option2);
                    if (option3.isEmpty()) {
                        Option result2 = (Option)this.body$3.apply();
                        Serializable serializable = new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TwoWayCache$$anonfun$toJavaOption$1 $outer;

                            public final void apply(J value) {
                                this.$outer.$outer.enter(value, this.$outer.key$3);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        };
                        if (!result2.isEmpty()) {
                            A a = result2.get();
                            serializable.apply(a);
                        }
                        option = result2;
                    } else {
                        option = new Some<T>(option3.get());
                    }
                    return option;
                }

                public /* synthetic */ TwoWayCache scala$reflect$runtime$TwoWayCaches$TwoWayCache$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    void var3_3;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.key$3 = key$3;
                    this.body$3 = var3_3;
                }
            });
        }

        public /* synthetic */ SymbolTable scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer() {
            return this.$outer;
        }

        public TwoWayCache(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toScalaMap = new WeakHashMap();
            this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$toJavaMap = new WeakHashMap();
        }
    }
}

