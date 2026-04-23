/*
 * Decompiled with CFR 0.152.
 */
package scala.collection;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.GenIterable;
import scala.collection.LinearSeqLike;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\t5a\u0001C\u0001\u0003!\u0003\r\ta\u0002\u000f\u0003%1Kg.Z1s'\u0016\fx\n\u001d;j[&TX\r\u001a\u0006\u0003\u0007\u0011\t!bY8mY\u0016\u001cG/[8o\u0015\u0005)\u0011!B:dC2\f7\u0001A\u000b\u0004\u0011Mi2c\u0001\u0001\n\u001bA\u0011!bC\u0007\u0002\t%\u0011A\u0002\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\t9y\u0011\u0003H\u0007\u0002\u0005%\u0011\u0001C\u0001\u0002\u000e\u0019&tW-\u0019:TKFd\u0015n[3\u0011\u0005I\u0019B\u0002\u0001\u0003\u0007)\u0001!)\u0019A\u000b\u0003\u0003\u0005\u000b\"AF\r\u0011\u0005)9\u0012B\u0001\r\u0005\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0003\u000e\n\u0005m!!aA!osB\u0011!#\b\u0003\u0007=\u0001!)\u0019A\u0010\u0003\tI+\u0007O]\t\u0003-\u0001\u0002BA\u0004\u0001\u00129!)!\u0005\u0001C\u0001G\u00051A%\u001b8ji\u0012\"\u0012\u0001\n\t\u0003\u0015\u0015J!A\n\u0003\u0003\tUs\u0017\u000e\u001e\u0005\u0006Q\u00011\t!K\u0001\bSN,U\u000e\u001d;z+\u0005Q\u0003C\u0001\u0006,\u0013\taCAA\u0004C_>dW-\u00198\t\u000b9\u0002a\u0011A\u0018\u0002\t!,\u0017\rZ\u000b\u0002#!)\u0011\u0007\u0001D\u0001e\u0005!A/Y5m+\u0005a\u0002\"\u0002\u001b\u0001\t\u0003)\u0014A\u00027f]\u001e$\b.F\u00017!\tQq'\u0003\u00029\t\t\u0019\u0011J\u001c;\t\u000bi\u0002A\u0011A\u001e\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005Ea\u0004\"B\u001f:\u0001\u00041\u0014!\u00018\t\u000b}\u0002A\u0011\t!\u0002\u000f\u0019|'/Z1dQV\u0011\u0011\t\u0013\u000b\u0003I\tCQa\u0011 A\u0002\u0011\u000b\u0011A\u001a\t\u0005\u0015\u0015\u000br)\u0003\u0002G\t\tIa)\u001e8di&|g.\r\t\u0003%!#Q!\u0013 C\u0002U\u0011\u0011!\u0016\u0005\u0006\u0017\u0002!\t\u0005T\u0001\u0007M>\u0014\u0018\r\u001c7\u0015\u0005)j\u0005\"\u0002(K\u0001\u0004y\u0015!\u00019\u0011\t))\u0015C\u000b\u0005\u0006#\u0002!\tEU\u0001\u0007KbL7\u000f^:\u0015\u0005)\u001a\u0006\"\u0002(Q\u0001\u0004y\u0005\"B+\u0001\t\u00032\u0016\u0001C2p]R\f\u0017N\\:\u0016\u0005][FC\u0001\u0016Y\u0011\u0015IF\u000b1\u0001[\u0003\u0011)G.Z7\u0011\u0005IYF!\u0002/U\u0005\u0004i&AA!2#\t\t\u0012\u0004C\u0003`\u0001\u0011\u0005\u0003-\u0001\u0003gS:$GCA1e!\rQ!-E\u0005\u0003G\u0012\u0011aa\u00149uS>t\u0007\"\u0002(_\u0001\u0004y\u0005\"\u00024\u0001\t\u0003:\u0017\u0001\u00034pY\u0012dUM\u001a;\u0016\u0005!\\GcA5\u0002*Q\u0011!.\u001c\t\u0003%-$Q\u0001\\3C\u0002U\u0011\u0011A\u0011\u0005\u0006]\u0016\u0004\ra\\\u0001\u0003_B\u0004RA\u00039k#)L!!\u001d\u0003\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004fA7tmB\u0011!\u0002^\u0005\u0003k\u0012\u0011a\u0002Z3qe\u0016\u001c\u0017\r^3e\u001d\u0006lW-M\u0003 oj\f\u0019\u0003\u0005\u0002\u000bq&\u0011\u0011\u0010\u0002\u0002\u0007'fl'm\u001c72\r\rZh0!\u0005;)\t9H\u0010\u0003\u0004~\r\u0001\u0007\u00111A\u0001\u0005]\u0006lW-\u0003\u0002;\u007f*\u0019\u0011\u0011\u0001\u0003\u0002\rMKXNY8m!\u0011\t)!a\u0003\u000f\u0007)\t9!C\u0002\u0002\n\u0011\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0007\u0003\u001f\u0011aa\u0015;sS:<'bAA\u0005\tEJ1%a\u0005\u0002 \u0005\u0005\u0012\u0011\u0001\b\u0005\u0003+\tyB\u0004\u0003\u0002\u0018\u0005uQBAA\r\u0015\r\tYBB\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015I1!!\u0001\u0005c\u0019!\u0013QCA\u000f\u000bE*Q%!\n\u0002(=\u0011\u0011qE\u0011\u0002\u0007\"1\u00111F3A\u0002)\f\u0011A\u001f\u0005\b\u0003_\u0001A\u0011IA\u0019\u0003%1w\u000e\u001c3SS\u001eDG/\u0006\u0003\u00024\u0005eB\u0003BA\u001b\u0003\u0017\"B!a\u000e\u0002<A\u0019!#!\u000f\u0005\r1\fiC1\u0001\u0016\u0011\u001dq\u0017Q\u0006a\u0001\u0003{\u0001rA\u00039\u0012\u0003o\t9\u0004K\u0003\u0002<M\f\t%\r\u0004 o\u0006\r\u0013\u0011J\u0019\u0007Gmt\u0018Q\t\u001e2\u0013\r\n\u0019\"a\b\u0002H\u0005\u0005\u0011G\u0002\u0013\u0002\u0016\u0005uQ!M\u0003&\u0003K\t9\u0003\u0003\u0005\u0002,\u00055\u0002\u0019AA\u001c\u0011\u001d\ty\u0005\u0001C!\u0003#\n!B]3ek\u000e,G*\u001a4u+\u0011\t\u0019&a\u0016\u0015\t\u0005U\u0013\u0011\f\t\u0004%\u0005]CA\u00027\u0002N\t\u0007Q\fC\u0004D\u0003\u001b\u0002\r!a\u0017\u0011\u000f)\u0001\u0018QK\t\u0002V!9\u0011q\f\u0001\u0005B\u0005\u0005\u0014a\u0003:fIV\u001cWMU5hQR,B!a\u0019\u0002hQ!\u0011QMA5!\r\u0011\u0012q\r\u0003\u0007Y\u0006u#\u0019A/\t\u000f9\fi\u00061\u0001\u0002lA9!\u0002]\t\u0002f\u0005\u0015\u0004BBA8\u0001\u0011\u0005s&\u0001\u0003mCN$\bbBA:\u0001\u0011\u0005\u0013QO\u0001\u0005i\u0006\\W\rF\u0002\u001d\u0003oBa!PA9\u0001\u00041\u0004bBA>\u0001\u0011\u0005\u0013QP\u0001\u0005IJ|\u0007\u000fF\u0002\u001d\u0003\u007fBa!PA=\u0001\u00041\u0004bBAB\u0001\u0011\u0005\u0013QQ\u0001\nIJ|\u0007OU5hQR$2\u0001HAD\u0011\u0019i\u0014\u0011\u0011a\u0001m!9\u00111\u0012\u0001\u0005B\u00055\u0015!B:mS\u000e,G#\u0002\u000f\u0002\u0010\u0006M\u0005bBAI\u0003\u0013\u0003\rAN\u0001\u0005MJ|W\u000eC\u0004\u0002\u0016\u0006%\u0005\u0019\u0001\u001c\u0002\u000bUtG/\u001b7\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\u0006IA/Y6f/\"LG.\u001a\u000b\u00049\u0005u\u0005B\u0002(\u0002\u0018\u0002\u0007q\nC\u0004\u0002\"\u0002!\t%a)\u0002\tM\u0004\u0018M\u001c\u000b\u0005\u0003K\u000bY\u000bE\u0003\u000b\u0003OcB$C\u0002\u0002*\u0012\u0011a\u0001V;qY\u0016\u0014\u0004B\u0002(\u0002 \u0002\u0007q\nC\u0004\u00020\u0002!\t%!-\u0002\u0019M\fW.Z#mK6,g\u000e^:\u0016\t\u0005M\u0016\u0011\u0019\u000b\u0004U\u0005U\u0006\u0002CA\\\u0003[\u0003\r!!/\u0002\tQD\u0017\r\u001e\t\u0006\u001d\u0005m\u0016qX\u0005\u0004\u0003{\u0013!aC$f]&#XM]1cY\u0016\u00042AEAa\t\u0019a\u0017Q\u0016b\u0001;\"9\u0011Q\u0019\u0001\u0005B\u0005\u001d\u0017!\u00047f]\u001e$\bnQ8na\u0006\u0014X\rF\u00027\u0003\u0013Dq!a3\u0002D\u0002\u0007a'A\u0002mK:Dq!a4\u0001\t\u0003\n\t.A\u0006jg\u0012+g-\u001b8fI\u0006#Hc\u0001\u0016\u0002T\"9\u0011Q[Ag\u0001\u00041\u0014!\u0001=\t\u000f\u0005e\u0007\u0001\"\u0011\u0002\\\u0006i1/Z4nK:$H*\u001a8hi\"$RANAo\u0003?DaATAl\u0001\u0004y\u0005bBAI\u0003/\u0004\rA\u000e\u0005\b\u0003G\u0004A\u0011IAs\u0003)Ig\u000eZ3y/\",'/\u001a\u000b\u0006m\u0005\u001d\u0018\u0011\u001e\u0005\u0007\u001d\u0006\u0005\b\u0019A(\t\u000f\u0005E\u0015\u0011\u001da\u0001m!9\u0011Q\u001e\u0001\u0005B\u0005=\u0018A\u00047bgRLe\u000eZ3y/\",'/\u001a\u000b\u0006m\u0005E\u00181\u001f\u0005\u0007\u001d\u0006-\b\u0019A(\t\u000f\u0005U\u00181\u001ea\u0001m\u0005\u0019QM\u001c3\t\u001d\u0005e\b\u0001%A\u0002\u0002\u0003%I!a?\u0003\b\u0005\u00112/\u001e9fe\u0012\u001a\u0018-\\3FY\u0016lWM\u001c;t+\u0011\tiP!\u0002\u0015\u0007)\ny\u0010\u0003\u0005\u00028\u0006]\b\u0019\u0001B\u0001!\u0015q\u00111\u0018B\u0002!\r\u0011\"Q\u0001\u0003\u0007Y\u0006](\u0019A/\n\t\u0005=&\u0011B\u0005\u0004\u0005\u0017\u0011!\u0001D%uKJ\f'\r\\3MS.,\u0007")
public interface LinearSeqOptimized<A, Repr extends LinearSeqOptimized<A, Repr>>
extends LinearSeqLike<A, Repr> {
    public /* synthetic */ boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable var1);

    @Override
    public boolean isEmpty();

    @Override
    public A head();

    @Override
    public Repr tail();

    @Override
    public int length();

    @Override
    public A apply(int var1);

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean forall(Function1<A, Object> var1);

    @Override
    public boolean exists(Function1<A, Object> var1);

    @Override
    public <A1> boolean contains(A1 var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    @Override
    public <B> B foldLeft(B var1, Function2<B, A, B> var2);

    @Override
    public <B> B foldRight(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B reduceLeft(Function2<B, A, B> var1);

    @Override
    public <B> B reduceRight(Function2<A, B, B> var1);

    @Override
    public A last();

    @Override
    public Repr take(int var1);

    @Override
    public Repr drop(int var1);

    @Override
    public Repr dropRight(int var1);

    @Override
    public Repr slice(int var1, int var2);

    @Override
    public Repr takeWhile(Function1<A, Object> var1);

    @Override
    public Tuple2<Repr, Repr> span(Function1<A, Object> var1);

    @Override
    public <B> boolean sameElements(GenIterable<B> var1);

    @Override
    public int lengthCompare(int var1);

    @Override
    public boolean isDefinedAt(int var1);

    @Override
    public int segmentLength(Function1<A, Object> var1, int var2);

    @Override
    public int indexWhere(Function1<A, Object> var1, int var2);

    @Override
    public int lastIndexWhere(Function1<A, Object> var1, int var2);
}

