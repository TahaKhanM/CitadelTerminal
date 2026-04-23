/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.reflect.runtime.SymbolTable;

@ScalaSignature(bytes="\u0006\u0001\u00194\u0011\"\u0001\u0002\u0011\u0002\u0007\u0005A\u0001\u00032\u0003%QC'/Z1e\u0019>\u001c\u0017\r\\*u_J\fw-\u001a\u0006\u0003\u0007\u0011\tqA];oi&lWM\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0014\u0005\u0001I\u0001C\u0001\u0006\f\u001b\u00051\u0011B\u0001\u0007\u0007\u0005\u0019\te.\u001f*fM\")a\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0012!\tQ!#\u0003\u0002\u0014\r\t!QK\\5u\r\u001d\t\u0001\u0001%A\u0012\u0002U)\"A\u0006\u000f\u0014\u0005QI\u0001\"\u0002\r\u0015\r\u0003I\u0012aA4fiV\t!\u0004\u0005\u0002\u001c91\u0001A!B\u000f\u0015\u0005\u0004q\"!\u0001+\u0012\u0005}\u0011\u0003C\u0001\u0006!\u0013\t\tcAA\u0004O_RD\u0017N\\4\u0011\u0005)\u0019\u0013B\u0001\u0013\u0007\u0005\r\te.\u001f\u0005\u0006MQ1\taJ\u0001\u0004g\u0016$HCA\t)\u0011\u0015IS\u00051\u0001\u001b\u0003!qWm\u001e,bYV,g\u0001B\u0016\u0001\t1\u0012A#T=UQJ,\u0017\r\u001a'pG\u0006d7\u000b^8sC\u001e,WCA\u00172'\rQ\u0013B\f\t\u0004_Q\u0001T\"\u0001\u0001\u0011\u0005m\tD!B\u000f+\u0005\u0004q\u0002\u0002C\u001a+\u0005\u0003%\u000b\u0011\u0002\u001b\u0002\u0019%t\u0017\u000e^5bYZ\u000bG.^3\u0011\u0007))\u0004'\u0003\u00027\r\tAAHY=oC6,g\bC\u00039U\u0011\u0005\u0011(\u0001\u0004=S:LGO\u0010\u000b\u0003um\u00022a\f\u00161\u0011\u0019\u0019t\u0007\"a\u0001i!9QH\u000bb\u0001\n\u0013q\u0014A\u0002<bYV,7/F\u0001@!\u0011\u0001Ui\u0012\u0019\u000e\u0003\u0005S!AQ\"\u0002\tU$\u0018\u000e\u001c\u0006\u0002\t\u0006!!.\u0019<b\u0013\t1\u0015IA\u0002NCB\u0004\"\u0001S&\u000e\u0003%S!AS\"\u0002\t1\fgnZ\u0005\u0003\u0019&\u0013a\u0001\u00165sK\u0006$\u0007B\u0002(+A\u0003%q(A\u0004wC2,Xm\u001d\u0011\t\u000baQC\u0011\u0001)\u0016\u0003ABQA\n\u0016\u0005\u0002I#\"!E*\t\u000b%\n\u0006\u0019\u0001\u0019\t\u000bU\u0003AQ\u0001,\u0002)5\\G\u000b\u001b:fC\u0012dunY1m'R|'/Y4f+\t9&\f\u0006\u0002Y7B\u0019q\u0006F-\u0011\u0005mQF!B\u000fU\u0005\u0004q\u0002B\u0002/U\t\u0003\u0007Q,A\u0001y!\rQQ'\u0017\u0015\u0003)~\u0003\"A\u00031\n\u0005\u00054!AB5oY&tW\r\u0005\u0002dI6\t!!\u0003\u0002f\u0005\tY1+_7c_2$\u0016M\u00197f\u0001")
public interface ThreadLocalStorage {
    public <T> ThreadLocalStorage<T> mkThreadLocalStorage(Function0<T> var1);

    public interface ThreadLocalStorage<T> {
        public T get();

        public void set(T var1);
    }

    public class MyThreadLocalStorage<T>
    implements ThreadLocalStorage<T> {
        private final Function0<T> initialValue;
        private final Map<Thread, T> values;
        public final /* synthetic */ SymbolTable $outer;

        private Map<Thread, T> values() {
            return this.values;
        }

        /*
         * WARNING - void declaration
         */
        @Override
        public T get() {
            T t;
            if (this.values().containsKey(Thread.currentThread())) {
                t = this.values().get(Thread.currentThread());
            } else {
                void var1_1;
                T value = this.initialValue.apply();
                this.values().put(Thread.currentThread(), value);
                t = var1_1;
            }
            return t;
        }

        @Override
        public void set(T newValue) {
            this.values().put(Thread.currentThread(), newValue);
        }

        public /* synthetic */ SymbolTable scala$reflect$runtime$ThreadLocalStorage$MyThreadLocalStorage$$$outer() {
            return this.$outer;
        }

        public MyThreadLocalStorage(SymbolTable $outer, Function0<T> initialValue) {
            this.initialValue = initialValue;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.values = Collections.synchronizedMap(new WeakHashMap());
        }
    }
}

