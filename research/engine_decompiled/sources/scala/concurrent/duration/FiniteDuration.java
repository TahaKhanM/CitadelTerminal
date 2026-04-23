/*
 * Decompiled with CFR 0.152.
 */
package scala.concurrent.duration;

import java.util.concurrent.TimeUnit;
import scala.MatchError;
import scala.Predef$;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.duration.Deadline;
import scala.concurrent.duration.Deadline$;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration$;
import scala.math.package$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;

@ScalaSignature(bytes="\u0006\u0001\t\u0015t!B\u0001\u0003\u0011\u0003I\u0011A\u0004$j]&$X\rR;sCRLwN\u001c\u0006\u0003\u0007\u0011\t\u0001\u0002Z;sCRLwN\u001c\u0006\u0003\u000b\u0019\t!bY8oGV\u0014(/\u001a8u\u0015\u00059\u0011!B:dC2\f7\u0001\u0001\t\u0003\u0015-i\u0011A\u0001\u0004\u0006\u0019\tA\t!\u0004\u0002\u000f\r&t\u0017\u000e^3EkJ\fG/[8o'\rYaB\u0005\t\u0003\u001fAi\u0011AB\u0005\u0003#\u0019\u0011a!\u00118z%\u00164\u0007CA\b\u0014\u0013\t!bA\u0001\u0007TKJL\u0017\r\\5{C\ndW\rC\u0003\u0017\u0017\u0011\u0005q#\u0001\u0004=S:LGO\u0010\u000b\u0002\u0013\u001d)\u0011d\u0003E\u00025\u00059b)\u001b8ji\u0016$UO]1uS>t\u0017j](sI\u0016\u0014X\r\u001a\t\u00037qi\u0011a\u0003\u0004\u0006;-A\tA\b\u0002\u0018\r&t\u0017\u000e^3EkJ\fG/[8o\u0013N|%\u000fZ3sK\u0012\u001c2\u0001H\u0010(!\t\u0001S%D\u0001\"\u0015\t\u00113%\u0001\u0003mC:<'\"\u0001\u0013\u0002\t)\fg/Y\u0005\u0003M\u0005\u0012aa\u00142kK\u000e$\bc\u0001\u00151g9\u0011\u0011F\f\b\u0003U5j\u0011a\u000b\u0006\u0003Y!\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005=2\u0011a\u00029bG.\fw-Z\u0005\u0003cI\u0012\u0001b\u0014:eKJLgn\u001a\u0006\u0003_\u0019\u0001\"A\u0003\u001b\u0007\t1\u0011!!N\n\u0003iY\u0002\"AC\u001c\n\u0005a\u0012!\u0001\u0003#ve\u0006$\u0018n\u001c8\t\u0011i\"$Q1A\u0005\u0002m\na\u0001\\3oORDW#\u0001\u001f\u0011\u0005=i\u0014B\u0001 \u0007\u0005\u0011auN\\4\t\u0011\u0001#$\u0011!Q\u0001\nq\nq\u0001\\3oORD\u0007\u0005\u0003\u0005Ci\t\u0015\r\u0011\"\u0001D\u0003\u0011)h.\u001b;\u0016\u0003\u0011\u0003\"!R$\u000f\u0005)1\u0015BA\u0018\u0003\u0013\tA\u0015J\u0001\u0005US6,WK\\5u\u0015\ty#\u0001\u0003\u0005Li\t\u0005\t\u0015!\u0003E\u0003\u0015)h.\u001b;!\u0011\u00151B\u0007\"\u0001N)\r\u0019dj\u0014\u0005\u0006u1\u0003\r\u0001\u0010\u0005\u0006\u00052\u0003\r\u0001\u0012\u0005\u0007#R\u0002K\u0011\u0002*\u0002\u000f\t|WO\u001c3fIR\u00111K\u0016\t\u0003\u001fQK!!\u0016\u0004\u0003\u000f\t{w\u000e\\3b]\")q\u000b\u0015a\u0001y\u0005\u0019Q.\u0019=\t\u000be#D\u0011A\u001e\u0002\u000fQ|g*\u00198pg\")1\f\u000eC\u0001w\u0005AAo\\'jGJ|7\u000fC\u0003^i\u0011\u00051(\u0001\u0005u_6KG\u000e\\5t\u0011\u0015yF\u0007\"\u0001<\u0003%!xnU3d_:$7\u000fC\u0003bi\u0011\u00051(A\u0005u_6Kg.\u001e;fg\")1\r\u000eC\u0001w\u00059Ao\u001c%pkJ\u001c\b\"B35\t\u0003Y\u0014A\u0002;p\t\u0006L8\u000fC\u0003hi\u0011\u0005\u0001.\u0001\u0004u_Vs\u0017\u000e\u001e\u000b\u0003S2\u0004\"a\u00046\n\u0005-4!A\u0002#pk\ndW\rC\u0003nM\u0002\u0007A)A\u0001v\u0011\u0015yG\u0007\"\u0001q\u0003\u001d1'o\\7O_^,\u0012!\u001d\t\u0003\u0015IL!a\u001d\u0002\u0003\u0011\u0011+\u0017\r\u001a7j]\u0016Da!\u001e\u001b!\n\u00131\u0018AC;oSR\u001cFO]5oOV\tq\u000f\u0005\u0002!q&\u0011\u00110\t\u0002\u0007'R\u0014\u0018N\\4\t\u000bm$D\u0011\t?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u001e\u0005\u0006}R\"\ta`\u0001\bG>l\u0007/\u0019:f)\u0011\t\t!a\u0002\u0011\u0007=\t\u0019!C\u0002\u0002\u0006\u0019\u00111!\u00138u\u0011\u0019\tI! a\u0001m\u0005)q\u000e\u001e5fe\"A\u0011Q\u0002\u001b!\n\u0013\ty!A\u0004tC\u001a,\u0017\t\u001a3\u0015\u000bq\n\t\"!\u0006\t\u000f\u0005M\u00111\u0002a\u0001y\u0005\t\u0011\rC\u0004\u0002\u0018\u0005-\u0001\u0019\u0001\u001f\u0002\u0003\tD\u0001\"a\u00075A\u0013%\u0011QD\u0001\u0004C\u0012$G#B\u001a\u0002 \u0005\r\u0002bBA\u0011\u00033\u0001\r\u0001P\u0001\f_RDWM\u001d'f]\u001e$\b\u000eC\u0004\u0002&\u0005e\u0001\u0019\u0001#\u0002\u0013=$\b.\u001a:V]&$\bbBA\u0015i\u0011\u0005\u00111F\u0001\u0006IAdWo\u001d\u000b\u0004m\u00055\u0002bBA\u0005\u0003O\u0001\rA\u000e\u0005\b\u0003c!D\u0011AA\u001a\u0003\u0019!S.\u001b8vgR\u0019a'!\u000e\t\u000f\u0005%\u0011q\u0006a\u0001m!9\u0011\u0011\b\u001b\u0005\u0002\u0005m\u0012A\u0002\u0013uS6,7\u000fF\u00027\u0003{Aq!a\u0010\u00028\u0001\u0007\u0011.\u0001\u0004gC\u000e$xN\u001d\u0005\b\u0003\u0007\"D\u0011AA#\u0003\u0011!C-\u001b<\u0015\u0007Y\n9\u0005C\u0004\u0002J\u0005\u0005\u0003\u0019A5\u0002\u000f\u0011Lg/[:pe\"A\u0011Q\n\u001b!\n\u0013\ty%A\u0005nS:,8OW3s_V\t\u0011\u000eC\u0004\u0002DQ\"\t!a\u0015\u0015\u0007%\f)\u0006C\u0004\u0002J\u0005E\u0003\u0019\u0001\u001c\t\u000f\u0005%B\u0007\"\u0001\u0002ZQ\u00191'a\u0017\t\u000f\u0005%\u0011q\u000ba\u0001g!9\u0011\u0011\u0007\u001b\u0005\u0002\u0005}CcA\u001a\u0002b!9\u0011\u0011BA/\u0001\u0004\u0019\u0004bBA3i\u0011\u0005\u0011qM\u0001\u0005a2,8\u000fF\u00024\u0003SBq!!\u0003\u0002d\u0001\u00071\u0007C\u0004\u0002nQ\"\t!a\u001c\u0002\u000b5Lg.^:\u0015\u0007M\n\t\bC\u0004\u0002\n\u0005-\u0004\u0019A\u001a\t\u000f\u0005UD\u0007\"\u0001\u0002x\u0005\u0019Q.\u001b8\u0015\u0007M\nI\bC\u0004\u0002\n\u0005M\u0004\u0019A\u001a\t\r]#D\u0011AA?)\r\u0019\u0014q\u0010\u0005\b\u0003\u0013\tY\b1\u00014\u0011\u001d\t\u0019\u0005\u000eC\u0001\u0003\u0007#2aMAC\u0011\u001d\tI%!!A\u0002qBq!!\u000f5\t\u0003\tI\tF\u00024\u0003\u0017Cq!a\u0010\u0002\b\u0002\u0007A\bC\u0004\u0002\u0010R\"I!!%\u0002\u000fM\fg-Z'vYR)A(a%\u0002\u0018\"9\u0011QSAG\u0001\u0004a\u0014AA0b\u0011\u001d\tI*!$A\u0002q\n!a\u00182\t\u000f\u0005uE\u0007\"\u0001\u0002 \u0006\u0019A-\u001b<\u0015\u0007M\n\t\u000bC\u0004\u0002J\u0005m\u0005\u0019\u0001\u001f\t\u000f\u0005\u0015F\u0007\"\u0001\u0002(\u0006\u0019Q.\u001e7\u0015\u0007M\nI\u000bC\u0004\u0002@\u0005\r\u0006\u0019\u0001\u001f\t\u000f\u00055F\u0007\"\u0001\u00020\u0006aQO\\1ss~#S.\u001b8vgV\t1\u0007C\u0004\u00024R\")!!.\u0002\u0011%\u001ch)\u001b8ji\u0016$\u0012a\u0015\u0005\b\u0003s#DQAA^\u0003)!xnQ8beN,7\u000f^\u000b\u0002m!9\u0011q\u0018\u001b\u0005B\u0005\u0005\u0017AB3rk\u0006d7\u000fF\u0002T\u0003\u0007D\u0001\"!\u0003\u0002>\u0002\u0007\u0011Q\u0019\t\u0004\u001f\u0005\u001d\u0017bAAe\r\t\u0019\u0011I\\=\t\u000f\u00055G\u0007\"\u0011\u0002P\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0002!1a\u0003\bC\u0001\u0003'$\u0012A\u0007\u0005\u0007}r!\t!a6\u0015\r\u0005\u0005\u0011\u0011\\An\u0011\u001d\t\u0019\"!6A\u0002MBq!a\u0006\u0002V\u0002\u00071\u0007C\u0005\u0002`r\t\t\u0011\"\u0003\u0002b\u0006Y!/Z1e%\u0016\u001cx\u000e\u001c<f)\u0005y\u0002bBAs\u0017\u0011\u0005\u0011q]\u0001\u0006CB\u0004H.\u001f\u000b\u0006g\u0005%\u00181\u001e\u0005\u0007u\u0005\r\b\u0019\u0001\u001f\t\r\t\u000b\u0019\u000f1\u0001E\u0011\u001d\t)o\u0003C\u0001\u0003_$RaMAy\u0003gDaAOAw\u0001\u0004a\u0004b\u0002\"\u0002n\u0002\u0007\u0011Q\u001f\t\u0005\u0003o\fiPD\u0002\u0010\u0003sL1!a?\u0007\u0003\u0019\u0001&/\u001a3fM&\u0019\u00110a@\u000b\u0007\u0005mh\u0001C\u0005\u0003\u0004-\u0011\r\u0011\"\u0004\u0003\u0006\u00051Q.\u0019=`]N,\"Aa\u0002\u0010\u0005\t%a\u0004C@\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\t\u0011\t51\u0002)A\u0007\u0005\u000f\tq!\\1y?:\u001c\b\u0005C\u0005\u0003\u0012-\u0011\r\u0011\"\u0004\u0003\u0014\u00059Q.\u0019=`\u0005X\u001eXC\u0001B\u000b\u001f\t\u00119BH\u0004!\tp-;}U|\t\u0011\tm1\u0002)A\u0007\u0005+\t\u0001\"\\1y?\n/<\u000f\t\u0005\n\u0005?Y!\u0019!C\u0007\u0005C\ta!\\1y?6\u001cXC\u0001B\u0012\u001f\t\u0011)C\b\u0004\tGn\u0004.L>\u0005\t\u0005SY\u0001\u0015!\u0004\u0003$\u00059Q.\u0019=`[N\u0004\u0003\"\u0003B\u0017\u0017\t\u0007IQ\u0002B\u0018\u0003\u0015i\u0017\r_0t+\t\u0011\td\u0004\u0002\u00034y)!!Ja~\t!A!qG\u0006!\u0002\u001b\u0011\t$\u0001\u0004nCb|6\u000f\t\u0005\n\u0005wY!\u0019!C\u0007\u0005{\tq!\\1y?6Lg.\u0006\u0002\u0003@=\u0011!\u0011\t\u0010\u0005\u0013%z:\u001f\u0003\u0005\u0003F-\u0001\u000bQ\u0002B \u0003!i\u0017\r_0nS:\u0004\u0003\"\u0003B%\u0017\t\u0007IQ\u0002B&\u0003\u0015i\u0017\r_0i+\t\u0011ie\u0004\u0002\u0003Py\u0019qeF\u0000\t\u0011\tM3\u0002)A\u0007\u0005\u001b\na!\\1y?\"\u0004\u0003\"\u0003B,\u0017\t\u0007IQ\u0002B-\u0003\u0015i\u0017\r_0e+\t\u0011Yf\u0004\u0002\u0003^y\u0019\u0011\u0001i\u0000\t\u0011\t\u00054\u0002)A\u0007\u00057\na!\\1y?\u0012\u0004\u0003\"CAp\u0017\u0005\u0005I\u0011BAq\u0001")
public final class FiniteDuration
extends Duration {
    private final long length;
    private final TimeUnit unit;

    public static FiniteDuration apply(long l, String string2) {
        return FiniteDuration$.MODULE$.apply(l, string2);
    }

    public static FiniteDuration apply(long l, TimeUnit timeUnit) {
        return FiniteDuration$.MODULE$.apply(l, timeUnit);
    }

    @Override
    public long length() {
        return this.length;
    }

    @Override
    public TimeUnit unit() {
        return this.unit;
    }

    private boolean bounded(long max2) {
        return -max2 <= this.length() && this.length() <= max2;
    }

    @Override
    public long toNanos() {
        return this.unit().toNanos(this.length());
    }

    @Override
    public long toMicros() {
        return this.unit().toMicros(this.length());
    }

    @Override
    public long toMillis() {
        return this.unit().toMillis(this.length());
    }

    @Override
    public long toSeconds() {
        return this.unit().toSeconds(this.length());
    }

    @Override
    public long toMinutes() {
        return this.unit().toMinutes(this.length());
    }

    @Override
    public long toHours() {
        return this.unit().toHours(this.length());
    }

    @Override
    public long toDays() {
        return this.unit().toDays(this.length());
    }

    @Override
    public double toUnit(TimeUnit u) {
        return (double)this.toNanos() / (double)TimeUnit.NANOSECONDS.convert(1L, u);
    }

    public Deadline fromNow() {
        return Deadline$.MODULE$.now().$plus(this);
    }

    private String unitString() {
        return new StringBuilder().append((Object)((String)Duration$.MODULE$.timeUnitName().apply(this.unit()))).append((Object)(this.length() == 1L ? "" : "s")).toString();
    }

    public String toString() {
        return new StringBuilder().append((Object)"").append(BoxesRunTime.boxToLong(this.length())).append((Object)" ").append((Object)this.unitString()).toString();
    }

    @Override
    public int compare(Duration other) {
        int n;
        if (other instanceof FiniteDuration) {
            FiniteDuration finiteDuration = (FiniteDuration)other;
            long l = this.toNanos();
            Predef$ predef$ = Predef$.MODULE$;
            n = new RichLong(l).compare(BoxesRunTime.boxToLong(finiteDuration.toNanos()));
        } else {
            n = -other.compare(this);
        }
        return n;
    }

    private long safeAdd(long a, long b) {
        if (b > 0L && a > Long.MAX_VALUE - b || b < 0L && a < Long.MIN_VALUE - b) {
            throw new IllegalArgumentException("integer overflow");
        }
        return a + b;
    }

    private FiniteDuration add(long otherLength, TimeUnit otherUnit) {
        TimeUnit commonUnit = otherUnit.convert(1L, this.unit()) == 0L ? this.unit() : otherUnit;
        long totalLength = this.safeAdd(commonUnit.convert(this.length(), this.unit()), commonUnit.convert(otherLength, otherUnit));
        return new FiniteDuration(totalLength, commonUnit);
    }

    @Override
    public Duration $plus(Duration other) {
        Duration duration;
        if (other instanceof FiniteDuration) {
            FiniteDuration finiteDuration = (FiniteDuration)other;
            duration = this.add(finiteDuration.length(), finiteDuration.unit());
        } else {
            duration = other;
        }
        return duration;
    }

    @Override
    public Duration $minus(Duration other) {
        Duration duration;
        if (other instanceof FiniteDuration) {
            FiniteDuration finiteDuration = (FiniteDuration)other;
            duration = this.add(-finiteDuration.length(), finiteDuration.unit());
        } else {
            duration = other.unary_$minus();
        }
        return duration;
    }

    @Override
    public Duration $times(double factor) {
        return Predef$.MODULE$.double2Double(factor).isInfinite() ? (Predef$.MODULE$.double2Double(factor).isNaN() ? Duration$.MODULE$.Undefined() : (factor > 0.0 ^ this.$less(Duration$.MODULE$.Zero()) ? Duration$.MODULE$.Inf() : Duration$.MODULE$.MinusInf())) : Duration$.MODULE$.fromNanos((double)this.toNanos() * factor);
    }

    @Override
    public Duration $div(double divisor) {
        return Predef$.MODULE$.double2Double(divisor).isInfinite() ? (Predef$.MODULE$.double2Double(divisor).isNaN() ? Duration$.MODULE$.Undefined() : Duration$.MODULE$.Zero()) : Duration$.MODULE$.fromNanos((double)this.toNanos() / divisor);
    }

    private double minusZero() {
        return -0.0;
    }

    @Override
    public double $div(Duration divisor) {
        return divisor.isFinite() ? (double)this.toNanos() / (double)divisor.toNanos() : (divisor == Duration$.MODULE$.Undefined() ? Double.NaN : (this.length() < 0L ^ divisor.$greater(Duration$.MODULE$.Zero()) ? 0.0 : this.minusZero()));
    }

    public FiniteDuration $plus(FiniteDuration other) {
        return this.add(other.length(), other.unit());
    }

    public FiniteDuration $minus(FiniteDuration other) {
        return this.add(-other.length(), other.unit());
    }

    public FiniteDuration plus(FiniteDuration other) {
        return this.$plus(other);
    }

    public FiniteDuration minus(FiniteDuration other) {
        return this.$minus(other);
    }

    public FiniteDuration min(FiniteDuration other) {
        return this.$less(other) ? this : other;
    }

    public FiniteDuration max(FiniteDuration other) {
        return this.$greater(other) ? this : other;
    }

    public FiniteDuration $div(long divisor) {
        return Duration$.MODULE$.fromNanos(this.toNanos() / divisor);
    }

    public FiniteDuration $times(long factor) {
        return new FiniteDuration(this.safeMul(this.length(), factor), this.unit());
    }

    private long safeMul(long _a, long _b) {
        long a = package$.MODULE$.abs(_a);
        long b = package$.MODULE$.abs(_b);
        if (Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(b) < 64) {
            throw new IllegalArgumentException("multiplication overflow");
        }
        long product2 = a * b;
        if (product2 < 0L) {
            throw new IllegalArgumentException("multiplication overflow");
        }
        return a == _a ^ b == _b ? -product2 : product2;
    }

    public FiniteDuration div(long divisor) {
        return this.$div(divisor);
    }

    public FiniteDuration mul(long factor) {
        return this.$times(factor);
    }

    @Override
    public FiniteDuration unary_$minus() {
        return Duration$.MODULE$.apply(-this.length(), this.unit());
    }

    @Override
    public final boolean isFinite() {
        return true;
    }

    @Override
    public final Duration toCoarsest() {
        TimeUnit timeUnit = this.unit();
        return timeUnit != null && ((Object)((Object)timeUnit)).equals((Object)TimeUnit.DAYS) || this.length() == 0L ? this : this.loop$1(this.length(), this.unit());
    }

    public boolean equals(Object other) {
        boolean bl;
        if (other instanceof FiniteDuration) {
            FiniteDuration finiteDuration = (FiniteDuration)other;
            bl = this.toNanos() == finiteDuration.toNanos();
        } else {
            bl = super.equals(other);
        }
        return bl;
    }

    public int hashCode() {
        return (int)this.toNanos();
    }

    private final FiniteDuration coarserOrThis$1(TimeUnit coarser, int divider, long length$1, TimeUnit unit$1) {
        FiniteDuration finiteDuration;
        if (length$1 % (long)divider == 0L) {
            finiteDuration = this.loop$1(length$1 / (long)divider, coarser);
        } else {
            TimeUnit timeUnit = unit$1;
            TimeUnit timeUnit2 = this.unit();
            finiteDuration = !(timeUnit != null ? !((Object)((Object)timeUnit)).equals((Object)timeUnit2) : timeUnit2 != null) ? this : FiniteDuration$.MODULE$.apply(length$1, unit$1);
        }
        return finiteDuration;
    }

    private final FiniteDuration loop$1(long length, TimeUnit unit) {
        block9: {
            FiniteDuration finiteDuration;
            block3: {
                block8: {
                    block7: {
                        block6: {
                            block5: {
                                block4: {
                                    block2: {
                                        if (!((Object)((Object)TimeUnit.DAYS)).equals((Object)unit)) break block2;
                                        finiteDuration = FiniteDuration$.MODULE$.apply(length, unit);
                                        break block3;
                                    }
                                    if (!((Object)((Object)TimeUnit.HOURS)).equals((Object)unit)) break block4;
                                    finiteDuration = this.coarserOrThis$1(TimeUnit.DAYS, 24, length, unit);
                                    break block3;
                                }
                                if (!((Object)((Object)TimeUnit.MINUTES)).equals((Object)unit)) break block5;
                                finiteDuration = this.coarserOrThis$1(TimeUnit.HOURS, 60, length, unit);
                                break block3;
                            }
                            if (!((Object)((Object)TimeUnit.SECONDS)).equals((Object)unit)) break block6;
                            finiteDuration = this.coarserOrThis$1(TimeUnit.MINUTES, 60, length, unit);
                            break block3;
                        }
                        if (!((Object)((Object)TimeUnit.MILLISECONDS)).equals((Object)unit)) break block7;
                        finiteDuration = this.coarserOrThis$1(TimeUnit.SECONDS, 1000, length, unit);
                        break block3;
                    }
                    if (!((Object)((Object)TimeUnit.MICROSECONDS)).equals((Object)unit)) break block8;
                    finiteDuration = this.coarserOrThis$1(TimeUnit.MILLISECONDS, 1000, length, unit);
                    break block3;
                }
                if (!((Object)((Object)TimeUnit.NANOSECONDS)).equals((Object)unit)) break block9;
                finiteDuration = this.coarserOrThis$1(TimeUnit.MICROSECONDS, 1000, length, unit);
            }
            return finiteDuration;
        }
        throw new MatchError((Object)unit);
    }

    public FiniteDuration(long length, TimeUnit unit) {
        long v;
        this.length = length;
        this.unit = unit;
        boolean bl = ((Object)((Object)TimeUnit.NANOSECONDS)).equals((Object)unit) ? this.bounded(Long.MAX_VALUE) : (((Object)((Object)TimeUnit.MICROSECONDS)).equals((Object)unit) ? this.bounded(9223372036854775L) : (((Object)((Object)TimeUnit.MILLISECONDS)).equals((Object)unit) ? this.bounded(9223372036854L) : (((Object)((Object)TimeUnit.SECONDS)).equals((Object)unit) ? this.bounded(9223372036L) : (((Object)((Object)TimeUnit.MINUTES)).equals((Object)unit) ? this.bounded(153722867L) : (((Object)((Object)TimeUnit.HOURS)).equals((Object)unit) ? this.bounded(2562047L) : (((Object)((Object)TimeUnit.DAYS)).equals((Object)unit) ? this.bounded(106751L) : -106751L <= (v = TimeUnit.DAYS.convert(length, unit)) && v <= 106751L))))));
        Predef$ predef$ = Predef$.MODULE$;
        if (bl) {
            return;
        }
        throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"Duration is limited to +-(2^63-1)ns (ca. 292 years)").toString());
    }
}

