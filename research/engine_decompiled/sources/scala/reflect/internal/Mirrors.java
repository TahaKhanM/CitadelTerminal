/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Predef$;
import scala.ScalaReflectionException;
import scala.Serializable;
import scala.StringContext;
import scala.collection.AbstractIterable;
import scala.collection.immutable.List;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Universe;
import scala.reflect.internal.Mirrors$Roots$RootSymbol$class;
import scala.reflect.internal.Mirrors$Roots$WellKnownSymbol$class;
import scala.reflect.internal.MissingRequirementError;
import scala.reflect.internal.MissingRequirementError$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.package$;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001\rmb!C\u0001\u0003!\u0003\r\t!CB\u001a\u0005\u001di\u0015N\u001d:peNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qa\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001a\u0004\"a\u0004\n\u000e\u0003AQ!!\u0005\u0003\u0002\u0007\u0005\u0004\u0018.\u0003\u0002\u0002!!)A\u0003\u0001C\u0001+\u00051A%\u001b8ji\u0012\"\u0012A\u0006\t\u0003\u0017]I!\u0001\u0007\u0004\u0003\tUs\u0017\u000e\u001e\u0003\u00065\u0001\u0011\te\u0007\u0002\u0007\u001b&\u0014(o\u001c:\u0012\u0005qy\u0002CA\u0006\u001e\u0013\tqbA\u0001\u0003Ok2d\u0007C\u0001\u0011\"\u001b\u0005\u0001a!\u0002\u0012\u0001\u0003\u0003\u0019#!\u0003*p_R\u001c()Y:f'\t\tC\u0005E\u0002\u0010K\u0001J!A\u0007\t\t\u0011\u001d\n#\u0011!Q\u0001\n!\n\u0011B]8pi>;h.\u001a:\u0011\u0005\u0001J\u0013B\u0001\u0016,\u0005\u0019\u0019\u00160\u001c2pY&\u0011AF\u0001\u0002\b'fl'm\u001c7t\u0011\u0015q\u0013\u0005\"\u00010\u0003\u0019a\u0014N\\5u}Q\u0011q\u0004\r\u0005\u0006O5\u0002\r\u0001\u000b\u0005\u0007e\u0005\u0002\u000b\u0015B\u001a\u0002\u0017%t\u0017\u000e^5bY&TX\r\u001a\t\u0003\u0017QJ!!\u000e\u0004\u0003\u000f\t{w\u000e\\3b]\")q'\tC\u0001q\u0005\u0019\u0012n]'jeJ|'/\u00138ji&\fG.\u001b>fIV\t1\u0007\u0003\u0004;C\u0019EaaO\u0001\u000be>|G\u000fT8bI\u0016\u0014X#\u0001\u001f\u0011\u0005\u0001j\u0014B\u0001 @\u0005!a\u0015M_=UsB,\u0017B\u0001!\u0003\u0005\u0015!\u0016\u0010]3t\u0011\u001d\u0011\u0015E1A\u0007\u0002\r\u000b\u0011BU8pi\u000ec\u0017m]:\u0016\u0003\u0011\u0003\"\u0001I#\n\u0005\u0019[#aC\"mCN\u001c8+_7c_2Dq\u0001S\u0011C\u0002\u001b\u0005\u0011*A\u0006S_>$\b+Y2lC\u001e,W#\u0001&\u0011\u0005\u0001Z\u0015B\u0001',\u00051iu\u000eZ;mKNKXNY8m\u0011\u001dq\u0015E1A\u0007\u0002\r\u000b\u0011#R7qif\u0004\u0016mY6bO\u0016\u001cE.Y:t\u0011\u001d\u0001\u0016E1A\u0007\u0002%\u000bA\"R7qif\u0004\u0016mY6bO\u0016DQAU\u0011\u0005\u0002M\u000b\u0001b]=nE>dwJZ\u000b\u0003)\u0016$\"!\u0016/\u0011\u0005YSfBA,Y\u001b\u0005\t\u0013BA-&\u0003!)h.\u001b<feN,\u0017BA.,\u0005)!\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0005\b;F\u000b\t\u0011q\u0001_\u0003))g/\u001b3f]\u000e,G%\r\t\u0004-~\u001b\u0017B\u00011b\u0005-9V-Y6UsB,G+Y4\n\u0005\t\u0004\"\u0001\u0003+za\u0016$\u0016mZ:\u0011\u0005\u0011,G\u0002\u0001\u0003\u0006MF\u0013\ra\u001a\u0002\u0002)F\u0011\u0001n\u001b\t\u0003\u0017%L!A\u001b\u0004\u0003\u000f9{G\u000f[5oOB\u00111\u0002\\\u0005\u0003[\u001a\u00111!\u00118z\u0011\u0015y\u0017\u0005\"\u0001q\u0003I1\u0017N\u001c3NK6\u0014WM\u001d$s_6\u0014vn\u001c;\u0015\u0005!\n\b\"\u0002:o\u0001\u0004\u0019\u0018\u0001\u00034vY2t\u0015-\\3\u0011\u0005\u0001\"\u0018BA;w\u0005\u0011q\u0015-\\3\n\u0005]\u0014!!\u0002(b[\u0016\u001c\b\"B=\"\t\u0013Q\u0018\u0001E4fi6{G-\u001e7f\u001fJ\u001cE.Y:t)\rA30 \u0005\u0006yb\u0004\ra]\u0001\u0005a\u0006$\b\u000eC\u0003\u007fq\u0002\u0007q0A\u0002mK:\u00042aCA\u0001\u0013\r\t\u0019A\u0002\u0002\u0004\u0013:$\bBB=\"\t\u0013\t9\u0001F\u0002)\u0003\u0013Aa\u0001`A\u0003\u0001\u0004\u0019\bbBA\u0007C\u0011%\u0011qB\u0001\u0014gR\fG/[2N_\u0012,H.Z(s\u00072\f7o\u001d\u000b\u0004Q\u0005E\u0001B\u0002?\u0002\f\u0001\u00071\u000fC\u0004\u0002\u0016\u0005\"\t\"a\u0006\u0002#5L'O]8s\u001b&\u001c8/\u001b8h\u0011>|7\u000eF\u0003)\u00033\ti\u0002C\u0004\u0002\u001c\u0005M\u0001\u0019\u0001\u0015\u0002\u000b=<h.\u001a:\t\u000f\u0005}\u00111\u0003a\u0001g\u0006!a.Y7f\u0011\u001d\t\u0019#\tC\t\u0003K\t1#\u001e8jm\u0016\u00148/Z'jgNLgn\u001a%p_.$R\u0001KA\u0014\u0003SAq!a\u0007\u0002\"\u0001\u0007\u0001\u0006C\u0004\u0002 \u0005\u0005\u0002\u0019A:\t\u0011\u00055\u0012\u0005\"\u0001\u0007\u0003_\t1\"\\5tg&tw\rS8pWR)\u0001&!\r\u00024!9\u00111DA\u0016\u0001\u0004A\u0003bBA\u0010\u0003W\u0001\ra\u001d\u0005\b\u0003o\tC\u0011BA\u001d\u0003E)gn];sK\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\u0006\t\u0006m\u0012Q\n\u0005\t\u0003{\t)\u00041\u0001\u0002@\u0005Aa-\u001e7m]\u0006lW\r\u0005\u0003\u0002B\u0005\u001dcbA\u0006\u0002D%\u0019\u0011Q\t\u0004\u0002\rA\u0013X\rZ3g\u0013\u0011\tI%a\u0013\u0003\rM#(/\u001b8h\u0015\r\t)E\u0002\u0005\b\u0003\u001f\n)\u00041\u0001)\u0003\r\u0019\u00180\u001c\u0005\b\u0003'\nC\u0011AA+\u000399W\r^\"mCN\u001c()\u001f(b[\u0016$2\u0001RA,\u0011\u001d\ti$!\u0015A\u0002MDq!a\u0017\"\t\u0003\ti&\u0001\thKR\u0014V-];je\u0016$7\t\\1tgR\u0019A)a\u0018\t\u0011\u0005u\u0012\u0011\fa\u0001\u0003\u007fAq!a\u0019\"\t\u0003\t)'A\u0007sKF,\u0018N]3e\u00072\f7o]\u000b\u0005\u0003O\n9\bF\u0002E\u0003SB!\"a\u001b\u0002b\u0005\u0005\t9AA7\u0003))g/\u001b3f]\u000e,GE\r\t\u0007\u0003_\n\t(!\u001e\u000e\u0003\u0011I1!a\u001d\u0005\u0005!\u0019E.Y:t)\u0006<\u0007c\u00013\u0002x\u00111a-!\u0019C\u0002\u001dDq!a\u001f\"\t\u0003\ti(A\thKR\u001cE.Y:t\u0013\u001a$UMZ5oK\u0012$2\u0001KA@\u0011!\ti$!\u001fA\u0002\u0005}\u0002bBA>C\u0011\u0005\u00111\u0011\u000b\u0004Q\u0005\u0015\u0005bBA\u001f\u0003\u0003\u0003\ra\u001d\u0005\b\u0003\u0013\u000bC\u0011IAF\u0003-\u0019H/\u0019;jG\u000ec\u0017m]:\u0015\u0007\u0011\u000bi\t\u0003\u0005\u0002>\u0005\u001d\u0005\u0019AA \u0011\u001d\t\t*\tC\u0005\u0003'\u000b!#\u001a8tkJ,Wj\u001c3vY\u0016\u001c\u00160\u001c2pYR9!*!&\u0002\u0018\u0006e\u0005\u0002CA\u001f\u0003\u001f\u0003\r!a\u0010\t\u000f\u0005=\u0013q\u0012a\u0001Q!9\u00111TAH\u0001\u0004\u0019\u0014!D1mY><\b+Y2lC\u001e,7\u000fC\u0004\u0002 \u0006\"\t!!)\u0002\u001f\u001d,G/T8ek2,')\u001f(b[\u0016$2ASAR\u0011\u001d\ti$!(A\u0002MDq!a*\"\t\u0003\tI+A\thKR\u0014V-];je\u0016$Wj\u001c3vY\u0016$2ASAV\u0011!\ti$!*A\u0002\u0005}\u0002bBAXC\u0011\u0005\u0011\u0011W\u0001\u000fe\u0016\fX/\u001b:fI6{G-\u001e7f+\u0011\t\u0019,!0\u0015\u0007)\u000b)\f\u0003\u0006\u00028\u00065\u0016\u0011!a\u0002\u0003s\u000b!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\ty'!\u001d\u0002<B\u0019A-!0\u0005\r\u0019\fiK1\u0001h\u0011\u001d\t\t-\tC\u0001\u0003\u0007\f!cZ3u\u001b>$W\u000f\\3JM\u0012+g-\u001b8fIR\u0019\u0001&!2\t\u0011\u0005u\u0012q\u0018a\u0001\u0003\u007fAq!!1\"\t\u0003\tI\rF\u0002)\u0003\u0017Dq!!\u0010\u0002H\u0002\u00071\u000fC\u0004\u0002P\u0006\"\t%!5\u0002\u0019M$\u0018\r^5d\u001b>$W\u000f\\3\u0015\u0007)\u000b\u0019\u000e\u0003\u0005\u0002>\u00055\u0007\u0019AA \u0011\u001d\t9.\tC\u0005\u00033\f1#\u001a8tkJ,\u0007+Y2lC\u001e,7+_7c_2$rASAn\u0003;\fy\u000e\u0003\u0005\u0002>\u0005U\u0007\u0019AA \u0011\u001d\ty%!6A\u0002!Bq!!9\u0002V\u0002\u00071'\u0001\u0007bY2|w/T8ek2,7\u000fC\u0004\u0002f\u0006\"\t!a:\u0002\u0015\u001d,G\u000fU1dW\u0006<W\rF\u0002K\u0003SD\u0001\"!\u0010\u0002d\u0002\u0007\u00111\u001e\t\u0004A\u00055\u0018bAAxm\nAA+\u001a:n\u001d\u0006lW\rC\u0004\u0002t\u0006\"\t!!>\u0002'\u001d,G\u000fU1dW\u0006<W-\u00134EK\u001aLg.\u001a3\u0015\u0007!\n9\u0010\u0003\u0005\u0002>\u0005E\b\u0019AAv\u0011\u001d\tY0\tC\u0001\u0003{\f!cZ3u%\u0016\fX/\u001b:fIB\u000b7m[1hKR\u0019!*a@\t\u0011\u0005u\u0012\u0011 a\u0001\u0003\u007fA\u0003\"!?\u0003\u0004\t%!Q\u0002\t\u0004\u0017\t\u0015\u0011b\u0001B\u0004\r\tQA-\u001a9sK\u000e\fG/\u001a3\"\u0005\t-\u0011AD+tK\u0002:W\r\u001e)bG.\fw-Z\u0011\u0003\u0005\u001f\taA\r\u00182c9\u0002\u0004b\u0002B\nC\u0011\u0005!QC\u0001\u0011O\u0016$\b+Y2lC\u001e,wJ\u00196fGR$2A\u0013B\f\u0011!\tiD!\u0005A\u0002\u0005}\u0002b\u0002B\nC\u0011\u0005!1\u0004\u000b\u0004\u0015\nu\u0001\u0002CA\u001f\u00053\u0001\r!a;\t\u000f\t\u0005\u0012\u0005\"\u0001\u0003$\u0005Ir-\u001a;QC\u000e\\\u0017mZ3PE*,7\r^%g\t\u00164\u0017N\\3e)\rA#Q\u0005\u0005\t\u0003{\u0011y\u00021\u0001\u0002@!9!\u0011E\u0011\u0005\u0002\t%Bc\u0001\u0015\u0003,!A\u0011Q\bB\u0014\u0001\u0004\tY\u000fC\u0004\u00030\u0005\")A!\r\u00025\u001d,G\u000fU1dW\u0006<Wm\u00142kK\u000e$x+\u001b;i\u001b\u0016l'-\u001a:\u0015\u000b!\u0012\u0019D!\u0010\t\u0011\tU\"Q\u0006a\u0001\u0005o\t1\u0001\u001d:f!\r\u0001#\u0011H\u0005\u0004\u0005wy$\u0001\u0002+za\u0016Dq!a\u0014\u0003.\u0001\u0007\u0001\u0006C\u0004\u0003B\u0005\"\tEa\u0011\u0002\u001bM$\u0018\r^5d!\u0006\u001c7.Y4f)\rQ%Q\t\u0005\t\u0003{\u0011y\u00041\u0001\u0002@!9!\u0011J\u0011\u0005\u0002\t-\u0013aC3sCN,(/\u001a(b[\u0016,BA!\u0014\u0003XQ!\u0011q\bB(\u0011)\u0011\tFa\u0012\u0002\u0002\u0003\u000f!1K\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004CBA8\u0003c\u0012)\u0006E\u0002e\u0005/\"aA\u001aB$\u0005\u00049\u0007b\u0002B.C\u0011\u0015!QL\u0001\foJ\f\u0007/T5tg&tw\rF\u0002)\u0005?B\u0011B!\u0019\u0003Z\u0011\u0005\rAa\u0019\u0002\t\t|G-\u001f\t\u0005\u0017\t\u0015\u0004&C\u0002\u0003h\u0019\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0015\u0005\u00053\u0012Y\u0007E\u0002\f\u0005[J1Aa\u001c\u0007\u0005\u0019Ig\u000e\\5oK\"1!1O\u0011\u0005\u0002U\tA!\u001b8ji\u001aI!q\u000f\u0001\u0011\u0002G\u0005!\u0011\u0010\u0002\u000b%>|GoU=nE>d7c\u0001B;Q!A!Q\u0010B;\r\u0003\u0011y(\u0001\u0004nSJ\u0014xN]\u000b\u0003\u0005\u0003\u0003\"\u0001I\r\u0007\u000f\t\u0015\u0005!!\u0001\u0003\b\n)!k\\8ugN\u0019!1Q\u0010\t\u0013\u001d\u0012\u0019I!A!\u0002\u0013A\u0003b\u0002\u0018\u0003\u0004\u0012\u0005!Q\u0012\u000b\u0005\u0005\u001f\u0013\t\nE\u0002!\u0005\u0007Caa\nBF\u0001\u0004AcA\u0003BK\u0005\u0007\u0003\n1!\t\u0003\u0018\nyq+\u001a7m\u0017:|wO\\*z[\n|GnE\u0002\u0003\u0014\"Ba\u0001\u0006BJ\t\u0003)\u0012\u0006\u0003BJ\u0005;\u0013\tL!5\u0007\u000f\t}%1\u0011\u0001\u0003\"\naQ)\u001c9usB\u000b7m[1hKN)!Q\u0014&\u0003$B!!Q\u0015BJ\u001b\t\u0011\u0019\tC\u0004/\u0005;#\tA!+\u0015\u0005\t-\u0006\u0003\u0002BS\u0005;CqAa,\u0003\u001e\u0012\u0005\u0003(\u0001\bjg\u0016k\u0007\u000f^=QC\u000e\\\u0017mZ3\u0007\u000f\tM&1\u0011\u0001\u00036\n\tR)\u001c9usB\u000b7m[1hK\u000ec\u0017m]:\u0014\r\tE&q\u0017BR!\r\u0001#\u0011X\u0005\u0004\u0005w[#A\u0005)bG.\fw-Z\"mCN\u001c8+_7c_2DqA\fBY\t\u0003\u0011y\f\u0006\u0002\u0003BB!!Q\u0015BY\u0011\u001d\u0011)M!-\u0005Ba\nq\"[:FM\u001a,7\r^5wKJ{w\u000e\u001e\u0005\b\u0005\u0013\u0014\t\f\"\u00119\u0003MI7/R7qif\u0004\u0016mY6bO\u0016\u001cE.Y:t\u0011!\u0011iM!-\u0005B\t=\u0017\u0001D:pkJ\u001cW-T8ek2,WC\u0001BV\r)\u00119Ha!\u0011\u0002\u0007\u0005\"1[\n\b\u0005#D#1\u0015Bk!\r\u0001#Q\u000f\u0005\u0007)\tEG\u0011A\u000b\t\u000f\tm'\u0011\u001bC#q\u0005a\u0011n\u001d*p_R\u001c\u00160\u001c2pY\"A\u00111\u0004Bi\t\u0003\u0012y.F\u0001)\u0011!\u0011\u0019O!5\u0005B\t\u0015\u0018A\u0003;za\u0016|e\r\u00165jgV\u0011!q\u0007\u0005\t\u0005{\u0012\t\u000e\"\u0001\u0003\u0000%2!\u0011\u001bBv\u0007\u00131qA!<\u0003\u0004\u0002\u0011yOA\u0005S_>$8\t\\1tgN1!1\u001eB\\\u0005c\u0004BA!*\u0003R\"9aFa;\u0005\u0002\tUHC\u0001B|!\u0011\u0011)Ka;\t\u000f\tm(1\u001eC!q\u00051\u0011n\u001d*p_RDqA!2\u0003l\u0012\u0005\u0003\bC\u0004\u0004\u0002\t-H\u0011\t\u001d\u0002\u001b%\u001ch*Z:uK\u0012\u001cE.Y:t\u0011!\u0011iMa;\u0005B\r\u0015QCAB\u0004!\u0011\u0011)k!\u0003\u0007\u000f\r-!1\u0011\u0001\u0004\u000e\tY!k\\8u!\u0006\u001c7.Y4f'\u0015\u0019IA\u0013By\u0011\u001dq3\u0011\u0002C\u0001\u0007#!\"aa\u0002\t\u000f\rU1\u0011\u0002C!q\u0005i\u0011n\u001d*p_R\u0004\u0016mY6bO\u0016D!\u0002\u0013BB\u0011\u000b\u0007I\u0011AB\u0003\u0011-\u0019YBa!\t\u0002\u0003\u0006Kaa\u0002\u0002\u0019I{w\u000e\u001e)bG.\fw-\u001a\u0011\t\u0015\t\u0013\u0019\t#b\u0001\n\u0003\u0019y\"\u0006\u0002\u0003x\"Y11\u0005BB\u0011\u0003\u0005\u000b\u0015\u0002B|\u0003)\u0011vn\u001c;DY\u0006\u001c8\u000f\t\u0005\u000b!\n\r\u0005R1A\u0005\u0002\t=\u0007bCB\u0015\u0005\u0007C\t\u0011)Q\u0005\u0005W\u000bQ\"R7qif\u0004\u0016mY6bO\u0016\u0004\u0003B\u0003(\u0003\u0004\"\u0015\r\u0011\"\u0001\u0004.U\u0011!\u0011\u0019\u0005\f\u0007c\u0011\u0019\t#A!B\u0013\u0011\t-\u0001\nF[B$\u0018\u0010U1dW\u0006<Wm\u00117bgN\u0004\u0003\u0003BB\u001b\u0007oi\u0011AA\u0005\u0004\u0007s\u0011!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface Mirrors
extends scala.reflect.api.Mirrors {

    public abstract class Roots
    extends RootsBase {
        public final Symbols.Symbol scala$reflect$internal$Mirrors$Roots$$rootOwner;
        private RootPackage RootPackage;
        private RootClass RootClass;
        private EmptyPackage EmptyPackage;
        private EmptyPackageClass EmptyPackageClass;
        private volatile byte bitmap$0;

        private RootPackage RootPackage$lzycompute() {
            Roots roots2 = this;
            synchronized (roots2) {
                if ((byte)(this.bitmap$0 & 1) == 0) {
                    this.RootPackage = new RootPackage();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RootPackage;
            }
        }

        private RootClass RootClass$lzycompute() {
            Roots roots2 = this;
            synchronized (roots2) {
                if ((byte)(this.bitmap$0 & 2) == 0) {
                    this.RootClass = new RootClass();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.RootClass;
            }
        }

        private EmptyPackage EmptyPackage$lzycompute() {
            Roots roots2 = this;
            synchronized (roots2) {
                if ((byte)(this.bitmap$0 & 4) == 0) {
                    this.EmptyPackage = new EmptyPackage();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.EmptyPackage;
            }
        }

        private EmptyPackageClass EmptyPackageClass$lzycompute() {
            Roots roots2 = this;
            synchronized (roots2) {
                if ((byte)(this.bitmap$0 & 8) == 0) {
                    this.EmptyPackageClass = new EmptyPackageClass();
                    this.bitmap$0 = (byte)(this.bitmap$0 | 8);
                }
                // ** MonitorExit[this] (shouldn't be in output)
                return this.EmptyPackageClass;
            }
        }

        @Override
        public RootPackage RootPackage() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.RootPackage$lzycompute() : this.RootPackage;
        }

        @Override
        public RootClass RootClass() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.RootClass$lzycompute() : this.RootClass;
        }

        @Override
        public EmptyPackage EmptyPackage() {
            return (byte)(this.bitmap$0 & 4) == 0 ? this.EmptyPackage$lzycompute() : this.EmptyPackage;
        }

        @Override
        public EmptyPackageClass EmptyPackageClass() {
            return (byte)(this.bitmap$0 & 8) == 0 ? this.EmptyPackageClass$lzycompute() : this.EmptyPackageClass;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Mirrors$Roots$$$outer() {
            return this.$outer;
        }

        public Roots(SymbolTable $outer, Symbols.Symbol rootOwner) {
            this.scala$reflect$internal$Mirrors$Roots$$rootOwner = rootOwner;
            super($outer, rootOwner);
        }

        public class RootClass
        extends Symbols.PackageClassSymbol
        implements RootSymbol {
            @Override
            public final boolean isRootSymbol() {
                return Mirrors$Roots$RootSymbol$class.isRootSymbol(this);
            }

            @Override
            public Symbols.Symbol owner() {
                return Mirrors$Roots$RootSymbol$class.owner(this);
            }

            @Override
            public Types.Type typeOfThis() {
                return Mirrors$Roots$RootSymbol$class.typeOfThis(this);
            }

            @Override
            public RootsBase mirror() {
                return Mirrors$Roots$RootSymbol$class.mirror(this);
            }

            @Override
            public boolean isRoot() {
                return true;
            }

            @Override
            public boolean isEffectiveRoot() {
                return true;
            }

            @Override
            public boolean isNestedClass() {
                return false;
            }

            @Override
            public RootPackage sourceModule() {
                return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer().RootPackage();
            }

            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$RootClass$$$outer() {
                return Roots.this;
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer();
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer();
            }

            public RootClass() {
                if (Roots.this == null) {
                    throw null;
                }
                super(Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer(), Roots.this.scala$reflect$internal$Mirrors$Roots$$rootOwner, (Position)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TypeName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().tpnme().ROOT());
                Mirrors$Roots$WellKnownSymbol$class.$init$(this);
                Mirrors$Roots$RootSymbol$class.$init$(this);
                this.setInfo(Roots.this.rootLoader());
            }
        }

        public interface RootSymbol
        extends WellKnownSymbol,
        scala.reflect.internal.Mirrors$RootSymbol {
            public boolean isRootSymbol();

            public Symbols.Symbol owner();

            public Types.Type typeOfThis();

            @Override
            public RootsBase mirror();

            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer();
        }

        public class RootPackage
        extends Symbols.ModuleSymbol
        implements RootSymbol {
            @Override
            public final boolean isRootSymbol() {
                return Mirrors$Roots$RootSymbol$class.isRootSymbol(this);
            }

            @Override
            public Symbols.Symbol owner() {
                return Mirrors$Roots$RootSymbol$class.owner(this);
            }

            @Override
            public Types.Type typeOfThis() {
                return Mirrors$Roots$RootSymbol$class.typeOfThis(this);
            }

            @Override
            public RootsBase mirror() {
                return Mirrors$Roots$RootSymbol$class.mirror(this);
            }

            @Override
            public boolean isRootPackage() {
                return true;
            }

            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer() {
                return Roots.this;
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer();
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer();
            }

            public RootPackage() {
                if (Roots.this == null) {
                    throw null;
                }
                super(Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer(), Roots.this.scala$reflect$internal$Mirrors$Roots$$rootOwner, (Position)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().nme().ROOTPKG());
                Mirrors$Roots$WellKnownSymbol$class.$init$(this);
                Mirrors$Roots$RootSymbol$class.$init$(this);
                this.setInfo(new Types.NullaryMethodType(Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer(), Roots.this.RootClass().tpe()));
            }
        }

        public class EmptyPackage
        extends Symbols.ModuleSymbol
        implements WellKnownSymbol {
            @Override
            public boolean isEmptyPackage() {
                return true;
            }

            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$EmptyPackage$$$outer() {
                return Roots.this;
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$EmptyPackage$$$outer();
            }

            public EmptyPackage() {
                if (Roots.this == null) {
                    throw null;
                }
                super(Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer(), (Symbols.Symbol)Roots.this.RootClass(), (Position)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TermName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().nme().EMPTY_PACKAGE_NAME());
                Mirrors$Roots$WellKnownSymbol$class.$init$(this);
            }
        }

        public interface WellKnownSymbol {
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer();
        }

        public class EmptyPackageClass
        extends Symbols.PackageClassSymbol
        implements WellKnownSymbol {
            @Override
            public boolean isEffectiveRoot() {
                return true;
            }

            @Override
            public boolean isEmptyPackageClass() {
                return true;
            }

            @Override
            public EmptyPackage sourceModule() {
                return this.scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer().EmptyPackage();
            }

            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer() {
                return Roots.this;
            }

            @Override
            public /* synthetic */ Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
                return this.scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer();
            }

            public EmptyPackageClass() {
                if (Roots.this == null) {
                    throw null;
                }
                super(Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer(), (Symbols.Symbol)Roots.this.RootClass(), (Position)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TypeName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().tpnme().EMPTY_PACKAGE_NAME());
                Mirrors$Roots$WellKnownSymbol$class.$init$(this);
            }
        }
    }

    public abstract class RootsBase
    extends Mirror<SymbolTable> {
        private final Symbols.Symbol rootOwner;
        private boolean initialized;
        public final /* synthetic */ SymbolTable $outer;

        public boolean isMirrorInitialized() {
            return this.initialized;
        }

        public abstract Types.LazyType rootLoader();

        @Override
        public abstract Symbols.ClassSymbol RootClass();

        @Override
        public abstract Symbols.ModuleSymbol RootPackage();

        @Override
        public abstract Symbols.ClassSymbol EmptyPackageClass();

        @Override
        public abstract Symbols.ModuleSymbol EmptyPackage();

        @Override
        public <T> Symbols.TypeSymbol symbolOf(TypeTags.WeakTypeTag<T> evidence$1) {
            return (Symbols.TypeSymbol)((Types.Type)((Universe)this.universe()).weakTypeTag(evidence$1).in(this).tpe()).typeSymbolDirect().asType();
        }

        public Symbols.Symbol findMemberFromRoot(Names.Name fullName) {
            List<Names.Name> segs = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().nme().segments(fullName.toString(), fullName.isTermName());
            return segs.isEmpty() ? this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol() : this.scala$reflect$internal$Mirrors$RootsBase$$$outer().definitions().findNamedMember((List)segs.tail(), this.RootClass().info().member(segs.head()));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private Symbols.Symbol getModuleOrClass(Names.Name path, int len) {
            Symbols.Symbol symbol;
            Symbols.Symbol result2;
            int point = path.lastPos('.', len - 1);
            Symbols.ClassSymbol owner2 = point > 0 ? this.getModuleOrClass(path.toTermName(), point) : this.RootClass();
            Names.Name name = path.subName(point + 1, len);
            Symbols.Symbol sym = owner2.info().member(name);
            Symbols.Symbol symbol2 = result2 = path.isTermName() ? sym.suchThat((Function1)((Object)new Serializable(this){
                public static final long serialVersionUID = 0L;

                public final boolean apply(Symbols.Symbol x$1) {
                    return x$1.hasFlag(256);
                }
            })) : sym;
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            if (!(symbol2 != null ? !symbol2.equals(noSymbol) : noSymbol != null)) {
                Symbols.Symbol symbol3;
                MutableSettings.SettingValue settingValue = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings().debug();
                MutableSettings$ mutableSettings$ = MutableSettings$.MODULE$;
                if (BoxesRunTime.unboxToBoolean(settingValue.value())) {
                    this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log((Function0<Object>)((Object)new Serializable(this, sym){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol sym$1;

                        public final Types.Type apply() {
                            return this.sym$1.info();
                        }
                        {
                            this.sym$1 = sym$1;
                        }
                    }));
                    this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log((Function0<Object>)((Object)new Serializable(this, sym){
                        public static final long serialVersionUID = 0L;
                        private final Symbols.Symbol sym$1;

                        public final Scopes.Scope apply() {
                            return this.sym$1.info().members();
                        }
                        {
                            this.sym$1 = sym$1;
                        }
                    }));
                }
                if ((symbol3 = this.missingHook(owner2, name)) == symbol3.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) throw MissingRequirementError$.MODULE$.notFound(new StringBuilder().append((Object)(path.isTermName() ? "object " : "class ")).append(path).append((Object)" in ").append(this).toString());
                symbol = symbol3;
                return symbol;
            } else {
                symbol = result2;
            }
            return symbol;
        }

        private Symbols.Symbol getModuleOrClass(Names.Name path) {
            return this.getModuleOrClass(path, path.length());
        }

        private Symbols.Symbol staticModuleOrClass(Names.Name path) {
            boolean isPackageless = path.pos('.') == path.length();
            return isPackageless ? this.EmptyPackageClass().info().decl(path) : this.getModuleOrClass(path);
        }

        public Symbols.Symbol mirrorMissingHook(Symbols.Symbol owner2, Names.Name name) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
        }

        public Symbols.Symbol universeMissingHook(Symbols.Symbol owner2, Names.Name name) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().missingHook(owner2, name);
        }

        public Symbols.Symbol missingHook(Symbols.Symbol owner2, Names.Name name) {
            Symbols.Symbol symbol = this.mirrorMissingHook(owner2, name);
            Symbols.Symbol symbol2 = symbol != symbol.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? symbol : this.universeMissingHook(owner2, name);
            Serializable serializable = new Serializable(this, owner2, name){
                public static final long serialVersionUID = 0L;
                private final Symbols.Symbol owner$1;
                private final Names.Name name$1;

                public final String apply() {
                    return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"missingHook(", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.owner$1, this.name$1}));
                }
                {
                    this.owner$1 = owner$1;
                    this.name$1 = name$1;
                }
            };
            SymbolTable symbolTable = this.scala$reflect$internal$Mirrors$RootsBase$$$outer();
            symbolTable.log((Function0<Object>)((Object)new Serializable(symbolTable, (Function0)((Object)serializable), symbol2){
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
            return symbol2;
        }

        private Symbols.ClassSymbol ensureClassSymbol(String fullname, Symbols.Symbol sym) {
            Symbols.Symbol result2 = sym;
            while (result2.isAliasType()) {
                result2 = result2.info().typeSymbol();
            }
            if (result2 instanceof Symbols.ClassSymbol) {
                Symbols.ClassSymbol classSymbol = (Symbols.ClassSymbol)result2;
                return classSymbol;
            }
            throw MissingRequirementError$.MODULE$.notFound(new StringBuilder().append((Object)"class ").append((Object)fullname).toString());
        }

        public Symbols.ClassSymbol getClassByName(Names.Name fullname) {
            return this.ensureClassSymbol(fullname.toString(), this.getModuleOrClass(fullname.toTypeName()));
        }

        public Symbols.ClassSymbol getRequiredClass(String fullname) {
            return this.getClassByName(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeNameCached(fullname));
        }

        public <T> Symbols.ClassSymbol requiredClass(ClassTag<T> evidence$2) {
            return this.getRequiredClass(this.erasureName(evidence$2));
        }

        public Symbols.Symbol getClassIfDefined(String fullname) {
            return this.getClassIfDefined(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeNameCached(fullname));
        }

        public Symbols.Symbol getClassIfDefined(Names.Name fullname) {
            Symbols.Symbol symbol;
            RootsBase rootsBase = this;
            try {
                symbol = this.getClassByName(fullname.toTypeName());
            }
            catch (MissingRequirementError missingRequirementError) {
                symbol = rootsBase.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            }
            return symbol;
        }

        @Override
        public Symbols.ClassSymbol staticClass(String fullname) {
            try {
                return this.ensureClassSymbol(fullname, this.staticModuleOrClass(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeNameCached(fullname)));
            }
            catch (MissingRequirementError missingRequirementError) {
                throw new ScalaReflectionException(missingRequirementError.msg());
            }
        }

        private Symbols.ModuleSymbol ensureModuleSymbol(String fullname, Symbols.Symbol sym, boolean allowPackages) {
            if (sym instanceof Symbols.ModuleSymbol) {
                Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)sym;
                if (allowPackages || !moduleSymbol.hasPackageFlag()) {
                    return moduleSymbol;
                }
            }
            throw MissingRequirementError$.MODULE$.notFound(new StringBuilder().append((Object)"object ").append((Object)fullname).toString());
        }

        public Symbols.ModuleSymbol getModuleByName(Names.Name fullname) {
            return this.ensureModuleSymbol(fullname.toString(), this.getModuleOrClass(fullname.toTermName()), true);
        }

        public Symbols.ModuleSymbol getRequiredModule(String fullname) {
            return this.getModuleByName(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname));
        }

        public <T> Symbols.ModuleSymbol requiredModule(ClassTag<T> evidence$3) {
            String string2 = this.erasureName(evidence$3);
            Predef$ predef$ = Predef$.MODULE$;
            return this.getRequiredModule(new StringOps(string2).stripSuffix("$"));
        }

        public Symbols.Symbol getModuleIfDefined(String fullname) {
            return this.getModuleIfDefined(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname));
        }

        public Symbols.Symbol getModuleIfDefined(Names.Name fullname) {
            Symbols.Symbol symbol;
            RootsBase rootsBase = this;
            try {
                symbol = this.getModuleByName(fullname.toTermName());
            }
            catch (MissingRequirementError missingRequirementError) {
                symbol = rootsBase.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            }
            return symbol;
        }

        @Override
        public Symbols.ModuleSymbol staticModule(String fullname) {
            try {
                return this.ensureModuleSymbol(fullname, this.staticModuleOrClass(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname)), false);
            }
            catch (MissingRequirementError missingRequirementError) {
                throw new ScalaReflectionException(missingRequirementError.msg());
            }
        }

        private Symbols.ModuleSymbol ensurePackageSymbol(String fullname, Symbols.Symbol sym, boolean allowModules) {
            if (sym instanceof Symbols.ModuleSymbol) {
                Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)sym;
                if (allowModules || moduleSymbol.hasPackageFlag()) {
                    return moduleSymbol;
                }
            }
            throw MissingRequirementError$.MODULE$.notFound(new StringBuilder().append((Object)"package ").append((Object)fullname).toString());
        }

        public Symbols.ModuleSymbol getPackage(Names.TermName fullname) {
            return this.ensurePackageSymbol(fullname.toString(), this.getModuleOrClass(fullname), true);
        }

        public Symbols.Symbol getPackageIfDefined(Names.TermName fullname) {
            Symbols.Symbol symbol;
            RootsBase rootsBase = this;
            try {
                symbol = this.getPackage(fullname);
            }
            catch (MissingRequirementError missingRequirementError) {
                symbol = rootsBase.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            }
            return symbol;
        }

        public Symbols.ModuleSymbol getRequiredPackage(String fullname) {
            return this.getPackage(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname));
        }

        public Symbols.ModuleSymbol getPackageObject(String fullname) {
            return this.getPackageObject(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(fullname));
        }

        public Symbols.ModuleSymbol getPackageObject(Names.TermName fullname) {
            Symbols.Symbol symbol = this.getPackage(fullname).info().member(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().nme().PACKAGE());
            if (symbol instanceof Symbols.ModuleSymbol) {
                Symbols.ModuleSymbol moduleSymbol = (Symbols.ModuleSymbol)symbol;
                return moduleSymbol;
            }
            throw MissingRequirementError$.MODULE$.notFound(new StringBuilder().append((Object)"package object ").append(fullname).toString());
        }

        public Symbols.Symbol getPackageObjectIfDefined(String fullname) {
            return this.getPackageObjectIfDefined(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname));
        }

        public Symbols.Symbol getPackageObjectIfDefined(Names.TermName fullname) {
            Symbols.Symbol symbol;
            RootsBase rootsBase = this;
            try {
                symbol = this.getPackageObject(fullname);
            }
            catch (MissingRequirementError missingRequirementError) {
                symbol = rootsBase.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            }
            return symbol;
        }

        public final Symbols.Symbol getPackageObjectWithMember(Types.Type pre, Symbols.Symbol sym) {
            return sym.owner().isModuleClass() ? sym.owner().sourceModule() : pre.member(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().nme().PACKAGE());
        }

        @Override
        public Symbols.ModuleSymbol staticPackage(String fullname) {
            try {
                return this.ensurePackageSymbol(fullname.toString(), this.getModuleOrClass(this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermNameCached(fullname)), false);
            }
            catch (MissingRequirementError missingRequirementError) {
                throw new ScalaReflectionException(missingRequirementError.msg());
            }
        }

        public <T> String erasureName(ClassTag<T> evidence$4) {
            return this.erasureString$1(package$.MODULE$.classTag(evidence$4).runtimeClass());
        }

        public final Symbols.Symbol wrapMissing(Function0<Symbols.Symbol> body2) {
            Symbols.Symbol symbol;
            try {
                symbol = body2.apply();
            }
            catch (MissingRequirementError missingRequirementError) {
                symbol = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            }
            return symbol;
        }

        public void init() {
            if (this.initialized) {
                return;
            }
            this.EmptyPackageClass().setInfo(this.rootLoader());
            this.EmptyPackage().setInfo(((Symbols.Symbol)this.EmptyPackageClass()).tpe_$times());
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().connectModuleToClass(this.EmptyPackage(), this.EmptyPackageClass());
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().connectModuleToClass(this.RootPackage(), this.RootClass());
            this.RootClass().info().decls().enter(this.EmptyPackage());
            this.RootClass().info().decls().enter(this.RootPackage());
            Symbols.Symbol symbol = this.rootOwner;
            Symbols.NoSymbol noSymbol = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            if (symbol == null ? noSymbol != null : !symbol.equals(noSymbol)) {
                List list2 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().definitions().syntheticCoreClasses();
                while (!((AbstractIterable)list2).isEmpty()) {
                    Symbols.TypeSymbol typeSymbol2 = (Symbols.TypeSymbol)((AbstractIterable)list2).head();
                    Symbols.Symbol theirOwner1 = typeSymbol2.owner();
                    boolean bl = theirOwner1.isPackageClass();
                    Predef$ predef$ = Predef$.MODULE$;
                    if (!bl) {
                        throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"theirSym = ", ", theirOwner = ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{typeSymbol2, theirOwner1}))).toString());
                    }
                    Symbols.Symbol ourOwner1 = this.staticPackage(theirOwner1.fullName('.')).moduleClass();
                    ourOwner1.info().decls().enterIfNew(typeSymbol2);
                    list2 = (List)list2.tail();
                }
            }
            this.initialized = true;
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$Mirrors$RootsBase$$$outer() {
            return this.$outer;
        }

        private final String erasureString$1(Class clazz) {
            return clazz.isArray() ? new StringBuilder().append((Object)"Array[").append((Object)this.erasureString$1(clazz.getComponentType())).append((Object)"]").toString() : clazz.getName();
        }

        public RootsBase(SymbolTable $outer, Symbols.Symbol rootOwner) {
            this.rootOwner = rootOwner;
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.initialized = false;
        }
    }

    public interface RootSymbol {
        public RootsBase mirror();
    }
}

