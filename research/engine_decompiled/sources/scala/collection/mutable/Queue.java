/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.GenTraversable;
import scala.collection.Seq;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.GenTraversableFactory;
import scala.collection.generic.GenericCompanion;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedList;
import scala.collection.mutable.LinkedListLike;
import scala.collection.mutable.MutableList;
import scala.collection.mutable.Queue$;
import scala.collection.mutable.Seq$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005ud\u0001B\u0001\u0003\u0001%\u0011Q!U;fk\u0016T!a\u0001\u0003\u0002\u000f5,H/\u00192mK*\u0011QAB\u0001\u000bG>dG.Z2uS>t'\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011!\"E\n\u0007\u0001-Y\u0002e\n\u0016\u0011\u00071iq\"D\u0001\u0003\u0013\tq!AA\u0006NkR\f'\r\\3MSN$\bC\u0001\t\u0012\u0019\u0001!QA\u0005\u0001C\u0002M\u0011\u0011!Q\t\u0003)a\u0001\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#G\u0005\u00035\u0019\u00111!\u00118z!\u0011aRdD\u0010\u000e\u0003\u0011I!A\b\u0003\u0003%1Kg.Z1s'\u0016\fx\n\u001d;j[&TX\r\u001a\t\u0004\u0019\u0001y\u0001\u0003B\u0011%\u001f\u0019j\u0011A\t\u0006\u0003G\u0011\tqaZ3oKJL7-\u0003\u0002&E\tQr)\u001a8fe&\u001cGK]1wKJ\u001c\u0018M\u00197f)\u0016l\u0007\u000f\\1uKB\u0011A\u0002\u0001\t\u0004\u0019!z\u0012BA\u0015\u0003\u0005%\u0019En\u001c8fC\ndW\r\u0005\u0002\u0016W%\u0011AF\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\u0006]\u0001!\taL\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003}AQ!\r\u0001\u0005BI\n\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003M\u00022!\t\u001b'\u0013\t)$E\u0001\tHK:,'/[2D_6\u0004\u0018M\\5p]\"1q\u0007\u0001Q\u0005Ra\n!B\\3x\u0005VLG\u000eZ3s+\u0005I\u0004\u0003\u0002\u0007;\u001f}I!a\u000f\u0002\u0003\u000f\t+\u0018\u000e\u001c3fe\"1a\u0006\u0001C\u0001\u0005u\"Ba\b D\u000b\")q\b\u0010a\u0001\u0001\u0006\u0019am\u001d;\u0011\u00071\tu\"\u0003\u0002C\u0005\tQA*\u001b8lK\u0012d\u0015n\u001d;\t\u000b\u0011c\u0004\u0019\u0001!\u0002\u00071\u001cH\u000fC\u0003Gy\u0001\u0007q)A\u0002m]\u001e\u0004\"!\u0006%\n\u0005%3!aA%oi\")1\n\u0001C\u0001\u0019\u00069QM\\9vKV,GCA'Q!\t)b*\u0003\u0002P\r\t!QK\\5u\u0011\u0015\t&\n1\u0001S\u0003\u0015)G.Z7t!\r)2kD\u0005\u0003)\u001a\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0011\u00151\u0006\u0001\"\u0001X\u0003\u001d!W-];fk\u0016$\u0012a\u0004\u0005\u00063\u0002!\tAW\u0001\rI\u0016\fX/Z;f\r&\u00148\u000f\u001e\u000b\u00037z\u00032!\u0006/\u0010\u0013\tifA\u0001\u0004PaRLwN\u001c\u0005\u0006?b\u0003\r\u0001Y\u0001\u0002aB!Q#Y\bd\u0013\t\u0011gAA\u0005Gk:\u001cG/[8ocA\u0011Q\u0003Z\u0005\u0003K\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003h\u0001\u0011%\u0001.\u0001\bsK6|g/\u001a$s_6d\u0015n\u001d;\u0015\u0005mK\u0007\"B0g\u0001\u0004\u0001\u0007\"B6\u0001\t\u0003a\u0017A\u00033fcV,W/Z!mYR\u0011Q\u000e\u001d\t\u0004\u00199|\u0011BA8\u0003\u0005\r\u0019V-\u001d\u0005\u0006?*\u0004\r\u0001\u0019\u0005\u0006e\u0002!Ia]\u0001\u0012e\u0016lwN^3BY24%o\\7MSN$Hc\u0001;xqB\u0019A\"^\b\n\u0005Y\u0014!aC!se\u0006L()\u001e4gKJDQaX9A\u0002\u0001DQ!_9A\u0002Q\f1A]3t\u0011\u0015Y\b\u0001\"\u0001}\u00031)\u0007\u0010\u001e:bGR4\u0015N]:u)\u0011ih0!\u0001\u0011\u0007Ua\u0006\tC\u0003\u0000u\u0002\u0007\u0001)A\u0003ti\u0006\u0014H\u000fC\u0003`u\u0002\u0007\u0001\rK\u0004{\u0003\u000b\tY!a\u0004\u0011\u0007U\t9!C\u0002\u0002\n\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t\ti!\u0001-fqR\u0014\u0018m\u0019;GSJ\u001cH\u000fI5oCB\u0004(o\u001c9sS\u0006$X\r\\=!Kb\u0004xn]3tA%l\u0007\u000f\\3nK:$\u0018\r^5p]\u0002\"W\r^1jYNt\u0003\u0005I+tK\u0002\"W-];fk\u0016\u0004sN\u001d\u0011eKF,X-^3BY2t\u0013EAA\t\u0003\u0019\u0011d&M\u0019/a!9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0011!\u00024s_:$X#A\b\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e\u0005!A/Y5m+\u0005y\u0002BBA\u0011\u0001\u0011\u0005s&A\u0003dY>tW\r\u0003\u0005\u0002&\u0001\u0001K\u0011BA\u0014\u0003=!Wm\u0019:f[\u0016tG\u000fT3oORDG#A'\b\u000f\u0005-\"\u0001#\u0001\u0002.\u0005)\u0011+^3vKB\u0019A\"a\f\u0007\r\u0005\u0011\u0001\u0012AA\u0019'\u0015\ty#a\r+!\u0011\t\u0013Q\u0007\u0014\n\u0007\u0005]\"E\u0001\u0006TKF4\u0015m\u0019;pefDqALA\u0018\t\u0003\tY\u0004\u0006\u0002\u0002.!A\u0011qHA\u0018\t\u0007\t\t%\u0001\u0007dC:\u0014U/\u001b7e\rJ|W.\u0006\u0003\u0002D\u0005USCAA#!%\t\u0013qIA&\u0003'\n9&C\u0002\u0002J\t\u0012AbQ1o\u0005VLG\u000e\u001a$s_6\u0004B!!\u0014\u0002P5\u0011\u0011qF\u0005\u0004\u0003#\"$\u0001B\"pY2\u00042\u0001EA+\t\u0019\u0011\u0012Q\bb\u0001'A!A\u0002AA*\u0011\u001d9\u0014q\u0006C\u0001\u00037*B!!\u0018\u0002dU\u0011\u0011q\f\t\u0007\u0019i\n\t'!\u001a\u0011\u0007A\t\u0019\u0007\u0002\u0004\u0013\u00033\u0012\ra\u0005\t\u0005\u0019\u0001\t\t\u0007\u0003\u0006\u0002j\u0005=\u0012\u0011!C\u0005\u0003W\n1B]3bIJ+7o\u001c7wKR\u0011\u0011Q\u000e\t\u0005\u0003_\nI(\u0004\u0002\u0002r)!\u00111OA;\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0014\u0001\u00026bm\u0006LA!a\u001f\u0002r\t1qJ\u00196fGR\u0004")
public class Queue<A>
extends MutableList<A> {
    public static <A> CanBuildFrom<Queue<?>, A, Queue<A>> canBuildFrom() {
        return Queue$.MODULE$.canBuildFrom();
    }

    public static Some unapplySeq(Seq seq) {
        return Queue$.MODULE$.unapplySeq(seq);
    }

    public static GenTraversable iterate(Object object, int n, Function1 function1) {
        return Queue$.MODULE$.iterate(object, n, function1);
    }

    public static GenTraversable range(Object object, Object object2, Object object3, Integral integral) {
        return Queue$.MODULE$.range(object, object2, object3, integral);
    }

    public static GenTraversable range(Object object, Object object2, Integral integral) {
        return Queue$.MODULE$.range(object, object2, integral);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, int n5, Function5 function5) {
        return Queue$.MODULE$.tabulate(n, n2, n3, n4, n5, function5);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, int n4, Function4 function4) {
        return Queue$.MODULE$.tabulate(n, n2, n3, n4, function4);
    }

    public static GenTraversable tabulate(int n, int n2, int n3, Function3 function3) {
        return Queue$.MODULE$.tabulate(n, n2, n3, function3);
    }

    public static GenTraversable tabulate(int n, int n2, Function2 function2) {
        return Queue$.MODULE$.tabulate(n, n2, function2);
    }

    public static GenTraversable tabulate(int n, Function1 function1) {
        return Queue$.MODULE$.tabulate(n, function1);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, int n5, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, n4, n5, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, int n4, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, n4, function0);
    }

    public static GenTraversable fill(int n, int n2, int n3, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, n3, function0);
    }

    public static GenTraversable fill(int n, int n2, Function0 function0) {
        return Queue$.MODULE$.fill(n, n2, function0);
    }

    public static GenTraversable fill(int n, Function0 function0) {
        return Queue$.MODULE$.fill(n, function0);
    }

    public static GenTraversable concat(Seq seq) {
        return Queue$.MODULE$.concat(seq);
    }

    public static GenTraversableFactory.GenericCanBuildFrom<Nothing$> ReusableCBF() {
        return Queue$.MODULE$.ReusableCBF();
    }

    public static GenTraversable empty() {
        return Queue$.MODULE$.empty();
    }

    @Override
    public GenericCompanion<Queue> companion() {
        return Queue$.MODULE$;
    }

    @Override
    public Builder<A, Queue<A>> newBuilder() {
        return this.companion().newBuilder();
    }

    public void enqueue(Seq<A> elems) {
        this.$plus$plus$eq(elems);
    }

    /*
     * WARNING - void declaration
     */
    public A dequeue() {
        void var1_1;
        if (this.isEmpty()) {
            throw new NoSuchElementException("queue empty");
        }
        Object res = this.first0().elem();
        this.first0_$eq((LinkedList)this.first0().next());
        this.decrementLength();
        return var1_1;
    }

    /*
     * WARNING - void declaration
     */
    public Option<A> dequeueFirst(Function1<A, Object> p) {
        None$ none$;
        if (this.isEmpty()) {
            none$ = None$.MODULE$;
        } else if (BoxesRunTime.unboxToBoolean(p.apply(this.first0().elem()))) {
            void var2_2;
            Some res = new Some(this.first0().elem());
            this.first0_$eq((LinkedList)this.first0().next());
            this.decrementLength();
            none$ = var2_2;
        } else {
            void var3_3;
            Option<A> optElem;
            Option<A> option = optElem = this.removeFromList(p);
            None$ none$2 = None$.MODULE$;
            if (option == null || !option.equals(none$2)) {
                this.decrementLength();
            }
            none$ = var3_3;
        }
        return none$;
    }

    /*
     * WARNING - void declaration
     */
    private Option<A> removeFromList(Function1<A, Object> p) {
        void var3_3;
        LinkedList leftlst = this.first0();
        Option res = None$.MODULE$;
        while (leftlst.next().nonEmpty() && !BoxesRunTime.unboxToBoolean(p.apply(((LinkedListLike)((Object)leftlst.next())).elem()))) {
            leftlst = (LinkedList)leftlst.next();
        }
        if (leftlst.next().nonEmpty()) {
            res = new Some(((LinkedListLike)((Object)leftlst.next())).elem());
            if (leftlst.next() == this.last0()) {
                this.last0_$eq(leftlst);
            }
            leftlst.next_$eq((scala.collection.mutable.Seq)((LinkedListLike)((Object)leftlst.next())).next());
        }
        return var3_3;
    }

    public scala.collection.mutable.Seq<A> dequeueAll(Function1<A, Object> p) {
        ArrayBuffer arrayBuffer;
        if (this.first0().isEmpty()) {
            arrayBuffer = (ArrayBuffer)Seq$.MODULE$.empty();
        } else {
            ArrayBuffer res = new ArrayBuffer();
            while (this.first0().nonEmpty() && BoxesRunTime.unboxToBoolean(p.apply(this.first0().elem()))) {
                res.$plus$eq(this.first0().elem());
                this.first0_$eq((LinkedList)this.first0().next());
                this.decrementLength();
            }
            arrayBuffer = this.first0().isEmpty() ? res : this.removeAllFromList(p, res);
        }
        return arrayBuffer;
    }

    private ArrayBuffer<A> removeAllFromList(Function1<A, Object> p, ArrayBuffer<A> res) {
        LinkedList leftlst = this.first0();
        while (leftlst.next().nonEmpty()) {
            if (BoxesRunTime.unboxToBoolean(p.apply(((LinkedListLike)((Object)leftlst.next())).elem()))) {
                res.$plus$eq(((LinkedListLike)((Object)leftlst.next())).elem());
                if (leftlst.next() == this.last0()) {
                    this.last0_$eq(leftlst);
                }
                leftlst.next_$eq((scala.collection.mutable.Seq)((LinkedListLike)((Object)leftlst.next())).next());
                this.decrementLength();
                continue;
            }
            leftlst = (LinkedList)leftlst.next();
        }
        return res;
    }

    public Option<LinkedList<A>> extractFirst(LinkedList<A> start, Function1<A, Object> p) {
        Option option;
        if (this.isEmpty()) {
            option = None$.MODULE$;
        } else {
            LinkedList cell = start;
            while (cell.next().nonEmpty() && !BoxesRunTime.unboxToBoolean(p.apply(((LinkedListLike)((Object)cell.next())).elem()))) {
                cell = (LinkedList)cell.next();
            }
            if (((LinkedListLike)((Object)cell.next())).isEmpty()) {
                option = None$.MODULE$;
            } else {
                Some<scala.collection.mutable.Seq> res = new Some<scala.collection.mutable.Seq>(cell.next());
                cell.next_$eq((scala.collection.mutable.Seq)((LinkedListLike)((Object)cell.next())).next());
                this.decrementLength();
                option = res;
            }
        }
        return option;
    }

    public A front() {
        return this.head();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public Queue<A> tail() {
        void var1_1;
        Queue<A> tl = new Queue<A>();
        this.tailImpl(tl);
        return var1_1;
    }

    @Override
    public Queue<A> clone() {
        Builder<A, Queue<A>> bf = this.newBuilder();
        bf.$plus$plus$eq(this.seq());
        return bf.result();
    }

    private void decrementLength() {
        this.len_$eq(this.len() - 1);
        if (this.len() == 0) {
            this.last0_$eq(this.first0());
        }
    }

    public Queue() {
    }

    public Queue(LinkedList<A> fst, LinkedList<A> lst, int lng) {
        this();
        this.first0_$eq(fst);
        this.last0_$eq(lst);
        this.len_$eq(lng);
    }
}

