/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Product;
import scala.Product$class;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.Failure;
import scala.util.Success$;
import scala.util.Try;
import scala.util.Try$;
import scala.util.control.NonFatal$;

@ScalaSignature(bytes="\u0006\u0001\u0005%h\u0001B\u0001\u0003\u0005\u001e\u0011qaU;dG\u0016\u001c8O\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0003\u0011=\u0019B\u0001A\u0005\u001a9A\u0019!bC\u0007\u000e\u0003\tI!\u0001\u0004\u0002\u0003\u0007Q\u0013\u0018\u0010\u0005\u0002\u000f\u001f1\u0001AA\u0002\t\u0001\t\u000b\u0007\u0011CA\u0001U#\t\u0011b\u0003\u0005\u0002\u0014)5\tA!\u0003\u0002\u0016\t\t9aj\u001c;iS:<\u0007CA\n\u0018\u0013\tABAA\u0002B]f\u0004\"a\u0005\u000e\n\u0005m!!a\u0002)s_\u0012,8\r\u001e\t\u0003'uI!A\b\u0003\u0003\u0019M+'/[1mSj\f'\r\\3\t\u0011\u0001\u0002!Q3A\u0005\u0002\u0005\nQA^1mk\u0016,\u0012!\u0004\u0005\tG\u0001\u0011\t\u0012)A\u0005\u001b\u00051a/\u00197vK\u0002BQ!\n\u0001\u0005\u0002\u0019\na\u0001P5oSRtDCA\u0014)!\rQ\u0001!\u0004\u0005\u0006A\u0011\u0002\r!\u0004\u0005\u0006U\u0001!\taK\u0001\nSN4\u0015-\u001b7ve\u0016,\u0012\u0001\f\t\u0003'5J!A\f\u0003\u0003\u000f\t{w\u000e\\3b]\")\u0001\u0007\u0001C\u0001W\u0005I\u0011n]*vG\u000e,7o\u001d\u0005\u0006e\u0001!\taM\u0001\fe\u0016\u001cwN^3s/&$\b.\u0006\u00025oQ\u0011QG\u000f\t\u0004\u0015-1\u0004C\u0001\b8\t\u0015A\u0014G1\u0001:\u0005\u0005)\u0016CA\u0007\u0017\u0011\u0015Y\u0014\u00071\u0001=\u0003\u00051\u0007\u0003B\n>\u007fUJ!A\u0010\u0003\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004\"\u0001Q\"\u000f\u0005M\t\u0015B\u0001\"\u0005\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001R#\u0003\u0013QC'o\\<bE2,'B\u0001\"\u0005\u0011\u00159\u0005\u0001\"\u0001\"\u0003\r9W\r\u001e\u0005\u0006\u0013\u0002!\tAS\u0001\bM2\fG/T1q+\tYe\n\u0006\u0002M\u001fB\u0019!bC'\u0011\u00059qE!\u0002\u001dI\u0005\u0004\t\u0002\"B\u001eI\u0001\u0004\u0001\u0006\u0003B\nR\u001b1K!A\u0015\u0003\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\"\u0002+\u0001\t\u0003)\u0016a\u00024mCR$XM\\\u000b\u0003-f#\"a\u0016.\u0011\u0007)Y\u0001\f\u0005\u0002\u000f3\u0012)\u0001h\u0015b\u0001#!)1l\u0015a\u00029\u0006\u0011QM\u001e\t\u0005;\u0002lqK\u0004\u0002\u0014=&\u0011q\fB\u0001\u0007!J,G-\u001a4\n\u0005\u0005\u0014'\u0001\u0005\u0013mKN\u001cHeY8m_:$C.Z:t\u0015\tyF\u0001C\u0003e\u0001\u0011\u0005Q-A\u0004g_J,\u0017m\u00195\u0016\u0005\u0019lGCA4k!\t\u0019\u0002.\u0003\u0002j\t\t!QK\\5u\u0011\u0015Y4\r1\u0001l!\u0011\u0019\u0012+\u00047\u0011\u00059iG!\u0002\u001dd\u0005\u0004\t\u0002\"B8\u0001\t\u0003\u0001\u0018aA7baV\u0011\u0011\u000f\u001e\u000b\u0003eV\u00042AC\u0006t!\tqA\u000fB\u00039]\n\u0007\u0011\u0003C\u0003<]\u0002\u0007a\u000f\u0005\u0003\u0014#6\u0019\b\"\u0002=\u0001\t\u0003I\u0018A\u00024jYR,'\u000f\u0006\u0002\nu\")1p\u001ea\u0001y\u0006\t\u0001\u000f\u0005\u0003\u0014#6a\u0003\"\u0002@\u0001\t\u0003y\u0018a\u0002:fG>4XM]\u000b\u0005\u0003\u0003\t9\u0001\u0006\u0003\u0002\u0004\u0005%\u0001\u0003\u0002\u0006\f\u0003\u000b\u00012ADA\u0004\t\u0015ATP1\u0001:\u0011\u001d\tY! a\u0001\u0003\u001b\tqB]3tGV,W\t_2faRLwN\u001c\t\u0006'uz\u0014Q\u0001\u0005\b\u0003#\u0001A\u0011AA\n\u0003\u00191\u0017-\u001b7fIV\u0011\u0011Q\u0003\t\u0004\u0015-y\u0004\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0003\u0011\u0019w\u000e]=\u0016\t\u0005u\u00111\u0005\u000b\u0005\u0003?\t)\u0003\u0005\u0003\u000b\u0001\u0005\u0005\u0002c\u0001\b\u0002$\u00111\u0001#a\u0006C\u0002EA\u0011\u0002IA\f!\u0003\u0005\r!!\t\t\u0013\u0005%\u0002!%A\u0005\u0002\u0005-\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0003[\t\u0019%\u0006\u0002\u00020)\u001aQ\"!\r,\u0005\u0005M\u0002\u0003BA\u001b\u0003\u007fi!!a\u000e\u000b\t\u0005e\u00121H\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0010\u0005\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u0003\n9DA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$a\u0001EA\u0014\u0005\u0004\t\u0002\"CA$\u0001\u0005\u0005I\u0011IA%\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\n\t\u0005\u0003\u001b\n9&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0013\u0001\u00026bm\u0006LA!!\u0017\u0002P\t11\u000b\u001e:j]\u001eD\u0011\"!\u0018\u0001\u0003\u0003%\t!a\u0018\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0005\u0004cA\n\u0002d%\u0019\u0011Q\r\u0003\u0003\u0007%sG\u000fC\u0005\u0002j\u0001\t\t\u0011\"\u0001\u0002l\u0005q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001\f\u0002n!Q\u0011qNA4\u0003\u0003\u0005\r!!\u0019\u0002\u0007a$\u0013\u0007C\u0005\u0002t\u0001\t\t\u0011\"\u0011\u0002v\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002xA)\u0011\u0011PA@-5\u0011\u00111\u0010\u0006\u0004\u0003{\"\u0011AC2pY2,7\r^5p]&!\u0011\u0011QA>\u0005!IE/\u001a:bi>\u0014\b\"CAC\u0001\u0005\u0005I\u0011AAD\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001\u0017\u0002\n\"I\u0011qNAB\u0003\u0003\u0005\rA\u0006\u0005\n\u0003\u001b\u0003\u0011\u0011!C!\u0003\u001f\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003CB\u0011\"a%\u0001\u0003\u0003%\t%!&\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0013\t\u0013\u0005e\u0005!!A\u0005B\u0005m\u0015AB3rk\u0006d7\u000fF\u0002-\u0003;C\u0011\"a\u001c\u0002\u0018\u0006\u0005\t\u0019\u0001\f\b\u0013\u0005\u0005&!!A\t\u0002\u0005\r\u0016aB*vG\u000e,7o\u001d\t\u0004\u0015\u0005\u0015f\u0001C\u0001\u0003\u0003\u0003E\t!a*\u0014\u000b\u0005\u0015\u0016\u0011\u0016\u000f\u0011\u0007M\tY+C\u0002\u0002.\u0012\u0011a!\u00118z%\u00164\u0007bB\u0013\u0002&\u0012\u0005\u0011\u0011\u0017\u000b\u0003\u0003GC!\"a%\u0002&\u0006\u0005IQIAK\u0011)\t9,!*\u0002\u0002\u0013\u0005\u0015\u0011X\u0001\u0006CB\u0004H._\u000b\u0005\u0003w\u000b\t\r\u0006\u0003\u0002>\u0006\r\u0007\u0003\u0002\u0006\u0001\u0003\u007f\u00032ADAa\t\u0019\u0001\u0012Q\u0017b\u0001#!9\u0001%!.A\u0002\u0005}\u0006BCAd\u0003K\u000b\t\u0011\"!\u0002J\u00069QO\\1qa2LX\u0003BAf\u0003+$B!!4\u0002XB)1#a4\u0002T&\u0019\u0011\u0011\u001b\u0003\u0003\r=\u0003H/[8o!\rq\u0011Q\u001b\u0003\u0007!\u0005\u0015'\u0019A\t\t\u0015\u0005e\u0017QYA\u0001\u0002\u0004\tY.A\u0002yIA\u0002BA\u0003\u0001\u0002T\"Q\u0011q\\AS\u0003\u0003%I!!9\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003G\u0004B!!\u0014\u0002f&!\u0011q]A(\u0005\u0019y%M[3di\u0002")
public final class Success<T>
extends Try<T>
implements Product,
Serializable {
    private final T value;

    public static <T> Option<T> unapply(Success<T> success) {
        return Success$.MODULE$.unapply(success);
    }

    public static <T> Success<T> apply(T t) {
        return Success$.MODULE$.apply(t);
    }

    public T value() {
        return this.value;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public <U> Try<U> recoverWith(PartialFunction<Throwable, Try<U>> f) {
        return this;
    }

    @Override
    public T get() {
        return this.value();
    }

    @Override
    public <U> Try<U> flatMap(Function1<T, Try<U>> f) {
        Failure failure;
        try {
            failure = f.apply(this.value());
        }
        catch (Throwable throwable) {
            Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
            if (option.isEmpty()) {
                throw throwable;
            }
            failure = new Failure(option.get());
        }
        return failure;
    }

    @Override
    public <U> Try<U> flatten(Predef$.less.colon.less<T, Try<U>> ev) {
        return (Try)ev.apply(this.value());
    }

    @Override
    public <U> void foreach(Function1<T, U> f) {
        f.apply(this.value());
    }

    @Override
    public <U> Try<U> map(Function1<T, U> f) {
        return Try$.MODULE$.apply(new Serializable(this, f){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Success $outer;
            private final Function1 f$1;

            public final U apply() {
                return (U)this.f$1.apply(this.$outer.value());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.f$1 = f$1;
            }
        });
    }

    @Override
    public Try<T> filter(Function1<T, Object> p) {
        Try try_;
        try {
            try_ = BoxesRunTime.unboxToBoolean(p.apply(this.value())) ? this : new Failure(new NoSuchElementException(new StringBuilder().append((Object)"Predicate does not hold for ").append(this.value()).toString()));
        }
        catch (Throwable throwable) {
            Option<Throwable> option = NonFatal$.MODULE$.unapply(throwable);
            if (option.isEmpty()) {
                throw throwable;
            }
            try_ = new Failure(option.get());
        }
        return try_;
    }

    @Override
    public <U> Try<U> recover(PartialFunction<Throwable, U> rescueException) {
        return this;
    }

    @Override
    public Try<Throwable> failed() {
        return new Failure<Throwable>(new UnsupportedOperationException("Success.failed"));
    }

    public <T> Success<T> copy(T value) {
        return new Success<T>(value);
    }

    public <T> T copy$default$1() {
        return this.value();
    }

    @Override
    public String productPrefix() {
        return "Success";
    }

    @Override
    public int productArity() {
        return 1;
    }

    @Override
    public Object productElement(int x$1) {
        switch (x$1) {
            default: {
                throw new IndexOutOfBoundsException(((Object)BoxesRunTime.boxToInteger(x$1)).toString());
            }
            case 0: 
        }
        return this.value();
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof Success;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$._hashCode(this);
    }

    public String toString() {
        return ScalaRunTime$.MODULE$._toString(this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        if (this == x$1) return true;
        if (!(x$1 instanceof Success)) return false;
        boolean bl = true;
        if (!bl) return false;
        Success success = (Success)x$1;
        T t = success.value();
        T t2 = this.value();
        if (t2 == t) {
            return true;
        }
        if (t2 == null) {
            return false;
        }
        boolean bl2 = t2 instanceof Number ? BoxesRunTime.equalsNumObject((Number)t2, t) : (t2 instanceof Character ? BoxesRunTime.equalsCharObject((Character)t2, t) : t2.equals(t));
        if (!bl2) return false;
        return true;
    }

    public Success(T value) {
        this.value = value;
        Product$class.$init$(this);
    }
}

