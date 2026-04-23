/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Predef$;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.AbstractIterable;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Subscriber;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001%4A!\u0001\u0002\u0001\u0013\t9\u0001*[:u_JL(BA\u0002\u0005\u0003\u001diW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)2AC\u0010\u0016'\u0015\u00011\"\t\u0013(!\raQbD\u0007\u0002\u0005%\u0011aB\u0001\u0002\u0011\u0003\n\u001cHO]1di&#XM]1cY\u0016\u0004B\u0001E\t\u0014=5\ta!\u0003\u0002\u0013\r\t1A+\u001e9mKJ\u0002\"\u0001F\u000b\r\u0001\u0011)a\u0003\u0001b\u0001/\t\u0019\u0001+\u001e2\u0012\u0005aY\u0002C\u0001\t\u001a\u0013\tQbAA\u0004O_RD\u0017N\\4\u0011\u0005Aa\u0012BA\u000f\u0007\u0005\r\te.\u001f\t\u0003)}!Q\u0001\t\u0001C\u0002]\u00111!\u0012<u!\u0011a!EH\n\n\u0005\r\u0012!AC*vEN\u001c'/\u001b2feB\u0019A\"J\b\n\u0005\u0019\u0012!\u0001C%uKJ\f'\r\\3\u0011\u0005AA\u0013BA\u0015\u0007\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011\u0015Y\u0003\u0001\"\u0001-\u0003\u0019a\u0014N\\5u}Q\tQ\u0006\u0005\u0003\r\u0001y\u0019\u0002bB\u0018\u0001\u0005\u0004%\t\u0002M\u0001\u0004Y><W#A\u0019\u0011\u00071\u0011t\"\u0003\u00024\u0005\t)\u0011+^3vK\"1Q\u0007\u0001Q\u0001\nE\nA\u0001\\8hA!9q\u0007\u0001b\u0001\n\u0003A\u0014AC7bq\"K7\u000f^8ssV\t\u0011\b\u0005\u0002\u0011u%\u00111H\u0002\u0002\u0004\u0013:$\bBB\u001f\u0001A\u0003%\u0011(A\u0006nCbD\u0015n\u001d;pef\u0004\u0003\"B \u0001\t\u0003\u0001\u0015A\u00028pi&4\u0017\u0010F\u0002B\t\u001a\u0003\"\u0001\u0005\"\n\u0005\r3!\u0001B+oSRDQ!\u0012 A\u0002M\t1\u0001];c\u0011\u00159e\b1\u0001\u001f\u0003\u0015)g/\u001a8u\u0011\u0015I\u0005\u0001\"\u00119\u0003\u0011\u0019\u0018N_3\t\u000b-\u0003A\u0011\u0001'\u0002\u0011%$XM]1u_J,\u0012!\u0014\t\u0004\u001d>{Q\"\u0001\u0003\n\u0005A#!\u0001C%uKJ\fGo\u001c:\t\u000bI\u0003A\u0011A*\u0002\r\u00154XM\u001c;t+\u0005!\u0006c\u0001(P=!)a\u000b\u0001C\u0001/\u0006)1\r\\3beR\t\u0011\tC\u0003Z\u0001\u0011\u0005#,\u0001\u0004fcV\fGn\u001d\u000b\u00037z\u0003\"\u0001\u0005/\n\u0005u3!a\u0002\"p_2,\u0017M\u001c\u0005\u0006?b\u0003\raG\u0001\u0004_\nT\u0007\"B1\u0001\t\u0003\u0012\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003eBC\u0001\u00013hQB\u0011\u0001#Z\u0005\u0003M\u001a\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0011!sw\f\u0005e\u001553\b")
public class History<Evt, Pub>
extends AbstractIterable<Tuple2<Pub, Evt>>
implements Subscriber<Evt, Pub>,
Serializable {
    public static final long serialVersionUID = 5219213543849892588L;
    private final Queue<Tuple2<Pub, Evt>> log = new Queue();
    private final int maxHistory;

    public Queue<Tuple2<Pub, Evt>> log() {
        return this.log;
    }

    public int maxHistory() {
        return this.maxHistory;
    }

    @Override
    public void notify(Pub pub, Evt event) {
        java.io.Serializable serializable = this.log().length() >= this.maxHistory() ? this.log().dequeue() : BoxedUnit.UNIT;
        this.log().enqueue(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{new Tuple2<Pub, Evt>(pub, event)}));
    }

    @Override
    public int size() {
        return this.log().length();
    }

    @Override
    public Iterator<Tuple2<Pub, Evt>> iterator() {
        return this.log().iterator();
    }

    public Iterator<Evt> events() {
        return this.log().iterator().map(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final Evt apply(Tuple2<Pub, Evt> x$1) {
                return x$1._2();
            }
        });
    }

    public void clear() {
        this.log().clear();
    }

    @Override
    public boolean equals(Object obj) {
        boolean bl;
        if (obj instanceof History) {
            History history = (History)obj;
            bl = this.log().equals(history.log());
        } else {
            bl = false;
        }
        return bl;
    }

    public int hashCode() {
        return this.log().hashCode();
    }

    public History() {
        this.maxHistory = 1000;
    }
}

