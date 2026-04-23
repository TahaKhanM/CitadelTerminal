/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedInputStream;
import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.collection.AbstractTraversable;
import scala.collection.Iterator;
import scala.collection.TraversableOnce;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ArrayBuffer;
import scala.package$;
import scala.reflect.ClassTag$;
import scala.reflect.io.Path$;
import scala.reflect.io.Streamable;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.VolatileByteRef;

public abstract class Streamable$Bytes$class {
    public static long length(Streamable.Bytes $this) {
        return -1L;
    }

    public static BufferedInputStream bufferedInput(Streamable.Bytes $this) {
        return new BufferedInputStream($this.inputStream());
    }

    public static Iterator bytes(Streamable.Bytes $this) {
        return $this.bytesAsInts().map(new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final byte apply(int x$1) {
                return (byte)x$1;
            }
        });
    }

    public static Iterator bytesAsInts(Streamable.Bytes $this) {
        BufferedInputStream in = $this.bufferedInput();
        return package$.MODULE$.Iterator().continually(new Serializable($this, in){
            public static final long serialVersionUID = 0L;
            public final BufferedInputStream in$1;

            public final int apply() {
                return this.in$1.read();
            }

            public int apply$mcI$sp() {
                return this.in$1.read();
            }
            {
                this.in$1 = in$1;
            }
        }).takeWhile((Function1<Object, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(int x$2) {
                return this.apply$mcZI$sp(x$2);
            }

            public boolean apply$mcZI$sp(int x$2) {
                return x$2 != -1;
            }
        }));
    }

    public static byte[] toByteArray(Streamable.Bytes $this) {
        ObjectRef<Object> in$lzy = ObjectRef.zero();
        VolatileByteRef bitmap$0 = VolatileByteRef.create((byte)0);
        if ($this.length() == -1L) {
            return (byte[])((AbstractTraversable)((Object)new ArrayBuffer().$plus$plus$eq((TraversableOnce)$this.bytes()))).toArray(ClassTag$.MODULE$.Byte());
        }
        byte[] arr = new byte[(int)$this.length()];
        int len = arr.length;
        IntRef offset = IntRef.create(0);
        Streamable$Bytes$class.loop$1($this, arr, len, in$lzy, offset, bitmap$0);
        if (offset.elem == arr.length) {
            return arr;
        }
        Predef$ predef$ = Predef$.MODULE$;
        throw Path$.MODULE$.fail(new StringOps("Could not read entire source (%d of %d bytes)").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(offset.elem), BoxesRunTime.boxToInteger(len)})));
        finally {
            Streamable$Bytes$class.in$2($this, in$lzy, bitmap$0).close();
        }
    }

    private static final BufferedInputStream in$lzycompute$1(Streamable.Bytes $this, ObjectRef in$lzy$1, VolatileByteRef bitmap$0$1) {
        Streamable.Bytes bytes2 = $this;
        synchronized (bytes2) {
            if ((byte)(bitmap$0$1.elem & 1) == 0) {
                in$lzy$1.elem = $this.bufferedInput();
                bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 1);
            }
            // ** MonitorExit[$this] (shouldn't be in output)
            return (BufferedInputStream)in$lzy$1.elem;
        }
    }

    private static final BufferedInputStream in$2(Streamable.Bytes $this, ObjectRef in$lzy$1, VolatileByteRef bitmap$0$1) {
        return (byte)(bitmap$0$1.elem & 1) == 0 ? Streamable$Bytes$class.in$lzycompute$1($this, in$lzy$1, bitmap$0$1) : (BufferedInputStream)in$lzy$1.elem;
    }

    private static final void loop$1(Streamable.Bytes $this, byte[] arr$1, int len$1, ObjectRef in$lzy$1, IntRef offset$1, VolatileByteRef bitmap$0$1) {
        block2: {
            BoxedUnit boxedUnit;
            while (offset$1.elem < len$1) {
                int read = Streamable$Bytes$class.in$2($this, in$lzy$1, bitmap$0$1).read(arr$1, offset$1.elem, len$1 - offset$1.elem);
                if (read >= 0) {
                    offset$1.elem += read;
                    continue;
                }
                boxedUnit = BoxedUnit.UNIT;
                break block2;
            }
            boxedUnit = BoxedUnit.UNIT;
        }
    }

    public static void $init$(Streamable.Bytes $this) {
    }
}

