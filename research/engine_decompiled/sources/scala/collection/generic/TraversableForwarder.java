/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.generic;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Predef$;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\rUaaB\u0001\u0003!\u0003\r\t!\u0003\u0002\u0015)J\fg/\u001a:tC\ndWMR8so\u0006\u0014H-\u001a:\u000b\u0005\r!\u0011aB4f]\u0016\u0014\u0018n\u0019\u0006\u0003\u000b\u0019\t!bY8mY\u0016\u001cG/[8o\u0015\u00059\u0011!B:dC2\f7\u0001A\u000b\u0003\u0015U\u00192\u0001A\u0006\u0010!\taQ\"D\u0001\u0007\u0013\tqaA\u0001\u0004B]f\u0014VM\u001a\t\u0004!E\u0019R\"\u0001\u0003\n\u0005I!!a\u0003+sCZ,'o]1cY\u0016\u0004\"\u0001F\u000b\r\u0001\u00111a\u0003\u0001CC\u0002]\u0011\u0011!Q\t\u00031m\u0001\"\u0001D\r\n\u0005i1!a\u0002(pi\"Lgn\u001a\t\u0003\u0019qI!!\b\u0004\u0003\u0007\u0005s\u0017\u0010C\u0003 \u0001\u0011\u0005\u0001%\u0001\u0004%S:LG\u000f\n\u000b\u0002CA\u0011ABI\u0005\u0003G\u0019\u0011A!\u00168ji\")Q\u0005\u0001D\tM\u0005QQO\u001c3fe2L\u0018N\\4\u0016\u0003=AQ\u0001\u000b\u0001\u0005B%\nqAZ8sK\u0006\u001c\u0007.\u0006\u0002+cQ\u0011\u0011e\u000b\u0005\u0006Y\u001d\u0002\r!L\u0001\u0002MB!ABL\n1\u0013\tycAA\u0005Gk:\u001cG/[8ocA\u0011A#\r\u0003\u0006e\u001d\u0012\ra\u0006\u0002\u0002+\")A\u0007\u0001C!k\u00059\u0011n]#naRLX#\u0001\u001c\u0011\u000519\u0014B\u0001\u001d\u0007\u0005\u001d\u0011un\u001c7fC:DQA\u000f\u0001\u0005BU\n\u0001B\\8o\u000b6\u0004H/\u001f\u0005\u0006y\u0001!\t%P\u0001\u0005g&TX-F\u0001?!\taq(\u0003\u0002A\r\t\u0019\u0011J\u001c;\t\u000b\t\u0003A\u0011I\u001b\u0002\u001f!\f7\u000fR3gS:LG/Z*ju\u0016DQ\u0001\u0012\u0001\u0005B\u0015\u000baAZ8sC2dGC\u0001\u001cG\u0011\u001595\t1\u0001I\u0003\u0005\u0001\b\u0003\u0002\u0007/'YBQA\u0013\u0001\u0005B-\u000ba!\u001a=jgR\u001cHC\u0001\u001cM\u0011\u00159\u0015\n1\u0001I\u0011\u0015q\u0005\u0001\"\u0011P\u0003\u0015\u0019w.\u001e8u)\tq\u0004\u000bC\u0003H\u001b\u0002\u0007\u0001\nC\u0003S\u0001\u0011\u00053+\u0001\u0003gS:$GC\u0001+X!\raQkE\u0005\u0003-\u001a\u0011aa\u00149uS>t\u0007\"B$R\u0001\u0004A\u0005\"B-\u0001\t\u0003R\u0016\u0001\u00034pY\u0012dUM\u001a;\u0016\u0005msFC\u0001/f)\ti\u0006\r\u0005\u0002\u0015=\u0012)q\f\u0017b\u0001/\t\t!\tC\u0003b1\u0002\u0007!-\u0001\u0002paB)AbY/\u0014;&\u0011AM\u0002\u0002\n\rVt7\r^5p]JBQA\u001a-A\u0002u\u000b\u0011A\u001f\u0005\u0006Q\u0002!\t%[\u0001\u000bI\u0011Lg\u000fJ2pY>tWC\u00016n)\tY\u0007\u000f\u0006\u0002m]B\u0011A#\u001c\u0003\u0006?\u001e\u0014\ra\u0006\u0005\u0006C\u001e\u0004\ra\u001c\t\u0006\u0019\rd7\u0003\u001c\u0005\u0006M\u001e\u0004\r\u0001\u001c\u0005\u0006e\u0002!\te]\u0001\nM>dGMU5hQR,\"\u0001^<\u0015\u0005UTHC\u0001<y!\t!r\u000fB\u0003`c\n\u0007q\u0003C\u0003bc\u0002\u0007\u0011\u0010E\u0003\rGN1h\u000fC\u0003gc\u0002\u0007a\u000fC\u0003}\u0001\u0011\u0005S0A\u0007%G>dwN\u001c\u0013cg2\f7\u000f[\u000b\u0004}\u0006\rAcA@\u0002\nQ!\u0011\u0011AA\u0003!\r!\u00121\u0001\u0003\u0006?n\u0014\ra\u0006\u0005\u0007Cn\u0004\r!a\u0002\u0011\u000f1\u00197#!\u0001\u0002\u0002!1am\u001fa\u0001\u0003\u0003Aq!!\u0004\u0001\t\u0003\ny!\u0001\u0006sK\u0012,8-\u001a'fMR,B!!\u0005\u0002\u0016Q!\u00111CA\r!\r!\u0012Q\u0003\u0003\b?\u0006-!\u0019AA\f#\t\u00192\u0004C\u0004b\u0003\u0017\u0001\r!a\u0007\u0011\u000f1\u0019\u00171C\n\u0002\u0014!9\u0011q\u0004\u0001\u0005B\u0005\u0005\u0012\u0001\u0005:fIV\u001cW\rT3gi>\u0003H/[8o+\u0011\t\u0019#!\u000b\u0015\t\u0005\u0015\u00121\u0006\t\u0005\u0019U\u000b9\u0003E\u0002\u0015\u0003S!qaXA\u000f\u0005\u0004\t9\u0002C\u0004b\u0003;\u0001\r!!\f\u0011\u000f1\u0019\u0017qE\n\u0002(!9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0012a\u0003:fIV\u001cWMU5hQR,B!!\u000e\u0002:Q!\u0011qGA\u001e!\r!\u0012\u0011\b\u0003\b?\u0006=\"\u0019AA\f\u0011\u001d\t\u0017q\u0006a\u0001\u0003{\u0001r\u0001D2\u0014\u0003o\t9\u0004C\u0004\u0002B\u0001!\t%a\u0011\u0002#I,G-^2f%&<\u0007\u000e^(qi&|g.\u0006\u0003\u0002F\u0005-C\u0003BA$\u0003\u001b\u0002B\u0001D+\u0002JA\u0019A#a\u0013\u0005\u000f}\u000byD1\u0001\u0002\u0018!9\u0011-a\u0010A\u0002\u0005=\u0003c\u0002\u0007d'\u0005%\u0013\u0011\n\u0005\b\u0003'\u0002A\u0011IA+\u0003\r\u0019X/\\\u000b\u0005\u0003/\nY\u0006\u0006\u0003\u0002Z\u0005u\u0003c\u0001\u000b\u0002\\\u00119q,!\u0015C\u0002\u0005]\u0001\u0002CA0\u0003#\u0002\u001d!!\u0019\u0002\u00079,X\u000e\u0005\u0004\u0002d\u0005%\u0014\u0011\f\b\u0004\u0019\u0005\u0015\u0014bAA4\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BA6\u0003[\u0012qAT;nKJL7MC\u0002\u0002h\u0019Aq!!\u001d\u0001\t\u0003\n\u0019(A\u0004qe>$Wo\u0019;\u0016\t\u0005U\u0014\u0011\u0010\u000b\u0005\u0003o\nY\bE\u0002\u0015\u0003s\"qaXA8\u0005\u0004\t9\u0002\u0003\u0005\u0002`\u0005=\u00049AA?!\u0019\t\u0019'!\u001b\u0002x!9\u0011\u0011\u0011\u0001\u0005B\u0005\r\u0015aA7j]V!\u0011QQAJ)\r\u0019\u0012q\u0011\u0005\t\u0003\u0013\u000by\bq\u0001\u0002\f\u0006\u00191-\u001c9\u0011\r\u0005\r\u0014QRAI\u0013\u0011\ty)!\u001c\u0003\u0011=\u0013H-\u001a:j]\u001e\u00042\u0001FAJ\t\u001dy\u0016q\u0010b\u0001\u0003/Aq!a&\u0001\t\u0003\nI*A\u0002nCb,B!a'\u0002$R\u00191#!(\t\u0011\u0005%\u0015Q\u0013a\u0002\u0003?\u0003b!a\u0019\u0002\u000e\u0006\u0005\u0006c\u0001\u000b\u0002$\u00129q,!&C\u0002\u0005]\u0001bBAT\u0001\u0011\u0005\u0013\u0011V\u0001\u0005Q\u0016\fG-F\u0001\u0014\u0011\u001d\ti\u000b\u0001C!\u0003_\u000b!\u0002[3bI>\u0003H/[8o+\u0005!\u0006bBAZ\u0001\u0011\u0005\u0013\u0011V\u0001\u0005Y\u0006\u001cH\u000fC\u0004\u00028\u0002!\t%a,\u0002\u00151\f7\u000f^(qi&|g\u000eC\u0004\u0002<\u0002!\t%!0\u0002\u0019\r|\u0007/\u001f+p\u0005V4g-\u001a:\u0016\t\u0005}\u00161\u001b\u000b\u0004C\u0005\u0005\u0007\u0002CAb\u0003s\u0003\r!!2\u0002\t\u0011,7\u000f\u001e\t\u0007\u0003\u000f\fi-!5\u000e\u0005\u0005%'bAAf\t\u00059Q.\u001e;bE2,\u0017\u0002BAh\u0003\u0013\u0014aAQ;gM\u0016\u0014\bc\u0001\u000b\u0002T\u00129q,!/C\u0002\u0005]\u0001bBAl\u0001\u0011\u0005\u0013\u0011\\\u0001\fG>\u0004\u0018\u0010V8BeJ\f\u00170\u0006\u0003\u0002\\\u0006%HcB\u0011\u0002^\u0006-\u0018q\u001e\u0005\t\u0003?\f)\u000e1\u0001\u0002b\u0006\u0011\u0001p\u001d\t\u0006\u0019\u0005\r\u0018q]\u0005\u0004\u0003K4!!B!se\u0006L\bc\u0001\u000b\u0002j\u00129q,!6C\u0002\u0005]\u0001bBAw\u0003+\u0004\rAP\u0001\u0006gR\f'\u000f\u001e\u0005\b\u0003c\f)\u000e1\u0001?\u0003\raWM\u001c\u0005\b\u0003/\u0004A\u0011IA{+\u0011\t90a@\u0015\u000b\u0005\nIP!\u0001\t\u0011\u0005}\u00171\u001fa\u0001\u0003w\u0004R\u0001DAr\u0003{\u00042\u0001FA\u0000\t\u001dy\u00161\u001fb\u0001\u0003/Aq!!<\u0002t\u0002\u0007a\bC\u0004\u0002X\u0002!\tE!\u0002\u0016\t\t\u001d!q\u0002\u000b\u0004C\t%\u0001\u0002CAp\u0005\u0007\u0001\rAa\u0003\u0011\u000b1\t\u0019O!\u0004\u0011\u0007Q\u0011y\u0001B\u0004`\u0005\u0007\u0011\r!a\u0006\t\u000f\tM\u0001\u0001\"\u0011\u0003\u0016\u00059Ao\\!se\u0006LX\u0003\u0002B\f\u0005;!BA!\u0007\u0003 A)A\"a9\u0003\u001cA\u0019AC!\b\u0005\u000f}\u0013\tB1\u0001\u0002\u0018!Q!\u0011\u0005B\t\u0003\u0003\u0005\u001dAa\t\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0003&\t-\"1D\u0007\u0003\u0005OQ1A!\u000b\u0007\u0003\u001d\u0011XM\u001a7fGRLAA!\f\u0003(\tA1\t\\1tgR\u000bw\rC\u0004\u00032\u0001!\tEa\r\u0002\rQ|G*[:u+\t\u0011)\u0004E\u0003\u00038\tu2#\u0004\u0002\u0003:)\u0019!1\b\u0003\u0002\u0013%lW.\u001e;bE2,\u0017\u0002\u0002B \u0005s\u0011A\u0001T5ti\"9!1\t\u0001\u0005B\t\u0015\u0013A\u0003;p\u0013R,'/\u00192mKV\u0011!q\t\t\u0005!\t%3#C\u0002\u0003L\u0011\u0011\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0005\b\u0005\u001f\u0002A\u0011\tB)\u0003\u0015!xnU3r+\t\u0011\u0019\u0006\u0005\u0003\u0011\u0005+\u001a\u0012b\u0001B,\t\t\u00191+Z9\t\u000f\tm\u0003\u0001\"\u0011\u0003^\u0005aAo\\%oI\u0016DX\rZ*fcV\u0011!q\f\t\u0006\u0005o\u0011\tgE\u0005\u0005\u0005G\u0012ID\u0001\u0006J]\u0012,\u00070\u001a3TKFDqAa\u001a\u0001\t\u0003\u0012I'\u0001\u0005u_\n+hMZ3s+\u0011\u0011YG!\u001d\u0016\u0005\t5\u0004CBAd\u0003\u001b\u0014y\u0007E\u0002\u0015\u0005c\"qa\u0018B3\u0005\u0004\t9\u0002C\u0004\u0003v\u0001!\tEa\u001e\u0002\u0011Q|7\u000b\u001e:fC6,\"A!\u001f\u0011\u000b\t]\"1P\n\n\t\tu$\u0011\b\u0002\u0007'R\u0014X-Y7\t\u000f\t\u0005\u0005\u0001\"\u0011\u0003\u0004\u0006)Ao\\*fiV!!Q\u0011BH+\t\u00119\t\u0005\u0004\u00038\t%%QR\u0005\u0005\u0005\u0017\u0013IDA\u0002TKR\u00042\u0001\u0006BH\t\u001dy&q\u0010b\u0001\u0003/AqAa%\u0001\t\u0003\u0012)*A\u0003u_6\u000b\u0007/\u0006\u0004\u0003\u0018\n\u0005&q\u0015\u000b\u0005\u00053\u0013I\u000b\u0005\u0005\u00038\tm%q\u0014BS\u0013\u0011\u0011iJ!\u000f\u0003\u00075\u000b\u0007\u000fE\u0002\u0015\u0005C#qAa)\u0003\u0012\n\u0007qCA\u0001U!\r!\"q\u0015\u0003\u0007e\tE%\u0019A\f\t\u0011\t-&\u0011\u0013a\u0002\u0005[\u000b!!\u001a<\u0011\u000f\t=&QW\n\u0003<:\u0019AB!-\n\u0007\tMf!\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005o\u0013IL\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tg*\u0019!1\u0017\u0004\u0011\u000f1\u0011iLa(\u0003&&\u0019!q\u0018\u0004\u0003\rQ+\b\u000f\\33\u0011\u001d\u0011\u0019\r\u0001C!\u0005\u000b\f\u0001\"\\6TiJLgn\u001a\u000b\t\u0005\u000f\u0014iMa4\u0003TB!!q\u0016Be\u0013\u0011\u0011YM!/\u0003\rM#(/\u001b8h\u0011!\tiO!1A\u0002\t\u001d\u0007\u0002\u0003Bi\u0005\u0003\u0004\rAa2\u0002\u0007M,\u0007\u000f\u0003\u0005\u0003V\n\u0005\u0007\u0019\u0001Bd\u0003\r)g\u000e\u001a\u0005\b\u0005\u0007\u0004A\u0011\tBm)\u0011\u00119Ma7\t\u0011\tE'q\u001ba\u0001\u0005\u000fDqAa1\u0001\t\u0003\u0012y.\u0006\u0002\u0003H\"9!1\u001d\u0001\u0005B\t\u0015\u0018!C1eIN#(/\u001b8h))\u00119O!<\u0003r\nM(Q\u001f\t\u0005\u0003\u000f\u0014I/\u0003\u0003\u0003l\u0006%'!D*ue&twMQ;jY\u0012,'\u000f\u0003\u0005\u0003p\n\u0005\b\u0019\u0001Bt\u0003\u0005\u0011\u0007\u0002CAw\u0005C\u0004\rAa2\t\u0011\tE'\u0011\u001da\u0001\u0005\u000fD\u0001B!6\u0003b\u0002\u0007!q\u0019\u0005\b\u0005G\u0004A\u0011\tB})\u0019\u00119Oa?\u0003~\"A!q\u001eB|\u0001\u0004\u00119\u000f\u0003\u0005\u0003R\n]\b\u0019\u0001Bd\u0011\u001d\u0011\u0019\u000f\u0001C!\u0007\u0003!BAa:\u0004\u0004!A!q\u001eB\u0000\u0001\u0004\u00119\u000fK\u0004\u0001\u0007\u000f\u0019ia!\u0005\u0011\u00071\u0019I!C\u0002\u0004\f\u0019\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t\u0019y!A0G_J<\u0018M\u001d3j]\u001e\u0004\u0013n\u001d\u0011j]\",'/\u001a8uYf\u0004SO\u001c:fY&\f'\r\\3!g&t7-\u001a\u0011ji\u0002J7\u000f\t8pi\u0002\nW\u000f^8nCR,G\rI1oI\u0002rWm\u001e\u0011nKRDw\u000eZ:!G\u0006t\u0007EY3!M>\u0014xm\u001c;uK:t\u0013EAB\n\u0003\u0019\u0011d&M\u0019/a\u0001")
public interface TraversableForwarder<A>
extends Traversable<A> {
    public Traversable<A> underlying();

    @Override
    public <U> void foreach(Function1<A, U> var1);

    @Override
    public boolean isEmpty();

    @Override
    public boolean nonEmpty();

    @Override
    public int size();

    @Override
    public boolean hasDefiniteSize();

    @Override
    public boolean forall(Function1<A, Object> var1);

    @Override
    public boolean exists(Function1<A, Object> var1);

    @Override
    public int count(Function1<A, Object> var1);

    @Override
    public Option<A> find(Function1<A, Object> var1);

    @Override
    public <B> B foldLeft(B var1, Function2<B, A, B> var2);

    @Override
    public <B> B $div$colon(B var1, Function2<B, A, B> var2);

    @Override
    public <B> B foldRight(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B $colon$bslash(B var1, Function2<A, B, B> var2);

    @Override
    public <B> B reduceLeft(Function2<B, A, B> var1);

    @Override
    public <B> Option<B> reduceLeftOption(Function2<B, A, B> var1);

    @Override
    public <B> B reduceRight(Function2<A, B, B> var1);

    @Override
    public <B> Option<B> reduceRightOption(Function2<A, B, B> var1);

    @Override
    public <B> B sum(Numeric<B> var1);

    @Override
    public <B> B product(Numeric<B> var1);

    @Override
    public <B> A min(Ordering<B> var1);

    @Override
    public <B> A max(Ordering<B> var1);

    @Override
    public A head();

    @Override
    public Option<A> headOption();

    @Override
    public A last();

    @Override
    public Option<A> lastOption();

    @Override
    public <B> void copyToBuffer(Buffer<B> var1);

    @Override
    public <B> void copyToArray(Object var1, int var2, int var3);

    @Override
    public <B> void copyToArray(Object var1, int var2);

    @Override
    public <B> void copyToArray(Object var1);

    @Override
    public <B> Object toArray(ClassTag<B> var1);

    @Override
    public List<A> toList();

    @Override
    public Iterable<A> toIterable();

    @Override
    public Seq<A> toSeq();

    @Override
    public IndexedSeq<A> toIndexedSeq();

    @Override
    public <B> Buffer<B> toBuffer();

    @Override
    public Stream<A> toStream();

    @Override
    public <B> Set<B> toSet();

    @Override
    public <T, U> Map<T, U> toMap(Predef$.less.colon.less<A, Tuple2<T, U>> var1);

    @Override
    public String mkString(String var1, String var2, String var3);

    @Override
    public String mkString(String var1);

    @Override
    public String mkString();

    @Override
    public StringBuilder addString(StringBuilder var1, String var2, String var3, String var4);

    @Override
    public StringBuilder addString(StringBuilder var1, String var2);

    @Override
    public StringBuilder addString(StringBuilder var1);
}

