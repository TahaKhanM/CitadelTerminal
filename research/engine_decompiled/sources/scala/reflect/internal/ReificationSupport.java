/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.ScalaReflectionException;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.AbstractIterable;
import scala.collection.Iterable;
import scala.collection.LinearSeqOptimized;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableLike;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Internals;
import scala.reflect.api.Internals$ReificationSupportApi$class;
import scala.reflect.api.Liftables;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$$anon$2$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$FilterCall$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$FlagsRepr$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$FunctionClassRef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$ImplicitParams$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$MaybeSelectApply$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$MaybeTypeTreeOriginal$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$MaybeTyped$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$MaybeUnchecked$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$ScalaDot$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticAnnotatedType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticApplied$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticAppliedType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticAssign$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticBlock$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticClassDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticCompoundType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticDefDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticEmptyTypeTree$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticExistentialType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticFilter$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticFor$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticForYield$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticFunction$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticFunctionType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticImport$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticMatch$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticNew$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticObjectDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticPackageObjectDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticPartialFunction$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticPatDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticSelectTerm$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticSelectType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticSingletonType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTermIdent$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTraitDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTry$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTuple$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTupleType$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTypeApplied$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTypeIdent$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticTypeProjection$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticValDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticValEq$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticValFrom$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntacticVarDef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$SyntheticUnit$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$TupleClassRef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$TupleCompanionRef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnCheckIfRefutable$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnClosure$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnCtor$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnFilter$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnFlatMap$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnFor$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnForeach$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnMap$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnMkTemplate$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnPatSeq$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnPatSeqWithRes$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnSyntheticParam$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnVisitor$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$UnitClassRef$;
import scala.reflect.internal.ReificationSupport$ReificationSupportImpl$implodePatDefs$;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments$ForAttachment$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001=ma!C\u0001\u0003!\u0003\r\t!CH\n\u0005I\u0011V-\u001b4jG\u0006$\u0018n\u001c8TkB\u0004xN\u001d;\u000b\u0005\r!\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0007\u0013\tiaA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006\u001f\u0001!\t\u0001E\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0001\"a\u0003\n\n\u0005M1!\u0001B+oSR4A!\u0006\u0001\u0001-\t1\"+Z5gS\u000e\fG/[8o'V\u0004\bo\u001c:u\u00136\u0004HnE\u0002\u0015\u0015]\u0001\"\u0001G\r\u000e\u0003\u0001I!AG\u000e\u0003+I+\u0017NZ5dCRLwN\\*vaB|'\u000f^!qS&\u0011A$\b\u0002\n\u0013:$XM\u001d8bYNT!A\b\u0003\u0002\u0007\u0005\u0004\u0018\u000eC\u0003!)\u0011\u0005\u0011%\u0001\u0004=S:LGO\u0010\u000b\u0002EA\u0011\u0001\u0004\u0006\u0005\u0006IQ!\t!J\u0001\u000bg\u0016dWm\u0019;UsB,Gc\u0001\u0014,aA\u0011\u0001dJ\u0005\u0003Q%\u0012!\u0002V=qKNKXNY8m\u0013\tQ#AA\u0004Ts6\u0014w\u000e\\:\t\u000b1\u001a\u0003\u0019A\u0017\u0002\u000b=<h.\u001a:\u0011\u0005aq\u0013BA\u0018*\u0005\u0019\u0019\u00160\u001c2pY\")\u0011g\ta\u0001e\u0005!a.Y7f!\t\u0019dG\u0004\u0002\fi%\u0011QGB\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u00026\r!)!\b\u0006C\u0001w\u0005Q1/\u001a7fGR$VM]7\u0015\u0007qz\u0004\t\u0005\u0002\u0019{%\u0011a(\u000b\u0002\u000b)\u0016\u0014XnU=nE>d\u0007\"\u0002\u0017:\u0001\u0004i\u0003\"B\u0019:\u0001\u0004\u0011\u0004\"\u0002\"\u0015\t#\u0019\u0015AB:fY\u0016\u001cG\u000fF\u0002.\t\u0016CQ\u0001L!A\u00025BQ!M!A\u0002\u0019\u0003\"\u0001G$\n\u0005!K%\u0001\u0002(b[\u0016L!A\u0013\u0002\u0003\u000b9\u000bW.Z:\t\u000b1#B\u0011A'\u0002-M,G.Z2u\u001fZ,'\u000f\\8bI\u0016$W*\u001a;i_\u0012$BAT)S'B\u0011\u0001dT\u0005\u0003!&\u0012A\"T3uQ>$7+_7c_2DQ\u0001L&A\u00025BQ!M&A\u0002IBQ\u0001V&A\u0002U\u000bQ!\u001b8eKb\u0004\"a\u0003,\n\u0005]3!aA%oi\")\u0011\f\u0006C\u00015\u0006Ya.Z<Ge\u0016,G+\u001a:n)\u0015YflX4m!\tAB,\u0003\u0002^S\tqaI]3f)\u0016\u0014XnU=nE>d\u0007\"B\u0019Y\u0001\u0004\u0011\u0004B\u00021Y\t\u0003\u0007\u0011-A\u0003wC2,X\rE\u0002\fE\u0012L!a\u0019\u0004\u0003\u0011q\u0012\u0017P\\1nKz\u0002\"aC3\n\u0005\u00194!aA!os\"9\u0001\u000e\u0017I\u0001\u0002\u0004I\u0017!\u00024mC\u001e\u001c\bCA\u0006k\u0013\tYgA\u0001\u0003M_:<\u0007bB7Y!\u0003\u0005\rAM\u0001\u0007_JLw-\u001b8\t\u000b=$B\u0011\u00019\u0002\u00179,wO\u0012:fKRK\b/\u001a\u000b\u0005cR,h\u000f\u0005\u0002\u0019e&\u00111/\u000b\u0002\u000f\rJ,W\rV=qKNKXNY8m\u0011\u0015\td\u000e1\u00013\u0011\u001dAg\u000e%AA\u0002%Dq!\u001c8\u0011\u0002\u0003\u0007!\u0007C\u0003y)\u0011\u0005\u00110A\boK^tUm\u001d;fINKXNY8m)!i#p\u001f?\u0002\b\u0005%\u0001\"\u0002\u0017x\u0001\u0004i\u0003\"B\u0019x\u0001\u00041\u0005\"B?x\u0001\u0004q\u0018a\u00019pgB\u0011\u0001d`\u0005\u0005\u0003\u0003\t\u0019A\u0001\u0005Q_NLG/[8o\u0013\r\t)A\u0001\u0002\n!>\u001c\u0018\u000e^5p]NDQ\u0001[<A\u0002%Dq!a\u0003x\u0001\u0004\ti!A\u0004jg\u000ec\u0017m]:\u0011\u0007-\ty!C\u0002\u0002\u0012\u0019\u0011qAQ8pY\u0016\fg\u000eC\u0004\u0002\u0016Q!\t!a\u0006\u0002\u00199,woU2pa\u0016<\u0016\u000e\u001e5\u0015\t\u0005e\u00111\u0005\t\u00041\u0005m\u0011\u0002BA\u000f\u0003?\u0011QaU2pa\u0016L1!!\t\u0003\u0005\u0019\u00196m\u001c9fg\"A\u0011QEA\n\u0001\u0004\t9#A\u0003fY\u0016l7\u000f\u0005\u0003\f\u0003Si\u0013bAA\u0016\r\tQAH]3qK\u0006$X\r\u001a \t\u000f\u0005=B\u0003\"\u0001\u00022\u0005q1/\u001a;B]:|G/\u0019;j_:\u001cX\u0003BA\u001a\u0003s!b!!\u000e\u0002F\u0005%\u0003\u0003BA\u001c\u0003sa\u0001\u0001\u0002\u0005\u0002<\u00055\"\u0019AA\u001f\u0005\u0005\u0019\u0016cAA [A\u00191\"!\u0011\n\u0007\u0005\rcAA\u0004O_RD\u0017N\\4\t\u0011\u0005\u001d\u0013Q\u0006a\u0001\u0003k\t1a]=n\u0011!\tY%!\fA\u0002\u00055\u0013AB1o]>$8\u000f\u0005\u0004\u0002P\u0005U\u00131\f\b\u0004\u0017\u0005E\u0013bAA*\r\u00059\u0001/Y2lC\u001e,\u0017\u0002BA,\u00033\u0012A\u0001T5ti*\u0019\u00111\u000b\u0004\u0011\u0007a\ti&\u0003\u0003\u0002`\u0005\u0005$AD!o]>$\u0018\r^5p]&sgm\\\u0005\u0004\u0003G\u0012!aD!o]>$\u0018\r^5p]&sgm\\:\t\u000f\u0005\u001dD\u0003\"\u0001\u0002j\u000591/\u001a;J]\u001a|W\u0003BA6\u0003_\"b!!\u001c\u0002r\u0005M\u0004\u0003BA\u001c\u0003_\"\u0001\"a\u000f\u0002f\t\u0007\u0011Q\b\u0005\t\u0003\u000f\n)\u00071\u0001\u0002n!A\u0011QOA3\u0001\u0004\t9(A\u0002ua\u0016\u00042\u0001GA=\u0013\u0011\tY(! \u0003\tQK\b/Z\u0005\u0004\u0003\u007f\u0012!!\u0002+za\u0016\u001c\bbBAB)\u0011\u0005\u0011QQ\u0001\u0007[.$\u0006.[:\u0015\t\u0005\u001d\u0015\u0011\u0013\t\u00041\u0005%\u0015\u0002BAF\u0003\u001b\u0013A\u0001\u0016:fK&\u0019\u0011q\u0012\u0002\u0003\u000bQ\u0013X-Z:\t\u000f\u0005\u001d\u0013\u0011\u0011a\u0001[!9\u0011Q\u0013\u000b\u0005\u0002\u0005]\u0015\u0001C7l'\u0016dWm\u0019;\u0015\r\u0005e\u0015qTAR!\rA\u00121T\u0005\u0005\u0003;\u000biI\u0001\u0004TK2,7\r\u001e\u0005\t\u0003C\u000b\u0019\n1\u0001\u0002\b\u0006I\u0011/^1mS\u001aLWM\u001d\u0005\b\u0003\u000f\n\u0019\n1\u0001.\u0011\u001d\t9\u000b\u0006C\u0001\u0003S\u000bq!\\6JI\u0016tG\u000f\u0006\u0003\u0002,\u0006E\u0006c\u0001\r\u0002.&!\u0011qVAG\u0005\u0015IE-\u001a8u\u0011\u001d\t9%!*A\u00025Bq!!.\u0015\t\u0003\t9,\u0001\u0006nWRK\b/\u001a+sK\u0016$B!!/\u0002@B\u0019\u0001$a/\n\t\u0005u\u0016Q\u0012\u0002\t)f\u0004X\r\u0016:fK\"A\u0011\u0011YAZ\u0001\u0004\t9(\u0001\u0002ua\"9\u0011Q\u0019\u000b\u0005\u0002\u0005\u001d\u0017\u0001\u0003+iSN$\u0016\u0010]3\u0015\t\u0005]\u0014\u0011\u001a\u0005\b\u0003\u000f\n\u0019\r1\u0001.\u0011\u001d\ti\r\u0006C\u0001\u0003\u001f\f!bU5oO2,G+\u001f9f)\u0019\t9(!5\u0002V\"A\u00111[Af\u0001\u0004\t9(A\u0002qe\u0016Dq!a\u0012\u0002L\u0002\u0007Q\u0006C\u0004\u0002ZR!\t!a7\u0002\u0013M+\b/\u001a:UsB,GCBA<\u0003;\f\t\u000f\u0003\u0005\u0002`\u0006]\u0007\u0019AA<\u0003\u001d!\b.[:ua\u0016D\u0001\"a9\u0002X\u0002\u0007\u0011qO\u0001\tgV\u0004XM\u001d;qK\"9\u0011q\u001d\u000b\u0005\u0002\u0005%\u0018\u0001D\"p]N$\u0018M\u001c;UsB,G\u0003BAv\u0003c\u00042\u0001GAw\u0013\u0011\ty/! \u0003\u0019\r{gn\u001d;b]R$\u0016\u0010]3\t\u000f\u0001\f)\u000f1\u0001\u0002tB\u0019\u0001$!>\n\t\u0005]\u0018\u0011 \u0002\t\u0007>t7\u000f^1oi&\u0019\u00111 \u0002\u0003\u0013\r{gn\u001d;b]R\u001c\bbBA\u0000)\u0011\u0005!\u0011A\u0001\b)f\u0004XMU3g)!\t9Ha\u0001\u0003\u0006\t\u001d\u0001\u0002CAj\u0003{\u0004\r!a\u001e\t\u000f\u0005\u001d\u0013Q a\u0001[!A!\u0011BA\u007f\u0001\u0004\u0011Y!\u0001\u0003be\u001e\u001c\bCBA(\u0003+\n9\bC\u0004\u0003\u0010Q!\tA!\u0005\u0002\u0017I+g-\u001b8fIRK\b/\u001a\u000b\t\u0005'\u0011IB!\b\u0003\"A\u0019\u0001D!\u0006\n\t\t]\u0011Q\u0010\u0002\f%\u00164\u0017N\\3e)f\u0004X\r\u0003\u0005\u0003\u001c\t5\u0001\u0019\u0001B\u0006\u0003\u001d\u0001\u0018M]3oiND\u0001Ba\b\u0003\u000e\u0001\u0007\u0011\u0011D\u0001\u0006I\u0016\u001cGn\u001d\u0005\b\u0005G\u0011i\u00011\u0001.\u0003)!\u0018\u0010]3Ts6\u0014w\u000e\u001c\u0005\b\u0005O!B\u0011\u0001B\u0015\u00035\u0019E.Y:t\u0013:4w\u000eV=qKRA!1\u0006B\u0019\u0005g\u0011)\u0004E\u0002\u0019\u0005[IAAa\f\u0002~\ti1\t\\1tg&sgm\u001c+za\u0016D\u0001Ba\u0007\u0003&\u0001\u0007!1\u0002\u0005\t\u0005?\u0011)\u00031\u0001\u0002\u001a!9!1\u0005B\u0013\u0001\u0004i\u0003b\u0002B\u001d)\u0011\u0005!1H\u0001\u000b\u001b\u0016$\bn\u001c3UsB,GC\u0002B\u001f\u0005\u0007\u0012I\u0005E\u0002\u0019\u0005\u007fIAA!\u0011\u0002~\tQQ*\u001a;i_\u0012$\u0016\u0010]3\t\u0011\t\u0015#q\u0007a\u0001\u0005\u000f\na\u0001]1sC6\u001c\b#BA(\u0003+j\u0003\u0002\u0003B&\u0005o\u0001\r!a\u001e\u0002\u0015I,7/\u001e7u)f\u0004X\rC\u0004\u0003PQ!\tA!\u0015\u0002#9+H\u000e\\1ss6+G\u000f[8e)f\u0004X\r\u0006\u0003\u0003T\te\u0003c\u0001\r\u0003V%!!qKA?\u0005EqU\u000f\u001c7beflU\r\u001e5pIRK\b/\u001a\u0005\t\u0005\u0017\u0012i\u00051\u0001\u0002x!9!Q\f\u000b\u0005\u0002\t}\u0013\u0001\u0003)pYf$\u0016\u0010]3\u0015\r\t\u0005$q\rB6!\rA\"1M\u0005\u0005\u0005K\niH\u0001\u0005Q_2LH+\u001f9f\u0011!\u0011IGa\u0017A\u0002\t\u001d\u0013A\u0003;za\u0016\u0004\u0016M]1ng\"A!1\nB.\u0001\u0004\t9\bC\u0004\u0003pQ!\tA!\u001d\u0002\u001f\u0015C\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016$bAa\u001d\u0003z\tu\u0004c\u0001\r\u0003v%!!qOA?\u0005=)\u00050[:uK:$\u0018.\u00197UsB,\u0007\u0002\u0003B>\u0005[\u0002\rAa\u0012\u0002\u0015E,\u0018M\u001c;jM&,G\r\u0003\u0005\u0003\u0000\t5\u0004\u0019AA<\u0003))h\u000eZ3sYfLgn\u001a\u0005\b\u0005\u0007#B\u0011\u0001BC\u00035\teN\\8uCR,G\rV=qKR1!q\u0011BG\u00053\u00032\u0001\u0007BE\u0013\u0011\u0011Y)! \u0003\u001b\u0005sgn\u001c;bi\u0016$G+\u001f9f\u0011!\u0011yI!!A\u0002\tE\u0015aC1o]>$\u0018\r^5p]N\u0004b!a\u0014\u0002V\tM\u0005c\u0001\r\u0003\u0016&!!qSA1\u0005)\teN\\8uCRLwN\u001c\u0005\t\u0005\u007f\u0012\t\t1\u0001\u0002x!9!Q\u0014\u000b\u0005\u0002\t}\u0015A\u0003+za\u0016\u0014u.\u001e8egR1!\u0011\u0015BT\u0005W\u00032\u0001\u0007BR\u0013\u0011\u0011)+! \u0003\u0015QK\b/\u001a\"pk:$7\u000f\u0003\u0005\u0003*\nm\u0005\u0019AA<\u0003\taw\u000e\u0003\u0005\u0003.\nm\u0005\u0019AA<\u0003\tA\u0017\u000eC\u0004\u00032R!\tAa-\u0002'\t{WO\u001c3fI^KG\u000eZ2be\u0012$\u0016\u0010]3\u0015\t\tU&1\u0018\t\u00041\t]\u0016\u0002\u0002B]\u0003{\u00121CQ8v]\u0012,GmV5mI\u000e\f'\u000f\u001a+za\u0016D\u0001B!0\u00030\u0002\u0007!\u0011U\u0001\u0007E>,h\u000eZ:\t\u000f\t\u0005G\u0003\"\u0001\u0003D\u0006QA\u000f[5t!J,g-\u001b=\u0015\t\u0005]$Q\u0019\u0005\b\u0003\u000f\u0012y\f1\u0001.\u0011\u001d\u0011I\r\u0006C\u0001\u0005\u0017\fqa]3u)f\u0004X-\u0006\u0003\u0003N\nEGC\u0002Bh\u0005/\u0014Y\u000e\u0005\u0003\u00028\tEG\u0001\u0003Bj\u0005\u000f\u0014\rA!6\u0003\u0003Q\u000bB!a\u0010\u0002\b\"A!\u0011\u001cBd\u0001\u0004\u0011y-\u0001\u0003ue\u0016,\u0007\u0002CA;\u0005\u000f\u0004\r!a\u001e\t\u000f\t}G\u0003\"\u0001\u0003b\u0006I1/\u001a;Ts6\u0014w\u000e\\\u000b\u0005\u0005G\u00149\u000f\u0006\u0004\u0003f\n%(1\u001e\t\u0005\u0003o\u00119\u000f\u0002\u0005\u0003T\nu'\u0019\u0001Bk\u0011!\u0011IN!8A\u0002\t\u0015\bbBA$\u0005;\u0004\r!\f\u0005\b\u0005_$B\u0011\u0001By\u0003\u001d!xn\u0015;biN$BAa=\u0003vB1\u0011qJA+\u0003\u000fC\u0001B!7\u0003n\u0002\u0007\u0011q\u0011\u0005\b\u0005s$B\u0011\u0001B~\u00031i7.\u00118o_R\fG/[8o)\u0011\t9I!@\t\u0011\te'q\u001fa\u0001\u0003\u000fCqA!?\u0015\t\u0003\u0019\t\u0001\u0006\u0003\u0003t\u000e\r\u0001\u0002CB\u0003\u0005\u007f\u0004\rAa=\u0002\u000bQ\u0014X-Z:\t\u000f\r%A\u0003\"\u0001\u0004\f\u00059Qn\u001b)be\u0006lG\u0003CB\u0007\u0007/\u0019iba\u000b\u0011\r\u0005=\u0013QKB\b!\u0019\ty%!\u0016\u0004\u0012A\u0019\u0001da\u0005\n\t\rU\u0011Q\u0012\u0002\u0007-\u0006dG)\u001a4\t\u0011\re1q\u0001a\u0001\u00077\tQ!\u0019:hgN\u0004b!a\u0014\u0002V\tM\bBCB\u0010\u0007\u000f\u0001\n\u00111\u0001\u0004\"\u0005QQ\r\u001f;sC\u001ac\u0017mZ:\u0011\u0007a\u0019\u0019#\u0003\u0003\u0004&\r\u001d\"a\u0002$mC\u001e\u001cV\r^\u0005\u0004\u0007S\u0011!\u0001\u0003$mC\u001e\u001cV\r^:\t\u0015\r52q\u0001I\u0001\u0002\u0004\u0019\t#\u0001\u0007fq\u000edW\u000fZ3GY\u0006<7\u000fC\u0004\u0004\nQ!\ta!\r\u0015\u0011\rE11GB\u001b\u0007oA\u0001B!7\u00040\u0001\u0007\u0011q\u0011\u0005\t\u0007?\u0019y\u00031\u0001\u0004\"!A1QFB\u0018\u0001\u0004\u0019\t\u0003C\u0004\u0004<Q!\ta!\u0010\u0002\u001f5\\\u0017*\u001c9mS\u000eLG\u000fU1sC6$Baa\u0004\u0004@!A!\u0011BB\u001d\u0001\u0004\u0011\u0019\u0010C\u0004\u0004<Q!\taa\u0011\u0015\t\rE1Q\t\u0005\t\u00053\u001c\t\u00051\u0001\u0002\b\"91\u0011\n\u000b\u0005\u0002\r-\u0013!C7l)B\f'/Y7t)\u0011\u0019ie!\u0016\u0011\r\u0005=\u0013QKB(!\rA2\u0011K\u0005\u0005\u0007'\niIA\u0004UsB,G)\u001a4\t\u0011\r]3q\ta\u0001\u0005g\fq\u0001\u001e9be\u0006l7\u000fC\u0004\u0004\\Q!\ta!\u0018\u0002\u00195\\'+\u001a4j]\u0016\u001cF/\u0019;\u0015\t\u0005\u001d5q\f\u0005\t\u0007C\u001aI\u00061\u0001\u0002\b\u0006!1\u000f^1u\u0011\u001d\u0019Y\u0006\u0006C\u0001\u0007K\"BAa=\u0004h!A1\u0011NB2\u0001\u0004\u0011\u00190A\u0003ti\u0006$8\u000fC\u0004\u0004nQ!\taa\u001c\u0002\u001b5\\\u0007+Y2lC\u001e,7\u000b^1u)\u0011\t9i!\u001d\t\u0011\r\u000541\u000ea\u0001\u0003\u000fCqa!\u001c\u0015\t\u0003\u0019)\b\u0006\u0003\u0003t\u000e]\u0004\u0002CB5\u0007g\u0002\rAa=\b\u000f\rmD\u0003#\u0001\u0004~\u0005A1kY1mC\u0012{G\u000f\u0005\u0003\u0004\u0000\r\u0005U\"\u0001\u000b\u0007\u000f\r\rE\u0003#\u0001\u0004\u0006\nA1kY1mC\u0012{GoE\u0003\u0004\u0002*\u00199\t\u0005\u0003\u0004\u0000\r%\u0015bABF3\t\t2kY1mC\u0012{G/\u0012=ue\u0006\u001cGo\u001c:\t\u000f\u0001\u001a\t\t\"\u0001\u0004\u0010R\u00111Q\u0010\u0005\t\u0007'\u001b\t\t\"\u0001\u0004\u0016\u0006)\u0011\r\u001d9msR!\u0011qQBL\u0011\u0019\t4\u0011\u0013a\u0001\r\"A11TBA\t\u0003\u0019i*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\r}5Q\u0015\t\u0005\u0017\r\u0005f)C\u0002\u0004$\u001a\u0011aa\u00149uS>t\u0007\u0002\u0003Bm\u00073\u0003\r!a\"\t\u000f\r%F\u0003\"\u0001\u0004,\u0006QQn[#be2LH)\u001a4\u0015\t\u0005\u001d5Q\u0016\u0005\t\u0007_\u001b9\u000b1\u0001\u0002\b\u0006!A-\u001a4o\u0011\u001d\u0019I\u000b\u0006C\u0001\u0007g#BAa=\u00046\"A1qWBY\u0001\u0004\u0011\u00190A\u0003eK\u001at7\u000fC\u0004\u0004<R!\ta!0\u0002\u00135\\'+\u001a4Ue\u0016,GCBB`\u0007\u000b\u001cI\rE\u0002\u0019\u0007\u0003LAaa1\u0002\u000e\n9!+\u001a4Ue\u0016,\u0007\u0002CBd\u0007s\u0003\r!a\"\u0002\tE,\u0018\r\u001c\u0005\b\u0003\u000f\u001aI\f1\u0001.\u0011\u001d\u0019i\r\u0006C\u0001\u0007\u001f\fQB\u001a:fg\"$VM]7OC6,G\u0003BBi\u0007/\u00042\u0001GBj\u0013\r\u0019).\u0013\u0002\t)\u0016\u0014XNT1nK\"I1\u0011\\Bf!\u0003\u0005\rAM\u0001\u0007aJ,g-\u001b=\t\u000f\ruG\u0003\"\u0001\u0004`\u0006iaM]3tQRK\b/\u001a(b[\u0016$Ba!9\u0004hB\u0019\u0001da9\n\u0007\r\u0015\u0018J\u0001\u0005UsB,g*Y7f\u0011\u001d\u0019Ina7A\u0002IBqaa;\u0015\t'\u0019i/A\u0003ge\u0016\u001c\b.\u0006\u0002\u0004pB!1\u0011_B|\u001b\t\u0019\u0019PC\u0002\u0004v\n\tA!\u001e;jY&!1\u0011`Bz\u0005A1%/Z:i\u001d\u0006lWm\u0011:fCR|'oB\u0004\u0004~RA\taa@\u0002\u001d%k\u0007\u000f\\5dSR\u0004\u0016M]1ngB!1q\u0010C\u0001\r\u001d!\u0019\u0001\u0006E\u0001\t\u000b\u0011a\"S7qY&\u001c\u0017\u000e\u001e)be\u0006l7oE\u0003\u0005\u0002)!9\u0001\u0005\u0003\u0004\u0000\u0011%\u0011b\u0001C\u00063\t9\u0012*\u001c9mS\u000eLG\u000fU1sC6\u001cX\t\u001f;sC\u000e$xN\u001d\u0005\bA\u0011\u0005A\u0011\u0001C\b)\t\u0019y\u0010\u0003\u0005\u0004\u0014\u0012\u0005A\u0011\u0001C\n)\u0019\u0019Y\u0002\"\u0006\u0005\u001a!AAq\u0003C\t\u0001\u0004\u0019Y\"A\u0004qCJ\fWn]:\t\u0011\u0011mA\u0011\u0003a\u0001\u0005g\f!\"[7qYB\f'/Y7t\u0011!\u0019Y\n\"\u0001\u0005\u0002\u0011}A\u0003\u0002C\u0011\t[\u0001Ra\u0003C\u0012\tOI1\u0001\"\n\u0007\u0005\u0011\u0019v.\\3\u0011\u000f-!Ic!\u0004\u0004\u0010%\u0019A1\u0006\u0004\u0003\rQ+\b\u000f\\33\u0011!!y\u0003\"\bA\u0002\r5\u0011\u0001\u0003<qCJ\fWn]:\b\u000f\u0011MB\u0003#\u0001\u00056\u0005Ia\t\\1hgJ+\u0007O\u001d\t\u0005\u0007\u007f\"9DB\u0004\u0005:QA\t\u0001b\u000f\u0003\u0013\u0019c\u0017mZ:SKB\u00148#\u0002C\u001c\u0015\u0011u\u0002\u0003BB@\t\u007fI1\u0001\"\u0011\u001a\u0005I1E.Y4t%\u0016\u0004(/\u0012=ue\u0006\u001cGo\u001c:\t\u000f\u0001\"9\u0004\"\u0001\u0005FQ\u0011AQ\u0007\u0005\t\u0007'#9\u0004\"\u0001\u0005JQ!1\u0011\u0005C&\u0011\u001d!i\u0005b\u0012A\u0002%\fAAY5ug\"A11\u0014C\u001c\t\u0003!\t\u0006\u0006\u0003\u0005T\u0011U\u0003\u0003B\u0006\u0005$%Da\u0001\u001bC(\u0001\u0004Iwa\u0002C-)!\u0005A1L\u0001\u0015'ftG/Y2uS\u000e$\u0016\u0010]3BaBd\u0017.\u001a3\u0011\t\r}DQ\f\u0004\b\t?\"\u0002\u0012\u0001C1\u0005Q\u0019\u0016P\u001c;bGRL7\rV=qK\u0006\u0003\b\u000f\\5fIN)AQ\f\u0006\u0005dA!1q\u0010C3\u0013\r!9'\u0007\u0002\u001e'ftG/Y2uS\u000e$\u0016\u0010]3BaBd\u0017.\u001a3FqR\u0014\u0018m\u0019;pe\"9\u0001\u0005\"\u0018\u0005\u0002\u0011-DC\u0001C.\u0011!\u0019\u0019\n\"\u0018\u0005\u0002\u0011=DCBAD\tc\"\u0019\b\u0003\u0005\u0003Z\u00125\u0004\u0019AAD\u0011!!)\b\"\u001cA\u0002\tM\u0018!\u0002;be\u001e\u001c\b\u0002CBN\t;\"\t\u0001\"\u001f\u0015\t\u0011mDq\u0010\t\u0006\u0017\r\u0005FQ\u0010\t\b\u0017\u0011%\u0012q\u0011Bz\u0011!\u0011I\u000eb\u001eA\u0002\u0005\u001dua\u0002CB)!\u0005AQQ\u0001\u0015'ftG/Y2uS\u000e\f\u0005\u000f\u001d7jK\u0012$\u0016\u0010]3\u0011\t\r}Dq\u0011\u0004\b\t\u0013#\u0002\u0012\u0001CF\u0005Q\u0019\u0016P\u001c;bGRL7-\u00119qY&,G\rV=qKN)Aq\u0011\u0006\u0005d!9\u0001\u0005b\"\u0005\u0002\u0011=EC\u0001CC\u0011!\u0019\u0019\nb\"\u0005\u0002\u0011MECBAD\t+#9\n\u0003\u0005\u0003Z\u0012E\u0005\u0019AAD\u0011!!)\b\"%A\u0002\tM\b\u0002CBN\t\u000f#\t\u0001b'\u0015\t\u0011mDQ\u0014\u0005\t\u00053$I\n1\u0001\u0002\b\u001e9A\u0011\u0015\u000b\t\u0002\u0011\r\u0016\u0001E*z]R\f7\r^5d\u0003B\u0004H.[3e!\u0011\u0019y\b\"*\u0007\u000f\u0011\u001dF\u0003#\u0001\u0005*\n\u00012+\u001f8uC\u000e$\u0018nY!qa2LW\rZ\n\u0006\tKSA1\u0016\t\u0005\u0007\u007f\"i+C\u0002\u00050f\u0011\u0011dU=oi\u0006\u001cG/[2BaBd\u0017.\u001a3FqR\u0014\u0018m\u0019;pe\"9\u0001\u0005\"*\u0005\u0002\u0011MFC\u0001CR\u0011!\u0019\u0019\n\"*\u0005\u0002\u0011]FCBAD\ts#Y\f\u0003\u0005\u0003Z\u0012U\u0006\u0019AAD\u0011!\u0019I\u0002\".A\u0002\rm\u0001\u0002CBN\tK#\t\u0001b0\u0015\t\u0011\u0005GQ\u0019\t\u0006\u0017\u0011\rB1\u0019\t\b\u0017\u0011%\u0012qQB\u000e\u0011!\u0011I\u000e\"0A\u0002\u0005\u001dua\u0002Ce)!EA1Z\u0001\u0007+:\u001cEo\u001c:\u0011\t\r}DQ\u001a\u0004\b\t\u001f$\u0002\u0012\u0003Ci\u0005\u0019)fn\u0011;peN\u0019AQ\u001a\u0006\t\u000f\u0001\"i\r\"\u0001\u0005VR\u0011A1\u001a\u0005\t\u00077#i\r\"\u0001\u0005ZR!A1\u001cCu!\u0015Y1\u0011\u0015Co!%YAq\u001cCr\u0007\u001b\u0011\u00190C\u0002\u0005b\u001a\u0011a\u0001V;qY\u0016\u001c\u0004c\u0001\r\u0005f&!Aq]AG\u0005%iu\u000eZ5gS\u0016\u00148\u000f\u0003\u0005\u0003Z\u0012]\u0007\u0019AAD\u000f\u001d!i\u000f\u0006E\t\t_\fA\"\u00168NWR+W\u000e\u001d7bi\u0016\u0004Baa \u0005r\u001a9A1\u001f\u000b\t\u0012\u0011U(\u0001D+o\u001b.$V-\u001c9mCR,7c\u0001Cy\u0015!9\u0001\u0005\"=\u0005\u0002\u0011eHC\u0001Cx\u0011!\u0019Y\n\"=\u0005\u0002\u0011uH\u0003\u0002C\u0000\u000b\u000f\u0001RaCBQ\u000b\u0003\u0001rbCC\u0002\u0005g\u001c\t\u0002b9\u0004\u000e\tM(1_\u0005\u0004\u000b\u000b1!A\u0002+va2,g\u0007\u0003\u0005\u0006\n\u0011m\b\u0019AC\u0006\u0003\u0015!X-\u001c9m!\rARQB\u0005\u0005\u000b\u001f\tiI\u0001\u0005UK6\u0004H.\u0019;f\u0011\u001d)\u0019\u0002\u0006C\t\u000b+\t!\"\\6TK24G+\u001f9f)\u0011\u0019\t\"b\u0006\t\u0011\teW\u0011\u0003a\u0001\u0003\u000f;q!b\u0007\u0015\u0011\u0003)i\"A\tTs:$\u0018m\u0019;jG\u000ec\u0017m]:EK\u001a\u0004Baa \u0006 \u00199Q\u0011\u0005\u000b\t\u0002\u0015\r\"!E*z]R\f7\r^5d\u00072\f7o\u001d#fMN)Qq\u0004\u0006\u0006&A!1qPC\u0014\u0013\r)I#\u0007\u0002\u001b'ftG/Y2uS\u000e\u001cE.Y:t\t\u00164W\t\u001f;sC\u000e$xN\u001d\u0005\bA\u0015}A\u0011AC\u0017)\t)i\u0002\u0003\u0005\u0004\u0014\u0016}A\u0011AC\u0019)Q)\u0019$\"\u000f\u0006>\u0015}R\u0011IC#\u000b\u000f*Y%\"\u0014\u0006RA\u0019\u0001$\"\u000e\n\t\u0015]\u0012Q\u0012\u0002\t\u00072\f7o\u001d#fM\"AQ1HC\u0018\u0001\u0004!\u0019/\u0001\u0003n_\u0012\u001c\bbB\u0019\u00060\u0001\u00071\u0011\u001d\u0005\t\u0007/*y\u00031\u0001\u0003t\"AQ1IC\u0018\u0001\u0004!\u0019/\u0001\u0006d_:\u001cHO]'pIND\u0001\u0002b\f\u00060\u0001\u000711\u0004\u0005\t\u000b\u0013*y\u00031\u0001\u0003t\u0006IQ-\u0019:ms\u0012+gm\u001d\u0005\t\u00057)y\u00031\u0001\u0003t\"AQqJC\u0018\u0001\u0004\t9)\u0001\u0005tK24G+\u001f9f\u0011!)\u0019&b\fA\u0002\tM\u0018\u0001\u00022pIfD\u0001ba'\u0006 \u0011\u0005Qq\u000b\u000b\u0005\u000b3*\t\u0007E\u0003\f\u0007C+Y\u0006E\u000b\f\u000b;\"\u0019o!9\u0004N\u0011\r8Q\u0002Bz\u0005g\u001c\tBa=\n\u0007\u0015}cA\u0001\u0004UkBdW-\u000f\u0005\t\u00053,)\u00061\u0001\u0002\b\u001e9QQ\r\u000b\t\u0002\u0015\u001d\u0014!E*z]R\f7\r^5d)J\f\u0017\u000e\u001e#fMB!1qPC5\r\u001d)Y\u0007\u0006E\u0001\u000b[\u0012\u0011cU=oi\u0006\u001cG/[2Ue\u0006LG\u000fR3g'\u0015)IGCC8!\u0011\u0019y(\"\u001d\n\u0007\u0015M\u0014D\u0001\u000eTs:$\u0018m\u0019;jGR\u0013\u0018-\u001b;EK\u001a,\u0005\u0010\u001e:bGR|'\u000fC\u0004!\u000bS\"\t!b\u001e\u0015\u0005\u0015\u001d\u0004\u0002CBJ\u000bS\"\t!b\u001f\u0015!\u0015MRQPC@\u000b\u0003+\u0019)\"\"\u0006\b\u0016%\u0005\u0002CC\u001e\u000bs\u0002\r\u0001b9\t\u000fE*I\b1\u0001\u0004b\"A1qKC=\u0001\u0004\u0011\u0019\u0010\u0003\u0005\u0006J\u0015e\u0004\u0019\u0001Bz\u0011!\u0011Y\"\"\u001fA\u0002\tM\b\u0002CC(\u000bs\u0002\r!a\"\t\u0011\u0015MS\u0011\u0010a\u0001\u0005gD\u0001ba'\u0006j\u0011\u0005QQ\u0012\u000b\u0005\u000b\u001f+9\nE\u0003\f\u0007C+\t\nE\t\f\u000b'#\u0019o!9\u0004N\tM(1_B\t\u0005gL1!\"&\u0007\u0005\u0019!V\u000f\u001d7fo!A!\u0011\\CF\u0001\u0004\t9iB\u0004\u0006\u001cRA\t!\"(\u0002%MKh\u000e^1di&\u001cwJ\u00196fGR$UM\u001a\t\u0005\u0007\u007f*yJB\u0004\u0006\"RA\t!b)\u0003%MKh\u000e^1di&\u001cwJ\u00196fGR$UMZ\n\u0006\u000b?SQQ\u0015\t\u0005\u0007\u007f*9+C\u0002\u0006*f\u00111dU=oi\u0006\u001cG/[2PE*,7\r\u001e#fM\u0016CHO]1di>\u0014\bb\u0002\u0011\u0006 \u0012\u0005QQ\u0016\u000b\u0003\u000b;C\u0001ba%\u0006 \u0012\u0005Q\u0011\u0017\u000b\u000f\u000bg+I,b/\u0006>\u0016}V\u0011YCb!\rARQW\u0005\u0005\u000bo\u000biIA\u0005N_\u0012,H.\u001a#fM\"AQ1HCX\u0001\u0004!\u0019\u000fC\u00042\u000b_\u0003\ra!5\t\u0011\u0015%Sq\u0016a\u0001\u0005gD\u0001Ba\u0007\u00060\u0002\u0007!1\u001f\u0005\t\u000b\u001f*y\u000b1\u0001\u0002\b\"AQ1KCX\u0001\u0004\u0011\u0019\u0010\u0003\u0005\u0004\u001c\u0016}E\u0011ACd)\u0011)I-\"4\u0011\u000b-\u0019\t+b3\u0011\u001f-)\u0019\u0001b9\u0004R\nM(1_B\t\u0005gD\u0001B!7\u0006F\u0002\u0007\u0011qQ\u0004\b\u000b#$\u0002\u0012ACj\u0003e\u0019\u0016P\u001c;bGRL7\rU1dW\u0006<Wm\u00142kK\u000e$H)\u001a4\u0011\t\r}TQ\u001b\u0004\b\u000b/$\u0002\u0012ACm\u0005e\u0019\u0016P\u001c;bGRL7\rU1dW\u0006<Wm\u00142kK\u000e$H)\u001a4\u0014\u000b\u0015U'\"b7\u0011\t\r}TQ\\\u0005\u0004\u000b?L\"AI*z]R\f7\r^5d!\u0006\u001c7.Y4f\u001f\nTWm\u0019;EK\u001a,\u0005\u0010\u001e:bGR|'\u000fC\u0004!\u000b+$\t!b9\u0015\u0005\u0015M\u0007\u0002CBJ\u000b+$\t!b:\u0015\u0019\u0015%Xq^Cy\u000bg,)0b>\u0011\u0007a)Y/\u0003\u0003\u0006n\u00065%A\u0003)bG.\fw-\u001a#fM\"9\u0011'\":A\u0002\rE\u0007\u0002CC%\u000bK\u0004\rAa=\t\u0011\tmQQ\u001da\u0001\u0005gD\u0001\"b\u0014\u0006f\u0002\u0007\u0011q\u0011\u0005\t\u000b'*)\u000f1\u0001\u0003t\"A11TCk\t\u0003)Y\u0010\u0006\u0003\u0006~\u001a\u0015\u0001#B\u0006\u0004\"\u0016}\b#D\u0006\u0007\u0002\rE'1\u001fBz\u0007#\u0011\u00190C\u0002\u0007\u0004\u0019\u0011a\u0001V;qY\u0016,\u0004\u0002\u0003Bm\u000bs\u0004\r!a\"\u0007\r\u0019%A\u0003\u0003D\u0006\u00059\u00196-\u00197b\u001b\u0016l'-\u001a:SK\u001a\u001c2Ab\u0002\u000b\u0011-1yAb\u0002\u0003\u0002\u0003\u0006IA\"\u0005\u0002\u000fMLXNY8mgB)\u0011q\nD\n[%!aQCA-\u0005\r\u0019V-\u001d\u0005\bA\u0019\u001dA\u0011\u0001D\r)\u00111YB\"\b\u0011\t\r}dq\u0001\u0005\t\r\u001f19\u00021\u0001\u0007\u0012!Aa\u0011\u0005D\u0004\t\u00031\u0019#\u0001\u0004sKN,H\u000e\u001e\u000b\u0005\rK19\u0003\u0005\u0003\f\u0007Ck\u0003BB\u0019\u0007 \u0001\u0007a\t\u0003\u0005\u0004\u001c\u001a\u001dA\u0011\u0001D\u0016)\u00111)C\"\f\t\u0011\teg\u0011\u0006a\u0001\u0003\u000f;qA\"\r\u0015\u0011#1\u0019$A\u0007UkBdWm\u00117bgN\u0014VM\u001a\t\u0005\u0007\u007f2)DB\u0004\u00078QA\tB\"\u000f\u0003\u001bQ+\b\u000f\\3DY\u0006\u001c8OU3g'\u00111)Db\u0007\t\u000f\u00012)\u0004\"\u0001\u0007>Q\u0011a1G\u0004\b\r\u0003\"\u0002\u0012\u0003D\"\u0003E!V\u000f\u001d7f\u0007>l\u0007/\u00198j_:\u0014VM\u001a\t\u0005\u0007\u007f2)EB\u0004\u0007HQA\tB\"\u0013\u0003#Q+\b\u000f\\3D_6\u0004\u0018M\\5p]J+gm\u0005\u0003\u0007F\u0019m\u0001b\u0002\u0011\u0007F\u0011\u0005aQ\n\u000b\u0003\r\u0007:qA\"\u0015\u0015\u0011#1\u0019&\u0001\u0007V]&$8\t\\1tgJ+g\r\u0005\u0003\u0004\u0000\u0019Uca\u0002D,)!Ea\u0011\f\u0002\r+:LGo\u00117bgN\u0014VMZ\n\u0005\r+2Y\u0002C\u0004!\r+\"\tA\"\u0018\u0015\u0005\u0019Msa\u0002D1)!Ea1M\u0001\u0011\rVt7\r^5p]\u000ec\u0017m]:SK\u001a\u0004Baa \u0007f\u00199aq\r\u000b\t\u0012\u0019%$\u0001\u0005$v]\u000e$\u0018n\u001c8DY\u0006\u001c8OU3g'\u00111)Gb\u0007\t\u000f\u00012)\u0007\"\u0001\u0007nQ\u0011a1M\u0004\b\rc\"\u0002\u0012\u0001D:\u00039\u0019\u0016P\u001c;bGRL7\rV;qY\u0016\u0004Baa \u0007v\u00199aq\u000f\u000b\t\u0002\u0019e$AD*z]R\f7\r^5d)V\u0004H.Z\n\u0006\rkRa1\u0010\t\u0005\u0007\u007f2i(C\u0002\u0007\u0000e\u0011qcU=oi\u0006\u001cG/[2UkBdW-\u0012=ue\u0006\u001cGo\u001c:\t\u000f\u00012)\b\"\u0001\u0007\u0004R\u0011a1\u000f\u0005\t\u0007'3)\b\"\u0001\u0007\bR!\u0011q\u0011DE\u0011!\u0011IA\"\"A\u0002\tM\b\u0002CBN\rk\"\tA\"$\u0015\t\u0019=e\u0011\u0013\t\u0006\u0017\r\u0005&1\u001f\u0005\t\u000534Y\t1\u0001\u0002\b\u001e9aQ\u0013\u000b\t\u0002\u0019]\u0015AE*z]R\f7\r^5d)V\u0004H.\u001a+za\u0016\u0004Baa \u0007\u001a\u001a9a1\u0014\u000b\t\u0002\u0019u%AE*z]R\f7\r^5d)V\u0004H.\u001a+za\u0016\u001cRA\"'\u000b\rwBq\u0001\tDM\t\u00031\t\u000b\u0006\u0002\u0007\u0018\"A11\u0013DM\t\u00031)\u000b\u0006\u0003\u0002\b\u001a\u001d\u0006\u0002\u0003B\u0005\rG\u0003\rAa=\t\u0011\rme\u0011\u0014C\u0001\rW#BAb$\u0007.\"A!\u0011\u001cDU\u0001\u0004\t9iB\u0004\u00072RA\tAb-\u0002+MKh\u000e^1di&\u001cg)\u001e8di&|g\u000eV=qKB!1q\u0010D[\r\u001d19\f\u0006E\u0001\rs\u0013QcU=oi\u0006\u001cG/[2Gk:\u001cG/[8o)f\u0004XmE\u0003\u00076*1Y\f\u0005\u0003\u0004\u0000\u0019u\u0016b\u0001D`3\tq2+\u001f8uC\u000e$\u0018n\u0019$v]\u000e$\u0018n\u001c8UsB,W\t\u001f;sC\u000e$xN\u001d\u0005\bA\u0019UF\u0011\u0001Db)\t1\u0019\f\u0003\u0005\u0004\u0014\u001aUF\u0011\u0001Dd)\u0019\t9I\"3\u0007N\"Aa1\u001aDc\u0001\u0004\u0011\u00190A\u0004be\u001e$\b/Z:\t\u0011\u0019=gQ\u0019a\u0001\u0003\u000f\u000baA]3tiB,\u0007\u0002CBN\rk#\tAb5\u0015\t\u0019Ug\u0011\u001c\t\u0006\u0017\r\u0005fq\u001b\t\b\u0017\u0011%\"1_AD\u0011!\u0011IN\"5A\u0002\u0005\u001dua\u0002Do)!\u0005aq\\\u0001\u000e'ftG\u000f[3uS\u000e,f.\u001b;\u0011\t\r}d\u0011\u001d\u0004\b\rG$\u0002\u0012\u0001Ds\u00055\u0019\u0016P\u001c;iKRL7-\u00168jiN\u0019a\u0011\u001d\u0006\t\u000f\u00012\t\u000f\"\u0001\u0007jR\u0011aq\u001c\u0005\t\u000773\t\u000f\"\u0001\u0007nR!\u0011Q\u0002Dx\u0011!\u0011INb;A\u0002\u0005\u001dua\u0002Dz)!\u0005aQ_\u0001\u000f'ftG/Y2uS\u000e\u0014En\\2l!\u0011\u0019yHb>\u0007\u000f\u0019eH\u0003#\u0001\u0007|\nq1+\u001f8uC\u000e$\u0018n\u0019\"m_\u000e\\7#\u0002D|\u0015\u0019u\b\u0003BB@\r\u007fL1a\"\u0001\u001a\u0005]\u0019\u0016P\u001c;bGRL7M\u00117pG.,\u0005\u0010\u001e:bGR|'\u000fC\u0004!\ro$\ta\"\u0002\u0015\u0005\u0019U\b\u0002CBJ\ro$\ta\"\u0003\u0015\t\u0005\u001du1\u0002\u0005\t\u0007S:9\u00011\u0001\u0003t\"A11\u0014D|\t\u00039y\u0001\u0006\u0003\u0007\u0010\u001eE\u0001\u0002\u0003Bm\u000f\u001b\u0001\r!a\"\b\u000f\u001dUA\u0003#\u0001\b\u0018\u0005\t2+\u001f8uC\u000e$\u0018n\u0019$v]\u000e$\u0018n\u001c8\u0011\t\r}t\u0011\u0004\u0004\b\u000f7!\u0002\u0012AD\u000f\u0005E\u0019\u0016P\u001c;bGRL7MR;oGRLwN\\\n\u0006\u000f3Qqq\u0004\t\u0005\u0007\u007f:\t#C\u0002\b$e\u0011!dU=oi\u0006\u001cG/[2Gk:\u001cG/[8o\u000bb$(/Y2u_JDq\u0001ID\r\t\u000399\u0003\u0006\u0002\b\u0018!A11SD\r\t\u00039Y\u0003\u0006\u0004\b.\u001dMrQ\u0007\t\u00041\u001d=\u0012\u0002BD\u0019\u0003\u001b\u0013\u0001BR;oGRLwN\u001c\u0005\t\u0005\u000b:I\u00031\u0001\u0003t\"AQ1KD\u0015\u0001\u0004\t9\t\u0003\u0005\u0004\u001c\u001eeA\u0011AD\u001d)\u00119Ydb\u0010\u0011\u000b-\u0019\tk\"\u0010\u0011\u000f-!Ica\u0004\u0002\b\"A!\u0011\\D\u001c\u0001\u00049icB\u0004\bDQA\ta\"\u0012\u0002\u0019MKh\u000e^1di&\u001cg*Z<\u0011\t\r}tq\t\u0004\b\u000f\u0013\"\u0002\u0012AD&\u00051\u0019\u0016P\u001c;bGRL7MT3x'\u001599ECD'!\u0011\u0019yhb\u0014\n\u0007\u001dE\u0013DA\u000bTs:$\u0018m\u0019;jG:+w/\u0012=ue\u0006\u001cGo\u001c:\t\u000f\u0001:9\u0005\"\u0001\bVQ\u0011qQ\t\u0005\t\u0007';9\u0005\"\u0001\bZQQ\u0011qQD.\u000f;:yf\"\u0019\t\u0011\u0015%sq\u000ba\u0001\u0005gD\u0001Ba\u0007\bX\u0001\u0007!1\u001f\u0005\t\u000b\u001f:9\u00061\u0001\u0002\b\"AQ1KD,\u0001\u0004\u0011\u0019\u0010\u0003\u0005\u0004\u001c\u001e\u001dC\u0011AD3)\u001199gb\u001c\u0011\u000b-\u0019\tk\"\u001b\u0011\u0017-9YGa=\u0003t\u000eE!1_\u0005\u0004\u000f[2!A\u0002+va2,G\u0007\u0003\u0005\u0003Z\u001e\r\u0004\u0019AAD\u000f\u001d9\u0019\b\u0006E\u0001\u000fk\nqbU=oi\u0006\u001cG/[2EK\u001a$UM\u001a\t\u0005\u0007\u007f:9HB\u0004\bzQA\tab\u001f\u0003\u001fMKh\u000e^1di&\u001cG)\u001a4EK\u001a\u001cRab\u001e\u000b\u000f{\u0002Baa \b\u0000%\u0019q\u0011Q\r\u00031MKh\u000e^1di&\u001cG)\u001a4EK\u001a,\u0005\u0010\u001e:bGR|'\u000fC\u0004!\u000fo\"\ta\"\"\u0015\u0005\u001dU\u0004\u0002CBJ\u000fo\"\ta\"#\u0015\u001d\u001d-u\u0011SDJ\u000f+;9j\"'\b\u001eB\u0019\u0001d\"$\n\t\u001d=\u0015Q\u0012\u0002\u0007\t\u00164G)\u001a4\t\u0011\u0015mrq\u0011a\u0001\tGDq!MDD\u0001\u0004\u0019\t\u000e\u0003\u0005\u0004X\u001d\u001d\u0005\u0019\u0001Bz\u0011!!ycb\"A\u0002\rm\u0001\u0002CDN\u000f\u000f\u0003\r!a\"\u0002\u0007Q\u0004H\u000f\u0003\u0005\b \u001e\u001d\u0005\u0019AAD\u0003\r\u0011\bn\u001d\u0005\t\u00077;9\b\"\u0001\b$R!qQUDU!\u0015Y1\u0011UDT!=YQ1\u0001Cr\u0007#\u001cie!\u0004\u0002\b\u0006\u001d\u0005\u0002\u0003Bm\u000fC\u0003\r!a\"\u0007\r\u001d5F\u0003CDX\u0005M\u0019\u0016P\u001c;bGRL7MV1m\t\u00164')Y:f'\u00159YKCDY!\u0011\u0019yhb-\n\u0007\u001dU\u0016D\u0001\rTs:$\u0018m\u0019;jGZ\u000bG\u000eR3g\u000bb$(/Y2u_JD1b\"/\b,\n\u0005\t\u0015!\u0003\u0002\u000e\u0005I\u0011n]'vi\u0006\u0014G.\u001a\u0005\bA\u001d-F\u0011AD_)\u00119yl\"1\u0011\t\r}t1\u0016\u0005\t\u000fs;Y\f1\u0001\u0002\u000e!AqQYDV\t\u000399-A\u0005n_\u0012Lg-[3sgR!A1]De\u0011!)Ydb1A\u0002\u0011\r\b\u0002CBJ\u000fW#\ta\"4\u0015\u0015\rEqqZDi\u000f'<)\u000e\u0003\u0005\u0006<\u001d-\u0007\u0019\u0001Cr\u0011\u001d\tt1\u001aa\u0001\u0007#D\u0001bb'\bL\u0002\u0007\u0011q\u0011\u0005\t\u000f?;Y\r1\u0001\u0002\b\"A11TDV\t\u00039I\u000e\u0006\u0003\b\\\u001e}\u0007#B\u0006\u0004\"\u001eu\u0007cC\u0006\bl\u0011\r8\u0011[AD\u0003\u000fC\u0001B!7\bX\u0002\u0007\u0011qQ\u0004\b\u000fG$\u0002\u0012ADs\u0003=\u0019\u0016P\u001c;bGRL7MV1m\t\u00164\u0007\u0003BB@\u000fO4qa\";\u0015\u0011\u00039YOA\bTs:$\u0018m\u0019;jGZ\u000bG\u000eR3g'\u001199ob0\t\u000f\u0001:9\u000f\"\u0001\bpR\u0011qQ]\u0004\b\u000fg$\u0002\u0012AD{\u0003=\u0019\u0016P\u001c;bGRL7MV1s\t\u00164\u0007\u0003BB@\u000fo4qa\"?\u0015\u0011\u00039YPA\bTs:$\u0018m\u0019;jGZ\u000b'\u000fR3g'\u001199pb0\t\u000f\u0001:9\u0010\"\u0001\b\u0000R\u0011qQ_\u0004\b\u0011\u0007!\u0002\u0012\u0001E\u0003\u0003=\u0019\u0016P\u001c;bGRL7-Q:tS\u001et\u0007\u0003BB@\u0011\u000f1q\u0001#\u0003\u0015\u0011\u0003AYAA\bTs:$\u0018m\u0019;jG\u0006\u001b8/[4o'\u0015A9A\u0003E\u0007!\u0011\u0019y\bc\u0004\n\u0007!E\u0011D\u0001\rTs:$\u0018m\u0019;jG\u0006\u001b8/[4o\u000bb$(/Y2u_JDq\u0001\tE\u0004\t\u0003A)\u0002\u0006\u0002\t\u0006!A11\u0013E\u0004\t\u0003AI\u0002\u0006\u0004\u0002\b\"m\u0001r\u0004\u0005\t\u0011;A9\u00021\u0001\u0002\b\u0006\u0019A\u000e[:\t\u0011\u001d}\u0005r\u0003a\u0001\u0003\u000fC\u0001ba'\t\b\u0011\u0005\u00012\u0005\u000b\u0005\u0011KAI\u0003E\u0003\f\u0007CC9\u0003E\u0004\f\tS\t9)a\"\t\u0011\te\u0007\u0012\u0005a\u0001\u0003\u000fCq\u0001#\f\u0015\t\u0003Ay#A\u000bV]2Lg\r\u001e'jgR,E.Z7f]R<\u0018n]3\u0016\t!E\u0002\u0012\t\u000b\u0005\u0011gA)EE\u0003\t6)AIDB\u0004\t8!-\u0002\u0001c\r\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\r\r}\u00042\bE \u0013\rAi$\u0007\u0002\u0016+:d\u0017N\u001a;MSN$X\t\\3nK:$x/[:f!\u0011\t9\u0004#\u0011\u0005\u0011\tM\u00072\u0006b\u0001\u0011\u0007\n2!a\u0010e\u0011!A9\u0005c\u000bA\u0002!%\u0013AC;oY&4G/\u00192mKB)\u0001\u0004c\u0013\t@%!\u0001R\nE(\u0005))f\u000e\\5gi\u0006\u0014G.Z\u0005\u0004\u0011#j\"!\u0003'jMR\f'\r\\3t\u0011\u001dA)\u0006\u0006C\u0001\u0011/\nA$\u00168mS\u001a$H*[:u\u001f\u001ad\u0015n\u001d;t\u000b2,W.\u001a8uo&\u001cX-\u0006\u0003\tZ!\u001dD\u0003\u0002E.\u0011S\u0012R\u0001#\u0018\u000b\u0011?2q\u0001c\u000e\tT\u0001AY\u0006\u0005\u0004\u0004\u0000!\u0005\u0004RM\u0005\u0004\u0011GJ\"\u0001H+oY&4G\u000fT5ti>3G*[:ug\u0016cW-\\3oi^L7/\u001a\t\u0005\u0003oA9\u0007\u0002\u0005\u0003T\"M#\u0019\u0001E\"\u0011!A9\u0005c\u0015A\u0002!-\u0004#\u0002\r\tL!\u0015ta\u0002E8)!\u0005\u0001\u0012O\u0001\u0011'ftG/Y2uS\u000e4\u0016\r\u001c$s_6\u0004Baa \tt\u00199\u0001R\u000f\u000b\t\u0002!]$\u0001E*z]R\f7\r^5d-\u0006dgI]8n'\u0015A\u0019H\u0003E=!\u0011\u0019y\bc\u001f\n\u0007!u\u0014DA\rTs:$\u0018m\u0019;jGZ\u000bGN\u0012:p[\u0016CHO]1di>\u0014\bb\u0002\u0011\tt\u0011\u0005\u0001\u0012\u0011\u000b\u0003\u0011cB\u0001ba%\tt\u0011\u0005\u0001R\u0011\u000b\u0007\u0003\u000fC9\tc#\t\u0011!%\u00052\u0011a\u0001\u0003\u000f\u000b1\u0001]1u\u0011!9y\nc!A\u0002\u0005\u001d\u0005\u0002CBN\u0011g\"\t\u0001c$\u0015\t!\u0015\u0002\u0012\u0013\u0005\t\u00053Di\t1\u0001\u0002\b\u001e9\u0001R\u0013\u000b\t\u0002!]\u0015AD*z]R\f7\r^5d-\u0006dW)\u001d\t\u0005\u0007\u007fBIJB\u0004\t\u001cRA\t\u0001#(\u0003\u001dMKh\u000e^1di&\u001cg+\u00197FcN)\u0001\u0012\u0014\u0006\t B!1q\u0010EQ\u0013\rA\u0019+\u0007\u0002\u0018'ftG/Y2uS\u000e4\u0016\r\\#r\u000bb$(/Y2u_JDq\u0001\tEM\t\u0003A9\u000b\u0006\u0002\t\u0018\"A11\u0013EM\t\u0003AY\u000b\u0006\u0004\u0002\b\"5\u0006r\u0016\u0005\t\u0011\u0013CI\u000b1\u0001\u0002\b\"Aqq\u0014EU\u0001\u0004\t9\t\u0003\u0005\u0004\u001c\"eE\u0011\u0001EZ)\u0011A)\u0003#.\t\u0011\te\u0007\u0012\u0017a\u0001\u0003\u000f;q\u0001#/\u0015\u0011\u0003AY,A\bTs:$\u0018m\u0019;jG\u001aKG\u000e^3s!\u0011\u0019y\b#0\u0007\u000f!}F\u0003#\u0001\tB\ny1+\u001f8uC\u000e$\u0018n\u0019$jYR,'oE\u0003\t>*A\u0019\r\u0005\u0003\u0004\u0000!\u0015\u0017b\u0001Ed3\tA2+\u001f8uC\u000e$\u0018n\u0019$jYR,'/\u0012=ue\u0006\u001cGo\u001c:\t\u000f\u0001Bi\f\"\u0001\tLR\u0011\u00012\u0018\u0005\t\u0007'Ci\f\"\u0001\tPR!\u0011q\u0011Ei\u0011!\u0011I\u000e#4A\u0002\u0005\u001d\u0005\u0002CBN\u0011{#\t\u0001#6\u0015\t!]\u0007\u0012\u001c\t\u0006\u0017\r\u0005\u0016q\u0011\u0005\t\u00053D\u0019\u000e1\u0001\u0002\b\u001e9\u0001R\u001c\u000b\t\u0002!}\u0017AF*z]R\f7\r^5d\u000b6\u0004H/\u001f+za\u0016$&/Z3\u0011\t\r}\u0004\u0012\u001d\u0004\b\u0011G$\u0002\u0012\u0001Es\u0005Y\u0019\u0016P\u001c;bGRL7-R7qif$\u0016\u0010]3Ue\u0016,7#\u0002Eq\u0015!\u001d\b\u0003BB@\u0011SL1\u0001c;\u001a\u0005}\u0019\u0016P\u001c;bGRL7-R7qif$\u0016\u0010]3Ue\u0016,W\t\u001f;sC\u000e$xN\u001d\u0005\bA!\u0005H\u0011\u0001Ex)\tAy\u000e\u0003\u0005\u0004\u0014\"\u0005H\u0011\u0001Ez)\t\tI\f\u0003\u0005\u0004\u001c\"\u0005H\u0011\u0001E|)\u0011\ti\u0001#?\t\u0011!m\bR\u001fa\u0001\u0003s\u000b!\u0001\u001e;\b\u000f!}H\u0003#\u0005\n\u0002\u0005AQK\u001c)biN+\u0017\u000f\u0005\u0003\u0004\u0000%\raaBE\u0003)!E\u0011r\u0001\u0002\t+:\u0004\u0016\r^*fcN\u0019\u00112\u0001\u0006\t\u000f\u0001J\u0019\u0001\"\u0001\n\fQ\u0011\u0011\u0012\u0001\u0005\t\u00077K\u0019\u0001\"\u0001\n\u0010Q!\u0011\u0012CE\u000b!\u0015Y1\u0011UE\n!\u0019\ty%!\u0016\t(!A1QAE\u0007\u0001\u0004\u0011\u0019pB\u0004\n\u001aQA\t!c\u0007\u0002\u001d%l\u0007\u000f\\8eKB\u000bG\u000fR3ggB!1qPE\u000f\r\u001dIy\u0002\u0006E\u0001\u0013C\u0011a\"[7qY>$W\rU1u\t\u001647o\u0005\u0003\n\u001e%\r\u0002c\u0001\r\n&%!\u0011rEE\u0015\u0005-!&/\u00198tM>\u0014X.\u001a:\n\u0007\u0005=U\u0004C\u0004!\u0013;!\t!#\f\u0015\u0005%m\u0001\u0002CE\u0019\u0013;!\t%c\r\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BAD\u0013kA\u0001B!7\n0\u0001\u0007\u0011q\u0011\u0005\t\u0013sIi\u0002\"\u0001\n<\u0005qAO]1og\u001a|'/\\*uCR\u001cH\u0003\u0002Bz\u0013{A\u0001b!\u0002\n8\u0001\u0007!1\u001f\u0005\t\u0007'Ki\u0002\"\u0001\nBQ!\u0011qQE\"\u0011!\u0011I.c\u0010A\u0002\u0005\u001d\u0005\u0002CBJ\u0013;!\t!c\u0012\u0015\t\tM\u0018\u0012\n\u0005\t\u0007\u000bI)\u00051\u0001\u0003t\u001e9\u0011R\n\u000b\t\u0002%=\u0013aD*z]R\f7\r^5d!\u0006$H)\u001a4\u0011\t\r}\u0014\u0012\u000b\u0004\b\u0013'\"\u0002\u0012AE+\u0005=\u0019\u0016P\u001c;bGRL7\rU1u\t\u001647#BE)\u0015%]\u0003\u0003BB@\u00133J1!c\u0017\u001a\u0005a\u0019\u0016P\u001c;bGRL7\rU1u\t\u00164W\t\u001f;sC\u000e$xN\u001d\u0005\bA%EC\u0011AE0)\tIy\u0005\u0003\u0005\u0004\u0014&EC\u0011AE2))\u0019y!#\u001a\nh%%\u00142\u000e\u0005\t\u000bwI\t\u00071\u0001\u0005d\"A\u0001\u0012RE1\u0001\u0004\t9\t\u0003\u0005\b\u001c&\u0005\u0004\u0019AAD\u0011!9y*#\u0019A\u0002\u0005\u001d\u0005\u0002CBN\u0013#\"\t!c\u001c\u0015\t%E\u0014R\u000f\t\u0006\u0017\r\u0005\u00162\u000f\t\f\u0017\u001d-D1]AD\u0003\u000f\u000b9\t\u0003\u0005\u0003Z&5\u0004\u0019AAD\u000f\u001dII\b\u0006E\t\u0013w\nq\"\u00168QCR\u001cV-],ji\"\u0014Vm\u001d\t\u0005\u0007\u007fJiHB\u0004\n\u0000QA\t\"#!\u0003\u001fUs\u0007+\u0019;TKF<\u0016\u000e\u001e5SKN\u001c2!# \u000b\u0011\u001d\u0001\u0013R\u0010C\u0001\u0013\u000b#\"!c\u001f\t\u0011\rm\u0015R\u0010C\u0001\u0013\u0013#B!c#\n\u0010B)1b!)\n\u000eB91\u0002\"\u000b\n\u0014\tM\b\u0002\u0003Bm\u0013\u000f\u0003\r!a\"\b\u000f%ME\u0003#\u0005\n\u0016\u0006\u0001RK\\*z]RDW\r^5d!\u0006\u0014\u0018-\u001c\t\u0005\u0007\u007fJ9JB\u0004\n\u001aRA\t\"c'\u0003!Us7+\u001f8uQ\u0016$\u0018n\u0019)be\u0006l7cAEL\u0015!9\u0001%c&\u0005\u0002%}ECAEK\u0011!\u0019Y*c&\u0005\u0002%\rF\u0003BES\u0013O\u0003RaCBQ\u0007#D\u0001B!7\n\"\u0002\u0007\u0011qQ\u0004\b\u0013W#\u0002\u0012CEW\u0003%)fNV5tSR|'\u000f\u0005\u0003\u0004\u0000%=faBEY)!E\u00112\u0017\u0002\n+:4\u0016n]5u_J\u001c2!c,\u000b\u0011\u001d\u0001\u0013r\u0016C\u0001\u0013o#\"!#,\t\u0011\rm\u0015r\u0016C\u0001\u0013w#B!#0\nJB)1b!)\n@B91\u0002\"\u000b\u0004R&\u0005\u0007CBA(\u0003+J\u0019\rE\u0002\u0019\u0013\u000bLA!c2\u0002\u000e\n91)Y:f\t\u00164\u0007\u0002\u0003Bm\u0013s\u0003\r!a\"\b\u000f%5G\u0003#\u0005\nP\u0006IQK\\\"m_N,(/\u001a\t\u0005\u0007\u007fJ\tNB\u0004\nTRA\t\"#6\u0003\u0013Us7\t\\8tkJ,7cAEi\u0015!9\u0001%#5\u0005\u0002%eGCAEh\u0011!\u0019Y*#5\u0005\u0002%uG\u0003\u0002E\u0013\u0013?D\u0001B!7\n\\\u0002\u0007\u0011qQ\u0004\b\u0013G$\u0002\u0012CEs\u0003)1\u0015\u000e\u001c;fe\u000e\u000bG\u000e\u001c\t\u0005\u0007\u007fJ9OB\u0004\njRA\t\"c;\u0003\u0015\u0019KG\u000e^3s\u0007\u0006dGnE\u0002\nh*Aq\u0001IEt\t\u0003Iy\u000f\u0006\u0002\nf\"A11TEt\t\u0003I\u0019\u0010\u0006\u0003\t&%U\b\u0002\u0003Bm\u0013c\u0004\r!a\"\b\u000f%eH\u0003#\u0005\n|\u0006AQK\u001c$jYR,'\u000f\u0005\u0003\u0004\u0000%uhaBE\u0000)!E!\u0012\u0001\u0002\t+:4\u0015\u000e\u001c;feN\u0019\u0011R \u0006\t\u000f\u0001Ji\u0010\"\u0001\u000b\u0006Q\u0011\u00112 \u0005\t\u00077Ki\u0010\"\u0001\u000b\nQ!!2\u0002F\u0007!\u0015YA1\u0005C?\u0011!\u0011INc\u0002A\u0002\u0005\u001dua\u0002F\t)!E!2C\u0001\u0013+:\u001c\u0005.Z2l\u0013\u001a\u0014VMZ;uC\ndW\r\u0005\u0003\u0004\u0000)Uaa\u0002F\f)!E!\u0012\u0004\u0002\u0013+:\u001c\u0005.Z2l\u0013\u001a\u0014VMZ;uC\ndWmE\u0002\u000b\u0016)Aq\u0001\tF\u000b\t\u0003Qi\u0002\u0006\u0002\u000b\u0014!A11\u0014F\u000b\t\u0003Q\t\u0003\u0006\u0003\t&)\r\u0002\u0002\u0003Bm\u0015?\u0001\r!a\"\u0007\r)\u001dB\u0003\u0003F\u0015\u0005A)fNR8s\u0007>l'-\u001b8bi&|gnE\u0002\u000b&)A!\"\rF\u0013\u0005\u0003\u0005\u000b\u0011BBi\u0011\u001d\u0001#R\u0005C\u0001\u0015_!BA#\r\u000b4A!1q\u0010F\u0013\u0011\u001d\t$R\u0006a\u0001\u0007#D\u0001ba'\u000b&\u0011\u0005!r\u0007\u000b\u0005\u0011KQI\u0004\u0003\u0005\u0003Z*U\u0002\u0019AAD\u000f\u001dQi\u0004\u0006E\t\u0015\u007f\tQ!\u00168NCB\u0004Baa \u000bB\u00199!2\t\u000b\t\u0012)\u0015#!B+o\u001b\u0006\u00048\u0003\u0002F!\u0015cAq\u0001\tF!\t\u0003QI\u0005\u0006\u0002\u000b@\u001d9!R\n\u000b\t\u0012)=\u0013!C+o\r>\u0014X-Y2i!\u0011\u0019yH#\u0015\u0007\u000f)MC\u0003#\u0005\u000bV\tIQK\u001c$pe\u0016\f7\r[\n\u0005\u0015#R\t\u0004C\u0004!\u0015#\"\tA#\u0017\u0015\u0005)=sa\u0002F/)!E!rL\u0001\n+:4E.\u0019;NCB\u0004Baa \u000bb\u00199!2\r\u000b\t\u0012)\u0015$!C+o\r2\fG/T1q'\u0011Q\tG#\r\t\u000f\u0001R\t\u0007\"\u0001\u000bjQ\u0011!rL\u0004\b\u0015[\"\u0002\u0012\u0003F8\u0003\u0015)fNR8s!\u0011\u0019yH#\u001d\u0007\u000f)MD\u0003#\u0005\u000bv\t)QK\u001c$peN\u0019!\u0012\u000f\u0006\t\u000f\u0001R\t\b\"\u0001\u000bzQ\u0011!r\u000e\u0005\t\u00077S\t\b\"\u0001\u000b~Q!aQ\u001bF@\u0011!\u0011INc\u001fA\u0002\u0005\u001d\u0005b\u0002FB)\u0011E!RQ\u0001\u000e[.,e.^7fe\u0006$xN]:\u0015\t\tM(r\u0011\u0005\t\u0015\u0013S\t\t1\u0001\u0003t\u0006)QM\\;ng\u001e9!R\u0012\u000b\t\u0002)=\u0015\u0001D*z]R\f7\r^5d\r>\u0014\b\u0003BB@\u0015#3qAc%\u0015\u0011\u0003Q)J\u0001\u0007Ts:$\u0018m\u0019;jG\u001a{'oE\u0003\u000b\u0012*Q9\n\u0005\u0003\u0004\u0000)e\u0015b\u0001FN3\t)2+\u001f8uC\u000e$\u0018n\u0019$pe\u0016CHO]1di>\u0014\bb\u0002\u0011\u000b\u0012\u0012\u0005!r\u0014\u000b\u0003\u0015\u001fC\u0001ba%\u000b\u0012\u0012\u0005!2\u0015\u000b\u0007\u0003\u000fS)Kc*\t\u0011)%%\u0012\u0015a\u0001\u0005gD\u0001\"b\u0015\u000b\"\u0002\u0007\u0011q\u0011\u0005\t\u00077S\t\n\"\u0001\u000b,R!aQ\u001bFW\u0011!\u0011IN#+A\u0002\u0005\u001dua\u0002FY)!\u0005!2W\u0001\u0012'ftG/Y2uS\u000e4uN]-jK2$\u0007\u0003BB@\u0015k3qAc.\u0015\u0011\u0003QILA\tTs:$\u0018m\u0019;jG\u001a{'/W5fY\u0012\u001cRA#.\u000b\u0015/Cq\u0001\tF[\t\u0003Qi\f\u0006\u0002\u000b4\"A11\u0013F[\t\u0003Q\t\r\u0006\u0004\u0002\b*\r'R\u0019\u0005\t\u0015\u0013Sy\f1\u0001\u0003t\"AQ1\u000bF`\u0001\u0004\t9\t\u0003\u0005\u0004\u001c*UF\u0011\u0001Fe)\u00111)Nc3\t\u0011\te'r\u0019a\u0001\u0003\u000f;qAc4\u0015\u0011#Q\t.A\u000bNCf\u0014W\rV=qKR\u0013X-Z(sS\u001eLg.\u00197\u0011\t\r}$2\u001b\u0004\b\u0015+$\u0002\u0012\u0003Fl\u0005Ui\u0015-\u001f2f)f\u0004X\r\u0016:fK>\u0013\u0018nZ5oC2\u001c2Ac5\u000b\u0011\u001d\u0001#2\u001bC\u0001\u00157$\"A#5\t\u0011\rm%2\u001bC\u0001\u0015?$BA#9\u000bdB)1\u0002b\t\u0002\b\"A!\u0011\u001cFo\u0001\u0004\t9iB\u0004\u000bhRA\tB#;\u0002!5\u000b\u0017PY3TK2,7\r^!qa2L\b\u0003BB@\u0015W4qA#<\u0015\u0011#QyO\u0001\tNCf\u0014WmU3mK\u000e$\u0018\t\u001d9msN\u0019!2\u001e\u0006\t\u000f\u0001RY\u000f\"\u0001\u000btR\u0011!\u0012\u001e\u0005\t\u00077SY\u000f\"\u0001\u000bxR!!\u0012\u001dF}\u0011!\u0011IN#>A\u0002\u0005\u001dua\u0002F\u007f)!E!r`\u0001\u000f\u001b\u0006L(-Z+oG\",7m[3e!\u0011\u0019yh#\u0001\u0007\u000f-\rA\u0003#\u0005\f\u0006\tqQ*Y=cKVs7\r[3dW\u0016$7cAF\u0001\u0015!9\u0001e#\u0001\u0005\u0002-%AC\u0001F\u0000\u0011!\u0019Yj#\u0001\u0005\u0002-5A\u0003\u0002Fq\u0017\u001fA\u0001B!7\f\f\u0001\u0007\u0011qQ\u0004\b\u0017'!\u0002\u0012CF\u000b\u0003)i\u0015-\u001f2f)f\u0004X\r\u001a\t\u0005\u0007\u007fZ9BB\u0004\f\u001aQA\tbc\u0007\u0003\u00155\u000b\u0017PY3UsB,GmE\u0002\f\u0018)Aq\u0001IF\f\t\u0003Yy\u0002\u0006\u0002\f\u0016!A11TF\f\t\u0003Y\u0019\u0003\u0006\u0003\f&-\u001d\u0002#B\u0006\u0005$!\u001d\u0002\u0002\u0003Bm\u0017C\u0001\r!a\"\t\u000f--B\u0003\"\u0005\f.\u00059Qn[\"bg\u0016\u001cH\u0003BEa\u0017_A\u0001b#\r\f*\u0001\u0007!1_\u0001\u0006G\u0006\u001cXm]\u0004\b\u0017k!\u0002\u0012AF\u001c\u0003a\u0019\u0016P\u001c;bGRL7\rU1si&\fGNR;oGRLwN\u001c\t\u0005\u0007\u007fZIDB\u0004\f<QA\ta#\u0010\u00031MKh\u000e^1di&\u001c\u0007+\u0019:uS\u0006dg)\u001e8di&|gnE\u0003\f:)Yy\u0004\u0005\u0003\u0004\u0000-\u0005\u0013bAF\"3\t\t3+\u001f8uC\u000e$\u0018n\u0019)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8FqR\u0014\u0018m\u0019;pe\"9\u0001e#\u000f\u0005\u0002-\u001dCCAF\u001c\u0011!\u0019\u0019j#\u000f\u0005\u0002--C\u0003BF'\u0017'\u00022\u0001GF(\u0013\u0011Y\t&!$\u0003\u000b5\u000bGo\u00195\t\u0011-E2\u0012\na\u0001\u0005gD\u0001ba'\f:\u0011\u00051r\u000b\u000b\u0005\u00173ZY\u0006E\u0003\f\u0007CK\t\r\u0003\u0005\u0003Z.U\u0003\u0019AAD\u000f\u001dYy\u0006\u0006E\u0001\u0017C\nabU=oi\u0006\u001cG/[2NCR\u001c\u0007\u000e\u0005\u0003\u0004\u0000-\rdaBF3)!\u00051r\r\u0002\u000f'ftG/Y2uS\u000el\u0015\r^2i'\u0015Y\u0019GCF5!\u0011\u0019yhc\u001b\n\u0007-5\u0014DA\fTs:$\u0018m\u0019;jG6\u000bGo\u00195FqR\u0014\u0018m\u0019;pe\"9\u0001ec\u0019\u0005\u0002-EDCAF1\u0011!\u0019\u0019jc\u0019\u0005\u0002-UDCBF'\u0017oZY\b\u0003\u0005\fz-M\u0004\u0019AAD\u0003%\u00198M];uS:,W\r\u0003\u0005\f2-M\u0004\u0019\u0001Bz\u0011!\u0019Yjc\u0019\u0005\u0002-}D\u0003BFA\u0017\u000b\u0003RaCBQ\u0017\u0007\u0003ra\u0003C\u0015\u0003\u000fK\t\r\u0003\u0005\u0003Z.u\u0004\u0019AF'\u000f\u001dYI\t\u0006E\u0001\u0017\u0017\u000bAbU=oi\u0006\u001cG/[2Uef\u0004Baa \f\u000e\u001a91r\u0012\u000b\t\u0002-E%\u0001D*z]R\f7\r^5d)JL8#BFG\u0015-M\u0005\u0003BB@\u0017+K1ac&\u001a\u0005U\u0019\u0016P\u001c;bGRL7\r\u0016:z\u000bb$(/Y2u_JDq\u0001IFG\t\u0003YY\n\u0006\u0002\f\f\"A11SFG\t\u0003Yy\n\u0006\u0005\f\".\u001d62VFX!\rA22U\u0005\u0005\u0017K\u000biIA\u0002UefD\u0001b#+\f\u001e\u0002\u0007\u0011qQ\u0001\u0006E2|7m\u001b\u0005\t\u0017[[i\n1\u0001\u0003t\u000691-\u0019;dQ\u0016\u001c\b\u0002CFY\u0017;\u0003\r!a\"\u0002\u0013\u0019Lg.\u00197ju\u0016\u0014\b\u0002CBN\u0017\u001b#\ta#.\u0015\t-]62\u0018\t\u0006\u0017\r\u00056\u0012\u0018\t\n\u0017\u0011}\u0017qQEa\u0003\u000fC\u0001B!7\f4\u0002\u00071\u0012U\u0004\b\u0017\u007f#\u0002\u0012AFa\u0003I\u0019\u0016P\u001c;bGRL7\rV3s[&#WM\u001c;\u0011\t\r}42\u0019\u0004\b\u0017\u000b$\u0002\u0012AFd\u0005I\u0019\u0016P\u001c;bGRL7\rV3s[&#WM\u001c;\u0014\u000b-\r'b#3\u0011\t\r}42Z\u0005\u0004\u0017\u001bL\"aG*z]R\f7\r^5d)\u0016\u0014X.\u00133f]R,\u0005\u0010\u001e:bGR|'\u000fC\u0004!\u0017\u0007$\ta#5\u0015\u0005-\u0005\u0007\u0002CBJ\u0017\u0007$\ta#6\u0015\r\u0005-6r[Fm\u0011\u001d\t42\u001ba\u0001\u0007#D!bc7\fTB\u0005\t\u0019AA\u0007\u00031I7OQ1dWF,x\u000e^3e\u0011!\u0019Yjc1\u0005\u0002-}G\u0003BFq\u0017K\u0004RaCBQ\u0017G\u0004ra\u0003C\u0015\u0007#\fi\u0001\u0003\u0005\fh.u\u0007\u0019AAV\u0003\tIGmB\u0004\flRA\ta#<\u0002%MKh\u000e^1di&\u001cG+\u001f9f\u0013\u0012,g\u000e\u001e\t\u0005\u0007\u007fZyOB\u0004\frRA\tac=\u0003%MKh\u000e^1di&\u001cG+\u001f9f\u0013\u0012,g\u000e^\n\u0006\u0017_T1R\u001f\t\u0005\u0007\u007fZ90C\u0002\fzf\u00111dU=oi\u0006\u001cG/[2UsB,\u0017\nZ3oi\u0016CHO]1di>\u0014\bb\u0002\u0011\fp\u0012\u00051R \u000b\u0003\u0017[D\u0001ba%\fp\u0012\u0005A\u0012\u0001\u000b\u0005\u0003Wc\u0019\u0001C\u00042\u0017\u007f\u0004\ra!9\t\u0011\rm5r\u001eC\u0001\u0019\u000f!B\u0001$\u0003\r\fA)1b!)\u0004b\"A!\u0011\u001cG\u0003\u0001\u0004\t9iB\u0004\r\u0010QA\t\u0001$\u0005\u0002\u001fMKh\u000e^1di&\u001c\u0017*\u001c9peR\u0004Baa \r\u0014\u00199AR\u0003\u000b\t\u00021]!aD*z]R\f7\r^5d\u00136\u0004xN\u001d;\u0014\u000b1M!\u0002$\u0007\u0011\t\r}D2D\u0005\u0004\u0019;I\"\u0001G*z]R\f7\r^5d\u00136\u0004xN\u001d;FqR\u0014\u0018m\u0019;pe\"9\u0001\u0005d\u0005\u0005\u00021\u0005BC\u0001G\t\u000f!a)\u0003d\u0005\t\n1\u001d\u0012\u0001E,jY\u0012\u001c\u0017M\u001d3TK2,7\r^8s!\u0011aI\u0003d\u000b\u000e\u00051Ma\u0001\u0003G\u0017\u0019'AI\u0001d\f\u0003!]KG\u000eZ2be\u0012\u001cV\r\\3di>\u00148c\u0001G\u0016\u0015!9\u0001\u0005d\u000b\u0005\u00021MBC\u0001G\u0014\u0011!\u0019\u0019\nd\u000b\u0005\u00021]B\u0003\u0002G\u001d\u0019\u007f\u00012\u0001\u0007G\u001e\u0013\u0011ai$!$\u0003\u001d%k\u0007o\u001c:u'\u0016dWm\u0019;pe\"9A\u0012\tG\u001b\u0001\u0004)\u0016AB8gMN,G\u000f\u0003\u0005\u0004\u001c2-B\u0011\u0001G#)\u0011a9\u0005$\u0013\u0011\t-\u0019\t+\u0016\u0005\t\u0019\u0017b\u0019\u00051\u0001\r:\u0005\u00191/\u001a7\b\u00111=C2\u0003E\u0005\u0019#\nABT1nKN+G.Z2u_J\u0004B\u0001$\u000b\rT\u0019AAR\u000bG\n\u0011\u0013a9F\u0001\u0007OC6,7+\u001a7fGR|'oE\u0002\rT)Aq\u0001\tG*\t\u0003aY\u0006\u0006\u0002\rR!A11\u0013G*\t\u0003ay\u0006\u0006\u0004\r:1\u0005D2\r\u0005\bc1u\u0003\u0019ABi\u0011\u001da\t\u0005$\u0018A\u0002UC\u0001ba'\rT\u0011\u0005Ar\r\u000b\u0005\u0019Sbi\u0007E\u0003\f\u0007CcY\u0007\u0005\u0004\f\tS\u0019\t.\u0016\u0005\t\u0019\u0017b)\u00071\u0001\r:\u001dAA\u0012\u000fG\n\u0011\u0013a\u0019(\u0001\bSK:\fW.Z*fY\u0016\u001cGo\u001c:\u0011\t1%BR\u000f\u0004\t\u0019ob\u0019\u0002#\u0003\rz\tq!+\u001a8b[\u0016\u001cV\r\\3di>\u00148c\u0001G;\u0015!9\u0001\u0005$\u001e\u0005\u00021uDC\u0001G:\u0011!\u0019\u0019\n$\u001e\u0005\u00021\u0005EC\u0003G\u001d\u0019\u0007c9\td#\r\u0010\"AAR\u0011G@\u0001\u0004\u0019\t.A\u0003oC6,\u0017\u0007C\u0004\r\n2}\u0004\u0019A+\u0002\u000f=4gm]3uc!AAR\u0012G@\u0001\u0004\u0019\t.A\u0003oC6,'\u0007C\u0004\r\u00122}\u0004\u0019A+\u0002\u000f=4gm]3ue!A11\u0014G;\t\u0003a)\n\u0006\u0003\r\u00182m\u0005#B\u0006\u0004\"2e\u0005#C\u0006\bl\rEWk!5V\u0011!aY\u0005d%A\u00021er\u0001\u0003GP\u0019'AI\u0001$)\u0002!Us\u0017.\u001c9peR\u001cV\r\\3di>\u0014\b\u0003\u0002G\u0015\u0019G3\u0001\u0002$*\r\u0014!%Ar\u0015\u0002\u0011+:LW\u000e]8siN+G.Z2u_J\u001c2\u0001d)\u000b\u0011\u001d\u0001C2\u0015C\u0001\u0019W#\"\u0001$)\t\u0011\rME2\u0015C\u0001\u0019_#b\u0001$\u000f\r22M\u0006bB\u0019\r.\u0002\u00071\u0011\u001b\u0005\b\u0019\u0003bi\u000b1\u0001V\u0011!\u0019Y\nd)\u0005\u00021]F\u0003\u0002G5\u0019sC\u0001\u0002d\u0013\r6\u0002\u0007A\u0012H\u0004\t\u0019{c\u0019\u0002#\u0003\r@\u0006!r+\u001b7eG\u0006\u0014HmU3mK\u000e$xN\u001d*faJ\u0004B\u0001$\u000b\rB\u001aAA2\u0019G\n\u0011\u0013a)M\u0001\u000bXS2$7-\u0019:e'\u0016dWm\u0019;peJ+\u0007O]\n\u0004\u0019\u0003T\u0001b\u0002\u0011\rB\u0012\u0005A\u0012\u001a\u000b\u0003\u0019\u007fC\u0001ba%\rB\u0012\u0005AR\u001a\u000b\u0005\u0003\u000fcy\r\u0003\u0004~\u0019\u0017\u0004\rA \u0005\t\u00077c\t\r\"\u0001\rTR!AR\u001bGl!\u0011Y1\u0011\u0015@\t\u0011\teG\u0012\u001ba\u0001\u0003\u000f;\u0001\u0002d7\r\u0014!%AR\\\u0001\u0011\u001d\u0006lWmU3mK\u000e$xN\u001d*faJ\u0004B\u0001$\u000b\r`\u001aAA\u0012\u001dG\n\u0011\u0013a\u0019O\u0001\tOC6,7+\u001a7fGR|'OU3qeN\u0019Ar\u001c\u0006\t\u000f\u0001by\u000e\"\u0001\rhR\u0011AR\u001c\u0005\t\u0007'cy\u000e\"\u0001\rlR1\u0011q\u0011Gw\u0019_Dq!\rGu\u0001\u0004\u0019\t\u000e\u0003\u0004~\u0019S\u0004\rA \u0005\t\u00077cy\u000e\"\u0001\rtR!AR\u001fG}!\u0015Y1\u0011\u0015G|!\u0019YA\u0011FBi}\"A!\u0011\u001cGy\u0001\u0004\t9i\u0002\u0005\r~2M\u0001\u0012\u0002G\u0000\u0003\u0015\t%O]8x!\u0011aI#$\u0001\u0007\u00115\rA2\u0003E\u0005\u001b\u000b\u0011Q!\u0011:s_^\u001c2!$\u0001\u000b\u0011\u001d\u0001S\u0012\u0001C\u0001\u001b\u0013!\"\u0001d@\t\u0011\rMU\u0012\u0001C\u0001\u001b\u001b!b!d\u0004\u000e\u00165e\u0001c\u0001\r\u000e\u0012%!Q2CAG\u0005\u0015\t\u0005\u000f\u001d7z\u0011!i9\"d\u0003A\u0002\u0005\u001d\u0015\u0001\u00027fMRD\u0001\"d\u0007\u000e\f\u0001\u0007\u0011qQ\u0001\u0006e&<\u0007\u000e\u001e\u0005\t\u00077k\t\u0001\"\u0001\u000e Q!\u0001REG\u0011\u0011!\u0011I.$\bA\u00025=q\u0001CG\u0013\u0019'AI!d\n\u0002%I+g.Y7f'\u0016dWm\u0019;peJ+\u0007O\u001d\t\u0005\u0019SiIC\u0002\u0005\u000e,1M\u0001\u0012BG\u0017\u0005I\u0011VM\\1nKN+G.Z2u_J\u0014V\r\u001d:\u0014\u00075%\"\u0002C\u0004!\u001bS!\t!$\r\u0015\u00055\u001d\u0002\u0002CBJ\u001bS!\t!$\u000e\u0015\u0015\u0005\u001dUrGG\u001d\u001b{iy\u0004\u0003\u0005\r\u00066M\u0002\u0019ABi\u0011\u001diY$d\rA\u0002y\fA\u0001]8tc!AARRG\u001a\u0001\u0004\u0019\t\u000eC\u0004\u000eB5M\u0002\u0019\u0001@\u0002\tA|7O\r\u0005\t\u00077kI\u0003\"\u0001\u000eFQ!QrIG&!\u0015Y1\u0011UG%!%Yq1NBi}\u000eEg\u0010\u0003\u0005\u0003Z6\r\u0003\u0019AAD\u000f!iy\u0005d\u0005\t\n5E\u0013\u0001F+oS6\u0004xN\u001d;TK2,7\r^8s%\u0016\u0004(\u000f\u0005\u0003\r*5Mc\u0001CG+\u0019'AI!d\u0016\u0003)Us\u0017.\u001c9peR\u001cV\r\\3di>\u0014(+\u001a9s'\ri\u0019F\u0003\u0005\bA5MC\u0011AG.)\ti\t\u0006\u0003\u0005\u0004\u00146MC\u0011AG0)\u0019\t9)$\u0019\u000ed!9\u0011'$\u0018A\u0002\rE\u0007BB?\u000e^\u0001\u0007a\u0010\u0003\u0005\u0004\u001c6MC\u0011AG4)\u0011a)0$\u001b\t\u0011\teWR\ra\u0001\u0003\u000fC\u0001\"$\u001c\r\u0014\u0011%QrN\u0001\u000bI\u0016\u0014\u0018N^3e!>\u001cH#\u0002@\u000er5U\u0004\u0002CG:\u001bW\u0002\r!a\"\u0002\u0003QDq\u0001$\u0011\u000el\u0001\u0007Q\u000b\u0003\u0005\u000ez1MA\u0011BG>\u00035!WM]5wK\u0012|eMZ:fiR\u0019Q+$ \t\rul9\b1\u0001\u007f\u0011!\u0019\u0019\nd\u0005\u0005\u00025\u0005ECBGB\u001b\u0013ki\tE\u0002\u0019\u001b\u000bKA!d\"\u0002\u000e\n1\u0011*\u001c9peRD\u0001\"d#\u000e\u0000\u0001\u0007\u0011qQ\u0001\u0005Kb\u0004(\u000f\u0003\u0005\u000e\u00106}\u0004\u0019\u0001Bz\u0003%\u0019X\r\\3di>\u00148\u000f\u0003\u0005\u0004\u001c2MA\u0011AGJ)\u0011QY!$&\t\u00115]U\u0012\u0013a\u0001\u001b\u0007\u000b1![7q\u000f\u001diY\n\u0006E\u0001\u001b;\u000b1cU=oi\u0006\u001cG/[2TK2,7\r\u001e+za\u0016\u0004Baa \u000e \u001a9Q\u0012\u0015\u000b\t\u00025\r&aE*z]R\f7\r^5d'\u0016dWm\u0019;UsB,7#BGP\u00155\u0015\u0006\u0003BB@\u001bOK1!$+\u001a\u0005q\u0019\u0016P\u001c;bGRL7mU3mK\u000e$H+\u001f9f\u000bb$(/Y2u_JDq\u0001IGP\t\u0003ii\u000b\u0006\u0002\u000e\u001e\"A11SGP\t\u0003i\t\f\u0006\u0004\u0002\u001a6MVR\u0017\u0005\t\u0007\u000fly\u000b1\u0001\u0002\b\"9\u0011'd,A\u0002\r\u0005\b\u0002CBN\u001b?#\t!$/\u0015\t5mVr\u0018\t\u0006\u0017\r\u0005VR\u0018\t\b\u0017\u0011%\u0012qQBq\u0011!\u0011I.d.A\u0002\u0005\u001duaBGb)!\u0005QRY\u0001\u0014'ftG/Y2uS\u000e\u001cV\r\\3diR+'/\u001c\t\u0005\u0007\u007fj9MB\u0004\u000eJRA\t!d3\u0003'MKh\u000e^1di&\u001c7+\u001a7fGR$VM]7\u0014\u000b5\u001d'\"$4\u0011\t\r}TrZ\u0005\u0004\u001b#L\"\u0001H*z]R\f7\r^5d'\u0016dWm\u0019;UKJlW\t\u001f;sC\u000e$xN\u001d\u0005\bA5\u001dG\u0011AGk)\ti)\r\u0003\u0005\u0004\u00146\u001dG\u0011AGm)\u0019\tI*d7\u000e^\"A1qYGl\u0001\u0004\t9\tC\u00042\u001b/\u0004\ra!5\t\u0011\rmUr\u0019C\u0001\u001bC$B!d9\u000ehB)1b!)\u000efB91\u0002\"\u000b\u0002\b\u000eE\u0007\u0002\u0003Bm\u001b?\u0004\r!a\"\b\u000f5-H\u0003#\u0001\u000en\u0006)2+\u001f8uC\u000e$\u0018nY\"p[B|WO\u001c3UsB,\u0007\u0003BB@\u001b_4q!$=\u0015\u0011\u0003i\u0019PA\u000bTs:$\u0018m\u0019;jG\u000e{W\u000e]8v]\u0012$\u0016\u0010]3\u0014\u000b5=(\"$>\u0011\t\r}Tr_\u0005\u0004\u001bsL\"AH*z]R\f7\r^5d\u0007>l\u0007o\\;oIRK\b/Z#yiJ\f7\r^8s\u0011\u001d\u0001Sr\u001eC\u0001\u001b{$\"!$<\t\u0011\rMUr\u001eC\u0001\u001d\u0003!bAd\u0001\u000f\n9-\u0001c\u0001\r\u000f\u0006%!arAAG\u0005A\u0019u.\u001c9pk:$G+\u001f9f)J,W\r\u0003\u0005\u0003\u001c5}\b\u0019\u0001Bz\u0011!\u00199,d@A\u0002\tM\b\u0002CBN\u001b_$\tAd\u0004\u0015\t9EaR\u0003\t\u0006\u0017\r\u0005f2\u0003\t\b\u0017\u0011%\"1\u001fBz\u0011!\u0011IN$\u0004A\u0002\u0005\u001dua\u0002H\r)!\u0005a2D\u0001\u0017'ftG/Y2uS\u000e\u001c\u0016N\\4mKR|g\u000eV=qKB!1q\u0010H\u000f\r\u001dqy\u0002\u0006E\u0001\u001dC\u0011acU=oi\u0006\u001cG/[2TS:<G.\u001a;p]RK\b/Z\n\u0006\u001d;Qa2\u0005\t\u0005\u0007\u007fr)#C\u0002\u000f(e\u0011qdU=oi\u0006\u001c\u0017\u000e^2TS:<G.\u001a;p]RK\b/Z#yiJ\f7\r^8s\u0011\u001d\u0001cR\u0004C\u0001\u001dW!\"Ad\u0007\t\u0011\rMeR\u0004C\u0001\u001d_!BA$\r\u000f8A\u0019\u0001Dd\r\n\t9U\u0012Q\u0012\u0002\u0012'&tw\r\\3u_:$\u0016\u0010]3Ue\u0016,\u0007\u0002\u0003H\u001d\u001d[\u0001\r!a\"\u0002\u0007I,g\r\u0003\u0005\u0004\u001c:uA\u0011\u0001H\u001f)\u0011A9Nd\u0010\t\u0011\teg2\ba\u0001\u0003\u000f;qAd\u0011\u0015\u0011\u0003q)%A\fTs:$\u0018m\u0019;jGRK\b/\u001a)s_*,7\r^5p]B!1q\u0010H$\r\u001dqI\u0005\u0006E\u0001\u001d\u0017\u0012qcU=oi\u0006\u001cG/[2UsB,\u0007K]8kK\u000e$\u0018n\u001c8\u0014\u000b9\u001d#B$\u0014\u0011\t\r}drJ\u0005\u0004\u001d#J\"\u0001I*z]R\f7\r^5d)f\u0004X\r\u0015:pU\u0016\u001cG/[8o\u000bb$(/Y2u_JDq\u0001\tH$\t\u0003q)\u0006\u0006\u0002\u000fF!A11\u0013H$\t\u0003qI\u0006\u0006\u0004\u000f\\9\u0005d2\r\t\u000419u\u0013\u0002\u0002H0\u0003\u001b\u0013!cU3mK\u000e$hI]8n)f\u0004X\r\u0016:fK\"A1q\u0019H,\u0001\u0004\t9\tC\u00042\u001d/\u0002\ra!9\t\u0011\rmer\tC\u0001\u001dO\"B!d/\u000fj!A!\u0011\u001cH3\u0001\u0004\t9iB\u0004\u000fnQA\tAd\u001c\u0002-MKh\u000e^1di&\u001c\u0017I\u001c8pi\u0006$X\r\u001a+za\u0016\u0004Baa \u000fr\u00199a2\u000f\u000b\t\u00029U$AF*z]R\f7\r^5d\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3\u0014\u000b9E$Bd\u001e\u0011\t\r}d\u0012P\u0005\u0004\u001dwJ\"aH*z]R\f7\r^5d\u0003:tw\u000e^1uK\u0012$\u0016\u0010]3FqR\u0014\u0018m\u0019;pe\"9\u0001E$\u001d\u0005\u00029}DC\u0001H8\u0011!\u0019\u0019J$\u001d\u0005\u00029\rEC\u0002HC\u001d\u0017si\tE\u0002\u0019\u001d\u000fKAA$#\u0002\u000e\nI\u0011I\u001c8pi\u0006$X\r\u001a\u0005\t\u000f7s\t\t1\u0001\u0002\b\"Aar\u0012HA\u0001\u0004\t9)A\u0003b]:|G\u000f\u0003\u0005\u0004\u001c:ED\u0011\u0001HJ)\u0011A)C$&\t\u0011\teg\u0012\u0013a\u0001\u0003\u000f;qA$'\u0015\u0011\u0003qY*\u0001\rTs:$\u0018m\u0019;jG\u0016C\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016\u0004Baa \u000f\u001e\u001a9ar\u0014\u000b\t\u00029\u0005&\u0001G*z]R\f7\r^5d\u000bbL7\u000f^3oi&\fG\u000eV=qKN)aR\u0014\u0006\u000f$B!1q\u0010HS\u0013\rq9+\u0007\u0002\"'ftG/Y2uS\u000e,\u00050[:uK:$\u0018.\u00197UsB,W\t\u001f;sC\u000e$xN\u001d\u0005\bA9uE\u0011\u0001HV)\tqY\n\u0003\u0005\u0004\u0014:uE\u0011\u0001HX)\u0019q\tLd.\u000f:B\u0019\u0001Dd-\n\t9U\u0016Q\u0012\u0002\u0014\u000bbL7\u000f^3oi&\fG\u000eV=qKR\u0013X-\u001a\u0005\t\u000f7si\u000b1\u0001\u0002\b\"Aa2\u0018HW\u0001\u0004\u0011\u00190A\u0003xQ\u0016\u0014X\r\u0003\u0005\u0004\u001c:uE\u0011\u0001H`)\u0011q\tM$4\u0011\u000b-\u0019\tKd1\u0011\u000f-!I#a\"\u000fFB1\u0011qJA+\u001d\u000f\u00042\u0001\u0007He\u0013\u0011qY-!$\u0003\u00135+WNY3s\t\u00164\u0007\u0002\u0003Bm\u001d{\u0003\r!a\"\t\u00139EG#%A\u0005B9M\u0017!\u00068fo\u001a\u0013X-\u001a+fe6$C-\u001a4bk2$HeM\u000b\u0003\u001d+T3!\u001bHlW\tqI\u000e\u0005\u0003\u000f\\:\u0015XB\u0001Ho\u0015\u0011qyN$9\u0002\u0013Ut7\r[3dW\u0016$'b\u0001Hr\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t9\u001dhR\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"\u0003Hv)E\u0005I\u0011\tHw\u0003UqWm\u001e$sK\u0016$VM]7%I\u00164\u0017-\u001e7uIQ*\"Ad<+\u0007Ir9\u000eC\u0005\u000ftR\t\n\u0011\"\u0011\u000fT\u0006)b.Z<Ge\u0016,G+\u001f9fI\u0011,g-Y;mi\u0012\u0012\u0004\"\u0003H|)E\u0005I\u0011\tHw\u0003UqWm\u001e$sK\u0016$\u0016\u0010]3%I\u00164\u0017-\u001e7uIMB\u0011Bd?\u0015#\u0003%\tA$@\u0002#5\\\u0007+\u0019:b[\u0012\"WMZ1vYR$#'\u0006\u0002\u000f\u0000*\"1\u0011\u0005Hl\u0011%y\u0019\u0001FI\u0001\n\u0003qi0A\tnWB\u000b'/Y7%I\u00164\u0017-\u001e7uIMB\u0011bd\u0002\u0015#\u0003%\tA$<\u0002/\u0019\u0014Xm\u001d5UKJlg*Y7fI\u0011,g-Y;mi\u0012\n\u0004\"CH\u0006\u0001\t\u0007I\u0011AH\u0007\u0003\u0015\u0011W/\u001b7e+\u0005\u0011\u0003bBH\t\u0001\u0001\u0006IAI\u0001\u0007EVLG\u000e\u001a\u0011\u0011\t=UqrC\u0007\u0002\u0005%\u0019q\u0012\u0004\u0002\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a")
public interface ReificationSupport {
    public void scala$reflect$internal$ReificationSupport$_setter_$build_$eq(ReificationSupportImpl var1);

    public ReificationSupportImpl build();

    public static class ReificationSupportImpl
    implements Internals.ReificationSupportApi {
        private volatile ReificationSupport$ReificationSupportImpl$ScalaDot$ ScalaDot$module;
        private volatile ReificationSupport$ReificationSupportImpl$ImplicitParams$ ImplicitParams$module;
        private volatile ReificationSupport$ReificationSupportImpl$FlagsRepr$ FlagsRepr$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTypeApplied$ SyntacticTypeApplied$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticAppliedType$ SyntacticAppliedType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticApplied$ SyntacticApplied$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnCtor$ UnCtor$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnMkTemplate$ UnMkTemplate$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticClassDef$ SyntacticClassDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTraitDef$ SyntacticTraitDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticObjectDef$ SyntacticObjectDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticPackageObjectDef$ SyntacticPackageObjectDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$TupleClassRef$ TupleClassRef$module;
        private volatile ReificationSupport$ReificationSupportImpl$TupleCompanionRef$ TupleCompanionRef$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnitClassRef$ UnitClassRef$module;
        private volatile ReificationSupport$ReificationSupportImpl$FunctionClassRef$ FunctionClassRef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTuple$ SyntacticTuple$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTupleType$ SyntacticTupleType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticFunctionType$ SyntacticFunctionType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntheticUnit$ SyntheticUnit$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticBlock$ SyntacticBlock$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticFunction$ SyntacticFunction$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticNew$ SyntacticNew$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticDefDef$ SyntacticDefDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticValDef$ SyntacticValDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticVarDef$ SyntacticVarDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticAssign$ SyntacticAssign$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticValFrom$ SyntacticValFrom$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticValEq$ SyntacticValEq$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticFilter$ SyntacticFilter$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticEmptyTypeTree$ SyntacticEmptyTypeTree$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnPatSeq$ UnPatSeq$module;
        private volatile ReificationSupport$ReificationSupportImpl$implodePatDefs$ implodePatDefs$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticPatDef$ SyntacticPatDef$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnPatSeqWithRes$ UnPatSeqWithRes$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnSyntheticParam$ UnSyntheticParam$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnVisitor$ UnVisitor$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnClosure$ UnClosure$module;
        private volatile ReificationSupport$ReificationSupportImpl$FilterCall$ FilterCall$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnFilter$ UnFilter$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnCheckIfRefutable$ UnCheckIfRefutable$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnMap$ UnMap$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnForeach$ UnForeach$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnFlatMap$ UnFlatMap$module;
        private volatile ReificationSupport$ReificationSupportImpl$UnFor$ UnFor$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticFor$ SyntacticFor$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticForYield$ SyntacticForYield$module;
        private volatile ReificationSupport$ReificationSupportImpl$MaybeTypeTreeOriginal$ MaybeTypeTreeOriginal$module;
        private volatile ReificationSupport$ReificationSupportImpl$MaybeSelectApply$ MaybeSelectApply$module;
        private volatile ReificationSupport$ReificationSupportImpl$MaybeUnchecked$ MaybeUnchecked$module;
        private volatile ReificationSupport$ReificationSupportImpl$MaybeTyped$ MaybeTyped$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticPartialFunction$ SyntacticPartialFunction$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticMatch$ SyntacticMatch$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTry$ SyntacticTry$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTermIdent$ SyntacticTermIdent$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTypeIdent$ SyntacticTypeIdent$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticImport$ SyntacticImport$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticSelectType$ SyntacticSelectType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticSelectTerm$ SyntacticSelectTerm$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticCompoundType$ SyntacticCompoundType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticSingletonType$ SyntacticSingletonType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticTypeProjection$ SyntacticTypeProjection$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticAnnotatedType$ SyntacticAnnotatedType$module;
        private volatile ReificationSupport$ReificationSupportImpl$SyntacticExistentialType$ SyntacticExistentialType$module;
        public final /* synthetic */ SymbolTable $outer;

        private ReificationSupport$ReificationSupportImpl$ScalaDot$ ScalaDot$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.ScalaDot$module == null) {
                    this.ScalaDot$module = new ReificationSupport$ReificationSupportImpl$ScalaDot$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ScalaDot$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$ImplicitParams$ ImplicitParams$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.ImplicitParams$module == null) {
                    this.ImplicitParams$module = new ReificationSupport$ReificationSupportImpl$ImplicitParams$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.ImplicitParams$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$FlagsRepr$ FlagsRepr$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.FlagsRepr$module == null) {
                    this.FlagsRepr$module = new ReificationSupport$ReificationSupportImpl$FlagsRepr$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FlagsRepr$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTypeApplied$ SyntacticTypeApplied$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTypeApplied$module == null) {
                    this.SyntacticTypeApplied$module = new ReificationSupport$ReificationSupportImpl$SyntacticTypeApplied$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTypeApplied$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticAppliedType$ SyntacticAppliedType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticAppliedType$module == null) {
                    this.SyntacticAppliedType$module = new ReificationSupport$ReificationSupportImpl$SyntacticAppliedType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticAppliedType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticApplied$ SyntacticApplied$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticApplied$module == null) {
                    this.SyntacticApplied$module = new ReificationSupport$ReificationSupportImpl$SyntacticApplied$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticApplied$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnCtor$ UnCtor$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnCtor$module == null) {
                    this.UnCtor$module = new ReificationSupport$ReificationSupportImpl$UnCtor$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnCtor$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnMkTemplate$ UnMkTemplate$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnMkTemplate$module == null) {
                    this.UnMkTemplate$module = new ReificationSupport$ReificationSupportImpl$UnMkTemplate$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnMkTemplate$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticClassDef$ SyntacticClassDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticClassDef$module == null) {
                    this.SyntacticClassDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticClassDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticClassDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTraitDef$ SyntacticTraitDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTraitDef$module == null) {
                    this.SyntacticTraitDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticTraitDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTraitDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticObjectDef$ SyntacticObjectDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticObjectDef$module == null) {
                    this.SyntacticObjectDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticObjectDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticObjectDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticPackageObjectDef$ SyntacticPackageObjectDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticPackageObjectDef$module == null) {
                    this.SyntacticPackageObjectDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticPackageObjectDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticPackageObjectDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$TupleClassRef$ TupleClassRef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.TupleClassRef$module == null) {
                    this.TupleClassRef$module = new ReificationSupport$ReificationSupportImpl$TupleClassRef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TupleClassRef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$TupleCompanionRef$ TupleCompanionRef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.TupleCompanionRef$module == null) {
                    this.TupleCompanionRef$module = new ReificationSupport$ReificationSupportImpl$TupleCompanionRef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.TupleCompanionRef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnitClassRef$ UnitClassRef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnitClassRef$module == null) {
                    this.UnitClassRef$module = new ReificationSupport$ReificationSupportImpl$UnitClassRef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnitClassRef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$FunctionClassRef$ FunctionClassRef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.FunctionClassRef$module == null) {
                    this.FunctionClassRef$module = new ReificationSupport$ReificationSupportImpl$FunctionClassRef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FunctionClassRef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTuple$ SyntacticTuple$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTuple$module == null) {
                    this.SyntacticTuple$module = new ReificationSupport$ReificationSupportImpl$SyntacticTuple$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTuple$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTupleType$ SyntacticTupleType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTupleType$module == null) {
                    this.SyntacticTupleType$module = new ReificationSupport$ReificationSupportImpl$SyntacticTupleType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTupleType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticFunctionType$ SyntacticFunctionType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticFunctionType$module == null) {
                    this.SyntacticFunctionType$module = new ReificationSupport$ReificationSupportImpl$SyntacticFunctionType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticFunctionType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntheticUnit$ SyntheticUnit$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntheticUnit$module == null) {
                    this.SyntheticUnit$module = new ReificationSupport$ReificationSupportImpl$SyntheticUnit$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntheticUnit$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticBlock$ SyntacticBlock$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticBlock$module == null) {
                    this.SyntacticBlock$module = new ReificationSupport$ReificationSupportImpl$SyntacticBlock$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticBlock$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticFunction$ SyntacticFunction$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticFunction$module == null) {
                    this.SyntacticFunction$module = new ReificationSupport$ReificationSupportImpl$SyntacticFunction$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticFunction$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticNew$ SyntacticNew$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticNew$module == null) {
                    this.SyntacticNew$module = new ReificationSupport$ReificationSupportImpl$SyntacticNew$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticNew$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticDefDef$ SyntacticDefDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticDefDef$module == null) {
                    this.SyntacticDefDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticDefDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticDefDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticValDef$ SyntacticValDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticValDef$module == null) {
                    this.SyntacticValDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticValDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticValDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticVarDef$ SyntacticVarDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticVarDef$module == null) {
                    this.SyntacticVarDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticVarDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticVarDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticAssign$ SyntacticAssign$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticAssign$module == null) {
                    this.SyntacticAssign$module = new ReificationSupport$ReificationSupportImpl$SyntacticAssign$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticAssign$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticValFrom$ SyntacticValFrom$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticValFrom$module == null) {
                    this.SyntacticValFrom$module = new ReificationSupport$ReificationSupportImpl$SyntacticValFrom$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticValFrom$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticValEq$ SyntacticValEq$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticValEq$module == null) {
                    this.SyntacticValEq$module = new ReificationSupport$ReificationSupportImpl$SyntacticValEq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticValEq$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticFilter$ SyntacticFilter$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticFilter$module == null) {
                    this.SyntacticFilter$module = new ReificationSupport$ReificationSupportImpl$SyntacticFilter$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticFilter$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticEmptyTypeTree$ SyntacticEmptyTypeTree$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticEmptyTypeTree$module == null) {
                    this.SyntacticEmptyTypeTree$module = new ReificationSupport$ReificationSupportImpl$SyntacticEmptyTypeTree$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticEmptyTypeTree$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnPatSeq$ UnPatSeq$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnPatSeq$module == null) {
                    this.UnPatSeq$module = new ReificationSupport$ReificationSupportImpl$UnPatSeq$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnPatSeq$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$implodePatDefs$ implodePatDefs$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.implodePatDefs$module == null) {
                    this.implodePatDefs$module = new ReificationSupport$ReificationSupportImpl$implodePatDefs$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.implodePatDefs$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticPatDef$ SyntacticPatDef$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticPatDef$module == null) {
                    this.SyntacticPatDef$module = new ReificationSupport$ReificationSupportImpl$SyntacticPatDef$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticPatDef$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnPatSeqWithRes$ UnPatSeqWithRes$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnPatSeqWithRes$module == null) {
                    this.UnPatSeqWithRes$module = new ReificationSupport$ReificationSupportImpl$UnPatSeqWithRes$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnPatSeqWithRes$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnSyntheticParam$ UnSyntheticParam$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnSyntheticParam$module == null) {
                    this.UnSyntheticParam$module = new ReificationSupport$ReificationSupportImpl$UnSyntheticParam$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnSyntheticParam$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnVisitor$ UnVisitor$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnVisitor$module == null) {
                    this.UnVisitor$module = new ReificationSupport$ReificationSupportImpl$UnVisitor$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnVisitor$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnClosure$ UnClosure$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnClosure$module == null) {
                    this.UnClosure$module = new ReificationSupport$ReificationSupportImpl$UnClosure$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnClosure$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$FilterCall$ FilterCall$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.FilterCall$module == null) {
                    this.FilterCall$module = new ReificationSupport$ReificationSupportImpl$FilterCall$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.FilterCall$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnFilter$ UnFilter$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnFilter$module == null) {
                    this.UnFilter$module = new ReificationSupport$ReificationSupportImpl$UnFilter$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnFilter$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnCheckIfRefutable$ UnCheckIfRefutable$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnCheckIfRefutable$module == null) {
                    this.UnCheckIfRefutable$module = new ReificationSupport$ReificationSupportImpl$UnCheckIfRefutable$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnCheckIfRefutable$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnMap$ UnMap$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnMap$module == null) {
                    this.UnMap$module = new ReificationSupport$ReificationSupportImpl$UnMap$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnMap$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnForeach$ UnForeach$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnForeach$module == null) {
                    this.UnForeach$module = new ReificationSupport$ReificationSupportImpl$UnForeach$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnForeach$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnFlatMap$ UnFlatMap$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnFlatMap$module == null) {
                    this.UnFlatMap$module = new ReificationSupport$ReificationSupportImpl$UnFlatMap$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnFlatMap$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$UnFor$ UnFor$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.UnFor$module == null) {
                    this.UnFor$module = new ReificationSupport$ReificationSupportImpl$UnFor$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.UnFor$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticFor$ SyntacticFor$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticFor$module == null) {
                    this.SyntacticFor$module = new ReificationSupport$ReificationSupportImpl$SyntacticFor$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticFor$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticForYield$ SyntacticForYield$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticForYield$module == null) {
                    this.SyntacticForYield$module = new ReificationSupport$ReificationSupportImpl$SyntacticForYield$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticForYield$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$MaybeTypeTreeOriginal$ MaybeTypeTreeOriginal$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.MaybeTypeTreeOriginal$module == null) {
                    this.MaybeTypeTreeOriginal$module = new ReificationSupport$ReificationSupportImpl$MaybeTypeTreeOriginal$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MaybeTypeTreeOriginal$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$MaybeSelectApply$ MaybeSelectApply$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.MaybeSelectApply$module == null) {
                    this.MaybeSelectApply$module = new ReificationSupport$ReificationSupportImpl$MaybeSelectApply$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MaybeSelectApply$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$MaybeUnchecked$ MaybeUnchecked$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.MaybeUnchecked$module == null) {
                    this.MaybeUnchecked$module = new ReificationSupport$ReificationSupportImpl$MaybeUnchecked$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MaybeUnchecked$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$MaybeTyped$ MaybeTyped$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.MaybeTyped$module == null) {
                    this.MaybeTyped$module = new ReificationSupport$ReificationSupportImpl$MaybeTyped$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.MaybeTyped$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticPartialFunction$ SyntacticPartialFunction$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticPartialFunction$module == null) {
                    this.SyntacticPartialFunction$module = new ReificationSupport$ReificationSupportImpl$SyntacticPartialFunction$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticPartialFunction$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticMatch$ SyntacticMatch$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticMatch$module == null) {
                    this.SyntacticMatch$module = new ReificationSupport$ReificationSupportImpl$SyntacticMatch$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticMatch$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTry$ SyntacticTry$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTry$module == null) {
                    this.SyntacticTry$module = new ReificationSupport$ReificationSupportImpl$SyntacticTry$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTry$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTermIdent$ SyntacticTermIdent$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTermIdent$module == null) {
                    this.SyntacticTermIdent$module = new ReificationSupport$ReificationSupportImpl$SyntacticTermIdent$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTermIdent$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTypeIdent$ SyntacticTypeIdent$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTypeIdent$module == null) {
                    this.SyntacticTypeIdent$module = new ReificationSupport$ReificationSupportImpl$SyntacticTypeIdent$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTypeIdent$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticImport$ SyntacticImport$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticImport$module == null) {
                    this.SyntacticImport$module = new ReificationSupport$ReificationSupportImpl$SyntacticImport$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticImport$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticSelectType$ SyntacticSelectType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticSelectType$module == null) {
                    this.SyntacticSelectType$module = new ReificationSupport$ReificationSupportImpl$SyntacticSelectType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticSelectType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticSelectTerm$ SyntacticSelectTerm$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticSelectTerm$module == null) {
                    this.SyntacticSelectTerm$module = new ReificationSupport$ReificationSupportImpl$SyntacticSelectTerm$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticSelectTerm$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticCompoundType$ SyntacticCompoundType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticCompoundType$module == null) {
                    this.SyntacticCompoundType$module = new ReificationSupport$ReificationSupportImpl$SyntacticCompoundType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticCompoundType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticSingletonType$ SyntacticSingletonType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticSingletonType$module == null) {
                    this.SyntacticSingletonType$module = new ReificationSupport$ReificationSupportImpl$SyntacticSingletonType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticSingletonType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticTypeProjection$ SyntacticTypeProjection$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticTypeProjection$module == null) {
                    this.SyntacticTypeProjection$module = new ReificationSupport$ReificationSupportImpl$SyntacticTypeProjection$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticTypeProjection$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticAnnotatedType$ SyntacticAnnotatedType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticAnnotatedType$module == null) {
                    this.SyntacticAnnotatedType$module = new ReificationSupport$ReificationSupportImpl$SyntacticAnnotatedType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticAnnotatedType$module;
            }
        }

        private ReificationSupport$ReificationSupportImpl$SyntacticExistentialType$ SyntacticExistentialType$lzycompute() {
            ReificationSupportImpl reificationSupportImpl = this;
            synchronized (reificationSupportImpl) {
                if (this.SyntacticExistentialType$module == null) {
                    this.SyntacticExistentialType$module = new ReificationSupport$ReificationSupportImpl$SyntacticExistentialType$(this);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.SyntacticExistentialType$module;
            }
        }

        public Symbols.TypeSymbol selectType(Symbols.Symbol owner2, String name) {
            return (Symbols.TypeSymbol)this.select(owner2, this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newTypeName(name)).asType();
        }

        public Symbols.TermSymbol selectTerm(Symbols.Symbol owner2, String name) {
            Symbols.TermSymbol result2 = (Symbols.TermSymbol)this.select(owner2, this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newTermName(name)).asTerm();
            return result2.isOverloaded() ? (Symbols.TermSymbol)((Symbols.SymbolContextApiImpl)result2.suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbols.Symbol x$1) {
                    return !x$1.isMethod();
                }
            }))).asTerm() : result2;
        }

        public Symbols.Symbol select(Symbols.Symbol owner2, Names.Name name) {
            block4: {
                Symbols.Symbol symbol;
                block3: {
                    block2: {
                        Symbols.Symbol result2 = owner2.info().decl(name);
                        if (result2 == this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().NoSymbol()) break block2;
                        symbol = result2;
                        break block3;
                    }
                    Symbols.Symbol symbol2 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().mirrorThatLoaded(owner2).missingHook(owner2, name);
                    if (symbol2 == symbol2.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) break block4;
                    symbol = symbol2;
                }
                return symbol;
            }
            Predef$ predef$ = Predef$.MODULE$;
            throw new ScalaReflectionException(new StringOps("%s %s in %s not found").format(Predef$.MODULE$.genericWrapArray(new Object[]{name.isTermName() ? "term" : "type", name, owner2.fullName('.')})));
        }

        public Symbols.MethodSymbol selectOverloadedMethod(Symbols.Symbol owner2, String name, int index) {
            Symbols.Symbol result2 = owner2.info().decl(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newTermName(name)).alternatives().apply(index);
            if (result2 != this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().NoSymbol()) {
                return (Symbols.MethodSymbol)result2.asMethod();
            }
            Predef$ predef$ = Predef$.MODULE$;
            throw new ScalaReflectionException(new StringOps("overloaded method %s #%d in %s not found").format(Predef$.MODULE$.genericWrapArray(new Object[]{name, BoxesRunTime.boxToInteger(index), owner2.fullName()})));
        }

        public Symbols.FreeTermSymbol newFreeTerm(String name, Function0<Object> value, long flags, String origin) {
            return (Symbols.FreeTermSymbol)this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newFreeTermSymbol(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newTermName(name), value, flags, origin).markFlagsCompleted(-1L);
        }

        public long newFreeTerm$default$3() {
            return 0L;
        }

        @Override
        public String newFreeTerm$default$4() {
            return null;
        }

        public Symbols.FreeTypeSymbol newFreeType(String name, long flags, String origin) {
            return (Symbols.FreeTypeSymbol)this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newFreeTypeSymbol(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newTypeName(name), flags, origin).markFlagsCompleted(-1L);
        }

        public long newFreeType$default$2() {
            return 0L;
        }

        @Override
        public String newFreeType$default$3() {
            return null;
        }

        public Symbols.Symbol newNestedSymbol(Symbols.Symbol owner2, Names.Name name, Position pos, long flags, boolean isClass) {
            return owner2.newNestedSymbol(name, pos, flags, isClass).markFlagsCompleted(-1L);
        }

        public Scopes.Scope newScopeWith(Seq<Symbols.Symbol> elems) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().newScopeWith(elems);
        }

        public <S extends Symbols.Symbol> S setAnnotations(S sym, List<AnnotationInfos.AnnotationInfo> annots) {
            return (S)sym.setAnnotations((List)annots);
        }

        public <S extends Symbols.Symbol> S setInfo(S sym, Types.Type tpe) {
            return (S)sym.setInfo(tpe).markAllCompleted();
        }

        public Trees.Tree mkThis(Symbols.Symbol sym) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().This(sym);
        }

        public Trees.Select mkSelect(Trees.Tree qualifier, Symbols.Symbol sym) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().Select(qualifier, sym);
        }

        public Trees.Ident mkIdent(Symbols.Symbol sym) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().Ident(sym);
        }

        public Trees.TypeTree mkTypeTree(Types.Type tp) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().TypeTree(tp);
        }

        public Types.Type ThisType(Symbols.Symbol sym) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().ThisType().apply(sym);
        }

        public Types.Type SingleType(Types.Type pre, Symbols.Symbol sym) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().SingleType().apply(pre, sym);
        }

        public Types.Type SuperType(Types.Type thistpe, Types.Type supertpe) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().SuperType().apply(thistpe, supertpe);
        }

        public Types.ConstantType ConstantType(Constants.Constant value) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().ConstantType().apply(value);
        }

        public Types.Type TypeRef(Types.Type pre, Symbols.Symbol sym, List<Types.Type> args) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().TypeRef().apply(pre, sym, args);
        }

        public Types.RefinedType RefinedType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol typeSymbol2) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().RefinedType().apply(parents2, decls, typeSymbol2);
        }

        public Types.ClassInfoType ClassInfoType(List<Types.Type> parents2, Scopes.Scope decls, Symbols.Symbol typeSymbol2) {
            return new Types.ClassInfoType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), parents2, decls, typeSymbol2);
        }

        public Types.MethodType MethodType(List<Symbols.Symbol> params2, Types.Type resultType) {
            return new Types.MethodType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), params2, resultType);
        }

        public Types.NullaryMethodType NullaryMethodType(Types.Type resultType) {
            return new Types.NullaryMethodType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), resultType);
        }

        public Types.PolyType PolyType(List<Symbols.Symbol> typeParams2, Types.Type resultType) {
            return new Types.PolyType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), typeParams2, resultType);
        }

        public Types.ExistentialType ExistentialType(List<Symbols.Symbol> quantified, Types.Type underlying) {
            return new Types.ExistentialType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), quantified, underlying);
        }

        public Types.AnnotatedType AnnotatedType(List<AnnotationInfos.AnnotationInfo> annotations, Types.Type underlying) {
            return new Types.AnnotatedType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), annotations, underlying);
        }

        public Types.TypeBounds TypeBounds(Types.Type lo, Types.Type hi) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().TypeBounds().apply(lo, hi);
        }

        public Types.BoundedWildcardType BoundedWildcardType(Types.TypeBounds bounds) {
            return new Types.BoundedWildcardType(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), bounds);
        }

        public Types.Type thisPrefix(Symbols.Symbol sym) {
            return sym.thisPrefix();
        }

        public <T extends Trees.Tree> T setType(T tree, Types.Type tpe) {
            tree.setType(tpe);
            return tree;
        }

        public <T extends Trees.Tree> T setSymbol(T tree, Symbols.Symbol sym) {
            tree.setSymbol(sym);
            return tree;
        }

        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public List<Trees.Tree> toStats(Trees.Tree tree) {
            void var4_6;
            if (((Object)this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().EmptyTree()).equals(tree)) {
                Nil$ nil$ = Nil$.MODULE$;
                return var4_6;
            } else {
                Option<List<Trees.Tree>> option = this.SyntacticBlock().unapply(tree);
                if (option.isEmpty()) {
                    if (tree.isDef()) {
                        List<Trees.Tree> list2 = Nil$.MODULE$.$colon$colon(tree);
                        return var4_6;
                    } else {
                        if (!(tree instanceof Trees.Import)) throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"can't flatten ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tree})));
                        Trees.Import import_ = (Trees.Import)tree;
                        List<Trees.Import> list3 = Nil$.MODULE$.$colon$colon(import_);
                    }
                    return var4_6;
                } else {
                    List<Trees.Tree> list4 = option.get();
                }
            }
            return var4_6;
        }

        public Trees.Tree mkAnnotation(Trees.Tree tree) {
            Option<Tuple4<List<Trees.Tree>, List<Trees.Tree>, Trees.ValDef, List<Trees.Tree>>> option = this.SyntacticNew().unapply(tree);
            if (!option.isEmpty() && ((Object)Nil$.MODULE$).equals(option.get()._1()) && option.get()._2() instanceof $colon$colon) {
                Option<Tuple2<Trees.Tree, List<Trees.Tree>>> option2;
                $colon$colon $colon$colon = ($colon$colon)option.get()._2();
                Some<Tuple2<Trees.Tree, List<List<Trees.Tree>>>> some = this.SyntacticApplied().unapply((Trees.Tree)$colon$colon.head());
                if (!some.isEmpty() && !(option2 = this.SyntacticAppliedType().unapply(some.get()._1())).isEmpty() && ((Object)Nil$.MODULE$).equals($colon$colon.tl$1()) && ((Object)Nil$.MODULE$).equals(option.get()._4())) {
                    return tree;
                }
            }
            throw new IllegalArgumentException(new StringBuilder().append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Tree ", " isn't a correct representation of annotation."})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw(tree, this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$2(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$3(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$4(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$5(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$6(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().showRaw$default$7())}))).append((Object)"Consider reformatting it into a q\"new $name[..$targs](...$argss)\" shape").toString());
        }

        public List<Trees.Tree> mkAnnotation(List<Trees.Tree> trees) {
            return trees.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.Tree apply(Trees.Tree tree) {
                    return this.$outer.mkAnnotation(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public List<List<Trees.ValDef>> mkParam(List<List<Trees.Tree>> argss, long extraFlags, long excludeFlags) {
            return argss.map(new Serializable(this, extraFlags, excludeFlags){
                public static final long serialVersionUID = 0L;
                public final /* synthetic */ ReificationSupportImpl $outer;
                public final long extraFlags$1;
                public final long excludeFlags$1;

                public final List<Trees.ValDef> apply(List<Trees.Tree> args) {
                    return args.map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ReificationSupportImpl$$anonfun$mkParam$1 $outer;

                        public final Trees.ValDef apply(Trees.Tree x$4) {
                            return this.$outer.$outer.mkParam(x$4, this.$outer.extraFlags$1, this.$outer.excludeFlags$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                }

                public /* synthetic */ ReificationSupportImpl scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$anonfun$$$outer() {
                    return this.$outer;
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.extraFlags$1 = extraFlags$1;
                    this.excludeFlags$1 = excludeFlags$1;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Trees.ValDef mkParam(Trees.Tree tree, long extraFlags, long excludeFlags) {
            block6: {
                Trees.ValDef valDef;
                block5: {
                    block4: {
                        Trees.Ident ident;
                        Trees.Typed typed;
                        if (!(tree instanceof Trees.Typed) || !((typed = (Trees.Typed)tree).expr() instanceof Trees.Ident) || !((ident = (Trees.Ident)typed.expr()).name() instanceof Names.TermName)) break block4;
                        Names.TermName termName = (Names.TermName)ident.name();
                        valDef = this.mkParam(new Trees.ValDef(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), (Trees.Modifiers)this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().NoMods(), termName, typed.tpt(), this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().EmptyTree()), extraFlags, excludeFlags);
                        break block5;
                    }
                    if (!(tree instanceof Trees.ValDef)) break block6;
                    Trees.ValDef valDef2 = (Trees.ValDef)tree;
                    Trees.Modifiers newmods = valDef2.mods().$amp(excludeFlags ^ 0xFFFFFFFFFFFFFFFFL);
                    if (valDef2.rhs().nonEmpty()) {
                        newmods = newmods.$bar(0x2000000);
                    }
                    Trees.Modifiers x$40 = newmods.$bar(extraFlags);
                    Names.Name x$41 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$3(valDef2);
                    Trees.Tree x$42 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$4(valDef2);
                    Trees.Tree x$43 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$5(valDef2);
                    valDef = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef(valDef2, x$40, x$41, x$42, x$43);
                }
                return valDef;
            }
            throw new IllegalArgumentException(new StringBuilder().append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not valid representation of a parameter, "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tree}))).append((Object)"consider reformatting it into q\"val $name: $T = $default\" shape").toString());
        }

        public long mkParam$default$2() {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().NoFlags();
        }

        public long mkParam$default$3() {
            return 16L;
        }

        public List<Trees.ValDef> mkImplicitParam(List<Trees.Tree> args) {
            return args.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.ValDef apply(Trees.Tree tree) {
                    return this.$outer.mkImplicitParam(tree);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Trees.ValDef mkImplicitParam(Trees.Tree tree) {
            return this.mkParam(tree, 8704L, this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().NoFlags());
        }

        public List<Trees.TypeDef> mkTparams(List<Trees.Tree> tparams2) {
            return tparams2.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.TypeDef apply(Trees.Tree x0$1) {
                    if (x0$1 instanceof Trees.TypeDef) {
                        Trees.TypeDef typeDef = (Trees.TypeDef)x0$1;
                        Trees.Modifiers x$45 = typeDef.mods().$bar(8192).$amp(-17L);
                        Names.Name x$46 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$3(typeDef);
                        List<Trees.TypeDef> x$47 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$4(typeDef);
                        Trees.Tree x$48 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$5(typeDef);
                        return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef(typeDef, x$45, x$46, x$47, x$48);
                    }
                    throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"can't splice ", " as type parameter"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{x0$1})));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Trees.Tree mkRefineStat(Trees.Tree stat) {
            block9: {
                block7: {
                    block8: {
                        block6: {
                            if (!(stat instanceof Trees.DefDef)) break block6;
                            Trees.DefDef defDef = (Trees.DefDef)stat;
                            boolean bl = defDef.rhs().isEmpty();
                            Predef$ predef$ = Predef$.MODULE$;
                            if (!bl) {
                                throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"can't use DefDef with non-empty body as refine stat").toString());
                            }
                            break block7;
                        }
                        if (!(stat instanceof Trees.ValDef)) break block8;
                        Trees.ValDef valDef = (Trees.ValDef)stat;
                        boolean bl = valDef.rhs().isEmpty();
                        Predef$ predef$ = Predef$.MODULE$;
                        if (!bl) {
                            throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"can't use ValDef with non-empty rhs as refine stat").toString());
                        }
                        break block7;
                    }
                    if (!(stat instanceof Trees.TypeDef)) break block9;
                }
                return stat;
            }
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"not legal refine stat: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{stat})));
        }

        public List<Trees.Tree> mkRefineStat(List<Trees.Tree> stats) {
            return stats.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.Tree apply(Trees.Tree stat) {
                    return this.$outer.mkRefineStat(stat);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Trees.Tree mkPackageStat(Trees.Tree stat) {
            block5: {
                block3: {
                    block4: {
                        block2: {
                            if (!(stat instanceof Trees.ClassDef)) break block2;
                            break block3;
                        }
                        if (!(stat instanceof Trees.ModuleDef)) break block4;
                        break block3;
                    }
                    if (!(stat instanceof Trees.PackageDef)) break block5;
                }
                return stat;
            }
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"not legal package stat: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{stat})));
        }

        public List<Trees.Tree> mkPackageStat(List<Trees.Tree> stats) {
            return stats.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.Tree apply(Trees.Tree stat) {
                    return this.$outer.mkPackageStat(stat);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$ScalaDot$ ScalaDot() {
            return this.ScalaDot$module == null ? this.ScalaDot$lzycompute() : this.ScalaDot$module;
        }

        public Trees.Tree mkEarlyDef(Trees.Tree defn) {
            block4: {
                Trees.MemberDef memberDef;
                block3: {
                    block2: {
                        Trees.ValDef valDef;
                        if (!(defn instanceof Trees.ValDef) || (valDef = (Trees.ValDef)defn).mods().isDeferred()) break block2;
                        Trees.Modifiers x$50 = valDef.mods().$bar(0x2000000000L);
                        Names.Name x$51 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$3(valDef);
                        Trees.Tree x$52 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$4(valDef);
                        Trees.Tree x$53 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$5(valDef);
                        memberDef = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef(valDef, x$50, x$51, x$52, x$53);
                        break block3;
                    }
                    if (!(defn instanceof Trees.TypeDef)) break block4;
                    Trees.TypeDef typeDef = (Trees.TypeDef)defn;
                    Trees.Modifiers x$55 = typeDef.mods().$bar(0x2000000000L);
                    Names.Name x$56 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$3(typeDef);
                    List<Trees.TypeDef> x$57 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$4(typeDef);
                    Trees.Tree x$58 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef$default$5(typeDef);
                    memberDef = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyTypeDef(typeDef, x$55, x$56, x$57, x$58);
                }
                return memberDef;
            }
            throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"not legal early def: ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{defn})));
        }

        public List<Trees.Tree> mkEarlyDef(List<Trees.Tree> defns) {
            return defns.map(new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ ReificationSupportImpl $outer;

                public final Trees.Tree apply(Trees.Tree defn) {
                    return this.$outer.mkEarlyDef(defn);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }, List$.MODULE$.canBuildFrom());
        }

        public Trees.RefTree mkRefTree(Trees.Tree qual, Symbols.Symbol sym) {
            return (Trees.RefTree)((Object)((Trees.Tree)((Object)this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().RefTree().apply(qual, sym.name()))).setSymbol(sym));
        }

        @Override
        public Names.TermName freshTermName(String prefix) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().freshTermName(prefix, this.fresh());
        }

        @Override
        public Names.TypeName freshTypeName(String prefix) {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().freshTypeName(prefix, this.fresh());
        }

        public FreshNameCreator fresh() {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().currentFreshNameCreator();
        }

        public String freshTermName$default$1() {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().FRESH_TERM_NAME_PREFIX();
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$ImplicitParams$ ImplicitParams() {
            return this.ImplicitParams$module == null ? this.ImplicitParams$lzycompute() : this.ImplicitParams$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$FlagsRepr$ FlagsRepr() {
            return this.FlagsRepr$module == null ? this.FlagsRepr$lzycompute() : this.FlagsRepr$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTypeApplied$ SyntacticTypeApplied() {
            return this.SyntacticTypeApplied$module == null ? this.SyntacticTypeApplied$lzycompute() : this.SyntacticTypeApplied$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticAppliedType$ SyntacticAppliedType() {
            return this.SyntacticAppliedType$module == null ? this.SyntacticAppliedType$lzycompute() : this.SyntacticAppliedType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticApplied$ SyntacticApplied() {
            return this.SyntacticApplied$module == null ? this.SyntacticApplied$lzycompute() : this.SyntacticApplied$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnCtor$ UnCtor() {
            return this.UnCtor$module == null ? this.UnCtor$lzycompute() : this.UnCtor$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnMkTemplate$ UnMkTemplate() {
            return this.UnMkTemplate$module == null ? this.UnMkTemplate$lzycompute() : this.UnMkTemplate$module;
        }

        public Trees.ValDef mkSelfType(Trees.Tree tree) {
            if (tree instanceof Trees.ValDef) {
                Trees.ValDef valDef = (Trees.ValDef)tree;
                boolean bl = valDef.rhs().isEmpty();
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"self types must have empty right hand side").toString());
                }
                Trees.Modifiers x$65 = valDef.mods().$bar(4).$amp(-17L);
                Names.Name x$66 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$3(valDef);
                Trees.Tree x$67 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$4(valDef);
                Trees.Tree x$68 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef$default$5(valDef);
                return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().copyValDef(valDef, x$65, x$66, x$67, x$68);
            }
            throw new IllegalArgumentException(new StringBuilder().append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a valid representation of self type, "})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tree}))).append((Object)"consider reformatting into q\"val $self: $T\" shape").toString());
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticClassDef$ SyntacticClassDef() {
            return this.SyntacticClassDef$module == null ? this.SyntacticClassDef$lzycompute() : this.SyntacticClassDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTraitDef$ SyntacticTraitDef() {
            return this.SyntacticTraitDef$module == null ? this.SyntacticTraitDef$lzycompute() : this.SyntacticTraitDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticObjectDef$ SyntacticObjectDef() {
            return this.SyntacticObjectDef$module == null ? this.SyntacticObjectDef$lzycompute() : this.SyntacticObjectDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticPackageObjectDef$ SyntacticPackageObjectDef() {
            return this.SyntacticPackageObjectDef$module == null ? this.SyntacticPackageObjectDef$lzycompute() : this.SyntacticPackageObjectDef$module;
        }

        public ReificationSupport$ReificationSupportImpl$TupleClassRef$ TupleClassRef() {
            return this.TupleClassRef$module == null ? this.TupleClassRef$lzycompute() : this.TupleClassRef$module;
        }

        public ReificationSupport$ReificationSupportImpl$TupleCompanionRef$ TupleCompanionRef() {
            return this.TupleCompanionRef$module == null ? this.TupleCompanionRef$lzycompute() : this.TupleCompanionRef$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnitClassRef$ UnitClassRef() {
            return this.UnitClassRef$module == null ? this.UnitClassRef$lzycompute() : this.UnitClassRef$module;
        }

        public ReificationSupport$ReificationSupportImpl$FunctionClassRef$ FunctionClassRef() {
            return this.FunctionClassRef$module == null ? this.FunctionClassRef$lzycompute() : this.FunctionClassRef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTuple$ SyntacticTuple() {
            return this.SyntacticTuple$module == null ? this.SyntacticTuple$lzycompute() : this.SyntacticTuple$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTupleType$ SyntacticTupleType() {
            return this.SyntacticTupleType$module == null ? this.SyntacticTupleType$lzycompute() : this.SyntacticTupleType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticFunctionType$ SyntacticFunctionType() {
            return this.SyntacticFunctionType$module == null ? this.SyntacticFunctionType$lzycompute() : this.SyntacticFunctionType$module;
        }

        public ReificationSupport$ReificationSupportImpl$SyntheticUnit$ SyntheticUnit() {
            return this.SyntheticUnit$module == null ? this.SyntheticUnit$lzycompute() : this.SyntheticUnit$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticBlock$ SyntacticBlock() {
            return this.SyntacticBlock$module == null ? this.SyntacticBlock$lzycompute() : this.SyntacticBlock$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticFunction$ SyntacticFunction() {
            return this.SyntacticFunction$module == null ? this.SyntacticFunction$lzycompute() : this.SyntacticFunction$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticNew$ SyntacticNew() {
            return this.SyntacticNew$module == null ? this.SyntacticNew$lzycompute() : this.SyntacticNew$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticDefDef$ SyntacticDefDef() {
            return this.SyntacticDefDef$module == null ? this.SyntacticDefDef$lzycompute() : this.SyntacticDefDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticValDef$ SyntacticValDef() {
            return this.SyntacticValDef$module == null ? this.SyntacticValDef$lzycompute() : this.SyntacticValDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticVarDef$ SyntacticVarDef() {
            return this.SyntacticVarDef$module == null ? this.SyntacticVarDef$lzycompute() : this.SyntacticVarDef$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticAssign$ SyntacticAssign() {
            return this.SyntacticAssign$module == null ? this.SyntacticAssign$lzycompute() : this.SyntacticAssign$module;
        }

        public <T> Object UnliftListElementwise(Liftables.Unliftable<T> unliftable) {
            return new Internals.ReificationSupportApi.UnliftListElementwise<T>(this, unliftable){
                public final Liftables.Unliftable unliftable$1;

                public Option<List<T>> unapply(List<Trees.Tree> lst) {
                    List<A> unlifted = lst.flatMap(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ ReificationSupportImpl$$anon$1 $outer;

                        public final Iterable<T> apply(Trees.Tree x$23) {
                            return Option$.MODULE$.option2Iterable(this.$outer.unliftable$1.unapply(x$23));
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    return unlifted.length() == lst.length() ? new Some<List<A>>(unlifted) : None$.MODULE$;
                }
                {
                    this.unliftable$1 = unliftable$1;
                }
            };
        }

        public <T> Object UnliftListOfListsElementwise(Liftables.Unliftable<T> unliftable) {
            return new Internals.ReificationSupportApi.UnliftListOfListsElementwise<T>(this, unliftable){
                public final Liftables.Unliftable unliftable$2;

                public Option<List<List<T>>> unapply(List<List<Trees.Tree>> lst) {
                    List<A> unlifted = lst.map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        public final /* synthetic */ ReificationSupportImpl$$anon$2 $outer;

                        public final List<T> apply(List<Trees.Tree> l) {
                            return l.flatMap(new Serializable(this){
                                public static final long serialVersionUID = 0L;
                                private final /* synthetic */ ReificationSupportImpl$$anon$2$$anonfun$8 $outer;

                                public final Iterable<T> apply(Trees.Tree x$24) {
                                    return Option$.MODULE$.option2Iterable(this.$outer.$outer.unliftable$2.unapply(x$24));
                                }
                                {
                                    if ($outer == null) {
                                        throw null;
                                    }
                                    this.$outer = $outer;
                                }
                            }, List$.MODULE$.canBuildFrom());
                        }

                        public /* synthetic */ ReificationSupportImpl$$anon$2 scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$anon$$anonfun$$$outer() {
                            return this.$outer;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    return ((LinearSeqOptimized)((Object)unlifted.flatten(Predef$.MODULE$.$conforms()))).length() == ((LinearSeqOptimized)((Object)lst.flatten(Predef$.MODULE$.$conforms()))).length() ? new Some<List<A>>(unlifted) : None$.MODULE$;
                }
                {
                    this.unliftable$2 = unliftable$2;
                }
            };
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticValFrom$ SyntacticValFrom() {
            return this.SyntacticValFrom$module == null ? this.SyntacticValFrom$lzycompute() : this.SyntacticValFrom$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticValEq$ SyntacticValEq() {
            return this.SyntacticValEq$module == null ? this.SyntacticValEq$lzycompute() : this.SyntacticValEq$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticFilter$ SyntacticFilter() {
            return this.SyntacticFilter$module == null ? this.SyntacticFilter$lzycompute() : this.SyntacticFilter$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticEmptyTypeTree$ SyntacticEmptyTypeTree() {
            return this.SyntacticEmptyTypeTree$module == null ? this.SyntacticEmptyTypeTree$lzycompute() : this.SyntacticEmptyTypeTree$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnPatSeq$ UnPatSeq() {
            return this.UnPatSeq$module == null ? this.UnPatSeq$lzycompute() : this.UnPatSeq$module;
        }

        public ReificationSupport$ReificationSupportImpl$implodePatDefs$ implodePatDefs() {
            return this.implodePatDefs$module == null ? this.implodePatDefs$lzycompute() : this.implodePatDefs$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticPatDef$ SyntacticPatDef() {
            return this.SyntacticPatDef$module == null ? this.SyntacticPatDef$lzycompute() : this.SyntacticPatDef$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnPatSeqWithRes$ UnPatSeqWithRes() {
            return this.UnPatSeqWithRes$module == null ? this.UnPatSeqWithRes$lzycompute() : this.UnPatSeqWithRes$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnSyntheticParam$ UnSyntheticParam() {
            return this.UnSyntheticParam$module == null ? this.UnSyntheticParam$lzycompute() : this.UnSyntheticParam$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnVisitor$ UnVisitor() {
            return this.UnVisitor$module == null ? this.UnVisitor$lzycompute() : this.UnVisitor$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnClosure$ UnClosure() {
            return this.UnClosure$module == null ? this.UnClosure$lzycompute() : this.UnClosure$module;
        }

        public ReificationSupport$ReificationSupportImpl$FilterCall$ FilterCall() {
            return this.FilterCall$module == null ? this.FilterCall$lzycompute() : this.FilterCall$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnFilter$ UnFilter() {
            return this.UnFilter$module == null ? this.UnFilter$lzycompute() : this.UnFilter$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnCheckIfRefutable$ UnCheckIfRefutable() {
            return this.UnCheckIfRefutable$module == null ? this.UnCheckIfRefutable$lzycompute() : this.UnCheckIfRefutable$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnMap$ UnMap() {
            return this.UnMap$module == null ? this.UnMap$lzycompute() : this.UnMap$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnForeach$ UnForeach() {
            return this.UnForeach$module == null ? this.UnForeach$lzycompute() : this.UnForeach$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnFlatMap$ UnFlatMap() {
            return this.UnFlatMap$module == null ? this.UnFlatMap$lzycompute() : this.UnFlatMap$module;
        }

        public ReificationSupport$ReificationSupportImpl$UnFor$ UnFor() {
            return this.UnFor$module == null ? this.UnFor$lzycompute() : this.UnFor$module;
        }

        public List<Trees.Tree> mkEnumerators(List<Trees.Tree> enums) {
            boolean bl = enums.nonEmpty();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new IllegalArgumentException(new StringBuilder().append((Object)"requirement failed: ").append((Object)"enumerators can't be empty").toString());
            }
            Trees.Tree tree = enums.head();
            Option<Tuple2<Trees.Tree, Trees.Tree>> option = this.SyntacticValFrom().unapply(tree);
            if (option.isEmpty()) {
                throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a valid first enumerator of for loop"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tree})));
            }
            List list2 = (List)enums.tail();
            while (!((AbstractIterable)list2).isEmpty()) {
                Option<Trees.Tree> o211;
                Option<Tuple2<Trees.Tree, Trees.Tree>> o191;
                Trees.Tree tree2 = (Trees.Tree)((AbstractIterable)list2).head();
                Option<Tuple2<Trees.Tree, Trees.Tree>> o171 = this.SyntacticValEq().$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().gen().ValEq().unapply(tree2);
                boolean bl2 = o171.isEmpty() ? ((o191 = this.SyntacticValFrom().unapply(tree2)).isEmpty() ? !(o211 = this.SyntacticFilter().$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().gen().Filter().unapply(tree2)).isEmpty() : true) : true;
                if (bl2) {
                    list2 = (List)list2.tail();
                    continue;
                }
                throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not a valid representation of a for loop enumerator"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{tree2})));
            }
            return enums;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticFor$ SyntacticFor() {
            return this.SyntacticFor$module == null ? this.SyntacticFor$lzycompute() : this.SyntacticFor$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticForYield$ SyntacticForYield() {
            return this.SyntacticForYield$module == null ? this.SyntacticForYield$lzycompute() : this.SyntacticForYield$module;
        }

        public ReificationSupport$ReificationSupportImpl$MaybeTypeTreeOriginal$ MaybeTypeTreeOriginal() {
            return this.MaybeTypeTreeOriginal$module == null ? this.MaybeTypeTreeOriginal$lzycompute() : this.MaybeTypeTreeOriginal$module;
        }

        public ReificationSupport$ReificationSupportImpl$MaybeSelectApply$ MaybeSelectApply() {
            return this.MaybeSelectApply$module == null ? this.MaybeSelectApply$lzycompute() : this.MaybeSelectApply$module;
        }

        public ReificationSupport$ReificationSupportImpl$MaybeUnchecked$ MaybeUnchecked() {
            return this.MaybeUnchecked$module == null ? this.MaybeUnchecked$lzycompute() : this.MaybeUnchecked$module;
        }

        public ReificationSupport$ReificationSupportImpl$MaybeTyped$ MaybeTyped() {
            return this.MaybeTyped$module == null ? this.MaybeTyped$lzycompute() : this.MaybeTyped$module;
        }

        public List<Trees.CaseDef> mkCases(List<Trees.Tree> cases) {
            return cases.map(new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final Trees.CaseDef apply(Trees.Tree x0$10) {
                    if (x0$10 instanceof Trees.CaseDef) {
                        Trees.CaseDef caseDef = (Trees.CaseDef)x0$10;
                        return caseDef;
                    }
                    throw new IllegalArgumentException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " is not valid representation of pattern match case"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{x0$10})));
                }
            }, List$.MODULE$.canBuildFrom());
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticPartialFunction$ SyntacticPartialFunction() {
            return this.SyntacticPartialFunction$module == null ? this.SyntacticPartialFunction$lzycompute() : this.SyntacticPartialFunction$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticMatch$ SyntacticMatch() {
            return this.SyntacticMatch$module == null ? this.SyntacticMatch$lzycompute() : this.SyntacticMatch$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTry$ SyntacticTry() {
            return this.SyntacticTry$module == null ? this.SyntacticTry$lzycompute() : this.SyntacticTry$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTermIdent$ SyntacticTermIdent() {
            return this.SyntacticTermIdent$module == null ? this.SyntacticTermIdent$lzycompute() : this.SyntacticTermIdent$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTypeIdent$ SyntacticTypeIdent() {
            return this.SyntacticTypeIdent$module == null ? this.SyntacticTypeIdent$lzycompute() : this.SyntacticTypeIdent$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticImport$ SyntacticImport() {
            return this.SyntacticImport$module == null ? this.SyntacticImport$lzycompute() : this.SyntacticImport$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticSelectType$ SyntacticSelectType() {
            return this.SyntacticSelectType$module == null ? this.SyntacticSelectType$lzycompute() : this.SyntacticSelectType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticSelectTerm$ SyntacticSelectTerm() {
            return this.SyntacticSelectTerm$module == null ? this.SyntacticSelectTerm$lzycompute() : this.SyntacticSelectTerm$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticCompoundType$ SyntacticCompoundType() {
            return this.SyntacticCompoundType$module == null ? this.SyntacticCompoundType$lzycompute() : this.SyntacticCompoundType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticSingletonType$ SyntacticSingletonType() {
            return this.SyntacticSingletonType$module == null ? this.SyntacticSingletonType$lzycompute() : this.SyntacticSingletonType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticTypeProjection$ SyntacticTypeProjection() {
            return this.SyntacticTypeProjection$module == null ? this.SyntacticTypeProjection$lzycompute() : this.SyntacticTypeProjection$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticAnnotatedType$ SyntacticAnnotatedType() {
            return this.SyntacticAnnotatedType$module == null ? this.SyntacticAnnotatedType$lzycompute() : this.SyntacticAnnotatedType$module;
        }

        @Override
        public ReificationSupport$ReificationSupportImpl$SyntacticExistentialType$ SyntacticExistentialType() {
            return this.SyntacticExistentialType$module == null ? this.SyntacticExistentialType$lzycompute() : this.SyntacticExistentialType$module;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer() {
            return this.$outer;
        }

        @Override
        public /* synthetic */ Internals scala$reflect$api$Internals$ReificationSupportApi$$$outer() {
            return this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer();
        }

        public ReificationSupportImpl(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            Internals$ReificationSupportApi$class.$init$(this);
        }

        public class ScalaMemberRef {
            private final Seq<Symbols.Symbol> symbols;
            public final /* synthetic */ ReificationSupportImpl $outer;

            public Option<Symbols.Symbol> result(Names.Name name) {
                return ((TraversableLike)this.symbols.collect(new Serializable(this, name){
                    public static final long serialVersionUID = 0L;
                    private final Names.Name name$2;

                    public final <A1 extends Symbols.Symbol, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        Names.Name name = x1.name();
                        Names.Name name2 = this.name$2;
                        Object object = !(name != null ? !name.equals(name2) : name2 != null) ? x1 : function1.apply(x1);
                        return (B1)object;
                    }

                    public final boolean isDefinedAt(Symbols.Symbol x1) {
                        Names.Name name = x1.name();
                        Names.Name name2 = this.name$2;
                        boolean bl = !(name != null ? !name.equals(name2) : name2 != null);
                        return bl;
                    }
                    {
                        this.name$2 = name$2;
                    }
                }, Seq$.MODULE$.canBuildFrom())).headOption();
            }

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public Option<Symbols.Symbol> unapply(Trees.Tree tree) {
                Trees.Select select;
                void var13_10;
                Trees.Ident ident;
                boolean bl = false;
                Trees.Select select2 = null;
                if (tree instanceof Trees.Ident && this.symbols.contains((ident = (Trees.Ident)tree).symbol())) {
                    Names.Name name = ident.name();
                    Names.Name name2 = ident.symbol().name();
                    if (!(name != null ? !name.equals(name2) : name2 != null)) {
                        Some<Symbols.Symbol> some = new Some<Symbols.Symbol>(ident.symbol());
                        return var13_10;
                    }
                }
                if (tree instanceof Trees.Select) {
                    bl = true;
                    select2 = (Trees.Select)tree;
                    if (select2.qualifier() instanceof Trees.Ident) {
                        Trees.Ident ident2 = (Trees.Ident)select2.qualifier();
                        Names.TermName termName = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$ScalaMemberRef$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().scala_();
                        Names.Name name = ident2.name();
                        if (!(termName != null ? !termName.equals(name) : name != null)) {
                            Symbols.Symbol symbol = ident2.symbol();
                            Symbols.ModuleSymbol moduleSymbol = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$ScalaMemberRef$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().definitions().ScalaPackage();
                            if (!(symbol != null ? !symbol.equals(moduleSymbol) : moduleSymbol != null)) {
                                Option<Symbols.Symbol> option = this.result(select2.name());
                                return var13_10;
                            }
                        }
                    }
                }
                if (bl && select2.qualifier() instanceof Trees.Select && (select = (Trees.Select)select2.qualifier()).qualifier() instanceof Trees.Ident) {
                    Trees.Ident ident3 = (Trees.Ident)select.qualifier();
                    Names.TermName termName = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$ScalaMemberRef$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().ROOTPKG();
                    Names.Name name = ident3.name();
                    if (!(termName != null ? !termName.equals(name) : name != null)) {
                        Names.TermName termName2 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$ScalaMemberRef$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().scala_();
                        Names.Name name3 = select.name();
                        if (!(termName2 != null ? !termName2.equals(name3) : name3 != null)) {
                            Option<Symbols.Symbol> option = this.result(select2.name());
                            return var13_10;
                        }
                    }
                }
                None$ none$ = None$.MODULE$;
                return var13_10;
            }

            public /* synthetic */ ReificationSupportImpl scala$reflect$internal$ReificationSupport$ReificationSupportImpl$ScalaMemberRef$$$outer() {
                return this.$outer;
            }

            public ScalaMemberRef(ReificationSupportImpl $outer, Seq<Symbols.Symbol> symbols) {
                this.symbols = symbols;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }

        public class UnForCombination {
            private final Names.TermName name;
            public final /* synthetic */ ReificationSupportImpl $outer;

            /*
             * WARNING - void declaration
             * Enabled aggressive block sorting
             */
            public Option<Tuple2<Trees.Tree, Trees.Tree>> unapply(Trees.Tree tree) {
                Option<Tuple2<Trees.Tree, List<Trees.Tree>>> option;
                Some<Tuple2<Trees.Tree, List<List<Trees.Tree>>>> some;
                void var15_11;
                Option<Tuple2<Trees.Tree, List<Trees.Tree>>> option2;
                Some<Tuple2<Trees.Tree, List<List<Trees.Tree>>>> some2 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$UnForCombination$$$outer().SyntacticApplied().unapply(tree);
                if (!some2.isEmpty() && !(option2 = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$UnForCombination$$$outer().SyntacticTypeApplied().unapply(some2.get()._1())).isEmpty() && option2.get()._1() instanceof Trees.Select) {
                    $colon$colon $colon$colon;
                    $colon$colon $colon$colon2;
                    Trees.Select select = (Trees.Select)option2.get()._1();
                    if (some2.get()._2() instanceof $colon$colon && ($colon$colon2 = ($colon$colon)some2.get()._2()).head() instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)$colon$colon2.head()).tl$1()) && ((Object)Nil$.MODULE$).equals($colon$colon2.tl$1())) {
                        Names.TermName termName = this.name;
                        Names.Name name = select.name();
                        if (!(termName != null ? !termName.equals(name) : name != null) && select.hasAttachment(ClassTag$.MODULE$.apply(StdAttachments$ForAttachment$.class))) {
                            Some some3 = new Some(new Tuple2(select.qualifier(), $colon$colon.head()));
                            return var15_11;
                        }
                    }
                }
                if (!(some = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$UnForCombination$$$outer().SyntacticApplied().unapply(tree)).isEmpty() && !(option = this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$UnForCombination$$$outer().SyntacticTypeApplied().unapply(some.get()._1())).isEmpty() && option.get()._1() instanceof Trees.Select) {
                    $colon$colon $colon$colon;
                    $colon$colon $colon$colon3;
                    $colon$colon $colon$colon4;
                    Trees.Select select = (Trees.Select)option.get()._1();
                    if (some.get()._2() instanceof $colon$colon && ($colon$colon4 = ($colon$colon)some.get()._2()).head() instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon3 = ($colon$colon)$colon$colon4.head()).tl$1()) && $colon$colon4.tl$1() instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)$colon$colon4.tl$1()).tl$1())) {
                        Names.TermName termName = this.name;
                        Names.Name name = select.name();
                        if (!(termName != null ? !termName.equals(name) : name != null) && select.hasAttachment(ClassTag$.MODULE$.apply(StdAttachments$ForAttachment$.class))) {
                            Some some4 = new Some(new Tuple2(select.qualifier(), $colon$colon3.head()));
                            return var15_11;
                        }
                    }
                }
                None$ none$ = None$.MODULE$;
                return var15_11;
            }

            public /* synthetic */ ReificationSupportImpl scala$reflect$internal$ReificationSupport$ReificationSupportImpl$UnForCombination$$$outer() {
                return this.$outer;
            }

            public UnForCombination(ReificationSupportImpl $outer, Names.TermName name) {
                this.name = name;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }

        public class SyntacticValDefBase
        implements Internals.ReificationSupportApi.SyntacticValDefExtractor {
            private final boolean isMutable;
            public final /* synthetic */ ReificationSupportImpl $outer;

            public Trees.Modifiers modifiers(Trees.Modifiers mods) {
                return this.isMutable ? mods.$bar(4096) : mods;
            }

            public Trees.ValDef apply(Trees.Modifiers mods, Names.TermName name, Trees.Tree tpt, Trees.Tree rhs) {
                return new Trees.ValDef(this.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticValDefBase$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer(), this.modifiers(mods), name, tpt, rhs);
            }

            public Option<Tuple4<Trees.Modifiers, Names.TermName, Trees.Tree, Trees.Tree>> unapply(Trees.Tree tree) {
                Trees.ValDef valDef;
                Option option = tree instanceof Trees.ValDef && (valDef = (Trees.ValDef)tree).mods().hasFlag(4096L) == this.isMutable ? new Some<Tuple4<Trees.Modifiers, Names.TermName, Trees.Tree, Trees.Tree>>(new Tuple4<Trees.Modifiers, Names.TermName, Trees.Tree, Trees.Tree>(valDef.mods(), valDef.name(), valDef.tpt(), valDef.rhs())) : None$.MODULE$;
                return option;
            }

            public /* synthetic */ ReificationSupportImpl scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticValDefBase$$$outer() {
                return this.$outer;
            }

            public SyntacticValDefBase(ReificationSupportImpl $outer, boolean isMutable) {
                this.isMutable = isMutable;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }
    }
}

