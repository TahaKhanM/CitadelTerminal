/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001U3Q!\u0001\u0002\u0002\u0002-\u00111aU3u\u0015\t\u0019A!\u0001\u0003vi&d'BA\u0003\u0007\u0003!Ig\u000e^3s]\u0006d'BA\u0004\t\u0003\u001d\u0011XM\u001a7fGRT\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\ta\u0001d\u0005\u0002\u0001\u001bA\u0011abD\u0007\u0002\u0011%\u0011\u0001\u0003\u0003\u0002\u0007\u0003:L(+\u001a4\t\u000bI\u0001A\u0011A\n\u0002\rqJg.\u001b;?)\u0005!\u0002cA\u000b\u0001-5\t!\u0001\u0005\u0002\u001811\u0001A!B\r\u0001\u0005\u0004Q\"!\u0001+\u0012\u0005mi\u0001C\u0001\b\u001d\u0013\ti\u0002BA\u0004O_RD\u0017N\\4\t\u000b}\u0001a\u0011\u0001\u0011\u0002\u0013\u0019Lg\u000eZ#oiJLHC\u0001\f\"\u0011\u0015\u0011c\u00041\u0001\u0017\u0003\u0005A\b\"\u0002\u0013\u0001\r\u0003)\u0013\u0001C1eI\u0016sGO]=\u0015\u0005\u0019J\u0003C\u0001\b(\u0013\tA\u0003B\u0001\u0003V]&$\b\"\u0002\u0012$\u0001\u00041\u0002\"B\u0016\u0001\r\u0003a\u0013\u0001C5uKJ\fGo\u001c:\u0016\u00035\u00022AL\u0019\u0017\u001d\tqq&\u0003\u00021\u0011\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001a4\u0005!IE/\u001a:bi>\u0014(B\u0001\u0019\t\u0011\u0015)\u0004\u0001\"\u00017\u0003\u001d1wN]3bG\",\"a\u000e \u0015\u0005\u0019B\u0004\"B\u001d5\u0001\u0004Q\u0014!\u00014\u0011\t9Yd#P\u0005\u0003y!\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005]qD!B 5\u0005\u0004\u0001%!A+\u0012\u0005m\t\u0005C\u0001\bC\u0013\t\u0019\u0005BA\u0002B]fDQ!\u0012\u0001\u0005\u0002\u0019\u000bQ!\u00199qYf$\"a\u0012&\u0011\u00059A\u0015BA%\t\u0005\u001d\u0011un\u001c7fC:DQA\t#A\u0002YAQ\u0001\u0014\u0001\u0005\u00025\u000b\u0001bY8oi\u0006Lgn\u001d\u000b\u0003\u000f:CQAI&A\u0002YAQ\u0001\u0015\u0001\u0005\u0002E\u000ba\u0001^8MSN$X#\u0001*\u0011\u00079\u001af#\u0003\u0002Ug\t!A*[:u\u0001")
public abstract class Set<T> {
    public abstract T findEntry(T var1);

    public abstract void addEntry(T var1);

    public abstract Iterator<T> iterator();

    public <U> void foreach(Function1<T, U> f) {
        this.iterator().foreach(f);
    }

    public boolean apply(T x) {
        return this.contains(x);
    }

    public boolean contains(T x) {
        return this.findEntry(x) != null;
    }

    public List<T> toList() {
        return this.iterator().toList();
    }
}

