/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.Function0;
import scala.Function1;
import scala.Function1$class;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$UnmappableTree$;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.tpe.TypeMaps$AnnotationFilter$class;
import scala.reflect.internal.tpe.TypeMaps$ApproximateDependentMap$;
import scala.reflect.internal.tpe.TypeMaps$AsSeenFromMap$;
import scala.reflect.internal.tpe.TypeMaps$AsSeenFromMap$annotationArgRewriter$;
import scala.reflect.internal.tpe.TypeMaps$ErroneousCollector$;
import scala.reflect.internal.tpe.TypeMaps$InstantiateDependentMap$StabilizedArgTp$;
import scala.reflect.internal.tpe.TypeMaps$InstantiateDependentMap$StableArgTp$;
import scala.reflect.internal.tpe.TypeMaps$InstantiateDependentMap$UnstableArgTp$;
import scala.reflect.internal.tpe.TypeMaps$InstantiateDependentMap$treeTrans$2$;
import scala.reflect.internal.tpe.TypeMaps$IsDependentCollector$;
import scala.reflect.internal.tpe.TypeMaps$KeepOnlyTypeConstraints$class;
import scala.reflect.internal.tpe.TypeMaps$SubstSymMap$mapTreeSymbols$;
import scala.reflect.internal.tpe.TypeMaps$SubstTypeMap$trans$2$;
import scala.reflect.internal.tpe.TypeMaps$TypeMap$;
import scala.reflect.internal.tpe.TypeMaps$abstractTypesToBounds$;
import scala.reflect.internal.tpe.TypeMaps$adaptToNewRunMap$;
import scala.reflect.internal.tpe.TypeMaps$dropIllegalStarTypes$;
import scala.reflect.internal.tpe.TypeMaps$dropSingletonType$;
import scala.reflect.internal.tpe.TypeMaps$normalizeAliases$;
import scala.reflect.internal.tpe.TypeMaps$typeVarToOriginMap$;
import scala.reflect.internal.tpe.TypeMaps$wildcardExtrapolation$;
import scala.reflect.internal.tpe.TypeMaps$wildcardToTypeVarMap$;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing$;
import scala.runtime.VolatileObjectRef;

@ScalaSignature(bytes="\u0006\u0001\u001d}cAC\u0001\u0003!\u0003\r\t\u0001\u0002\u0006\bZ\tAA+\u001f9f\u001b\u0006\u00048O\u0003\u0002\u0004\t\u0005\u0019A\u000f]3\u000b\u0005\u00151\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mCN\u0011\u0001a\u0003\t\u0003\u00195i\u0011\u0001C\u0005\u0003\u001d!\u0011a!\u00118z%\u00164\u0007\"\u0002\t\u0001\t\u0003\u0011\u0012A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003M\u0001\"\u0001\u0004\u000b\n\u0005UA!\u0001B+oSR<Qa\u0006\u0001\t\u0002a\t\u0001C\\8s[\u0006d\u0017N_3BY&\f7/Z:\u0011\u0005eQR\"\u0001\u0001\u0007\u000bm\u0001\u0001\u0012\u0001\u000f\u0003!9|'/\\1mSj,\u0017\t\\5bg\u0016\u001c8C\u0001\u000e\u001e!\tIbDB\u0003 \u0001\u0005\u0005\u0001EA\u0004UsB,W*\u00199\u0014\u0007yY\u0011\u0005\u0005\u0003\rE\u0011\"\u0013BA\u0012\t\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u001aK%\u0011ae\n\u0002\u0005)f\u0004X-\u0003\u0002)\t\t)A+\u001f9fg\"a!F\bC\u0001\u0002\u000b\u0015\t\u0011)A\u0005W\u0005Q4oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000e\n;qK\u0012\"\u0016\u0010]3NCB\u001cH\u0005V=qK6\u000b\u0007\u000f\n\u0013ue\u0006\u001c7NV1sS\u0006t7-\u001a\t\u0003\u00191J!!\f\u0005\u0003\u000f\t{w\u000e\\3b]\")qF\bC\u0001a\u00051A(\u001b8jiz\"\"!H\u0019\t\u000bIr\u0003\u0019A\u0016\u0002\u001bQ\u0014\u0018mY6WCJL\u0017M\\2f\u0011\u0015yc\u0004\"\u00015)\u0005i\u0002\"\u0002\u001c\u001f\r\u00039\u0014!B1qa2LHC\u0001\u00139\u0011\u0015IT\u00071\u0001%\u0003\t!\b\u000f\u0003\u0004<=\u0001\u0006K\u0001P\u0001\n?Z\f'/[1oG\u0016\u0004\"!\u0010 \u000e\u0003\u0011I!a\u0010\u0003\u0003\u0011Y\u000b'/[1oG\u0016DQ!\u0011\u0010\u0005\u0002\t\u000bAB^1sS\u0006t7-Z0%KF$\"aE\"\t\u000b\u0011\u0003\u0005\u0019\u0001\u001f\u0002\u0003aDQA\u0012\u0010\u0005\u0002\u001d\u000b\u0001B^1sS\u0006t7-Z\u000b\u0002y!)\u0011J\bC\u0001\u0015\u00069Q.\u00199Pm\u0016\u0014HC\u0001\u0013L\u0011\u0015I\u0004\n1\u0001%\u0011\u0015ie\u0004\"\u0001O\u000319\u0018\u000e\u001e5WCJL\u0017M\\2f+\ty5\u000b\u0006\u0002QCR\u0011\u0011\u000b\u0018\t\u0003%Nc\u0001\u0001B\u0003U\u0019\n\u0007QKA\u0001U#\t1\u0016\f\u0005\u0002\r/&\u0011\u0001\f\u0003\u0002\b\u001d>$\b.\u001b8h!\ta!,\u0003\u0002\\\u0011\t\u0019\u0011I\\=\t\rucE\u00111\u0001_\u0003\u0011\u0011w\u000eZ=\u0011\u00071y\u0016+\u0003\u0002a\u0011\tAAHY=oC6,g\bC\u0003c\u0019\u0002\u0007A(A\u0001w\u0011\u0015!g\u0004\"\u0002f\u0003\u001d1G.\u001b9qK\u0012,\"A\u001a5\u0015\u0005\u001dL\u0007C\u0001*i\t\u0015!6M1\u0001V\u0011\u0019i6\r\"a\u0001UB\u0019AbX4)\u0005\rd\u0007C\u0001\u0007n\u0013\tq\u0007B\u0001\u0004j]2Lg.\u001a\u0005\u0006az!\t\"]\u0001\f[\u0006\u0004xJ^3s\u0003J<7\u000fF\u0002ssn\u00042a\u001d<%\u001d\taA/\u0003\u0002v\u0011\u00059\u0001/Y2lC\u001e,\u0017BA<y\u0005\u0011a\u0015n\u001d;\u000b\u0005UD\u0001\"\u0002>p\u0001\u0004\u0011\u0018\u0001B1sONDQ\u0001`8A\u0002u\fq\u0001\u001e9be\u0006l7\u000fE\u0002tmz\u0004\"!G@\n\t\u0005\u0005\u00111\u0001\u0002\u0007'fl'm\u001c7\n\u0007\u0005\u0015AAA\u0004Ts6\u0014w\u000e\\:\t\u000f\u0005%a\u0004\"\u0003\u0002\f\u0005\t\u0012\r\u001d9msR{7+_7c_2LeNZ8\u0015\u0007\u0011\ni\u0001C\u0004\u0002\u0010\u0005\u001d\u0001\u0019\u0001@\u0002\u0007MLX\u000eC\u0004\u0002\u0014y!\t\"!\u0006\u0002#9|7\t[1oO\u0016$vnU=nE>d7\u000fF\u0002,\u0003/Aq!!\u0007\u0002\u0012\u0001\u0007Q0\u0001\u0005pe&<7+_7t\u0011\u0019Ie\u0004\"\u0001\u0002\u001eQ!\u0011qDA\u0015!\rI\u0012\u0011E\u0005\u0005\u0003G\t)CA\u0003TG>\u0004X-C\u0002\u0002(\u0011\u0011aaU2pa\u0016\u001c\b\u0002CA\u0016\u00037\u0001\r!a\b\u0002\u000bM\u001cw\u000e]3\t\r%sB\u0011AA\u0018)\ri\u0018\u0011\u0007\u0005\b\u00033\ti\u00031\u0001~\u0011\u0019Ie\u0004\"\u0001\u00026Q!\u0011qGA!!\rI\u0012\u0011H\u0005\u0005\u0003w\tiD\u0001\bB]:|G/\u0019;j_:LeNZ8\n\u0007\u0005}BAA\bB]:|G/\u0019;j_:LeNZ8t\u0011!\t\u0019%a\rA\u0002\u0005]\u0012!B1o]>$\bbBA$=\u0011\u0005\u0011\u0011J\u0001\u0013[\u0006\u0004xJ^3s\u0003:tw\u000e^1uS>t7\u000f\u0006\u0003\u0002L\u00055\u0003\u0003B:w\u0003oA\u0001\"a\u0014\u0002F\u0001\u0007\u00111J\u0001\u0007C:tw\u000e^:\t\u000f\u0005Mc\u0004\"\u0001\u0002V\u0005\u0001R.\u00199Pm\u0016\u0014\u0018I\u001c8pi\u0006\u0013xm\u001d\u000b\u0005\u0003/\n\u0019\u0007\u0005\u0003tm\u0006e\u0003cA\r\u0002\\%!\u0011QLA0\u0005\u0011!&/Z3\n\u0007\u0005\u0005DAA\u0003Ue\u0016,7\u000fC\u0004{\u0003#\u0002\r!a\u0016\t\r%sB\u0011AA4)\u0011\tI&!\u001b\t\u0011\u0005-\u0014Q\ra\u0001\u00033\nA\u0001\u001e:fK\"1\u0011J\bC\u0001\u0003_\"b!!\u0017\u0002r\u0005M\u0004\u0002CA6\u0003[\u0002\r!!\u0017\t\u0011\u0005U\u0014Q\u000ea\u0001\u0003o\naaZ5wKV\u0004\b\u0003\u0002\u0007\u0002zYK1!a\u001f\t\u0005%1UO\\2uS>t\u0007G\u0002\u0004\u0002\u0000y\u0001\u0011\u0011\u0011\u0002\u0013)f\u0004X-T1q)J\fgn\u001d4pe6,'o\u0005\u0003\u0002~\u0005\r\u0005cA\r\u0002\u0006&!\u0011qQAE\u0005-!&/\u00198tM>\u0014X.\u001a:\n\t\u0005\u0005\u00141\u0012\u0006\u0004\u0003\u001b3\u0011aA1qS\"9q&! \u0005\u0002\u0005EECAAJ!\u0011\t)*! \u000e\u0003yA\u0001\"!'\u0002~\u0011\u0005\u00131T\u0001\niJ\fgn\u001d4pe6$B!!\u0017\u0002\u001e\"A\u00111NAL\u0001\u0004\tI\u0006\u0003\u000405\u0011\u0005\u0011\u0011\u0015\u000b\u00021!1aG\u0007C\u0001\u0003K#2\u0001JAT\u0011\u0019I\u00141\u0015a\u0001I\u001d9\u00111\u0016\u0001\t\u0002\u00055\u0016!\u00053s_B\u001c\u0016N\\4mKR|g\u000eV=qKB\u0019\u0011$a,\u0007\u000f\u0005E\u0006\u0001#\u0001\u00024\n\tBM]8q'&tw\r\\3u_:$\u0016\u0010]3\u0014\u0007\u0005=V\u0004C\u00040\u0003_#\t!a.\u0015\u0005\u00055\u0006b\u0002\u001c\u00020\u0012\u0005\u00111\u0018\u000b\u0004I\u0005u\u0006BB\u001d\u0002:\u0002\u0007AeB\u0004\u0002B\u0002A\t!a1\u0002+\u0005\u00147\u000f\u001e:bGR$\u0016\u0010]3t)>\u0014u.\u001e8egB\u0019\u0011$!2\u0007\u000f\u0005\u001d\u0007\u0001#\u0001\u0002J\n)\u0012MY:ue\u0006\u001cG\u000fV=qKN$vNQ8v]\u0012\u001c8cAAc;!9q&!2\u0005\u0002\u00055GCAAb\u0011\u001d1\u0014Q\u0019C\u0001\u0003#$2\u0001JAj\u0011\u0019I\u0014q\u001aa\u0001I!9\u0011q\u001b\u0001\u0005\u0012\u0005e\u0017AE3uC\u0016C\b/\u00198e\u0017\u0016,\u0007o]*uCJ,\u0012aK\u0004\b\u0003;\u0004\u0001\u0012AAp\u0003Q!'o\u001c9JY2,w-\u00197Ti\u0006\u0014H+\u001f9fgB\u0019\u0011$!9\u0007\u000f\u0005\r\b\u0001#\u0001\u0002f\n!BM]8q\u00132dWmZ1m'R\f'\u000fV=qKN\u001c2!!9\u001e\u0011\u001dy\u0013\u0011\u001dC\u0001\u0003S$\"!a8\t\u000fY\n\t\u000f\"\u0001\u0002nR\u0019A%a<\t\re\nY\u000f1\u0001%\r%\t\u0019\u0010\u0001I\u0001\u0004\u0003\t)P\u0001\tB]:|G/\u0019;j_:4\u0015\u000e\u001c;feN\u0019\u0011\u0011_\u000f\t\rA\t\t\u0010\"\u0001\u0013\u0011!\tY0!=\u0007\u0002\u0005u\u0018AD6fKB\feN\\8uCRLwN\u001c\u000b\u0004W\u0005}\b\u0002CA\"\u0003s\u0004\r!a\u000e\t\u000f%\u000b\t\u0010\"\u0011\u0003\u0004Q!\u0011q\u0007B\u0003\u0011!\t\u0019E!\u0001A\u0002\u0005]\u0002b\u0004B\u0005\u0003c\u0004\n1!A\u0001\n\u0013\u0011Y!a\r\u0002\u001bM,\b/\u001a:%[\u0006\u0004xJ^3s)\u0011\t9D!\u0004\t\u0011\u0005\r#q\u0001a\u0001\u0003o1\u0011B!\u0005\u0001!\u0003\r\tAa\u0005\u0003/-+W\r](oYf$\u0016\u0010]3D_:\u001cHO]1j]R\u001c8#\u0002B\b;\tU\u0001cA\r\u0002r\"1\u0001Ca\u0004\u0005\u0002IA\u0001\"a?\u0003\u0010\u0011\u0005!1\u0004\u000b\u0004W\tu\u0001\u0002CA\"\u00053\u0001\r!a\u000e\u0007\u000f\t\u0005\u0002!!\u0001\u0003$\tiA+\u001f9f)J\fg/\u001a:tKJ\u001c2Aa\b\u001e\u0011\u001dy#q\u0004C\u0001\u0005O!\"A!\u000b\u0011\u0007e\u0011y\u0002\u0003\u0005\u0003.\t}a\u0011\u0001B\u0018\u0003!!(/\u0019<feN,GcA\n\u00032!1\u0011Ha\u000bA\u0002\u0011BqA\u000eB\u0010\t\u0003\u0011)\u0004F\u0002%\u0005oAa!\u000fB\u001a\u0001\u0004!ca\u0002B\u001e\u0001\u0005\u0005!Q\b\u0002\u0018)f\u0004X\r\u0016:bm\u0016\u00148/\u001a:XSRD'+Z:vYR,BAa\u0010\u0003JM!!\u0011\bB\u0015\u0011\u001dy#\u0011\bC\u0001\u0005\u0007\"\"A!\u0012\u0011\u000be\u0011IDa\u0012\u0011\u0007I\u0013I\u0005\u0002\u0004U\u0005s\u0011\r!\u0016\u0005\t\u0005\u001b\u0012ID\"\u0001\u0003P\u00051!/Z:vYR,\"Aa\u0012\t\u000f\tM#\u0011\bD\u0001%\u0005)1\r\\3be\u001a9!q\u000b\u0001\u0002\u0002\te#!\u0004+za\u0016\u001cu\u000e\u001c7fGR|'/\u0006\u0003\u0003\\\t\r4\u0003\u0002B+\u0005SA1Ba\u0018\u0003V\t\u0005\t\u0015!\u0003\u0003b\u00059\u0011N\\5uS\u0006d\u0007c\u0001*\u0003d\u00111AK!\u0016C\u0002UCqa\fB+\t\u0003\u00119\u0007\u0006\u0003\u0003j\t-\u0004#B\r\u0003V\t\u0005\u0004\u0002\u0003B0\u0005K\u0002\rA!\u0019\t\u0019\t5#Q\u000ba\u0001\u0002\u0004%\tAa\u001c\u0016\u0005\t\u0005\u0004\u0002\u0004B:\u0005+\u0002\r\u00111A\u0005\u0002\tU\u0014A\u0003:fgVdGo\u0018\u0013fcR\u00191Ca\u001e\t\u0015\te$\u0011OA\u0001\u0002\u0004\u0011\t'A\u0002yIEB\u0011B! \u0003V\u0001\u0006KA!\u0019\u0002\u000fI,7/\u001e7uA!A!\u0011\u0011B+\t\u0003\u0011\u0019)A\u0004d_2dWm\u0019;\u0015\t\t\u0005$Q\u0011\u0005\u0007s\t}\u0004\u0019\u0001\u0013\t\u000f\t%\u0005\u0001\"\u0001\u0003\f\u0006\u0001\"/Y<U_\u0016C\u0018n\u001d;f]RL\u0017\r\\\u000b\u0002;\u00191!q\u0012\u0001\u0001\u0005#\u0013\u0001$\u0012=jgR,g\u000e^5bY\u0016CHO]1q_2\fG/[8o'\r\u0011i)\b\u0005\ny\n5%\u0011!Q\u0001\nuDqa\fBG\t\u0003\u00119\n\u0006\u0003\u0003\u001a\nm\u0005cA\r\u0003\u000e\"1AP!&A\u0002uD!Ba(\u0003\u000e\n\u0007I\u0011\u0002BQ\u0003)y7mY;s\u0007>,h\u000e^\u000b\u0003\u0005G\u0003rA!*\u00030z\u0014\u0019,\u0004\u0002\u0003(*!!\u0011\u0016BV\u0003\u001diW\u000f^1cY\u0016T1A!,\t\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005c\u00139KA\u0004ICNDW*\u00199\u0011\u00071\u0011),C\u0002\u00038\"\u00111!\u00138u\u0011%\u0011YL!$!\u0002\u0013\u0011\u0019+A\u0006pG\u000e,(oQ8v]R\u0004\u0003\u0002\u0003B`\u0005\u001b#IA!1\u0002\u0013\r|WO\u001c;PG\u000e\u001cHcA\n\u0003D\"1\u0011H!0A\u0002\u0011B\u0001Ba2\u0003\u000e\u0012\u0005!\u0011Z\u0001\fKb$(/\u00199pY\u0006$X\rF\u0002%\u0005\u0017Daa\u0001Bc\u0001\u0004!\u0003b\u0002\u001c\u0003\u000e\u0012\u0005!q\u001a\u000b\u0004I\tE\u0007BB\u001d\u0003N\u0002\u0007A\u0005C\u0004J\u0005\u001b#\tE!6\u0015\u0007\u0011\u00129\u000e\u0003\u0004:\u0005'\u0004\r\u0001\n\u0005\b\u0013\n5E\u0011\tBn)\u0011\tIF!8\t\u0011\u0005-$\u0011\u001ca\u0001\u00033:qA!9\u0001\u0011\u0003\u0011\u0019/A\u000bxS2$7-\u0019:e\u000bb$(/\u00199pY\u0006$\u0018n\u001c8\u0011\u0007e\u0011)OB\u0004\u0003h\u0002A\tA!;\u0003+]LG\u000eZ2be\u0012,\u0005\u0010\u001e:ba>d\u0017\r^5p]N\u0019!Q]\u000f\t\u000f=\u0012)\u000f\"\u0001\u0003nR\u0011!1\u001d\u0005\bm\t\u0015H\u0011\u0001By)\r!#1\u001f\u0005\u0007s\t=\b\u0019\u0001\u0013\t\u000f\t]\b\u0001\"\u0001\u0003z\u0006\u0001\u0012n\u001d)pgNL'\r\\3Qe\u00164\u0017\u000e\u001f\u000b\u0004W\tm\bb\u0002B\u007f\u0005k\u0004\rA`\u0001\u0006G2\f'P\u001f\u0005\t\u0007\u0003\u0001A\u0011\u0003\u0003\u0004\u0004\u0005a1o[5q!J,g-\u001b=PMR)1f!\u0002\u0004\n!91q\u0001B\u0000\u0001\u0004!\u0013a\u00019sK\"9!Q B\u0000\u0001\u0004q\bbBB\u0007\u0001\u0011\u00051qB\u0001\u0011]\u0016<\u0018i]*fK:4%o\\7NCB$ba!\u0005\u0005\u0018\u0011e\u0001cA\r\u0004\u0014\u001911Q\u0003\u0001\u0001\u0007/\u0011Q\"Q:TK\u0016tgI]8n\u001b\u0006\u00048#BB\n;\re\u0001cA\r\u0003\u0010!Q1QDB\n\u0005\u0003\u0005\u000b\u0011\u0002\u0013\u0002\u001dM,WM\u001c$s_6\u0004&/\u001a4jq\"Q1\u0011EB\n\u0005\u0003\u0005\u000b\u0011\u0002@\u0002\u001bM,WM\u001c$s_6\u001cE.Y:t\u0011\u001dy31\u0003C\u0001\u0007K!ba!\u0005\u0004(\r%\u0002bBB\u000f\u0007G\u0001\r\u0001\n\u0005\b\u0007C\u0019\u0019\u00031\u0001\u007f\u0011!\u0019ica\u0005\u0005\u0002\r=\u0012AD2baR,(/\u001a3QCJ\fWn]\u000b\u0002{\"A11GB\n\t\u0003\u0019y#A\bdCB$XO]3e'.|G.Z7t\u0011\u001d141\u0003C\u0001\u0007o!2\u0001JB\u001d\u0011\u0019I4Q\u0007a\u0001I!Q1QHB\n\u0001\u0004%Iaa\f\u0002!}\u001b\u0017\r\u001d;ve\u0016$7k[8mK6\u001c\bBCB!\u0007'\u0001\r\u0011\"\u0003\u0004D\u0005!rlY1qiV\u0014X\rZ*l_2,Wn]0%KF$2aEB#\u0011%\u0011Iha\u0010\u0002\u0002\u0003\u0007Q\u0010\u0003\u0005\u0004J\rM\u0001\u0015)\u0003~\u0003Ey6-\u00199ukJ,GmU6pY\u0016l7\u000f\t\u0005\u000b\u0007\u001b\u001a\u0019\u00021A\u0005\n\r=\u0012aD0dCB$XO]3e!\u0006\u0014\u0018-\\:\t\u0015\rE31\u0003a\u0001\n\u0013\u0019\u0019&A\n`G\u0006\u0004H/\u001e:fIB\u000b'/Y7t?\u0012*\u0017\u000fF\u0002\u0014\u0007+B\u0011B!\u001f\u0004P\u0005\u0005\t\u0019A?\t\u0011\re31\u0003Q!\nu\f\u0001cX2baR,(/\u001a3QCJ\fWn\u001d\u0011\t\u0015\ru31\u0003b\u0001\n\u0013\tI.\u0001\bjgN#\u0018M\u00197f!J,g-\u001b=\t\u0011\r\u000541\u0003Q\u0001\n-\nq\"[:Ti\u0006\u0014G.\u001a)sK\u001aL\u0007\u0010\t\u0005\t\u0007K\u001a\u0019\u0002\"\u0003\u0004h\u0005Y\u0012n\u001d\"bg\u0016\u001cE.Y:t\u001f\u001a,en\u00197pg&twm\u00117bgN$2aKB5\u0011\u001d\u0019Yga\u0019A\u0002y\fAAY1tK\"A1qNB\n\t\u0013\u0019\t(A\u000ejgRK\b/\u001a)be\u0006lwJZ#oG2|7/\u001b8h\u00072\f7o\u001d\u000b\u0004W\rM\u0004bBA\b\u0007[\u0002\rA \u0005\t\u0007o\u001a\u0019\u0002\"\u0005\u0004z\u0005Y1-\u00199ukJ,G\u000b[5t)\u0015!31PB?\u0011\u001d\u00199a!\u001eA\u0002\u0011BqA!@\u0004v\u0001\u0007a\u0010\u0003\u0005\u0004\u0002\u000eMA\u0011CBB\u00039\u0019\u0017\r\u001d;ve\u0016\u001c6n\u001c7f[N$2aEBC\u0011\u001d\u00199ia A\u0002u\fqa]6pY\u0016l7\u000f\u0003\u0005\u0004\f\u000eMA\u0011BBG\u0003e\u0019wN\u001d:fgB|g\u000eZ5oORK\b/Z!sOVlWM\u001c;\u0015\u000b\u0011\u001ayia%\t\u000f\rE5\u0011\u0012a\u0001I\u0005\u0019A\u000e[:\t\u000f\rU5\u0011\u0012a\u0001I\u0005\u0019!\u000f[:\t\u0011\re51\u0003C\u0005\u00077\u000bAc\u00197bgN\u0004\u0016M]1nKR,'/Q:TK\u0016tGc\u0001\u0013\u0004\u001e\"91qTBL\u0001\u0004!\u0013AC2mCN\u001c\b+\u0019:b[\"A11UB\n\t\u0013\u0019)+A\u000bnCR\u001c\u0007.Z:Qe\u00164\u0017\u000e_!oI\u000ec\u0017m]:\u0015\r\r\u001d6QVBX)\rY3\u0011\u0016\u0005\b\u0007W\u001b\t\u000b1\u0001\u007f\u0003%\u0019\u0017M\u001c3jI\u0006$X\rC\u0004\u0004\b\r\u0005\u0006\u0019\u0001\u0013\t\u000f\tu8\u0011\u0015a\u0001}\"A11WB\nA\u0003&1&A\bxe>$X-\u00118o_R\fG/[8o\u000f!\u00199la\u0005\t\n\re\u0016!F1o]>$\u0018\r^5p]\u0006\u0013xMU3xe&$XM\u001d\t\u0005\u0007w\u001bi,\u0004\u0002\u0004\u0014\u0019A1qXB\n\u0011\u0013\u0019\tMA\u000bb]:|G/\u0019;j_:\f%o\u001a*foJLG/\u001a:\u0014\t\ru61\u0019\t\u0005\u0007w\u000bi\bC\u00040\u0007{#\taa2\u0015\u0005\re\u0006\u0002CBf\u0007{#Ia!4\u0002\u00175\fGo\u00195fgRC\u0017n\u001d\u000b\u0004W\r=\u0007bBBi\u0007\u0013\u0004\rA`\u0001\u0005i\"L'\u0010\u0003\u0005\u0004V\u000euF\u0011BBl\u0003\u001dqWm\u001e+iSN$\"!!\u0017\t\u0011\u0005e5Q\u0018C!\u00077$B!!\u0017\u0004^\"A\u00111NBm\u0001\u0004\tI\u0006C\u0004J\u0007'!\te!9\u0015\r\u0005e31]Bs\u0011!\tYga8A\u0002\u0005e\u0003\u0002CA;\u0007?\u0004\r!a\u001e\t\u0011\r%81\u0003C\u0005\u0007W\fa\u0002\u001e5jgRK\b/Z!t'\u0016,g\u000eF\u0002%\u0007[Dq!OBt\u0001\u0004\u0019y\u000fE\u0002\u001a\u0007cL1aa=(\u0005!!\u0006.[:UsB,\u0007\u0002CB|\u0007'!Ia!?\u0002!MLgn\u001a7f)f\u0004X-Q:TK\u0016tGc\u0001\u0013\u0004|\"9\u0011h!>A\u0002\ru\bcA\r\u0004\u0000&\u0019A\u0011A\u0014\u0003\u0015MKgn\u001a7f)f\u0004X\r\u0003\u0005\u0005\u0006\rMA\u0011\tC\u0004\u0003!!xn\u0015;sS:<GC\u0001C\u0005!\u0011!Y\u0001\"\u0005\u000f\u00071!i!C\u0002\u0005\u0010!\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002C\n\t+\u0011aa\u0015;sS:<'b\u0001C\b\u0011!91qAB\u0006\u0001\u0004!\u0003b\u0002B\u007f\u0007\u0017\u0001\rA \u0004\b\t;\u0001\u0011\u0011\u0001C\u0010\u0005!\u0019VOY:u\u001b\u0006\u0004X\u0003\u0002C\u0011\t_\u00192\u0001b\u0007\u001e\u0011)!)\u0003b\u0007\u0003\u0002\u0003\u0006I!`\u0001\u0005MJ|W\u000eC\u0006\u0005*\u0011m!\u0011!Q\u0001\n\u0011-\u0012A\u0001;p!\u0011\u0019h\u000f\"\f\u0011\u0007I#y\u0003\u0002\u0004U\t7\u0011\r!\u0016\u0005\b_\u0011mA\u0011\u0001C\u001a)\u0019!)\u0004b\u000e\u0005:A)\u0011\u0004b\u0007\u0005.!9AQ\u0005C\u0019\u0001\u0004i\b\u0002\u0003C\u0015\tc\u0001\r\u0001b\u000b\t\u0011\u0011uB1\u0004C\t\t\u007f\tq!\\1uG\",7\u000fF\u0003,\t\u0003\"\u0019\u0005C\u0004\u0002\u0010\u0011m\u0002\u0019\u0001@\t\u000f\u0011\u0015C1\ba\u0001}\u0006!1/_72\u0011!!I\u0005b\u0007\u0007\u0012\u0011-\u0013A\u0002;p)f\u0004X\rF\u0003%\t\u001b\"\t\u0006C\u0004\u0005P\u0011\u001d\u0003\u0019\u0001\u0013\u0002\r\u0019\u0014x.\u001c;q\u0011\u001dIDq\ta\u0001\t[A\u0001\u0002\"\u0016\u0005\u001c\u0011EAqK\u0001\u0010e\u0016t\u0017-\\3C_VtGmU=ngR\u0019A\u0005\"\u0017\t\re\"\u0019\u00061\u0001%\u0011!!i\u0006b\u0007\u0005\n\u0011}\u0013!B:vEN$H#\u0003\u0013\u0005b\u0011\rDQ\rC4\u0011\u0019ID1\fa\u0001I!9\u0011q\u0002C.\u0001\u0004q\bb\u0002C\u0013\t7\u0002\r! \u0005\t\tS!Y\u00061\u0001\u0005,!\"A1\fC6!\u0011!i\u0007b\u001d\u000e\u0005\u0011=$b\u0001C9\u0011\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0011UDq\u000e\u0002\bi\u0006LGN]3d\u0011\u001d1D1\u0004C\u0001\ts\"2\u0001\nC>\u0011\u001d!i\bb\u001eA\u0002\u0011\n1\u0001\u001e91\r\u0019!\t\t\u0001\u0001\u0005\u0004\nY1+\u001e2tiNKX.T1q'\u0011!y\b\"\"\u0011\te!YB \u0005\u000b\tK!yH!A!\u0002\u0013i\bB\u0003C\u0015\t\u007f\u0012\t\u0011)A\u0005{\"9q\u0006b \u0005\u0002\u00115EC\u0002CH\t##\u0019\nE\u0002\u001a\t\u007fBq\u0001\"\n\u0005\f\u0002\u0007Q\u0010C\u0004\u0005*\u0011-\u0005\u0019A?\t\u000f=\"y\b\"\u0001\u0005\u0018R!Aq\u0012CM\u0011!!Y\n\"&A\u0002\u0011u\u0015!\u00029bSJ\u001c\b#\u0002\u0007\u0005 \u0012\r\u0016b\u0001CQ\u0011\tQAH]3qK\u0006$X\r\u001a \u0011\u000b1!)K @\n\u0007\u0011\u001d\u0006B\u0001\u0004UkBdWM\r\u0005\t\t\u0013\"y\b\"\u0005\u0005,R)A\u0005\",\u00050\"9Aq\nCU\u0001\u0004!\u0003bBA\b\tS\u0003\rA \u0005\t\t;\"y\b\"\u0003\u00054R9a\u0010\".\u00058\u0012e\u0006bBA\b\tc\u0003\rA \u0005\b\tK!\t\f1\u0001~\u0011\u001d!I\u0003\"-A\u0002uDC\u0001\"-\u0005l!AAq\u0018C@\t\u0013!\t-\u0001\u0005tk\n\u001cHOR8s)\rqH1\u0019\u0005\b\u0003\u001f!i\f1\u0001\u007f\u0011\u001d1Dq\u0010C!\t\u000f$2\u0001\nCe\u0011\u0019IDQ\u0019a\u0001I\u001dAAQ\u001aC@\u0011\u0003!y-\u0001\bnCB$&/Z3Ts6\u0014w\u000e\\:\u0011\t\u0011EG1[\u0007\u0003\t\u007f2\u0001\u0002\"6\u0005\u0000!\u0005Aq\u001b\u0002\u000f[\u0006\u0004HK]3f'fl'm\u001c7t'\u0011!\u0019\u000e\"7\u0011\t\u0011E\u0017Q\u0010\u0005\b_\u0011MG\u0011\u0001Co)\t!y\r\u0003\u0006\u0005b\u0012M'\u0019!C\u0001\tG\f!b\u001d;sS\u000e$8i\u001c9z+\t!)\u000fE\u0002\u001a\tOLA\u0001\";\u0002`\tQAK]3f\u0007>\u0004\u0018.\u001a:\t\u0013\u00115H1\u001bQ\u0001\n\u0011\u0015\u0018aC:ue&\u001cGoQ8qs\u0002B\u0001\u0002\"=\u0005T\u0012\u0005A1_\u0001\u000bi\u0016\u0014X.T1qgR{G\u0003\u0002C{\tw\u0004B\u0001\u0004C|}&\u0019A\u0011 \u0005\u0003\r=\u0003H/[8o\u0011\u001d\ty\u0001b<A\u0002yD\u0001\u0002b@\u0005T\u0012\u0005Q\u0011A\u0001\u0012iJ\fgn\u001d4pe6Le-T1qa\u0016$G\u0003BC\u0002\u000b\u0017!B!!\u0017\u0006\u0006!AQq\u0001C\u007f\u0001\u0004)I!A\u0003ue\u0006t7\u000fE\u0003\rEy\fI\u0006\u0003\u0005\u0002l\u0011u\b\u0019AA-\u0011!\tI\nb5\u0005B\u0015=A\u0003BA-\u000b#A\u0001\"a\u001b\u0006\u000e\u0001\u0007\u0011\u0011\f\u0005\b\u0013\u0012}D\u0011IC\u000b)\u0019\tI&b\u0006\u0006\u001a!A\u00111NC\n\u0001\u0004\tI\u0006\u0003\u0005\u0002v\u0015M\u0001\u0019AA<\r\u0019)i\u0002\u0001\u0001\u0006 \ta1+\u001e2tiRK\b/Z'baN!Q1DC\u0011!\u0011IB1\u0004\u0013\t\u0017\u0011\u0015R1\u0004BC\u0002\u0013\u00051q\u0006\u0005\u000b\u000bO)YB!A!\u0002\u0013i\u0018!\u00024s_6\u0004\u0003b\u0003C\u0015\u000b7\u0011)\u0019!C\u0001\u000bW)\u0012A\u001d\u0005\u000b\u000b_)YB!A!\u0002\u0013\u0011\u0018a\u0001;pA!9q&b\u0007\u0005\u0002\u0015MBCBC\u001b\u000bo)I\u0004E\u0002\u001a\u000b7Aq\u0001\"\n\u00062\u0001\u0007Q\u0010C\u0004\u0005*\u0015E\u0002\u0019\u0001:\t\u0011\u0011%S1\u0004C\t\u000b{!R\u0001JC \u000b\u0003Bq\u0001b\u0014\u0006<\u0001\u0007A\u0005\u0003\u0004:\u000bw\u0001\r\u0001\n\u0005\b\u0013\u0016mA\u0011IC#)\u0019\tI&b\u0012\u0006J!A\u00111NC\"\u0001\u0004\tI\u0006\u0003\u0005\u0002v\u0015\r\u0003\u0019AA<\r\u0019)i\u0005\u0001\u0001\u0006P\ta1+\u001e2tiRC\u0017n]'baN\u0019Q1J\u000f\t\u0015\u0011\u0015R1\nB\u0001B\u0003%a\u0010\u0003\u0006\u0005*\u0015-#\u0011!Q\u0001\n\u0011BqaLC&\t\u0003)9\u0006\u0006\u0004\u0006Z\u0015mSQ\f\t\u00043\u0015-\u0003b\u0002C\u0013\u000b+\u0002\rA \u0005\b\tS))\u00061\u0001%\u0011\u001d1T1\nC\u0001\u000bC\"2\u0001JC2\u0011\u0019ITq\fa\u0001I\u00191Qq\r\u0001\u0001\u000bS\u0012\u0001cU;cgR<\u0016\u000e\u001c3dCJ$W*\u00199\u0014\u0007\u0015\u0015T\u0004\u0003\u0006\u0005&\u0015\u0015$\u0011!Q\u0001\nuDqaLC3\t\u0003)y\u0007\u0006\u0003\u0006r\u0015M\u0004cA\r\u0006f!9AQEC7\u0001\u0004i\bb\u0002\u001c\u0006f\u0011\u0005Qq\u000f\u000b\u0004I\u0015e\u0004BB\u001d\u0006v\u0001\u0007AeB\u0004\u0006~\u0001A\t!b \u0002)%\u001bH)\u001a9f]\u0012,g\u000e^\"pY2,7\r^8s!\rIR\u0011\u0011\u0004\b\u000b\u0007\u0003\u0001\u0012ACC\u0005QI5\u000fR3qK:$WM\u001c;D_2dWm\u0019;peN!Q\u0011QCD!\u0011I\"QK\u0016\t\u000f=*\t\t\"\u0001\u0006\fR\u0011Qq\u0010\u0005\t\u0005[)\t\t\"\u0001\u0006\u0010R\u00191#\"%\t\re*i\t1\u0001%\u000f\u001d))\n\u0001E\u0001\u000b/\u000bq#\u00119qe>D\u0018.\\1uK\u0012+\u0007/\u001a8eK:$X*\u00199\u0011\u0007e)IJB\u0004\u0006\u001c\u0002A\t!\"(\u0003/\u0005\u0003\bO]8yS6\fG/\u001a#fa\u0016tG-\u001a8u\u001b\u0006\u00048cACM;!9q&\"'\u0005\u0002\u0015\u0005FCACL\u0011\u001d1T\u0011\u0014C\u0001\u000bK#2\u0001JCT\u0011\u0019IT1\u0015a\u0001I\u00191Q1\u0016\u0001\u0001\u000b[\u0013q#\u00138ti\u0006tG/[1uK\u0012+\u0007/\u001a8eK:$X*\u00199\u0014\u000b\u0015%Vd!\u0007\t\u0015\u0015EV\u0011\u0016B\u0001B\u0003%Q0\u0001\u0004qCJ\fWn\u001d\u0005\u000b\u000bk+IK!A!\u0002\u0013\u0011\u0018\u0001C1diV\fGn\u001d\u0019\t\u000f=*I\u000b\"\u0001\u0006:R1Q1XC_\u000b\u007f\u00032!GCU\u0011\u001d)\t,b.A\u0002uDq!\".\u00068\u0002\u0007!\u000f\u0003\u0006\u0006D\u0016%&\u0019!C\u0005\u000b\u000b\fq!Y2uk\u0006d7/\u0006\u0002\u0006HB)Q\u0011ZChI5\u0011Q1\u001a\u0006\u0005\u000b\u001b\u0014Y+A\u0005j[6,H/\u00192mK&!Q\u0011[Cf\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\n\u000b+,I\u000b)A\u0005\u000b\u000f\f\u0001\"Y2uk\u0006d7\u000f\t\u0005\u000b\u000b3,IK1A\u0005\n\u0015m\u0017\u0001D3ySN$XM\u001c;jC2\u001cXCACo!\u0011aQq\u001c@\n\u0007\u0015\u0005\bBA\u0003BeJ\f\u0017\u0010C\u0005\u0006f\u0016%\u0006\u0015!\u0003\u0006^\u0006iQ\r_5ti\u0016tG/[1mg\u0002B\u0001\"\";\u0006*\u0012\u00051qF\u0001\u0013KbL7\u000f^3oi&\fGn\u001d(fK\u0012,Gm\u0002\u0005\u0006n\u0016%\u0006\u0012BCx\u0003-\u0019F/\u00192mK\u0006\u0013x\r\u00169\u0011\t\u0015EX1_\u0007\u0003\u000bS3\u0001\"\">\u0006*\"%Qq\u001f\u0002\f'R\f'\r\\3Be\u001e$\u0006oE\u0002\u0006t.AqaLCz\t\u0003)Y\u0010\u0006\u0002\u0006p\"AQq`Cz\t\u00031\t!A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0019\raQ\u0001\t\u0005\u0019\u0011]H\u0005C\u0004\u0007\b\u0015u\b\u0019\u0001@\u0002\u000bA\f'/Y7\t\u0011\u0019-Q\u0011\u0016C\u0005\r\u001b\ta\"\u001a=jgR,g\u000e^5bY\u001a{'\u000fF\u0002\u007f\r\u001fA\u0001B\"\u0005\u0007\n\u0001\u0007!1W\u0001\u0004a&$w\u0001\u0003D\u000b\u000bSCIAb\u0006\u0002\u001bUs7\u000f^1cY\u0016\f%o\u001a+q!\u0011)\tP\"\u0007\u0007\u0011\u0019mQ\u0011\u0016E\u0005\r;\u0011Q\"\u00168ti\u0006\u0014G.Z!sOR\u00038c\u0001D\r\u0017!9qF\"\u0007\u0005\u0002\u0019\u0005BC\u0001D\f\u0011!)yP\"\u0007\u0005\u0002\u0019\u0015B\u0003\u0002D\u0014\rW\u0001R\u0001\u0004C|\rS\u0001R\u0001\u0004CS}\u0012BqAb\u0002\u0007$\u0001\u0007ap\u0002\u0005\u00070\u0015%\u0006\u0012\u0002D\u0019\u0003=\u0019F/\u00192jY&TX\rZ!sOR\u0003\b\u0003BCy\rg1\u0001B\"\u000e\u0006*\"%aq\u0007\u0002\u0010'R\f'-\u001b7ju\u0016$\u0017I]4UaN\u0019a1G\u0006\t\u000f=2\u0019\u0004\"\u0001\u0007<Q\u0011a\u0011\u0007\u0005\t\u000b\u007f4\u0019\u0004\"\u0001\u0007@Q!a1\u0001D!\u0011\u001d19A\"\u0010A\u0002yDqANCU\t\u00031)\u0005F\u0002%\r\u000fBa!\u000fD\"\u0001\u0004!\u0003bB%\u0006*\u0012\u0005c1\n\u000b\u0007\u000332iE\"\u0015\t\u0011\u0019=c\u0011\na\u0001\u00033\n1!\u0019:h\u0011!\t)H\"\u0013A\u0002\u0005]ta\u0002D+\u0001!\u0005aqK\u0001\u0015o&dGmY1sIR{G+\u001f9f-\u0006\u0014X*\u00199\u0011\u0007e1IFB\u0004\u0007\\\u0001A\tA\"\u0018\u0003)]LG\u000eZ2be\u0012$v\u000eV=qKZ\u000b'/T1q'\r1I&\b\u0005\b_\u0019eC\u0011\u0001D1)\t19\u0006C\u00047\r3\"\tA\"\u001a\u0015\u0007\u001129\u0007\u0003\u0004:\rG\u0002\r\u0001J\u0004\b\rW\u0002\u0001\u0012\u0001D7\u0003I!\u0018\u0010]3WCJ$vn\u0014:jO&tW*\u00199\u0011\u0007e1yGB\u0004\u0007r\u0001A\tAb\u001d\u0003%QL\b/\u001a,beR{wJ]5hS:l\u0015\r]\n\u0004\r_j\u0002bB\u0018\u0007p\u0011\u0005aq\u000f\u000b\u0003\r[BqA\u000eD8\t\u00031Y\bF\u0002%\r{Ba!\u000fD=\u0001\u0004!cA\u0002DA\u0001\u00011\u0019IA\tD_:$\u0018-\u001b8t\u0007>dG.Z2u_J\u001cBAb \u0006\b\"Q\u0011q\u0002D@\u0005\u0003\u0005\u000b\u0011\u0002@\t\u000f=2y\b\"\u0001\u0007\nR!a1\u0012DG!\rIbq\u0010\u0005\b\u0003\u001f19\t1\u0001\u007f\u0011!\u0011iCb \u0005\u0002\u0019EEcA\n\u0007\u0014\"1\u0011Hb$A\u0002\u0011Bq!\u0013D@\t\u000329\n\u0006\u0003\u0002Z\u0019e\u0005\u0002\u0003D(\r+\u0003\r!!\u0017\u0007\r\u0019u\u0005\u0001\u0001DP\u0005M1\u0015\u000e\u001c;feRK\b/Z\"pY2,7\r^8s'\u00111YJ\")\u0011\te\u0011)F\u001d\u0005\f\rK3YJ!A!\u0002\u001319+A\u0001q!\u0011a!\u0005J\u0016\t\u000f=2Y\n\"\u0001\u0007,R!aQ\u0016DX!\rIb1\u0014\u0005\t\rK3I\u000b1\u0001\u0007(\"A!\u0011\u0011DN\t\u00032\u0019\f\u0006\u0003\u00076\u001ae\u0006#BCe\ro#\u0013bA<\u0006L\"1\u0011H\"-A\u0002\u0011B\u0001B!\f\u0007\u001c\u0012\u0005aQ\u0018\u000b\u0004'\u0019}\u0006BB\u001d\u0007<\u0002\u0007AE\u0002\u0004\u0007D\u0002\u0001aQ\u0019\u0002\u0015\u0007>dG.Z2u)f\u0004XmQ8mY\u0016\u001cGo\u001c:\u0016\t\u0019\u001dgqZ\n\u0005\r\u00034I\rE\u0003\u001a\u0005+2Y\r\u0005\u0003tm\u001a5\u0007c\u0001*\u0007P\u00121AK\"1C\u0002UC1Bb5\u0007B\n\u0005\t\u0015!\u0003\u0007V\u0006\u0011\u0001O\u001a\t\u0007\u0019\u0019]GE\"4\n\u0007\u0019e\u0007BA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u0011\u001dyc\u0011\u0019C\u0001\r;$BAb8\u0007bB)\u0011D\"1\u0007N\"Aa1\u001bDn\u0001\u00041)\u000e\u0003\u0005\u0003\u0002\u001a\u0005G\u0011\tDs)\u001119O\";\u0011\r\u0015%gq\u0017Dg\u0011\u0019Id1\u001da\u0001I!A!Q\u0006Da\t\u00031i\u000fF\u0002\u0014\r_Da!\u000fDv\u0001\u0004!cA\u0002Dz\u0001\u00011)P\u0001\u000bG_J,\u0015m\u00195UsB,GK]1wKJ\u001cXM]\n\u0005\rc\u0014I\u0003C\u0006\u0007z\u001aE(\u0011!Q\u0001\n\u0019m\u0018!\u00014\u0011\t1\u0011Ce\u0005\u0005\b_\u0019EH\u0011\u0001D\u0000)\u00119\tab\u0001\u0011\u0007e1\t\u0010\u0003\u0005\u0007z\u001au\b\u0019\u0001D~\u0011!\u0011iC\"=\u0005\u0002\u001d\u001dAcA\n\b\n!1\u0011h\"\u0002A\u0002\u00112aa\"\u0004\u0001\u0001\u001d=!!\u0005$j]\u0012$\u0016\u0010]3D_2dWm\u0019;peN!q1BD\t!\u0015I\"Q\u000bD\u0002\u0011-1)kb\u0003\u0003\u0002\u0003\u0006IAb*\t\u000f=:Y\u0001\"\u0001\b\u0018Q!q\u0011DD\u000e!\rIr1\u0002\u0005\t\rK;)\u00021\u0001\u0007(\"A!QFD\u0006\t\u00039y\u0002F\u0002\u0014\u000fCAa!OD\u000f\u0001\u0004!saBD\u0013\u0001!\u0005qqE\u0001\u0013\u000bJ\u0014xN\\3pkN\u001cu\u000e\u001c7fGR|'\u000fE\u0002\u001a\u000fS1qab\u000b\u0001\u0011\u00039iC\u0001\nFeJ|g.Z8vg\u000e{G\u000e\\3di>\u00148\u0003BD\u0015\u000b\u000fCqaLD\u0015\t\u00039\t\u0004\u0006\u0002\b(!A!QFD\u0015\t\u00039)\u0004F\u0002\u0014\u000foAa!OD\u001a\u0001\u0004!saBD\u001e\u0001!\u0005qQH\u0001\u0011C\u0012\f\u0007\u000f\u001e+p\u001d\u0016<(+\u001e8NCB\u00042!GD \r\u001d9\t\u0005\u0001E\u0001\u000f\u0007\u0012\u0001#\u00193baR$vNT3x%VtW*\u00199\u0014\u0007\u001d}R\u0004C\u00040\u000f\u007f!\tab\u0012\u0015\u0005\u001du\u0002\u0002CD&\u000f\u007f!Ia\"\u0014\u0002\u001b\u0005$\u0017\r\u001d;U_:+wOU;o)\u0015qxqJD)\u0011\u001d\u00199a\"\u0013A\u0002\u0011Bq!a\u0004\bJ\u0001\u0007a\u0010C\u00047\u000f\u007f!\ta\"\u0016\u0015\u0007\u0011:9\u0006\u0003\u0004:\u000f'\u0002\r\u0001\n\t\u0004{\u001dm\u0013bAD/\t\tY1+_7c_2$\u0016M\u00197f\u0001")
public interface TypeMaps {
    public TypeMaps$normalizeAliases$ normalizeAliases();

    public TypeMaps$dropSingletonType$ dropSingletonType();

    public TypeMaps$abstractTypesToBounds$ abstractTypesToBounds();

    public boolean etaExpandKeepsStar();

    public TypeMaps$dropIllegalStarTypes$ dropIllegalStarTypes();

    public TypeMap rawToExistential();

    public TypeMaps$wildcardExtrapolation$ wildcardExtrapolation();

    public boolean isPossiblePrefix(Symbols.Symbol var1);

    public boolean skipPrefixOf(Types.Type var1, Symbols.Symbol var2);

    public AsSeenFromMap newAsSeenFromMap(Types.Type var1, Symbols.Symbol var2);

    public TypeMaps$IsDependentCollector$ IsDependentCollector();

    public TypeMaps$ApproximateDependentMap$ ApproximateDependentMap();

    public TypeMaps$wildcardToTypeVarMap$ wildcardToTypeVarMap();

    public TypeMaps$typeVarToOriginMap$ typeVarToOriginMap();

    public TypeMaps$ErroneousCollector$ ErroneousCollector();

    public TypeMaps$adaptToNewRunMap$ adaptToNewRunMap();

    public abstract class TypeMap
    implements Function1<Types.Type, Types.Type> {
        public final boolean scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance;
        private int _variance;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public boolean apply$mcZD$sp(double v1) {
            return Function1$class.apply$mcZD$sp(this, v1);
        }

        @Override
        public double apply$mcDD$sp(double v1) {
            return Function1$class.apply$mcDD$sp(this, v1);
        }

        @Override
        public float apply$mcFD$sp(double v1) {
            return Function1$class.apply$mcFD$sp(this, v1);
        }

        @Override
        public int apply$mcID$sp(double v1) {
            return Function1$class.apply$mcID$sp(this, v1);
        }

        @Override
        public long apply$mcJD$sp(double v1) {
            return Function1$class.apply$mcJD$sp(this, v1);
        }

        @Override
        public void apply$mcVD$sp(double v1) {
            Function1$class.apply$mcVD$sp(this, v1);
        }

        @Override
        public boolean apply$mcZF$sp(float v1) {
            return Function1$class.apply$mcZF$sp(this, v1);
        }

        @Override
        public double apply$mcDF$sp(float v1) {
            return Function1$class.apply$mcDF$sp(this, v1);
        }

        @Override
        public float apply$mcFF$sp(float v1) {
            return Function1$class.apply$mcFF$sp(this, v1);
        }

        @Override
        public int apply$mcIF$sp(float v1) {
            return Function1$class.apply$mcIF$sp(this, v1);
        }

        @Override
        public long apply$mcJF$sp(float v1) {
            return Function1$class.apply$mcJF$sp(this, v1);
        }

        @Override
        public void apply$mcVF$sp(float v1) {
            Function1$class.apply$mcVF$sp(this, v1);
        }

        @Override
        public boolean apply$mcZI$sp(int v1) {
            return Function1$class.apply$mcZI$sp(this, v1);
        }

        @Override
        public double apply$mcDI$sp(int v1) {
            return Function1$class.apply$mcDI$sp(this, v1);
        }

        @Override
        public float apply$mcFI$sp(int v1) {
            return Function1$class.apply$mcFI$sp(this, v1);
        }

        @Override
        public int apply$mcII$sp(int v1) {
            return Function1$class.apply$mcII$sp(this, v1);
        }

        @Override
        public long apply$mcJI$sp(int v1) {
            return Function1$class.apply$mcJI$sp(this, v1);
        }

        @Override
        public void apply$mcVI$sp(int v1) {
            Function1$class.apply$mcVI$sp(this, v1);
        }

        @Override
        public boolean apply$mcZJ$sp(long v1) {
            return Function1$class.apply$mcZJ$sp(this, v1);
        }

        @Override
        public double apply$mcDJ$sp(long v1) {
            return Function1$class.apply$mcDJ$sp(this, v1);
        }

        @Override
        public float apply$mcFJ$sp(long v1) {
            return Function1$class.apply$mcFJ$sp(this, v1);
        }

        @Override
        public int apply$mcIJ$sp(long v1) {
            return Function1$class.apply$mcIJ$sp(this, v1);
        }

        @Override
        public long apply$mcJJ$sp(long v1) {
            return Function1$class.apply$mcJJ$sp(this, v1);
        }

        @Override
        public void apply$mcVJ$sp(long v1) {
            Function1$class.apply$mcVJ$sp(this, v1);
        }

        @Override
        public <A> Function1<A, Types.Type> compose(Function1<A, Types.Type> g) {
            return Function1$class.compose(this, g);
        }

        @Override
        public <A> Function1<Types.Type, A> andThen(Function1<Types.Type, A> g) {
            return Function1$class.andThen(this, g);
        }

        @Override
        public String toString() {
            return Function1$class.toString(this);
        }

        @Override
        public abstract Types.Type apply(Types.Type var1);

        public void variance_$eq(int x) {
            boolean bl = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance;
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(this).toString());
            }
            this._variance = x;
        }

        public int variance() {
            return this._variance;
        }

        /*
         * Loose catch block
         */
        public Types.Type mapOver(Types.Type tp) {
            TypeMap typeMap;
            TypeMap typeMap2;
            TypeMap typeMap3;
            Types.Type type;
            if (tp instanceof Types.TypeRef) {
                Types.TypeRef typeRef = (Types.TypeRef)tp;
                Types.Type pre1 = this.apply(typeRef.pre());
                List<Types.Type> args1 = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance && typeRef.args().nonEmpty() && !Variance$.MODULE$.isInvariant$extension(this.variance()) && typeRef.sym().typeParams().nonEmpty() ? this.mapOverArgs(typeRef.args(), typeRef.sym().typeParams()) : typeRef.args().mapConserve(this);
                type = pre1 == typeRef.pre() && args1 == typeRef.args() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().copyTypeRef(tp, pre1, typeRef.coevolveSym(pre1), args1);
            } else if (tp instanceof Types.ThisType) {
                type = tp;
            } else if (tp instanceof Types.SingleType) {
                Types.Type pre1;
                Types.SingleType singleType = (Types.SingleType)tp;
                type = singleType.sym().isPackageClass() ? tp : ((pre1 = this.apply(singleType.pre())) == singleType.pre() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().singleType(pre1, singleType.sym()));
            } else if (tp instanceof Types.MethodType) {
                List<Symbols.Symbol> list2;
                Types.MethodType methodType;
                block44: {
                    methodType = (Types.MethodType)tp;
                    typeMap3 = this;
                    if (this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                        this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                    }
                    list2 = this.mapOver(methodType.params());
                    if (!this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) break block44;
                    this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                }
                List<Symbols.Symbol> params1 = list2;
                Types.Type result1 = this.apply(methodType.resultType());
                type = params1 == methodType.params() && result1 == methodType.resultType() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().copyMethodType(tp, params1, result1.substSym(methodType.params(), params1));
            } else if (tp instanceof Types.PolyType) {
                List<Symbols.Symbol> list3;
                Types.PolyType polyType;
                block45: {
                    polyType = (Types.PolyType)tp;
                    typeMap2 = this;
                    if (this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                        this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                    }
                    list3 = this.mapOver(polyType.typeParams());
                    if (!this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) break block45;
                    this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                }
                List<Symbols.Symbol> tparams1 = list3;
                Types.Type result1 = this.apply(polyType.resultType());
                type = tparams1 == polyType.typeParams() && result1 == polyType.resultType() ? tp : new Types.PolyType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), tparams1, result1.substSym(polyType.typeParams(), tparams1));
            } else if (tp instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                Types.Type result1 = this.apply(nullaryMethodType.resultType());
                type = result1 == nullaryMethodType.resultType() ? tp : new Types.NullaryMethodType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), result1);
            } else if (tp instanceof Types.ConstantType) {
                type = tp;
            } else if (tp instanceof Types.SuperType) {
                Types.SuperType superType = (Types.SuperType)tp;
                Types.Type thistp1 = this.apply(superType.thistpe());
                Types.Type supertp1 = this.apply(superType.supertpe());
                type = thistp1 == superType.thistpe() && supertp1 == superType.supertpe() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().SuperType().apply(thistp1, supertp1);
            } else if (tp instanceof Types.TypeBounds) {
                Types.Type type2;
                Types.TypeBounds typeBounds;
                block46: {
                    typeBounds = (Types.TypeBounds)tp;
                    typeMap = this;
                    if (this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                        this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                    }
                    type2 = this.apply(typeBounds.lo());
                    if (!this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) break block46;
                    this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                }
                Types.Type lo1 = type2;
                Types.Type hi1 = this.apply(typeBounds.hi());
                type = lo1 == typeBounds.lo() && hi1 == typeBounds.hi() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().TypeBounds().apply(lo1, hi1);
            } else if (tp instanceof Types.BoundedWildcardType) {
                Types.BoundedWildcardType boundedWildcardType = (Types.BoundedWildcardType)tp;
                Types.Type bounds1 = this.apply(boundedWildcardType.bounds());
                type = bounds1 == boundedWildcardType.bounds() ? tp : new Types.BoundedWildcardType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), (Types.TypeBounds)bounds1);
            } else if (tp instanceof Types.RefinedType) {
                Types.RefinedType refinedType = (Types.RefinedType)tp;
                List<Types.Type> parents1 = refinedType.parents().mapConserve(this);
                Scopes.Scope decls1 = this.mapOver(refinedType.decls());
                type = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().copyRefinedType(refinedType, parents1, decls1);
            } else if (tp instanceof Types.ExistentialType) {
                Types.ExistentialType existentialType = (Types.ExistentialType)tp;
                List<Symbols.Symbol> tparams1 = this.mapOver(existentialType.quantified());
                Types.Type result1 = this.apply(existentialType.underlying());
                type = tparams1 == existentialType.quantified() && result1 == existentialType.underlying() ? tp : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().newExistentialType(tparams1, result1.substSym(existentialType.quantified(), tparams1));
            } else if (tp instanceof Types.OverloadedType) {
                Types.OverloadedType overloadedType = (Types.OverloadedType)tp;
                Types.Type pre1 = overloadedType.pre() instanceof Types.ClassInfoType ? overloadedType.pre() : this.apply(overloadedType.pre());
                type = pre1 == overloadedType.pre() ? tp : new Types.OverloadedType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), pre1, overloadedType.alternatives());
            } else if (tp instanceof Types.AntiPolyType) {
                Types.AntiPolyType antiPolyType = (Types.AntiPolyType)tp;
                Types.Type pre1 = this.apply(antiPolyType.pre());
                List<Types.Type> args1 = antiPolyType.targs().mapConserve(this);
                type = pre1 == antiPolyType.pre() && args1 == antiPolyType.targs() ? tp : new Types.AntiPolyType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), pre1, args1);
            } else if (tp instanceof Types.TypeVar) {
                Types.TypeVar typeVar = (Types.TypeVar)tp;
                type = typeVar.constr().instValid() ? this.apply(typeVar.constr().inst()) : typeVar.applyArgs(this.mapOverArgs(typeVar.typeArgs(), typeVar.params()));
            } else if (tp instanceof Types.AnnotatedType) {
                Types.AnnotatedType annotatedType = (Types.AnnotatedType)tp;
                List<AnnotationInfos.AnnotationInfo> annots1 = this.mapOverAnnotations(annotatedType.annotations());
                Types.Type atp1 = this.apply(annotatedType.underlying());
                type = annots1 == annotatedType.annotations() && atp1 == annotatedType.underlying() ? tp : (annots1.isEmpty() ? atp1 : new Types.AnnotatedType(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer(), annots1, atp1));
            } else {
                type = tp;
            }
            return type;
            catch (Throwable throwable) {
                if (typeMap3.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                    typeMap3.variance_$eq(Variance$.MODULE$.flip$extension(typeMap3.variance()));
                }
                throw throwable;
            }
            catch (Throwable throwable) {
                if (typeMap2.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                    typeMap2.variance_$eq(Variance$.MODULE$.flip$extension(typeMap2.variance()));
                }
                throw throwable;
            }
            catch (Throwable throwable) {
                if (typeMap.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                    typeMap.variance_$eq(Variance$.MODULE$.flip$extension(typeMap.variance()));
                }
                throw throwable;
            }
        }

        /*
         * WARNING - void declaration
         */
        public <T> T withVariance(int v, Function0<T> body2) {
            T t;
            int saved = this.variance();
            this.variance_$eq(v);
            try {
                t = body2.apply();
                this.variance_$eq(saved);
            }
            catch (Throwable throwable) {
                void var3_3;
                this.variance_$eq((int)var3_3);
                throw throwable;
            }
            return t;
        }

        public final <T> T flipped(Function0<T> body2) {
            if (this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
            }
            try {
                return body2.apply();
            }
            finally {
                if (this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance) {
                    this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
                }
            }
        }

        public List<Types.Type> mapOverArgs(List<Types.Type> args, List<Symbols.Symbol> tparams2) {
            return this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance ? this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().map2Conserve(args, tparams2, new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ TypeMap $outer;

                public final Types.Type apply(Types.Type arg, Symbols.Symbol tparam) {
                    return this.$outer.withVariance(Variance$.MODULE$.$times$extension(this.$outer.variance(), tparam.variance()), new Serializable(this, arg){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TypeMap$$anonfun$mapOverArgs$1 $outer;
                        private final Types.Type arg$1;

                        public final Types.Type apply() {
                            return this.$outer.$outer.apply(this.arg$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.arg$1 = arg$1;
                        }
                    });
                }

                public /* synthetic */ TypeMap scala$reflect$internal$tpe$TypeMaps$TypeMap$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }) : args.mapConserve(this);
        }

        private Types.Type applyToSymbolInfo(Symbols.Symbol sym) {
            return this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance && !Variance$.MODULE$.isInvariant$extension(this.variance()) && sym.isAliasType() ? this.withVariance(Variance$.MODULE$.Invariant(), (Function0)((Object)new Serializable(this, sym){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TypeMap $outer;
                private final Symbols.Symbol sym$1;

                public final Types.Type apply() {
                    return this.$outer.apply(this.sym$1.info());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sym$1 = sym$1;
                }
            })) : this.apply(sym.info());
        }

        public boolean noChangeToSymbols(List<Symbols.Symbol> origSyms) {
            return this.loop$1(origSyms);
        }

        public Scopes.Scope mapOver(Scopes.Scope scope) {
            List<Symbols.Symbol> elems = scope.toList();
            List<Symbols.Symbol> elems1 = this.mapOver(elems);
            return elems1 == elems ? scope : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().newScopeWith(elems1);
        }

        public List<Symbols.Symbol> mapOver(List<Symbols.Symbol> origSyms) {
            return this.noChangeToSymbols(origSyms) ? origSyms : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().cloneSymbolsAndModify(origSyms, this);
        }

        public AnnotationInfos.AnnotationInfo mapOver(AnnotationInfos.AnnotationInfo annot) {
            Option<Tuple3<Types.Type, List<Trees.Tree>, List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>>>> option = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().AnnotationInfo().unapply(annot);
            if (option.isEmpty()) {
                throw new MatchError(annot);
            }
            Tuple3<Types.Type, List<Trees.Tree>, List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>>> tuple3 = new Tuple3<Types.Type, List<Trees.Tree>, List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>>>(option.get()._1(), option.get()._2(), option.get()._3());
            Types.Type atp = tuple3._1();
            List<Trees.Tree> args = tuple3._2();
            List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>> assocs2 = tuple3._3();
            Types.Type atp1 = this.mapOver(atp);
            List<Trees.Tree> args1 = this.mapOverAnnotArgs(args);
            return args == args1 && atp == atp1 ? annot : (args1.isEmpty() && args.nonEmpty() ? this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableAnnotation() : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().AnnotationInfo().apply(atp1, args1, assocs2).setPos(annot.pos()));
        }

        public List<AnnotationInfos.AnnotationInfo> mapOverAnnotations(List<AnnotationInfos.AnnotationInfo> annots) {
            List<AnnotationInfos.AnnotationInfo> annots1 = annots.mapConserve(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TypeMap $outer;

                public final AnnotationInfos.AnnotationInfo apply(AnnotationInfos.AnnotationInfo annot) {
                    return this.$outer.mapOver(annot);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
            return annots1 == annots ? annots : (List)annots1.filterNot((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TypeMap $outer;

                public final boolean apply(AnnotationInfos.AnnotationInfo x$3) {
                    return x$3 == this.$outer.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableAnnotation();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public List<Trees.Tree> mapOverAnnotArgs(List<Trees.Tree> args) {
            List<Trees.Tree> args1 = args.mapConserve(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TypeMap $outer;

                public final Trees.Tree apply(Trees.Tree tree) {
                    return this.$outer.mapOver(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
            return args1.contains(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableTree()) ? Nil$.MODULE$ : args1;
        }

        public Trees.Tree mapOver(Trees.Tree tree) {
            NonLocalReturnControl nonLocalReturnControl;
            block2: {
                Trees.Tree tree2;
                Object object = new Object();
                try {
                    tree2 = this.mapOver(tree, (Function0<Nothing$>)((Object)new Serializable(this, object){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TypeMap $outer;
                        private final Object nonLocalReturnKey1$1;

                        public final Nothing$ apply() {
                            throw new NonLocalReturnControl<Types$UnmappableTree$>(this.nonLocalReturnKey1$1, this.$outer.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableTree());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                        }
                    }));
                }
                catch (NonLocalReturnControl nonLocalReturnControl2) {
                    nonLocalReturnControl = nonLocalReturnControl2;
                    if (nonLocalReturnControl2.key() != object) break block2;
                    tree2 = (Trees.Tree)nonLocalReturnControl.value();
                }
                return tree2;
            }
            throw nonLocalReturnControl;
        }

        public Trees.Tree mapOver(Trees.Tree tree, Function0<Nothing$> giveup) {
            return new TypeMapTransformer().transform(tree);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer() {
            return this.$outer;
        }

        private final boolean loop$1(List syms) {
            block3: {
                boolean bl;
                block2: {
                    while (true) {
                        if (((Object)Nil$.MODULE$).equals(syms)) {
                            bl = true;
                            break block2;
                        }
                        if (!(syms instanceof $colon$colon)) break block3;
                        $colon$colon $colon$colon = ($colon$colon)syms;
                        if (((Symbols.Symbol)$colon$colon.head()).info() != this.applyToSymbolInfo((Symbols.Symbol)$colon$colon.head())) break;
                        syms = $colon$colon.tl$1();
                    }
                    bl = false;
                }
                return bl;
            }
            throw new MatchError(syms);
        }

        public TypeMap(SymbolTable $outer, boolean trackVariance) {
            this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$trackVariance = trackVariance;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Function1$class.$init$(this);
            this._variance = trackVariance ? Variance$.MODULE$.Covariant() : Variance$.MODULE$.Invariant();
        }

        public TypeMap(SymbolTable $outer) {
            this($outer, false);
        }

        public class TypeMapTransformer
        extends Trees.Transformer {
            public Trees.Tree transform(Trees.Tree tree) {
                Trees.Tree tree1 = (Trees.Tree)super.transform(tree);
                Types.Type tpe1 = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$TypeMapTransformer$$$outer().apply(tree1.tpe());
                return tree == tree1 && tree.tpe() == tpe1 ? tree : tree1.shallowDuplicate().setType(tpe1);
            }

            public /* synthetic */ TypeMap scala$reflect$internal$tpe$TypeMaps$TypeMap$TypeMapTransformer$$$outer() {
                return TypeMap.this;
            }

            public TypeMapTransformer() {
                if (TypeMap.this == null) {
                    throw null;
                }
                super(TypeMap.this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer());
            }
        }
    }

    public abstract class SubstMap<T>
    extends TypeMap {
        public final List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$SubstMap$$from;
        public final List<T> scala$reflect$internal$tpe$TypeMaps$SubstMap$$to;

        public boolean matches(Symbols.Symbol sym, Symbols.Symbol sym1) {
            return sym == sym1;
        }

        public abstract Types.Type toType(Types.Type var1, T var2);

        public Types.Type renameBoundSyms(Types.Type tp) {
            Types.Type type;
            if (tp instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tp;
                type = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(methodType.params(), methodType.resultType(), new Serializable(this, tp){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SubstMap $outer;
                    private final Types.Type tp$3;

                    public final Types.Type apply(List<Symbols.Symbol> ps1, Types.Type tp1) {
                        return this.$outer.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().copyMethodType(this.tp$3, ps1, this.$outer.renameBoundSyms(tp1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.tp$3 = tp$3;
                    }
                });
            } else if (tp instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)tp;
                type = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(polyType.typeParams(), polyType.resultType(), new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SubstMap $outer;

                    public final Types.PolyType apply(List<Symbols.Symbol> ps1, Types.Type tp1) {
                        return new Types.PolyType(this.$outer.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer(), ps1, this.$outer.renameBoundSyms(tp1));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            } else if (tp instanceof Types.ExistentialType) {
                Types.ExistentialType existentialType = (Types.ExistentialType)tp;
                type = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(existentialType.quantified(), existentialType.underlying(), new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ SubstMap $outer;

                    public final Types.Type apply(List<Symbols.Symbol> quantified, Types.Type underlying) {
                        return this.$outer.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().newExistentialType(quantified, underlying);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            } else {
                type = tp;
            }
            return type;
        }

        private Types.Type subst(Types.Type tp, Symbols.Symbol sym, List<Symbols.Symbol> from2, List<T> to2) {
            while (true) {
                block5: {
                    Types.Type type;
                    block4: {
                        block3: {
                            if (!from2.isEmpty()) break block3;
                            type = tp;
                            break block4;
                        }
                        if (!this.matches(from2.head(), sym)) break block5;
                        type = this.toType(tp, to2.head());
                    }
                    return type;
                }
                to2 = (List)to2.tail();
                from2 = (List)from2.tail();
            }
        }

        /*
         * Unable to fully structure code
         */
        @Override
        public Types.Type apply(Types.Type tp0) {
            block3: {
                block5: {
                    block4: {
                        block2: {
                            if (!this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$from.isEmpty()) break block2;
                            v0 = tp0;
                            break block3;
                        }
                        boundSyms = tp0.boundSyms();
                        tp1 = boundSyms.nonEmpty() != false && boundSyms.exists((Function1<Symbols.Symbol, Object>)new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ SubstMap $outer;

                            public final boolean apply(Object elem) {
                                return this.$outer.scala$reflect$internal$tpe$TypeMaps$SubstMap$$from.contains(elem);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }) != false ? this.renameBoundSyms(tp0) : tp0;
                        tp = this.mapOver(tp1);
                        if (!(tp instanceof Types.TypeRef)) break block4;
                        var5_5 = (Types.TypeRef)tp;
                        if (!this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().NoPrefix().equals(var5_5.pre())) break block4;
                        tcon = this.substFor$1(var5_5.sym(), tp);
                        var10_7 = tp == tcon || var5_5.args().isEmpty() != false ? tcon : this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().appliedType(tcon.typeConstructor(), var5_5.args());
                        break block5;
                    }
                    if (!(tp instanceof Types.SingleType)) ** GOTO lbl-1000
                    var6_8 = (Types.SingleType)tp;
                    if (this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().NoPrefix().equals(var6_8.pre())) {
                        var10_7 = this.substFor$1(var6_8.sym(), tp);
                    } else lbl-1000:
                    // 2 sources

                    {
                        var10_7 = tp instanceof Types.ClassInfoType != false ? ((parents1 = (var8_9 = (Types.ClassInfoType)tp).parents().mapConserve(this)) == var8_9.parents() ? tp : new Types.ClassInfoType(this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer(), parents1, var8_9.decls(), var8_9.typeSymbol())) : tp;
                    }
                }
                v0 = var10_7;
            }
            return v0;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer() {
            return this.$outer;
        }

        private final Types.Type substFor$1(Symbols.Symbol sym, Types.Type tp$4) {
            return this.subst(tp$4, sym, this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$from, this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$to);
        }

        public SubstMap(SymbolTable $outer, List<Symbols.Symbol> from2, List<T> to2) {
            this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$from = from2;
            this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$to = to2;
            super($outer);
            if ($outer.isDeveloper()) {
                boolean bl = $outer.sameLength(from2, to2);
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"Unsound substitution from ").append(this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$from).append((Object)" to ").append(this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$to).toString()).toString());
                }
            }
        }
    }

    public class SubstSymMap
    extends SubstMap<Symbols.Symbol> {
        public final List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$from;
        public final List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$to;
        private volatile TypeMaps$SubstSymMap$mapTreeSymbols$ mapTreeSymbols$module;

        private TypeMaps$SubstSymMap$mapTreeSymbols$ mapTreeSymbols$lzycompute() {
            SubstSymMap substSymMap = this;
            synchronized (substSymMap) {
                if (this.mapTreeSymbols$module == null) {
                    this.mapTreeSymbols$module = new TypeMaps$SubstSymMap$mapTreeSymbols$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.mapTreeSymbols$module;
            }
        }

        @Override
        public Types.Type toType(Types.Type fromtp, Symbols.Symbol sym) {
            block4: {
                Types.Type type;
                block3: {
                    block2: {
                        if (!(fromtp instanceof Types.TypeRef)) break block2;
                        Types.TypeRef typeRef = (Types.TypeRef)fromtp;
                        type = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().copyTypeRef(fromtp, typeRef.pre(), sym, typeRef.args());
                        break block3;
                    }
                    if (!(fromtp instanceof Types.SingleType)) break block4;
                    Types.SingleType singleType = (Types.SingleType)fromtp;
                    type = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().singleType(singleType.pre(), sym);
                }
                return type;
            }
            throw new MatchError(fromtp);
        }

        private Symbols.Symbol subst(Symbols.Symbol sym, List<Symbols.Symbol> from2, List<Symbols.Symbol> to2) {
            while (true) {
                block5: {
                    Symbols.Symbol symbol;
                    block4: {
                        block3: {
                            if (!from2.isEmpty()) break block3;
                            symbol = sym;
                            break block4;
                        }
                        if (!this.matches(from2.head(), sym)) break block5;
                        symbol = to2.head();
                    }
                    return symbol;
                }
                to2 = (List)to2.tail();
                from2 = (List)from2.tail();
            }
        }

        private Symbols.Symbol substFor(Symbols.Symbol sym) {
            return this.subst(sym, this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$from, this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$to);
        }

        @Override
        public Types.Type apply(Types.Type tp) {
            Types.Type type;
            if (this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$from.isEmpty()) {
                type = tp;
            } else {
                Types.SingleType singleType;
                Types.Type type2;
                Types.TypeRef typeRef;
                if (tp instanceof Types.TypeRef && (typeRef = (Types.TypeRef)tp).pre() != this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().NoPrefix()) {
                    Symbols.Symbol newSym = this.substFor(typeRef.sym());
                    type2 = this.mapOver(typeRef.sym() == newSym ? tp : this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().copyTypeRef(tp, typeRef.pre(), newSym, typeRef.args()));
                } else if (tp instanceof Types.SingleType && (singleType = (Types.SingleType)tp).pre() != this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().NoPrefix()) {
                    Symbols.Symbol newSym = this.substFor(singleType.sym());
                    type2 = this.mapOver(singleType.sym() == newSym ? tp : this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().singleType(singleType.pre(), newSym));
                } else {
                    type2 = super.apply(tp);
                }
                type = type2;
            }
            return type;
        }

        public TypeMaps$SubstSymMap$mapTreeSymbols$ mapTreeSymbols() {
            return this.mapTreeSymbols$module == null ? this.mapTreeSymbols$lzycompute() : this.mapTreeSymbols$module;
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree tree, Function0<Nothing$> giveup) {
            return this.mapTreeSymbols().transform(tree);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer() {
            return this.$outer;
        }

        public SubstSymMap(SymbolTable $outer, List<Symbols.Symbol> from2, List<Symbols.Symbol> to2) {
            this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$from = from2;
            this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$to = to2;
            super($outer, from2, to2);
        }

        public SubstSymMap(SymbolTable $outer, Seq<Tuple2<Symbols.Symbol, Symbols.Symbol>> pairs) {
            this($outer, pairs.toList().map(new Serializable($outer){
                public static final long serialVersionUID = 0L;

                public final Symbols.Symbol apply(Tuple2<Symbols.Symbol, Symbols.Symbol> x$11) {
                    return x$11._1();
                }
            }, List$.MODULE$.canBuildFrom()), pairs.toList().map(new Serializable($outer){
                public static final long serialVersionUID = 0L;

                public final Symbols.Symbol apply(Tuple2<Symbols.Symbol, Symbols.Symbol> x$12) {
                    return x$12._2();
                }
            }, List$.MODULE$.canBuildFrom()));
        }
    }

    public class SubstTypeMap
    extends SubstMap<Types.Type> {
        private final List<Symbols.Symbol> from;
        private final List<Types.Type> to;

        private TypeMaps$SubstTypeMap$trans$2$ trans$1$lzycompute(Function0 x$1, VolatileObjectRef x$2) {
            SubstTypeMap substTypeMap = this;
            synchronized (substTypeMap) {
                if (x$2.elem == null) {
                    x$2.elem = new TypeMaps$SubstTypeMap$trans$2$(this, x$1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return (TypeMaps$SubstTypeMap$trans$2$)x$2.elem;
            }
        }

        public List<Symbols.Symbol> from() {
            return this.from;
        }

        public List<Types.Type> to() {
            return this.to;
        }

        @Override
        public Types.Type toType(Types.Type fromtp, Types.Type tp) {
            return tp;
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree tree, Function0<Nothing$> giveup) {
            VolatileObjectRef<Object> trans$module = VolatileObjectRef.zero();
            return this.trans$1(giveup, trans$module).transform(tree);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstTypeMap$$$outer() {
            return this.$outer;
        }

        private final TypeMaps$SubstTypeMap$trans$2$ trans$1(Function0 giveup$1, VolatileObjectRef trans$module$1) {
            return trans$module$1.elem == null ? this.trans$1$lzycompute(giveup$1, trans$module$1) : (TypeMaps$SubstTypeMap$trans$2$)trans$module$1.elem;
        }

        public SubstTypeMap(SymbolTable $outer, List<Symbols.Symbol> from2, List<Types.Type> to2) {
            this.from = from2;
            this.to = to2;
            super($outer, from2, to2);
        }
    }

    public class SubstThisMap
    extends TypeMap {
        private final Symbols.Symbol from;
        private final Types.Type to;

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public Types.Type apply(Types.Type tp) {
            if (!(tp instanceof Types.ThisType)) return this.mapOver(tp);
            Types.ThisType thisType = (Types.ThisType)tp;
            Symbols.Symbol symbol = thisType.sym();
            Symbols.Symbol symbol2 = this.from;
            if (symbol != null) {
                if (!symbol.equals(symbol2)) return this.mapOver(tp);
                return this.to;
            }
            if (symbol2 == null) return this.to;
            return this.mapOver(tp);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstThisMap$$$outer() {
            return this.$outer;
        }

        public SubstThisMap(SymbolTable $outer, Symbols.Symbol from2, Types.Type to2) {
            this.from = from2;
            this.to = to2;
            super($outer);
        }
    }

    public class AsSeenFromMap
    extends TypeMap
    implements KeepOnlyTypeConstraints {
        public final Types.Type scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix;
        public final Symbols.Symbol scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass;
        private List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems;
        private List<Symbols.Symbol> _capturedParams;
        private final boolean isStablePrefix;
        public boolean scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation;
        private volatile TypeMaps$AsSeenFromMap$annotationArgRewriter$ annotationArgRewriter$module;

        private TypeMaps$AsSeenFromMap$annotationArgRewriter$ annotationArgRewriter$lzycompute() {
            AsSeenFromMap asSeenFromMap = this;
            synchronized (asSeenFromMap) {
                if (this.annotationArgRewriter$module == null) {
                    this.annotationArgRewriter$module = new TypeMaps$AsSeenFromMap$annotationArgRewriter$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.annotationArgRewriter$module;
            }
        }

        @Override
        public boolean keepAnnotation(AnnotationInfos.AnnotationInfo annot) {
            return TypeMaps$KeepOnlyTypeConstraints$class.keepAnnotation(this, annot);
        }

        @Override
        public /* synthetic */ AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(AnnotationInfos.AnnotationInfo annot) {
            return super.mapOver(annot);
        }

        @Override
        public AnnotationInfos.AnnotationInfo mapOver(AnnotationInfos.AnnotationInfo annot) {
            return TypeMaps$AnnotationFilter$class.mapOver(this, annot);
        }

        public List<Symbols.Symbol> capturedParams() {
            return this._capturedParams();
        }

        public List<Symbols.Symbol> capturedSkolems() {
            return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems();
        }

        @Override
        public Types.Type apply(Types.Type tp) {
            Types.Type type;
            if (tp instanceof Types.ThisType) {
                Types.ThisType thisType = (Types.ThisType)tp;
                type = this.thisTypeAsSeen(thisType);
            } else {
                Types.TypeRef typeRef;
                Types.SingleType singleType;
                type = tp instanceof Types.SingleType ? ((singleType = (Types.SingleType)tp).sym().isPackageClass() ? singleType : this.singleTypeAsSeen(singleType)) : (tp instanceof Types.TypeRef && this.isTypeParamOfEnclosingClass((typeRef = (Types.TypeRef)tp).sym()) ? this.classParameterAsSeen(typeRef) : this.mapOver(tp));
            }
            return type;
        }

        public List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems() {
            return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems;
        }

        public void scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems_$eq(List<Symbols.Symbol> x$1) {
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems = x$1;
        }

        private List<Symbols.Symbol> _capturedParams() {
            return this._capturedParams;
        }

        private void _capturedParams_$eq(List<Symbols.Symbol> x$1) {
            this._capturedParams = x$1;
        }

        private boolean isStablePrefix() {
            return this.isStablePrefix;
        }

        private boolean isBaseClassOfEnclosingClass(Symbols.Symbol base) {
            return !base.hasCompleteInfo() || this.loop$2(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, base);
        }

        private boolean isTypeParamOfEnclosingClass(Symbols.Symbol sym) {
            return sym.isTypeParameter() && sym.owner().isClass() && this.isBaseClassOfEnclosingClass(sym.owner());
        }

        public Types.Type captureThis(Types.Type pre, Symbols.Symbol clazz) {
            Types.Type type;
            Option<Symbols.Symbol> option = this.capturedParams().find((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, clazz){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol clazz$1;

                public final boolean apply(Symbols.Symbol x$6) {
                    Symbols.Symbol symbol = x$6.owner();
                    Symbols.Symbol symbol2 = this.clazz$1;
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                }
                {
                    this.clazz$1 = clazz$1;
                }
            }));
            if (option instanceof Some) {
                Some some = (Some)option;
                type = ((Symbols.Symbol)some.x()).tpe();
            } else {
                Symbols.TypeSymbol qvar = (Symbols.TypeSymbol)clazz.freshExistential(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().nme().SINGLETON_SUFFIX()).setInfo(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().singletonBounds(pre));
                this._capturedParams_$eq(this._capturedParams().$colon$colon(qvar));
                this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().debuglog((Function0<String>)((Object)new Serializable(this, clazz, qvar){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ AsSeenFromMap $outer;
                    private final Symbols.Symbol clazz$1;
                    private final Symbols.TypeSymbol qvar$1;

                    public final String apply() {
                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Captured This(", ") seen from ", ": ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.clazz$1.fullNameString(), this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.qvar$1.defString()}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.clazz$1 = clazz$1;
                        this.qvar$1 = qvar$1;
                    }
                }));
                type = qvar.tpe();
            }
            return type;
        }

        public void captureSkolems(List<Symbols.Symbol> skolems) {
            skolems.withFilter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ AsSeenFromMap $outer;

                public final boolean apply(Symbols.Symbol p) {
                    return !this.$outer.capturedSkolems().contains(p);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })).foreach(new Serializable(this){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ AsSeenFromMap $outer;

                public final void apply(Symbols.Symbol p) {
                    this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().debuglog((Function0<String>)((Object)new Serializable(this, p){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ AsSeenFromMap$$anonfun$captureSkolems$2 $outer;
                        private final Symbols.Symbol p$1;

                        public final String apply() {
                            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Captured ", " seen from ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.p$1, this.$outer.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix}));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.p$1 = p$1;
                        }
                    }));
                    this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems_$eq(this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems().$colon$colon(p));
                }

                public /* synthetic */ AsSeenFromMap scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }

        private Types.Type correspondingTypeArgument(Types.Type lhs, Types.Type rhs) {
            block5: {
                block6: {
                    Symbols.Symbol rhsSym;
                    Symbols.Symbol lhsSym;
                    block9: {
                        Types.Type type;
                        block8: {
                            int argIndex;
                            List<Types.Type> rhsArgs;
                            List<Types.Type> lhsArgs;
                            block7: {
                                if (!(lhs instanceof Types.TypeRef)) break block5;
                                Types.TypeRef typeRef = (Types.TypeRef)lhs;
                                Tuple2<Symbols.Symbol, List<Types.Type>> tuple2 = new Tuple2<Symbols.Symbol, List<Types.Type>>(typeRef.sym(), typeRef.args());
                                lhsSym = tuple2._1();
                                lhsArgs = tuple2._2();
                                if (!(rhs instanceof Types.TypeRef)) break block6;
                                Types.TypeRef typeRef2 = (Types.TypeRef)rhs;
                                Tuple2<Symbols.Symbol, List<Types.Type>> tuple22 = new Tuple2<Symbols.Symbol, List<Types.Type>>(typeRef2.sym(), typeRef2.args());
                                rhsSym = tuple22._1();
                                rhsArgs = tuple22._2();
                                Symbols.Symbol symbol = lhsSym.owner();
                                boolean bl = !(symbol != null ? !symbol.equals(rhsSym) : rhsSym != null);
                                Predef$ predef$ = Predef$.MODULE$;
                                if (!bl) {
                                    throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a type parameter of ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{lhsSym, rhsSym}))).toString());
                                }
                                argIndex = rhsSym.typeParams().indexWhere((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, lhsSym){
                                    public static final long serialVersionUID = 0L;
                                    private final Symbols.Symbol lhsSym$1;

                                    public final boolean apply(Symbols.Symbol x$9) {
                                        Names.Name name = this.lhsSym$1.name();
                                        Names.Name name2 = x$9.name();
                                        return !(name != null ? !name.equals(name2) : name2 != null);
                                    }
                                    {
                                        this.lhsSym$1 = lhsSym$1;
                                    }
                                }));
                                if (argIndex >= 0 || !rhs.parents().exists(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().typeIsErroneous())) break block7;
                                type = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().ErrorType();
                                break block8;
                            }
                            if (!rhsArgs.isDefinedAt(argIndex)) break block9;
                            Types.Type targ = rhsArgs.apply(argIndex);
                            Types.Type result2 = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().appliedType(targ, lhsArgs.mapConserve(this));
                            if (!rhsSym.typeParams().contains(lhsSym)) {
                                this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().devWarning((Function0<String>)((Object)new Serializable(this, lhsSym, rhsSym, result2){
                                    public static final long serialVersionUID = 0L;
                                    private final /* synthetic */ AsSeenFromMap $outer;
                                    private final Symbols.Symbol lhsSym$1;
                                    private final Symbols.Symbol rhsSym$1;
                                    private final Types.Type result$1;

                                    public final String apply() {
                                        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Inconsistent tparam/owner views: had to fall back on names\\n", "\\n", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$msg$2(this.lhsSym$1, this.rhsSym$1, this.result$1), this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$explain$1(this.lhsSym$1, this.rhsSym$1)}));
                                    }
                                    {
                                        if ($outer == null) {
                                            throw null;
                                        }
                                        this.$outer = $outer;
                                        this.lhsSym$1 = lhsSym$1;
                                        this.rhsSym$1 = rhsSym$1;
                                        this.result$1 = result$1;
                                    }
                                }));
                            }
                            type = result2;
                        }
                        return type;
                    }
                    throw this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().abort(new StringBuilder().append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Something is wrong: cannot find ", " in applied type ", "\\n"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{lhs, rhs}))).append((Object)this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$explain$1(lhsSym, rhsSym)).toString());
                }
                throw new MatchError(rhs);
            }
            throw new MatchError(lhs);
        }

        private Types.Type classParameterAsSeen(Types.Type classParam) {
            if (classParam instanceof Types.TypeRef) {
                Types.TypeRef typeRef = (Types.TypeRef)classParam;
                Symbols.Symbol symbol = typeRef.sym();
                return this.loop$3(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, classParam, symbol);
            }
            throw new MatchError(classParam);
        }

        public boolean scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(Types.Type pre, Symbols.Symbol clazz, Symbols.Symbol candidate) {
            Symbols.Symbol symbol = clazz;
            return !(symbol != null ? !symbol.equals(candidate) : candidate != null) && pre.widen().typeSymbol().isSubClass(clazz);
        }

        private TypeMaps$AsSeenFromMap$annotationArgRewriter$ annotationArgRewriter() {
            return this.annotationArgRewriter$module == null ? this.annotationArgRewriter$lzycompute() : this.annotationArgRewriter$module;
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree tree, Function0<Nothing$> giveup) {
            boolean saved;
            Trees.Tree tree2;
            if (this.isStablePrefix()) {
                tree2 = this.annotationArgRewriter().transform(tree);
            } else {
                saved = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation;
                this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = false;
                tree2 = this.annotationArgRewriter().transform(tree);
            }
            return tree2;
            finally {
                if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation) {
                    giveup.apply();
                } else {
                    this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = saved;
                }
            }
        }

        private Types.Type thisTypeAsSeen(Types.ThisType tp) {
            return this.loop$4(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, tp);
        }

        private Types.Type singleTypeAsSeen(Types.SingleType tp) {
            if (tp != null) {
                Tuple2<Types.Type, Symbols.Symbol> tuple2 = new Tuple2<Types.Type, Symbols.Symbol>(tp.pre(), tp.sym());
                Types.Type pre = tuple2._1();
                Symbols.Symbol sym = tuple2._2();
                Types.Type pre1 = this.apply(pre);
                return pre1 == pre ? tp : (pre1.isStable() ? this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().singleType(pre1, sym) : pre1.memberType(sym).resultType());
            }
            throw new MatchError(tp);
        }

        @Override
        public String toString() {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"AsSeenFromMap(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass}));
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer() {
            return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
        }

        @Override
        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer() {
            return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
        }

        private final boolean loop$2(Symbols.Symbol encl, Symbols.Symbol base$1) {
            boolean bl;
            block2: {
                while (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().isPossiblePrefix(encl)) {
                    if (encl.isSubClass(base$1)) {
                        bl = true;
                        break block2;
                    }
                    encl = encl.owner().enclClass();
                }
                bl = false;
            }
            return bl;
        }

        public final String scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(Symbols.Symbol s2) {
            return new StringBuilder().append((Object)s2.nameString()).append((Object)" in ").append((Object)s2.owner().nameString()).toString();
        }

        public final String scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$explain$1(Symbols.Symbol lhsSym$1, Symbols.Symbol rhsSym$1) {
            return ((StripMarginInterpolator)this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().StringContextStripMarginOps().apply(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"|   sought  ", "\n               | classSym  ", "\n               |  tparams  ", "\n               |"})))).sm(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(lhsSym$1), this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(rhsSym$1), ((TraversableOnce)rhsSym$1.typeParams().map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ AsSeenFromMap $outer;

                public final String apply(Symbols.Symbol s2) {
                    return this.$outer.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(s2);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom())).mkString(", ")}));
        }

        public final String scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$msg$2(Symbols.Symbol lhsSym$1, Symbols.Symbol rhsSym$1, Types.Type result$1) {
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Created ", ", though could not find ", " among tparams of ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{result$1, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(lhsSym$1), this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$own_s$1(rhsSym$1)}));
        }

        private final Types.Type nextBase$1(Types.Type pre$1, Symbols.Symbol clazz$2) {
            return pre$1.baseType(clazz$2).deconst();
        }

        private final Types.Type loop$3(Types.Type pre, Symbols.Symbol clazz, Types.Type classParam$1, Symbols.Symbol tparam$1) {
            while (true) {
                block8: {
                    Types.Type type;
                    block9: {
                        Types.Type type2;
                        block7: {
                            block6: {
                                if (!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().skipPrefixOf(pre, clazz)) break block6;
                                type2 = this.mapOver(classParam$1);
                                break block7;
                            }
                            if (!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(pre, clazz, tparam$1.owner())) break block8;
                            type = this.nextBase$1(pre, clazz);
                            if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().NoType().equals(type)) {
                                clazz = clazz.owner();
                                pre = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().NoType();
                                continue;
                            }
                            if (!(type instanceof Types.TypeRef)) break block9;
                            Types.TypeRef typeRef = (Types.TypeRef)type;
                            type2 = this.correspondingTypeArgument(classParam$1, typeRef);
                        }
                        return type2;
                    }
                    if (type instanceof Types.ExistentialType) {
                        Types.ExistentialType existentialType = (Types.ExistentialType)type;
                        this.captureSkolems(existentialType.quantified());
                        pre = existentialType.underlying();
                        continue;
                    }
                    throw this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().abort(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " in ", " cannot be instantiated from ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tparam$1, tparam$1.owner(), this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix.widen()})));
                }
                Types.Type type = this.nextBase$1(pre, clazz).prefix();
                clazz = clazz.owner();
                pre = type;
            }
        }

        private final Types.Type loop$4(Types.Type pre, Symbols.Symbol clazz, Types.ThisType tp$2) {
            while (true) {
                block8: {
                    Types.Type type;
                    block7: {
                        Types.Type type2;
                        block6: {
                            if (pre instanceof Types.SuperType) {
                                Types.SuperType superType = (Types.SuperType)pre;
                                type2 = superType.thistpe();
                            } else {
                                type2 = pre;
                            }
                            if (!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().skipPrefixOf(pre, clazz)) break block6;
                            type = this.mapOver(tp$2);
                            break block7;
                        }
                        if (!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(pre, clazz, tp$2.sym())) break block8;
                        type = type2.isStable() ? type2 : this.captureThis(type2, clazz);
                    }
                    return type;
                }
                Types.Type type = pre.baseType(clazz).prefix();
                clazz = clazz.owner();
                pre = type;
            }
        }

        public AsSeenFromMap(SymbolTable $outer, Types.Type seenFromPrefix, Symbols.Symbol seenFromClass) {
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix = seenFromPrefix;
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass = seenFromClass;
            super($outer);
            TypeMaps$AnnotationFilter$class.$init$(this);
            TypeMaps$KeepOnlyTypeConstraints$class.$init$(this);
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$_capturedSkolems = Nil$.MODULE$;
            this._capturedParams = Nil$.MODULE$;
            this.isStablePrefix = seenFromPrefix.isStable();
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = false;
        }
    }

    public abstract class TypeTraverser
    extends TypeMap {
        public abstract void traverse(Types.Type var1);

        @Override
        public Types.Type apply(Types.Type tp) {
            this.traverse(tp);
            return tp;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeTraverser$$$outer() {
            return this.$outer;
        }

        public TypeTraverser(SymbolTable $outer) {
            super($outer);
        }
    }

    public abstract class TypeCollector<T>
    extends TypeTraverser {
        private final T initial;
        private T result;

        public T result() {
            return this.result;
        }

        public void result_$eq(T x$1) {
            this.result = x$1;
        }

        public T collect(Types.Type tp) {
            this.result_$eq(this.initial);
            this.traverse(tp);
            return this.result();
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeCollector$$$outer() {
            return this.$outer;
        }

        public TypeCollector(SymbolTable $outer, T initial) {
            this.initial = initial;
            super($outer);
        }
    }

    public interface AnnotationFilter {
        public /* synthetic */ AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(AnnotationInfos.AnnotationInfo var1);

        public boolean keepAnnotation(AnnotationInfos.AnnotationInfo var1);

        public AnnotationInfos.AnnotationInfo mapOver(AnnotationInfos.AnnotationInfo var1);

        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer();
    }

    public class SubstWildcardMap
    extends TypeMap {
        private final List<Symbols.Symbol> from;

        @Override
        public Types.Type apply(Types.Type tp) {
            Types.Type type;
            try {
                Types.TypeRef typeRef;
                Types.Type type2 = tp instanceof Types.TypeRef && this.from.contains((typeRef = (Types.TypeRef)tp).sym()) ? new Types.BoundedWildcardType(this.scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer(), typeRef.sym().info().bounds()) : this.mapOver(tp);
                type = type2;
            }
            catch (Types.MalformedType malformedType) {
                type = this.scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer().WildcardType();
            }
            return type;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer() {
            return this.$outer;
        }

        public SubstWildcardMap(SymbolTable $outer, List<Symbols.Symbol> from2) {
            this.from = from2;
            super($outer);
        }
    }

    public class ContainsCollector
    extends TypeCollector<Object> {
        public final Symbols.Symbol scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$sym;

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void traverse(Types.Type tp) {
            if (BoxesRunTime.unboxToBoolean(this.result())) return;
            if (tp instanceof Types.ExistentialType) {
                this.mapOver(tp);
                return;
            }
            Types.Type type = tp.normalize();
            if (type instanceof Types.TypeRef) {
                Types.TypeRef typeRef = (Types.TypeRef)type;
                Symbols.Symbol symbol = this.scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$sym;
                Symbols.Symbol symbol2 = typeRef.sym();
                if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                    this.result_$eq(BoxesRunTime.boxToBoolean(true));
                    return;
                }
            }
            if (type instanceof Types.SingleType) {
                Types.SingleType singleType = (Types.SingleType)type;
                Symbols.Symbol symbol = this.scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$sym;
                Symbols.Symbol symbol3 = singleType.sym();
                if (!(symbol != null ? !symbol.equals(symbol3) : symbol3 != null)) {
                    this.result_$eq(BoxesRunTime.boxToBoolean(true));
                    return;
                }
            }
            this.mapOver(tp);
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree arg) {
            arg.foreach((Function1<Trees.Tree, BoxedUnit>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ContainsCollector $outer;

                public final void apply(Trees.Tree t) {
                    this.$outer.traverse(t.tpe());
                    Symbols.Symbol symbol = t.symbol();
                    Symbols.Symbol symbol2 = this.$outer.scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$sym;
                    if (!(symbol != null ? !symbol.equals(symbol2) : symbol2 != null)) {
                        this.$outer.result_$eq(BoxesRunTime.boxToBoolean(true));
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            return arg;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$$outer() {
            return this.$outer;
        }

        public ContainsCollector(SymbolTable $outer, Symbols.Symbol sym) {
            this.scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$sym = sym;
            super($outer, BoxesRunTime.boxToBoolean(false));
        }
    }

    public class FindTypeCollector
    extends TypeCollector<Option<Types.Type>> {
        private final Function1<Types.Type, Object> p;

        @Override
        public void traverse(Types.Type tp) {
            if (((Option)this.result()).isEmpty()) {
                if (BoxesRunTime.unboxToBoolean(this.p.apply(tp))) {
                    this.result_$eq(new Some<Types.Type>(tp));
                }
                this.mapOver(tp);
            }
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$FindTypeCollector$$$outer() {
            return this.$outer;
        }

        public FindTypeCollector(SymbolTable $outer, Function1<Types.Type, Object> p) {
            this.p = p;
            super($outer, None$.MODULE$);
        }
    }

    public class FilterTypeCollector
    extends TypeCollector<List<Types.Type>> {
        private final Function1<Types.Type, Object> p;

        @Override
        public List<Types.Type> collect(Types.Type tp) {
            return ((List)super.collect(tp)).reverse();
        }

        @Override
        public void traverse(Types.Type tp) {
            if (BoxesRunTime.unboxToBoolean(this.p.apply(tp))) {
                this.result_$eq(((List)this.result()).$colon$colon(tp));
            }
            this.mapOver(tp);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$FilterTypeCollector$$$outer() {
            return this.$outer;
        }

        public FilterTypeCollector(SymbolTable $outer, Function1<Types.Type, Object> p) {
            this.p = p;
            super($outer, Nil$.MODULE$);
        }
    }

    public class CollectTypeCollector<T>
    extends TypeCollector<List<T>> {
        private final PartialFunction<Types.Type, T> pf;

        @Override
        public List<T> collect(Types.Type tp) {
            return ((List)super.collect(tp)).reverse();
        }

        @Override
        public void traverse(Types.Type tp) {
            if (this.pf.isDefinedAt(tp)) {
                this.result_$eq(((List)this.result()).$colon$colon(this.pf.apply(tp)));
            }
            this.mapOver(tp);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$CollectTypeCollector$$$outer() {
            return this.$outer;
        }

        public CollectTypeCollector(SymbolTable $outer, PartialFunction<Types.Type, T> pf) {
            this.pf = pf;
            super($outer, Nil$.MODULE$);
        }
    }

    public class ForEachTypeTraverser
    extends TypeTraverser {
        private final Function1<Types.Type, BoxedUnit> f;

        @Override
        public void traverse(Types.Type tp) {
            this.f.apply(tp);
            this.mapOver(tp);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$ForEachTypeTraverser$$$outer() {
            return this.$outer;
        }

        public ForEachTypeTraverser(SymbolTable $outer, Function1<Types.Type, BoxedUnit> f) {
            this.f = f;
            super($outer);
        }
    }

    public interface KeepOnlyTypeConstraints
    extends AnnotationFilter {
        @Override
        public boolean keepAnnotation(AnnotationInfos.AnnotationInfo var1);

        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer();
    }

    public abstract class TypeTraverserWithResult<T>
    extends TypeTraverser {
        public abstract T result();

        public abstract void clear();

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeTraverserWithResult$$$outer() {
            return this.$outer;
        }

        public TypeTraverserWithResult(SymbolTable $outer) {
            super($outer);
        }
    }

    public class InstantiateDependentMap
    extends TypeMap
    implements KeepOnlyTypeConstraints {
        public final List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params;
        private final IndexedSeq<Types.Type> scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals;
        private final Symbols.Symbol[] existentials;
        private volatile TypeMaps$InstantiateDependentMap$StableArgTp$ StableArgTp$module;
        private volatile TypeMaps$InstantiateDependentMap$UnstableArgTp$ UnstableArgTp$module;
        private volatile TypeMaps$InstantiateDependentMap$StabilizedArgTp$ StabilizedArgTp$module;

        private TypeMaps$InstantiateDependentMap$StableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp$lzycompute() {
            InstantiateDependentMap instantiateDependentMap = this;
            synchronized (instantiateDependentMap) {
                if (this.StableArgTp$module == null) {
                    this.StableArgTp$module = new TypeMaps$InstantiateDependentMap$StableArgTp$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StableArgTp$module;
            }
        }

        private TypeMaps$InstantiateDependentMap$UnstableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp$lzycompute() {
            InstantiateDependentMap instantiateDependentMap = this;
            synchronized (instantiateDependentMap) {
                if (this.UnstableArgTp$module == null) {
                    this.UnstableArgTp$module = new TypeMaps$InstantiateDependentMap$UnstableArgTp$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnstableArgTp$module;
            }
        }

        private TypeMaps$InstantiateDependentMap$StabilizedArgTp$ StabilizedArgTp$lzycompute() {
            InstantiateDependentMap instantiateDependentMap = this;
            synchronized (instantiateDependentMap) {
                if (this.StabilizedArgTp$module == null) {
                    this.StabilizedArgTp$module = new TypeMaps$InstantiateDependentMap$StabilizedArgTp$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StabilizedArgTp$module;
            }
        }

        private TypeMaps$InstantiateDependentMap$treeTrans$2$ treeTrans$1$lzycompute(VolatileObjectRef x$1) {
            InstantiateDependentMap instantiateDependentMap = this;
            synchronized (instantiateDependentMap) {
                if (x$1.elem == null) {
                    x$1.elem = new TypeMaps$InstantiateDependentMap$treeTrans$2$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return (TypeMaps$InstantiateDependentMap$treeTrans$2$)x$1.elem;
            }
        }

        @Override
        public boolean keepAnnotation(AnnotationInfos.AnnotationInfo annot) {
            return TypeMaps$KeepOnlyTypeConstraints$class.keepAnnotation(this, annot);
        }

        @Override
        public /* synthetic */ AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(AnnotationInfos.AnnotationInfo annot) {
            return super.mapOver(annot);
        }

        @Override
        public AnnotationInfos.AnnotationInfo mapOver(AnnotationInfos.AnnotationInfo annot) {
            return TypeMaps$AnnotationFilter$class.mapOver(this, annot);
        }

        public IndexedSeq<Types.Type> scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals() {
            return this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals;
        }

        private Symbols.Symbol[] existentials() {
            return this.existentials;
        }

        public List<Symbols.Symbol> existentialsNeeded() {
            return Predef$.MODULE$.refArrayOps((Object[])this.existentials()).iterator().filter((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbols.Symbol x$13) {
                    return x$13 != null;
                }
            })).toList();
        }

        public TypeMaps$InstantiateDependentMap$StableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp() {
            return this.StableArgTp$module == null ? this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp$lzycompute() : this.StableArgTp$module;
        }

        public Symbols.Symbol scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$existentialFor(int pid) {
            if (this.existentials()[pid] == null) {
                Symbols.Symbol param2 = this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params.apply(pid);
                this.existentials()[pid] = param2.owner().newExistential((Names.TypeName)param2.name().toTypeName().append(this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().nme().SINGLETON_SUFFIX()), param2.pos(), param2.flags()).setInfo(this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().singletonBounds((Types.Type)this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals().apply(pid)));
            }
            return this.existentials()[pid];
        }

        public TypeMaps$InstantiateDependentMap$UnstableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp() {
            return this.UnstableArgTp$module == null ? this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp$lzycompute() : this.UnstableArgTp$module;
        }

        private TypeMaps$InstantiateDependentMap$StabilizedArgTp$ StabilizedArgTp() {
            return this.StabilizedArgTp$module == null ? this.StabilizedArgTp$lzycompute() : this.StabilizedArgTp$module;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public Types.Type apply(Types.Type tp) {
            if (!(tp instanceof Types.SingleType)) return this.mapOver(tp);
            Types.SingleType singleType = (Types.SingleType)tp;
            if (!this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().NoPrefix().equals(singleType.pre())) return this.mapOver(tp);
            Option<Types.Type> option = this.StabilizedArgTp().unapply(singleType.sym());
            if (option.isEmpty()) return this.mapOver(tp);
            return option.get();
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree arg, Function0<Nothing$> giveup) {
            VolatileObjectRef<Object> treeTrans$module = VolatileObjectRef.zero();
            return this.treeTrans$1(treeTrans$module).transform(arg);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer() {
            return this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer();
        }

        @Override
        public /* synthetic */ TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer() {
            return this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer();
        }

        private final TypeMaps$InstantiateDependentMap$treeTrans$2$ treeTrans$1(VolatileObjectRef treeTrans$module$1) {
            return treeTrans$module$1.elem == null ? this.treeTrans$1$lzycompute(treeTrans$module$1) : (TypeMaps$InstantiateDependentMap$treeTrans$2$)treeTrans$module$1.elem;
        }

        public InstantiateDependentMap(SymbolTable $outer, List<Symbols.Symbol> params2, List<Types.Type> actuals0) {
            this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params = params2;
            super($outer);
            TypeMaps$AnnotationFilter$class.$init$(this);
            TypeMaps$KeepOnlyTypeConstraints$class.$init$(this);
            this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals = actuals0.toIndexedSeq();
            this.existentials = new Symbols.Symbol[this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals().size()];
        }
    }

    public class ExistentialExtrapolation
    extends TypeMap {
        public final List<Symbols.Symbol> scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams;
        private final HashMap<Symbols.Symbol, Object> scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount;

        public HashMap<Symbols.Symbol, Object> scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount() {
            return this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount;
        }

        public void scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$countOccs(Types.Type tp) {
            tp.foreach((Function1<Types.Type, BoxedUnit>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ExistentialExtrapolation $outer;

                public final void apply(Types.Type x0$1) {
                    block1: {
                        if (!(x0$1 instanceof Types.TypeRef)) break block1;
                        Types.TypeRef typeRef = (Types.TypeRef)x0$1;
                        if (this.$outer.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams.contains(typeRef.sym())) {
                            this.$outer.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount().update(typeRef.sym(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.$outer.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount().apply(typeRef.sym())) + 1));
                        }
                    }
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Types.Type extrapolate(Types.Type tpe) {
            List list2 = this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams;
            while (!((AbstractIterable)list2).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount().update(symbol, BoxesRunTime.boxToInteger(0));
                list2 = (List)list2.tail();
            }
            tpe.foreach((Function1<Types.Type, BoxedUnit>)((Object)new /* invalid duplicate definition of identical inner class */));
            List list3 = this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams;
            while (!((AbstractIterable)list3).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list3).head();
                Types.Type type = symbol.info();
                ExistentialExtrapolation existentialExtrapolation = this;
                type.foreach((Function1<Types.Type, BoxedUnit>)((Object)new /* invalid duplicate definition of identical inner class */));
                list3 = (List)list3.tail();
            }
            return this.apply(tpe);
        }

        @Override
        public Types.Type apply(Types.Type tp) {
            Types.Type type;
            Types.Type tp1 = this.mapOver(tp);
            if (Variance$.MODULE$.isInvariant$extension(this.variance())) {
                type = tp1;
            } else {
                Types.Type type2;
                Types.TypeRef typeRef;
                if (tp1 instanceof Types.TypeRef && this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams.contains((typeRef = (Types.TypeRef)tp1).sym())) {
                    Types.Type type3;
                    Types.Type repl = Variance$.MODULE$.isPositive$extension(this.variance()) ? this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer().dropSingletonType().apply(tp1.bounds().hi()) : tp1.bounds().lo();
                    int count2 = BoxesRunTime.unboxToInt(this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount().apply(typeRef.sym()));
                    boolean containsTypeParam = this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams.exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, repl){
                        public static final long serialVersionUID = 0L;
                        private final Types.Type repl$1;

                        public final boolean apply(Symbols.Symbol x$5) {
                            return this.repl$1.contains(x$5);
                        }
                        {
                            this.repl$1 = repl$1;
                        }
                    }));
                    if (!repl.typeSymbol().isBottomClass() && count2 == 1 && !containsTypeParam) {
                        Serializable serializable = new Serializable(this, tp1){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ ExistentialExtrapolation $outer;
                            private final Types.Type tp1$1;

                            public final String apply() {
                                return this.$outer.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$msg$1(this.tp1$1);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.tp1$1 = tp1$1;
                            }
                        };
                        SymbolTable symbolTable = this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer();
                        symbolTable.debuglog((Function0<String>)((Object)new Serializable(symbolTable, (Function0)((Object)serializable), repl){
                            public static final long serialVersionUID = 0L;
                            private final Function0 msg$3;
                            private final Object result$2;

                            public final String apply() {
                                return new StringBuilder().append((Object)((String)this.msg$3.apply())).append((Object)": ").append(this.result$2).toString();
                            }
                            {
                                this.msg$3 = msg$3;
                                this.result$2 = result$2;
                            }
                        }));
                        type3 = repl;
                    } else {
                        type3 = tp1;
                    }
                    type2 = type3;
                } else {
                    type2 = tp1;
                }
                type = type2;
            }
            return type;
        }

        @Override
        public Types.Type mapOver(Types.Type tp) {
            Types.Type pre1;
            Types.SingleType singleType;
            Types.Type type = tp instanceof Types.SingleType ? ((singleType = (Types.SingleType)tp).sym().isPackageClass() ? tp : ((pre1 = this.apply(singleType.pre())) != singleType.pre() && pre1.isStable() ? this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer().singleType(pre1, singleType.sym()) : tp)) : super.mapOver(tp);
            return type;
        }

        @Override
        public Trees.Tree mapOver(Trees.Tree tree) {
            Trees.Tree tree2 = tree instanceof Trees.Ident && tree.tpe().isStable() ? tree : super.mapOver(tree);
            return tree2;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer() {
            return this.$outer;
        }

        public final String scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$msg$1(Types.Type tp1$1) {
            String word = Variance$.MODULE$.isPositive$extension(this.variance()) ? "upper" : "lower";
            return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Widened lone occurrence of ", " inside existential to ", " bound"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tp1$1, word}));
        }

        public ExistentialExtrapolation(SymbolTable $outer, List<Symbols.Symbol> tparams2) {
            this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$tparams = tparams2;
            super($outer, true);
            this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$occurCount = (HashMap)HashMap$.MODULE$.apply(Nil$.MODULE$);
        }
    }
}

