/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.Option;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Internals;
import scala.reflect.api.Names;
import scala.reflect.api.Position;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.macros.Attachments;

@ScalaSignature(bytes="\u0006\u0001\u001d\u001da!B\u0001\u0003\u0003\u0003I!\u0001C+oSZ,'o]3\u000b\u0005\r!\u0011AB7bGJ|7O\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0003\u001b\u0011\t1!\u00199j\u0013\t\tA\u0002C\u0003\u0011\u0001\u0011\u0005\u0011#\u0001\u0004=S:LGO\u0010\u000b\u0002%A\u00111\u0003A\u0007\u0002\u0005\u0011)Q\u0003\u0001B!-\tA\u0011J\u001c;fe:\fG.\u0005\u0002\u00187A\u0011\u0001$G\u0007\u0002\r%\u0011!D\u0002\u0002\b\u001d>$\b.\u001b8h!\taR$D\u0001\u0001\r\u001dq\u0002\u0001%A\u0002\u0002}\u0011\u0001#T1de>Le\u000e^3s]\u0006d\u0017\t]5\u0014\u0007u\u00013\u0005\u0005\u0002\u0019C%\u0011!E\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q!\u0013BA\u0013'\u0005-Ie\u000e^3s]\u0006d\u0017\t]5\n\u0005\u001db!!C%oi\u0016\u0014h.\u00197t\u0011\u0015IS\u0004\"\u0001+\u0003\u0019!\u0013N\\5uIQ\t1\u0006\u0005\u0002\u0019Y%\u0011QF\u0002\u0002\u0005+:LG\u000fC\u00030;\u0019\u0005\u0001'A\u0003f]R,'\u000fF\u00022gir!AM\u001a\r\u0001!)AG\fa\u0001k\u0005)1oY8qKB\u0011ADN\u0005\u0003oa\u0012QaU2pa\u0016L!!\u000f\u0007\u0003\rM\u001bw\u000e]3t\u0011\u0015Yd\u00061\u0001=\u0003\r\u0019\u00180\u001c\t\u00039uJ!AP \u0003\rMKXNY8m\u0013\t\u0001EBA\u0004Ts6\u0014w\u000e\\:\t\u000b\tkb\u0011A\"\u0002\rUtG.\u001b8l)\r!UI\u0012\b\u0003e\u0015CQ\u0001N!A\u0002UBQaO!A\u0002qBQ\u0001S\u000f\u0007\u0002%\u000b1b\u00195b]\u001e,wj\u001e8feR!!j\u0013*U\u001d\t\u00114\nC\u0003M\u000f\u0002\u0007Q*\u0001\u0003ue\u0016,\u0007C\u0001\u000fO\u0013\ty\u0005K\u0001\u0003Ue\u0016,\u0017BA)\r\u0005\u0015!&/Z3t\u0011\u0015\u0019v\t1\u0001=\u0003\u0011\u0001(/\u001a<\t\u000bU;\u0005\u0019\u0001\u001f\u0002\t9,\u0007\u0010\u001e\u0005\b/v\u0011\rQ\"\u0001Y\u0003\r9WM\\\u000b\u00023B\u0011AD\u0017\u0004\b7\u0002\u0001\n1%\u0001]\u0005\u001d!&/Z3HK:\u001c\"A\u0017\u0011\t\u000bySf\u0011A0\u0002+5\\\u0017\t\u001e;sS\n,H/\u001a3Rk\u0006d\u0017NZ5feR\u0011Q\n\u0019\u0005\u0006Cv\u0003\rAY\u0001\u0004iB,\u0007C\u0001\u000fd\u0013\t!WM\u0001\u0003UsB,\u0017B\u00014\r\u0005\u0015!\u0016\u0010]3t\u0011\u0015q&L\"\u0001i)\ri\u0015N\u001b\u0005\u0006C\u001e\u0004\rA\u0019\u0005\u0006W\u001e\u0004\r\u0001P\u0001\bi\u0016\u0014XnU=n\u0011\u0015i'L\"\u0001o\u0003=i7.\u0011;ue&\u0014W\u000f^3e%\u00164GcA8siB\u0011A\u0004]\u0005\u0003cB\u0013qAU3g)J,W\rC\u0003tY\u0002\u0007!-A\u0002qe\u0016DQa\u000f7A\u0002qBQ!\u001c.\u0007\u0002Y$\"a\\<\t\u000bm*\b\u0019\u0001\u001f\t\u000beTf\u0011\u0001>\u0002\u0013M$\u0018MY5mSj,GCA'|\u0011\u0015a\u0005\u00101\u0001N\u0011\u0015i(L\"\u0001\u007f\u0003Ui7.\u0011;ue&\u0014W\u000f^3e'R\f'\r\\3SK\u001a$B!T@\u0002\u0002!)1\u000f a\u0001E\")1\b a\u0001y!1QP\u0017D\u0001\u0003\u000b!2!TA\u0004\u0011\u0019Y\u00141\u0001a\u0001y!9\u00111\u0002.\u0007\u0002\u00055\u0011!E7l+:\fG\u000f\u001e:jEV$X\r\u001a*fMR\u0019q.a\u0004\t\rm\nI\u00011\u0001=\u0011\u001d\tYA\u0017D\u0001\u0003'!2a\\A\u000b\u0011!\t9\"!\u0005A\u0002\u0005e\u0011\u0001\u00034vY2t\u0015-\\3\u0011\u0007q\tY\"\u0003\u0003\u0002\u001e\u0005}!\u0001\u0002(b[\u0016L1!!\t\r\u0005\u0015q\u0015-\\3t\u0011\u001d\t)C\u0017D\u0001\u0003O\t\u0001#\\6BiR\u0014\u0018NY;uK\u0012$\u0006.[:\u0015\t\u0005%\u0012q\u0006\t\u00049\u0005-\u0012bAA\u0017!\n!A\u000b[5t\u0011\u0019Y\u00141\u0005a\u0001y!9\u00111\u0007.\u0007\u0002\u0005U\u0012!E7l\u0003R$(/\u001b2vi\u0016$\u0017\nZ3oiR\u0019q.a\u000e\t\rm\n\t\u00041\u0001=\u0011\u001d\tYD\u0017D\u0001\u0003{\t!#\\6BiR\u0014\u0018NY;uK\u0012\u001cV\r\\3diR)q.a\u0010\u0002D!9\u0011\u0011IA\u001d\u0001\u0004i\u0015\u0001B9vC2DaaOA\u001d\u0001\u0004a\u0004bBA$5\u001a\u0005\u0011\u0011J\u0001\r[.lU\r\u001e5pI\u000e\u000bG\u000e\u001c\u000b\n\u001b\u0006-\u0013qJA*\u0003KBq!!\u0014\u0002F\u0001\u0007A(\u0001\u0005sK\u000e,\u0017N^3s\u0011!\t\t&!\u0012A\u0002\u0005e\u0011AC7fi\"|GMT1nK\"A\u0011QKA#\u0001\u0004\t9&A\u0003uCJ<7\u000fE\u0003\u0002Z\u0005}#MD\u0002\u0019\u00037J1!!\u0018\u0007\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0019\u0002d\t!A*[:u\u0015\r\tiF\u0002\u0005\t\u0003O\n)\u00051\u0001\u0002j\u0005!\u0011M]4t!\u0015\tI&a\u0018N\u0011\u001d\t9E\u0017D\u0001\u0003[\"r!TA8\u0003g\n)\bC\u0004\u0002r\u0005-\u0004\u0019\u0001\u001f\u0002\r5,G\u000f[8e\u0011!\t)&a\u001bA\u0002\u0005]\u0003\u0002CA4\u0003W\u0002\r!!\u001b\t\u000f\u0005\u001d#L\"\u0001\u0002zQ)Q*a\u001f\u0002~!9\u0011\u0011OA<\u0001\u0004a\u0004\u0002CA4\u0003o\u0002\r!!\u001b\t\u000f\u0005\u001d#L\"\u0001\u0002\u0002R)Q*a!\u0002\b\"9\u0011QQA@\u0001\u0004i\u0015A\u0002;be\u001e,G\u000f\u0003\u0005\u0002h\u0005}\u0004\u0019AA5\u0011\u001d\t9E\u0017D\u0001\u0003\u0017#r!TAG\u0003\u001f\u000b\t\nC\u0004\u0002N\u0005%\u0005\u0019\u0001\u001f\t\u0011\u0005E\u0013\u0011\u0012a\u0001\u00033A\u0001\"a\u001a\u0002\n\u0002\u0007\u0011\u0011\u000e\u0005\b\u0003\u000fRf\u0011AAK)%i\u0015qSAM\u00037\u000bi\nC\u0004\u0002N\u0005M\u0005\u0019A'\t\u000f\u0005E\u00141\u0013a\u0001y!A\u0011QKAJ\u0001\u0004\t9\u0006\u0003\u0005\u0002h\u0005M\u0005\u0019AA5\u0011\u001d\t9E\u0017D\u0001\u0003C#r!TAR\u0003K\u000b9\u000bC\u0004\u0002\u0006\u0006}\u0005\u0019A'\t\u0011\u0005U\u0013q\u0014a\u0001\u0003/B\u0001\"a\u001a\u0002 \u0002\u0007\u0011\u0011\u000e\u0005\b\u0003WSf\u0011AAW\u00035i7NT;mY\u0006\u0014\u0018pQ1mYR)Q*a,\u00022\"9\u0011\u0011OAU\u0001\u0004a\u0004\u0002CA+\u0003S\u0003\r!a\u0016\t\u000f\u0005U&L\"\u0001\u00028\u0006!Rn\u001b*v]RLW.Z+oSZ,'o]3SK\u001a,\u0012!\u0014\u0005\b\u0003wSf\u0011AA_\u0003\u0019i7NW3s_R\u0019Q*a0\t\u000f\u0005\u0005\u0017\u0011\u0018a\u0001E\u0006\u0011A\u000f\u001d\u0005\b\u0003\u000bTf\u0011AAd\u0003\u0019i7nQ1tiR)Q*!3\u0002L\"1A*a1A\u00025Cq!!4\u0002D\u0002\u0007!-\u0001\u0002qi\"9\u0011\u0011[\u000f\u0007\u0002\u0005M\u0017aC1ui\u0006\u001c\u0007.\\3oiN$B!!6\u0002pJ!\u0011q[An\r\u0019\tI.\b\u0001\u0002V\naAH]3gS:,W.\u001a8u}A\u00191#!8\n\u0007\u0005}'AA\u0006BiR\f7\r[7f]R\u001cXaBAr\u0003/\u0004\u0013Q\u001d\u0002\u0004!>\u001c\bc\u0001\u000f\u0002h&!\u0011\u0011^Av\u0005!\u0001vn]5uS>t\u0017bAAw\u0019\tI\u0001k\\:ji&|gn\u001d\u0005\b\u0003c\fy\r1\u0001=\u0003\u0019\u0019\u00180\u001c2pY\"9\u0011Q_\u000f\u0007\u0002\u0005]\u0018\u0001E;qI\u0006$X-\u0011;uC\u000eDW.\u001a8u+\u0011\tIPa\u0004\u0015\r\u0005m\u0018q B\u000e)\u0011\tiP!\u0001\u000f\u0007I\ny\u0010C\u0004\u0002r\u0006M\b\u0019\u0001\u001f\t\u0015\t\r\u00111_A\u0001\u0002\b\u0011)!\u0001\u0006fm&$WM\\2fIE\u0002bAa\u0002\u0003\n\t5Q\"\u0001\u0003\n\u0007\t-AA\u0001\u0005DY\u0006\u001c8\u000fV1h!\r\u0011$q\u0002\u0003\t\u0005#\t\u0019P1\u0001\u0003\u0014\t\tA+E\u0002\u0018\u0005+\u00012\u0001\u0007B\f\u0013\r\u0011IB\u0002\u0002\u0004\u0003:L\b\u0002\u0003B\u000f\u0003g\u0004\rA!\u0004\u0002\u0015\u0005$H/Y2i[\u0016tG\u000fC\u0004\u0003\"u1\tAa\t\u0002!I,Wn\u001c<f\u0003R$\u0018m\u00195nK:$X\u0003\u0002B\u0013\u0005k!BAa\n\u0003,Q!!\u0011\u0006B\u0017\u001d\r\u0011$1\u0006\u0005\b\u0003c\u0014y\u00021\u0001=\u0011)\u0011yCa\b\u0002\u0002\u0003\u000f!\u0011G\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004C\u0002B\u0004\u0005\u0013\u0011\u0019\u0004E\u00023\u0005k!\u0001B!\u0005\u0003 \t\u0007!1\u0003\u0005\b\u0005sib\u0011\u0001B\u001e\u0003!\u0019X\r^(x]\u0016\u0014HC\u0002B\u001f\u0005\u007f\u0011\tED\u00023\u0005\u007fAq!!=\u00038\u0001\u0007A\bC\u0004\u0003D\t]\u0002\u0019\u0001\u001f\u0002\u00119,wo\\<oKJDqAa\u0012\u001e\r\u0003\u0011I%A\u0004tKRLeNZ8\u0015\r\t-#Q\nB(\u001d\r\u0011$Q\n\u0005\b\u0003c\u0014)\u00051\u0001=\u0011\u0019\t'Q\ta\u0001E\"9!1K\u000f\u0007\u0002\tU\u0013AD:fi\u0006sgn\u001c;bi&|gn\u001d\u000b\u0007\u0005/\u0012IFa\u0017\u000f\u0007I\u0012I\u0006C\u0004\u0002r\nE\u0003\u0019\u0001\u001f\t\u0011\tu#\u0011\u000ba\u0001\u0005?\na!\u00198o_R\u001c\b#\u0002\r\u0003b\t\u0015\u0014b\u0001B2\r\tQAH]3qK\u0006$X\r\u001a \u0011\u0007q\u00119'\u0003\u0003\u0003j\t-$AC!o]>$\u0018\r^5p]&\u0019!Q\u000e\u0007\u0003\u0017\u0005sgn\u001c;bi&|gn\u001d\u0005\b\u0005cjb\u0011\u0001B:\u0003\u001d\u0019X\r\u001e(b[\u0016$bA!\u001e\u0003x\tedb\u0001\u001a\u0003x!9\u0011\u0011\u001fB8\u0001\u0004a\u0004\u0002\u0003B>\u0005_\u0002\r!!\u0007\u0002\t9\fW.\u001a\u0005\b\u0005\u007fjb\u0011\u0001BA\u0003A\u0019X\r\u001e)sSZ\fG/Z,ji\"Lg\u000e\u0006\u0004\u0003\u0004\n\u0015%q\u0011\b\u0004e\t\u0015\u0005bBAy\u0005{\u0002\r\u0001\u0010\u0005\u0007w\tu\u0004\u0019\u0001\u001f\t\u000f\t-UD\"\u0001\u0003\u000e\u000691/\u001a;GY\u0006<GC\u0002BH\u0005#\u0013\u0019JD\u00023\u0005#Cq!!=\u0003\n\u0002\u0007A\b\u0003\u0005\u0003\u0016\n%\u0005\u0019\u0001BL\u0003\u00151G.Y4t!\ra\"\u0011T\u0005\u0005\u00057\u0013iJA\u0004GY\u0006<7+\u001a;\n\u0007\t}EB\u0001\u0005GY\u0006<7+\u001a;t\u0011\u001d\u0011\u0019+\bD\u0001\u0005K\u000b\u0011B]3tKR4E.Y4\u0015\r\t\u001d&\u0011\u0016BV\u001d\r\u0011$\u0011\u0016\u0005\b\u0003c\u0014\t\u000b1\u0001=\u0011!\u0011)J!)A\u0002\t]\u0005bBAi;\u0019\u0005!q\u0016\u000b\u0005\u0005c\u00139L\u0005\u0003\u00034\u0006mgABAm;\u0001\u0011\t,B\u0004\u0002d\nM\u0006%!:\t\r1\u0013i\u000b1\u0001N\u0011\u001d\t)0\bD\u0001\u0005w+BA!0\u0003NR1!q\u0018Bb\u0005\u001f$BA!1\u0003F:\u0019!Ga1\t\r1\u0013I\f1\u0001N\u0011)\u00119M!/\u0002\u0002\u0003\u000f!\u0011Z\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004C\u0002B\u0004\u0005\u0013\u0011Y\rE\u00023\u0005\u001b$\u0001B!\u0005\u0003:\n\u0007!1\u0003\u0005\t\u0005;\u0011I\f1\u0001\u0003L\"9!\u0011E\u000f\u0007\u0002\tMW\u0003\u0002Bk\u0005K$BAa6\u0003\\R!!\u0011\u001cBo\u001d\r\u0011$1\u001c\u0005\u0007\u0019\nE\u0007\u0019A'\t\u0015\t}'\u0011[A\u0001\u0002\b\u0011\t/\u0001\u0006fm&$WM\\2fIQ\u0002bAa\u0002\u0003\n\t\r\bc\u0001\u001a\u0003f\u0012A!\u0011\u0003Bi\u0005\u0004\u0011\u0019\u0002C\u0004\u0003jv1\tAa;\u0002\rM,G\u000fU8t)\u0019\u0011iOa<\u0003r:\u0019!Ga<\t\r1\u00139\u000f1\u0001N\u0011!\u0011\u0019Pa:A\u0002\u0005\u0015\u0018A\u00028foB|7\u000fC\u0004\u0003xv1\tA!?\u0002\u000fM,G\u000fV=qKR1!1 B\u007f\u0005\u007ft1A\rB\u007f\u0011\u0019a%Q\u001fa\u0001\u001b\"9\u0011\u0011\u0019B{\u0001\u0004\u0011\u0007bBB\u0002;\u0019\u00051QA\u0001\u000bI\u00164\u0017N\\3UsB,GCBB\u0004\u0007\u0013\u0019YAD\u00023\u0007\u0013Aa\u0001TB\u0001\u0001\u0004i\u0005bBAa\u0007\u0003\u0001\rA\u0019\u0005\b\u0007\u001fib\u0011AB\t\u0003%\u0019X\r^*z[\n|G\u000e\u0006\u0004\u0004\u0014\rU1q\u0003\b\u0004e\rU\u0001B\u0002'\u0004\u000e\u0001\u0007Q\n\u0003\u0004<\u0007\u001b\u0001\r\u0001\u0010\u0005\b\u00077ib\u0011AB\u000f\u0003-\u0019X\r^(sS\u001eLg.\u00197\u0015\r\r}1QEB\u0015!\ra2\u0011E\u0005\u0004\u0007G\u0001&\u0001\u0003+za\u0016$&/Z3\t\u0011\r\u001d2\u0011\u0004a\u0001\u0007?\t!\u0001\u001e;\t\u000f\r-2\u0011\u0004a\u0001\u001b\u0006AqN]5hS:\fG\u000eC\u0004\u00040u1\ta!\r\u0002\u001f\r\f\u0007\u000f^;sKZ\u000b'/[1cY\u0016$2aKB\u001a\u0011\u001d\u0019)d!\fA\u0002q\nAA\u001e2mK\"91\u0011H\u000f\u0007\u0002\rm\u0012!\u0007:fM\u0016\u0014XM\\2f\u0007\u0006\u0004H/\u001e:fIZ\u000b'/[1cY\u0016$2!TB\u001f\u0011\u001d\u0019)da\u000eA\u0002qBqa!\u0011\u001e\r\u0003\u0019\u0019%\u0001\u000bdCB$XO]3e-\u0006\u0014\u0018.\u00192mKRK\b/\u001a\u000b\u0004E\u000e\u0015\u0003bBB\u001b\u0007\u007f\u0001\r\u0001\u0010\u0005\b\u0007\u0013jb\u0011AB&\u0003-\u0019XO\u00199biR,'O\\:\u0015\t\r531\u000b\t\u00061\r=\u0013\u0011N\u0005\u0004\u0007#2!AB(qi&|g\u000e\u0003\u0004M\u0007\u000f\u0002\r!\u0014\u0003\b\u0007/j\"\u0011IB-\u0005)!UmY8sCR|'o]\t\u0004/\rm\u0003\u0003BB/\u0007?j\u0011!\b\u0004\n\u0007Cj\u0002\u0013aA\u0001\u0007G\u0012\u0011#T1de>$UmY8sCR|'/\u00119j'\u0015\u0019y\u0006IB3!\u0011\u0019ifa\u001a\n\u0007\r%DE\u0001\u0007EK\u000e|'/\u0019;pe\u0006\u0003\u0018\u000e\u0003\u0004*\u0007?\"\tA\u000b\u0003\t\u0007_\u001ayF!\u0001\u0004r\tq1kY8qK\u0012+7m\u001c:bi>\u0014X\u0003BB:\u0007K\u000b2aFB;!\u0019\u00199h!\u001f\u0004$6\u00111q\f\u0004\b\u0007w\u001ay\u0006AB?\u0005Yi\u0015m\u0019:p'\u000e|\u0007/\u001a#fG>\u0014\u0018\r^8s\u0003BLW\u0003BB@\u0007\u000f\u001b2a!\u001f!\u0011)!4\u0011\u0010BC\u0002\u0013\u000511Q\u000b\u0003\u0007\u000b\u00032AMBD\t!\u0011\tb!\u001fC\u0002\r%\u0015CA\f6\u0011-\u0019ii!\u001f\u0003\u0002\u0003\u0006Ia!\"\u0002\rM\u001cw\u000e]3!\u0011\u001d\u00012\u0011\u0010C\u0001\u0007##Baa%\u0004\u0016B11qOB=\u0007\u000bCq\u0001NBH\u0001\u0004\u0019)\tC\u00040\u0007s\"\ta!'\u0015\t\r\u001551\u0014\u0005\u0007w\r]\u0005\u0019\u0001\u001f\t\u000f\t\u001bI\b\"\u0001\u0004 R!1QQBQ\u0011\u0019Y4Q\u0014a\u0001yA\u0019!g!*\u0005\u0011\tE1Q\u000eb\u0001\u0007\u0013C\u0001b!+\u0004`\u0019\r11V\u0001\u000fg\u000e|\u0007/\u001a#fG>\u0014\u0018\r^8s+\u0011\u0019ika-\u0015\t\r=6Q\u0017\t\u0007\u0007o\u001aig!-\u0011\u0007I\u001a\u0019\f\u0002\u0005\u0003\u0012\r\u001d&\u0019ABE\u0011\u001da5q\u0015a\u0001\u0007c#\u0001b!/\u0004`\t\u000531\u0018\u0002\u000e)J,W\rR3d_J\fGo\u001c:\u0016\t\ruFQH\t\u0004/\r}\u0006CBB<\u0007\u0003$YDB\u0004\u0004D\u000e}\u0003a!2\u0003+5\u000b7M]8Ue\u0016,G)Z2pe\u0006$xN]!qSV!1qYBi'\u0011\u0019\tm!3\u0011\r\r]41ZBh\u0013\u0011\u0019ima\u001a\u0003!Q\u0013X-\u001a#fG>\u0014\u0018\r^8s\u0003BL\u0007c\u0001\u001a\u0004R\u0012A!\u0011CBa\u0005\u0004\u0019\u0019.\u0005\u0002\u0018\u001b\"QAj!1\u0003\u0006\u0004%\tea6\u0016\u0005\r=\u0007\"DBn\u0007\u0003\u0014\t\u0011)A\u0005\u0007\u001f\u001ci.A\u0003ue\u0016,\u0007%C\u0002M\u0007\u0017Dq\u0001EBa\t\u0003\u0019\t\u000f\u0006\u0003\u0004d\u000e\u0015\bCBB<\u0007\u0003\u001cy\rC\u0004M\u0007?\u0004\raa4\t\u000f!\u001b\t\r\"\u0001\u0004jR111^Bx\u0007ctAa!<\u0004V6\u00111\u0011\u0019\u0005\u0007'\u000e\u001d\b\u0019\u0001\u001f\t\rU\u001b9\u000f1\u0001=\u0011!\t\tn!1\u0005\u0002\rUXCAB|%\u0011\u0019I0a7\u0007\u000f\u0005e7\u0011\u0019\u0001\u0004x\u00169\u00111]B}A\u0005\u0015\b\u0002CA{\u0007\u0003$\taa@\u0016\t\u0011\u0005AQ\u0002\u000b\u0005\t\u0007!\t\u0002\u0006\u0003\u0004l\u0012\u0015\u0001B\u0003C\u0004\u0007{\f\t\u0011q\u0001\u0005\n\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\t\u001d!\u0011\u0002C\u0006!\r\u0011DQ\u0002\u0003\t\t\u001f\u0019iP1\u0001\u0003\u0014\t\t\u0011\t\u0003\u0005\u0003\u001e\ru\b\u0019\u0001C\u0006\u0011!\u0011\tc!1\u0005\u0002\u0011UQ\u0003\u0002C\f\tC!Baa4\u0005\u001a!QA1\u0004C\n\u0003\u0003\u0005\u001d\u0001\"\b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0003\b\t%Aq\u0004\t\u0004e\u0011\u0005B\u0001\u0003C\b\t'\u0011\rAa\u0005\t\u0011\t%8\u0011\u0019C\u0001\tK!Baa4\u0005(!A!1\u001fC\u0012\u0001\u0004\t)\u000f\u0003\u0005\u0003x\u000e\u0005G\u0011\u0001C\u0016)\u0011\u0019y\r\"\f\t\u000f\u0005\u0005G\u0011\u0006a\u0001E\"A11ABa\t\u0003!\t\u0004\u0006\u0003\u0004P\u0012M\u0002bBAa\t_\u0001\rA\u0019\u0005\t\u0007\u001f\u0019\t\r\"\u0001\u00058Q!1q\u001aC\u001d\u0011\u0019YDQ\u0007a\u0001yA\u0019!\u0007\"\u0010\u0005\u0011\tE1q\u0017b\u0001\u0007'$\u0001\u0002\"\u0011\u0004`\t\u0005A1\t\u0002\u0012)f\u0004X\r\u0016:fK\u0012+7m\u001c:bi>\u0014X\u0003\u0002C#\t_\n2a\u0006C$!\u0019\u00199\b\"\u0013\u0005n\u00199A1JB0\u0001\u00115#!G'bGJ|G+\u001f9f)J,W\rR3d_J\fGo\u001c:Ba&,B\u0001b\u0014\u0005XM\u0019A\u0011\n\u0011\t\u0017\r\u001dB\u0011\nBC\u0002\u0013\u0005A1K\u000b\u0003\t+\u00022A\rC,\t!\u0011\t\u0002\"\u0013C\u0002\u0011e\u0013cA\f\u0004 !YAQ\fC%\u0005\u0003\u0005\u000b\u0011\u0002C+\u0003\r!H\u000f\t\u0005\b!\u0011%C\u0011\u0001C1)\u0011!\u0019\u0007\"\u001a\u0011\r\r]D\u0011\nC+\u0011!\u00199\u0003b\u0018A\u0002\u0011U\u0003\u0002CB\u000e\t\u0013\"\t\u0001\"\u001b\u0015\t\r}A1\u000e\u0005\u0007\u0019\u0012\u001d\u0004\u0019A'\u0011\u0007I\"y\u0007\u0002\u0005\u0003\u0012\u0011}\"\u0019\u0001C-\u0011!!\u0019ha\u0018\u0007\u0004\u0011U\u0014!\u0005;za\u0016$&/Z3EK\u000e|'/\u0019;peV!Aq\u000fC?)\u0011!I\bb \u0011\r\r]Dq\bC>!\r\u0011DQ\u0010\u0003\t\u0005#!\tH1\u0001\u0005Z!A1q\u0005C9\u0001\u0004!Y\b\u0002\u0005\u0005\u0004\u000e}#\u0011\tCC\u0005=\u0019\u00160\u001c2pY\u0012+7m\u001c:bi>\u0014X\u0003\u0002CD\u000b\u0017\t2a\u0006CE!\u0019\u00199\bb#\u0006\n\u00199AQRB0\u0001\u0011=%aF'bGJ|7+_7c_2$UmY8sCR|'/\u00119j+\u0011!\t\nb'\u0014\t\u0011-E1\u0013\t\u0007\u0007o\")\n\"'\n\t\u0011]5q\r\u0002\u0013'fl'm\u001c7EK\u000e|'/\u0019;pe\u0006\u0003\u0018\u000eE\u00023\t7#\u0001B!\u0005\u0005\f\n\u0007AQT\t\u0003/qB1\"!=\u0005\f\n\u0015\r\u0011\"\u0011\u0005\"V\u0011A\u0011\u0014\u0005\u000e\tK#YI!A!\u0002\u0013!I\nb*\u0002\u000fMLXNY8mA%!\u0011\u0011\u001fCK\u0011\u001d\u0001B1\u0012C\u0001\tW#B\u0001\",\u00050B11q\u000fCF\t3C\u0001\"!=\u0005*\u0002\u0007A\u0011\u0014\u0005\t\u0003#$Y\t\"\u0001\u00054V\u0011AQ\u0017\n\u0005\to\u000bYNB\u0004\u0002Z\u0012-\u0005\u0001\".\u0006\u000f\u0005\rHq\u0017\u0011\u0002f\"A\u0011Q\u001fCF\t\u0003!i,\u0006\u0003\u0005@\u0012-G\u0003\u0002Ca\t\u001b$B\u0001\"'\u0005D\"QAQ\u0019C^\u0003\u0003\u0005\u001d\u0001b2\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0003\b\t%A\u0011\u001a\t\u0004e\u0011-G\u0001\u0003C\b\tw\u0013\rAa\u0005\t\u0011\tuA1\u0018a\u0001\t\u0013D\u0001B!\t\u0005\f\u0012\u0005A\u0011[\u000b\u0005\t'$i\u000e\u0006\u0003\u0005\u001a\u0012U\u0007B\u0003Cl\t\u001f\f\t\u0011q\u0001\u0005Z\u0006QQM^5eK:\u001cW\r\n\u001d\u0011\r\t\u001d!\u0011\u0002Cn!\r\u0011DQ\u001c\u0003\t\t\u001f!yM1\u0001\u0003\u0014!A!\u0011\bCF\t\u0003!\t\u000f\u0006\u0003\u0005\u001a\u0012\r\bb\u0002B\"\t?\u0004\r\u0001\u0010\u0005\t\u0005\u000f\"Y\t\"\u0001\u0005hR!A\u0011\u0014Cu\u0011\u0019\tGQ\u001da\u0001E\"A!1\u000bCF\t\u0003!i\u000f\u0006\u0003\u0005\u001a\u0012=\b\u0002\u0003B/\tW\u0004\rAa\u0018\t\u0011\tED1\u0012C\u0001\tg$B\u0001\"'\u0005v\"A!1\u0010Cy\u0001\u0004\tI\u0002\u0003\u0005\u0003\u0000\u0011-E\u0011\u0001C})\u0011!I\nb?\t\rm\"9\u00101\u0001=\u0011!\u0011Y\tb#\u0005\u0002\u0011}H\u0003\u0002CM\u000b\u0003A\u0001B!&\u0005~\u0002\u0007!q\u0013\u0005\t\u0005G#Y\t\"\u0001\u0006\u0006Q!A\u0011TC\u0004\u0011!\u0011)*b\u0001A\u0002\t]\u0005c\u0001\u001a\u0006\f\u0011A!\u0011\u0003CA\u0005\u0004!i\n\u0003\u0005\u0006\u0010\u0001\u0011\rQ\"\u0001Y\u0003%!(/Z3Ck&dG\r\u000b\u0005\u0006\u000e\u0015MQ\u0011DC\u000f!\rARQC\u0005\u0004\u000b/1!A\u00033faJ,7-\u0019;fI\u0006\u0012Q1D\u0001\u001b+N,\u0007\u0005Y5oi\u0016\u0014h.\u00197/O\u0016t\u0007\rI5ogR,\u0017\rZ\u0011\u0003\u000b?\taA\r\u00182c9\u0002DaBC\u0012\u0001\t\u0005QQ\u0005\u0002\u0007\u0007>l\u0007/\u0019;\u0012\u0007])9\u0003E\u0002\u001d\u000bS1\u0011\"b\u000b\u0001!\u0003\r\t!\"\f\u0003\u001d5\u000b7M]8D_6\u0004\u0018\r^!qSN)Q\u0011\u0006\u0011\u00060A\u0019A$\"\r\n\u0007\u0015MbEA\u0005D_6\u0004\u0018\r^!qS\"1\u0011&\"\u000b\u0005\u0002)2q!\"\u000f\u0006*\u0005)YDA\u000bNC\u000e\u0014xnQ8na\u0006$\u0018N\u00197f'fl'm\u001c7\u0014\u0007\u0015]\u0002\u0005\u0003\u0006\u0002r\u0016]\"\u0011!Q\u0001\nqBq\u0001EC\u001c\t\u0003)\t\u0005\u0006\u0003\u0006D\u0015\u001d\u0003\u0003BC#\u000boi!!\"\u000b\t\u000f\u0005EXq\ba\u0001y!A\u0011\u0011[C\u001c\t\u0003)Y%\u0006\u0002\u0006NI!QqJAn\r\u001d\tI.b\u000e\u0001\u000b\u001b*q!a9\u0006P\u0001\n)\u000f\u000b\u0005\u0006J\u0015MQQKC\u000fC\t)9&A+Vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018biR\f7\r[7f]R\u001c\b\rI5ogR,\u0017\r\u001a\u0011pe\u0002JW\u000e]8si\u0002\u0002\u0017N\u001c;fe:\fGN\f3fG>\u0014\u0018\r^8sg:z\u0006\r\t4pe\u0002JgNZ5yAMLh\u000e^1y\u0011!\t)0b\u000e\u0005\u0002\u0015mS\u0003BC/\u000bS\"B!b\u0018\u0006lQ\u0019A(\"\u0019\t\u0015\u0015\rT\u0011LA\u0001\u0002\b))'\u0001\u0006fm&$WM\\2fIe\u0002bAa\u0002\u0003\n\u0015\u001d\u0004c\u0001\u001a\u0006j\u0011A!\u0011CC-\u0005\u0004\u0011\u0019\u0002\u0003\u0005\u0003\u001e\u0015e\u0003\u0019AC4Q!)I&b\u0005\u0006p\u0015u\u0011EAC9\u0003i+6/\u001a\u0011aS:$XM\u001d8bY:*\b\u000fZ1uK\u0006#H/Y2i[\u0016tG\u000f\u0019\u0011j]N$X-\u00193!_J\u0004\u0013.\u001c9peR\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018eK\u000e|'/\u0019;peNts\f\u0019\u0011g_J\u0004\u0013N\u001c4jq\u0002\u001a\u0018P\u001c;bq\"A!\u0011EC\u001c\t\u0003))(\u0006\u0003\u0006x\u0015\u0005Ec\u0001\u001f\u0006z!QQ1PC:\u0003\u0003\u0005\u001d!\" \u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0007\u0005\u000f\u0011I!b \u0011\u0007I*\t\t\u0002\u0005\u0003\u0012\u0015M$\u0019\u0001B\nQ!)\u0019(b\u0005\u0006\u0006\u0016u\u0011EACD\u0003i+6/\u001a\u0011aS:$XM\u001d8bY:\u0012X-\\8wK\u0006#H/Y2i[\u0016tG\u000f\u0019\u0011j]N$X-\u00193!_J\u0004\u0013.\u001c9peR\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018eK\u000e|'/\u0019;peNts\f\u0019\u0011g_J\u0004\u0013N\u001c4jq\u0002\u001a\u0018P\u001c;bq\"AQ1RC\u001c\t\u0003)i)\u0001\ttKR$\u0016\u0010]3TS\u001et\u0017\r^;sKR\u0019A(b$\t\r\u0005,I\t1\u0001cQ!)I)b\u0005\u0006\u0014\u0016u\u0011EACK\u0003E+6/\u001a\u0011aS:$XM\u001d8bY:\u001aX\r^%oM>\u0004\u0007%\u001b8ti\u0016\fG\rI8sA%l\u0007o\u001c:uA\u0001Lg\u000e^3s]\u0006dg\u0006Z3d_J\fGo\u001c:t]}\u0003\u0007EZ8sA%tg-\u001b=!gftG/\u0019=\t\u0011\tMSq\u0007C\u0001\u000b3#2\u0001PCN\u0011!\u0011i&b&A\u0002\t}\u0003\u0006CCL\u000b')y*\"\b\"\u0005\u0015\u0005\u0016\u0001W+tK\u0002\u0002\u0017N\u001c;fe:\fGNL:fi\u0006sgn\u001c;bi&|gn\u001d1!S:\u001cH/Z1eA=\u0014\b%[7q_J$\b\u0005Y5oi\u0016\u0014h.\u00197/I\u0016\u001cwN]1u_J\u001chf\u00181!M>\u0014\b%\u001b8gSb\u00043/\u001f8uCbD\u0001B!\u001d\u00068\u0011\u0005QQ\u0015\u000b\u0004y\u0015\u001d\u0006\u0002\u0003B>\u000bG\u0003\r!!\u0007)\u0011\u0015\rV1CCV\u000b;\t#!\",\u0002#V\u001bX\r\t1j]R,'O\\1m]M,GOT1nK\u0002\u0004\u0013N\\:uK\u0006$\u0007e\u001c:!S6\u0004xN\u001d;!A&tG/\u001a:oC2tC-Z2pe\u0006$xN]:/?\u0002\u0004cm\u001c:!S:4\u0017\u000e\u001f\u0011ts:$\u0018\r\u001f\u0005\t\u0005\u007f*9\u0004\"\u0001\u00062R\u0019A(b-\t\rm*y\u000b1\u0001=Q!)y+b\u0005\u00068\u0016u\u0011EAC]\u0003i+6/\u001a\u0011aS:$XM\u001d8bY:\u001aX\r\u001e)sSZ\fG/Z,ji\"Lg\u000e\u0019\u0011j]N$X-\u00193!_J\u0004\u0013.\u001c9peR\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018eK\u000e|'/\u0019;peNts\f\u0019\u0011g_J\u0004\u0013N\u001c4jq\u0002\u001a\u0018P\u001c;bq\"QQQXC\u0015\u0003\u0003%\u0019!b0\u0002+5\u000b7M]8D_6\u0004\u0018\r^5cY\u0016\u001c\u00160\u001c2pYR!Q1ICa\u0011\u001d\t\t0b/A\u0002q2q!\"2\u0006*\u0005)9MA\nNC\u000e\u0014xnQ8na\u0006$\u0018N\u00197f)J,WmE\u0002\u0006D\u0002B\u0011\u0002TCb\u0005\u0003\u0005\u000b\u0011B'\t\u000fA)\u0019\r\"\u0001\u0006NR!QqZCi!\u0011))%b1\t\r1+Y\r1\u0001N\u0011!\t\t.b1\u0005\u0002\u0015UWCACl%\u0011)I.a7\u0007\u000f\u0005eW1\u0019\u0001\u0006X\u00169\u00111]CmA\u0005\u0015\b\u0006CCj\u000b'))&\"\b\t\u0011\u0005UX1\u0019C\u0001\u000bC,B!b9\u0006pR!QQ]Cy)\riUq\u001d\u0005\u000b\u000bS,y.!AA\u0004\u0015-\u0018aC3wS\u0012,gnY3%cE\u0002bAa\u0002\u0003\n\u00155\bc\u0001\u001a\u0006p\u0012A!\u0011CCp\u0005\u0004\u0011\u0019\u0002\u0003\u0005\u0003\u001e\u0015}\u0007\u0019ACwQ!)y.b\u0005\u0006p\u0015u\u0001\u0002\u0003B\u0011\u000b\u0007$\t!b>\u0016\t\u0015eh1\u0001\u000b\u0004\u001b\u0016m\bBCC\u007f\u000bk\f\t\u0011q\u0001\u0006\u0000\u0006YQM^5eK:\u001cW\rJ\u00193!\u0019\u00119A!\u0003\u0007\u0002A\u0019!Gb\u0001\u0005\u0011\tEQQ\u001fb\u0001\u0005'A\u0003\"\">\u0006\u0014\u0015\u0015UQ\u0004\u0005\t\r\u0013)\u0019\r\"\u0001\u0007\f\u00059\u0001o\\:`I\u0015\fHcA\u0016\u0007\u000e!Aaq\u0002D\u0004\u0001\u0004\t)/A\u0002q_ND\u0003Bb\u0002\u0006\u0014\u0019MQQD\u0011\u0003\r+\t\u0001+V:fA\u0001Lg\u000e^3s]\u0006dgf]3u!>\u001c\b\rI5ogR,\u0017\r\u001a\u0011pe\u0002JW\u000e]8si\u0002\u0002\u0017N\u001c;fe:\fGN\f3fG>\u0014\u0018\r^8sg:z\u0006\r\t4pe\u0002JgNZ5yAMLh\u000e^1y\u0011!\u0011I/b1\u0005\u0002\u0019eAcA'\u0007\u001c!A!1\u001fD\f\u0001\u0004\t)\u000f\u000b\u0005\u0007\u0018\u0015Ma1CC\u000f\u0011!1\t#b1\u0005\u0002\u0019\r\u0012a\u0002;qK~#S-\u001d\u000b\u0004W\u0019\u0015\u0002b\u0002D\u0014\r?\u0001\rAY\u0001\u0002i\"BaqDC\n\rW)i\"\t\u0002\u0007.\u0005\tVk]3!A&tG/\u001a:oC2t3/\u001a;UsB,\u0007\rI5ogR,\u0017\r\u001a\u0011pe\u0002JW\u000e]8si\u0002\u0002\u0017N\u001c;fe:\fGN\f3fG>\u0014\u0018\r^8sg:z\u0006\r\t4pe\u0002JgNZ5yAMLh\u000e^1y\u0011!\u001190b1\u0005\u0002\u0019EBcA'\u00074!9\u0011\u0011\u0019D\u0018\u0001\u0004\u0011\u0007\u0006\u0003D\u0018\u000b'1Y#\"\b\t\u0011\r\rQ1\u0019C\u0001\rs!2!\u0014D\u001e\u0011\u001d\t\tMb\u000eA\u0002\tD\u0003Bb\u000e\u0006\u0014\u0019}RQD\u0011\u0003\r\u0003\nA+V:fA\u0001Lg\u000e^3s]\u0006dg\u0006Z3gS:,G+\u001f9fA\u0002Jgn\u001d;fC\u0012\u0004sN\u001d\u0011j[B|'\u000f\u001e\u0011aS:$XM\u001d8bY:\"WmY8sCR|'o\u001d\u0018`A\u00022wN\u001d\u0011j]\u001aL\u0007\u0010I:z]R\f\u0007\u0010\u0003\u0005\u0007F\u0015\rG\u0011\u0001D$\u0003)\u0019\u00180\u001c2pY~#S-\u001d\u000b\u0004W\u0019%\u0003BB\u001e\u0007D\u0001\u0007A\b\u000b\u0005\u0007D\u0015MaQJC\u000fC\t1y%A*Vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018tKR\u001c\u00160\u001c2pY\u0002\u0004\u0013N\\:uK\u0006$\u0007e\u001c:!S6\u0004xN\u001d;!A&tG/\u001a:oC2tC-Z2pe\u0006$xN]:/?\u0002\u0004cm\u001c:!S:4\u0017\u000e\u001f\u0011ts:$\u0018\r\u001f\u0005\t\u0007\u001f)\u0019\r\"\u0001\u0007TQ\u0019QJ\"\u0016\t\rm2\t\u00061\u0001=Q!1\t&b\u0005\u0007N\u0015u\u0001B\u0003D.\u000bS\t\t\u0011b\u0001\u0007^\u0005\u0019R*Y2s_\u000e{W\u000e]1uS\ndW\r\u0016:fKR!Qq\u001aD0\u0011\u0019ae\u0011\fa\u0001\u001b\u001a9a1MC\u0015\u0003\u0019\u0015$AE\"p[B\fG/\u001b2mKRK\b/\u001a+sK\u0016\u001c2A\"\u0019!\u0011-\u00199C\"\u0019\u0003\u0002\u0003\u0006Iaa\b\t\u000fA1\t\u0007\"\u0001\u0007lQ!aQ\u000eD8!\u0011))E\"\u0019\t\u0011\r\u001db\u0011\u000ea\u0001\u0007?A\u0001ba\u0007\u0007b\u0011\u0005a1\u000f\u000b\u0005\u0007?1)\b\u0003\u0004M\rc\u0002\r!\u0014\u0015\t\rc*\u0019B\"\u001f\u0006\u001e\u0005\u0012a1P\u0001V+N,\u0007\u0005Y5oi\u0016\u0014h.\u00197/g\u0016$xJ]5hS:\fG\u000e\u0019\u0011j]N$X-\u00193!_J\u0004\u0013.\u001c9peR\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018eK\u000e|'/\u0019;peNts\f\u0019\u0011g_J\u0004\u0013N\u001c4jq\u0002\u001a\u0018P\u001c;bq\"QaqPC\u0015\u0003\u0003%\u0019A\"!\u0002%\r{W\u000e]1uS\ndW\rV=qKR\u0013X-\u001a\u000b\u0005\r[2\u0019\t\u0003\u0005\u0004(\u0019u\u0004\u0019AB\u0010\u0011!\u0019y#\"\u000b\u0005\u0002\u0019\u001dEcA\u0016\u0007\n\"91Q\u0007DC\u0001\u0004a\u0004\u0006\u0003DC\u000b'1i)\"\b\"\u0005\u0019=\u0015AJ+tK\u0002\u0002\u0017N\u001c;fe:\fGNL2baR,(/\u001a,be&\f'\r\\3aA%t7\u000f^3bI\"A1\u0011HC\u0015\t\u00031\u0019\nF\u0002N\r+Cqa!\u000e\u0007\u0012\u0002\u0007A\b\u000b\u0005\u0007\u0012\u0016Ma\u0011TC\u000fC\t1Y*\u0001\u0019Vg\u0016\u0004\u0003-\u001b8uKJt\u0017\r\u001c\u0018sK\u001a,'/\u001a8dK\u000e\u000b\u0007\u000f^;sK\u00124\u0016M]5bE2,\u0007\rI5ogR,\u0017\r\u001a\u0005\t\u0007\u0003*I\u0003\"\u0001\u0007 R\u0019!M\")\t\u000f\rUbQ\u0014a\u0001y!BaQTC\n\rK+i\"\t\u0002\u0007(\u0006YSk]3!A&tG/\u001a:oC2t3-\u00199ukJ,GMV1sS\u0006\u0014G.\u001a+za\u0016\u0004\u0007%\u001b8ti\u0016\fG\rB\u0004\u0007,\u0002\u0011\tA\",\u0003\u0007I+h.E\u0002\u0018\r_\u00032\u0001\bDY\r%1\u0019\f\u0001I\u0001$\u00031)LA\u0007Sk:\u001cuN\u001c;fqR\f\u0005/[\n\u0004\rc\u0003\u0003\u0002\u0003D]\rc3\tAb/\u0002\u0017\r,(O]3oiVs\u0017\u000e^\u000b\u0003\r{\u00032\u0001\bD`\t\u001d1\t\r\u0001B\u0001\r\u0007\u0014qbQ8na&d\u0017\r^5p]Vs\u0017\u000e^\t\u0004/\u0019\u0015\u0007c\u0001\u000f\u0007H\u001aIa\u0011\u001a\u0001\u0011\u0002G\u0005a1\u001a\u0002\u001a\u0007>l\u0007/\u001b7bi&|g.\u00168ji\u000e{g\u000e^3yi\u0006\u0003\u0018nE\u0002\u0007H\u0002B\u0001Bb4\u0007H\u001a\u0005a\u0011[\u0001\u0007g>,(oY3\u0016\u0005\u0019M\u0007\u0003\u0002Dk\r?l!Ab6\u000b\t\u0019eg1\\\u0001\u0005kRLGNC\u0002\u0007^\u0012\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\rC49N\u0001\u0006T_V\u00148-\u001a$jY\u0016D\u0003B\"4\u0006\u0014\u0019\u0015XQD\u0011\u0003\rO\f\u0001l\u0019\u0018f]\u000edwn]5oOR\u0013X-Z\u0017tifdW\rI!Q\u0013N\u0004\u0013M]3!]><\b\u0005Z3qe\u0016\u001c\u0017\r^3ew\u0001\u001awN\\:vYR\u0004C\u000f[3!g\u000e\fG.\u00193pG\u00022wN\u001d\u0011n_J,\u0007%\u001b8g_Jl\u0017\r^5p]\"Aa1\u001eDd\r\u0003\t9,\u0001\u0003c_\u0012L\b\u0006\u0003Du\u000b'1)/\"\b)\u0011\u0019\u001dW1\u0003Ds\u000b;A\u0003Bb0\u0006\u0014\u0019\u0015XQ\u0004\u0015\t\ro+\u0019B\":\u0006\u001e!Aaq\u001fDY\r\u00031I0A\u0003v]&$8/\u0006\u0002\u0007|B1\u0011\u0011\fD\u007f\r{KAAb@\u0002d\tA\u0011\n^3sCR|'\u000f\u000b\u0005\u0007v\u0016MaQ]C\u000fQ!1\t,b\u0005\u0007f\u0016u\u0001\u0006\u0003DU\u000b'1)/\"\b")
public abstract class Universe
extends scala.reflect.api.Universe {
    public abstract TreeGen treeBuild();

    public interface TreeGen {
        public Trees.TreeApi mkAttributedQualifier(Types.TypeApi var1);

        public Trees.TreeApi mkAttributedQualifier(Types.TypeApi var1, Symbols.SymbolApi var2);

        public Trees.RefTreeApi mkAttributedRef(Types.TypeApi var1, Symbols.SymbolApi var2);

        public Trees.RefTreeApi mkAttributedRef(Symbols.SymbolApi var1);

        public Trees.TreeApi stabilize(Trees.TreeApi var1);

        public Trees.TreeApi mkAttributedStableRef(Types.TypeApi var1, Symbols.SymbolApi var2);

        public Trees.TreeApi mkAttributedStableRef(Symbols.SymbolApi var1);

        public Trees.RefTreeApi mkUnattributedRef(Symbols.SymbolApi var1);

        public Trees.RefTreeApi mkUnattributedRef(Names.NameApi var1);

        public Trees.ThisApi mkAttributedThis(Symbols.SymbolApi var1);

        public Trees.RefTreeApi mkAttributedIdent(Symbols.SymbolApi var1);

        public Trees.RefTreeApi mkAttributedSelect(Trees.TreeApi var1, Symbols.SymbolApi var2);

        public Trees.TreeApi mkMethodCall(Symbols.SymbolApi var1, Names.NameApi var2, List<Types.TypeApi> var3, List<Trees.TreeApi> var4);

        public Trees.TreeApi mkMethodCall(Symbols.SymbolApi var1, List<Types.TypeApi> var2, List<Trees.TreeApi> var3);

        public Trees.TreeApi mkMethodCall(Symbols.SymbolApi var1, List<Trees.TreeApi> var2);

        public Trees.TreeApi mkMethodCall(Trees.TreeApi var1, List<Trees.TreeApi> var2);

        public Trees.TreeApi mkMethodCall(Symbols.SymbolApi var1, Names.NameApi var2, List<Trees.TreeApi> var3);

        public Trees.TreeApi mkMethodCall(Trees.TreeApi var1, Symbols.SymbolApi var2, List<Types.TypeApi> var3, List<Trees.TreeApi> var4);

        public Trees.TreeApi mkMethodCall(Trees.TreeApi var1, List<Types.TypeApi> var2, List<Trees.TreeApi> var3);

        public Trees.TreeApi mkNullaryCall(Symbols.SymbolApi var1, List<Types.TypeApi> var2);

        public Trees.TreeApi mkRuntimeUniverseRef();

        public Trees.TreeApi mkZero(Types.TypeApi var1);

        public Trees.TreeApi mkCast(Trees.TreeApi var1, Types.TypeApi var2);
    }

    public interface RunContextApi {
        public CompilationUnitContextApi currentUnit();

        public Iterator<CompilationUnitContextApi> units();
    }

    public interface MacroCompatApi
    extends Internals.CompatApi {
        public MacroCompatibleSymbol MacroCompatibleSymbol(Symbols.SymbolApi var1);

        public MacroCompatibleTree MacroCompatibleTree(Trees.TreeApi var1);

        public CompatibleTypeTree CompatibleTypeTree(Trees.TypeTreeApi var1);

        public void captureVariable(Symbols.SymbolApi var1);

        public Trees.TreeApi referenceCapturedVariable(Symbols.SymbolApi var1);

        public Types.TypeApi capturedVariableType(Symbols.SymbolApi var1);

        public /* synthetic */ Universe scala$reflect$macros$Universe$MacroCompatApi$$$outer();

        public class CompatibleTypeTree {
            private final Trees.TypeTreeApi tt;
            public final /* synthetic */ MacroCompatApi $outer;

            public Trees.TypeTreeApi setOriginal(Trees.TreeApi tree) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$CompatibleTypeTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setOriginal(this.tt, tree);
            }

            public /* synthetic */ MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$CompatibleTypeTree$$$outer() {
                return this.$outer;
            }

            public CompatibleTypeTree(MacroCompatApi $outer, Trees.TypeTreeApi tt) {
                this.tt = tt;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }

        public class MacroCompatibleTree {
            private final Trees.TreeApi tree;
            public final /* synthetic */ MacroCompatApi $outer;

            public Attachments attachments() {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).attachments(this.tree);
            }

            public <T> Trees.TreeApi updateAttachment(T attachment, ClassTag<T> evidence$11) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).updateAttachment(this.tree, attachment, evidence$11);
            }

            public <T> Trees.TreeApi removeAttachment(ClassTag<T> evidence$12) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).removeAttachment(this.tree, evidence$12);
            }

            public void pos_$eq(Position pos) {
                ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setPos(this.tree, pos);
            }

            public Trees.TreeApi setPos(Position newpos) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setPos(this.tree, newpos);
            }

            public void tpe_$eq(Types.TypeApi t) {
                ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setType(this.tree, t);
            }

            public Trees.TreeApi setType(Types.TypeApi tp) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setType(this.tree, tp);
            }

            public Trees.TreeApi defineType(Types.TypeApi tp) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).defineType(this.tree, tp);
            }

            public void symbol_$eq(Symbols.SymbolApi sym) {
                ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setSymbol(this.tree, sym);
            }

            public Trees.TreeApi setSymbol(Symbols.SymbolApi sym) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setSymbol(this.tree, sym);
            }

            public /* synthetic */ MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleTree$$$outer() {
                return this.$outer;
            }

            public MacroCompatibleTree(MacroCompatApi $outer, Trees.TreeApi tree) {
                this.tree = tree;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }

        public class MacroCompatibleSymbol {
            private final Symbols.SymbolApi symbol;
            public final /* synthetic */ MacroCompatApi $outer;

            public Attachments attachments() {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).attachments(this.symbol);
            }

            public <T> Symbols.SymbolApi updateAttachment(T attachment, ClassTag<T> evidence$9) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).updateAttachment(this.symbol, attachment, evidence$9);
            }

            public <T> Symbols.SymbolApi removeAttachment(ClassTag<T> evidence$10) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).removeAttachment(this.symbol, evidence$10);
            }

            public Symbols.SymbolApi setTypeSignature(Types.TypeApi tpe) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setInfo(this.symbol, tpe);
            }

            public Symbols.SymbolApi setAnnotations(Seq<Annotations.AnnotationApi> annots) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setAnnotations(this.symbol, annots);
            }

            public Symbols.SymbolApi setName(Names.NameApi name) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setName(this.symbol, name);
            }

            public Symbols.SymbolApi setPrivateWithin(Symbols.SymbolApi sym) {
                return ((MacroInternalApi)this.scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer().scala$reflect$macros$Universe$MacroCompatApi$$$outer().internal()).setPrivateWithin(this.symbol, sym);
            }

            public /* synthetic */ MacroCompatApi scala$reflect$macros$Universe$MacroCompatApi$MacroCompatibleSymbol$$$outer() {
                return this.$outer;
            }

            public MacroCompatibleSymbol(MacroCompatApi $outer, Symbols.SymbolApi symbol) {
                this.symbol = symbol;
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }
    }

    public interface MacroInternalApi
    extends Internals.InternalApi {
        public Scopes.ScopeApi enter(Scopes.ScopeApi var1, Symbols.SymbolApi var2);

        public Scopes.ScopeApi unlink(Scopes.ScopeApi var1, Symbols.SymbolApi var2);

        public Trees.TreeApi changeOwner(Trees.TreeApi var1, Symbols.SymbolApi var2, Symbols.SymbolApi var3);

        public TreeGen gen();

        public Attachments attachments(Symbols.SymbolApi var1);

        public <T> Symbols.SymbolApi updateAttachment(Symbols.SymbolApi var1, T var2, ClassTag<T> var3);

        public <T> Symbols.SymbolApi removeAttachment(Symbols.SymbolApi var1, ClassTag<T> var2);

        public Symbols.SymbolApi setOwner(Symbols.SymbolApi var1, Symbols.SymbolApi var2);

        public Symbols.SymbolApi setInfo(Symbols.SymbolApi var1, Types.TypeApi var2);

        public Symbols.SymbolApi setAnnotations(Symbols.SymbolApi var1, Seq<Annotations.AnnotationApi> var2);

        public Symbols.SymbolApi setName(Symbols.SymbolApi var1, Names.NameApi var2);

        public Symbols.SymbolApi setPrivateWithin(Symbols.SymbolApi var1, Symbols.SymbolApi var2);

        public Symbols.SymbolApi setFlag(Symbols.SymbolApi var1, Object var2);

        public Symbols.SymbolApi resetFlag(Symbols.SymbolApi var1, Object var2);

        public Attachments attachments(Trees.TreeApi var1);

        public <T> Trees.TreeApi updateAttachment(Trees.TreeApi var1, T var2, ClassTag<T> var3);

        public <T> Trees.TreeApi removeAttachment(Trees.TreeApi var1, ClassTag<T> var2);

        public Trees.TreeApi setPos(Trees.TreeApi var1, Position var2);

        public Trees.TreeApi setType(Trees.TreeApi var1, Types.TypeApi var2);

        public Trees.TreeApi defineType(Trees.TreeApi var1, Types.TypeApi var2);

        public Trees.TreeApi setSymbol(Trees.TreeApi var1, Symbols.SymbolApi var2);

        public Trees.TypeTreeApi setOriginal(Trees.TypeTreeApi var1, Trees.TreeApi var2);

        public void captureVariable(Symbols.SymbolApi var1);

        public Trees.TreeApi referenceCapturedVariable(Symbols.SymbolApi var1);

        public Types.TypeApi capturedVariableType(Symbols.SymbolApi var1);

        public Option<List<Trees.TreeApi>> subpatterns(Trees.TreeApi var1);

        public /* synthetic */ Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer();

        public interface MacroDecoratorApi
        extends Internals.InternalApi.DecoratorApi {
            public <T extends Scopes.ScopeApi> MacroScopeDecoratorApi scopeDecorator(T var1);

            public <T extends Trees.TypeTreeApi> MacroTypeTreeDecoratorApi typeTreeDecorator(T var1);

            public /* synthetic */ MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer();

            public class MacroTreeDecoratorApi<T extends Trees.TreeApi>
            extends Internals.InternalApi.DecoratorApi.TreeDecoratorApi<T> {
                @Override
                public T tree() {
                    return super.tree();
                }

                public T changeOwner(Symbols.SymbolApi prev, Symbols.SymbolApi next2) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().changeOwner((Trees.TreeApi)this.tree(), prev, next2);
                }

                public Attachments attachments() {
                    return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().attachments((Trees.TreeApi)this.tree());
                }

                public <A> T updateAttachment(A attachment, ClassTag<A> evidence$5) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().updateAttachment((Trees.TreeApi)this.tree(), attachment, evidence$5);
                }

                public <A> T removeAttachment(ClassTag<A> evidence$6) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().removeAttachment((Trees.TreeApi)this.tree(), evidence$6);
                }

                public T setPos(Position newpos) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setPos((Trees.TreeApi)this.tree(), newpos);
                }

                public T setType(Types.TypeApi tp) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setType((Trees.TreeApi)this.tree(), tp);
                }

                public T defineType(Types.TypeApi tp) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().defineType((Trees.TreeApi)this.tree(), tp);
                }

                public T setSymbol(Symbols.SymbolApi sym) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setSymbol((Trees.TreeApi)this.tree(), sym);
                }

                public /* synthetic */ MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTreeDecoratorApi$$$outer() {
                    return (MacroDecoratorApi)this.$outer;
                }

                public MacroTreeDecoratorApi(MacroDecoratorApi $outer, T tree) {
                    super($outer, tree);
                }
            }

            public class MacroScopeDecoratorApi<T extends Scopes.ScopeApi> {
                private final T scope;
                public final /* synthetic */ MacroDecoratorApi $outer;

                public T scope() {
                    return this.scope;
                }

                public T enter(Symbols.SymbolApi sym) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().enter((Scopes.ScopeApi)this.scope(), sym);
                }

                public T unlink(Symbols.SymbolApi sym) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().unlink((Scopes.ScopeApi)this.scope(), sym);
                }

                public /* synthetic */ MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroScopeDecoratorApi$$$outer() {
                    return this.$outer;
                }

                public MacroScopeDecoratorApi(MacroDecoratorApi $outer, T scope) {
                    this.scope = scope;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }

            public class MacroSymbolDecoratorApi<T extends Symbols.SymbolApi>
            extends Internals.InternalApi.DecoratorApi.SymbolDecoratorApi<T> {
                @Override
                public T symbol() {
                    return super.symbol();
                }

                public Attachments attachments() {
                    return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().attachments((Symbols.SymbolApi)this.symbol());
                }

                public <A> T updateAttachment(A attachment, ClassTag<A> evidence$7) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().updateAttachment((Symbols.SymbolApi)this.symbol(), attachment, evidence$7);
                }

                public <A> T removeAttachment(ClassTag<A> evidence$8) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().removeAttachment((Symbols.SymbolApi)this.symbol(), evidence$8);
                }

                public T setOwner(Symbols.SymbolApi newowner) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setOwner((Symbols.SymbolApi)this.symbol(), newowner);
                }

                public T setInfo(Types.TypeApi tpe) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setInfo((Symbols.SymbolApi)this.symbol(), tpe);
                }

                public T setAnnotations(Seq<Annotations.AnnotationApi> annots) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setAnnotations((Symbols.SymbolApi)this.symbol(), annots);
                }

                public T setName(Names.NameApi name) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setName((Symbols.SymbolApi)this.symbol(), name);
                }

                public T setPrivateWithin(Symbols.SymbolApi sym) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setPrivateWithin((Symbols.SymbolApi)this.symbol(), sym);
                }

                public T setFlag(Object flags) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setFlag((Symbols.SymbolApi)this.symbol(), flags);
                }

                public T resetFlag(Object flags) {
                    return (T)this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().resetFlag((Symbols.SymbolApi)this.symbol(), flags);
                }

                public /* synthetic */ MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroSymbolDecoratorApi$$$outer() {
                    return (MacroDecoratorApi)this.$outer;
                }

                public MacroSymbolDecoratorApi(MacroDecoratorApi $outer, T symbol) {
                    super($outer, symbol);
                }
            }

            public class MacroTypeTreeDecoratorApi<T extends Trees.TypeTreeApi> {
                private final T tt;
                public final /* synthetic */ MacroDecoratorApi $outer;

                public T tt() {
                    return this.tt;
                }

                public Trees.TypeTreeApi setOriginal(Trees.TreeApi tree) {
                    return this.scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTypeTreeDecoratorApi$$$outer().scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer().setOriginal((Trees.TypeTreeApi)this.tt(), tree);
                }

                public /* synthetic */ MacroDecoratorApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$MacroTypeTreeDecoratorApi$$$outer() {
                    return this.$outer;
                }

                public MacroTypeTreeDecoratorApi(MacroDecoratorApi $outer, T tt) {
                    this.tt = tt;
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                }
            }
        }
    }

    public interface CompilationUnitContextApi {
        public SourceFile source();

        public Trees.TreeApi body();
    }
}

