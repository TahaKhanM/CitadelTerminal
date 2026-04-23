/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.runtime.SynchronizedSymbols;
import scala.reflect.runtime.SynchronizedTypes;

@ScalaSignature(bytes="\u0006\u0001\tMeAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0005\u0003\u0010\ny1+\u001f8dQJ|g.\u001b>fI>\u00038O\u0003\u0002\u0004\t\u00059!/\u001e8uS6,'BA\u0003\u0007\u0003\u001d\u0011XM\u001a7fGRT\u0011aB\u0001\u0006g\u000e\fG.Y\n\u0005\u0001%y1\u0003\u0005\u0002\u000b\u001b5\t1B\u0003\u0002\r\t\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u000f\u0017\tY1+_7c_2$\u0016M\u00197f!\t\u0001\u0012#D\u0001\u0003\u0013\t\u0011\"AA\nTs:\u001c\u0007N]8oSj,GmU=nE>d7\u000f\u0005\u0002\u0011)%\u0011QC\u0001\u0002\u0012'ft7\r\u001b:p]&TX\r\u001a+za\u0016\u001c\b\"B\f\u0001\t\u0003I\u0012A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003i\u0001\"a\u0007\u000f\u000e\u0003\u0019I!!\b\u0004\u0003\tUs\u0017\u000e\u001e\u0005\u0006?\u0001!\t\u0006I\u0001\u0011gft7\r\u001b:p]&TXMT1nKN,\u0012!\t\t\u00037\tJ!a\t\u0004\u0003\u000f\t{w\u000e\\3b]\")Q\u0005\u0001C)M\u0005qa.Z<CCN,G+\u001f9f'\u0016\fHcA\u0014.wA\u0011\u0001&K\u0007\u0002\u0001%\u0011!f\u000b\u0002\f\u0005\u0006\u001cX\rV=qKN+\u0017/\u0003\u0002-\u0017\ta!)Y:f)f\u0004XmU3rg\")a\u0006\na\u0001_\u00059\u0001/\u0019:f]R\u001c\bc\u0001\u00194m9\u00111$M\u0005\u0003e\u0019\tq\u0001]1dW\u0006<W-\u0003\u00025k\t!A*[:u\u0015\t\u0011d\u0001\u0005\u0002)o%\u0011\u0001(\u000f\u0002\u0005)f\u0004X-\u0003\u0002;\u0017\t)A+\u001f9fg\")A\b\na\u0001{\u0005)Q\r\\3ngB\u00191D\u0010\u001c\n\u0005}2!!B!se\u0006LhaB!\u0001!\u0003\r\tA\u0011\u0002\u0018'ft7\r\u001b:p]&TX\r\u001a\"bg\u0016$\u0016\u0010]3TKF\u001c\"\u0001Q\u0014\t\u000b]\u0001E\u0011A\r\t\u000b\u0015\u0003E\u0011\t$\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005Y:\u0005\"\u0002%E\u0001\u0004I\u0015!A5\u0011\u0005mQ\u0015BA&\u0007\u0005\rIe\u000e\u001e\u0005\u0006\u001b\u0002#\tET\u0001\be\u0006<X\t\\3n)\t1t\nC\u0003I\u0019\u0002\u0007\u0011\nC\u0003R\u0001\u0012\u0005#+\u0001\u0006usB,7+_7c_2$\"a\u0015-\u0011\u0005!\"\u0016BA+W\u0005\u0019\u0019\u00160\u001c2pY&\u0011qk\u0003\u0002\b'fl'm\u001c7t\u0011\u0015A\u0005\u000b1\u0001J\u0011\u0015Q\u0006\t\"\u0011\\\u0003\u0019!x\u000eT5tiV\tq\u0006C\u0003^\u0001\u0012\u0005c,\u0001\u0003d_BLHcA\u0014`C\")\u0001\r\u0018a\u0001m\u0005!\u0001.Z1e\u0011\u0015\u0011G\f1\u0001J\u0003\u0019ygMZ:fi\")A\r\u0011C!K\u0006\u0019Q.\u00199\u0015\u0005\u001d2\u0007\"B4d\u0001\u0004A\u0017!\u00014\u0011\tmIgGN\u0005\u0003U\u001a\u0011\u0011BR;oGRLwN\\\u0019\t\u000b1\u0004E\u0011I7\u0002\r\u0015D\u0018n\u001d;t)\t\tc\u000eC\u0003pW\u0002\u0007\u0001/A\u0001q!\u0011Y\u0012NN\u0011\t\u0011I\u0004\u0005R1A\u0005BM\f\u0001\"\\1y\t\u0016\u0004H\u000f[\u000b\u0002iB\u0011!\"^\u0005\u0003m.\u0011Q\u0001R3qi\"D\u0001\u0002\u001f!\t\u0002\u0003\u0006K\u0001^\u0001\n[\u0006DH)\u001a9uQ\u0002BQA\u001f!\u0005Bm\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002yB\u0019Q0!\u0001\u000f\u0005mq\u0018BA@\u0007\u0003\u0019\u0001&/\u001a3fM&!\u00111AA\u0003\u0005\u0019\u0019FO]5oO*\u0011qP\u0002\u0005\b\u0003\u0013\u0001E\u0011IA\u0006\u0003\u001da\u0017\r^3NCB$2aJA\u0007\u0011\u00199\u0017q\u0001a\u0001Q\"q\u0011\u0011\u0003!\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\u0014\u0005]\u0011aC:va\u0016\u0014H%\u00199qYf$2ANA\u000b\u0011\u0019A\u0015q\u0002a\u0001\u0013&\u0011Q)\u000b\u0005\u000f\u00037\u0001\u0005\u0013aA\u0001\u0002\u0013%\u0011QDA\u0011\u00035\u0019X\u000f]3sII\fw/\u00127f[R\u0019a'a\b\t\r!\u000bI\u00021\u0001J\u0013\ti\u0015\u0006\u0003\b\u0002&\u0001\u0003\n1!A\u0001\n\u0013\t9#a\u000b\u0002!M,\b/\u001a:%if\u0004XmU=nE>dGcA*\u0002*!1\u0001*a\tA\u0002%K!!U\u0015\t\u001b\u0005=\u0002\t%A\u0002\u0002\u0003%IaWA\u0019\u00031\u0019X\u000f]3sIQ|G*[:u\u0013\tQ\u0016\u0006\u0003\b\u00026\u0001\u0003\n1!A\u0001\n\u0013\t9$!\u0010\u0002\u0015M,\b/\u001a:%G>\u0004\u0018\u0010F\u0003(\u0003s\tY\u0004\u0003\u0004a\u0003g\u0001\rA\u000e\u0005\u0007E\u0006M\u0002\u0019A%\n\u0005uK\u0003BDA!\u0001B\u0005\u0019\u0011!A\u0005\n\u0005\r\u0013qI\u0001\ngV\u0004XM\u001d\u0013nCB$2aJA#\u0011\u00199\u0017q\ba\u0001Q&\u0011A-\u000b\u0005\u000f\u0003\u0017\u0002\u0005\u0013aA\u0001\u0002\u0013%\u0011QJA)\u00031\u0019X\u000f]3sI\u0015D\u0018n\u001d;t)\r\t\u0013q\n\u0005\u0007_\u0006%\u0003\u0019\u00019\n\u00051L\u0003\"DA+\u0001B\u0005\u0019\u0011!A\u0005\nM\f9&A\u000btkB,'\u000fJ7bq\u0012+\u0007\u000f\u001e5PM\u0016cW-\\:\n\u0007\u0005e\u0013&A\bnCb$U\r\u001d;i\u001f\u001a,E.Z7t\u00115\ti\u0006\u0011I\u0001\u0004\u0003\u0005I\u0011B>\u0002`\u0005q1/\u001e9fe\u0012\"xn\u0015;sS:<\u0017B\u0001>*\u0011\u001d\t\u0019\u0007\u0001C!\u0003K\n\u0001B\\3x'\u000e|\u0007/Z\u000b\u0003\u0003O\u0012b!!\u001b\u0002n\u0005]daBA6\u0003C\u0002\u0011q\r\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004Q\u0005=\u0014\u0002BA9\u0003g\u0012QaU2pa\u0016L1!!\u001e\f\u0005\u0019\u00196m\u001c9fgB\u0019\u0001&!\u001f\u0007\u0013\u0005m\u0004\u0001%A\u0002\u0002\u0005u$!E*z]\u000eD'o\u001c8ju\u0016$7kY8qKN!\u0011\u0011PA7\u0011\u00199\u0012\u0011\u0010C\u00013!Y\u00111QA=\u0011\u000b\u0007I\u0011BAC\u0003!\u0019\u0018P\\2M_\u000e\\WCAAD!\u0011\tI)a%\u000e\u0005\u0005-%\u0002BAG\u0003\u001f\u000bA\u0001\\1oO*\u0011\u0011\u0011S\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0016\u0006-%AB(cU\u0016\u001cG\u000fC\u0006\u0002\u001a\u0006e\u0004\u0012!Q!\n\u0005\u001d\u0015!C:z]\u000edunY6!\u0011!\ti*!\u001f\u0005\u0002\u0005}\u0015\u0001F:z]\u000edunY6Ts:\u001c\u0007N]8oSj,G-\u0006\u0003\u0002\"\u0006\u001dF\u0003BAR\u0003s\u0003B!!*\u0002(2\u0001A\u0001CAU\u00037\u0013\r!a+\u0003\u0003Q\u000bB!!,\u00024B\u00191$a,\n\u0007\u0005EfAA\u0004O_RD\u0017N\\4\u0011\u0007m\t),C\u0002\u00028\u001a\u00111!\u00118z\u0011%\tY,a'\u0005\u0002\u0004\ti,\u0001\u0003c_\u0012L\b#B\u000e\u0002@\u0006\r\u0016bAAa\r\tAAHY=oC6,g\bC\u0004\u0002F\u0006eD\u0011\t\u0011\u0002\u000f%\u001cX)\u001c9us\"A\u0011\u0011ZA=\t\u0003\nY-\u0001\u0003tSj,W#A%\t\u0011\u0005=\u0017\u0011\u0010C!\u0003#\fQ!\u001a8uKJ,B!a5\u0002XR!\u0011Q[An!\u0011\t)+a6\u0005\u0011\u0005%\u0016Q\u001ab\u0001\u00033\f2!!,T\u0011!\ti.!4A\u0002\u0005U\u0017aA:z[\"A\u0011\u0011]A=\t\u0003\n\u0019/\u0001\u0004sK\"\f7\u000f\u001b\u000b\u00065\u0005\u0015\u0018q\u001d\u0005\b\u0003;\fy\u000e1\u0001T\u0011!\tI/a8A\u0002\u0005-\u0018a\u00028fo:\fW.\u001a\t\u0004Q\u00055\u0018\u0002BAx\u0003c\u0014AAT1nK&\u0019\u00111_\u0006\u0003\u000b9\u000bW.Z:\t\u0011\u0005]\u0018\u0011\u0010C!\u0003s\fa!\u001e8mS:\\Gc\u0001\u000e\u0002|\"A\u0011Q`A{\u0001\u0004\ty0A\u0001f!\rA#\u0011A\u0005\u0005\u0005\u0007\t\u0019H\u0001\u0006TG>\u0004X-\u00128uefD\u0001\"a>\u0002z\u0011\u0005#q\u0001\u000b\u00045\t%\u0001bBAo\u0005\u000b\u0001\ra\u0015\u0005\t\u0005\u001b\tI\b\"\u0011\u0003\u0010\u0005IAn\\8lkB\fE\u000e\u001c\u000b\u0005\u0005#\u00119\u0002\u0005\u00031\u0005'\u0019\u0016b\u0001B\u000bk\tA\u0011\n^3sCR|'\u000f\u0003\u0005\u0003\u001a\t-\u0001\u0019AAv\u0003\u0011q\u0017-\\3\t\u0011\tu\u0011\u0011\u0010C!\u0005?\t1\u0002\\8pWV\u0004XI\u001c;ssR!\u0011q B\u0011\u0011!\u0011IBa\u0007A\u0002\u0005-\b\u0002\u0003B\u0013\u0003s\"\tEa\n\u0002\u001f1|wn[;q\u001d\u0016DH/\u00128uef$B!a@\u0003*!A!1\u0006B\u0012\u0001\u0004\ty0A\u0003f]R\u0014\u0018\u0010C\u0004[\u0003s\"\tEa\f\u0016\u0005\tE\u0002c\u0001\u00194'\"q!QGA=!\u0003\r\t\u0011!C\u0005A\t]\u0012!D:va\u0016\u0014H%[:F[B$\u00180\u0003\u0003\u0002F\u0006=\u0004b\u0004B\u001e\u0003s\u0002\n1!A\u0001\n\u0013\tYM!\u0010\u0002\u0015M,\b/\u001a:%g&TX-\u0003\u0003\u0002J\u0006=\u0004b\u0004B!\u0003s\u0002\n1!A\u0001\n\u0013\u0011\u0019E!\u0014\u0002\u0017M,\b/\u001a:%K:$XM]\u000b\u0005\u0005\u000b\u0012I\u0005\u0006\u0003\u0003H\t-\u0003\u0003BAS\u0005\u0013\"\u0001\"!+\u0003@\t\u0007\u0011\u0011\u001c\u0005\t\u0003;\u0014y\u00041\u0001\u0003H%!\u0011qZA8\u0011=\u0011\t&!\u001f\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003T\te\u0013\u0001D:va\u0016\u0014HE]3iCNDG#\u0002\u000e\u0003V\t]\u0003bBAo\u0005\u001f\u0002\ra\u0015\u0005\t\u0003S\u0014y\u00051\u0001\u0002l&!\u0011\u0011]A8\u0011=\u0011i&!\u001f\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003`\t\r\u0014\u0001D:va\u0016\u0014H%\u001e8mS:\\Gc\u0001\u000e\u0003b!A\u0011Q B.\u0001\u0004\ty0\u0003\u0003\u0002x\u0006=\u0004b\u0004B/\u0003s\u0002\n1!A\u0001\n\u0013\u00119Ga\u001b\u0015\u0007i\u0011I\u0007C\u0004\u0002^\n\u0015\u0004\u0019A*\n\t\u0005]\u0018q\u000e\u0005\u0010\u0005_\nI\b%A\u0002\u0002\u0003%IA!\u001d\u0003v\u0005y1/\u001e9fe\u0012bwn\\6va\u0006cG\u000e\u0006\u0003\u0003\u0012\tM\u0004\u0002\u0003B\r\u0005[\u0002\r!a;\n\t\t5\u0011q\u000e\u0005\u0010\u0005s\nI\b%A\u0002\u0002\u0003%IAa\u001f\u0003\u0000\u0005\t2/\u001e9fe\u0012bwn\\6va\u0016sGO]=\u0015\t\u0005}(Q\u0010\u0005\t\u00053\u00119\b1\u0001\u0002l&!!QDA8\u0011=\u0011\u0019)!\u001f\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003\u0006\n%\u0015!F:va\u0016\u0014H\u0005\\8pWV\u0004h*\u001a=u\u000b:$(/\u001f\u000b\u0005\u0003\u007f\u00149\t\u0003\u0005\u0003,\t\u0005\u0005\u0019AA\u0000\u0013\u0011\u0011)#a\u001c\t\u001f\u0005=\u0012\u0011\u0010I\u0001\u0004\u0003\u0005I\u0011\u0002B\u0018\u0005\u001bK1AWA8!\r\u0001\"\u0011S\u0005\u0003\u001d\t\u0001")
public interface SynchronizedOps
extends SynchronizedSymbols,
SynchronizedTypes {
    public boolean synchronizeNames();

    public BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(List<Types.Type> var1, Types.Type[] var2);

    public SynchronizedScope newScope();

    public interface SynchronizedScope {
        public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty();

        public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size();

        public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(Symbols.Symbol var1);

        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(Symbols.Symbol var1, Names.Name var2);

        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Scopes.ScopeEntry var1);

        public /* synthetic */ void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(Symbols.Symbol var1);

        public /* synthetic */ Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(Names.Name var1);

        public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(Names.Name var1);

        public /* synthetic */ Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(Scopes.ScopeEntry var1);

        public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList();

        public Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock();

        public <T> T syncLockSynchronized(Function0<T> var1);

        public boolean isEmpty();

        public int size();

        public <T extends Symbols.Symbol> T enter(T var1);

        public void rehash(Symbols.Symbol var1, Names.Name var2);

        public void unlink(Scopes.ScopeEntry var1);

        public void unlink(Symbols.Symbol var1);

        public Iterator<Symbols.Symbol> lookupAll(Names.Name var1);

        public Scopes.ScopeEntry lookupEntry(Names.Name var1);

        public Scopes.ScopeEntry lookupNextEntry(Scopes.ScopeEntry var1);

        public List<Symbols.Symbol> toList();

        public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer();
    }

    public interface SynchronizedBaseTypeSeq {
        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(int var1);

        public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(int var1);

        public /* synthetic */ Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(int var1);

        public /* synthetic */ List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();

        public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(Types.Type var1, int var2);

        public /* synthetic */ BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(Function1 var1);

        public /* synthetic */ boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(Function1 var1);

        public /* synthetic */ int scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$maxDepthOfElems();

        public /* synthetic */ String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();

        public Types.Type apply(int var1);

        public Types.Type rawElem(int var1);

        public Symbols.Symbol typeSymbol(int var1);

        public List<Types.Type> toList();

        public BaseTypeSeqs.BaseTypeSeq copy(Types.Type var1, int var2);

        public BaseTypeSeqs.BaseTypeSeq map(Function1<Types.Type, Types.Type> var1);

        public boolean exists(Function1<Types.Type, Object> var1);

        public int maxDepth();

        public String toString();

        public BaseTypeSeqs.BaseTypeSeq lateMap(Function1<Types.Type, Types.Type> var1);

        public /* synthetic */ SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
    }
}

