/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Array$;
import scala.Function1;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.generic.Growable;
import scala.collection.generic.Growable$class;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Builder$class;
import scala.collection.mutable.WrappedArray;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001a4A!\u0001\u0002\u0001\u0013\t\u0019rK]1qa\u0016$\u0017I\u001d:bs\n+\u0018\u000e\u001c3fe*\u00111\u0001B\u0001\b[V$\u0018M\u00197f\u0015\t)a!\u0001\u0006d_2dWm\u0019;j_:T\u0011aB\u0001\u0006g\u000e\fG.Y\u0002\u0001+\tQQcE\u0002\u0001\u0017=\u0001\"\u0001D\u0007\u000e\u0003\u0019I!A\u0004\u0004\u0003\r\u0005s\u0017PU3g!\u0011\u0001\u0012c\u0005\u0010\u000e\u0003\tI!A\u0005\u0002\u0003\u000f\t+\u0018\u000e\u001c3feB\u0011A#\u0006\u0007\u0001\t\u00151\u0002A1\u0001\u0018\u0005\u0005\t\u0015C\u0001\r\u001c!\ta\u0011$\u0003\u0002\u001b\r\t9aj\u001c;iS:<\u0007C\u0001\u0007\u001d\u0013\tibAA\u0002B]f\u00042\u0001E\u0010\u0014\u0013\t\u0001#A\u0001\u0007Xe\u0006\u0004\b/\u001a3BeJ\f\u0017\u0010\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003$\u0003\r!\u0018m\u001a\t\u0004I\u001d\u001aR\"A\u0013\u000b\u0005\u00192\u0011a\u0002:fM2,7\r^\u0005\u0003Q\u0015\u0012\u0001b\u00117bgN$\u0016m\u001a\u0005\u0006U\u0001!\taK\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00051j\u0003c\u0001\t\u0001'!)!%\u000ba\u0001G!9q\u0006\u0001b\u0001\n\u0003\u0001\u0014\u0001C7b]&4Wm\u001d;\u0016\u0003\rBCA\f\u001a6oA\u0011AbM\u0005\u0003i\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\u00051\u0014aD;tK\u0002\"\u0018m\u001a\u0011j]N$X-\u00193\"\u0003a\naA\r\u00182a9\u0002\u0004B\u0002\u001e\u0001A\u0003%1%A\u0005nC:Lg-Z:uA!IA\b\u0001a\u0001\u0002\u0004%I!P\u0001\u0006K2,Wn]\u000b\u0002=!Iq\b\u0001a\u0001\u0002\u0004%I\u0001Q\u0001\nK2,Wn]0%KF$\"!\u0011#\u0011\u00051\u0011\u0015BA\"\u0007\u0005\u0011)f.\u001b;\t\u000f\u0015s\u0014\u0011!a\u0001=\u0005\u0019\u0001\u0010J\u0019\t\r\u001d\u0003\u0001\u0015)\u0003\u001f\u0003\u0019)G.Z7tA!9\u0011\n\u0001a\u0001\n\u0013Q\u0015\u0001C2ba\u0006\u001c\u0017\u000e^=\u0016\u0003-\u0003\"\u0001\u0004'\n\u000553!aA%oi\"9q\n\u0001a\u0001\n\u0013\u0001\u0016\u0001D2ba\u0006\u001c\u0017\u000e^=`I\u0015\fHCA!R\u0011\u001d)e*!AA\u0002-Caa\u0015\u0001!B\u0013Y\u0015!C2ba\u0006\u001c\u0017\u000e^=!\u0011\u001d)\u0006\u00011A\u0005\n)\u000bAa]5{K\"9q\u000b\u0001a\u0001\n\u0013A\u0016\u0001C:ju\u0016|F%Z9\u0015\u0005\u0005K\u0006bB#W\u0003\u0003\u0005\ra\u0013\u0005\u00077\u0002\u0001\u000b\u0015B&\u0002\u000bML'0\u001a\u0011\t\u000bu\u0003A\u0011\u00020\u0002\u000f5\\\u0017I\u001d:bsR\u0011ad\u0018\u0005\u0006+r\u0003\ra\u0013\u0005\u0006C\u0002!IAY\u0001\u0007e\u0016\u001c\u0018N_3\u0015\u0005\u0005\u001b\u0007\"B+a\u0001\u0004Y\u0005\"B3\u0001\t\u00032\u0017\u0001C:ju\u0016D\u0015N\u001c;\u0015\u0005\u0005;\u0007\"B+e\u0001\u0004Y\u0005\"B5\u0001\t\u0013Q\u0017AC3ogV\u0014XmU5{KR\u0011\u0011i\u001b\u0005\u0006+\"\u0004\ra\u0013\u0005\u0006[\u0002!\tA\\\u0001\tIAdWo\u001d\u0013fcR\u0011q\u000e]\u0007\u0002\u0001!)\u0011\u000f\u001ca\u0001'\u0005!Q\r\\3n\u0011\u0015\u0019\b\u0001\"\u0001u\u0003\u0015\u0019G.Z1s)\u0005\t\u0005\"\u0002<\u0001\t\u00039\u0018A\u0002:fgVdG\u000fF\u0001\u001f\u0001")
public class WrappedArrayBuilder<A>
implements Builder<A, WrappedArray<A>> {
    private final ClassTag<A> tag;
    private final ClassTag<A> manifest;
    private WrappedArray<A> elems;
    private int capacity;
    private int size;

    @Override
    public void sizeHint(TraversableLike<?, ?> coll) {
        Builder$class.sizeHint((Builder)this, coll);
    }

    @Override
    public void sizeHint(TraversableLike<?, ?> coll, int delta) {
        Builder$class.sizeHint(this, coll, delta);
    }

    @Override
    public void sizeHintBounded(int size2, TraversableLike<?, ?> boundingColl) {
        Builder$class.sizeHintBounded(this, size2, boundingColl);
    }

    @Override
    public <NewTo> Builder<A, NewTo> mapResult(Function1<WrappedArray<A>, NewTo> f) {
        return Builder$class.mapResult(this, f);
    }

    @Override
    public Growable<A> $plus$eq(A elem1, A elem2, Seq<A> elems) {
        return Growable$class.$plus$eq(this, elem1, elem2, elems);
    }

    @Override
    public Growable<A> $plus$plus$eq(TraversableOnce<A> xs) {
        return Growable$class.$plus$plus$eq(this, xs);
    }

    public ClassTag<A> manifest() {
        return this.manifest;
    }

    private WrappedArray<A> elems() {
        return this.elems;
    }

    private void elems_$eq(WrappedArray<A> x$1) {
        this.elems = x$1;
    }

    private int capacity() {
        return this.capacity;
    }

    private void capacity_$eq(int x$1) {
        this.capacity = x$1;
    }

    private int size() {
        return this.size;
    }

    private void size_$eq(int x$1) {
        this.size = x$1;
    }

    private WrappedArray<A> mkArray(int size2) {
        WrappedArray wrappedArray;
        Class<?> runtimeClass = ScalaRunTime$.MODULE$.arrayElementClass(this.tag);
        Class<Byte> clazz = Byte.TYPE;
        if (!(clazz != null ? !clazz.equals(runtimeClass) : runtimeClass != null)) {
            wrappedArray = new WrappedArray.ofByte(new byte[size2]);
        } else {
            Class<Short> clazz2 = Short.TYPE;
            if (!(clazz2 != null ? !clazz2.equals(runtimeClass) : runtimeClass != null)) {
                wrappedArray = new WrappedArray.ofShort(new short[size2]);
            } else {
                Class<Character> clazz3 = Character.TYPE;
                if (!(clazz3 != null ? !clazz3.equals(runtimeClass) : runtimeClass != null)) {
                    wrappedArray = new WrappedArray.ofChar(new char[size2]);
                } else {
                    Class<Integer> clazz4 = Integer.TYPE;
                    if (!(clazz4 != null ? !clazz4.equals(runtimeClass) : runtimeClass != null)) {
                        wrappedArray = new WrappedArray.ofInt(new int[size2]);
                    } else {
                        Class<Long> clazz5 = Long.TYPE;
                        if (!(clazz5 != null ? !clazz5.equals(runtimeClass) : runtimeClass != null)) {
                            wrappedArray = new WrappedArray.ofLong(new long[size2]);
                        } else {
                            Class<Float> clazz6 = Float.TYPE;
                            if (!(clazz6 != null ? !clazz6.equals(runtimeClass) : runtimeClass != null)) {
                                wrappedArray = new WrappedArray.ofFloat(new float[size2]);
                            } else {
                                Class<Double> clazz7 = Double.TYPE;
                                if (!(clazz7 != null ? !clazz7.equals(runtimeClass) : runtimeClass != null)) {
                                    wrappedArray = new WrappedArray.ofDouble(new double[size2]);
                                } else {
                                    Class<Boolean> clazz8 = Boolean.TYPE;
                                    if (!(clazz8 != null ? !clazz8.equals(runtimeClass) : runtimeClass != null)) {
                                        wrappedArray = new WrappedArray.ofBoolean(new boolean[size2]);
                                    } else {
                                        Class<Void> clazz9 = Void.TYPE;
                                        wrappedArray = !(clazz9 != null ? !clazz9.equals(runtimeClass) : runtimeClass != null) ? new WrappedArray.ofUnit(new BoxedUnit[size2]) : new WrappedArray.ofRef<Object>((Object[])this.tag.newArray(size2));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems().array(), 0, wrappedArray.array(), 0, this.size());
        }
        return wrappedArray;
    }

    private void resize(int size2) {
        this.elems_$eq(this.mkArray(size2));
        this.capacity_$eq(size2);
    }

    @Override
    public void sizeHint(int size2) {
        if (this.capacity() < size2) {
            this.resize(size2);
        }
    }

    private void ensureSize(int size2) {
        if (this.capacity() < size2) {
            int newsize;
            int n = newsize = this.capacity() == 0 ? 16 : this.capacity() * 2;
            while (newsize < size2) {
                newsize *= 2;
            }
            this.resize(newsize);
        }
    }

    @Override
    public WrappedArrayBuilder<A> $plus$eq(A elem) {
        this.ensureSize(this.size() + 1);
        this.elems().update(this.size(), elem);
        this.size_$eq(this.size() + 1);
        return this;
    }

    @Override
    public void clear() {
        this.size_$eq(0);
    }

    @Override
    public WrappedArray<A> result() {
        WrappedArray<A> wrappedArray;
        if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            wrappedArray = this.elems();
        } else {
            wrappedArray = this.mkArray(this.size());
        }
        return wrappedArray;
    }

    public WrappedArrayBuilder(ClassTag<A> tag) {
        this.tag = tag;
        Growable$class.$init$(this);
        Builder$class.$init$(this);
        this.manifest = tag;
        this.capacity = 0;
        this.size = 0;
    }
}

