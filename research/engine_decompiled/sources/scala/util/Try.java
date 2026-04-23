/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try$;
import scala.util.control.NonFatal$;

@ScalaSignature(bytes="\u0006\u0001\u0005=g!B\u0001\u0003\u0003C9!a\u0001+ss*\u00111\u0001B\u0001\u0005kRLGNC\u0001\u0006\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001\u0003\u000b\u0014\u0005\u0001I\u0001C\u0001\u0006\f\u001b\u0005!\u0011B\u0001\u0007\u0005\u0005\u0019\te.\u001f*fM\")a\u0002\u0001C\u0001\u001f\u00051A(\u001b8jiz\"\u0012\u0001\u0005\t\u0004#\u0001\u0011R\"\u0001\u0002\u0011\u0005M!B\u0002\u0001\u0003\u0007+\u0001!)\u0019\u0001\f\u0003\u0003Q\u000b\"a\u0006\u000e\u0011\u0005)A\u0012BA\r\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AC\u000e\n\u0005q!!aA!os\")a\u0004\u0001D\u0001?\u0005I\u0011n\u001d$bS2,(/Z\u000b\u0002AA\u0011!\"I\u0005\u0003E\u0011\u0011qAQ8pY\u0016\fg\u000eC\u0003%\u0001\u0019\u0005q$A\u0005jgN+8mY3tg\")a\u0005\u0001C\u0001O\u0005Iq-\u001a;Pe\u0016c7/Z\u000b\u0003Q)\"\"!K\u0017\u0011\u0005MQC!B\u0016&\u0005\u0004a#!A+\u0012\u0005IQ\u0002B\u0002\u0018&\t\u0003\u0007q&A\u0004eK\u001a\fW\u000f\u001c;\u0011\u0007)\u0001\u0014&\u0003\u00022\t\tAAHY=oC6,g\bC\u00034\u0001\u0011\u0005A'\u0001\u0004pe\u0016c7/Z\u000b\u0003ka\"\"AN\u001d\u0011\u0007E\u0001q\u0007\u0005\u0002\u0014q\u0011)1F\rb\u0001Y!1aF\rCA\u0002i\u00022A\u0003\u00197\u0011\u0015a\u0004A\"\u0001>\u0003\r9W\r^\u000b\u0002%!)q\b\u0001D\u0001\u0001\u00069am\u001c:fC\u000eDWCA!L)\t\u0011U\t\u0005\u0002\u000b\u0007&\u0011A\t\u0002\u0002\u0005+:LG\u000fC\u0003G}\u0001\u0007q)A\u0001g!\u0011Q\u0001J\u0005&\n\u0005%#!!\u0003$v]\u000e$\u0018n\u001c82!\t\u00192\nB\u0003,}\t\u0007a\u0003C\u0003N\u0001\u0019\u0005a*A\u0004gY\u0006$X*\u00199\u0016\u0005=\u0013FC\u0001)T!\r\t\u0002!\u0015\t\u0003'I#Qa\u000b'C\u0002YAQA\u0012'A\u0002Q\u0003BA\u0003%\u0013!\")a\u000b\u0001D\u0001/\u0006\u0019Q.\u00199\u0016\u0005a[FCA-]!\r\t\u0002A\u0017\t\u0003'm#QaK+C\u0002YAQAR+A\u0002u\u0003BA\u0003%\u00135\")q\f\u0001D\u0001A\u00061a-\u001b7uKJ$\"\u0001E1\t\u000b\tt\u0006\u0019A2\u0002\u0003A\u0004BA\u0003%\u0013A!)Q\r\u0001C\u0003M\u0006Qq/\u001b;i\r&dG/\u001a:\u0015\u0007\u001d\f9\u0002\u0005\u0002iS6\t\u0001A\u0002\u0003k\u0001\u0001Y'AC,ji\"4\u0015\u000e\u001c;feN\u0011\u0011.\u0003\u0005\tE&\u0014\t\u0011)A\u0005G\")a\"\u001bC\u0001]R\u0011qm\u001c\u0005\u0006E6\u0004\ra\u0019\u0005\u0006-&$\t!]\u000b\u0003eV$\"a\u001d<\u0011\u0007E\u0001A\u000f\u0005\u0002\u0014k\u0012)1\u0006\u001db\u0001-!)a\t\u001da\u0001oB!!\u0002\u0013\nu\u0011\u0015i\u0015\u000e\"\u0001z+\tQX\u0010\u0006\u0002|}B\u0019\u0011\u0003\u0001?\u0011\u0005MiH!B\u0016y\u0005\u00041\u0002\"\u0002$y\u0001\u0004y\b\u0003\u0002\u0006I%mDaaP5\u0005\u0002\u0005\rQ\u0003BA\u0003\u0003\u001b!2AQA\u0004\u0011\u001d1\u0015\u0011\u0001a\u0001\u0003\u0013\u0001RA\u0003%\u0013\u0003\u0017\u00012aEA\u0007\t\u0019Y\u0013\u0011\u0001b\u0001-!1Q-\u001bC\u0001\u0003#!2aZA\n\u0011\u001d\t)\"a\u0004A\u0002\r\f\u0011!\u001d\u0005\u0006E\u0012\u0004\ra\u0019\u0015\u0004I\u0006m\u0001c\u0001\u0006\u0002\u001e%\u0019\u0011q\u0004\u0003\u0003\r%tG.\u001b8f\u0011\u001d\t\u0019\u0003\u0001D\u0001\u0003K\t1B]3d_Z,'oV5uQV!\u0011qEA\u0017)\u0011\tI#a\f\u0011\tE\u0001\u00111\u0006\t\u0004'\u00055BAB\u0016\u0002\"\t\u0007A\u0006C\u0004G\u0003C\u0001\r!!\r\u0011\u000f)\t\u0019$a\u000e\u0002*%\u0019\u0011Q\u0007\u0003\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004B!!\u000f\u0002@9\u0019!\"a\u000f\n\u0007\u0005uB!A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0005\u00131\t\u0002\n)\"\u0014xn^1cY\u0016T1!!\u0010\u0005\u0011\u001d\t9\u0005\u0001D\u0001\u0003\u0013\nqA]3d_Z,'/\u0006\u0003\u0002L\u0005EC\u0003BA'\u0003'\u0002B!\u0005\u0001\u0002PA\u00191#!\u0015\u0005\r-\n)E1\u0001-\u0011\u001d1\u0015Q\ta\u0001\u0003+\u0002rACA\u001a\u0003o\ty\u0005C\u0004\u0002Z\u0001!\t!a\u0017\u0002\u0011Q|w\n\u001d;j_:,\"!!\u0018\u0011\t)\tyFE\u0005\u0004\u0003C\"!AB(qi&|g\u000eC\u0004\u0002f\u00011\t!a\u001a\u0002\u000f\u0019d\u0017\r\u001e;f]V!\u0011\u0011NA8)\u0011\tY'!\u001d\u0011\tE\u0001\u0011Q\u000e\t\u0004'\u0005=DAB\u0016\u0002d\t\u0007a\u0003\u0003\u0005\u0002t\u0005\r\u00049AA;\u0003\t)g\u000fE\u0004\u0002x\u0005u$#a\u001b\u000f\u0007)\tI(C\u0002\u0002|\u0011\ta\u0001\u0015:fI\u00164\u0017\u0002BA@\u0003\u0003\u0013\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u000b\u0007\u0005mD\u0001C\u0004\u0002\u0006\u00021\t!a\"\u0002\r\u0019\f\u0017\u000e\\3e+\t\tI\t\u0005\u0003\u0012\u0001\u0005]\u0002bBAG\u0001\u0011\u0005\u0011qR\u0001\niJ\fgn\u001d4pe6,B!!%\u0002\u0018R1\u00111SAM\u0003?\u0003B!\u0005\u0001\u0002\u0016B\u00191#a&\u0005\r-\nYI1\u0001\u0017\u0011!\tY*a#A\u0002\u0005u\u0015!A:\u0011\u000b)A%#a%\t\u000f\u0019\u000bY\t1\u0001\u0002\"B1!\u0002SA\u001c\u0003'KS\u0001AAS\u0003SK1!a*\u0003\u0005\u001d1\u0015-\u001b7ve\u0016L1!a+\u0003\u0005\u001d\u0019VoY2fgN<q!a,\u0003\u0011\u0003\t\t,A\u0002Uef\u00042!EAZ\r\u0019\t!\u0001#\u0001\u00026N\u0019\u00111W\u0005\t\u000f9\t\u0019\f\"\u0001\u0002:R\u0011\u0011\u0011\u0017\u0005\t\u0003{\u000b\u0019\f\"\u0001\u0002@\u0006)\u0011\r\u001d9msV!\u0011\u0011YAd)\u0011\t\u0019-!3\u0011\tE\u0001\u0011Q\u0019\t\u0004'\u0005\u001dGAB\u000b\u0002<\n\u0007a\u0003C\u0005\u0002L\u0006mF\u00111\u0001\u0002N\u0006\t!\u000f\u0005\u0003\u000ba\u0005\u0015\u0007")
public abstract class Try<T> {
    public static <T> Try<T> apply(Function0<T> function0) {
        return Try$.MODULE$.apply(function0);
    }

    public abstract boolean isFailure();

    public abstract boolean isSuccess();

    public <U> U getOrElse(Function0<U> function0) {
        return (U)(this.isSuccess() ? this.get() : function0.apply());
    }

    public <U> Try<U> orElse(Function0<Try<U>> function0) {
        Failure failure;
        try {
            failure = ((Try)this).isSuccess() ? this : function0.apply();
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

    public abstract T get();

    public abstract <U> void foreach(Function1<T, U> var1);

    public abstract <U> Try<U> flatMap(Function1<T, Try<U>> var1);

    public abstract <U> Try<U> map(Function1<T, U> var1);

    public abstract Try<T> filter(Function1<T, Object> var1);

    public final WithFilter withFilter(Function1<T, Object> p) {
        return new WithFilter(this, p);
    }

    public abstract <U> Try<U> recoverWith(PartialFunction<Throwable, Try<U>> var1);

    public abstract <U> Try<U> recover(PartialFunction<Throwable, U> var1);

    public Option<T> toOption() {
        return this.isSuccess() ? new Some<T>(this.get()) : None$.MODULE$;
    }

    public abstract <U> Try<U> flatten(Predef$.less.colon.less<T, Try<U>> var1);

    public abstract Try<Throwable> failed();

    public <U> Try<U> transform(Function1<T, Try<U>> s2, Function1<Throwable, Try<U>> f) {
        Try try_;
        block3: {
            Option<Throwable> option;
            block5: {
                MatchError matchError;
                block4: {
                    try {
                        block8: {
                            Try<U> try_2;
                            block7: {
                                block6: {
                                    if (!(this instanceof Success)) break block6;
                                    Success success = (Success)this;
                                    try_2 = s2.apply(success.value());
                                    break block7;
                                }
                                if (!(this instanceof Failure)) break block8;
                                Failure failure = (Failure)this;
                                try_2 = f.apply(failure.exception());
                            }
                            try_ = try_2;
                            break block3;
                        }
                        matchError = new MatchError(this);
                        option = NonFatal$.MODULE$.unapply(matchError);
                        if (option.isEmpty()) break block4;
                        break block5;
                    }
                    catch (Throwable throwable) {
                        option = NonFatal$.MODULE$.unapply(throwable);
                        if (!option.isEmpty()) break block5;
                    }
                }
                throw matchError;
            }
            try_ = new Failure(option.get());
        }
        return try_;
    }

    public class WithFilter {
        public final Function1<T, Object> scala$util$Try$WithFilter$$p;
        public final /* synthetic */ Try $outer;

        public <U> Try<U> map(Function1<T, U> f) {
            return this.scala$util$Try$WithFilter$$$outer().filter(this.scala$util$Try$WithFilter$$p).map(f);
        }

        public <U> Try<U> flatMap(Function1<T, Try<U>> f) {
            return this.scala$util$Try$WithFilter$$$outer().filter(this.scala$util$Try$WithFilter$$p).flatMap(f);
        }

        public <U> void foreach(Function1<T, U> f) {
            this.scala$util$Try$WithFilter$$$outer().filter(this.scala$util$Try$WithFilter$$p).foreach(f);
        }

        public WithFilter withFilter(Function1<T, Object> q) {
            return new WithFilter(this.scala$util$Try$WithFilter$$$outer(), new Serializable(this, q){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ WithFilter $outer;
                private final Function1 q$1;

                public final boolean apply(T x) {
                    return BoxesRunTime.unboxToBoolean(this.$outer.scala$util$Try$WithFilter$$p.apply(x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.q$1 = q$1;
                }
            });
        }

        public /* synthetic */ Try scala$util$Try$WithFilter$$$outer() {
            return this.$outer;
        }

        public WithFilter(Try<T> $outer, Function1<T, Object> p) {
            this.scala$util$Try$WithFilter$$p = p;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
        }
    }
}

