/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import scala.Console$;
import scala.Serializable;
import scala.collection.JavaConversions$;

public final class Debug$ {
    public static final Debug$ MODULE$;
    private ConcurrentLinkedQueue<Object> logbuffer;
    private volatile boolean bitmap$0;

    static {
        new Debug$();
    }

    private ConcurrentLinkedQueue logbuffer$lzycompute() {
        Debug$ debug$ = this;
        synchronized (debug$) {
            if (!this.bitmap$0) {
                this.logbuffer = new ConcurrentLinkedQueue();
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.logbuffer;
        }
    }

    public ConcurrentLinkedQueue<Object> logbuffer() {
        return this.bitmap$0 ? this.logbuffer : this.logbuffer$lzycompute();
    }

    public boolean log(Object s2) {
        return this.logbuffer().add(s2);
    }

    public void flush() {
        JavaConversions$.MODULE$.asScalaIterator(this.logbuffer().iterator()).foreach(new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply(Object s2) {
                Console$.MODULE$.out().println(s2.toString());
            }
        });
        this.logbuffer().clear();
    }

    public void clear() {
        this.logbuffer().clear();
    }

    private Debug$() {
        MODULE$ = this;
    }
}

