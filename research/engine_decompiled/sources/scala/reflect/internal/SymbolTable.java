/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.concurrent.TimeUnit;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.StringBuilder;
import scala.collection.mutable.WeakHashMap;
import scala.math.Ordering;
import scala.ref.WeakReference;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.FlagSets;
import scala.reflect.api.Internals;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.AnnotationCheckers;
import scala.reflect.internal.AnnotationCheckers$class;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.AnnotationInfos$Annotation$;
import scala.reflect.internal.AnnotationInfos$AnnotationInfo$;
import scala.reflect.internal.AnnotationInfos$ArrayAnnotArg$;
import scala.reflect.internal.AnnotationInfos$LiteralAnnotArg$;
import scala.reflect.internal.AnnotationInfos$NestedAnnotArg$;
import scala.reflect.internal.AnnotationInfos$ScalaSigBytes$;
import scala.reflect.internal.AnnotationInfos$ThrownException$;
import scala.reflect.internal.AnnotationInfos$UnmappableAnnotArg$;
import scala.reflect.internal.AnnotationInfos$UnmappableAnnotation$;
import scala.reflect.internal.AnnotationInfos$class;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.BaseTypeSeqs$class;
import scala.reflect.internal.CapturedVariables;
import scala.reflect.internal.CapturedVariables$class;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Constants$Constant$;
import scala.reflect.internal.Constants$class;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Definitions$class;
import scala.reflect.internal.Definitions$definitions$;
import scala.reflect.internal.Depth;
import scala.reflect.internal.ExistentialsAndSkolems;
import scala.reflect.internal.ExistentialsAndSkolems$class;
import scala.reflect.internal.FlagSets;
import scala.reflect.internal.FlagSets$Flag$;
import scala.reflect.internal.FlagSets$class;
import scala.reflect.internal.FreshNames;
import scala.reflect.internal.FreshNames$FreshNameExtractor$;
import scala.reflect.internal.FreshNames$class;
import scala.reflect.internal.Importers;
import scala.reflect.internal.Importers$class;
import scala.reflect.internal.InfoTransformers;
import scala.reflect.internal.InfoTransformers$class;
import scala.reflect.internal.Internals;
import scala.reflect.internal.Internals$class;
import scala.reflect.internal.Kinds;
import scala.reflect.internal.Kinds$Kind$;
import scala.reflect.internal.Kinds$KindErrors$;
import scala.reflect.internal.Kinds$ProperTypeKind$;
import scala.reflect.internal.Kinds$TypeConKind$;
import scala.reflect.internal.Kinds$class;
import scala.reflect.internal.Kinds$inferKind$;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Mirrors$class;
import scala.reflect.internal.Names;
import scala.reflect.internal.Names$TermName$;
import scala.reflect.internal.Names$TypeName$;
import scala.reflect.internal.Names$class;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.Positions;
import scala.reflect.internal.Positions$Range$;
import scala.reflect.internal.Positions$class;
import scala.reflect.internal.Printers;
import scala.reflect.internal.Printers$ConsoleWriter$;
import scala.reflect.internal.Printers$class;
import scala.reflect.internal.PrivateWithin;
import scala.reflect.internal.PrivateWithin$class;
import scala.reflect.internal.ReificationSupport;
import scala.reflect.internal.ReificationSupport$class;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.Reporting$class;
import scala.reflect.internal.Required;
import scala.reflect.internal.Required$class;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Scopes$EmptyScope$;
import scala.reflect.internal.Scopes$LookupAmbiguous$;
import scala.reflect.internal.Scopes$LookupInaccessible$;
import scala.reflect.internal.Scopes$LookupNotFound$;
import scala.reflect.internal.Scopes$LookupSucceeded$;
import scala.reflect.internal.Scopes$Scope$;
import scala.reflect.internal.Scopes$class;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.StdAttachments$BackquotedIdentifierAttachment$;
import scala.reflect.internal.StdAttachments$CompoundTypeTreeOriginalAttachment$;
import scala.reflect.internal.StdAttachments$ForAttachment$;
import scala.reflect.internal.StdAttachments$KnownDirectSubclassesCalled$;
import scala.reflect.internal.StdAttachments$SubpatternsAttachment$;
import scala.reflect.internal.StdAttachments$SyntheticUnitAttachment$;
import scala.reflect.internal.StdAttachments$TypeParamVarargsAttachment$;
import scala.reflect.internal.StdAttachments$class;
import scala.reflect.internal.StdCreators;
import scala.reflect.internal.StdCreators$FixedMirrorTreeCreator$;
import scala.reflect.internal.StdCreators$FixedMirrorTypeCreator$;
import scala.reflect.internal.StdCreators$class;
import scala.reflect.internal.StdNames;
import scala.reflect.internal.StdNames$binarynme$;
import scala.reflect.internal.StdNames$class;
import scala.reflect.internal.StdNames$compactify$;
import scala.reflect.internal.StdNames$fulltpnme$;
import scala.reflect.internal.StdNames$nme$;
import scala.reflect.internal.StdNames$tpnme$;
import scala.reflect.internal.SymbolTable$SimpleNameOrdering$;
import scala.reflect.internal.SymbolTable$perRunCaches$;
import scala.reflect.internal.SymbolTable$traceSymbols$;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Symbols$CyclicReference$;
import scala.reflect.internal.Symbols$SymbolKind$;
import scala.reflect.internal.Symbols$SymbolOps$;
import scala.reflect.internal.Symbols$TypeHistory$;
import scala.reflect.internal.Symbols$class;
import scala.reflect.internal.TreeGen;
import scala.reflect.internal.TreeInfo;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Trees$Alternative$;
import scala.reflect.internal.Trees$Annotated$;
import scala.reflect.internal.Trees$AppliedTypeTree$;
import scala.reflect.internal.Trees$Apply$;
import scala.reflect.internal.Trees$ApplyDynamic$;
import scala.reflect.internal.Trees$ArrayValue$;
import scala.reflect.internal.Trees$Assign$;
import scala.reflect.internal.Trees$AssignOrNamedArg$;
import scala.reflect.internal.Trees$Bind$;
import scala.reflect.internal.Trees$Block$;
import scala.reflect.internal.Trees$CaseDef$;
import scala.reflect.internal.Trees$ClassDef$;
import scala.reflect.internal.Trees$CompoundTypeTree$;
import scala.reflect.internal.Trees$DefDef$;
import scala.reflect.internal.Trees$EmptyTree$;
import scala.reflect.internal.Trees$ExistentialTypeTree$;
import scala.reflect.internal.Trees$Function$;
import scala.reflect.internal.Trees$Ident$;
import scala.reflect.internal.Trees$If$;
import scala.reflect.internal.Trees$Import$;
import scala.reflect.internal.Trees$ImportSelector$;
import scala.reflect.internal.Trees$LabelDef$;
import scala.reflect.internal.Trees$Literal$;
import scala.reflect.internal.Trees$Match$;
import scala.reflect.internal.Trees$Modifiers$;
import scala.reflect.internal.Trees$ModuleDef$;
import scala.reflect.internal.Trees$New$;
import scala.reflect.internal.Trees$PackageDef$;
import scala.reflect.internal.Trees$RefTree$;
import scala.reflect.internal.Trees$ReferenceToBoxed$;
import scala.reflect.internal.Trees$Return$;
import scala.reflect.internal.Trees$Select$;
import scala.reflect.internal.Trees$SelectFromTypeTree$;
import scala.reflect.internal.Trees$SingletonTypeTree$;
import scala.reflect.internal.Trees$Star$;
import scala.reflect.internal.Trees$Super$;
import scala.reflect.internal.Trees$Template$;
import scala.reflect.internal.Trees$This$;
import scala.reflect.internal.Trees$Throw$;
import scala.reflect.internal.Trees$Try$;
import scala.reflect.internal.Trees$TypeApply$;
import scala.reflect.internal.Trees$TypeBoundsTree$;
import scala.reflect.internal.Trees$TypeDef$;
import scala.reflect.internal.Trees$TypeTree$;
import scala.reflect.internal.Trees$Typed$;
import scala.reflect.internal.Trees$UnApply$;
import scala.reflect.internal.Trees$ValDef$;
import scala.reflect.internal.Trees$ValOrDefDef$;
import scala.reflect.internal.Trees$class;
import scala.reflect.internal.Trees$noSelfType$;
import scala.reflect.internal.Trees$pendingSuperCall$;
import scala.reflect.internal.TypeDebugging;
import scala.reflect.internal.TypeDebugging$class;
import scala.reflect.internal.TypeDebugging$noPrint$;
import scala.reflect.internal.TypeDebugging$typeDebug$;
import scala.reflect.internal.Types;
import scala.reflect.internal.Types$AnnotatedType$;
import scala.reflect.internal.Types$AntiPolyType$;
import scala.reflect.internal.Types$ArrayTypeRef$;
import scala.reflect.internal.Types$BoundedWildcardType$;
import scala.reflect.internal.Types$ClassInfoType$;
import scala.reflect.internal.Types$CompoundType$;
import scala.reflect.internal.Types$ConstantType$;
import scala.reflect.internal.Types$ErasedValueType$;
import scala.reflect.internal.Types$ErrorType$;
import scala.reflect.internal.Types$ExistentialType$;
import scala.reflect.internal.Types$GenPolyType$;
import scala.reflect.internal.Types$HasTypeMember$;
import scala.reflect.internal.Types$ImportType$;
import scala.reflect.internal.Types$MethodType$;
import scala.reflect.internal.Types$NamedType$;
import scala.reflect.internal.Types$NoPrefix$;
import scala.reflect.internal.Types$NoType$;
import scala.reflect.internal.Types$NullaryMethodType$;
import scala.reflect.internal.Types$OverloadedType$;
import scala.reflect.internal.Types$PolyType$;
import scala.reflect.internal.Types$RecoverableCyclicReference$;
import scala.reflect.internal.Types$RefinedType$;
import scala.reflect.internal.Types$RepeatedType$;
import scala.reflect.internal.Types$SingleType$;
import scala.reflect.internal.Types$StaticallyAnnotatedType$;
import scala.reflect.internal.Types$SuperType$;
import scala.reflect.internal.Types$ThisType$;
import scala.reflect.internal.Types$TypeBounds$;
import scala.reflect.internal.Types$TypeRef$;
import scala.reflect.internal.Types$TypeVar$;
import scala.reflect.internal.Types$UnmappableTree$;
import scala.reflect.internal.Types$WildcardType$;
import scala.reflect.internal.Types$baseClassesCycleMonitor$;
import scala.reflect.internal.Types$class;
import scala.reflect.internal.Types$substTypeMapCache$;
import scala.reflect.internal.Types$unwrapToClass$;
import scala.reflect.internal.Types$unwrapToStableClass$;
import scala.reflect.internal.Types$unwrapWrapperTypes$;
import scala.reflect.internal.Variance;
import scala.reflect.internal.Variances;
import scala.reflect.internal.Variances$class;
import scala.reflect.internal.pickling.Translations;
import scala.reflect.internal.pickling.Translations$class;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.CommonOwners$class;
import scala.reflect.internal.tpe.FindMembers$class;
import scala.reflect.internal.tpe.GlbLubs$class;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeComparers$SubTypePair$;
import scala.reflect.internal.tpe.TypeComparers$class;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.tpe.TypeConstraints$TypeConstraint$;
import scala.reflect.internal.tpe.TypeConstraints$class;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.tpe.TypeMaps$ApproximateDependentMap$;
import scala.reflect.internal.tpe.TypeMaps$ErroneousCollector$;
import scala.reflect.internal.tpe.TypeMaps$IsDependentCollector$;
import scala.reflect.internal.tpe.TypeMaps$abstractTypesToBounds$;
import scala.reflect.internal.tpe.TypeMaps$adaptToNewRunMap$;
import scala.reflect.internal.tpe.TypeMaps$class;
import scala.reflect.internal.tpe.TypeMaps$dropIllegalStarTypes$;
import scala.reflect.internal.tpe.TypeMaps$dropSingletonType$;
import scala.reflect.internal.tpe.TypeMaps$normalizeAliases$;
import scala.reflect.internal.tpe.TypeMaps$typeVarToOriginMap$;
import scala.reflect.internal.tpe.TypeMaps$wildcardExtrapolation$;
import scala.reflect.internal.tpe.TypeMaps$wildcardToTypeVarMap$;
import scala.reflect.internal.tpe.TypeToStrings$class;
import scala.reflect.internal.transform.Erasure;
import scala.reflect.internal.transform.PostErasure;
import scala.reflect.internal.transform.RefChecks;
import scala.reflect.internal.transform.Transforms;
import scala.reflect.internal.transform.Transforms$class;
import scala.reflect.internal.transform.UnCurry;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Collections$class;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.WeakHashSet;
import scala.reflect.internal.util.package;
import scala.reflect.internal.util.package$;
import scala.reflect.macros.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u00195d!B\u0001\u0003\u0003\u0003I!aC*z[\n|G\u000eV1cY\u0016T!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014G\u0001Q\u0001C\u0006\u000e\u001eA\r2\u0013\u0006L\u00183kaZd\bR$K\u001bB\u001bf+\u0017/`E\u0016D7N\u001c;xuB\u00111BD\u0007\u0002\u0019)\u0011Q\u0002B\u0001\u0007[\u0006\u001c'o\\:\n\u0005=a!\u0001C+oSZ,'o]3\u0011\u0005E!R\"\u0001\n\u000b\u0005M\u0011\u0011\u0001B;uS2L!!\u0006\n\u0003\u0017\r{G\u000e\\3di&|gn\u001d\t\u0003/ai\u0011AA\u0005\u00033\t\u0011QAT1nKN\u0004\"aF\u000e\n\u0005q\u0011!aB*z[\n|Gn\u001d\t\u0003/yI!a\b\u0002\u0003\u000bQK\b/Z:\u0011\u0005]\t\u0013B\u0001\u0012\u0003\u0005%1\u0016M]5b]\u000e,7\u000f\u0005\u0002\u0018I%\u0011QE\u0001\u0002\u0006\u0017&tGm\u001d\t\u0003/\u001dJ!\u0001\u000b\u0002\u0003-\u0015C\u0018n\u001d;f]RL\u0017\r\\:B]\u0012\u001c6n\u001c7f[N\u0004\"a\u0006\u0016\n\u0005-\u0012!\u0001\u0003$mC\u001e\u001cV\r^:\u0011\u0005]i\u0013B\u0001\u0018\u0003\u0005\u0019\u00196m\u001c9fgB\u0011q\u0003M\u0005\u0003c\t\u0011q!T5se>\u00148\u000f\u0005\u0002\u0018g%\u0011AG\u0001\u0002\f\t\u00164\u0017N\\5uS>t7\u000f\u0005\u0002\u0018m%\u0011qG\u0001\u0002\n\u0007>t7\u000f^1oiN\u0004\"aF\u001d\n\u0005i\u0012!\u0001\u0004\"bg\u0016$\u0016\u0010]3TKF\u001c\bCA\f=\u0013\ti$A\u0001\tJ]\u001a|GK]1og\u001a|'/\\3sgB\u0011qHQ\u0007\u0002\u0001*\u0011\u0011IA\u0001\niJ\fgn\u001d4pe6L!a\u0011!\u0003\u0015Q\u0013\u0018M\\:g_Jl7\u000f\u0005\u0002\u0018\u000b&\u0011aI\u0001\u0002\t'R$g*Y7fgB\u0011q\u0003S\u0005\u0003\u0013\n\u0011q\"\u00118o_R\fG/[8o\u0013:4wn\u001d\t\u0003/-K!\u0001\u0014\u0002\u0003%\u0005sgn\u001c;bi&|gn\u00115fG.,'o\u001d\t\u0003/9K!a\u0014\u0002\u0003\u000bQ\u0013X-Z:\u0011\u0005]\t\u0016B\u0001*\u0003\u0005!\u0001&/\u001b8uKJ\u001c\bCA\fU\u0013\t)&AA\u0005Q_NLG/[8ogB\u0011qcV\u0005\u00031\n\u0011Q\u0002V=qK\u0012+'-^4hS:<\u0007CA\f[\u0013\tY&AA\u0005J[B|'\u000f^3sgB\u0011q#X\u0005\u0003=\n\u0011\u0001BU3rk&\u0014X\r\u001a\t\u0003/\u0001L!!\u0019\u0002\u0003#\r\u000b\u0007\u000f^;sK\u00124\u0016M]5bE2,7\u000f\u0005\u0002\u0018G&\u0011AM\u0001\u0002\u000f'R$\u0017\t\u001e;bG\"lWM\u001c;t!\t9b-\u0003\u0002h\u0005\tY1\u000b\u001e3De\u0016\fGo\u001c:t!\t9\u0012.\u0003\u0002k\u0005\t\u0011\"+Z5gS\u000e\fG/[8o'V\u0004\bo\u001c:u!\t9B.\u0003\u0002n\u0005\ti\u0001K]5wCR,w+\u001b;iS:\u0004\"a\u001c:\u000e\u0003AT!!\u001d\u0002\u0002\u0011AL7m\u001b7j]\u001eL!a\u001d9\u0003\u0019Q\u0013\u0018M\\:mCRLwN\\:\u0011\u0005])\u0018B\u0001<\u0003\u0005)1%/Z:i\u001d\u0006lWm\u001d\t\u0003/aL!!\u001f\u0002\u0003\u0013%sG/\u001a:oC2\u001c\bCA\f|\u0013\ta(AA\u0005SKB|'\u000f^5oO\")a\u0010\u0001C\u0001\u007f\u00061A(\u001b8jiz\"\"!!\u0001\u0011\u0005]\u0001\u0001\"CA\u0003\u0001\t\u0007I\u0011AA\u0004\u0003\r9WM\\\u000b\u0003\u0003\u0013\u0011B!a\u0003\u0002\u0014\u00199\u0011QBA\b\u0001\u0005%!\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004\u0002CA\t\u0001\u0001\u0006I!!\u0003\u0002\t\u001d,g\u000e\t\t\u0004/\u0005U\u0011bAA\f\u0005\t9AK]3f\u000f\u0016t\u0007BCA\u000e\u0003\u0017\u0011\r\u0011\"\u0001\u0002\u001e\u00051q\r\\8cC2,\"!a\b\u000e\u0003\u0001Aq!a\t\u0001\r\u0003\t)#A\u0002m_\u001e$B!a\n\u00020A!\u0011\u0011FA\u0016\u001b\u00051\u0011bAA\u0017\r\t!QK\\5u\u0011%\t\t$!\t\u0005\u0002\u0004\t\u0019$A\u0002ng\u001e\u0004b!!\u000b\u00026\u0005e\u0012bAA\u001c\r\tAAHY=oC6,g\b\u0005\u0003\u0002*\u0005m\u0012bAA\u001f\r\t1\u0011I\\=SK\u001aDq!!\u0011\u0001\t#\t\u0019%\u0001\bfY\u0006\u00048/\u001a3NKN\u001c\u0018mZ3\u0015\r\u0005\u0015\u0013QKA2!\u0011\t9%!\u0015\u000e\u0005\u0005%#\u0002BA&\u0003\u001b\nA\u0001\\1oO*\u0011\u0011qJ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002T\u0005%#AB*ue&tw\r\u0003\u0005\u00022\u0005}\u0002\u0019AA,!\u0011\tI&a\u0018\u000f\t\u0005%\u00121L\u0005\u0004\u0003;2\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002T\u0005\u0005$bAA/\r!A\u0011QMA \u0001\u0004\t9'A\u0003ti\u0006\u0014H\u000f\u0005\u0003\u0002*\u0005%\u0014bAA6\r\t!Aj\u001c8h\u0011\u001d\ty\u0007\u0001C\u0001\u0003c\na\"\u001b8g_Jl\u0007K]8he\u0016\u001c8\u000f\u0006\u0003\u0002(\u0005M\u0004\u0002CA\u0019\u0003[\u0002\r!a\u0016\t\u000f\u0005]\u0004\u0001\"\u0001\u0002z\u0005Q\u0011N\u001c4pe6$\u0016.\\3\u0015\r\u0005\u001d\u00121PA?\u0011!\t\t$!\u001eA\u0002\u0005]\u0003\u0002CA3\u0003k\u0002\r!a\u001a\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\u0006!2\u000f[8vY\u0012dunZ!u)\"L7\u000f\u00155bg\u0016,\"!!\"\u0011\t\u0005%\u0012qQ\u0005\u0004\u0003\u00133!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003\u001b\u0003A\u0011AAB\u0003-I7\u000fU1tiRK\b/\u001a:\t\u000f\u0005E\u0005\u0001\"\u0005\u0002\u0004\u0006Y\u0011n\u001d#fm\u0016dw\u000e]3s\u0011\u001d\t)\n\u0001C\u0001\u0003/\u000b\u0011\u0002Z3ck\u001e<\u0018M\u001d8\u0015\t\u0005\u001d\u0012\u0011\u0014\u0005\n\u0003c\t\u0019\n\"a\u0001\u00037\u0003b!!\u000b\u00026\u0005]\u0003\u0006CAJ\u0003?\u000b)+!+\u0011\t\u0005%\u0012\u0011U\u0005\u0004\u0003G3!A\u00033faJ,7-\u0019;fI\u0006\u0012\u0011qU\u0001>+N,\u0007\u0005Z3w/\u0006\u0014h.\u001b8hA%4\u0007\u0005\u001e5jg\u0002J7\u000f\t:fC2d\u0017\u0010I1!o\u0006\u0014h.\u001b8hw\u0001zG\u000f[3so&\u001cX\rI;tK\u0002bwnZ\u0011\u0003\u0003W\u000baA\r\u00182c9\u0002\u0004bBAX\u0001\u0011\u0005\u0011\u0011W\u0001\tI\u0016\u0014Wo\u001a7pOR!\u0011qEAZ\u0011%\t\t$!,\u0005\u0002\u0004\tY\nC\u0004\u00028\u0002!\t!!/\u0002\u0015\u0011,goV1s]&tw\r\u0006\u0003\u0002(\u0005m\u0006\"CA\u0019\u0003k#\t\u0019AAN\u0011\u001d\ty\f\u0001C\u0001\u0003\u0003\f\u0011\u0003\u001e5s_^\f'\r\\3BgN#(/\u001b8h)\u0011\t9&a1\t\u0011\u0005\u0015\u0017Q\u0018a\u0001\u0003\u000f\f\u0011\u0001\u001e\t\u0005\u0003\u0013\fyM\u0004\u0003\u0002*\u0005-\u0017bAAg\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BAi\u0003'\u0014\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0007\u00055g\u0001C\u0004\u0002@\u0002!\t!a6\u0015\r\u0005]\u0013\u0011\\An\u0011!\t)-!6A\u0002\u0005\u001d\u0007\u0002CAo\u0003+\u0004\r!a8\u0002\u00135\f\u0007P\u0012:b[\u0016\u001c\b\u0003BA\u0015\u0003CL1!a9\u0007\u0005\rIe\u000e\u001e\u0005\b\u0003O\u0004AQAAu\u0003M!WM^,be:Lgn\u001a#v[B\u001cF/Y2l)\u0019\t9#a;\u0002n\"I\u0011\u0011GAs\t\u0003\u0007\u00111\u0014\u0005\t\u0003;\f)\u000f1\u0001\u0002`\"\"\u0011Q]Ay!\u0011\tI#a=\n\u0007\u0005UhA\u0001\u0004j]2Lg.\u001a\u0005\b\u0003s\u0004A\u0011AA~\u0003)!WMY;h'R\f7m\u001b\u000b\u0005\u0003O\ti\u0010\u0003\u0005\u0002F\u0006]\b\u0019AAd\u0011!\u0011\t\u0001\u0001C\u0001\r\t\r\u0011a\u00039sS:$8)\u00197mKJ,BA!\u0002\u0003\u000eQ!!q\u0001B\u0012)\u0011\u0011IAa\b\u0011\t\t-!Q\u0002\u0007\u0001\t!\u0011y!a@C\u0002\tE!!\u0001+\u0012\t\tM!\u0011\u0004\t\u0005\u0003S\u0011)\"C\u0002\u0003\u0018\u0019\u0011qAT8uQ&tw\r\u0005\u0003\u0002*\tm\u0011b\u0001B\u000f\r\t\u0019\u0011I\\=\t\u0011\t\u0005\u0012q a\u0001\u0005\u0013\taA]3tk2$\b\u0002CA\u0019\u0003\u007f\u0004\r!a\u0016\t\u0011\t\u001d\u0002\u0001\"\u0001\u0007\u0005S\t1\u0002\u001d:j]R\u0014Vm];miV!!1\u0006B\u0019)\u0011\u0011iC!\u000e\u0015\t\t=\"1\u0007\t\u0005\u0005\u0017\u0011\t\u0004\u0002\u0005\u0003\u0010\t\u0015\"\u0019\u0001B\t\u0011!\u0011\tC!\nA\u0002\t=\u0002\u0002CA\u0019\u0005K\u0001\r!a\u0016\t\u0011\te\u0002\u0001\"\u0002\u0007\u0005w\t\u0011\u0002\\8h%\u0016\u001cX\u000f\u001c;\u0016\t\tu\"1\t\u000b\u0005\u0005\u007f\u00119\u0005\u0006\u0003\u0003B\t\u0015\u0003\u0003\u0002B\u0006\u0005\u0007\"\u0001Ba\u0004\u00038\t\u0007!\u0011\u0003\u0005\t\u0005C\u00119\u00041\u0001\u0003B!I\u0011\u0011\u0007B\u001c\t\u0003\u0007\u00111\u0014\u0015\u0005\u0005o\t\t\u0010\u0003\u0005\u0003N\u0001!)A\u0002B(\u00039!WMY;hY><'+Z:vYR,BA!\u0015\u0003XQ!!1\u000bB.)\u0011\u0011)F!\u0017\u0011\t\t-!q\u000b\u0003\t\u0005\u001f\u0011YE1\u0001\u0003\u0012!A!\u0011\u0005B&\u0001\u0004\u0011)\u0006C\u0005\u00022\t-C\u00111\u0001\u0002\u001c\"\"!1JAy\u0011!\u0011\t\u0007\u0001C\u0003\r\t\r\u0014\u0001\u00053fm^\u000b'O\\5oOJ+7/\u001e7u+\u0011\u0011)Ga\u001b\u0015\t\t\u001d$q\u000e\u000b\u0005\u0005S\u0012i\u0007\u0005\u0003\u0003\f\t-D\u0001\u0003B\b\u0005?\u0012\rA!\u0005\t\u0011\t\u0005\"q\fa\u0001\u0005SB\u0011\"!\r\u0003`\u0011\u0005\r!a')\t\t}\u0013\u0011\u001f\u0005\t\u0005k\u0002AQ\u0001\u0004\u0003x\u0005YAn\\4SKN,H\u000e^%g+\u0011\u0011IHa \u0015\r\tm$1\u0011BC)\u0011\u0011iH!!\u0011\t\t-!q\u0010\u0003\t\u0005\u001f\u0011\u0019H1\u0001\u0003\u0012!A!\u0011\u0005B:\u0001\u0004\u0011i\bC\u0005\u00022\tMD\u00111\u0001\u0002\u001c\"A!q\u0011B:\u0001\u0004\u0011I)\u0001\u0003d_:$\u0007\u0003CA\u0015\u0005\u0017\u0013i(!\"\n\u0007\t5eAA\u0005Gk:\u001cG/[8oc!\"!1OAy\u0011!\u0011\u0019\n\u0001C\u0003\r\tU\u0015\u0001\u00053fEV<Gn\\4SKN,H\u000e^%g+\u0011\u00119J!(\u0015\r\te%\u0011\u0015BR)\u0011\u0011YJa(\u0011\t\t-!Q\u0014\u0003\t\u0005\u001f\u0011\tJ1\u0001\u0003\u0012!A!\u0011\u0005BI\u0001\u0004\u0011Y\nC\u0005\u00022\tEE\u00111\u0001\u0002\u001c\"A!q\u0011BI\u0001\u0004\u0011)\u000b\u0005\u0005\u0002*\t-%1TACQ\u0011\u0011\t*!=\t\u000f\t-\u0006\u0001\"\u0002\u0003.\u0006Qa-\u001b8e'fl'm\u001c7\u0015\t\t=&Q\u0018\u000b\u0005\u0005c\u00139\f\u0005\u0003\u0002 \tM\u0016b\u0001B[7\t11+_7c_2D\u0001B!/\u0003*\u0002\u0007!1X\u0001\u0002aBA\u0011\u0011\u0006BF\u0005c\u000b)\t\u0003\u0005\u0003@\n%\u0006\u0019\u0001Ba\u0003\tA8\u000f\u0005\u0004\u0002J\n\r'\u0011W\u0005\u0005\u0005\u000b\f\u0019NA\bUe\u00064XM]:bE2,wJ\\2fQ\u0011\u0011I+!=\t\u000f\t-\u0007\u0001b\u0001\u0003N\u00069Bn\\<Qe&|'/\u001b;z\u001d\u0006lWm\u0014:eKJLgnZ\u000b\u0005\u0005\u001f\u0014I.\u0006\u0002\u0003RB1\u0011\u0011\u001aBj\u0005/LAA!6\u0002T\nAqJ\u001d3fe&tw\r\u0005\u0003\u0003\f\teG\u0001\u0003B\b\u0005\u0013\u0014\rAa7\u0012\t\tM!Q\u001c\t\u0004-\t}\u0017b\u0001Bq1\t!a*Y7f\u000f\u001d\u0011)\u000f\u0001E\u0005\u0005O\f!cU5na2,g*Y7f\u001fJ$WM]5oOB!\u0011q\u0004Bu\r\u001d\u0011Y\u000f\u0001E\u0005\u0005[\u0014!cU5na2,g*Y7f\u001fJ$WM]5oON1!\u0011\u001eBx\u0005k\u0004B!a\u0012\u0003r&!!1_A%\u0005\u0019y%M[3diB1\u0011\u0011\u001aBj\u0005;DqA Bu\t\u0003\u0011I\u0010\u0006\u0002\u0003h\"A!Q Bu\t\u0003\u0011y0A\u0004d_6\u0004\u0018M]3\u0015\r\u0005}7\u0011AB\u0003\u0011!\u0019\u0019Aa?A\u0002\tu\u0017A\u000182\u0011!\u00199Aa?A\u0002\tu\u0017A\u000183\u0011%\u0019Y\u0001\u0001b\u0001\n\u000b\t\u0019)A\nue\u0006\u001cWmU=nE>d\u0017i\u0019;jm&$\u0018\u0010\u0003\u0005\u0004\u0010\u0001\u0001\u000bQBAC\u0003Q!(/Y2f'fl'm\u001c7BGRLg/\u001b;zA\u001d911\u0003\u0001\t\u0002\rU\u0011\u0001\u0004;sC\u000e,7+_7c_2\u001c\b\u0003BA\u0010\u0007/1qa!\u0007\u0001\u0011\u0003\u0019YB\u0001\u0007ue\u0006\u001cWmU=nE>d7o\u0005\u0004\u0004\u0018\u0005e2Q\u0004\t\u0004#\r}\u0011bAB\u0011%\t\u0019BK]1dKNKXNY8m\u0003\u000e$\u0018N^5us\"Q\u00111DB\f\u0005\u0004%\t!!\b\t\u0019\r\u001d2q\u0003C\u0001\u0002\u0003\u0006I!a\b\u0002\u000f\u001ddwNY1mA!9apa\u0006\u0005\u0002\r-BCAB\u000b\u0011%\u0019y\u0003\u0001b\u0001\u000e\u0003\u0019\t$\u0001\u0005ue\u0016,\u0017J\u001c4p+\t\u0019\u0019D\u0005\u0003\u00046\r]bABA\u0007\u0001\u0001\u0019\u0019\u0004E\u0002\u0018\u0007sI1aa\u000f\u0003\u0005!!&/Z3J]\u001a|\u0007BCA\u000e\u0007k\u0011\rQ\"\u0011\u0002\u001e!91\u0011\t\u0001\u0005\u0002\r\r\u0013aE1tg\u0016\u0014HoQ8se\u0016\u001cG\u000f\u00165sK\u0006$GCAA\u0014Q\u0019\u0019yda\u0012\u0004TA!1\u0011JB(\u001b\t\u0019YEC\u0002\u0004N\u0019\t!\"\u00198o_R\fG/[8o\u0013\u0011\u0019\tfa\u0013\u0003\u0011\u0015d\u0017\u000eZ1cY\u0016l\"a\u0001C\t\u000f\r]\u0003\u0001\"\u0001\u0004Z\u0005YQ.[:tS:<\u0007j\\8l)\u0019\u0011\tla\u0017\u0004`!A1QLB+\u0001\u0004\u0011\t,A\u0003po:,'\u000f\u0003\u0005\u0004b\rU\u0003\u0019AB2\u0003\u0011q\u0017-\\3\u0011\t\u0005}!q\u001c\u0005\b\u0007O\u0002a\u0011AB5\u0003Ai\u0017N\u001d:peRC\u0017\r\u001e'pC\u0012,G\r\u0006\u0003\u0004l\rE\u0004\u0003BA\u0010\u0007[J1aa\u001c1\u0005\u0019i\u0015N\u001d:pe\"A11OB3\u0001\u0004\u0011\t,A\u0002ts6,aaa\u001e\u0001\u0001\u0005}'A\u0002)fe&|G\rC\u0005\u0004|\u0001\u0011\r\u0011\"\u0002\u0004~\u0005Aaj\u001c)fe&|G-\u0006\u0002\u0004\u0000=\u00111\u0011Q\u000f\u0002\u0001!A1Q\u0011\u0001!\u0002\u001b\u0019y(A\u0005O_B+'/[8eA\u001511\u0011\u0012\u0001\u0001\u0003?\u0014QAU;o\u0013\u0012D\u0011b!$\u0001\u0005\u0004%)a! \u0002\u000f9{'+\u001e8JI\"A1\u0011\u0013\u0001!\u0002\u001b\u0019y(\u0001\u0005O_J+h.\u00133!\u0011%\u0019)\n\u0001a\u0001\n\u0003\u00199*A\u0004qQN#\u0018mY6\u0016\u0005\re\u0005CBAe\u00077\u001by*\u0003\u0003\u0004\u001e\u0006M'\u0001\u0002'jgR\u00042aFBQ\u0013\r\u0019\u0019K\u0001\u0002\u0006!\"\f7/\u001a\u0005\n\u0007O\u0003\u0001\u0019!C\u0001\u0007S\u000b1\u0002\u001d5Ti\u0006\u001c7n\u0018\u0013fcR!\u0011qEBV\u0011)\u0019ik!*\u0002\u0002\u0003\u00071\u0011T\u0001\u0004q\u0012\n\u0004\u0002CBY\u0001\u0001\u0006Ka!'\u0002\u0011AD7\u000b^1dW\u0002B\u0001b!.\u0001A\u0003&1qT\u0001\u0003a\"D\u0001b!/\u0001A\u0003&\u0011q\\\u0001\u0004a\u0016\u0014\bbBB_\u0001\u0011\u00151qS\u0001\rCR\u0004\u0006.Y:f'R\f7m\u001b\u0005\b\u0007\u0003\u0004AQABb\u0003\u0015\u0001\b.Y:f+\t\u0019y\nC\u0004\u0004H\u0002!\ta!3\u0002'\u0005$\b\u000b[1tKN#\u0018mY6NKN\u001c\u0018mZ3\u0016\u0005\u0005\u0015\u0003bBBg\u0001\u0011\u00151qZ\u0001\na\"\f7/Z0%KF$B!a\n\u0004R\"A!\u0011XBf\u0001\u0004\u0019y\nC\u0004\u0004V\u0002!)aa6\u0002\u0013A,8\u000f\u001b)iCN,G\u0003BBP\u00073D\u0001b!.\u0004T\u0002\u00071q\u0014\u0005\b\u0007;\u0004AQABp\u0003!\u0001x\u000e\u001d)iCN,G\u0003BA\u0014\u0007CD\u0001b!.\u0004\\\u0002\u00071q\u0014\u0005\b\u0007K\u0004a\u0011ABt\u00031\u0019WO\u001d:f]R\u0014VO\\%e+\t\u0019I\u000f\u0005\u0003\u0002 \r\u001d\u0005bBBw\u0001\u0011\u00151q^\u0001\u0006eVt\u0017\n\u001a\u000b\u0005\u0007S\u001c\t\u0010\u0003\u0005\u0004t\u000e-\b\u0019AB{\u0003\u0019\u0001XM]5pIB!\u0011qDB;\u0011\u001d\u0019I\u0010\u0001C\u0003\u0007w\fq\u0001\u001d5bg\u0016LE\r\u0006\u0003\u0002`\u000eu\b\u0002CBz\u0007o\u0004\ra!>\t\u000f\u0011\u0005\u0001\u0001\"\u0002\u0005\u0004\u0005i1-\u001e:sK:$\b+\u001a:j_\u0012,\"a!>\t\u000f\u0011\u001d\u0001\u0001\"\u0002\u0005\n\u00059\u0001\u000f[1tK>3G\u0003BBP\t\u0017A\u0001ba=\u0005\u0006\u0001\u00071Q\u001f\u0005\b\u0007g\u0004AQ\u0001C\b)\u0019\u0019)\u0010\"\u0005\u0005\u0016!AA1\u0003C\u0007\u0001\u0004\u0019I/A\u0002sS\u0012D\u0001\u0002b\u0006\u0005\u000e\u0001\u0007\u0011q\\\u0001\u0004a&$\u0007b\u0002C\u000e\u0001\u0011\u0015AQD\u0001\u000fSN\fE\u000f\u00155bg\u0016\fe\r^3s)\u0011\t)\tb\b\t\u0011\teF\u0011\u0004a\u0001\u0007?Cq\u0001b\t\u0001\t\u000b!)#A\u0007f]R,'/\u001b8h!\"\f7/Z\u000b\u0005\tO!i\u0003\u0006\u0003\u0005*\u0011UB\u0003\u0002C\u0016\t_\u0001BAa\u0003\u0005.\u0011A!q\u0002C\u0011\u0005\u0004\u0011\t\u0002C\u0005\u00052\u0011\u0005B\u00111\u0001\u00054\u0005\u0011q\u000e\u001d\t\u0007\u0003S\t)\u0004b\u000b\t\u0011\rUF\u0011\u0005a\u0001\u0007?CC\u0001\"\t\u0002r\"9A1\b\u0001\u0005\u0006\u0011u\u0012!\u00054j]\u0012\u0004\u0006.Y:f/&$\bNT1nKR!1q\u0014C \u0011!!\t\u0005\"\u000fA\u0002\u0005]\u0013!\u00039iCN,g*Y7f\u0011\u001d!)\u0005\u0001C\u0003\t\u000f\nQ#\u001a8uKJLgn\u001a)iCN,w+\u001b;i\u001d\u0006lW-\u0006\u0003\u0005J\u0011=C\u0003\u0002C&\t/\"B\u0001\"\u0014\u0005RA!!1\u0002C(\t!\u0011y\u0001b\u0011C\u0002\tE\u0001\"\u0003C*\t\u0007\"\t\u0019\u0001C+\u0003\u0011\u0011w\u000eZ=\u0011\r\u0005%\u0012Q\u0007C'\u0011!!\t\u0005b\u0011A\u0002\u0005]\u0003b\u0002C.\u0001\u0011\u0005AQL\u0001\u0019g2|wOQ;u'\u00064W-\u00128uKJLgn\u001a)iCN,W\u0003\u0002C0\tK\"B\u0001\"\u0019\u0005lQ!A1\rC4!\u0011\u0011Y\u0001\"\u001a\u0005\u0011\t=A\u0011\fb\u0001\u0005#A\u0011\u0002\"\r\u0005Z\u0011\u0005\r\u0001\"\u001b\u0011\r\u0005%\u0012Q\u0007C2\u0011!\u0019)\f\"\u0017A\u0002\r}\u0005b\u0002C8\u0001\u0011\u0015A\u0011O\u0001\rKbLG/\u001b8h!\"\f7/Z\u000b\u0005\tg\"I\b\u0006\u0003\u0005v\u0011}D\u0003\u0002C<\tw\u0002BAa\u0003\u0005z\u0011A!q\u0002C7\u0005\u0004\u0011\t\u0002C\u0005\u00052\u00115D\u00111\u0001\u0005~A1\u0011\u0011FA\u001b\toB\u0001b!.\u0005n\u0001\u00071q\u0014\u0015\u0005\t[\n\t\u0010C\u0004\u0005\u0006\u0002!)\u0001b\"\u0002#\u0015tG/\u001a:j]\u001e\u0004&/\u001a<QQ\u0006\u001cX-\u0006\u0003\u0005\n\u00125E\u0003\u0002CF\t\u001f\u0003BAa\u0003\u0005\u000e\u0012A!q\u0002CB\u0005\u0004\u0011\t\u0002C\u0005\u00052\u0011\rE\u00111\u0001\u0005\u0012B1\u0011\u0011FA\u001b\t\u0017CC\u0001b!\u0002r\"9Aq\u0013\u0001\u0005\u0006\u0011e\u0015!G3oi\u0016\u0014\u0018N\\4QQ\u0006\u001cXMT8u\u0019\u0006$XM\u001d+iC:,B\u0001b'\u0005\"R!AQ\u0014CT)\u0011!y\nb)\u0011\t\t-A\u0011\u0015\u0003\t\u0005\u001f!)J1\u0001\u0003\u0012!IA\u0011\u0007CK\t\u0003\u0007AQ\u0015\t\u0007\u0003S\t)\u0004b(\t\u0011\u0011%FQ\u0013a\u0001\u0007?\u000ba\u0001^1sO\u0016$\b\u0006\u0002CK\u0003cDq\u0001b,\u0001\t\u0003!\t,\u0001\u0013tY><()\u001e;TC\u001a,WI\u001c;fe&tw\r\u00155bg\u0016tu\u000e\u001e'bi\u0016\u0014H\u000b[1o+\u0011!\u0019\f\"/\u0015\t\u0011UFq\u0018\u000b\u0005\to#Y\f\u0005\u0003\u0003\f\u0011eF\u0001\u0003B\b\t[\u0013\rA!\u0005\t\u0013\u0011EBQ\u0016CA\u0002\u0011u\u0006CBA\u0015\u0003k!9\f\u0003\u0005\u0005*\u00125\u0006\u0019ABP\u0011\u001d!\u0019\r\u0001C\u0003\t\u000b\fq![:WC2LG\r\u0006\u0003\u0002\u0006\u0012\u001d\u0007\u0002CBz\t\u0003\u0004\ra!>\t\u000f\u0011-\u0007\u0001\"\u0002\u0005N\u0006)\u0012n\u001d,bY&$gi\u001c:CCN,7\t\\1tg\u0016\u001cH\u0003BAC\t\u001fD\u0001ba=\u0005J\u0002\u00071Q\u001f\u0005\b\t'\u0004A\u0011\u0001Ck\u0003Ey\u0007/\u001a8QC\u000e\\\u0017mZ3N_\u0012,H.\u001a\u000b\u0007\u0003O!9\u000eb7\t\u0011\u0011eG\u0011\u001ba\u0001\u0005c\u000b\u0011bY8oi\u0006Lg.\u001a:\t\u0011\u0011uG\u0011\u001ba\u0001\u0005c\u000bA\u0001Z3ti\"9A\u0011\u001d\u0001\u0005\u0002\u0011\r\u0018aD1se\u0006LHk\u001c*fa\u0016\fG/\u001a3\u0015\t\u0011\u0015H1\u001e\t\u0005\u0003?!9/C\u0002\u0005jz\u0011A\u0001V=qK\"AAQ\u001eCp\u0001\u0004!)/\u0001\u0002ua\u001a9A\u0011\u001f\u0001\u0002\u0002\u0011M(!C*z[2{\u0017\rZ3s'\u0011!y\u000f\">\u0011\t\u0005}Aq_\u0005\u0004\tst\"\u0001\u0003'buf$\u0016\u0010]3\t\u000fy$y\u000f\"\u0001\u0005~R\u0011Aq \t\u0005\u0003?!y\u000f\u0003\u0005\u0006\u0004\u0011=H\u0011AAB\u0003)1'o\\7T_V\u00148-\u001a\u0005\b\t'\u0004A\u0011AC\u0004)\u0011\t9#\"\u0003\t\u0011\u0015-QQ\u0001a\u0001\u0005c\u000b\u0001\u0002]6h\u00072\f7o]\u0004\b\u000b\u001f\u0001\u0001\u0012AC\t\u00031\u0001XM\u001d*v]\u000e\u000b7\r[3t!\u0011\ty\"b\u0005\u0007\u000f\u0015U\u0001\u0001#\u0001\u0006\u0018\ta\u0001/\u001a:Sk:\u001c\u0015m\u00195fgN!Q1CA\u001d\u0011\u001dqX1\u0003C\u0001\u000b7!\"!\"\u0005\t\u0015\u0015}Q1\u0003a\u0001\n\u0013)\t#\u0001\u0004dC\u000eDWm]\u000b\u0003\u000bG\u0001b!\"\n\u00060\u0015ERBAC\u0014\u0015\u0011)I#b\u000b\u0002\u0013%lW.\u001e;bE2,'bAC\u0017\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\ruUq\u0005\t\u0007\u000bg)I$\"\u0010\u000e\u0005\u0015U\"\u0002BC\u001c\u0003\u0013\n1A]3g\u0013\u0011)Y$\"\u000e\u0003\u001b]+\u0017m\u001b*fM\u0016\u0014XM\\2f!\u0011)y$\"\u0012\u000e\u0005\u0015\u0005#\u0002BC\"\u000bW\tqaZ3oKJL7-\u0003\u0003\u0006H\u0015\u0005#!C\"mK\u0006\u0014\u0018M\u00197f\u0011))Y%b\u0005A\u0002\u0013%QQJ\u0001\u000bG\u0006\u001c\u0007.Z:`I\u0015\fH\u0003BA\u0014\u000b\u001fB!b!,\u0006J\u0005\u0005\t\u0019AC\u0012\u0011%)\u0019&b\u0005!B\u0013)\u0019#A\u0004dC\u000eDWm\u001d\u0011\t\u0011\u0015]S1\u0003C\u0001\u000b3\n1B]3d_J$7)Y2iKV!Q1LC0)\u0011)i&b\u0019\u0011\t\t-Qq\f\u0003\t\u0005\u001f))F1\u0001\u0006bE!!1CC\u001f\u0011!))'\"\u0016A\u0002\u0015u\u0013!B2bG\",\u0007\u0002CC5\u000b'!\t!b\u001b\u0002\u001bUt'/Z2pe\u0012\u001c\u0015m\u00195f+\u0011)i'b\u001d\u0015\t\u0005\u001dRq\u000e\u0005\t\u000bK*9\u00071\u0001\u0006rA!!1BC:\t!\u0011y!b\u001aC\u0002\u0015\u0005\u0004\u0002CC<\u000b'!\taa\u0011\u0002\u0011\rdW-\u0019:BY2D\u0001\"b\u001f\u0006\u0014\u0011\u0005QQP\u0001\u000b]\u0016<x+Z1l\u001b\u0006\u0004XCBC@\u000b\u001f+)\n\u0006\u0002\u0006\u0002BAQ1QCE\u000b\u001b+\u0019*\u0004\u0002\u0006\u0006*!QqQC\u0016\u0003\u001diW\u000f^1cY\u0016LA!b#\u0006\u0006\nYq+Z1l\u0011\u0006\u001c\b.T1q!\u0011\u0011Y!b$\u0005\u0011\u0015EU\u0011\u0010b\u0001\u0005#\u0011\u0011a\u0013\t\u0005\u0005\u0017))\n\u0002\u0005\u0006\u0018\u0016e$\u0019\u0001B\t\u0005\u00051\u0006\u0002CCN\u000b'!\t!\"(\u0002\r9,w/T1q+\u0019)y*\"+\u0006.R\u0011Q\u0011\u0015\t\t\u000b\u0007+\u0019+b*\u0006,&!QQUCC\u0005\u001dA\u0015m\u001d5NCB\u0004BAa\u0003\u0006*\u0012AQ\u0011SCM\u0005\u0004\u0011\t\u0002\u0005\u0003\u0003\f\u00155F\u0001CCL\u000b3\u0013\rA!\u0005\t\u0011\u0015EV1\u0003C\u0001\u000bg\u000baA\\3x'\u0016$X\u0003BC[\u000b\u007f#\"!b.\u0011\r\u0015\rU\u0011XC_\u0013\u0011)Y,\"\"\u0003\u000f!\u000b7\u000f[*fiB!!1BC`\t!)\t*b,C\u0002\tE\u0001\u0002CCb\u000b'!\t!\"2\u0002\u00159,woV3bWN+G/\u0006\u0003\u0006H\u0016EGCACe!\u0015\tR1ZCh\u0013\r)iM\u0005\u0002\f/\u0016\f7\u000eS1tQN+G\u000f\u0005\u0003\u0003\f\u0015EG\u0001CCI\u000b\u0003\u0014\r!b5\u0012\t\tM\u0011\u0011\b\u0005\t\u000b/,\u0019\u0002\"\u0001\u0006Z\u0006aa.Z<B]f\u0014VMZ'baV1Q1\\Cs\u000bS$\"!\"8\u0011\u0011\u0015\rUq\\Cr\u000bOLA!\"9\u0006\u0006\nI\u0011I\\=SK\u001al\u0015\r\u001d\t\u0005\u0005\u0017))\u000f\u0002\u0005\u0006\u0012\u0016U'\u0019ACj!\u0011\u0011Y!\";\u0005\u0011\u0015]UQ\u001bb\u0001\u0005#A\u0001\"\"<\u0006\u0014\u0011\u0005Qq^\u0001\u000b]\u0016<x)\u001a8fe&\u001cW\u0003BCy\u000bw$B!b=\u0006~B1\u0011\u0011FC{\u000bsL1!b>\u0007\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0003\u0003\f\u0015mH\u0001\u0003B\b\u000bW\u0014\rA!\u0005\t\u0013\u0015}X1\u001eCA\u0002\u0019\u0005\u0011!\u00014\u0011\r\u0005%\u0012QGC}\u0011%1)\u0001\u0001a\u0001\n\u000319!\u0001\tj]\u001a|GK]1og\u001a|'/\\3sgV\u0011a\u0011\u0002\t\u0005\u0003?1Y!C\u0002\u0007\u000eq\u0012q\"\u00138g_R\u0013\u0018M\\:g_JlWM\u001d\u0005\n\r#\u0001\u0001\u0019!C\u0001\r'\tA#\u001b8g_R\u0013\u0018M\\:g_JlWM]:`I\u0015\fH\u0003BA\u0014\r+A!b!,\u0007\u0010\u0005\u0005\t\u0019\u0001D\u0005\u0011!1I\u0002\u0001Q!\n\u0019%\u0011!E5oM>$&/\u00198tM>\u0014X.\u001a:tA!IaQ\u0004\u0001C\u0002\u001b\u0005aqD\u0001\fa\"\f7/Z,ji\"LE-\u0006\u0002\u0007\"A1\u0011\u0011\u0006D\u0012\u0007?K1A\"\n\u0007\u0005\u0015\t%O]1z\u0011\u001d1I\u0003\u0001C\u0001\u0003\u0007\u000b!#[:D_6\u0004\u0018\u000e\\3s+:Lg/\u001a:tK\"9aQ\u0006\u0001\u0005\u0006\u0019=\u0012aB1u!\"\f7/Z\u000b\u0005\rc19\u0004\u0006\u0003\u00074\u0019uB\u0003\u0002D\u001b\rs\u0001BAa\u0003\u00078\u0011A!q\u0002D\u0016\u0005\u0004\u0011\t\u0002C\u0005\u00052\u0019-B\u00111\u0001\u0007<A1\u0011\u0011FA\u001b\rkA\u0001b!.\u0007,\u0001\u00071q\u0014\u0015\u0005\rW\t\t\u0010\u000b\u0005\u0007,\u0005}e1\tD$C\t1)%A\tVg\u0016\u0004SM\u001c;fe&tw\r\u00155bg\u0016\f#A\"\u0013\u0002\rIr\u0013\u0007\r\u00181\u0011%1i\u0005\u0001b\u0001\n\u00071y%A\u000eTiJLgnZ\"p]R,\u0007\u0010^*ue&\u0004X*\u0019:hS:|\u0005o]\u000b\u0003\r#\u0002\u0002\"!\u000b\u0003\f\u001aMc\u0011\f\t\u0005\u0003S1)&C\u0002\u0007X\u0019\u0011Qb\u0015;sS:<7i\u001c8uKb$\b\u0003\u0002D.\rGrAA\"\u0018\u0007b9\u0019qCb\u0018\n\u0005M\u0011\u0011bAAg%%!aQ\rD4\u0005m\u0019FO]5oO\u000e{g\u000e^3yiN#(/\u001b9NCJ<\u0017N\\(qg*\u0019\u0011Q\u001a\n\t\u0011\u0019-\u0004\u0001)A\u0005\r#\nAd\u0015;sS:<7i\u001c8uKb$8\u000b\u001e:ja6\u000b'oZ5o\u001fB\u001c\b\u0005")
public abstract class SymbolTable
extends Universe
implements Names,
Symbols,
Types,
Variances,
Kinds,
ExistentialsAndSkolems,
FlagSets,
Scopes,
Mirrors,
Definitions,
Constants,
BaseTypeSeqs,
InfoTransformers,
Transforms,
StdNames,
AnnotationInfos,
AnnotationCheckers,
Trees,
Printers,
Positions,
TypeDebugging,
Importers,
Required,
CapturedVariables,
StdAttachments,
StdCreators,
ReificationSupport,
PrivateWithin,
Translations,
FreshNames,
Internals,
Reporting {
    private final TreeGen gen;
    private final boolean traceSymbolActivity;
    private final int NoPeriod;
    private final int NoRunId;
    private List<Phase> phStack;
    private Phase ph;
    private int per;
    private InfoTransformers.InfoTransformer infoTransformers;
    private final Function1<StringContext, package.StringContextStripMarginOps> StringContextStripMarginOps;
    private volatile SymbolTable$SimpleNameOrdering$ scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module;
    private volatile SymbolTable$traceSymbols$ traceSymbols$module;
    private volatile SymbolTable$perRunCaches$ perRunCaches$module;
    private final Universe.MacroInternalApi internal;
    private final Universe.MacroCompatApi compat;
    private final Universe.TreeGen treeBuild;
    private final FreshNameCreator globalFreshNameCreator;
    private final ReificationSupport.ReificationSupportImpl build;
    private final NoPosition$ NoPosition;
    private final ClassTag<Position> PositionTag;
    private final Positions.Range scala$reflect$internal$Positions$$maxFree;
    private final Positions.PosAssigner posAssigner;
    private final Printers.FootnoteIndex scala$reflect$internal$Printers$$footnoteIndex;
    private int nodeCount;
    private final ClassTag<Trees.Modifiers> ModifiersTag;
    private final Trees$noSelfType$ emptyValDef;
    private final Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter;
    private final Trees.Duplicator scala$reflect$internal$Trees$$duplicator;
    private final ClassTag<Trees.Alternative> AlternativeTag;
    private final ClassTag<Trees.Annotated> AnnotatedTag;
    private final ClassTag<Trees.AppliedTypeTree> AppliedTypeTreeTag;
    private final ClassTag<Trees.Apply> ApplyTag;
    private final ClassTag<Trees.AssignOrNamedArg> AssignOrNamedArgTag;
    private final ClassTag<Trees.Assign> AssignTag;
    private final ClassTag<Trees.Bind> BindTag;
    private final ClassTag<Trees.Block> BlockTag;
    private final ClassTag<Trees.CaseDef> CaseDefTag;
    private final ClassTag<Trees.ClassDef> ClassDefTag;
    private final ClassTag<Trees.CompoundTypeTree> CompoundTypeTreeTag;
    private final ClassTag<Trees.DefDef> DefDefTag;
    private final ClassTag<Trees.DefTree> DefTreeTag;
    private final ClassTag<Trees.ExistentialTypeTree> ExistentialTypeTreeTag;
    private final ClassTag<Trees.Function> FunctionTag;
    private final ClassTag<Trees.GenericApply> GenericApplyTag;
    private final ClassTag<Trees.Ident> IdentTag;
    private final ClassTag<Trees.If> IfTag;
    private final ClassTag<Trees.ImplDef> ImplDefTag;
    private final ClassTag<Trees.ImportSelector> ImportSelectorTag;
    private final ClassTag<Trees.Import> ImportTag;
    private final ClassTag<Trees.LabelDef> LabelDefTag;
    private final ClassTag<Trees.Literal> LiteralTag;
    private final ClassTag<Trees.Match> MatchTag;
    private final ClassTag<Trees.MemberDef> MemberDefTag;
    private final ClassTag<Trees.ModuleDef> ModuleDefTag;
    private final ClassTag<Trees.NameTree> NameTreeTag;
    private final ClassTag<Trees.New> NewTag;
    private final ClassTag<Trees.PackageDef> PackageDefTag;
    private final ClassTag<Trees.ReferenceToBoxed> ReferenceToBoxedTag;
    private final ClassTag<Trees.RefTree> RefTreeTag;
    private final ClassTag<Trees.Return> ReturnTag;
    private final ClassTag<Trees.SelectFromTypeTree> SelectFromTypeTreeTag;
    private final ClassTag<Trees.Select> SelectTag;
    private final ClassTag<Trees.SingletonTypeTree> SingletonTypeTreeTag;
    private final ClassTag<Trees.Star> StarTag;
    private final ClassTag<Trees.Super> SuperTag;
    private final ClassTag<Trees.SymTree> SymTreeTag;
    private final ClassTag<Trees.Template> TemplateTag;
    private final ClassTag<Trees.TermTree> TermTreeTag;
    private final ClassTag<Trees.This> ThisTag;
    private final ClassTag<Trees.Throw> ThrowTag;
    private final ClassTag<Trees.Tree> TreeTag;
    private final ClassTag<Trees.Try> TryTag;
    private final ClassTag<Trees.TypTree> TypTreeTag;
    private final ClassTag<Trees.TypeApply> TypeApplyTag;
    private final ClassTag<Trees.TypeBoundsTree> TypeBoundsTreeTag;
    private final ClassTag<Trees.TypeDef> TypeDefTag;
    private final ClassTag<Trees.TypeTree> TypeTreeTag;
    private final ClassTag<Trees.Typed> TypedTag;
    private final ClassTag<Trees.UnApply> UnApplyTag;
    private final ClassTag<Trees.ValDef> ValDefTag;
    private final ClassTag<Trees.ValOrDefDef> ValOrDefDefTag;
    private final Statistics.View treeNodeCount;
    private List<AnnotationCheckers.AnnotationChecker> scala$reflect$internal$AnnotationCheckers$$annotationCheckers;
    private final ClassTag<AnnotationInfos.ClassfileAnnotArg> JavaArgumentTag;
    private final AnnotationInfos$LiteralAnnotArg$ LiteralArgument;
    private final ClassTag<AnnotationInfos.LiteralAnnotArg> LiteralArgumentTag;
    private final AnnotationInfos$ArrayAnnotArg$ ArrayArgument;
    private final ClassTag<AnnotationInfos.ArrayAnnotArg> ArrayArgumentTag;
    private final AnnotationInfos$NestedAnnotArg$ NestedArgument;
    private final ClassTag<AnnotationInfos.NestedAnnotArg> NestedArgumentTag;
    private final ClassTag<AnnotationInfos.AnnotationInfo> AnnotationTag;
    private final StdNames$tpnme$ typeNames;
    private final StdNames.JavaKeywords javanme;
    private final StdNames$nme$ termNames;
    private final StdNames.SymbolNames sn;
    private final Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$refChecksLazy;
    private final Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$uncurryLazy;
    private final Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$erasureLazy;
    private final Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$postErasureLazy;
    private final BaseTypeSeqs.BaseTypeSeq undetBaseTypeSeq;
    private final Throwable CyclicInheritance;
    private final ClassTag<Constants.Constant> ConstantTag;
    private final ClassTag<Scopes.Scope> ScopeTag;
    private final ClassTag<Scopes.Scope> MemberScopeTag;
    private final ClassTag<Object> FlagSetTag;
    private final long NoFlags;
    private final Kinds.KindErrors NoKindErrors;
    private boolean scala$reflect$internal$Types$$explainSwitch;
    private final Set<Symbols.Symbol> scala$reflect$internal$Types$$emptySymbolSet;
    private final boolean scala$reflect$internal$Types$$traceTypeVars;
    private final boolean scala$reflect$internal$Types$$breakCycles;
    private final boolean scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars;
    private final boolean scala$reflect$internal$Types$$sharperSkolems;
    private final boolean enableTypeVarExperimentals;
    private int scala$reflect$internal$Types$$_skolemizationLevel;
    private final WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> scala$reflect$internal$Types$$_intersectionWitness;
    private int scala$reflect$internal$Types$$volatileRecursions;
    private final HashSet<Symbols.Symbol> scala$reflect$internal$Types$$pendingVolatiles;
    private final int scala$reflect$internal$Types$$initialUniquesCapacity;
    private WeakHashSet<Types.Type> scala$reflect$internal$Types$$uniques;
    private int scala$reflect$internal$Types$$uniqueRunId;
    private final Types.MissingAliasControl missingAliasException;
    private int scala$reflect$internal$Types$$_basetypeRecursions;
    private final HashSet<Types.Type> scala$reflect$internal$Types$$_pendingBaseTypes;
    private String scala$reflect$internal$Types$$_indent;
    private final Set<String> shorthands;
    private final Function1<Types.Type, Object> isTypeVar;
    private final Function1<Types.Type, Object> typeContainsTypeVar;
    private final Function1<Types.Type, Object> typeIsNonClassType;
    private final Function1<Types.Type, Object> typeIsExistentiallyBound;
    private final Function1<Types.Type, Object> typeIsErroneous;
    private final Function1<Symbols.Symbol, Object> symTypeIsError;
    private final Function1<Trees.Tree, Types.Type> treeTpe;
    private final Function1<Symbols.Symbol, Types.Type> symTpe;
    private final Function1<Symbols.Symbol, Types.Type> symInfo;
    private final Function1<Types.Type, Object> typeHasAnnotations;
    private final Function2<Types.TypeBounds, Types.Type, Object> boundsContainType;
    private final Function1<List<Types.Type>, Object> typeListIsEmpty;
    private final Function1<Types.Type, Object> typeIsSubTypeOfSerializable;
    private final Function1<Types.Type, Object> typeIsNothing;
    private final Function1<Types.Type, Object> typeIsAny;
    private final Function1<Types.Type, Object> typeIsHigherKinded;
    private final ClassTag<Types.AnnotatedType> AnnotatedTypeTag;
    private final ClassTag<Types.BoundedWildcardType> BoundedWildcardTypeTag;
    private final ClassTag<Types.ClassInfoType> ClassInfoTypeTag;
    private final ClassTag<Types.CompoundType> CompoundTypeTag;
    private final ClassTag<Types.ConstantType> ConstantTypeTag;
    private final ClassTag<Types.ExistentialType> ExistentialTypeTag;
    private final ClassTag<Types.MethodType> MethodTypeTag;
    private final ClassTag<Types.NullaryMethodType> NullaryMethodTypeTag;
    private final ClassTag<Types.PolyType> PolyTypeTag;
    private final ClassTag<Types.RefinedType> RefinedTypeTag;
    private final ClassTag<Types.SingletonType> SingletonTypeTag;
    private final ClassTag<Types.SingleType> SingleTypeTag;
    private final ClassTag<Types.SuperType> SuperTypeTag;
    private final ClassTag<Types.ThisType> ThisTypeTag;
    private final ClassTag<Types.TypeBounds> TypeBoundsTag;
    private final ClassTag<Types.TypeRef> TypeRefTag;
    private final ClassTag<Types.Type> TypeTagg;
    private final TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
    private final Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
    private final Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
    private final boolean scala$reflect$internal$tpe$GlbLubs$$printLubs;
    private final MutableSettings.SettingValue scala$reflect$internal$tpe$GlbLubs$$strictInference;
    private final HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_lubResults;
    private final HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_glbResults;
    private final Throwable GlbFailure;
    private int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth;
    private final int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit;
    private final CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
    private int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions;
    private HashSet<Types.Type> scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects;
    private final HashSet<TypeComparers.SubTypePair> scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes;
    private int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions;
    private int ids;
    private Map<Symbols.Symbol, Object> scala$reflect$internal$Symbols$$_recursionTable;
    private int scala$reflect$internal$Symbols$$existentialIds;
    private final HashMap<Symbols.Symbol, Symbols.Symbol> scala$reflect$internal$Symbols$$originalOwnerMap;
    private final ClassTag<Symbols.Symbol> SymbolTag;
    private final ClassTag<Symbols.TermSymbol> TermSymbolTag;
    private final ClassTag<Symbols.ModuleSymbol> ModuleSymbolTag;
    private final ClassTag<Symbols.MethodSymbol> MethodSymbolTag;
    private final ClassTag<Symbols.TypeSymbol> TypeSymbolTag;
    private final ClassTag<Symbols.ClassSymbol> ClassSymbolTag;
    private final ClassTag<Symbols.FreeTermSymbol> FreeTermSymbolTag;
    private final ClassTag<Symbols.FreeTypeSymbol> FreeTypeSymbolTag;
    private final Symbols.NoSymbol NoSymbol;
    private final Function1<Symbols.Symbol, Object> symbolIsPossibleInRefinement;
    private final Symbols.SymbolOps AllOps;
    private final Object scala$reflect$internal$Names$$nameLock;
    private char[] chrs;
    private int scala$reflect$internal$Names$$nc;
    private final Names.TermName[] scala$reflect$internal$Names$$termHashtable;
    private final Names.TypeName[] scala$reflect$internal$Names$$typeHashtable;
    private final ClassTag<Names.Name> NameTag;
    private final ClassTag<Names.TermName> TermNameTag;
    private final ClassTag<Names.TypeName> TypeNameTag;
    private volatile int bitmap$0;
    private volatile FreshNames$FreshNameExtractor$ FreshNameExtractor$module;
    private volatile StdCreators$FixedMirrorTreeCreator$ FixedMirrorTreeCreator$module;
    private volatile StdCreators$FixedMirrorTypeCreator$ FixedMirrorTypeCreator$module;
    private volatile StdAttachments$CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment$module;
    private volatile StdAttachments$BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment$module;
    private volatile StdAttachments$ForAttachment$ ForAttachment$module;
    private volatile StdAttachments$SyntheticUnitAttachment$ SyntheticUnitAttachment$module;
    private volatile StdAttachments$SubpatternsAttachment$ SubpatternsAttachment$module;
    private volatile StdAttachments$KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled$module;
    private volatile StdAttachments$TypeParamVarargsAttachment$ TypeParamVarargsAttachment$module;
    private volatile TypeDebugging$noPrint$ noPrint$module;
    private volatile TypeDebugging$typeDebug$ typeDebug$module;
    private volatile Positions$Range$ Range$module;
    private volatile Printers$ConsoleWriter$ ConsoleWriter$module;
    private volatile Trees$RefTree$ RefTree$module;
    private volatile Trees$PackageDef$ PackageDef$module;
    private volatile Trees$ClassDef$ ClassDef$module;
    private volatile Trees$ModuleDef$ ModuleDef$module;
    private volatile Trees$ValOrDefDef$ ValOrDefDef$module;
    private volatile Trees$ValDef$ ValDef$module;
    private volatile Trees$DefDef$ DefDef$module;
    private volatile Trees$TypeDef$ TypeDef$module;
    private volatile Trees$LabelDef$ LabelDef$module;
    private volatile Trees$ImportSelector$ ImportSelector$module;
    private volatile Trees$Import$ Import$module;
    private volatile Trees$Template$ Template$module;
    private volatile Trees$Block$ Block$module;
    private volatile Trees$CaseDef$ CaseDef$module;
    private volatile Trees$Alternative$ Alternative$module;
    private volatile Trees$Star$ Star$module;
    private volatile Trees$Bind$ Bind$module;
    private volatile Trees$UnApply$ UnApply$module;
    private volatile Trees$ArrayValue$ ArrayValue$module;
    private volatile Trees$Function$ Function$module;
    private volatile Trees$Assign$ Assign$module;
    private volatile Trees$AssignOrNamedArg$ AssignOrNamedArg$module;
    private volatile Trees$If$ If$module;
    private volatile Trees$Match$ Match$module;
    private volatile Trees$Return$ Return$module;
    private volatile Trees$Try$ Try$module;
    private volatile Trees$Throw$ Throw$module;
    private volatile Trees$New$ New$module;
    private volatile Trees$Typed$ Typed$module;
    private volatile Trees$TypeApply$ TypeApply$module;
    private volatile Trees$Apply$ Apply$module;
    private volatile Trees$ApplyDynamic$ ApplyDynamic$module;
    private volatile Trees$Super$ Super$module;
    private volatile Trees$This$ This$module;
    private volatile Trees$Select$ Select$module;
    private volatile Trees$Ident$ Ident$module;
    private volatile Trees$ReferenceToBoxed$ ReferenceToBoxed$module;
    private volatile Trees$Literal$ Literal$module;
    private volatile Trees$Annotated$ Annotated$module;
    private volatile Trees$SingletonTypeTree$ SingletonTypeTree$module;
    private volatile Trees$SelectFromTypeTree$ SelectFromTypeTree$module;
    private volatile Trees$CompoundTypeTree$ CompoundTypeTree$module;
    private volatile Trees$AppliedTypeTree$ AppliedTypeTree$module;
    private volatile Trees$TypeBoundsTree$ TypeBoundsTree$module;
    private volatile Trees$ExistentialTypeTree$ ExistentialTypeTree$module;
    private volatile Trees$TypeTree$ TypeTree$module;
    private volatile Trees$Modifiers$ Modifiers$module;
    private volatile Trees$EmptyTree$ EmptyTree$module;
    private volatile Trees$noSelfType$ noSelfType$module;
    private volatile Trees$pendingSuperCall$ pendingSuperCall$module;
    private volatile AnnotationInfos$UnmappableAnnotArg$ UnmappableAnnotArg$module;
    private volatile AnnotationInfos$LiteralAnnotArg$ LiteralAnnotArg$module;
    private volatile AnnotationInfos$ArrayAnnotArg$ ArrayAnnotArg$module;
    private volatile AnnotationInfos$NestedAnnotArg$ NestedAnnotArg$module;
    private volatile AnnotationInfos$ScalaSigBytes$ ScalaSigBytes$module;
    private volatile AnnotationInfos$AnnotationInfo$ AnnotationInfo$module;
    private volatile AnnotationInfos$Annotation$ Annotation$module;
    private volatile AnnotationInfos$UnmappableAnnotation$ UnmappableAnnotation$module;
    private volatile AnnotationInfos$ThrownException$ ThrownException$module;
    private volatile StdNames$compactify$ scala$reflect$internal$StdNames$$compactify$module;
    private volatile StdNames$tpnme$ tpnme$module;
    private volatile StdNames$fulltpnme$ fulltpnme$module;
    private volatile StdNames$binarynme$ binarynme$module;
    private volatile StdNames$nme$ nme$module;
    private volatile Constants$Constant$ Constant$module;
    private volatile Definitions$definitions$ definitions$module;
    private volatile Scopes$LookupSucceeded$ LookupSucceeded$module;
    private volatile Scopes$LookupAmbiguous$ LookupAmbiguous$module;
    private volatile Scopes$LookupInaccessible$ LookupInaccessible$module;
    private volatile Scopes$LookupNotFound$ LookupNotFound$module;
    private volatile Scopes$Scope$ Scope$module;
    private volatile Scopes$EmptyScope$ EmptyScope$module;
    private volatile FlagSets$Flag$ Flag$module;
    private volatile Kinds$KindErrors$ KindErrors$module;
    private volatile Kinds$Kind$ Kind$module;
    private volatile Kinds$ProperTypeKind$ ProperTypeKind$module;
    private volatile Kinds$TypeConKind$ TypeConKind$module;
    private volatile Kinds$inferKind$ inferKind$module;
    private volatile Types$substTypeMapCache$ scala$reflect$internal$Types$$substTypeMapCache$module;
    private volatile Types$UnmappableTree$ UnmappableTree$module;
    private volatile Types$ErrorType$ ErrorType$module;
    private volatile Types$WildcardType$ WildcardType$module;
    private volatile Types$BoundedWildcardType$ BoundedWildcardType$module;
    private volatile Types$NoType$ NoType$module;
    private volatile Types$NoPrefix$ NoPrefix$module;
    private volatile Types$ThisType$ ThisType$module;
    private volatile Types$SingleType$ SingleType$module;
    private volatile Types$SuperType$ SuperType$module;
    private volatile Types$TypeBounds$ TypeBounds$module;
    private volatile Types$CompoundType$ CompoundType$module;
    private volatile Types$baseClassesCycleMonitor$ baseClassesCycleMonitor$module;
    private volatile Types$RefinedType$ RefinedType$module;
    private volatile Types$ClassInfoType$ ClassInfoType$module;
    private volatile Types$ConstantType$ ConstantType$module;
    private volatile Types$TypeRef$ TypeRef$module;
    private volatile Types$MethodType$ MethodType$module;
    private volatile Types$NullaryMethodType$ NullaryMethodType$module;
    private volatile Types$PolyType$ PolyType$module;
    private volatile Types$ExistentialType$ ExistentialType$module;
    private volatile Types$OverloadedType$ OverloadedType$module;
    private volatile Types$ImportType$ ImportType$module;
    private volatile Types$AntiPolyType$ AntiPolyType$module;
    private volatile Types$HasTypeMember$ HasTypeMember$module;
    private volatile Types$ArrayTypeRef$ ArrayTypeRef$module;
    private volatile Types$TypeVar$ TypeVar$module;
    private volatile Types$AnnotatedType$ AnnotatedType$module;
    private volatile Types$StaticallyAnnotatedType$ StaticallyAnnotatedType$module;
    private volatile Types$NamedType$ NamedType$module;
    private volatile Types$RepeatedType$ RepeatedType$module;
    private volatile Types$ErasedValueType$ ErasedValueType$module;
    private volatile Types$GenPolyType$ GenPolyType$module;
    private volatile Types$unwrapToClass$ unwrapToClass$module;
    private volatile Types$unwrapToStableClass$ unwrapToStableClass$module;
    private volatile Types$unwrapWrapperTypes$ unwrapWrapperTypes$module;
    private volatile Types$RecoverableCyclicReference$ RecoverableCyclicReference$module;
    private volatile TypeConstraints$TypeConstraint$ TypeConstraint$module;
    private volatile TypeMaps$normalizeAliases$ normalizeAliases$module;
    private volatile TypeMaps$dropSingletonType$ dropSingletonType$module;
    private volatile TypeMaps$abstractTypesToBounds$ abstractTypesToBounds$module;
    private volatile TypeMaps$dropIllegalStarTypes$ dropIllegalStarTypes$module;
    private volatile TypeMaps$wildcardExtrapolation$ wildcardExtrapolation$module;
    private volatile TypeMaps$IsDependentCollector$ IsDependentCollector$module;
    private volatile TypeMaps$ApproximateDependentMap$ ApproximateDependentMap$module;
    private volatile TypeMaps$wildcardToTypeVarMap$ wildcardToTypeVarMap$module;
    private volatile TypeMaps$typeVarToOriginMap$ typeVarToOriginMap$module;
    private volatile TypeMaps$ErroneousCollector$ ErroneousCollector$module;
    private volatile TypeMaps$adaptToNewRunMap$ adaptToNewRunMap$module;
    private volatile TypeComparers$SubTypePair$ SubTypePair$module;
    private volatile Symbols$SymbolKind$ SymbolKind$module;
    private volatile Symbols$CyclicReference$ CyclicReference$module;
    private volatile Symbols$TypeHistory$ scala$reflect$internal$Symbols$$TypeHistory$module;
    private volatile Symbols$SymbolOps$ SymbolOps$module;
    private volatile Names$TermName$ TermName$module;
    private volatile Names$TypeName$ TypeName$module;

    private SymbolTable$SimpleNameOrdering$ scala$reflect$internal$SymbolTable$$SimpleNameOrdering$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module == null) {
                this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module = new SymbolTable$SimpleNameOrdering$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module;
        }
    }

    private SymbolTable$traceSymbols$ traceSymbols$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.traceSymbols$module == null) {
                this.traceSymbols$module = new SymbolTable$traceSymbols$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.traceSymbols$module;
        }
    }

    private SymbolTable$perRunCaches$ perRunCaches$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.perRunCaches$module == null) {
                this.perRunCaches$module = new SymbolTable$perRunCaches$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.perRunCaches$module;
        }
    }

    @Override
    public String supplementTyperState(String errorMessage2) {
        return Reporting$class.supplementTyperState(this, errorMessage2);
    }

    @Override
    public String supplementErrorMessage(String errorMessage2) {
        return Reporting$class.supplementErrorMessage(this, errorMessage2);
    }

    @Override
    public void inform(String msg) {
        Reporting$class.inform(this, msg);
    }

    @Override
    public void warning(String msg) {
        Reporting$class.warning(this, msg);
    }

    @Override
    public void globalError(String msg) {
        Reporting$class.globalError(this, msg);
    }

    @Override
    public Nothing$ abort(String msg) {
        return Reporting$class.abort(this, msg);
    }

    @Override
    public void inform(Position pos, String msg) {
        Reporting$class.inform(this, pos, msg);
    }

    @Override
    public void warning(Position pos, String msg) {
        Reporting$class.warning(this, pos, msg);
    }

    @Override
    public void globalError(Position pos, String msg) {
        Reporting$class.globalError(this, pos, msg);
    }

    private Universe.MacroInternalApi internal$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 1) == 0) {
                this.internal = Internals$class.internal(this);
                this.bitmap$0 |= 1;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.internal;
        }
    }

    @Override
    public Universe.MacroInternalApi internal() {
        return (this.bitmap$0 & 1) == 0 ? this.internal$lzycompute() : this.internal;
    }

    private Universe.MacroCompatApi compat$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 2) == 0) {
                this.compat = Internals$class.compat(this);
                this.bitmap$0 |= 2;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.compat;
        }
    }

    @Override
    public Universe.MacroCompatApi compat() {
        return (this.bitmap$0 & 2) == 0 ? this.compat$lzycompute() : this.compat;
    }

    private Universe.TreeGen treeBuild$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 4) == 0) {
                this.treeBuild = Internals$class.treeBuild(this);
                this.bitmap$0 |= 4;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.treeBuild;
        }
    }

    @Override
    public Universe.TreeGen treeBuild() {
        return (this.bitmap$0 & 4) == 0 ? this.treeBuild$lzycompute() : this.treeBuild;
    }

    @Override
    public FreshNameCreator globalFreshNameCreator() {
        return this.globalFreshNameCreator;
    }

    private FreshNames$FreshNameExtractor$ FreshNameExtractor$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.FreshNameExtractor$module == null) {
                this.FreshNameExtractor$module = new FreshNames$FreshNameExtractor$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.FreshNameExtractor$module;
        }
    }

    @Override
    public FreshNames$FreshNameExtractor$ FreshNameExtractor() {
        return this.FreshNameExtractor$module == null ? this.FreshNameExtractor$lzycompute() : this.FreshNameExtractor$module;
    }

    @Override
    public void scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(FreshNameCreator x$1) {
        this.globalFreshNameCreator = x$1;
    }

    @Override
    public Names.TermName freshTermName(String prefix, FreshNameCreator creator) {
        return FreshNames$class.freshTermName(this, prefix, creator);
    }

    @Override
    public Names.TypeName freshTypeName(String prefix, FreshNameCreator creator) {
        return FreshNames$class.freshTypeName(this, prefix, creator);
    }

    @Override
    public String freshTermName$default$1() {
        return FreshNames$class.freshTermName$default$1(this);
    }

    @Override
    public boolean isTreeSymbolPickled(int code) {
        return Translations$class.isTreeSymbolPickled(this, code);
    }

    @Override
    public boolean isTreeSymbolPickled(Trees.Tree tree) {
        return Translations$class.isTreeSymbolPickled(this, tree);
    }

    @Override
    public int picklerTag(Object ref) {
        return Translations$class.picklerTag(this, ref);
    }

    @Override
    public int picklerTag(Symbols.Symbol sym) {
        return Translations$class.picklerTag(this, sym);
    }

    @Override
    public int picklerTag(Types.Type tpe) {
        return Translations$class.picklerTag(this, tpe);
    }

    @Override
    public int picklerSubTag(Trees.Tree tree) {
        return Translations$class.picklerSubTag(this, tree);
    }

    @Override
    public void propagatePackageBoundary(Class<?> c, Seq<Symbols.Symbol> syms) {
        PrivateWithin$class.propagatePackageBoundary(this, c, syms);
    }

    @Override
    public void propagatePackageBoundary(Member m, Seq<Symbols.Symbol> syms) {
        PrivateWithin$class.propagatePackageBoundary(this, m, syms);
    }

    @Override
    public void propagatePackageBoundary(int jflags, Seq<Symbols.Symbol> syms) {
        PrivateWithin$class.propagatePackageBoundary(this, jflags, syms);
    }

    @Override
    public Symbols.Symbol setPackageAccessBoundary(Symbols.Symbol sym) {
        return PrivateWithin$class.setPackageAccessBoundary(this, sym);
    }

    @Override
    public ReificationSupport.ReificationSupportImpl build() {
        return this.build;
    }

    @Override
    public void scala$reflect$internal$ReificationSupport$_setter_$build_$eq(ReificationSupport.ReificationSupportImpl x$1) {
        this.build = x$1;
    }

    private StdCreators$FixedMirrorTreeCreator$ FixedMirrorTreeCreator$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.FixedMirrorTreeCreator$module == null) {
                this.FixedMirrorTreeCreator$module = new StdCreators$FixedMirrorTreeCreator$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.FixedMirrorTreeCreator$module;
        }
    }

    @Override
    public StdCreators$FixedMirrorTreeCreator$ FixedMirrorTreeCreator() {
        return this.FixedMirrorTreeCreator$module == null ? this.FixedMirrorTreeCreator$lzycompute() : this.FixedMirrorTreeCreator$module;
    }

    private StdCreators$FixedMirrorTypeCreator$ FixedMirrorTypeCreator$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.FixedMirrorTypeCreator$module == null) {
                this.FixedMirrorTypeCreator$module = new StdCreators$FixedMirrorTypeCreator$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.FixedMirrorTypeCreator$module;
        }
    }

    @Override
    public StdCreators$FixedMirrorTypeCreator$ FixedMirrorTypeCreator() {
        return this.FixedMirrorTypeCreator$module == null ? this.FixedMirrorTypeCreator$lzycompute() : this.FixedMirrorTypeCreator$module;
    }

    private StdAttachments$CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.CompoundTypeTreeOriginalAttachment$module == null) {
                this.CompoundTypeTreeOriginalAttachment$module = new StdAttachments$CompoundTypeTreeOriginalAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.CompoundTypeTreeOriginalAttachment$module;
        }
    }

    @Override
    public StdAttachments$CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment() {
        return this.CompoundTypeTreeOriginalAttachment$module == null ? this.CompoundTypeTreeOriginalAttachment$lzycompute() : this.CompoundTypeTreeOriginalAttachment$module;
    }

    private StdAttachments$BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.BackquotedIdentifierAttachment$module == null) {
                this.BackquotedIdentifierAttachment$module = new StdAttachments$BackquotedIdentifierAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.BackquotedIdentifierAttachment$module;
        }
    }

    @Override
    public StdAttachments$BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment() {
        return this.BackquotedIdentifierAttachment$module == null ? this.BackquotedIdentifierAttachment$lzycompute() : this.BackquotedIdentifierAttachment$module;
    }

    private StdAttachments$ForAttachment$ ForAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ForAttachment$module == null) {
                this.ForAttachment$module = new StdAttachments$ForAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ForAttachment$module;
        }
    }

    @Override
    public StdAttachments$ForAttachment$ ForAttachment() {
        return this.ForAttachment$module == null ? this.ForAttachment$lzycompute() : this.ForAttachment$module;
    }

    private StdAttachments$SyntheticUnitAttachment$ SyntheticUnitAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SyntheticUnitAttachment$module == null) {
                this.SyntheticUnitAttachment$module = new StdAttachments$SyntheticUnitAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SyntheticUnitAttachment$module;
        }
    }

    @Override
    public StdAttachments$SyntheticUnitAttachment$ SyntheticUnitAttachment() {
        return this.SyntheticUnitAttachment$module == null ? this.SyntheticUnitAttachment$lzycompute() : this.SyntheticUnitAttachment$module;
    }

    private StdAttachments$SubpatternsAttachment$ SubpatternsAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SubpatternsAttachment$module == null) {
                this.SubpatternsAttachment$module = new StdAttachments$SubpatternsAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SubpatternsAttachment$module;
        }
    }

    @Override
    public StdAttachments$SubpatternsAttachment$ SubpatternsAttachment() {
        return this.SubpatternsAttachment$module == null ? this.SubpatternsAttachment$lzycompute() : this.SubpatternsAttachment$module;
    }

    private StdAttachments$KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.KnownDirectSubclassesCalled$module == null) {
                this.KnownDirectSubclassesCalled$module = new StdAttachments$KnownDirectSubclassesCalled$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.KnownDirectSubclassesCalled$module;
        }
    }

    @Override
    public StdAttachments$KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled() {
        return this.KnownDirectSubclassesCalled$module == null ? this.KnownDirectSubclassesCalled$lzycompute() : this.KnownDirectSubclassesCalled$module;
    }

    private StdAttachments$TypeParamVarargsAttachment$ TypeParamVarargsAttachment$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeParamVarargsAttachment$module == null) {
                this.TypeParamVarargsAttachment$module = new StdAttachments$TypeParamVarargsAttachment$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeParamVarargsAttachment$module;
        }
    }

    @Override
    public StdAttachments$TypeParamVarargsAttachment$ TypeParamVarargsAttachment() {
        return this.TypeParamVarargsAttachment$module == null ? this.TypeParamVarargsAttachment$lzycompute() : this.TypeParamVarargsAttachment$module;
    }

    @Override
    public void captureVariable(Symbols.Symbol vble) {
        CapturedVariables$class.captureVariable(this, vble);
    }

    @Override
    public Trees.Tree referenceCapturedVariable(Symbols.Symbol vble) {
        return CapturedVariables$class.referenceCapturedVariable(this, vble);
    }

    @Override
    public Types.Type capturedVariableType(Symbols.Symbol vble) {
        return CapturedVariables$class.capturedVariableType(this, vble);
    }

    @Override
    public Types.Type capturedVariableType(Symbols.Symbol vble, Types.Type tpe, boolean erasedTypes) {
        return CapturedVariables$class.capturedVariableType(this, vble, tpe, erasedTypes);
    }

    @Override
    public Types.Type capturedVariableType$default$2() {
        return CapturedVariables$class.capturedVariableType$default$2(this);
    }

    @Override
    public boolean capturedVariableType$default$3() {
        return CapturedVariables$class.capturedVariableType$default$3(this);
    }

    @Override
    public boolean forInteractive() {
        return Required$class.forInteractive(this);
    }

    @Override
    public boolean forScaladoc() {
        return Required$class.forScaladoc(this);
    }

    @Override
    public Internals.Importer mkImporter(scala.reflect.api.Universe from0) {
        return Importers$class.mkImporter(this, from0);
    }

    private TypeDebugging$noPrint$ noPrint$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.noPrint$module == null) {
                this.noPrint$module = new TypeDebugging$noPrint$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.noPrint$module;
        }
    }

    @Override
    public TypeDebugging$noPrint$ noPrint() {
        return this.noPrint$module == null ? this.noPrint$lzycompute() : this.noPrint$module;
    }

    private TypeDebugging$typeDebug$ typeDebug$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.typeDebug$module == null) {
                this.typeDebug$module = new TypeDebugging$typeDebug$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.typeDebug$module;
        }
    }

    @Override
    public TypeDebugging$typeDebug$ typeDebug() {
        return this.typeDebug$module == null ? this.typeDebug$lzycompute() : this.typeDebug$module;
    }

    @Override
    public String paramString(Types.Type tp) {
        return TypeDebugging$class.paramString(this, tp);
    }

    @Override
    public String typeParamsString(Types.Type tp) {
        return TypeDebugging$class.typeParamsString(this, tp);
    }

    @Override
    public String debugString(Types.Type tp) {
        return TypeDebugging$class.debugString(this, tp);
    }

    @Override
    public NoPosition$ NoPosition() {
        return this.NoPosition;
    }

    @Override
    public ClassTag<Position> PositionTag() {
        return this.PositionTag;
    }

    private Positions.Range scala$reflect$internal$Positions$$maxFree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 8) == 0) {
                this.scala$reflect$internal$Positions$$maxFree = Positions$class.scala$reflect$internal$Positions$$maxFree(this);
                this.bitmap$0 |= 8;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$Positions$$maxFree;
        }
    }

    @Override
    public Positions.Range scala$reflect$internal$Positions$$maxFree() {
        return (this.bitmap$0 & 8) == 0 ? this.scala$reflect$internal$Positions$$maxFree$lzycompute() : this.scala$reflect$internal$Positions$$maxFree;
    }

    private Positions$Range$ Range$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Range$module == null) {
                this.Range$module = new Positions$Range$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Range$module;
        }
    }

    @Override
    public Positions$Range$ Range() {
        return this.Range$module == null ? this.Range$lzycompute() : this.Range$module;
    }

    private Positions.PosAssigner posAssigner$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x10) == 0) {
                this.posAssigner = Positions$class.posAssigner(this);
                this.bitmap$0 |= 0x10;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.posAssigner;
        }
    }

    @Override
    public Positions.PosAssigner posAssigner() {
        return (this.bitmap$0 & 0x10) == 0 ? this.posAssigner$lzycompute() : this.posAssigner;
    }

    @Override
    public void scala$reflect$internal$Positions$_setter_$NoPosition_$eq(NoPosition$ x$1) {
        this.NoPosition = x$1;
    }

    @Override
    public void scala$reflect$internal$Positions$_setter_$PositionTag_$eq(ClassTag x$1) {
        this.PositionTag = x$1;
    }

    @Override
    public boolean useOffsetPositions() {
        return Positions$class.useOffsetPositions(this);
    }

    @Override
    public Position wrappingPos(Position position, List<Trees.Tree> trees) {
        return Positions$class.wrappingPos(this, position, trees);
    }

    @Override
    public Position wrappingPos(Position position, List<Trees.Tree> trees, boolean focus) {
        return Positions$class.wrappingPos(this, position, trees, focus);
    }

    @Override
    public Position wrappingPos(List<Trees.Tree> trees) {
        return Positions$class.wrappingPos(this, trees);
    }

    @Override
    public void ensureNonOverlapping(Trees.Tree tree, List<Trees.Tree> others) {
        Positions$class.ensureNonOverlapping(this, tree, others);
    }

    @Override
    public void ensureNonOverlapping(Trees.Tree tree, List<Trees.Tree> others, boolean focus) {
        Positions$class.ensureNonOverlapping(this, tree, others, focus);
    }

    @Override
    public Position rangePos(SourceFile source, int start, int point, int end) {
        return Positions$class.rangePos(this, source, start, point, end);
    }

    @Override
    public void validatePositions(Trees.Tree tree) {
        Positions$class.validatePositions(this, tree);
    }

    @Override
    public List<Trees.Tree> solidDescendants(Trees.Tree tree) {
        return Positions$class.solidDescendants(this, tree);
    }

    @Override
    public List<Tuple2<Trees.Tree, Trees.Tree>> findOverlapping(List<Trees.Tree> cts) {
        return Positions$class.findOverlapping(this, cts);
    }

    @Override
    public <T extends Trees.Tree> T atPos(Position pos, T tree) {
        return (T)Positions$class.atPos(this, pos, tree);
    }

    private Printers$ConsoleWriter$ ConsoleWriter$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ConsoleWriter$module == null) {
                this.ConsoleWriter$module = new Printers$ConsoleWriter$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ConsoleWriter$module;
        }
    }

    @Override
    public Printers$ConsoleWriter$ ConsoleWriter() {
        return this.ConsoleWriter$module == null ? this.ConsoleWriter$lzycompute() : this.ConsoleWriter$module;
    }

    @Override
    public Printers.FootnoteIndex scala$reflect$internal$Printers$$footnoteIndex() {
        return this.scala$reflect$internal$Printers$$footnoteIndex;
    }

    @Override
    public void scala$reflect$internal$Printers$_setter_$scala$reflect$internal$Printers$$footnoteIndex_$eq(Printers.FootnoteIndex x$1) {
        this.scala$reflect$internal$Printers$$footnoteIndex = x$1;
    }

    @Override
    public String quotedName(Names.Name name, boolean decode) {
        return Printers$class.quotedName(this, name, decode);
    }

    @Override
    public String quotedName(Names.Name name) {
        return Printers$class.quotedName(this, name);
    }

    @Override
    public String quotedName(String name) {
        return Printers$class.quotedName(this, name);
    }

    @Override
    public String decodedSymName(Trees.Tree tree, Names.Name name) {
        return Printers$class.decodedSymName(this, tree, name);
    }

    @Override
    public String symName(Trees.Tree tree, Names.Name name) {
        return Printers$class.symName(this, tree, name);
    }

    @Override
    public String backquotedPath(Trees.Tree t) {
        return Printers$class.backquotedPath(this, t);
    }

    @Override
    public void xprintTree(Printers.TreePrinter treePrinter, Trees.Tree tree) {
        Printers$class.xprintTree(this, treePrinter, tree);
    }

    @Override
    public Printers.TreePrinter newCodePrinter(PrintWriter writer, Trees.Tree tree, boolean printRootPkg) {
        return Printers$class.newCodePrinter(this, writer, tree, printRootPkg);
    }

    @Override
    public Printers.TreePrinter newTreePrinter(PrintWriter writer) {
        return Printers$class.newTreePrinter(this, writer);
    }

    @Override
    public Printers.TreePrinter newTreePrinter(OutputStream stream) {
        return Printers$class.newTreePrinter(this, stream);
    }

    @Override
    public Printers.TreePrinter newTreePrinter() {
        return Printers$class.newTreePrinter(this);
    }

    @Override
    public Printers.RawTreePrinter newRawTreePrinter(PrintWriter writer) {
        return Printers$class.newRawTreePrinter(this, writer);
    }

    @Override
    public String show(Names.Name name) {
        return Printers$class.show(this, name);
    }

    @Override
    public String show(long flags) {
        return Printers$class.show(this, flags);
    }

    @Override
    public String show(Position position) {
        return Printers$class.show(this, position);
    }

    @Override
    public String showDecl(Symbols.Symbol sym) {
        return Printers$class.showDecl(this, sym);
    }

    @Override
    public int nodeCount() {
        return this.nodeCount;
    }

    @Override
    public void nodeCount_$eq(int x$1) {
        this.nodeCount = x$1;
    }

    private Trees$RefTree$ RefTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.RefTree$module == null) {
                this.RefTree$module = new Trees$RefTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RefTree$module;
        }
    }

    @Override
    public Trees$RefTree$ RefTree() {
        return this.RefTree$module == null ? this.RefTree$lzycompute() : this.RefTree$module;
    }

    private Trees$PackageDef$ PackageDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.PackageDef$module == null) {
                this.PackageDef$module = new Trees$PackageDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.PackageDef$module;
        }
    }

    @Override
    public Trees$PackageDef$ PackageDef() {
        return this.PackageDef$module == null ? this.PackageDef$lzycompute() : this.PackageDef$module;
    }

    private Trees$ClassDef$ ClassDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ClassDef$module == null) {
                this.ClassDef$module = new Trees$ClassDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ClassDef$module;
        }
    }

    @Override
    public Trees$ClassDef$ ClassDef() {
        return this.ClassDef$module == null ? this.ClassDef$lzycompute() : this.ClassDef$module;
    }

    private Trees$ModuleDef$ ModuleDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ModuleDef$module == null) {
                this.ModuleDef$module = new Trees$ModuleDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ModuleDef$module;
        }
    }

    @Override
    public Trees$ModuleDef$ ModuleDef() {
        return this.ModuleDef$module == null ? this.ModuleDef$lzycompute() : this.ModuleDef$module;
    }

    private Trees$ValOrDefDef$ ValOrDefDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ValOrDefDef$module == null) {
                this.ValOrDefDef$module = new Trees$ValOrDefDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValOrDefDef$module;
        }
    }

    @Override
    public Trees$ValOrDefDef$ ValOrDefDef() {
        return this.ValOrDefDef$module == null ? this.ValOrDefDef$lzycompute() : this.ValOrDefDef$module;
    }

    private Trees$ValDef$ ValDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ValDef$module == null) {
                this.ValDef$module = new Trees$ValDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValDef$module;
        }
    }

    @Override
    public Trees$ValDef$ ValDef() {
        return this.ValDef$module == null ? this.ValDef$lzycompute() : this.ValDef$module;
    }

    private Trees$DefDef$ DefDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.DefDef$module == null) {
                this.DefDef$module = new Trees$DefDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.DefDef$module;
        }
    }

    @Override
    public Trees$DefDef$ DefDef() {
        return this.DefDef$module == null ? this.DefDef$lzycompute() : this.DefDef$module;
    }

    private Trees$TypeDef$ TypeDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeDef$module == null) {
                this.TypeDef$module = new Trees$TypeDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeDef$module;
        }
    }

    @Override
    public Trees$TypeDef$ TypeDef() {
        return this.TypeDef$module == null ? this.TypeDef$lzycompute() : this.TypeDef$module;
    }

    private Trees$LabelDef$ LabelDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LabelDef$module == null) {
                this.LabelDef$module = new Trees$LabelDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LabelDef$module;
        }
    }

    @Override
    public Trees$LabelDef$ LabelDef() {
        return this.LabelDef$module == null ? this.LabelDef$lzycompute() : this.LabelDef$module;
    }

    private Trees$ImportSelector$ ImportSelector$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ImportSelector$module == null) {
                this.ImportSelector$module = new Trees$ImportSelector$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ImportSelector$module;
        }
    }

    @Override
    public Trees$ImportSelector$ ImportSelector() {
        return this.ImportSelector$module == null ? this.ImportSelector$lzycompute() : this.ImportSelector$module;
    }

    private Trees$Import$ Import$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Import$module == null) {
                this.Import$module = new Trees$Import$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Import$module;
        }
    }

    @Override
    public Trees$Import$ Import() {
        return this.Import$module == null ? this.Import$lzycompute() : this.Import$module;
    }

    private Trees$Template$ Template$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Template$module == null) {
                this.Template$module = new Trees$Template$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Template$module;
        }
    }

    @Override
    public Trees$Template$ Template() {
        return this.Template$module == null ? this.Template$lzycompute() : this.Template$module;
    }

    private Trees$Block$ Block$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Block$module == null) {
                this.Block$module = new Trees$Block$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Block$module;
        }
    }

    @Override
    public Trees$Block$ Block() {
        return this.Block$module == null ? this.Block$lzycompute() : this.Block$module;
    }

    private Trees$CaseDef$ CaseDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.CaseDef$module == null) {
                this.CaseDef$module = new Trees$CaseDef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.CaseDef$module;
        }
    }

    @Override
    public Trees$CaseDef$ CaseDef() {
        return this.CaseDef$module == null ? this.CaseDef$lzycompute() : this.CaseDef$module;
    }

    private Trees$Alternative$ Alternative$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Alternative$module == null) {
                this.Alternative$module = new Trees$Alternative$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Alternative$module;
        }
    }

    @Override
    public Trees$Alternative$ Alternative() {
        return this.Alternative$module == null ? this.Alternative$lzycompute() : this.Alternative$module;
    }

    private Trees$Star$ Star$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Star$module == null) {
                this.Star$module = new Trees$Star$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Star$module;
        }
    }

    @Override
    public Trees$Star$ Star() {
        return this.Star$module == null ? this.Star$lzycompute() : this.Star$module;
    }

    private Trees$Bind$ Bind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Bind$module == null) {
                this.Bind$module = new Trees$Bind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Bind$module;
        }
    }

    @Override
    public Trees$Bind$ Bind() {
        return this.Bind$module == null ? this.Bind$lzycompute() : this.Bind$module;
    }

    private Trees$UnApply$ UnApply$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.UnApply$module == null) {
                this.UnApply$module = new Trees$UnApply$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.UnApply$module;
        }
    }

    @Override
    public Trees$UnApply$ UnApply() {
        return this.UnApply$module == null ? this.UnApply$lzycompute() : this.UnApply$module;
    }

    private Trees$ArrayValue$ ArrayValue$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ArrayValue$module == null) {
                this.ArrayValue$module = new Trees$ArrayValue$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ArrayValue$module;
        }
    }

    @Override
    public Trees$ArrayValue$ ArrayValue() {
        return this.ArrayValue$module == null ? this.ArrayValue$lzycompute() : this.ArrayValue$module;
    }

    private Trees$Function$ Function$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Function$module == null) {
                this.Function$module = new Trees$Function$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Function$module;
        }
    }

    @Override
    public Trees$Function$ Function() {
        return this.Function$module == null ? this.Function$lzycompute() : this.Function$module;
    }

    private Trees$Assign$ Assign$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Assign$module == null) {
                this.Assign$module = new Trees$Assign$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Assign$module;
        }
    }

    @Override
    public Trees$Assign$ Assign() {
        return this.Assign$module == null ? this.Assign$lzycompute() : this.Assign$module;
    }

    private Trees$AssignOrNamedArg$ AssignOrNamedArg$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.AssignOrNamedArg$module == null) {
                this.AssignOrNamedArg$module = new Trees$AssignOrNamedArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.AssignOrNamedArg$module;
        }
    }

    @Override
    public Trees$AssignOrNamedArg$ AssignOrNamedArg() {
        return this.AssignOrNamedArg$module == null ? this.AssignOrNamedArg$lzycompute() : this.AssignOrNamedArg$module;
    }

    private Trees$If$ If$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.If$module == null) {
                this.If$module = new Trees$If$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.If$module;
        }
    }

    @Override
    public Trees$If$ If() {
        return this.If$module == null ? this.If$lzycompute() : this.If$module;
    }

    private Trees$Match$ Match$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Match$module == null) {
                this.Match$module = new Trees$Match$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Match$module;
        }
    }

    @Override
    public Trees$Match$ Match() {
        return this.Match$module == null ? this.Match$lzycompute() : this.Match$module;
    }

    private Trees$Return$ Return$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Return$module == null) {
                this.Return$module = new Trees$Return$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Return$module;
        }
    }

    @Override
    public Trees$Return$ Return() {
        return this.Return$module == null ? this.Return$lzycompute() : this.Return$module;
    }

    private Trees$Try$ Try$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Try$module == null) {
                this.Try$module = new Trees$Try$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Try$module;
        }
    }

    @Override
    public Trees$Try$ Try() {
        return this.Try$module == null ? this.Try$lzycompute() : this.Try$module;
    }

    private Trees$Throw$ Throw$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Throw$module == null) {
                this.Throw$module = new Trees$Throw$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Throw$module;
        }
    }

    @Override
    public Trees$Throw$ Throw() {
        return this.Throw$module == null ? this.Throw$lzycompute() : this.Throw$module;
    }

    private Trees$New$ New$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.New$module == null) {
                this.New$module = new Trees$New$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.New$module;
        }
    }

    @Override
    public Trees$New$ New() {
        return this.New$module == null ? this.New$lzycompute() : this.New$module;
    }

    private Trees$Typed$ Typed$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Typed$module == null) {
                this.Typed$module = new Trees$Typed$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Typed$module;
        }
    }

    @Override
    public Trees$Typed$ Typed() {
        return this.Typed$module == null ? this.Typed$lzycompute() : this.Typed$module;
    }

    private Trees$TypeApply$ TypeApply$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeApply$module == null) {
                this.TypeApply$module = new Trees$TypeApply$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeApply$module;
        }
    }

    @Override
    public Trees$TypeApply$ TypeApply() {
        return this.TypeApply$module == null ? this.TypeApply$lzycompute() : this.TypeApply$module;
    }

    private Trees$Apply$ Apply$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Apply$module == null) {
                this.Apply$module = new Trees$Apply$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Apply$module;
        }
    }

    @Override
    public Trees$Apply$ Apply() {
        return this.Apply$module == null ? this.Apply$lzycompute() : this.Apply$module;
    }

    private Trees$ApplyDynamic$ ApplyDynamic$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ApplyDynamic$module == null) {
                this.ApplyDynamic$module = new Trees$ApplyDynamic$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ApplyDynamic$module;
        }
    }

    @Override
    public Trees$ApplyDynamic$ ApplyDynamic() {
        return this.ApplyDynamic$module == null ? this.ApplyDynamic$lzycompute() : this.ApplyDynamic$module;
    }

    private Trees$Super$ Super$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Super$module == null) {
                this.Super$module = new Trees$Super$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Super$module;
        }
    }

    @Override
    public Trees$Super$ Super() {
        return this.Super$module == null ? this.Super$lzycompute() : this.Super$module;
    }

    private Trees$This$ This$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.This$module == null) {
                this.This$module = new Trees$This$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.This$module;
        }
    }

    @Override
    public Trees$This$ This() {
        return this.This$module == null ? this.This$lzycompute() : this.This$module;
    }

    private Trees$Select$ Select$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Select$module == null) {
                this.Select$module = new Trees$Select$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Select$module;
        }
    }

    @Override
    public Trees$Select$ Select() {
        return this.Select$module == null ? this.Select$lzycompute() : this.Select$module;
    }

    private Trees$Ident$ Ident$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Ident$module == null) {
                this.Ident$module = new Trees$Ident$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Ident$module;
        }
    }

    @Override
    public Trees$Ident$ Ident() {
        return this.Ident$module == null ? this.Ident$lzycompute() : this.Ident$module;
    }

    private Trees$ReferenceToBoxed$ ReferenceToBoxed$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ReferenceToBoxed$module == null) {
                this.ReferenceToBoxed$module = new Trees$ReferenceToBoxed$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ReferenceToBoxed$module;
        }
    }

    @Override
    public Trees$ReferenceToBoxed$ ReferenceToBoxed() {
        return this.ReferenceToBoxed$module == null ? this.ReferenceToBoxed$lzycompute() : this.ReferenceToBoxed$module;
    }

    private Trees$Literal$ Literal$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Literal$module == null) {
                this.Literal$module = new Trees$Literal$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Literal$module;
        }
    }

    @Override
    public Trees$Literal$ Literal() {
        return this.Literal$module == null ? this.Literal$lzycompute() : this.Literal$module;
    }

    private Trees$Annotated$ Annotated$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Annotated$module == null) {
                this.Annotated$module = new Trees$Annotated$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Annotated$module;
        }
    }

    @Override
    public Trees$Annotated$ Annotated() {
        return this.Annotated$module == null ? this.Annotated$lzycompute() : this.Annotated$module;
    }

    private Trees$SingletonTypeTree$ SingletonTypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SingletonTypeTree$module == null) {
                this.SingletonTypeTree$module = new Trees$SingletonTypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SingletonTypeTree$module;
        }
    }

    @Override
    public Trees$SingletonTypeTree$ SingletonTypeTree() {
        return this.SingletonTypeTree$module == null ? this.SingletonTypeTree$lzycompute() : this.SingletonTypeTree$module;
    }

    private Trees$SelectFromTypeTree$ SelectFromTypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SelectFromTypeTree$module == null) {
                this.SelectFromTypeTree$module = new Trees$SelectFromTypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SelectFromTypeTree$module;
        }
    }

    @Override
    public Trees$SelectFromTypeTree$ SelectFromTypeTree() {
        return this.SelectFromTypeTree$module == null ? this.SelectFromTypeTree$lzycompute() : this.SelectFromTypeTree$module;
    }

    private Trees$CompoundTypeTree$ CompoundTypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.CompoundTypeTree$module == null) {
                this.CompoundTypeTree$module = new Trees$CompoundTypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.CompoundTypeTree$module;
        }
    }

    @Override
    public Trees$CompoundTypeTree$ CompoundTypeTree() {
        return this.CompoundTypeTree$module == null ? this.CompoundTypeTree$lzycompute() : this.CompoundTypeTree$module;
    }

    private Trees$AppliedTypeTree$ AppliedTypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.AppliedTypeTree$module == null) {
                this.AppliedTypeTree$module = new Trees$AppliedTypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.AppliedTypeTree$module;
        }
    }

    @Override
    public Trees$AppliedTypeTree$ AppliedTypeTree() {
        return this.AppliedTypeTree$module == null ? this.AppliedTypeTree$lzycompute() : this.AppliedTypeTree$module;
    }

    private Trees$TypeBoundsTree$ TypeBoundsTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeBoundsTree$module == null) {
                this.TypeBoundsTree$module = new Trees$TypeBoundsTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeBoundsTree$module;
        }
    }

    @Override
    public Trees$TypeBoundsTree$ TypeBoundsTree() {
        return this.TypeBoundsTree$module == null ? this.TypeBoundsTree$lzycompute() : this.TypeBoundsTree$module;
    }

    private Trees$ExistentialTypeTree$ ExistentialTypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ExistentialTypeTree$module == null) {
                this.ExistentialTypeTree$module = new Trees$ExistentialTypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ExistentialTypeTree$module;
        }
    }

    @Override
    public Trees$ExistentialTypeTree$ ExistentialTypeTree() {
        return this.ExistentialTypeTree$module == null ? this.ExistentialTypeTree$lzycompute() : this.ExistentialTypeTree$module;
    }

    private Trees$TypeTree$ TypeTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeTree$module == null) {
                this.TypeTree$module = new Trees$TypeTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeTree$module;
        }
    }

    @Override
    public Trees$TypeTree$ TypeTree() {
        return this.TypeTree$module == null ? this.TypeTree$lzycompute() : this.TypeTree$module;
    }

    private Trees$Modifiers$ Modifiers$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Modifiers$module == null) {
                this.Modifiers$module = new Trees$Modifiers$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Modifiers$module;
        }
    }

    @Override
    public Trees$Modifiers$ Modifiers() {
        return this.Modifiers$module == null ? this.Modifiers$lzycompute() : this.Modifiers$module;
    }

    @Override
    public ClassTag<Trees.Modifiers> ModifiersTag() {
        return this.ModifiersTag;
    }

    private Trees$EmptyTree$ EmptyTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.EmptyTree$module == null) {
                this.EmptyTree$module = new Trees$EmptyTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.EmptyTree$module;
        }
    }

    @Override
    public Trees$EmptyTree$ EmptyTree() {
        return this.EmptyTree$module == null ? this.EmptyTree$lzycompute() : this.EmptyTree$module;
    }

    private Trees$noSelfType$ noSelfType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.noSelfType$module == null) {
                this.noSelfType$module = new Trees$noSelfType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.noSelfType$module;
        }
    }

    @Override
    public Trees$noSelfType$ noSelfType() {
        return this.noSelfType$module == null ? this.noSelfType$lzycompute() : this.noSelfType$module;
    }

    private Trees$pendingSuperCall$ pendingSuperCall$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.pendingSuperCall$module == null) {
                this.pendingSuperCall$module = new Trees$pendingSuperCall$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.pendingSuperCall$module;
        }
    }

    @Override
    public Trees$pendingSuperCall$ pendingSuperCall() {
        return this.pendingSuperCall$module == null ? this.pendingSuperCall$lzycompute() : this.pendingSuperCall$module;
    }

    private Trees$noSelfType$ emptyValDef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x20) == 0) {
                this.emptyValDef = Trees$class.emptyValDef(this);
                this.bitmap$0 |= 0x20;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.emptyValDef;
        }
    }

    @Override
    public Trees$noSelfType$ emptyValDef() {
        return (this.bitmap$0 & 0x20) == 0 ? this.emptyValDef$lzycompute() : this.emptyValDef;
    }

    private Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x40) == 0) {
                this.EmptyTreeTypeSubstituter = Trees$class.EmptyTreeTypeSubstituter(this);
                this.bitmap$0 |= 0x40;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.EmptyTreeTypeSubstituter;
        }
    }

    @Override
    public Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter() {
        return (this.bitmap$0 & 0x40) == 0 ? this.EmptyTreeTypeSubstituter$lzycompute() : this.EmptyTreeTypeSubstituter;
    }

    private Trees.Duplicator scala$reflect$internal$Trees$$duplicator$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x80) == 0) {
                this.scala$reflect$internal$Trees$$duplicator = Trees$class.scala$reflect$internal$Trees$$duplicator(this);
                this.bitmap$0 |= 0x80;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$Trees$$duplicator;
        }
    }

    @Override
    public Trees.Duplicator scala$reflect$internal$Trees$$duplicator() {
        return (this.bitmap$0 & 0x80) == 0 ? this.scala$reflect$internal$Trees$$duplicator$lzycompute() : this.scala$reflect$internal$Trees$$duplicator;
    }

    @Override
    public ClassTag<Trees.Alternative> AlternativeTag() {
        return this.AlternativeTag;
    }

    @Override
    public ClassTag<Trees.Annotated> AnnotatedTag() {
        return this.AnnotatedTag;
    }

    @Override
    public ClassTag<Trees.AppliedTypeTree> AppliedTypeTreeTag() {
        return this.AppliedTypeTreeTag;
    }

    @Override
    public ClassTag<Trees.Apply> ApplyTag() {
        return this.ApplyTag;
    }

    @Override
    public ClassTag<Trees.AssignOrNamedArg> AssignOrNamedArgTag() {
        return this.AssignOrNamedArgTag;
    }

    @Override
    public ClassTag<Trees.Assign> AssignTag() {
        return this.AssignTag;
    }

    @Override
    public ClassTag<Trees.Bind> BindTag() {
        return this.BindTag;
    }

    @Override
    public ClassTag<Trees.Block> BlockTag() {
        return this.BlockTag;
    }

    @Override
    public ClassTag<Trees.CaseDef> CaseDefTag() {
        return this.CaseDefTag;
    }

    @Override
    public ClassTag<Trees.ClassDef> ClassDefTag() {
        return this.ClassDefTag;
    }

    @Override
    public ClassTag<Trees.CompoundTypeTree> CompoundTypeTreeTag() {
        return this.CompoundTypeTreeTag;
    }

    @Override
    public ClassTag<Trees.DefDef> DefDefTag() {
        return this.DefDefTag;
    }

    @Override
    public ClassTag<Trees.DefTree> DefTreeTag() {
        return this.DefTreeTag;
    }

    @Override
    public ClassTag<Trees.ExistentialTypeTree> ExistentialTypeTreeTag() {
        return this.ExistentialTypeTreeTag;
    }

    @Override
    public ClassTag<Trees.Function> FunctionTag() {
        return this.FunctionTag;
    }

    @Override
    public ClassTag<Trees.GenericApply> GenericApplyTag() {
        return this.GenericApplyTag;
    }

    @Override
    public ClassTag<Trees.Ident> IdentTag() {
        return this.IdentTag;
    }

    @Override
    public ClassTag<Trees.If> IfTag() {
        return this.IfTag;
    }

    @Override
    public ClassTag<Trees.ImplDef> ImplDefTag() {
        return this.ImplDefTag;
    }

    @Override
    public ClassTag<Trees.ImportSelector> ImportSelectorTag() {
        return this.ImportSelectorTag;
    }

    @Override
    public ClassTag<Trees.Import> ImportTag() {
        return this.ImportTag;
    }

    @Override
    public ClassTag<Trees.LabelDef> LabelDefTag() {
        return this.LabelDefTag;
    }

    @Override
    public ClassTag<Trees.Literal> LiteralTag() {
        return this.LiteralTag;
    }

    @Override
    public ClassTag<Trees.Match> MatchTag() {
        return this.MatchTag;
    }

    @Override
    public ClassTag<Trees.MemberDef> MemberDefTag() {
        return this.MemberDefTag;
    }

    @Override
    public ClassTag<Trees.ModuleDef> ModuleDefTag() {
        return this.ModuleDefTag;
    }

    @Override
    public ClassTag<Trees.NameTree> NameTreeTag() {
        return this.NameTreeTag;
    }

    @Override
    public ClassTag<Trees.New> NewTag() {
        return this.NewTag;
    }

    @Override
    public ClassTag<Trees.PackageDef> PackageDefTag() {
        return this.PackageDefTag;
    }

    @Override
    public ClassTag<Trees.ReferenceToBoxed> ReferenceToBoxedTag() {
        return this.ReferenceToBoxedTag;
    }

    @Override
    public ClassTag<Trees.RefTree> RefTreeTag() {
        return this.RefTreeTag;
    }

    @Override
    public ClassTag<Trees.Return> ReturnTag() {
        return this.ReturnTag;
    }

    @Override
    public ClassTag<Trees.SelectFromTypeTree> SelectFromTypeTreeTag() {
        return this.SelectFromTypeTreeTag;
    }

    @Override
    public ClassTag<Trees.Select> SelectTag() {
        return this.SelectTag;
    }

    @Override
    public ClassTag<Trees.SingletonTypeTree> SingletonTypeTreeTag() {
        return this.SingletonTypeTreeTag;
    }

    @Override
    public ClassTag<Trees.Star> StarTag() {
        return this.StarTag;
    }

    @Override
    public ClassTag<Trees.Super> SuperTag() {
        return this.SuperTag;
    }

    @Override
    public ClassTag<Trees.SymTree> SymTreeTag() {
        return this.SymTreeTag;
    }

    @Override
    public ClassTag<Trees.Template> TemplateTag() {
        return this.TemplateTag;
    }

    @Override
    public ClassTag<Trees.TermTree> TermTreeTag() {
        return this.TermTreeTag;
    }

    @Override
    public ClassTag<Trees.This> ThisTag() {
        return this.ThisTag;
    }

    @Override
    public ClassTag<Trees.Throw> ThrowTag() {
        return this.ThrowTag;
    }

    @Override
    public ClassTag<Trees.Tree> TreeTag() {
        return this.TreeTag;
    }

    @Override
    public ClassTag<Trees.Try> TryTag() {
        return this.TryTag;
    }

    @Override
    public ClassTag<Trees.TypTree> TypTreeTag() {
        return this.TypTreeTag;
    }

    @Override
    public ClassTag<Trees.TypeApply> TypeApplyTag() {
        return this.TypeApplyTag;
    }

    @Override
    public ClassTag<Trees.TypeBoundsTree> TypeBoundsTreeTag() {
        return this.TypeBoundsTreeTag;
    }

    @Override
    public ClassTag<Trees.TypeDef> TypeDefTag() {
        return this.TypeDefTag;
    }

    @Override
    public ClassTag<Trees.TypeTree> TypeTreeTag() {
        return this.TypeTreeTag;
    }

    @Override
    public ClassTag<Trees.Typed> TypedTag() {
        return this.TypedTag;
    }

    @Override
    public ClassTag<Trees.UnApply> UnApplyTag() {
        return this.UnApplyTag;
    }

    @Override
    public ClassTag<Trees.ValDef> ValDefTag() {
        return this.ValDefTag;
    }

    @Override
    public ClassTag<Trees.ValOrDefDef> ValOrDefDefTag() {
        return this.ValOrDefDefTag;
    }

    @Override
    public Statistics.View treeNodeCount() {
        return this.treeNodeCount;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ModifiersTag_$eq(ClassTag x$1) {
        this.ModifiersTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$AlternativeTag_$eq(ClassTag x$1) {
        this.AlternativeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$AnnotatedTag_$eq(ClassTag x$1) {
        this.AnnotatedTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$AppliedTypeTreeTag_$eq(ClassTag x$1) {
        this.AppliedTypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ApplyTag_$eq(ClassTag x$1) {
        this.ApplyTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$AssignOrNamedArgTag_$eq(ClassTag x$1) {
        this.AssignOrNamedArgTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$AssignTag_$eq(ClassTag x$1) {
        this.AssignTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$BindTag_$eq(ClassTag x$1) {
        this.BindTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$BlockTag_$eq(ClassTag x$1) {
        this.BlockTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$CaseDefTag_$eq(ClassTag x$1) {
        this.CaseDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ClassDefTag_$eq(ClassTag x$1) {
        this.ClassDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$CompoundTypeTreeTag_$eq(ClassTag x$1) {
        this.CompoundTypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$DefDefTag_$eq(ClassTag x$1) {
        this.DefDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$DefTreeTag_$eq(ClassTag x$1) {
        this.DefTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ExistentialTypeTreeTag_$eq(ClassTag x$1) {
        this.ExistentialTypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$FunctionTag_$eq(ClassTag x$1) {
        this.FunctionTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$GenericApplyTag_$eq(ClassTag x$1) {
        this.GenericApplyTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$IdentTag_$eq(ClassTag x$1) {
        this.IdentTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$IfTag_$eq(ClassTag x$1) {
        this.IfTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ImplDefTag_$eq(ClassTag x$1) {
        this.ImplDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ImportSelectorTag_$eq(ClassTag x$1) {
        this.ImportSelectorTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ImportTag_$eq(ClassTag x$1) {
        this.ImportTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$LabelDefTag_$eq(ClassTag x$1) {
        this.LabelDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$LiteralTag_$eq(ClassTag x$1) {
        this.LiteralTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$MatchTag_$eq(ClassTag x$1) {
        this.MatchTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$MemberDefTag_$eq(ClassTag x$1) {
        this.MemberDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ModuleDefTag_$eq(ClassTag x$1) {
        this.ModuleDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$NameTreeTag_$eq(ClassTag x$1) {
        this.NameTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$NewTag_$eq(ClassTag x$1) {
        this.NewTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$PackageDefTag_$eq(ClassTag x$1) {
        this.PackageDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ReferenceToBoxedTag_$eq(ClassTag x$1) {
        this.ReferenceToBoxedTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$RefTreeTag_$eq(ClassTag x$1) {
        this.RefTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ReturnTag_$eq(ClassTag x$1) {
        this.ReturnTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$SelectFromTypeTreeTag_$eq(ClassTag x$1) {
        this.SelectFromTypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$SelectTag_$eq(ClassTag x$1) {
        this.SelectTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$SingletonTypeTreeTag_$eq(ClassTag x$1) {
        this.SingletonTypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$StarTag_$eq(ClassTag x$1) {
        this.StarTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$SuperTag_$eq(ClassTag x$1) {
        this.SuperTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$SymTreeTag_$eq(ClassTag x$1) {
        this.SymTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TemplateTag_$eq(ClassTag x$1) {
        this.TemplateTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TermTreeTag_$eq(ClassTag x$1) {
        this.TermTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ThisTag_$eq(ClassTag x$1) {
        this.ThisTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ThrowTag_$eq(ClassTag x$1) {
        this.ThrowTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TreeTag_$eq(ClassTag x$1) {
        this.TreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TryTag_$eq(ClassTag x$1) {
        this.TryTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypTreeTag_$eq(ClassTag x$1) {
        this.TypTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypeApplyTag_$eq(ClassTag x$1) {
        this.TypeApplyTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypeBoundsTreeTag_$eq(ClassTag x$1) {
        this.TypeBoundsTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypeDefTag_$eq(ClassTag x$1) {
        this.TypeDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypeTreeTag_$eq(ClassTag x$1) {
        this.TypeTreeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$TypedTag_$eq(ClassTag x$1) {
        this.TypedTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$UnApplyTag_$eq(ClassTag x$1) {
        this.UnApplyTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ValDefTag_$eq(ClassTag x$1) {
        this.ValDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$ValOrDefDefTag_$eq(ClassTag x$1) {
        this.ValOrDefDefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Trees$_setter_$treeNodeCount_$eq(Statistics.View x$1) {
        this.treeNodeCount = x$1;
    }

    @Override
    public String treeLine(Trees.Tree t) {
        return Trees$class.treeLine(this, t);
    }

    @Override
    public String treeStatus(Trees.Tree t, Trees.Tree enclosingTree) {
        return Trees$class.treeStatus(this, t, enclosingTree);
    }

    @Override
    public String treeSymStatus(Trees.Tree t) {
        return Trees$class.treeSymStatus(this, t);
    }

    @Override
    public Trees.Apply ApplyConstructor(Trees.Tree tpt, List<Trees.Tree> args) {
        return Trees$class.ApplyConstructor(this, tpt, args);
    }

    @Override
    public Trees.Apply NewFromConstructor(Symbols.Symbol constructor, Seq<Trees.Tree> args) {
        return Trees$class.NewFromConstructor(this, constructor, args);
    }

    @Override
    public Trees.TypeTree TypeTree(Types.Type tp) {
        return Trees$class.TypeTree(this, tp);
    }

    @Override
    public Trees.TypeBoundsTree TypeBoundsTree(Types.TypeBounds bounds) {
        return Trees$class.TypeBoundsTree(this, bounds);
    }

    @Override
    public Trees.TypeBoundsTree TypeBoundsTree(Symbols.Symbol sym) {
        return Trees$class.TypeBoundsTree(this, sym);
    }

    @Override
    public boolean isReferenceToScalaMember(Trees.Tree t, Names.Name Id) {
        return Trees$class.isReferenceToScalaMember(this, t, Id);
    }

    @Override
    public boolean isReferenceToPredef(Trees.Tree t) {
        return Trees$class.isReferenceToPredef(this, t);
    }

    @Override
    public Trees.Template Template(Symbols.Symbol sym, List<Trees.Tree> body2) {
        return Trees$class.Template(this, sym, body2);
    }

    @Override
    public Trees.ValDef newValDef(Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TermName name, Trees.Tree tpt) {
        return Trees$class.newValDef(this, sym, rhs, mods, name, tpt);
    }

    @Override
    public Trees.DefDef newDefDef(Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TermName name, List<Trees.TypeDef> tparams2, List<List<Trees.ValDef>> vparamss, Trees.Tree tpt) {
        return Trees$class.newDefDef(this, sym, rhs, mods, name, tparams2, vparamss, tpt);
    }

    @Override
    public Trees.TypeDef newTypeDef(Symbols.Symbol sym, Trees.Tree rhs, Trees.Modifiers mods, Names.TypeName name, List<Trees.TypeDef> tparams2) {
        return Trees$class.newTypeDef(this, sym, rhs, mods, name, tparams2);
    }

    @Override
    public Trees.CaseDef CaseDef(Trees.Tree pat, Trees.Tree body2) {
        return Trees$class.CaseDef(this, pat, body2);
    }

    @Override
    public Trees.Bind Bind(Symbols.Symbol sym, Trees.Tree body2) {
        return Trees$class.Bind(this, sym, body2);
    }

    @Override
    public Trees.Try Try(Trees.Tree body2, Seq<Tuple2<Trees.Tree, Trees.Tree>> cases) {
        return Trees$class.Try(this, body2, cases);
    }

    @Override
    public Trees.Throw Throw(Types.Type tpe, Seq<Trees.Tree> args) {
        return Trees$class.Throw(this, tpe, args);
    }

    @Override
    public Trees.Tree Apply(Symbols.Symbol sym, Seq<Trees.Tree> args) {
        return Trees$class.Apply(this, sym, args);
    }

    @Override
    public Trees.Tree New(Trees.Tree tpt, List<List<Trees.Tree>> argss) {
        return Trees$class.New(this, tpt, argss);
    }

    @Override
    public Trees.Tree New(Types.Type tpe, Seq<Trees.Tree> args) {
        return Trees$class.New(this, tpe, args);
    }

    @Override
    public Trees.Tree New(Types.Type tpe, List<List<Trees.Tree>> argss) {
        return Trees$class.New(this, tpe, argss);
    }

    @Override
    public Trees.Tree New(Symbols.Symbol sym, Seq<Trees.Tree> args) {
        return Trees$class.New(this, sym, args);
    }

    @Override
    public Trees.Tree Super(Symbols.Symbol sym, Names.TypeName mix) {
        return Trees$class.Super(this, sym, mix);
    }

    @Override
    public Trees.Tree This(Symbols.Symbol sym) {
        return Trees$class.This(this, sym);
    }

    @Override
    public Trees.Select Select(Trees.Tree qualifier, String name) {
        return Trees$class.Select(this, qualifier, name);
    }

    @Override
    public Trees.Select Select(Trees.Tree qualifier, Symbols.Symbol sym) {
        return Trees$class.Select(this, qualifier, sym);
    }

    @Override
    public Trees.Ident Ident(String name) {
        return Trees$class.Ident(this, name);
    }

    @Override
    public Trees.Ident Ident(Symbols.Symbol sym) {
        return Trees$class.Ident(this, sym);
    }

    @Override
    public Trees.Block Block(Seq<Trees.Tree> stats) {
        return Trees$class.Block(this, stats);
    }

    @Override
    public Symbols.Symbol typeTreeSymbol(Trees.TypeTree tree) {
        return Trees$class.typeTreeSymbol(this, tree);
    }

    @Override
    public void itraverse(Trees.Traverser traverser, Trees.Tree tree) {
        Trees$class.itraverse(this, traverser, tree);
    }

    @Override
    public Trees.Tree itransform(Trees.Transformer transformer, Trees.Tree tree) {
        return Trees$class.itransform(this, transformer, tree);
    }

    @Override
    public Trees.Tree duplicateAndKeepPositions(Trees.Tree tree) {
        return Trees$class.duplicateAndKeepPositions(this, tree);
    }

    @Override
    public Trees.Tree wrappingIntoTerm(Trees.Tree tree0, Function1<Trees.Tree, Trees.Tree> op) {
        return Trees$class.wrappingIntoTerm(this, tree0, op);
    }

    @Override
    public Trees.DefDef copyDefDef(Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List<Trees.TypeDef> tparams2, List<List<Trees.ValDef>> vparamss, Trees.Tree tpt, Trees.Tree rhs) {
        return Trees$class.copyDefDef(this, tree, mods, name, tparams2, vparamss, tpt, rhs);
    }

    @Override
    public Trees.ValDef copyValDef(Trees.Tree tree, Trees.Modifiers mods, Names.Name name, Trees.Tree tpt, Trees.Tree rhs) {
        return Trees$class.copyValDef(this, tree, mods, name, tpt, rhs);
    }

    @Override
    public Trees.TypeDef copyTypeDef(Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List<Trees.TypeDef> tparams2, Trees.Tree rhs) {
        return Trees$class.copyTypeDef(this, tree, mods, name, tparams2, rhs);
    }

    @Override
    public Trees.ClassDef copyClassDef(Trees.Tree tree, Trees.Modifiers mods, Names.Name name, List<Trees.TypeDef> tparams2, Trees.Template impl) {
        return Trees$class.copyClassDef(this, tree, mods, name, tparams2, impl);
    }

    @Override
    public Trees.ModuleDef copyModuleDef(Trees.Tree tree, Trees.Modifiers mods, Names.Name name, Trees.Template impl) {
        return Trees$class.copyModuleDef(this, tree, mods, name, impl);
    }

    @Override
    public Trees.DefDef deriveDefDef(Trees.Tree ddef, Function1<Trees.Tree, Trees.Tree> applyToRhs) {
        return Trees$class.deriveDefDef(this, ddef, applyToRhs);
    }

    @Override
    public Trees.ValDef deriveValDef(Trees.Tree vdef, Function1<Trees.Tree, Trees.Tree> applyToRhs) {
        return Trees$class.deriveValDef(this, vdef, applyToRhs);
    }

    @Override
    public Trees.Template deriveTemplate(Trees.Tree templ, Function1<List<Trees.Tree>, List<Trees.Tree>> applyToBody) {
        return Trees$class.deriveTemplate(this, templ, applyToBody);
    }

    @Override
    public Trees.ClassDef deriveClassDef(Trees.Tree cdef, Function1<Trees.Template, Trees.Template> applyToImpl) {
        return Trees$class.deriveClassDef(this, cdef, applyToImpl);
    }

    @Override
    public Trees.ModuleDef deriveModuleDef(Trees.Tree mdef, Function1<Trees.Template, Trees.Template> applyToImpl) {
        return Trees$class.deriveModuleDef(this, mdef, applyToImpl);
    }

    @Override
    public Trees.CaseDef deriveCaseDef(Trees.Tree cdef, Function1<Trees.Tree, Trees.Tree> applyToBody) {
        return Trees$class.deriveCaseDef(this, cdef, applyToBody);
    }

    @Override
    public Trees.LabelDef deriveLabelDef(Trees.Tree ldef, Function1<Trees.Tree, Trees.Tree> applyToRhs) {
        return Trees$class.deriveLabelDef(this, ldef, applyToRhs);
    }

    @Override
    public Trees.Function deriveFunction(Trees.Tree func, Function1<Trees.Tree, Trees.Tree> applyToRhs) {
        return Trees$class.deriveFunction(this, func, applyToRhs);
    }

    @Override
    public Trees.Tree treeStatus$default$2() {
        return Trees$class.treeStatus$default$2(this);
    }

    @Override
    public Trees.Modifiers copyValDef$default$2(Trees.Tree tree) {
        return Trees$class.copyValDef$default$2(this, tree);
    }

    @Override
    public Names.Name copyValDef$default$3(Trees.Tree tree) {
        return Trees$class.copyValDef$default$3(this, tree);
    }

    @Override
    public Trees.Tree copyValDef$default$4(Trees.Tree tree) {
        return Trees$class.copyValDef$default$4(this, tree);
    }

    @Override
    public Trees.Tree copyValDef$default$5(Trees.Tree tree) {
        return Trees$class.copyValDef$default$5(this, tree);
    }

    @Override
    public Trees.Modifiers copyTypeDef$default$2(Trees.Tree tree) {
        return Trees$class.copyTypeDef$default$2(this, tree);
    }

    @Override
    public Names.Name copyTypeDef$default$3(Trees.Tree tree) {
        return Trees$class.copyTypeDef$default$3(this, tree);
    }

    @Override
    public List<Trees.TypeDef> copyTypeDef$default$4(Trees.Tree tree) {
        return Trees$class.copyTypeDef$default$4(this, tree);
    }

    @Override
    public Trees.Tree copyTypeDef$default$5(Trees.Tree tree) {
        return Trees$class.copyTypeDef$default$5(this, tree);
    }

    @Override
    public Trees.Modifiers copyModuleDef$default$2(Trees.Tree tree) {
        return Trees$class.copyModuleDef$default$2(this, tree);
    }

    @Override
    public Names.Name copyModuleDef$default$3(Trees.Tree tree) {
        return Trees$class.copyModuleDef$default$3(this, tree);
    }

    @Override
    public Trees.Template copyModuleDef$default$4(Trees.Tree tree) {
        return Trees$class.copyModuleDef$default$4(this, tree);
    }

    @Override
    public Trees.Modifiers newValDef$default$3(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newValDef$default$3(this, sym, rhs);
    }

    @Override
    public Names.TermName newValDef$default$4(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newValDef$default$4(this, sym, rhs);
    }

    @Override
    public Trees.Tree newValDef$default$5(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newValDef$default$5(this, sym, rhs);
    }

    @Override
    public Trees.Modifiers newDefDef$default$3(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newDefDef$default$3(this, sym, rhs);
    }

    @Override
    public Names.TermName newDefDef$default$4(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newDefDef$default$4(this, sym, rhs);
    }

    @Override
    public List<Trees.TypeDef> newDefDef$default$5(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newDefDef$default$5(this, sym, rhs);
    }

    @Override
    public List<List<Trees.ValDef>> newDefDef$default$6(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newDefDef$default$6(this, sym, rhs);
    }

    @Override
    public Trees.Tree newDefDef$default$7(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newDefDef$default$7(this, sym, rhs);
    }

    @Override
    public Trees.Modifiers newTypeDef$default$3(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newTypeDef$default$3(this, sym, rhs);
    }

    @Override
    public Names.TypeName newTypeDef$default$4(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newTypeDef$default$4(this, sym, rhs);
    }

    @Override
    public List<Trees.TypeDef> newTypeDef$default$5(Symbols.Symbol sym, Trees.Tree rhs) {
        return Trees$class.newTypeDef$default$5(this, sym, rhs);
    }

    @Override
    public Trees.Modifiers copyDefDef$default$2(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$2(this, tree);
    }

    @Override
    public Names.Name copyDefDef$default$3(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$3(this, tree);
    }

    @Override
    public List<Trees.TypeDef> copyDefDef$default$4(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$4(this, tree);
    }

    @Override
    public List<List<Trees.ValDef>> copyDefDef$default$5(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$5(this, tree);
    }

    @Override
    public Trees.Tree copyDefDef$default$6(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$6(this, tree);
    }

    @Override
    public Trees.Tree copyDefDef$default$7(Trees.Tree tree) {
        return Trees$class.copyDefDef$default$7(this, tree);
    }

    @Override
    public Trees.Modifiers copyClassDef$default$2(Trees.Tree tree) {
        return Trees$class.copyClassDef$default$2(this, tree);
    }

    @Override
    public Names.Name copyClassDef$default$3(Trees.Tree tree) {
        return Trees$class.copyClassDef$default$3(this, tree);
    }

    @Override
    public List<Trees.TypeDef> copyClassDef$default$4(Trees.Tree tree) {
        return Trees$class.copyClassDef$default$4(this, tree);
    }

    @Override
    public Trees.Template copyClassDef$default$5(Trees.Tree tree) {
        return Trees$class.copyClassDef$default$5(this, tree);
    }

    @Override
    public List<AnnotationCheckers.AnnotationChecker> scala$reflect$internal$AnnotationCheckers$$annotationCheckers() {
        return this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers;
    }

    @Override
    @TraitSetter
    public void scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(List<AnnotationCheckers.AnnotationChecker> x$1) {
        this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers = x$1;
    }

    @Override
    public void addAnnotationChecker(AnnotationCheckers.AnnotationChecker checker) {
        AnnotationCheckers$class.addAnnotationChecker(this, checker);
    }

    @Override
    public void removeAllAnnotationCheckers() {
        AnnotationCheckers$class.removeAllAnnotationCheckers(this);
    }

    @Override
    public boolean annotationsConform(Types.Type tp1, Types.Type tp2) {
        return AnnotationCheckers$class.annotationsConform(this, tp1, tp2);
    }

    @Override
    public Types.Type annotationsLub(Types.Type tpe, List<Types.Type> ts) {
        return AnnotationCheckers$class.annotationsLub(this, tpe, ts);
    }

    @Override
    public Types.Type annotationsGlb(Types.Type tpe, List<Types.Type> ts) {
        return AnnotationCheckers$class.annotationsGlb(this, tpe, ts);
    }

    @Override
    public List<Types.TypeBounds> adaptBoundsToAnnotations(List<Types.TypeBounds> bounds, List<Symbols.Symbol> tparams2, List<Types.Type> targs) {
        return AnnotationCheckers$class.adaptBoundsToAnnotations(this, bounds, tparams2, targs);
    }

    @Override
    public Types.Type addAnnotations(Trees.Tree tree, Types.Type tpe) {
        return AnnotationCheckers$class.addAnnotations(this, tree, tpe);
    }

    @Override
    public boolean canAdaptAnnotations(Trees.Tree tree, int mode, Types.Type pt) {
        return AnnotationCheckers$class.canAdaptAnnotations(this, tree, mode, pt);
    }

    @Override
    public Trees.Tree adaptAnnotations(Trees.Tree tree, int mode, Types.Type pt) {
        return AnnotationCheckers$class.adaptAnnotations(this, tree, mode, pt);
    }

    @Override
    public Types.Type adaptTypeOfReturn(Trees.Tree tree, Types.Type pt, Function0<Types.Type> function0) {
        return AnnotationCheckers$class.adaptTypeOfReturn(this, tree, pt, function0);
    }

    @Override
    public ClassTag<AnnotationInfos.ClassfileAnnotArg> JavaArgumentTag() {
        return this.JavaArgumentTag;
    }

    private AnnotationInfos$UnmappableAnnotArg$ UnmappableAnnotArg$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.UnmappableAnnotArg$module == null) {
                this.UnmappableAnnotArg$module = new AnnotationInfos$UnmappableAnnotArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.UnmappableAnnotArg$module;
        }
    }

    @Override
    public AnnotationInfos$UnmappableAnnotArg$ UnmappableAnnotArg() {
        return this.UnmappableAnnotArg$module == null ? this.UnmappableAnnotArg$lzycompute() : this.UnmappableAnnotArg$module;
    }

    private AnnotationInfos$LiteralAnnotArg$ LiteralAnnotArg$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LiteralAnnotArg$module == null) {
                this.LiteralAnnotArg$module = new AnnotationInfos$LiteralAnnotArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LiteralAnnotArg$module;
        }
    }

    @Override
    public AnnotationInfos$LiteralAnnotArg$ LiteralAnnotArg() {
        return this.LiteralAnnotArg$module == null ? this.LiteralAnnotArg$lzycompute() : this.LiteralAnnotArg$module;
    }

    private AnnotationInfos$ArrayAnnotArg$ ArrayAnnotArg$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ArrayAnnotArg$module == null) {
                this.ArrayAnnotArg$module = new AnnotationInfos$ArrayAnnotArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ArrayAnnotArg$module;
        }
    }

    @Override
    public AnnotationInfos$ArrayAnnotArg$ ArrayAnnotArg() {
        return this.ArrayAnnotArg$module == null ? this.ArrayAnnotArg$lzycompute() : this.ArrayAnnotArg$module;
    }

    private AnnotationInfos$NestedAnnotArg$ NestedAnnotArg$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.NestedAnnotArg$module == null) {
                this.NestedAnnotArg$module = new AnnotationInfos$NestedAnnotArg$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NestedAnnotArg$module;
        }
    }

    @Override
    public AnnotationInfos$NestedAnnotArg$ NestedAnnotArg() {
        return this.NestedAnnotArg$module == null ? this.NestedAnnotArg$lzycompute() : this.NestedAnnotArg$module;
    }

    @Override
    public AnnotationInfos$LiteralAnnotArg$ LiteralArgument() {
        return this.LiteralArgument;
    }

    @Override
    public ClassTag<AnnotationInfos.LiteralAnnotArg> LiteralArgumentTag() {
        return this.LiteralArgumentTag;
    }

    @Override
    public AnnotationInfos$ArrayAnnotArg$ ArrayArgument() {
        return this.ArrayArgument;
    }

    @Override
    public ClassTag<AnnotationInfos.ArrayAnnotArg> ArrayArgumentTag() {
        return this.ArrayArgumentTag;
    }

    @Override
    public AnnotationInfos$NestedAnnotArg$ NestedArgument() {
        return this.NestedArgument;
    }

    @Override
    public ClassTag<AnnotationInfos.NestedAnnotArg> NestedArgumentTag() {
        return this.NestedArgumentTag;
    }

    private AnnotationInfos$ScalaSigBytes$ ScalaSigBytes$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ScalaSigBytes$module == null) {
                this.ScalaSigBytes$module = new AnnotationInfos$ScalaSigBytes$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ScalaSigBytes$module;
        }
    }

    @Override
    public AnnotationInfos$ScalaSigBytes$ ScalaSigBytes() {
        return this.ScalaSigBytes$module == null ? this.ScalaSigBytes$lzycompute() : this.ScalaSigBytes$module;
    }

    private AnnotationInfos$AnnotationInfo$ AnnotationInfo$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.AnnotationInfo$module == null) {
                this.AnnotationInfo$module = new AnnotationInfos$AnnotationInfo$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.AnnotationInfo$module;
        }
    }

    @Override
    public AnnotationInfos$AnnotationInfo$ AnnotationInfo() {
        return this.AnnotationInfo$module == null ? this.AnnotationInfo$lzycompute() : this.AnnotationInfo$module;
    }

    private AnnotationInfos$Annotation$ Annotation$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Annotation$module == null) {
                this.Annotation$module = new AnnotationInfos$Annotation$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Annotation$module;
        }
    }

    @Override
    public AnnotationInfos$Annotation$ Annotation() {
        return this.Annotation$module == null ? this.Annotation$lzycompute() : this.Annotation$module;
    }

    @Override
    public ClassTag<AnnotationInfos.AnnotationInfo> AnnotationTag() {
        return this.AnnotationTag;
    }

    private AnnotationInfos$UnmappableAnnotation$ UnmappableAnnotation$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.UnmappableAnnotation$module == null) {
                this.UnmappableAnnotation$module = new AnnotationInfos$UnmappableAnnotation$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.UnmappableAnnotation$module;
        }
    }

    @Override
    public AnnotationInfos$UnmappableAnnotation$ UnmappableAnnotation() {
        return this.UnmappableAnnotation$module == null ? this.UnmappableAnnotation$lzycompute() : this.UnmappableAnnotation$module;
    }

    private AnnotationInfos$ThrownException$ ThrownException$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ThrownException$module == null) {
                this.ThrownException$module = new AnnotationInfos$ThrownException$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ThrownException$module;
        }
    }

    @Override
    public AnnotationInfos$ThrownException$ ThrownException() {
        return this.ThrownException$module == null ? this.ThrownException$lzycompute() : this.ThrownException$module;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(ClassTag x$1) {
        this.JavaArgumentTag = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgument_$eq(AnnotationInfos$LiteralAnnotArg$ x$1) {
        this.LiteralArgument = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$LiteralArgumentTag_$eq(ClassTag x$1) {
        this.LiteralArgumentTag = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgument_$eq(AnnotationInfos$ArrayAnnotArg$ x$1) {
        this.ArrayArgument = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$ArrayArgumentTag_$eq(ClassTag x$1) {
        this.ArrayArgumentTag = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$NestedArgument_$eq(AnnotationInfos$NestedAnnotArg$ x$1) {
        this.NestedArgument = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$NestedArgumentTag_$eq(ClassTag x$1) {
        this.NestedArgumentTag = x$1;
    }

    @Override
    public void scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(ClassTag x$1) {
        this.AnnotationTag = x$1;
    }

    @Override
    public String completeAnnotationToString(AnnotationInfos.AnnotationInfo annInfo) {
        return AnnotationInfos$class.completeAnnotationToString(this, annInfo);
    }

    @Override
    public Trees.Tree annotationToTree(AnnotationInfos.AnnotationInfo ann) {
        return AnnotationInfos$class.annotationToTree(this, ann);
    }

    @Override
    public AnnotationInfos.AnnotationInfo treeToAnnotation(Trees.Tree tree) {
        return AnnotationInfos$class.treeToAnnotation(this, tree);
    }

    private StdNames$compactify$ scala$reflect$internal$StdNames$$compactify$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.scala$reflect$internal$StdNames$$compactify$module == null) {
                this.scala$reflect$internal$StdNames$$compactify$module = new StdNames$compactify$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$StdNames$$compactify$module;
        }
    }

    @Override
    public final StdNames$compactify$ scala$reflect$internal$StdNames$$compactify() {
        return this.scala$reflect$internal$StdNames$$compactify$module == null ? this.scala$reflect$internal$StdNames$$compactify$lzycompute() : this.scala$reflect$internal$StdNames$$compactify$module;
    }

    private StdNames$tpnme$ typeNames$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x100) == 0) {
                this.typeNames = StdNames$class.typeNames(this);
                this.bitmap$0 |= 0x100;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.typeNames;
        }
    }

    @Override
    public StdNames$tpnme$ typeNames() {
        return (this.bitmap$0 & 0x100) == 0 ? this.typeNames$lzycompute() : this.typeNames;
    }

    private StdNames$tpnme$ tpnme$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.tpnme$module == null) {
                this.tpnme$module = new StdNames$tpnme$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.tpnme$module;
        }
    }

    @Override
    public StdNames$tpnme$ tpnme() {
        return this.tpnme$module == null ? this.tpnme$lzycompute() : this.tpnme$module;
    }

    private StdNames$fulltpnme$ fulltpnme$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.fulltpnme$module == null) {
                this.fulltpnme$module = new StdNames$fulltpnme$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.fulltpnme$module;
        }
    }

    @Override
    public StdNames$fulltpnme$ fulltpnme() {
        return this.fulltpnme$module == null ? this.fulltpnme$lzycompute() : this.fulltpnme$module;
    }

    private StdNames$binarynme$ binarynme$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.binarynme$module == null) {
                this.binarynme$module = new StdNames$binarynme$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.binarynme$module;
        }
    }

    @Override
    public StdNames$binarynme$ binarynme() {
        return this.binarynme$module == null ? this.binarynme$lzycompute() : this.binarynme$module;
    }

    @Override
    public StdNames.JavaKeywords javanme() {
        return this.javanme;
    }

    private StdNames$nme$ termNames$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x200) == 0) {
                this.termNames = StdNames$class.termNames(this);
                this.bitmap$0 |= 0x200;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.termNames;
        }
    }

    @Override
    public StdNames$nme$ termNames() {
        return (this.bitmap$0 & 0x200) == 0 ? this.termNames$lzycompute() : this.termNames;
    }

    private StdNames$nme$ nme$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.nme$module == null) {
                this.nme$module = new StdNames$nme$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.nme$module;
        }
    }

    @Override
    public StdNames$nme$ nme() {
        return this.nme$module == null ? this.nme$lzycompute() : this.nme$module;
    }

    private StdNames.SymbolNames sn$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x400) == 0) {
                this.sn = StdNames$class.sn(this);
                this.bitmap$0 |= 0x400;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.sn;
        }
    }

    @Override
    public StdNames.SymbolNames sn() {
        return (this.bitmap$0 & 0x400) == 0 ? this.sn$lzycompute() : this.sn;
    }

    @Override
    public void scala$reflect$internal$StdNames$_setter_$javanme_$eq(StdNames.JavaKeywords x$1) {
        this.javanme = x$1;
    }

    @Override
    public Names.TermName encode(String str) {
        return StdNames$class.encode(this, str);
    }

    @Override
    public String compactifyName(String orig) {
        return StdNames$class.compactifyName(this, orig);
    }

    @Override
    public Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$refChecksLazy() {
        return this.scala$reflect$internal$transform$Transforms$$refChecksLazy;
    }

    @Override
    public Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$uncurryLazy() {
        return this.scala$reflect$internal$transform$Transforms$$uncurryLazy;
    }

    @Override
    public Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$erasureLazy() {
        return this.scala$reflect$internal$transform$Transforms$$erasureLazy;
    }

    @Override
    public Transforms.Lazy<Object> scala$reflect$internal$transform$Transforms$$postErasureLazy() {
        return this.scala$reflect$internal$transform$Transforms$$postErasureLazy;
    }

    @Override
    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$refChecksLazy_$eq(Transforms.Lazy x$1) {
        this.scala$reflect$internal$transform$Transforms$$refChecksLazy = x$1;
    }

    @Override
    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq(Transforms.Lazy x$1) {
        this.scala$reflect$internal$transform$Transforms$$uncurryLazy = x$1;
    }

    @Override
    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq(Transforms.Lazy x$1) {
        this.scala$reflect$internal$transform$Transforms$$erasureLazy = x$1;
    }

    @Override
    public void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq(Transforms.Lazy x$1) {
        this.scala$reflect$internal$transform$Transforms$$postErasureLazy = x$1;
    }

    @Override
    public RefChecks refChecks() {
        return Transforms$class.refChecks(this);
    }

    @Override
    public UnCurry uncurry() {
        return Transforms$class.uncurry(this);
    }

    @Override
    public Erasure erasure() {
        return Transforms$class.erasure(this);
    }

    @Override
    public PostErasure postErasure() {
        return Transforms$class.postErasure(this);
    }

    @Override
    public Types.Type transformedType(Symbols.Symbol sym) {
        return Transforms$class.transformedType(this, sym);
    }

    @Override
    public Types.Type transformedType(Types.Type tpe) {
        return Transforms$class.transformedType(this, tpe);
    }

    @Override
    public BaseTypeSeqs.BaseTypeSeq undetBaseTypeSeq() {
        return this.undetBaseTypeSeq;
    }

    @Override
    public Throwable CyclicInheritance() {
        return this.CyclicInheritance;
    }

    @Override
    public void scala$reflect$internal$BaseTypeSeqs$_setter_$undetBaseTypeSeq_$eq(BaseTypeSeqs.BaseTypeSeq x$1) {
        this.undetBaseTypeSeq = x$1;
    }

    @Override
    public void scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(Throwable x$1) {
        this.CyclicInheritance = x$1;
    }

    @Override
    public BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(List<Types.Type> parents2, Types.Type[] elems) {
        return BaseTypeSeqs$class.newBaseTypeSeq(this, parents2, elems);
    }

    @Override
    public BaseTypeSeqs.BaseTypeSeq baseTypeSingletonSeq(Types.Type tp) {
        return BaseTypeSeqs$class.baseTypeSingletonSeq(this, tp);
    }

    @Override
    public BaseTypeSeqs.BaseTypeSeq compoundBaseTypeSeq(Types.Type tp) {
        return BaseTypeSeqs$class.compoundBaseTypeSeq(this, tp);
    }

    @Override
    public final int NoTag() {
        return 0;
    }

    @Override
    public final int UnitTag() {
        return 1;
    }

    @Override
    public final int BooleanTag() {
        return 2;
    }

    @Override
    public final int ByteTag() {
        return 3;
    }

    @Override
    public final int ShortTag() {
        return 4;
    }

    @Override
    public final int CharTag() {
        return 5;
    }

    @Override
    public final int IntTag() {
        return 6;
    }

    @Override
    public final int LongTag() {
        return 7;
    }

    @Override
    public final int FloatTag() {
        return 8;
    }

    @Override
    public final int DoubleTag() {
        return 9;
    }

    @Override
    public final int StringTag() {
        return 10;
    }

    @Override
    public final int NullTag() {
        return 11;
    }

    @Override
    public final int ClazzTag() {
        return 12;
    }

    @Override
    public final int EnumTag() {
        return 13;
    }

    private Constants$Constant$ Constant$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Constant$module == null) {
                this.Constant$module = new Constants$Constant$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Constant$module;
        }
    }

    @Override
    public Constants$Constant$ Constant() {
        return this.Constant$module == null ? this.Constant$lzycompute() : this.Constant$module;
    }

    @Override
    public ClassTag<Constants.Constant> ConstantTag() {
        return this.ConstantTag;
    }

    @Override
    public void scala$reflect$internal$Constants$_setter_$ConstantTag_$eq(ClassTag x$1) {
        this.ConstantTag = x$1;
    }

    private Definitions$definitions$ definitions$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.definitions$module == null) {
                this.definitions$module = new Definitions$definitions$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.definitions$module;
        }
    }

    @Override
    public Definitions$definitions$ definitions() {
        return this.definitions$module == null ? this.definitions$lzycompute() : this.definitions$module;
    }

    private Scopes$LookupSucceeded$ LookupSucceeded$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LookupSucceeded$module == null) {
                this.LookupSucceeded$module = new Scopes$LookupSucceeded$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LookupSucceeded$module;
        }
    }

    @Override
    public Scopes$LookupSucceeded$ LookupSucceeded() {
        return this.LookupSucceeded$module == null ? this.LookupSucceeded$lzycompute() : this.LookupSucceeded$module;
    }

    private Scopes$LookupAmbiguous$ LookupAmbiguous$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LookupAmbiguous$module == null) {
                this.LookupAmbiguous$module = new Scopes$LookupAmbiguous$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LookupAmbiguous$module;
        }
    }

    @Override
    public Scopes$LookupAmbiguous$ LookupAmbiguous() {
        return this.LookupAmbiguous$module == null ? this.LookupAmbiguous$lzycompute() : this.LookupAmbiguous$module;
    }

    private Scopes$LookupInaccessible$ LookupInaccessible$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LookupInaccessible$module == null) {
                this.LookupInaccessible$module = new Scopes$LookupInaccessible$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LookupInaccessible$module;
        }
    }

    @Override
    public Scopes$LookupInaccessible$ LookupInaccessible() {
        return this.LookupInaccessible$module == null ? this.LookupInaccessible$lzycompute() : this.LookupInaccessible$module;
    }

    private Scopes$LookupNotFound$ LookupNotFound$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.LookupNotFound$module == null) {
                this.LookupNotFound$module = new Scopes$LookupNotFound$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.LookupNotFound$module;
        }
    }

    @Override
    public Scopes$LookupNotFound$ LookupNotFound() {
        return this.LookupNotFound$module == null ? this.LookupNotFound$lzycompute() : this.LookupNotFound$module;
    }

    private Scopes$Scope$ Scope$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Scope$module == null) {
                this.Scope$module = new Scopes$Scope$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Scope$module;
        }
    }

    @Override
    public Scopes$Scope$ Scope() {
        return this.Scope$module == null ? this.Scope$lzycompute() : this.Scope$module;
    }

    @Override
    public ClassTag<Scopes.Scope> ScopeTag() {
        return this.ScopeTag;
    }

    @Override
    public ClassTag<Scopes.Scope> MemberScopeTag() {
        return this.MemberScopeTag;
    }

    private Scopes$EmptyScope$ EmptyScope$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.EmptyScope$module == null) {
                this.EmptyScope$module = new Scopes$EmptyScope$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.EmptyScope$module;
        }
    }

    @Override
    public Scopes$EmptyScope$ EmptyScope() {
        return this.EmptyScope$module == null ? this.EmptyScope$lzycompute() : this.EmptyScope$module;
    }

    @Override
    public final int scala$reflect$internal$Scopes$$maxRecursions() {
        return 1000;
    }

    @Override
    public void scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(ClassTag x$1) {
        this.ScopeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(ClassTag x$1) {
        this.MemberScopeTag = x$1;
    }

    @Override
    public Scopes.Scope newScope() {
        return Scopes$class.newScope(this);
    }

    @Override
    public Scopes.Scope newFindMemberScope() {
        return Scopes$class.newFindMemberScope(this);
    }

    @Override
    public final Scopes.Scope newNestedScope(Scopes.Scope outer) {
        return Scopes$class.newNestedScope(this, outer);
    }

    @Override
    public Scopes.Scope newScopeWith(Seq<Symbols.Symbol> elems) {
        return Scopes$class.newScopeWith(this, elems);
    }

    @Override
    public Scopes.Scope newPackageScope(Symbols.Symbol pkgClass) {
        return Scopes$class.newPackageScope(this, pkgClass);
    }

    @Override
    public Scopes.Scope scopeTransform(Symbols.Symbol owner2, Function0<Scopes.Scope> op) {
        return Scopes$class.scopeTransform(this, owner2, op);
    }

    @Override
    public ClassTag<Object> FlagSetTag() {
        return this.FlagSetTag;
    }

    @Override
    public long NoFlags() {
        return this.NoFlags;
    }

    private FlagSets$Flag$ Flag$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Flag$module == null) {
                this.Flag$module = new FlagSets$Flag$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Flag$module;
        }
    }

    @Override
    public FlagSets$Flag$ Flag() {
        return this.Flag$module == null ? this.Flag$lzycompute() : this.Flag$module;
    }

    @Override
    public void scala$reflect$internal$FlagSets$_setter_$FlagSetTag_$eq(ClassTag x$1) {
        this.FlagSetTag = x$1;
    }

    @Override
    public void scala$reflect$internal$FlagSets$_setter_$NoFlags_$eq(long x$1) {
        this.NoFlags = x$1;
    }

    @Override
    public FlagSets.FlagOps addFlagOps(long left) {
        return FlagSets$class.addFlagOps(this, left);
    }

    @Override
    public List<Symbols.Symbol> deriveFreshSkolems(List<Symbols.Symbol> tparams2) {
        return ExistentialsAndSkolems$class.deriveFreshSkolems(this, tparams2);
    }

    @Override
    public boolean isRawParameter(Symbols.Symbol sym) {
        return ExistentialsAndSkolems$class.isRawParameter(this, sym);
    }

    @Override
    public final <T> T existentialTransform(List<Symbols.Symbol> rawSyms, Types.Type tp, Symbols.Symbol rawOwner, Function2<List<Symbols.Symbol>, Types.Type, T> creator) {
        return (T)ExistentialsAndSkolems$class.existentialTransform(this, rawSyms, tp, rawOwner, creator);
    }

    @Override
    public final Types.Type packSymbols(List<Symbols.Symbol> hidden, Types.Type tp, Symbols.Symbol rawOwner) {
        return ExistentialsAndSkolems$class.packSymbols(this, hidden, tp, rawOwner);
    }

    @Override
    public final <T> Symbols.Symbol existentialTransform$default$3() {
        return ExistentialsAndSkolems$class.existentialTransform$default$3(this);
    }

    @Override
    public final Symbols.Symbol packSymbols$default$3() {
        return ExistentialsAndSkolems$class.packSymbols$default$3(this);
    }

    private Kinds$KindErrors$ KindErrors$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.KindErrors$module == null) {
                this.KindErrors$module = new Kinds$KindErrors$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.KindErrors$module;
        }
    }

    @Override
    public Kinds$KindErrors$ KindErrors() {
        return this.KindErrors$module == null ? this.KindErrors$lzycompute() : this.KindErrors$module;
    }

    @Override
    public Kinds.KindErrors NoKindErrors() {
        return this.NoKindErrors;
    }

    private Kinds$Kind$ Kind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.Kind$module == null) {
                this.Kind$module = new Kinds$Kind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Kind$module;
        }
    }

    @Override
    public Kinds$Kind$ Kind() {
        return this.Kind$module == null ? this.Kind$lzycompute() : this.Kind$module;
    }

    private Kinds$ProperTypeKind$ ProperTypeKind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ProperTypeKind$module == null) {
                this.ProperTypeKind$module = new Kinds$ProperTypeKind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ProperTypeKind$module;
        }
    }

    @Override
    public Kinds$ProperTypeKind$ ProperTypeKind() {
        return this.ProperTypeKind$module == null ? this.ProperTypeKind$lzycompute() : this.ProperTypeKind$module;
    }

    private Kinds$TypeConKind$ TypeConKind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeConKind$module == null) {
                this.TypeConKind$module = new Kinds$TypeConKind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeConKind$module;
        }
    }

    @Override
    public Kinds$TypeConKind$ TypeConKind() {
        return this.TypeConKind$module == null ? this.TypeConKind$lzycompute() : this.TypeConKind$module;
    }

    private Kinds$inferKind$ inferKind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.inferKind$module == null) {
                this.inferKind$module = new Kinds$inferKind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.inferKind$module;
        }
    }

    @Override
    public Kinds$inferKind$ inferKind() {
        return this.inferKind$module == null ? this.inferKind$lzycompute() : this.inferKind$module;
    }

    @Override
    public void scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq(Kinds.KindErrors x$1) {
        this.NoKindErrors = x$1;
    }

    @Override
    public boolean kindsConform(List<Symbols.Symbol> tparams2, List<Types.Type> targs, Types.Type pre, Symbols.Symbol owner2) {
        return Kinds$class.kindsConform(this, tparams2, targs, pre, owner2);
    }

    @Override
    public List<Tuple3<Types.Type, Symbols.Symbol, Kinds.KindErrors>> checkKindBounds0(List<Symbols.Symbol> tparams2, List<Types.Type> targs, Types.Type pre, Symbols.Symbol owner2, boolean explainErrors) {
        return Kinds$class.checkKindBounds0(this, tparams2, targs, pre, owner2, explainErrors);
    }

    @Override
    public int varianceInTypes(List<Types.Type> tps, Symbols.Symbol tparam) {
        return Variances$class.varianceInTypes(this, tps, tparam);
    }

    @Override
    public int varianceInType(Types.Type tp, Symbols.Symbol tparam) {
        return Variances$class.varianceInType(this, tp, tparam);
    }

    @Override
    public boolean scala$reflect$internal$Types$$explainSwitch() {
        return this.scala$reflect$internal$Types$$explainSwitch;
    }

    @Override
    public void scala$reflect$internal$Types$$explainSwitch_$eq(boolean x$1) {
        this.scala$reflect$internal$Types$$explainSwitch = x$1;
    }

    @Override
    public final Set<Symbols.Symbol> scala$reflect$internal$Types$$emptySymbolSet() {
        return this.scala$reflect$internal$Types$$emptySymbolSet;
    }

    @Override
    public final boolean scala$reflect$internal$Types$$traceTypeVars() {
        return this.scala$reflect$internal$Types$$traceTypeVars;
    }

    @Override
    public final boolean scala$reflect$internal$Types$$breakCycles() {
        return this.scala$reflect$internal$Types$$breakCycles;
    }

    @Override
    public final boolean scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars() {
        return this.scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars;
    }

    @Override
    public final boolean scala$reflect$internal$Types$$sharperSkolems() {
        return this.scala$reflect$internal$Types$$sharperSkolems;
    }

    @Override
    public boolean enableTypeVarExperimentals() {
        return this.enableTypeVarExperimentals;
    }

    private Types$substTypeMapCache$ scala$reflect$internal$Types$$substTypeMapCache$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.scala$reflect$internal$Types$$substTypeMapCache$module == null) {
                this.scala$reflect$internal$Types$$substTypeMapCache$module = new Types$substTypeMapCache$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$Types$$substTypeMapCache$module;
        }
    }

    @Override
    public final Types$substTypeMapCache$ scala$reflect$internal$Types$$substTypeMapCache() {
        return this.scala$reflect$internal$Types$$substTypeMapCache$module == null ? this.scala$reflect$internal$Types$$substTypeMapCache$lzycompute() : this.scala$reflect$internal$Types$$substTypeMapCache$module;
    }

    @Override
    public int scala$reflect$internal$Types$$_skolemizationLevel() {
        return this.scala$reflect$internal$Types$$_skolemizationLevel;
    }

    @Override
    public void scala$reflect$internal$Types$$_skolemizationLevel_$eq(int x$1) {
        this.scala$reflect$internal$Types$$_skolemizationLevel = x$1;
    }

    @Override
    public WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> scala$reflect$internal$Types$$_intersectionWitness() {
        return this.scala$reflect$internal$Types$$_intersectionWitness;
    }

    private Types$UnmappableTree$ UnmappableTree$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.UnmappableTree$module == null) {
                this.UnmappableTree$module = new Types$UnmappableTree$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.UnmappableTree$module;
        }
    }

    @Override
    public Types$UnmappableTree$ UnmappableTree() {
        return this.UnmappableTree$module == null ? this.UnmappableTree$lzycompute() : this.UnmappableTree$module;
    }

    private Types$ErrorType$ ErrorType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ErrorType$module == null) {
                this.ErrorType$module = new Types$ErrorType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ErrorType$module;
        }
    }

    @Override
    public Types$ErrorType$ ErrorType() {
        return this.ErrorType$module == null ? this.ErrorType$lzycompute() : this.ErrorType$module;
    }

    private Types$WildcardType$ WildcardType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.WildcardType$module == null) {
                this.WildcardType$module = new Types$WildcardType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.WildcardType$module;
        }
    }

    @Override
    public Types$WildcardType$ WildcardType() {
        return this.WildcardType$module == null ? this.WildcardType$lzycompute() : this.WildcardType$module;
    }

    private Types$BoundedWildcardType$ BoundedWildcardType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.BoundedWildcardType$module == null) {
                this.BoundedWildcardType$module = new Types$BoundedWildcardType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.BoundedWildcardType$module;
        }
    }

    @Override
    public Types$BoundedWildcardType$ BoundedWildcardType() {
        return this.BoundedWildcardType$module == null ? this.BoundedWildcardType$lzycompute() : this.BoundedWildcardType$module;
    }

    private Types$NoType$ NoType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.NoType$module == null) {
                this.NoType$module = new Types$NoType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NoType$module;
        }
    }

    @Override
    public Types$NoType$ NoType() {
        return this.NoType$module == null ? this.NoType$lzycompute() : this.NoType$module;
    }

    private Types$NoPrefix$ NoPrefix$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.NoPrefix$module == null) {
                this.NoPrefix$module = new Types$NoPrefix$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NoPrefix$module;
        }
    }

    @Override
    public Types$NoPrefix$ NoPrefix() {
        return this.NoPrefix$module == null ? this.NoPrefix$lzycompute() : this.NoPrefix$module;
    }

    private Types$ThisType$ ThisType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ThisType$module == null) {
                this.ThisType$module = new Types$ThisType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ThisType$module;
        }
    }

    @Override
    public Types$ThisType$ ThisType() {
        return this.ThisType$module == null ? this.ThisType$lzycompute() : this.ThisType$module;
    }

    private Types$SingleType$ SingleType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SingleType$module == null) {
                this.SingleType$module = new Types$SingleType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SingleType$module;
        }
    }

    @Override
    public Types$SingleType$ SingleType() {
        return this.SingleType$module == null ? this.SingleType$lzycompute() : this.SingleType$module;
    }

    private Types$SuperType$ SuperType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SuperType$module == null) {
                this.SuperType$module = new Types$SuperType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SuperType$module;
        }
    }

    @Override
    public Types$SuperType$ SuperType() {
        return this.SuperType$module == null ? this.SuperType$lzycompute() : this.SuperType$module;
    }

    private Types$TypeBounds$ TypeBounds$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeBounds$module == null) {
                this.TypeBounds$module = new Types$TypeBounds$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeBounds$module;
        }
    }

    @Override
    public Types$TypeBounds$ TypeBounds() {
        return this.TypeBounds$module == null ? this.TypeBounds$lzycompute() : this.TypeBounds$module;
    }

    private Types$CompoundType$ CompoundType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.CompoundType$module == null) {
                this.CompoundType$module = new Types$CompoundType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.CompoundType$module;
        }
    }

    @Override
    public Types$CompoundType$ CompoundType() {
        return this.CompoundType$module == null ? this.CompoundType$lzycompute() : this.CompoundType$module;
    }

    private Types$baseClassesCycleMonitor$ baseClassesCycleMonitor$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.baseClassesCycleMonitor$module == null) {
                this.baseClassesCycleMonitor$module = new Types$baseClassesCycleMonitor$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.baseClassesCycleMonitor$module;
        }
    }

    @Override
    public Types$baseClassesCycleMonitor$ baseClassesCycleMonitor() {
        return this.baseClassesCycleMonitor$module == null ? this.baseClassesCycleMonitor$lzycompute() : this.baseClassesCycleMonitor$module;
    }

    private Types$RefinedType$ RefinedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.RefinedType$module == null) {
                this.RefinedType$module = new Types$RefinedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RefinedType$module;
        }
    }

    @Override
    public Types$RefinedType$ RefinedType() {
        return this.RefinedType$module == null ? this.RefinedType$lzycompute() : this.RefinedType$module;
    }

    private Types$ClassInfoType$ ClassInfoType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ClassInfoType$module == null) {
                this.ClassInfoType$module = new Types$ClassInfoType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ClassInfoType$module;
        }
    }

    @Override
    public Types$ClassInfoType$ ClassInfoType() {
        return this.ClassInfoType$module == null ? this.ClassInfoType$lzycompute() : this.ClassInfoType$module;
    }

    private Types$ConstantType$ ConstantType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ConstantType$module == null) {
                this.ConstantType$module = new Types$ConstantType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ConstantType$module;
        }
    }

    @Override
    public Types$ConstantType$ ConstantType() {
        return this.ConstantType$module == null ? this.ConstantType$lzycompute() : this.ConstantType$module;
    }

    @Override
    public int scala$reflect$internal$Types$$volatileRecursions() {
        return this.scala$reflect$internal$Types$$volatileRecursions;
    }

    @Override
    public void scala$reflect$internal$Types$$volatileRecursions_$eq(int x$1) {
        this.scala$reflect$internal$Types$$volatileRecursions = x$1;
    }

    @Override
    public HashSet<Symbols.Symbol> scala$reflect$internal$Types$$pendingVolatiles() {
        return this.scala$reflect$internal$Types$$pendingVolatiles;
    }

    private Types$TypeRef$ TypeRef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeRef$module == null) {
                this.TypeRef$module = new Types$TypeRef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeRef$module;
        }
    }

    @Override
    public Types$TypeRef$ TypeRef() {
        return this.TypeRef$module == null ? this.TypeRef$lzycompute() : this.TypeRef$module;
    }

    private Types$MethodType$ MethodType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.MethodType$module == null) {
                this.MethodType$module = new Types$MethodType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MethodType$module;
        }
    }

    @Override
    public Types$MethodType$ MethodType() {
        return this.MethodType$module == null ? this.MethodType$lzycompute() : this.MethodType$module;
    }

    private Types$NullaryMethodType$ NullaryMethodType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.NullaryMethodType$module == null) {
                this.NullaryMethodType$module = new Types$NullaryMethodType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NullaryMethodType$module;
        }
    }

    @Override
    public Types$NullaryMethodType$ NullaryMethodType() {
        return this.NullaryMethodType$module == null ? this.NullaryMethodType$lzycompute() : this.NullaryMethodType$module;
    }

    private Types$PolyType$ PolyType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.PolyType$module == null) {
                this.PolyType$module = new Types$PolyType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.PolyType$module;
        }
    }

    @Override
    public Types$PolyType$ PolyType() {
        return this.PolyType$module == null ? this.PolyType$lzycompute() : this.PolyType$module;
    }

    private Types$ExistentialType$ ExistentialType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ExistentialType$module == null) {
                this.ExistentialType$module = new Types$ExistentialType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ExistentialType$module;
        }
    }

    @Override
    public Types$ExistentialType$ ExistentialType() {
        return this.ExistentialType$module == null ? this.ExistentialType$lzycompute() : this.ExistentialType$module;
    }

    private Types$OverloadedType$ OverloadedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.OverloadedType$module == null) {
                this.OverloadedType$module = new Types$OverloadedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.OverloadedType$module;
        }
    }

    @Override
    public Types$OverloadedType$ OverloadedType() {
        return this.OverloadedType$module == null ? this.OverloadedType$lzycompute() : this.OverloadedType$module;
    }

    private Types$ImportType$ ImportType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ImportType$module == null) {
                this.ImportType$module = new Types$ImportType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ImportType$module;
        }
    }

    @Override
    public Types$ImportType$ ImportType() {
        return this.ImportType$module == null ? this.ImportType$lzycompute() : this.ImportType$module;
    }

    private Types$AntiPolyType$ AntiPolyType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.AntiPolyType$module == null) {
                this.AntiPolyType$module = new Types$AntiPolyType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.AntiPolyType$module;
        }
    }

    @Override
    public Types$AntiPolyType$ AntiPolyType() {
        return this.AntiPolyType$module == null ? this.AntiPolyType$lzycompute() : this.AntiPolyType$module;
    }

    private Types$HasTypeMember$ HasTypeMember$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.HasTypeMember$module == null) {
                this.HasTypeMember$module = new Types$HasTypeMember$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.HasTypeMember$module;
        }
    }

    @Override
    public Types$HasTypeMember$ HasTypeMember() {
        return this.HasTypeMember$module == null ? this.HasTypeMember$lzycompute() : this.HasTypeMember$module;
    }

    private Types$ArrayTypeRef$ ArrayTypeRef$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ArrayTypeRef$module == null) {
                this.ArrayTypeRef$module = new Types$ArrayTypeRef$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ArrayTypeRef$module;
        }
    }

    @Override
    public Types$ArrayTypeRef$ ArrayTypeRef() {
        return this.ArrayTypeRef$module == null ? this.ArrayTypeRef$lzycompute() : this.ArrayTypeRef$module;
    }

    private Types$TypeVar$ TypeVar$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeVar$module == null) {
                this.TypeVar$module = new Types$TypeVar$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeVar$module;
        }
    }

    @Override
    public Types$TypeVar$ TypeVar() {
        return this.TypeVar$module == null ? this.TypeVar$lzycompute() : this.TypeVar$module;
    }

    private Types$AnnotatedType$ AnnotatedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.AnnotatedType$module == null) {
                this.AnnotatedType$module = new Types$AnnotatedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.AnnotatedType$module;
        }
    }

    @Override
    public Types$AnnotatedType$ AnnotatedType() {
        return this.AnnotatedType$module == null ? this.AnnotatedType$lzycompute() : this.AnnotatedType$module;
    }

    private Types$StaticallyAnnotatedType$ StaticallyAnnotatedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.StaticallyAnnotatedType$module == null) {
                this.StaticallyAnnotatedType$module = new Types$StaticallyAnnotatedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.StaticallyAnnotatedType$module;
        }
    }

    @Override
    public Types$StaticallyAnnotatedType$ StaticallyAnnotatedType() {
        return this.StaticallyAnnotatedType$module == null ? this.StaticallyAnnotatedType$lzycompute() : this.StaticallyAnnotatedType$module;
    }

    private Types$NamedType$ NamedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.NamedType$module == null) {
                this.NamedType$module = new Types$NamedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NamedType$module;
        }
    }

    @Override
    public Types$NamedType$ NamedType() {
        return this.NamedType$module == null ? this.NamedType$lzycompute() : this.NamedType$module;
    }

    private Types$RepeatedType$ RepeatedType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.RepeatedType$module == null) {
                this.RepeatedType$module = new Types$RepeatedType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RepeatedType$module;
        }
    }

    @Override
    public Types$RepeatedType$ RepeatedType() {
        return this.RepeatedType$module == null ? this.RepeatedType$lzycompute() : this.RepeatedType$module;
    }

    private Types$ErasedValueType$ ErasedValueType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ErasedValueType$module == null) {
                this.ErasedValueType$module = new Types$ErasedValueType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ErasedValueType$module;
        }
    }

    @Override
    public Types$ErasedValueType$ ErasedValueType() {
        return this.ErasedValueType$module == null ? this.ErasedValueType$lzycompute() : this.ErasedValueType$module;
    }

    private Types$GenPolyType$ GenPolyType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.GenPolyType$module == null) {
                this.GenPolyType$module = new Types$GenPolyType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.GenPolyType$module;
        }
    }

    @Override
    public Types$GenPolyType$ GenPolyType() {
        return this.GenPolyType$module == null ? this.GenPolyType$lzycompute() : this.GenPolyType$module;
    }

    @Override
    public int scala$reflect$internal$Types$$initialUniquesCapacity() {
        return this.scala$reflect$internal$Types$$initialUniquesCapacity;
    }

    @Override
    public WeakHashSet<Types.Type> scala$reflect$internal$Types$$uniques() {
        return this.scala$reflect$internal$Types$$uniques;
    }

    @Override
    public void scala$reflect$internal$Types$$uniques_$eq(WeakHashSet<Types.Type> x$1) {
        this.scala$reflect$internal$Types$$uniques = x$1;
    }

    @Override
    public int scala$reflect$internal$Types$$uniqueRunId() {
        return this.scala$reflect$internal$Types$$uniqueRunId;
    }

    @Override
    public void scala$reflect$internal$Types$$uniqueRunId_$eq(int x$1) {
        this.scala$reflect$internal$Types$$uniqueRunId = x$1;
    }

    private Types$unwrapToClass$ unwrapToClass$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.unwrapToClass$module == null) {
                this.unwrapToClass$module = new Types$unwrapToClass$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.unwrapToClass$module;
        }
    }

    @Override
    public Types$unwrapToClass$ unwrapToClass() {
        return this.unwrapToClass$module == null ? this.unwrapToClass$lzycompute() : this.unwrapToClass$module;
    }

    private Types$unwrapToStableClass$ unwrapToStableClass$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.unwrapToStableClass$module == null) {
                this.unwrapToStableClass$module = new Types$unwrapToStableClass$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.unwrapToStableClass$module;
        }
    }

    @Override
    public Types$unwrapToStableClass$ unwrapToStableClass() {
        return this.unwrapToStableClass$module == null ? this.unwrapToStableClass$lzycompute() : this.unwrapToStableClass$module;
    }

    private Types$unwrapWrapperTypes$ unwrapWrapperTypes$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.unwrapWrapperTypes$module == null) {
                this.unwrapWrapperTypes$module = new Types$unwrapWrapperTypes$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.unwrapWrapperTypes$module;
        }
    }

    @Override
    public Types$unwrapWrapperTypes$ unwrapWrapperTypes() {
        return this.unwrapWrapperTypes$module == null ? this.unwrapWrapperTypes$lzycompute() : this.unwrapWrapperTypes$module;
    }

    @Override
    public Types.MissingAliasControl missingAliasException() {
        return this.missingAliasException;
    }

    @Override
    public int scala$reflect$internal$Types$$_basetypeRecursions() {
        return this.scala$reflect$internal$Types$$_basetypeRecursions;
    }

    @Override
    public void scala$reflect$internal$Types$$_basetypeRecursions_$eq(int x$1) {
        this.scala$reflect$internal$Types$$_basetypeRecursions = x$1;
    }

    @Override
    public HashSet<Types.Type> scala$reflect$internal$Types$$_pendingBaseTypes() {
        return this.scala$reflect$internal$Types$$_pendingBaseTypes;
    }

    private Types$RecoverableCyclicReference$ RecoverableCyclicReference$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.RecoverableCyclicReference$module == null) {
                this.RecoverableCyclicReference$module = new Types$RecoverableCyclicReference$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.RecoverableCyclicReference$module;
        }
    }

    @Override
    public Types$RecoverableCyclicReference$ RecoverableCyclicReference() {
        return this.RecoverableCyclicReference$module == null ? this.RecoverableCyclicReference$lzycompute() : this.RecoverableCyclicReference$module;
    }

    @Override
    public String scala$reflect$internal$Types$$_indent() {
        return this.scala$reflect$internal$Types$$_indent;
    }

    @Override
    public void scala$reflect$internal$Types$$_indent_$eq(String x$1) {
        this.scala$reflect$internal$Types$$_indent = x$1;
    }

    @Override
    public Set<String> shorthands() {
        return this.shorthands;
    }

    @Override
    public Function1<Types.Type, Object> isTypeVar() {
        return this.isTypeVar;
    }

    @Override
    public Function1<Types.Type, Object> typeContainsTypeVar() {
        return this.typeContainsTypeVar;
    }

    @Override
    public Function1<Types.Type, Object> typeIsNonClassType() {
        return this.typeIsNonClassType;
    }

    @Override
    public Function1<Types.Type, Object> typeIsExistentiallyBound() {
        return this.typeIsExistentiallyBound;
    }

    @Override
    public Function1<Types.Type, Object> typeIsErroneous() {
        return this.typeIsErroneous;
    }

    @Override
    public Function1<Symbols.Symbol, Object> symTypeIsError() {
        return this.symTypeIsError;
    }

    @Override
    public Function1<Trees.Tree, Types.Type> treeTpe() {
        return this.treeTpe;
    }

    @Override
    public Function1<Symbols.Symbol, Types.Type> symTpe() {
        return this.symTpe;
    }

    @Override
    public Function1<Symbols.Symbol, Types.Type> symInfo() {
        return this.symInfo;
    }

    @Override
    public Function1<Types.Type, Object> typeHasAnnotations() {
        return this.typeHasAnnotations;
    }

    @Override
    public Function2<Types.TypeBounds, Types.Type, Object> boundsContainType() {
        return this.boundsContainType;
    }

    @Override
    public Function1<List<Types.Type>, Object> typeListIsEmpty() {
        return this.typeListIsEmpty;
    }

    @Override
    public Function1<Types.Type, Object> typeIsSubTypeOfSerializable() {
        return this.typeIsSubTypeOfSerializable;
    }

    @Override
    public Function1<Types.Type, Object> typeIsNothing() {
        return this.typeIsNothing;
    }

    @Override
    public Function1<Types.Type, Object> typeIsAny() {
        return this.typeIsAny;
    }

    @Override
    public Function1<Types.Type, Object> typeIsHigherKinded() {
        return this.typeIsHigherKinded;
    }

    @Override
    public ClassTag<Types.AnnotatedType> AnnotatedTypeTag() {
        return this.AnnotatedTypeTag;
    }

    @Override
    public ClassTag<Types.BoundedWildcardType> BoundedWildcardTypeTag() {
        return this.BoundedWildcardTypeTag;
    }

    @Override
    public ClassTag<Types.ClassInfoType> ClassInfoTypeTag() {
        return this.ClassInfoTypeTag;
    }

    @Override
    public ClassTag<Types.CompoundType> CompoundTypeTag() {
        return this.CompoundTypeTag;
    }

    @Override
    public ClassTag<Types.ConstantType> ConstantTypeTag() {
        return this.ConstantTypeTag;
    }

    @Override
    public ClassTag<Types.ExistentialType> ExistentialTypeTag() {
        return this.ExistentialTypeTag;
    }

    @Override
    public ClassTag<Types.MethodType> MethodTypeTag() {
        return this.MethodTypeTag;
    }

    @Override
    public ClassTag<Types.NullaryMethodType> NullaryMethodTypeTag() {
        return this.NullaryMethodTypeTag;
    }

    @Override
    public ClassTag<Types.PolyType> PolyTypeTag() {
        return this.PolyTypeTag;
    }

    @Override
    public ClassTag<Types.RefinedType> RefinedTypeTag() {
        return this.RefinedTypeTag;
    }

    @Override
    public ClassTag<Types.SingletonType> SingletonTypeTag() {
        return this.SingletonTypeTag;
    }

    @Override
    public ClassTag<Types.SingleType> SingleTypeTag() {
        return this.SingleTypeTag;
    }

    @Override
    public ClassTag<Types.SuperType> SuperTypeTag() {
        return this.SuperTypeTag;
    }

    @Override
    public ClassTag<Types.ThisType> ThisTypeTag() {
        return this.ThisTypeTag;
    }

    @Override
    public ClassTag<Types.TypeBounds> TypeBoundsTag() {
        return this.TypeBoundsTag;
    }

    @Override
    public ClassTag<Types.TypeRef> TypeRefTag() {
        return this.TypeRefTag;
    }

    @Override
    public ClassTag<Types.Type> TypeTagg() {
        return this.TypeTagg;
    }

    @Override
    public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$emptySymbolSet_$eq(Set x$1) {
        this.scala$reflect$internal$Types$$emptySymbolSet = x$1;
    }

    @Override
    public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$traceTypeVars_$eq(boolean x$1) {
        this.scala$reflect$internal$Types$$traceTypeVars = x$1;
    }

    @Override
    public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$breakCycles_$eq(boolean x$1) {
        this.scala$reflect$internal$Types$$breakCycles = x$1;
    }

    @Override
    public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars_$eq(boolean x$1) {
        this.scala$reflect$internal$Types$$propagateParameterBoundsToTypeVars = x$1;
    }

    @Override
    public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$sharperSkolems_$eq(boolean x$1) {
        this.scala$reflect$internal$Types$$sharperSkolems = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$enableTypeVarExperimentals_$eq(boolean x$1) {
        this.enableTypeVarExperimentals = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_intersectionWitness_$eq(WeakHashMap x$1) {
        this.scala$reflect$internal$Types$$_intersectionWitness = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$pendingVolatiles_$eq(HashSet x$1) {
        this.scala$reflect$internal$Types$$pendingVolatiles = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$initialUniquesCapacity_$eq(int x$1) {
        this.scala$reflect$internal$Types$$initialUniquesCapacity = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$missingAliasException_$eq(Types.MissingAliasControl x$1) {
        this.missingAliasException = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_pendingBaseTypes_$eq(HashSet x$1) {
        this.scala$reflect$internal$Types$$_pendingBaseTypes = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$shorthands_$eq(Set x$1) {
        this.shorthands = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$isTypeVar_$eq(Function1 x$1) {
        this.isTypeVar = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeContainsTypeVar_$eq(Function1 x$1) {
        this.typeContainsTypeVar = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsNonClassType_$eq(Function1 x$1) {
        this.typeIsNonClassType = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsExistentiallyBound_$eq(Function1 x$1) {
        this.typeIsExistentiallyBound = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsErroneous_$eq(Function1 x$1) {
        this.typeIsErroneous = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$symTypeIsError_$eq(Function1 x$1) {
        this.symTypeIsError = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$treeTpe_$eq(Function1 x$1) {
        this.treeTpe = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$symTpe_$eq(Function1 x$1) {
        this.symTpe = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$symInfo_$eq(Function1 x$1) {
        this.symInfo = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeHasAnnotations_$eq(Function1 x$1) {
        this.typeHasAnnotations = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$boundsContainType_$eq(Function2 x$1) {
        this.boundsContainType = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeListIsEmpty_$eq(Function1 x$1) {
        this.typeListIsEmpty = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsSubTypeOfSerializable_$eq(Function1 x$1) {
        this.typeIsSubTypeOfSerializable = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsNothing_$eq(Function1 x$1) {
        this.typeIsNothing = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsAny_$eq(Function1 x$1) {
        this.typeIsAny = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$typeIsHigherKinded_$eq(Function1 x$1) {
        this.typeIsHigherKinded = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$AnnotatedTypeTag_$eq(ClassTag x$1) {
        this.AnnotatedTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$BoundedWildcardTypeTag_$eq(ClassTag x$1) {
        this.BoundedWildcardTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$ClassInfoTypeTag_$eq(ClassTag x$1) {
        this.ClassInfoTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$CompoundTypeTag_$eq(ClassTag x$1) {
        this.CompoundTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$ConstantTypeTag_$eq(ClassTag x$1) {
        this.ConstantTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$ExistentialTypeTag_$eq(ClassTag x$1) {
        this.ExistentialTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$MethodTypeTag_$eq(ClassTag x$1) {
        this.MethodTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$NullaryMethodTypeTag_$eq(ClassTag x$1) {
        this.NullaryMethodTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$PolyTypeTag_$eq(ClassTag x$1) {
        this.PolyTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$RefinedTypeTag_$eq(ClassTag x$1) {
        this.RefinedTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$SingletonTypeTag_$eq(ClassTag x$1) {
        this.SingletonTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$SingleTypeTag_$eq(ClassTag x$1) {
        this.SingleTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$SuperTypeTag_$eq(ClassTag x$1) {
        this.SuperTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$ThisTypeTag_$eq(ClassTag x$1) {
        this.ThisTypeTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$TypeBoundsTag_$eq(ClassTag x$1) {
        this.TypeBoundsTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$TypeRefTag_$eq(ClassTag x$1) {
        this.TypeRefTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Types$_setter_$TypeTagg_$eq(ClassTag x$1) {
        this.TypeTagg = x$1;
    }

    @Override
    public int skolemizationLevel() {
        return Types$class.skolemizationLevel(this);
    }

    @Override
    public void skolemizationLevel_$eq(int value) {
        Types$class.skolemizationLevel_$eq(this, value);
    }

    @Override
    public WeakHashMap<List<Types.Type>, WeakReference<Types.Type>> intersectionWitness() {
        return Types$class.intersectionWitness(this);
    }

    @Override
    public void defineUnderlyingOfSingleType(Types.SingleType tpe) {
        Types$class.defineUnderlyingOfSingleType(this, tpe);
    }

    @Override
    public List<Symbols.Symbol> computeBaseClasses(Types.Type tpe) {
        return Types$class.computeBaseClasses(this, tpe);
    }

    @Override
    public void defineBaseTypeSeqOfCompoundType(Types.CompoundType tpe) {
        Types$class.defineBaseTypeSeqOfCompoundType(this, tpe);
    }

    @Override
    public void defineBaseClassesOfCompoundType(Types.CompoundType tpe) {
        Types$class.defineBaseClassesOfCompoundType(this, tpe);
    }

    @Override
    public void validateClassInfo(Types.ClassInfoType tp) {
        Types$class.validateClassInfo(this, tp);
    }

    @Override
    public Types.Type baseTypeOfNonClassTypeRef(Types.NonClassTypeRef tpe, Symbols.Symbol clazz) {
        return Types$class.baseTypeOfNonClassTypeRef(this, tpe, clazz);
    }

    @Override
    public void defineParentsOfTypeRef(Types.TypeRef tpe) {
        Types$class.defineParentsOfTypeRef(this, tpe);
    }

    @Override
    public void defineBaseTypeSeqOfTypeRef(Types.TypeRef tpe) {
        Types$class.defineBaseTypeSeqOfTypeRef(this, tpe);
    }

    @Override
    public Types.Type newExistentialType(List<Symbols.Symbol> quantified, Types.Type underlying) {
        return Types$class.newExistentialType(this, quantified, underlying);
    }

    @Override
    public Types.Type overloadedType(Types.Type pre, List<Symbols.Symbol> alternatives) {
        return Types$class.overloadedType(this, pre, alternatives);
    }

    @Override
    public Types.Type annotatedType(List<AnnotationInfos.AnnotationInfo> annots, Types.Type underlying) {
        return Types$class.annotatedType(this, annots, underlying);
    }

    @Override
    public Types.Type singleType(Types.Type pre, Symbols.Symbol sym) {
        return Types$class.singleType(this, pre, sym);
    }

    @Override
    public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2, Scopes.Scope decls, Position pos) {
        return Types$class.refinedType(this, parents2, owner2, decls, pos);
    }

    @Override
    public Types.Type refinedType(List<Types.Type> parents2, Symbols.Symbol owner2) {
        return Types$class.refinedType(this, parents2, owner2);
    }

    @Override
    public Types.Type copyRefinedType(Types.RefinedType original, List<Types.Type> parents2, Scopes.Scope decls) {
        return Types$class.copyRefinedType(this, original, parents2, decls);
    }

    @Override
    public Types.Type typeRef(Types.Type pre, Symbols.Symbol sym, List<Types.Type> args) {
        return Types$class.typeRef(this, pre, sym, args);
    }

    @Override
    public Types.Type copyTypeRef(Types.Type tp, Types.Type pre, Symbols.Symbol sym, List<Types.Type> args) {
        return Types$class.copyTypeRef(this, tp, pre, sym, args);
    }

    @Override
    public Types.JavaMethodType JavaMethodType(List<Symbols.Symbol> params2, Types.Type resultType) {
        return Types$class.JavaMethodType(this, params2, resultType);
    }

    @Override
    public Types.Type copyMethodType(Types.Type tp, List<Symbols.Symbol> params2, Types.Type restpe) {
        return Types$class.copyMethodType(this, tp, params2, restpe);
    }

    @Override
    public Types.Type intersectionType(List<Types.Type> tps, Symbols.Symbol owner2) {
        return Types$class.intersectionType(this, tps, owner2);
    }

    @Override
    public Types.Type intersectionType(List<Types.Type> tps) {
        return Types$class.intersectionType(this, tps);
    }

    @Override
    public Types.Type appliedType(Types.Type tycon, List<Types.Type> args) {
        return Types$class.appliedType(this, tycon, args);
    }

    @Override
    public Types.Type appliedType(Types.Type tycon, Seq<Types.Type> args) {
        return Types$class.appliedType(this, tycon, args);
    }

    @Override
    public Types.Type appliedType(Symbols.Symbol tyconSym, List<Types.Type> args) {
        return Types$class.appliedType(this, tyconSym, args);
    }

    @Override
    public Types.Type appliedType(Symbols.Symbol tyconSym, Seq<Types.Type> args) {
        return Types$class.appliedType(this, tyconSym, args);
    }

    @Override
    public Types.Type genPolyType(List<Symbols.Symbol> params2, Types.Type tpe) {
        return Types$class.genPolyType(this, params2, tpe);
    }

    @Override
    public Types.Type polyType(List<Symbols.Symbol> params2, Types.Type tpe) {
        return Types$class.polyType(this, params2, tpe);
    }

    @Override
    public Types.Type typeFunAnon(List<Symbols.Symbol> tps, Types.Type body2) {
        return Types$class.typeFunAnon(this, tps, body2);
    }

    @Override
    public Types.Type typeFun(List<Symbols.Symbol> tps, Types.Type body2) {
        return Types$class.typeFun(this, tps, body2);
    }

    @Override
    public Types.Type existentialAbstraction(List<Symbols.Symbol> tparams2, Types.Type tpe0) {
        return Types$class.existentialAbstraction(this, tparams2, tpe0);
    }

    @Override
    public <T extends Types.Type> T unique(T tp) {
        return (T)Types$class.unique(this, tp);
    }

    @Override
    public Types.Type elementExtract(Symbols.Symbol container, Types.Type tp) {
        return Types$class.elementExtract(this, container, tp);
    }

    @Override
    public Option<Types.Type> elementExtractOption(Symbols.Symbol container, Types.Type tp) {
        return Types$class.elementExtractOption(this, container, tp);
    }

    @Override
    public boolean elementTest(Symbols.Symbol container, Types.Type tp, Function1<Types.Type, Object> f) {
        return Types$class.elementTest(this, container, tp, f);
    }

    @Override
    public Types.Type elementTransform(Symbols.Symbol container, Types.Type tp, Function1<Types.Type, Types.Type> f) {
        return Types$class.elementTransform(this, container, tp, f);
    }

    @Override
    public Types.Type transparentShallowTransform(Symbols.Symbol container, Types.Type tp, Function1<Types.Type, Types.Type> f) {
        return Types$class.transparentShallowTransform(this, container, tp, f);
    }

    @Override
    public Types.Type repackExistential(Types.Type tp) {
        return Types$class.repackExistential(this, tp);
    }

    @Override
    public boolean containsExistential(Types.Type tpe) {
        return Types$class.containsExistential(this, tpe);
    }

    @Override
    public List<Symbols.Symbol> existentialsInType(Types.Type tpe) {
        return Types$class.existentialsInType(this, tpe);
    }

    @Override
    public boolean isDummyAppliedType(Types.Type tp) {
        return Types$class.isDummyAppliedType(this, tp);
    }

    @Override
    public List<Symbols.Symbol> typeParamsToExistentials(Symbols.Symbol clazz, List<Symbols.Symbol> tparams2) {
        return Types$class.typeParamsToExistentials(this, clazz, tparams2);
    }

    @Override
    public List<Symbols.Symbol> typeParamsToExistentials(Symbols.Symbol clazz) {
        return Types$class.typeParamsToExistentials(this, clazz);
    }

    @Override
    public boolean isRawIfWithoutArgs(Symbols.Symbol sym) {
        return Types$class.isRawIfWithoutArgs(this, sym);
    }

    @Override
    public boolean isRawType(Types.Type tp) {
        return Types$class.isRawType(this, tp);
    }

    @Override
    public boolean isRaw(Symbols.Symbol sym, List<Types.Type> args) {
        return Types$class.isRaw(this, sym, args);
    }

    @Override
    public Types.TypeBounds singletonBounds(Types.Type hi) {
        return Types$class.singletonBounds(this, hi);
    }

    @Override
    public Types.Type nestedMemberType(Symbols.Symbol sym, Types.Type pre, Symbols.Symbol owner2) {
        return Types$class.nestedMemberType(this, sym, pre, owner2);
    }

    @Override
    public int lubDepth(List<Types.Type> ts) {
        return Types$class.lubDepth(this, ts);
    }

    @Override
    public boolean isPopulated(Types.Type tp1, Types.Type tp2) {
        return Types$class.isPopulated(this, tp1, tp2);
    }

    @Override
    public boolean needsOuterTest(Types.Type patType, Types.Type selType, Symbols.Symbol currentOwner) {
        return Types$class.needsOuterTest(this, patType, selType, currentOwner);
    }

    @Override
    public Types.Type normalizePlus(Types.Type tp) {
        return Types$class.normalizePlus(this, tp);
    }

    @Override
    public boolean isSameTypes(List<Types.Type> tps1, List<Types.Type> tps2) {
        return Types$class.isSameTypes(this, tps1, tps2);
    }

    @Override
    public final boolean sameLength(List<?> xs1, List<?> xs2) {
        return Types$class.sameLength(this, xs1, xs2);
    }

    @Override
    public final int compareLengths(List<?> xs1, List<?> xs2) {
        return Types$class.compareLengths(this, xs1, xs2);
    }

    @Override
    public final boolean hasLength(List<?> xs, int len) {
        return Types$class.hasLength(this, xs, len);
    }

    @Override
    public int basetypeRecursions() {
        return Types$class.basetypeRecursions(this);
    }

    @Override
    public void basetypeRecursions_$eq(int value) {
        Types$class.basetypeRecursions_$eq(this, value);
    }

    @Override
    public HashSet<Types.Type> pendingBaseTypes() {
        return Types$class.pendingBaseTypes(this);
    }

    @Override
    public boolean isEligibleForPrefixUnification(Types.Type tp) {
        return Types$class.isEligibleForPrefixUnification(this, tp);
    }

    @Override
    public boolean isErrorOrWildcard(Types.Type tp) {
        return Types$class.isErrorOrWildcard(this, tp);
    }

    @Override
    public boolean isSingleType(Types.Type tp) {
        return Types$class.isSingleType(this, tp);
    }

    @Override
    public boolean isConstantType(Types.Type tp) {
        return Types$class.isConstantType(this, tp);
    }

    @Override
    public boolean isExistentialType(Types.Type tp) {
        return Types$class.isExistentialType(this, tp);
    }

    @Override
    public boolean isImplicitMethodType(Types.Type tp) {
        return Types$class.isImplicitMethodType(this, tp);
    }

    @Override
    public boolean isUseableAsTypeArg(Types.Type tp) {
        return Types$class.isUseableAsTypeArg(this, tp);
    }

    @Override
    public final boolean isUseableAsTypeArgs(List<Types.Type> tps) {
        return Types$class.isUseableAsTypeArgs(this, tps);
    }

    @Override
    public boolean isNonRefinementClassType(Types.Type tpe) {
        return Types$class.isNonRefinementClassType(this, tpe);
    }

    @Override
    public boolean isSubArgs(List<Types.Type> tps1, List<Types.Type> tps2, List<Symbols.Symbol> tparams2, int depth) {
        return Types$class.isSubArgs(this, tps1, tps2, tparams2, depth);
    }

    @Override
    public boolean specializesSym(Types.Type tp, Symbols.Symbol sym, int depth) {
        return Types$class.specializesSym(this, tp, sym, depth);
    }

    @Override
    public boolean specializesSym(Types.Type preLo, Symbols.Symbol symLo, Types.Type preHi, Symbols.Symbol symHi, int depth) {
        return Types$class.specializesSym(this, preLo, symLo, preHi, symHi, depth);
    }

    @Override
    public final boolean matchesType(Types.Type tp1, Types.Type tp2, boolean alwaysMatchSimple) {
        return Types$class.matchesType(this, tp1, tp2, alwaysMatchSimple);
    }

    @Override
    public boolean matchingParams(List<Symbols.Symbol> syms1, List<Symbols.Symbol> syms2, boolean syms1isJava, boolean syms2isJava) {
        return Types$class.matchingParams(this, syms1, syms2, syms1isJava, syms2isJava);
    }

    @Override
    public boolean isWithinBounds(Types.Type pre, Symbols.Symbol owner2, List<Symbols.Symbol> tparams2, List<Types.Type> targs) {
        return Types$class.isWithinBounds(this, pre, owner2, tparams2, targs);
    }

    @Override
    public List<Types.TypeBounds> instantiatedBounds(Types.Type pre, Symbols.Symbol owner2, List<Symbols.Symbol> tparams2, List<Types.Type> targs) {
        return Types$class.instantiatedBounds(this, pre, owner2, tparams2, targs);
    }

    @Override
    public Types.Type elimAnonymousClass(Types.Type t) {
        return Types$class.elimAnonymousClass(this, t);
    }

    @Override
    public List<Types.TypeVar> typeVarsInType(Types.Type tp) {
        return Types$class.typeVarsInType(this, tp);
    }

    @Override
    public final <T> T suspendingTypeVars(List<Types.TypeVar> tvs, Function0<T> op) {
        return (T)Types$class.suspendingTypeVars(this, tvs, op);
    }

    @Override
    public Types.Type mergePrefixAndArgs(List<Types.Type> tps, int variance, int depth) {
        return Types$class.mergePrefixAndArgs(this, tps, variance, depth);
    }

    @Override
    public void addMember(Types.Type thistp, Types.Type tp, Symbols.Symbol sym) {
        Types$class.addMember(this, thistp, tp, sym);
    }

    @Override
    public void addMember(Types.Type thistp, Types.Type tp, Symbols.Symbol sym, int depth) {
        Types$class.addMember(this, thistp, tp, sym, depth);
    }

    @Override
    public boolean isJavaVarargsAncestor(Symbols.Symbol clazz) {
        return Types$class.isJavaVarargsAncestor(this, clazz);
    }

    @Override
    public boolean inheritsJavaVarArgsMethod(Symbols.Symbol clazz) {
        return Types$class.inheritsJavaVarArgsMethod(this, clazz);
    }

    @Override
    public String indent() {
        return Types$class.indent(this);
    }

    @Override
    public void indent_$eq(String value) {
        Types$class.indent_$eq(this, value);
    }

    @Override
    public <T> boolean explain(String op, Function2<Types.Type, T, Object> p, Types.Type tp1, T arg2) {
        return Types$class.explain(this, op, p, tp1, arg2);
    }

    @Override
    public void explainTypes(Types.Type found, Types.Type required) {
        Types$class.explainTypes(this, found, required);
    }

    @Override
    public void explainTypes(Function2<Types.Type, Types.Type, Object> op, Types.Type found, Types.Type required) {
        Types$class.explainTypes(this, op, found, required);
    }

    @Override
    public <A> A withTypesExplained(Function0<A> op) {
        return (A)Types$class.withTypesExplained(this, op);
    }

    @Override
    public boolean isUnboundedGeneric(Types.Type tp) {
        return Types$class.isUnboundedGeneric(this, tp);
    }

    @Override
    public boolean isBoundedGeneric(Types.Type tp) {
        return Types$class.isBoundedGeneric(this, tp);
    }

    @Override
    public List<Types.Type> addSerializable(Seq<Types.Type> ps) {
        return Types$class.addSerializable(this, ps);
    }

    @Override
    public final Types.Type uncheckedBounds(Types.Type tp) {
        return Types$class.uncheckedBounds(this, tp);
    }

    @Override
    public Scopes.Scope nonTrivialMembers(Symbols.Symbol clazz) {
        return Types$class.nonTrivialMembers(this, clazz);
    }

    @Override
    public Scopes.Scope importableMembers(Types.Type pre) {
        return Types$class.importableMembers(this, pre);
    }

    @Override
    public Types.Type objToAny(Types.Type tp) {
        return Types$class.objToAny(this, tp);
    }

    @Override
    public int typeDepth(Types.Type tp) {
        return Types$class.typeDepth(this, tp);
    }

    @Override
    public int maxDepth(List<Types.Type> tps) {
        return Types$class.maxDepth(this, tps);
    }

    @Override
    public int maxbaseTypeSeqDepth(List<Types.Type> tps) {
        return Types$class.maxbaseTypeSeqDepth(this, tps);
    }

    private TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x800) == 0) {
                this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog = TypeConstraints$class.scala$reflect$internal$tpe$TypeConstraints$$_undoLog(this);
                this.bitmap$0 |= 0x800;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
        }
    }

    @Override
    public TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog() {
        return (this.bitmap$0 & 0x800) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
    }

    private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x1000) == 0) {
                this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound = TypeConstraints$class.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound(this);
                this.bitmap$0 |= 0x1000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
        }
    }

    @Override
    public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound() {
        return (this.bitmap$0 & 0x1000) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
    }

    private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x2000) == 0) {
                this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound = TypeConstraints$class.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound(this);
                this.bitmap$0 |= 0x2000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
        }
    }

    @Override
    public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound() {
        return (this.bitmap$0 & 0x2000) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
    }

    private TypeConstraints$TypeConstraint$ TypeConstraint$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeConstraint$module == null) {
                this.TypeConstraint$module = new TypeConstraints$TypeConstraint$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeConstraint$module;
        }
    }

    @Override
    public TypeConstraints$TypeConstraint$ TypeConstraint() {
        return this.TypeConstraint$module == null ? this.TypeConstraint$lzycompute() : this.TypeConstraint$module;
    }

    @Override
    public TypeConstraints.UndoLog undoLog() {
        return TypeConstraints$class.undoLog(this);
    }

    @Override
    public boolean solve(List<Types.TypeVar> tvars, List<Symbols.Symbol> tparams2, List<Variance> variances, boolean upper, int depth) {
        return TypeConstraints$class.solve(this, tvars, tparams2, variances, upper, depth);
    }

    private TypeMaps$normalizeAliases$ normalizeAliases$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.normalizeAliases$module == null) {
                this.normalizeAliases$module = new TypeMaps$normalizeAliases$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.normalizeAliases$module;
        }
    }

    @Override
    public TypeMaps$normalizeAliases$ normalizeAliases() {
        return this.normalizeAliases$module == null ? this.normalizeAliases$lzycompute() : this.normalizeAliases$module;
    }

    private TypeMaps$dropSingletonType$ dropSingletonType$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.dropSingletonType$module == null) {
                this.dropSingletonType$module = new TypeMaps$dropSingletonType$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.dropSingletonType$module;
        }
    }

    @Override
    public TypeMaps$dropSingletonType$ dropSingletonType() {
        return this.dropSingletonType$module == null ? this.dropSingletonType$lzycompute() : this.dropSingletonType$module;
    }

    private TypeMaps$abstractTypesToBounds$ abstractTypesToBounds$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.abstractTypesToBounds$module == null) {
                this.abstractTypesToBounds$module = new TypeMaps$abstractTypesToBounds$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.abstractTypesToBounds$module;
        }
    }

    @Override
    public TypeMaps$abstractTypesToBounds$ abstractTypesToBounds() {
        return this.abstractTypesToBounds$module == null ? this.abstractTypesToBounds$lzycompute() : this.abstractTypesToBounds$module;
    }

    private TypeMaps$dropIllegalStarTypes$ dropIllegalStarTypes$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.dropIllegalStarTypes$module == null) {
                this.dropIllegalStarTypes$module = new TypeMaps$dropIllegalStarTypes$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.dropIllegalStarTypes$module;
        }
    }

    @Override
    public TypeMaps$dropIllegalStarTypes$ dropIllegalStarTypes() {
        return this.dropIllegalStarTypes$module == null ? this.dropIllegalStarTypes$lzycompute() : this.dropIllegalStarTypes$module;
    }

    private TypeMaps$wildcardExtrapolation$ wildcardExtrapolation$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.wildcardExtrapolation$module == null) {
                this.wildcardExtrapolation$module = new TypeMaps$wildcardExtrapolation$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.wildcardExtrapolation$module;
        }
    }

    @Override
    public TypeMaps$wildcardExtrapolation$ wildcardExtrapolation() {
        return this.wildcardExtrapolation$module == null ? this.wildcardExtrapolation$lzycompute() : this.wildcardExtrapolation$module;
    }

    private TypeMaps$IsDependentCollector$ IsDependentCollector$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.IsDependentCollector$module == null) {
                this.IsDependentCollector$module = new TypeMaps$IsDependentCollector$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.IsDependentCollector$module;
        }
    }

    @Override
    public TypeMaps$IsDependentCollector$ IsDependentCollector() {
        return this.IsDependentCollector$module == null ? this.IsDependentCollector$lzycompute() : this.IsDependentCollector$module;
    }

    private TypeMaps$ApproximateDependentMap$ ApproximateDependentMap$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ApproximateDependentMap$module == null) {
                this.ApproximateDependentMap$module = new TypeMaps$ApproximateDependentMap$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ApproximateDependentMap$module;
        }
    }

    @Override
    public TypeMaps$ApproximateDependentMap$ ApproximateDependentMap() {
        return this.ApproximateDependentMap$module == null ? this.ApproximateDependentMap$lzycompute() : this.ApproximateDependentMap$module;
    }

    private TypeMaps$wildcardToTypeVarMap$ wildcardToTypeVarMap$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.wildcardToTypeVarMap$module == null) {
                this.wildcardToTypeVarMap$module = new TypeMaps$wildcardToTypeVarMap$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.wildcardToTypeVarMap$module;
        }
    }

    @Override
    public TypeMaps$wildcardToTypeVarMap$ wildcardToTypeVarMap() {
        return this.wildcardToTypeVarMap$module == null ? this.wildcardToTypeVarMap$lzycompute() : this.wildcardToTypeVarMap$module;
    }

    private TypeMaps$typeVarToOriginMap$ typeVarToOriginMap$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.typeVarToOriginMap$module == null) {
                this.typeVarToOriginMap$module = new TypeMaps$typeVarToOriginMap$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.typeVarToOriginMap$module;
        }
    }

    @Override
    public TypeMaps$typeVarToOriginMap$ typeVarToOriginMap() {
        return this.typeVarToOriginMap$module == null ? this.typeVarToOriginMap$lzycompute() : this.typeVarToOriginMap$module;
    }

    private TypeMaps$ErroneousCollector$ ErroneousCollector$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.ErroneousCollector$module == null) {
                this.ErroneousCollector$module = new TypeMaps$ErroneousCollector$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ErroneousCollector$module;
        }
    }

    @Override
    public TypeMaps$ErroneousCollector$ ErroneousCollector() {
        return this.ErroneousCollector$module == null ? this.ErroneousCollector$lzycompute() : this.ErroneousCollector$module;
    }

    private TypeMaps$adaptToNewRunMap$ adaptToNewRunMap$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.adaptToNewRunMap$module == null) {
                this.adaptToNewRunMap$module = new TypeMaps$adaptToNewRunMap$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.adaptToNewRunMap$module;
        }
    }

    @Override
    public TypeMaps$adaptToNewRunMap$ adaptToNewRunMap() {
        return this.adaptToNewRunMap$module == null ? this.adaptToNewRunMap$lzycompute() : this.adaptToNewRunMap$module;
    }

    @Override
    public boolean etaExpandKeepsStar() {
        return TypeMaps$class.etaExpandKeepsStar(this);
    }

    @Override
    public TypeMaps.TypeMap rawToExistential() {
        return TypeMaps$class.rawToExistential(this);
    }

    @Override
    public boolean isPossiblePrefix(Symbols.Symbol clazz) {
        return TypeMaps$class.isPossiblePrefix(this, clazz);
    }

    @Override
    public boolean skipPrefixOf(Types.Type pre, Symbols.Symbol clazz) {
        return TypeMaps$class.skipPrefixOf(this, pre, clazz);
    }

    @Override
    public TypeMaps.AsSeenFromMap newAsSeenFromMap(Types.Type pre, Symbols.Symbol clazz) {
        return TypeMaps$class.newAsSeenFromMap(this, pre, clazz);
    }

    @Override
    public final boolean scala$reflect$internal$tpe$GlbLubs$$printLubs() {
        return this.scala$reflect$internal$tpe$GlbLubs$$printLubs;
    }

    @Override
    public final MutableSettings.SettingValue scala$reflect$internal$tpe$GlbLubs$$strictInference() {
        return this.scala$reflect$internal$tpe$GlbLubs$$strictInference;
    }

    @Override
    public final boolean scala$reflect$internal$tpe$GlbLubs$$verifyLubs() {
        return true;
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_lubResults() {
        return this.scala$reflect$internal$tpe$GlbLubs$$_lubResults;
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> scala$reflect$internal$tpe$GlbLubs$$_glbResults() {
        return this.scala$reflect$internal$tpe$GlbLubs$$_glbResults;
    }

    @Override
    public Throwable GlbFailure() {
        return this.GlbFailure;
    }

    @Override
    public int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth() {
        return this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth;
    }

    @Override
    public void scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(int x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth = x$1;
    }

    @Override
    public final int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit() {
        return this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit;
    }

    @Override
    public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(boolean x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$printLubs = x$1;
    }

    @Override
    public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$strictInference_$eq(MutableSettings.SettingValue x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$strictInference = x$1;
    }

    @Override
    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(HashMap x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$_lubResults = x$1;
    }

    @Override
    public void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(HashMap x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$_glbResults = x$1;
    }

    @Override
    public void scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(Throwable x$1) {
        this.GlbFailure = x$1;
    }

    @Override
    public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(int x$1) {
        this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit = x$1;
    }

    @Override
    public List<Tuple2<Symbols.Symbol, Symbols.Symbol>> findRecursiveBounds(List<Types.Type> ts) {
        return GlbLubs$class.findRecursiveBounds(this, ts);
    }

    @Override
    public List<Types.Type> lubList(List<Types.Type> ts, int depth) {
        return GlbLubs$class.lubList(this, ts, depth);
    }

    @Override
    public List<Types.Type> spanningTypes(List<Types.Type> ts) {
        return GlbLubs$class.spanningTypes(this, ts);
    }

    @Override
    public boolean sameWeakLubAsLub(List<Types.Type> tps) {
        return GlbLubs$class.sameWeakLubAsLub(this, tps);
    }

    @Override
    public Types.Type weakLub(List<Types.Type> tps) {
        return GlbLubs$class.weakLub(this, tps);
    }

    @Override
    public Types.Type numericLub(List<Types.Type> ts) {
        return GlbLubs$class.numericLub(this, ts);
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> lubResults() {
        return GlbLubs$class.lubResults(this);
    }

    @Override
    public HashMap<Tuple2<Depth, List<Types.Type>>, Types.Type> glbResults() {
        return GlbLubs$class.glbResults(this);
    }

    @Override
    public Types.Type lub(List<Types.Type> ts) {
        return GlbLubs$class.lub(this, ts);
    }

    @Override
    public Types.Type lub(List<Types.Type> ts, int depth) {
        return GlbLubs$class.lub(this, ts, depth);
    }

    @Override
    public Types.Type glb(List<Types.Type> ts) {
        return GlbLubs$class.glb(this, ts);
    }

    @Override
    public Types.Type glb(List<Types.Type> ts, int depth) {
        return GlbLubs$class.glb(this, ts, depth);
    }

    @Override
    public Types.Type glbNorm(List<Types.Type> ts, int depth) {
        return GlbLubs$class.glbNorm(this, ts, depth);
    }

    private CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x4000) == 0) {
                this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj = CommonOwners$class.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj(this);
                this.bitmap$0 |= 0x4000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
        }
    }

    @Override
    public CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj() {
        return (this.bitmap$0 & 0x4000) == 0 ? this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$lzycompute() : this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
    }

    @Override
    public Symbols.Symbol commonOwner(Types.Type t) {
        return CommonOwners$class.commonOwner(this, t);
    }

    @Override
    public Symbols.Symbol commonOwner(List<Types.Type> tps) {
        return CommonOwners$class.commonOwner(this, tps);
    }

    @Override
    public CommonOwners.CommonOwnerMap commonOwnerMap() {
        return CommonOwners$class.commonOwnerMap(this);
    }

    @Override
    public final int maxToStringRecursions() {
        return 50;
    }

    @Override
    public int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions() {
        return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions;
    }

    @Override
    public void scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(int x$1) {
        this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions = x$1;
    }

    @Override
    public HashSet<Types.Type> scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects() {
        return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects;
    }

    @Override
    public void scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq(HashSet<Types.Type> x$1) {
        this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects = x$1;
    }

    @Override
    public int toStringRecursions() {
        return TypeToStrings$class.toStringRecursions(this);
    }

    @Override
    public void toStringRecursions_$eq(int value) {
        TypeToStrings$class.toStringRecursions_$eq(this, value);
    }

    @Override
    public HashSet<Types.Type> toStringSubjects() {
        return TypeToStrings$class.toStringSubjects(this);
    }

    @Override
    public String typeToString(Types.Type tpe) {
        return TypeToStrings$class.typeToString(this, tpe);
    }

    @Override
    public final int scala$reflect$internal$tpe$TypeComparers$$LogPendingSubTypesThreshold() {
        return 50;
    }

    @Override
    public HashSet<TypeComparers.SubTypePair> scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes() {
        return this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes;
    }

    private TypeComparers$SubTypePair$ SubTypePair$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SubTypePair$module == null) {
                this.SubTypePair$module = new TypeComparers$SubTypePair$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SubTypePair$module;
        }
    }

    @Override
    public TypeComparers$SubTypePair$ SubTypePair() {
        return this.SubTypePair$module == null ? this.SubTypePair$lzycompute() : this.SubTypePair$module;
    }

    @Override
    public int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions() {
        return this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions;
    }

    @Override
    public void scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(int x$1) {
        this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions = x$1;
    }

    @Override
    public void scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(HashSet x$1) {
        this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes = x$1;
    }

    @Override
    public HashSet<TypeComparers.SubTypePair> pendingSubTypes() {
        return TypeComparers$class.pendingSubTypes(this);
    }

    @Override
    public int subsametypeRecursions() {
        return TypeComparers$class.subsametypeRecursions(this);
    }

    @Override
    public void subsametypeRecursions_$eq(int value) {
        TypeComparers$class.subsametypeRecursions_$eq(this, value);
    }

    @Override
    public boolean isDifferentType(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isDifferentType(this, tp1, tp2);
    }

    @Override
    public boolean isDifferentTypeConstructor(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isDifferentTypeConstructor(this, tp1, tp2);
    }

    @Override
    public boolean isSameType(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isSameType(this, tp1, tp2);
    }

    @Override
    public boolean isSameType2(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isSameType2(this, tp1, tp2);
    }

    @Override
    public boolean isSubType(Types.Type tp1, Types.Type tp2, int depth) {
        return TypeComparers$class.isSubType(this, tp1, tp2, depth);
    }

    @Override
    public boolean isHKSubType(Types.Type tp1, Types.Type tp2, int depth) {
        return TypeComparers$class.isHKSubType(this, tp1, tp2, depth);
    }

    @Override
    public boolean isWeakSubType(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isWeakSubType(this, tp1, tp2);
    }

    @Override
    public boolean isNumericSubType(Types.Type tp1, Types.Type tp2) {
        return TypeComparers$class.isNumericSubType(this, tp1, tp2);
    }

    @Override
    public int isSubType$default$3() {
        return TypeComparers$class.isSubType$default$3(this);
    }

    @Override
    public int ids() {
        return this.ids;
    }

    @Override
    public void ids_$eq(int x$1) {
        this.ids = x$1;
    }

    @Override
    public Map<Symbols.Symbol, Object> scala$reflect$internal$Symbols$$_recursionTable() {
        return this.scala$reflect$internal$Symbols$$_recursionTable;
    }

    @Override
    public void scala$reflect$internal$Symbols$$_recursionTable_$eq(Map<Symbols.Symbol, Object> x$1) {
        this.scala$reflect$internal$Symbols$$_recursionTable = x$1;
    }

    @Override
    public int scala$reflect$internal$Symbols$$existentialIds() {
        return this.scala$reflect$internal$Symbols$$existentialIds;
    }

    @Override
    public void scala$reflect$internal$Symbols$$existentialIds_$eq(int x$1) {
        this.scala$reflect$internal$Symbols$$existentialIds = x$1;
    }

    @Override
    public HashMap<Symbols.Symbol, Symbols.Symbol> scala$reflect$internal$Symbols$$originalOwnerMap() {
        return this.scala$reflect$internal$Symbols$$originalOwnerMap;
    }

    private Symbols$SymbolKind$ SymbolKind$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SymbolKind$module == null) {
                this.SymbolKind$module = new Symbols$SymbolKind$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SymbolKind$module;
        }
    }

    @Override
    public Symbols$SymbolKind$ SymbolKind() {
        return this.SymbolKind$module == null ? this.SymbolKind$lzycompute() : this.SymbolKind$module;
    }

    @Override
    public ClassTag<Symbols.Symbol> SymbolTag() {
        return this.SymbolTag;
    }

    @Override
    public ClassTag<Symbols.TermSymbol> TermSymbolTag() {
        return this.TermSymbolTag;
    }

    @Override
    public ClassTag<Symbols.ModuleSymbol> ModuleSymbolTag() {
        return this.ModuleSymbolTag;
    }

    @Override
    public ClassTag<Symbols.MethodSymbol> MethodSymbolTag() {
        return this.MethodSymbolTag;
    }

    @Override
    public ClassTag<Symbols.TypeSymbol> TypeSymbolTag() {
        return this.TypeSymbolTag;
    }

    @Override
    public ClassTag<Symbols.ClassSymbol> ClassSymbolTag() {
        return this.ClassSymbolTag;
    }

    @Override
    public ClassTag<Symbols.FreeTermSymbol> FreeTermSymbolTag() {
        return this.FreeTermSymbolTag;
    }

    @Override
    public ClassTag<Symbols.FreeTypeSymbol> FreeTypeSymbolTag() {
        return this.FreeTypeSymbolTag;
    }

    private Symbols.NoSymbol NoSymbol$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if ((this.bitmap$0 & 0x8000) == 0) {
                this.NoSymbol = Symbols$class.NoSymbol(this);
                this.bitmap$0 |= 0x8000;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.NoSymbol;
        }
    }

    @Override
    public Symbols.NoSymbol NoSymbol() {
        return (this.bitmap$0 & 0x8000) == 0 ? this.NoSymbol$lzycompute() : this.NoSymbol;
    }

    private Symbols$CyclicReference$ CyclicReference$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.CyclicReference$module == null) {
                this.CyclicReference$module = new Symbols$CyclicReference$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.CyclicReference$module;
        }
    }

    @Override
    public Symbols$CyclicReference$ CyclicReference() {
        return this.CyclicReference$module == null ? this.CyclicReference$lzycompute() : this.CyclicReference$module;
    }

    private Symbols$TypeHistory$ scala$reflect$internal$Symbols$$TypeHistory$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.scala$reflect$internal$Symbols$$TypeHistory$module == null) {
                this.scala$reflect$internal$Symbols$$TypeHistory$module = new Symbols$TypeHistory$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scala$reflect$internal$Symbols$$TypeHistory$module;
        }
    }

    @Override
    public Symbols$TypeHistory$ scala$reflect$internal$Symbols$$TypeHistory() {
        return this.scala$reflect$internal$Symbols$$TypeHistory$module == null ? this.scala$reflect$internal$Symbols$$TypeHistory$lzycompute() : this.scala$reflect$internal$Symbols$$TypeHistory$module;
    }

    @Override
    public final Function1<Symbols.Symbol, Object> symbolIsPossibleInRefinement() {
        return this.symbolIsPossibleInRefinement;
    }

    private Symbols$SymbolOps$ SymbolOps$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.SymbolOps$module == null) {
                this.SymbolOps$module = new Symbols$SymbolOps$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SymbolOps$module;
        }
    }

    @Override
    public Symbols$SymbolOps$ SymbolOps() {
        return this.SymbolOps$module == null ? this.SymbolOps$lzycompute() : this.SymbolOps$module;
    }

    @Override
    public Symbols.SymbolOps AllOps() {
        return this.AllOps;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$originalOwnerMap_$eq(HashMap x$1) {
        this.scala$reflect$internal$Symbols$$originalOwnerMap = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$SymbolTag_$eq(ClassTag x$1) {
        this.SymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$TermSymbolTag_$eq(ClassTag x$1) {
        this.TermSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$ModuleSymbolTag_$eq(ClassTag x$1) {
        this.ModuleSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$MethodSymbolTag_$eq(ClassTag x$1) {
        this.MethodSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$TypeSymbolTag_$eq(ClassTag x$1) {
        this.TypeSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$ClassSymbolTag_$eq(ClassTag x$1) {
        this.ClassSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$FreeTermSymbolTag_$eq(ClassTag x$1) {
        this.FreeTermSymbolTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$FreeTypeSymbolTag_$eq(ClassTag x$1) {
        this.FreeTypeSymbolTag = x$1;
    }

    @Override
    public final void scala$reflect$internal$Symbols$_setter_$symbolIsPossibleInRefinement_$eq(Function1 x$1) {
        this.symbolIsPossibleInRefinement = x$1;
    }

    @Override
    public void scala$reflect$internal$Symbols$_setter_$AllOps_$eq(Symbols.SymbolOps x$1) {
        this.AllOps = x$1;
    }

    @Override
    public int nextId() {
        return Symbols$class.nextId(this);
    }

    @Override
    public Map<Symbols.Symbol, Object> recursionTable() {
        return Symbols$class.recursionTable(this);
    }

    @Override
    public void recursionTable_$eq(Map<Symbols.Symbol, Object> value) {
        Symbols$class.recursionTable_$eq(this, value);
    }

    @Override
    public int nextExistentialId() {
        return Symbols$class.nextExistentialId(this);
    }

    @Override
    public Names.TypeName freshExistentialName(String suffix) {
        return Symbols$class.freshExistentialName(this, suffix);
    }

    @Override
    public Symbols.ModuleSymbol connectModuleToClass(Symbols.ModuleSymbol m, Symbols.ClassSymbol moduleClass) {
        return Symbols$class.connectModuleToClass(this, m, moduleClass);
    }

    @Override
    public Symbols.FreeTermSymbol newFreeTermSymbol(Names.TermName name, Function0<Object> value, long flags, String origin) {
        return Symbols$class.newFreeTermSymbol(this, name, value, flags, origin);
    }

    @Override
    public Symbols.FreeTypeSymbol newFreeTypeSymbol(Names.TypeName name, long flags, String origin) {
        return Symbols$class.newFreeTypeSymbol(this, name, flags, origin);
    }

    @Override
    public void saveOriginalOwner(Symbols.Symbol sym) {
        Symbols$class.saveOriginalOwner(this, sym);
    }

    @Override
    public void defineOriginalOwner(Symbols.Symbol sym, Symbols.Symbol owner2) {
        Symbols$class.defineOriginalOwner(this, sym, owner2);
    }

    @Override
    public <T> Symbols.TypeSymbol symbolOf(TypeTags.WeakTypeTag<T> evidence$1) {
        return Symbols$class.symbolOf(this, evidence$1);
    }

    @Override
    public Symbols.Symbol newStubSymbol(Symbols.Symbol owner2, Names.Name name, String missingMessage, boolean isPackage) {
        return Symbols$class.newStubSymbol(this, owner2, name, missingMessage, isPackage);
    }

    @Override
    public Symbols.NoSymbol makeNoSymbol() {
        return Symbols$class.makeNoSymbol(this);
    }

    @Override
    public List<Symbols.Symbol> deriveSymbols(List<Symbols.Symbol> syms, Function1<Symbols.Symbol, Symbols.Symbol> symFn) {
        return Symbols$class.deriveSymbols(this, syms, symFn);
    }

    @Override
    public <A> List<Symbols.Symbol> deriveSymbols2(List<Symbols.Symbol> syms, List<A> as, Function2<Symbols.Symbol, A, Symbols.Symbol> symFn) {
        return Symbols$class.deriveSymbols2(this, syms, as, symFn);
    }

    @Override
    public Types.Type deriveType(List<Symbols.Symbol> syms, Function1<Symbols.Symbol, Symbols.Symbol> symFn, Types.Type tpe) {
        return Symbols$class.deriveType(this, syms, symFn, tpe);
    }

    @Override
    public <A> Types.Type deriveType2(List<Symbols.Symbol> syms, List<A> as, Function2<Symbols.Symbol, A, Symbols.Symbol> symFn, Types.Type tpe) {
        return Symbols$class.deriveType2(this, syms, as, symFn, tpe);
    }

    @Override
    public Types.Type deriveTypeWithWildcards(List<Symbols.Symbol> syms, Types.Type tpe) {
        return Symbols$class.deriveTypeWithWildcards(this, syms, tpe);
    }

    @Override
    public List<Symbols.Symbol> cloneSymbols(List<Symbols.Symbol> syms) {
        return Symbols$class.cloneSymbols(this, syms);
    }

    @Override
    public List<Symbols.Symbol> cloneSymbolsAtOwner(List<Symbols.Symbol> syms, Symbols.Symbol owner2) {
        return Symbols$class.cloneSymbolsAtOwner(this, syms, owner2);
    }

    @Override
    public List<Symbols.Symbol> cloneSymbolsAndModify(List<Symbols.Symbol> syms, Function1<Types.Type, Types.Type> infoFn) {
        return Symbols$class.cloneSymbolsAndModify(this, syms, infoFn);
    }

    @Override
    public List<Symbols.Symbol> cloneSymbolsAtOwnerAndModify(List<Symbols.Symbol> syms, Symbols.Symbol owner2, Function1<Types.Type, Types.Type> infoFn) {
        return Symbols$class.cloneSymbolsAtOwnerAndModify(this, syms, owner2, infoFn);
    }

    @Override
    public <T> T createFromClonedSymbols(List<Symbols.Symbol> syms, Types.Type tpe, Function2<List<Symbols.Symbol>, Types.Type, T> creator) {
        return (T)Symbols$class.createFromClonedSymbols(this, syms, tpe, creator);
    }

    @Override
    public <T> T createFromClonedSymbolsAtOwner(List<Symbols.Symbol> syms, Symbols.Symbol owner2, Types.Type tpe, Function2<List<Symbols.Symbol>, Types.Type, T> creator) {
        return (T)Symbols$class.createFromClonedSymbolsAtOwner(this, syms, owner2, tpe, creator);
    }

    @Override
    public <T> List<List<T>> mapParamss(Symbols.Symbol sym, Function1<Symbols.Symbol, T> f) {
        return Symbols$class.mapParamss(this, sym, f);
    }

    @Override
    public List<Symbols.Symbol> existingSymbols(List<Symbols.Symbol> syms) {
        return Symbols$class.existingSymbols(this, syms);
    }

    @Override
    public final Symbols.Symbol closestEnclMethod(Symbols.Symbol from2) {
        return Symbols$class.closestEnclMethod(this, from2);
    }

    @Override
    public final boolean allSymbolsHaveOwner(List<Symbols.Symbol> syms, Symbols.Symbol owner2) {
        return Symbols$class.allSymbolsHaveOwner(this, syms, owner2);
    }

    @Override
    public Symbols.SymbolOps FlagOps(long mask) {
        return Symbols$class.FlagOps(this, mask);
    }

    @Override
    public void markFlagsCompleted(Seq<Symbols.Symbol> syms, long mask) {
        Symbols$class.markFlagsCompleted(this, syms, mask);
    }

    @Override
    public void markAllCompleted(Seq<Symbols.Symbol> syms) {
        Symbols$class.markAllCompleted(this, syms);
    }

    @Override
    public long newFreeTermSymbol$default$3() {
        return Symbols$class.newFreeTermSymbol$default$3(this);
    }

    @Override
    public long newFreeTypeSymbol$default$2() {
        return Symbols$class.newFreeTypeSymbol$default$2(this);
    }

    @Override
    public boolean newStubSymbol$default$4() {
        return Symbols$class.newStubSymbol$default$4(this);
    }

    @Override
    public final int scala$reflect$internal$Names$$HASH_SIZE() {
        return 32768;
    }

    @Override
    public final int scala$reflect$internal$Names$$HASH_MASK() {
        return Short.MAX_VALUE;
    }

    @Override
    public final int scala$reflect$internal$Names$$NAME_SIZE() {
        return 131072;
    }

    @Override
    public final boolean nameDebug() {
        return false;
    }

    @Override
    public Object scala$reflect$internal$Names$$nameLock() {
        return this.scala$reflect$internal$Names$$nameLock;
    }

    @Override
    public char[] chrs() {
        return this.chrs;
    }

    @Override
    @TraitSetter
    public void chrs_$eq(char[] x$1) {
        this.chrs = x$1;
    }

    @Override
    public int scala$reflect$internal$Names$$nc() {
        return this.scala$reflect$internal$Names$$nc;
    }

    @Override
    @TraitSetter
    public void scala$reflect$internal$Names$$nc_$eq(int x$1) {
        this.scala$reflect$internal$Names$$nc = x$1;
    }

    @Override
    public Names.TermName[] scala$reflect$internal$Names$$termHashtable() {
        return this.scala$reflect$internal$Names$$termHashtable;
    }

    @Override
    public Names.TypeName[] scala$reflect$internal$Names$$typeHashtable() {
        return this.scala$reflect$internal$Names$$typeHashtable;
    }

    @Override
    public ClassTag<Names.Name> NameTag() {
        return this.NameTag;
    }

    @Override
    public ClassTag<Names.TermName> TermNameTag() {
        return this.TermNameTag;
    }

    private Names$TermName$ TermName$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TermName$module == null) {
                this.TermName$module = new Names$TermName$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TermName$module;
        }
    }

    @Override
    public Names$TermName$ TermName() {
        return this.TermName$module == null ? this.TermName$lzycompute() : this.TermName$module;
    }

    @Override
    public ClassTag<Names.TypeName> TypeNameTag() {
        return this.TypeNameTag;
    }

    private Names$TypeName$ TypeName$lzycompute() {
        SymbolTable symbolTable = this;
        synchronized (symbolTable) {
            if (this.TypeName$module == null) {
                this.TypeName$module = new Names$TypeName$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.TypeName$module;
        }
    }

    @Override
    public Names$TypeName$ TypeName() {
        return this.TypeName$module == null ? this.TypeName$lzycompute() : this.TypeName$module;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(Object x$1) {
        this.scala$reflect$internal$Names$$nameLock = x$1;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(Names.TermName[] x$1) {
        this.scala$reflect$internal$Names$$termHashtable = x$1;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(Names.TypeName[] x$1) {
        this.scala$reflect$internal$Names$$typeHashtable = x$1;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$NameTag_$eq(ClassTag x$1) {
        this.NameTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$TermNameTag_$eq(ClassTag x$1) {
        this.TermNameTag = x$1;
    }

    @Override
    public void scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(ClassTag x$1) {
        this.TypeNameTag = x$1;
    }

    @Override
    public boolean synchronizeNames() {
        return Names$class.synchronizeNames(this);
    }

    @Override
    public final Names.TermName newTermName(char[] cs, int offset, int len) {
        return Names$class.newTermName((Names)this, cs, offset, len);
    }

    @Override
    public final Names.TermName newTermName(char[] cs) {
        return Names$class.newTermName((Names)this, cs);
    }

    @Override
    public final Names.TypeName newTypeName(char[] cs) {
        return Names$class.newTypeName((Names)this, cs);
    }

    @Override
    public final Names.TermName newTermName(char[] cs, int offset, int len0, String cachedString) {
        return Names$class.newTermName(this, cs, offset, len0, cachedString);
    }

    @Override
    public final Names.TypeName newTypeName(char[] cs, int offset, int len, String cachedString) {
        return Names$class.newTypeName(this, cs, offset, len, cachedString);
    }

    @Override
    public Names.TermName newTermName(String s2) {
        return Names$class.newTermName((Names)this, s2);
    }

    @Override
    public Names.TypeName newTypeName(String s2) {
        return Names$class.newTypeName((Names)this, s2);
    }

    @Override
    public final Names.TermName newTermName(byte[] bs, int offset, int len) {
        return Names$class.newTermName((Names)this, bs, offset, len);
    }

    @Override
    public final Names.TermName newTermNameCached(String s2) {
        return Names$class.newTermNameCached(this, s2);
    }

    @Override
    public final Names.TypeName newTypeNameCached(String s2) {
        return Names$class.newTypeNameCached(this, s2);
    }

    @Override
    public final Names.TypeName newTypeName(char[] cs, int offset, int len) {
        return Names$class.newTypeName((Names)this, cs, offset, len);
    }

    @Override
    public final Names.TypeName newTypeName(byte[] bs, int offset, int len) {
        return Names$class.newTypeName((Names)this, bs, offset, len);
    }

    @Override
    public final Names.TypeName lookupTypeName(char[] cs) {
        return Names$class.lookupTypeName(this, cs);
    }

    @Override
    public Names.NameOps<Names.Name> AnyNameOps(Names.Name name) {
        return Names$class.AnyNameOps(this, name);
    }

    @Override
    public Names.NameOps<Names.TermName> TermNameOps(Names.TermName name) {
        return Names$class.TermNameOps(this, name);
    }

    @Override
    public Names.NameOps<Names.TypeName> TypeNameOps(Names.TypeName name) {
        return Names$class.TypeNameOps(this, name);
    }

    @Override
    public final <A, B, C> boolean corresponds3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.corresponds3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A> boolean mexists(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mexists(this, xss, p);
    }

    @Override
    public final <A> boolean mforall(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mforall(this, xss, p);
    }

    @Override
    public final <A, B> List<List<B>> mmap(List<List<A>> xss, Function1<A, B> f) {
        return Collections$class.mmap(this, xss, f);
    }

    @Override
    public final <A> Option<A> mfind(List<List<A>> xss, Function1<A, Object> p) {
        return Collections$class.mfind(this, xss, p);
    }

    @Override
    public final <A> void mforeach(List<List<A>> xss, Function1<A, BoxedUnit> f) {
        Collections$class.mforeach((Collections)this, xss, f);
    }

    @Override
    public final <A> void mforeach(Traversable<Traversable<A>> xss, Function1<A, BoxedUnit> f) {
        Collections$class.mforeach((Collections)this, xss, f);
    }

    @Override
    public final <A, B> List<B> mapList(List<A> as, Function1<A, B> f) {
        return Collections$class.mapList(this, as, f);
    }

    @Override
    public final <A, B> Option<B> collectFirst(List<A> as, PartialFunction<A, B> pf) {
        return Collections$class.collectFirst(this, as, pf);
    }

    @Override
    public final <A, B, C> List<C> map2(List<A> xs1, List<B> xs2, Function2<A, B, C> f) {
        return Collections$class.map2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B> List<A> map2Conserve(List<A> xs, List<B> ys, Function2<A, B, A> f) {
        return Collections$class.map2Conserve(this, xs, ys, f);
    }

    @Override
    public final <A, B, C, D> List<D> map3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, D> f) {
        return Collections$class.map3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B, C> List<C> flatMap2(List<A> xs1, List<B> xs2, Function2<A, B, List<C>> f) {
        return Collections$class.flatMap2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B> List<B> flatCollect(List<A> elems, PartialFunction<A, Traversable<B>> pf) {
        return Collections$class.flatCollect(this, elems, pf);
    }

    @Override
    public final <A, B> List<A> distinctBy(List<A> xs, Function1<A, B> f) {
        return Collections$class.distinctBy(this, xs, f);
    }

    @Override
    public final boolean flattensToEmpty(Seq<Seq<?>> xss) {
        return Collections$class.flattensToEmpty(this, xss);
    }

    @Override
    public final <A, B> void foreachWithIndex(List<A> xs, Function2<A, Object, BoxedUnit> f) {
        Collections$class.foreachWithIndex(this, xs, f);
    }

    @Override
    public final <A> A findOrElse(TraversableOnce<A> xs, Function1<A, Object> p, Function0<A> orElse) {
        return (A)Collections$class.findOrElse(this, xs, p, orElse);
    }

    @Override
    public final <A, A1, B> Map<A1, B> mapFrom(List<A> xs, Function1<A, B> f) {
        return Collections$class.mapFrom(this, xs, f);
    }

    @Override
    public final <A, A1, B> LinkedHashMap<A1, B> linkedMapFrom(List<A> xs, Function1<A, B> f) {
        return Collections$class.linkedMapFrom(this, xs, f);
    }

    @Override
    public final <A, B> List<B> mapWithIndex(List<A> xs, Function2<A, Object, B> f) {
        return Collections$class.mapWithIndex(this, xs, f);
    }

    @Override
    public final <A, B, C> Map<A, B> collectMap2(List<A> xs1, List<B> xs2, Function2<A, B, Object> p) {
        return Collections$class.collectMap2(this, xs1, xs2, p);
    }

    @Override
    public final <A, B> void foreach2(List<A> xs1, List<B> xs2, Function2<A, B, BoxedUnit> f) {
        Collections$class.foreach2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B, C> void foreach3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, BoxedUnit> f) {
        Collections$class.foreach3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B> boolean exists2(List<A> xs1, List<B> xs2, Function2<A, B, Object> f) {
        return Collections$class.exists2(this, xs1, xs2, f);
    }

    @Override
    public final <A, B, C> boolean exists3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.exists3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A, B, C> boolean forall3(List<A> xs1, List<B> xs2, List<C> xs3, Function3<A, B, C, Object> f) {
        return Collections$class.forall3(this, xs1, xs2, xs3, f);
    }

    @Override
    public final <A> Option<List<A>> sequence(List<Option<A>> as) {
        return Collections$class.sequence(this, as);
    }

    @Override
    public final <A> Option<List<List<A>>> transposeSafe(List<List<A>> ass) {
        return Collections$class.transposeSafe(this, ass);
    }

    public TreeGen gen() {
        return this.gen;
    }

    public abstract void log(Function0<Object> var1);

    public String elapsedMessage(String msg, long start) {
        return new StringBuilder().append((Object)msg).append((Object)" in ").append(BoxesRunTime.boxToLong(TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - start)).append((Object)"ms").toString();
    }

    public void informProgress(String msg) {
        MutableSettings.SettingValue settingValue = this.settings().verbose();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            this.inform(new StringBuilder().append((Object)"[").append((Object)msg).append((Object)"]").toString());
        }
    }

    public void informTime(String msg, long start) {
        this.informProgress(this.elapsedMessage(msg, start));
    }

    public boolean shouldLogAtThisPhase() {
        return false;
    }

    public boolean isPastTyper() {
        return false;
    }

    public boolean isDeveloper() {
        MutableSettings.SettingValue settingValue = this.settings().debug();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        return BoxesRunTime.unboxToBoolean(settingValue.value());
    }

    public void debugwarn(Function0<String> msg) {
        this.devWarning(msg);
    }

    public void debuglog(Function0<String> msg) {
        MutableSettings.SettingValue settingValue = this.settings().debug();
        MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
        if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
            this.log(msg);
        }
    }

    public void devWarning(Function0<String> msg) {
        if (this.isDeveloper()) {
            Console$.MODULE$.err().println(msg.apply());
        }
    }

    public String throwableAsString(Throwable t) {
        return String.valueOf(t);
    }

    public String throwableAsString(Throwable t, int maxFrames) {
        return Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])t.getStackTrace()).take(maxFrames)).mkString("\n  at ");
    }

    public final void devWarningDumpStack(Function0<String> msg, int maxFrames) {
        this.devWarning((Function0<String>)((Object)new Serializable(this, msg, maxFrames){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Function0 msg$2;
            private final int maxFrames$1;

            public final String apply() {
                return new StringBuilder().append((Object)((String)this.msg$2.apply())).append((Object)"\n").append((Object)this.$outer.throwableAsString(new Throwable(), this.maxFrames$1)).toString();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.msg$2 = msg$2;
                this.maxFrames$1 = maxFrames$1;
            }
        }));
    }

    public void debugStack(Throwable t) {
        this.devWarning((Function0<String>)((Object)new Serializable(this, t){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ SymbolTable $outer;
            private final Throwable t$1;

            public final String apply() {
                return this.$outer.throwableAsString(this.t$1);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.t$1 = t$1;
            }
        }));
    }

    public <T> T printCaller(String msg, T result2) {
        Predef$ predef$ = Predef$.MODULE$;
        Console$.MODULE$.err().println(new StringOps("%s: %s\nCalled from: %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{msg, result2, Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])new Throwable().getStackTrace()).drop(2)).take(50)).mkString("\n")})));
        return result2;
    }

    public <T> T printResult(String msg, T result2) {
        Console$.MODULE$.err().println(new StringBuilder().append((Object)msg).append((Object)": ").append(result2).toString());
        return result2;
    }

    public final <T> T logResult(Function0<String> msg, T result2) {
        this.log((Function0<Object>)((Object)new Serializable(this, msg, result2){
            public static final long serialVersionUID = 0L;
            private final Function0 msg$1;
            private final Object result$1;

            public final String apply() {
                return new StringBuilder().append((Object)((String)this.msg$1.apply())).append((Object)": ").append(this.result$1).toString();
            }
            {
                this.msg$1 = msg$1;
                this.result$1 = result$1;
            }
        }));
        return result2;
    }

    public final <T> T debuglogResult(Function0<String> msg, T result2) {
        this.debuglog((Function0<String>)((Object)new Serializable(this, msg, result2){
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
        return result2;
    }

    public final <T> T devWarningResult(Function0<String> msg, T result2) {
        this.devWarning((Function0<String>)((Object)new Serializable(this, msg, result2){
            public static final long serialVersionUID = 0L;
            private final Function0 msg$4;
            private final Object result$3;

            public final String apply() {
                return new StringBuilder().append((Object)((String)this.msg$4.apply())).append((Object)": ").append(this.result$3).toString();
            }
            {
                this.msg$4 = msg$4;
                this.result$3 = result$3;
            }
        }));
        return result2;
    }

    public final <T> T logResultIf(Function0<String> msg, Function1<T, Object> cond, T result2) {
        if (BoxesRunTime.unboxToBoolean(cond.apply(result2))) {
            this.log((Function0<Object>)((Object)new Serializable(this, msg, result2){
                public static final long serialVersionUID = 0L;
                private final Function0 msg$5;
                private final Object result$4;

                public final String apply() {
                    return new StringBuilder().append((Object)((String)this.msg$5.apply())).append((Object)": ").append(this.result$4).toString();
                }
                {
                    this.msg$5 = msg$5;
                    this.result$4 = result$4;
                }
            }));
        }
        return result2;
    }

    public final <T> T debuglogResultIf(Function0<String> msg, Function1<T, Object> cond, T result2) {
        if (BoxesRunTime.unboxToBoolean(cond.apply(result2))) {
            this.debuglog((Function0<String>)((Object)new Serializable(this, msg, result2){
                public static final long serialVersionUID = 0L;
                private final Function0 msg$6;
                private final Object result$5;

                public final String apply() {
                    return new StringBuilder().append((Object)((String)this.msg$6.apply())).append((Object)": ").append(this.result$5).toString();
                }
                {
                    this.msg$6 = msg$6;
                    this.result$5 = result$5;
                }
            }));
        }
        return result2;
    }

    public final Symbols.Symbol findSymbol(TraversableOnce<Symbols.Symbol> xs, Function1<Symbols.Symbol, Object> p) {
        return xs.find(p).getOrElse(new Serializable(this){
            public static final long serialVersionUID = 0L;
            public final /* synthetic */ SymbolTable $outer;

            public final Symbols.NoSymbol apply() {
                return this.$outer.NoSymbol();
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public <T extends Names.Name> Ordering<T> lowPriorityNameOrdering() {
        return this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering();
    }

    public SymbolTable$SimpleNameOrdering$ scala$reflect$internal$SymbolTable$$SimpleNameOrdering() {
        return this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module == null ? this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$lzycompute() : this.scala$reflect$internal$SymbolTable$$SimpleNameOrdering$module;
    }

    public final boolean traceSymbolActivity() {
        return this.traceSymbolActivity;
    }

    public SymbolTable$traceSymbols$ traceSymbols() {
        return this.traceSymbols$module == null ? this.traceSymbols$lzycompute() : this.traceSymbols$module;
    }

    public abstract TreeInfo treeInfo();

    public void assertCorrectThread() {
    }

    public Symbols.Symbol missingHook(Symbols.Symbol owner2, Names.Name name) {
        return this.NoSymbol();
    }

    public abstract Mirrors.RootsBase mirrorThatLoaded(Symbols.Symbol var1);

    public final int NoPeriod() {
        return 0;
    }

    public final int NoRunId() {
        return 0;
    }

    public List<Phase> phStack() {
        return this.phStack;
    }

    public void phStack_$eq(List<Phase> x$1) {
        this.phStack = x$1;
    }

    public final List<Phase> atPhaseStack() {
        return this.phStack();
    }

    public final Phase phase() {
        return this.ph;
    }

    public String atPhaseStackMessage() {
        List<Phase> list2 = this.atPhaseStack();
        String string2 = ((Object)Nil$.MODULE$).equals(list2) ? "" : ((TraversableOnce)list2.reverseMap(new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final String apply(Phase x$1) {
                return new StringBuilder().append((Object)"->").append(x$1).toString();
            }
        }, List$.MODULE$.canBuildFrom())).mkString("(", " ", ")");
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    public final void phase_$eq(Phase p) {
        if (p == null) ** GOTO lbl-1000
        v0 = p;
        var2_2 = NoPhase$.MODULE$;
        if (v0 == null || !v0.equals(var2_2)) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = false;
        }
        var5_3 = new Serializable(this, p){
            public static final long serialVersionUID = 0L;
            public final Phase p$1;

            public final Phase apply() {
                return this.p$1;
            }
            {
                this.p$1 = p$1;
            }
        };
        var4_4 = v1;
        var3_5 = Predef$.MODULE$;
        if (!var4_4) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(var5_3.p$1).toString());
        }
        this.ph = p;
        var7_6 = p.id();
        this.per = (this.currentRunId() << 8) + var7_6;
    }

    /*
     * WARNING - void declaration
     */
    public final Phase pushPhase(Phase ph) {
        void var2_2;
        Phase current = this.phase();
        this.phase_$eq(ph);
        this.phStack_$eq(this.phStack().$colon$colon(ph));
        return var2_2;
    }

    public final void popPhase(Phase ph) {
        this.phStack_$eq((List)this.phStack().tail());
        this.phase_$eq(ph);
    }

    public abstract int currentRunId();

    public final int runId(int period) {
        return period >> 8;
    }

    public final int phaseId(int period) {
        return period & 0xFF;
    }

    public final int currentPeriod() {
        return this.per;
    }

    public final Phase phaseOf(int period) {
        return this.phaseWithId()[this.phaseId(period)];
    }

    public final int period(int rid, int pid) {
        return (rid << 8) + pid;
    }

    public final boolean isAtPhaseAfter(Phase p) {
        Phase phase = p;
        NoPhase$ noPhase$ = NoPhase$.MODULE$;
        return (phase == null || !((Object)phase).equals(noPhase$)) && this.phase().id() > p.id();
    }

    /*
     * WARNING - void declaration
     */
    public final <T> T enteringPhase(Phase ph, Function0<T> op) {
        T t;
        Phase saved = this.pushPhase(ph);
        try {
            t = op.apply();
            this.popPhase(saved);
        }
        catch (Throwable throwable) {
            void var3_3;
            this.popPhase((Phase)var3_3);
            throw throwable;
        }
        return t;
    }

    public final Phase findPhaseWithName(String phaseName) {
        Phase ph = this.phase();
        while (true) {
            block4: {
                block3: {
                    Phase phase = ph;
                    NoPhase$ noPhase$ = NoPhase$.MODULE$;
                    if (phase != null && ((Object)phase).equals(noPhase$)) break block3;
                    String string2 = ph.name();
                    if (string2 != null ? !string2.equals(phaseName) : phaseName != null) break block4;
                }
                return ph == NoPhase$.MODULE$ ? this.phase() : ph;
            }
            ph = ph.prev();
        }
    }

    /*
     * WARNING - void declaration
     */
    public final <T> T enteringPhaseWithName(String phaseName, Function0<T> body2) {
        T t;
        Phase phase = this.findPhaseWithName(phaseName);
        SymbolTable symbolTable = this;
        Phase saved1 = this.pushPhase(phase);
        try {
            t = body2.apply();
            this.popPhase(saved1);
        }
        catch (Throwable throwable) {
            void var5_5;
            symbolTable.popPhase((Phase)var5_5);
            throw throwable;
        }
        return t;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public <T> T slowButSafeEnteringPhase(Phase ph, Function0<T> op) {
        T t;
        if (!this.isCompilerUniverse()) {
            t = op.apply();
            return t;
        }
        SymbolTable symbolTable = this;
        Phase saved1 = this.pushPhase(ph);
        try {
            t = op.apply();
            this.popPhase(saved1);
            return t;
        }
        catch (Throwable throwable) {
            void var4_4;
            symbolTable.popPhase((Phase)var4_4);
            throw throwable;
        }
    }

    public final <T> T exitingPhase(Phase ph, Function0<T> op) {
        return this.enteringPhase(ph.next(), op);
    }

    public final <T> T enteringPrevPhase(Function0<T> op) {
        return this.enteringPhase(this.phase().prev(), op);
    }

    public final <T> T enteringPhaseNotLaterThan(Phase target, Function0<T> op) {
        return this.isAtPhaseAfter(target) ? this.enteringPhase(target, op) : op.apply();
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public <T> T slowButSafeEnteringPhaseNotLaterThan(Phase target, Function0<T> op) {
        T t;
        if (!this.isCompilerUniverse()) {
            t = op.apply();
            return t;
        }
        if (!this.isAtPhaseAfter(target)) {
            t = op.apply();
            return t;
        }
        SymbolTable symbolTable = this;
        Phase saved1 = this.pushPhase(target);
        try {
            t = op.apply();
            this.popPhase(saved1);
            return t;
        }
        catch (Throwable throwable) {
            void var4_4;
            symbolTable.popPhase((Phase)var4_4);
            throw throwable;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isValid(int period) {
        if (period == 0) return false;
        if (this.runId(period) != this.currentRunId()) return false;
        int pid = this.phaseId(period);
        if (this.phase().id() > pid) {
            if (this.infoTransformers().nextFrom(pid).pid() < this.phase().id()) return false;
            return true;
        }
        if (this.infoTransformers().nextFrom(this.phase().id()).pid() < pid) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isValidForBaseClasses(int period) {
        if (period == 0) return false;
        if (this.runId(period) != this.currentRunId()) return false;
        int pid = this.phaseId(period);
        if (!(this.phase().id() > pid ? this.noChangeInBaseClasses$1(this.infoTransformers().nextFrom(pid), this.phase().id()) : this.noChangeInBaseClasses$1(this.infoTransformers().nextFrom(this.phase().id()), pid))) return false;
        return true;
    }

    public void openPackageModule(Symbols.Symbol container, Symbols.Symbol dest) {
        container.info().decls().iterator().foreach(new Serializable(this, dest){
            public static final long serialVersionUID = 0L;
            public final Symbols.Symbol dest$1;

            public final void apply(Symbols.Symbol member) {
                if (!member.isPrivate() && !member.isConstructor()) {
                    List list2 = this.dest$1.info().decl(member.name()).alternatives();
                    while (!((AbstractIterable)list2).isEmpty()) {
                        Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
                        this.dest$1.info().decls().unlink(symbol);
                        list2 = (List)list2.tail();
                    }
                }
            }
            {
                this.dest$1 = dest$1;
            }
        });
        container.info().decls().iterator().foreach(new Serializable(this, dest){
            public static final long serialVersionUID = 0L;
            private final Symbols.Symbol dest$1;

            public final Object apply(Symbols.Symbol member) {
                return member.isPrivate() || member.isConstructor() ? BoxedUnit.UNIT : this.dest$1.info().decls().enter(member);
            }
            {
                this.dest$1 = dest$1;
            }
        });
        List list2 = container.parentSymbols();
        while (!((AbstractIterable)list2).isEmpty()) {
            Symbols.Symbol symbol = (Symbols.Symbol)((AbstractIterable)list2).head();
            Symbols.ClassSymbol classSymbol = this.definitions().ObjectClass();
            if (symbol == null ? classSymbol != null : !symbol.equals(classSymbol)) {
                this.openPackageModule(symbol, dest);
            }
            list2 = (List)list2.tail();
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public Types.Type arrayToRepeated(Types.Type tp) {
        block8: {
            block7: {
                block6: {
                    if (!(tp instanceof Types.MethodType)) break block6;
                    var16_2 = (Types.MethodType)tp;
                    formals = tp.paramTypes();
                    v0 = formals.last().typeSymbol();
                    var2_4 = this.definitions().ArrayClass();
                    var5_5 = new Serializable(this, formals){
                        public static final long serialVersionUID = 0L;
                        public final List formals$1;

                        public final List<Types.Type> apply() {
                            return this.formals$1;
                        }
                        {
                            this.formals$1 = formals$1;
                        }
                    };
                    var4_6 = !(v0 != null ? v0.equals(var2_4) == false : var2_4 != null);
                    var3_7 = Predef$.MODULE$;
                    if (!var4_6) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(var5_5.formals$1).toString());
                    }
                    method = var16_2.params().last().owner();
                    var11_9 = formals.last().typeArgs().head();
                    if (!(var11_9 instanceof Types.RefinedType) || (var7_11 = List$.MODULE$.unapplySeq((var6_10 = (Types.RefinedType)var11_9).parents())).isEmpty() || var7_11.get() == null || ((LinearSeqOptimized)var7_11.get()).lengthCompare(2) != 0) ** GOTO lbl-1000
                    t1 = (Types.Type)((LinearSeqOptimized)var7_11.get()).apply(0);
                    t2 = (Types.Type)((LinearSeqOptimized)var7_11.get()).apply(1);
                    if (!t1.typeSymbol().isAbstractType()) ** GOTO lbl-1000
                    v1 = t2.typeSymbol();
                    var9_14 = this.definitions().ObjectClass();
                    if (!(v1 != null ? v1.equals(var9_14) == false : var9_14 != null)) {
                        var14_15 = t1;
                    } else lbl-1000:
                    // 3 sources

                    {
                        var14_15 = var11_9;
                    }
                    newParams = method.newSyntheticValueParams(((SeqLike)formals.init()).$colon$plus(this.definitions().javaRepeatedType(var14_15), List$.MODULE$.canBuildFrom()));
                    var18_17 /* !! */  = new Types.MethodType(this, newParams, var16_2.resultType());
                    break block7;
                }
                if (!(tp instanceof Types.PolyType)) break block8;
                var17_18 = (Types.PolyType)tp;
                var18_17 /* !! */  = new Types.PolyType(this, var17_18.typeParams(), this.arrayToRepeated(var17_18.resultType()));
            }
            return var18_17 /* !! */ ;
        }
        throw new MatchError(tp);
    }

    public void openPackageModule(Symbols.Symbol pkgClass) {
        Symbols.Symbol pkgModule = pkgClass.info().decl(this.nme().PACKAGEkw());
        if (pkgModule.isModule() && !this.fromSource$1(pkgModule)) {
            this.openPackageModule(pkgModule, pkgClass);
        }
    }

    public SymbolTable$perRunCaches$ perRunCaches() {
        return this.perRunCaches$module == null ? this.perRunCaches$lzycompute() : this.perRunCaches$module;
    }

    public InfoTransformers.InfoTransformer infoTransformers() {
        return this.infoTransformers;
    }

    public void infoTransformers_$eq(InfoTransformers.InfoTransformer x$1) {
        this.infoTransformers = x$1;
    }

    public abstract Phase[] phaseWithId();

    public boolean isCompilerUniverse() {
        return false;
    }

    public final <T> T atPhase(Phase ph, Function0<T> op) {
        return this.enteringPhase(ph, op);
    }

    public Function1<StringContext, package.StringContextStripMarginOps> StringContextStripMarginOps() {
        return this.StringContextStripMarginOps;
    }

    private final boolean noChangeInBaseClasses$1(InfoTransformers.InfoTransformer it, int limit) {
        while (true) {
            block5: {
                boolean bl;
                block4: {
                    block3: {
                        if (it.pid() < limit) break block3;
                        bl = true;
                        break block4;
                    }
                    if (!it.changesBaseClasses()) break block5;
                    bl = false;
                }
                return bl;
            }
            it = it.next();
        }
    }

    private final boolean fromSource$1(Symbols.Symbol pkgModule$1) {
        boolean bl;
        Types.Type type = pkgModule$1.rawInfo();
        if (type instanceof SymLoader) {
            SymLoader symLoader = (SymLoader)type;
            bl = symLoader.fromSource();
        } else {
            bl = false;
        }
        return bl;
    }

    public SymbolTable() {
        Collections$class.$init$(this);
        Names$class.$init$(this);
        Symbols$class.$init$(this);
        TypeComparers$class.$init$(this);
        TypeToStrings$class.$init$(this);
        CommonOwners$class.$init$(this);
        GlbLubs$class.$init$(this);
        TypeMaps$class.$init$(this);
        TypeConstraints$class.$init$(this);
        FindMembers$class.$init$(this);
        Types$class.$init$(this);
        Variances$class.$init$(this);
        Kinds$class.$init$(this);
        ExistentialsAndSkolems$class.$init$(this);
        FlagSets$class.$init$(this);
        Scopes$class.$init$(this);
        Mirrors$class.$init$(this);
        Definitions$class.$init$(this);
        Constants$class.$init$(this);
        BaseTypeSeqs$class.$init$(this);
        InfoTransformers$class.$init$(this);
        Transforms$class.$init$(this);
        StdNames$class.$init$(this);
        AnnotationInfos$class.$init$(this);
        AnnotationCheckers$class.$init$(this);
        Trees$class.$init$(this);
        Printers$class.$init$(this);
        Positions$class.$init$(this);
        TypeDebugging$class.$init$(this);
        Importers$class.$init$(this);
        Required$class.$init$(this);
        CapturedVariables$class.$init$(this);
        StdAttachments$class.$init$(this);
        StdCreators$class.$init$(this);
        ReificationSupport$class.$init$(this);
        PrivateWithin$class.$init$(this);
        Translations$class.$init$(this);
        FreshNames$class.$init$(this);
        Internals$class.$init$(this);
        Reporting$class.$init$(this);
        this.gen = new TreeGen(this){
            private final SymbolTable global;

            public SymbolTable global() {
                return this.global;
            }
            {
                this.global = $outer;
            }
        };
        this.traceSymbolActivity = scala.sys.package$.MODULE$.props().contains("scalac.debug.syms");
        this.phStack = Nil$.MODULE$;
        this.ph = NoPhase$.MODULE$;
        this.per = 0;
        this.infoTransformers = new InfoTransformers.InfoTransformer(this){
            private final int pid;
            private final boolean changesBaseClasses;

            public int pid() {
                return this.pid;
            }

            public boolean changesBaseClasses() {
                return this.changesBaseClasses;
            }

            public Types.Type transform(Symbols.Symbol sym, Types.Type tpe) {
                return tpe;
            }
            {
                this.pid = NoPhase$.MODULE$.id();
                this.changesBaseClasses = true;
            }
        };
        this.StringContextStripMarginOps = new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final package.StringContextStripMarginOps apply(StringContext stringContext) {
                return package$.MODULE$.StringContextStripMarginOps(stringContext);
            }
        };
    }

    public abstract class SymLoader
    extends Types.LazyType {
        public boolean fromSource() {
            return false;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$SymbolTable$SymLoader$$$outer() {
            return (SymbolTable)this.$outer;
        }

        public SymLoader(SymbolTable $outer) {
            super($outer);
        }
    }
}

