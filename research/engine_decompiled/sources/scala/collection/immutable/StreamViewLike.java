/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.Function0;
import scala.Function1;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.GenSeq;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.SeqView;
import scala.collection.SeqViewLike;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.SliceInterval;
import scala.collection.immutable.StreamView;
import scala.collection.immutable.StreamViewLike$Transformed$class;
import scala.collection.immutable.StreamViewLike$class;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\t\u001dg\u0001C\u0001\u0003!\u0003\r\t!C\u0018\u0003\u001dM#(/Z1n-&,w\u000fT5lK*\u00111\u0001B\u0001\nS6lW\u000f^1cY\u0016T!!\u0002\u0004\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001)BAC\u000b KM!\u0001aC\b\"!\taQ\"D\u0001\u0007\u0013\tqaA\u0001\u0004B]f\u0014VM\u001a\t\u0005!E\u0019b$D\u0001\u0005\u0013\t\u0011BAA\u0004TKF4\u0016.Z<\u0011\u0005Q)B\u0002\u0001\u0003\u0007-\u0001!)\u0019A\f\u0003\u0003\u0005\u000b\"\u0001G\u000e\u0011\u00051I\u0012B\u0001\u000e\u0007\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0004\u000f\n\u0005u1!aA!osB\u0011Ac\b\u0003\u0007A\u0001!)\u0019A\f\u0003\t\r{G\u000e\u001c\t\u0006!\t\u001ab\u0004J\u0005\u0003G\u0011\u00111bU3r-&,w\u000fT5lKB\u0011A#\n\u0003\u0007M\u0001!)\u0019A\u0014\u0003\tQC\u0017n]\t\u00031!\u00122!K\u00160\r\u0011Q\u0003\u0001\u0001\u0015\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t1j3CH\u0007\u0002\u0005%\u0011aF\u0001\u0002\u000b'R\u0014X-Y7WS\u0016<\b#\u0002\u0017\u0001'y!\u0003\"B\u0019\u0001\t\u0003\u0011\u0014A\u0002\u0013j]&$H\u0005F\u00014!\taA'\u0003\u00026\r\t!QK\\5u\u0011\u00159\u0004\u0001\"\u00119\u0003\u00151wN]2f+\rIdi\u000f\u000b\u0003uu\u0002\"\u0001F\u001e\u0005\u000bq2$\u0019A\f\u0003\tQC\u0017\r\u001e\u0005\u0006}Y\u0002\u001daP\u0001\u0003E\u001a\u0004R\u0001Q\"\u001f\u000bjj\u0011!\u0011\u0006\u0003\u0005\u0012\tqaZ3oKJL7-\u0003\u0002E\u0003\na1)\u00198Ck&dGM\u0012:p[B\u0011AC\u0012\u0003\u0006\u000fZ\u0012\r\u0001\u0013\u0002\u0002\u0005F\u00111c\u0007\u0004\b\u0015\u0002\u0001\n1!\u0001L\u0005-!&/\u00198tM>\u0014X.\u001a3\u0016\u00051{5\u0003B%\f\u001bB\u0003B\u0001L\u0017O=A\u0011Ac\u0014\u0003\u0007\u000f&#)\u0019A\f\u0011\u0007E\u0013f*D\u0001\u0001\u0013\tQ%\u0005C\u00032\u0013\u0012\u0005!\u0007C\u0003V\u0013\u0012\u0005c+\u0001\u0005u_N#(/\u001b8h)\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0007\r\u0001\u0004\u0011\u0011\u0001\u0003b\u0005M\t%m\u001d;sC\u000e$HK]1og\u001a|'/\\3e+\t\u0011gmE\u0002`G\u001e\u00042!\u00153f\u0013\t\u0001'\u0005\u0005\u0002\u0015M\u00121qi\u0018CC\u0002]\u00012!U%f\u0011\u0015Iw\f\"\u0001k\u0003\u0019a\u0014N\\5u}Q\t1\u000eE\u0002R?\u00164q!\u001c\u0001\u0011\u0002G\u0005aNA\u0005F[B$\u0018PV5foN!AnC8q!\r\t\u0016\n\u0007\t\u0003#FL!!\u001c\u0012\u0007\u000fM\u0004\u0001\u0013aI\u0001i\n1ai\u001c:dK\u0012,\"!^=\u0014\tI\\aO\u001f\t\u0004#^D\u0018BA:#!\t!\u0012\u0010B\u0003He\n\u0007q\u0003E\u0002R\u0013b4q\u0001 \u0001\u0011\u0002G\u0005QP\u0001\u0004TY&\u001cW\rZ\n\u0006w.q\u0018\u0011\u0001\t\u0003#~L!\u0001 \u0012\u0011\u0007EK5CB\u0005\u0002\u0006\u0001\u0001\n1%\u0001\u0002\b\t1Q*\u00199qK\u0012,B!!\u0003\u0002\u0012M9\u00111A\u0006\u0002\f\u0005M\u0001#B)\u0002\u000e\u0005=\u0011bAA\u0003EA\u0019A#!\u0005\u0005\r\u001d\u000b\u0019A1\u0001\u0018!\u0011\t\u0016*a\u0004\u0007\u0013\u0005]\u0001\u0001%A\u0012\u0002\u0005e!A\u0003$mCRl\u0015\r\u001d9fIV!\u00111DA\u0012'\u001d\t)bCA\u000f\u0003K\u0001R!UA\u0010\u0003CI1!a\u0006#!\r!\u00121\u0005\u0003\u0007\u000f\u0006U!\u0019A\f\u0011\tEK\u0015\u0011\u0005\u0004\n\u0003S\u0001\u0001\u0013aI\u0001\u0003W\u0011\u0001\"\u00119qK:$W\rZ\u000b\u0005\u0003[\t)dE\u0004\u0002(-\ty#a\u000e\u0011\u000bE\u000b\t$a\r\n\u0007\u0005%\"\u0005E\u0002\u0015\u0003k!aaRA\u0014\u0005\u0004A\u0005\u0003B)J\u0003g1\u0011\"a\u000f\u0001!\u0003\r\n!!\u0010\u0003\u0011\u0019KG\u000e^3sK\u0012\u001cr!!\u000f\f\u0003\u007f\t\t\u0001E\u0002R\u0003\u0003J1!a\u000f#\r%\t)\u0005\u0001I\u0001$\u0003\t9E\u0001\u0006UC.,gn\u00165jY\u0016\u001cr!a\u0011\f\u0003\u0013\n\t\u0001E\u0002R\u0003\u0017J1!!\u0012#\r%\ty\u0005\u0001I\u0001$\u0003\t\tF\u0001\u0007Ee>\u0004\b/\u001a3XQ&dWmE\u0004\u0002N-\t\u0019&!\u0001\u0011\u0007E\u000b)&C\u0002\u0002P\t2\u0011\"!\u0017\u0001!\u0003\r\n!a\u0017\u0003\riK\u0007\u000f]3e+\u0011\ti&!\u001a\u0014\u000f\u0005]3\"a\u0018\u0002hA)\u0011+!\u0019\u0002d%\u0019\u0011\u0011\f\u0012\u0011\u0007Q\t)\u0007\u0002\u0004H\u0003/\u0012\ra\u0006\t\u0005#&\u000bI\u0007\u0005\u0004\r\u0003W\u001a\u00121M\u0005\u0004\u0003[2!A\u0002+va2,'GB\u0005\u0002r\u0001\u0001\n1%\u0001\u0002t\tI!,\u001b9qK\u0012\fE\u000e\\\u000b\u0007\u0003k\ni(a!\u0014\u000f\u0005=4\"a\u001e\u0002\u0006B9\u0011+!\u001f\u0002|\u0005\u0005\u0015bAA9EA\u0019A#! \u0005\u000f\u0005}\u0014q\u000eb\u0001\u0011\n\u0011\u0011)\r\t\u0004)\u0005\rEAB$\u0002p\t\u0007q\u0003\u0005\u0003R\u0013\u0006\u001d\u0005c\u0002\u0007\u0002l\u0005m\u0014\u0011\u0011\u0004\n\u0003\u0017\u0003\u0001\u0013aI\u0001\u0003\u001b\u0013\u0001BU3wKJ\u001cX\rZ\n\b\u0003\u0013[\u0011qRA\u0001!\r\t\u0016\u0011S\u0005\u0004\u0003\u0017\u0013c!CAK\u0001A\u0005\u0019\u0013AAL\u0005\u001d\u0001\u0016\r^2iK\u0012,B!!'\u0002\"N9\u00111S\u0006\u0002\u001c\u0006\r\u0006#B)\u0002\u001e\u0006}\u0015bAAKEA\u0019A#!)\u0005\r\u001d\u000b\u0019J1\u0001I!\u0011\t\u0016*a(\u0007\u0013\u0005\u001d\u0006\u0001%A\u0012\u0002\u0005%&!\u0003)sKB,g\u000eZ3e+\u0011\tY+a-\u0014\u000f\u0005\u00156\"!,\u00026B)\u0011+a,\u00022&\u0019\u0011q\u0015\u0012\u0011\u0007Q\t\u0019\f\u0002\u0004H\u0003K\u0013\r\u0001\u0013\t\u0005#&\u000b\t\fC\u0004\u0002:\u0002!\t&a/\u0002\u00139,wOR8sG\u0016$W\u0003BA_\u0003\u0007$B!a0\u0002FB!\u0011+SAa!\r!\u00121\u0019\u0003\u0007\u000f\u0006]&\u0019A\f\t\u0013\u0005\u001d\u0017q\u0017CA\u0002\u0005%\u0017A\u0001=t!\u0015a\u00111ZAh\u0013\r\tiM\u0002\u0002\ty\tLh.Y7f}A)\u0001#!5\u0002B&\u0019\u00111\u001b\u0003\u0003\r\u001d+gnU3r\u0011\u001d\t9\u000e\u0001C)\u00033\f1B\\3x\u0003B\u0004XM\u001c3fIV!\u00111\\Aq)\u0011\ti.a9\u0011\tEK\u0015q\u001c\t\u0004)\u0005\u0005HAB$\u0002V\n\u0007\u0001\n\u0003\u0005\u0002f\u0006U\u0007\u0019AAt\u0003\u0011!\b.\u0019;\u0011\u000bA\tI/a8\n\u0007\u0005-HA\u0001\bHK:$&/\u0019<feN\f'\r\\3\t\u000f\u0005=\b\u0001\"\u0015\u0002r\u0006Ia.Z<NCB\u0004X\rZ\u000b\u0005\u0003g\fI\u0010\u0006\u0003\u0002v\u0006m\b\u0003B)J\u0003o\u00042\u0001FA}\t\u00199\u0015Q\u001eb\u0001/!A\u0011Q`Aw\u0001\u0004\ty0A\u0001g!\u0019a!\u0011A\n\u0002x&\u0019!1\u0001\u0004\u0003\u0013\u0019+hn\u0019;j_:\f\u0004b\u0002B\u0004\u0001\u0011E#\u0011B\u0001\u000e]\u0016<h\t\\1u\u001b\u0006\u0004\b/\u001a3\u0016\t\t-!\u0011\u0003\u000b\u0005\u0005\u001b\u0011\u0019\u0002\u0005\u0003R\u0013\n=\u0001c\u0001\u000b\u0003\u0012\u00111qI!\u0002C\u0002]A\u0001\"!@\u0003\u0006\u0001\u0007!Q\u0003\t\u0007\u0019\t\u00051Ca\u0006\u0011\u000bA\u0011IBa\u0004\n\u0007\tmAA\u0001\nHK:$&/\u0019<feN\f'\r\\3P]\u000e,\u0007b\u0002B\u0010\u0001\u0011E#\u0011E\u0001\f]\u0016<h)\u001b7uKJ,G\r\u0006\u0003\u0002\u0002\t\r\u0002\u0002\u0003B\u0013\u0005;\u0001\rAa\n\u0002\u0003A\u0004b\u0001\u0004B\u0001'\t%\u0002c\u0001\u0007\u0003,%\u0019!Q\u0006\u0004\u0003\u000f\t{w\u000e\\3b]\"9!\u0011\u0007\u0001\u0005R\tM\u0012!\u00038foNc\u0017nY3e)\u0011\t\tA!\u000e\t\u0011\t]\"q\u0006a\u0001\u0005s\t!bX3oIB|\u0017N\u001c;t!\r\u0001%1H\u0005\u0004\u0005{\t%!D*mS\u000e,\u0017J\u001c;feZ\fG\u000eC\u0004\u0003B\u0001!\tFa\u0011\u0002\u001f9,w\u000f\u0012:paB,Gm\u00165jY\u0016$B!!\u0001\u0003F!A!Q\u0005B \u0001\u0004\u00119\u0003C\u0004\u0003J\u0001!\tFa\u0013\u0002\u001b9,w\u000fV1lK:<\u0006.\u001b7f)\u0011\t\tA!\u0014\t\u0011\t\u0015\"q\ta\u0001\u0005OAqA!\u0015\u0001\t#\u0012\u0019&A\u0005oK^T\u0016\u000e\u001d9fIV!!Q\u000bB/)\u0011\u00119Fa\u0018\u0011\tEK%\u0011\f\t\u0007\u0019\u0005-4Ca\u0017\u0011\u0007Q\u0011i\u0006\u0002\u0004H\u0005\u001f\u0012\ra\u0006\u0005\t\u0003K\u0014y\u00051\u0001\u0003bA)\u0001Ca\u0019\u0003\\%\u0019!Q\r\u0003\u0003\u0017\u001d+g.\u0013;fe\u0006\u0014G.\u001a\u0005\b\u0005S\u0002A\u0011\u000bB6\u00031qWm\u001e.jaB,G-\u00117m+\u0019\u0011iG!\u001e\u0003zQA!q\u000eB>\u0005\u007f\u0012\u0019\t\u0005\u0003R\u0013\nE\u0004c\u0002\u0007\u0002l\tM$q\u000f\t\u0004)\tUDaBA@\u0005O\u0012\r\u0001\u0013\t\u0004)\teDAB$\u0003h\t\u0007q\u0003\u0003\u0005\u0002f\n\u001d\u0004\u0019\u0001B?!\u0015\u0001\"1\rB<\u0011!\u0011\tIa\u001aA\u0002\tM\u0014!C0uQ&\u001cX\t\\3n\u0011!\u0011)Ia\u001aA\u0002\t]\u0014!C0uQ\u0006$X\t\\3n\u0011\u001d\u0011I\t\u0001C)\u0005\u0017\u000b1B\\3x%\u00164XM]:fIV\u0011\u0011\u0011\u0001\u0005\b\u0005\u001f\u0003A\u0011\u000bBI\u0003)qWm\u001e)bi\u000eDW\rZ\u000b\u0005\u0005'\u0013I\n\u0006\u0005\u0003\u0016\nm%Q\u0015BV!\u0011\t\u0016Ja&\u0011\u0007Q\u0011I\n\u0002\u0004H\u0005\u001b\u0013\r\u0001\u0013\u0005\t\u0005;\u0013i\t1\u0001\u0003 \u0006)qL\u001a:p[B\u0019AB!)\n\u0007\t\rfAA\u0002J]RD\u0001Ba*\u0003\u000e\u0002\u0007!\u0011V\u0001\u0007?B\fGo\u00195\u0011\u000bA\t\tNa&\t\u0011\t5&Q\u0012a\u0001\u0005?\u000b\u0011b\u0018:fa2\f7-\u001a3\t\u000f\tE\u0006\u0001\"\u0015\u00034\u0006aa.Z<Qe\u0016\u0004XM\u001c3fIV!!Q\u0017B^)\u0011\u00119L!0\u0011\tEK%\u0011\u0018\t\u0004)\tmFAB$\u00030\n\u0007\u0001\n\u0003\u0005\u0003@\n=\u0006\u0019\u0001B]\u0003\u0011)G.Z7\t\u000f\t\r\u0007\u0001\"\u0011\u0003F\u0006a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\tq\u000b")
public interface StreamViewLike<A, Coll, This extends StreamView<A, Coll> & StreamViewLike<A, Coll, This>>
extends SeqView<A, Coll> {
    @Override
    public <B, That> That force(CanBuildFrom<Coll, B, That> var1);

    @Override
    public <B> Transformed<B> newForced(Function0<GenSeq<B>> var1);

    @Override
    public <B> Transformed<B> newAppended(GenTraversable<B> var1);

    @Override
    public <B> Transformed<B> newMapped(Function1<A, B> var1);

    @Override
    public <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> var1);

    @Override
    public Transformed<A> newFiltered(Function1<A, Object> var1);

    @Override
    public Transformed<A> newSliced(SliceInterval var1);

    @Override
    public Transformed<A> newDroppedWhile(Function1<A, Object> var1);

    @Override
    public Transformed<A> newTakenWhile(Function1<A, Object> var1);

    @Override
    public <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> var1);

    @Override
    public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> var1, A1 var2, B var3);

    @Override
    public Transformed<A> newReversed();

    @Override
    public <B> Transformed<B> newPatched(int var1, GenSeq<B> var2, int var3);

    @Override
    public <B> Transformed<B> newPrepended(B var1);

    @Override
    public String stringPrefix();

    public interface Forced<B>
    extends SeqViewLike.Forced<B>,
    Transformed<B> {
    }

    public interface Sliced
    extends SeqViewLike.Sliced,
    Transformed<A> {
    }

    public interface Mapped<B>
    extends SeqViewLike.Mapped<B>,
    Transformed<B> {
    }

    public interface Zipped<B>
    extends SeqViewLike.Zipped<B>,
    Transformed<Tuple2<A, B>> {
    }

    public interface Patched<B>
    extends SeqViewLike.Patched<B>,
    Transformed<B> {
    }

    public interface Appended<B>
    extends SeqViewLike.Appended<B>,
    Transformed<B> {
    }

    public interface Filtered
    extends SeqViewLike.Filtered,
    Transformed<A> {
    }

    public interface Reversed
    extends SeqViewLike.Reversed,
    Transformed<A> {
    }

    public interface EmptyView
    extends Transformed<Nothing$>,
    SeqViewLike.EmptyView {
    }

    public interface ZippedAll<A1, B>
    extends SeqViewLike.ZippedAll<A1, B>,
    Transformed<Tuple2<A1, B>> {
    }

    public interface Prepended<B>
    extends SeqViewLike.Prepended<B>,
    Transformed<B> {
    }

    public interface FlatMapped<B>
    extends SeqViewLike.FlatMapped<B>,
    Transformed<B> {
    }

    public interface TakenWhile
    extends SeqViewLike.TakenWhile,
    Transformed<A> {
    }

    public interface Transformed<B>
    extends StreamView<B, Coll>,
    SeqViewLike.Transformed<B> {
        @Override
        public String toString();

        public /* synthetic */ StreamViewLike scala$collection$immutable$StreamViewLike$Transformed$$$outer();
    }

    public interface DroppedWhile
    extends SeqViewLike.DroppedWhile,
    Transformed<A> {
    }

    public abstract class AbstractTransformed<B>
    extends SeqViewLike.AbstractTransformed<B>
    implements Transformed<B> {
        @Override
        public String toString() {
            return StreamViewLike$Transformed$class.toString(this);
        }

        @Override
        public <B, That> That force(CanBuildFrom<Coll, B, That> bf) {
            return (That)StreamViewLike$class.force(this, bf);
        }

        @Override
        public <B> Transformed<B> newForced(Function0<GenSeq<B>> xs) {
            return StreamViewLike$class.newForced(this, xs);
        }

        @Override
        public <B> Transformed<B> newAppended(GenTraversable<B> that) {
            return StreamViewLike$class.newAppended(this, that);
        }

        @Override
        public <B> Transformed<B> newMapped(Function1<B, B> f) {
            return StreamViewLike$class.newMapped(this, f);
        }

        @Override
        public <B> Transformed<B> newFlatMapped(Function1<B, GenTraversableOnce<B>> f) {
            return StreamViewLike$class.newFlatMapped(this, f);
        }

        @Override
        public Transformed<B> newFiltered(Function1<B, Object> p) {
            return StreamViewLike$class.newFiltered(this, p);
        }

        @Override
        public Transformed<B> newSliced(SliceInterval _endpoints) {
            return StreamViewLike$class.newSliced(this, _endpoints);
        }

        @Override
        public Transformed<B> newDroppedWhile(Function1<B, Object> p) {
            return StreamViewLike$class.newDroppedWhile(this, p);
        }

        @Override
        public Transformed<B> newTakenWhile(Function1<B, Object> p) {
            return StreamViewLike$class.newTakenWhile(this, p);
        }

        @Override
        public <B> Transformed<Tuple2<B, B>> newZipped(GenIterable<B> that) {
            return StreamViewLike$class.newZipped(this, that);
        }

        @Override
        public <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> that, A1 _thisElem, B _thatElem) {
            return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
        }

        @Override
        public Transformed<B> newReversed() {
            return StreamViewLike$class.newReversed(this);
        }

        @Override
        public <B> Transformed<B> newPatched(int _from, GenSeq<B> _patch, int _replaced) {
            return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
        }

        @Override
        public <B> Transformed<B> newPrepended(B elem) {
            return StreamViewLike$class.newPrepended(this, elem);
        }

        @Override
        public String stringPrefix() {
            return StreamViewLike$class.stringPrefix(this);
        }

        public /* synthetic */ StreamViewLike scala$collection$immutable$StreamViewLike$AbstractTransformed$$$outer() {
            return (StreamViewLike)this.$outer;
        }

        @Override
        public /* synthetic */ StreamViewLike scala$collection$immutable$StreamViewLike$Transformed$$$outer() {
            return this.scala$collection$immutable$StreamViewLike$AbstractTransformed$$$outer();
        }

        public AbstractTransformed(StreamViewLike<A, Coll, This> $outer) {
            StreamViewLike$class.$init$(this);
            StreamViewLike$Transformed$class.$init$(this);
        }
    }
}

