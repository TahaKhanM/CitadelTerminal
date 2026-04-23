/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Option;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ScalaSignature;
import scala.reflect.runtime.TwoWayCache$SomeRef$;

@ScalaSignature(bytes="\u0006\u0001M4Q!\u0001\u0002\u0001\u0005!\u00111\u0002V<p/\u0006L8)Y2iK*\u00111\u0001B\u0001\beVtG/[7f\u0015\t)a!A\u0004sK\u001adWm\u0019;\u000b\u0003\u001d\tQa]2bY\u0006,2!\u0003\f!'\t\u0001!\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001aDQa\u0004\u0001\u0005\u0002E\ta\u0001P5oSRt4\u0001\u0001\u000b\u0002%A!1\u0003\u0001\u000b \u001b\u0005\u0011\u0001CA\u000b\u0017\u0019\u0001!Qa\u0006\u0001C\u0002a\u0011\u0011AS\t\u00033q\u0001\"a\u0003\u000e\n\u0005m1!a\u0002(pi\"Lgn\u001a\t\u0003\u0017uI!A\b\u0004\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0016A\u0011)\u0011\u0005\u0001b\u00011\t\t1\u000bC\u0004$\u0001\t\u0007I\u0011\u0002\u0013\u0002\u0015Q|7kY1mC6\u000b\u0007/F\u0001&!\u001113\u0006F\u0017\u000e\u0003\u001dR!\u0001K\u0015\u0002\u000f5,H/\u00192mK*\u0011!FB\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u0017(\u0005-9V-Y6ICNDW*\u00199\u0011\u00079*t$D\u00010\u0015\t\u0001\u0014'A\u0002sK\u001aT!AM\u001a\u0002\t1\fgn\u001a\u0006\u0002i\u0005!!.\u0019<b\u0013\t1tFA\u0007XK\u0006\\'+\u001a4fe\u0016t7-\u001a\u0005\u0007q\u0001\u0001\u000b\u0011B\u0013\u0002\u0017Q|7kY1mC6\u000b\u0007\u000f\t\u0005\bu\u0001\u0011\r\u0011\"\u0003<\u0003%!xNS1wC6\u000b\u0007/F\u0001=!\u001113fH\u001f\u0011\u00079*D\u0003\u0003\u0004@\u0001\u0001\u0006I\u0001P\u0001\u000bi>T\u0015M^1NCB\u0004\u0003\"B!\u0001\t\u0003\u0011\u0015!B3oi\u0016\u0014HcA\"G\u0011B\u00111\u0002R\u0005\u0003\u000b\u001a\u0011A!\u00168ji\")q\t\u0011a\u0001)\u0005\t!\u000eC\u0003J\u0001\u0002\u0007q$A\u0001t\u000f\u0015Y\u0005\u0001#\u0003M\u0003\u001d\u0019v.\\3SK\u001a\u0004\"!\u0014(\u000e\u0003\u00011Qa\u0014\u0001\t\nA\u0013qaU8nKJ+gm\u0005\u0002O\u0015!)qB\u0014C\u0001%R\tA\nC\u0003U\u001d\u0012\u0005Q+A\u0004v]\u0006\u0004\b\u000f\\=\u0016\u0005Y[FCA,^!\rY\u0001LW\u0005\u00033\u001a\u0011aa\u00149uS>t\u0007CA\u000b\\\t\u0015a6K1\u0001\u0019\u0005\u0005!\u0006\"\u00020T\u0001\u0004y\u0016AB8qiJ+g\rE\u0002\f1\u0002\u00042AL\u001b[\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0003\u001d!xnU2bY\u0006$\"\u0001\u001a6\u0015\u0005})\u0007B\u00024b\t\u0003\u0007q-\u0001\u0003c_\u0012L\bcA\u0006i?%\u0011\u0011N\u0002\u0002\ty\tLh.Y7f}!)1.\u0019a\u0001)\u0005\u00191.Z=\t\u000b5\u0004A\u0011\u00018\u0002\rQ|'*\u0019<b)\ty'\u000f\u0006\u0002\u0015a\"1a\r\u001cCA\u0002E\u00042a\u00035\u0015\u0011\u0015YG\u000e1\u0001 \u0001")
public class TwoWayCache<J, S> {
    private final WeakHashMap<J, WeakReference<S>> toScalaMap = new WeakHashMap();
    private final WeakHashMap<S, WeakReference<J>> toJavaMap = new WeakHashMap();
    private volatile TwoWayCache$SomeRef$ SomeRef$module;

    private TwoWayCache$SomeRef$ SomeRef$lzycompute() {
        TwoWayCache twoWayCache = this;
        synchronized (twoWayCache) {
            if (this.SomeRef$module == null) {
                this.SomeRef$module = new TwoWayCache$SomeRef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SomeRef$module;
        }
    }

    private WeakHashMap<J, WeakReference<S>> toScalaMap() {
        return this.toScalaMap;
    }

    private WeakHashMap<S, WeakReference<J>> toJavaMap() {
        return this.toJavaMap;
    }

    public synchronized void enter(J j, S s2) {
        this.toScalaMap().update(j, new WeakReference<S>(s2));
        this.toJavaMap().update(s2, new WeakReference<J>(j));
    }

    private TwoWayCache$SomeRef$ SomeRef() {
        return this.SomeRef$module == null ? this.SomeRef$lzycompute() : this.SomeRef$module;
    }

    public synchronized S toScala(J key, Function0<S> body2) {
        Object object;
        Option option = this.toScalaMap().get(key);
        Option option2 = this.SomeRef().unapply(option);
        if (option2.isEmpty()) {
            S result2 = body2.apply();
            this.enter(key, result2);
            object = result2;
        } else {
            object = option2.get();
        }
        return (S)object;
    }

    public synchronized J toJava(S key, Function0<J> body2) {
        Object object;
        Option option = this.toJavaMap().get(key);
        Option option2 = this.SomeRef().unapply(option);
        if (option2.isEmpty()) {
            J result2 = body2.apply();
            this.enter(result2, key);
            object = result2;
        } else {
            object = option2.get();
        }
        return (J)object;
    }
}

