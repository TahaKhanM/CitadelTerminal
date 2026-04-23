/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.SoftReference;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import scala.Array$;
import scala.DelayedInit;
import scala.Dynamic;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Predef$any2stringadd$;
import scala.Product;
import scala.SerialVersionUID;
import scala.Serializable;
import scala.Some;
import scala.Some$;
import scala.Specializable;
import scala.StringContext;
import scala.Symbol;
import scala.Symbol$;
import scala.Tuple2;
import scala.UninitializedFieldError;
import scala.annotation.Annotation;
import scala.annotation.ClassfileAnnotation;
import scala.annotation.StaticAnnotation;
import scala.annotation.TypeConstraint;
import scala.annotation.bridge;
import scala.annotation.elidable;
import scala.annotation.implicitNotFound;
import scala.annotation.meta.beanGetter;
import scala.annotation.meta.beanSetter;
import scala.annotation.meta.companionClass;
import scala.annotation.meta.companionMethod;
import scala.annotation.meta.companionObject;
import scala.annotation.meta.field;
import scala.annotation.meta.getter;
import scala.annotation.meta.languageFeature;
import scala.annotation.meta.param;
import scala.annotation.meta.setter;
import scala.annotation.migration;
import scala.annotation.strictfp;
import scala.annotation.switch;
import scala.annotation.tailrec;
import scala.annotation.unchecked.uncheckedStable;
import scala.annotation.unchecked.uncheckedVariance;
import scala.annotation.unspecialized;
import scala.annotation.varargs;
import scala.beans.BeanProperty;
import scala.beans.BooleanBeanProperty;
import scala.collection.AbstractIterable;
import scala.collection.GenTraversable;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.LinearSeqLike;
import scala.collection.LinearSeqOptimized;
import scala.collection.MapLike;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SetLike;
import scala.collection.Traversable;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.StringBuilder;
import scala.deprecated;
import scala.deprecatedInheritance;
import scala.deprecatedName;
import scala.deprecatedOverriding;
import scala.inline;
import scala.math.ScalaNumber;
import scala.native;
import scala.noinline;
import scala.reflect.ClassManifestFactory$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.Manifest;
import scala.reflect.ManifestFactory$;
import scala.reflect.NoManifest$;
import scala.reflect.OptManifest;
import scala.reflect.ScalaLongSignature;
import scala.reflect.ScalaSignature;
import scala.reflect.api.StandardDefinitions;
import scala.reflect.api.StandardDefinitions$DefinitionsApi$class;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions$DefinitionsClass$MacroContextType$;
import scala.reflect.internal.Definitions$DefinitionsClass$NothingClass$;
import scala.reflect.internal.Definitions$DefinitionsClass$NullClass$;
import scala.reflect.internal.Definitions$DefinitionsClass$RunDefinitions$ExprClassOf$;
import scala.reflect.internal.Definitions$DefinitionsClass$RunDefinitions$SubtreeType$;
import scala.reflect.internal.Definitions$DefinitionsClass$RunDefinitions$TreeType$;
import scala.reflect.internal.Definitions$DefinitionsClass$VarArityClass$;
import scala.reflect.internal.Definitions$ValueClassDefinitions$class;
import scala.reflect.internal.Definitions$class;
import scala.reflect.internal.Definitions$definitions$;
import scala.reflect.internal.FatalError;
import scala.reflect.internal.HasFlags;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.Names$TermName$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.internal.macroImpl;
import scala.reflect.package$;
import scala.remote;
import scala.runtime.AbstractPartialFunction;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.EmptyMethodCache;
import scala.runtime.MethodCache;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.StringAdd;
import scala.runtime.TraitSetter;
import scala.runtime.VolatileObjectRef;
import scala.specialized;
import scala.throws;
import scala.transient;
import scala.unchecked;
import scala.volatile;

@ScalaSignature(bytes="\u0006\u0001Q\u0015h!C\u0001\u0003!\u0003\r\t!\u0003Kp\u0005-!UMZ5oSRLwN\\:\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u000b\u001dA\u00111\u0002D\u0007\u0002\r%\u0011QB\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=\u0011R\"\u0001\t\u000b\u0005E!\u0011aA1qS&\u00111\u0003\u0005\u0002\u0014'R\fg\u000eZ1sI\u0012+g-\u001b8ji&|gn\u001d\u0005\u0006+\u0001!\tAF\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003]\u0001\"a\u0003\r\n\u0005e1!\u0001B+oSR<Qa\u0007\u0001\t\u0002q\t1\u0002Z3gS:LG/[8ogB\u0011QDH\u0007\u0002\u0001\u0019)q\u0004\u0001E\u0001A\tYA-\u001a4j]&$\u0018n\u001c8t'\tq\u0012\u0005\u0005\u0002\u001eE\u0019)1\u0005AA\u0001I\t\u0001B)\u001a4j]&$\u0018n\u001c8t\u00072\f7o]\n\u0005E))\u0003\u0006\u0005\u0002\u001eM%\u0011qE\u0005\u0002\u000f\t\u00164\u0017N\\5uS>t7/\u00119j!\ti\u0012F\u0002\u0005+\u0001A\u0005\u0019\u0011A\u0016\"\u0005U1\u0016\r\\;f\u00072\f7o\u001d#fM&t\u0017\u000e^5p]N\u001c\"!\u000b\u0006\t\u000bUIC\u0011\u0001\f\t\u000f9J#\u0019!C\u0005_\u0005aa.Y7f)><V-[4iiV\t\u0001\u0007\u0005\u00032majT\"\u0001\u001a\u000b\u0005M\"\u0014!C5n[V$\u0018M\u00197f\u0015\t)d!\u0001\u0006d_2dWm\u0019;j_:L!a\u000e\u001a\u0003\u00075\u000b\u0007\u000f\u0005\u0002\u001es%\u0011!h\u000f\u0002\u0005\u001d\u0006lW-\u0003\u0002=\u0005\t)a*Y7fgB\u00111BP\u0005\u0003\u007f\u0019\u00111!\u00138u\u0011\u0019\t\u0015\u0006)A\u0005a\u0005ia.Y7f)><V-[4ii\u0002BqaQ\u0015C\u0002\u0013%A)A\u0005oC6,Gk\u001c+bOV\tQ\t\u0005\u00032ma2\u0005CA\u0006H\u0013\tAeA\u0001\u0003DQ\u0006\u0014\bB\u0002&*A\u0003%Q)\u0001\u0006oC6,Gk\u001c+bO\u0002BQ\u0001T\u0015\u0005\n5\u000b\u0001C^1mk\u0016\u001cE.Y:t'fl'm\u001c7\u0015\u00059\u001b\u0006CA\u000fP\u0013\t\u0001\u0016KA\u0006DY\u0006\u001c8oU=nE>d\u0017B\u0001*\u0003\u0005\u001d\u0019\u00160\u001c2pYNDQ\u0001V&A\u0002U\u000bAA\\1nKB\u0011QDV\u0005\u0003/n\u0012\u0001\u0002V=qK:\u000bW.\u001a\u0005\u00063&\"IAW\u0001\u000bgfl'm\u001c7t\u001b\u0006\u0004XCA.h)\ra\u0006/\u001f\t\u0005;\u0002\u0014WM\u0004\u0002\f=&\u0011qLB\u0001\u0007!J,G-\u001a4\n\u0005]\n'BA0\u0007!\ti2-\u0003\u0002e#\n11+_7c_2\u0004\"AZ4\r\u0001\u0011)\u0001\u000e\u0017b\u0001S\n\tA+\u0005\u0002k[B\u00111b[\u0005\u0003Y\u001a\u0011qAT8uQ&tw\r\u0005\u0002\f]&\u0011qN\u0002\u0002\u0004\u0003:L\b\"B9Y\u0001\u0004\u0011\u0018\u0001B:z[N\u00042a\u001d<c\u001d\tYA/\u0003\u0002v\r\u00059\u0001/Y2lC\u001e,\u0017BA<y\u0005\u0011a\u0015n\u001d;\u000b\u0005U4\u0001\"\u0002>Y\u0001\u0004Y\u0018!\u00014\u0011\t-a\b(Z\u0005\u0003{\u001a\u0011\u0011BR;oGRLwN\\\u0019\t\r}LC\u0011BA\u0001\u00039\u0019\u00180\u001c2pYNl\u0015\r\u001d$jYR,B!a\u0001\u0002\nQA\u0011QAA\u0006\u0003\u001b\tI\u0002E\u0003^A\n\f9\u0001E\u0002g\u0003\u0013!Q\u0001\u001b@C\u0002%DQ!\u001d@A\u0002IDq!a\u0004\u007f\u0001\u0004\t\t\"A\u0001q!\u0015YA\u0010OA\n!\rY\u0011QC\u0005\u0004\u0003/1!a\u0002\"p_2,\u0017M\u001c\u0005\u0007uz\u0004\r!a\u0007\u0011\u000b-a\b(a\u0002\t\u000f\u0005}\u0011\u0006\"\u0003\u0002\"\u0005I!m\u001c=fI:\u000bW.\u001a\u000b\u0004+\u0006\r\u0002B\u0002+\u0002\u001e\u0001\u0007\u0001\b\u0003\u0006\u0002(%B)\u0019!C\u0001\u0003S\t\u0001\"\u00192ceZ$\u0016mZ\u000b\u0003\u0003W\u0001B!\r\u001cc\r\"Q\u0011qF\u0015\t\u0002\u0003\u0006K!a\u000b\u0002\u0013\u0005\u0014'M\u001d<UC\u001e\u0004\u0003BCA\u001aS!\u0015\r\u0011\"\u0001\u00026\u0005ia.^7fe&\u001cw+Z5hQR,\"!a\u000e\u0011\tu\u0003'-\u0010\u0005\u000b\u0003wI\u0003\u0012!Q!\n\u0005]\u0012A\u00048v[\u0016\u0014\u0018nY,fS\u001eDG\u000f\t\u0005\u000b\u0003\u007fI\u0003R1A\u0005\u0002\u0005\u0005\u0013a\u00032pq\u0016$Wj\u001c3vY\u0016,\"!a\u0011\u0011\u000bu\u0003'-!\u0012\u0011\u0007u\t9%C\u0002\u0002JE\u0013A\"T8ek2,7+_7c_2D!\"!\u0014*\u0011\u0003\u0005\u000b\u0015BA\"\u00031\u0011w\u000e_3e\u001b>$W\u000f\\3!\u0011)\t\t&\u000bEC\u0002\u0013\u0005\u00111K\u0001\u000bE>DX\rZ\"mCN\u001cXCAA+!\u0011i\u0006M\u0019(\t\u0015\u0005e\u0013\u0006#A!B\u0013\t)&A\u0006c_b,Gm\u00117bgN\u0004\u0003BCA/S!\u0015\r\u0011\"\u0001\u0002T\u0005A!/\u001a4DY\u0006\u001c8\u000f\u0003\u0006\u0002b%B\t\u0011)Q\u0005\u0003+\n\u0011B]3g\u00072\f7o\u001d\u0011\t\u0015\u0005\u0015\u0014\u0006#b\u0001\n\u0003\t\u0019&\u0001\tw_2\fG/\u001b7f%\u001647\t\\1tg\"Q\u0011\u0011N\u0015\t\u0002\u0003\u0006K!!\u0016\u0002#Y|G.\u0019;jY\u0016\u0014VMZ\"mCN\u001c\b\u0005C\u0004\u0002n%\"\t!a\u001c\u0002#%\u001ch*^7fe&\u001c7+\u001e2DY\u0006\u001c8\u000f\u0006\u0004\u0002\u0014\u0005E\u0014Q\u000f\u0005\b\u0003g\nY\u00071\u0001c\u0003\r\u0019XO\u0019\u0005\b\u0003o\nY\u00071\u0001c\u0003\r\u0019X\u000f\u001d\u0005\b\u0003wJC\u0011AA?\u0003MI7OT;nKJL7MV1mk\u0016\u001cE.Y:t)\u0011\t\u0019\"a \t\u000f\u0005\u0005\u0015\u0011\u0010a\u0001E\u0006\u00191/_7\t\u000f\u0005\u0015\u0015\u0006\"\u0001\u0002\b\u0006Q\u0011n]$fi\u000ec\u0017m]:\u0015\t\u0005M\u0011\u0011\u0012\u0005\b\u0003\u0003\u000b\u0019\t1\u0001c\u0011)\ti)\u000bEC\u0002\u0013\u0005\u0011qR\u0001\n+:LGo\u00117bgN,\u0012A\u0014\u0005\n\u0003'K\u0003\u0012!Q!\n9\u000b!\"\u00168ji\u000ec\u0017m]:!\u0011)\t9*\u000bEC\u0002\u0013\u0005\u0011qR\u0001\n\u0005f$Xm\u00117bgND\u0011\"a'*\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0015\tKH/Z\"mCN\u001c\b\u0005\u0003\u0006\u0002 &B)\u0019!C\u0001\u0003\u001f\u000b!b\u00155peR\u001cE.Y:t\u0011%\t\u0019+\u000bE\u0001B\u0003&a*A\u0006TQ>\u0014Ho\u00117bgN\u0004\u0003BCATS!\u0015\r\u0011\"\u0001\u0002\u0010\u0006I1\t[1s\u00072\f7o\u001d\u0005\n\u0003WK\u0003\u0012!Q!\n9\u000b!b\u00115be\u000ec\u0017m]:!\u0011)\ty+\u000bEC\u0002\u0013\u0005\u0011qR\u0001\t\u0013:$8\t\\1tg\"I\u00111W\u0015\t\u0002\u0003\u0006KAT\u0001\n\u0013:$8\t\\1tg\u0002B!\"a.*\u0011\u000b\u0007I\u0011AAH\u0003%auN\\4DY\u0006\u001c8\u000fC\u0005\u0002<&B\t\u0011)Q\u0005\u001d\u0006QAj\u001c8h\u00072\f7o\u001d\u0011\t\u0015\u0005}\u0016\u0006#b\u0001\n\u0003\ty)\u0001\u0006GY>\fGo\u00117bgND\u0011\"a1*\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0017\u0019cw.\u0019;DY\u0006\u001c8\u000f\t\u0005\u000b\u0003\u000fL\u0003R1A\u0005\u0002\u0005=\u0015a\u0003#pk\ndWm\u00117bgND\u0011\"a3*\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0019\u0011{WO\u00197f\u00072\f7o\u001d\u0011\t\u0015\u0005=\u0017\u0006#b\u0001\n\u0003\ty)\u0001\u0007C_>dW-\u00198DY\u0006\u001c8\u000fC\u0005\u0002T&B\t\u0011)Q\u0005\u001d\u0006i!i\\8mK\u0006t7\t\\1tg\u0002Bq!a6*\t\u0003\tI.A\u0006C_>dW-\u00198`C:$WCAAn!\ri\u0012Q\\\u0005\u0004\u0003?\f&A\u0003+fe6\u001c\u00160\u001c2pY\"9\u00111]\u0015\u0005\u0002\u0005e\u0017A\u0003\"p_2,\u0017M\\0pe\"9\u0011q]\u0015\u0005\u0002\u0005e\u0017a\u0003\"p_2,\u0017M\\0o_RD!\"a;*\u0011\u000b\u0007I\u0011AAw\u0003\u001d)f.\u001b;Ua\u0016,\"!a<\u0011\u0007u\t\t0\u0003\u0003\u0002t\u0006U(\u0001\u0002+za\u0016L1!a>\u0003\u0005\u0015!\u0016\u0010]3t\u0011)\tY0\u000bE\u0001B\u0003&\u0011q^\u0001\t+:LG\u000f\u00169fA!Q\u0011q`\u0015\t\u0006\u0004%\t!!<\u0002\u000f\tKH/\u001a+qK\"Q!1A\u0015\t\u0002\u0003\u0006K!a<\u0002\u0011\tKH/\u001a+qK\u0002B!Ba\u0002*\u0011\u000b\u0007I\u0011AAw\u0003!\u0019\u0006n\u001c:u)B,\u0007B\u0003B\u0006S!\u0005\t\u0015)\u0003\u0002p\u0006I1\u000b[8siR\u0003X\r\t\u0005\u000b\u0005\u001fI\u0003R1A\u0005\u0002\u00055\u0018aB\"iCJ$\u0006/\u001a\u0005\u000b\u0005'I\u0003\u0012!Q!\n\u0005=\u0018\u0001C\"iCJ$\u0006/\u001a\u0011\t\u0015\t]\u0011\u0006#b\u0001\n\u0003\ti/\u0001\u0004J]R$\u0006/\u001a\u0005\u000b\u00057I\u0003\u0012!Q!\n\u0005=\u0018aB%oiR\u0003X\r\t\u0005\u000b\u0005?I\u0003R1A\u0005\u0002\u00055\u0018a\u0002'p]\u001e$\u0006/\u001a\u0005\u000b\u0005GI\u0003\u0012!Q!\n\u0005=\u0018\u0001\u0003'p]\u001e$\u0006/\u001a\u0011\t\u0015\t\u001d\u0012\u0006#b\u0001\n\u0003\ti/\u0001\u0005GY>\fG\u000f\u00169f\u0011)\u0011Y#\u000bE\u0001B\u0003&\u0011q^\u0001\n\r2|\u0017\r\u001e+qK\u0002B!Ba\f*\u0011\u000b\u0007I\u0011AAw\u0003%!u.\u001e2mKR\u0003X\r\u0003\u0006\u00034%B\t\u0011)Q\u0005\u0003_\f!\u0002R8vE2,G\u000b]3!\u0011)\u00119$\u000bEC\u0002\u0013\u0005\u0011Q^\u0001\u000b\u0005>|G.Z1o)B,\u0007B\u0003B\u001eS!\u0005\t\u0015)\u0003\u0002p\u0006Y!i\\8mK\u0006tG\u000b]3!\u0011)\u0011y$\u000bEC\u0002\u0013\u0005!\u0011I\u0001\u0019'\u000e\fG.\u0019(v[\u0016\u0014\u0018n\u0019,bYV,7\t\\1tg\u0016\u001cXC\u0001B\"!\u0011\t$Q\t(\n\u0005]\u0014\u0004B\u0003B%S!\u0005\t\u0015)\u0003\u0003D\u0005I2kY1mC:+X.\u001a:jGZ\u000bG.^3DY\u0006\u001c8/Z:!\u0011)\u0011i%\u000bEC\u0002\u0013\u0005!\u0011I\u0001\u0018'\u000e\fG.\u0019,bYV,7\t\\1tg\u0016\u001chj\\+oSRD!B!\u0015*\u0011\u0003\u0005\u000b\u0015\u0002B\"\u0003a\u00196-\u00197b-\u0006dW/Z\"mCN\u001cXm\u001d(p+:LG\u000f\t\u0005\u000b\u0005+J\u0003R1A\u0005\u0002\t]\u0013!E*dC2\fg+\u00197vK\u000ec\u0017m]:fgV\u0011!\u0011\f\t\u0004gZt\u0005B\u0003B/S!\u0005\t\u0015)\u0003\u0003Z\u0005\u00112kY1mCZ\u000bG.^3DY\u0006\u001c8/Z:!\u0011\u001d\u0011\t'\u000bC\u0001\u0005/\n!dU2bY\u0006\u0004&/[7ji&4XMV1mk\u0016\u001cE.Y:tKNDqA!\u001a*\t\u0003\u00119'\u0001\fv]\u0012,'\u000f\\=j]\u001e|eMV1mk\u0016\u001cE.Y:t)\u0011\tyO!\u001b\t\u000f\t-$1\ra\u0001E\u0006)1\r\\1{u\"a!qN\u0015\u0003\u0002\u0003\u0005I\u0011\u0001\u0001\u0003r\u000594oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000e\n#fM&t\u0017\u000e^5p]N$CeY1uCN$(o\u001c9iS\u000e4\u0015-\u001b7ve\u0016$\u0012A\u001b\u0005\r\u0005kJ#\u0011!A\u0001\n\u0003\u0001!qO\u0001/g\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\"UMZ5oSRLwN\\:%I\rd\u0017m]:fg6\u000b\u0007/\u0006\u0003\u0003z\t}D\u0003\u0002B>\u0005\u0003\u0003R!\u00181c\u0005{\u00022A\u001aB@\t\u0019A'1\u000fb\u0001S\"9!Pa\u001dA\u0002\t\r\u0005#B\u0006}q\tu\u0004b\u0002BDE\u0011\u0005!\u0011R\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0005B\u0011B!$#\u0001\u0004%IAa$\u0002\u001b%\u001c\u0018J\\5uS\u0006d\u0017N_3e+\t\t\u0019\u0002C\u0005\u0003\u0014\n\u0002\r\u0011\"\u0003\u0003\u0016\u0006\t\u0012n]%oSRL\u0017\r\\5{K\u0012|F%Z9\u0015\u0007]\u00119\n\u0003\u0006\u0003\u001a\nE\u0015\u0011!a\u0001\u0003'\t1\u0001\u001f\u00132\u0011!\u0011iJ\tQ!\n\u0005M\u0011AD5t\u0013:LG/[1mSj,G\r\t\u0005\b\u0005C\u0013C\u0011\u0001BH\u0003aI7\u000fR3gS:LG/[8og&s\u0017\u000e^5bY&TX\r\u001a\u0005\u000b\u0005K\u0013\u0003R1A\u0005\u0002\t\u001d\u0016a\u0004&bm\u0006d\u0015M\\4QC\u000e\\\u0017mZ3\u0016\u0005\u0005\u0015\u0003B\u0003BVE!\u0005\t\u0015)\u0003\u0002F\u0005\u0001\"*\u0019<b\u0019\u0006tw\rU1dW\u0006<W\r\t\u0005\u000b\u0005_\u0013\u0003R1A\u0005\u0002\u0005=\u0015\u0001\u0006&bm\u0006d\u0015M\\4QC\u000e\\\u0017mZ3DY\u0006\u001c8\u000fC\u0005\u00034\nB\t\u0011)Q\u0005\u001d\u0006)\"*\u0019<b\u0019\u0006tw\rU1dW\u0006<Wm\u00117bgN\u0004\u0003B\u0003B\\E!\u0015\r\u0011\"\u0001\u0003(\u0006a1kY1mCB\u000b7m[1hK\"Q!1\u0018\u0012\t\u0002\u0003\u0006K!!\u0012\u0002\u001bM\u001b\u0017\r\\1QC\u000e\\\u0017mZ3!\u0011)\u0011yL\tEC\u0002\u0013\u0005\u0011qR\u0001\u0012'\u000e\fG.\u0019)bG.\fw-Z\"mCN\u001c\b\"\u0003BbE!\u0005\t\u0015)\u0003O\u0003I\u00196-\u00197b!\u0006\u001c7.Y4f\u00072\f7o\u001d\u0011\t\u0015\t\u001d'\u0005#b\u0001\n\u0003\u00119+\u0001\bSk:$\u0018.\\3QC\u000e\\\u0017mZ3\t\u0015\t-'\u0005#A!B\u0013\t)%A\bSk:$\u0018.\\3QC\u000e\\\u0017mZ3!\u0011)\u0011yM\tEC\u0002\u0013\u0005\u0011qR\u0001\u0014%VtG/[7f!\u0006\u001c7.Y4f\u00072\f7o\u001d\u0005\n\u0005'\u0014\u0003\u0012!Q!\n9\u000bACU;oi&lW\rU1dW\u0006<Wm\u00117bgN\u0004\u0003b\u0002BlE\u0011\u0005!\u0011\\\u0001\u0015U\u00064\u0018\rV=qKR{g+\u00197vK\u000ec\u0017m]:\u0015\u0007\t\u0014Y\u000e\u0003\u0005\u0003^\nU\u0007\u0019\u0001Bp\u0003\u0015QG/\u001f9fa\u0011\u0011\tO!;\u0011\u000bu\u0013\u0019Oa:\n\u0007\t\u0015\u0018MA\u0003DY\u0006\u001c8\u000fE\u0002g\u0005S$1Ba;\u0003\\\u0006\u0005\t\u0011!B\u0001S\n\u0019q\fJ\u0019\t\u000f\t=(\u0005\"\u0001\u0003r\u0006!b/\u00197vK\u000ec\u0017m]:U_*\u000bg/\u0019+za\u0016$BAa=\u0003~B\"!Q\u001fB}!\u0015i&1\u001dB|!\r1'\u0011 \u0003\f\u0005w\u0014i/!A\u0001\u0002\u000b\u0005\u0011NA\u0002`IIBq!!!\u0003n\u0002\u0007!\rC\u0004\u0004\u0002\t\"\taa\u0001\u0002+\u0019,H\u000e\\=J]&$\u0018.\u00197ju\u0016\u001c\u00160\u001c2pYR\u0019!m!\u0002\t\u000f\u0005\u0005%q a\u0001E\"91\u0011\u0002\u0012\u0005\u0002\r-\u0011a\u00054vY2L\u0018J\\5uS\u0006d\u0017N_3UsB,G\u0003BAx\u0007\u001bA\u0001ba\u0004\u0004\b\u0001\u0007\u0011q^\u0001\u0003iBDqaa\u0005#\t\u0003\u0019)\"\u0001\u000bgk2d\u00170\u00138ji&\fG.\u001b>f'\u000e|\u0007/\u001a\u000b\u0005\u0007/\u0019\t\u0003E\u0002\u001e\u00073IAaa\u0007\u0004\u001e\t)1kY8qK&\u00191q\u0004\u0002\u0003\rM\u001bw\u000e]3t\u0011!\u0019\u0019c!\u0005A\u0002\r]\u0011!B:d_B,\u0007bBB\u0014E\u0011\u00051\u0011F\u0001\u0012SN,f.\u001b<feN\fG.T3nE\u0016\u0014H\u0003BA\n\u0007WAq!!!\u0004&\u0001\u0007!\rC\u0004\u00040\t\"\ta!\r\u0002\u001d%\u001cXK\\5na>\u0014H/\u00192mKR!\u00111CB\u001a\u0011\u001d\t\ti!\fA\u0002\tDqaa\u000e#\t\u0003\u0019I$A\u000ejgVs\u0017.\u001c9peR\f'\r\\3V]2,7o\u001d*f]\u0006lW\r\u001a\u000b\u0005\u0003'\u0019Y\u0004C\u0004\u0002\u0002\u000eU\u0002\u0019\u00012\t\u000f\r}\"\u0005\"\u0001\u0004B\u0005a\u0011n]%na>\u0014H/\u00192mKR!\u00111CB\"\u0011\u001d\t\ti!\u0010A\u0002\tDqaa\u0012#\t\u0003\u0019I%\u0001\tjgR\u0013\u0018N^5bYR{\u0007\u000fV=qKR!\u00111CB&\u0011!\u0019ya!\u0012A\u0002\u0005=\bbBB(E\u0011\u00051\u0011K\u0001!Q\u0006\u001cX*\u001e7uSBdWMT8o\u00136\u0004H.[2jiB\u000b'/Y7MSN$8\u000f\u0006\u0003\u0002\u0014\rM\u0003bBB+\u0007\u001b\u0002\rAY\u0001\u0007[\u0016l'-\u001a:\t\u000f\r=#\u0005\"\u0001\u0004ZQ!\u00111CB.\u0011!\u0019ifa\u0016A\u0002\u0005=\u0018\u0001B5oM>Dqa!\u0019#\t\u0013\u0019\u0019'A\bgSb,\b/Q:B]f$&/Y5u)\u0011\tyo!\u001a\t\u0011\r\u001d4q\fa\u0001\u0003_\f1\u0001\u001e9f\u0011)\u0019YG\tEC\u0002\u0013\u0005\u0011qR\u0001\t\u0003:L8\t\\1tg\"I1q\u000e\u0012\t\u0002\u0003\u0006KAT\u0001\n\u0003:L8\t\\1tg\u0002B!ba\u001d#\u0011\u000b\u0007I\u0011AB;\u0003-\te.\u001f*fM\u000ec\u0017m]:\u0016\u0005\r]\u0004cA\u000f\u0004z%\u001911P)\u0003\u001f\u0005c\u0017.Y:UsB,7+_7c_2D!ba #\u0011\u0003\u0005\u000b\u0015BB<\u00031\te.\u001f*fM\u000ec\u0017m]:!\u0011)\u0019\u0019I\tEC\u0002\u0013\u0005\u0011qR\u0001\f\u001f\nTWm\u0019;DY\u0006\u001c8\u000fC\u0005\u0004\b\nB\t\u0011)Q\u0005\u001d\u0006aqJ\u00196fGR\u001cE.Y:tA!Q11\u0012\u0012\t\u0006\u0004%\t!!<\u0002\u0013\u0005s\u0017PU3g)B,\u0007BCBHE!\u0005\t\u0015)\u0003\u0002p\u0006Q\u0011I\\=SK\u001a$\u0006/\u001a\u0011\t\u0015\rM%\u0005#b\u0001\n\u0003\ti/\u0001\u0004B]f$\u0006/\u001a\u0005\u000b\u0007/\u0013\u0003\u0012!Q!\n\u0005=\u0018aB!osR\u0003X\r\t\u0005\u000b\u00077\u0013\u0003R1A\u0005\u0002\u00055\u0018!C!osZ\u000bG\u000e\u00169f\u0011)\u0019yJ\tE\u0001B\u0003&\u0011q^\u0001\u000b\u0003:Lh+\u00197Ua\u0016\u0004\u0003BCBRE!\u0015\r\u0011\"\u0001\u0002n\u0006a!i\u001c=fIVs\u0017\u000e\u001e+qK\"Q1q\u0015\u0012\t\u0002\u0003\u0006K!a<\u0002\u001b\t{\u00070\u001a3V]&$H\u000b]3!\u0011)\u0019YK\tEC\u0002\u0013\u0005\u0011Q^\u0001\u000b\u001d>$\b.\u001b8h)B,\u0007BCBXE!\u0005\t\u0015)\u0003\u0002p\u0006Yaj\u001c;iS:<G\u000b]3!\u0011)\u0019\u0019L\tEC\u0002\u0013\u0005\u0011Q^\u0001\b\u001dVdG\u000e\u00169f\u0011)\u00199L\tE\u0001B\u0003&\u0011q^\u0001\t\u001dVdG\u000e\u00169fA!Q11\u0018\u0012\t\u0006\u0004%\t!!<\u0002\u0013=\u0013'.Z2u)B,\u0007BCB`E!\u0005\t\u0015)\u0003\u0002p\u0006QqJ\u00196fGR$\u0006/\u001a\u0011\t\u0015\r\r'\u0005#b\u0001\n\u0003\ti/A\bTKJL\u0017\r\\5{C\ndW\r\u00169f\u0011)\u00199M\tE\u0001B\u0003&\u0011q^\u0001\u0011'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a+qK\u0002B!ba3#\u0011\u000b\u0007I\u0011AAw\u0003%\u0019FO]5oOR\u0003X\r\u0003\u0006\u0004P\nB\t\u0011)Q\u0005\u0003_\f!b\u0015;sS:<G\u000b]3!\u0011)\u0019\u0019N\tEC\u0002\u0013\u0005\u0011Q^\u0001\r)\"\u0014xn^1cY\u0016$\u0006/\u001a\u0005\u000b\u0007/\u0014\u0003\u0012!Q!\n\u0005=\u0018!\u0004+ie><\u0018M\u00197f)B,\u0007\u0005\u0003\u0006\u0004\\\nB)\u0019!C\u0001\u0007;\fAbQ8ogR\fg\u000e\u001e+sk\u0016,\"aa8\u0011\u0007u\u0019\t/\u0003\u0003\u0004d\u0006U(AE+oSF,XmQ8ogR\fg\u000e\u001e+za\u0016D!ba:#\u0011\u0003\u0005\u000b\u0015BBp\u00035\u0019uN\\:uC:$HK];fA!Q11\u001e\u0012\t\u0006\u0004%\ta!8\u0002\u001b\r{gn\u001d;b]R4\u0015\r\\:f\u0011)\u0019yO\tE\u0001B\u0003&1q\\\u0001\u000f\u0007>t7\u000f^1oi\u001a\u000bGn]3!\u0011)\u0019\u0019P\tEC\u0002\u0013\u00051Q\\\u0001\r\u0007>t7\u000f^1oi:+H\u000e\u001c\u0005\u000b\u0007o\u0014\u0003\u0012!Q!\n\r}\u0017!D\"p]N$\u0018M\u001c;Ok2d\u0007\u0005\u0003\u0006\u0004|\nB)\u0019!C\u0001\u0003\u001f\u000b1\"\u00118z-\u0006d7\t\\1tg\"I1q \u0012\t\u0002\u0003\u0006KAT\u0001\r\u0003:Lh+\u00197DY\u0006\u001c8\u000f\t\u0005\b\t\u0007\u0011C\u0011AAm\u0003=\te.\u001f,bY~;W\r^\"mCN\u001c\bB\u0003C\u0004E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u0019\"+\u001e8uS6,gj\u001c;iS:<7\t\\1tg\"IA1\u0002\u0012\t\u0002\u0003\u0006KAT\u0001\u0015%VtG/[7f\u001d>$\b.\u001b8h\u00072\f7o\u001d\u0011\t\u0015\u0011=!\u0005#b\u0001\n\u0003\ty)\u0001\tSk:$\u0018.\\3Ok2d7\t\\1tg\"IA1\u0003\u0012\t\u0002\u0003\u0006KAT\u0001\u0012%VtG/[7f\u001dVdGn\u00117bgN\u0004ca\u0002C\fE\u0005\u0005B\u0011\u0004\u0002\u0012\u0005>$Ho\\7DY\u0006\u001c8oU=nE>d7c\u0001C\u000b\u001d\"IA\u000b\"\u0006\u0003\u0002\u0003\u0006I!\u0016\u0005\u000b\t?!)B!A!\u0002\u0013\u0011\u0017A\u00029be\u0016tG\u000f\u0003\u0005\u0003\b\u0012UA\u0011\u0001C\u0012)\u0019!)\u0003\"\u000b\u0005,A!Aq\u0005C\u000b\u001b\u0005\u0011\u0003B\u0002+\u0005\"\u0001\u0007Q\u000bC\u0004\u0005 \u0011\u0005\u0002\u0019\u00012\t\u0011\u0011=BQ\u0003C#\u0005\u001f\u000bQ\"[:C_R$x.\\\"mCN\u001c\b\u0002\u0003C\u001a\t+!)\u0005\"\u000e\u0002\u0019%\u001cH\u000b\u001b:fC\u0012\u001c\u0018MZ3\u0015\t\u0005MAq\u0007\u0005\t\ts!\t\u00041\u0001\u0005<\u00059\u0001/\u001e:q_N,\u0007cA\u000f\u0005>%\u0019AqH)\u0003\u0013MKXNY8m\u001fB\u001c\u0018F\u0002C\u000b\t\u0007\"IFB\u0004\u0005F\tB)\u0001b\u0012\u0003\u00199{G\u000f[5oO\u000ec\u0017m]:\u0014\t\u0011\rCQ\u0005\u0005\t\u0005\u000f#\u0019\u0005\"\u0001\u0005LQ\u0011AQ\n\t\u0005\tO!\u0019\u0005\u0003\u0005\u0005R\u0011\rC\u0011\tC*\u0003)I7oU;c\u00072\f7o\u001d\u000b\u0005\u0003'!)\u0006C\u0004\u0005X\u0011=\u0003\u0019\u00012\u0002\tQD\u0017\r\u001e\u0004\b\t7\u0012\u0003R\u0001C/\u0005%qU\u000f\u001c7DY\u0006\u001c8o\u0005\u0003\u0005Z\u0011\u0015\u0002\u0002\u0003BD\t3\"\t\u0001\"\u0019\u0015\u0005\u0011\r\u0004\u0003\u0002C\u0014\t3B\u0001\u0002\"\u0015\u0005Z\u0011\u0005Cq\r\u000b\u0005\u0003'!I\u0007C\u0004\u0005X\u0011\u0015\u0004\u0019\u00012\b\u000f\u00115$\u0005#\u0002\u0005N\u0005aaj\u001c;iS:<7\t\\1tg\u001e9A\u0011\u000f\u0012\t\u0006\u0011\r\u0014!\u0003(vY2\u001cE.Y:t\u0011)!)H\tEC\u0002\u0013\u0005\u0011qR\u0001\u0018\u00072\f7o]\"bgR,\u0005pY3qi&|gn\u00117bgND\u0011\u0002\"\u001f#\u0011\u0003\u0005\u000b\u0015\u0002(\u00021\rc\u0017m]:DCN$X\t_2faRLwN\\\"mCN\u001c\b\u0005\u0003\u0006\u0005~\tB)\u0019!C\u0001\u0003\u001f\u000ba$\u00138eKb|U\u000f^(g\u0005>,h\u000eZ:Fq\u000e,\u0007\u000f^5p]\u000ec\u0017m]:\t\u0013\u0011\u0005%\u0005#A!B\u0013q\u0015aH%oI\u0016Dx*\u001e;PM\n{WO\u001c3t\u000bb\u001cW\r\u001d;j_:\u001cE.Y:tA!QAQ\u0011\u0012\t\u0006\u0004%\t!a$\u0002=%sgo\\2bi&|g\u000eV1sO\u0016$X\t_2faRLwN\\\"mCN\u001c\b\"\u0003CEE!\u0005\t\u0015)\u0003O\u0003}IeN^8dCRLwN\u001c+be\u001e,G/\u0012=dKB$\u0018n\u001c8DY\u0006\u001c8\u000f\t\u0005\u000b\t\u001b\u0013\u0003R1A\u0005\u0002\u0005=\u0015aD'bi\u000eDWI\u001d:pe\u000ec\u0017m]:\t\u0013\u0011E%\u0005#A!B\u0013q\u0015\u0001E'bi\u000eDWI\u001d:pe\u000ec\u0017m]:!\u0011)!)J\tEC\u0002\u0013\u0005\u0011qR\u0001\u001b\u001d>tGj\\2bYJ+G/\u001e:o\u0007>tGO]8m\u00072\f7o\u001d\u0005\n\t3\u0013\u0003\u0012!Q!\n9\u000b1DT8o\u0019>\u001c\u0017\r\u001c*fiV\u0014hnQ8oiJ|Gn\u00117bgN\u0004\u0003B\u0003COE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006Ib*\u001e7m!>Lg\u000e^3s\u000bb\u001cW\r\u001d;j_:\u001cE.Y:t\u0011%!\tK\tE\u0001B\u0003&a*\u0001\u000eOk2d\u0007k\\5oi\u0016\u0014X\t_2faRLwN\\\"mCN\u001c\b\u0005\u0003\u0006\u0005&\nB)\u0019!C\u0001\u0003\u001f\u000ba\u0002\u00165s_^\f'\r\\3DY\u0006\u001c8\u000fC\u0005\u0005*\nB\t\u0011)Q\u0005\u001d\u0006yA\u000b\u001b:po\u0006\u0014G.Z\"mCN\u001c\b\u0005\u0003\u0006\u0005.\nB)\u0019!C\u0001\u0003\u001f\u000bq#\u00168j]&$\u0018.\u00197ju\u0016$WI\u001d:pe\u000ec\u0017m]:\t\u0013\u0011E&\u0005#A!B\u0013q\u0015\u0001G+oS:LG/[1mSj,G-\u0012:s_J\u001cE.Y:tA!QAQ\u0017\u0012\t\u0006\u0004%\t\u0001b.\u0002;Us\u0017N\\5uS\u0006d\u0017N_3e\r&,G\u000eZ\"p]N$(/^2u_J,\u0012A\u0019\u0005\n\tw\u0013\u0003\u0012!Q!\n\t\fa$\u00168j]&$\u0018.\u00197ju\u0016$g)[3mI\u000e{gn\u001d;sk\u000e$xN\u001d\u0011\t\u0015\u0011}&\u0005#b\u0001\n\u0003\ty)\u0001\u000bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u00072\f7o\u001d\u0005\n\t\u0007\u0014\u0003\u0012!Q!\n9\u000bQ\u0003U1si&\fGNR;oGRLwN\\\"mCN\u001c\b\u0005\u0003\u0006\u0005H\nB)\u0019!C\u0001\u0003\u001f\u000bA$\u00112tiJ\f7\r\u001e)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8DY\u0006\u001c8\u000fC\u0005\u0005L\nB\t\u0011)Q\u0005\u001d\u0006i\u0012IY:ue\u0006\u001cG\u000fU1si&\fGNR;oGRLwN\\\"mCN\u001c\b\u0005\u0003\u0006\u0005P\nB)\u0019!C\u0001\u0003\u001f\u000b1bU=nE>d7\t\\1tg\"IA1\u001b\u0012\t\u0002\u0003\u0006KAT\u0001\r'fl'm\u001c7DY\u0006\u001c8\u000f\t\u0005\u000b\t/\u0014\u0003R1A\u0005\u0002\u0005=\u0015aC*ue&twm\u00117bgND\u0011\u0002b7#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0019M#(/\u001b8h\u00072\f7o\u001d\u0011\t\u0015\u0011}'\u0005#b\u0001\n\u0003!9,\u0001\u0007TiJLgnZ'pIVdW\rC\u0005\u0005d\nB\t\u0011)Q\u0005E\u0006i1\u000b\u001e:j]\u001elu\u000eZ;mK\u0002B!\u0002b:#\u0011\u000b\u0007I\u0011AAH\u0003)\u0019E.Y:t\u00072\f7o\u001d\u0005\n\tW\u0014\u0003\u0012!Q!\n9\u000b1b\u00117bgN\u001cE.Y:tA!9Aq\u001e\u0012\u0005\u0002\u0005e\u0017aD\"mCN\u001cxlZ3u\u001b\u0016$\bn\u001c3\t\u0015\u0011M(\u0005#b\u0001\n\u0003\ty)\u0001\u0007Es:\fW.[2DY\u0006\u001c8\u000fC\u0005\u0005x\nB\t\u0011)Q\u0005\u001d\u0006iA)\u001f8b[&\u001c7\t\\1tg\u0002B!\u0002b?#\u0011\u000b\u0007I\u0011\u0001BT\u0003)\u0019\u0016p\u001d)bG.\fw-\u001a\u0005\u000b\t\u007f\u0014\u0003\u0012!Q!\n\u0005\u0015\u0013aC*zgB\u000b7m[1hK\u0002Bq!b\u0001#\t\u0003\tI.A\u0005TsN|VM\u001d:pe\"QQq\u0001\u0012\t\u0006\u0004%\t!\"\u0003\u0002%Us\u0017/^1mS\u001aLW\rZ'pIVdWm]\u000b\u0003\u000b\u0017\u0001R!\rB#\u0003\u000bB!\"b\u0004#\u0011\u0003\u0005\u000b\u0015BC\u0006\u0003M)f.];bY&4\u0017.\u001a3N_\u0012,H.Z:!\u0011))\u0019B\tEC\u0002\u0013\u0005QQC\u0001\u0012+:\fX/\u00197jM&,GmT<oKJ\u001cXCAC\f!\u0011\tT\u0011\u00042\n\u0007\u0015m!GA\u0002TKRD!\"b\b#\u0011\u0003\u0005\u000b\u0015BC\f\u0003I)f.];bY&4\u0017.\u001a3Po:,'o\u001d\u0011\t\u0015\u0015\r\"\u0005#b\u0001\n\u0003\u00119+\u0001\u0007Qe\u0016$WMZ'pIVdW\r\u0003\u0006\u0006(\tB\t\u0011)Q\u0005\u0003\u000b\nQ\u0002\u0015:fI\u00164Wj\u001c3vY\u0016\u0004\u0003bBC\u0016E\u0011\u0005QQF\u0001\u0011!J,G-\u001a4`oJ\f\u0007/\u0011:sCf$B!a7\u00060!A1qBC\u0015\u0001\u0004\ty\u000fC\u0004\u00064\t\"\t!!7\u00023A\u0013X\rZ3g?\u0012\nX.\u0019:lIEl\u0017M]6%c6\f'o\u001b\u0005\b\u000bo\u0011C\u0011AC\u001d\u0003MI7\u000f\u0015:fI\u00164W*Z7cKJt\u0015-\\3e)\u0019\t\u0019\"b\u000f\u0006>!9\u0011\u0011QC\u001b\u0001\u0004\u0011\u0007B\u0002+\u00066\u0001\u0007\u0001\b\u0003\u0006\u0006B\tB)\u0019!C\u0001\u0005O\u000b1c\u00159fG&\fG.\u001b>bE2,Wj\u001c3vY\u0016D!\"\"\u0012#\u0011\u0003\u0005\u000b\u0015BA#\u0003Q\u0019\u0006/Z2jC2L'0\u00192mK6{G-\u001e7fA!QQ\u0011\n\u0012\t\u0006\u0004%\tAa*\u0002%M\u001b\u0017\r\\1Sk:$\u0016.\\3N_\u0012,H.\u001a\u0005\u000b\u000b\u001b\u0012\u0003\u0012!Q!\n\u0005\u0015\u0013aE*dC2\f'+\u001e8US6,Wj\u001c3vY\u0016\u0004\u0003BCC)E!\u0015\r\u0011\"\u0001\u0003(\u0006a1+_7c_2lu\u000eZ;mK\"QQQ\u000b\u0012\t\u0002\u0003\u0006K!!\u0012\u0002\u001bMKXNY8m\u001b>$W\u000f\\3!\u0011\u001d)IF\tC\u0001\u00033\fAbU=nE>dw,\u00199qYfD!\"\"\u0018#\u0011\u000b\u0007I\u0011AAH\u00039\u0019FO]5oO\u0006#Gm\u00117bgND\u0011\"\"\u0019#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u001fM#(/\u001b8h\u0003\u0012$7\t\\1tg\u0002B!\"\"\u001a#\u0011\u000b\u0007I\u0011AAH\u0003A\u00196-\u00197b\u001dVl'-\u001a:DY\u0006\u001c8\u000fC\u0005\u0006j\tB\t\u0011)Q\u0005\u001d\u0006\t2kY1mC:+XNY3s\u00072\f7o\u001d\u0011\t\u0015\u00155$\u0005#b\u0001\n\u0003\ty)\u0001\u000eUe\u0006LGoU3ui\u0016\u0014\u0018I\u001c8pi\u0006$\u0018n\u001c8DY\u0006\u001c8\u000fC\u0005\u0006r\tB\t\u0011)Q\u0005\u001d\u0006YBK]1jiN+G\u000f^3s\u0003:tw\u000e^1uS>t7\t\\1tg\u0002B!\"\"\u001e#\u0011\u000b\u0007I\u0011AAH\u0003A!U\r\\1zK\u0012Le.\u001b;DY\u0006\u001c8\u000fC\u0005\u0006z\tB\t\u0011)Q\u0005\u001d\u0006\tB)\u001a7bs\u0016$\u0017J\\5u\u00072\f7o\u001d\u0011\t\u000f\u0015u$\u0005\"\u0001\u0002Z\u0006\tB-\u001a7bs\u0016$\u0017J\\5u\u001b\u0016$\bn\u001c3\t\u0015\u0015\u0005%\u0005#b\u0001\n\u0003\ty)A\nUsB,7i\u001c8tiJ\f\u0017N\u001c;DY\u0006\u001c8\u000fC\u0005\u0006\u0006\nB\t\u0011)Q\u0005\u001d\u0006!B+\u001f9f\u0007>t7\u000f\u001e:bS:$8\t\\1tg\u0002B!\"\"##\u0011\u000b\u0007I\u0011AAH\u00039\u0019\u0016N\\4mKR|gn\u00117bgND\u0011\"\"$#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u001fMKgn\u001a7fi>t7\t\\1tg\u0002B!\"\"%#\u0011\u000b\u0007I\u0011AAH\u0003E\u0019VM]5bY&T\u0018M\u00197f\u00072\f7o\u001d\u0005\n\u000b+\u0013\u0003\u0012!Q!\n9\u000b!cU3sS\u0006d\u0017N_1cY\u0016\u001cE.Y:tA!QQ\u0011\u0014\u0012\t\u0006\u0004%\t!a$\u0002+)\u000bg/Y*fe&\fG.\u001b>bE2,7\t\\1tg\"IQQ\u0014\u0012\t\u0002\u0003\u0006KAT\u0001\u0017\u0015\u00064\u0018mU3sS\u0006d\u0017N_1cY\u0016\u001cE.Y:tA!QQ\u0011\u0015\u0012\t\u0006\u0004%\t!a$\u0002\u001f\r{W\u000e]1sC\ndWm\u00117bgND\u0011\"\"*#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002!\r{W\u000e]1sC\ndWm\u00117bgN\u0004\u0003BCCUE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u0011\"*\u0019<b\u00072|g.Z1cY\u0016\u001cE.Y:t\u0011%)iK\tE\u0001B\u0003&a*A\nKCZ\f7\t\\8oK\u0006\u0014G.Z\"mCN\u001c\b\u0005\u0003\u0006\u00062\nB)\u0019!C\u0001\u0003\u001f\u000bqBS1wC:+XNY3s\u00072\f7o\u001d\u0005\n\u000bk\u0013\u0003\u0012!Q!\n9\u000b\u0001CS1wC:+XNY3s\u00072\f7o\u001d\u0011\t\u0015\u0015e&\u0005#b\u0001\n\u0003\ty)A\u0007KCZ\fWI\\;n\u00072\f7o\u001d\u0005\n\u000b{\u0013\u0003\u0012!Q!\n9\u000baBS1wC\u0016sW/\\\"mCN\u001c\b\u0005\u0003\u0006\u0006B\nB)\u0019!C\u0001\u0003\u001f\u000bACU3n_R,\u0017J\u001c;fe\u001a\f7-Z\"mCN\u001c\b\"CCcE!\u0005\t\u0015)\u0003O\u0003U\u0011V-\\8uK&sG/\u001a:gC\u000e,7\t\\1tg\u0002B!\"\"3#\u0011\u000b\u0007I\u0011AAH\u0003Q\u0011V-\\8uK\u0016C8-\u001a9uS>t7\t\\1tg\"IQQ\u001a\u0012\t\u0002\u0003\u0006KAT\u0001\u0016%\u0016lw\u000e^3Fq\u000e,\u0007\u000f^5p]\u000ec\u0017m]:!\u0011))\tN\tEC\u0002\u0013\u0005\u0011qR\u0001\f\u0015\u00064\u0018-\u0016;jY6\u000b\u0007\u000fC\u0005\u0006V\nB\t\u0011)Q\u0005\u001d\u0006a!*\u0019<b+RLG.T1qA!QQ\u0011\u001c\u0012\t\u0006\u0004%\t!a$\u0002\u001f)\u000bg/Y+uS2D\u0015m\u001d5NCBD\u0011\"\"8#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002!)\u000bg/Y+uS2D\u0015m\u001d5NCB\u0004\u0003BCCqE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u0001\")\u001f(b[\u0016\u0004\u0016M]1n\u00072\f7o\u001d\u0005\n\u000bK\u0014\u0003\u0012!Q!\n9\u000b\u0011CQ=OC6,\u0007+\u0019:b[\u000ec\u0017m]:!\u0011))IO\tEC\u0002\u0013\u0005\u0011qR\u0001\u0017\u0015\u00064\u0018MU3qK\u0006$X\r\u001a)be\u0006l7\t\\1tg\"IQQ\u001e\u0012\t\u0002\u0003\u0006KAT\u0001\u0018\u0015\u00064\u0018MU3qK\u0006$X\r\u001a)be\u0006l7\t\\1tg\u0002B!\"\"=#\u0011\u000b\u0007I\u0011AAH\u0003I\u0011V\r]3bi\u0016$\u0007+\u0019:b[\u000ec\u0017m]:\t\u0013\u0015U(\u0005#A!B\u0013q\u0015a\u0005*fa\u0016\fG/\u001a3QCJ\fWn\u00117bgN\u0004\u0003bBC}E\u0011\u0005Q1`\u0001\u0012SN\u0014\u0015PT1nKB\u000b'/Y7UsB,G\u0003BA\n\u000b{D\u0001ba\u0004\u0006x\u0002\u0007\u0011q\u001e\u0005\b\r\u0003\u0011C\u0011\u0001D\u0002\u0003aI7oU2bY\u0006\u0014V\r]3bi\u0016$\u0007+\u0019:b[RK\b/\u001a\u000b\u0005\u0003'1)\u0001\u0003\u0005\u0004\u0010\u0015}\b\u0019AAx\u0011\u001d1IA\tC\u0001\r\u0017\tq#[:KCZ\f'+\u001a9fCR,G\rU1sC6$\u0016\u0010]3\u0015\t\u0005MaQ\u0002\u0005\t\u0007\u001f19\u00011\u0001\u0002p\"9a\u0011\u0003\u0012\u0005\u0002\u0019M\u0011aE5t%\u0016\u0004X-\u0019;fIB\u000b'/Y7UsB,G\u0003BA\n\r+A\u0001ba\u0004\u0007\u0010\u0001\u0007\u0011q\u001e\u0005\b\r3\u0011C\u0011\u0001D\u000e\u0003)I7OU3qK\u0006$X\r\u001a\u000b\u0005\u0003'1i\u0002C\u0004\u0007 \u0019]\u0001\u0019\u00012\u0002\u000bA\f'/Y7\t\u000f\u0019\r\"\u0005\"\u0001\u0007&\u0005A\u0011n\u001d\"z\u001d\u0006lW\r\u0006\u0003\u0002\u0014\u0019\u001d\u0002b\u0002D\u0010\rC\u0001\rA\u0019\u0005\b\rW\u0011C\u0011\u0001D\u0017\u00031I7oQ1tiNKXNY8m)\u0011\t\u0019Bb\f\t\u000f\u0005\u0005e\u0011\u0006a\u0001E\"9a1\u0007\u0012\u0005\u0002\u0019U\u0012aE5t\u0015\u00064\u0018MV1s\u0003J<7/T3uQ>$G\u0003BA\n\roAqA\"\u000f\u00072\u0001\u0007!-A\u0001n\u0011\u001d1iD\tC\u0001\r\u007f\tQ\"[:KCZ\fg+\u0019:Be\u001e\u001cH\u0003BA\n\r\u0003B\u0001Bb\u0011\u0007<\u0001\u0007aQI\u0001\u0007a\u0006\u0014\u0018-\\:\u0011\tM49EY\u0005\u0004\r\u0013B(aA*fc\"9aQ\n\u0012\u0005\u0002\u0019=\u0013AD5t'\u000e\fG.\u0019,be\u0006\u0013xm\u001d\u000b\u0005\u0003'1\t\u0006\u0003\u0005\u0007D\u0019-\u0003\u0019\u0001D#\u0011\u001d1)F\tC\u0001\r/\nQ\"[:WCJ\f%oZ:MSN$H\u0003BA\n\r3B\u0001Bb\u0011\u0007T\u0001\u0007aQ\t\u0005\b\r;\u0012C\u0011\u0001D0\u00035I7OV1s\u0003J<G+\u001f9fgR!\u00111\u0003D1\u0011!1\u0019Gb\u0017A\u0002\u0019\u0015\u0014a\u00024pe6\fGn\u001d\t\u0006g\u001a\u001d\u0013q\u001e\u0005\b\rS\u0012C\u0011\u0001D6\u000391\u0017N]:u!\u0006\u0014\u0018-\u001c+za\u0016$B!a<\u0007n!A1q\rD4\u0001\u0004\ty\u000fC\u0004\u0007r\t\"\tAb\u001d\u0002#%\u001c\u0018*\u001c9mS\u000eLG\u000fU1sC6\u001c8\u000f\u0006\u0003\u0002\u0014\u0019U\u0004\u0002\u0003D<\r_\u0002\rA\"\u001f\u0002\u000fA\f'/Y7tgB\u00191O\u001e:\t\u000f\u0019u$\u0005\"\u0001\u0007\u0000\u0005\u0001\u0002.Y:SKB,\u0017\r^3e!\u0006\u0014\u0018-\u001c\u000b\u0005\u0003'1\t\t\u0003\u0005\u0004\u0010\u0019m\u0004\u0019AAx\u0011\u001d1)I\tC\u0001\r\u000f\u000b!\u0002\u001a:pa\nKh*Y7f)\u0011\tyO\"#\t\u0011\r=a1\u0011a\u0001\u0003_DqA\"$#\t\u00031y)\u0001\u0007ee>\u0004(+\u001a9fCR,G\r\u0006\u0003\u0002p\u001aE\u0005\u0002CB\b\r\u0017\u0003\r!a<\t\u000f\u0019U%\u0005\"\u0001\u0007\u0018\u0006\u0001\"/\u001a9fCR,G\rV8TS:<G.\u001a\u000b\u0005\u0003_4I\n\u0003\u0005\u0004\u0010\u0019M\u0005\u0019AAx\u0011\u001d1iJ\tC\u0001\r?\u000bQB]3qK\u0006$X\r\u001a+p'\u0016\fH\u0003BAx\rCC\u0001ba\u0004\u0007\u001c\u0002\u0007\u0011q\u001e\u0005\b\rK\u0013C\u0011\u0001DT\u00035\u0019X-\u001d+p%\u0016\u0004X-\u0019;fIR!\u0011q\u001eDU\u0011!\u0019yAb)A\u0002\u0005=\bb\u0002DWE\u0011\u0005aqV\u0001\u0011SN\u0014VMZ3sK:\u001cW-\u0011:sCf$B!a\u0005\u00072\"A1q\u0002DV\u0001\u0004\ty\u000fC\u0004\u00076\n\"\tAb.\u0002\u001f%\u001c\u0018I\u001d:bs>37+_7c_2$b!a\u0005\u0007:\u001am\u0006\u0002CB\b\rg\u0003\r!a<\t\u000f\u0019uf1\u0017a\u0001E\u0006!Q\r\\3n\u0011\u001d1\tM\tC\u0001\r\u0007\f1\"\u001a7f[\u0016tG\u000fV=qKR1\u0011q\u001eDc\r\u0013DqAb2\u0007@\u0002\u0007!-A\u0005d_:$\u0018-\u001b8fe\"A1q\u0002D`\u0001\u0004\ty\u000f\u0003\u0006\u0007N\nB)\u0019!C\u0001\u0003\u001f\u000b\u0011bQ8og\u000ec\u0017m]:\t\u0013\u0019E'\u0005#A!B\u0013q\u0015AC\"p]N\u001cE.Y:tA!QaQ\u001b\u0012\t\u0006\u0004%\t!a$\u0002\u001b%#XM]1u_J\u001cE.Y:t\u0011%1IN\tE\u0001B\u0003&a*\u0001\bJi\u0016\u0014\u0018\r^8s\u00072\f7o\u001d\u0011\t\u0015\u0019u'\u0005#b\u0001\n\u0003\ty)A\u0007Ji\u0016\u0014\u0018M\u00197f\u00072\f7o\u001d\u0005\n\rC\u0014\u0003\u0012!Q!\n9\u000ba\"\u0013;fe\u0006\u0014G.Z\"mCN\u001c\b\u0005\u0003\u0006\u0007f\nB)\u0019!C\u0001\u0003\u001f\u000b\u0011\u0002T5ti\u000ec\u0017m]:\t\u0013\u0019%(\u0005#A!B\u0013q\u0015A\u0003'jgR\u001cE.Y:tA!QaQ\u001e\u0012\t\u0006\u0004%\t!a$\u0002\u0011M+\u0017o\u00117bgND\u0011B\"=#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0013M+\u0017o\u00117bgN\u0004\u0003B\u0003D{E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u00112\u000b\u001e:j]\u001e\u0014U/\u001b7eKJ\u001cE.Y:t\u0011%1IP\tE\u0001B\u0003&a*A\nTiJLgn\u001a\"vS2$WM]\"mCN\u001c\b\u0005\u0003\u0006\u0007~\nB)\u0019!C\u0001\u0003\u001f\u000b\u0001\u0003\u0016:bm\u0016\u00148/\u00192mK\u000ec\u0017m]:\t\u0013\u001d\u0005!\u0005#A!B\u0013q\u0015!\u0005+sCZ,'o]1cY\u0016\u001cE.Y:tA!QqQ\u0001\u0012\t\u0006\u0004%\tAa*\u0002\u00151K7\u000f^'pIVdW\r\u0003\u0006\b\n\tB\t\u0011)Q\u0005\u0003\u000b\n1\u0002T5ti6{G-\u001e7fA!9qQ\u0002\u0012\u0005\u0002\u0005e\u0017A\u0003'jgR|\u0016\r\u001d9ms\"Qq\u0011\u0003\u0012\t\u0006\u0004%\tAa*\u0002\u00139KG.T8ek2,\u0007BCD\u000bE!\u0005\t\u0015)\u0003\u0002F\u0005Qa*\u001b7N_\u0012,H.\u001a\u0011\t\u0015\u001de!\u0005#b\u0001\n\u0003\u00119+A\u0005TKFlu\u000eZ;mK\"QqQ\u0004\u0012\t\u0002\u0003\u0006K!!\u0012\u0002\u0015M+\u0017/T8ek2,\u0007\u0005\u0003\u0006\b\"\tB)\u0019!C\u0001\u0005O\u000b1\"\u0011:sCflu\u000eZ;mK\"QqQ\u0005\u0012\t\u0002\u0003\u0006K!!\u0012\u0002\u0019\u0005\u0013(/Y=N_\u0012,H.\u001a\u0011\t\u0015\u001d%\"\u0005#b\u0001\n\u0003\tI.A\u000eBeJ\f\u00170T8ek2,wl\u001c<fe2|\u0017\rZ3e\u0003B\u0004H.\u001f\u0005\u000b\u000f[\u0011\u0003\u0012!Q!\n\u0005m\u0017\u0001H!se\u0006LXj\u001c3vY\u0016|vN^3sY>\fG-\u001a3BaBd\u0017\u0010\t\u0005\b\u000fc\u0011C\u0011\u0001C\\\u0003a\t%O]1z\u001b>$W\u000f\\3`O\u0016tWM]5d\u0003B\u0004H.\u001f\u0005\b\u000fk\u0011C\u0011AD\u001c\u0003E\t%O]1z\u001b>$W\u000f\\3`CB\u0004H.\u001f\u000b\u0004E\u001ee\u0002\u0002CB\b\u000fg\u0001\r!a<\t\u0015\u001du\"\u0005#b\u0001\n\u0003\ty)\u0001\u0006BeJ\f\u0017p\u00117bgND\u0011b\"\u0011#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0017\u0005\u0013(/Y=DY\u0006\u001c8\u000f\t\u0005\u000b\u000f\u000b\u0012\u0003R1A\u0005\u0002\u0005e\u0017aC!se\u0006Lx,\u00199qYfD!b\"\u0013#\u0011\u0003\u0005\u000b\u0015BAn\u00031\t%O]1z?\u0006\u0004\b\u000f\\=!\u0011)9iE\tEC\u0002\u0013\u0005\u0011\u0011\\\u0001\r\u0003J\u0014\u0018-_0va\u0012\fG/\u001a\u0005\u000b\u000f#\u0012\u0003\u0012!Q!\n\u0005m\u0017!D!se\u0006Lx,\u001e9eCR,\u0007\u0005\u0003\u0006\bV\tB)\u0019!C\u0001\u00033\fA\"\u0011:sCf|F.\u001a8hi\"D!b\"\u0017#\u0011\u0003\u0005\u000b\u0015BAn\u00035\t%O]1z?2,gn\u001a;iA!QqQ\f\u0012\t\u0006\u0004%\t!!7\u0002\u0017\u0005\u0013(/Y=`G2|g.\u001a\u0005\u000b\u000fC\u0012\u0003\u0012!Q!\n\u0005m\u0017\u0001D!se\u0006Lxl\u00197p]\u0016\u0004\u0003BCD3E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u00112k\u001c4u%\u00164WM]3oG\u0016\u001cE.Y:t\u0011%9IG\tE\u0001B\u0003&a*A\nT_\u001a$(+\u001a4fe\u0016t7-Z\"mCN\u001c\b\u0005\u0003\u0006\bn\tB)\u0019!C\u0001\u0003\u001f\u000b1\"T3uQ>$7\t\\1tg\"Iq\u0011\u000f\u0012\t\u0002\u0003\u0006KAT\u0001\r\u001b\u0016$\bn\u001c3DY\u0006\u001c8\u000f\t\u0005\u000b\u000fk\u0012\u0003R1A\u0005\u0002\u0005=\u0015!F#naRLX*\u001a;i_\u0012\u001c\u0015m\u00195f\u00072\f7o\u001d\u0005\n\u000fs\u0012\u0003\u0012!Q!\n9\u000ba#R7qiflU\r\u001e5pI\u000e\u000b7\r[3DY\u0006\u001c8\u000f\t\u0005\u000b\u000f{\u0012\u0003R1A\u0005\u0002\u0005=\u0015\u0001E'fi\"|GmQ1dQ\u0016\u001cE.Y:t\u0011%9\tI\tE\u0001B\u0003&a*A\tNKRDw\u000eZ\"bG\",7\t\\1tg\u0002Bqa\"\"#\t\u0003\tI.\u0001\tnKRDw\u000eZ\"bG\",wLZ5oI\"9q\u0011\u0012\u0012\u0005\u0002\u0005e\u0017aD7fi\"|GmQ1dQ\u0016|\u0016\r\u001a3\t\u0015\u001d5%\u0005#b\u0001\n\u0003!9,\u0001\tTG\u0006d\u0017\rW7m)>\u00048kY8qK\"Iq\u0011\u0013\u0012\t\u0002\u0003\u0006KAY\u0001\u0012'\u000e\fG.\u0019-nYR{\u0007oU2pa\u0016\u0004\u0003BCDKE!\u0015\r\u0011\"\u0001\u00058\u0006y1kY1mCbkG\u000eU1dW\u0006<W\rC\u0005\b\u001a\nB\t\u0011)Q\u0005E\u0006\u00012kY1mCbkG\u000eU1dW\u0006<W\r\t\u0005\u000b\u000f;\u0013\u0003R1A\u0005\u0002\t\u001d\u0016A\u0004*fM2,7\r\u001e)bG.\fw-\u001a\u0005\u000b\u000fC\u0013\u0003\u0012!Q!\n\u0005\u0015\u0013a\u0004*fM2,7\r\u001e)bG.\fw-\u001a\u0011\t\u0015\u001d\u0015&\u0005#b\u0001\n\u0003!9,A\tSK\u001adWm\u0019;Ba&\u0004\u0016mY6bO\u0016D\u0011b\"+#\u0011\u0003\u0005\u000b\u0015\u00022\u0002%I+g\r\\3di\u0006\u0003\u0018\u000eU1dW\u0006<W\r\t\u0005\u000b\u000f[\u0013\u0003R1A\u0005\u0002\u0011]\u0016!\u0006*fM2,7\r\u001e*v]RLW.\u001a)bG.\fw-\u001a\u0005\n\u000fc\u0013\u0003\u0012!Q!\n\t\faCU3gY\u0016\u001cGOU;oi&lW\rU1dW\u0006<W\r\t\u0005\b\u000fk\u0013C\u0011\u0001C\\\u0003Y\u0011VM\u001a7fGR\u0014VO\u001c;j[\u0016,f.\u001b<feN,\u0007bBD]E\u0011\u0005AqW\u0001\u001c%\u00164G.Z2u%VtG/[7f\u0007V\u0014(/\u001a8u\u001b&\u0014(o\u001c:\t\u0015\u001du&\u0005#b\u0001\n\u0003!9,A\u0007V]&4XM]:f\u00072\f7o\u001d\u0005\n\u000f\u0003\u0014\u0003\u0012!Q!\n\t\fa\"\u00168jm\u0016\u00148/Z\"mCN\u001c\b\u0005C\u0004\bF\n\"\t!!7\u0002!Us\u0017N^3sg\u0016Le\u000e^3s]\u0006d\u0007BCDeE!\u0015\r\u0011\"\u0001\u0003(\u0006)\u0002+\u0019:uS\u0006dW*\u00198jM\u0016\u001cH/T8ek2,\u0007BCDgE!\u0005\t\u0015)\u0003\u0002F\u00051\u0002+\u0019:uS\u0006dW*\u00198jM\u0016\u001cH/T8ek2,\u0007\u0005\u0003\u0006\bR\nB)\u0019!C\u0001\u0003\u001f\u000b\u0011CR;mY6\u000bg.\u001b4fgR\u001cE.Y:t\u0011%9)N\tE\u0001B\u0003&a*\u0001\nGk2dW*\u00198jM\u0016\u001cHo\u00117bgN\u0004\u0003BCDmE!\u0015\r\u0011\"\u0001\u0003(\u0006\u0011b)\u001e7m\u001b\u0006t\u0017NZ3ti6{G-\u001e7f\u0011)9iN\tE\u0001B\u0003&\u0011QI\u0001\u0014\rVdG.T1oS\u001a,7\u000f^'pIVdW\r\t\u0005\u000b\u000fC\u0014\u0003R1A\u0005\u0002\u0005=\u0015\u0001E(qi6\u000bg.\u001b4fgR\u001cE.Y:t\u0011%9)O\tE\u0001B\u0003&a*A\tPaRl\u0015M\\5gKN$8\t\\1tg\u0002B!b\";#\u0011\u000b\u0007I\u0011\u0001BT\u0003)qu.T1oS\u001a,7\u000f\u001e\u0005\u000b\u000f[\u0014\u0003\u0012!Q!\n\u0005\u0015\u0013a\u0003(p\u001b\u0006t\u0017NZ3ti\u0002B!b\"=#\u0011\u000b\u0007I\u0011\u0001C\\\u0003)!&/Z3t\u00072\f7o\u001d\u0005\n\u000fk\u0014\u0003\u0012!Q!\n\t\f1\u0002\u0016:fKN\u001cE.Y:tA!Qq\u0011 \u0012\t\u0006\u0004%\t\u0001b.\u0002\u0015\u0015C\bO]:DY\u0006\u001c8\u000fC\u0005\b~\nB\t\u0011)Q\u0005E\u0006YQ\t\u001f9sg\u000ec\u0017m]:!\u0011\u001dA\tA\tC\u0001\to\u000b\u0011\"\u0012=qe\u000ec\u0017m]:\t\u000f!\u0015!\u0005\"\u0001\u00058\u0006QQ\t\u001f9s'Bd\u0017nY3\t\u000f!%!\u0005\"\u0001\u00058\u0006IQ\t\u001f9s-\u0006dW/\u001a\u0005\u000b\u0011\u001b\u0011\u0003R1A\u0005\u0002\t\u001d\u0016AD\"mCN\u001cH+Y4N_\u0012,H.\u001a\u0005\u000b\u0011#\u0011\u0003\u0012!Q!\n\u0005\u0015\u0013aD\"mCN\u001cH+Y4N_\u0012,H.\u001a\u0011\t\u0015!U!\u0005#b\u0001\n\u0003\ty)A\u0007DY\u0006\u001c8\u000fV1h\u00072\f7o\u001d\u0005\n\u00113\u0011\u0003\u0012!Q!\n9\u000bab\u00117bgN$\u0016mZ\"mCN\u001c\b\u0005\u0003\u0006\t\u001e\tB)\u0019!C\u0001\to\u000bQ\u0002V=qKR\u000bwm]\"mCN\u001c\b\"\u0003E\u0011E!\u0005\t\u0015)\u0003c\u00039!\u0016\u0010]3UC\u001e\u001c8\t\\1tg\u0002B!\u0002#\n#\u0011\u000b\u0007I\u0011\u0001C\\\u0003A\t\u0005/[+oSZ,'o]3DY\u0006\u001c8\u000fC\u0005\t*\tB\t\u0011)Q\u0005E\u0006\t\u0012\t]5V]&4XM]:f\u00072\f7o\u001d\u0011\t\u0015!5\"\u0005#b\u0001\n\u0003!9,A\tKCZ\fWK\\5wKJ\u001cXm\u00117bgND\u0011\u0002#\r#\u0011\u0003\u0005\u000b\u0015\u00022\u0002%)\u000bg/Y+oSZ,'o]3DY\u0006\u001c8\u000f\t\u0005\u000b\u0011k\u0011\u0003R1A\u0005\u0002\u0011]\u0016aC'jeJ|'o\u00117bgND\u0011\u0002#\u000f#\u0011\u0003\u0005\u000b\u0015\u00022\u0002\u00195K'O]8s\u00072\f7o\u001d\u0011\t\u0015!u\"\u0005#b\u0001\n\u0003!9,\u0001\tUsB,7I]3bi>\u00148\t\\1tg\"I\u0001\u0012\t\u0012\t\u0002\u0003\u0006KAY\u0001\u0012)f\u0004Xm\u0011:fCR|'o\u00117bgN\u0004\u0003B\u0003E#E!\u0015\r\u0011\"\u0001\u00058\u0006\u0001BK]3f\u0007J,\u0017\r^8s\u00072\f7o\u001d\u0005\n\u0011\u0013\u0012\u0003\u0012!Q!\n\t\f\u0011\u0003\u0016:fK\u000e\u0013X-\u0019;pe\u000ec\u0017m]:!\u0011\u001dAiE\tC\u0005\to\u000b1bQ8oi\u0016DHo\u0018\u001a2a!Q\u0001\u0012\u000b\u0012\t\u0006\u0004%\t\u0001b.\u0002)\tc\u0017mY6c_b\u001cuN\u001c;fqR\u001cE.Y:t\u0011%A)F\tE\u0001B\u0003&!-A\u000bCY\u0006\u001c7NY8y\u0007>tG/\u001a=u\u00072\f7o\u001d\u0011\t\u0015!e#\u0005#b\u0001\n\u0003!9,\u0001\u000bXQ&$XMY8y\u0007>tG/\u001a=u\u00072\f7o\u001d\u0005\n\u0011;\u0012\u0003\u0012!Q!\n\t\fQc\u00165ji\u0016\u0014w\u000e_\"p]R,\u0007\u0010^\"mCN\u001c\b\u0005C\u0004\tb\t\"\t\u0001b.\u0002%5\u000b7M]8D_:$X\r\u001f;Qe\u00164\u0017\u000e\u001f\u0005\b\u0011K\u0012C\u0011\u0001C\\\u0003Yi\u0015m\u0019:p\u0007>tG/\u001a=u!J,g-\u001b=UsB,\u0007b\u0002E5E\u0011\u0005AqW\u0001\u0015\u001b\u0006\u001c'o\\\"p]R,\u0007\u0010^+oSZ,'o]3\t\u000f!5$\u0005\"\u0001\u00058\u0006)R*Y2s_\u000e{g\u000e^3yi\u0016C\bO]\"mCN\u001c\bb\u0002E9E\u0011\u0005AqW\u0001\u001d\u001b\u0006\u001c'o\\\"p]R,\u0007\u0010^,fC.$\u0016\u0010]3UC\u001e\u001cE.Y:t\u0011\u001dA)H\tC\u0001\to\u000bA#T1de>\u001cuN\u001c;fqR$&/Z3UsB,\u0007B\u0003E=E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\u0019R*Y2s_&k\u0007\u000f\\!o]>$\u0018\r^5p]\"I\u0001R\u0010\u0012\t\u0002\u0003\u0006KAT\u0001\u0015\u001b\u0006\u001c'o\\%na2\feN\\8uCRLwN\u001c\u0011\t\u0015!\u0005%\u0005#b\u0001\n\u0003\ty)\u0001\nTiJLgnZ\"p]R,\u0007\u0010^\"mCN\u001c\b\"\u0003ECE!\u0005\t\u0015)\u0003O\u0003M\u0019FO]5oO\u000e{g\u000e^3yi\u000ec\u0017m]:!\u0011)AII\tEC\u0002\u0013\u0005AqW\u0001\u0010#V\f7/[9v_R,7\t\\1tg\"I\u0001R\u0012\u0012\t\u0002\u0003\u0006KAY\u0001\u0011#V\f7/[9v_R,7\t\\1tg\u0002B!\u0002#%#\u0011\u000b\u0007I\u0011\u0001C\\\u0003M\tV/Y:jcV|G/Z\"mCN\u001cx,\u00199j\u0011%A)J\tE\u0001B\u0003&!-\u0001\u000bRk\u0006\u001c\u0018.];pi\u0016\u001cE.Y:t?\u0006\u0004\u0018\u000e\t\u0005\u000b\u00113\u0013\u0003R1A\u0005\u0002\u0011]\u0016!G)vCNL\u0017/^8uK\u000ec\u0017m]:`CBLw,\u00199qYfD\u0011\u0002#(#\u0011\u0003\u0005\u000b\u0015\u00022\u00025E+\u0018m]5rk>$Xm\u00117bgN|\u0016\r]5`CB\u0004H.\u001f\u0011\t\u0015!\u0005&\u0005#b\u0001\n\u0003!9,A\u000eRk\u0006\u001c\u0018.];pi\u0016\u001cE.Y:t?\u0006\u0004\u0018nX;oCB\u0004H.\u001f\u0005\n\u0011K\u0013\u0003\u0012!Q!\n\t\fA$U;bg&\fXo\u001c;f\u00072\f7o]0ba&|VO\\1qa2L\b\u0005\u0003\u0006\t*\nB)\u0019!C\u0001\u0003\u001f\u000b\u0001dU2bY\u0006\u001c\u0016n\u001a8biV\u0014X-\u00118o_R\fG/[8o\u0011%AiK\tE\u0001B\u0003&a*A\rTG\u0006d\u0017mU5h]\u0006$XO]3B]:|G/\u0019;j_:\u0004\u0003B\u0003EYE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006a2kY1mC2{gnZ*jO:\fG/\u001e:f\u0003:tw\u000e^1uS>t\u0007\"\u0003E[E!\u0005\t\u0015)\u0003O\u0003u\u00196-\u00197b\u0019>twmU5h]\u0006$XO]3B]:|G/\u0019;j_:\u0004\u0003B\u0003E]E!\u0015\r\u0011\"\u0001\u00058\u0006\tB*Y7cI\u0006lU\r^1GC\u000e$xN]=\t\u0013!u&\u0005#A!B\u0013\u0011\u0017A\u0005'b[\n$\u0017-T3uC\u001a\u000b7\r^8ss\u0002B!\u0002#1#\u0011\u000b\u0007I\u0011\u0001C\\\u00031iU\r\u001e5pI\"\u000bg\u000e\u001a7f\u0011%A)M\tE\u0001B\u0003&!-A\u0007NKRDw\u000e\u001a%b]\u0012dW\r\t\u0005\u000b\u0011\u0013\u0014\u0003R1A\u0005\u0002\u0005=\u0015aC(qi&|gn\u00117bgND\u0011\u0002#4#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0019=\u0003H/[8o\u00072\f7o\u001d\u0011\t\u0015!E'\u0005#b\u0001\n\u0003\u00119+\u0001\u0007PaRLwN\\'pIVdW\r\u0003\u0006\tV\nB\t\u0011)Q\u0005\u0003\u000b\nQb\u00149uS>tWj\u001c3vY\u0016\u0004\u0003B\u0003EmE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006I1k\\7f\u00072\f7o\u001d\u0005\n\u0011;\u0014\u0003\u0012!Q!\n9\u000b!bU8nK\u000ec\u0017m]:!\u0011)A\tO\tEC\u0002\u0013\u0005!qU\u0001\u000b\u001d>tW-T8ek2,\u0007B\u0003EsE!\u0005\t\u0015)\u0003\u0002F\u0005Yaj\u001c8f\u001b>$W\u000f\\3!\u0011)AIO\tEC\u0002\u0013\u0005!qU\u0001\u000b'>lW-T8ek2,\u0007B\u0003EwE!\u0005\t\u0015)\u0003\u0002F\u0005Y1k\\7f\u001b>$W\u000f\\3!\u0011\u001dA\tP\tC\u0001\u0011g\f1cY8na&dWM\u001d+za\u00164%o\\7UC\u001e$B!a<\tv\"A\u0001r\u001fEx\u0001\u0004AI0\u0001\u0002uiB\"\u00012`E\u0007!\u0019Ai0c\u0001\n\fA\u0019q\u0002c@\n\u0007%\u0005\u0001C\u0001\u0005V]&4XM]:f\u0013\u0011I)!c\u0002\u0003\u0017]+\u0017m\u001b+za\u0016$\u0016mZ\u0005\u0004\u0013\u0013\u0001\"\u0001\u0003+za\u0016$\u0016mZ:\u0011\u0007\u0019Li\u0001B\u0006\n\u0010!U\u0018\u0011!A\u0001\u0006\u0003I'\u0001B0%eaBq!c\u0005#\t\u0003I)\"A\u000bd_6\u0004\u0018\u000e\\3s'fl'm\u001c7Ge>lG+Y4\u0015\u0007\tL9\u0002\u0003\u0005\tx&E\u0001\u0019AE\ra\u0011IY\"c\b\u0011\r!u\u00182AE\u000f!\r1\u0017r\u0004\u0003\f\u0013CI9\"!A\u0001\u0002\u000b\u0005\u0011N\u0001\u0003`IIJ\u0004bBE\u0013E\u0011\u0005\u0011rE\u0001\u0011SNT\u0015M^1NC&tW*\u001a;i_\u0012$B!a\u0005\n*!9\u0011\u0011QE\u0012\u0001\u0004\u0011\u0007bBE\u0017E\u0011\u0005\u0011rF\u0001\u0012Q\u0006\u001c(*\u0019<b\u001b\u0006Lg.T3uQ>$G\u0003BA\n\u0013cAq!!!\n,\u0001\u0007!M\u0002\u0004\n6\t\u0002\u0011r\u0007\u0002\u000e-\u0006\u0014\u0018I]5us\u000ec\u0017m]:\u0014\t%M\u0012\u0012\b\t\u0005\tOIY$C\u0002\n>\u0019\u0012\u0001CV1s\u0003JLG/_\"mCN\u001c\u0018\t]5\t\u0015QK\u0019D!A!\u0002\u0013I\t\u0005E\u0002^\u0013\u0007J1!#\u0012b\u0005\u0019\u0019FO]5oO\"Q\u0011\u0012JE\u001a\u0005\u0003\u0005\u000b\u0011B\u001f\u0002\u00115\f\u00070\u0011:jifD!\"#\u0014\n4\t\u0005\t\u0015!\u0003>\u0003%\u0019w.\u001e8u\rJ|W\u000eC\u0006\nR%M\"\u0011!Q\u0001\n%M\u0013\u0001B5oSR\u0004BaCE+\u001d&\u0019\u0011r\u000b\u0004\u0003\r=\u0003H/[8o\u0011!\u00119)c\r\u0005\u0002%mCCCE/\u0013?J\t'c\u0019\nfA!AqEE\u001a\u0011\u001d!\u0016\u0012\fa\u0001\u0013\u0003Bq!#\u0013\nZ\u0001\u0007Q\bC\u0005\nN%e\u0003\u0013!a\u0001{!Q\u0011\u0012KE-!\u0003\u0005\r!c\u0015\t\u0015%%\u00142\u0007b\u0001\n\u0013IY'\u0001\u0004pM\u001a\u001cX\r^\u000b\u0002{!A\u0011rNE\u001aA\u0003%Q(A\u0004pM\u001a\u001cX\r\u001e\u0011\t\u0011%M\u00142\u0007C\u0005\u0013k\n1\"[:EK\u001aLg.\u001a3BiR!\u00111CE<\u0011\u001dII(#\u001dA\u0002u\n\u0011!\u001b\u0005\u000b\u0013{J\u0019D1A\u0005\u0002%}\u0014aA:fcV\u0011\u0011\u0012\u0011\t\u0005g&\re*C\u0002\n\u0006b\u0014!\"\u00138eKb,GmU3r\u0011%II)c\r!\u0002\u0013I\t)\u0001\u0003tKF\u0004\u0003\u0002CEG\u0013g!\t!c$\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\tL\t\nC\u0004\nz%-\u0005\u0019A\u001f\t\u0011%U\u00152\u0007C\u0001\u0013/\u000bAb\u001d9fG&4\u0017n\u0019+za\u0016$b!a<\n\u001a&}\u0005\u0002CEN\u0013'\u0003\r!#(\u0002\t\u0005\u0014xm\u001d\t\u0005gZ\fy\u000f\u0003\u0005\n\"&M\u0005\u0019AER\u0003\u0019yG\u000f[3sgB)1\"#*\u0002p&\u0019\u0011r\u0015\u0004\u0003\u0015q\u0012X\r]3bi\u0016$ghB\u0004\n,\nB\t!#,\u0002\u001bY\u000b'/\u0011:jif\u001cE.Y:t!\u0011!9#c,\u0007\u000f%U\"\u0005#\u0001\n2N\u0019\u0011r\u0016\u0006\t\u0011\t\u001d\u0015r\u0016C\u0001\u0013k#\"!#,\t\u0015%e\u0016rVI\u0001\n\u0003IY,A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0003\u0013{S3!PE`W\tI\t\r\u0005\u0003\nD&5WBAEc\u0015\u0011I9-#3\u0002\u0013Ut7\r[3dW\u0016$'bAEf\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t%=\u0017R\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007BCEj\u0013_\u000b\n\u0011\"\u0001\nV\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*\"!c6+\t%M\u0013r\u0018\u0005\n\u00137\u0014#\u0019!C\u0001\u0013W\nQ\"T1y)V\u0004H.Z!sSRL\bbBEpE\u0001\u0006I!P\u0001\u000f\u001b\u0006DH+\u001e9mK\u0006\u0013\u0018\u000e^=!\u0011%I\u0019O\tb\u0001\n\u0003IY'A\bNCb\u0004&o\u001c3vGR\f%/\u001b;z\u0011\u001dI9O\tQ\u0001\nu\n\u0001#T1y!J|G-^2u\u0003JLG/\u001f\u0011\t\u0013%-(E1A\u0005\u0002%-\u0014\u0001E'bq\u001a+hn\u0019;j_:\f%/\u001b;z\u0011\u001dIyO\tQ\u0001\nu\n\u0011#T1y\rVt7\r^5p]\u0006\u0013\u0018\u000e^=!\u0011)I\u0019P\tEC\u0002\u0013\u0005\u0011R_\u0001\r!J|G-^2u\u00072\f7o]\u000b\u0003\u0013;B!\"#?#\u0011\u0003\u0005\u000b\u0015BE/\u00035\u0001&o\u001c3vGR\u001cE.Y:tA!Q\u0011R \u0012\t\u0006\u0004%\t!#>\u0002\u0015Q+\b\u000f\\3DY\u0006\u001c8\u000f\u0003\u0006\u000b\u0002\tB\t\u0011)Q\u0005\u0013;\n1\u0002V;qY\u0016\u001cE.Y:tA!Q!R\u0001\u0012\t\u0006\u0004%\t!#>\u0002\u001b\u0019+hn\u0019;j_:\u001cE.Y:t\u0011)QIA\tE\u0001B\u0003&\u0011RL\u0001\u000f\rVt7\r^5p]\u000ec\u0017m]:!\u0011)QiA\tEC\u0002\u0013\u0005\u0011R_\u0001\u0016\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cE.Y:t\u0011)Q\tB\tE\u0001B\u0003&\u0011RL\u0001\u0017\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cE.Y:tA!9!R\u0003\u0012\u0005\u0002)]\u0011!\u0003;va2,G+\u001f9f)\u0011\tyO#\u0007\t\u0011)m!2\u0003a\u0001\u0013;\u000bQ!\u001a7f[NDqAc\b#\t\u0003Q\t#\u0001\u0007gk:\u001cG/[8o)f\u0004X\r\u0006\u0004\u0002p*\r\"R\u0005\u0005\t\rGRi\u00021\u0001\n\u001e\"A!r\u0005F\u000f\u0001\u0004\ty/\u0001\u0004sKN$\b/\u001a\u0005\b\u0015W\u0011C\u0011\u0001F\u0017\u0003Q\t'm\u001d;sC\u000e$h)\u001e8di&|g\u000eV=qKR1\u0011q\u001eF\u0018\u0015cA\u0001Bb\u0019\u000b*\u0001\u0007\u0011R\u0014\u0005\t\u0015OQI\u00031\u0001\u0002p\"9!R\u0007\u0012\u0005\u0002)]\u0012aE<sCB\f%O]1z\u001b\u0016$\bn\u001c3OC6,G\u0003\u0002F\u001d\u0015\u007f\u00012!\bF\u001e\u0013\rQid\u000f\u0002\t)\u0016\u0014XNT1nK\"A!\u0012\tF\u001a\u0001\u0004\ty/\u0001\u0004fY\u0016lG\u000f\u001d\u0005\b\u0015\u000b\u0012C\u0011\u0001F$\u00035I7\u000fV;qY\u0016\u001c\u00160\u001c2pYR!\u00111\u0003F%\u0011\u001d\t\tIc\u0011A\u0002\tDqA#\u0014#\t\u0003Qy%\u0001\tjg\u001a+hn\u0019;j_:\u001c\u00160\u001c2pYR!\u00111\u0003F)\u0011\u001d\t\tIc\u0013A\u0002\tDqA#\u0016#\t\u0003Q9&\u0001\tjgB\u0013x\u000eZ;di:\u001b\u00160\u001c2pYR!\u00111\u0003F-\u0011\u001d\t\tIc\u0015A\u0002\tDqA#\u0018#\t\u0003Qy&A\nv]N\u0004XmY5bY&TX\rZ*z[\n|G\u000eF\u0002c\u0015CBq!!!\u000b\\\u0001\u0007!\rC\u0004\u000bf\t\"\tAc\u001a\u0002+Ut7\u000f]3dS\u0006d\u0017N_3e)f\u0004X-\u0011:hgR!\u0011R\u0014F5\u0011!\u0019yAc\u0019A\u0002\u0005=xa\u0002F7E!\u0005!rN\u0001\u0011\u001b\u0006\u001c'o\\\"p]R,\u0007\u0010\u001e+za\u0016\u0004B\u0001b\n\u000br\u00199!2\u000f\u0012\t\u0002)U$\u0001E'bGJ|7i\u001c8uKb$H+\u001f9f'\rQ\tH\u0003\u0005\t\u0005\u000fS\t\b\"\u0001\u000bzQ\u0011!r\u000e\u0005\t\u0015{R\t\b\"\u0001\u000b\u0000\u00059QO\\1qa2LH\u0003\u0002FA\u0015\u0007\u0003RaCE+\u0003_D\u0001ba\u0004\u000b|\u0001\u0007\u0011q\u001e\u0005\b\u0015\u000f\u0013C\u0011\u0001FE\u0003II7/T1de>\u001cuN\u001c;fqR$\u0016\u0010]3\u0015\t\u0005M!2\u0012\u0005\t\u0007\u001fQ)\t1\u0001\u0002p\"9!r\u0012\u0012\u0005\u0002)E\u0015!F5t/\"LG/\u001a2pq\u000e{g\u000e^3yiRK\b/\u001a\u000b\u0005\u0003'Q\u0019\n\u0003\u0005\u0004\u0010)5\u0005\u0019AAx\u0011\u001dQ9J\tC\u0005\u00153\u000bA#\\1de>\u0014UO\u001c3mKB\u000b'/Y7J]\u001a|G\u0003BAx\u00157C\u0001ba\u0004\u000b\u0016\u0002\u0007\u0011q\u001e\u0005\b\u0015?\u0013C\u0011\u0001FQ\u0003aawn\\6t\u0019&\\W-T1de>\u0014UO\u001c3mKRK\b/\u001a\u000b\u0005\u0003'Q\u0019\u000b\u0003\u0005\u0004\u0010)u\u0005\u0019AAx\u0011\u001dQ9K\tC\u0001\u0015S\u000b\u0011#[:NC\u000e\u0014xNQ;oI2,G+\u001f9f)\u0011\t\u0019Bc+\t\u0011\r=!R\u0015a\u0001\u0003_DqAc,#\t\u0003Q\t,A\rjg\nc\u0017mY6c_bl\u0015m\u0019:p\u0005VtG\r\\3UsB,G\u0003BA\n\u0015gC\u0001ba\u0004\u000b.\u0002\u0007\u0011q\u001e\u0005\b\u0015o\u0013C\u0011\u0001F]\u0003)I7\u000fT5tiRK\b/\u001a\u000b\u0005\u0003'QY\f\u0003\u0005\u0004\u0010)U\u0006\u0019AAx\u0011\u001dQyL\tC\u0001\u0015\u0003\fa\"[:Ji\u0016\u0014\u0018M\u00197f)f\u0004X\r\u0006\u0003\u0002\u0014)\r\u0007\u0002CB\b\u0015{\u0003\r!a<\t\u000f)\u001d'\u0005\"\u0001\u000bJ\u0006!\u0012n\u001d$v]\u000e$\u0018n\u001c8UsB,G)\u001b:fGR$B!a\u0005\u000bL\"A1q\u0002Fc\u0001\u0004\ty\u000fC\u0004\u000bP\n\"\tA#5\u0002#%\u001cH+\u001e9mKRK\b/\u001a#je\u0016\u001cG\u000f\u0006\u0003\u0002\u0014)M\u0007\u0002CB\b\u0015\u001b\u0004\r!a<\t\u000f)]'\u0005\"\u0001\u000bZ\u0006q\u0011n\u001d$v]\u000e$\u0018n\u001c8UsB,G\u0003BA\n\u00157D\u0001ba\u0004\u000bV\u0002\u0007\u0011q\u001e\u0005\b\u0015?\u0014C\u0011\u0001Fq\u0003-I7\u000fV;qY\u0016$\u0016\u0010]3\u0015\t\u0005M!2\u001d\u0005\t\u0007\u001fQi\u000e1\u0001\u0002p\"9!r\u001d\u0012\u0005\u0002)%\u0018a\u0004;va2,7i\\7q_:,g\u000e^:\u0015\t%u%2\u001e\u0005\t\u0007\u001fQ)\u000f1\u0001\u0002p\"Q!r\u001e\u0012\t\u0006\u0004%\t!a$\u0002!A\u0013x\u000eZ;diJ{w\u000e^\"mCN\u001c\b\"\u0003FzE!\u0005\t\u0015)\u0003O\u0003E\u0001&o\u001c3vGR\u0014vn\u001c;DY\u0006\u001c8\u000f\t\u0005\b\u0015o\u0014C\u0011AAm\u0003Q\u0001&o\u001c3vGR|\u0006O]8ek\u000e$\u0018I]5us\"9!2 \u0012\u0005\u0002\u0005e\u0017A\u0006)s_\u0012,8\r^0qe>$Wo\u0019;FY\u0016lWM\u001c;\t\u000f)}(\u0005\"\u0001\u0002Z\u0006\u0001\u0002K]8ek\u000e$x,\u001b;fe\u0006$xN\u001d\u0005\b\u0017\u0007\u0011C\u0011AAm\u0003U\u0001&o\u001c3vGR|\u0006O]8ek\u000e$\bK]3gSbDqac\u0002#\t\u0003\tI.\u0001\tQe>$Wo\u0019;`G\u0006tW)];bY\"912\u0002\u0012\u0005\u0002-5\u0011a\u00039s_\u0012,8\r\u001e)s_*$b!a7\f\u0010-M\u0001bBF\t\u0017\u0013\u0001\rAY\u0001\u0002u\"91RCF\u0005\u0001\u0004i\u0014!\u00016\t\u000f-e!\u0005\"\u0001\f\u001c\u0005qq-\u001a;Qe>$Wo\u0019;Be\u001e\u001cH\u0003BEO\u0017;A\u0001ba\u001a\f\u0018\u0001\u0007\u0011q\u001e\u0015\t\u0017/Y\tcc\n\f,A\u00191bc\t\n\u0007-\u0015bA\u0001\u0006eKB\u0014XmY1uK\u0012\f#a#\u000b\u0002\u001d9{\u0007\u0005\\8oO\u0016\u0014\b%^:fI\u0006\u00121RF\u0001\u0007e9\n\u0014G\f\u0019\t\u000f-E\"\u0005\"\u0001\f4\u0005iQO\\1qa2LXK\\<sCB$B!a<\f6!A1qMF\u0018\u0001\u0004\ty\u000f\u000b\u0005\f0-\u00052rEF\u0016\u0011\u001dYYD\tC\u0001\u0017{\t\u0011cZ3ui\u0016\u0014X*Z7cKJ$\u0016\u0010]3t)\u0019Iijc\u0010\fB!A1qMF\u001d\u0001\u0004\ty\u000fC\u0004\fD-e\u0002\u0019\u0001:\u0002\u000f\u001d,G\u000f^3sg\"91r\t\u0012\u0005\u0002-%\u0013!\u00053s_BtU\u000f\u001c7beflU\r\u001e5pIR!\u0011q^F&\u0011!\u0019ya#\u0012A\u0002\u0005=\bbBF(E\u0011\u00051\u0012K\u0001\u0010M&t\u0017\r\u001c*fgVdG\u000fV=qKR!\u0011q^F*\u0011!\u0019ya#\u0014A\u0002\u0005=\bbBF,E\u0011\u00051\u0012L\u0001\tSN\u001cF/\u00192mKR!\u00111CF.\u0011!\u0019ya#\u0016A\u0002\u0005=\bbBF0E\u0011\u00051\u0012M\u0001\u000bSN4v\u000e\\1uS2,G\u0003BA\n\u0017GB\u0001ba\u0004\f^\u0001\u0007\u0011q\u001e\u0005\b\u0017O\u0012\u0003\u0015)\u0003>\u0003I1x\u000e\\1uS2,'+Z2veNLwN\\:\t\u0011--$\u0005)A\u0005\u0017[\n\u0001\u0003]3oI&twMV8mCRLG.Z:\u0011\u000b-=4R\u000f2\u000e\u0005-E$bAF:i\u00059Q.\u001e;bE2,\u0017\u0002BF<\u0017c\u0012q\u0001S1tQN+G\u000fC\u0004\f|\t\"\ta# \u0002?\u0005\u00147\u000f\u001e:bGR4UO\\2uS>tgi\u001c:Gk:\u001cG/[8o)f\u0004X\r\u0006\u0003\u0002p.}\u0004\u0002CB\b\u0017s\u0002\r!a<\t\u000f-\r%\u0005\"\u0001\f\u0006\u0006\tb-\u001e8di&|gN\u0014\"bg\u0016$\u0016\u0010]3\u0015\t\u0005=8r\u0011\u0005\t\u0007\u001fY\t\t1\u0001\u0002p\"912\u0012\u0012\u0005\u0002-5\u0015!F5t!\u0006\u0014H/[1m\rVt7\r^5p]RK\b/\u001a\u000b\u0005\u0003'Yy\t\u0003\u0005\u0004\u0010-%\u0005\u0019AAx\u0011\u001dY\u0019J\tC\u0001\u0017+\u000bQa]1n\u001f\u001a$2AYFL\u0011!\u0019ya#%A\u0002\u0005=\bbBFNE\u0011\u00051RT\u0001\nCJ\u0014\u0018-\u001f+za\u0016$B!a<\f \"A1\u0012UFM\u0001\u0004\ty/A\u0002be\u001eDqa#*#\t\u0003Y9+\u0001\u0006cs:\u000bW.\u001a+za\u0016$B!a<\f*\"A1\u0012UFR\u0001\u0004\ty\u000fC\u0004\f.\n\"\tac,\u0002\u001d%$XM]1u_J|e\rV=qKR!\u0011q^FY\u0011!\u0019yac+A\u0002\u0005=\bbBF[E\u0011\u00051rW\u0001\u0011U\u00064\u0018MU3qK\u0006$X\r\u001a+za\u0016$B!a<\f:\"A1\u0012UFZ\u0001\u0004\ty\u000fC\u0004\f>\n\"\tac0\u0002\u0015=\u0004H/[8o)f\u0004X\r\u0006\u0003\u0002p.\u0005\u0007\u0002CB\b\u0017w\u0003\r!a<\t\u000f-\u0015'\u0005\"\u0001\fH\u0006\t2oY1mCJ+\u0007/Z1uK\u0012$\u0016\u0010]3\u0015\t\u0005=8\u0012\u001a\u0005\t\u0017C[\u0019\r1\u0001\u0002p\"91R\u001a\u0012\u0005\u0002-=\u0017aB:fcRK\b/\u001a\u000b\u0005\u0003_\\\t\u000e\u0003\u0005\f\".-\u0007\u0019AAx\u0011\u001dY)N\tC\u0001\u0017/\fA\u0003^=qK>3W*Z7cKJt\u0015-\\3e\u000f\u0016$H\u0003BAx\u00173D\u0001ba\u0004\fT\u0002\u0007\u0011q\u001e\u0005\b\u0017;\u0014C\u0011AFp\u0003U!\u0018\u0010]3PM6+WNY3s\u001d\u0006lW\r\u001a%fC\u0012$B!a<\fb\"A1qBFn\u0001\u0004\ty\u000fC\u0004\ff\n\"\tac:\u0002-QL\b/Z(g\u001b\u0016l'-\u001a:OC6,G-\u00119qYf$B!a<\fj\"A1qBFr\u0001\u0004\ty\u000fC\u0004\fn\n\"\tac<\u0002+QL\b/Z(g\u001b\u0016l'-\u001a:OC6,G\r\u0012:paR!\u0011q^Fy\u0011!\u0019yac;A\u0002\u0005=\bbBF{E\u0011\u00051r_\u0001\u0011if\u0004Xm](g'\u0016dWm\u0019;peN$B!#(\fz\"A1qBFz\u0001\u0004\ty\u000fC\u0004\f~\n\"Iac@\u0002'QL\b/Z!sO>3')Y:f)f\u0004Xm\u0014:\u0015\r1\u0005AR\u0002G\b)\u0011\ty\u000fd\u0001\t\u00131\u001512 CA\u00021\u001d\u0011AA8s!\u0015YA\u0012BAx\u0013\raYA\u0002\u0002\ty\tLh.Y7f}!A1qBF~\u0001\u0004\ty\u000fC\u0004\r\u0012-m\b\u0019\u00012\u0002\u0013\t\f7/Z\"mCN\u001c\bb\u0002G\u000bE\u0011\u0005ArC\u0001\rQ\u0006\u001c8+\u001a7fGR|'o\u001d\u000b\u0005\u0003'aI\u0002\u0003\u0005\u0004\u00101M\u0001\u0019AAx\u0011\u001daiB\tC\u0001\u0019?\t\u0001\u0003\u001d:pIV\u001cGoU3mK\u000e$xN]:\u0015\u0007Id\t\u0003\u0003\u0005\u0004h1m\u0001\u0019AAx\u0011\u001da)C\tC\u0001\u0019O\taC]3tk2$xJZ'bi\u000eD\u0017N\\4NKRDw\u000e\u001a\u000b\u0007\u0019Say\u0003$\r\u0015\t\u0005=H2\u0006\u0005\t\u0019[a\u0019\u00031\u0001\n$\u0006Q\u0001/\u0019:b[RK\b/Z:\t\u0011\r=A2\u0005a\u0001\u0003_Dq\u0001\u0016G\u0012\u0001\u0004QI\u0004C\u0004\r6\t\"\t\u0001d\u000e\u0002\u0013\rc\u0017m]:UsB,G\u0003BAx\u0019sA\u0001b#)\r4\u0001\u0007\u0011q\u001e\u0005\b\u0019{\u0011C\u0011\u0001G \u0003YqWM^3s\u0011\u0006\u001cH+\u001f9f!\u0006\u0014\u0018-\\3uKJ\u001cH\u0003BA\n\u0019\u0003Bq!!!\r<\u0001\u0007!\rC\u0004\rF\t\"\t\u0001d\u0012\u0002\u0011\u0015sW/\u001c+za\u0016$B!a<\rJ!9\u0011\u0011\u0011G\"\u0001\u0004\u0011\u0007b\u0002G'E\u0011\u0005ArJ\u0001\u0015G2\f7o]#ySN$XM\u001c;jC2$\u0016\u0010]3\u0015\r\u0005=H\u0012\u000bG+\u0011!a\u0019\u0006d\u0013A\u0002\u0005=\u0018A\u00029sK\u001aL\u0007\u0010C\u0004\u0003l1-\u0003\u0019\u00012\t\u00151e#\u0005#b\u0001\n\u0003aY&\u0001\u0006B]f|F%Z9%KF,\"\u0001$\u0018\u0011\u0007uay&C\u0002\rbE\u0013A\"T3uQ>$7+_7c_2D!\u0002$\u001a#\u0011\u0003\u0005\u000b\u0015\u0002G/\u0003-\te._0%KF$S-\u001d\u0011\t\u00151%$\u0005#b\u0001\n\u0003aY&\u0001\u0007B]f|FEY1oO\u0012*\u0017\u000f\u0003\u0006\rn\tB\t\u0011)Q\u0005\u0019;\nQ\"\u00118z?\u0012\u0012\u0017M\\4%KF\u0004\u0003B\u0003G9E!\u0015\r\u0011\"\u0001\r\\\u0005Q\u0011I\\=`KF,\u0018\r\\:\t\u00151U$\u0005#A!B\u0013ai&A\u0006B]f|V-];bYN\u0004\u0003B\u0003G=E!\u0015\r\u0011\"\u0001\r\\\u0005a\u0011I\\=`Q\u0006\u001c\bnQ8eK\"QAR\u0010\u0012\t\u0002\u0003\u0006K\u0001$\u0018\u0002\u001b\u0005s\u0017p\u00185bg\"\u001cu\u000eZ3!\u0011)a\tI\tEC\u0002\u0013\u0005A2L\u0001\r\u0003:Lx\f^8TiJLgn\u001a\u0005\u000b\u0019\u000b\u0013\u0003\u0012!Q!\n1u\u0013!D!os~#xn\u0015;sS:<\u0007\u0005\u0003\u0006\r\n\nB)\u0019!C\u0001\u00197\na\"\u00118z?\u0012B\u0017m\u001d5%Q\u0006\u001c\b\u000e\u0003\u0006\r\u000e\nB\t\u0011)Q\u0005\u0019;\nq\"\u00118z?\u0012B\u0017m\u001d5%Q\u0006\u001c\b\u000e\t\u0005\u000b\u0019#\u0013\u0003R1A\u0005\u00021m\u0013\u0001D!os~;W\r^\"mCN\u001c\bB\u0003GKE!\u0005\t\u0015)\u0003\r^\u0005i\u0011I\\=`O\u0016$8\t\\1tg\u0002B!\u0002$'#\u0011\u000b\u0007I\u0011\u0001G.\u0003A\te._0jg&s7\u000f^1oG\u0016|e\r\u0003\u0006\r\u001e\nB\t\u0011)Q\u0005\u0019;\n\u0011#\u00118z?&\u001c\u0018J\\:uC:\u001cWm\u00144!\u0011)a\tK\tEC\u0002\u0013\u0005A2L\u0001\u0011\u0003:Lx,Y:J]N$\u0018M\\2f\u001f\u001aD!\u0002$*#\u0011\u0003\u0005\u000b\u0015\u0002G/\u0003E\te._0bg&s7\u000f^1oG\u0016|e\r\t\u0005\u000b\u0019S\u0013\u0003R1A\u0005\u0002\u0015U\u0011\u0001\u00079sS6LG/\u001b<f\u000f\u0016$8\t\\1tg6+G\u000f[8eg\"QAR\u0016\u0012\t\u0002\u0003\u0006K!b\u0006\u00023A\u0014\u0018.\\5uSZ,w)\u001a;DY\u0006\u001c8/T3uQ>$7\u000f\t\u0005\u000b\u0019c\u0013\u0003R1A\u0005\u00021M\u0016aD4fi\u000ec\u0017m]:NKRDw\u000eZ:\u0016\u00051U\u0006\u0003B/\r8\nL1!b\u0007b\u0011)aYL\tE\u0001B\u0003&ARW\u0001\u0011O\u0016$8\t\\1tg6+G\u000f[8eg\u0002Bq\u0001d0#\t\u0003a\t-\u0001\nhKR\u001cE.Y:t%\u0016$XO\u001d8UsB,G\u0003BAx\u0019\u0007D\u0001ba\u0004\r>\u0002\u0007\u0011q\u001e\u0005\b\u0019\u000f\u0014C\u0011\u0001Ge\u0003I\u0011X-\\8wK2\u000bG/\u001a:PE*,7\r^:\u0015\t%uE2\u001a\u0005\t\u0019\u001bd)\r1\u0001\n\u001e\u0006\u0019A\u000f]:\t\u000f1E'\u0005\"\u0001\rT\u00061\"/Z7pm\u0016\u0014V\rZ;oI\u0006tGo\u00142kK\u000e$8\u000f\u0006\u0003\n\u001e2U\u0007\u0002\u0003Gg\u0019\u001f\u0004\r!#(\t\u000f1e'\u0005\"\u0001\r\\\u0006\tbn\u001c:nC2L'0\u001a3QCJ,g\u000e^:\u0015\t%uER\u001c\u0005\t\u0019?d9\u000e1\u0001\n\u001e\u00069\u0001/\u0019:f]R\u001c\bb\u0002GrE\u0011\u0005AR]\u0001\u000eC2d\u0007+\u0019:b[\u0016$XM]:\u0015\u0007Id9\u000f\u0003\u0005\u0004h1\u0005\b\u0019AAx\u0011\u001daYO\tC\u0001\u0019[\f1\u0003^=qKN#(/\u001b8h\u001d>\u0004\u0016mY6bO\u0016$B\u0001d<\r~B!A\u0012\u001fG~\u001b\ta\u0019P\u0003\u0003\rv2]\u0018\u0001\u00027b]\u001eT!\u0001$?\u0002\t)\fg/Y\u0005\u0005\u0013\u000bb\u0019\u0010\u0003\u0005\u0004\u00101%\b\u0019AAx\u0011\u001di\tA\tC\u0001\u001b\u0007\t!C\u0019:jK\u001a\u0004\u0016M]3oiN\u001cFO]5oOR!\u0011\u0012IG\u0003\u0011!ay\u000ed@A\u0002%u\u0005bBG\u0005E\u0011\u0005Q2B\u0001\u000ea\u0006\u0014XM\u001c;t'R\u0014\u0018N\\4\u0015\t%\u0005SR\u0002\u0005\t\u0019?l9\u00011\u0001\n\u001e\"9Q\u0012\u0003\u0012\u0005\u00025M\u0011!\u0005<bYV,\u0007+\u0019:b[N\u001cFO]5oOR!\u0011\u0012IG\u000b\u0011!\u0019y!d\u0004A\u0002\u0005=\bBCG\rE!\u0015\r\u0011\"\u0001\r\\\u0005\trJ\u00196fGR|F\u0005[1tQ\u0012B\u0017m\u001d5\t\u00155u!\u0005#A!B\u0013ai&\u0001\nPE*,7\r^0%Q\u0006\u001c\b\u000e\n5bg\"\u0004\u0003BCG\u0011E!\u0015\r\u0011\"\u0001\r\\\u0005iqJ\u00196fGR|F%Z9%KFD!\"$\n#\u0011\u0003\u0005\u000b\u0015\u0002G/\u00039y%M[3di~#S-\u001d\u0013fc\u0002B!\"$\u000b#\u0011\u000b\u0007I\u0011\u0001G.\u0003=y%M[3di~##-\u00198hI\u0015\f\bBCG\u0017E!\u0005\t\u0015)\u0003\r^\u0005\u0001rJ\u00196fGR|FEY1oO\u0012*\u0017\u000f\t\u0005\u000b\u001bc\u0011\u0003R1A\u0005\u00021m\u0013!C(cU\u0016\u001cGoX3r\u0011)i)D\tE\u0001B\u0003&ARL\u0001\u000b\u001f\nTWm\u0019;`KF\u0004\u0003BCG\u001dE!\u0015\r\u0011\"\u0001\r\\\u0005IqJ\u00196fGR|f.\u001a\u0005\u000b\u001b{\u0011\u0003\u0012!Q!\n1u\u0013AC(cU\u0016\u001cGo\u00188fA!QQ\u0012\t\u0012\t\u0006\u0004%\t\u0001d\u0017\u0002'=\u0013'.Z2u?&\u001c\u0018J\\:uC:\u001cWm\u00144\t\u00155\u0015#\u0005#A!B\u0013ai&\u0001\u000bPE*,7\r^0jg&s7\u000f^1oG\u0016|e\r\t\u0005\u000b\u001b\u0013\u0012\u0003R1A\u0005\u00021m\u0013aE(cU\u0016\u001cGoX1t\u0013:\u001cH/\u00198dK>3\u0007BCG'E!\u0005\t\u0015)\u0003\r^\u0005!rJ\u00196fGR|\u0016m]%ogR\fgnY3PM\u0002B!\"$\u0015#\u0011\u000b\u0007I\u0011\u0001G.\u0003My%M[3di~\u001b\u0018P\\2ie>t\u0017N_3e\u0011)i)F\tE\u0001B\u0003&ARL\u0001\u0015\u001f\nTWm\u0019;`gft7\r\u001b:p]&TX\r\u001a\u0011\t\u00155e#\u0005#b\u0001\n\u0003aY&\u0001\u0007TiJLgnZ0%a2,8\u000f\u0003\u0006\u000e^\tB\t\u0011)Q\u0005\u0019;\nQb\u0015;sS:<w\f\n9mkN\u0004\u0003bBG1E\u0011\u0005\u0011\u0011\\\u0001\u0010\u001f\nTWm\u0019;`O\u0016$8\t\\1tg\"9QR\r\u0012\u0005\u0002\u0005e\u0017\u0001D(cU\u0016\u001cGoX2m_:,\u0007bBG5E\u0011\u0005\u0011\u0011\\\u0001\u0010\u001f\nTWm\u0019;`M&t\u0017\r\\5{K\"9QR\u000e\u0012\u0005\u0002\u0005e\u0017!D(cU\u0016\u001cGo\u00188pi&4\u0017\u0010C\u0004\u000er\t\"\t!!7\u0002!=\u0013'.Z2u?:|G/\u001b4z\u00032d\u0007bBG;E\u0011\u0005\u0011\u0011\\\u0001\u000e\u001f\nTWm\u0019;`KF,\u0018\r\\:\t\u000f5e$\u0005\"\u0001\u0002Z\u0006yqJ\u00196fGR|\u0006.Y:i\u0007>$W\rC\u0004\u000e~\t\"\t!!7\u0002\u001f=\u0013'.Z2u?R|7\u000b\u001e:j]\u001eD!\"$!#\u0011\u000b\u0007I\u0011AAH\u00039y%M[3diJ+gm\u00117bgND\u0011\"$\"#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u001f=\u0013'.Z2u%\u001647\t\\1tg\u0002B!\"$##\u0011\u000b\u0007I\u0011AAH\u0003Y1v\u000e\\1uS2,wJ\u00196fGR\u0014VMZ\"mCN\u001c\b\"CGGE!\u0005\t\u0015)\u0003O\u0003]1v\u000e\\1uS2,wJ\u00196fGR\u0014VMZ\"mCN\u001c\b\u0005\u0003\u0006\u000e\u0012\nB)\u0019!C\u0001\u0005O\u000bACU;oi&lWm\u0015;bi&\u001c7/T8ek2,\u0007BCGKE!\u0005\t\u0015)\u0003\u0002F\u0005)\"+\u001e8uS6,7\u000b^1uS\u000e\u001cXj\u001c3vY\u0016\u0004\u0003BCGME!\u0015\r\u0011\"\u0001\u0003(\u0006\u0011\"i\u001c=fgJ+h\u000eV5nK6{G-\u001e7f\u0011)iiJ\tE\u0001B\u0003&\u0011QI\u0001\u0014\u0005>DXm\u001d*v]RKW.Z'pIVdW\r\t\u0005\u000b\u001bC\u0013\u0003R1A\u0005\u0002\u0011]\u0016!\u0005\"pq\u0016\u001c(+\u001e8US6,7\t\\1tg\"IQR\u0015\u0012\t\u0002\u0003\u0006KAY\u0001\u0013\u0005>DXm\u001d*v]RKW.Z\"mCN\u001c\b\u0005\u0003\u0006\u000e*\nB)\u0019!C\u0001\u0003\u001f\u000b\u0001CQ8yK\u0012tU/\u001c2fe\u000ec\u0017m]:\t\u001355&\u0005#A!B\u0013q\u0015!\u0005\"pq\u0016$g*^7cKJ\u001cE.Y:tA!QQ\u0012\u0017\u0012\t\u0006\u0004%\t!a$\u0002'\t{\u00070\u001a3DQ\u0006\u0014\u0018m\u0019;fe\u000ec\u0017m]:\t\u00135U&\u0005#A!B\u0013q\u0015\u0001\u0006\"pq\u0016$7\t[1sC\u000e$XM]\"mCN\u001c\b\u0005\u0003\u0006\u000e:\nB)\u0019!C\u0001\u0003\u001f\u000b\u0011CQ8yK\u0012\u0014un\u001c7fC:\u001cE.Y:t\u0011%iiL\tE\u0001B\u0003&a*\u0001\nC_b,GMQ8pY\u0016\fgn\u00117bgN\u0004\u0003BCGaE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006q!i\u001c=fI\nKH/Z\"mCN\u001c\b\"CGcE!\u0005\t\u0015)\u0003O\u0003=\u0011u\u000e_3e\u0005f$Xm\u00117bgN\u0004\u0003BCGeE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006y!i\u001c=fINCwN\u001d;DY\u0006\u001c8\u000fC\u0005\u000eN\nB\t\u0011)Q\u0005\u001d\u0006\u0001\"i\u001c=fINCwN\u001d;DY\u0006\u001c8\u000f\t\u0005\u000b\u001b#\u0014\u0003R1A\u0005\u0002\u0005=\u0015!\u0004\"pq\u0016$\u0017J\u001c;DY\u0006\u001c8\u000fC\u0005\u000eV\nB\t\u0011)Q\u0005\u001d\u0006q!i\u001c=fI&sGo\u00117bgN\u0004\u0003BCGmE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006q!i\u001c=fI2{gnZ\"mCN\u001c\b\"CGoE!\u0005\t\u0015)\u0003O\u0003=\u0011u\u000e_3e\u0019>twm\u00117bgN\u0004\u0003BCGqE!\u0015\r\u0011\"\u0001\u0002\u0010\u0006y!i\u001c=fI\u001acw.\u0019;DY\u0006\u001c8\u000fC\u0005\u000ef\nB\t\u0011)Q\u0005\u001d\u0006\u0001\"i\u001c=fI\u001acw.\u0019;DY\u0006\u001c8\u000f\t\u0005\u000b\u001bS\u0014\u0003R1A\u0005\u0002\u0005=\u0015\u0001\u0005\"pq\u0016$Gi\\;cY\u0016\u001cE.Y:t\u0011%iiO\tE\u0001B\u0003&a*A\tC_b,G\rR8vE2,7\t\\1tg\u0002B!\"$=#\u0011\u000b\u0007I\u0011AAH\u00039\u0011u\u000e_3e+:LGo\u00117bgND\u0011\"$>#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u001f\t{\u00070\u001a3V]&$8\t\\1tg\u0002B!\"$?#\u0011\u000b\u0007I\u0011\u0001BT\u0003=\u0011u\u000e_3e+:LG/T8ek2,\u0007BCG\u007fE!\u0005\t\u0015)\u0003\u0002F\u0005\u0001\"i\u001c=fIVs\u0017\u000e^'pIVdW\r\t\u0005\b\u001d\u0003\u0011C\u0011AAm\u00039\u0011u\u000e_3e+:LGoX+O\u0013RCqA$\u0002#\t\u0003\tI.\u0001\bC_b,G-\u00168ji~#\u0016\fU#\t\u00159%!\u0005#b\u0001\n\u0003\ty)A\bB]:|G/\u0019;j_:\u001cE.Y:t\u0011%qiA\tE\u0001B\u0003&a*\u0001\tB]:|G/\u0019;j_:\u001cE.Y:tA!Qa\u0012\u0003\u0012\t\u0006\u0004%\t!a$\u00021\rc\u0017m]:gS2,\u0017I\u001c8pi\u0006$\u0018n\u001c8DY\u0006\u001c8\u000fC\u0005\u000f\u0016\tB\t\u0011)Q\u0005\u001d\u0006I2\t\\1tg\u001aLG.Z!o]>$\u0018\r^5p]\u000ec\u0017m]:!\u0011)qIB\tEC\u0002\u0013\u0005\u0011qR\u0001\u0016'R\fG/[2B]:|G/\u0019;j_:\u001cE.Y:t\u0011%qiB\tE\u0001B\u0003&a*\u0001\fTi\u0006$\u0018nY!o]>$\u0018\r^5p]\u000ec\u0017m]:!\u0011)q\tC\tEC\u0002\u0013\u0005\u0011qR\u0001\u0018\u0003:tw\u000e^1uS>t'+\u001a;f]RLwN\\!uiJD\u0011B$\n#\u0011\u0003\u0005\u000b\u0015\u0002(\u00021\u0005sgn\u001c;bi&|gNU3uK:$\u0018n\u001c8BiR\u0014\b\u0005\u0003\u0006\u000f*\tB)\u0019!C\u0001\u0003\u001f\u000bQ$\u00118o_R\fG/[8o%\u0016$XM\u001c;j_:\u0004v\u000e\\5ds\u0006#HO\u001d\u0005\n\u001d[\u0011\u0003\u0012!Q!\n9\u000ba$\u00118o_R\fG/[8o%\u0016$XM\u001c;j_:\u0004v\u000e\\5ds\u0006#HO\u001d\u0011\t\u00159E\"\u0005#b\u0001\n\u0003\ty)A\u0006Ce&$w-Z\"mCN\u001c\b\"\u0003H\u001bE!\u0005\t\u0015)\u0003O\u00031\u0011%/\u001b3hK\u000ec\u0017m]:!\u0011)qID\tEC\u0002\u0013\u0005\u0011qR\u0001\u0014\u000b2LG-\u00192mK6+G\u000f[8e\u00072\f7o\u001d\u0005\n\u001d{\u0011\u0003\u0012!Q!\n9\u000bA#\u00127jI\u0006\u0014G.Z'fi\"|Gm\u00117bgN\u0004\u0003B\u0003H!E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006)\u0012*\u001c9mS\u000eLGOT8u\r>,h\u000eZ\"mCN\u001c\b\"\u0003H#E!\u0005\t\u0015)\u0003O\u0003YIU\u000e\u001d7jG&$hj\u001c;G_VtGm\u00117bgN\u0004\u0003B\u0003H%E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006AR*[4sCRLwN\\!o]>$\u0018\r^5p]\u000ec\u0017m]:\t\u001395#\u0005#A!B\u0013q\u0015!G'jOJ\fG/[8o\u0003:tw\u000e^1uS>t7\t\\1tg\u0002B!B$\u0015#\u0011\u000b\u0007I\u0011AAH\u0003E\u00196-\u00197b'R\u0014\u0018n\u0019;G!\u0006#HO\u001d\u0005\n\u001d+\u0012\u0003\u0012!Q!\n9\u000b!cU2bY\u0006\u001cFO]5di\u001a\u0003\u0016\t\u001e;sA!Qa\u0012\f\u0012\t\u0006\u0004%\t!a$\u0002\u0017M;\u0018\u000e^2i\u00072\f7o\u001d\u0005\n\u001d;\u0012\u0003\u0012!Q!\n9\u000bAbU<ji\u000eD7\t\\1tg\u0002B!B$\u0019#\u0011\u000b\u0007I\u0011AAH\u00031!\u0016-\u001b7sK\u000e\u001cE.Y:t\u0011%q)G\tE\u0001B\u0003&a*A\u0007UC&d'/Z2DY\u0006\u001c8\u000f\t\u0005\u000b\u001dS\u0012\u0003R1A\u0005\u0002\u0005=\u0015\u0001\u0004,be\u0006\u0014xm]\"mCN\u001c\b\"\u0003H7E!\u0005\t\u0015)\u0003O\u000351\u0016M]1sON\u001cE.Y:tA!Qa\u0012\u000f\u0012\t\u0006\u0004%\t!a$\u0002)Ut7\r[3dW\u0016$7\u000b^1cY\u0016\u001cE.Y:t\u0011%q)H\tE\u0001B\u0003&a*A\u000bv]\u000eDWmY6fIN#\u0018M\u00197f\u00072\f7o\u001d\u0011\t\u00159e$\u0005#b\u0001\n\u0003\ty)\u0001\fv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u001cE.Y:t\u0011%qiH\tE\u0001B\u0003&a*A\fv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u001cE.Y:tA!Qa\u0012\u0011\u0012\t\u0006\u0004%\t!a$\u0002!\t+\u0017M\u001c)s_B,'\u000f^=BiR\u0014\b\"\u0003HCE!\u0005\t\u0015)\u0003O\u0003E\u0011U-\u00198Qe>\u0004XM\u001d;z\u0003R$(\u000f\t\u0005\u000b\u001d\u0013\u0013\u0003R1A\u0005\u0002\u0005=\u0015a\u0006\"p_2,\u0017M\u001c\"fC:\u0004&o\u001c9feRL\u0018\t\u001e;s\u0011%qiI\tE\u0001B\u0003&a*\u0001\rC_>dW-\u00198CK\u0006t\u0007K]8qKJ$\u00180\u0011;ue\u0002B!B$%#\u0011\u000b\u0007I\u0011\u0001C\\\u0003M\u0019u.\u001c9jY\u0016$\u0016.\\3P]2L\u0018\t\u001e;s\u0011%q)J\tE\u0001B\u0003&!-\u0001\u000bD_6\u0004\u0018\u000e\\3US6,wJ\u001c7z\u0003R$(\u000f\t\u0005\u000b\u001d3\u0013\u0003R1A\u0005\u0002\u0005=\u0015A\u0004#faJ,7-\u0019;fI\u0006#HO\u001d\u0005\n\u001d;\u0013\u0003\u0012!Q!\n9\u000bq\u0002R3qe\u0016\u001c\u0017\r^3e\u0003R$(\u000f\t\u0005\u000b\u001dC\u0013\u0003R1A\u0005\u0002\u0005=\u0015A\u0005#faJ,7-\u0019;fI:\u000bW.Z!uiJD\u0011B$*#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002'\u0011+\u0007O]3dCR,GMT1nK\u0006#HO\u001d\u0011\t\u00159%&\u0005#b\u0001\n\u0003\ty)A\rEKB\u0014XmY1uK\u0012Le\u000e[3sSR\fgnY3BiR\u0014\b\"\u0003HWE!\u0005\t\u0015)\u0003O\u0003i!U\r\u001d:fG\u0006$X\rZ%oQ\u0016\u0014\u0018\u000e^1oG\u0016\fE\u000f\u001e:!\u0011)q\tL\tEC\u0002\u0013\u0005\u0011qR\u0001\u0019\t\u0016\u0004(/Z2bi\u0016$wJ^3se&$\u0017N\\4BiR\u0014\b\"\u0003H[E!\u0005\t\u0015)\u0003O\u0003e!U\r\u001d:fG\u0006$X\rZ(wKJ\u0014\u0018\u000eZ5oO\u0006#HO\u001d\u0011\t\u00159e&\u0005#b\u0001\n\u0003\ty)\u0001\u0006OCRLg/Z!uiJD\u0011B$0#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u00179\u000bG/\u001b<f\u0003R$(\u000f\t\u0005\u000b\u001d\u0003\u0014\u0003R1A\u0005\u0002\u0005=\u0015A\u0003*f[>$X-\u0011;ue\"IaR\u0019\u0012\t\u0002\u0003\u0006KAT\u0001\f%\u0016lw\u000e^3BiR\u0014\b\u0005\u0003\u0006\u000fJ\nB)\u0019!C\u0001\u0003\u001f\u000b\u0001cU2bY\u0006Le\u000e\\5oK\u000ec\u0017m]:\t\u001395'\u0005#A!B\u0013q\u0015!E*dC2\f\u0017J\u001c7j]\u0016\u001cE.Y:tA!Qa\u0012\u001b\u0012\t\u0006\u0004%\t!a$\u0002%M\u001b\u0017\r\\1O_&sG.\u001b8f\u00072\f7o\u001d\u0005\n\u001d+\u0014\u0003\u0012!Q!\n9\u000b1cU2bY\u0006tu.\u00138mS:,7\t\\1tg\u0002B!B$7#\u0011\u000b\u0007I\u0011AAH\u0003Q\u0019VM]5bYZ+'o]5p]VKE)\u0011;ue\"IaR\u001c\u0012\t\u0002\u0003\u0006KAT\u0001\u0016'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bE\u000f\u001e:!\u0011)q\tO\tEC\u0002\u0013\u0005a2]\u0001\u001b'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000beN\\8uCRLwN\\\u000b\u0003\u001dK\u00042!\bHt\u0013\u0011qIOd;\u0003\u001d\u0005sgn\u001c;bi&|g.\u00138g_&\u0019aR\u001e\u0002\u0003\u001f\u0005sgn\u001c;bi&|g.\u00138g_ND!B$=#\u0011\u0003\u0005\u000b\u0015\u0002Hs\u0003m\u0019VM]5bYZ+'o]5p]VKE)\u00118o_R\fG/[8oA!QaR\u001f\u0012\t\u0006\u0004%\t!a$\u0002!M\u0003XmY5bY&TX\rZ\"mCN\u001c\b\"\u0003H}E!\u0005\t\u0015)\u0003O\u0003E\u0019\u0006/Z2jC2L'0\u001a3DY\u0006\u001c8\u000f\t\u0005\u000b\u001d{\u0014\u0003R1A\u0005\u0002\u0005=\u0015a\u0003+ie><8o\u00117bgND\u0011b$\u0001#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002\u0019QC'o\\<t\u00072\f7o\u001d\u0011\t\u0015=\u0015!\u0005#b\u0001\n\u0003\ty)A\u0007Ue\u0006t7/[3oi\u0006#HO\u001d\u0005\n\u001f\u0013\u0011\u0003\u0012!Q!\n9\u000ba\u0002\u0016:b]NLWM\u001c;BiR\u0014\b\u0005\u0003\u0006\u0010\u000e\tB)\u0019!C\u0001\u0003\u001f\u000ba\"\u00168dQ\u0016\u001c7.\u001a3DY\u0006\u001c8\u000fC\u0005\u0010\u0012\tB\t\u0011)Q\u0005\u001d\u0006yQK\\2iK\u000e\\W\rZ\"mCN\u001c\b\u0005\u0003\u0006\u0010\u0016\tB)\u0019!C\u0001\to\u000bA#\u00168dQ\u0016\u001c7.\u001a3C_VtGm]\"mCN\u001c\b\"CH\rE!\u0005\t\u0015)\u0003c\u0003U)fn\u00195fG.,GMQ8v]\u0012\u001c8\t\\1tg\u0002B!b$\b#\u0011\u000b\u0007I\u0011AAH\u0003I)fn\u001d9fG&\fG.\u001b>fI\u000ec\u0017m]:\t\u0013=\u0005\"\u0005#A!B\u0013q\u0015aE+ogB,7-[1mSj,Gm\u00117bgN\u0004\u0003BCH\u0013E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006aak\u001c7bi&dW-\u0011;ue\"Iq\u0012\u0006\u0012\t\u0002\u0003\u0006KAT\u0001\u000e->d\u0017\r^5mK\u0006#HO\u001d\u0011\t\u0015=5\"\u0005#b\u0001\n\u0003\ty)A\u000bCK\u0006tw)\u001a;uKJ$\u0016M]4fi\u000ec\u0017m]:\t\u0013=E\"\u0005#A!B\u0013q\u0015A\u0006\"fC:<U\r\u001e;feR\u000b'oZ3u\u00072\f7o\u001d\u0011\t\u0015=U\"\u0005#b\u0001\n\u0003\ty)A\u000bCK\u0006t7+\u001a;uKJ$\u0016M]4fi\u000ec\u0017m]:\t\u0013=e\"\u0005#A!B\u0013q\u0015A\u0006\"fC:\u001cV\r\u001e;feR\u000b'oZ3u\u00072\f7o\u001d\u0011\t\u0015=u\"\u0005#b\u0001\n\u0003\ty)\u0001\tGS\u0016dG\rV1sO\u0016$8\t\\1tg\"Iq\u0012\t\u0012\t\u0002\u0003\u0006KAT\u0001\u0012\r&,G\u000e\u001a+be\u001e,Go\u00117bgN\u0004\u0003BCH#E!\u0015\r\u0011\"\u0001\u0002\u0010\u0006\tr)\u001a;uKJ$\u0016M]4fi\u000ec\u0017m]:\t\u0013=%#\u0005#A!B\u0013q\u0015AE$fiR,'\u000fV1sO\u0016$8\t\\1tg\u0002B!b$\u0014#\u0011\u000b\u0007I\u0011AAH\u0003A\u0001\u0016M]1n)\u0006\u0014x-\u001a;DY\u0006\u001c8\u000fC\u0005\u0010R\tB\t\u0011)Q\u0005\u001d\u0006\t\u0002+\u0019:b[R\u000b'oZ3u\u00072\f7o\u001d\u0011\t\u0015=U#\u0005#b\u0001\n\u0003\ty)A\tTKR$XM\u001d+be\u001e,Go\u00117bgND\u0011b$\u0017#\u0011\u0003\u0005\u000b\u0015\u0002(\u0002%M+G\u000f^3s)\u0006\u0014x-\u001a;DY\u0006\u001c8\u000f\t\u0005\u000b\u001f;\u0012\u0003R1A\u0005\u0002\u0005=\u0015!E(cU\u0016\u001cG\u000fV1sO\u0016$8\t\\1tg\"Iq\u0012\r\u0012\t\u0002\u0003\u0006KAT\u0001\u0013\u001f\nTWm\u0019;UCJ<W\r^\"mCN\u001c\b\u0005\u0003\u0006\u0010f\tB)\u0019!C\u0001\u0003\u001f\u000b\u0001c\u00117bgN$\u0016M]4fi\u000ec\u0017m]:\t\u0013=%$\u0005#A!B\u0013q\u0015!E\"mCN\u001cH+\u0019:hKR\u001cE.Y:tA!QqR\u000e\u0012\t\u0006\u0004%\t!a$\u0002#5+G\u000f[8e)\u0006\u0014x-\u001a;DY\u0006\u001c8\u000fC\u0005\u0010r\tB\t\u0011)Q\u0005\u001d\u0006\u0011R*\u001a;i_\u0012$\u0016M]4fi\u000ec\u0017m]:!\u0011)y)H\tEC\u0002\u0013\u0005\u0011qR\u0001\u0015\u0019\u0006tw-^1hK\u001a+\u0017\r^;sK\u0006sgn\u001c;\t\u0013=e$\u0005#A!B\u0013q\u0015!\u0006'b]\u001e,\u0018mZ3GK\u0006$XO]3B]:|G\u000f\t\u0005\u000b\u001f{\u0012\u0003R1A\u0005\u0002\t\u001d\u0016!\u00067b]\u001e,\u0018mZ3GK\u0006$XO]3N_\u0012,H.\u001a\u0005\u000b\u001f\u0003\u0013\u0003\u0012!Q!\n\u0005\u0015\u0013A\u00067b]\u001e,\u0018mZ3GK\u0006$XO]3N_\u0012,H.\u001a\u0011\t\u000f=\u0015%\u0005\"\u0001\u0010\b\u0006\u0001\u0012n]'fi\u0006\feN\\8uCRLwN\u001c\u000b\u0005\u0003'yI\tC\u0004\u0002\u0002>\r\u0005\u0019\u00012\t\u0015=5%\u0005#b\u0001\n\u0003a\u0019,A\bnKR\f\u0017I\u001c8pi\u0006$\u0018n\u001c8t\u0011)y\tJ\tE\u0001B\u0003&ARW\u0001\u0011[\u0016$\u0018-\u00118o_R\fG/[8og\u0002Bqa$&#\t\u0003y9*A\feK\u001a\fW\u000f\u001c;B]:|G/\u0019;j_:$\u0016M]4fiR\u0019!m$'\t\u0011=mu2\u0013a\u0001\u001f;\u000b\u0011\u0001\u001e\t\u0004;=}\u0015\u0002BHQ\u001fG\u0013A\u0001\u0016:fK&\u0019qR\u0015\u0002\u0003\u000bQ\u0013X-Z:\t\u0015=%&\u0005#b\u0001\n\u0003\ty)A\u000bB]:|G/\u0019;j_:$UMZ1vYR\fE\u000f\u001e:\t\u0013=5&\u0005#A!B\u0013q\u0015AF!o]>$\u0018\r^5p]\u0012+g-Y;mi\u0006#HO\u001d\u0011\t\u000f=E&\u0005\"\u0003\u00104\u0006\u0011b-\u0019;bY6K7o]5oONKXNY8m)%QwRWH]\u001fw{y\fC\u0004\u00108>=\u0006\u0019\u00012\u0002\u000b=<h.\u001a:\t\rQ{y\u000b1\u00019\u0011)yild,\u0011\u0002\u0003\u0007\u0011\u0012I\u0001\u0005o\"\fG\u000f\u0003\u0006\u0010B>=\u0006\u0013!a\u0001\u0013\u0003\n\u0001\"\u00193eK:$W/\u001c\u0005\b\u001f\u000b\u0014C\u0011AHd\u0003I9W\r\u001e'b]\u001e,\u0018mZ3GK\u0006$XO]3\u0015\u000b\t|Imd3\t\u000fQ{\u0019\r1\u0001\nB!IqrWHb!\u0003\u0005\rA\u0019\u0005\b\u001f\u001f\u0014C\u0011AHi\u0003)!XM]7NK6\u0014WM\u001d\u000b\u0006E>MwR\u001b\u0005\b\u001fo{i\r1\u0001c\u0011\u001d!vR\u001aa\u0001\u0013\u0003Bqa$7#\t\u0003yY.A\bgS:$g*Y7fI6+WNY3s)\u0015\u0011wR\\Hq\u0011\u001dyynd6A\u0002a\n\u0001BZ;mY:\u000bW.\u001a\u0005\b\u001fG|9\u000e1\u0001c\u0003\u0011\u0011xn\u001c;\t\u000f=e'\u0005\"\u0001\u0010hR)!m$;\u0010p\"Aq2^Hs\u0001\u0004yi/\u0001\u0003tK\u001e\u001c\bcA:wq!9q2]Hs\u0001\u0004\u0011\u0007bBHzE\u0011\u0005qR_\u0001\nO\u0016$X*Z7cKJ$RAYH|\u001fsDqad.\u0010r\u0002\u0007!\r\u0003\u0004U\u001fc\u0004\r\u0001\u000f\u0005\b\u001f{\u0014C\u0011AH\u0000\u000399W\r^'f[\n,'OV1mk\u0016$b!a7\u0011\u0002A\r\u0001bBH\\\u001fw\u0004\rA\u0019\u0005\u0007)>m\b\u0019\u0001\u001d\t\u000fA\u001d!\u0005\"\u0001\u0011\n\u0005yq-\u001a;NK6\u0014WM]'pIVdW\r\u0006\u0004\u0002FA-\u0001S\u0002\u0005\b\u001fo\u0003*\u00011\u0001c\u0011\u0019!\u0006S\u0001a\u0001q!9\u0001\u0013\u0003\u0012\u0005\u0002AM\u0011!D4fiRK\b/Z'f[\n,'\u000f\u0006\u0004\u0011\u0016Am\u0001S\u0004\t\u0004;A]\u0011b\u0001I\r#\nQA+\u001f9f'fl'm\u001c7\t\u000f=]\u0006s\u0002a\u0001E\"1A\u000be\u0004A\u0002aBq\u0001%\t#\t\u0003\u0001\u001a#\u0001\bhKRlU-\u001c2fe\u000ec\u0017m]:\u0015\u000b9\u0003*\u0003e\n\t\u000f=]\u0006s\u0004a\u0001E\"1A\u000be\bA\u0002aBq\u0001e\u000b#\t\u0003\u0001j#A\bhKRlU-\u001c2fe6+G\u000f[8e)\u0019\tY\u000ee\f\u00112!9qr\u0017I\u0015\u0001\u0004\u0011\u0007B\u0002+\u0011*\u0001\u0007\u0001\b\u0003\u0006\u00116\tB)\u0019!C\u0005!o\tA\"\u001a:bgV\u0014X\r\u00155bg\u0016,\"\u0001%\u000f\u0011\tAm\u0002SH\u0007\u0002\u0005%\u0019\u0001s\b\u0002\u0003\u000bAC\u0017m]3\t\u0015A\r#\u0005#A!B\u0013\u0001J$A\u0007fe\u0006\u001cXO]3QQ\u0006\u001cX\r\t\u0005\b!\u000f\u0012C\u0011\u0001I%\u0003I9W\r^'f[\n,'/\u00134EK\u001aLg.\u001a3\u0015\u000b\t\u0004Z\u0005%\u0014\t\u000f=]\u0006S\ta\u0001E\"1A\u000b%\u0012A\u0002aBq\u0001%\u0015#\t\u0003\u0001\u001a&A\u0004hKR$Um\u00197\u0015\u000b\t\u0004*\u0006e\u0016\t\u000f=]\u0006s\na\u0001E\"1A\u000be\u0014A\u0002aBq\u0001e\u0017#\t\u0003\u0001j&\u0001\thKR$Um\u00197JM\u0012+g-\u001b8fIR)!\re\u0018\u0011b!9qr\u0017I-\u0001\u0004\u0011\u0007B\u0002+\u0011Z\u0001\u0007\u0001\bC\u0004\u0011f\t\"I\u0001e\u001a\u0002\u00119,w/\u00117jCN$\u0002ba\u001e\u0011jA-\u0004S\u000e\u0005\b\u001fo\u0003\u001a\u00071\u0001c\u0011\u0019!\u00063\ra\u0001+\"A\u0001s\u000eI2\u0001\u0004\ty/A\u0003bY&\f7\u000fC\u0004\u0011t\t\"I\u0001%\u001e\u0002!M\u0004XmY5bYB{G._\"mCN\u001cHC\u0002I<!\u007f\u0002\n\tF\u0002O!sB\u0001\u0002e\u001f\u0011r\u0001\u0007\u0001SP\u0001\ta\u0006\u0014XM\u001c;G]B)1\u0002 2\u0002p\"1A\u000b%\u001dA\u0002UC\u0001\u0002e!\u0011r\u0001\u0007\u0001SQ\u0001\u0006M2\fwm\u001d\t\u0004\u0017A\u001d\u0015b\u0001IE\r\t!Aj\u001c8h\u0011\u001d\u0001jI\tC\u0001!\u001f\u000bQB\\3x!>d\u00170T3uQ>$GC\u0003II!O\u0003Z\u000b%,\u00110R!AR\fIJ\u0011!\u0001*\ne#A\u0002A]\u0015\u0001C2sK\u0006$XM\u00128\u0011\u0007u\u0001J*\u0002\u0004\u0011\u001c\u0002!\u0001S\u0014\u0002\u0012!>d\u00170T3uQ>$7I]3bi>\u0014\b#B\u0006}eB}\u0005cB\u0006\u0011\"B\u0015\u0016q^\u0005\u0004!G3!A\u0002+va2,'\u0007E\u0003\f\u0013+Ji\nC\u0004\u0011*B-\u0005\u0019A\u001f\u0002\u001dQL\b/\u001a)be\u0006l7i\\;oi\"9qr\u0017IF\u0001\u0004\u0011\u0007b\u0002+\u0011\f\u0002\u0007!\u0012\b\u0005\t!\u0007\u0003Z\t1\u0001\u0011\u0006\"9\u00013\u0017\u0012\u0005\u0002AU\u0016A\u00058foR\u000bd*\u001e7mCJLX*\u001a;i_\u0012$\u0002\u0002e.\u0011<Bu\u0006s\u0018\u000b\u0005\u0019;\u0002J\f\u0003\u0005\u0011\u0016BE\u0006\u0019\u0001I?\u0011\u001dy9\f%-A\u0002\tDq\u0001\u0016IY\u0001\u0004QI\u0004\u0003\u0005\u0011\u0004BE\u0006\u0019\u0001IC\u0011\u001d\u0001\u001aM\tC\u0001!\u000b\f1C\\3x)Fru\u000eU1sC6\u001cX*\u001a;i_\u0012$\u0002\u0002e2\u0011LB5\u0007s\u001a\u000b\u0005\u0019;\u0002J\r\u0003\u0005\u0011\u0016B\u0005\u0007\u0019\u0001I?\u0011\u001dy9\f%1A\u0002\tDq\u0001\u0016Ia\u0001\u0004QI\u0004\u0003\u0005\u0011\u0004B\u0005\u0007\u0019\u0001IC\u0011)\u0001\u001aN\tEC\u0002\u0013\u0005QQC\u0001\u000fSN\u0004\u0006.\u00198u_6\u001cE.Y:t\u0011)\u0001:N\tE\u0001B\u0003&QqC\u0001\u0010SN\u0004\u0006.\u00198u_6\u001cE.Y:tA!Q\u00013\u001c\u0012\t\u0006\u0004%\t\u0001%8\u0002)MLh\u000e\u001e5fi&\u001c7i\u001c:f\u00072\f7o]3t+\t\u0001z\u000eE\u00032\u0005\u000b\u0002\nO\u0005\u0003\u0011dBUaA\u0002Is\u0001\u0001\u0001\nO\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0002\u0005\u0011jB\r(\u0011\u0001Iv\u0005I!\u0016\u0010]3PM\u000ecwN\\3e'fl'm\u001c7\u0012\u00079\u0003*\u0002\u0003\u0006\u0011p\nB\t\u0011)Q\u0005!?\fQc]=oi\",G/[2D_J,7\t\\1tg\u0016\u001c\b\u0005\u0003\u0006\u0011t\nB)\u0019!C\u0001!k\fAc]=oi\",G/[2D_J,W*\u001a;i_\u0012\u001cXC\u0001I|!\u0015\t$Q\tG/\u0011)\u0001ZP\tE\u0001B\u0003&\u0001s_\u0001\u0016gftG\u000f[3uS\u000e\u001cuN]3NKRDw\u000eZ:!\u0011)\u0001zP\tEC\u0002\u0013\u0005!\u0011I\u0001\u0014Q&T\u0017mY6fI\u000e{'/Z\"mCN\u001cXm\u001d\u0005\u000b#\u0007\u0011\u0003\u0012!Q!\n\t\r\u0013\u0001\u00065jU\u0006\u001c7.\u001a3D_J,7\t\\1tg\u0016\u001c\b\u0005\u0003\u0006\u0012\b\tB)\u0019!C\u0001#\u0013\t1d]=nE>d7OT8u!J,7/\u001a8u\u0013:\u0014\u0015\u0010^3d_\u0012,WCAI\u0006!\u0015\t$QII\u0007%\r\tzA\u0019\u0004\u0007!K\u0004\u0001!%\u0004\t\u000fQ\u000bzA\"\u0001\u0012\u0014U\u0011\u0011S\u0003\n\u0004#/AdA\u0002Is\u0001\u0001\t*\u0002\u0003\u0005\u0012\u001cE]a\u0011AI\u000f\u0003\u001dqWm\u001e(b[\u0016$B!e\b\u00124I\u0019\u0011\u0013\u0005\u001d\u0007\rA\u0015\b\u0001AI\u0010\u0011!\t*#%\t\u0007\u0002E\u001d\u0012\u0001\u00028fqR,\u0012\u0001\u000f\u0003\t#W\t\nC!\u0001\u0012.\taA\u000b[5t\u001d\u0006lW\rV=qKF\u0019\u0011s\u0006\u001d\u0013\u000bEERK#\u000f\u0007\rA\u0015\b\u0001AI\u0018\u0011!\t*$%\u0007A\u0002%\u0005\u0013aA:ue\"A\u0011\u0013HI\f\r\u0003\tZ$A\u0004tk\nt\u0015-\\3\u0015\rE}\u0011SHI!\u0011\u001d\tz$e\u000eA\u0002u\nAA\u001a:p[\"9\u00113II\u001c\u0001\u0004i\u0014A\u0001;p\u0011!\t:%e\u0006\u0007\u0002E%\u0013!D2p[B\fg.[8o\u001d\u0006lW-\u0006\u0002\u0012LI\u0019\u0011S\n\u001d\u0007\rA\u0015\b\u0001AI&\u0011!\t*#%\u0014\u0007\u0002E\u001dB\u0001CI\u0016#\u001b\u0012\t!e\u0015\u0012\u0007EU\u0003HE\u0003\u0012X)eRK\u0002\u0004\u0011f\u0002\u0001\u0011S\u000b\u0005\t#K\t:B\"\u0001\u0012\\U\u0011\u0011s\u0004\u0003\t#W\t:B!\u0001\u0012`E!\u0011\u0013MI\u0010%\u0015\t\u001a'\u0016F\u001d\r\u0019\u0001*\u000f\u0001\u0001\u0012b!A\u0011sMI\b\r\u0003\t\u001a\"A\u0004sC^t\u0017-\\3\u0005\u0011A%\u0018s\u0002B\u0001#W\nB!%\u001c\u0012rI)\u0011s\u000e(\u0002\\\u001a1\u0001S\u001d\u0001\u0001#[\u00122!e\u001dc\r\u0019\u0001*\u000f\u0001\u0001\u0012r!9A+e\u001d\u0007\u0002Em\u0003\u0002CI4#g2\t!e\u0017\u0005\u0011A%\u00183\u000fB\u0001#w\nB!% \u0012\u0004B\u00191\"e \n\u0007E\u0005eA\u0001\u0003Ok2d'cAICE\u001a1\u0001S\u001d\u0001\u0001#\u0007#\u0001\"%#\u0012\u0006\n\u0005\u0011S\u0006\u0002\t\u001d\u0006lW\rV=qK\u0012A\u0011\u0013RI:\u0005\u0003\tz\u0006\u0002\u0005\u0012\nF=!\u0011AIH#\u0011\t\n*%\u0006\u0013\u000bEMUK#\u000f\u0007\rA\u0015\b\u0001AII\u0011)\t:J\tE\u0001B\u0003&\u00113B\u0001\u001dgfl'm\u001c7t\u001d>$\bK]3tK:$\u0018J\u001c\"zi\u0016\u001cw\u000eZ3!\u0011)\tZJ\tEC\u0002\u0013\u0005QQC\u0001\u001aSN\u0004vn]:jE2,7+\u001f8uQ\u0016$\u0018n\u0019)be\u0016tG\u000f\u0003\u0006\u0012 \nB\t\u0011)Q\u0005\u000b/\t!$[:Q_N\u001c\u0018N\u00197f'ftG\u000f[3uS\u000e\u0004\u0016M]3oi\u0002B!\"e)#\u0011\u000b\u0007I\u0011BC\u000b\u0003Q\u0011w\u000e_3e-\u0006dW/Z\"mCN\u001cXm]*fi\"Q\u0011s\u0015\u0012\t\u0002\u0003\u0006K!b\u0006\u0002+\t|\u00070\u001a3WC2,Xm\u00117bgN,7oU3uA!9\u00113\u0016\u0012\u0005\u0002E5\u0016!F5t!JLW.\u001b;jm\u00164\u0016\r\\;f\u00072\f7o\u001d\u000b\u0005\u0003'\tz\u000bC\u0004\u0002\u0002F%\u0006\u0019\u00012\t\u000fEM&\u0005\"\u0001\u00126\u0006!\u0012n\u001d)sS6LG/\u001b<f-\u0006dW/\u001a+za\u0016$B!a\u0005\u00128\"A1qBIY\u0001\u0004\ty\u000fC\u0004\u0012<\n\"\t!%0\u0002#%\u001c(i\u001c=fIZ\u000bG.^3DY\u0006\u001c8\u000f\u0006\u0003\u0002\u0014E}\u0006bBAA#s\u0003\rA\u0019\u0005\b#\u0007\u0014C\u0011AIc\u0003E)hNY8yK\u00124\u0016\r\\;f\u00072\f7o\u001d\u000b\u0004EF\u001d\u0007bBAA#\u0003\u0004\rA\u0019\u0005\b#\u0017\u0014C\u0011AIg\u0003II7OT;nKJL7MV1mk\u0016$\u0016\u0010]3\u0015\t\u0005M\u0011s\u001a\u0005\t\u0007\u001f\tJ\r1\u0001\u0002p\"9\u00113\u001b\u0012\u0005\u0002EU\u0017!C:jO:\fG/\u001e:f)\u0011I\t%e6\t\u0011\r=\u0011\u0013\u001ba\u0001\u0003_Da!#\u0015#\t\u00031bABIoE\u0001\tzN\u0001\fV]&4XM]:f\t\u0016\u0004XM\u001c3f]R$\u0016\u0010]3t'\r\tZN\u0003\u0005\f#G\fZN!A!\u0002\u0013yi*\u0001\u0005v]&4XM]:f\u0011!\u00119)e7\u0005\u0002E\u001dH\u0003BIu#W\u0004B\u0001b\n\u0012\\\"A\u00113]Is\u0001\u0004yi\nC\u0006\u0012pFm\u0007R1A\u0005\u0002\u00055\u0018\u0001\u00038b[\u0016$\u0016\u0010]3\t\u0017EM\u00183\u001cE\u0001B\u0003&\u0011q^\u0001\n]\u0006lW\rV=qK\u0002B1\"e>\u0012\\\"\u0015\r\u0011\"\u0001\u0002n\u0006AQn\u001c3t)f\u0004X\rC\u0006\u0012|Fm\u0007\u0012!Q!\n\u0005=\u0018!C7pIN$\u0016\u0010]3!\u0011-\tz0e7\t\u0006\u0004%\t!!<\u0002\u0013\u0019d\u0017mZ:UsB,\u0007b\u0003J\u0002#7D\t\u0011)Q\u0005\u0003_\f!B\u001a7bON$\u0016\u0010]3!\u0011-\u0011:!e7\t\u0006\u0004%\t!!<\u0002\u0015MLXNY8m)f\u0004X\rC\u0006\u0013\fEm\u0007\u0012!Q!\n\u0005=\u0018aC:z[\n|G\u000eV=qK\u0002B1Be\u0004\u0012\\\"\u0015\r\u0011\"\u0001\u0002n\u0006AAO]3f)f\u0004X\rC\u0006\u0013\u0014Em\u0007\u0012!Q!\n\u0005=\u0018!\u0003;sK\u0016$\u0016\u0010]3!\u0011-\u0011:\"e7\t\u0006\u0004%\t!!<\u0002\u0017\r\f7/\u001a#fMRK\b/\u001a\u0005\f%7\tZ\u000e#A!B\u0013\ty/\u0001\u0007dCN,G)\u001a4UsB,\u0007\u0005C\u0006\u0013 Em\u0007R1A\u0005\u0002\u00055\u0018\u0001\u00047jMR\f'\r\\3UsB,\u0007b\u0003J\u0012#7D\t\u0011)Q\u0005\u0003_\fQ\u0002\\5gi\u0006\u0014G.\u001a+za\u0016\u0004\u0003b\u0003J\u0014#7D)\u0019!C\u0001\u0003[\fa\"\u001e8mS\u001a$\u0018M\u00197f)f\u0004X\rC\u0006\u0013,Em\u0007\u0012!Q!\n\u0005=\u0018aD;oY&4G/\u00192mKRK\b/\u001a\u0011\t\u0017I=\u00123\u001cEC\u0002\u0013\u0005\u0011Q^\u0001\u0011SR,'/\u00192mKR\u0013X-\u001a+za\u0016D1Be\r\u0012\\\"\u0005\t\u0015)\u0003\u0002p\u0006\t\u0012\u000e^3sC\ndW\r\u0016:fKRK\b/\u001a\u0011\t\u0017I]\u00123\u001cEC\u0002\u0013\u0005\u0011Q^\u0001\rY&\u001cH\u000f\u0016:fKRK\b/\u001a\u0005\f%w\tZ\u000e#A!B\u0013\ty/A\u0007mSN$HK]3f)f\u0004X\r\t\u0005\f%\u007f\tZ\u000e#b\u0001\n\u0003\ti/\u0001\tmSN$H*[:u)J,W\rV=qK\"Y!3IIn\u0011\u0003\u0005\u000b\u0015BAx\u0003Ea\u0017n\u001d;MSN$HK]3f)f\u0004X\r\t\u0005\t%\u000f\nZ\u000e\"\u0001\u0013J\u0005\u0011RO\\5wKJ\u001cX-T3nE\u0016\u0014H+\u001f9f)\u0011\tyOe\u0013\t\rQ\u0013*\u00051\u0001V\r\u0019\u0011zE\t\u0002\u0013R\tq!+\u001e8EK\u001aLg.\u001b;j_:\u001c8c\u0001J'\u0015!A!q\u0011J'\t\u0003\u0011*\u0006\u0006\u0002\u0013XA!Aq\u0005J'\u0011-\u0011ZF%\u0014\t\u0006\u0004%\t!!7\u0002\u001fM#(/\u001b8h\u0003\u0012$w\f\n9mkND1Be\u0018\u0013N!\u0005\t\u0015)\u0003\u0002\\\u0006\u00012\u000b\u001e:j]\u001e\fE\rZ0%a2,8\u000f\t\u0005\t%G\u0012j\u0005\"\u0001\u0013f\u0005\u0001\u0012n]*ue&tw-\u00113eSRLwN\u001c\u000b\u0005\u0003'\u0011:\u0007C\u0004\u0002\u0002J\u0005\u0004\u0019\u00012\t\u0017I-$S\nEC\u0002\u0013\u0005\u0011\u0011\\\u0001\u0010'R\u0014\u0018N\\4D_:$X\r\u001f;`M\"Y!s\u000eJ'\u0011\u0003\u0005\u000b\u0015BAn\u0003A\u0019FO]5oO\u000e{g\u000e^3yi~3\u0007\u0005C\u0006\u0013tI5\u0003R1A\u0005\u0002\u0005=\u0015aD!se><\u0018i]:pG\u000ec\u0017m]:\t\u0015I]$S\nE\u0001B\u0003&a*\u0001\tBeJ|w/Q:t_\u000e\u001cE.Y:tA!A!3\u0010J'\t\u0003\u0011j(\u0001\u0007jg\u0006\u0013(o\\<BgN|7\r\u0006\u0003\u0002\u0014I}\u0004bBAA%s\u0002\rA\u0019\u0005\f%\u0007\u0013j\u0005#b\u0001\n\u0003!9,\u0001\u000bC_b,7oX5t\u001dVl'-\u001a:Pe\n{w\u000e\u001c\u0005\u000b%\u000f\u0013j\u0005#A!B\u0013\u0011\u0017!\u0006\"pq\u0016\u001cx,[:Ok6\u0014WM](s\u0005>|G\u000e\t\u0005\f%\u0017\u0013j\u0005#b\u0001\n\u0003!9,\u0001\bC_b,7oX5t\u001dVl'-\u001a:\t\u0015I=%S\nE\u0001B\u0003&!-A\bC_b,7oX5t\u001dVl'-\u001a:!\u0011!\u0011\u001aJ%\u0014\u0005\nIU\u0015a\u0005<bYV,7\t\\1tg\u000e{W\u000e]1oS>tG\u0003BA#%/Cq\u0001\u0016JI\u0001\u0004QI\u0004\u0003\u0005\u0013\u001cJ5C\u0011\u0002JO\u0003Q1\u0018\r\\;f\u0007>l\u0007/\u00198j_:lU-\u001c2feR1\u00111\u001cJP%GCqA%)\u0013\u001a\u0002\u0007\u0001(A\u0005dY\u0006\u001c8OT1nK\"A!S\u0015JM\u0001\u0004QI$\u0001\u0006nKRDw\u000e\u001a(b[\u0016D1B%+\u0013N!\u0015\r\u0011\"\u0001\u0013,\u0006I!m\u001c=NKRDw\u000eZ\u000b\u0003%[\u0003R!\u00181c\u00037D1B%-\u0013N!\u0005\t\u0015)\u0003\u0013.\u0006Q!m\u001c=NKRDw\u000e\u001a\u0011\t\u0017IU&S\nEC\u0002\u0013\u0005!3V\u0001\fk:\u0014w\u000e_'fi\"|G\rC\u0006\u0013:J5\u0003\u0012!Q!\nI5\u0016\u0001D;oE>DX*\u001a;i_\u0012\u0004\u0003b\u0003J_%\u001bB)\u0019!C\u0001\u000b+\tq![:V]\n|\u0007\u0010C\u0006\u0013BJ5\u0003\u0012!Q!\n\u0015]\u0011\u0001C5t+:\u0014w\u000e\u001f\u0011\t\u0017I\u0015'S\nEC\u0002\u0013\u0005QQC\u0001\u0006SN\u0014u\u000e\u001f\u0005\f%\u0013\u0014j\u0005#A!B\u0013)9\"\u0001\u0004jg\n{\u0007\u0010\t\u0005\f\u0003/\u0014j\u0005#b\u0001\n\u0003\tI\u000eC\u0006\u0013PJ5\u0003\u0012!Q!\n\u0005m\u0017\u0001\u0004\"p_2,\u0017M\\0b]\u0012\u0004\u0003bCAr%\u001bB)\u0019!C\u0001\u00033D1B%6\u0013N!\u0005\t\u0015)\u0003\u0002\\\u0006Y!i\\8mK\u0006twl\u001c:!\u0011-\t9O%\u0014\t\u0006\u0004%\t!!7\t\u0017Im'S\nE\u0001B\u0003&\u00111\\\u0001\r\u0005>|G.Z1o?:|G\u000f\t\u0005\f%?\u0014j\u0005#b\u0001\n\u0003\tI.\u0001\u0007PaRLwN\\0baBd\u0017\u0010C\u0006\u0013dJ5\u0003\u0012!Q!\n\u0005m\u0017!D(qi&|gnX1qa2L\b\u0005C\u0006\b\u000eI5\u0003R1A\u0005\u0002\u0005e\u0007b\u0003Ju%\u001bB\t\u0011)Q\u0005\u00037\f1\u0002T5ti~\u000b\u0007\u000f\u001d7zA!A!S\u001eJ'\t\u0003\u0011z/A\u0006jg2K7\u000f^!qa2LH\u0003BA\n%cDq!!!\u0013l\u0002\u0007!\r\u0003\u0005\u0013vJ5C\u0011\u0001J|\u0003=I7\u000f\u0015:fI\u001647\t\\1tg>3G\u0003BA\n%sDq!!!\u0013t\u0002\u0007!\rC\u0006\u0013~J5\u0003R1A\u0005\u0002I}\u0018\u0001\u0005+bO6\u000bG/\u001a:jC2L'0\u001a:t+\t\u0019\n\u0001\u0005\u00032m\t\u0014\u0007bCJ\u0003%\u001bB\t\u0011)Q\u0005'\u0003\t\u0011\u0003V1h\u001b\u0006$XM]5bY&TXM]:!\u0011-\u0019JA%\u0014\t\u0006\u0004%\t!\"\u0006\u0002\u0015Q\u000bwmU=nE>d7\u000fC\u0006\u0014\u000eI5\u0003\u0012!Q!\n\u0015]\u0011a\u0003+bONKXNY8mg\u0002B1b%\u0005\u0013N!\u0015\r\u0011\"\u0001\u00058\u0006y\u0001K]3eK\u001a|6m\u001c8g_Jl7\u000f\u0003\u0006\u0014\u0016I5\u0003\u0012!Q!\n\t\f\u0001\u0003\u0015:fI\u00164wlY8oM>\u0014Xn\u001d\u0011\t\u0017Me!S\nEC\u0002\u0013\u0005\u0011\u0011\\\u0001\u000f!J,G-\u001a4`G2\f7o](g\u0011-\u0019jB%\u0014\t\u0002\u0003\u0006K!a7\u0002\u001fA\u0013X\rZ3g?\u000ed\u0017m]:PM\u0002B1b%\t\u0013N!\u0015\r\u0011\"\u0001\u0002Z\u0006\t\u0002K]3eK\u001a|\u0016.\u001c9mS\u000eLG\u000f\\=\t\u0017M\u0015\"S\nE\u0001B\u0003&\u00111\\\u0001\u0013!J,G-\u001a4`S6\u0004H.[2ji2L\b\u0005C\u0006\u0014*I5\u0003R1A\u0005\u0002\u0005e\u0017a\u0005)sK\u0012,gmX<sCB\u0014VMZ!se\u0006L\bbCJ\u0017%\u001bB\t\u0011)Q\u0005\u00037\fA\u0003\u0015:fI\u00164wl\u001e:baJ+g-\u0011:sCf\u0004\u0003bCC\u001a%\u001bB)\u0019!C\u0001\u00033D1be\r\u0013N!\u0005\t\u0015)\u0003\u0002\\\u0006Q\u0002K]3eK\u001a|F%]7be.$\u0013/\\1sW\u0012\nX.\u0019:lA!Y1s\u0007J'\u0011\u000b\u0007I\u0011AAm\u0003A\t'O]1z\u0003B\u0004H._'fi\"|G\rC\u0006\u0014<I5\u0003\u0012!Q!\n\u0005m\u0017!E1se\u0006L\u0018\t\u001d9ms6+G\u000f[8eA!Y1s\bJ'\u0011\u000b\u0007I\u0011AAm\u0003E\t'O]1z+B$\u0017\r^3NKRDw\u000e\u001a\u0005\f'\u0007\u0012j\u0005#A!B\u0013\tY.\u0001\nbeJ\f\u00170\u00169eCR,W*\u001a;i_\u0012\u0004\u0003bCJ$%\u001bB)\u0019!C\u0001\u00033\f\u0011#\u0019:sCfdUM\\4uQ6+G\u000f[8e\u0011-\u0019ZE%\u0014\t\u0002\u0003\u0006K!a7\u0002%\u0005\u0014(/Y=MK:<G\u000f['fi\"|G\r\t\u0005\f'\u001f\u0012j\u0005#b\u0001\n\u0003\tI.\u0001\tbeJ\f\u0017p\u00117p]\u0016lU\r\u001e5pI\"Y13\u000bJ'\u0011\u0003\u0005\u000b\u0015BAn\u0003E\t'O]1z\u00072|g.Z'fi\"|G\r\t\u0005\f'/\u0012j\u0005#b\u0001\n\u0003\tI.\u0001\ff]N,(/Z!dG\u0016\u001c8/\u001b2mK6+G\u000f[8e\u0011-\u0019ZF%\u0014\t\u0002\u0003\u0006K!a7\u0002/\u0015t7/\u001e:f\u0003\u000e\u001cWm]:jE2,W*\u001a;i_\u0012\u0004\u0003bCJ0%\u001bB)\u0019!C\u0001\u00033\f\u0001#\u0019:sCf\u001cE.Y:t\u001b\u0016$\bn\u001c3\t\u0017M\r$S\nE\u0001B\u0003&\u00111\\\u0001\u0012CJ\u0014\u0018-_\"mCN\u001cX*\u001a;i_\u0012\u0004\u0003bCJ4%\u001bB)\u0019!C\u0001\u00033\fQ\u0003\u001e:bm\u0016\u00148/\u00192mK\u0012\u0013x\u000e]'fi\"|G\rC\u0006\u0014lI5\u0003\u0012!Q!\n\u0005m\u0017A\u0006;sCZ,'o]1cY\u0016$%o\u001c9NKRDw\u000e\u001a\u0011\t\u0017M=$S\nEC\u0002\u0013\u0005\u0011qR\u0001\u0015\u000fJ|W\u000f](g'B,7-[1mSj\f'\r\\3\t\u0015MM$S\nE\u0001B\u0003&a*A\u000bHe>,\bo\u00144Ta\u0016\u001c\u0017.\u00197ju\u0006\u0014G.\u001a\u0011\t\u0017M]$S\nEC\u0002\u0013\u0005AqW\u0001\u0011/\u0016\f7\u000eV=qKR\u000bwm\u00117bgND!be\u001f\u0013N!\u0005\t\u0015)\u0003c\u0003E9V-Y6UsB,G+Y4DY\u0006\u001c8\u000f\t\u0005\f'\u007f\u0012j\u0005#b\u0001\n\u0003!9,A\tXK\u0006\\G+\u001f9f)\u0006<Wj\u001c3vY\u0016D!be!\u0013N!\u0005\t\u0015)\u0003c\u0003I9V-Y6UsB,G+Y4N_\u0012,H.\u001a\u0011\t\u0017M\u001d%S\nEC\u0002\u0013\u0005AqW\u0001\r)f\u0004X\rV1h\u00072\f7o\u001d\u0005\u000b'\u0017\u0013j\u0005#A!B\u0013\u0011\u0017!\u0004+za\u0016$\u0016mZ\"mCN\u001c\b\u0005C\u0006\u0014\u0010J5\u0003R1A\u0005\u0002\u0011]\u0016!\u0004+za\u0016$\u0016mZ'pIVdW\r\u0003\u0006\u0014\u0014J5\u0003\u0012!Q!\n\t\fa\u0002V=qKR\u000bw-T8ek2,\u0007\u0005C\u0006\tjI5\u0003R1A\u0005\u0002\u0011]\u0006BCJM%\u001bB\t\u0011)Q\u0005E\u0006)R*Y2s_\u000e{g\u000e^3yiVs\u0017N^3sg\u0016\u0004\u0003bCJO%\u001bB)\u0019!C\u0001\u00033\f1#\\1uKJL\u0017\r\\5{K\u000ec\u0017m]:UC\u001eD1b%)\u0013N!\u0005\t\u0015)\u0003\u0002\\\u0006!R.\u0019;fe&\fG.\u001b>f\u00072\f7o\u001d+bO\u0002B1b%*\u0013N!\u0015\r\u0011\"\u0001\u00058\u00061R.\u0019;fe&\fG.\u001b>f/\u0016\f7\u000eV=qKR\u000bw\r\u0003\u0006\u0014*J5\u0003\u0012!Q!\n\t\fq#\\1uKJL\u0017\r\\5{K^+\u0017m\u001b+za\u0016$\u0016m\u001a\u0011\t\u0017M5&S\nEC\u0002\u0013\u0005AqW\u0001\u0013[\u0006$XM]5bY&TX\rV=qKR\u000bw\r\u0003\u0006\u00142J5\u0003\u0012!Q!\n\t\f1#\\1uKJL\u0017\r\\5{KRK\b/\u001a+bO\u0002B1b%.\u0013N!\u0015\r\u0011\"\u0001\u0003(\u0006\u0011R\r\u001f9fe&lWM\u001c;bY6{G-\u001e7f\u0011-\u0019JL%\u0014\t\u0002\u0003\u0006K!!\u0012\u0002'\u0015D\b/\u001a:j[\u0016tG/\u00197N_\u0012,H.\u001a\u0011\t\u0017Mu&S\nEC\u0002\u0013\u0005AqW\u0001\u000e\u001b\u0006\u001c'o\\:GK\u0006$XO]3\t\u0015M\u0005'S\nE\u0001B\u0003&!-\u0001\bNC\u000e\u0014xn\u001d$fCR,(/\u001a\u0011\t\u0017M\u0015'S\nEC\u0002\u0013\u0005AqW\u0001\u0010\tft\u0017-\\5dg\u001a+\u0017\r^;sK\"Q1\u0013\u001aJ'\u0011\u0003\u0005\u000b\u0015\u00022\u0002!\u0011Kh.Y7jGN4U-\u0019;ve\u0016\u0004\u0003bCJg%\u001bB)\u0019!C\u0001\to\u000b\u0011\u0003U8ti\u001aL\u0007p\u00149t\r\u0016\fG/\u001e:f\u0011)\u0019\nN%\u0014\t\u0002\u0003\u0006KAY\u0001\u0013!>\u001cHOZ5y\u001fB\u001ch)Z1ukJ,\u0007\u0005C\u0006\u0014VJ5\u0003R1A\u0005\u0002\u0011]\u0016A\u0006*fM2,7\r^5wK\u000e\u000bG\u000e\\:GK\u0006$XO]3\t\u0015Me'S\nE\u0001B\u0003&!-A\fSK\u001adWm\u0019;jm\u0016\u001c\u0015\r\u001c7t\r\u0016\fG/\u001e:fA!Y1S\u001cJ'\u0011\u000b\u0007I\u0011\u0001C\\\u0003iIU\u000e\u001d7jG&$8i\u001c8wKJ\u001c\u0018n\u001c8t\r\u0016\fG/\u001e:f\u0011)\u0019\nO%\u0014\t\u0002\u0003\u0006KAY\u0001\u001c\u00136\u0004H.[2ji\u000e{gN^3sg&|gn\u001d$fCR,(/\u001a\u0011\t\u0017M\u0015(S\nEC\u0002\u0013\u0005AqW\u0001\u0013\u0011&<\u0007.\u001a:LS:$7OR3biV\u0014X\r\u0003\u0006\u0014jJ5\u0003\u0012!Q!\n\t\f1\u0003S5hQ\u0016\u00148*\u001b8eg\u001a+\u0017\r^;sK\u0002B1b%<\u0013N!\u0015\r\u0011\"\u0001\u00058\u0006\u0019R\t_5ti\u0016tG/[1mg\u001a+\u0017\r^;sK\"Q1\u0013\u001fJ'\u0011\u0003\u0005\u000b\u0015\u00022\u0002)\u0015C\u0018n\u001d;f]RL\u0017\r\\:GK\u0006$XO]3!\u0011-\u0019*P%\u0014\t\u0006\u0004%\t\u0001b.\u0002!\u0005\u0003\u0018.\u00168jm\u0016\u00148/\u001a*fS\u001aL\bBCJ}%\u001bB\t\u0011)Q\u0005E\u0006\t\u0012\t]5V]&4XM]:f%\u0016Lg-\u001f\u0011\t\u0017\u001dU&S\nEC\u0002\u0013\u0005Aq\u0017\u0005\u000b'\u007f\u0014j\u0005#A!B\u0013\u0011\u0017a\u0006*fM2,7\r\u001e*v]RLW.Z+oSZ,'o]3!\u0011-9IL%\u0014\t\u0006\u0004%\t\u0001b.\t\u0015Q\u0015!S\nE\u0001B\u0003&!-\u0001\u000fSK\u001adWm\u0019;Sk:$\u0018.\\3DkJ\u0014XM\u001c;NSJ\u0014xN\u001d\u0011\t\u0017Q%!S\nEC\u0002\u0013\u0005AqW\u0001\u000e)J,Wm\u001d+sK\u0016$\u0016\u0010]3\t\u0015Q5!S\nE\u0001B\u0003&!-\u0001\bUe\u0016,7\u000f\u0016:fKRK\b/\u001a\u0011\b\u0011QE!S\nE\u0001)'\t\u0001\u0002\u0016:fKRK\b/\u001a\t\u0005)+!:\"\u0004\u0002\u0013N\u0019AA\u0013\u0004J'\u0011\u0003!ZB\u0001\u0005Ue\u0016,G+\u001f9f'\r!:B\u0003\u0005\t\u0005\u000f#:\u0002\"\u0001\u0015 Q\u0011A3\u0003\u0005\t\u0015{\":\u0002\"\u0001\u0015$Q!\u00111\u0003K\u0013\u0011!\u00199\u0007&\tA\u0002\u0005=x\u0001\u0003K\u0015%\u001bB\t\u0001f\u000b\u0002\u0017M+(\r\u001e:fKRK\b/\u001a\t\u0005)+!jC\u0002\u0005\u00150I5\u0003\u0012\u0001K\u0019\u0005-\u0019VO\u0019;sK\u0016$\u0016\u0010]3\u0014\u0007Q5\"\u0002\u0003\u0005\u0003\bR5B\u0011\u0001K\u001b)\t!Z\u0003\u0003\u0005\u000b~Q5B\u0011\u0001K\u001d)\u0011\t\u0019\u0002f\u000f\t\u0011\r\u001dDs\u0007a\u0001\u0003_<\u0001\u0002f\u0010\u0013N!\u0005A\u0013I\u0001\f\u000bb\u0004(o\u00117bgN|e\r\u0005\u0003\u0015\u0016Q\rc\u0001\u0003K#%\u001bB\t\u0001f\u0012\u0003\u0017\u0015C\bO]\"mCN\u001cxJZ\n\u0004)\u0007R\u0001\u0002\u0003BD)\u0007\"\t\u0001f\u0013\u0015\u0005Q\u0005\u0003\u0002\u0003F?)\u0007\"\t\u0001f\u0014\u0015\t)\u0005E\u0013\u000b\u0005\t\u0007\u001f!j\u00051\u0001\u0002p\"YAS\u000bJ'\u0011\u000b\u0007I\u0011\u0001K,\u0003Q\u0001\u0016M\u001d;jC2l\u0015M\\5gKN$8\t\\1tgV\u0011\u0001S\u0003\u0005\f)7\u0012j\u0005#A!B\u0013\u0001*\"A\u000bQCJ$\u0018.\u00197NC:Lg-Z:u\u00072\f7o\u001d\u0011\t\u0017Q}#S\nEC\u0002\u0013\u0005QQC\u0001\u0010\u001b\u0006t\u0017NZ3tiNKXNY8mg\"YA3\rJ'\u0011\u0003\u0005\u000b\u0015BC\f\u0003Ai\u0015M\\5gKN$8+_7c_2\u001c\b\u0005\u0003\u0005\u0015hI5C\u0011\u0001K5\u0003YI7\u000fU8ms6|'\u000f\u001d5jGNKwM\\1ukJ,G\u0003BA\n)WBq!!!\u0015f\u0001\u0007!\rC\u0006\u0015pI5\u0003R1A\u0005\n1M\u0016A\u0004)pYf\u001c\u0016nZ'fi\"|Gm\u001d\u0005\f)g\u0012j\u0005#A!B\u0013a),A\bQ_2L8+[4NKRDw\u000eZ:!\u0011-!:H%\u0014\t\u0006\u0004%\t\u0001b.\u00023M\u001b\u0017\r\\1`\u0015\u00064\u0018\rO0D_6\u0004\u0018\r\u001e)bG.\fw-\u001a\u0005\u000b)w\u0012j\u0005#A!B\u0013\u0011\u0017AG*dC2\fwLS1wCbz6i\\7qCR\u0004\u0016mY6bO\u0016\u0004\u0003b\u0003K@%\u001bB)\u0019!C\u0001)\u0003\u000b1eU2bY\u0006|&*\u0019<bq}\u001bu.\u001c9biB\u000b7m[1hK~Se)\u001e8di&|g.\u0006\u0002\u0015\u0004B!1\u0002&\"c\u0013\r!:I\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\f)\u0017\u0013j\u0005#A!B\u0013!\u001a)\u0001\u0013TG\u0006d\u0017m\u0018&bm\u0006DtlQ8na\u0006$\b+Y2lC\u001e,wL\u0013$v]\u000e$\u0018n\u001c8!\u0011%!zIII\u0001\n\u0013!\n*\u0001\u000fgCR\fG.T5tg&twmU=nE>dG\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005QM%\u0006BE!\u0013\u007fC\u0011\u0002f&##\u0003%I\u0001&%\u00029\u0019\fG/\u00197NSN\u001c\u0018N\\4Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%i!IA3\u0014\u0012\u0012\u0002\u0013\u0005AST\u0001\u001dO\u0016$H*\u00198hk\u0006<WMR3biV\u0014X\r\n3fM\u0006,H\u000e\u001e\u00133+\t!zJK\u0002c\u0013\u007fCqAa\"\u001f\t\u0003!\u001a\u000bF\u0001\u001d\u0011\u001d!:\u000b\u0001C\u0005)S\u000bQ\"\u001a8uKJtUm^\"mCN\u001cH#\u0003(\u0015,R5Fs\u0016KY\u0011\u001dy9\f&*A\u0002\tDa\u0001\u0016KS\u0001\u0004)\u0006\u0002\u0003Gp)K\u0003\r!#(\t\u0015A\rES\u0015I\u0001\u0002\u0004\u0001*\tC\u0004\u00156\u0002!I\u0001f.\u0002\u00139,w/T3uQ>$G\u0003\u0004G/)s#Z\f&0\u0015@R\u0005\u0007bBH\\)g\u0003\rA\u0019\u0005\b)RM\u0006\u0019\u0001F\u001d\u0011!1\u0019\u0007f-A\u0002%u\u0005\u0002\u0003F\u0014)g\u0003\r!a<\t\u0011A\rE3\u0017a\u0001!\u000bCq\u0001&2\u0001\t\u0013!:-\u0001\bf]R,'OT3x\u001b\u0016$\bn\u001c3\u0015\u00191uC\u0013\u001aKf)\u001b$z\r&5\t\u000f=]F3\u0019a\u0001E\"9A\u000bf1A\u0002)e\u0002\u0002\u0003D2)\u0007\u0004\r!#(\t\u0011)\u001dB3\u0019a\u0001\u0003_D!\u0002e!\u0015DB\u0005\t\u0019\u0001IC\u0011%!*\u000eAI\u0001\n\u0013!:.A\ff]R,'OT3x\u00072\f7o\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011A\u0013\u001c\u0016\u0005!\u000bKy\fC\u0005\u0015^\u0002\t\n\u0011\"\u0003\u0015X\u0006ARM\u001c;fe:+w/T3uQ>$G\u0005Z3gCVdG\u000fJ\u001b\u0011\tAmB\u0013]\u0005\u0004)G\u0014!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface Definitions
extends StandardDefinitions {
    @Override
    public Definitions$definitions$ definitions();

    public static abstract class DefinitionsClass
    implements StandardDefinitions.DefinitionsApi,
    ValueClassDefinitions {
        private boolean isInitialized;
        private Symbols.ModuleSymbol JavaLangPackage;
        private Symbols.ClassSymbol JavaLangPackageClass;
        private Symbols.ModuleSymbol ScalaPackage;
        private Symbols.ClassSymbol ScalaPackageClass;
        private Symbols.ModuleSymbol RuntimePackage;
        private Symbols.ClassSymbol RuntimePackageClass;
        private Symbols.ClassSymbol AnyClass;
        private Symbols.AliasTypeSymbol AnyRefClass;
        private Symbols.ClassSymbol ObjectClass;
        private Types.Type AnyRefTpe;
        private Types.Type AnyTpe;
        private Types.Type AnyValTpe;
        private Types.Type BoxedUnitTpe;
        private Types.Type NothingTpe;
        private Types.Type NullTpe;
        private Types.Type ObjectTpe;
        private Types.Type SerializableTpe;
        private Types.Type StringTpe;
        private Types.Type ThrowableTpe;
        private Types.UniqueConstantType ConstantTrue;
        private Types.UniqueConstantType ConstantFalse;
        private Types.UniqueConstantType ConstantNull;
        private Symbols.ClassSymbol AnyValClass;
        private Symbols.ClassSymbol RuntimeNothingClass;
        private Symbols.ClassSymbol RuntimeNullClass;
        private Symbols.ClassSymbol ClassCastExceptionClass;
        private Symbols.ClassSymbol IndexOutOfBoundsExceptionClass;
        private Symbols.ClassSymbol InvocationTargetExceptionClass;
        private Symbols.ClassSymbol MatchErrorClass;
        private Symbols.ClassSymbol NonLocalReturnControlClass;
        private Symbols.ClassSymbol NullPointerExceptionClass;
        private Symbols.ClassSymbol ThrowableClass;
        private Symbols.ClassSymbol UninitializedErrorClass;
        private Symbols.Symbol UninitializedFieldConstructor;
        private Symbols.ClassSymbol PartialFunctionClass;
        private Symbols.ClassSymbol AbstractPartialFunctionClass;
        private Symbols.ClassSymbol SymbolClass;
        private Symbols.ClassSymbol StringClass;
        private Symbols.Symbol StringModule;
        private Symbols.ClassSymbol ClassClass;
        private Symbols.ClassSymbol DynamicClass;
        private Symbols.ModuleSymbol SysPackage;
        private List<Symbols.ModuleSymbol> UnqualifiedModules;
        private Set<Symbols.Symbol> UnqualifiedOwners;
        private Symbols.ModuleSymbol PredefModule;
        private Symbols.ModuleSymbol SpecializableModule;
        private Symbols.ModuleSymbol ScalaRunTimeModule;
        private Symbols.ModuleSymbol SymbolModule;
        private Symbols.ClassSymbol StringAddClass;
        private Symbols.ClassSymbol ScalaNumberClass;
        private Symbols.ClassSymbol TraitSetterAnnotationClass;
        private Symbols.ClassSymbol DelayedInitClass;
        private Symbols.ClassSymbol TypeConstraintClass;
        private Symbols.ClassSymbol SingletonClass;
        private Symbols.ClassSymbol SerializableClass;
        private Symbols.ClassSymbol JavaSerializableClass;
        private Symbols.ClassSymbol ComparableClass;
        private Symbols.ClassSymbol JavaCloneableClass;
        private Symbols.ClassSymbol JavaNumberClass;
        private Symbols.ClassSymbol JavaEnumClass;
        private Symbols.ClassSymbol RemoteInterfaceClass;
        private Symbols.ClassSymbol RemoteExceptionClass;
        private Symbols.ClassSymbol JavaUtilMap;
        private Symbols.ClassSymbol JavaUtilHashMap;
        private Symbols.ClassSymbol ByNameParamClass;
        private Symbols.ClassSymbol JavaRepeatedParamClass;
        private Symbols.ClassSymbol RepeatedParamClass;
        private Symbols.ClassSymbol ConsClass;
        private Symbols.ClassSymbol IteratorClass;
        private Symbols.ClassSymbol IterableClass;
        private Symbols.ClassSymbol ListClass;
        private Symbols.ClassSymbol SeqClass;
        private Symbols.ClassSymbol StringBuilderClass;
        private Symbols.ClassSymbol TraversableClass;
        private Symbols.ModuleSymbol ListModule;
        private Symbols.ModuleSymbol NilModule;
        private Symbols.ModuleSymbol SeqModule;
        private Symbols.ModuleSymbol ArrayModule;
        private Symbols.TermSymbol ArrayModule_overloadedApply;
        private Symbols.ClassSymbol ArrayClass;
        private Symbols.TermSymbol Array_apply;
        private Symbols.TermSymbol Array_update;
        private Symbols.TermSymbol Array_length;
        private Symbols.TermSymbol Array_clone;
        private Symbols.ClassSymbol SoftReferenceClass;
        private Symbols.ClassSymbol MethodClass;
        private Symbols.ClassSymbol EmptyMethodCacheClass;
        private Symbols.ClassSymbol MethodCacheClass;
        private Symbols.Symbol ScalaXmlTopScope;
        private Symbols.Symbol ScalaXmlPackage;
        private Symbols.ModuleSymbol ReflectPackage;
        private Symbols.Symbol ReflectApiPackage;
        private Symbols.Symbol ReflectRuntimePackage;
        private Symbols.Symbol UniverseClass;
        private Symbols.ModuleSymbol PartialManifestModule;
        private Symbols.ClassSymbol FullManifestClass;
        private Symbols.ModuleSymbol FullManifestModule;
        private Symbols.ClassSymbol OptManifestClass;
        private Symbols.ModuleSymbol NoManifest;
        private Symbols.Symbol TreesClass;
        private Symbols.Symbol ExprsClass;
        private Symbols.ModuleSymbol ClassTagModule;
        private Symbols.ClassSymbol ClassTagClass;
        private Symbols.Symbol TypeTagsClass;
        private Symbols.Symbol ApiUniverseClass;
        private Symbols.Symbol JavaUniverseClass;
        private Symbols.Symbol MirrorClass;
        private Symbols.Symbol TypeCreatorClass;
        private Symbols.Symbol TreeCreatorClass;
        private Symbols.Symbol BlackboxContextClass;
        private Symbols.Symbol WhiteboxContextClass;
        private Symbols.ClassSymbol MacroImplAnnotation;
        private Symbols.ClassSymbol StringContextClass;
        private Symbols.Symbol QuasiquoteClass;
        private Symbols.Symbol QuasiquoteClass_api;
        private Symbols.Symbol QuasiquoteClass_api_apply;
        private Symbols.Symbol QuasiquoteClass_api_unapply;
        private Symbols.ClassSymbol ScalaSignatureAnnotation;
        private Symbols.ClassSymbol ScalaLongSignatureAnnotation;
        private Symbols.Symbol LambdaMetaFactory;
        private Symbols.Symbol MethodHandle;
        private Symbols.ClassSymbol OptionClass;
        private Symbols.ModuleSymbol OptionModule;
        private Symbols.ClassSymbol SomeClass;
        private Symbols.ModuleSymbol NoneModule;
        private Symbols.ModuleSymbol SomeModule;
        private final int MaxTupleArity;
        private final int MaxProductArity;
        private final int MaxFunctionArity;
        private VarArityClass ProductClass;
        private VarArityClass TupleClass;
        private VarArityClass FunctionClass;
        private VarArityClass AbstractFunctionClass;
        private Symbols.ClassSymbol ProductRootClass;
        private int volatileRecursions;
        private final HashSet<Symbols.Symbol> pendingVolatiles;
        private Symbols.MethodSymbol Any_$eq$eq;
        private Symbols.MethodSymbol Any_$bang$eq;
        private Symbols.MethodSymbol Any_equals;
        private Symbols.MethodSymbol Any_hashCode;
        private Symbols.MethodSymbol Any_toString;
        private Symbols.MethodSymbol Any_$hash$hash;
        private Symbols.MethodSymbol Any_getClass;
        private Symbols.MethodSymbol Any_isInstanceOf;
        private Symbols.MethodSymbol Any_asInstanceOf;
        private Set<Symbols.Symbol> primitiveGetClassMethods;
        private Set<Symbols.Symbol> getClassMethods;
        private Symbols.MethodSymbol Object_$hash$hash;
        private Symbols.MethodSymbol Object_$eq$eq;
        private Symbols.MethodSymbol Object_$bang$eq;
        private Symbols.MethodSymbol Object_eq;
        private Symbols.MethodSymbol Object_ne;
        private Symbols.MethodSymbol Object_isInstanceOf;
        private Symbols.MethodSymbol Object_asInstanceOf;
        private Symbols.MethodSymbol Object_synchronized;
        private Symbols.MethodSymbol String_$plus;
        private Symbols.ClassSymbol ObjectRefClass;
        private Symbols.ClassSymbol VolatileObjectRefClass;
        private Symbols.ModuleSymbol RuntimeStaticsModule;
        private Symbols.ModuleSymbol BoxesRunTimeModule;
        private Symbols.Symbol BoxesRunTimeClass;
        private Symbols.ClassSymbol BoxedNumberClass;
        private Symbols.ClassSymbol BoxedCharacterClass;
        private Symbols.ClassSymbol BoxedBooleanClass;
        private Symbols.ClassSymbol BoxedByteClass;
        private Symbols.ClassSymbol BoxedShortClass;
        private Symbols.ClassSymbol BoxedIntClass;
        private Symbols.ClassSymbol BoxedLongClass;
        private Symbols.ClassSymbol BoxedFloatClass;
        private Symbols.ClassSymbol BoxedDoubleClass;
        private Symbols.ClassSymbol BoxedUnitClass;
        private Symbols.ModuleSymbol BoxedUnitModule;
        private Symbols.ClassSymbol AnnotationClass;
        private Symbols.ClassSymbol ClassfileAnnotationClass;
        private Symbols.ClassSymbol StaticAnnotationClass;
        private Symbols.ClassSymbol AnnotationRetentionAttr;
        private Symbols.ClassSymbol AnnotationRetentionPolicyAttr;
        private Symbols.ClassSymbol BridgeClass;
        private Symbols.ClassSymbol ElidableMethodClass;
        private Symbols.ClassSymbol ImplicitNotFoundClass;
        private Symbols.ClassSymbol MigrationAnnotationClass;
        private Symbols.ClassSymbol ScalaStrictFPAttr;
        private Symbols.ClassSymbol SwitchClass;
        private Symbols.ClassSymbol TailrecClass;
        private Symbols.ClassSymbol VarargsClass;
        private Symbols.ClassSymbol uncheckedStableClass;
        private Symbols.ClassSymbol uncheckedVarianceClass;
        private Symbols.ClassSymbol BeanPropertyAttr;
        private Symbols.ClassSymbol BooleanBeanPropertyAttr;
        private Symbols.Symbol CompileTimeOnlyAttr;
        private Symbols.ClassSymbol DeprecatedAttr;
        private Symbols.ClassSymbol DeprecatedNameAttr;
        private Symbols.ClassSymbol DeprecatedInheritanceAttr;
        private Symbols.ClassSymbol DeprecatedOverridingAttr;
        private Symbols.ClassSymbol NativeAttr;
        private Symbols.ClassSymbol RemoteAttr;
        private Symbols.ClassSymbol ScalaInlineClass;
        private Symbols.ClassSymbol ScalaNoInlineClass;
        private Symbols.ClassSymbol SerialVersionUIDAttr;
        private AnnotationInfos.AnnotationInfo SerialVersionUIDAnnotation;
        private Symbols.ClassSymbol SpecializedClass;
        private Symbols.ClassSymbol ThrowsClass;
        private Symbols.ClassSymbol TransientAttr;
        private Symbols.ClassSymbol UncheckedClass;
        private Symbols.Symbol UncheckedBoundsClass;
        private Symbols.ClassSymbol UnspecializedClass;
        private Symbols.ClassSymbol VolatileAttr;
        private Symbols.ClassSymbol BeanGetterTargetClass;
        private Symbols.ClassSymbol BeanSetterTargetClass;
        private Symbols.ClassSymbol FieldTargetClass;
        private Symbols.ClassSymbol GetterTargetClass;
        private Symbols.ClassSymbol ParamTargetClass;
        private Symbols.ClassSymbol SetterTargetClass;
        private Symbols.ClassSymbol ObjectTargetClass;
        private Symbols.ClassSymbol ClassTargetClass;
        private Symbols.ClassSymbol MethodTargetClass;
        private Symbols.ClassSymbol LanguageFeatureAnnot;
        private Symbols.ModuleSymbol languageFeatureModule;
        private Set<Symbols.Symbol> metaAnnotations;
        private Symbols.ClassSymbol AnnotationDefaultAttr;
        private Phase erasurePhase;
        private Set<Symbols.Symbol> isPhantomClass;
        private List<Symbols.TypeSymbol> syntheticCoreClasses;
        private List<Symbols.MethodSymbol> syntheticCoreMethods;
        private List<Symbols.ClassSymbol> hijackedCoreClasses;
        private List<Symbols.Symbol> symbolsNotPresentInBytecode;
        private Set<Symbols.Symbol> isPossibleSyntheticParent;
        private Set<Symbols.Symbol> boxedValueClassesSet;
        private volatile Definitions$DefinitionsClass$NothingClass$ NothingClass$module;
        private volatile Definitions$DefinitionsClass$NullClass$ NullClass$module;
        private volatile Definitions$DefinitionsClass$VarArityClass$ VarArityClass$module;
        private volatile Definitions$DefinitionsClass$MacroContextType$ MacroContextType$module;
        public final /* synthetic */ SymbolTable $outer;
        private final scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight;
        private final scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag;
        private final scala.collection.immutable.Map<Symbols.Symbol, Object> abbrvTag;
        private final scala.collection.immutable.Map<Symbols.Symbol, Object> numericWeight;
        private final scala.collection.immutable.Map<Symbols.Symbol, Symbols.ModuleSymbol> boxedModule;
        private final scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> boxedClass;
        private final scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> refClass;
        private final scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> volatileRefClass;
        private final Symbols.ClassSymbol UnitClass;
        private final Symbols.ClassSymbol ByteClass;
        private final Symbols.ClassSymbol ShortClass;
        private final Symbols.ClassSymbol CharClass;
        private final Symbols.ClassSymbol IntClass;
        private final Symbols.ClassSymbol LongClass;
        private final Symbols.ClassSymbol FloatClass;
        private final Symbols.ClassSymbol DoubleClass;
        private final Symbols.ClassSymbol BooleanClass;
        private final Types.Type UnitTpe;
        private final Types.Type ByteTpe;
        private final Types.Type ShortTpe;
        private final Types.Type CharTpe;
        private final Types.Type IntTpe;
        private final Types.Type LongTpe;
        private final Types.Type FloatTpe;
        private final Types.Type DoubleTpe;
        private final Types.Type BooleanTpe;
        private final List<Symbols.ClassSymbol> ScalaNumericValueClasses;
        private final List<Symbols.ClassSymbol> ScalaValueClassesNoUnit;
        private final List<Symbols.ClassSymbol> ScalaValueClasses;
        private volatile long bitmap$0;
        private volatile long bitmap$1;
        private volatile long bitmap$2;
        private volatile long bitmap$3;

        private Symbols.ModuleSymbol JavaLangPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 1L) == 0L) {
                    this.JavaLangPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackage(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply("java.lang"));
                    this.bitmap$0 |= 1L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaLangPackage;
            }
        }

        private Symbols.ClassSymbol JavaLangPackageClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 2L) == 0L) {
                    this.JavaLangPackageClass = (Symbols.ClassSymbol)this.JavaLangPackage().moduleClass().asClass();
                    this.bitmap$0 |= 2L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaLangPackageClass;
            }
        }

        private Symbols.ModuleSymbol ScalaPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 4L) == 0L) {
                    this.ScalaPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackage(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply("scala"));
                    this.bitmap$0 |= 4L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaPackage;
            }
        }

        private Symbols.ClassSymbol ScalaPackageClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 8L) == 0L) {
                    this.ScalaPackageClass = (Symbols.ClassSymbol)this.ScalaPackage().moduleClass().asClass();
                    this.bitmap$0 |= 8L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaPackageClass;
            }
        }

        private Symbols.ModuleSymbol RuntimePackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x10L) == 0L) {
                    this.RuntimePackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackage(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply("scala.runtime"));
                    this.bitmap$0 |= 0x10L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RuntimePackage;
            }
        }

        private Symbols.ClassSymbol RuntimePackageClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x20L) == 0L) {
                    this.RuntimePackageClass = (Symbols.ClassSymbol)this.RuntimePackage().moduleClass().asClass();
                    this.bitmap$0 |= 0x20L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RuntimePackageClass;
            }
        }

        private Symbols.ClassSymbol AnyClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x40L) == 0L) {
                    this.AnyClass = (Symbols.ClassSymbol)Definitions$class.scala$reflect$internal$Definitions$$enterNewClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ScalaPackageClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Any(), Nil$.MODULE$, 8L).markAllCompleted();
                    this.bitmap$0 |= 0x40L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyClass;
            }
        }

        private Symbols.AliasTypeSymbol AnyRefClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x80L) == 0L) {
                    this.AnyRefClass = (Symbols.AliasTypeSymbol)this.newAlias(this.ScalaPackageClass(), (Names.TypeName)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().AnyRef(), this.ObjectTpe()).markAllCompleted();
                    this.bitmap$0 |= 0x80L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyRefClass;
            }
        }

        private Symbols.ClassSymbol ObjectClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x100L) == 0L) {
                    this.ObjectClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().Object().toString());
                    this.bitmap$0 |= 0x100L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ObjectClass;
            }
        }

        private Types.Type AnyRefTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x200L) == 0L) {
                    this.AnyRefTpe = this.AnyRefClass().tpe();
                    this.bitmap$0 |= 0x200L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyRefTpe;
            }
        }

        private Types.Type AnyTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x400L) == 0L) {
                    this.AnyTpe = this.AnyClass().tpe();
                    this.bitmap$0 |= 0x400L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyTpe;
            }
        }

        private Types.Type AnyValTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x800L) == 0L) {
                    this.AnyValTpe = this.AnyValClass().tpe();
                    this.bitmap$0 |= 0x800L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyValTpe;
            }
        }

        private Types.Type BoxedUnitTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x1000L) == 0L) {
                    this.BoxedUnitTpe = this.BoxedUnitClass().tpe();
                    this.bitmap$0 |= 0x1000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedUnitTpe;
            }
        }

        private Types.Type NothingTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x2000L) == 0L) {
                    this.NothingTpe = this.NothingClass().tpe();
                    this.bitmap$0 |= 0x2000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NothingTpe;
            }
        }

        private Types.Type NullTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x4000L) == 0L) {
                    this.NullTpe = this.NullClass().tpe();
                    this.bitmap$0 |= 0x4000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NullTpe;
            }
        }

        private Types.Type ObjectTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x8000L) == 0L) {
                    this.ObjectTpe = this.ObjectClass().tpe();
                    this.bitmap$0 |= 0x8000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ObjectTpe;
            }
        }

        private Types.Type SerializableTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x10000L) == 0L) {
                    this.SerializableTpe = this.SerializableClass().tpe();
                    this.bitmap$0 |= 0x10000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SerializableTpe;
            }
        }

        private Types.Type StringTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x20000L) == 0L) {
                    this.StringTpe = this.StringClass().tpe();
                    this.bitmap$0 |= 0x20000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringTpe;
            }
        }

        private Types.Type ThrowableTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x40000L) == 0L) {
                    this.ThrowableTpe = this.ThrowableClass().tpe();
                    this.bitmap$0 |= 0x40000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ThrowableTpe;
            }
        }

        private Types.UniqueConstantType ConstantTrue$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x80000L) == 0L) {
                    this.ConstantTrue = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().ConstantType().apply(new Constants.Constant(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), BoxesRunTime.boxToBoolean(true)));
                    this.bitmap$0 |= 0x80000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ConstantTrue;
            }
        }

        private Types.UniqueConstantType ConstantFalse$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x100000L) == 0L) {
                    this.ConstantFalse = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().ConstantType().apply(new Constants.Constant(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), BoxesRunTime.boxToBoolean(false)));
                    this.bitmap$0 |= 0x100000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ConstantFalse;
            }
        }

        private Types.UniqueConstantType ConstantNull$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x200000L) == 0L) {
                    this.ConstantNull = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().ConstantType().apply(new Constants.Constant(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), null));
                    this.bitmap$0 |= 0x200000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ConstantNull;
            }
        }

        private Symbols.ClassSymbol AnyValClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x400000L) == 0L) {
                    Symbols.Symbol symbol;
                    Symbols.Symbol symbol2 = this.ScalaPackageClass().info().member(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().AnyVal());
                    if (symbol2 != symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        symbol = symbol2;
                    } else {
                        Types.Type x$31 = this.AnyTpe();
                        Nil$ nil$ = Nil$.MODULE$;
                        Symbols.ClassSymbol anyval1 = Definitions$class.scala$reflect$internal$Definitions$$enterNewClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ScalaPackageClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().AnyVal(), new $colon$colon<Nothing$>((Nothing$)((Object)x$31), nil$), 8L);
                        Symbols.MethodSymbol av_constr1 = anyval1.newClassConstructor(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPosition());
                        anyval1.info().decls().enter(av_constr1);
                        symbol = (Symbols.ClassSymbol)anyval1.markAllCompleted();
                    }
                    this.AnyValClass = (Symbols.ClassSymbol)symbol;
                    this.bitmap$0 |= 0x400000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnyValClass;
            }
        }

        private Symbols.ClassSymbol RuntimeNothingClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x800000L) == 0L) {
                    this.RuntimeNothingClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().fulltpnme().RuntimeNothing());
                    this.bitmap$0 |= 0x800000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RuntimeNothingClass;
            }
        }

        private Symbols.ClassSymbol RuntimeNullClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x1000000L) == 0L) {
                    this.RuntimeNullClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().fulltpnme().RuntimeNull());
                    this.bitmap$0 |= 0x1000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RuntimeNullClass;
            }
        }

        private Definitions$DefinitionsClass$NothingClass$ NothingClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if (this.NothingClass$module == null) {
                    this.NothingClass$module = new Definitions$DefinitionsClass$NothingClass$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NothingClass$module;
            }
        }

        private Definitions$DefinitionsClass$NullClass$ NullClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if (this.NullClass$module == null) {
                    this.NullClass$module = new Definitions$DefinitionsClass$NullClass$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NullClass$module;
            }
        }

        private Symbols.ClassSymbol ClassCastExceptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x2000000L) == 0L) {
                    this.ClassCastExceptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ClassCastException.class));
                    this.bitmap$0 |= 0x2000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassCastExceptionClass;
            }
        }

        private Symbols.ClassSymbol IndexOutOfBoundsExceptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x4000000L) == 0L) {
                    this.IndexOutOfBoundsExceptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().IOOBException());
                    this.bitmap$0 |= 0x4000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.IndexOutOfBoundsExceptionClass;
            }
        }

        private Symbols.ClassSymbol InvocationTargetExceptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x8000000L) == 0L) {
                    this.InvocationTargetExceptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().InvTargetException());
                    this.bitmap$0 |= 0x8000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.InvocationTargetExceptionClass;
            }
        }

        private Symbols.ClassSymbol MatchErrorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x10000000L) == 0L) {
                    this.MatchErrorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(MatchError.class));
                    this.bitmap$0 |= 0x10000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MatchErrorClass;
            }
        }

        private Symbols.ClassSymbol NonLocalReturnControlClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x20000000L) == 0L) {
                    this.NonLocalReturnControlClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(NonLocalReturnControl.class));
                    this.bitmap$0 |= 0x20000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NonLocalReturnControlClass;
            }
        }

        private Symbols.ClassSymbol NullPointerExceptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x40000000L) == 0L) {
                    this.NullPointerExceptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().NPException());
                    this.bitmap$0 |= 0x40000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NullPointerExceptionClass;
            }
        }

        private Symbols.ClassSymbol ThrowableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x80000000L) == 0L) {
                    this.ThrowableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().Throwable());
                    this.bitmap$0 |= 0x80000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ThrowableClass;
            }
        }

        private Symbols.ClassSymbol UninitializedErrorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x100000000L) == 0L) {
                    this.UninitializedErrorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(UninitializedFieldError.class));
                    this.bitmap$0 |= 0x100000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UninitializedErrorClass;
            }
        }

        private Symbols.Symbol UninitializedFieldConstructor$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x200000000L) == 0L) {
                    this.UninitializedFieldConstructor = this.UninitializedErrorClass().primaryConstructor();
                    this.bitmap$0 |= 0x200000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UninitializedFieldConstructor;
            }
        }

        private Symbols.ClassSymbol PartialFunctionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x400000000L) == 0L) {
                    this.PartialFunctionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(PartialFunction.class));
                    this.bitmap$0 |= 0x400000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.PartialFunctionClass;
            }
        }

        private Symbols.ClassSymbol AbstractPartialFunctionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x800000000L) == 0L) {
                    this.AbstractPartialFunctionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(AbstractPartialFunction.class));
                    this.bitmap$0 |= 0x800000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AbstractPartialFunctionClass;
            }
        }

        private Symbols.ClassSymbol SymbolClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x1000000000L) == 0L) {
                    this.SymbolClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Symbol.class));
                    this.bitmap$0 |= 0x1000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SymbolClass;
            }
        }

        private Symbols.ClassSymbol StringClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x2000000000L) == 0L) {
                    this.StringClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(String.class));
                    this.bitmap$0 |= 0x2000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringClass;
            }
        }

        private Symbols.Symbol StringModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x4000000000L) == 0L) {
                    this.StringModule = this.StringClass().linkedClassOfClass();
                    this.bitmap$0 |= 0x4000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringModule;
            }
        }

        private Symbols.ClassSymbol ClassClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x8000000000L) == 0L) {
                    this.ClassClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Class.class));
                    this.bitmap$0 |= 0x8000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassClass;
            }
        }

        private Symbols.ClassSymbol DynamicClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x10000000000L) == 0L) {
                    this.DynamicClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Dynamic.class));
                    this.bitmap$0 |= 0x10000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DynamicClass;
            }
        }

        private Symbols.ModuleSymbol SysPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x20000000000L) == 0L) {
                    this.SysPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackageObject("scala.sys");
                    this.bitmap$0 |= 0x20000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SysPackage;
            }
        }

        private List UnqualifiedModules$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x40000000000L) == 0L) {
                    this.UnqualifiedModules = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.ModuleSymbol[]{this.PredefModule(), this.ScalaPackage(), this.JavaLangPackage()}));
                    this.bitmap$0 |= 0x40000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnqualifiedModules;
            }
        }

        private Set UnqualifiedOwners$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x80000000000L) == 0L) {
                    this.UnqualifiedOwners = (Set)this.UnqualifiedModules().toSet().$plus$plus(this.UnqualifiedModules().map(new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Symbols.Symbol apply(Symbols.ModuleSymbol x$4) {
                            return x$4.moduleClass();
                        }
                    }, List$.MODULE$.canBuildFrom()));
                    this.bitmap$0 |= 0x80000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnqualifiedOwners;
            }
        }

        private Symbols.ModuleSymbol PredefModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x100000000000L) == 0L) {
                    this.PredefModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Predef$.class));
                    this.bitmap$0 |= 0x100000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.PredefModule;
            }
        }

        private Symbols.ModuleSymbol SpecializableModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x200000000000L) == 0L) {
                    this.SpecializableModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Specializable.class));
                    this.bitmap$0 |= 0x200000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SpecializableModule;
            }
        }

        private Symbols.ModuleSymbol ScalaRunTimeModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x400000000000L) == 0L) {
                    this.ScalaRunTimeModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(ScalaRunTime$.class));
                    this.bitmap$0 |= 0x400000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaRunTimeModule;
            }
        }

        private Symbols.ModuleSymbol SymbolModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x800000000000L) == 0L) {
                    this.SymbolModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Symbol$.class));
                    this.bitmap$0 |= 0x800000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SymbolModule;
            }
        }

        private Symbols.ClassSymbol StringAddClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x1000000000000L) == 0L) {
                    this.StringAddClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(StringAdd.class));
                    this.bitmap$0 |= 0x1000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringAddClass;
            }
        }

        private Symbols.ClassSymbol ScalaNumberClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x2000000000000L) == 0L) {
                    this.ScalaNumberClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ScalaNumber.class));
                    this.bitmap$0 |= 0x2000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaNumberClass;
            }
        }

        private Symbols.ClassSymbol TraitSetterAnnotationClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x4000000000000L) == 0L) {
                    this.TraitSetterAnnotationClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(TraitSetter.class));
                    this.bitmap$0 |= 0x4000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TraitSetterAnnotationClass;
            }
        }

        private Symbols.ClassSymbol DelayedInitClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x8000000000000L) == 0L) {
                    this.DelayedInitClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(DelayedInit.class));
                    this.bitmap$0 |= 0x8000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DelayedInitClass;
            }
        }

        private Symbols.ClassSymbol TypeConstraintClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x10000000000000L) == 0L) {
                    this.TypeConstraintClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(TypeConstraint.class));
                    this.bitmap$0 |= 0x10000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TypeConstraintClass;
            }
        }

        private Symbols.ClassSymbol SingletonClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x20000000000000L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.SingletonClass = (Symbols.ClassSymbol)Definitions$class.scala$reflect$internal$Definitions$$enterNewClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ScalaPackageClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Singleton(), Nil$.MODULE$.$colon$colon(type), 0x2000028L).markAllCompleted();
                    this.bitmap$0 |= 0x20000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SingletonClass;
            }
        }

        private Symbols.ClassSymbol SerializableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x40000000000000L) == 0L) {
                    this.SerializableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Serializable.class));
                    this.bitmap$0 |= 0x40000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SerializableClass;
            }
        }

        private Symbols.ClassSymbol JavaSerializableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x80000000000000L) == 0L) {
                    this.JavaSerializableClass = (Symbols.ClassSymbol)((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(java.io.Serializable.class)).modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Types.Type tpe) {
                            return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$fixupAsAnyTrait(tpe);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$0 |= 0x80000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaSerializableClass;
            }
        }

        private Symbols.ClassSymbol ComparableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x100000000000000L) == 0L) {
                    this.ComparableClass = (Symbols.ClassSymbol)((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Comparable.class)).modifyInfo((Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Types.Type tpe) {
                            return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$fixupAsAnyTrait(tpe);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$0 |= 0x100000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ComparableClass;
            }
        }

        private Symbols.ClassSymbol JavaCloneableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x200000000000000L) == 0L) {
                    this.JavaCloneableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Cloneable.class));
                    this.bitmap$0 |= 0x200000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaCloneableClass;
            }
        }

        private Symbols.ClassSymbol JavaNumberClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x400000000000000L) == 0L) {
                    this.JavaNumberClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Number.class));
                    this.bitmap$0 |= 0x400000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaNumberClass;
            }
        }

        private Symbols.ClassSymbol JavaEnumClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x800000000000000L) == 0L) {
                    this.JavaEnumClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Enum.class));
                    this.bitmap$0 |= 0x800000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaEnumClass;
            }
        }

        private Symbols.ClassSymbol RemoteInterfaceClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x1000000000000000L) == 0L) {
                    this.RemoteInterfaceClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Remote.class));
                    this.bitmap$0 |= 0x1000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RemoteInterfaceClass;
            }
        }

        private Symbols.ClassSymbol RemoteExceptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x2000000000000000L) == 0L) {
                    this.RemoteExceptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(RemoteException.class));
                    this.bitmap$0 |= 0x2000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RemoteExceptionClass;
            }
        }

        private Symbols.ClassSymbol JavaUtilMap$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & 0x4000000000000000L) == 0L) {
                    this.JavaUtilMap = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Map.class));
                    this.bitmap$0 |= 0x4000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaUtilMap;
            }
        }

        private Symbols.ClassSymbol JavaUtilHashMap$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$0 & Long.MIN_VALUE) == 0L) {
                    this.JavaUtilHashMap = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(HashMap.class));
                    this.bitmap$0 |= Long.MIN_VALUE;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaUtilHashMap;
            }
        }

        private Symbols.ClassSymbol ByNameParamClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 1L) == 0L) {
                    this.ByNameParamClass = this.specialPolyClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().BYNAME_PARAM_CLASS_NAME(), 65536L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Symbols.Symbol x$6) {
                            return this.$outer.AnyTpe();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$1 |= 1L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ByNameParamClass;
            }
        }

        private Symbols.ClassSymbol JavaRepeatedParamClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 2L) == 0L) {
                    this.JavaRepeatedParamClass = this.specialPolyClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().JAVA_REPEATED_PARAM_CLASS_NAME(), 65536L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Symbols.Symbol tparam) {
                            return this.$outer.arrayType(tparam.tpe());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$1 |= 2L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaRepeatedParamClass;
            }
        }

        private Symbols.ClassSymbol RepeatedParamClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 4L) == 0L) {
                    this.RepeatedParamClass = this.specialPolyClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().REPEATED_PARAM_CLASS_NAME(), 65536L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Symbols.Symbol tparam) {
                            return this.$outer.seqType(tparam.tpe());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$1 |= 4L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RepeatedParamClass;
            }
        }

        private Symbols.ClassSymbol ConsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 8L) == 0L) {
                    this.ConsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply($colon$colon.class));
                    this.bitmap$1 |= 8L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ConsClass;
            }
        }

        private Symbols.ClassSymbol IteratorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x10L) == 0L) {
                    this.IteratorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Iterator.class));
                    this.bitmap$1 |= 0x10L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.IteratorClass;
            }
        }

        private Symbols.ClassSymbol IterableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x20L) == 0L) {
                    this.IterableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Iterable.class));
                    this.bitmap$1 |= 0x20L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.IterableClass;
            }
        }

        private Symbols.ClassSymbol ListClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x40L) == 0L) {
                    this.ListClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(List.class));
                    this.bitmap$1 |= 0x40L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ListClass;
            }
        }

        private Symbols.ClassSymbol SeqClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x80L) == 0L) {
                    this.SeqClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Seq.class));
                    this.bitmap$1 |= 0x80L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SeqClass;
            }
        }

        private Symbols.ClassSymbol StringBuilderClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x100L) == 0L) {
                    this.StringBuilderClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(StringBuilder.class));
                    this.bitmap$1 |= 0x100L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringBuilderClass;
            }
        }

        private Symbols.ClassSymbol TraversableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x200L) == 0L) {
                    this.TraversableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Traversable.class));
                    this.bitmap$1 |= 0x200L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TraversableClass;
            }
        }

        private Symbols.ModuleSymbol ListModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x400L) == 0L) {
                    this.ListModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(List$.class));
                    this.bitmap$1 |= 0x400L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ListModule;
            }
        }

        private Symbols.ModuleSymbol NilModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x800L) == 0L) {
                    this.NilModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Nil$.class));
                    this.bitmap$1 |= 0x800L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NilModule;
            }
        }

        private Symbols.ModuleSymbol SeqModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x1000L) == 0L) {
                    this.SeqModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Seq$.class));
                    this.bitmap$1 |= 0x1000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SeqModule;
            }
        }

        private Symbols.ModuleSymbol ArrayModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x2000L) == 0L) {
                    this.ArrayModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Array$.class));
                    this.bitmap$1 |= 0x2000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ArrayModule;
            }
        }

        private Symbols.TermSymbol ArrayModule_overloadedApply$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x4000L) == 0L) {
                    this.ArrayModule_overloadedApply = this.getMemberMethod(this.ArrayModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
                    this.bitmap$1 |= 0x4000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ArrayModule_overloadedApply;
            }
        }

        private Symbols.ClassSymbol ArrayClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x8000L) == 0L) {
                    this.ArrayClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredClass("scala.Array");
                    this.bitmap$1 |= 0x8000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ArrayClass;
            }
        }

        private Symbols.TermSymbol Array_apply$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x10000L) == 0L) {
                    this.Array_apply = this.getMemberMethod(this.ArrayClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
                    this.bitmap$1 |= 0x10000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Array_apply;
            }
        }

        private Symbols.TermSymbol Array_update$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x20000L) == 0L) {
                    this.Array_update = this.getMemberMethod(this.ArrayClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().update());
                    this.bitmap$1 |= 0x20000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Array_update;
            }
        }

        private Symbols.TermSymbol Array_length$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x40000L) == 0L) {
                    this.Array_length = this.getMemberMethod(this.ArrayClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().length());
                    this.bitmap$1 |= 0x40000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Array_length;
            }
        }

        private Symbols.TermSymbol Array_clone$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x80000L) == 0L) {
                    this.Array_clone = this.getMemberMethod(this.ArrayClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().clone_());
                    this.bitmap$1 |= 0x80000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Array_clone;
            }
        }

        private Symbols.ClassSymbol SoftReferenceClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x100000L) == 0L) {
                    this.SoftReferenceClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(SoftReference.class));
                    this.bitmap$1 |= 0x100000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SoftReferenceClass;
            }
        }

        private Symbols.ClassSymbol MethodClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x200000L) == 0L) {
                    this.MethodClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().MethodAsObject());
                    this.bitmap$1 |= 0x200000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MethodClass;
            }
        }

        private Symbols.ClassSymbol EmptyMethodCacheClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x400000L) == 0L) {
                    this.EmptyMethodCacheClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(EmptyMethodCache.class));
                    this.bitmap$1 |= 0x400000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.EmptyMethodCacheClass;
            }
        }

        private Symbols.ClassSymbol MethodCacheClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x800000L) == 0L) {
                    this.MethodCacheClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(MethodCache.class));
                    this.bitmap$1 |= 0x800000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MethodCacheClass;
            }
        }

        private Symbols.Symbol ScalaXmlTopScope$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x1000000L) == 0L) {
                    this.ScalaXmlTopScope = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getModuleIfDefined("scala.xml.TopScope");
                    this.bitmap$1 |= 0x1000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaXmlTopScope;
            }
        }

        private Symbols.Symbol ScalaXmlPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x2000000L) == 0L) {
                    this.ScalaXmlPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackageIfDefined(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply("scala.xml"));
                    this.bitmap$1 |= 0x2000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaXmlPackage;
            }
        }

        private Symbols.ModuleSymbol ReflectPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x4000000L) == 0L) {
                    this.ReflectPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(package$.class));
                    this.bitmap$1 |= 0x4000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ReflectPackage;
            }
        }

        private Symbols.Symbol ReflectApiPackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x8000000L) == 0L) {
                    this.ReflectApiPackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackageObjectIfDefined("scala.reflect.api");
                    this.bitmap$1 |= 0x8000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ReflectApiPackage;
            }
        }

        private Symbols.Symbol ReflectRuntimePackage$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x10000000L) == 0L) {
                    this.ReflectRuntimePackage = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackageObjectIfDefined("scala.reflect.runtime");
                    this.bitmap$1 |= 0x10000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ReflectRuntimePackage;
            }
        }

        private Symbols.Symbol UniverseClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x20000000L) == 0L) {
                    this.UniverseClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.Universe");
                    this.bitmap$1 |= 0x20000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UniverseClass;
            }
        }

        private Symbols.ModuleSymbol PartialManifestModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x40000000L) == 0L) {
                    this.PartialManifestModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(ClassManifestFactory$.class));
                    this.bitmap$1 |= 0x40000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.PartialManifestModule;
            }
        }

        private Symbols.ClassSymbol FullManifestClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x80000000L) == 0L) {
                    this.FullManifestClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Manifest.class));
                    this.bitmap$1 |= 0x80000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FullManifestClass;
            }
        }

        private Symbols.ModuleSymbol FullManifestModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x100000000L) == 0L) {
                    this.FullManifestModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(ManifestFactory$.class));
                    this.bitmap$1 |= 0x100000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FullManifestModule;
            }
        }

        private Symbols.ClassSymbol OptManifestClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x200000000L) == 0L) {
                    this.OptManifestClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(OptManifest.class));
                    this.bitmap$1 |= 0x200000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.OptManifestClass;
            }
        }

        private Symbols.ModuleSymbol NoManifest$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x400000000L) == 0L) {
                    this.NoManifest = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(NoManifest$.class));
                    this.bitmap$1 |= 0x400000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NoManifest;
            }
        }

        private Symbols.Symbol TreesClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x800000000L) == 0L) {
                    this.TreesClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.Trees");
                    this.bitmap$1 |= 0x800000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TreesClass;
            }
        }

        private Symbols.Symbol ExprsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x1000000000L) == 0L) {
                    this.ExprsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.Exprs");
                    this.bitmap$1 |= 0x1000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ExprsClass;
            }
        }

        private Symbols.ModuleSymbol ClassTagModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x2000000000L) == 0L) {
                    this.ClassTagModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(ClassTag.class));
                    this.bitmap$1 |= 0x2000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassTagModule;
            }
        }

        private Symbols.ClassSymbol ClassTagClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x4000000000L) == 0L) {
                    this.ClassTagClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ClassTag.class));
                    this.bitmap$1 |= 0x4000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassTagClass;
            }
        }

        private Symbols.Symbol TypeTagsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x8000000000L) == 0L) {
                    this.TypeTagsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.TypeTags");
                    this.bitmap$1 |= 0x8000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TypeTagsClass;
            }
        }

        private Symbols.Symbol ApiUniverseClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x10000000000L) == 0L) {
                    this.ApiUniverseClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.Universe");
                    this.bitmap$1 |= 0x10000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ApiUniverseClass;
            }
        }

        private Symbols.Symbol JavaUniverseClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x20000000000L) == 0L) {
                    this.JavaUniverseClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.JavaUniverse");
                    this.bitmap$1 |= 0x20000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.JavaUniverseClass;
            }
        }

        private Symbols.Symbol MirrorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x40000000000L) == 0L) {
                    this.MirrorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.Mirror");
                    this.bitmap$1 |= 0x40000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MirrorClass;
            }
        }

        private Symbols.Symbol TypeCreatorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x80000000000L) == 0L) {
                    this.TypeCreatorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.TypeCreator");
                    this.bitmap$1 |= 0x80000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TypeCreatorClass;
            }
        }

        private Symbols.Symbol TreeCreatorClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x100000000000L) == 0L) {
                    this.TreeCreatorClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.api.TreeCreator");
                    this.bitmap$1 |= 0x100000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TreeCreatorClass;
            }
        }

        private Symbols.Symbol BlackboxContextClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x200000000000L) == 0L) {
                    Symbols.Symbol symbol = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.macros.blackbox.Context");
                    this.BlackboxContextClass = symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : this.scala$reflect$internal$Definitions$DefinitionsClass$$Context_210();
                    this.bitmap$1 |= 0x200000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BlackboxContextClass;
            }
        }

        private Symbols.Symbol WhiteboxContextClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x400000000000L) == 0L) {
                    Symbols.Symbol symbol = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.macros.whitebox.Context");
                    this.WhiteboxContextClass = symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : this.scala$reflect$internal$Definitions$DefinitionsClass$$Context_210();
                    this.bitmap$1 |= 0x400000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.WhiteboxContextClass;
            }
        }

        private Symbols.ClassSymbol MacroImplAnnotation$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x800000000000L) == 0L) {
                    this.MacroImplAnnotation = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(macroImpl.class));
                    this.bitmap$1 |= 0x800000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MacroImplAnnotation;
            }
        }

        private Symbols.ClassSymbol StringContextClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x1000000000000L) == 0L) {
                    this.StringContextClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(StringContext.class));
                    this.bitmap$1 |= 0x1000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StringContextClass;
            }
        }

        private Symbols.Symbol QuasiquoteClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x2000000000000L) == 0L) {
                    Symbols.Symbol symbol = this.ApiUniverseClass();
                    Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                    this.QuasiquoteClass = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() : this.getMemberIfDefined(this.ApiUniverseClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Quasiquote());
                    this.bitmap$1 |= 0x2000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.QuasiquoteClass;
            }
        }

        private Symbols.Symbol QuasiquoteClass_api$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x4000000000000L) == 0L) {
                    Symbols.Symbol symbol = this.QuasiquoteClass();
                    Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                    this.QuasiquoteClass_api = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() : this.getMember(this.QuasiquoteClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().api());
                    this.bitmap$1 |= 0x4000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.QuasiquoteClass_api;
            }
        }

        private Symbols.Symbol QuasiquoteClass_api_apply$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x8000000000000L) == 0L) {
                    Symbols.Symbol symbol = this.QuasiquoteClass_api();
                    Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                    this.QuasiquoteClass_api_apply = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() : this.getMember(this.QuasiquoteClass_api(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
                    this.bitmap$1 |= 0x8000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.QuasiquoteClass_api_apply;
            }
        }

        private Symbols.Symbol QuasiquoteClass_api_unapply$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x10000000000000L) == 0L) {
                    Symbols.Symbol symbol = this.QuasiquoteClass_api();
                    Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                    this.QuasiquoteClass_api_unapply = !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() : this.getMember(this.QuasiquoteClass_api(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().unapply());
                    this.bitmap$1 |= 0x10000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.QuasiquoteClass_api_unapply;
            }
        }

        private Symbols.ClassSymbol ScalaSignatureAnnotation$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x20000000000000L) == 0L) {
                    this.ScalaSignatureAnnotation = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ScalaSignature.class));
                    this.bitmap$1 |= 0x20000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaSignatureAnnotation;
            }
        }

        private Symbols.ClassSymbol ScalaLongSignatureAnnotation$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x40000000000000L) == 0L) {
                    this.ScalaLongSignatureAnnotation = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ScalaLongSignature.class));
                    this.bitmap$1 |= 0x40000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaLongSignatureAnnotation;
            }
        }

        private Symbols.Symbol LambdaMetaFactory$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x80000000000000L) == 0L) {
                    this.LambdaMetaFactory = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("java.lang.invoke.LambdaMetafactory");
                    this.bitmap$1 |= 0x80000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.LambdaMetaFactory;
            }
        }

        private Symbols.Symbol MethodHandle$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x100000000000000L) == 0L) {
                    this.MethodHandle = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("java.lang.invoke.MethodHandle");
                    this.bitmap$1 |= 0x100000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MethodHandle;
            }
        }

        private Symbols.ClassSymbol OptionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x200000000000000L) == 0L) {
                    this.OptionClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Option.class));
                    this.bitmap$1 |= 0x200000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.OptionClass;
            }
        }

        private Symbols.ModuleSymbol OptionModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x400000000000000L) == 0L) {
                    this.OptionModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Option$.class));
                    this.bitmap$1 |= 0x400000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.OptionModule;
            }
        }

        private Symbols.ClassSymbol SomeClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x800000000000000L) == 0L) {
                    this.SomeClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Some.class));
                    this.bitmap$1 |= 0x800000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SomeClass;
            }
        }

        private Symbols.ModuleSymbol NoneModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x1000000000000000L) == 0L) {
                    this.NoneModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(None$.class));
                    this.bitmap$1 |= 0x1000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NoneModule;
            }
        }

        private Symbols.ModuleSymbol SomeModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x2000000000000000L) == 0L) {
                    this.SomeModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredModule(ClassTag$.MODULE$.apply(Some$.class));
                    this.bitmap$1 |= 0x2000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SomeModule;
            }
        }

        private Definitions$DefinitionsClass$VarArityClass$ VarArityClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if (this.VarArityClass$module == null) {
                    this.VarArityClass$module = new Definitions$DefinitionsClass$VarArityClass$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.VarArityClass$module;
            }
        }

        private VarArityClass ProductClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & 0x4000000000000000L) == 0L) {
                    this.ProductClass = new VarArityClass(this, "Product", this.MaxProductArity(), 1, new Some<Symbols.ClassSymbol>(this.UnitClass()));
                    this.bitmap$1 |= 0x4000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ProductClass;
            }
        }

        private VarArityClass TupleClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$1 & Long.MIN_VALUE) == 0L) {
                    this.TupleClass = new VarArityClass(this, "Tuple", this.MaxTupleArity(), 1, this.VarArityClass().$lessinit$greater$default$4());
                    this.bitmap$1 |= Long.MIN_VALUE;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TupleClass;
            }
        }

        private VarArityClass FunctionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 1L) == 0L) {
                    this.FunctionClass = new VarArityClass(this, "Function", this.MaxFunctionArity(), this.VarArityClass().$lessinit$greater$default$3(), this.VarArityClass().$lessinit$greater$default$4());
                    this.bitmap$2 |= 1L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FunctionClass;
            }
        }

        private VarArityClass AbstractFunctionClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 2L) == 0L) {
                    this.AbstractFunctionClass = new VarArityClass(this, "runtime.AbstractFunction", this.MaxFunctionArity(), this.VarArityClass().$lessinit$greater$default$3(), this.VarArityClass().$lessinit$greater$default$4());
                    this.bitmap$2 |= 2L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AbstractFunctionClass;
            }
        }

        private Definitions$DefinitionsClass$MacroContextType$ MacroContextType$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if (this.MacroContextType$module == null) {
                    this.MacroContextType$module = new Definitions$DefinitionsClass$MacroContextType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MacroContextType$module;
            }
        }

        private Symbols.ClassSymbol ProductRootClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 4L) == 0L) {
                    this.ProductRootClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Product.class));
                    this.bitmap$2 |= 4L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ProductRootClass;
            }
        }

        private Symbols.MethodSymbol Any_$eq$eq$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 8L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.Any_$eq$eq = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().EQ(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 8L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_$eq$eq;
            }
        }

        private Symbols.MethodSymbol Any_$bang$eq$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x10L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.Any_$bang$eq = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().NE(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 0x10L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_$bang$eq;
            }
        }

        private Symbols.MethodSymbol Any_equals$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x20L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.Any_equals = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().equals_(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod$default$5(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer()));
                    this.bitmap$2 |= 0x20L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_equals;
            }
        }

        private Symbols.MethodSymbol Any_hashCode$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x40L) == 0L) {
                    this.Any_hashCode = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().hashCode_(), Nil$.MODULE$, this.IntTpe(), Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod$default$5(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer()));
                    this.bitmap$2 |= 0x40L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_hashCode;
            }
        }

        private Symbols.MethodSymbol Any_toString$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x80L) == 0L) {
                    this.Any_toString = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().toString_(), Nil$.MODULE$, this.StringTpe(), Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod$default$5(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer()));
                    this.bitmap$2 |= 0x80L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_toString;
            }
        }

        private Symbols.MethodSymbol Any_$hash$hash$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x100L) == 0L) {
                    this.Any_$hash$hash = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().HASHHASH(), Nil$.MODULE$, this.IntTpe(), 32L);
                    this.bitmap$2 |= 0x100L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_$hash$hash;
            }
        }

        private Symbols.MethodSymbol Any_getClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x200L) == 0L) {
                    this.Any_getClass = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getClass_(), Nil$.MODULE$, this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getClass_()).tpe().resultType(), 16L);
                    this.bitmap$2 |= 0x200L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_getClass;
            }
        }

        private Symbols.MethodSymbol Any_isInstanceOf$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x400L) == 0L) {
                    this.Any_isInstanceOf = this.newT1NullaryMethod(this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().isInstanceOf_(), 32L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Symbols.Symbol x$20) {
                            return this.$outer.BooleanTpe();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$2 |= 0x400L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_isInstanceOf;
            }
        }

        private Symbols.MethodSymbol Any_asInstanceOf$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x800L) == 0L) {
                    this.Any_asInstanceOf = this.newT1NullaryMethod(this.AnyClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().asInstanceOf_(), 32L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Types.Type apply(Symbols.Symbol x$21) {
                            return x$21.typeConstructor();
                        }
                    }));
                    this.bitmap$2 |= 0x800L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Any_asInstanceOf;
            }
        }

        private Set primitiveGetClassMethods$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x1000L) == 0L) {
                    this.primitiveGetClassMethods = (Set)((SetLike)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{this.Any_getClass(), this.AnyVal_getClass()}))).$plus$plus(this.ScalaValueClasses().map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Symbols.Symbol apply(Symbols.ClassSymbol x$22) {
                            return x$22.tpe().member(this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getClass_());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom()));
                    this.bitmap$2 |= 0x1000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.primitiveGetClassMethods;
            }
        }

        private Set getClassMethods$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x2000L) == 0L) {
                    this.getClassMethods = (Set)this.primitiveGetClassMethods().$plus(this.Object_getClass());
                    this.bitmap$2 |= 0x2000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.getClassMethods;
            }
        }

        private Symbols.MethodSymbol Object_$hash$hash$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x4000L) == 0L) {
                    this.Object_$hash$hash = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().HASHHASH(), Nil$.MODULE$, this.IntTpe(), 32L);
                    this.bitmap$2 |= 0x4000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_$hash$hash;
            }
        }

        private Symbols.MethodSymbol Object_$eq$eq$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x8000L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.Object_$eq$eq = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().EQ(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 0x8000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_$eq$eq;
            }
        }

        private Symbols.MethodSymbol Object_$bang$eq$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x10000L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.Object_$bang$eq = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().NE(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 0x10000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_$bang$eq;
            }
        }

        private Symbols.MethodSymbol Object_eq$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x20000L) == 0L) {
                    Types.Type type = this.AnyRefTpe();
                    this.Object_eq = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().eq(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 0x20000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_eq;
            }
        }

        private Symbols.MethodSymbol Object_ne$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x40000L) == 0L) {
                    Types.Type type = this.AnyRefTpe();
                    this.Object_ne = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().ne(), Nil$.MODULE$.$colon$colon(type), this.BooleanTpe(), 32L);
                    this.bitmap$2 |= 0x40000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_ne;
            }
        }

        private Symbols.MethodSymbol Object_isInstanceOf$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x80000L) == 0L) {
                    this.Object_isInstanceOf = this.newT1NoParamsMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().isInstanceOf_Ob(), 0x400000200020L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final Types.Type apply(Symbols.Symbol x$35) {
                            return this.$outer.BooleanTpe();
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                    this.bitmap$2 |= 0x80000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_isInstanceOf;
            }
        }

        private Symbols.MethodSymbol Object_asInstanceOf$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x100000L) == 0L) {
                    this.Object_asInstanceOf = this.newT1NoParamsMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().asInstanceOf_Ob(), 0x400000200020L, (Function1<Symbols.Symbol, Types.Type>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Types.Type apply(Symbols.Symbol x$36) {
                            return x$36.typeConstructor();
                        }
                    }));
                    this.bitmap$2 |= 0x100000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_asInstanceOf;
            }
        }

        private Symbols.MethodSymbol Object_synchronized$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x200000L) == 0L) {
                    this.Object_synchronized = this.newPolyMethod(1, this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().synchronized_(), 32L, (Function1<List<Symbols.Symbol>, Tuple2<Option<List<Types.Type>>, Types.Type>>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final Tuple2<Some<List<Types.Type>>, Types.Type> apply(List<Symbols.Symbol> tps) {
                            return new Tuple2<Some<List<Types.Type>>, Types.Type>(new Some<GenTraversable>(List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{tps.head().typeConstructor()}))), tps.head().typeConstructor());
                        }
                    }));
                    this.bitmap$2 |= 0x200000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.Object_synchronized;
            }
        }

        private Symbols.MethodSymbol String_$plus$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x400000L) == 0L) {
                    Types.Type type = this.AnyTpe();
                    this.String_$plus = Definitions$class.scala$reflect$internal$Definitions$$enterNewMethod(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.StringClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().raw().PLUS(), Nil$.MODULE$.$colon$colon(type), this.StringTpe(), 32L);
                    this.bitmap$2 |= 0x400000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.String_$plus;
            }
        }

        private Symbols.ClassSymbol ObjectRefClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x800000L) == 0L) {
                    this.ObjectRefClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ObjectRef.class));
                    this.bitmap$2 |= 0x800000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ObjectRefClass;
            }
        }

        private Symbols.ClassSymbol VolatileObjectRefClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x1000000L) == 0L) {
                    this.VolatileObjectRefClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(VolatileObjectRef.class));
                    this.bitmap$2 |= 0x1000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.VolatileObjectRefClass;
            }
        }

        private Symbols.ModuleSymbol RuntimeStaticsModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x2000000L) == 0L) {
                    this.RuntimeStaticsModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredModule("scala.runtime.Statics");
                    this.bitmap$2 |= 0x2000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RuntimeStaticsModule;
            }
        }

        private Symbols.ModuleSymbol BoxesRunTimeModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x4000000L) == 0L) {
                    this.BoxesRunTimeModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredModule("scala.runtime.BoxesRunTime");
                    this.bitmap$2 |= 0x4000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxesRunTimeModule;
            }
        }

        private Symbols.Symbol BoxesRunTimeClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x8000000L) == 0L) {
                    this.BoxesRunTimeClass = this.BoxesRunTimeModule().moduleClass();
                    this.bitmap$2 |= 0x8000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxesRunTimeClass;
            }
        }

        private Symbols.ClassSymbol BoxedNumberClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x10000000L) == 0L) {
                    this.BoxedNumberClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().BoxedNumber());
                    this.bitmap$2 |= 0x10000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedNumberClass;
            }
        }

        private Symbols.ClassSymbol BoxedCharacterClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x20000000L) == 0L) {
                    this.BoxedCharacterClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().BoxedCharacter());
                    this.bitmap$2 |= 0x20000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedCharacterClass;
            }
        }

        private Symbols.ClassSymbol BoxedBooleanClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x40000000L) == 0L) {
                    this.BoxedBooleanClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassByName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().BoxedBoolean());
                    this.bitmap$2 |= 0x40000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedBooleanClass;
            }
        }

        private Symbols.ClassSymbol BoxedByteClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x80000000L) == 0L) {
                    this.BoxedByteClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Byte.class));
                    this.bitmap$2 |= 0x80000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedByteClass;
            }
        }

        private Symbols.ClassSymbol BoxedShortClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x100000000L) == 0L) {
                    this.BoxedShortClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Short.class));
                    this.bitmap$2 |= 0x100000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedShortClass;
            }
        }

        private Symbols.ClassSymbol BoxedIntClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x200000000L) == 0L) {
                    this.BoxedIntClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Integer.class));
                    this.bitmap$2 |= 0x200000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedIntClass;
            }
        }

        private Symbols.ClassSymbol BoxedLongClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x400000000L) == 0L) {
                    this.BoxedLongClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Long.class));
                    this.bitmap$2 |= 0x400000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedLongClass;
            }
        }

        private Symbols.ClassSymbol BoxedFloatClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x800000000L) == 0L) {
                    this.BoxedFloatClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Float.class));
                    this.bitmap$2 |= 0x800000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedFloatClass;
            }
        }

        private Symbols.ClassSymbol BoxedDoubleClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x1000000000L) == 0L) {
                    this.BoxedDoubleClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Double.class));
                    this.bitmap$2 |= 0x1000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedDoubleClass;
            }
        }

        private Symbols.ClassSymbol BoxedUnitClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x2000000000L) == 0L) {
                    this.BoxedUnitClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(BoxedUnit.class));
                    this.bitmap$2 |= 0x2000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedUnitClass;
            }
        }

        private Symbols.ModuleSymbol BoxedUnitModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x4000000000L) == 0L) {
                    this.BoxedUnitModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredModule("scala.runtime.BoxedUnit");
                    this.bitmap$2 |= 0x4000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BoxedUnitModule;
            }
        }

        private Symbols.ClassSymbol AnnotationClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x8000000000L) == 0L) {
                    this.AnnotationClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Annotation.class));
                    this.bitmap$2 |= 0x8000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnnotationClass;
            }
        }

        private Symbols.ClassSymbol ClassfileAnnotationClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x10000000000L) == 0L) {
                    this.ClassfileAnnotationClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(ClassfileAnnotation.class));
                    this.bitmap$2 |= 0x10000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassfileAnnotationClass;
            }
        }

        private Symbols.ClassSymbol StaticAnnotationClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x20000000000L) == 0L) {
                    this.StaticAnnotationClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(StaticAnnotation.class));
                    this.bitmap$2 |= 0x20000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.StaticAnnotationClass;
            }
        }

        private Symbols.ClassSymbol AnnotationRetentionAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x40000000000L) == 0L) {
                    this.AnnotationRetentionAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(Retention.class));
                    this.bitmap$2 |= 0x40000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnnotationRetentionAttr;
            }
        }

        private Symbols.ClassSymbol AnnotationRetentionPolicyAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x80000000000L) == 0L) {
                    this.AnnotationRetentionPolicyAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(RetentionPolicy.class));
                    this.bitmap$2 |= 0x80000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnnotationRetentionPolicyAttr;
            }
        }

        private Symbols.ClassSymbol BridgeClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x100000000000L) == 0L) {
                    this.BridgeClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(bridge.class));
                    this.bitmap$2 |= 0x100000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BridgeClass;
            }
        }

        private Symbols.ClassSymbol ElidableMethodClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x200000000000L) == 0L) {
                    this.ElidableMethodClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(elidable.class));
                    this.bitmap$2 |= 0x200000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ElidableMethodClass;
            }
        }

        private Symbols.ClassSymbol ImplicitNotFoundClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x400000000000L) == 0L) {
                    this.ImplicitNotFoundClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(implicitNotFound.class));
                    this.bitmap$2 |= 0x400000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ImplicitNotFoundClass;
            }
        }

        private Symbols.ClassSymbol MigrationAnnotationClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x800000000000L) == 0L) {
                    this.MigrationAnnotationClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(migration.class));
                    this.bitmap$2 |= 0x800000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MigrationAnnotationClass;
            }
        }

        private Symbols.ClassSymbol ScalaStrictFPAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x1000000000000L) == 0L) {
                    this.ScalaStrictFPAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(strictfp.class));
                    this.bitmap$2 |= 0x1000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaStrictFPAttr;
            }
        }

        private Symbols.ClassSymbol SwitchClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x2000000000000L) == 0L) {
                    this.SwitchClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(switch.class));
                    this.bitmap$2 |= 0x2000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SwitchClass;
            }
        }

        private Symbols.ClassSymbol TailrecClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x4000000000000L) == 0L) {
                    this.TailrecClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(tailrec.class));
                    this.bitmap$2 |= 0x4000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TailrecClass;
            }
        }

        private Symbols.ClassSymbol VarargsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x8000000000000L) == 0L) {
                    this.VarargsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(varargs.class));
                    this.bitmap$2 |= 0x8000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.VarargsClass;
            }
        }

        private Symbols.ClassSymbol uncheckedStableClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x10000000000000L) == 0L) {
                    this.uncheckedStableClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(uncheckedStable.class));
                    this.bitmap$2 |= 0x10000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.uncheckedStableClass;
            }
        }

        private Symbols.ClassSymbol uncheckedVarianceClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x20000000000000L) == 0L) {
                    this.uncheckedVarianceClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(uncheckedVariance.class));
                    this.bitmap$2 |= 0x20000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.uncheckedVarianceClass;
            }
        }

        private Symbols.ClassSymbol BeanPropertyAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x40000000000000L) == 0L) {
                    this.BeanPropertyAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(BeanProperty.class));
                    this.bitmap$2 |= 0x40000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BeanPropertyAttr;
            }
        }

        private Symbols.ClassSymbol BooleanBeanPropertyAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x80000000000000L) == 0L) {
                    this.BooleanBeanPropertyAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(BooleanBeanProperty.class));
                    this.bitmap$2 |= 0x80000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BooleanBeanPropertyAttr;
            }
        }

        private Symbols.Symbol CompileTimeOnlyAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x100000000000000L) == 0L) {
                    this.CompileTimeOnlyAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.annotation.compileTimeOnly");
                    this.bitmap$2 |= 0x100000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.CompileTimeOnlyAttr;
            }
        }

        private Symbols.ClassSymbol DeprecatedAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x200000000000000L) == 0L) {
                    this.DeprecatedAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(deprecated.class));
                    this.bitmap$2 |= 0x200000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DeprecatedAttr;
            }
        }

        private Symbols.ClassSymbol DeprecatedNameAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x400000000000000L) == 0L) {
                    this.DeprecatedNameAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(deprecatedName.class));
                    this.bitmap$2 |= 0x400000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DeprecatedNameAttr;
            }
        }

        private Symbols.ClassSymbol DeprecatedInheritanceAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x800000000000000L) == 0L) {
                    this.DeprecatedInheritanceAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(deprecatedInheritance.class));
                    this.bitmap$2 |= 0x800000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DeprecatedInheritanceAttr;
            }
        }

        private Symbols.ClassSymbol DeprecatedOverridingAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x1000000000000000L) == 0L) {
                    this.DeprecatedOverridingAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(deprecatedOverriding.class));
                    this.bitmap$2 |= 0x1000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DeprecatedOverridingAttr;
            }
        }

        private Symbols.ClassSymbol NativeAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x2000000000000000L) == 0L) {
                    this.NativeAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(native.class));
                    this.bitmap$2 |= 0x2000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.NativeAttr;
            }
        }

        private Symbols.ClassSymbol RemoteAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & 0x4000000000000000L) == 0L) {
                    this.RemoteAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(remote.class));
                    this.bitmap$2 |= 0x4000000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RemoteAttr;
            }
        }

        private Symbols.ClassSymbol ScalaInlineClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$2 & Long.MIN_VALUE) == 0L) {
                    this.ScalaInlineClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(inline.class));
                    this.bitmap$2 |= Long.MIN_VALUE;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaInlineClass;
            }
        }

        private Symbols.ClassSymbol ScalaNoInlineClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 1L) == 0L) {
                    this.ScalaNoInlineClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(noinline.class));
                    this.bitmap$3 |= 1L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaNoInlineClass;
            }
        }

        private Symbols.ClassSymbol SerialVersionUIDAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 2L) == 0L) {
                    this.SerialVersionUIDAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(SerialVersionUID.class));
                    this.bitmap$3 |= 2L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SerialVersionUIDAttr;
            }
        }

        private AnnotationInfos.AnnotationInfo SerialVersionUIDAnnotation$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 4L) == 0L) {
                    Tuple2[] tuple2Array = new Tuple2[1];
                    AnnotationInfos.LiteralAnnotArg literalAnnotArg = new AnnotationInfos.LiteralAnnotArg(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), new Constants.Constant(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), BoxesRunTime.boxToInteger(0)));
                    Names.TermName termName = Predef$.MODULE$.ArrowAssoc(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().value());
                    Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                    tuple2Array[0] = new Tuple2<Names.TermName, AnnotationInfos.LiteralAnnotArg>(termName, literalAnnotArg);
                    this.SerialVersionUIDAnnotation = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().AnnotationInfo().apply(this.SerialVersionUIDAttr().tpe(), Nil$.MODULE$, (List<Tuple2<Names.Name, AnnotationInfos.ClassfileAnnotArg>>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array)));
                    this.bitmap$3 |= 4L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SerialVersionUIDAnnotation;
            }
        }

        private Symbols.ClassSymbol SpecializedClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 8L) == 0L) {
                    this.SpecializedClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(specialized.class));
                    this.bitmap$3 |= 8L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SpecializedClass;
            }
        }

        private Symbols.ClassSymbol ThrowsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x10L) == 0L) {
                    this.ThrowsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(throws.class));
                    this.bitmap$3 |= 0x10L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ThrowsClass;
            }
        }

        private Symbols.ClassSymbol TransientAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x20L) == 0L) {
                    this.TransientAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(transient.class));
                    this.bitmap$3 |= 0x20L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TransientAttr;
            }
        }

        private Symbols.ClassSymbol UncheckedClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x40L) == 0L) {
                    this.UncheckedClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(unchecked.class));
                    this.bitmap$3 |= 0x40L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UncheckedClass;
            }
        }

        private Symbols.Symbol UncheckedBoundsClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x80L) == 0L) {
                    this.UncheckedBoundsClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.internal.annotations.uncheckedBounds");
                    this.bitmap$3 |= 0x80L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UncheckedBoundsClass;
            }
        }

        private Symbols.ClassSymbol UnspecializedClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x100L) == 0L) {
                    this.UnspecializedClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(unspecialized.class));
                    this.bitmap$3 |= 0x100L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnspecializedClass;
            }
        }

        private Symbols.ClassSymbol VolatileAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x200L) == 0L) {
                    this.VolatileAttr = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(volatile.class));
                    this.bitmap$3 |= 0x200L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.VolatileAttr;
            }
        }

        private Symbols.ClassSymbol BeanGetterTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x400L) == 0L) {
                    this.BeanGetterTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(beanGetter.class));
                    this.bitmap$3 |= 0x400L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BeanGetterTargetClass;
            }
        }

        private Symbols.ClassSymbol BeanSetterTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x800L) == 0L) {
                    this.BeanSetterTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(beanSetter.class));
                    this.bitmap$3 |= 0x800L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BeanSetterTargetClass;
            }
        }

        private Symbols.ClassSymbol FieldTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x1000L) == 0L) {
                    this.FieldTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(field.class));
                    this.bitmap$3 |= 0x1000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FieldTargetClass;
            }
        }

        private Symbols.ClassSymbol GetterTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x2000L) == 0L) {
                    this.GetterTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(getter.class));
                    this.bitmap$3 |= 0x2000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.GetterTargetClass;
            }
        }

        private Symbols.ClassSymbol ParamTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x4000L) == 0L) {
                    this.ParamTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(param.class));
                    this.bitmap$3 |= 0x4000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ParamTargetClass;
            }
        }

        private Symbols.ClassSymbol SetterTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x8000L) == 0L) {
                    this.SetterTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(setter.class));
                    this.bitmap$3 |= 0x8000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SetterTargetClass;
            }
        }

        private Symbols.ClassSymbol ObjectTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x10000L) == 0L) {
                    this.ObjectTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(companionObject.class));
                    this.bitmap$3 |= 0x10000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ObjectTargetClass;
            }
        }

        private Symbols.ClassSymbol ClassTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x20000L) == 0L) {
                    this.ClassTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(companionClass.class));
                    this.bitmap$3 |= 0x20000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ClassTargetClass;
            }
        }

        private Symbols.ClassSymbol MethodTargetClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x40000L) == 0L) {
                    this.MethodTargetClass = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(companionMethod.class));
                    this.bitmap$3 |= 0x40000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MethodTargetClass;
            }
        }

        private Symbols.ClassSymbol LanguageFeatureAnnot$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x80000L) == 0L) {
                    this.LanguageFeatureAnnot = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).requiredClass(ClassTag$.MODULE$.apply(languageFeature.class));
                    this.bitmap$3 |= 0x80000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.LanguageFeatureAnnot;
            }
        }

        private Symbols.ModuleSymbol languageFeatureModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x100000L) == 0L) {
                    this.languageFeatureModule = ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredModule("scala.languageFeature");
                    this.bitmap$3 |= 0x100000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.languageFeatureModule;
            }
        }

        private Set metaAnnotations$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x200000L) == 0L) {
                    this.metaAnnotations = ((Scopes.Scope)((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackage(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply("scala.annotation.meta")).info().members().filter((Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final boolean apply(Symbols.Symbol x$38) {
                            return x$38.isSubClass(this.$outer.StaticAnnotationClass());
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }))).toSet();
                    this.bitmap$3 |= 0x200000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.metaAnnotations;
            }
        }

        private Symbols.ClassSymbol AnnotationDefaultAttr$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x400000L) == 0L) {
                    Symbols.ClassSymbol classSymbol;
                    Symbols.ClassSymbol sym = this.RuntimePackageClass().newClassSymbol(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().AnnotationDefaultATTR(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPosition(), 0L);
                    sym.setInfo(new Types.ClassInfoType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.AnnotationClass().tpe()})), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newScope(), sym));
                    this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().markAllCompleted(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{sym}));
                    List list2 = (List)this.RuntimePackageClass().info().decls().toList().filter((Function1)((Object)new Serializable(this, sym){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.ClassSymbol sym$3;

                        public final boolean apply(Symbols.Symbol x$39) {
                            Names.Name name = x$39.name();
                            Names.TypeName typeName = this.sym$3.name();
                            return !(name != null ? !name.equals(typeName) : typeName != null);
                        }
                        {
                            this.sym$3 = sym$3;
                        }
                    }));
                    if (list2 instanceof $colon$colon) {
                        $colon$colon $colon$colon = ($colon$colon)list2;
                        classSymbol = (Symbols.ClassSymbol)$colon$colon.head();
                    } else {
                        this.RuntimePackageClass().info().decls().enter(sym);
                        sym.info().decls().enter(sym.newClassConstructor(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPosition()));
                        classSymbol = sym;
                    }
                    this.AnnotationDefaultAttr = classSymbol;
                    this.bitmap$3 |= 0x400000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.AnnotationDefaultAttr;
            }
        }

        private Phase erasurePhase$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x800000L) == 0L) {
                    this.erasurePhase = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().findPhaseWithName("erasure");
                    this.bitmap$3 |= 0x800000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.erasurePhase;
            }
        }

        private Set isPhantomClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x1000000L) == 0L) {
                    this.isPhantomClass = (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{this.AnyClass(), this.AnyValClass(), this.NullClass(), this.NothingClass()}));
                    this.bitmap$3 |= 0x1000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.isPhantomClass;
            }
        }

        private List syntheticCoreClasses$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x2000000L) == 0L) {
                    this.syntheticCoreClasses = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.TypeSymbol[]{this.AnnotationDefaultAttr(), this.RepeatedParamClass(), this.JavaRepeatedParamClass(), this.ByNameParamClass(), this.AnyClass(), this.AnyRefClass(), this.AnyValClass(), this.NullClass(), this.NothingClass(), this.SingletonClass()}));
                    this.bitmap$3 |= 0x2000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.syntheticCoreClasses;
            }
        }

        private List syntheticCoreMethods$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x4000000L) == 0L) {
                    this.syntheticCoreMethods = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.MethodSymbol[]{this.Any_$eq$eq(), this.Any_$bang$eq(), this.Any_equals(), this.Any_hashCode(), this.Any_toString(), this.Any_getClass(), this.Any_isInstanceOf(), this.Any_asInstanceOf(), this.Any_$hash$hash(), this.Object_eq(), this.Object_ne(), this.Object_$eq$eq(), this.Object_$bang$eq(), this.Object_$hash$hash(), this.Object_synchronized(), this.Object_isInstanceOf(), this.Object_asInstanceOf(), this.String_$plus()}));
                    this.bitmap$3 |= 0x4000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.syntheticCoreMethods;
            }
        }

        private List hijackedCoreClasses$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x8000000L) == 0L) {
                    this.hijackedCoreClasses = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.ClassSymbol[]{this.ComparableClass(), this.JavaSerializableClass()}));
                    this.bitmap$3 |= 0x8000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.hijackedCoreClasses;
            }
        }

        private List symbolsNotPresentInBytecode$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x10000000L) == 0L) {
                    this.symbolsNotPresentInBytecode = this.syntheticCoreClasses().$plus$plus(this.syntheticCoreMethods(), List$.MODULE$.canBuildFrom()).$plus$plus(this.hijackedCoreClasses(), List$.MODULE$.canBuildFrom());
                    this.bitmap$3 |= 0x10000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.symbolsNotPresentInBytecode;
            }
        }

        private Set isPossibleSyntheticParent$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x20000000L) == 0L) {
                    this.isPossibleSyntheticParent = (Set)this.ProductClass().seq().toSet().$plus(this.ProductRootClass()).$plus((Symbols.ClassSymbol)this.SerializableClass());
                    this.bitmap$3 |= 0x20000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.isPossibleSyntheticParent;
            }
        }

        private Set boxedValueClassesSet$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x40000000L) == 0L) {
                    this.boxedValueClassesSet = (Set)this.boxedClass().values().toSet().$plus(this.BoxedUnitClass());
                    this.bitmap$3 |= 0x40000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.boxedValueClassesSet;
            }
        }

        @Override
        public scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight() {
            return this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight;
        }

        @Override
        public scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag() {
            return this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag;
        }

        private scala.collection.immutable.Map abbrvTag$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x80000000L) == 0L) {
                    this.abbrvTag = Definitions$ValueClassDefinitions$class.abbrvTag(this);
                    this.bitmap$3 |= 0x80000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.abbrvTag;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Object> abbrvTag() {
            return (this.bitmap$3 & 0x80000000L) == 0L ? this.abbrvTag$lzycompute() : this.abbrvTag;
        }

        private scala.collection.immutable.Map numericWeight$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x100000000L) == 0L) {
                    this.numericWeight = Definitions$ValueClassDefinitions$class.numericWeight(this);
                    this.bitmap$3 |= 0x100000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.numericWeight;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Object> numericWeight() {
            return (this.bitmap$3 & 0x100000000L) == 0L ? this.numericWeight$lzycompute() : this.numericWeight;
        }

        private scala.collection.immutable.Map boxedModule$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x200000000L) == 0L) {
                    this.boxedModule = Definitions$ValueClassDefinitions$class.boxedModule(this);
                    this.bitmap$3 |= 0x200000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.boxedModule;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ModuleSymbol> boxedModule() {
            return (this.bitmap$3 & 0x200000000L) == 0L ? this.boxedModule$lzycompute() : this.boxedModule;
        }

        private scala.collection.immutable.Map boxedClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x400000000L) == 0L) {
                    this.boxedClass = Definitions$ValueClassDefinitions$class.boxedClass(this);
                    this.bitmap$3 |= 0x400000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.boxedClass;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> boxedClass() {
            return (this.bitmap$3 & 0x400000000L) == 0L ? this.boxedClass$lzycompute() : this.boxedClass;
        }

        private scala.collection.immutable.Map refClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x800000000L) == 0L) {
                    this.refClass = Definitions$ValueClassDefinitions$class.refClass(this);
                    this.bitmap$3 |= 0x800000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.refClass;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> refClass() {
            return (this.bitmap$3 & 0x800000000L) == 0L ? this.refClass$lzycompute() : this.refClass;
        }

        private scala.collection.immutable.Map volatileRefClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x1000000000L) == 0L) {
                    this.volatileRefClass = Definitions$ValueClassDefinitions$class.volatileRefClass(this);
                    this.bitmap$3 |= 0x1000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.volatileRefClass;
            }
        }

        @Override
        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> volatileRefClass() {
            return (this.bitmap$3 & 0x1000000000L) == 0L ? this.volatileRefClass$lzycompute() : this.volatileRefClass;
        }

        private Symbols.ClassSymbol UnitClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x2000000000L) == 0L) {
                    this.UnitClass = Definitions$ValueClassDefinitions$class.UnitClass(this);
                    this.bitmap$3 |= 0x2000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnitClass;
            }
        }

        @Override
        public Symbols.ClassSymbol UnitClass() {
            return (this.bitmap$3 & 0x2000000000L) == 0L ? this.UnitClass$lzycompute() : this.UnitClass;
        }

        private Symbols.ClassSymbol ByteClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x4000000000L) == 0L) {
                    this.ByteClass = Definitions$ValueClassDefinitions$class.ByteClass(this);
                    this.bitmap$3 |= 0x4000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ByteClass;
            }
        }

        @Override
        public Symbols.ClassSymbol ByteClass() {
            return (this.bitmap$3 & 0x4000000000L) == 0L ? this.ByteClass$lzycompute() : this.ByteClass;
        }

        private Symbols.ClassSymbol ShortClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x8000000000L) == 0L) {
                    this.ShortClass = Definitions$ValueClassDefinitions$class.ShortClass(this);
                    this.bitmap$3 |= 0x8000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ShortClass;
            }
        }

        @Override
        public Symbols.ClassSymbol ShortClass() {
            return (this.bitmap$3 & 0x8000000000L) == 0L ? this.ShortClass$lzycompute() : this.ShortClass;
        }

        private Symbols.ClassSymbol CharClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x10000000000L) == 0L) {
                    this.CharClass = Definitions$ValueClassDefinitions$class.CharClass(this);
                    this.bitmap$3 |= 0x10000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.CharClass;
            }
        }

        @Override
        public Symbols.ClassSymbol CharClass() {
            return (this.bitmap$3 & 0x10000000000L) == 0L ? this.CharClass$lzycompute() : this.CharClass;
        }

        private Symbols.ClassSymbol IntClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x20000000000L) == 0L) {
                    this.IntClass = Definitions$ValueClassDefinitions$class.IntClass(this);
                    this.bitmap$3 |= 0x20000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.IntClass;
            }
        }

        @Override
        public Symbols.ClassSymbol IntClass() {
            return (this.bitmap$3 & 0x20000000000L) == 0L ? this.IntClass$lzycompute() : this.IntClass;
        }

        private Symbols.ClassSymbol LongClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x40000000000L) == 0L) {
                    this.LongClass = Definitions$ValueClassDefinitions$class.LongClass(this);
                    this.bitmap$3 |= 0x40000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.LongClass;
            }
        }

        @Override
        public Symbols.ClassSymbol LongClass() {
            return (this.bitmap$3 & 0x40000000000L) == 0L ? this.LongClass$lzycompute() : this.LongClass;
        }

        private Symbols.ClassSymbol FloatClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x80000000000L) == 0L) {
                    this.FloatClass = Definitions$ValueClassDefinitions$class.FloatClass(this);
                    this.bitmap$3 |= 0x80000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FloatClass;
            }
        }

        @Override
        public Symbols.ClassSymbol FloatClass() {
            return (this.bitmap$3 & 0x80000000000L) == 0L ? this.FloatClass$lzycompute() : this.FloatClass;
        }

        private Symbols.ClassSymbol DoubleClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x100000000000L) == 0L) {
                    this.DoubleClass = Definitions$ValueClassDefinitions$class.DoubleClass(this);
                    this.bitmap$3 |= 0x100000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DoubleClass;
            }
        }

        @Override
        public Symbols.ClassSymbol DoubleClass() {
            return (this.bitmap$3 & 0x100000000000L) == 0L ? this.DoubleClass$lzycompute() : this.DoubleClass;
        }

        private Symbols.ClassSymbol BooleanClass$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x200000000000L) == 0L) {
                    this.BooleanClass = Definitions$ValueClassDefinitions$class.BooleanClass(this);
                    this.bitmap$3 |= 0x200000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BooleanClass;
            }
        }

        @Override
        public Symbols.ClassSymbol BooleanClass() {
            return (this.bitmap$3 & 0x200000000000L) == 0L ? this.BooleanClass$lzycompute() : this.BooleanClass;
        }

        private Types.Type UnitTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x400000000000L) == 0L) {
                    this.UnitTpe = Definitions$ValueClassDefinitions$class.UnitTpe(this);
                    this.bitmap$3 |= 0x400000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnitTpe;
            }
        }

        @Override
        public Types.Type UnitTpe() {
            return (this.bitmap$3 & 0x400000000000L) == 0L ? this.UnitTpe$lzycompute() : this.UnitTpe;
        }

        private Types.Type ByteTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x800000000000L) == 0L) {
                    this.ByteTpe = Definitions$ValueClassDefinitions$class.ByteTpe(this);
                    this.bitmap$3 |= 0x800000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ByteTpe;
            }
        }

        @Override
        public Types.Type ByteTpe() {
            return (this.bitmap$3 & 0x800000000000L) == 0L ? this.ByteTpe$lzycompute() : this.ByteTpe;
        }

        private Types.Type ShortTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x1000000000000L) == 0L) {
                    this.ShortTpe = Definitions$ValueClassDefinitions$class.ShortTpe(this);
                    this.bitmap$3 |= 0x1000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ShortTpe;
            }
        }

        @Override
        public Types.Type ShortTpe() {
            return (this.bitmap$3 & 0x1000000000000L) == 0L ? this.ShortTpe$lzycompute() : this.ShortTpe;
        }

        private Types.Type CharTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x2000000000000L) == 0L) {
                    this.CharTpe = Definitions$ValueClassDefinitions$class.CharTpe(this);
                    this.bitmap$3 |= 0x2000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.CharTpe;
            }
        }

        @Override
        public Types.Type CharTpe() {
            return (this.bitmap$3 & 0x2000000000000L) == 0L ? this.CharTpe$lzycompute() : this.CharTpe;
        }

        private Types.Type IntTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x4000000000000L) == 0L) {
                    this.IntTpe = Definitions$ValueClassDefinitions$class.IntTpe(this);
                    this.bitmap$3 |= 0x4000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.IntTpe;
            }
        }

        @Override
        public Types.Type IntTpe() {
            return (this.bitmap$3 & 0x4000000000000L) == 0L ? this.IntTpe$lzycompute() : this.IntTpe;
        }

        private Types.Type LongTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x8000000000000L) == 0L) {
                    this.LongTpe = Definitions$ValueClassDefinitions$class.LongTpe(this);
                    this.bitmap$3 |= 0x8000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.LongTpe;
            }
        }

        @Override
        public Types.Type LongTpe() {
            return (this.bitmap$3 & 0x8000000000000L) == 0L ? this.LongTpe$lzycompute() : this.LongTpe;
        }

        private Types.Type FloatTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x10000000000000L) == 0L) {
                    this.FloatTpe = Definitions$ValueClassDefinitions$class.FloatTpe(this);
                    this.bitmap$3 |= 0x10000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FloatTpe;
            }
        }

        @Override
        public Types.Type FloatTpe() {
            return (this.bitmap$3 & 0x10000000000000L) == 0L ? this.FloatTpe$lzycompute() : this.FloatTpe;
        }

        private Types.Type DoubleTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x20000000000000L) == 0L) {
                    this.DoubleTpe = Definitions$ValueClassDefinitions$class.DoubleTpe(this);
                    this.bitmap$3 |= 0x20000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.DoubleTpe;
            }
        }

        @Override
        public Types.Type DoubleTpe() {
            return (this.bitmap$3 & 0x20000000000000L) == 0L ? this.DoubleTpe$lzycompute() : this.DoubleTpe;
        }

        private Types.Type BooleanTpe$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x40000000000000L) == 0L) {
                    this.BooleanTpe = Definitions$ValueClassDefinitions$class.BooleanTpe(this);
                    this.bitmap$3 |= 0x40000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.BooleanTpe;
            }
        }

        @Override
        public Types.Type BooleanTpe() {
            return (this.bitmap$3 & 0x40000000000000L) == 0L ? this.BooleanTpe$lzycompute() : this.BooleanTpe;
        }

        private List ScalaNumericValueClasses$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x80000000000000L) == 0L) {
                    this.ScalaNumericValueClasses = Definitions$ValueClassDefinitions$class.ScalaNumericValueClasses(this);
                    this.bitmap$3 |= 0x80000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaNumericValueClasses;
            }
        }

        @Override
        public List<Symbols.ClassSymbol> ScalaNumericValueClasses() {
            return (this.bitmap$3 & 0x80000000000000L) == 0L ? this.ScalaNumericValueClasses$lzycompute() : this.ScalaNumericValueClasses;
        }

        private List ScalaValueClassesNoUnit$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x100000000000000L) == 0L) {
                    this.ScalaValueClassesNoUnit = Definitions$ValueClassDefinitions$class.ScalaValueClassesNoUnit(this);
                    this.bitmap$3 |= 0x100000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaValueClassesNoUnit;
            }
        }

        @Override
        public List<Symbols.ClassSymbol> ScalaValueClassesNoUnit() {
            return (this.bitmap$3 & 0x100000000000000L) == 0L ? this.ScalaValueClassesNoUnit$lzycompute() : this.ScalaValueClassesNoUnit;
        }

        private List ScalaValueClasses$lzycompute() {
            DefinitionsClass definitionsClass = this;
            synchronized (definitionsClass) {
                if ((this.bitmap$3 & 0x200000000000000L) == 0L) {
                    this.ScalaValueClasses = Definitions$ValueClassDefinitions$class.ScalaValueClasses(this);
                    this.bitmap$3 |= 0x200000000000000L;
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaValueClasses;
            }
        }

        @Override
        public List<Symbols.ClassSymbol> ScalaValueClasses() {
            return (this.bitmap$3 & 0x200000000000000L) == 0L ? this.ScalaValueClasses$lzycompute() : this.ScalaValueClasses;
        }

        @Override
        public void scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight_$eq(scala.collection.immutable.Map x$1) {
            this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight = x$1;
        }

        @Override
        public void scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag_$eq(scala.collection.immutable.Map x$1) {
            this.scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag = x$1;
        }

        @Override
        public boolean isNumericSubClass(Symbols.Symbol sub, Symbols.Symbol sup) {
            return Definitions$ValueClassDefinitions$class.isNumericSubClass(this, sub, sup);
        }

        @Override
        public boolean isNumericValueClass(Symbols.Symbol sym) {
            return Definitions$ValueClassDefinitions$class.isNumericValueClass(this, sym);
        }

        @Override
        public boolean isGetClass(Symbols.Symbol sym) {
            return Definitions$ValueClassDefinitions$class.isGetClass(this, sym);
        }

        @Override
        public Symbols.TermSymbol Boolean_and() {
            return Definitions$ValueClassDefinitions$class.Boolean_and(this);
        }

        @Override
        public Symbols.TermSymbol Boolean_or() {
            return Definitions$ValueClassDefinitions$class.Boolean_or(this);
        }

        @Override
        public Symbols.TermSymbol Boolean_not() {
            return Definitions$ValueClassDefinitions$class.Boolean_not(this);
        }

        @Override
        public List<Symbols.ClassSymbol> ScalaPrimitiveValueClasses() {
            return Definitions$ValueClassDefinitions$class.ScalaPrimitiveValueClasses(this);
        }

        @Override
        public Types.Type underlyingOfValueClass(Symbols.Symbol clazz) {
            return Definitions$ValueClassDefinitions$class.underlyingOfValueClass(this, clazz);
        }

        @Override
        public Nothing$ scala$reflect$internal$Definitions$$catastrophicFailure() {
            return Definitions$ValueClassDefinitions$class.scala$reflect$internal$Definitions$$catastrophicFailure(this);
        }

        @Override
        public <T> scala.collection.immutable.Map<Symbols.Symbol, T> scala$reflect$internal$Definitions$$classesMap(Function1<Names.Name, T> f) {
            return Definitions$ValueClassDefinitions$class.scala$reflect$internal$Definitions$$classesMap(this, f);
        }

        private boolean isInitialized() {
            return this.isInitialized;
        }

        private void isInitialized_$eq(boolean x$1) {
            this.isInitialized = x$1;
        }

        public boolean isDefinitionsInitialized() {
            return this.isInitialized();
        }

        @Override
        public Symbols.ModuleSymbol JavaLangPackage() {
            return (this.bitmap$0 & 1L) == 0L ? this.JavaLangPackage$lzycompute() : this.JavaLangPackage;
        }

        @Override
        public Symbols.ClassSymbol JavaLangPackageClass() {
            return (this.bitmap$0 & 2L) == 0L ? this.JavaLangPackageClass$lzycompute() : this.JavaLangPackageClass;
        }

        @Override
        public Symbols.ModuleSymbol ScalaPackage() {
            return (this.bitmap$0 & 4L) == 0L ? this.ScalaPackage$lzycompute() : this.ScalaPackage;
        }

        @Override
        public Symbols.ClassSymbol ScalaPackageClass() {
            return (this.bitmap$0 & 8L) == 0L ? this.ScalaPackageClass$lzycompute() : this.ScalaPackageClass;
        }

        public Symbols.ModuleSymbol RuntimePackage() {
            return (this.bitmap$0 & 0x10L) == 0L ? this.RuntimePackage$lzycompute() : this.RuntimePackage;
        }

        public Symbols.ClassSymbol RuntimePackageClass() {
            return (this.bitmap$0 & 0x20L) == 0L ? this.RuntimePackageClass$lzycompute() : this.RuntimePackageClass;
        }

        public Symbols.Symbol javaTypeToValueClass(Class<?> jtype) {
            Symbols.Symbol symbol;
            Class<Void> clazz = Void.TYPE;
            if (!(clazz != null ? !clazz.equals(jtype) : jtype != null)) {
                symbol = this.UnitClass();
            } else {
                Class<Byte> clazz2 = Byte.TYPE;
                if (!(clazz2 != null ? !clazz2.equals(jtype) : jtype != null)) {
                    symbol = this.ByteClass();
                } else {
                    Class<Character> clazz3 = Character.TYPE;
                    if (!(clazz3 != null ? !clazz3.equals(jtype) : jtype != null)) {
                        symbol = this.CharClass();
                    } else {
                        Class<Short> clazz4 = Short.TYPE;
                        if (!(clazz4 != null ? !clazz4.equals(jtype) : jtype != null)) {
                            symbol = this.ShortClass();
                        } else {
                            Class<Integer> clazz5 = Integer.TYPE;
                            if (!(clazz5 != null ? !clazz5.equals(jtype) : jtype != null)) {
                                symbol = this.IntClass();
                            } else {
                                Class<Long> clazz6 = Long.TYPE;
                                if (!(clazz6 != null ? !clazz6.equals(jtype) : jtype != null)) {
                                    symbol = this.LongClass();
                                } else {
                                    Class<Float> clazz7 = Float.TYPE;
                                    if (!(clazz7 != null ? !clazz7.equals(jtype) : jtype != null)) {
                                        symbol = this.FloatClass();
                                    } else {
                                        Class<Double> clazz8 = Double.TYPE;
                                        if (!(clazz8 != null ? !clazz8.equals(jtype) : jtype != null)) {
                                            symbol = this.DoubleClass();
                                        } else {
                                            Class<Boolean> clazz9 = Boolean.TYPE;
                                            symbol = !(clazz9 != null ? !clazz9.equals(jtype) : jtype != null) ? this.BooleanClass() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return symbol;
        }

        public Class<?> valueClassToJavaType(Symbols.Symbol sym) {
            Class<Object> clazz;
            Symbols.ClassSymbol classSymbol = this.UnitClass();
            if (!(classSymbol != null ? !classSymbol.equals(sym) : sym != null)) {
                clazz = Void.TYPE;
            } else {
                Symbols.ClassSymbol classSymbol2 = this.ByteClass();
                if (!(classSymbol2 != null ? !classSymbol2.equals(sym) : sym != null)) {
                    clazz = Byte.TYPE;
                } else {
                    Symbols.ClassSymbol classSymbol3 = this.CharClass();
                    if (!(classSymbol3 != null ? !classSymbol3.equals(sym) : sym != null)) {
                        clazz = Character.TYPE;
                    } else {
                        Symbols.ClassSymbol classSymbol4 = this.ShortClass();
                        if (!(classSymbol4 != null ? !classSymbol4.equals(sym) : sym != null)) {
                            clazz = Short.TYPE;
                        } else {
                            Symbols.ClassSymbol classSymbol5 = this.IntClass();
                            if (!(classSymbol5 != null ? !classSymbol5.equals(sym) : sym != null)) {
                                clazz = Integer.TYPE;
                            } else {
                                Symbols.ClassSymbol classSymbol6 = this.LongClass();
                                if (!(classSymbol6 != null ? !classSymbol6.equals(sym) : sym != null)) {
                                    clazz = Long.TYPE;
                                } else {
                                    Symbols.ClassSymbol classSymbol7 = this.FloatClass();
                                    if (!(classSymbol7 != null ? !classSymbol7.equals(sym) : sym != null)) {
                                        clazz = Float.TYPE;
                                    } else {
                                        Symbols.ClassSymbol classSymbol8 = this.DoubleClass();
                                        if (!(classSymbol8 != null ? !classSymbol8.equals(sym) : sym != null)) {
                                            clazz = Double.TYPE;
                                        } else {
                                            Symbols.ClassSymbol classSymbol9 = this.BooleanClass();
                                            clazz = !(classSymbol9 != null ? !classSymbol9.equals(sym) : sym != null) ? Boolean.TYPE : null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return clazz;
        }

        public Symbols.Symbol fullyInitializeSymbol(Symbols.Symbol sym) {
            sym.initialize();
            Object object = sym.owner().initialize().isJavaDefined() ? sym.cookJavaRawInfo() : BoxedUnit.UNIT;
            this.fullyInitializeType(sym.info());
            this.fullyInitializeType(sym.tpe_$times());
            return sym;
        }

        public Types.Type fullyInitializeType(Types.Type tp) {
            List list2 = tp.typeParams();
            while (!((AbstractIterable)list2).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                this.fullyInitializeSymbol(symbol);
                list2 = (List)list2.tail();
            }
            this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().mforeach(tp.paramss(), new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final void apply(Symbols.Symbol sym) {
                    this.$outer.fullyInitializeSymbol(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
            return tp;
        }

        public Scopes.Scope fullyInitializeScope(Scopes.Scope scope) {
            List list2 = scope.sorted();
            while (!((AbstractIterable)list2).isEmpty()) {
                Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                this.fullyInitializeSymbol(symbol);
                list2 = (List)list2.tail();
            }
            return scope;
        }

        public boolean isUniversalMember(Symbols.Symbol sym) {
            return this.ObjectClass().isSubClass(sym.owner());
        }

        public boolean isUnimportable(Symbols.Symbol sym) {
            return sym == this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() || sym.isConstructor() || sym.isPrivateLocal();
        }

        public boolean isUnimportableUnlessRenamed(Symbols.Symbol sym) {
            return this.isUnimportable(sym) || this.isUniversalMember(sym);
        }

        public boolean isImportable(Symbols.Symbol sym) {
            return !this.isUnimportable(sym);
        }

        public boolean isTrivialTopType(Types.Type tp) {
            return tp.$eq$colon$eq(this.AnyTpe()) || tp.$eq$colon$eq(this.AnyValTpe()) || tp.$eq$colon$eq(this.AnyRefTpe());
        }

        public boolean hasMultipleNonImplicitParamLists(Symbols.Symbol member) {
            return this.hasMultipleNonImplicitParamLists(member.info());
        }

        public boolean hasMultipleNonImplicitParamLists(Types.Type info2) {
            boolean bl;
            if (info2 instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)info2;
                bl = this.hasMultipleNonImplicitParamLists(polyType.resultType());
            } else {
                $colon$colon $colon$colon;
                Types.MethodType methodType;
                Types.MethodType methodType2;
                bl = info2 instanceof Types.MethodType && (methodType2 = (Types.MethodType)info2).resultType() instanceof Types.MethodType && (methodType = (Types.MethodType)methodType2.resultType()).params() instanceof $colon$colon && !((HasFlags)($colon$colon = ($colon$colon)methodType.params()).head()).isImplicit();
            }
            return bl;
        }

        public Types.Type scala$reflect$internal$Definitions$DefinitionsClass$$fixupAsAnyTrait(Types.Type tpe) {
            block8: {
                Types.Type type;
                block7: {
                    block6: {
                        Types.Type type2;
                        if (!(tpe instanceof Types.ClassInfoType)) break block6;
                        Types.ClassInfoType classInfoType = (Types.ClassInfoType)tpe;
                        Symbols.Symbol symbol = classInfoType.parents().head().typeSymbol();
                        Symbols.ClassSymbol classSymbol = this.AnyClass();
                        if (!(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null)) {
                            type2 = tpe;
                        } else {
                            Symbols.Symbol symbol2 = classInfoType.parents().head().typeSymbol();
                            Symbols.ClassSymbol classSymbol2 = this.ObjectClass();
                            boolean bl = !(symbol2 != null ? !symbol2.equals(classSymbol2) : classSymbol2 != null);
                            Predef$ predef$ = Predef$.MODULE$;
                            if (!bl) {
                                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(classInfoType.parents()).toString());
                            }
                            Types.Type type3 = this.AnyTpe();
                            List list2 = (List)classInfoType.parents().tail();
                            Types.ClassInfoType classInfoType2 = new Types.ClassInfoType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), new $colon$colon<Types.Type>(type3, list2), classInfoType.decls(), classInfoType.typeSymbol());
                            type2 = classInfoType2;
                        }
                        type = type2;
                        break block7;
                    }
                    if (!(tpe instanceof Types.PolyType)) break block8;
                    Types.PolyType polyType = (Types.PolyType)tpe;
                    type = new Types.PolyType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), polyType.typeParams(), this.scala$reflect$internal$Definitions$DefinitionsClass$$fixupAsAnyTrait(polyType.resultType()));
                }
                return type;
            }
            throw new MatchError(tpe);
        }

        @Override
        public Symbols.ClassSymbol AnyClass() {
            return (this.bitmap$0 & 0x40L) == 0L ? this.AnyClass$lzycompute() : this.AnyClass;
        }

        @Override
        public Symbols.AliasTypeSymbol AnyRefClass() {
            return (this.bitmap$0 & 0x80L) == 0L ? this.AnyRefClass$lzycompute() : this.AnyRefClass;
        }

        @Override
        public Symbols.ClassSymbol ObjectClass() {
            return (this.bitmap$0 & 0x100L) == 0L ? this.ObjectClass$lzycompute() : this.ObjectClass;
        }

        @Override
        public Types.Type AnyRefTpe() {
            return (this.bitmap$0 & 0x200L) == 0L ? this.AnyRefTpe$lzycompute() : this.AnyRefTpe;
        }

        @Override
        public Types.Type AnyTpe() {
            return (this.bitmap$0 & 0x400L) == 0L ? this.AnyTpe$lzycompute() : this.AnyTpe;
        }

        @Override
        public Types.Type AnyValTpe() {
            return (this.bitmap$0 & 0x800L) == 0L ? this.AnyValTpe$lzycompute() : this.AnyValTpe;
        }

        public Types.Type BoxedUnitTpe() {
            return (this.bitmap$0 & 0x1000L) == 0L ? this.BoxedUnitTpe$lzycompute() : this.BoxedUnitTpe;
        }

        @Override
        public Types.Type NothingTpe() {
            return (this.bitmap$0 & 0x2000L) == 0L ? this.NothingTpe$lzycompute() : this.NothingTpe;
        }

        @Override
        public Types.Type NullTpe() {
            return (this.bitmap$0 & 0x4000L) == 0L ? this.NullTpe$lzycompute() : this.NullTpe;
        }

        @Override
        public Types.Type ObjectTpe() {
            return (this.bitmap$0 & 0x8000L) == 0L ? this.ObjectTpe$lzycompute() : this.ObjectTpe;
        }

        public Types.Type SerializableTpe() {
            return (this.bitmap$0 & 0x10000L) == 0L ? this.SerializableTpe$lzycompute() : this.SerializableTpe;
        }

        public Types.Type StringTpe() {
            return (this.bitmap$0 & 0x20000L) == 0L ? this.StringTpe$lzycompute() : this.StringTpe;
        }

        public Types.Type ThrowableTpe() {
            return (this.bitmap$0 & 0x40000L) == 0L ? this.ThrowableTpe$lzycompute() : this.ThrowableTpe;
        }

        public Types.UniqueConstantType ConstantTrue() {
            return (this.bitmap$0 & 0x80000L) == 0L ? this.ConstantTrue$lzycompute() : this.ConstantTrue;
        }

        public Types.UniqueConstantType ConstantFalse() {
            return (this.bitmap$0 & 0x100000L) == 0L ? this.ConstantFalse$lzycompute() : this.ConstantFalse;
        }

        public Types.UniqueConstantType ConstantNull() {
            return (this.bitmap$0 & 0x200000L) == 0L ? this.ConstantNull$lzycompute() : this.ConstantNull;
        }

        @Override
        public Symbols.ClassSymbol AnyValClass() {
            return (this.bitmap$0 & 0x400000L) == 0L ? this.AnyValClass$lzycompute() : this.AnyValClass;
        }

        public Symbols.TermSymbol AnyVal_getClass() {
            return this.getMemberMethod(this.AnyValClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getClass_());
        }

        public Symbols.ClassSymbol RuntimeNothingClass() {
            return (this.bitmap$0 & 0x800000L) == 0L ? this.RuntimeNothingClass$lzycompute() : this.RuntimeNothingClass;
        }

        public Symbols.ClassSymbol RuntimeNullClass() {
            return (this.bitmap$0 & 0x1000000L) == 0L ? this.RuntimeNullClass$lzycompute() : this.RuntimeNullClass;
        }

        @Override
        public final Definitions$DefinitionsClass$NothingClass$ NothingClass() {
            return this.NothingClass$module == null ? this.NothingClass$lzycompute() : this.NothingClass$module;
        }

        @Override
        public final Definitions$DefinitionsClass$NullClass$ NullClass() {
            return this.NullClass$module == null ? this.NullClass$lzycompute() : this.NullClass$module;
        }

        public Symbols.ClassSymbol ClassCastExceptionClass() {
            return (this.bitmap$0 & 0x2000000L) == 0L ? this.ClassCastExceptionClass$lzycompute() : this.ClassCastExceptionClass;
        }

        public Symbols.ClassSymbol IndexOutOfBoundsExceptionClass() {
            return (this.bitmap$0 & 0x4000000L) == 0L ? this.IndexOutOfBoundsExceptionClass$lzycompute() : this.IndexOutOfBoundsExceptionClass;
        }

        public Symbols.ClassSymbol InvocationTargetExceptionClass() {
            return (this.bitmap$0 & 0x8000000L) == 0L ? this.InvocationTargetExceptionClass$lzycompute() : this.InvocationTargetExceptionClass;
        }

        public Symbols.ClassSymbol MatchErrorClass() {
            return (this.bitmap$0 & 0x10000000L) == 0L ? this.MatchErrorClass$lzycompute() : this.MatchErrorClass;
        }

        public Symbols.ClassSymbol NonLocalReturnControlClass() {
            return (this.bitmap$0 & 0x20000000L) == 0L ? this.NonLocalReturnControlClass$lzycompute() : this.NonLocalReturnControlClass;
        }

        public Symbols.ClassSymbol NullPointerExceptionClass() {
            return (this.bitmap$0 & 0x40000000L) == 0L ? this.NullPointerExceptionClass$lzycompute() : this.NullPointerExceptionClass;
        }

        public Symbols.ClassSymbol ThrowableClass() {
            return (this.bitmap$0 & 0x80000000L) == 0L ? this.ThrowableClass$lzycompute() : this.ThrowableClass;
        }

        public Symbols.ClassSymbol UninitializedErrorClass() {
            return (this.bitmap$0 & 0x100000000L) == 0L ? this.UninitializedErrorClass$lzycompute() : this.UninitializedErrorClass;
        }

        public Symbols.Symbol UninitializedFieldConstructor() {
            return (this.bitmap$0 & 0x200000000L) == 0L ? this.UninitializedFieldConstructor$lzycompute() : this.UninitializedFieldConstructor;
        }

        public Symbols.ClassSymbol PartialFunctionClass() {
            return (this.bitmap$0 & 0x400000000L) == 0L ? this.PartialFunctionClass$lzycompute() : this.PartialFunctionClass;
        }

        public Symbols.ClassSymbol AbstractPartialFunctionClass() {
            return (this.bitmap$0 & 0x800000000L) == 0L ? this.AbstractPartialFunctionClass$lzycompute() : this.AbstractPartialFunctionClass;
        }

        public Symbols.ClassSymbol SymbolClass() {
            return (this.bitmap$0 & 0x1000000000L) == 0L ? this.SymbolClass$lzycompute() : this.SymbolClass;
        }

        @Override
        public Symbols.ClassSymbol StringClass() {
            return (this.bitmap$0 & 0x2000000000L) == 0L ? this.StringClass$lzycompute() : this.StringClass;
        }

        public Symbols.Symbol StringModule() {
            return (this.bitmap$0 & 0x4000000000L) == 0L ? this.StringModule$lzycompute() : this.StringModule;
        }

        @Override
        public Symbols.ClassSymbol ClassClass() {
            return (this.bitmap$0 & 0x8000000000L) == 0L ? this.ClassClass$lzycompute() : this.ClassClass;
        }

        public Symbols.TermSymbol Class_getMethod() {
            return this.getMemberMethod(this.ClassClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getMethod_());
        }

        public Symbols.ClassSymbol DynamicClass() {
            return (this.bitmap$0 & 0x10000000000L) == 0L ? this.DynamicClass$lzycompute() : this.DynamicClass;
        }

        public Symbols.ModuleSymbol SysPackage() {
            return (this.bitmap$0 & 0x20000000000L) == 0L ? this.SysPackage$lzycompute() : this.SysPackage;
        }

        public Symbols.TermSymbol Sys_error() {
            return this.getMemberMethod(this.SysPackage(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().error());
        }

        public List<Symbols.ModuleSymbol> UnqualifiedModules() {
            return (this.bitmap$0 & 0x40000000000L) == 0L ? this.UnqualifiedModules$lzycompute() : this.UnqualifiedModules;
        }

        public Set<Symbols.Symbol> UnqualifiedOwners() {
            return (this.bitmap$0 & 0x80000000000L) == 0L ? this.UnqualifiedOwners$lzycompute() : this.UnqualifiedOwners;
        }

        @Override
        public Symbols.ModuleSymbol PredefModule() {
            return (this.bitmap$0 & 0x100000000000L) == 0L ? this.PredefModule$lzycompute() : this.PredefModule;
        }

        public Symbols.TermSymbol Predef_wrapArray(Types.Type tp) {
            return this.getMemberMethod(this.PredefModule(), this.wrapArrayMethodName(tp));
        }

        public Symbols.TermSymbol Predef_$qmark$qmark$qmark() {
            return this.getMemberMethod(this.PredefModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().$qmark$qmark$qmark());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isPredefMemberNamed(Symbols.Symbol sym, Names.Name name) {
            Names.Name name2 = sym.name();
            if (name2 == null) {
                if (name != null) {
                    return false;
                }
            } else if (!name2.equals(name)) return false;
            Symbols.Symbol symbol = sym.owner();
            Symbols.Symbol symbol2 = this.PredefModule().moduleClass();
            if (symbol == null) {
                if (symbol2 == null) return true;
                return false;
            } else {
                if (!symbol.equals(symbol2)) return false;
                return true;
            }
        }

        public Symbols.ModuleSymbol SpecializableModule() {
            return (this.bitmap$0 & 0x200000000000L) == 0L ? this.SpecializableModule$lzycompute() : this.SpecializableModule;
        }

        public Symbols.ModuleSymbol ScalaRunTimeModule() {
            return (this.bitmap$0 & 0x400000000000L) == 0L ? this.ScalaRunTimeModule$lzycompute() : this.ScalaRunTimeModule;
        }

        public Symbols.ModuleSymbol SymbolModule() {
            return (this.bitmap$0 & 0x800000000000L) == 0L ? this.SymbolModule$lzycompute() : this.SymbolModule;
        }

        public Symbols.TermSymbol Symbol_apply() {
            return this.getMemberMethod(this.SymbolModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
        }

        public Symbols.ClassSymbol StringAddClass() {
            return (this.bitmap$0 & 0x1000000000000L) == 0L ? this.StringAddClass$lzycompute() : this.StringAddClass;
        }

        public Symbols.ClassSymbol ScalaNumberClass() {
            return (this.bitmap$0 & 0x2000000000000L) == 0L ? this.ScalaNumberClass$lzycompute() : this.ScalaNumberClass;
        }

        public Symbols.ClassSymbol TraitSetterAnnotationClass() {
            return (this.bitmap$0 & 0x4000000000000L) == 0L ? this.TraitSetterAnnotationClass$lzycompute() : this.TraitSetterAnnotationClass;
        }

        public Symbols.ClassSymbol DelayedInitClass() {
            return (this.bitmap$0 & 0x8000000000000L) == 0L ? this.DelayedInitClass$lzycompute() : this.DelayedInitClass;
        }

        public Symbols.TermSymbol delayedInitMethod() {
            return this.getMemberMethod(this.DelayedInitClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().delayedInit());
        }

        public Symbols.ClassSymbol TypeConstraintClass() {
            return (this.bitmap$0 & 0x10000000000000L) == 0L ? this.TypeConstraintClass$lzycompute() : this.TypeConstraintClass;
        }

        public Symbols.ClassSymbol SingletonClass() {
            return (this.bitmap$0 & 0x20000000000000L) == 0L ? this.SingletonClass$lzycompute() : this.SingletonClass;
        }

        public Symbols.ClassSymbol SerializableClass() {
            return (this.bitmap$0 & 0x40000000000000L) == 0L ? this.SerializableClass$lzycompute() : this.SerializableClass;
        }

        public Symbols.ClassSymbol JavaSerializableClass() {
            return (this.bitmap$0 & 0x80000000000000L) == 0L ? this.JavaSerializableClass$lzycompute() : this.JavaSerializableClass;
        }

        public Symbols.ClassSymbol ComparableClass() {
            return (this.bitmap$0 & 0x100000000000000L) == 0L ? this.ComparableClass$lzycompute() : this.ComparableClass;
        }

        public Symbols.ClassSymbol JavaCloneableClass() {
            return (this.bitmap$0 & 0x200000000000000L) == 0L ? this.JavaCloneableClass$lzycompute() : this.JavaCloneableClass;
        }

        public Symbols.ClassSymbol JavaNumberClass() {
            return (this.bitmap$0 & 0x400000000000000L) == 0L ? this.JavaNumberClass$lzycompute() : this.JavaNumberClass;
        }

        public Symbols.ClassSymbol JavaEnumClass() {
            return (this.bitmap$0 & 0x800000000000000L) == 0L ? this.JavaEnumClass$lzycompute() : this.JavaEnumClass;
        }

        public Symbols.ClassSymbol RemoteInterfaceClass() {
            return (this.bitmap$0 & 0x1000000000000000L) == 0L ? this.RemoteInterfaceClass$lzycompute() : this.RemoteInterfaceClass;
        }

        public Symbols.ClassSymbol RemoteExceptionClass() {
            return (this.bitmap$0 & 0x2000000000000000L) == 0L ? this.RemoteExceptionClass$lzycompute() : this.RemoteExceptionClass;
        }

        public Symbols.ClassSymbol JavaUtilMap() {
            return (this.bitmap$0 & 0x4000000000000000L) == 0L ? this.JavaUtilMap$lzycompute() : this.JavaUtilMap;
        }

        public Symbols.ClassSymbol JavaUtilHashMap() {
            return (this.bitmap$0 & Long.MIN_VALUE) == 0L ? this.JavaUtilHashMap$lzycompute() : this.JavaUtilHashMap;
        }

        @Override
        public Symbols.ClassSymbol ByNameParamClass() {
            return (this.bitmap$1 & 1L) == 0L ? this.ByNameParamClass$lzycompute() : this.ByNameParamClass;
        }

        @Override
        public Symbols.ClassSymbol JavaRepeatedParamClass() {
            return (this.bitmap$1 & 2L) == 0L ? this.JavaRepeatedParamClass$lzycompute() : this.JavaRepeatedParamClass;
        }

        @Override
        public Symbols.ClassSymbol RepeatedParamClass() {
            return (this.bitmap$1 & 4L) == 0L ? this.RepeatedParamClass$lzycompute() : this.RepeatedParamClass;
        }

        public boolean isByNameParamType(Types.Type tp) {
            Symbols.Symbol symbol = tp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.ByNameParamClass();
            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
        }

        public boolean isScalaRepeatedParamType(Types.Type tp) {
            Symbols.Symbol symbol = tp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.RepeatedParamClass();
            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
        }

        public boolean isJavaRepeatedParamType(Types.Type tp) {
            Symbols.Symbol symbol = tp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.JavaRepeatedParamClass();
            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
        }

        public boolean isRepeatedParamType(Types.Type tp) {
            return this.isScalaRepeatedParamType(tp) || this.isJavaRepeatedParamType(tp);
        }

        public boolean isRepeated(Symbols.Symbol param2) {
            return this.isRepeatedParamType(param2.tpe_$times());
        }

        public boolean isByName(Symbols.Symbol param2) {
            return this.isByNameParamType(param2.tpe_$times());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isCastSymbol(Symbols.Symbol sym) {
            Symbols.Symbol symbol = sym;
            Symbols.MethodSymbol methodSymbol = this.Any_asInstanceOf();
            if (symbol == null) {
                if (methodSymbol == null) return true;
            } else if (symbol.equals(methodSymbol)) return true;
            Symbols.Symbol symbol2 = sym;
            Symbols.MethodSymbol methodSymbol2 = this.Object_asInstanceOf();
            if (symbol2 != null) {
                if (!symbol2.equals(methodSymbol2)) return false;
                return true;
            }
            if (methodSymbol2 == null) return true;
            return false;
        }

        public boolean isJavaVarArgsMethod(Symbols.Symbol m) {
            return m.isMethod() && this.isJavaVarArgs(m.info().params());
        }

        public boolean isJavaVarArgs(Seq<Symbols.Symbol> params2) {
            return params2.nonEmpty() && this.isJavaRepeatedParamType(((Symbols.Symbol)params2.last()).tpe());
        }

        public boolean isScalaVarArgs(Seq<Symbols.Symbol> params2) {
            return params2.nonEmpty() && this.isScalaRepeatedParamType(((Symbols.Symbol)params2.last()).tpe());
        }

        public boolean isVarArgsList(Seq<Symbols.Symbol> params2) {
            return params2.nonEmpty() && this.isRepeatedParamType(((Symbols.Symbol)params2.last()).tpe());
        }

        public boolean isVarArgTypes(Seq<Types.Type> formals) {
            return formals.nonEmpty() && this.isRepeatedParamType((Types.Type)formals.last());
        }

        public Types.Type firstParamType(Types.Type tpe) {
            Types.Type type;
            List<Types.Type> list2 = tpe.paramTypes();
            if (list2 instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)list2;
                type = (Types.Type)$colon$colon.head();
            } else {
                type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            }
            return type;
        }

        public boolean isImplicitParamss(List<List<Symbols.Symbol>> paramss) {
            boolean bl;
            $colon$colon $colon$colon;
            if (paramss instanceof $colon$colon && ($colon$colon = ($colon$colon)paramss).head() instanceof $colon$colon) {
                $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.head();
                bl = ((HasFlags)$colon$colon2.head()).isImplicit();
            } else {
                bl = false;
            }
            return bl;
        }

        public boolean hasRepeatedParam(Types.Type tp) {
            boolean bl;
            if (tp instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tp;
                bl = this.isScalaVarArgs(methodType.params()) || this.hasRepeatedParam(methodType.resultType());
            } else if (tp instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)tp;
                bl = this.hasRepeatedParam(polyType.resultType());
            } else {
                bl = false;
            }
            return bl;
        }

        public Types.Type dropByName(Types.Type tp) {
            Types.Type type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(this.ByNameParamClass(), tp);
            return type != type.scala$reflect$internal$Types$Type$$$outer().NoType() ? type : tp;
        }

        public Types.Type dropRepeated(Types.Type tp) {
            Types.Type type;
            Types.Type type2;
            Types.Type type3 = this.isJavaRepeatedParamType(tp) ? ((type2 = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(this.JavaRepeatedParamClass(), tp)) != type2.scala$reflect$internal$Types$Type$$$outer().NoType() ? type2 : tp) : (this.isScalaRepeatedParamType(tp) ? ((type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(this.RepeatedParamClass(), tp)) != type.scala$reflect$internal$Types$Type$$$outer().NoType() ? type : tp) : tp);
            return type3;
        }

        public Types.Type repeatedToSingle(Types.Type tp) {
            Types.Type type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(this.RepeatedParamClass(), tp);
            Types.Type type2 = type != type.scala$reflect$internal$Types$Type$$$outer().NoType() ? type : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(this.JavaRepeatedParamClass(), tp);
            return type2 != type2.scala$reflect$internal$Types$Type$$$outer().NoType() ? type2 : tp;
        }

        public Types.Type repeatedToSeq(Types.Type tp) {
            Types.Type type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementTransform(this.RepeatedParamClass(), tp, (Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final Types.Type apply(Types.Type arg) {
                    return this.$outer.seqType(arg);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            return type != type.scala$reflect$internal$Types$Type$$$outer().NoType() ? type : tp;
        }

        public Types.Type seqToRepeated(Types.Type tp) {
            Types.Type type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementTransform(this.SeqClass(), tp, (Function1<Types.Type, Types.Type>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final Types.Type apply(Types.Type arg) {
                    return this.$outer.scalaRepeatedType(arg);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            return type != type.scala$reflect$internal$Types$Type$$$outer().NoType() ? type : tp;
        }

        public boolean isReferenceArray(Types.Type tp) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementTest(this.ArrayClass(), tp, (Function1<Types.Type, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Types.Type x$7) {
                    return x$7.$less$colon$less(this.$outer.AnyRefTpe());
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public boolean isArrayOfSymbol(Types.Type tp, Symbols.Symbol elem) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementTest(this.ArrayClass(), tp, (Function1<Types.Type, Object>)((Object)new Serializable(this, elem){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol elem$1;

                public final boolean apply(Types.Type x$8) {
                    Symbols.Symbol symbol = x$8.typeSymbol();
                    Symbols.Symbol symbol2 = this.elem$1;
                    return !(symbol != null ? !symbol.equals(symbol2) : symbol2 != null);
                }
                {
                    this.elem$1 = elem$1;
                }
            }));
        }

        public Types.Type elementType(Symbols.Symbol container, Types.Type tp) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().elementExtract(container, tp);
        }

        public Symbols.ClassSymbol ConsClass() {
            return (this.bitmap$1 & 8L) == 0L ? this.ConsClass$lzycompute() : this.ConsClass;
        }

        public Symbols.ClassSymbol IteratorClass() {
            return (this.bitmap$1 & 0x10L) == 0L ? this.IteratorClass$lzycompute() : this.IteratorClass;
        }

        public Symbols.ClassSymbol IterableClass() {
            return (this.bitmap$1 & 0x20L) == 0L ? this.IterableClass$lzycompute() : this.IterableClass;
        }

        @Override
        public Symbols.ClassSymbol ListClass() {
            return (this.bitmap$1 & 0x40L) == 0L ? this.ListClass$lzycompute() : this.ListClass;
        }

        public Symbols.ClassSymbol SeqClass() {
            return (this.bitmap$1 & 0x80L) == 0L ? this.SeqClass$lzycompute() : this.SeqClass;
        }

        public Symbols.ClassSymbol StringBuilderClass() {
            return (this.bitmap$1 & 0x100L) == 0L ? this.StringBuilderClass$lzycompute() : this.StringBuilderClass;
        }

        public Symbols.ClassSymbol TraversableClass() {
            return (this.bitmap$1 & 0x200L) == 0L ? this.TraversableClass$lzycompute() : this.TraversableClass;
        }

        @Override
        public Symbols.ModuleSymbol ListModule() {
            return (this.bitmap$1 & 0x400L) == 0L ? this.ListModule$lzycompute() : this.ListModule;
        }

        @Override
        public Symbols.TermSymbol List_apply() {
            return this.getMemberMethod(this.ListModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
        }

        @Override
        public Symbols.ModuleSymbol NilModule() {
            return (this.bitmap$1 & 0x800L) == 0L ? this.NilModule$lzycompute() : this.NilModule;
        }

        public Symbols.ModuleSymbol SeqModule() {
            return (this.bitmap$1 & 0x1000L) == 0L ? this.SeqModule$lzycompute() : this.SeqModule;
        }

        @Override
        public Symbols.ModuleSymbol ArrayModule() {
            return (this.bitmap$1 & 0x2000L) == 0L ? this.ArrayModule$lzycompute() : this.ArrayModule;
        }

        @Override
        public Symbols.TermSymbol ArrayModule_overloadedApply() {
            return (this.bitmap$1 & 0x4000L) == 0L ? this.ArrayModule_overloadedApply$lzycompute() : this.ArrayModule_overloadedApply;
        }

        public Symbols.Symbol ArrayModule_genericApply() {
            return this.ArrayModule_overloadedApply().suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Symbols.Symbol x$9) {
                    Symbols.Symbol symbol = ((Symbols.Symbol)((LinearSeqOptimized)((Object)x$9.paramss().flatten(Predef$.MODULE$.$conforms()))).last()).tpe().typeSymbol();
                    Symbols.ClassSymbol classSymbol = this.$outer.ClassTagClass();
                    return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Symbols.Symbol ArrayModule_apply(Types.Type tp) {
            return this.ArrayModule_overloadedApply().suchThat((Function1)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$6;

                public final boolean apply(Symbols.Symbol x$10) {
                    return x$10.tpe().resultType().$eq$colon$eq(this.$outer.arrayType(this.tp$6));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$6 = tp$6;
                }
            }));
        }

        @Override
        public Symbols.ClassSymbol ArrayClass() {
            return (this.bitmap$1 & 0x8000L) == 0L ? this.ArrayClass$lzycompute() : this.ArrayClass;
        }

        @Override
        public Symbols.TermSymbol Array_apply() {
            return (this.bitmap$1 & 0x10000L) == 0L ? this.Array_apply$lzycompute() : this.Array_apply;
        }

        @Override
        public Symbols.TermSymbol Array_update() {
            return (this.bitmap$1 & 0x20000L) == 0L ? this.Array_update$lzycompute() : this.Array_update;
        }

        @Override
        public Symbols.TermSymbol Array_length() {
            return (this.bitmap$1 & 0x40000L) == 0L ? this.Array_length$lzycompute() : this.Array_length;
        }

        @Override
        public Symbols.TermSymbol Array_clone() {
            return (this.bitmap$1 & 0x80000L) == 0L ? this.Array_clone$lzycompute() : this.Array_clone;
        }

        public Symbols.ClassSymbol SoftReferenceClass() {
            return (this.bitmap$1 & 0x100000L) == 0L ? this.SoftReferenceClass$lzycompute() : this.SoftReferenceClass;
        }

        public Symbols.ClassSymbol MethodClass() {
            return (this.bitmap$1 & 0x200000L) == 0L ? this.MethodClass$lzycompute() : this.MethodClass;
        }

        public Symbols.ClassSymbol EmptyMethodCacheClass() {
            return (this.bitmap$1 & 0x400000L) == 0L ? this.EmptyMethodCacheClass$lzycompute() : this.EmptyMethodCacheClass;
        }

        public Symbols.ClassSymbol MethodCacheClass() {
            return (this.bitmap$1 & 0x800000L) == 0L ? this.MethodCacheClass$lzycompute() : this.MethodCacheClass;
        }

        public Symbols.TermSymbol methodCache_find() {
            return this.getMemberMethod(this.MethodCacheClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().find_());
        }

        public Symbols.TermSymbol methodCache_add() {
            return this.getMemberMethod(this.MethodCacheClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().add_());
        }

        public Symbols.Symbol ScalaXmlTopScope() {
            return (this.bitmap$1 & 0x1000000L) == 0L ? this.ScalaXmlTopScope$lzycompute() : this.ScalaXmlTopScope;
        }

        public Symbols.Symbol ScalaXmlPackage() {
            return (this.bitmap$1 & 0x2000000L) == 0L ? this.ScalaXmlPackage$lzycompute() : this.ScalaXmlPackage;
        }

        public Symbols.ModuleSymbol ReflectPackage() {
            return (this.bitmap$1 & 0x4000000L) == 0L ? this.ReflectPackage$lzycompute() : this.ReflectPackage;
        }

        public Symbols.Symbol ReflectApiPackage() {
            return (this.bitmap$1 & 0x8000000L) == 0L ? this.ReflectApiPackage$lzycompute() : this.ReflectApiPackage;
        }

        public Symbols.Symbol ReflectRuntimePackage() {
            return (this.bitmap$1 & 0x10000000L) == 0L ? this.ReflectRuntimePackage$lzycompute() : this.ReflectRuntimePackage;
        }

        public Symbols.Symbol ReflectRuntimeUniverse() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.ReflectRuntimePackage();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberValue(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().universe());
            }
            return symbol;
        }

        public Symbols.Symbol ReflectRuntimeCurrentMirror() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.ReflectRuntimePackage();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberMethod(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().currentMirror());
            }
            return symbol;
        }

        public Symbols.Symbol UniverseClass() {
            return (this.bitmap$1 & 0x20000000L) == 0L ? this.UniverseClass$lzycompute() : this.UniverseClass;
        }

        public Symbols.TermSymbol UniverseInternal() {
            return this.getMemberValue(this.UniverseClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().internal());
        }

        public Symbols.ModuleSymbol PartialManifestModule() {
            return (this.bitmap$1 & 0x40000000L) == 0L ? this.PartialManifestModule$lzycompute() : this.PartialManifestModule;
        }

        public Symbols.ClassSymbol FullManifestClass() {
            return (this.bitmap$1 & 0x80000000L) == 0L ? this.FullManifestClass$lzycompute() : this.FullManifestClass;
        }

        public Symbols.ModuleSymbol FullManifestModule() {
            return (this.bitmap$1 & 0x100000000L) == 0L ? this.FullManifestModule$lzycompute() : this.FullManifestModule;
        }

        public Symbols.ClassSymbol OptManifestClass() {
            return (this.bitmap$1 & 0x200000000L) == 0L ? this.OptManifestClass$lzycompute() : this.OptManifestClass;
        }

        public Symbols.ModuleSymbol NoManifest() {
            return (this.bitmap$1 & 0x400000000L) == 0L ? this.NoManifest$lzycompute() : this.NoManifest;
        }

        public Symbols.Symbol TreesClass() {
            return (this.bitmap$1 & 0x800000000L) == 0L ? this.TreesClass$lzycompute() : this.TreesClass;
        }

        public Symbols.Symbol ExprsClass() {
            return (this.bitmap$1 & 0x1000000000L) == 0L ? this.ExprsClass$lzycompute() : this.ExprsClass;
        }

        public Symbols.Symbol ExprClass() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.ExprsClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberClass(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Expr());
            }
            return symbol;
        }

        public Symbols.Symbol ExprSplice() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.ExprClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberMethod(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().splice());
            }
            return symbol;
        }

        public Symbols.Symbol ExprValue() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.ExprClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberMethod(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().value());
            }
            return symbol;
        }

        public Symbols.ModuleSymbol ClassTagModule() {
            return (this.bitmap$1 & 0x2000000000L) == 0L ? this.ClassTagModule$lzycompute() : this.ClassTagModule;
        }

        public Symbols.ClassSymbol ClassTagClass() {
            return (this.bitmap$1 & 0x4000000000L) == 0L ? this.ClassTagClass$lzycompute() : this.ClassTagClass;
        }

        public Symbols.Symbol TypeTagsClass() {
            return (this.bitmap$1 & 0x8000000000L) == 0L ? this.TypeTagsClass$lzycompute() : this.TypeTagsClass;
        }

        public Symbols.Symbol ApiUniverseClass() {
            return (this.bitmap$1 & 0x10000000000L) == 0L ? this.ApiUniverseClass$lzycompute() : this.ApiUniverseClass;
        }

        public Symbols.Symbol JavaUniverseClass() {
            return (this.bitmap$1 & 0x20000000000L) == 0L ? this.JavaUniverseClass$lzycompute() : this.JavaUniverseClass;
        }

        public Symbols.Symbol MirrorClass() {
            return (this.bitmap$1 & 0x40000000000L) == 0L ? this.MirrorClass$lzycompute() : this.MirrorClass;
        }

        public Symbols.Symbol TypeCreatorClass() {
            return (this.bitmap$1 & 0x80000000000L) == 0L ? this.TypeCreatorClass$lzycompute() : this.TypeCreatorClass;
        }

        public Symbols.Symbol TreeCreatorClass() {
            return (this.bitmap$1 & 0x100000000000L) == 0L ? this.TreeCreatorClass$lzycompute() : this.TreeCreatorClass;
        }

        public Symbols.Symbol scala$reflect$internal$Definitions$DefinitionsClass$$Context_210() {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().settings().isScala211() ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol() : ((Mirrors.RootsBase)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getClassIfDefined("scala.reflect.macros.Context");
        }

        public Symbols.Symbol BlackboxContextClass() {
            return (this.bitmap$1 & 0x200000000000L) == 0L ? this.BlackboxContextClass$lzycompute() : this.BlackboxContextClass;
        }

        public Symbols.Symbol WhiteboxContextClass() {
            return (this.bitmap$1 & 0x400000000000L) == 0L ? this.WhiteboxContextClass$lzycompute() : this.WhiteboxContextClass;
        }

        public Symbols.Symbol MacroContextPrefix() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberMethod(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().prefix());
            }
            return symbol;
        }

        public Symbols.Symbol MacroContextPrefixType() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getTypeMember(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().PrefixType());
            }
            return symbol;
        }

        public Symbols.Symbol MacroContextUniverse() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getMemberMethod(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().universe());
            }
            return symbol;
        }

        public Symbols.Symbol MacroContextExprClass() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getTypeMember(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Expr());
            }
            return symbol;
        }

        public Symbols.Symbol MacroContextWeakTypeTagClass() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getTypeMember(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().WeakTypeTag());
            }
            return symbol;
        }

        public Symbols.Symbol MacroContextTreeType() {
            Symbols.Symbol symbol;
            Symbols.Symbol symbol2 = this.BlackboxContextClass();
            if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                symbol = symbol2;
            } else {
                Symbols.Symbol symbol3 = symbol2;
                symbol = this.getTypeMember(symbol3, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Tree());
            }
            return symbol;
        }

        public Symbols.ClassSymbol MacroImplAnnotation() {
            return (this.bitmap$1 & 0x800000000000L) == 0L ? this.MacroImplAnnotation$lzycompute() : this.MacroImplAnnotation;
        }

        public Symbols.ClassSymbol StringContextClass() {
            return (this.bitmap$1 & 0x1000000000000L) == 0L ? this.StringContextClass$lzycompute() : this.StringContextClass;
        }

        public Symbols.Symbol QuasiquoteClass() {
            return (this.bitmap$1 & 0x2000000000000L) == 0L ? this.QuasiquoteClass$lzycompute() : this.QuasiquoteClass;
        }

        public Symbols.Symbol QuasiquoteClass_api() {
            return (this.bitmap$1 & 0x4000000000000L) == 0L ? this.QuasiquoteClass_api$lzycompute() : this.QuasiquoteClass_api;
        }

        public Symbols.Symbol QuasiquoteClass_api_apply() {
            return (this.bitmap$1 & 0x8000000000000L) == 0L ? this.QuasiquoteClass_api_apply$lzycompute() : this.QuasiquoteClass_api_apply;
        }

        public Symbols.Symbol QuasiquoteClass_api_unapply() {
            return (this.bitmap$1 & 0x10000000000000L) == 0L ? this.QuasiquoteClass_api_unapply$lzycompute() : this.QuasiquoteClass_api_unapply;
        }

        public Symbols.ClassSymbol ScalaSignatureAnnotation() {
            return (this.bitmap$1 & 0x20000000000000L) == 0L ? this.ScalaSignatureAnnotation$lzycompute() : this.ScalaSignatureAnnotation;
        }

        public Symbols.ClassSymbol ScalaLongSignatureAnnotation() {
            return (this.bitmap$1 & 0x40000000000000L) == 0L ? this.ScalaLongSignatureAnnotation$lzycompute() : this.ScalaLongSignatureAnnotation;
        }

        public Symbols.Symbol LambdaMetaFactory() {
            return (this.bitmap$1 & 0x80000000000000L) == 0L ? this.LambdaMetaFactory$lzycompute() : this.LambdaMetaFactory;
        }

        public Symbols.Symbol MethodHandle() {
            return (this.bitmap$1 & 0x100000000000000L) == 0L ? this.MethodHandle$lzycompute() : this.MethodHandle;
        }

        @Override
        public Symbols.ClassSymbol OptionClass() {
            return (this.bitmap$1 & 0x200000000000000L) == 0L ? this.OptionClass$lzycompute() : this.OptionClass;
        }

        public Symbols.ModuleSymbol OptionModule() {
            return (this.bitmap$1 & 0x400000000000000L) == 0L ? this.OptionModule$lzycompute() : this.OptionModule;
        }

        public Symbols.ClassSymbol SomeClass() {
            return (this.bitmap$1 & 0x800000000000000L) == 0L ? this.SomeClass$lzycompute() : this.SomeClass;
        }

        @Override
        public Symbols.ModuleSymbol NoneModule() {
            return (this.bitmap$1 & 0x1000000000000000L) == 0L ? this.NoneModule$lzycompute() : this.NoneModule;
        }

        @Override
        public Symbols.ModuleSymbol SomeModule() {
            return (this.bitmap$1 & 0x2000000000000000L) == 0L ? this.SomeModule$lzycompute() : this.SomeModule;
        }

        public Types.Type compilerTypeFromTag(TypeTags.WeakTypeTag<?> tt) {
            return (Types.Type)tt.in(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).tpe();
        }

        public Symbols.Symbol compilerSymbolFromTag(TypeTags.WeakTypeTag<?> tt) {
            return ((Types.Type)tt.in(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).tpe()).typeSymbol();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isJavaMainMethod(Symbols.Symbol sym) {
            Types.Type type;
            Names.Name name = sym.name();
            Names.TermName termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().main();
            if (name == null) {
                if (termName != null) {
                    return false;
                }
            } else if (!name.equals(termName)) return false;
            if (!((type = sym.info()) instanceof Types.MethodType)) return false;
            Types.MethodType methodType = (Types.MethodType)type;
            if (!(methodType.params() instanceof $colon$colon)) return false;
            $colon$colon $colon$colon = ($colon$colon)methodType.params();
            if (!((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) return false;
            if (!this.isArrayOfSymbol(((Symbols.Symbol)$colon$colon.head()).tpe(), this.StringClass())) return false;
            Symbols.Symbol symbol = methodType.resultType().typeSymbol();
            Symbols.ClassSymbol classSymbol = this.UnitClass();
            if (symbol != null) {
                if (!symbol.equals(classSymbol)) return false;
                return true;
            }
            if (classSymbol == null) return true;
            return false;
        }

        public boolean hasJavaMainMethod(Symbols.Symbol sym) {
            return sym.tpe().member(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().main()).alternatives().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Symbols.Symbol sym) {
                    return this.$outer.isJavaMainMethod(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
        }

        public Definitions$DefinitionsClass$VarArityClass$ VarArityClass() {
            return this.VarArityClass$module == null ? this.VarArityClass$lzycompute() : this.VarArityClass$module;
        }

        public int MaxTupleArity() {
            return this.MaxTupleArity;
        }

        public int MaxProductArity() {
            return this.MaxProductArity;
        }

        public int MaxFunctionArity() {
            return this.MaxFunctionArity;
        }

        @Override
        public VarArityClass ProductClass() {
            return (this.bitmap$1 & 0x4000000000000000L) == 0L ? this.ProductClass$lzycompute() : this.ProductClass;
        }

        @Override
        public VarArityClass TupleClass() {
            return (this.bitmap$1 & Long.MIN_VALUE) == 0L ? this.TupleClass$lzycompute() : this.TupleClass;
        }

        @Override
        public VarArityClass FunctionClass() {
            return (this.bitmap$2 & 1L) == 0L ? this.FunctionClass$lzycompute() : this.FunctionClass;
        }

        public VarArityClass AbstractFunctionClass() {
            return (this.bitmap$2 & 2L) == 0L ? this.AbstractFunctionClass$lzycompute() : this.AbstractFunctionClass;
        }

        public Types.Type tupleType(List<Types.Type> elems) {
            return this.TupleClass().specificType(elems, Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[0]));
        }

        public Types.Type functionType(List<Types.Type> formals, Types.Type restpe) {
            return this.FunctionClass().specificType(formals, Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{restpe}));
        }

        public Types.Type abstractFunctionType(List<Types.Type> formals, Types.Type restpe) {
            return this.AbstractFunctionClass().specificType(formals, Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{restpe}));
        }

        public Names.TermName wrapArrayMethodName(Types.Type elemtp) {
            Names.TermName termName;
            Symbols.Symbol symbol = elemtp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.ByteClass();
            if (!(classSymbol != null ? !classSymbol.equals(symbol) : symbol != null)) {
                termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapByteArray();
            } else {
                Symbols.ClassSymbol classSymbol2 = this.ShortClass();
                if (!(classSymbol2 != null ? !classSymbol2.equals(symbol) : symbol != null)) {
                    termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapShortArray();
                } else {
                    Symbols.ClassSymbol classSymbol3 = this.CharClass();
                    if (!(classSymbol3 != null ? !classSymbol3.equals(symbol) : symbol != null)) {
                        termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapCharArray();
                    } else {
                        Symbols.ClassSymbol classSymbol4 = this.IntClass();
                        if (!(classSymbol4 != null ? !classSymbol4.equals(symbol) : symbol != null)) {
                            termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapIntArray();
                        } else {
                            Symbols.ClassSymbol classSymbol5 = this.LongClass();
                            if (!(classSymbol5 != null ? !classSymbol5.equals(symbol) : symbol != null)) {
                                termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapLongArray();
                            } else {
                                Symbols.ClassSymbol classSymbol6 = this.FloatClass();
                                if (!(classSymbol6 != null ? !classSymbol6.equals(symbol) : symbol != null)) {
                                    termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapFloatArray();
                                } else {
                                    Symbols.ClassSymbol classSymbol7 = this.DoubleClass();
                                    if (!(classSymbol7 != null ? !classSymbol7.equals(symbol) : symbol != null)) {
                                        termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapDoubleArray();
                                    } else {
                                        Symbols.ClassSymbol classSymbol8 = this.BooleanClass();
                                        if (!(classSymbol8 != null ? !classSymbol8.equals(symbol) : symbol != null)) {
                                            termName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapBooleanArray();
                                        } else {
                                            Symbols.ClassSymbol classSymbol9 = this.UnitClass();
                                            termName = !(classSymbol9 != null ? !classSymbol9.equals(symbol) : symbol != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapUnitArray() : (elemtp.$less$colon$less(this.AnyRefTpe()) && !this.isPhantomClass().apply(elemtp.typeSymbol()) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapRefArray() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().genericWrapArray());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return termName;
        }

        public boolean isTupleSymbol(Symbols.Symbol sym) {
            return this.TupleClass().seq().contains(this.unspecializedSymbol(sym));
        }

        public boolean isFunctionSymbol(Symbols.Symbol sym) {
            return this.FunctionClass().seq().contains(this.unspecializedSymbol(sym));
        }

        public boolean isProductNSymbol(Symbols.Symbol sym) {
            return this.ProductClass().seq().contains(this.unspecializedSymbol(sym));
        }

        /*
         * WARNING - void declaration
         */
        public Symbols.Symbol unspecializedSymbol(Symbols.Symbol sym) {
            Symbols.Symbol symbol;
            if (sym.hasFlag(0x10000000000L)) {
                void var3_3;
                Names.Name genericName = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().unspecializedName(sym.name());
                Symbols.Symbol member = sym.owner().info().decl(genericName.toTypeName());
                symbol = var3_3;
            } else {
                symbol = sym;
            }
            return symbol;
        }

        public List<Types.Type> unspecializedTypeArgs(Types.Type tp) {
            return tp.baseType(this.unspecializedSymbol(tp.typeSymbolDirect())).typeArgs();
        }

        public Definitions$DefinitionsClass$MacroContextType$ MacroContextType() {
            return this.MacroContextType$module == null ? this.MacroContextType$lzycompute() : this.MacroContextType$module;
        }

        public boolean isMacroContextType(Types.Type tp) {
            return this.MacroContextType().unapply(tp).isDefined();
        }

        public boolean isWhiteboxContextType(Types.Type tp) {
            return this.isMacroContextType(tp) && tp.$less$colon$less(this.WhiteboxContextClass().tpe());
        }

        private Types.Type macroBundleParamInfo(Types.Type tp) {
            Types.Type type;
            List list2;
            Some<List> some;
            Symbols.Symbol ctor = tp.erasure().typeSymbol().primaryConstructor();
            List<List<Symbols.Symbol>> list3 = ctor.paramss();
            Some<List<List<Symbols.Symbol>>> some2 = List$.MODULE$.unapplySeq(list3);
            if (!some2.isEmpty() && some2.get() != null && ((LinearSeqOptimized)some2.get()).lengthCompare(1) == 0 && !(some = List$.MODULE$.unapplySeq(list2 = (List)((LinearSeqOptimized)some2.get()).apply(0))).isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0) {
                Symbols.Symbol c = (Symbols.Symbol)((LinearSeqOptimized)some.get()).apply(0);
                Symbols.Symbol sym = c.info().typeSymbol();
                boolean isContextCompatible = sym.isNonBottomSubClass(this.BlackboxContextClass()) || sym.isNonBottomSubClass(this.WhiteboxContextClass());
                type = isContextCompatible ? c.info() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            } else {
                type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            }
            return type;
        }

        public boolean looksLikeMacroBundleType(Types.Type tp) {
            Types.Type type = this.macroBundleParamInfo(tp);
            Types$NoType$ types$NoType$ = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            return type != null ? !type.equals(types$NoType$) : types$NoType$ != null;
        }

        public boolean isMacroBundleType(Types.Type tp) {
            boolean isMonomorphic = tp.typeSymbol().typeParams().isEmpty();
            boolean isContextCompatible = this.isMacroContextType(this.macroBundleParamInfo(tp));
            boolean hasSingleConstructor = !tp.declaration(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().CONSTRUCTOR()).isOverloaded();
            boolean nonAbstract = !tp.erasure().typeSymbol().isAbstractClass();
            return isMonomorphic && isContextCompatible && hasSingleConstructor && nonAbstract;
        }

        public boolean isBlackboxMacroBundleType(Types.Type tp) {
            boolean isBundle = this.isMacroBundleType(tp);
            Option<Types.Type> option = this.MacroContextType().unapply(this.macroBundleParamInfo(tp));
            Types.Type unwrappedContext = !option.isEmpty() ? option.get() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            boolean isBlackbox = unwrappedContext.$eq$colon$eq(this.BlackboxContextClass().tpe_$times());
            return isBundle && isBlackbox;
        }

        public boolean isListType(Types.Type tp) {
            return tp.typeSymbol().isNonBottomSubClass(this.ListClass());
        }

        public boolean isIterableType(Types.Type tp) {
            return tp.typeSymbol().isNonBottomSubClass(this.IterableClass());
        }

        public boolean isFunctionTypeDirect(Types.Type tp) {
            return !tp.isHigherKinded() && this.isFunctionSymbol(tp.typeSymbolDirect());
        }

        public boolean isTupleTypeDirect(Types.Type tp) {
            return !tp.isHigherKinded() && this.isTupleSymbol(tp.typeSymbolDirect());
        }

        public boolean isFunctionType(Types.Type tp) {
            return this.isFunctionTypeDirect(tp.dealiasWiden());
        }

        public boolean isTupleType(Types.Type tp) {
            return this.isTupleTypeDirect(tp.dealiasWiden());
        }

        public List<Types.Type> tupleComponents(Types.Type tp) {
            return tp.dealiasWiden().typeArgs();
        }

        public Symbols.ClassSymbol ProductRootClass() {
            return (this.bitmap$2 & 4L) == 0L ? this.ProductRootClass$lzycompute() : this.ProductRootClass;
        }

        public Symbols.TermSymbol Product_productArity() {
            return this.getMemberMethod(this.ProductRootClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().productArity());
        }

        public Symbols.TermSymbol Product_productElement() {
            return this.getMemberMethod(this.ProductRootClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().productElement());
        }

        public Symbols.TermSymbol Product_iterator() {
            return this.getMemberMethod(this.ProductRootClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().productIterator());
        }

        public Symbols.TermSymbol Product_productPrefix() {
            return this.getMemberMethod(this.ProductRootClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().productPrefix());
        }

        public Symbols.TermSymbol Product_canEqual() {
            return this.getMemberMethod(this.ProductRootClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().canEqual_());
        }

        public Symbols.TermSymbol productProj(Symbols.Symbol z, int j) {
            return this.getMemberValue(z, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().productAccessorName(j));
        }

        public List<Types.Type> getProductArgs(Types.Type tpe) {
            List list2;
            Option<Symbols.Symbol> option = tpe.baseClasses().find((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Symbols.Symbol sym) {
                    return this.$outer.isProductNSymbol(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            if (option instanceof Some) {
                Some some = (Some)option;
                list2 = tpe.baseType((Symbols.Symbol)some.x()).typeArgs();
            } else {
                list2 = Nil$.MODULE$;
            }
            return list2;
        }

        public Types.Type unapplyUnwrap(Types.Type tpe) {
            Types.Type type;
            Types.RefinedType refinedType;
            Types.Type type2 = tpe.finalResultType().dealiasWiden();
            if (type2 instanceof Types.RefinedType && (refinedType = (Types.RefinedType)type2).parents() instanceof $colon$colon) {
                $colon$colon $colon$colon = ($colon$colon)refinedType.parents();
                type = ((Types.Type)$colon$colon.head()).dealiasWiden();
            } else {
                type = type2;
            }
            return type;
        }

        public List<Types.Type> getterMemberTypes(Types.Type tpe, List<Symbols.Symbol> getters) {
            return getters.map(new Serializable(this, tpe){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tpe$1;

                public final Types.Type apply(Symbols.Symbol m) {
                    return this.$outer.dropNullaryMethod(this.tpe$1.memberType(m));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tpe$1 = tpe$1;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Types.Type dropNullaryMethod(Types.Type tp) {
            Types.Type type;
            if (tp instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                type = nullaryMethodType.resultType();
            } else {
                type = tp;
            }
            return type;
        }

        public Types.Type finalResultType(Types.Type tp) {
            Types.Type type;
            if (tp instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)tp;
                type = this.finalResultType(polyType.resultType());
            } else if (tp instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tp;
                type = this.finalResultType(methodType.resultType());
            } else if (tp instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                type = this.finalResultType(nullaryMethodType.resultType());
            } else {
                type = tp;
            }
            return type;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isStable(Types.Type tp) {
            boolean bl = false;
            Types.TypeRef typeRef = null;
            if (tp instanceof Types.SingletonType) {
                return true;
            }
            if (this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPrefix().equals(tp)) {
                return true;
            }
            if (tp instanceof Types.TypeRef) {
                bl = true;
                typeRef = (Types.TypeRef)tp;
                if (this.NothingClass().equals(typeRef.sym())) {
                    return true;
                }
                Symbols.ClassSymbol classSymbol = this.SingletonClass();
                Symbols.Symbol symbol = typeRef.sym();
                if (classSymbol == null) {
                    if (symbol == null) return true;
                } else if (classSymbol.equals(symbol)) {
                    return true;
                }
                boolean bl2 = false;
                if (bl2) {
                    return true;
                }
            }
            if (bl && typeRef.sym().isAbstractType()) {
                return tp.bounds().hi().typeSymbol().isSubClass(this.SingletonClass());
            }
            if (bl && typeRef.sym().isModuleClass()) {
                return this.isStable(typeRef.pre());
            }
            if (bl && tp != tp.dealias()) {
                return this.isStable(tp.dealias());
            }
            if (tp instanceof Types.TypeVar) {
                Types.TypeVar typeVar = (Types.TypeVar)tp;
                return this.isStable(typeVar.origin());
            }
            if (tp instanceof Types.AnnotatedType) {
                Types.AnnotatedType annotatedType = (Types.AnnotatedType)tp;
                return this.isStable(annotatedType.underlying());
            }
            if (!(tp instanceof Types.SimpleTypeProxy)) return false;
            return this.isStable(tp.underlying());
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean isVolatile(Types.Type tp) {
            boolean bl = false;
            Types.TypeRef typeRef = null;
            if (tp instanceof Types.ThisType) {
                return false;
            }
            if (tp instanceof Types.SingleType) {
                Types.SingleType singleType = (Types.SingleType)tp;
                if (!this.isVolatile(tp.underlying())) return false;
                if (singleType.sym().hasVolatileType()) return true;
                if (singleType.sym().isStable()) return false;
                return true;
            }
            if (tp instanceof Types.NullaryMethodType) {
                Types.NullaryMethodType nullaryMethodType = (Types.NullaryMethodType)tp;
                return this.isVolatile(nullaryMethodType.resultType());
            }
            if (tp instanceof Types.PolyType) {
                Types.PolyType polyType = (Types.PolyType)tp;
                return this.isVolatile(polyType.resultType());
            }
            if (tp instanceof Types.TypeRef) {
                bl = true;
                typeRef = (Types.TypeRef)tp;
                if (tp != tp.dealias()) {
                    return this.isVolatile(tp.dealias());
                }
            }
            if (bl && typeRef.sym().isAbstractType()) {
                return this.isVolatileAbstractType$1(tp);
            }
            if (tp instanceof Types.RefinedType) {
                return this.isVolatileRefinedType$1(tp);
            }
            if (tp instanceof Types.TypeVar) {
                Types.TypeVar typeVar = (Types.TypeVar)tp;
                return this.isVolatile(typeVar.origin());
            }
            if (!(tp instanceof Types.SimpleTypeProxy)) return false;
            return this.isVolatile(tp.underlying());
        }

        public Types.Type abstractFunctionForFunctionType(Types.Type tp) {
            boolean bl = this.isFunctionType(tp);
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(tp).toString());
            }
            return this.abstractFunctionType((List)tp.typeArgs().init(), tp.typeArgs().last());
        }

        public Types.Type functionNBaseType(Types.Type tp) {
            Types.Type type;
            Option<Symbols.Symbol> option = tp.baseClasses().find((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Symbols.Symbol sym) {
                    return this.$outer.isFunctionSymbol(sym);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }));
            if (option instanceof Some) {
                Some some = (Some)option;
                type = tp.baseType(this.unspecializedSymbol((Symbols.Symbol)some.x()));
            } else {
                type = tp;
            }
            return type;
        }

        public boolean isPartialFunctionType(Types.Type tp) {
            Symbols.Symbol sym = tp.typeSymbol();
            return sym == this.PartialFunctionClass() || sym == this.AbstractPartialFunctionClass();
        }

        public Symbols.Symbol samOf(Types.Type tp) {
            Symbols.Symbol symbol;
            MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().settings().Xexperimental();
            MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
            if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                Object deferredMembers;
                boolean ctorOk;
                Symbols.Symbol tpSym = tp.typeSymbol();
                Symbols.Symbol ctor = tpSym.primaryConstructor();
                boolean bl = ctorOk = !ctor.exists() || !ctor.isOverloaded() && ctor.isPublic() && ctor.info().params().isEmpty() && ctor.info().paramSectionCount() <= 1;
                symbol = tpSym.exists() && ctorOk ? (((Scopes.Scope)(deferredMembers = tp.membersBasedOnFlags(0x40004000004L, 64L).filter((Function1)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ DefinitionsClass $outer;

                    public final boolean apply(Symbols.Symbol mem) {
                        return mem.isDeferredNotJavaDefault() && !this.$outer.isUniversalMember(mem);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                })))).size() == 1 && ((Symbols.Symbol)((Scopes.Scope)deferredMembers).head()).typeParams().isEmpty() && ((Symbols.Symbol)((Scopes.Scope)deferredMembers).head()).info().paramSectionCount() == 1 ? (Symbols.Symbol)((Scopes.Scope)deferredMembers).head() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol()) : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            } else {
                symbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            }
            return symbol;
        }

        public Types.Type arrayType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.ArrayClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public Types.Type byNameType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.ByNameParamClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public Types.Type iteratorOfType(Types.Type tp) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.IteratorClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{tp}));
        }

        public Types.Type javaRepeatedType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.JavaRepeatedParamClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public Types.Type optionType(Types.Type tp) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.OptionClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{tp}));
        }

        public Types.Type scalaRepeatedType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.RepeatedParamClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public Types.Type seqType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.SeqClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public Types.Type typeOfMemberNamedGet(Types.Type tp) {
            return this.typeArgOfBaseTypeOr(tp, this.OptionClass(), (Function0<Types.Type>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$9;

                public final Types.Type apply() {
                    return this.$outer.resultOfMatchingMethod(this.tp$9, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().get(), Nil$.MODULE$);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$9 = tp$9;
                }
            }));
        }

        public Types.Type typeOfMemberNamedHead(Types.Type tp) {
            return this.typeArgOfBaseTypeOr(tp, this.SeqClass(), (Function0<Types.Type>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$10;

                public final Types.Type apply() {
                    return this.$outer.resultOfMatchingMethod(this.tp$10, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().head(), Nil$.MODULE$);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$10 = tp$10;
                }
            }));
        }

        public Types.Type typeOfMemberNamedApply(Types.Type tp) {
            return this.typeArgOfBaseTypeOr(tp, this.SeqClass(), (Function0<Types.Type>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$11;

                public final Types.Type apply() {
                    return this.$outer.resultOfMatchingMethod(this.tp$11, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.$outer.IntTpe()}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$11 = tp$11;
                }
            }));
        }

        public Types.Type typeOfMemberNamedDrop(Types.Type tp) {
            return this.typeArgOfBaseTypeOr(tp, this.SeqClass(), (Function0<Types.Type>)((Object)new Serializable(this, tp){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$12;

                public final Types.Type apply() {
                    return this.$outer.resultOfMatchingMethod(this.tp$12, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().drop(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.$outer.IntTpe()}));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$12 = tp$12;
                }
            }));
        }

        public List<Types.Type> typesOfSelectors(Types.Type tp) {
            return this.isTupleType(tp) ? this.tupleComponents(tp) : this.getterMemberTypes(tp, this.productSelectors(tp));
        }

        private Types.Type typeArgOfBaseTypeOr(Types.Type tp, Symbols.Symbol baseClass, Function0<Types.Type> or) {
            Types.Type type;
            $colon$colon $colon$colon;
            List<Types.Type> list2 = tp.baseType(baseClass).typeArgs();
            if (list2 instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)list2).tl$1())) {
                Types.Type x2;
                Types.Type x1 = (Types.Type)$colon$colon.head();
                type = x2 = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().repackExistential(x1);
            } else {
                type = or.apply();
            }
            return type;
        }

        public boolean hasSelectors(Types.Type tp) {
            return tp.members().containsName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme()._1()) && tp.members().containsName(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme()._2());
        }

        public List<Symbols.Symbol> productSelectors(Types.Type tpe) {
            return tpe.isErroneous() ? Nil$.MODULE$ : this.loop$1(1, tpe);
        }

        public Types.Type resultOfMatchingMethod(Types.Type tp, Names.TermName name, Seq<Types.Type> paramTypes2) {
            Symbols.SymbolApi symbolApi = tp.member(name).filter((Function1)((Object)new Serializable(this, paramTypes2){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Seq paramTypes$1;

                public final boolean apply(Symbols.Symbol member) {
                    return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$matchesParams$1(member, this.paramTypes$1);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.paramTypes$1 = paramTypes$1;
                }
            }));
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            Types.Type type = !(noSymbol != null ? !noSymbol.equals(symbolApi) : symbolApi != null) ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType() : tp.memberType((Symbols.Symbol)symbolApi).finalResultType();
            return type;
        }

        public Types.Type ClassType(Types.Type arg) {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().phase().erasedTypes() ? this.ClassClass().tpe() : this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.ClassClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{arg}));
        }

        public boolean neverHasTypeParameters(Symbols.Symbol sym) {
            boolean bl = sym instanceof Symbols.RefinementClassSymbol ? true : (sym instanceof Symbols.ModuleClassSymbol ? true : (sym instanceof Symbols.ImplClassSymbol ? true : sym.isPrimitiveValueClass() || sym.isAnonymousClass() || sym.initialize().isMonomorphicType()));
            return bl;
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public Types.Type EnumType(Symbols.Symbol sym) {
            Symbols.Symbol symbol;
            Phase phase = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().picklerPhase();
            SymbolTable symbolTable2 = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer();
            if (!symbolTable2.isAtPhaseAfter(phase)) {
                symbol = sym.owner().linkedClassOfClass();
                return symbol.tpe_$times();
            }
            SymbolTable symbolTable = symbolTable2;
            Phase saved1 = symbolTable2.pushPhase(phase);
            try {
                symbol = sym.owner().linkedClassOfClass();
                symbolTable2.popPhase(saved1);
                return symbol.tpe_$times();
            }
            catch (Throwable throwable) {
                void var5_5;
                symbolTable.popPhase((Phase)var5_5);
                throw throwable;
            }
        }

        public Types.Type classExistentialType(Types.Type prefix, Symbols.Symbol clazz) {
            List<Symbols.Symbol> eparams = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().typeParamsToExistentials(clazz, clazz.unsafeTypeParams());
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newExistentialType(eparams, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().typeRef(prefix, clazz, eparams.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Types.Type apply(Symbols.Symbol x$16) {
                    return x$16.tpeHK();
                }
            }, List$.MODULE$.canBuildFrom())));
        }

        public Symbols.MethodSymbol Any_$eq$eq() {
            return (this.bitmap$2 & 8L) == 0L ? this.Any_$eq$eq$lzycompute() : this.Any_$eq$eq;
        }

        public Symbols.MethodSymbol Any_$bang$eq() {
            return (this.bitmap$2 & 0x10L) == 0L ? this.Any_$bang$eq$lzycompute() : this.Any_$bang$eq;
        }

        public Symbols.MethodSymbol Any_equals() {
            return (this.bitmap$2 & 0x20L) == 0L ? this.Any_equals$lzycompute() : this.Any_equals;
        }

        public Symbols.MethodSymbol Any_hashCode() {
            return (this.bitmap$2 & 0x40L) == 0L ? this.Any_hashCode$lzycompute() : this.Any_hashCode;
        }

        public Symbols.MethodSymbol Any_toString() {
            return (this.bitmap$2 & 0x80L) == 0L ? this.Any_toString$lzycompute() : this.Any_toString;
        }

        public Symbols.MethodSymbol Any_$hash$hash() {
            return (this.bitmap$2 & 0x100L) == 0L ? this.Any_$hash$hash$lzycompute() : this.Any_$hash$hash;
        }

        public Symbols.MethodSymbol Any_getClass() {
            return (this.bitmap$2 & 0x200L) == 0L ? this.Any_getClass$lzycompute() : this.Any_getClass;
        }

        public Symbols.MethodSymbol Any_isInstanceOf() {
            return (this.bitmap$2 & 0x400L) == 0L ? this.Any_isInstanceOf$lzycompute() : this.Any_isInstanceOf;
        }

        public Symbols.MethodSymbol Any_asInstanceOf() {
            return (this.bitmap$2 & 0x800L) == 0L ? this.Any_asInstanceOf$lzycompute() : this.Any_asInstanceOf;
        }

        public Set<Symbols.Symbol> primitiveGetClassMethods() {
            return (this.bitmap$2 & 0x1000L) == 0L ? this.primitiveGetClassMethods$lzycompute() : this.primitiveGetClassMethods;
        }

        public Set<Symbols.Symbol> getClassMethods() {
            return (this.bitmap$2 & 0x2000L) == 0L ? this.getClassMethods$lzycompute() : this.getClassMethods;
        }

        public Types.Type getClassReturnType(Types.Type tp) {
            Types.Type type;
            Symbols.Symbol sym = tp.typeSymbol();
            if (this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().phase().erasedTypes()) {
                type = this.ClassClass().tpe();
            } else if (this.isPrimitiveValueClass(sym)) {
                type = this.ClassType(tp.widen());
            } else {
                List<Symbols.Symbol> eparams = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().typeParamsToExistentials(this.ClassClass(), this.ClassClass().typeParams());
                Types.Type upperBound = this.isPhantomClass().apply(sym) ? this.AnyTpe() : (sym.isLocalClass() ? this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().erasure().intersectionDominator(tp.parents()) : tp.widen());
                type = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().existentialAbstraction(eparams, this.ClassType(eparams.head().setInfo(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TypeBounds().upper(upperBound)).tpe()));
            }
            return type;
        }

        public List<Types.Type> removeLaterObjects(List<Types.Type> tps) {
            block4: {
                List list2;
                block3: {
                    block2: {
                        if (!((Object)Nil$.MODULE$).equals(tps)) break block2;
                        list2 = Nil$.MODULE$;
                        break block3;
                    }
                    if (!(tps instanceof $colon$colon)) break block4;
                    $colon$colon $colon$colon = ($colon$colon)tps;
                    Types.Type type = (Types.Type)$colon$colon.head();
                    list2 = ((List)$colon$colon.tl$1().filterNot((Function1)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ DefinitionsClass $outer;

                        public final boolean apply(Types.Type x$23) {
                            Symbols.Symbol symbol = x$23.typeSymbol();
                            Symbols.ClassSymbol classSymbol = this.$outer.ObjectClass();
                            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }))).$colon$colon(type);
                }
                return list2;
            }
            throw new MatchError(tps);
        }

        public List<Types.Type> removeRedundantObjects(List<Types.Type> tps) {
            block7: {
                List list2;
                block6: {
                    List<Types.Type> list3;
                    block5: {
                        if (!((Object)Nil$.MODULE$).equals(tps)) break block5;
                        list2 = Nil$.MODULE$;
                        break block6;
                    }
                    if (!(tps instanceof $colon$colon)) break block7;
                    $colon$colon $colon$colon = ($colon$colon)tps;
                    Symbols.Symbol symbol = ((Types.Type)$colon$colon.head()).typeSymbol();
                    Symbols.ClassSymbol classSymbol = this.ObjectClass();
                    if (!(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null)) {
                        Types.Type type = (Types.Type)$colon$colon.head();
                        list3 = ((List)$colon$colon.tl$1().filterNot((Function1)((Object)new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ DefinitionsClass $outer;

                            public final boolean apply(Types.Type x$25) {
                                Symbols.Symbol symbol = x$25.typeSymbol();
                                Symbols.ClassSymbol classSymbol = this.$outer.ObjectClass();
                                return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }))).$colon$colon(type);
                    } else {
                        Types.Type type = (Types.Type)$colon$colon.head();
                        list3 = this.removeRedundantObjects($colon$colon.tl$1()).$colon$colon(type);
                    }
                    list2 = list3;
                }
                return list2;
            }
            throw new MatchError(tps);
        }

        public List<Types.Type> normalizedParents(List<Types.Type> parents2) {
            return parents2.exists((Function1<Types.Type, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Types.Type t) {
                    return t.typeSymbol() != this.$outer.ObjectClass() && t.typeSymbol().isClass();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })) ? (List)parents2.filterNot((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Types.Type x$28) {
                    return x$28.typeSymbol() == this.$outer.ObjectClass();
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            })) : this.removeRedundantObjects(parents2);
        }

        public List<Symbols.Symbol> allParameters(Types.Type tpe) {
            List list2;
            if (tpe instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tpe;
                List<Symbols.Symbol> list3 = methodType.params();
                list2 = this.allParameters(methodType.resultType()).$colon$colon$colon(list3);
            } else {
                list2 = Nil$.MODULE$;
            }
            return list2;
        }

        public String typeStringNoPackage(Types.Type tp) {
            String string2 = String.valueOf(tp);
            Predef$ predef$ = Predef$.MODULE$;
            return new StringOps(string2).stripPrefix(new StringBuilder().append((Object)tp.typeSymbol().enclosingPackage().fullName()).append((Object)".").toString());
        }

        public String briefParentsString(List<Types.Type> parents2) {
            return ((TraversableOnce)this.normalizedParents(parents2).map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final String apply(Types.Type tp) {
                    return this.$outer.typeStringNoPackage(tp);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom())).mkString(" with ");
        }

        public String parentsString(List<Types.Type> parents2) {
            return this.normalizedParents(parents2).mkString(" with ");
        }

        public String valueParamsString(Types.Type tp) {
            String string2;
            if (tp instanceof Types.MethodType) {
                Types.MethodType methodType = (Types.MethodType)tp;
                string2 = ((TraversableOnce)methodType.params().map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final String apply(Symbols.Symbol x$30) {
                        return x$30.defString();
                    }
                }, List$.MODULE$.canBuildFrom())).mkString("(", ",", ")");
            } else {
                string2 = "";
            }
            return string2;
        }

        public Symbols.MethodSymbol Object_$hash$hash() {
            return (this.bitmap$2 & 0x4000L) == 0L ? this.Object_$hash$hash$lzycompute() : this.Object_$hash$hash;
        }

        public Symbols.MethodSymbol Object_$eq$eq() {
            return (this.bitmap$2 & 0x8000L) == 0L ? this.Object_$eq$eq$lzycompute() : this.Object_$eq$eq;
        }

        public Symbols.MethodSymbol Object_$bang$eq() {
            return (this.bitmap$2 & 0x10000L) == 0L ? this.Object_$bang$eq$lzycompute() : this.Object_$bang$eq;
        }

        public Symbols.MethodSymbol Object_eq() {
            return (this.bitmap$2 & 0x20000L) == 0L ? this.Object_eq$lzycompute() : this.Object_eq;
        }

        public Symbols.MethodSymbol Object_ne() {
            return (this.bitmap$2 & 0x40000L) == 0L ? this.Object_ne$lzycompute() : this.Object_ne;
        }

        public Symbols.MethodSymbol Object_isInstanceOf() {
            return (this.bitmap$2 & 0x80000L) == 0L ? this.Object_isInstanceOf$lzycompute() : this.Object_isInstanceOf;
        }

        public Symbols.MethodSymbol Object_asInstanceOf() {
            return (this.bitmap$2 & 0x100000L) == 0L ? this.Object_asInstanceOf$lzycompute() : this.Object_asInstanceOf;
        }

        public Symbols.MethodSymbol Object_synchronized() {
            return (this.bitmap$2 & 0x200000L) == 0L ? this.Object_synchronized$lzycompute() : this.Object_synchronized;
        }

        public Symbols.MethodSymbol String_$plus() {
            return (this.bitmap$2 & 0x400000L) == 0L ? this.String_$plus$lzycompute() : this.String_$plus;
        }

        public Symbols.TermSymbol Object_getClass() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().getClass_());
        }

        public Symbols.TermSymbol Object_clone() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().clone_());
        }

        public Symbols.TermSymbol Object_finalize() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().finalize_());
        }

        public Symbols.TermSymbol Object_notify() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().notify_());
        }

        public Symbols.TermSymbol Object_notifyAll() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().notifyAll_());
        }

        public Symbols.TermSymbol Object_equals() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().equals_());
        }

        public Symbols.TermSymbol Object_hashCode() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().hashCode_());
        }

        public Symbols.TermSymbol Object_toString() {
            return this.getMemberMethod(this.ObjectClass(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().toString_());
        }

        public Symbols.ClassSymbol ObjectRefClass() {
            return (this.bitmap$2 & 0x800000L) == 0L ? this.ObjectRefClass$lzycompute() : this.ObjectRefClass;
        }

        public Symbols.ClassSymbol VolatileObjectRefClass() {
            return (this.bitmap$2 & 0x1000000L) == 0L ? this.VolatileObjectRefClass$lzycompute() : this.VolatileObjectRefClass;
        }

        public Symbols.ModuleSymbol RuntimeStaticsModule() {
            return (this.bitmap$2 & 0x2000000L) == 0L ? this.RuntimeStaticsModule$lzycompute() : this.RuntimeStaticsModule;
        }

        public Symbols.ModuleSymbol BoxesRunTimeModule() {
            return (this.bitmap$2 & 0x4000000L) == 0L ? this.BoxesRunTimeModule$lzycompute() : this.BoxesRunTimeModule;
        }

        public Symbols.Symbol BoxesRunTimeClass() {
            return (this.bitmap$2 & 0x8000000L) == 0L ? this.BoxesRunTimeClass$lzycompute() : this.BoxesRunTimeClass;
        }

        public Symbols.ClassSymbol BoxedNumberClass() {
            return (this.bitmap$2 & 0x10000000L) == 0L ? this.BoxedNumberClass$lzycompute() : this.BoxedNumberClass;
        }

        public Symbols.ClassSymbol BoxedCharacterClass() {
            return (this.bitmap$2 & 0x20000000L) == 0L ? this.BoxedCharacterClass$lzycompute() : this.BoxedCharacterClass;
        }

        public Symbols.ClassSymbol BoxedBooleanClass() {
            return (this.bitmap$2 & 0x40000000L) == 0L ? this.BoxedBooleanClass$lzycompute() : this.BoxedBooleanClass;
        }

        public Symbols.ClassSymbol BoxedByteClass() {
            return (this.bitmap$2 & 0x80000000L) == 0L ? this.BoxedByteClass$lzycompute() : this.BoxedByteClass;
        }

        public Symbols.ClassSymbol BoxedShortClass() {
            return (this.bitmap$2 & 0x100000000L) == 0L ? this.BoxedShortClass$lzycompute() : this.BoxedShortClass;
        }

        public Symbols.ClassSymbol BoxedIntClass() {
            return (this.bitmap$2 & 0x200000000L) == 0L ? this.BoxedIntClass$lzycompute() : this.BoxedIntClass;
        }

        public Symbols.ClassSymbol BoxedLongClass() {
            return (this.bitmap$2 & 0x400000000L) == 0L ? this.BoxedLongClass$lzycompute() : this.BoxedLongClass;
        }

        public Symbols.ClassSymbol BoxedFloatClass() {
            return (this.bitmap$2 & 0x800000000L) == 0L ? this.BoxedFloatClass$lzycompute() : this.BoxedFloatClass;
        }

        public Symbols.ClassSymbol BoxedDoubleClass() {
            return (this.bitmap$2 & 0x1000000000L) == 0L ? this.BoxedDoubleClass$lzycompute() : this.BoxedDoubleClass;
        }

        public Symbols.ClassSymbol BoxedUnitClass() {
            return (this.bitmap$2 & 0x2000000000L) == 0L ? this.BoxedUnitClass$lzycompute() : this.BoxedUnitClass;
        }

        public Symbols.ModuleSymbol BoxedUnitModule() {
            return (this.bitmap$2 & 0x4000000000L) == 0L ? this.BoxedUnitModule$lzycompute() : this.BoxedUnitModule;
        }

        public Symbols.TermSymbol BoxedUnit_UNIT() {
            return this.getMemberValue(this.BoxedUnitModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().UNIT());
        }

        public Symbols.TermSymbol BoxedUnit_TYPE() {
            return this.getMemberValue(this.BoxedUnitModule(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().TYPE_());
        }

        public Symbols.ClassSymbol AnnotationClass() {
            return (this.bitmap$2 & 0x8000000000L) == 0L ? this.AnnotationClass$lzycompute() : this.AnnotationClass;
        }

        public Symbols.ClassSymbol ClassfileAnnotationClass() {
            return (this.bitmap$2 & 0x10000000000L) == 0L ? this.ClassfileAnnotationClass$lzycompute() : this.ClassfileAnnotationClass;
        }

        public Symbols.ClassSymbol StaticAnnotationClass() {
            return (this.bitmap$2 & 0x20000000000L) == 0L ? this.StaticAnnotationClass$lzycompute() : this.StaticAnnotationClass;
        }

        public Symbols.ClassSymbol AnnotationRetentionAttr() {
            return (this.bitmap$2 & 0x40000000000L) == 0L ? this.AnnotationRetentionAttr$lzycompute() : this.AnnotationRetentionAttr;
        }

        public Symbols.ClassSymbol AnnotationRetentionPolicyAttr() {
            return (this.bitmap$2 & 0x80000000000L) == 0L ? this.AnnotationRetentionPolicyAttr$lzycompute() : this.AnnotationRetentionPolicyAttr;
        }

        public Symbols.ClassSymbol BridgeClass() {
            return (this.bitmap$2 & 0x100000000000L) == 0L ? this.BridgeClass$lzycompute() : this.BridgeClass;
        }

        public Symbols.ClassSymbol ElidableMethodClass() {
            return (this.bitmap$2 & 0x200000000000L) == 0L ? this.ElidableMethodClass$lzycompute() : this.ElidableMethodClass;
        }

        public Symbols.ClassSymbol ImplicitNotFoundClass() {
            return (this.bitmap$2 & 0x400000000000L) == 0L ? this.ImplicitNotFoundClass$lzycompute() : this.ImplicitNotFoundClass;
        }

        public Symbols.ClassSymbol MigrationAnnotationClass() {
            return (this.bitmap$2 & 0x800000000000L) == 0L ? this.MigrationAnnotationClass$lzycompute() : this.MigrationAnnotationClass;
        }

        public Symbols.ClassSymbol ScalaStrictFPAttr() {
            return (this.bitmap$2 & 0x1000000000000L) == 0L ? this.ScalaStrictFPAttr$lzycompute() : this.ScalaStrictFPAttr;
        }

        public Symbols.ClassSymbol SwitchClass() {
            return (this.bitmap$2 & 0x2000000000000L) == 0L ? this.SwitchClass$lzycompute() : this.SwitchClass;
        }

        public Symbols.ClassSymbol TailrecClass() {
            return (this.bitmap$2 & 0x4000000000000L) == 0L ? this.TailrecClass$lzycompute() : this.TailrecClass;
        }

        public Symbols.ClassSymbol VarargsClass() {
            return (this.bitmap$2 & 0x8000000000000L) == 0L ? this.VarargsClass$lzycompute() : this.VarargsClass;
        }

        public Symbols.ClassSymbol uncheckedStableClass() {
            return (this.bitmap$2 & 0x10000000000000L) == 0L ? this.uncheckedStableClass$lzycompute() : this.uncheckedStableClass;
        }

        public Symbols.ClassSymbol uncheckedVarianceClass() {
            return (this.bitmap$2 & 0x20000000000000L) == 0L ? this.uncheckedVarianceClass$lzycompute() : this.uncheckedVarianceClass;
        }

        public Symbols.ClassSymbol BeanPropertyAttr() {
            return (this.bitmap$2 & 0x40000000000000L) == 0L ? this.BeanPropertyAttr$lzycompute() : this.BeanPropertyAttr;
        }

        public Symbols.ClassSymbol BooleanBeanPropertyAttr() {
            return (this.bitmap$2 & 0x80000000000000L) == 0L ? this.BooleanBeanPropertyAttr$lzycompute() : this.BooleanBeanPropertyAttr;
        }

        public Symbols.Symbol CompileTimeOnlyAttr() {
            return (this.bitmap$2 & 0x100000000000000L) == 0L ? this.CompileTimeOnlyAttr$lzycompute() : this.CompileTimeOnlyAttr;
        }

        public Symbols.ClassSymbol DeprecatedAttr() {
            return (this.bitmap$2 & 0x200000000000000L) == 0L ? this.DeprecatedAttr$lzycompute() : this.DeprecatedAttr;
        }

        public Symbols.ClassSymbol DeprecatedNameAttr() {
            return (this.bitmap$2 & 0x400000000000000L) == 0L ? this.DeprecatedNameAttr$lzycompute() : this.DeprecatedNameAttr;
        }

        public Symbols.ClassSymbol DeprecatedInheritanceAttr() {
            return (this.bitmap$2 & 0x800000000000000L) == 0L ? this.DeprecatedInheritanceAttr$lzycompute() : this.DeprecatedInheritanceAttr;
        }

        public Symbols.ClassSymbol DeprecatedOverridingAttr() {
            return (this.bitmap$2 & 0x1000000000000000L) == 0L ? this.DeprecatedOverridingAttr$lzycompute() : this.DeprecatedOverridingAttr;
        }

        public Symbols.ClassSymbol NativeAttr() {
            return (this.bitmap$2 & 0x2000000000000000L) == 0L ? this.NativeAttr$lzycompute() : this.NativeAttr;
        }

        public Symbols.ClassSymbol RemoteAttr() {
            return (this.bitmap$2 & 0x4000000000000000L) == 0L ? this.RemoteAttr$lzycompute() : this.RemoteAttr;
        }

        public Symbols.ClassSymbol ScalaInlineClass() {
            return (this.bitmap$2 & Long.MIN_VALUE) == 0L ? this.ScalaInlineClass$lzycompute() : this.ScalaInlineClass;
        }

        public Symbols.ClassSymbol ScalaNoInlineClass() {
            return (this.bitmap$3 & 1L) == 0L ? this.ScalaNoInlineClass$lzycompute() : this.ScalaNoInlineClass;
        }

        public Symbols.ClassSymbol SerialVersionUIDAttr() {
            return (this.bitmap$3 & 2L) == 0L ? this.SerialVersionUIDAttr$lzycompute() : this.SerialVersionUIDAttr;
        }

        public AnnotationInfos.AnnotationInfo SerialVersionUIDAnnotation() {
            return (this.bitmap$3 & 4L) == 0L ? this.SerialVersionUIDAnnotation$lzycompute() : this.SerialVersionUIDAnnotation;
        }

        public Symbols.ClassSymbol SpecializedClass() {
            return (this.bitmap$3 & 8L) == 0L ? this.SpecializedClass$lzycompute() : this.SpecializedClass;
        }

        public Symbols.ClassSymbol ThrowsClass() {
            return (this.bitmap$3 & 0x10L) == 0L ? this.ThrowsClass$lzycompute() : this.ThrowsClass;
        }

        public Symbols.ClassSymbol TransientAttr() {
            return (this.bitmap$3 & 0x20L) == 0L ? this.TransientAttr$lzycompute() : this.TransientAttr;
        }

        public Symbols.ClassSymbol UncheckedClass() {
            return (this.bitmap$3 & 0x40L) == 0L ? this.UncheckedClass$lzycompute() : this.UncheckedClass;
        }

        public Symbols.Symbol UncheckedBoundsClass() {
            return (this.bitmap$3 & 0x80L) == 0L ? this.UncheckedBoundsClass$lzycompute() : this.UncheckedBoundsClass;
        }

        public Symbols.ClassSymbol UnspecializedClass() {
            return (this.bitmap$3 & 0x100L) == 0L ? this.UnspecializedClass$lzycompute() : this.UnspecializedClass;
        }

        public Symbols.ClassSymbol VolatileAttr() {
            return (this.bitmap$3 & 0x200L) == 0L ? this.VolatileAttr$lzycompute() : this.VolatileAttr;
        }

        public Symbols.ClassSymbol BeanGetterTargetClass() {
            return (this.bitmap$3 & 0x400L) == 0L ? this.BeanGetterTargetClass$lzycompute() : this.BeanGetterTargetClass;
        }

        public Symbols.ClassSymbol BeanSetterTargetClass() {
            return (this.bitmap$3 & 0x800L) == 0L ? this.BeanSetterTargetClass$lzycompute() : this.BeanSetterTargetClass;
        }

        public Symbols.ClassSymbol FieldTargetClass() {
            return (this.bitmap$3 & 0x1000L) == 0L ? this.FieldTargetClass$lzycompute() : this.FieldTargetClass;
        }

        public Symbols.ClassSymbol GetterTargetClass() {
            return (this.bitmap$3 & 0x2000L) == 0L ? this.GetterTargetClass$lzycompute() : this.GetterTargetClass;
        }

        public Symbols.ClassSymbol ParamTargetClass() {
            return (this.bitmap$3 & 0x4000L) == 0L ? this.ParamTargetClass$lzycompute() : this.ParamTargetClass;
        }

        public Symbols.ClassSymbol SetterTargetClass() {
            return (this.bitmap$3 & 0x8000L) == 0L ? this.SetterTargetClass$lzycompute() : this.SetterTargetClass;
        }

        public Symbols.ClassSymbol ObjectTargetClass() {
            return (this.bitmap$3 & 0x10000L) == 0L ? this.ObjectTargetClass$lzycompute() : this.ObjectTargetClass;
        }

        public Symbols.ClassSymbol ClassTargetClass() {
            return (this.bitmap$3 & 0x20000L) == 0L ? this.ClassTargetClass$lzycompute() : this.ClassTargetClass;
        }

        public Symbols.ClassSymbol MethodTargetClass() {
            return (this.bitmap$3 & 0x40000L) == 0L ? this.MethodTargetClass$lzycompute() : this.MethodTargetClass;
        }

        public Symbols.ClassSymbol LanguageFeatureAnnot() {
            return (this.bitmap$3 & 0x80000L) == 0L ? this.LanguageFeatureAnnot$lzycompute() : this.LanguageFeatureAnnot;
        }

        public Symbols.ModuleSymbol languageFeatureModule() {
            return (this.bitmap$3 & 0x100000L) == 0L ? this.languageFeatureModule$lzycompute() : this.languageFeatureModule;
        }

        public boolean isMetaAnnotation(Symbols.Symbol sym) {
            return this.metaAnnotations().apply(sym) || sym.isAliasType() && this.isMetaAnnotation(sym.info().typeSymbol());
        }

        public Set<Symbols.Symbol> metaAnnotations() {
            return (this.bitmap$3 & 0x200000L) == 0L ? this.metaAnnotations$lzycompute() : this.metaAnnotations;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public Symbols.Symbol defaultAnnotationTarget(Trees.Tree t) {
            boolean bl = false;
            Trees.SymTree symTree = null;
            if (t instanceof Trees.ClassDef) {
                return this.ClassTargetClass();
            }
            if (t instanceof Trees.ModuleDef) {
                return this.ObjectTargetClass();
            }
            if (t instanceof Trees.ValDef) {
                bl = true;
                symTree = (Trees.ValDef)t;
                if (symTree.symbol().isParamAccessor()) {
                    return this.ParamTargetClass();
                }
            }
            if (bl && symTree.symbol().isValueParameter()) {
                return this.ParamTargetClass();
            }
            if (bl) {
                return this.FieldTargetClass();
            }
            if (!(t instanceof Trees.DefDef)) return this.GetterTargetClass();
            return this.MethodTargetClass();
        }

        public Symbols.ClassSymbol AnnotationDefaultAttr() {
            return (this.bitmap$3 & 0x400000L) == 0L ? this.AnnotationDefaultAttr$lzycompute() : this.AnnotationDefaultAttr;
        }

        public Nothing$ scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(Symbols.Symbol owner2, Names.Name name, String what, String addendum) {
            throw new FatalError(new StringBuilder().append((Object)Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(owner2), " does not have a ")).append((Object)what).append((Object)" ").append(name).append((Object)addendum).toString());
        }

        public String scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$3() {
            return "member";
        }

        public String scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4() {
            return "";
        }

        public Symbols.Symbol getLanguageFeature(String name, Symbols.Symbol owner2) {
            return this.getMember(owner2, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newTypeName(name));
        }

        public Symbols.Symbol getLanguageFeature$default$2() {
            return this.languageFeatureModule();
        }

        public Symbols.Symbol termMember(Symbols.Symbol owner2, String name) {
            return owner2.info().member(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newTermName(name));
        }

        /*
         * Enabled aggressive block sorting
         */
        public Symbols.Symbol findNamedMember(Names.Name fullName, Symbols.Symbol root) {
            Symbols.Symbol symbol;
            List<Names.Name> segs = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().segments(fullName.toString(), fullName.isTermName());
            if (!segs.isEmpty()) {
                Names.Name name = segs.head();
                Names.Name name2 = root.simpleName();
                if (!(name != null ? !name.equals(name2) : name2 != null)) {
                    symbol = this.findNamedMember((List)segs.tail(), root);
                    return symbol;
                }
            }
            symbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            return symbol;
        }

        public Symbols.Symbol findNamedMember(List<Names.Name> segs, Symbols.Symbol root) {
            return segs.isEmpty() ? root : this.findNamedMember((List)segs.tail(), root.info().member(segs.head()));
        }

        public Symbols.Symbol getMember(Symbols.Symbol owner2, Names.Name name) {
            block4: {
                Symbols.Symbol symbol;
                block3: {
                    block2: {
                        Symbols.Symbol symbol2 = this.getMemberIfDefined(owner2, name);
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) break block2;
                        symbol = symbol2;
                        break block3;
                    }
                    if (!this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().phase().flatClasses() || !name.isTypeName() || owner2.isPackageObjectOrClass()) break block4;
                    Symbols.Symbol pkg1 = owner2.owner();
                    Names.TypeName flatname1 = (Names.TypeName)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().flattenedName(Predef$.MODULE$.wrapRefArray((Object[])new Names.Name[]{owner2.name(), name}));
                    symbol = this.getMember(pkg1, flatname1);
                }
                return symbol;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "member", "");
        }

        public Symbols.TermSymbol getMemberValue(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getMember(owner2, name.toTermName());
            if (symbol instanceof Symbols.TermSymbol) {
                Symbols.TermSymbol termSymbol = (Symbols.TermSymbol)symbol;
                return termSymbol;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "member value", this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4());
        }

        public Symbols.ModuleSymbol getMemberModule(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getMember(owner2, name.toTermName());
            if (symbol instanceof Symbols.ModuleSymbol) {
                Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)symbol;
                return moduleSymbol;
            }
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            if (!(noSymbol != null ? !noSymbol.equals(symbol) : symbol != null)) {
                throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "member object", this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4());
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "member object", new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{". A symbol ", " of kind ", " already exists."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{symbol, symbol.accurateKindString()})));
        }

        public Symbols.TypeSymbol getTypeMember(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getMember(owner2, name.toTypeName());
            if (symbol instanceof Symbols.TypeSymbol) {
                Symbols.TypeSymbol typeSymbol2 = (Symbols.TypeSymbol)symbol;
                return typeSymbol2;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "type member", this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4());
        }

        public Symbols.ClassSymbol getMemberClass(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getMember(owner2, name.toTypeName());
            if (symbol instanceof Symbols.ClassSymbol) {
                Symbols.ClassSymbol classSymbol = (Symbols.ClassSymbol)symbol;
                return classSymbol;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "member class", this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4());
        }

        public Symbols.TermSymbol getMemberMethod(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getMember(owner2, name.toTermName());
            if (symbol instanceof Symbols.TermSymbol) {
                Symbols.TermSymbol termSymbol = (Symbols.TermSymbol)symbol;
                return termSymbol;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "method", this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol$default$4());
        }

        private Phase erasurePhase() {
            return (this.bitmap$3 & 0x800000L) == 0L ? this.erasurePhase$lzycompute() : this.erasurePhase;
        }

        /*
         * WARNING - void declaration
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public Symbols.Symbol getMemberIfDefined(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol;
            Phase phase = this.erasurePhase();
            SymbolTable symbolTable2 = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer();
            if (!symbolTable2.isAtPhaseAfter(phase)) {
                symbol = owner2.info().nonPrivateMember(name);
                return symbol;
            }
            SymbolTable symbolTable = symbolTable2;
            Phase saved1 = symbolTable2.pushPhase(phase);
            try {
                symbol = owner2.info().nonPrivateMember(name);
                symbolTable2.popPhase(saved1);
                return symbol;
            }
            catch (Throwable throwable) {
                void var6_6;
                symbolTable.popPhase((Phase)var6_6);
                throw throwable;
            }
        }

        public Symbols.Symbol getDecl(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.getDeclIfDefined(owner2, name);
            if (symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                return symbol;
            }
            throw this.scala$reflect$internal$Definitions$DefinitionsClass$$fatalMissingSymbol(owner2, name, "decl", "");
        }

        public Symbols.Symbol getDeclIfDefined(Symbols.Symbol owner2, Names.Name name) {
            return owner2.info().nonPrivateDecl(name);
        }

        private Symbols.AliasTypeSymbol newAlias(Symbols.Symbol owner2, Names.TypeName name, Types.Type alias) {
            return (Symbols.AliasTypeSymbol)owner2.newAliasType(name, owner2.newAliasType$default$2(), owner2.newAliasType$default$3()).setInfoAndEnter(alias);
        }

        private Symbols.ClassSymbol specialPolyClass(Names.TypeName name, long flags, Function1<Symbols.Symbol, Types.Type> parentFn) {
            Symbols.ClassSymbol clazz = Definitions$class.scala$reflect$internal$Definitions$$enterNewClass(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), this.ScalaPackageClass(), name, Nil$.MODULE$, Definitions$class.scala$reflect$internal$Definitions$$enterNewClass$default$4(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer()));
            Symbols.TypeSymbol tparam = clazz.newSyntheticTypeParam("T0", flags);
            GenTraversable parents2 = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.AnyRefTpe(), parentFn.apply(tparam)}));
            return (Symbols.ClassSymbol)clazz.setInfo(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().GenPolyType().apply((List<Symbols.Symbol>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.TypeSymbol[]{tparam})), new Types.ClassInfoType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), (List<Types.Type>)parents2, this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newScope(), clazz))).markAllCompleted();
        }

        public Symbols.MethodSymbol newPolyMethod(int typeParamCount, Symbols.Symbol owner2, Names.TermName name, long flags, Function1<List<Symbols.Symbol>, Tuple2<Option<List<Types.Type>>, Types.Type>> createFn) {
            Tuple2<Option<List<Types.Type>>, Types.Type> tuple2;
            block4: {
                Types.Type type;
                List<Symbols.Symbol> tparams2;
                Symbols.MethodSymbol msym;
                block3: {
                    block2: {
                        msym = owner2.newMethod((Names.TermName)name.encode(), this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPosition(), flags);
                        tparams2 = msym.newSyntheticTypeParams(typeParamCount);
                        tuple2 = createFn.apply(tparams2);
                        if (tuple2 == null || !(tuple2._1() instanceof Some)) break block2;
                        Some some = (Some)tuple2._1();
                        type = new Types.MethodType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), msym.newSyntheticValueParams((List)some.x()), tuple2._2());
                        break block3;
                    }
                    if (tuple2 == null) break block4;
                    type = new Types.NullaryMethodType(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), tuple2._2());
                }
                return (Symbols.MethodSymbol)msym.setInfoAndEnter(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().genPolyType(tparams2, type)).markAllCompleted();
            }
            throw new MatchError(tuple2);
        }

        public Symbols.MethodSymbol newT1NullaryMethod(Symbols.Symbol owner2, Names.TermName name, long flags, Function1<Symbols.Symbol, Types.Type> createFn) {
            return this.newPolyMethod(1, owner2, name, flags, (Function1<List<Symbols.Symbol>, Tuple2<Option<List<Types.Type>>, Types.Type>>)((Object)new Serializable(this, createFn){
                public static final long serialVersionUID = 0L;
                private final Function1 createFn$1;

                public final Tuple2<None$, Types.Type> apply(List<Symbols.Symbol> tparams2) {
                    return new Tuple2<None$, Types.Type>(None$.MODULE$, (Types.Type)this.createFn$1.apply(tparams2.head()));
                }
                {
                    this.createFn$1 = createFn$1;
                }
            }));
        }

        public Symbols.MethodSymbol newT1NoParamsMethod(Symbols.Symbol owner2, Names.TermName name, long flags, Function1<Symbols.Symbol, Types.Type> createFn) {
            return this.newPolyMethod(1, owner2, name, flags, (Function1<List<Symbols.Symbol>, Tuple2<Option<List<Types.Type>>, Types.Type>>)((Object)new Serializable(this, createFn){
                public static final long serialVersionUID = 0L;
                private final Function1 createFn$2;

                public final Tuple2<Some<Nil$>, Types.Type> apply(List<Symbols.Symbol> tparams2) {
                    return new Tuple2<Some<Nil$>, Types.Type>(new Some<Nil$>(Nil$.MODULE$), (Types.Type)this.createFn$2.apply(tparams2.head()));
                }
                {
                    this.createFn$2 = createFn$2;
                }
            }));
        }

        public Set<Symbols.Symbol> isPhantomClass() {
            return (this.bitmap$3 & 0x1000000L) == 0L ? this.isPhantomClass$lzycompute() : this.isPhantomClass;
        }

        public List<Symbols.TypeSymbol> syntheticCoreClasses() {
            return (this.bitmap$3 & 0x2000000L) == 0L ? this.syntheticCoreClasses$lzycompute() : this.syntheticCoreClasses;
        }

        public List<Symbols.MethodSymbol> syntheticCoreMethods() {
            return (this.bitmap$3 & 0x4000000L) == 0L ? this.syntheticCoreMethods$lzycompute() : this.syntheticCoreMethods;
        }

        public List<Symbols.ClassSymbol> hijackedCoreClasses() {
            return (this.bitmap$3 & 0x8000000L) == 0L ? this.hijackedCoreClasses$lzycompute() : this.hijackedCoreClasses;
        }

        public List<Symbols.Symbol> symbolsNotPresentInBytecode() {
            return (this.bitmap$3 & 0x10000000L) == 0L ? this.symbolsNotPresentInBytecode$lzycompute() : this.symbolsNotPresentInBytecode;
        }

        public Set<Symbols.Symbol> isPossibleSyntheticParent() {
            return (this.bitmap$3 & 0x20000000L) == 0L ? this.isPossibleSyntheticParent$lzycompute() : this.isPossibleSyntheticParent;
        }

        private Set<Symbols.Symbol> boxedValueClassesSet() {
            return (this.bitmap$3 & 0x40000000L) == 0L ? this.boxedValueClassesSet$lzycompute() : this.boxedValueClassesSet;
        }

        public boolean isPrimitiveValueClass(Symbols.Symbol sym) {
            return this.ScalaValueClasses().contains(sym);
        }

        public boolean isPrimitiveValueType(Types.Type tp) {
            return this.isPrimitiveValueClass(tp.typeSymbol());
        }

        public boolean isBoxedValueClass(Symbols.Symbol sym) {
            return this.boxedValueClassesSet().apply(sym);
        }

        public Symbols.Symbol unboxedValueClass(Symbols.Symbol sym) {
            Symbols.Symbol symbol;
            if (this.isPrimitiveValueClass(sym)) {
                symbol = sym;
            } else {
                Symbols.Symbol symbol2 = sym;
                Symbols.ClassSymbol classSymbol = this.BoxedUnitClass();
                symbol = !(symbol2 != null ? !symbol2.equals(classSymbol) : classSymbol != null) ? this.UnitClass() : (Symbols.Symbol)((MapLike)this.boxedClass().map((Function1<Symbols.Symbol, Symbols.ClassSymbol>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Tuple2<Symbols.Symbol, Symbols.Symbol> apply(Tuple2<Symbols.Symbol, Symbols.ClassSymbol> kvp) {
                        return new Tuple2<Symbols.Symbol, Symbols.Symbol>(kvp._2(), kvp._1());
                    }
                }), Map$.MODULE$.canBuildFrom())).getOrElse(sym, new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ DefinitionsClass $outer;

                    public final Symbols.NoSymbol apply() {
                        return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                });
            }
            return symbol;
        }

        public boolean isNumericValueType(Types.Type tp) {
            boolean bl;
            if (tp instanceof Types.TypeRef) {
                Types.TypeRef typeRef = (Types.TypeRef)tp;
                bl = this.isNumericValueClass(typeRef.sym());
            } else {
                bl = false;
            }
            return bl;
        }

        public String signature(Types.Type tp) {
            Types.Type etp = this.erasure$1(tp);
            Symbols.Symbol symbol = etp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.ArrayClass();
            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null) ? this.signature1$1(etp) : this.flatNameString$1(etp.typeSymbol(), '.');
        }

        public void init() {
            if (this.isInitialized()) {
                return;
            }
            this.ObjectClass().initialize();
            this.ScalaPackageClass().initialize();
            this.symbolsNotPresentInBytecode();
            this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            this.isInitialized_$eq(true);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Definitions$DefinitionsClass$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Definitions scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer() {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer();
        }

        @Override
        public /* synthetic */ StandardDefinitions scala$reflect$api$StandardDefinitions$DefinitionsApi$$$outer() {
            return this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer();
        }

        private final Symbols.Symbol sym$2(Types.Type tp$7) {
            return tp$7.typeSymbol();
        }

        private final boolean volatileUpperBound$1(Types.Type tp$7) {
            return this.isVolatile(tp$7.bounds().hi());
        }

        private final boolean safeIsVolatile$1(Types.Type tp$7) {
            boolean bl;
            block7: {
                block6: {
                    if (this.volatileRecursions < 50) {
                        bl = this.volatileUpperBound$1(tp$7);
                    } else {
                        if (!this.pendingVolatiles.apply(this.sym$2(tp$7))) {
                            this.pendingVolatiles.$plus$eq((Object)this.sym$2(tp$7));
                            if (!this.volatileUpperBound$1(tp$7)) break block6;
                        }
                        bl = true;
                    }
                    break block7;
                }
                bl = false;
            }
            return bl;
            finally {
                this.pendingVolatiles.$minus$eq((Object)this.sym$2(tp$7));
            }
        }

        private final boolean isVolatileAbstractType$1(Types.Type tp$7) {
            ++this.volatileRecursions;
            try {
                return this.safeIsVolatile$1(tp$7);
            }
            finally {
                --this.volatileRecursions;
            }
        }

        public final boolean scala$reflect$internal$Definitions$DefinitionsClass$$isVisibleDeferred$1(Symbols.Symbol m, Types.Type tp$7) {
            return m.isDeferred() && tp$7.nonPrivateMember(m.name()).alternatives().contains(m);
        }

        public final boolean scala$reflect$internal$Definitions$DefinitionsClass$$contributesAbstractMembers$1(Types.Type p, Types.Type tp$7) {
            return p.deferredMembers().exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, tp$7){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$7;

                public final boolean apply(Symbols.Symbol m) {
                    return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$isVisibleDeferred$1(m, this.tp$7);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$7 = tp$7;
                }
            }));
        }

        private final List dropConcreteParents$1(List parents$1) {
            return parents$1.dropWhile((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Types.Type p) {
                    return !p.typeSymbol().isAbstractType();
                }
            }));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isVolatileRefinedType$1(Types.Type tp$7) {
            if (!(tp$7 instanceof Types.RefinedType)) throw new MatchError(tp$7);
            Types.RefinedType refinedType = (Types.RefinedType)tp$7;
            Tuple2<List<Types.Type>, Scopes.Scope> tuple2 = new Tuple2<List<Types.Type>, Scopes.Scope>(refinedType.parents(), refinedType.decls());
            List<Types.Type> parents2 = tuple2._1();
            Scopes.Scope decls = tuple2._2();
            if (parents2.exists((Function1<Types.Type, Object>)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;

                public final boolean apply(Types.Type tp) {
                    return this.$outer.isVolatile(tp);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }))) return true;
            List list2 = this.dropConcreteParents$1(parents2);
            if (((Object)Nil$.MODULE$).equals(list2)) {
                return false;
            }
            if (list2 != parents2) return true;
            if (((LinearSeqOptimized)list2.tail()).exists(new Serializable(this, tp$7){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$7;

                public final boolean apply(Types.Type p) {
                    return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$contributesAbstractMembers$1(p, this.tp$7);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$7 = tp$7;
                }
            })) return true;
            if (!decls.exists((Function1<Symbols.Symbol, Object>)((Object)new Serializable(this, tp$7){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ DefinitionsClass $outer;
                private final Types.Type tp$7;

                public final boolean apply(Symbols.Symbol m) {
                    return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$$isVisibleDeferred$1(m, this.tp$7);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.tp$7 = tp$7;
                }
            }))) return false;
            return true;
        }

        private final List loop$1(int n, Types.Type tpe$2) {
            Symbols.Symbol symbol = tpe$2.member(this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName().apply(new StringBuilder().append((Object)"_").append(BoxesRunTime.boxToInteger(n)).toString()));
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            List list2 = !(noSymbol != null ? !noSymbol.equals(symbol) : symbol != null) ? Nil$.MODULE$ : (symbol.paramss().nonEmpty() ? Nil$.MODULE$ : this.loop$1(n + 1, tpe$2).$colon$colon(symbol));
            return list2;
        }

        public final boolean scala$reflect$internal$Definitions$DefinitionsClass$$matchesParams$1(Symbols.Symbol member, Seq paramTypes$1) {
            List<List<Symbols.Symbol>> list2;
            block4: {
                boolean bl;
                block3: {
                    block2: {
                        list2 = member.paramss();
                        if (!((Object)Nil$.MODULE$).equals(list2)) break block2;
                        bl = paramTypes$1.isEmpty();
                        break block3;
                    }
                    if (!(list2 instanceof $colon$colon)) break block4;
                    $colon$colon $colon$colon = ($colon$colon)list2;
                    bl = ($colon$colon.tl$1().isEmpty() || this.isImplicitParamss($colon$colon.tl$1())) && ((LinearSeqLike)$colon$colon.head()).corresponds(paramTypes$1, new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Symbols.Symbol x$14, Types.Type x$15) {
                            return x$14.tpe().$eq$colon$eq(x$15);
                        }
                    });
                }
                return bl;
            }
            throw new MatchError(list2);
        }

        private final Types.Type erasure$1(Types.Type tp) {
            while (true) {
                if (tp instanceof Types.SubType) {
                    Types.SubType subType = (Types.SubType)tp;
                    tp = subType.supertype();
                    continue;
                }
                if (!(tp instanceof Types.RefinedType)) break;
                Types.RefinedType refinedType = (Types.RefinedType)tp;
                tp = refinedType.parents().head();
            }
            return tp;
        }

        private final String flatNameString$1(Symbols.Symbol sym, char separator) {
            Symbols.Symbol symbol = sym;
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            return !(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null) ? "" : (sym.isTopLevel() ? sym.javaClassName() : new StringBuilder().append((Object)this.flatNameString$1(sym.owner(), separator)).append((Object)this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().NAME_JOIN_STRING()).append(sym.simpleName()).toString());
        }

        private final String signature1$1(Types.Type etp) {
            Symbols.Symbol symbol = etp.typeSymbol();
            Symbols.ClassSymbol classSymbol = this.ArrayClass();
            return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null) ? new StringBuilder().append((Object)"[").append((Object)this.signature1$1(this.erasure$1(etp.dealiasWiden().typeArgs().head()))).toString() : (this.isPrimitiveValueClass(etp.typeSymbol()) ? this.abbrvTag().apply(etp.typeSymbol()).toString() : new StringBuilder().append((Object)"L").append((Object)this.flatNameString$1(etp.typeSymbol(), '/')).append((Object)";").toString());
        }

        public DefinitionsClass(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            StandardDefinitions$DefinitionsApi$class.$init$(this);
            Definitions$ValueClassDefinitions$class.$init$(this);
            this.isInitialized = false;
            this.MaxTupleArity = 22;
            this.MaxProductArity = 22;
            this.MaxFunctionArity = 22;
            this.volatileRecursions = 0;
            this.pendingVolatiles = (HashSet)HashSet$.MODULE$.apply(Nil$.MODULE$);
        }

        public class VarArityClass
        extends StandardDefinitions.DefinitionsApi.VarArityClassApi {
            public final String scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$name;
            private final int offset;
            private final IndexedSeq<Symbols.ClassSymbol> seq;

            private int offset() {
                return this.offset;
            }

            private boolean isDefinedAt(int i) {
                return i < this.seq().length() + this.offset() && i >= this.offset();
            }

            public IndexedSeq<Symbols.ClassSymbol> seq() {
                return this.seq;
            }

            @Override
            public Symbols.Symbol apply(int i) {
                return this.isDefinedAt(i) ? (Symbols.Symbol)this.seq().apply(i - this.offset()) : this.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoSymbol();
            }

            public Types.Type specificType(List<Types.Type> args, Seq<Types.Type> others) {
                int arity = args.length();
                return this.isDefinedAt(arity) ? this.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType(this.apply(arity), (Seq<Types.Type>)args.$plus$plus(others, List$.MODULE$.canBuildFrom())) : this.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoType();
            }

            public /* synthetic */ DefinitionsClass scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$$outer() {
                return (DefinitionsClass)this.$outer;
            }

            public VarArityClass(DefinitionsClass $outer, String name, int maxArity, int countFrom, Option<Symbols.ClassSymbol> init2) {
                this.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$name = name;
                this.offset = countFrom - Option$.MODULE$.option2Iterable(init2).size();
                Predef$ predef$ = Predef$.MODULE$;
                this.seq = ((TraversableOnce)((TraversableLike)RichInt$.MODULE$.to$extension0(countFrom, maxArity).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ VarArityClass $outer;

                    public final Symbols.ClassSymbol apply(int i) {
                        return ((Mirrors.RootsBase)this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getRequiredClass(new StringBuilder().append((Object)"scala.").append((Object)this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$VarArityClass$$name).append(BoxesRunTime.boxToInteger(i)).toString());
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus$colon(Option$.MODULE$.option2Iterable(init2), IndexedSeq$.MODULE$.canBuildFrom())).toVector();
            }
        }

        public final class RunDefinitions {
            private Symbols.TermSymbol StringAdd_$plus;
            private Symbols.TermSymbol StringContext_f;
            private Symbols.ClassSymbol ArrowAssocClass;
            private Symbols.Symbol Boxes_isNumberOrBool;
            private Symbols.Symbol Boxes_isNumber;
            private scala.collection.immutable.Map<Symbols.Symbol, Symbols.TermSymbol> boxMethod;
            private scala.collection.immutable.Map<Symbols.Symbol, Symbols.TermSymbol> unboxMethod;
            private Set<Symbols.Symbol> isUnbox;
            private Set<Symbols.Symbol> isBox;
            private Symbols.TermSymbol Boolean_and;
            private Symbols.TermSymbol Boolean_or;
            private Symbols.TermSymbol Boolean_not;
            private Symbols.TermSymbol Option_apply;
            private Symbols.TermSymbol List_apply;
            private scala.collection.immutable.Map<Symbols.Symbol, Symbols.Symbol> TagMaterializers;
            private Set<Symbols.Symbol> TagSymbols;
            private Symbols.Symbol Predef_conforms;
            private Symbols.TermSymbol Predef_classOf;
            private Symbols.TermSymbol Predef_implicitly;
            private Symbols.TermSymbol Predef_wrapRefArray;
            private Symbols.TermSymbol Predef_$qmark$qmark$qmark;
            private Symbols.TermSymbol arrayApplyMethod;
            private Symbols.TermSymbol arrayUpdateMethod;
            private Symbols.TermSymbol arrayLengthMethod;
            private Symbols.TermSymbol arrayCloneMethod;
            private Symbols.TermSymbol ensureAccessibleMethod;
            private Symbols.TermSymbol arrayClassMethod;
            private Symbols.TermSymbol traversableDropMethod;
            private Symbols.ClassSymbol GroupOfSpecializable;
            private Symbols.Symbol WeakTypeTagClass;
            private Symbols.Symbol WeakTypeTagModule;
            private Symbols.Symbol TypeTagClass;
            private Symbols.Symbol TypeTagModule;
            private Symbols.Symbol MacroContextUniverse;
            private Symbols.TermSymbol materializeClassTag;
            private Symbols.Symbol materializeWeakTypeTag;
            private Symbols.Symbol materializeTypeTag;
            private Symbols.ModuleSymbol experimentalModule;
            private Symbols.Symbol MacrosFeature;
            private Symbols.Symbol DynamicsFeature;
            private Symbols.Symbol PostfixOpsFeature;
            private Symbols.Symbol ReflectiveCallsFeature;
            private Symbols.Symbol ImplicitConversionsFeature;
            private Symbols.Symbol HigherKindsFeature;
            private Symbols.Symbol ExistentialsFeature;
            private Symbols.Symbol ApiUniverseReify;
            private Symbols.Symbol ReflectRuntimeUniverse;
            private Symbols.Symbol ReflectRuntimeCurrentMirror;
            private Symbols.Symbol TreesTreeType;
            private Symbols.TypeSymbol PartialManifestClass;
            private Set<Symbols.Symbol> ManifestSymbols;
            private Set<Symbols.Symbol> PolySigMethods;
            private Symbols.Symbol Scala_Java8_CompatPackage;
            private Symbols.Symbol[] Scala_Java8_CompatPackage_JFunction;
            private volatile Definitions$DefinitionsClass$RunDefinitions$TreeType$ TreeType$module;
            private volatile Definitions$DefinitionsClass$RunDefinitions$SubtreeType$ SubtreeType$module;
            private volatile Definitions$DefinitionsClass$RunDefinitions$ExprClassOf$ ExprClassOf$module;
            private volatile long bitmap$0;

            private Symbols.TermSymbol StringAdd_$plus$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 1L) == 0L) {
                        this.StringAdd_$plus = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.StringAddClass(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().PLUS());
                        this.bitmap$0 |= 1L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.StringAdd_$plus;
                }
            }

            private Symbols.TermSymbol StringContext_f$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 2L) == 0L) {
                        this.StringContext_f = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.StringContextClass(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().f());
                        this.bitmap$0 |= 2L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.StringContext_f;
                }
            }

            private Symbols.ClassSymbol ArrowAssocClass$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 4L) == 0L) {
                        this.ArrowAssocClass = DefinitionsClass.this.getMemberClass(DefinitionsClass.this.PredefModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TypeName().apply("ArrowAssoc"));
                        this.bitmap$0 |= 4L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ArrowAssocClass;
                }
            }

            private Symbols.Symbol Boxes_isNumberOrBool$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 8L) == 0L) {
                        this.Boxes_isNumberOrBool = DefinitionsClass.this.getDecl(DefinitionsClass.this.BoxesRunTimeClass(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().isBoxedNumberOrBoolean());
                        this.bitmap$0 |= 8L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Boxes_isNumberOrBool;
                }
            }

            private Symbols.Symbol Boxes_isNumber$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x10L) == 0L) {
                        this.Boxes_isNumber = DefinitionsClass.this.getDecl(DefinitionsClass.this.BoxesRunTimeClass(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().isBoxedNumber());
                        this.bitmap$0 |= 0x10L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Boxes_isNumber;
                }
            }

            private scala.collection.immutable.Map boxMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x20L) == 0L) {
                        this.boxMethod = DefinitionsClass.this.scala$reflect$internal$Definitions$$classesMap(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ RunDefinitions $outer;

                            public final Symbols.TermSymbol apply(Names.Name x) {
                                return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$valueCompanionMember(x, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().box());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        });
                        this.bitmap$0 |= 0x20L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.boxMethod;
                }
            }

            private scala.collection.immutable.Map unboxMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x40L) == 0L) {
                        this.unboxMethod = DefinitionsClass.this.scala$reflect$internal$Definitions$$classesMap(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ RunDefinitions $outer;

                            public final Symbols.TermSymbol apply(Names.Name x) {
                                return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$valueCompanionMember(x, this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().unbox());
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        });
                        this.bitmap$0 |= 0x40L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.unboxMethod;
                }
            }

            private Set isUnbox$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x80L) == 0L) {
                        this.isUnbox = this.unboxMethod().values().toSet();
                        this.bitmap$0 |= 0x80L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.isUnbox;
                }
            }

            private Set isBox$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x100L) == 0L) {
                        this.isBox = this.boxMethod().values().toSet();
                        this.bitmap$0 |= 0x100L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.isBox;
                }
            }

            private Symbols.TermSymbol Boolean_and$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x200L) == 0L) {
                        this.Boolean_and = DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().definitions().Boolean_and();
                        this.bitmap$0 |= 0x200L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Boolean_and;
                }
            }

            private Symbols.TermSymbol Boolean_or$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x400L) == 0L) {
                        this.Boolean_or = DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().definitions().Boolean_or();
                        this.bitmap$0 |= 0x400L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Boolean_or;
                }
            }

            private Symbols.TermSymbol Boolean_not$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x800L) == 0L) {
                        this.Boolean_not = DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().definitions().Boolean_not();
                        this.bitmap$0 |= 0x800L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Boolean_not;
                }
            }

            private Symbols.TermSymbol Option_apply$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x1000L) == 0L) {
                        this.Option_apply = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.OptionModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().apply());
                        this.bitmap$0 |= 0x1000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Option_apply;
                }
            }

            private Symbols.TermSymbol List_apply$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x2000L) == 0L) {
                        this.List_apply = DefinitionsClass.this.List_apply();
                        this.bitmap$0 |= 0x2000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.List_apply;
                }
            }

            private scala.collection.immutable.Map TagMaterializers$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x4000L) == 0L) {
                        Tuple2[] tuple2Array = new Tuple2[3];
                        Symbols.TermSymbol termSymbol = this.materializeClassTag();
                        Symbols.ClassSymbol classSymbol = Predef$.MODULE$.ArrowAssoc(DefinitionsClass.this.ClassTagClass());
                        Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
                        tuple2Array[0] = new Tuple2<Symbols.ClassSymbol, Symbols.TermSymbol>(classSymbol, termSymbol);
                        Symbols.Symbol symbol = this.materializeWeakTypeTag();
                        Symbols.Symbol symbol2 = Predef$.MODULE$.ArrowAssoc(this.WeakTypeTagClass());
                        Predef$ArrowAssoc$ predef$ArrowAssoc$2 = Predef$ArrowAssoc$.MODULE$;
                        tuple2Array[1] = new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol2, symbol);
                        Symbols.Symbol symbol3 = this.materializeTypeTag();
                        Symbols.Symbol symbol4 = Predef$.MODULE$.ArrowAssoc(this.TypeTagClass());
                        Predef$ArrowAssoc$ predef$ArrowAssoc$3 = Predef$ArrowAssoc$.MODULE$;
                        tuple2Array[2] = new Tuple2<Symbols.Symbol, Symbols.Symbol>(symbol4, symbol3);
                        this.TagMaterializers = (scala.collection.immutable.Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])tuple2Array));
                        this.bitmap$0 |= 0x4000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TagMaterializers;
                }
            }

            private Set TagSymbols$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x8000L) == 0L) {
                        this.TagSymbols = this.TagMaterializers().keySet();
                        this.bitmap$0 |= 0x8000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TagSymbols;
                }
            }

            private Symbols.Symbol Predef_conforms$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x10000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.getMemberIfDefined(DefinitionsClass.this.PredefModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().conforms());
                        if (symbol2 != symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            RunDefinitions runDefinitions4 = this;
                            Names$TermName$ names$TermName$ = runDefinitions4.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TermName();
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberMethod(runDefinitions3.DefinitionsClass.this.PredefModule(), names$TermName$.scala$reflect$internal$Names$TermName$$$outer().newTermName("conforms"));
                        }
                        this.Predef_conforms = symbol;
                        this.bitmap$0 |= 0x10000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Predef_conforms;
                }
            }

            private Symbols.TermSymbol Predef_classOf$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x20000L) == 0L) {
                        this.Predef_classOf = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.PredefModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().classOf());
                        this.bitmap$0 |= 0x20000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Predef_classOf;
                }
            }

            private Symbols.TermSymbol Predef_implicitly$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x40000L) == 0L) {
                        this.Predef_implicitly = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.PredefModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().implicitly());
                        this.bitmap$0 |= 0x40000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Predef_implicitly;
                }
            }

            private Symbols.TermSymbol Predef_wrapRefArray$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x80000L) == 0L) {
                        this.Predef_wrapRefArray = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.PredefModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().wrapRefArray());
                        this.bitmap$0 |= 0x80000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Predef_wrapRefArray;
                }
            }

            private Symbols.TermSymbol Predef_$qmark$qmark$qmark$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x100000L) == 0L) {
                        this.Predef_$qmark$qmark$qmark = DefinitionsClass.this.Predef_$qmark$qmark$qmark();
                        this.bitmap$0 |= 0x100000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Predef_$qmark$qmark$qmark;
                }
            }

            private Symbols.TermSymbol arrayApplyMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x200000L) == 0L) {
                        this.arrayApplyMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().array_apply());
                        this.bitmap$0 |= 0x200000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.arrayApplyMethod;
                }
            }

            private Symbols.TermSymbol arrayUpdateMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x400000L) == 0L) {
                        this.arrayUpdateMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().array_update());
                        this.bitmap$0 |= 0x400000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.arrayUpdateMethod;
                }
            }

            private Symbols.TermSymbol arrayLengthMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x800000L) == 0L) {
                        this.arrayLengthMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().array_length());
                        this.bitmap$0 |= 0x800000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.arrayLengthMethod;
                }
            }

            private Symbols.TermSymbol arrayCloneMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x1000000L) == 0L) {
                        this.arrayCloneMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().array_clone());
                        this.bitmap$0 |= 0x1000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.arrayCloneMethod;
                }
            }

            private Symbols.TermSymbol ensureAccessibleMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x2000000L) == 0L) {
                        this.ensureAccessibleMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().ensureAccessible());
                        this.bitmap$0 |= 0x2000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ensureAccessibleMethod;
                }
            }

            private Symbols.TermSymbol arrayClassMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x4000000L) == 0L) {
                        this.arrayClassMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().arrayClass());
                        this.bitmap$0 |= 0x4000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.arrayClassMethod;
                }
            }

            private Symbols.TermSymbol traversableDropMethod$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x8000000L) == 0L) {
                        this.traversableDropMethod = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ScalaRunTimeModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().drop());
                        this.bitmap$0 |= 0x8000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.traversableDropMethod;
                }
            }

            private Symbols.ClassSymbol GroupOfSpecializable$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x10000000L) == 0L) {
                        this.GroupOfSpecializable = DefinitionsClass.this.getMemberClass(DefinitionsClass.this.SpecializableModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Group());
                        this.bitmap$0 |= 0x10000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.GroupOfSpecializable;
                }
            }

            private Symbols.Symbol WeakTypeTagClass$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x20000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.TypeTagsClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberClass(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().WeakTypeTag());
                        }
                        this.WeakTypeTagClass = symbol;
                        this.bitmap$0 |= 0x20000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.WeakTypeTagClass;
                }
            }

            private Symbols.Symbol WeakTypeTagModule$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x40000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.TypeTagsClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberModule(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().WeakTypeTag());
                        }
                        this.WeakTypeTagModule = symbol;
                        this.bitmap$0 |= 0x40000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.WeakTypeTagModule;
                }
            }

            private Symbols.Symbol TypeTagClass$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x80000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.TypeTagsClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberClass(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().TypeTag());
                        }
                        this.TypeTagClass = symbol;
                        this.bitmap$0 |= 0x80000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TypeTagClass;
                }
            }

            private Symbols.Symbol TypeTagModule$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x100000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.TypeTagsClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberModule(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().TypeTag());
                        }
                        this.TypeTagModule = symbol;
                        this.bitmap$0 |= 0x100000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TypeTagModule;
                }
            }

            private Symbols.Symbol MacroContextUniverse$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x200000000L) == 0L) {
                        this.MacroContextUniverse = DefinitionsClass.this.MacroContextUniverse();
                        this.bitmap$0 |= 0x200000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.MacroContextUniverse;
                }
            }

            private Symbols.TermSymbol materializeClassTag$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x400000000L) == 0L) {
                        this.materializeClassTag = DefinitionsClass.this.getMemberMethod(DefinitionsClass.this.ReflectPackage(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().materializeClassTag());
                        this.bitmap$0 |= 0x400000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.materializeClassTag;
                }
            }

            private Symbols.Symbol materializeWeakTypeTag$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x800000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.ReflectApiPackage();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberMethod(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().materializeWeakTypeTag());
                        }
                        this.materializeWeakTypeTag = symbol;
                        this.bitmap$0 |= 0x800000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.materializeWeakTypeTag;
                }
            }

            private Symbols.Symbol materializeTypeTag$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x1000000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.ReflectApiPackage();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberMethod(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().materializeTypeTag());
                        }
                        this.materializeTypeTag = symbol;
                        this.bitmap$0 |= 0x1000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.materializeTypeTag;
                }
            }

            private Symbols.ModuleSymbol experimentalModule$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x2000000000L) == 0L) {
                        this.experimentalModule = DefinitionsClass.this.getMemberModule(DefinitionsClass.this.languageFeatureModule(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().experimental());
                        this.bitmap$0 |= 0x2000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.experimentalModule;
                }
            }

            private Symbols.Symbol MacrosFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x4000000000L) == 0L) {
                        this.MacrosFeature = DefinitionsClass.this.getLanguageFeature("macros", this.experimentalModule());
                        this.bitmap$0 |= 0x4000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.MacrosFeature;
                }
            }

            private Symbols.Symbol DynamicsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x8000000000L) == 0L) {
                        this.DynamicsFeature = DefinitionsClass.this.getLanguageFeature("dynamics", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x8000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.DynamicsFeature;
                }
            }

            private Symbols.Symbol PostfixOpsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x10000000000L) == 0L) {
                        this.PostfixOpsFeature = DefinitionsClass.this.getLanguageFeature("postfixOps", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x10000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.PostfixOpsFeature;
                }
            }

            private Symbols.Symbol ReflectiveCallsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x20000000000L) == 0L) {
                        this.ReflectiveCallsFeature = DefinitionsClass.this.getLanguageFeature("reflectiveCalls", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x20000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ReflectiveCallsFeature;
                }
            }

            private Symbols.Symbol ImplicitConversionsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x40000000000L) == 0L) {
                        this.ImplicitConversionsFeature = DefinitionsClass.this.getLanguageFeature("implicitConversions", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x40000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ImplicitConversionsFeature;
                }
            }

            private Symbols.Symbol HigherKindsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x80000000000L) == 0L) {
                        this.HigherKindsFeature = DefinitionsClass.this.getLanguageFeature("higherKinds", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x80000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.HigherKindsFeature;
                }
            }

            private Symbols.Symbol ExistentialsFeature$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x100000000000L) == 0L) {
                        this.ExistentialsFeature = DefinitionsClass.this.getLanguageFeature("existentials", DefinitionsClass.this.getLanguageFeature$default$2());
                        this.bitmap$0 |= 0x100000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ExistentialsFeature;
                }
            }

            private Symbols.Symbol ApiUniverseReify$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x200000000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.ApiUniverseClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getMemberMethod(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().reify());
                        }
                        this.ApiUniverseReify = symbol;
                        this.bitmap$0 |= 0x200000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ApiUniverseReify;
                }
            }

            private Symbols.Symbol ReflectRuntimeUniverse$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x400000000000L) == 0L) {
                        this.ReflectRuntimeUniverse = DefinitionsClass.this.ReflectRuntimeUniverse();
                        this.bitmap$0 |= 0x400000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ReflectRuntimeUniverse;
                }
            }

            private Symbols.Symbol ReflectRuntimeCurrentMirror$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x800000000000L) == 0L) {
                        this.ReflectRuntimeCurrentMirror = DefinitionsClass.this.ReflectRuntimeCurrentMirror();
                        this.bitmap$0 |= 0x800000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ReflectRuntimeCurrentMirror;
                }
            }

            private Symbols.Symbol TreesTreeType$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x1000000000000L) == 0L) {
                        Symbols.Symbol symbol;
                        Symbols.Symbol symbol2 = DefinitionsClass.this.TreesClass();
                        if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                            symbol = symbol2;
                        } else {
                            Symbols.Symbol symbol3 = symbol2;
                            RunDefinitions runDefinitions2 = this;
                            RunDefinitions runDefinitions3 = this;
                            symbol = runDefinitions2.DefinitionsClass.this.getTypeMember(symbol3, runDefinitions3.DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Tree());
                        }
                        this.TreesTreeType = symbol;
                        this.bitmap$0 |= 0x1000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TreesTreeType;
                }
            }

            private Definitions$DefinitionsClass$RunDefinitions$TreeType$ TreeType$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if (this.TreeType$module == null) {
                        this.TreeType$module = new Definitions$DefinitionsClass$RunDefinitions$TreeType$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.TreeType$module;
                }
            }

            private Definitions$DefinitionsClass$RunDefinitions$SubtreeType$ SubtreeType$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if (this.SubtreeType$module == null) {
                        this.SubtreeType$module = new Definitions$DefinitionsClass$RunDefinitions$SubtreeType$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.SubtreeType$module;
                }
            }

            private Definitions$DefinitionsClass$RunDefinitions$ExprClassOf$ ExprClassOf$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if (this.ExprClassOf$module == null) {
                        this.ExprClassOf$module = new Definitions$DefinitionsClass$RunDefinitions$ExprClassOf$(this);
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ExprClassOf$module;
                }
            }

            private Symbols.TypeSymbol PartialManifestClass$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x2000000000000L) == 0L) {
                        this.PartialManifestClass = DefinitionsClass.this.getTypeMember(DefinitionsClass.this.ReflectPackage(), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().ClassManifest());
                        this.bitmap$0 |= 0x2000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.PartialManifestClass;
                }
            }

            private Set ManifestSymbols$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x4000000000000L) == 0L) {
                        this.ManifestSymbols = (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{this.PartialManifestClass(), DefinitionsClass.this.FullManifestClass(), DefinitionsClass.this.OptManifestClass()}));
                        this.bitmap$0 |= 0x4000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.ManifestSymbols;
                }
            }

            private Set PolySigMethods$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x8000000000000L) == 0L) {
                        this.PolySigMethods = (Set)((TraversableLike)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new Symbols.Symbol[]{DefinitionsClass.this.MethodHandle().info().decl(DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().Invoke()), DefinitionsClass.this.MethodHandle().info().decl(DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().sn().InvokeExact())}))).filter(new Serializable(this){
                            public static final long serialVersionUID = 0L;

                            public final boolean apply(Symbols.Symbol x$41) {
                                return x$41.exists();
                            }
                        });
                        this.bitmap$0 |= 0x8000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.PolySigMethods;
                }
            }

            private Symbols.Symbol Scala_Java8_CompatPackage$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x10000000000000L) == 0L) {
                        this.Scala_Java8_CompatPackage = ((Mirrors.RootsBase)DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().rootMirror()).getPackageIfDefined((Names.TermName)DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().stringToTermName("scala.compat.java8"));
                        this.bitmap$0 |= 0x10000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Scala_Java8_CompatPackage;
                }
            }

            private Symbols.Symbol[] Scala_Java8_CompatPackage_JFunction$lzycompute() {
                RunDefinitions runDefinitions = this;
                synchronized (runDefinitions) {
                    if ((this.bitmap$0 & 0x20000000000000L) == 0L) {
                        Predef$ predef$ = Predef$.MODULE$;
                        this.Scala_Java8_CompatPackage_JFunction = (Symbols.Symbol[])Predef$.MODULE$.intArrayOps((int[])RichInt$.MODULE$.to$extension0(0, DefinitionsClass.this.MaxFunctionArity()).toArray(ClassTag$.MODULE$.Int())).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ RunDefinitions $outer;

                            public final Symbols.Symbol apply(int i) {
                                return this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$$outer().getMemberIfDefined(this.$outer.Scala_Java8_CompatPackage().moduleClass(), this.$outer.scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().TypeName().apply(new StringBuilder().append((Object)"JFunction").append(BoxesRunTime.boxToInteger(i)).toString()));
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, Array$.MODULE$.canBuildFrom(DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().SymbolTag()));
                        this.bitmap$0 |= 0x20000000000000L;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.Scala_Java8_CompatPackage_JFunction;
                }
            }

            public Symbols.TermSymbol StringAdd_$plus() {
                return (this.bitmap$0 & 1L) == 0L ? this.StringAdd_$plus$lzycompute() : this.StringAdd_$plus;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean isStringAddition(Symbols.Symbol sym) {
                Symbols.Symbol symbol = sym;
                Symbols.MethodSymbol methodSymbol = DefinitionsClass.this.String_$plus();
                if (symbol == null) {
                    if (methodSymbol == null) return true;
                } else if (symbol.equals(methodSymbol)) return true;
                Symbols.Symbol symbol2 = sym;
                Symbols.TermSymbol termSymbol = this.StringAdd_$plus();
                if (symbol2 != null) {
                    if (!symbol2.equals(termSymbol)) return false;
                    return true;
                }
                if (termSymbol == null) return true;
                return false;
            }

            public Symbols.TermSymbol StringContext_f() {
                return (this.bitmap$0 & 2L) == 0L ? this.StringContext_f$lzycompute() : this.StringContext_f;
            }

            public Symbols.ClassSymbol ArrowAssocClass() {
                return (this.bitmap$0 & 4L) == 0L ? this.ArrowAssocClass$lzycompute() : this.ArrowAssocClass;
            }

            public boolean isArrowAssoc(Symbols.Symbol sym) {
                Symbols.Symbol symbol = sym.owner();
                Symbols.ClassSymbol classSymbol = this.ArrowAssocClass();
                return !(symbol != null ? !symbol.equals(classSymbol) : classSymbol != null);
            }

            public Symbols.Symbol Boxes_isNumberOrBool() {
                return (this.bitmap$0 & 8L) == 0L ? this.Boxes_isNumberOrBool$lzycompute() : this.Boxes_isNumberOrBool;
            }

            public Symbols.Symbol Boxes_isNumber() {
                return (this.bitmap$0 & 0x10L) == 0L ? this.Boxes_isNumber$lzycompute() : this.Boxes_isNumber;
            }

            private Symbols.ModuleSymbol valueClassCompanion(Names.TermName name) {
                Symbols.Symbol symbol = DefinitionsClass.this.getMember(DefinitionsClass.this.ScalaPackageClass(), name);
                if (symbol instanceof Symbols.ModuleSymbol) {
                    Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)symbol;
                    return moduleSymbol;
                }
                throw DefinitionsClass.this.scala$reflect$internal$Definitions$$catastrophicFailure();
            }

            public Symbols.TermSymbol scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$valueCompanionMember(Names.Name className, Names.TermName methodName) {
                return DefinitionsClass.this.getMemberMethod(this.valueClassCompanion(className.toTermName()).moduleClass(), methodName);
            }

            public scala.collection.immutable.Map<Symbols.Symbol, Symbols.TermSymbol> boxMethod() {
                return (this.bitmap$0 & 0x20L) == 0L ? this.boxMethod$lzycompute() : this.boxMethod;
            }

            public scala.collection.immutable.Map<Symbols.Symbol, Symbols.TermSymbol> unboxMethod() {
                return (this.bitmap$0 & 0x40L) == 0L ? this.unboxMethod$lzycompute() : this.unboxMethod;
            }

            public Set<Symbols.Symbol> isUnbox() {
                return (this.bitmap$0 & 0x80L) == 0L ? this.isUnbox$lzycompute() : this.isUnbox;
            }

            public Set<Symbols.Symbol> isBox() {
                return (this.bitmap$0 & 0x100L) == 0L ? this.isBox$lzycompute() : this.isBox;
            }

            public Symbols.TermSymbol Boolean_and() {
                return (this.bitmap$0 & 0x200L) == 0L ? this.Boolean_and$lzycompute() : this.Boolean_and;
            }

            public Symbols.TermSymbol Boolean_or() {
                return (this.bitmap$0 & 0x400L) == 0L ? this.Boolean_or$lzycompute() : this.Boolean_or;
            }

            public Symbols.TermSymbol Boolean_not() {
                return (this.bitmap$0 & 0x800L) == 0L ? this.Boolean_not$lzycompute() : this.Boolean_not;
            }

            public Symbols.TermSymbol Option_apply() {
                return (this.bitmap$0 & 0x1000L) == 0L ? this.Option_apply$lzycompute() : this.Option_apply;
            }

            public Symbols.TermSymbol List_apply() {
                return (this.bitmap$0 & 0x2000L) == 0L ? this.List_apply$lzycompute() : this.List_apply;
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean isListApply(Symbols.Symbol sym) {
                if (!sym.isInitialized()) return false;
                if (!DefinitionsClass.this.ListModule().hasCompleteInfo()) return false;
                Symbols.Symbol symbol = sym;
                Symbols.TermSymbol termSymbol = this.List_apply();
                if (symbol != null) {
                    if (!symbol.equals(termSymbol)) return false;
                    return true;
                }
                if (termSymbol == null) return true;
                return false;
            }

            public boolean isPredefClassOf(Symbols.Symbol sym) {
                boolean bl;
                if (DefinitionsClass.this.PredefModule().hasCompleteInfo()) {
                    Symbols.Symbol symbol = sym;
                    Symbols.TermSymbol termSymbol = this.Predef_classOf();
                    bl = !(symbol != null ? !symbol.equals(termSymbol) : termSymbol != null);
                } else {
                    bl = DefinitionsClass.this.isPredefMemberNamed(sym, DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().nme().classOf());
                }
                return bl;
            }

            public scala.collection.immutable.Map<Symbols.Symbol, Symbols.Symbol> TagMaterializers() {
                return (this.bitmap$0 & 0x4000L) == 0L ? this.TagMaterializers$lzycompute() : this.TagMaterializers;
            }

            public Set<Symbols.Symbol> TagSymbols() {
                return (this.bitmap$0 & 0x8000L) == 0L ? this.TagSymbols$lzycompute() : this.TagSymbols;
            }

            public Symbols.Symbol Predef_conforms() {
                return (this.bitmap$0 & 0x10000L) == 0L ? this.Predef_conforms$lzycompute() : this.Predef_conforms;
            }

            public Symbols.TermSymbol Predef_classOf() {
                return (this.bitmap$0 & 0x20000L) == 0L ? this.Predef_classOf$lzycompute() : this.Predef_classOf;
            }

            public Symbols.TermSymbol Predef_implicitly() {
                return (this.bitmap$0 & 0x40000L) == 0L ? this.Predef_implicitly$lzycompute() : this.Predef_implicitly;
            }

            public Symbols.TermSymbol Predef_wrapRefArray() {
                return (this.bitmap$0 & 0x80000L) == 0L ? this.Predef_wrapRefArray$lzycompute() : this.Predef_wrapRefArray;
            }

            public Symbols.TermSymbol Predef_$qmark$qmark$qmark() {
                return (this.bitmap$0 & 0x100000L) == 0L ? this.Predef_$qmark$qmark$qmark$lzycompute() : this.Predef_$qmark$qmark$qmark;
            }

            public Symbols.TermSymbol arrayApplyMethod() {
                return (this.bitmap$0 & 0x200000L) == 0L ? this.arrayApplyMethod$lzycompute() : this.arrayApplyMethod;
            }

            public Symbols.TermSymbol arrayUpdateMethod() {
                return (this.bitmap$0 & 0x400000L) == 0L ? this.arrayUpdateMethod$lzycompute() : this.arrayUpdateMethod;
            }

            public Symbols.TermSymbol arrayLengthMethod() {
                return (this.bitmap$0 & 0x800000L) == 0L ? this.arrayLengthMethod$lzycompute() : this.arrayLengthMethod;
            }

            public Symbols.TermSymbol arrayCloneMethod() {
                return (this.bitmap$0 & 0x1000000L) == 0L ? this.arrayCloneMethod$lzycompute() : this.arrayCloneMethod;
            }

            public Symbols.TermSymbol ensureAccessibleMethod() {
                return (this.bitmap$0 & 0x2000000L) == 0L ? this.ensureAccessibleMethod$lzycompute() : this.ensureAccessibleMethod;
            }

            public Symbols.TermSymbol arrayClassMethod() {
                return (this.bitmap$0 & 0x4000000L) == 0L ? this.arrayClassMethod$lzycompute() : this.arrayClassMethod;
            }

            public Symbols.TermSymbol traversableDropMethod() {
                return (this.bitmap$0 & 0x8000000L) == 0L ? this.traversableDropMethod$lzycompute() : this.traversableDropMethod;
            }

            public Symbols.ClassSymbol GroupOfSpecializable() {
                return (this.bitmap$0 & 0x10000000L) == 0L ? this.GroupOfSpecializable$lzycompute() : this.GroupOfSpecializable;
            }

            public Symbols.Symbol WeakTypeTagClass() {
                return (this.bitmap$0 & 0x20000000L) == 0L ? this.WeakTypeTagClass$lzycompute() : this.WeakTypeTagClass;
            }

            public Symbols.Symbol WeakTypeTagModule() {
                return (this.bitmap$0 & 0x40000000L) == 0L ? this.WeakTypeTagModule$lzycompute() : this.WeakTypeTagModule;
            }

            public Symbols.Symbol TypeTagClass() {
                return (this.bitmap$0 & 0x80000000L) == 0L ? this.TypeTagClass$lzycompute() : this.TypeTagClass;
            }

            public Symbols.Symbol TypeTagModule() {
                return (this.bitmap$0 & 0x100000000L) == 0L ? this.TypeTagModule$lzycompute() : this.TypeTagModule;
            }

            public Symbols.Symbol MacroContextUniverse() {
                return (this.bitmap$0 & 0x200000000L) == 0L ? this.MacroContextUniverse$lzycompute() : this.MacroContextUniverse;
            }

            public Symbols.TermSymbol materializeClassTag() {
                return (this.bitmap$0 & 0x400000000L) == 0L ? this.materializeClassTag$lzycompute() : this.materializeClassTag;
            }

            public Symbols.Symbol materializeWeakTypeTag() {
                return (this.bitmap$0 & 0x800000000L) == 0L ? this.materializeWeakTypeTag$lzycompute() : this.materializeWeakTypeTag;
            }

            public Symbols.Symbol materializeTypeTag() {
                return (this.bitmap$0 & 0x1000000000L) == 0L ? this.materializeTypeTag$lzycompute() : this.materializeTypeTag;
            }

            public Symbols.ModuleSymbol experimentalModule() {
                return (this.bitmap$0 & 0x2000000000L) == 0L ? this.experimentalModule$lzycompute() : this.experimentalModule;
            }

            public Symbols.Symbol MacrosFeature() {
                return (this.bitmap$0 & 0x4000000000L) == 0L ? this.MacrosFeature$lzycompute() : this.MacrosFeature;
            }

            public Symbols.Symbol DynamicsFeature() {
                return (this.bitmap$0 & 0x8000000000L) == 0L ? this.DynamicsFeature$lzycompute() : this.DynamicsFeature;
            }

            public Symbols.Symbol PostfixOpsFeature() {
                return (this.bitmap$0 & 0x10000000000L) == 0L ? this.PostfixOpsFeature$lzycompute() : this.PostfixOpsFeature;
            }

            public Symbols.Symbol ReflectiveCallsFeature() {
                return (this.bitmap$0 & 0x20000000000L) == 0L ? this.ReflectiveCallsFeature$lzycompute() : this.ReflectiveCallsFeature;
            }

            public Symbols.Symbol ImplicitConversionsFeature() {
                return (this.bitmap$0 & 0x40000000000L) == 0L ? this.ImplicitConversionsFeature$lzycompute() : this.ImplicitConversionsFeature;
            }

            public Symbols.Symbol HigherKindsFeature() {
                return (this.bitmap$0 & 0x80000000000L) == 0L ? this.HigherKindsFeature$lzycompute() : this.HigherKindsFeature;
            }

            public Symbols.Symbol ExistentialsFeature() {
                return (this.bitmap$0 & 0x100000000000L) == 0L ? this.ExistentialsFeature$lzycompute() : this.ExistentialsFeature;
            }

            public Symbols.Symbol ApiUniverseReify() {
                return (this.bitmap$0 & 0x200000000000L) == 0L ? this.ApiUniverseReify$lzycompute() : this.ApiUniverseReify;
            }

            public Symbols.Symbol ReflectRuntimeUniverse() {
                return (this.bitmap$0 & 0x400000000000L) == 0L ? this.ReflectRuntimeUniverse$lzycompute() : this.ReflectRuntimeUniverse;
            }

            public Symbols.Symbol ReflectRuntimeCurrentMirror() {
                return (this.bitmap$0 & 0x800000000000L) == 0L ? this.ReflectRuntimeCurrentMirror$lzycompute() : this.ReflectRuntimeCurrentMirror;
            }

            public Symbols.Symbol TreesTreeType() {
                return (this.bitmap$0 & 0x1000000000000L) == 0L ? this.TreesTreeType$lzycompute() : this.TreesTreeType;
            }

            public Definitions$DefinitionsClass$RunDefinitions$TreeType$ TreeType() {
                return this.TreeType$module == null ? this.TreeType$lzycompute() : this.TreeType$module;
            }

            public Definitions$DefinitionsClass$RunDefinitions$SubtreeType$ SubtreeType() {
                return this.SubtreeType$module == null ? this.SubtreeType$lzycompute() : this.SubtreeType$module;
            }

            public Definitions$DefinitionsClass$RunDefinitions$ExprClassOf$ ExprClassOf() {
                return this.ExprClassOf$module == null ? this.ExprClassOf$lzycompute() : this.ExprClassOf$module;
            }

            public Symbols.TypeSymbol PartialManifestClass() {
                return (this.bitmap$0 & 0x2000000000000L) == 0L ? this.PartialManifestClass$lzycompute() : this.PartialManifestClass;
            }

            public Set<Symbols.Symbol> ManifestSymbols() {
                return (this.bitmap$0 & 0x4000000000000L) == 0L ? this.ManifestSymbols$lzycompute() : this.ManifestSymbols;
            }

            public boolean isPolymorphicSignature(Symbols.Symbol sym) {
                return this.PolySigMethods().apply(sym);
            }

            private Set<Symbols.Symbol> PolySigMethods() {
                return (this.bitmap$0 & 0x8000000000000L) == 0L ? this.PolySigMethods$lzycompute() : this.PolySigMethods;
            }

            public Symbols.Symbol Scala_Java8_CompatPackage() {
                return (this.bitmap$0 & 0x10000000000000L) == 0L ? this.Scala_Java8_CompatPackage$lzycompute() : this.Scala_Java8_CompatPackage;
            }

            public Symbols.Symbol[] Scala_Java8_CompatPackage_JFunction() {
                return (this.bitmap$0 & 0x20000000000000L) == 0L ? this.Scala_Java8_CompatPackage_JFunction$lzycompute() : this.Scala_Java8_CompatPackage_JFunction;
            }

            public /* synthetic */ DefinitionsClass scala$reflect$internal$Definitions$DefinitionsClass$RunDefinitions$$$outer() {
                return DefinitionsClass.this;
            }

            public RunDefinitions() {
                if (DefinitionsClass.this == null) {
                    throw null;
                }
            }
        }

        public abstract class BottomClassSymbol
        extends Symbols.ClassSymbol {
            @Override
            public final boolean isBottomClass() {
                return true;
            }

            @Override
            public final boolean isThreadsafe(Symbols.SymbolOps purpose) {
                return true;
            }

            public /* synthetic */ DefinitionsClass scala$reflect$internal$Definitions$DefinitionsClass$BottomClassSymbol$$$outer() {
                return DefinitionsClass.this;
            }

            public BottomClassSymbol(Names.TypeName name, Symbols.Symbol parent) {
                if (DefinitionsClass.this == null) {
                    throw null;
                }
                super(DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), (Symbols.Symbol)DefinitionsClass.this.ScalaPackageClass(), (Position)DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().NoPosition(), name);
                this.initFlags(40L);
                this.setInfoAndEnter(new Types.ClassInfoType(DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer(), (List<Types.Type>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{parent.tpe()})), DefinitionsClass.this.scala$reflect$internal$Definitions$DefinitionsClass$$$outer().newScope(), this));
                this.markAllCompleted();
                Predef$ predef$ = Predef$.MODULE$;
            }
        }

        public class UniverseDependentTypes {
            private final Trees.Tree universe;
            private Types.Type nameType;
            private Types.Type modsType;
            private Types.Type flagsType;
            private Types.Type symbolType;
            private Types.Type treeType;
            private Types.Type caseDefType;
            private Types.Type liftableType;
            private Types.Type unliftableType;
            private Types.Type iterableTreeType;
            private Types.Type listTreeType;
            private Types.Type listListTreeType;
            public final /* synthetic */ DefinitionsClass $outer;
            private volatile int bitmap$0;

            private Types.Type nameType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 1) == 0) {
                        this.nameType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Name());
                        this.bitmap$0 |= 1;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.nameType;
                }
            }

            private Types.Type modsType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 2) == 0) {
                        this.modsType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Modifiers());
                        this.bitmap$0 |= 2;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.modsType;
                }
            }

            private Types.Type flagsType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 4) == 0) {
                        this.flagsType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().FlagSet());
                        this.bitmap$0 |= 4;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.flagsType;
                }
            }

            private Types.Type symbolType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 8) == 0) {
                        this.symbolType = this.universeMemberType((Names.TypeName)this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Symbol());
                        this.bitmap$0 |= 8;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.symbolType;
                }
            }

            private Types.Type treeType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x10) == 0) {
                        this.treeType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Tree());
                        this.bitmap$0 |= 0x10;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.treeType;
                }
            }

            private Types.Type caseDefType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x20) == 0) {
                        this.caseDefType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().CaseDef());
                        this.bitmap$0 |= 0x20;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.caseDefType;
                }
            }

            private Types.Type liftableType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x40) == 0) {
                        this.liftableType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Liftable());
                        this.bitmap$0 |= 0x40;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.liftableType;
                }
            }

            private Types.Type unliftableType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x80) == 0) {
                        this.unliftableType = this.universeMemberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().tpnme().Unliftable());
                        this.bitmap$0 |= 0x80;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.unliftableType;
                }
            }

            private Types.Type iterableTreeType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x100) == 0) {
                        this.iterableTreeType = this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().IterableClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.treeType()}));
                        this.bitmap$0 |= 0x100;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.iterableTreeType;
                }
            }

            private Types.Type listTreeType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x200) == 0) {
                        this.listTreeType = this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().ListClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.treeType()}));
                        this.bitmap$0 |= 0x200;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.listTreeType;
                }
            }

            private Types.Type listListTreeType$lzycompute() {
                UniverseDependentTypes universeDependentTypes = this;
                synchronized (universeDependentTypes) {
                    if ((this.bitmap$0 & 0x400) == 0) {
                        this.listListTreeType = this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().scala$reflect$internal$Definitions$DefinitionsClass$$$outer().appliedType((Symbols.Symbol)this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().ListClass(), Predef$.MODULE$.wrapRefArray((Object[])new Types.Type[]{this.listTreeType()}));
                        this.bitmap$0 |= 0x400;
                    }
                    // ** MonitorExit[this] (shouldn't be in output)
                    return this.listListTreeType;
                }
            }

            public Types.Type nameType() {
                return (this.bitmap$0 & 1) == 0 ? this.nameType$lzycompute() : this.nameType;
            }

            public Types.Type modsType() {
                return (this.bitmap$0 & 2) == 0 ? this.modsType$lzycompute() : this.modsType;
            }

            public Types.Type flagsType() {
                return (this.bitmap$0 & 4) == 0 ? this.flagsType$lzycompute() : this.flagsType;
            }

            public Types.Type symbolType() {
                return (this.bitmap$0 & 8) == 0 ? this.symbolType$lzycompute() : this.symbolType;
            }

            public Types.Type treeType() {
                return (this.bitmap$0 & 0x10) == 0 ? this.treeType$lzycompute() : this.treeType;
            }

            public Types.Type caseDefType() {
                return (this.bitmap$0 & 0x20) == 0 ? this.caseDefType$lzycompute() : this.caseDefType;
            }

            public Types.Type liftableType() {
                return (this.bitmap$0 & 0x40) == 0 ? this.liftableType$lzycompute() : this.liftableType;
            }

            public Types.Type unliftableType() {
                return (this.bitmap$0 & 0x80) == 0 ? this.unliftableType$lzycompute() : this.unliftableType;
            }

            public Types.Type iterableTreeType() {
                return (this.bitmap$0 & 0x100) == 0 ? this.iterableTreeType$lzycompute() : this.iterableTreeType;
            }

            public Types.Type listTreeType() {
                return (this.bitmap$0 & 0x200) == 0 ? this.listTreeType$lzycompute() : this.listTreeType;
            }

            public Types.Type listListTreeType() {
                return (this.bitmap$0 & 0x400) == 0 ? this.listListTreeType$lzycompute() : this.listListTreeType;
            }

            public Types.Type universeMemberType(Names.TypeName name) {
                return this.universe.tpe().memberType(this.scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer().getTypeMember(this.universe.symbol(), name));
            }

            public /* synthetic */ DefinitionsClass scala$reflect$internal$Definitions$DefinitionsClass$UniverseDependentTypes$$$outer() {
                return this.$outer;
            }

            public UniverseDependentTypes(DefinitionsClass $outer, Trees.Tree universe) {
                this.universe = universe;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }
    }

    public interface ValueClassDefinitions {
        public void scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight_$eq(scala.collection.immutable.Map var1);

        public void scala$reflect$internal$Definitions$ValueClassDefinitions$_setter_$scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag_$eq(scala.collection.immutable.Map var1);

        public scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToWeight();

        public scala.collection.immutable.Map<Names.Name, Object> scala$reflect$internal$Definitions$ValueClassDefinitions$$nameToTag();

        public Nothing$ scala$reflect$internal$Definitions$$catastrophicFailure();

        public <T> scala.collection.immutable.Map<Symbols.Symbol, T> scala$reflect$internal$Definitions$$classesMap(Function1<Names.Name, T> var1);

        public scala.collection.immutable.Map<Symbols.Symbol, Object> abbrvTag();

        public scala.collection.immutable.Map<Symbols.Symbol, Object> numericWeight();

        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ModuleSymbol> boxedModule();

        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> boxedClass();

        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> refClass();

        public scala.collection.immutable.Map<Symbols.Symbol, Symbols.ClassSymbol> volatileRefClass();

        public boolean isNumericSubClass(Symbols.Symbol var1, Symbols.Symbol var2);

        public boolean isNumericValueClass(Symbols.Symbol var1);

        public boolean isGetClass(Symbols.Symbol var1);

        public Symbols.ClassSymbol UnitClass();

        public Symbols.ClassSymbol ByteClass();

        public Symbols.ClassSymbol ShortClass();

        public Symbols.ClassSymbol CharClass();

        public Symbols.ClassSymbol IntClass();

        public Symbols.ClassSymbol LongClass();

        public Symbols.ClassSymbol FloatClass();

        public Symbols.ClassSymbol DoubleClass();

        public Symbols.ClassSymbol BooleanClass();

        public Symbols.TermSymbol Boolean_and();

        public Symbols.TermSymbol Boolean_or();

        public Symbols.TermSymbol Boolean_not();

        public Types.Type UnitTpe();

        public Types.Type ByteTpe();

        public Types.Type ShortTpe();

        public Types.Type CharTpe();

        public Types.Type IntTpe();

        public Types.Type LongTpe();

        public Types.Type FloatTpe();

        public Types.Type DoubleTpe();

        public Types.Type BooleanTpe();

        public List<Symbols.ClassSymbol> ScalaNumericValueClasses();

        public List<Symbols.ClassSymbol> ScalaValueClassesNoUnit();

        public List<Symbols.ClassSymbol> ScalaValueClasses();

        public List<Symbols.ClassSymbol> ScalaPrimitiveValueClasses();

        public Types.Type underlyingOfValueClass(Symbols.Symbol var1);

        public /* synthetic */ Definitions scala$reflect$internal$Definitions$ValueClassDefinitions$$$outer();
    }
}

