/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.WeakHashMap;
import scala.ref.WeakReference;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.runtime.ThreadLocalStorage;

@ScalaSignature(bytes="\u0006\u0001\tedAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0005\u0003r\t\t2+\u001f8dQJ|g.\u001b>fIRK\b/Z:\u000b\u0005\r!\u0011a\u0002:v]RLW.\u001a\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b'\r\u0001\u0011\"\u0004\t\u0003\u0015-i\u0011AB\u0005\u0003\u0019\u0019\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0012\u001b\u0005y!B\u0001\t\u0005\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\n\u0010\u0005\u0015!\u0016\u0010]3t\u0011\u0015!\u0002\u0001\"\u0001\u0017\u0003\u0019!\u0013N\\5uI\r\u0001A#A\f\u0011\u0005)A\u0012BA\r\u0007\u0005\u0011)f.\u001b;\t\u000bm\u0001A\u0011\u000b\u000f\u0002\u001d\r|W.\\8o\u001f^tWM]'baV\tQ\u0004\u0005\u0002\u001f?5\t\u0001!\u0003\u0002!C\tq1i\\7n_:|uO\\3s\u001b\u0006\u0004\u0018B\u0001\u0012$\u00051\u0019u.\\7p]>;h.\u001a:t\u0015\t!s\"A\u0002ua\u0016D\u0001B\n\u0001\t\u0006\u0004%IaJ\u0001\u000bk:L\u0017/^3M_\u000e\\W#\u0001\u0015\u0011\u0005%rS\"\u0001\u0016\u000b\u0005-b\u0013\u0001\u00027b]\u001eT\u0011!L\u0001\u0005U\u00064\u0018-\u0003\u00020U\t1qJ\u00196fGRD\u0001\"\r\u0001\t\u0002\u0003\u0006K\u0001K\u0001\fk:L\u0017/^3M_\u000e\\\u0007\u0005C\u00044\u0001\t\u0007I\u0011\u0002\u001b\u0002\u000fUt\u0017.];fgV\tQ\u0007\u0005\u00037wu\u0002U\"A\u001c\u000b\u0005aJ\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003u\u0019\t!bY8mY\u0016\u001cG/[8o\u0013\tatGA\u0006XK\u0006\\\u0007*Y:i\u001b\u0006\u0004\bC\u0001\u0010?\u0013\ty\u0014C\u0001\u0003UsB,\u0007cA!E{5\t!I\u0003\u0002DU\u0005\u0019!/\u001a4\n\u0005\u0015\u0013%!D,fC.\u0014VMZ3sK:\u001cW\r\u0003\u0004H\u0001\u0001\u0006I!N\u0001\tk:L\u0017/^3tA!)\u0011\n\u0001C!\u0015\u00061QO\\5rk\u0016,\"a\u0013(\u0015\u00051#\u0006CA'O\u0019\u0001!Qa\u0014%C\u0002A\u0013\u0011\u0001V\t\u0003#v\u0002\"A\u0003*\n\u0005M3!a\u0002(pi\"Lgn\u001a\u0005\u0006+\"\u0003\r\u0001T\u0001\u0003iBD\u0001b\u0016\u0001\t\u0006\u0004%I\u0001W\u0001\u0014?N\\w\u000e\\3nSj\fG/[8o\u0019\u00164X\r\\\u000b\u00023B\u0019aDW/\n\u0005mc&A\u0005+ie\u0016\fG\rT8dC2\u001cFo\u001c:bO\u0016L!a\u0017\u0002\u0011\u0005)q\u0016BA0\u0007\u0005\rIe\u000e\u001e\u0005\tC\u0002A\t\u0011)Q\u00053\u0006!rl]6pY\u0016l\u0017N_1uS>tG*\u001a<fY\u0002BQa\u0019\u0001\u0005B\u0011\f!c]6pY\u0016l\u0017N_1uS>tG*\u001a<fYV\tQ\fC\u0003g\u0001\u0011\u0005s-\u0001\ftW>dW-\\5{CRLwN\u001c'fm\u0016dw\fJ3r)\t9\u0002\u000eC\u0003jK\u0002\u0007Q,A\u0003wC2,X\r\u0003\u0005l\u0001!\u0015\r\u0011\"\u0003m\u0003!yVO\u001c3p\u0019><W#A7\u0011\u0007yQf\u000e\u0005\u0002\u001f_&\u0011\u0001/\u001d\u0002\b+:$w\u000eT8h\u0013\t\u00118EA\bUsB,7i\u001c8tiJ\f\u0017N\u001c;t\u0011!!\b\u0001#A!B\u0013i\u0017!C0v]\u0012|Gj\\4!\u0011\u00151\b\u0001\"\u0011x\u0003\u001d)h\u000eZ8M_\u001e,\u0012A\u001c\u0005\ts\u0002A)\u0019!C\u0005u\u0006!r,\u001b8uKJ\u001cXm\u0019;j_:<\u0016\u000e\u001e8fgN,\u0012a\u001f\t\u0004=ic\b#\u0002\u001c<{\u0006%\u0001\u0003\u0002@\u0002\u0004ur!AC@\n\u0007\u0005\u0005a!A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0011q\u0001\u0002\u0005\u0019&\u001cHOC\u0002\u0002\u0002\u0019\u0001R!a\u0003\u0002\u0010uj!!!\u0004\u000b\u0005\r3\u0011bA#\u0002\u000e!I\u00111\u0003\u0001\t\u0002\u0003\u0006Ka_\u0001\u0016?&tG/\u001a:tK\u000e$\u0018n\u001c8XSRtWm]:!\u0011\u001d\t9\u0002\u0001C!\u00033\t1#\u001b8uKJ\u001cXm\u0019;j_:<\u0016\u000e\u001e8fgN,\u0012\u0001 \u0005\n\u0003;\u0001\u0001R1A\u0005\na\u000bacX:vEN\fW.\u001a;za\u0016\u0014VmY;sg&|gn\u001d\u0005\n\u0003C\u0001\u0001\u0012!Q!\ne\u000bqcX:vEN\fW.\u001a;za\u0016\u0014VmY;sg&|gn\u001d\u0011\t\r\u0005\u0015\u0002\u0001\"\u0011e\u0003U\u0019XOY:b[\u0016$\u0018\u0010]3SK\u000e,(o]5p]NDq!!\u000b\u0001\t\u0003\nY#A\rtk\n\u001c\u0018-\\3usB,'+Z2veNLwN\\:`I\u0015\fHcA\f\u0002.!1\u0011.a\nA\u0002uC!\"!\r\u0001\u0011\u000b\u0007I\u0011BA\u001a\u0003Ay\u0006/\u001a8eS:<7+\u001e2UsB,7/\u0006\u0002\u00026A!aDWA\u001c!\u00151\u0014\u0011HA\u001f\u0013\r\tYd\u000e\u0002\b\u0011\u0006\u001c\bnU3u!\rq\u0012qH\u0005\u0005\u0003\u0003\n\u0019EA\u0006Tk\n$\u0016\u0010]3QC&\u0014\u0018bAA#G\tiA+\u001f9f\u0007>l\u0007/\u0019:feND!\"!\u0013\u0001\u0011\u0003\u0005\u000b\u0015BA\u001b\u0003Ey\u0006/\u001a8eS:<7+\u001e2UsB,7\u000f\t\u0005\b\u0003\u001b\u0002A\u0011IA(\u0003=\u0001XM\u001c3j]\u001e\u001cVO\u0019+za\u0016\u001cXCAA\u001c\u0011%\t\u0019\u0006\u0001EC\u0002\u0013%\u0001,A\n`E\u0006\u001cX\r^=qKJ+7-\u001e:tS>t7\u000fC\u0005\u0002X\u0001A\t\u0011)Q\u00053\u0006!rLY1tKRL\b/\u001a*fGV\u00148/[8og\u0002Ba!a\u0017\u0001\t\u0003\"\u0017A\u00052bg\u0016$\u0018\u0010]3SK\u000e,(o]5p]NDq!a\u0018\u0001\t\u0003\n\t'\u0001\fcCN,G/\u001f9f%\u0016\u001cWO]:j_:\u001cx\fJ3r)\r9\u00121\r\u0005\u0007S\u0006u\u0003\u0019A/\t\u0015\u0005\u001d\u0004\u0001#b\u0001\n\u0013\tI'A\t`a\u0016tG-\u001b8h\u0005\u0006\u001cX\rV=qKN,\"!a\u001b\u0011\tyQ\u0016Q\u000e\t\u0005m\u0005eR\b\u0003\u0006\u0002r\u0001A\t\u0011)Q\u0005\u0003W\n!c\u00189f]\u0012Lgn\u001a\"bg\u0016$\u0016\u0010]3tA!9\u0011Q\u000f\u0001\u0005B\u0005]\u0014\u0001\u00059f]\u0012Lgn\u001a\"bg\u0016$\u0016\u0010]3t+\t\ti\u0007\u0003\u0006\u0002|\u0001A)\u0019!C\u0005\u0003{\n1b\u00187vEJ+7/\u001e7ugV\u0011\u0011q\u0010\t\u0005=i\u000b\t\t\u0005\u00047\u0003\u0007\u000b9)P\u0005\u0004\u0003\u000b;$a\u0002%bg\"l\u0015\r\u001d\t\u0007\u0015\u0005%\u0015QR?\n\u0007\u0005-eA\u0001\u0004UkBdWM\r\t\u0004\u001d\u0005=\u0015bAAI\u001f\t)A)\u001a9uQ\"Q\u0011Q\u0013\u0001\t\u0002\u0003\u0006K!a \u0002\u0019}cWO\u0019*fgVdGo\u001d\u0011\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\u0006QA.\u001e2SKN,H\u000e^:\u0016\u0005\u0005\u0005\u0005BCAP\u0001!\u0015\r\u0011\"\u0003\u0002~\u0005Yql\u001a7c%\u0016\u001cX\u000f\u001c;t\u0011)\t\u0019\u000b\u0001E\u0001B\u0003&\u0011qP\u0001\r?\u001ed'MU3tk2$8\u000f\t\u0005\b\u0003O\u0003A\u0011IAN\u0003)9GN\u0019*fgVdGo\u001d\u0005\u000b\u0003W\u0003\u0001R1A\u0005\n\u00055\u0016aB0j]\u0012,g\u000e^\u000b\u0003\u0003_\u0003BA\b.\u00022B\u0019\u0011&a-\n\u0007\u0005U&F\u0001\u0004TiJLgn\u001a\u0005\u000b\u0003s\u0003\u0001\u0012!Q!\n\u0005=\u0016\u0001C0j]\u0012,g\u000e\u001e\u0011\t\u000f\u0005u\u0006\u0001\"\u0011\u0002@\u00061\u0011N\u001c3f]R,\"!!-\t\u000f\u0005\r\u0007\u0001\"\u0011\u0002F\u0006Q\u0011N\u001c3f]R|F%Z9\u0015\u0007]\t9\rC\u0004j\u0003\u0003\u0004\r!!3\u0011\t\u0005-\u0017\u0011\u001b\b\u0004\u0015\u00055\u0017bAAh\r\u00051\u0001K]3eK\u001aLA!!.\u0002T*\u0019\u0011q\u001a\u0004\t\u0013\u0005]\u0007\u0001#b\u0001\n\u0013A\u0016aE0u_N#(/\u001b8h%\u0016\u001cWO]:j_:\u001c\b\"CAn\u0001!\u0005\t\u0015)\u0003Z\u0003QyFo\\*ue&twMU3dkJ\u001c\u0018n\u001c8tA!1\u0011q\u001c\u0001\u0005B\u0011\f!\u0003^8TiJLgn\u001a*fGV\u00148/[8og\"9\u00111\u001d\u0001\u0005B\u0005\u0015\u0018A\u0006;p'R\u0014\u0018N\\4SK\u000e,(o]5p]N|F%Z9\u0015\u0007]\t9\u000f\u0003\u0004j\u0003C\u0004\r!\u0018\u0005\u000b\u0003W\u0004\u0001R1A\u0005\n\u0005%\u0014!E0u_N#(/\u001b8h'V\u0014'.Z2ug\"Q\u0011q\u001e\u0001\t\u0002\u0003\u0006K!a\u001b\u0002%}#xn\u0015;sS:<7+\u001e2kK\u000e$8\u000f\t\u0005\b\u0003g\u0004A\u0011IA<\u0003A!xn\u0015;sS:<7+\u001e2kK\u000e$8\u000fC\u0004\u0002x\u0002!\t&!?\u00029\u0011,g-\u001b8f+:$WM\u001d7zS:<wJZ*j]\u001edW\rV=qKR\u0019q#a?\t\u000f\u0011\n)\u00101\u0001\u0002~B\u0019a$a@\n\u0007\t\u0005\u0011C\u0001\u0006TS:<G.\u001a+za\u0016DqA!\u0002\u0001\t#\u00129!A\u0010eK\u001aLg.\u001a\"bg\u0016$\u0016\u0010]3TKF|emQ8na>,h\u000e\u001a+za\u0016$2a\u0006B\u0005\u0011\u001d!#1\u0001a\u0001\u0005\u0017\u00012A\bB\u0007\u0013\r\u0011y!\u0005\u0002\r\u0007>l\u0007o\\;oIRK\b/\u001a\u0005\b\u0005'\u0001A\u0011\u000bB\u000b\u0003}!WMZ5oK\n\u000b7/Z\"mCN\u001cXm](g\u0007>l\u0007o\\;oIRK\b/\u001a\u000b\u0004/\t]\u0001b\u0002\u0013\u0003\u0012\u0001\u0007!1\u0002\u0005\b\u00057\u0001A\u0011\u000bB\u000f\u0003Y!WMZ5oKB\u000b'/\u001a8ug>3G+\u001f9f%\u00164GcA\f\u0003 !9AE!\u0007A\u0002\t\u0005\u0002c\u0001\u0010\u0003$%\u0019!QE\t\u0003\u000fQK\b/\u001a*fM\"9!\u0011\u0006\u0001\u0005R\t-\u0012A\u00073fM&tWMQ1tKRK\b/Z*fc>3G+\u001f9f%\u00164GcA\f\u0003.!9AEa\nA\u0002\t\u0005\u0002B\u0004B\u0019\u0001A\u0005\u0019\u0011!A\u0005\n\tM\"QH\u0001\rgV\u0004XM\u001d\u0013v]&\fX/Z\u000b\u0005\u0005k\u0011I\u0004\u0006\u0003\u00038\tm\u0002cA'\u0003:\u00111qJa\fC\u0002ACq!\u0016B\u0018\u0001\u0004\u00119$\u0003\u0002J#!q!\u0011\t\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003D\t\u001d\u0013AI:va\u0016\u0014H\u0005Z3gS:,WK\u001c3fe2L\u0018N\\4PMNKgn\u001a7f)f\u0004X\rF\u0002\u0018\u0005\u000bBq\u0001\nB \u0001\u0004\ti0C\u0002\u0002xFAaBa\u0013\u0001!\u0003\r\t\u0011!C\u0005\u0005\u001b\u0012\t&A\u0013tkB,'\u000f\n3fM&tWMQ1tKRK\b/Z*fc>37i\\7q_VtG\rV=qKR\u0019qCa\u0014\t\u000f\u0011\u0012I\u00051\u0001\u0003\f%\u0019!QA\t\t\u001d\tU\u0003\u0001%A\u0002\u0002\u0003%IAa\u0016\u0003\\\u0005)3/\u001e9fe\u0012\"WMZ5oK\n\u000b7/Z\"mCN\u001cXm](g\u0007>l\u0007o\\;oIRK\b/\u001a\u000b\u0004/\te\u0003b\u0002\u0013\u0003T\u0001\u0007!1B\u0005\u0004\u0005'\t\u0002B\u0004B0\u0001A\u0005\u0019\u0011!A\u0005\n\t\u0005$QM\u0001\u001dgV\u0004XM\u001d\u0013eK\u001aLg.\u001a)be\u0016tGo](g)f\u0004XMU3g)\r9\"1\r\u0005\bI\tu\u0003\u0019\u0001B\u0011\u0013\r\u0011Y\"\u0005\u0005\u000f\u0005S\u0002\u0001\u0013aA\u0001\u0002\u0013%!1\u000eB8\u0003\u0001\u001aX\u000f]3sI\u0011,g-\u001b8f\u0005\u0006\u001cX\rV=qKN+\u0017o\u00144UsB,'+\u001a4\u0015\u0007]\u0011i\u0007C\u0004%\u0005O\u0002\rA!\t\n\u0007\t%\u0012\u0003\u0005\u0003\u0003t\tUT\"\u0001\u0002\n\u0007\t]$AA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007")
public interface SynchronizedTypes
extends Types {
    public void scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq(WeakHashMap var1);

    public /* synthetic */ Types.Type scala$reflect$runtime$SynchronizedTypes$$super$unique(Types.Type var1);

    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(Types.SingleType var1);

    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(Types.CompoundType var1);

    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(Types.CompoundType var1);

    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(Types.TypeRef var1);

    public /* synthetic */ void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(Types.TypeRef var1);

    @Override
    public CommonOwners.CommonOwnerMap commonOwnerMap();

    public Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock();

    public WeakHashMap<Types.Type, java.lang.ref.WeakReference<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$uniques();

    @Override
    public <T extends Types.Type> T unique(T var1);

    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel();

    @Override
    public int skolemizationLevel();

    @Override
    public void skolemizationLevel_$eq(int var1);

    public ThreadLocalStorage.ThreadLocalStorage<TypeConstraints.UndoLog> scala$reflect$runtime$SynchronizedTypes$$_undoLog();

    @Override
    public TypeConstraints.UndoLog undoLog();

    public ThreadLocalStorage.ThreadLocalStorage<WeakHashMap<List<Types.Type>, WeakReference<Types.Type>>> scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness();

    @Override
    public WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> intersectionWitness();

    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions();

    @Override
    public int subsametypeRecursions();

    @Override
    public void subsametypeRecursions_$eq(int var1);

    public ThreadLocalStorage.ThreadLocalStorage<HashSet<TypeComparers.SubTypePair>> scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes();

    @Override
    public HashSet<TypeComparers.SubTypePair> pendingSubTypes();

    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions();

    @Override
    public int basetypeRecursions();

    @Override
    public void basetypeRecursions_$eq(int var1);

    public ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes();

    @Override
    public HashSet<Types.Type> pendingBaseTypes();

    public ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_lubResults();

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> lubResults();

    public ThreadLocalStorage.ThreadLocalStorage<HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_glbResults();

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> glbResults();

    public ThreadLocalStorage.ThreadLocalStorage<String> scala$reflect$runtime$SynchronizedTypes$$_indent();

    @Override
    public String indent();

    @Override
    public void indent_$eq(String var1);

    public ThreadLocalStorage.ThreadLocalStorage<Object> scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions();

    @Override
    public int toStringRecursions();

    @Override
    public void toStringRecursions_$eq(int var1);

    public ThreadLocalStorage.ThreadLocalStorage<HashSet<Types.Type>> scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects();

    @Override
    public HashSet<Types.Type> toStringSubjects();

    @Override
    public void defineUnderlyingOfSingleType(Types.SingleType var1);

    @Override
    public void defineBaseTypeSeqOfCompoundType(Types.CompoundType var1);

    @Override
    public void defineBaseClassesOfCompoundType(Types.CompoundType var1);

    @Override
    public void defineParentsOfTypeRef(Types.TypeRef var1);

    @Override
    public void defineBaseTypeSeqOfTypeRef(Types.TypeRef var1);
}

