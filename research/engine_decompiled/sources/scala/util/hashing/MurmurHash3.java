/*
 * Decompiled with CFR 0.152.
 */
package scala.util.hashing;

import scala.Product;
import scala.Serializable;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime$;
import scala.util.hashing.Hashing;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(bytes="\u0006\u0001\t\u001dd!B\u0001\u0003\u0001\tA!aC've6,(\u000fS1tQNR!a\u0001\u0003\u0002\u000f!\f7\u000f[5oO*\u0011QAB\u0001\u0005kRLGNC\u0001\b\u0003\u0015\u00198-\u00197b'\t\u0001\u0011\u0002\u0005\u0002\u000b\u00175\ta!\u0003\u0002\r\r\t1\u0011I\\=SK\u001aDQA\u0004\u0001\u0005\u0002A\ta\u0001P5oSRt4\u0001\u0001\u000b\u0002#A\u0011!\u0003A\u0007\u0002\u0005!)A\u0003\u0001C\u0003+\u0005\u0019Q.\u001b=\u0015\u0007YI2\u0004\u0005\u0002\u000b/%\u0011\u0001D\u0002\u0002\u0004\u0013:$\b\"\u0002\u000e\u0014\u0001\u00041\u0012\u0001\u00025bg\"DQ\u0001H\nA\u0002Y\tA\u0001Z1uC\")a\u0004\u0001C\u0003?\u00059Q.\u001b=MCN$Hc\u0001\f!C!)!$\ba\u0001-!)A$\ba\u0001-!)1\u0005\u0001C\u0003I\u0005aa-\u001b8bY&TX\rS1tQR\u0019a#\n\u0014\t\u000bi\u0011\u0003\u0019\u0001\f\t\u000b\u001d\u0012\u0003\u0019\u0001\f\u0002\r1,gn\u001a;i\u0011\u0015I\u0003\u0001\"\u0004+\u0003%\tg/\u00197b]\u000eDW\r\u0006\u0002\u0017W!)!\u0004\u000ba\u0001-!)Q\u0006\u0001C\u0003]\u0005Y\u0001O]8ek\u000e$\b*Y:i)\r1r\u0006\u000e\u0005\u0006a1\u0002\r!M\u0001\u0002qB\u0011!BM\u0005\u0003g\u0019\u0011q\u0001\u0015:pIV\u001cG\u000fC\u00036Y\u0001\u0007a#\u0001\u0003tK\u0016$\u0007\"B\u001c\u0001\t\u000bA\u0014AC:ue&tw\rS1tQR\u0019a#\u000f\"\t\u000bi2\u0004\u0019A\u001e\u0002\u0007M$(\u000f\u0005\u0002=\u007f9\u0011!\"P\u0005\u0003}\u0019\ta\u0001\u0015:fI\u00164\u0017B\u0001!B\u0005\u0019\u0019FO]5oO*\u0011aH\u0002\u0005\u0006kY\u0002\rA\u0006\u0005\u0006\t\u0002!)!R\u0001\u000ek:|'\u000fZ3sK\u0012D\u0015m\u001d5\u0015\u0007Y1%\u000bC\u0003H\u0007\u0002\u0007\u0001*\u0001\u0002ygB\u0019\u0011\nT(\u000f\u0005)Q\u0015BA&\u0007\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0014(\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016T!a\u0013\u0004\u0011\u0005)\u0001\u0016BA)\u0007\u0005\r\te.\u001f\u0005\u0006k\r\u0003\rA\u0006\u0005\u0006)\u0002!)!V\u0001\f_J$WM]3e\u0011\u0006\u001c\b\u000eF\u0002\u0017-^CQaR*A\u0002!CQ!N*A\u0002YAQ!\u0017\u0001\u0005\u0006i\u000b\u0011\"\u0019:sCfD\u0015m\u001d5\u0016\u0005m\u001bGc\u0001\f][\")Q\f\u0017a\u0001=\u0006\t\u0011\rE\u0002\u000b?\u0006L!\u0001\u0019\u0004\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\t\u001cG\u0002\u0001\u0003\nIb\u0003\u000b\u0011!AC\u0002\u0015\u0014\u0011\u0001V\t\u0003M>\u0003\"AC4\n\u0005!4!a\u0002(pi\"Lgn\u001a\u0015\u0003G*\u0004\"AC6\n\u000514!aC:qK\u000eL\u0017\r\\5{K\u0012DQ!\u000e-A\u0002YAQa\u001c\u0001\u0005\u0006A\f\u0011BY=uKND\u0015m\u001d5\u0015\u0007Y\th\u000fC\u0003\u001d]\u0002\u0007!\u000fE\u0002\u000b?N\u0004\"A\u0003;\n\u0005U4!\u0001\u0002\"zi\u0016DQ!\u000e8A\u0002YAQ\u0001\u001f\u0001\u0005\u0006e\f\u0001\u0002\\5ti\"\u000b7\u000f\u001b\u000b\u0005-i\fy\u0001C\u0003Ho\u0002\u00071\u0010M\u0002}\u0003\u0017\u0001R!`A\u0003\u0003\u0013i\u0011A \u0006\u0004\u007f\u0006\u0005\u0011!C5n[V$\u0018M\u00197f\u0015\r\t\u0019AB\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0004}\n!A*[:u!\r\u0011\u00171\u0002\u0003\u000b\u0003\u001bQ\u0018\u0011!A\u0001\u0006\u0003)'aA0%c!)Qg\u001ea\u0001-\u001d9\u00111\u0003\u0002\t\u0002\u0005U\u0011aC've6,(\u000fS1tQN\u00022AEA\f\r\u0019\t!\u0001#\u0001\u0002\u001aM\u0019\u0011qC\t\t\u000f9\t9\u0002\"\u0001\u0002\u001eQ\u0011\u0011Q\u0003\u0005\u000b\u0003C\t9B1A\u0005\u0006\u0005\r\u0012!C1se\u0006L8+Z3e+\t\t)c\u0004\u0002\u0002(u!Ah\u0002&b\u0011%\tY#a\u0006!\u0002\u001b\t)#\u0001\u0006beJ\f\u0017pU3fI\u0002B!\"a\f\u0002\u0018\t\u0007IQAA\u0019\u0003)\u0019HO]5oON+W\rZ\u000b\u0003\u0003gy!!!\u000e\u001e\t]X}P5\u0005\n\u0003s\t9\u0002)A\u0007\u0003g\t1b\u001d;sS:<7+Z3eA!Q\u0011QHA\f\u0005\u0004%)!a\u0010\u0002\u0017A\u0014x\u000eZ;diN+W\rZ\u000b\u0003\u0003\u0003z!!a\u0011\u001e\t)w0X0\u0005\n\u0003\u000f\n9\u0002)A\u0007\u0003\u0003\nA\u0002\u001d:pIV\u001cGoU3fI\u0002B!\"a\u0013\u0002\u0018\t\u0007IQAA'\u00035\u0019\u00180\\7fiJL7mU3fIV\u0011\u0011qJ\b\u0003\u0003#jB!.Jx^#I\u0011QKA\fA\u00035\u0011qJ\u0001\u000fgflW.\u001a;sS\u000e\u001cV-\u001a3!\u0011)\tI&a\u0006C\u0002\u0013\u0015\u00111L\u0001\u0010iJ\fg/\u001a:tC\ndWmU3fIV\u0011\u0011QL\b\u0003\u0003?jBa:\u001e\f,!I\u00111MA\fA\u00035\u0011QL\u0001\u0011iJ\fg/\u001a:tC\ndWmU3fI\u0002B!\"a\u001a\u0002\u0018\t\u0007IQAA5\u0003\u001d\u0019X-]*fK\u0012,\u0012A\u0006\u0005\t\u0003[\n9\u0002)A\u0007-\u0005A1/Z9TK\u0016$\u0007\u0005\u0003\u0006\u0002r\u0005]!\u0019!C\u0003\u0003S\nq!\\1q'\u0016,G\r\u0003\u0005\u0002v\u0005]\u0001\u0015!\u0004\u0017\u0003!i\u0017\r]*fK\u0012\u0004\u0003BCA=\u0003/\u0011\r\u0011\"\u0002\u0002j\u000591/\u001a;TK\u0016$\u0007\u0002CA?\u0003/\u0001\u000bQ\u0002\f\u0002\u0011M,GoU3fI\u0002Bq!WA\f\t\u0003\t\t)\u0006\u0003\u0002\u0004\u0006-Ec\u0001\f\u0002\u0006\"9Q,a A\u0002\u0005\u001d\u0005\u0003\u0002\u0006`\u0003\u0013\u00032AYAF\t)!\u0017q\u0010Q\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0004\u0003\u0017S\u0007bB8\u0002\u0018\u0011\u0005\u0011\u0011\u0013\u000b\u0004-\u0005M\u0005B\u0002\u000f\u0002\u0010\u0002\u0007!\u000fC\u0004U\u0003/!\t!a&\u0015\u0007Y\tI\n\u0003\u0004H\u0003+\u0003\r\u0001\u0013\u0005\b[\u0005]A\u0011AAO)\r1\u0012q\u0014\u0005\u0007a\u0005m\u0005\u0019A\u0019\t\u000f]\n9\u0002\"\u0001\u0002$R\u0019a#!*\t\rA\n\t\u000b1\u0001<\u0011\u001d!\u0015q\u0003C\u0001\u0003S#2AFAV\u0011\u00199\u0015q\u0015a\u0001\u0011\"A\u0011qVA\f\t\u0003\t\t,A\u0004tKFD\u0015m\u001d5\u0015\u0007Y\t\u0019\fC\u0004H\u0003[\u0003\r!!.1\t\u0005]\u0016\u0011\u0019\t\u0007\u0003s\u000bY,a0\u000e\u0005\u0005\u0005\u0011\u0002BA_\u0003\u0003\u00111aU3r!\r\u0011\u0017\u0011\u0019\u0003\f\u0003\u0007\f\u0019,!A\u0001\u0002\u000b\u0005QMA\u0002`IIB\u0001\"a2\u0002\u0018\u0011\u0005\u0011\u0011Z\u0001\b[\u0006\u0004\b*Y:i)\r1\u00121\u001a\u0005\b\u000f\u0006\u0015\u0007\u0019AAga\u0019\ty-a6\u0002^BA\u0011\u0011XAi\u0003+\fY.\u0003\u0003\u0002T\u0006\u0005!aA'baB\u0019!-a6\u0005\u0017\u0005e\u00171ZA\u0001\u0002\u0003\u0015\t!\u001a\u0002\u0004?\u0012\u001a\u0004c\u00012\u0002^\u0012Y\u0011q\\Af\u0003\u0003\u0005\tQ!\u0001f\u0005\ryF\u0005\u000e\u0005\t\u0003G\f9\u0002\"\u0001\u0002f\u000691/\u001a;ICNDGc\u0001\f\u0002h\"9q)!9A\u0002\u0005%\b\u0007BAv\u0003g\u0004b!!/\u0002n\u0006E\u0018\u0002BAx\u0003\u0003\u00111aU3u!\r\u0011\u00171\u001f\u0003\f\u0003k\f9/!A\u0001\u0002\u000b\u0005QMA\u0002`IU2q!!?\u0002\u0018\u0001\tYP\u0001\u0007BeJ\f\u0017\u0010S1tQ&tw-\u0006\u0003\u0002~\n%1#BA|\u0013\u0005}\b#\u0002\n\u0003\u0002\t\u0015\u0011b\u0001B\u0002\u0005\t9\u0001*Y:iS:<\u0007\u0003\u0002\u0006`\u0005\u000f\u00012A\u0019B\u0005\t)!\u0017q\u001fQ\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0004\u0005\u0013Q\u0007b\u0002\b\u0002x\u0012\u0005!q\u0002\u000b\u0003\u0005#\u0001bAa\u0005\u0002x\n\u001dQBAA\f\u0011\u001dQ\u0012q\u001fC\u0001\u0005/!2A\u0006B\r\u0011\u001di&Q\u0003a\u0001\u0005\u000bA\u0001B!\b\u0002\u0018\u0011\u0005!qD\u0001\rCJ\u0014\u0018-\u001f%bg\"LgnZ\u000b\u0005\u0005C\u00119#\u0006\u0002\u0003$A1!1CA|\u0005K\u00012A\u0019B\u0014\t)!'1\u0004Q\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0004\u0005OQ\u0007\u0002\u0003B\u0017\u0003/!\tAa\f\u0002\u0019\tLH/Z:ICND\u0017N\\4\u0016\u0005\tE\"#\u0002B\u001a\u0013\t]ba\u0002B\u001b\u0005W\u0001!\u0011\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0005%\t\u0005!\u000f\u0003\u0005\u0003<\u0005]A\u0011\u0001B\u001f\u00039y'\u000fZ3sK\u0012D\u0015m\u001d5j]\u001e,\"Aa\u0010\u0013\u000b\t\u0005\u0013Ba\u0011\u0007\u000f\tU\"\u0011\b\u0001\u0003@A!!C!\u0001I\u0011!\u00119%a\u0006\u0005\u0002\t%\u0013A\u00049s_\u0012,8\r\u001e%bg\"LgnZ\u000b\u0003\u0005\u0017\u0012RA!\u0014\n\u0005\u001f2qA!\u000e\u0003F\u0001\u0011Y\u0005\u0005\u0003\u0013\u0005\u0003\t\u0004\u0002\u0003B*\u0003/!\tA!\u0016\u0002\u001bM$(/\u001b8h\u0011\u0006\u001c\b.\u001b8h+\t\u00119FE\u0003\u0003Z%\u0011YFB\u0004\u00036\tE\u0003Aa\u0016\u0011\tI\u0011\ta\u000f\u0005\t\u0005?\n9\u0002\"\u0001\u0003b\u0005\u0001RO\\8sI\u0016\u0014X\r\u001a%bg\"LgnZ\u000b\u0003\u0005G\u0012RA!\u001a\n\u0005\u00072qA!\u000e\u0003^\u0001\u0011\u0019\u0007")
public class MurmurHash3 {
    public static Object unorderedHashing() {
        return MurmurHash3$.MODULE$.unorderedHashing();
    }

    public static Object stringHashing() {
        return MurmurHash3$.MODULE$.stringHashing();
    }

    public static Object productHashing() {
        return MurmurHash3$.MODULE$.productHashing();
    }

    public static Object orderedHashing() {
        return MurmurHash3$.MODULE$.orderedHashing();
    }

    public static Object bytesHashing() {
        return MurmurHash3$.MODULE$.bytesHashing();
    }

    public static <T> ArrayHashing<T> arrayHashing() {
        return MurmurHash3$.MODULE$.arrayHashing();
    }

    public static int setHash(Set<?> set) {
        return MurmurHash3$.MODULE$.setHash(set);
    }

    public static int mapHash(Map<?, ?> map2) {
        return MurmurHash3$.MODULE$.mapHash(map2);
    }

    public static int seqHash(Seq<?> seq) {
        return MurmurHash3$.MODULE$.seqHash(seq);
    }

    public static int setSeed() {
        return MurmurHash3$.MODULE$.setSeed();
    }

    public static int mapSeed() {
        return MurmurHash3$.MODULE$.mapSeed();
    }

    public static int seqSeed() {
        return MurmurHash3$.MODULE$.seqSeed();
    }

    public static int traversableSeed() {
        return MurmurHash3$.MODULE$.traversableSeed();
    }

    public static int symmetricSeed() {
        return MurmurHash3$.MODULE$.symmetricSeed();
    }

    public static int productSeed() {
        return MurmurHash3$.MODULE$.productSeed();
    }

    public static int stringSeed() {
        return MurmurHash3$.MODULE$.stringSeed();
    }

    public static int arraySeed() {
        return MurmurHash3$.MODULE$.arraySeed();
    }

    public final int mix(int hash, int data) {
        int h = this.mixLast(hash, data);
        return Integer.rotateLeft(h, 13) * 5 + -430675100;
    }

    public final int mixLast(int hash, int data) {
        int k = Integer.rotateLeft(data * -862048943, 15) * 461845907;
        return hash ^ k;
    }

    public final int finalizeHash(int hash, int length) {
        return this.avalanche(hash ^ length);
    }

    private final int avalanche(int hash) {
        int h = (hash ^ hash >>> 16) * -2048144789;
        h = (h ^ h >>> 13) * -1028477387;
        return h ^ h >>> 16;
    }

    public final int productHash(Product x, int seed) {
        int n;
        int arr = x.productArity();
        if (arr == 0) {
            n = x.productPrefix().hashCode();
        } else {
            int h = seed;
            for (int i = 0; i < arr; ++i) {
                h = this.mix(h, ScalaRunTime$.MODULE$.hash(x.productElement(i)));
            }
            n = this.finalizeHash(h, arr);
        }
        return n;
    }

    public final int stringHash(String str, int seed) {
        int h = seed;
        int i = 0;
        while (i + 1 < str.length()) {
            int data = (str.charAt(i) << 16) + str.charAt(i + 1);
            h = this.mix(h, data);
            i += 2;
        }
        if (i < str.length()) {
            h = this.mixLast(h, str.charAt(i));
        }
        return this.finalizeHash(h, str.length());
    }

    public final int unorderedHash(TraversableOnce<Object> xs, int seed) {
        IntRef a = IntRef.create(0);
        IntRef b = IntRef.create(0);
        IntRef n = IntRef.create(0);
        IntRef c = IntRef.create(1);
        xs.foreach(new Serializable(this, a, b, n, c){
            public static final long serialVersionUID = 0L;
            private final IntRef a$1;
            private final IntRef b$1;
            private final IntRef n$1;
            private final IntRef c$1;

            public final void apply(Object x) {
                int h = ScalaRunTime$.MODULE$.hash(x);
                this.a$1.elem += h;
                this.b$1.elem ^= h;
                if (h != 0) {
                    this.c$1.elem *= h;
                }
                ++this.n$1.elem;
            }
            {
                this.a$1 = a$1;
                this.b$1 = b$1;
                this.n$1 = n$1;
                this.c$1 = c$1;
            }
        });
        int h = this.mix(seed, a.elem);
        h = this.mix(h, b.elem);
        h = this.mixLast(h, c.elem);
        return this.finalizeHash(h, n.elem);
    }

    public final int orderedHash(TraversableOnce<Object> xs, int seed) {
        IntRef n = IntRef.create(0);
        IntRef h = IntRef.create(seed);
        xs.foreach(new Serializable(this, n, h){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ MurmurHash3 $outer;
            private final IntRef n$2;
            private final IntRef h$1;

            public final void apply(Object x) {
                this.h$1.elem = this.$outer.mix(this.h$1.elem, ScalaRunTime$.MODULE$.hash(x));
                ++this.n$2.elem;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.n$2 = n$2;
                this.h$1 = h$1;
            }
        });
        return this.finalizeHash(h.elem, n.elem);
    }

    public final <T> int arrayHash(Object a, int seed) {
        int h = seed;
        for (int i = 0; i < ScalaRunTime$.MODULE$.array_length(a); ++i) {
            h = this.mix(h, ScalaRunTime$.MODULE$.hash(ScalaRunTime$.MODULE$.array_apply(a, i)));
        }
        return this.finalizeHash(h, ScalaRunTime$.MODULE$.array_length(a));
    }

    public final int bytesHash(byte[] data, int seed) {
        int len;
        int h = seed;
        int i = 0;
        for (len = data.length; len >= 4; len -= 4) {
            int k = data[i + 0] & 0xFF;
            k = k | (data[i + 1] & 0xFF) << 8 | (data[i + 2] & 0xFF) << 16 | (data[i + 3] & 0xFF) << 24;
            h = this.mix(h, k);
            i += 4;
        }
        int k = 0;
        if (len == 3) {
            k = 0 ^ (data[i + 2] & 0xFF) << 16;
        }
        if (len >= 2) {
            k ^= (data[i + 1] & 0xFF) << 8;
        }
        if (len >= 1) {
            h = this.mixLast(h, k ^= data[i + 0] & 0xFF);
        }
        return this.finalizeHash(h, data.length);
    }

    /*
     * WARNING - void declaration
     */
    public final int listHash(List<?> xs, int seed) {
        int n = 0;
        int h = seed;
        List<?> elems = xs;
        while (!elems.isEmpty()) {
            void var3_3;
            int n2;
            List list2;
            Object head2 = list2.head();
            List tail = (List)list2.tail();
            n2 = this.mix(n2, ScalaRunTime$.MODULE$.hash(head2));
            ++var3_3;
            list2 = tail;
        }
        return this.finalizeHash(h, n);
    }

    public final int arrayHash$mZc$sp(boolean[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, a[i] ? 1231 : 1237);
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mBc$sp(byte[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, a[i]);
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mCc$sp(char[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, a[i]);
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mDc$sp(double[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mFc$sp(float[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mIc$sp(int[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, a[i]);
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mJc$sp(long[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, ScalaRunTime$.MODULE$.hash(a[i]));
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mSc$sp(short[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, a[i]);
        }
        return this.finalizeHash(h, a.length);
    }

    public final int arrayHash$mVc$sp(BoxedUnit[] a, int seed) {
        int h = seed;
        for (int i = 0; i < a.length; ++i) {
            h = this.mix(h, 0);
        }
        return this.finalizeHash(h, a.length);
    }

    public static class ArrayHashing<T>
    implements Hashing<Object> {
        @Override
        public int hash(Object a) {
            return MurmurHash3$.MODULE$.arrayHash(a);
        }

        public int hash$mcZ$sp(boolean[] a) {
            return this.hash(a);
        }

        public int hash$mcB$sp(byte[] a) {
            return this.hash(a);
        }

        public int hash$mcC$sp(char[] a) {
            return this.hash(a);
        }

        public int hash$mcD$sp(double[] a) {
            return this.hash(a);
        }

        public int hash$mcF$sp(float[] a) {
            return this.hash(a);
        }

        public int hash$mcI$sp(int[] a) {
            return this.hash(a);
        }

        public int hash$mcJ$sp(long[] a) {
            return this.hash(a);
        }

        public int hash$mcS$sp(short[] a) {
            return this.hash(a);
        }

        public int hash$mcV$sp(BoxedUnit[] a) {
            return this.hash(a);
        }
    }
}

