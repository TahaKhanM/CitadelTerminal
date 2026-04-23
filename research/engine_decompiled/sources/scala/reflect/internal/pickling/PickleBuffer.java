/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

import scala.Array$;
import scala.Function0;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\u0005Uc\u0001B\u0001\u0003\u0001-\u0011A\u0002U5dW2,')\u001e4gKJT!a\u0001\u0003\u0002\u0011AL7m\u001b7j]\u001eT!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u000fI,g\r\\3di*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u000f\u001b\u0005A\u0011BA\b\t\u0005\u0019\te.\u001f*fM\"A\u0011\u0003\u0001B\u0001B\u0003%!#\u0001\u0003eCR\f\u0007cA\u0007\u0014+%\u0011A\u0003\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u001bYI!a\u0006\u0005\u0003\t\tKH/\u001a\u0005\t3\u0001\u0011\t\u0011)A\u00055\u0005!aM]8n!\ti1$\u0003\u0002\u001d\u0011\t\u0019\u0011J\u001c;\t\u0011y\u0001!\u0011!Q\u0001\ni\t!\u0001^8\t\u000b\u0001\u0002A\u0011A\u0011\u0002\rqJg.\u001b;?)\u0011\u0011C%\n\u0014\u0011\u0005\r\u0002Q\"\u0001\u0002\t\u000bEy\u0002\u0019\u0001\n\t\u000bey\u0002\u0019\u0001\u000e\t\u000byy\u0002\u0019\u0001\u000e\t\u000f!\u0002\u0001\u0019!C\u0001S\u0005)!-\u001f;fgV\t!\u0003C\u0004,\u0001\u0001\u0007I\u0011\u0001\u0017\u0002\u0013\tLH/Z:`I\u0015\fHCA\u00171!\tia&\u0003\u00020\u0011\t!QK\\5u\u0011\u001d\t$&!AA\u0002I\t1\u0001\u001f\u00132\u0011\u0019\u0019\u0004\u0001)Q\u0005%\u00051!-\u001f;fg\u0002Bq!\u000e\u0001A\u0002\u0013\u0005a'A\u0005sK\u0006$\u0017J\u001c3fqV\t!\u0004C\u00049\u0001\u0001\u0007I\u0011A\u001d\u0002\u001bI,\u0017\rZ%oI\u0016Dx\fJ3r)\ti#\bC\u00042o\u0005\u0005\t\u0019\u0001\u000e\t\rq\u0002\u0001\u0015)\u0003\u001b\u0003)\u0011X-\u00193J]\u0012,\u0007\u0010\t\u0005\b}\u0001\u0001\r\u0011\"\u00017\u0003)9(/\u001b;f\u0013:$W\r\u001f\u0005\b\u0001\u0002\u0001\r\u0011\"\u0001B\u000399(/\u001b;f\u0013:$W\r_0%KF$\"!\f\"\t\u000fEz\u0014\u0011!a\u00015!1A\t\u0001Q!\ni\t1b\u001e:ji\u0016Le\u000eZ3yA!)a\t\u0001C\u0005\u000f\u0006!AM\u00197f)\u0005i\u0003\"B%\u0001\t\u0003Q\u0015AD3ogV\u0014XmQ1qC\u000eLG/\u001f\u000b\u0003[-CQ\u0001\u0014%A\u0002i\t\u0001bY1qC\u000eLG/\u001f\u0005\u0006\u001d\u0002!\taT\u0001\noJLG/\u001a\"zi\u0016$\"!\f)\t\u000bEk\u0005\u0019\u0001\u000e\u0002\u0003\tDQa\u0015\u0001\u0005\u0002Q\u000b\u0001b\u001e:ji\u0016t\u0015\r\u001e\u000b\u0003[UCQA\u0016*A\u0002i\t\u0011\u0001\u001f\u0005\u00061\u0002!\t!W\u0001\roJLG/\u001a'p]\u001et\u0015\r\u001e\u000b\u0003[iCQAV,A\u0002m\u0003\"!\u0004/\n\u0005uC!\u0001\u0002'p]\u001eDQa\u0018\u0001\u0005\u0002\u0001\f\u0001\u0002]1uG\"t\u0015\r\u001e\u000b\u0004[\u0005\u001c\u0007\"\u00022_\u0001\u0004Q\u0012a\u00019pg\")aK\u0018a\u00015!)Q\r\u0001C\u0001M\u0006IqO]5uK2{gn\u001a\u000b\u0003[\u001dDQA\u00163A\u0002mCQ!\u001b\u0001\u0005\u0002)\f\u0001B]3bI\nKH/\u001a\u000b\u00025!)A\u000e\u0001C\u0001U\u00069!/Z1e\u001d\u0006$\b\"\u00028\u0001\t\u0003y\u0017a\u0003:fC\u0012duN\\4OCR$\u0012a\u0017\u0005\u0006c\u0002!\tA]\u0001\te\u0016\fG\rT8oOR\u00111l\u001d\u0005\u0006iB\u0004\rAG\u0001\u0004Y\u0016t\u0007\"\u0002<\u0001\t\u00039\u0018\u0001\u0004;p\u0013:$W\r_3e'\u0016\fX#\u0001=\u0011\u0007edxP\u0004\u0002\u000eu&\u00111\u0010C\u0001\ba\u0006\u001c7.Y4f\u0013\tihP\u0001\u0006J]\u0012,\u00070\u001a3TKFT!a\u001f\u0005\u0011\u000b5\t\tA\u0007\n\n\u0007\u0005\r\u0001B\u0001\u0004UkBdWM\r\u0005\b\u0003\u000f\u0001A\u0011AA\u0005\u0003\u0015)h\u000e^5m+\u0011\tY!a\u0006\u0015\r\u00055\u0011\u0011FA\u0017!\u0015I\u0018qBA\n\u0013\r\t\tB \u0002\u0005\u0019&\u001cH\u000f\u0005\u0003\u0002\u0016\u0005]A\u0002\u0001\u0003\t\u00033\t)A1\u0001\u0002\u001c\t\tA+\u0005\u0003\u0002\u001e\u0005\r\u0002cA\u0007\u0002 %\u0019\u0011\u0011\u0005\u0005\u0003\u000f9{G\u000f[5oOB\u0019Q\"!\n\n\u0007\u0005\u001d\u0002BA\u0002B]fDq!a\u000b\u0002\u0006\u0001\u0007!$A\u0002f]\u0012D\u0001\"a\f\u0002\u0006\u0001\u0007\u0011\u0011G\u0001\u0003_B\u0004R!DA\u001a\u0003'I1!!\u000e\t\u0005%1UO\\2uS>t\u0007\u0007C\u0004\u0002:\u0001!\t!a\u000f\u0002\u000bQLW.Z:\u0016\t\u0005u\u00121\t\u000b\u0007\u0003\u007f\t)%!\u0013\u0011\u000be\fy!!\u0011\u0011\t\u0005U\u00111\t\u0003\t\u00033\t9D1\u0001\u0002\u001c!9\u0011qIA\u001c\u0001\u0004Q\u0012!\u00018\t\u0011\u0005=\u0012q\u0007a\u0001\u0003\u0017\u0002R!DA\u001a\u0003\u0003Bq!a\u0014\u0001\t\u0003\t\t&A\u0006de\u0016\fG/Z%oI\u0016DXCAA*!\ri1C\u0007")
public class PickleBuffer {
    public final byte[] scala$reflect$internal$pickling$PickleBuffer$$data;
    private byte[] bytes;
    private int readIndex;
    private int writeIndex;

    public byte[] bytes() {
        return this.bytes;
    }

    public void bytes_$eq(byte[] x$1) {
        this.bytes = x$1;
    }

    public int readIndex() {
        return this.readIndex;
    }

    public void readIndex_$eq(int x$1) {
        this.readIndex = x$1;
    }

    public int writeIndex() {
        return this.writeIndex;
    }

    public void writeIndex_$eq(int x$1) {
        this.writeIndex = x$1;
    }

    private void dble() {
        byte[] bytes1 = new byte[this.bytes().length * 2];
        Array$.MODULE$.copy(this.bytes(), 0, bytes1, 0, this.writeIndex());
        this.bytes_$eq(bytes1);
    }

    public void ensureCapacity(int capacity) {
        while (this.bytes().length < this.writeIndex() + capacity) {
            this.dble();
        }
    }

    public void writeByte(int b) {
        if (this.writeIndex() == this.bytes().length) {
            this.dble();
        }
        this.bytes()[this.writeIndex()] = (byte)b;
        this.writeIndex_$eq(this.writeIndex() + 1);
    }

    public void writeNat(int x) {
        this.writeLongNat((long)x & 0xFFFFFFFFL);
    }

    public void writeLongNat(long x) {
        long y = x >>> 7;
        if (y != 0L) {
            this.writeNatPrefix$1(y);
        }
        this.writeByte((int)(x & 0x7FL));
    }

    public void patchNat(int pos, int x) {
        this.bytes()[pos] = (byte)(x & 0x7F);
        int y = x >>> 7;
        if (y != 0) {
            this.patchNatPrefix$1(y, pos);
        }
    }

    public void writeLong(long x) {
        long y = x >> 8;
        long z = x & 0xFFL;
        if (-y != z >> 7) {
            this.writeLong(y);
        }
        this.writeByte((int)z);
    }

    /*
     * WARNING - void declaration
     */
    public int readByte() {
        void var1_1;
        byte x = this.bytes()[this.readIndex()];
        this.readIndex_$eq(this.readIndex() + 1);
        return (int)var1_1;
    }

    public int readNat() {
        return (int)this.readLongNat();
    }

    /*
     * WARNING - void declaration
     */
    public long readLongNat() {
        void var3_1;
        long b;
        long x = 0L;
        do {
            b = this.readByte();
            x = (x << 7) + (b & 0x7FL);
        } while ((b & 0x80L) != 0L);
        return (long)var3_1;
    }

    public long readLong(int len) {
        long x = 0L;
        for (int i = 0; i < len; ++i) {
            x = (x << 8) + (long)(this.readByte() & 0xFF);
        }
        int leading = 64 - (len << 3);
        return x << leading >> leading;
    }

    public IndexedSeq<Tuple2<Object, byte[]>> toIndexedSeq() {
        int saved = this.readIndex();
        this.readIndex_$eq(0);
        this.readNat();
        this.readNat();
        Tuple2[] result2 = new Tuple2[this.readNat()];
        Object[] objectArray = result2;
        Predef$ predef$ = Predef$.MODULE$;
        Range range2 = new ArrayOps.ofRef<Object>(objectArray).indices();
        if (!range2.isEmpty()) {
            int n = range2.start();
            while (true) {
                int tag1 = this.readNat();
                int len1 = this.readNat();
                byte[] byArray = this.scala$reflect$internal$pickling$PickleBuffer$$data;
                Predef$ predef$2 = Predef$.MODULE$;
                byte[] bytes1 = (byte[])new ArrayOps.ofByte(byArray).slice(this.readIndex(), len1 + this.readIndex());
                this.readIndex_$eq(this.readIndex() + len1);
                Integer n2 = BoxesRunTime.boxToInteger(tag1);
                Predef$ predef$3 = Predef$.MODULE$;
                Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                result2[n] = new Tuple2<Integer, byte[]>(n2, bytes1);
                if (n == range2.lastElement()) break;
                n += range2.step();
            }
        }
        this.readIndex_$eq(saved);
        Object[] objectArray2 = result2;
        Predef$ predef$4 = Predef$.MODULE$;
        return new ArrayOps.ofRef<Object>(objectArray2).toIndexedSeq();
    }

    public <T> List<T> until(int end, Function0<T> op) {
        List list2;
        if (this.readIndex() == end) {
            list2 = Nil$.MODULE$;
        } else {
            T t = op.apply();
            list2 = this.until(end, op).$colon$colon(t);
        }
        return list2;
    }

    public <T> List<T> times(int n, Function0<T> op) {
        List list2;
        if (n == 0) {
            list2 = Nil$.MODULE$;
        } else {
            T t = op.apply();
            list2 = this.times(n - 1, op).$colon$colon(t);
        }
        return list2;
    }

    public int[] createIndex() {
        int[] index = new int[this.readNat()];
        Predef$ predef$ = Predef$.MODULE$;
        int n = index.length;
        Range range2 = Range$.MODULE$.apply(0, n);
        if (!range2.isEmpty()) {
            int n2 = range2.start();
            while (true) {
                index[n2] = this.readIndex();
                this.readByte();
                this.readIndex_$eq(this.readNat() + this.readIndex());
                if (n2 == range2.lastElement()) break;
                n2 += range2.step();
            }
        }
        return index;
    }

    private final void writeNatPrefix$1(long x) {
        long y = x >>> 7;
        if (y != 0L) {
            this.writeNatPrefix$1(y);
        }
        this.writeByte((int)(x & 0x7FL | 0x80L));
    }

    private final void patchNatPrefix$1(int x, int pos$1) {
        while (true) {
            this.writeByte(0);
            Array$.MODULE$.copy(this.bytes(), pos$1, this.bytes(), pos$1 + 1, this.writeIndex() - (pos$1 + 1));
            this.bytes()[pos$1] = (byte)(x & 0x7F | 0x80);
            int y = x >>> 7;
            if (y == 0) break;
            x = y;
        }
    }

    public PickleBuffer(byte[] data, int from2, int to2) {
        this.scala$reflect$internal$pickling$PickleBuffer$$data = data;
        this.bytes = data;
        this.readIndex = from2;
        this.writeIndex = to2;
    }
}

