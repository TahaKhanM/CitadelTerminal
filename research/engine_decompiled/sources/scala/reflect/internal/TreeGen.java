/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.AbstractTraversable;
import scala.collection.GenTraversable;
import scala.collection.IterableLike;
import scala.collection.LinearSeqOptimized;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Trees;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Mirrors;
import scala.reflect.internal.Names;
import scala.reflect.internal.StdAttachments$ForAttachment$;
import scala.reflect.internal.StdAttachments$SyntheticUnitAttachment$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.TreeGen$Filter$;
import scala.reflect.internal.TreeGen$ValEq$;
import scala.reflect.internal.TreeGen$ValFrom$;
import scala.reflect.internal.TreeGen$Yield$;
import scala.reflect.internal.TreeGen$patvarTransformer$;
import scala.reflect.internal.TreeInfo;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Trees$EmptyTree$;
import scala.reflect.internal.Trees$pendingSuperCall$;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Tuple2Zipped$;

@ScalaSignature(bytes="\u0006\u0001\u0015\rf!B\u0001\u0003\u0003\u0003I!a\u0002+sK\u0016<UM\u001c\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004=S:LGO\u0010\u000b\u0002#A\u0011!\u0003A\u0007\u0002\u0005!9A\u0003\u0001b\u0001\u000e\u0003)\u0012AB4m_\n\fG.F\u0001\u0017!\t\u0011r#\u0003\u0002\u0019\u0005\tY1+_7c_2$\u0016M\u00197f\u0011\u0015Q\u0002\u0001\"\u0001\u001c\u0003\u0019\u0011xn\u001c;JIR\u0011Ad\t\t\u0003;}q!AH\n\u000e\u0003\u0001I!\u0001I\u0011\u0003\rM+G.Z2u\u0013\t\u0011#AA\u0003Ue\u0016,7\u000fC\u0003%3\u0001\u0007Q%\u0001\u0003oC6,\u0007CA\u000f'\u0013\t9\u0003F\u0001\u0003OC6,\u0017BA\u0015\u0003\u0005\u0015q\u0015-\\3t\u0011\u0015Y\u0003\u0001\"\u0001-\u00031\u0011xn\u001c;TG\u0006d\u0017\rR8u)\taR\u0006C\u0003%U\u0001\u0007Q\u0005C\u00030\u0001\u0011\u0005\u0001'\u0001\u0005tG\u0006d\u0017\rR8u)\ta\u0012\u0007C\u0003%]\u0001\u0007Q\u0005C\u00034\u0001\u0011\u0005A'\u0001\ntG\u0006d\u0017-\u00118o_R\fG/[8o\t>$HC\u0001\u000f6\u0011\u0015!#\u00071\u0001&\u0011\u00159\u0004\u0001\"\u00019\u0003E\u00198-\u00197b\u0003:L(+\u001a4D_:\u001cHO]\u000b\u00029!)!\b\u0001C\u0001w\u0005\u00192oY1mC\u001a+hn\u0019;j_:\u001cuN\\:ueR!Ah\u0010%K!\tiR(\u0003\u0002?C\t!AK]3f\u0011\u0015\u0001\u0015\b1\u0001B\u0003\u001d\t'o\u001a;qKN\u00042AQ#=\u001d\tY1)\u0003\u0002E\r\u00059\u0001/Y2lC\u001e,\u0017B\u0001$H\u0005\u0011a\u0015n\u001d;\u000b\u0005\u00113\u0001\"B%:\u0001\u0004a\u0014A\u0002:fgR\u0004X\rC\u0004LsA\u0005\t\u0019\u0001'\u0002\u0017\u0005\u00147\u000f\u001e:bGR4UO\u001c\t\u0003\u00175K!A\u0014\u0004\u0003\u000f\t{w\u000e\\3b]\")\u0001\u000b\u0001C\u0001#\u0006aQn['fi\"|GmQ1mYR)AHU-\\G\")1k\u0014a\u0001)\u0006A!/Z2fSZ,'\u000f\u0005\u0002\u001e+&\u0011ak\u0016\u0002\u0007'fl'm\u001c7\n\u0005a\u0013!aB*z[\n|Gn\u001d\u0005\u00065>\u0003\r!J\u0001\u000b[\u0016$\bn\u001c3OC6,\u0007\"\u0002/P\u0001\u0004i\u0016!\u0002;be\u001e\u001c\bc\u0001\"F=B\u0011QdX\u0005\u0003A\u0006\u0014A\u0001V=qK&\u0011!M\u0001\u0002\u0006)f\u0004Xm\u001d\u0005\u0006I>\u0003\r!Q\u0001\u0005CJ<7\u000fC\u0003Q\u0001\u0011\u0005a\r\u0006\u0003=O&T\u0007\"\u00025f\u0001\u0004!\u0016AB7fi\"|G\rC\u0003]K\u0002\u0007Q\fC\u0003eK\u0002\u0007\u0011\tC\u0003Q\u0001\u0011\u0005A\u000eF\u0002=[:DQ\u0001[6A\u0002QCQ\u0001Z6A\u0002\u0005CQ\u0001\u0015\u0001\u0005\u0002A$2\u0001P9t\u0011\u0015\u0011x\u000e1\u0001=\u0003\u0019!\u0018M]4fi\")Am\u001ca\u0001\u0003\")\u0001\u000b\u0001C\u0001kR!AH^<y\u0011\u0015\u0019F\u000f1\u0001U\u0011\u0015QF\u000f1\u0001&\u0011\u0015!G\u000f1\u0001B\u0011\u0015\u0001\u0006\u0001\"\u0001{)\u0015a4\u0010`?\u007f\u0011\u0015\u0019\u0016\u00101\u0001=\u0011\u0015A\u0017\u00101\u0001U\u0011\u0015a\u0016\u00101\u0001^\u0011\u0015!\u0017\u00101\u0001B\u0011\u0019\u0001\u0006\u0001\"\u0001\u0002\u0002Q9A(a\u0001\u0002\u0006\u0005\u001d\u0001\"\u0002:\u0000\u0001\u0004a\u0004\"\u0002/\u0000\u0001\u0004i\u0006\"\u00023\u0000\u0001\u0004\t\u0005bBA\u0006\u0001\u0011\u0005\u0011QB\u0001\u000e[.tU\u000f\u001c7bef\u001c\u0015\r\u001c7\u0015\u000bq\ny!!\u0005\t\r!\fI\u00011\u0001U\u0011\u0019a\u0016\u0011\u0002a\u0001;\"9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0011!F7l\u0003R$(/\u001b2vi\u0016$\u0017+^1mS\u001aLWM\u001d\u000b\u0004y\u0005e\u0001bBA\u000e\u0003'\u0001\rAX\u0001\u0004iB,\u0007bBA\u000b\u0001\u0011\u0005\u0011q\u0004\u000b\u0006y\u0005\u0005\u00121\u0005\u0005\b\u00037\ti\u00021\u0001_\u0011\u001d\t)#!\bA\u0002Q\u000bq\u0001^3s[NKX\u000eC\u0004\u0002*\u0001!\t!a\u000b\u0002\u001f5\\\u0017\t\u001d9ms&3g*Z3eK\u0012$2\u0001PA\u0017\u0011\u001d\ty#a\nA\u0002q\nA!];bY\"9\u00111\u0007\u0001\u0005\u0002\u0005U\u0012aD7l\u0003R$(/\u001b2vi\u0016$'+\u001a4\u0015\r\u0005]\u0012QHA!!\ri\u0012\u0011H\u0005\u0004\u0003w\t#a\u0002*fMR\u0013X-\u001a\u0005\b\u0003\u007f\t\t\u00041\u0001_\u0003\r\u0001(/\u001a\u0005\b\u0003\u0007\n\t\u00041\u0001U\u0003\r\u0019\u00180\u001c\u0005\b\u0003g\u0001A\u0011AA$)\u0011\t9$!\u0013\t\u000f\u0005\r\u0013Q\ta\u0001)\"9\u0011Q\n\u0001\u0005\u0002\u0005=\u0013!E7l+:\fG\u000f\u001e:jEV$X\r\u001a*fMR!\u0011qGA)\u0011\u001d\t\u0019%a\u0013A\u0002QCq!!\u0014\u0001\t\u0003\t)\u0006\u0006\u0003\u00028\u0005]\u0003bBA-\u0003'\u0002\r!J\u0001\tMVdGNT1nK\"9\u0011Q\f\u0001\u0005\u0002\u0005}\u0013!C:uC\nLG.\u001b>f)\ra\u0014\u0011\r\u0005\b\u0003G\nY\u00061\u0001=\u0003\u0011!(/Z3\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0002j\u0005i1\u000f^1cY\u0016$\u0016\u0010]3G_J$2AXA6\u0011\u001d\t\u0019'!\u001aA\u0002qBq!a\u001c\u0001\t\u0003\t\t(A\u000bnW\u0006#HO]5ckR,Gm\u0015;bE2,'+\u001a4\u0015\u000bq\n\u0019(!\u001e\t\u000f\u0005}\u0012Q\u000ea\u0001=\"9\u00111IA7\u0001\u0004!\u0006bBA8\u0001\u0011\u0005\u0011\u0011\u0010\u000b\u0004y\u0005m\u0004bBA\"\u0003o\u0002\r\u0001\u0016\u0005\b\u0003\u007f\u0002A\u0011AAA\u0003Ai7.\u0011;ue&\u0014W\u000f^3e)\"L7\u000f\u0006\u0003\u0002\u0004\u0006%\u0005cA\u000f\u0002\u0006&\u0019\u0011qQ\u0011\u0003\tQC\u0017n\u001d\u0005\b\u0003\u0007\ni\b1\u0001U\u0011\u001d\ti\t\u0001C\u0001\u0003\u001f\u000b\u0011#\\6BiR\u0014\u0018NY;uK\u0012LE-\u001a8u)\u0011\t9$!%\t\u000f\u0005\r\u00131\u0012a\u0001)\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0015AE7l\u0003R$(/\u001b2vi\u0016$7+\u001a7fGR$b!a\u000e\u0002\u001a\u0006m\u0005bBA\u0018\u0003'\u0003\r\u0001\u0010\u0005\b\u0003\u0007\n\u0019\n1\u0001U\u0011\u001d\ty\n\u0001C\u0001\u0003C\u000b1\"\\6UsB,\u0017\t\u001d9msR)A(a)\u0002(\"9\u0011QUAO\u0001\u0004a\u0014a\u00014v]\"1A,!(A\u0002\u0005Cq!a+\u0001\t\u0003\ti+A\tnW\u0006\u0003\b\u000f\\5fIRK\b/\u001a+sK\u0016$R\u0001PAX\u0003cCq!!*\u0002*\u0002\u0007A\b\u0003\u0004]\u0003S\u0003\r!\u0011\u0005\b\u0003k\u0003A\u0011AA\\\u0003Ui7.\u0011;ue&\u0014W\u000f^3e)f\u0004X-\u00119qYf$r\u0001PA]\u0003w\u000bi\f\u0003\u0004s\u0003g\u0003\r\u0001\u0010\u0005\u0007Q\u0006M\u0006\u0019\u0001+\t\rq\u000b\u0019\f1\u0001^\u0011\u001d\t\t\r\u0001C\u0005\u0003\u0007\f\u0011#\\6TS:<G.\u001a+za\u0016\f\u0005\u000f\u001d7z)%a\u0014QYAe\u0003\u0017\fy\rC\u0004\u0002H\u0006}\u0006\u0019\u0001\u001f\u0002\u000bY\fG.^3\t\u000f\u0005m\u0011q\u0018a\u0001=\"9\u0011QZA`\u0001\u0004!\u0016\u0001B<iCRDq!!5\u0002@\u0002\u0007A*A\u0006xe\u0006\u0004\u0018J\\!qa2L\bbBAk\u0001\u0011%\u0011q[\u0001\u000fif\u0004X\rV3tiNKXNY8m)\u0011\tI.a8\u0011\u0007u\tY.C\u0002\u0002^^\u0013A\"T3uQ>$7+_7c_2Dq!!9\u0002T\u0002\u0007A*A\u0002b]fDq!!:\u0001\t\u0013\t9/\u0001\busB,7)Y:u'fl'm\u001c7\u0015\t\u0005e\u0017\u0011\u001e\u0005\b\u0003C\f\u0019\u000f1\u0001M\u0011\u001d\ti\u000f\u0001C\u0001\u0003_\fa\"\\6Jg&s7\u000f^1oG\u0016|e\rF\u0005=\u0003c\f\u00190!>\u0002x\"9\u0011qYAv\u0001\u0004a\u0004bBA\u000e\u0003W\u0004\rA\u0018\u0005\n\u0003C\fY\u000f%AA\u00021C\u0011\"!5\u0002lB\u0005\t\u0019\u0001'\t\u000f\u0005m\b\u0001\"\u0001\u0002~\u0006qQn[!t\u0013:\u001cH/\u00198dK>3G#\u0003\u001f\u0002\u0000\n\u0005!1\u0001B\u0003\u0011\u001d\t9-!?A\u0002qBq!a\u0007\u0002z\u0002\u0007a\fC\u0005\u0002b\u0006e\b\u0013!a\u0001\u0019\"I\u0011\u0011[A}!\u0003\u0005\r\u0001\u0014\u0005\b\u0005\u0013\u0001A\u0011\u0001B\u0006\u0003Mi\u0017-\u001f2f\u001b.\f5/\u00138ti\u0006t7-Z(g)%a$Q\u0002B\b\u0005'\u0011)\u0002C\u0004\u0002d\t\u001d\u0001\u0019\u0001\u001f\t\u000f\tE!q\u0001a\u0001=\u0006\u0011\u0001\u000f\u001e\u0005\b\u00037\u00119\u00011\u0001_\u0011%\u00119Ba\u0002\u0011\u0002\u0003\u0007A*A\bcK\u001a|'/\u001a*fM\u000eCWmY6t\u0011\u001d\u0011Y\u0002\u0001C\u0001\u0005;\t\u0011\"\\6DY\u0006\u001c8o\u00144\u0015\u0007q\u0012y\u0002C\u0004\u0003\"\te\u0001\u0019\u00010\u0002\u0005Q\u0004\bb\u0002B\u0013\u0001\u0011\u0005!qE\u0001\u0006[.t\u0015\u000e\\\u000b\u0002y!9!1\u0006\u0001\u0005\u0002\t5\u0012AB7l5\u0016\u0014x\u000eF\u0002=\u0005_AqA!\t\u0003*\u0001\u0007a\fC\u0004\u00034\u0001!\tA!\u000e\u0002\u001d5\\7i\u001c8ti\u0006tGOW3s_R!!q\u0007B!!\ri\"\u0011H\u0005\u0005\u0005w\u0011iD\u0001\u0005D_:\u001cH/\u00198u\u0013\r\u0011yD\u0001\u0002\n\u0007>t7\u000f^1oiNDqA!\t\u00032\u0001\u0007a\fC\u0004\u0003F\u0001!\tAa\u0012\u0002\u00155\\g*Y7fI\u0006\u0013x\rF\u0003=\u0005\u0013\u0012Y\u0005\u0003\u0004%\u0005\u0007\u0002\r!\n\u0005\b\u0003G\u0012\u0019\u00051\u0001=\u0011\u001d\u0011)\u0005\u0001C\u0001\u0005\u001f\"R\u0001\u0010B)\u0005+BqAa\u0015\u0003N\u0001\u0007A(A\u0002mQNDqAa\u0016\u0003N\u0001\u0007A(A\u0002sQNDqAa\u0017\u0001\t\u0003\u0011i&A\u0004nWR+\b\u000f\\3\u0015\u000bq\u0012yFa\u0019\t\u000f\t\u0005$\u0011\fa\u0001\u0003\u0006)Q\r\\3ng\"I!Q\rB-!\u0003\u0005\r\u0001T\u0001\rM2\fG\u000f^3o+:\f'/\u001f\u0005\b\u0005S\u0002A\u0011\u0001B6\u0003-i7\u000eV;qY\u0016$\u0016\u0010]3\u0015\u000bq\u0012iGa\u001c\t\u000f\t\u0005$q\ra\u0001\u0003\"I!Q\rB4!\u0003\u0005\r\u0001\u0014\u0005\b\u0005g\u0002A\u0011\u0001B;\u0003\u0015i7.\u00118e)\u0015a$q\u000fB>\u0011\u001d\u0011IH!\u001dA\u0002q\nQ\u0001\u001e:fKFBqA! \u0003r\u0001\u0007A(A\u0003ue\u0016,'\u0007C\u0004\u0003\u0002\u0002!\tAa!\u0002\t5\\wJ\u001d\u000b\u0006y\t\u0015%q\u0011\u0005\b\u0005s\u0012y\b1\u0001=\u0011\u001d\u0011iHa A\u0002qBqAa#\u0001\t\u0003\u00119#\u0001\u000bnWJ+h\u000e^5nKVs\u0017N^3sg\u0016\u0014VM\u001a\u0005\b\u0005\u001f\u0003A\u0011\u0001BI\u0003)i7nU3r\u0003B\u0004H.\u001f\u000b\u0005\u0005'\u0013I\nE\u0002\u001e\u0005+K1Aa&\"\u0005\u0015\t\u0005\u000f\u001d7z\u0011\u001d\u0011YJ!$A\u0002q\n1!\u0019:h\u0011\u0019\u0011y\n\u0001C\u0001q\u0005yQn[*va\u0016\u0014\u0018J\\5u\u0007\u0006dG\u000eC\u0004\u0003$\u0002!\tA!*\u0002\u00155\\G+Z7qY\u0006$X\r\u0006\b\u0003(\n5&\u0011\u0017B^\u0005\u000b\u0014iM!5\u0011\u0007u\u0011I+C\u0002\u0003,\u0006\u0012\u0001\u0002V3na2\fG/\u001a\u0005\b\u0005_\u0013\t\u000b1\u0001B\u0003\u001d\u0001\u0018M]3oiND\u0001Ba-\u0003\"\u0002\u0007!QW\u0001\u0005g\u0016dg\rE\u0002\u001e\u0005oK1A!/\"\u0005\u00191\u0016\r\u001c#fM\"A!Q\u0018BQ\u0001\u0004\u0011y,\u0001\u0006d_:\u001cHO]'pIN\u00042!\bBa\u0013\r\u0011\u0019-\t\u0002\n\u001b>$\u0017NZ5feND\u0001Ba2\u0003\"\u0002\u0007!\u0011Z\u0001\tmB\f'/Y7tgB!!)\u0012Bf!\u0011\u0011UI!.\t\u000f\t='\u0011\u0015a\u0001\u0003\u0006!!m\u001c3z\u0011)\u0011\u0019N!)\u0011\u0002\u0003\u0007!Q[\u0001\tgV\u0004XM\u001d)pgB\u0019QDa6\n\t\te'1\u001c\u0002\t!>\u001c\u0018\u000e^5p]&\u0019!Q\u001c\u0002\u0003\u0013A{7/\u001b;j_:\u001c\bb\u0002Bq\u0001\u0011\u0005!1]\u0001\n[.\u0004\u0016M]3oiN$\u0002B!:\u0003t\n](\u0011 \t\u0006\u0005O\u0014\t\u0010P\u0007\u0003\u0005STAAa;\u0003n\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0004\u0005_4\u0011AC2pY2,7\r^5p]&\u0019aI!;\t\u0011\tU(q\u001ca\u0001\u0005\u007f\u000b\u0011b\\<oKJlu\u000eZ:\t\u000f\t=&q\u001ca\u0001\u0003\"Q!1 Bp!\u0003\u0005\rA!6\u0002\u0013A\f'/\u001a8u!>\u001c\bb\u0002B\u0000\u0001\u0011\u00051\u0011A\u0001\u000b[.\u001cE.Y:t\t\u00164GCCB\u0002\u0007\u0013\u0019ia!\u0006\u0004\"A\u0019Qd!\u0002\n\u0007\r\u001d\u0011E\u0001\u0005DY\u0006\u001c8\u000fR3g\u0011!\u0019YA!@A\u0002\t}\u0016\u0001B7pINDq\u0001\nB\u007f\u0001\u0004\u0019y\u0001E\u0002\u001e\u0007#I1aa\u0005)\u0005!!\u0016\u0010]3OC6,\u0007\u0002CB\f\u0005{\u0004\ra!\u0007\u0002\u000fQ\u0004\u0018M]1ngB!!)RB\u000e!\ri2QD\u0005\u0004\u0007?\t#a\u0002+za\u0016$UM\u001a\u0005\t\u0007G\u0011i\u00101\u0001\u0003(\u0006)A/Z7qY\"91q\u0005\u0001\u0005\u0002\r%\u0012!B7l\u001d\u0016<Hc\u0003\u001f\u0004,\r52qFB\u001a\u0007oAqAa,\u0004&\u0001\u0007\u0011\t\u0003\u0005\u00034\u000e\u0015\u0002\u0019\u0001B[\u0011\u001d\u0019\td!\nA\u0002\u0005\u000bQa\u001d;biND\u0001b!\u000e\u0004&\u0001\u0007!Q[\u0001\u0005]B|7\u000f\u0003\u0005\u0004:\r\u0015\u0002\u0019\u0001Bk\u0003\u0011\u0019\u0007o\\:\t\u000f\ru\u0002\u0001\"\u0001\u0004@\u0005\u0011Rn\u001b$v]\u000e$\u0018n\u001c8UsB,GK]3f)\u0015a4\u0011IB\"\u0011\u0019\u000151\ba\u0001\u0003\"1\u0011ja\u000fA\u0002qBqaa\u0012\u0001\t\u0003\u0019I%A\bnWNKh\u000e\u001e5fi&\u001cWK\\5u)\t\u0019Y\u0005E\u0002\u001e\u0007\u001bJ1aa\u0014\"\u0005\u001da\u0015\u000e^3sC2Dqaa\u0015\u0001\t\u0003\u0019)&A\u0004nW\ncwnY6\u0015\u000bq\u001a9f!\u0017\t\u000f\rE2\u0011\u000ba\u0001\u0003\"I11LB)!\u0003\u0005\r\u0001T\u0001\nI>4E.\u0019;uK:Dqaa\u0018\u0001\t\u0003\u0019\t'A\u0007nWR\u0013X-Z(s\u00052|7m\u001b\u000b\u0004y\r\r\u0004bBB\u0019\u0007;\u0002\r!\u0011\u0005\b\u0007O\u0002A\u0011AB5\u0003!i7.Q:tS\u001etG#\u0002\u001f\u0004l\r5\u0004b\u0002B*\u0007K\u0002\r\u0001\u0010\u0005\b\u0005/\u001a)\u00071\u0001=\u0011\u001d\u0019\t\b\u0001C\u0001\u0007g\nq\"\\6QC\u000e\\\u0017mZ3PE*,7\r\u001e\u000b\t\u0007k\u001aYh!\"\u0004\nB\u0019Qda\u001e\n\u0007\re\u0014E\u0001\u0006QC\u000e\\\u0017mZ3EK\u001aD\u0001b! \u0004p\u0001\u00071qP\u0001\u0005I\u00164g\u000eE\u0002\u001e\u0007\u0003K1aa!\"\u0005%iu\u000eZ;mK\u0012+g\r\u0003\u0006\u0004\b\u000e=\u0004\u0013!a\u0001\u0005+\fa\u0001]5e!>\u001c\bBCBF\u0007_\u0002\n\u00111\u0001\u0003V\u00061\u0001o[4Q_N<qaa$\u0001\u0011\u0003\u0019\t*A\u0004WC24%o\\7\u0011\u0007y\u0019\u0019JB\u0004\u0004\u0016\u0002A\taa&\u0003\u000fY\u000bGN\u0012:p[N\u001911\u0013\u0006\t\u000f=\u0019\u0019\n\"\u0001\u0004\u001cR\u00111\u0011\u0013\u0005\t\u0007?\u001b\u0019\n\"\u0001\u0004\"\u0006)\u0011\r\u001d9msR)Aha)\u0004(\"91QUBO\u0001\u0004a\u0014a\u00019bi\"9!qKBO\u0001\u0004a\u0004\u0002CBV\u0007'#\ta!,\u0002\u000fUt\u0017\r\u001d9msR!1qVB^!\u0015Y1\u0011WB[\u0013\r\u0019\u0019L\u0002\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b-\u00199\f\u0010\u001f\n\u0007\refA\u0001\u0004UkBdWM\r\u0005\b\u0003G\u001aI\u000b1\u0001=\u000f\u001d\u0019y\f\u0001E\u0001\u0007\u0003\fQAV1m\u000bF\u00042AHBb\r\u001d\u0019)\r\u0001E\u0001\u0007\u000f\u0014QAV1m\u000bF\u001c2aa1\u000b\u0011\u001dy11\u0019C\u0001\u0007\u0017$\"a!1\t\u0011\r}51\u0019C\u0001\u0007\u001f$R\u0001PBi\u0007'Dqa!*\u0004N\u0002\u0007A\bC\u0004\u0003X\r5\u0007\u0019\u0001\u001f\t\u0011\r-61\u0019C\u0001\u0007/$Baa,\u0004Z\"9\u00111MBk\u0001\u0004ataBBo\u0001!\u00051q\\\u0001\u0007\r&dG/\u001a:\u0011\u0007y\u0019\tOB\u0004\u0004d\u0002A\ta!:\u0003\r\u0019KG\u000e^3s'\r\u0019\tO\u0003\u0005\b\u001f\r\u0005H\u0011ABu)\t\u0019y\u000e\u0003\u0005\u0004 \u000e\u0005H\u0011ABw)\u0011\u0011\u0019ja<\t\u000f\u0005\r41\u001ea\u0001y!A11VBq\t\u0003\u0019\u0019\u0010\u0006\u0003\u0004v\u000e]\b\u0003B\u0006\u00042rBq!a\u0019\u0004r\u0002\u0007AhB\u0004\u0004|\u0002A\ta!@\u0002\u000beKW\r\u001c3\u0011\u0007y\u0019yPB\u0004\u0005\u0002\u0001A\t\u0001b\u0001\u0003\u000beKW\r\u001c3\u0014\u0007\r}(\u0002C\u0004\u0010\u0007\u007f$\t\u0001b\u0002\u0015\u0005\ru\b\u0002CBP\u0007\u007f$\t\u0001b\u0003\u0015\u0007q\"i\u0001C\u0004\u0002d\u0011%\u0001\u0019\u0001\u001f\t\u0011\r-6q C\u0001\t#!Ba!>\u0005\u0014!9\u00111\rC\b\u0001\u0004a\u0004b\u0002C\f\u0001\u0011\u0005A\u0011D\u0001\u0006[.4uN\u001d\u000b\u0007\t7!i\u0003\"\r\u0015\u0007q\"i\u0002\u0003\u0005\u0005 \u0011U\u00019\u0001C\u0011\u0003\u00151'/Z:i!\u0011!\u0019\u0003\"\u000b\u000e\u0005\u0011\u0015\"b\u0001C\u0014\u0005\u0005!Q\u000f^5m\u0013\u0011!Y\u0003\"\n\u0003!\u0019\u0013Xm\u001d5OC6,7I]3bi>\u0014\bb\u0002C\u0018\t+\u0001\r!Q\u0001\u0006K:,Xn\u001d\u0005\b\tg!)\u00021\u0001=\u0003%\u0019XoZ1s\u0005>$\u0017\u0010C\u0004\u00058\u0001!\t\u0001\"\u000f\u0002\u00115\\\u0007+\u0019;EK\u001a$b\u0001b\u000f\u0005@\u0011\u0005C\u0003\u0002Bf\t{A\u0001\u0002b\b\u00056\u0001\u000fA\u0011\u0005\u0005\b\u0007K#)\u00041\u0001=\u0011\u001d\u00119\u0006\"\u000eA\u0002qBq\u0001b\u000e\u0001\t\u0003!)\u0005\u0006\u0005\u0005H\u0011-CQ\nC()\u0011\u0011Y\r\"\u0013\t\u0011\u0011}A1\ta\u0002\tCA\u0001ba\u0003\u0005D\u0001\u0007!q\u0018\u0005\b\u0007K#\u0019\u00051\u0001=\u0011\u001d\u00119\u0006b\u0011A\u0002qBq\u0001b\u0015\u0001\t\u0003!)&A\u0006nW\u001e+g.\u001a:bi>\u0014HC\u0003C,\t7\"y\u0006\"\u0019\u0005fQ\u0019A\b\"\u0017\t\u0011\u0011}A\u0011\u000ba\u0002\tCA\u0001\u0002\"\u0018\u0005R\u0001\u0007!Q[\u0001\u0004a>\u001c\bbBBS\t#\u0002\r\u0001\u0010\u0005\b\tG\"\t\u00061\u0001M\u0003\u00151\u0018\r\\3r\u0011\u001d\u00119\u0006\"\u0015A\u0002qBq\u0001\"\u001b\u0001\t\u0003!Y'\u0001\nnW\u000eCWmY6JMJ+g-\u001e;bE2,GC\u0002C7\tc\"\u0019\bF\u0002=\t_B\u0001\u0002b\b\u0005h\u0001\u000fA\u0011\u0005\u0005\b\u0007K#9\u00071\u0001=\u0011\u001d\u00119\u0006b\u001aA\u0002qBq\u0001b\u001e\u0001\t\u0013!I(A\bnCR\u001c\u0007NV1s!\u0006$H/\u001a:o)\u0011!Y\bb \u0011\u000b-\u0019\t\f\" \u0011\u000b-\u00199,\n\u001f\t\u000f\u0005\rDQ\u000fa\u0001y!9A1\u0011\u0001\u0005\u0002\u0011\u0015\u0015!C7l-&\u001c\u0018\u000e^8s)!!9\tb#\u0005\u0018\u0012mEc\u0001\u001f\u0005\n\"AAq\u0004CA\u0001\b!\t\u0003\u0003\u0005\u0005\u000e\u0012\u0005\u0005\u0019\u0001CH\u0003\u0015\u0019\u0017m]3t!\u0011\u0011U\t\"%\u0011\u0007u!\u0019*C\u0002\u0005\u0016\u0006\u0012qaQ1tK\u0012+g\rC\u0004\u0005\u001a\u0012\u0005\u0005\u0019\u0001'\u0002\u001f\rDWmY6Fq\"\fWo\u001d;jm\u0016D!\u0002\"(\u0005\u0002B\u0005\t\u0019\u0001CP\u0003\u0019\u0001(/\u001a4jqB!A\u0011\u0015CT\u001d\rYA1U\u0005\u0004\tK3\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0005*\u0012-&AB*ue&twMC\u0002\u0005&\u001a1a\u0001b,\u0001\u0001\u0011E&aD$fiZ\u000b'\u000f\u0016:bm\u0016\u00148/\u001a:\u0014\t\u00115F1\u0017\t\u0004;\u0011U\u0016\u0002\u0002C\\\ts\u0013\u0011\u0002\u0016:bm\u0016\u00148/\u001a:\n\u0007\t\"YLC\u0002\u0005>\u0012\t1!\u00199j\u0011\u001dyAQ\u0016C\u0001\t\u0003$\"\u0001b1\u0011\u0007y!i\u000b\u0003\u0006\u0005H\u00125&\u0019!C\u0001\t\u0013\f1AY;g+\t!Y\r\u0005\u0004\u0005N\u0012MGq[\u0007\u0003\t\u001fTA\u0001\"5\u0003n\u00069Q.\u001e;bE2,\u0017\u0002\u0002Ck\t\u001f\u0014!\u0002T5ti\n+hMZ3s!\u001dYA\u0011\\\u0013=\u0005+L1\u0001b7\u0007\u0005\u0019!V\u000f\u001d7fg!IAq\u001cCWA\u0003%A1Z\u0001\u0005EV4\u0007\u0005\u0003\u0005\u0005d\u00125F\u0011\u0001Cs\u0003\u001dq\u0017-\\3Q_N$bA!6\u0005h\u0012%\bbBA2\tC\u0004\r\u0001\u0010\u0005\u0007I\u0011\u0005\b\u0019A\u0013\t\u0011\u00115HQ\u0016C!\t_\f\u0001\u0002\u001e:bm\u0016\u00148/\u001a\u000b\u0005\tc$9\u0010E\u0002\f\tgL1\u0001\">\u0007\u0005\u0011)f.\u001b;\t\u000f\u0005\rD1\u001ea\u0001y!A1q\u0014CW\t\u0003!Y\u0010\u0006\u0003\u0005~\u0012}\bC\u0002Bt\u0005c$9\u000eC\u0004\u0002d\u0011e\b\u0019\u0001\u001f\t\u000f\u0015\r\u0001\u0001\"\u0003\u0006\u0006\u0005aq-\u001a;WCJL\u0017M\u00197fgR!QqAC\u0005!\u0011\u0011U\tb6\t\u000f\u0005\rT\u0011\u0001a\u0001y\u001d9QQ\u0002\u0001\t\u0002\u0015=\u0011!\u00059biZ\f'\u000f\u0016:b]N4wN]7feB\u0019a$\"\u0005\u0007\u000f\u0015M\u0001\u0001#\u0001\u0006\u0016\t\t\u0002/\u0019;wCJ$&/\u00198tM>\u0014X.\u001a:\u0014\t\u0015EQq\u0003\t\u0004;\u0015e\u0011\u0002BC\u000e\ts\u00131\u0002\u0016:b]N4wN]7fe\"9q\"\"\u0005\u0005\u0002\u0015}ACAC\b\u0011!)\u0019#\"\u0005\u0005B\u0015\u0015\u0012!\u0003;sC:\u001chm\u001c:n)\raTq\u0005\u0005\b\u0003G*\t\u00031\u0001=\u0011\u001d)Y\u0003\u0001C\u0001\u000b[\t1\"\\6V]\u000eDWmY6fIR\u0019A(b\f\t\u000f\u0015ER\u0011\u0006a\u0001y\u0005!Q\r\u001f9s\u0011\u001d))\u0004\u0001C\u0001\u000bo\t\u0001#\\6Ts:$\b.\u001a;jGB\u000b'/Y7\u0015\t\tUV\u0011\b\u0005\t\u000bw)\u0019\u00041\u0001\u0006>\u0005)\u0001O\\1nKB\u0019Q$b\u0010\n\u0007\u0015\u0005\u0003F\u0001\u0005UKJlg*Y7f\u0011\u001d))\u0005\u0001C\u0001\u000b\u000f\na!\\6DCN$H#\u0002\u001f\u0006J\u0015-\u0003bBA2\u000b\u0007\u0002\r\u0001\u0010\u0005\b\u0005#)\u0019\u00051\u0001_\u0011%)y\u0005AI\u0001\n\u0003)\t&A\nnWB\u000b'/\u001a8ug\u0012\"WMZ1vYR$3'\u0006\u0002\u0006T)\"!Q[C+W\t)9\u0006\u0005\u0003\u0006Z\u0015\rTBAC.\u0015\u0011)i&b\u0018\u0002\u0013Ut7\r[3dW\u0016$'bAC1\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0015\u0015T1\f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007\"CC5\u0001E\u0005I\u0011AC)\u0003Qi7\u000eV3na2\fG/\u001a\u0013eK\u001a\fW\u000f\u001c;%m!IQQ\u000e\u0001\u0012\u0002\u0013\u0005Q\u0011K\u0001\u001a[.\u0004\u0016mY6bO\u0016|%M[3di\u0012\"WMZ1vYR$#\u0007C\u0005\u0006r\u0001\t\n\u0011\"\u0001\u0006R\u0005IRn\u001b)bG.\fw-Z(cU\u0016\u001cG\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0011%))\bAI\u0001\n\u0003)9(A\tnWR+\b\u000f\\3%I\u00164\u0017-\u001e7uII*\"!\"\u001f+\u00071+)\u0006C\u0005\u0006~\u0001\t\n\u0011\"\u0001\u0006x\u0005)Rn\u001b+va2,G+\u001f9fI\u0011,g-Y;mi\u0012\u0012\u0004\"CCA\u0001E\u0005I\u0011AC<\u0003Ei7N\u00117pG.$C-\u001a4bk2$HE\r\u0005\n\u000b\u000b\u0003\u0011\u0013!C\u0001\u000b\u000f\u000b1#\\6WSNLGo\u001c:%I\u00164\u0017-\u001e7uIM*\"!\"#+\t\u0011}UQ\u000b\u0005\n\u000b\u001b\u0003\u0011\u0013!C\u0001\u000bo\nQd]2bY\u00064UO\\2uS>t7i\u001c8tiJ$C-\u001a4bk2$He\r\u0005\n\u000b#\u0003\u0011\u0013!C\u0001\u000bo\n\u0001$\\6Jg&s7\u000f^1oG\u0016|e\r\n3fM\u0006,H\u000e\u001e\u00134\u0011%))\nAI\u0001\n\u0003)9(\u0001\rnW&\u001b\u0018J\\:uC:\u001cWm\u00144%I\u00164\u0017-\u001e7uIQB\u0011\"\"'\u0001#\u0003%\t!b\u001e\u000215\\\u0017i]%ogR\fgnY3PM\u0012\"WMZ1vYR$3\u0007C\u0005\u0006\u001e\u0002\t\n\u0011\"\u0001\u0006x\u0005ARn[!t\u0013:\u001cH/\u00198dK>3G\u0005Z3gCVdG\u000f\n\u001b\t\u0013\u0015\u0005\u0006!%A\u0005\u0002\u0015]\u0014!H7bs\n,Wj[!t\u0013:\u001cH/\u00198dK>3G\u0005Z3gCVdG\u000f\n\u001b")
public abstract class TreeGen {
    private volatile TreeGen$ValFrom$ ValFrom$module;
    private volatile TreeGen$ValEq$ ValEq$module;
    private volatile TreeGen$Filter$ Filter$module;
    private volatile TreeGen$Yield$ Yield$module;
    private volatile TreeGen$patvarTransformer$ patvarTransformer$module;

    private TreeGen$ValFrom$ ValFrom$lzycompute() {
        TreeGen treeGen = this;
        synchronized (treeGen) {
            if (this.ValFrom$module == null) {
                this.ValFrom$module = new TreeGen$ValFrom$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValFrom$module;
        }
    }

    private TreeGen$ValEq$ ValEq$lzycompute() {
        TreeGen treeGen = this;
        synchronized (treeGen) {
            if (this.ValEq$module == null) {
                this.ValEq$module = new TreeGen$ValEq$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.ValEq$module;
        }
    }

    private TreeGen$Filter$ Filter$lzycompute() {
        TreeGen treeGen = this;
        synchronized (treeGen) {
            if (this.Filter$module == null) {
                this.Filter$module = new TreeGen$Filter$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Filter$module;
        }
    }

    private TreeGen$Yield$ Yield$lzycompute() {
        TreeGen treeGen = this;
        synchronized (treeGen) {
            if (this.Yield$module == null) {
                this.Yield$module = new TreeGen$Yield$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.Yield$module;
        }
    }

    private TreeGen$patvarTransformer$ patvarTransformer$lzycompute() {
        TreeGen treeGen = this;
        synchronized (treeGen) {
            if (this.patvarTransformer$module == null) {
                this.patvarTransformer$module = new TreeGen$patvarTransformer$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.patvarTransformer$module;
        }
    }

    public abstract SymbolTable global();

    public Trees.Select rootId(Names.Name name) {
        return new Trees.Select(this.global(), new Trees.Ident(this.global(), this.global().nme().ROOTPKG()), name);
    }

    public Trees.Select rootScalaDot(Names.Name name) {
        return new Trees.Select(this.global(), this.rootId(this.global().nme().scala_()).setSymbol(this.global().definitions().ScalaPackage()), name);
    }

    public Trees.Select scalaDot(Names.Name name) {
        return new Trees.Select(this.global(), new Trees.Ident(this.global(), this.global().nme().scala_()).setSymbol(this.global().definitions().ScalaPackage()), name);
    }

    public Trees.Select scalaAnnotationDot(Names.Name name) {
        return new Trees.Select(this.global(), this.scalaDot(this.global().nme().annotation()), name);
    }

    public Trees.Select scalaAnyRefConstr() {
        return this.scalaDot(this.global().tpnme().AnyRef());
    }

    public Trees.Tree scalaFunctionConstr(List<Trees.Tree> argtpes, Trees.Tree restpe, boolean abstractFun) {
        Trees.RefTree cls = abstractFun ? this.mkAttributedRef(this.global().definitions().AbstractFunctionClass().apply(argtpes.length())) : this.mkAttributedRef(this.global().definitions().FunctionClass().apply(argtpes.length()));
        return new Trees.AppliedTypeTree(this.global(), (Trees.Tree)((Object)cls), argtpes.$colon$plus(restpe, List$.MODULE$.canBuildFrom()));
    }

    public boolean scalaFunctionConstr$default$3() {
        return false;
    }

    public Trees.Tree mkMethodCall(Symbols.Symbol receiver, Names.Name methodName, List<Types.Type> targs, List<Trees.Tree> args) {
        return this.mkMethodCall(new Trees.Select(this.global(), (Trees.Tree)((Object)this.mkAttributedRef(receiver)), methodName), targs, args);
    }

    public Trees.Tree mkMethodCall(Symbols.Symbol method, List<Types.Type> targs, List<Trees.Tree> args) {
        return this.mkMethodCall((Trees.Tree)((Object)this.mkAttributedRef(method)), targs, args);
    }

    public Trees.Tree mkMethodCall(Symbols.Symbol method, List<Trees.Tree> args) {
        return this.mkMethodCall(method, (List<Types.Type>)Nil$.MODULE$, args);
    }

    public Trees.Tree mkMethodCall(Trees.Tree target, List<Trees.Tree> args) {
        return this.mkMethodCall(target, (List<Types.Type>)Nil$.MODULE$, args);
    }

    public Trees.Tree mkMethodCall(Symbols.Symbol receiver, Names.Name methodName, List<Trees.Tree> args) {
        return this.mkMethodCall(receiver, methodName, (List<Types.Type>)Nil$.MODULE$, args);
    }

    public Trees.Tree mkMethodCall(Trees.Tree receiver, Symbols.Symbol method, List<Types.Type> targs, List<Trees.Tree> args) {
        return this.mkMethodCall(this.global().Select(receiver, method), targs, args);
    }

    public Trees.Tree mkMethodCall(Trees.Tree target, List<Types.Type> targs, List<Trees.Tree> args) {
        return new Trees.Apply(this.global(), this.mkTypeApply(target, this.global().mapList(targs, new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeGen $outer;

            public final Trees.TypeTree apply(Types.Type tp) {
                return this.$outer.global().TypeTree(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        })), args);
    }

    public Trees.Tree mkNullaryCall(Symbols.Symbol method, List<Types.Type> targs) {
        return this.mkTypeApply((Trees.Tree)((Object)this.mkAttributedRef(method)), this.global().mapList(targs, new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeGen $outer;

            public final Trees.TypeTree apply(Types.Type tp) {
                return this.$outer.global().TypeTree(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public Trees.Tree mkAttributedQualifier(Types.Type tpe) {
        return this.mkAttributedQualifier(tpe, this.global().NoSymbol());
    }

    public Trees.Tree mkAttributedQualifier(Types.Type tpe, Symbols.Symbol termSym) {
        block21: {
            Trees.Tree tree;
            block15: {
                block20: {
                    block19: {
                        block18: {
                            Trees.Tree tree2;
                            block17: {
                                block16: {
                                    block14: {
                                        if (!this.global().NoPrefix().equals(tpe)) break block14;
                                        tree = this.global().EmptyTree();
                                        break block15;
                                    }
                                    if (!(tpe instanceof Types.ThisType)) break block16;
                                    Types.ThisType thisType = (Types.ThisType)tpe;
                                    tree = thisType.sym().isEffectiveRoot() ? this.global().EmptyTree() : this.mkAttributedThis(thisType.sym());
                                    break block15;
                                }
                                if (!(tpe instanceof Types.SingleType)) break block17;
                                Types.SingleType singleType = (Types.SingleType)tpe;
                                tree = this.mkApplyIfNeeded(this.mkAttributedStableRef(singleType.pre(), singleType.sym()));
                                break block15;
                            }
                            if (!(tpe instanceof Types.TypeRef)) break block18;
                            Types.TypeRef typeRef = (Types.TypeRef)tpe;
                            if (typeRef.sym().isRoot()) {
                                tree2 = this.mkAttributedThis(typeRef.sym());
                            } else if (typeRef.sym().isModuleClass()) {
                                tree2 = this.mkApplyIfNeeded((Trees.Tree)((Object)this.mkAttributedRef(typeRef.pre(), typeRef.sym().sourceModule())));
                            } else if (typeRef.sym().isModule() || typeRef.sym().isClass()) {
                                boolean bl = this.global().phase().erasedTypes();
                                Predef$ predef$ = Predef$.MODULE$;
                                if (!bl) {
                                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)this.scala$reflect$internal$TreeGen$$failMessage$1(tpe, termSym)).toString());
                                }
                                tree2 = this.mkAttributedThis(typeRef.sym());
                            } else if (typeRef.sym().isType()) {
                                Symbols.Symbol symbol = termSym;
                                Symbols.NoSymbol noSymbol = this.global().NoSymbol();
                                boolean bl = symbol != null ? !symbol.equals(noSymbol) : noSymbol != null;
                                Predef$ predef$ = Predef$.MODULE$;
                                if (!bl) {
                                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)this.scala$reflect$internal$TreeGen$$failMessage$1(tpe, termSym)).toString());
                                }
                                tree2 = ((Trees.Tree)((Object)this.mkAttributedIdent(termSym))).setType(tpe);
                            } else {
                                tree2 = (Trees.Tree)((Object)this.mkAttributedRef(typeRef.pre(), typeRef.sym()));
                            }
                            tree = tree2;
                            break block15;
                        }
                        if (!(tpe instanceof Types.ConstantType)) break block19;
                        Types.ConstantType constantType = (Types.ConstantType)tpe;
                        tree = new Trees.Literal(this.global(), constantType.value()).setType(tpe);
                        break block15;
                    }
                    if (!(tpe instanceof Types.AnnotatedType)) break block20;
                    Types.AnnotatedType annotatedType = (Types.AnnotatedType)tpe;
                    tree = this.mkAttributedQualifier(annotatedType.underlying());
                    break block15;
                }
                if (!(tpe instanceof Types.RefinedType)) break block21;
                Types.RefinedType refinedType = (Types.RefinedType)tpe;
                Option<Types.Type> firstStable = refinedType.parents().find((Function1<Types.Type, Object>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final boolean apply(Types.Type x$1) {
                        return x$1.isStable();
                    }
                }));
                boolean bl = !firstStable.isEmpty();
                Predef$ predef$ = Predef$.MODULE$;
                if (!bl) {
                    throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)this.scala$reflect$internal$TreeGen$$failMessage$1(tpe, termSym)).append((Object)" parents = ").append(refinedType.parents()).toString()).toString());
                }
                tree = this.mkAttributedQualifier(firstStable.get());
            }
            return tree;
        }
        throw this.global().abort(new StringBuilder().append((Object)"bad qualifier received: ").append((Object)this.scala$reflect$internal$TreeGen$$failMessage$1(tpe, termSym)).toString());
    }

    public Trees.Tree mkApplyIfNeeded(Trees.Tree qual) {
        Types.MethodType methodType;
        Types.Type type = qual.tpe();
        Trees.Tree tree = type instanceof Types.MethodType && ((Object)Nil$.MODULE$).equals((methodType = (Types.MethodType)type).params()) ? this.global().atPos(qual.pos(), new Trees.Apply(this.global(), qual, Nil$.MODULE$).setType(methodType.resultType())) : qual;
        return tree;
    }

    public Trees.RefTree mkAttributedRef(Types.Type pre, Symbols.Symbol sym) {
        Trees.Tree qual = this.mkAttributedQualifier(pre);
        Trees.RefTree refTree = ((Object)this.global().EmptyTree()).equals(qual) ? this.mkAttributedIdent(sym) : (qual instanceof Trees.This && qual.symbol().isEffectiveRoot() ? this.mkAttributedIdent(sym) : this.mkAttributedSelect(qual, sym));
        return refTree;
    }

    public Trees.RefTree mkAttributedRef(Symbols.Symbol sym) {
        return sym.owner().isClass() ? this.mkAttributedRef(sym.owner().thisType(), sym) : this.mkAttributedIdent(sym);
    }

    public Trees.RefTree mkUnattributedRef(Symbols.Symbol sym) {
        return this.mkUnattributedRef(sym.fullNameAsName('.'));
    }

    public Trees.RefTree mkUnattributedRef(Names.Name fullName) {
        List<Names.Name> list2 = this.global().nme().segments(fullName.toString(), fullName.isTermName());
        if (list2 instanceof $colon$colon) {
            $colon$colon $colon$colon = ($colon$colon)list2;
            Tuple2 tuple2 = new Tuple2($colon$colon.head(), $colon$colon.tl$1());
            Names.Name hd = (Names.Name)tuple2._1();
            List<Trees.Select> tl = tuple2._2();
            return tl.foldLeft(new Trees.Ident(this.global(), hd), new Serializable(this){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ TreeGen $outer;

                public final Trees.Select apply(Trees.RefTree x$3, Names.Name x$4) {
                    return new Trees.Select(this.$outer.global(), (Trees.Tree)((Object)x$3), x$4);
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            });
        }
        throw new MatchError(list2);
    }

    public Trees.Tree stabilize(Trees.Tree tree) {
        Types.Type type = this.stableTypeFor(tree);
        Trees.Tree tree2 = this.global().NoType().equals(type) ? tree : tree.setType(type);
        return tree2;
    }

    public Types.Type stableTypeFor(Trees.Tree tree) {
        Types.Type type;
        if (this.global().treeInfo().admitsTypeSelection(tree)) {
            Types.Type type2;
            if (tree instanceof Trees.This) {
                type2 = this.global().ThisType().apply(tree.symbol());
            } else if (tree instanceof Trees.Ident) {
                type2 = this.global().singleType(tree.symbol().owner().thisType(), tree.symbol());
            } else if (tree instanceof Trees.Select) {
                Trees.Select select = (Trees.Select)tree;
                type2 = this.global().singleType(select.qualifier().tpe(), tree.symbol());
            } else {
                type2 = this.global().NoType();
            }
            type = type2;
        } else {
            type = this.global().NoType();
        }
        return type;
    }

    public Trees.Tree mkAttributedStableRef(Types.Type pre, Symbols.Symbol sym) {
        return this.stabilize((Trees.Tree)((Object)this.mkAttributedRef(pre, sym)));
    }

    public Trees.Tree mkAttributedStableRef(Symbols.Symbol sym) {
        return this.stabilize((Trees.Tree)((Object)this.mkAttributedRef(sym)));
    }

    public Trees.This mkAttributedThis(Symbols.Symbol sym) {
        return (Trees.This)new Trees.This(this.global(), sym.name().toTypeName()).setSymbol(sym).setType(sym.thisType());
    }

    public Trees.RefTree mkAttributedIdent(Symbols.Symbol sym) {
        return (Trees.RefTree)((Object)new Trees.Ident(this.global(), sym.name()).setSymbol(sym).setType(sym.tpeHK()));
    }

    public Trees.RefTree mkAttributedSelect(Trees.Tree qual, Symbols.Symbol sym) {
        Trees.RefTree refTree;
        if (qual.symbol() != null && (qual.symbol().isEffectiveRoot() || qual.symbol().isEmptyPackage())) {
            refTree = this.mkAttributedIdent(sym);
        } else {
            Trees.Tree tree;
            boolean needsPackageQualifier;
            Symbols.Symbol qualsym = qual.tpe() != null ? qual.tpe().typeSymbol() : (qual.symbol() != null ? qual.symbol() : this.global().NoSymbol());
            boolean bl = needsPackageQualifier = sym != null && qualsym.hasPackageFlag() && !sym.isDefinedInPackage() && !sym.moduleClass().isDefinedInPackage();
            if (needsPackageQualifier) {
                Symbols.Symbol packageObject = ((Mirrors.RootsBase)this.global().rootMirror()).getPackageObjectWithMember(qual.tpe(), sym);
                tree = new Trees.Select(this.global(), qual, this.global().nme().PACKAGE()).setSymbol(packageObject).setType(this.global().singleType(qual.tpe(), packageObject));
            } else {
                tree = qual;
            }
            Trees.Tree pkgQualifier = tree;
            Trees.Select tree2 = this.global().Select(pkgQualifier, sym);
            refTree = pkgQualifier.tpe() == null ? tree2 : (Trees.RefTree)((Object)tree2.setType(qual.tpe().memberType(sym)));
        }
        return refTree;
    }

    public Trees.Tree mkTypeApply(Trees.Tree fun, List<Trees.Tree> targs) {
        return targs.isEmpty() ? fun : new Trees.TypeApply(this.global(), fun, targs);
    }

    public Trees.Tree mkAppliedTypeTree(Trees.Tree fun, List<Trees.Tree> targs) {
        return targs.isEmpty() ? fun : new Trees.AppliedTypeTree(this.global(), fun, targs);
    }

    public Trees.Tree mkAttributedTypeApply(Trees.Tree target, Symbols.Symbol method, List<Types.Type> targs) {
        return this.mkTypeApply((Trees.Tree)((Object)this.mkAttributedSelect(target, method)), targs.map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeGen $outer;

            public final Trees.TypeTree apply(Types.Type tp) {
                return this.$outer.global().TypeTree(tp);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }, List$.MODULE$.canBuildFrom()));
    }

    private Trees.Tree mkSingleTypeApply(Trees.Tree value, Types.Type tpe, Symbols.Symbol what, boolean wrapInApply) {
        Types.Type type = tpe.dealias();
        Trees.Tree tapp = this.mkAttributedTypeApply(value, what, Nil$.MODULE$.$colon$colon(type));
        return wrapInApply ? new Trees.Apply(this.global(), tapp, Nil$.MODULE$) : tapp;
    }

    private Symbols.MethodSymbol typeTestSymbol(boolean any) {
        return any ? this.global().definitions().Any_isInstanceOf() : this.global().definitions().Object_isInstanceOf();
    }

    private Symbols.MethodSymbol typeCastSymbol(boolean any) {
        return any ? this.global().definitions().Any_asInstanceOf() : this.global().definitions().Object_asInstanceOf();
    }

    public Trees.Tree mkIsInstanceOf(Trees.Tree value, Types.Type tpe, boolean any, boolean wrapInApply) {
        return this.mkSingleTypeApply(value, tpe, this.typeTestSymbol(any), wrapInApply);
    }

    public boolean mkIsInstanceOf$default$3() {
        return true;
    }

    public boolean mkIsInstanceOf$default$4() {
        return true;
    }

    public Trees.Tree mkAsInstanceOf(Trees.Tree value, Types.Type tpe, boolean any, boolean wrapInApply) {
        return this.mkSingleTypeApply(value, tpe, this.typeCastSymbol(any), wrapInApply);
    }

    public boolean mkAsInstanceOf$default$3() {
        return true;
    }

    public boolean mkAsInstanceOf$default$4() {
        return true;
    }

    public Trees.Tree maybeMkAsInstanceOf(Trees.Tree tree, Types.Type pt, Types.Type tpe, boolean beforeRefChecks) {
        Types.Type type = pt;
        Types.Type type2 = this.global().definitions().UnitTpe();
        return !(type == null ? type2 != null : !type.equals(type2)) || tpe.$less$colon$less(pt) ? tree : this.global().atPos(tree.pos(), this.mkAsInstanceOf(tree, pt, true, !beforeRefChecks));
    }

    public boolean maybeMkAsInstanceOf$default$4() {
        return false;
    }

    public Trees.Tree mkClassOf(Types.Type tp) {
        return new Trees.Literal(this.global(), new Constants.Constant(this.global(), tp)).setType(this.global().ConstantType().apply(new Constants.Constant(this.global(), tp)));
    }

    public Trees.Tree mkNil() {
        return (Trees.Tree)((Object)this.mkAttributedRef(this.global().definitions().NilModule()));
    }

    public Trees.Tree mkZero(Types.Type tp) {
        Symbols.Symbol symbol = tp.typeSymbol();
        Trees.Tree tree = this.global().definitions().NothingClass().equals(symbol) ? this.mkMethodCall(this.global().definitions().Predef_$qmark$qmark$qmark(), (List<Trees.Tree>)Nil$.MODULE$).setType(this.global().definitions().NothingTpe()) : new Trees.Literal(this.global(), this.mkConstantZero(tp)).setType(tp);
        return tree;
    }

    public Constants.Constant mkConstantZero(Types.Type tp) {
        Constants.Constant constant;
        Symbols.Symbol symbol = tp.typeSymbol();
        Symbols.ClassSymbol classSymbol = this.global().definitions().UnitClass();
        if (!(classSymbol != null ? !classSymbol.equals(symbol) : symbol != null)) {
            constant = new Constants.Constant(this.global(), BoxedUnit.UNIT);
        } else {
            Symbols.ClassSymbol classSymbol2 = this.global().definitions().BooleanClass();
            if (!(classSymbol2 != null ? !classSymbol2.equals(symbol) : symbol != null)) {
                constant = new Constants.Constant(this.global(), BoxesRunTime.boxToBoolean(false));
            } else {
                Symbols.ClassSymbol classSymbol3 = this.global().definitions().FloatClass();
                if (!(classSymbol3 != null ? !classSymbol3.equals(symbol) : symbol != null)) {
                    constant = new Constants.Constant(this.global(), BoxesRunTime.boxToFloat(0.0f));
                } else {
                    Symbols.ClassSymbol classSymbol4 = this.global().definitions().DoubleClass();
                    if (!(classSymbol4 != null ? !classSymbol4.equals(symbol) : symbol != null)) {
                        constant = new Constants.Constant(this.global(), BoxesRunTime.boxToDouble(0.0));
                    } else {
                        Symbols.ClassSymbol classSymbol5 = this.global().definitions().ByteClass();
                        if (!(classSymbol5 != null ? !classSymbol5.equals(symbol) : symbol != null)) {
                            constant = new Constants.Constant(this.global(), BoxesRunTime.boxToByte((byte)0));
                        } else {
                            Symbols.ClassSymbol classSymbol6 = this.global().definitions().ShortClass();
                            if (!(classSymbol6 != null ? !classSymbol6.equals(symbol) : symbol != null)) {
                                constant = new Constants.Constant(this.global(), BoxesRunTime.boxToShort((short)0));
                            } else {
                                Symbols.ClassSymbol classSymbol7 = this.global().definitions().IntClass();
                                if (!(classSymbol7 != null ? !classSymbol7.equals(symbol) : symbol != null)) {
                                    constant = new Constants.Constant(this.global(), BoxesRunTime.boxToInteger(0));
                                } else {
                                    Symbols.ClassSymbol classSymbol8 = this.global().definitions().LongClass();
                                    if (!(classSymbol8 != null ? !classSymbol8.equals(symbol) : symbol != null)) {
                                        constant = new Constants.Constant(this.global(), BoxesRunTime.boxToLong(0L));
                                    } else {
                                        Symbols.ClassSymbol classSymbol9 = this.global().definitions().CharClass();
                                        constant = !(classSymbol9 != null ? !classSymbol9.equals(symbol) : symbol != null) ? new Constants.Constant(this.global(), BoxesRunTime.boxToCharacter((char)0)) : new Constants.Constant(this.global(), null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return constant;
    }

    public Trees.Tree mkNamedArg(Names.Name name, Trees.Tree tree) {
        return this.mkNamedArg(new Trees.Ident(this.global(), name), tree);
    }

    public Trees.Tree mkNamedArg(Trees.Tree lhs, Trees.Tree rhs) {
        return this.global().atPos(rhs.pos(), new Trees.AssignOrNamedArg(this.global(), lhs, rhs));
    }

    public Trees.Tree mkTuple(List<Trees.Tree> elems, boolean flattenUnary) {
        $colon$colon $colon$colon;
        Trees.Tree tree = ((Object)Nil$.MODULE$).equals(elems) ? new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxedUnit.UNIT)) : (elems instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)elems).tl$1()) && flattenUnary ? (Trees.Tree)$colon$colon.head() : new Trees.Apply(this.global(), this.scalaDot(this.global().definitions().TupleClass().apply(elems.length()).name().toTermName()), elems));
        return tree;
    }

    public boolean mkTuple$default$2() {
        return true;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public Trees.Tree mkTupleType(List<Trees.Tree> elems, boolean flattenUnary) {
        void var5_6;
        if (((Object)Nil$.MODULE$).equals(elems)) {
            Trees.Select select = this.scalaDot(this.global().tpnme().Unit());
            return var5_6;
        }
        Some<List<Trees.Tree>> some = List$.MODULE$.unapplySeq(elems);
        if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0) {
            Trees.Tree tree = (Trees.Tree)((LinearSeqOptimized)some.get()).apply(0);
            if (flattenUnary) {
                Trees.Tree tree2 = tree;
                return var5_6;
            }
        }
        Trees.AppliedTypeTree appliedTypeTree = new Trees.AppliedTypeTree(this.global(), this.scalaDot(this.global().definitions().TupleClass().apply(elems.length()).name()), elems);
        return var5_6;
    }

    public boolean mkTupleType$default$2() {
        return true;
    }

    public Trees.Tree mkAnd(Trees.Tree tree1, Trees.Tree tree2) {
        return new Trees.Apply(this.global(), this.global().Select(tree1, this.global().definitions().Boolean_and()), (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{tree2})));
    }

    public Trees.Tree mkOr(Trees.Tree tree1, Trees.Tree tree2) {
        return new Trees.Apply(this.global(), this.global().Select(tree1, this.global().definitions().Boolean_or()), (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{tree2})));
    }

    public Trees.Tree mkRuntimeUniverseRef() {
        Symbols.Symbol symbol = this.global().definitions().ReflectRuntimeUniverse();
        Symbols.NoSymbol noSymbol = this.global().NoSymbol();
        Predef$.MODULE$.assert(symbol != null ? !symbol.equals(noSymbol) : noSymbol != null);
        return ((Trees.Tree)((Object)this.mkAttributedRef(this.global().definitions().ReflectRuntimeUniverse()))).setType(this.global().singleType(this.global().definitions().ReflectRuntimeUniverse().owner().thisPrefix(), this.global().definitions().ReflectRuntimeUniverse()));
    }

    public Trees.Apply mkSeqApply(Trees.Tree arg) {
        Trees.Select factory = new Trees.Select(this.global(), (Trees.Tree)((Object)this.mkAttributedRef(this.global().definitions().SeqModule())), this.global().nme().apply());
        return new Trees.Apply(this.global(), factory, (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{arg})));
    }

    public Trees.Select mkSuperInitCall() {
        return new Trees.Select(this.global(), new Trees.Super(this.global(), new Trees.This(this.global(), (Names.TypeName)this.global().tpnme().EMPTY()), (Names.TypeName)this.global().tpnme().EMPTY()), this.global().nme().CONSTRUCTOR());
    }

    public Trees.Template mkTemplate(List<Trees.Tree> parents2, Trees.ValDef self, Trees.Modifiers constrMods, List<List<Trees.ValDef>> vparamss, List<Trees.Tree> body2, Position superPos) {
        List<List<Trees.ValDef>> vparamss1 = this.global().mmap(vparamss, new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeGen $outer;

            /*
             * WARNING - void declaration
             */
            public final Trees.ValDef apply(Trees.ValDef vd) {
                void var3_3;
                Trees.Modifiers mods = (Trees.Modifiers)this.$outer.global().Modifiers(BoxesRunTime.boxToLong(vd.mods().flags() & 0x2010200L | 0x2000L | 0x20000000L));
                Trees.ValDef param2 = this.$outer.global().atPos(vd.pos().makeTransparent(), new Trees.ValDef(this.$outer.global(), mods.withAnnotations(vd.mods().annotations()), vd.name(), vd.tpt().duplicate(), this.$outer.global().duplicateAndKeepPositions(vd.rhs())));
                return var3_3;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
        ListBuffer listBuffer = new ListBuffer();
        List list2 = body2;
        while (true) {
            block10: {
                block9: {
                    if (((AbstractIterable)list2).isEmpty()) break block9;
                    Trees.Tree tree = (Trees.Tree)((AbstractIterable)list2).head();
                    if (this.global().treeInfo().isEarlyDef(tree)) break block10;
                }
                Tuple2 tuple2 = new Tuple2(listBuffer.toList(), list2);
                Tuple2 tuple22 = new Tuple2(tuple2._1(), tuple2._2());
                List<Trees.Tree> edefs = tuple22._1();
                List<Trees.Tree> rest = tuple22._2();
                Tuple2<Traversable<Trees.Tree>, Traversable<Trees.Tree>> tuple23 = edefs.partition((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreeGen $outer;

                    public final boolean apply(Trees.Tree tree) {
                        return this.$outer.global().treeInfo().isEarlyValDef(tree);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
                if (tuple23 != null) {
                    Option constr;
                    Option option;
                    Tuple2<Traversable<Trees.Tree>, Traversable<Trees.Tree>> tuple24 = new Tuple2<Traversable<Trees.Tree>, Traversable<Trees.Tree>>(tuple23._1(), tuple23._2());
                    List evdefs = (List)tuple24._1();
                    List etdefs = (List)tuple24._2();
                    List gvdefs = evdefs.map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreeGen $outer;

                        public final Trees.ValDef apply(Trees.Tree x0$1) {
                            if (x0$1 instanceof Trees.ValDef) {
                                Trees.ValDef valDef = (Trees.ValDef)x0$1;
                                Trees.TypeTree x$39 = (Trees.TypeTree)this.$outer.global().atPos(valDef.pos().focus(), (Trees.Tree)new Trees.TypeTree(this.$outer.global()).setOriginal(valDef.tpt()).setPos(valDef.tpt().pos().focus()));
                                Trees$EmptyTree$ x$40 = this.$outer.global().EmptyTree();
                                Trees.Modifiers x$41 = this.$outer.global().copyValDef$default$2(valDef);
                                Names.Name x$42 = this.$outer.global().copyValDef$default$3(valDef);
                                return this.$outer.global().copyValDef(valDef, x$41, x$42, x$39, x$40);
                            }
                            throw new MatchError(x0$1);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    List<Trees.Tree> lvdefs = evdefs.collect(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreeGen $outer;

                        public final <A1 extends Trees.Tree, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                            Object object;
                            if (x1 instanceof Trees.ValDef) {
                                Trees.ValDef valDef = (Trees.ValDef)x1;
                                Trees.Modifiers x$44 = valDef.mods().$bar(0x2000000000L);
                                Names.Name x$45 = this.$outer.global().copyValDef$default$3(valDef);
                                Trees.Tree x$46 = this.$outer.global().copyValDef$default$4(valDef);
                                Trees.Tree x$47 = this.$outer.global().copyValDef$default$5(valDef);
                                object = this.$outer.global().copyValDef(valDef, x$44, x$45, x$46, x$47);
                            } else {
                                object = function1.apply(x1);
                            }
                            return object;
                        }

                        public final boolean isDefinedAt(Trees.Tree x1) {
                            boolean bl = x1 instanceof Trees.ValDef;
                            return bl;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    if (constrMods.isTrait()) {
                        option = body2.forall(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;

                            public final boolean apply(Trees.Tree tree) {
                                return this.$outer.global().treeInfo().isInterfaceMember(tree);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }) ? None$.MODULE$ : new Some<Trees.DefDef>(this.global().atPos(this.global().wrappingPos(superPos, lvdefs), new Trees.DefDef(this.global(), (Trees.Modifiers)this.global().NoMods(), this.global().nme().MIXIN_CONSTRUCTOR(), Nil$.MODULE$, package$.MODULE$.ListOfNil(), new Trees.TypeTree(this.global()), new Trees.Block(this.global(), lvdefs, new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxedUnit.UNIT))))));
                    } else {
                        if (vparamss1.isEmpty() || !((SeqLike)vparamss1.head()).isEmpty() && ((Trees.ValDef)((IterableLike)vparamss1.head()).head()).mods().isImplicit()) {
                            Nil$ nil$ = Nil$.MODULE$;
                            vparamss1 = new $colon$colon<List<Trees.ValDef>>(nil$, vparamss1);
                        }
                        Trees$pendingSuperCall$ superCall = this.global().pendingSuperCall();
                        option = constr = new Some<Trees.DefDef>(this.global().atPos(this.global().wrappingPos(superPos, ((List)vparamss1.flatten((Function1)Predef$.MODULE$.$conforms())).$colon$colon$colon(lvdefs)).makeTransparent(), new Trees.DefDef(this.global(), constrMods, this.global().nme().CONSTRUCTOR(), Nil$.MODULE$, vparamss1, new Trees.TypeTree(this.global()), new Trees.Block(this.global(), ((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees$pendingSuperCall$[]{superCall}))).$colon$colon$colon(lvdefs), new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxedUnit.UNIT))))));
                    }
                    if (!constr.isEmpty()) {
                        Trees.DefDef defDef = (Trees.DefDef)constr.get();
                        List<Trees.Tree> x$121 = parents2;
                        this.global().ensureNonOverlapping(defDef, gvdefs.$colon$colon$colon(x$121), false);
                    }
                    List fieldDefs = ((List)vparamss.flatten((Function1)Predef$.MODULE$.$conforms())).map(new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreeGen $outer;

                        public final Trees.ValDef apply(Trees.ValDef vd) {
                            Trees.Modifiers x$49 = vd.mods().$amp$tilde(0x2000000L);
                            Trees$EmptyTree$ x$50 = this.$outer.global().EmptyTree();
                            Names.Name x$51 = this.$outer.global().copyValDef$default$3(vd);
                            Trees.Tree x$52 = this.$outer.global().copyValDef$default$4(vd);
                            Trees.ValDef field2 = this.$outer.global().copyValDef(vd, x$49, x$51, x$52, x$50);
                            if (field2.pos().isRange() && vd.rhs().pos().isRange()) {
                                field2.pos_$eq(field2.pos().withEnd(vd.rhs().pos().start() - 1));
                            }
                            return field2;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    List list3 = etdefs.$plus$plus$colon(Option$.MODULE$.option2Iterable(constr), List$.MODULE$.canBuildFrom());
                    return new Trees.Template(this.global(), parents2, self, rest.$colon$colon$colon(list3).$colon$colon$colon(fieldDefs).$colon$colon$colon(gvdefs));
                }
                throw new MatchError(tuple23);
            }
            listBuffer.$plus$eq(((AbstractIterable)list2).head());
            list2 = (List)list2.tail();
        }
    }

    public Position mkTemplate$default$6() {
        return this.global().NoPosition();
    }

    public List<Trees.Tree> mkParents(Trees.Modifiers ownerMods, List<Trees.Tree> parents2, Position parentPos) {
        List<Trees.Tree> list2;
        if (ownerMods.isCase()) {
            list2 = ((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Select[]{this.scalaDot(this.global().tpnme().Product()), this.scalaDot(this.global().tpnme().Serializable())}))).$colon$colon$colon(parents2);
        } else if (parents2.isEmpty()) {
            Trees.Select select = this.global().atPos(parentPos, this.scalaAnyRefConstr());
            list2 = Nil$.MODULE$.$colon$colon(select);
        } else {
            list2 = parents2;
        }
        return list2;
    }

    public Position mkParents$default$3() {
        return this.global().NoPosition();
    }

    public Trees.ClassDef mkClassDef(Trees.Modifiers mods, Names.TypeName name, List<Trees.TypeDef> tparams2, Trees.Template templ) {
        boolean isInterface = mods.isTrait() && templ.body().forall((Function1<Trees.Tree, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TreeGen $outer;

            public final boolean apply(Trees.Tree tree) {
                return this.$outer.global().treeInfo().isInterfaceMember(tree);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
        Trees.Modifiers mods1 = isInterface ? mods.$bar(128) : mods;
        return new Trees.ClassDef(this.global(), mods1, name, tparams2, templ);
    }

    public Trees.Tree mkNew(List<Trees.Tree> parents2, Trees.ValDef self, List<Trees.Tree> stats, Position npos, Position cpos) {
        Trees.Tree tree;
        if (parents2.isEmpty()) {
            tree = this.mkNew((List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Select[]{this.scalaAnyRefConstr()})), self, stats, npos, cpos);
        } else if (((SeqLike)parents2.tail()).isEmpty() && stats.isEmpty()) {
            TreeInfo.Applied app = this.global().treeInfo().dissectApplied(parents2.head());
            tree = this.global().atPos(npos.union(cpos), this.global().New(app.callee(), app.argss()));
        } else {
            Names.TypeName x = (Names.TypeName)this.global().tpnme().ANON_CLASS_NAME();
            tree = this.global().atPos(npos.union(cpos), new Trees.Block(this.global(), (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.ClassDef[]{this.global().atPos(cpos, new Trees.ClassDef(this.global(), (Trees.Modifiers)this.global().Modifiers(BoxesRunTime.boxToLong(32L)), x, Nil$.MODULE$, this.mkTemplate(parents2, self, (Trees.Modifiers)this.global().NoMods(), package$.MODULE$.ListOfNil(), stats, cpos.focus())))})), this.global().atPos(npos, this.global().New((Trees.Tree)new Trees.Ident(this.global(), x).setPos(npos.focus()), (List<List<Trees.Tree>>)Nil$.MODULE$))));
        }
        return tree;
    }

    public Trees.Tree mkFunctionTypeTree(List<Trees.Tree> argtpes, Trees.Tree restpe) {
        return new Trees.AppliedTypeTree(this.global(), this.rootScalaDot(this.global().newTypeName(new StringBuilder().append((Object)"Function").append(BoxesRunTime.boxToInteger(argtpes.length())).toString())), ((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{restpe}))).$colon$colon$colon(argtpes));
    }

    public Trees.Literal mkSyntheticUnit() {
        return (Trees.Literal)new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxedUnit.UNIT)).updateAttachment(this.global().SyntheticUnitAttachment(), ClassTag$.MODULE$.apply(StdAttachments$SyntheticUnitAttachment$.class));
    }

    public Trees.Tree mkBlock(List<Trees.Tree> stats, boolean doFlatten) {
        return stats.isEmpty() ? this.mkSyntheticUnit() : (stats.last().isTerm() ? (stats.length() == 1 && doFlatten ? stats.head() : new Trees.Block(this.global(), (List)stats.init(), stats.last())) : new Trees.Block(this.global(), stats, this.mkSyntheticUnit()));
    }

    public boolean mkBlock$default$2() {
        return true;
    }

    public Trees.Tree mkTreeOrBlock(List<Trees.Tree> stats) {
        $colon$colon $colon$colon;
        Trees.Tree tree = ((Object)Nil$.MODULE$).equals(stats) ? this.global().EmptyTree() : (stats instanceof $colon$colon && ((Object)Nil$.MODULE$).equals(($colon$colon = ($colon$colon)stats).tl$1()) ? (Trees.Tree)$colon$colon.head() : this.mkBlock(stats, this.mkBlock$default$2()));
        return tree;
    }

    public Trees.Tree mkAssign(Trees.Tree lhs, Trees.Tree rhs) {
        Trees.Tree tree;
        if (lhs instanceof Trees.Apply) {
            Trees.Apply apply2 = (Trees.Apply)lhs;
            tree = new Trees.Apply(this.global(), this.global().atPos(apply2.fun().pos(), new Trees.Select(this.global(), apply2.fun(), this.global().nme().update())), apply2.args().$colon$plus(rhs, List$.MODULE$.canBuildFrom()));
        } else {
            tree = new Trees.Assign(this.global(), lhs, rhs);
        }
        return tree;
    }

    public Trees.PackageDef mkPackageObject(Trees.ModuleDef defn, Position pidPos, Position pkgPos) {
        Names.TermName x$35 = this.global().nme().PACKAGEkw();
        Trees.Modifiers x$36 = this.global().copyModuleDef$default$2(defn);
        Trees.Template x$37 = this.global().copyModuleDef$default$4(defn);
        Trees.ModuleDef module = this.global().copyModuleDef(defn, x$36, x$35, x$37);
        Trees.Ident pid = this.global().atPos(pidPos, new Trees.Ident(this.global(), defn.name()));
        return this.global().atPos(pkgPos, new Trees.PackageDef(this.global(), pid, Nil$.MODULE$.$colon$colon(module)));
    }

    public Position mkPackageObject$default$2() {
        return this.global().NoPosition();
    }

    public Position mkPackageObject$default$3() {
        return this.global().NoPosition();
    }

    public TreeGen$ValFrom$ ValFrom() {
        return this.ValFrom$module == null ? this.ValFrom$lzycompute() : this.ValFrom$module;
    }

    public TreeGen$ValEq$ ValEq() {
        return this.ValEq$module == null ? this.ValEq$lzycompute() : this.ValEq$module;
    }

    public TreeGen$Filter$ Filter() {
        return this.Filter$module == null ? this.Filter$lzycompute() : this.Filter$module;
    }

    public TreeGen$Yield$ Yield() {
        return this.Yield$module == null ? this.Yield$lzycompute() : this.Yield$module;
    }

    public Trees.Tree mkFor(List<Trees.Tree> enums, Trees.Tree sugarBody, FreshNameCreator fresh) {
        Trees.Tree tree;
        block2: {
            block6: {
                Option<Tuple2<Trees.Tree, Trees.Tree>> option;
                $colon$colon $colon$colon;
                boolean bl;
                block5: {
                    Option<Tuple2<Trees.Tree, Trees.Tree>> option2;
                    block4: {
                        Option<Tuple2<Trees.Tree, Trees.Tree>> option3;
                        Trees.Tree body2;
                        Names.TermName flatMapName;
                        block3: {
                            Option<Trees.Tree> option4 = this.Yield().unapply(sugarBody);
                            Tuple3<Names.TermName, Names.TermName, Trees.Tree> tuple3 = option4.isEmpty() ? new Tuple3<Names.TermName, Names.TermName, Trees.Tree>(this.global().nme().foreach(), this.global().nme().foreach(), sugarBody) : new Tuple3<Names.TermName, Names.TermName, Trees.Tree>(this.global().nme().map(), this.global().nme().flatMap(), option4.get());
                            Tuple3<Names.TermName, Names.TermName, Trees.Tree> tuple32 = new Tuple3<Names.TermName, Names.TermName, Trees.Tree>(tuple3._1(), tuple3._2(), tuple3._3());
                            Names.TermName mapName = tuple32._1();
                            flatMapName = tuple32._2();
                            body2 = tuple32._3();
                            bl = false;
                            $colon$colon = null;
                            if (!(enums instanceof $colon$colon)) break block3;
                            bl = true;
                            $colon$colon = ($colon$colon)enums;
                            Option<Tuple2<Trees.Tree, Trees.Tree>> option5 = this.ValFrom().unapply((Trees.Tree)$colon$colon.head());
                            if (option5.isEmpty() || !((Object)Nil$.MODULE$).equals($colon$colon.tl$1())) break block3;
                            tree = this.makeCombination$1(this.closurePos$1((Position)((Trees.Tree)$colon$colon.head()).rawatt().pos(), body2), mapName, option5.get()._2(), option5.get()._1(), body2, fresh);
                            break block2;
                        }
                        if (!bl || (option3 = this.ValFrom().unapply((Trees.Tree)$colon$colon.head())).isEmpty() || !($colon$colon.tl$1() instanceof $colon$colon)) break block4;
                        $colon$colon $colon$colon2 = ($colon$colon)$colon$colon.tl$1();
                        Option<Tuple2<Trees.Tree, Trees.Tree>> option6 = this.ValFrom().unapply((Trees.Tree)$colon$colon2.head());
                        if (option6.isEmpty()) break block4;
                        tree = this.makeCombination$1(this.closurePos$1((Position)((Trees.Tree)$colon$colon.head()).rawatt().pos(), body2), flatMapName, option3.get()._2(), option3.get()._1(), this.mkFor($colon$colon2, sugarBody, fresh), fresh);
                        break block2;
                    }
                    if (!bl || (option2 = this.ValFrom().unapply((Trees.Tree)$colon$colon.head())).isEmpty() || !($colon$colon.tl$1() instanceof $colon$colon)) break block5;
                    $colon$colon $colon$colon3 = ($colon$colon)$colon$colon.tl$1();
                    Option<Trees.Tree> option7 = this.Filter().unapply((Trees.Tree)$colon$colon3.head());
                    if (option7.isEmpty()) break block5;
                    Trees.Tree tree2 = (Trees.Tree)this.ValFrom().apply(option2.get()._1(), this.makeCombination$1(option2.get()._2().pos().union(option7.get().pos()), this.global().nme().withFilter(), option2.get()._2(), option2.get()._1().duplicate(), option7.get(), fresh)).setPos(((Trees.Tree)$colon$colon.head()).pos());
                    List list2 = $colon$colon3.tl$1();
                    tree = this.mkFor(new $colon$colon<Trees.Tree>(tree2, list2), sugarBody, fresh);
                    break block2;
                }
                if (!bl || (option = this.ValFrom().unapply((Trees.Tree)$colon$colon.head())).isEmpty()) break block6;
                Object object = $colon$colon.tl$1().take(this.global().definitions().MaxTupleArity() - 1);
                ListBuffer listBuffer = new ListBuffer();
                Object object2 = object;
                while (true) {
                    block8: {
                        List<Trees.Tree> valeqs;
                        block7: {
                            if (((AbstractIterable)object2).isEmpty()) break block7;
                            Trees.Tree tree3 = (Trees.Tree)((AbstractIterable)object2).head();
                            if (this.ValEq().unapply(tree3).isDefined()) break block8;
                        }
                        Predef$.MODULE$.assert(!(valeqs = listBuffer.toList()).isEmpty());
                        Object rest1 = $colon$colon.tl$1().drop(valeqs.length());
                        List<Trees.Tree> pats = valeqs.map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;

                            public final Trees.Tree apply(Trees.Tree x0$2) {
                                Option<Tuple2<Trees.Tree, Trees.Tree>> option = this.$outer.ValEq().unapply(x0$2);
                                if (option.isEmpty()) {
                                    throw new MatchError(x0$2);
                                }
                                return option.get()._1();
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        List rhss = valeqs.map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;

                            public final Trees.Tree apply(Trees.Tree x0$3) {
                                Option<Tuple2<Trees.Tree, Trees.Tree>> option = this.$outer.ValEq().unapply(x0$3);
                                if (option.isEmpty()) {
                                    throw new MatchError(x0$3);
                                }
                                return option.get()._2();
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        Trees.Tree defpat1 = this.scala$reflect$internal$TreeGen$$makeBind$1(option.get()._1(), fresh);
                        List defpats = pats.map(new Serializable(this, fresh){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;
                            private final FreshNameCreator fresh$1;

                            public final Trees.Tree apply(Trees.Tree pat) {
                                return this.$outer.scala$reflect$internal$TreeGen$$makeBind$1(pat, this.fresh$1);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.fresh$1 = fresh$1;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        Tuple2 tuple2 = new Tuple2(defpats, rhss);
                        Predef$ predef$ = Predef$.MODULE$;
                        Predef$.less.colon.less less2 = Predef$.MODULE$.$conforms();
                        Predef$.less.colon.less less3 = Predef$.MODULE$.$conforms();
                        List<Trees.Tree> pdefs = Tuple2Zipped$.MODULE$.flatMap$extension(new Tuple2(less3.apply(tuple2._1()), less2.apply(tuple2._2())), new Serializable(this, fresh){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;
                            private final FreshNameCreator fresh$1;

                            public final List<Trees.ValDef> apply(Trees.Tree pat, Trees.Tree rhs) {
                                return this.$outer.mkPatDef(pat, rhs, this.fresh$1);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                                this.fresh$1 = fresh$1;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        List<Trees.Tree> ids = new $colon$colon<Trees.Tree>(defpat1, defpats).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;
                            private final /* synthetic */ TreeGen $outer;

                            public final Trees.Tree apply(Trees.Tree pat) {
                                return this.$outer.scala$reflect$internal$TreeGen$$makeValue$1(pat);
                            }
                            {
                                if ($outer == null) {
                                    throw null;
                                }
                                this.$outer = $outer;
                            }
                        }, List$.MODULE$.canBuildFrom());
                        Trees.Tree rhs1 = this.mkFor((List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{(Trees.Tree)this.ValFrom().apply(defpat1, option.get()._2()).setPos(((Trees.Tree)$colon$colon.head()).pos())})), this.Yield().apply((Trees.Tree)new Trees.Block(this.global(), pdefs, this.global().atPos((Position)this.global().wrappingPos((List)ids), this.mkTuple(ids, this.mkTuple$default$2()))).setPos((Position)this.global().wrappingPos((List)pdefs))), fresh);
                        Trees.Tree tree4 = option.get()._1();
                        List<Trees.Tree> allpats = pats.$colon$colon(tree4).map(new Serializable(this){
                            public static final long serialVersionUID = 0L;

                            public final Trees.Tree apply(Trees.Tree x$26) {
                                return x$26.duplicate();
                            }
                        }, List$.MODULE$.canBuildFrom());
                        Position position = ((Trees.Tree)$colon$colon.head()).pos();
                        NoPosition$ noPosition$ = this.global().NoPosition();
                        NoPosition$ pos1 = !(position != null ? !position.equals(noPosition$) : noPosition$ != null) ? this.global().NoPosition() : this.global().rangePos(((Position)((Trees.Tree)$colon$colon.head()).rawatt().pos()).source(), ((Trees.Tree)$colon$colon.head()).pos().start(), ((Trees.Tree)$colon$colon.head()).pos().point(), rhs1.pos().end());
                        Trees.Tree vfrom1 = (Trees.Tree)this.ValFrom().apply(this.global().atPos((Position)this.global().wrappingPos((List)allpats), this.mkTuple(allpats, this.mkTuple$default$2())), rhs1).setPos(pos1);
                        tree = this.mkFor(new $colon$colon<Trees.Tree>(vfrom1, (List<Trees.Tree>)rest1), sugarBody, fresh);
                        break block2;
                    }
                    listBuffer.$plus$eq(((AbstractIterable)object2).head());
                    object2 = (List)((AbstractTraversable)object2).tail();
                }
            }
            tree = this.global().EmptyTree();
        }
        return tree;
    }

    public List<Trees.ValDef> mkPatDef(Trees.Tree pat, Trees.Tree rhs, FreshNameCreator fresh) {
        return this.mkPatDef((Trees.Modifiers)this.global().Modifiers(BoxesRunTime.boxToLong(0L)), pat, rhs, fresh);
    }

    public List<Trees.ValDef> mkPatDef(Trees.Modifiers mods, Trees.Tree pat, Trees.Tree rhs, FreshNameCreator fresh) {
        Option<Tuple2<Names.Name, Trees.Tree>> option;
        block9: {
            GenTraversable genTraversable;
            block8: {
                List<Trees.ValDef> list2;
                Tuple3 tuple3;
                Tuple2<Trees.Tree, Trees.Tree> tuple2;
                Trees.Typed typed;
                block7: {
                    Some some;
                    option = this.matchVarPattern(pat);
                    if (!(option instanceof Some) || (some = (Some)option).x() == null) break block7;
                    genTraversable = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.ValDef[]{this.global().atPos(pat.pos().union(rhs.pos()), new Trees.ValDef(this.global(), mods, ((Names.Name)((Tuple2)some.x())._1()).toTermName(), (Trees.Tree)((Tuple2)some.x())._2(), rhs))}));
                    break block8;
                }
                if (!None$.MODULE$.equals(option)) break block9;
                Trees.Tree rhsUnchecked = this.mkUnchecked(rhs);
                Trees.Tree tree = this.patvarTransformer().transform(pat);
                if (tree instanceof Trees.Typed && !((typed = (Trees.Typed)tree).expr() instanceof Trees.Ident)) {
                    Trees.Tree rhsTypedUnchecked = typed.tpt().isEmpty() ? rhsUnchecked : (Trees.Tree)new Trees.Typed(this.global(), rhsUnchecked, typed.tpt()).setPos(rhs.pos().union(typed.tpt().pos()));
                    tuple2 = new Tuple2<Trees.Tree, Trees.Tree>(typed.expr(), rhsTypedUnchecked);
                } else {
                    tuple2 = new Tuple2<Trees.Tree, Trees.Tree>(tree, rhsUnchecked);
                }
                Tuple2<Trees.Tree, Trees.Tree> tuple22 = new Tuple2<Trees.Tree, Trees.Tree>(tuple2._1(), tuple2._2());
                Trees.Tree pat1 = tuple22._1();
                Trees.Tree rhs1 = tuple22._2();
                List<Tuple3<Names.Name, Trees.Tree, Position>> vars = this.getVariables(pat1);
                Trees.Match matchExpr = this.global().atPos(pat1.pos().union(rhs.pos()).makeTransparent(), new Trees.Match(this.global(), rhs1, (List<Trees.CaseDef>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.CaseDef[]{this.global().atPos(pat1.pos(), new Trees.CaseDef(this.global(), pat1, this.global().EmptyTree(), this.mkTuple(vars.map(new Serializable(this){
                    public static final long serialVersionUID = 0L;

                    public final Names.Name apply(Tuple3<Names.Name, Trees.Tree, Position> x$29) {
                        return x$29._1();
                    }
                }, List$.MODULE$.canBuildFrom()).map(new Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ TreeGen $outer;

                    public final Trees.Ident apply(Names.Name name) {
                        return new Trees.Ident(this.$outer.global(), name);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }, List$.MODULE$.canBuildFrom()), this.mkTuple$default$2())))}))));
                Some<List<Tuple3<Names.Name, Trees.Tree, Position>>> some = List$.MODULE$.unapplySeq(vars);
                if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(1) == 0 && (tuple3 = (Tuple3)((LinearSeqOptimized)some.get()).apply(0)) != null) {
                    list2 = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.ValDef[]{this.global().atPos(pat.pos().union((Position)tuple3._3()).union(rhs.pos()), new Trees.ValDef(this.global(), mods, ((Names.Name)tuple3._1()).toTermName(), (Trees.Tree)tuple3._2(), matchExpr))}));
                } else {
                    Names.TermName tmp = this.global().freshTermName(this.global().freshTermName$default$1(), fresh);
                    Trees.ValDef firstDef = this.global().atPos(matchExpr.pos(), new Trees.ValDef(this.global(), (Trees.Modifiers)this.global().Modifiers(BoxesRunTime.boxToLong(0x400000280004L | mods.flags() & 0x80000000L)), tmp, new Trees.TypeTree(this.global()), matchExpr));
                    IntRef cnt = IntRef.create(0);
                    List restDefs = vars.withFilter((Function1<Tuple3<Names.Name, Trees.Tree, Position>, Object>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;

                        public final boolean apply(Tuple3<Names.Name, Trees.Tree, Position> check$ifrefutable$1) {
                            boolean bl = check$ifrefutable$1 != null;
                            return bl;
                        }
                    })).map(new Serializable(this, mods, tmp, cnt){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ TreeGen $outer;
                        private final Trees.Modifiers mods$1;
                        private final Names.TermName tmp$1;
                        private final IntRef cnt$1;

                        public final Trees.ValDef apply(Tuple3<Names.Name, Trees.Tree, Position> x$30) {
                            if (x$30 != null) {
                                ++this.cnt$1.elem;
                                return this.$outer.global().atPos(x$30._3(), new Trees.ValDef(this.$outer.global(), this.mods$1, x$30._1().toTermName(), x$30._2(), new Trees.Select(this.$outer.global(), new Trees.Ident(this.$outer.global(), this.tmp$1), this.$outer.global().newTermName(new StringBuilder().append((Object)"_").append(BoxesRunTime.boxToInteger(this.cnt$1.elem)).toString()))));
                            }
                            throw new MatchError(x$30);
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                            this.mods$1 = mods$1;
                            this.tmp$1 = tmp$1;
                            this.cnt$1 = cnt$1;
                        }
                    }, List$.MODULE$.canBuildFrom());
                    list2 = restDefs.$colon$colon(firstDef);
                }
                genTraversable = list2;
            }
            return genTraversable;
        }
        throw new MatchError(option);
    }

    public Trees.Tree mkGenerator(Position pos, Trees.Tree pat, boolean valeq, Trees.Tree rhs, FreshNameCreator fresh) {
        Trees.Tree pat1 = this.patvarTransformer().transform(pat);
        return valeq ? (Trees.Tree)this.ValEq().apply(pat1, rhs).setPos(pos) : (Trees.Tree)this.ValFrom().apply(pat1, this.mkCheckIfRefutable(pat1, rhs, fresh)).setPos(pos);
    }

    public Trees.Tree mkCheckIfRefutable(Trees.Tree pat, Trees.Tree rhs, FreshNameCreator fresh) {
        Trees.Tree tree;
        if (this.global().treeInfo().isVarPatternDeep(pat)) {
            tree = rhs;
        } else {
            GenTraversable cases = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.CaseDef[]{new Trees.CaseDef(this.global(), pat.duplicate(), this.global().EmptyTree(), new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxesRunTime.boxToBoolean(true)))), new Trees.CaseDef(this.global(), new Trees.Ident(this.global(), this.global().nme().WILDCARD()), this.global().EmptyTree(), new Trees.Literal(this.global(), new Constants.Constant(this.global(), BoxesRunTime.boxToBoolean(false))))}));
            Trees.Tree visitor = this.mkVisitor((List<Trees.CaseDef>)cases, false, this.global().nme().CHECK_IF_REFUTABLE_STRING(), fresh);
            tree = this.global().atPos(rhs.pos(), new Trees.Apply(this.global(), new Trees.Select(this.global(), rhs, this.global().nme().withFilter()), Nil$.MODULE$.$colon$colon(visitor)));
        }
        return tree;
    }

    private Option<Tuple2<Names.Name, Trees.Tree>> matchVarPattern(Trees.Tree tree) {
        Trees.Typed typed;
        Option option;
        if (tree instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)tree;
            option = new Some<Tuple2<Names.Name, Trees.TypeTree>>(new Tuple2<Names.Name, Trees.TypeTree>(ident.name(), new Trees.TypeTree(this.global())));
        } else if (tree instanceof Trees.Bind) {
            Option option2;
            Trees.Bind bind = (Trees.Bind)tree;
            Serializable serializable = new Serializable(this, bind){
                public static final long serialVersionUID = 0L;
                public final Trees.Bind x3$1;

                public final Tuple2<Names.Name, Trees.Tree> apply(Trees.Tree x) {
                    return new Tuple2<Names.Name, Trees.Tree>(this.x3$1.name(), x);
                }
                {
                    this.x3$1 = x3$1;
                }
            };
            Option option3 = this.wildType$1(bind.body());
            if (!option3.isEmpty()) {
                Trees.Tree tree2 = (Trees.Tree)option3.get();
                Some<Tuple2<Names.Name, Trees.Tree>> some = new Some<Tuple2<Names.Name, Trees.Tree>>(new Tuple2<Names.Name, Trees.Tree>(serializable.x3$1.name(), tree2));
                option2 = some;
            } else {
                option2 = None$.MODULE$;
            }
            option = option2;
        } else if (tree instanceof Trees.Typed && (typed = (Trees.Typed)tree).expr() instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)typed.expr();
            option = new Some<Tuple2<Names.Name, Trees.Tree>>(new Tuple2<Names.Name, Trees.Tree>(ident.name(), typed.tpt()));
        } else {
            option = None$.MODULE$;
        }
        return option;
    }

    public Trees.Tree mkVisitor(List<Trees.CaseDef> cases, boolean checkExhaustive, String prefix, FreshNameCreator fresh) {
        Names.TermName x = this.global().freshTermName(prefix, fresh);
        Trees.Ident id = new Trees.Ident(this.global(), x);
        Trees.Ident sel = checkExhaustive ? id : this.mkUnchecked(id);
        return new Trees.Function(this.global(), (List<Trees.ValDef>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.ValDef[]{this.mkSyntheticParam(x)})), new Trees.Match(this.global(), sel, cases));
    }

    public String mkVisitor$default$3() {
        return "x$";
    }

    private List<Tuple3<Names.Name, Trees.Tree, Position>> getVariables(Trees.Tree tree) {
        return new GetVarTraverser().apply(tree);
    }

    public TreeGen$patvarTransformer$ patvarTransformer() {
        return this.patvarTransformer$module == null ? this.patvarTransformer$lzycompute() : this.patvarTransformer$module;
    }

    public Trees.Tree mkUnchecked(Trees.Tree expr) {
        return this.global().atPos(expr.pos(), new Trees.Annotated(this.global(), this.global().New(this.scalaDot(this.global().tpnme().unchecked()), (List<List<Trees.Tree>>)Nil$.MODULE$), expr));
    }

    public Trees.ValDef mkSyntheticParam(Names.TermName pname) {
        return new Trees.ValDef(this.global(), (Trees.Modifiers)this.global().Modifiers(BoxesRunTime.boxToLong(0x202000L)), pname, new Trees.TypeTree(this.global()), this.global().EmptyTree());
    }

    public Trees.Tree mkCast(Trees.Tree tree, Types.Type pt) {
        return this.global().atPos(tree.pos(), this.mkAsInstanceOf(tree, pt, true, false));
    }

    public final String scala$reflect$internal$TreeGen$$failMessage$1(Types.Type tpe$1, Symbols.Symbol termSym$1) {
        return new StringBuilder().append((Object)"mkAttributedQualifier(").append(tpe$1).append((Object)", ").append(termSym$1).append((Object)")").toString();
    }

    private final Position wrapped$1(Trees.Tree pat$1, Trees.Tree body$2) {
        return this.global().wrappingPos((List)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{pat$1, body$2})));
    }

    private final Position splitpos$1(Position pos$1, Trees.Tree pat$1, Trees.Tree body$2) {
        Position position = pos$1;
        NoPosition$ noPosition$ = this.global().NoPosition();
        return (!(position != null ? !position.equals(noPosition$) : noPosition$ != null) ? pos$1 : this.wrapped$1(pat$1, body$2).withPoint(pos$1.point())).makeTransparent();
    }

    private final Trees.Tree makeClosure$1(Position pos, Trees.Tree pat, Trees.Tree body2, FreshNameCreator fresh$1) {
        Option<Tuple2<Names.Name, Trees.Tree>> option;
        block4: {
            Trees.Tree tree;
            block3: {
                block2: {
                    Some some;
                    option = this.matchVarPattern(pat);
                    if (!(option instanceof Some) || (some = (Some)option).x() == null) break block2;
                    tree = (Trees.Tree)new Trees.Function(this.global(), (List<Trees.ValDef>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.ValDef[]{this.global().atPos(pat.pos(), new Trees.ValDef(this.global(), (Trees.Modifiers)this.global().Modifiers(BoxesRunTime.boxToLong(8192L)), ((Names.Name)((Tuple2)some.x())._1()).toTermName(), (Trees.Tree)((Tuple2)some.x())._2(), this.global().EmptyTree()))})), body2).setPos(this.splitpos$1(pos, pat, body2));
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                tree = this.global().atPos(this.splitpos$1(pos, pat, body2), this.mkVisitor((List<Trees.CaseDef>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.CaseDef[]{new Trees.CaseDef(this.global(), pat, this.global().EmptyTree(), body2)})), false, this.mkVisitor$default$3(), fresh$1));
            }
            return tree;
        }
        throw new MatchError(option);
    }

    private final Trees.Tree makeCombination$1(Position pos, Names.TermName meth, Trees.Tree qual, Trees.Tree pat, Trees.Tree body2, FreshNameCreator fresh$1) {
        return (Trees.Tree)new Trees.Apply(this.global(), (Trees.Tree)new Trees.Select(this.global(), qual, meth).setPos(qual.pos()).updateAttachment(this.global().ForAttachment(), ClassTag$.MODULE$.apply(StdAttachments$ForAttachment$.class)), (List<Trees.Tree>)List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Trees.Tree[]{this.makeClosure$1(pos, pat, body2, fresh$1)}))).setPos(pos);
    }

    public final Trees.Tree scala$reflect$internal$TreeGen$$makeBind$1(Trees.Tree pat, FreshNameCreator fresh$1) {
        Trees.Tree tree = pat instanceof Trees.Bind ? pat : (Trees.Tree)new Trees.Bind(this.global(), this.global().freshTermName(this.global().freshTermName$default$1(), fresh$1), pat).setPos(pat.pos());
        return tree;
    }

    public final Trees.Tree scala$reflect$internal$TreeGen$$makeValue$1(Trees.Tree pat) {
        if (pat instanceof Trees.Bind) {
            Trees.Bind bind = (Trees.Bind)pat;
            return (Trees.Tree)new Trees.Ident(this.global(), bind.name()).setPos(pat.pos().focus());
        }
        throw new MatchError(pat);
    }

    private final Position closurePos$1(Position genpos, Trees.Tree body$1) {
        Position position;
        Position position2 = genpos;
        NoPosition$ noPosition$ = this.global().NoPosition();
        if (!(position2 != null ? !position2.equals(noPosition$) : noPosition$ != null)) {
            position = this.global().NoPosition();
        } else {
            Position position3 = body$1.pos();
            NoPosition$ noPosition$2 = this.global().NoPosition();
            int n = !(noPosition$2 != null ? !noPosition$2.equals(position3) : position3 != null) ? genpos.point() : position3.end();
            position = this.global().rangePos(genpos.source(), genpos.start(), genpos.point(), n);
        }
        return position;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    private final Option wildType$1(Trees.Tree t) {
        Trees.Typed typed;
        void var7_7;
        if (t instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)t;
            Names.TermName termName = ident.name().toTermName();
            Names.Name name = this.global().nme().WILDCARD();
            if (!(termName != null ? !termName.equals(name) : name != null)) {
                Some<Trees.TypeTree> some = new Some<Trees.TypeTree>(new Trees.TypeTree(this.global()));
                return var7_7;
            }
        }
        if (t instanceof Trees.Typed && (typed = (Trees.Typed)t).expr() instanceof Trees.Ident) {
            Trees.Ident ident = (Trees.Ident)typed.expr();
            Names.TermName termName = ident.name().toTermName();
            Names.Name name = this.global().nme().WILDCARD();
            if (!(termName != null ? !termName.equals(name) : name != null)) {
                Some<Trees.Tree> some = new Some<Trees.Tree>(typed.tpt());
                return var7_7;
            }
        }
        None$ none$ = None$.MODULE$;
        return var7_7;
    }

    public class GetVarTraverser
    extends Trees.Traverser {
        private final ListBuffer<Tuple3<Names.Name, Trees.Tree, Position>> buf;

        public ListBuffer<Tuple3<Names.Name, Trees.Tree, Position>> buf() {
            return this.buf;
        }

        public Position namePos(Trees.Tree tree, Names.Name name) {
            Position position;
            if (tree.pos().isRange() && !name.containsName(this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().nme().raw().DOLLAR())) {
                int start = tree.pos().start();
                int end = start + name.decode().length();
                position = this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().rangePos(tree.pos().source(), start, start, end);
            } else {
                position = tree.pos().focus();
            }
            return position;
        }

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        public void traverse(Trees.Tree tree) {
            bl = this.buf().length();
            var5_3 = false;
            var6_4 = null;
            if (!(tree instanceof Trees.Bind)) ** GOTO lbl-1000
            var5_3 = true;
            var6_4 = (Trees.Bind)tree;
            v0 = this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().nme().WILDCARD();
            var2_5 = var6_4.name();
            if (!(v0 != null ? v0.equals(var2_5) == false : var2_5 != null)) {
                super.traverse(tree);
            } else if (var5_3 && var6_4.body() instanceof Trees.Typed) {
                var4_6 = (Trees.Typed)var6_4.body();
                newTree /* !! */  = this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().treeInfo().mayBeTypePat(var4_6.tpt()) != false ? new Trees.TypeTree(this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global()) : var4_6.tpt().duplicate();
                this.add$1(var6_4.name(), newTree /* !! */ , tree);
                this.traverse(var4_6.expr());
            } else if (var5_3) {
                this.add$1(var6_4.name(), new Trees.TypeTree(this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global()), tree);
                this.traverse(var6_4.body());
            } else {
                super.traverse(tree);
            }
            if (this.buf().length() > bl) {
                tree.setPos(tree.pos().makeTransparent());
            }
        }

        public List<Tuple3<Names.Name, Trees.Tree, Position>> apply(Trees.Tree tree) {
            this.traverse(tree);
            return this.buf().toList();
        }

        public /* synthetic */ TreeGen scala$reflect$internal$TreeGen$GetVarTraverser$$$outer() {
            return TreeGen.this;
        }

        private final boolean seenName$1(Names.Name name) {
            return this.buf().exists((Function1<Tuple3<Names.Name, Trees.Tree, Position>, Object>)((Object)new Serializable(this, name){
                public static final long serialVersionUID = 0L;
                private final Names.Name name$1;

                public final boolean apply(Tuple3<Names.Name, Trees.Tree, Position> x$33) {
                    Names.Name name = x$33._1();
                    Names.Name name2 = this.name$1;
                    return !(name != null ? !name.equals(name2) : name2 != null);
                }
                {
                    this.name$1 = name$1;
                }
            }));
        }

        private final Object add$1(Names.Name name, Trees.Tree t, Trees.Tree tree$1) {
            return this.seenName$1(name) ? BoxedUnit.UNIT : this.buf().$plus$eq((Object)new Tuple3<Names.Name, Trees.Tree, Position>(name, t, this.namePos(tree$1, name)));
        }

        public GetVarTraverser() {
            if (TreeGen.this == null) {
                throw null;
            }
            super(TreeGen.this.global());
            this.buf = new ListBuffer();
        }
    }
}

