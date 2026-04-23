/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Function0;
import scala.Function1;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Names;
import scala.reflect.api.Position;
import scala.reflect.api.Types;
import scala.reflect.io.AbstractFile;

@ScalaSignature(bytes="\u0006\u0001\rec!C\u0001\u0003!\u0003\r\t!CB)\u0005\u001d\u0019\u00160\u001c2pYNT!a\u0001\u0003\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0006\r\u00059!/\u001a4mK\u000e$(\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00171i\u0011AB\u0005\u0003\u001b\u0019\u0011a!\u00118z%\u00164\u0007\"B\b\u0001\t\u0003\u0001\u0012A\u0002\u0013j]&$H\u0005F\u0001\u0012!\tY!#\u0003\u0002\u0014\r\t!QK\\5u\t\u0015)\u0002A!\u0001\u0017\u0005\u0019\u0019\u00160\u001c2pYF\u0011qC\u0007\t\u0003\u0017aI!!\u0007\u0004\u0003\t9+H\u000e\u001c\n\u00047)ib\u0001\u0002\u000f\u0001\u0001i\u0011A\u0002\u0010:fM&tW-\\3oiz\u0002\"AH\u0010\u000e\u0003\u00011\u0001\u0002\t\u0001\u0011\u0002\u0007\u0005\u0011E\n\u0002\n'fl'm\u001c7Ba&\u001c\"a\b\u0006\t\u000b=yB\u0011\u0001\t\t\u000b\u0011zb\u0011A\u0013\u0002\u000b=<h.\u001a:\u0016\u0003\u0019\u0002\"A\b\u000b\u0005\u000b!z\"\u0011A\u0015\u0003\u00119\u000bW.\u001a+za\u0016\f\"a\u0006\u0016\u0011\u0005yY\u0013B\u0001\u0017.\u0005\u0011q\u0015-\\3\n\u00059\u0012!!\u0002(b[\u0016\u001c\b\"\u0002\u0019 \r\u0003\t\u0014\u0001\u00028b[\u0016,\u0012A\r\t\u0003g\u001dj\u0011a\b\u0005\u0006k}1\tAN\u0001\tMVdGNT1nKV\tq\u0007\u0005\u00029w9\u00111\"O\u0005\u0003u\u0019\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001f>\u0005\u0019\u0019FO]5oO*\u0011!H\u0002\u0005\u0006\u007f}1\t\u0001Q\u0001\u0004a>\u001cX#A!\u0011\u0005y\u0011\u0015BA\"E\u0005!\u0001vn]5uS>t\u0017BA#\u0003\u0005%\u0001vn]5uS>t7\u000fC\u0003H?\u0011\u0005\u0001*\u0001\u0004jgRK\b/Z\u000b\u0002\u0013B\u00111BS\u0005\u0003\u0017\u001a\u0011qAQ8pY\u0016\fg\u000eC\u0003N?\u0011\u0005a*\u0001\u0004bgRK\b/Z\u000b\u0002\u001fB\u0011a\u0004\u0015\u0003\u0006#\u0002\u0011\tA\u0015\u0002\u000b)f\u0004XmU=nE>d\u0017CA\fT%\r!VK\n\u0004\u00059\u0001\u00011\u000b\u0005\u0002\u001f-\u001aAq\u000b\u0001I\u0001\u0004\u0003AvJA\u0007UsB,7+_7c_2\f\u0005/[\n\u0004-*i\u0002\"B\bW\t\u0003\u0001R\u0001\u0002\u0015W\u0005m\u0003\"A\b/\n\u0005uk#\u0001\u0003+za\u0016t\u0015-\\3\t\u000b}3f\u0011\u00011\u0002#Q|G+\u001f9f\u0007>t7\u000f\u001e:vGR|'/F\u0001b!\tq\"-\u0003\u0002dI\n!A+\u001f9f\u0013\t)'AA\u0003UsB,7\u000fC\u0003h-\u001a\u0005\u0001.\u0001\u0005u_RK\b/Z%o)\t\t\u0017\u000eC\u0003kM\u0002\u0007\u0011-\u0001\u0003tSR,\u0007\"\u00027W\r\u0003\u0001\u0017A\u0002;p)f\u0004X\rC\u0003H-\u0012\u0015\u0003\nC\u0003N-\u0012\u0015c\nC\u0003q-\u001a\u0005\u0001*A\bjg\u000e{g\u000e\u001e:bm\u0006\u0014\u0018.\u00198u\u0011\u0015\u0011hK\"\u0001I\u0003-I7oQ8wCJL\u0017M\u001c;\t\u000bQ4f\u0011\u0001%\u0002\u0017%\u001c\u0018\t\\5bgRK\b/\u001a\u0005\u0006mZ3\t\u0001S\u0001\u000fSN\f%m\u001d;sC\u000e$H+\u001f9fQ\u0011)\bp_?\u0011\u0005-I\u0018B\u0001>\u0007\u0005)!W\r\u001d:fG\u0006$X\rZ\u0011\u0002y\u00061Rk]3!SN\f%m\u001d;sC\u000e$\b%\u001b8ti\u0016\fG-I\u0001\u007f\u0003\u0019\u0011d&M\u0019/a!1\u0011\u0011\u0001,\u0007\u0002!\u000bQ\"[:Fq&\u001cH/\u001a8uS\u0006d\u0007bBA\u0003-\u001a\u0005\u0011qA\u0001\u000bif\u0004X\rU1sC6\u001cXCAA\u0005!\u0015\tY!!\u0005'\u001d\rY\u0011QB\u0005\u0004\u0003\u001f1\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003'\t)B\u0001\u0003MSN$(bAA\b\r!1\u0011\u0011D\u0010\u0005\u0002!\u000ba![:UKJl\u0007bBA\u000f?\u0011\u0005\u0011qD\u0001\u0007CN$VM]7\u0016\u0005\u0005\u0005\u0002c\u0001\u0010\u0002$\u00119\u0011Q\u0005\u0001\u0003\u0002\u0005\u001d\"A\u0003+fe6\u001c\u00160\u001c2pYF\u0019q#!\u000b\u0013\u000b\u0005-\u0012Q\u0006\u0014\u0007\u000bq\u0001\u0001!!\u000b\u0011\u0007y\tyCB\u0006\u00022\u0001\u0001\n1!\u0001\u00024\u0005\u0005\"!\u0004+fe6\u001c\u00160\u001c2pY\u0006\u0003\u0018n\u0005\u0003\u00020)i\u0002BB\b\u00020\u0011\u0005\u0001#\u0002\u0004)\u0003_\u0011\u0011\u0011\b\t\u0004=\u0005m\u0012bAA\u001f[\tAA+\u001a:n\u001d\u0006lW\rC\u0004\u0002\u001a\u0005=BQ\t%\t\u0011\u0005u\u0011q\u0006C#\u0003?Aq!!\u0012\u00020\u0019\u0005\u0001*A\u0003jgZ\u000bG\u000eC\u0004\u0002J\u0005=b\u0011\u0001%\u0002\u0011%\u001c8\u000b^1cY\u0016Dq!!\u0014\u00020\u0019\u0005\u0001*A\u0003jgZ\u000b'\u000fC\u0004\u0002R\u0005=b\u0011\u0001%\u0002\u0015%\u001c\u0018iY2fgN|'\u000fC\u0004\u0002V\u0005=b\u0011\u0001%\u0002\u0011%\u001cx)\u001a;uKJDq!!\u0017\u00020\u0019\u0005\u0001*\u0001\u0005jgN+G\u000f^3s\u0011\u001d\ti&a\f\u0007\u0002!\u000bA\"[:Pm\u0016\u0014Hn\\1eK\u0012Dq!!\u0019\u00020\u0019\u0005\u0001*\u0001\u0004jg2\u000b'0\u001f\u0005\b\u0003K\ny\u0003\"\u0015I\u0003II7o\u0014<fe2|\u0017\rZ3e\u001b\u0016$\bn\u001c3\t\u000f\u0005%\u0014q\u0006D\u0001K\u0005A\u0011mY2fgN,G\rC\u0004\u0002n\u0005=b\u0011A\u0013\u0002\r\u001d,G\u000f^3s\u0011\u001d\t\t(a\f\u0007\u0002\u0015\naa]3ui\u0016\u0014\bbBA;\u0003_1\t\u0001S\u0001\u0010SN\u0004\u0016M]1n\u0003\u000e\u001cWm]:pe\"9\u0011\u0011PA\u0018\r\u0003A\u0015AD5t\u0007\u0006\u001cX-Q2dKN\u001cxN\u001d\u0005\b\u0003{\nyC\"\u0001I\u0003II7\u000fU1sC6<\u0016\u000e\u001e5EK\u001a\fW\u000f\u001c;\t\u000f\u0005\u0005\u0015q\u0006D\u0001\u0011\u0006i\u0011n\u001d\"z\u001d\u0006lW\rU1sC6Da!!\" \t\u0003A\u0015\u0001C5t\u001b\u0016$\bn\u001c3\t\r\u0005%uD\"\u0001I\u00035I7oQ8ogR\u0014Xo\u0019;pe\"9\u0011QR\u0010\u0005\u0002\u0005=\u0015\u0001C1t\u001b\u0016$\bn\u001c3\u0016\u0005\u0005E\u0005c\u0001\u0010\u0002\u0014\u00129\u0011Q\u0013\u0001\u0003\u0002\u0005]%\u0001D'fi\"|GmU=nE>d\u0017cA\f\u0002\u001aJ1\u00111TAO\u0003C1Q\u0001\b\u0001\u0001\u00033\u00032AHAP\r-\t\t\u000b\u0001I\u0001\u0004\u0003\t\u0019+!%\u0003\u001f5+G\u000f[8e'fl'm\u001c7Ba&\u001cR!a(\u000b\u0003[AaaDAP\t\u0003\u0001\u0002bBAC\u0003?#)\u0005\u0013\u0005\t\u0003\u001b\u000by\n\"\u0012\u0002\u0010\"9\u0011QVAP\r\u0003A\u0015\u0001F5t!JLW.\u0019:z\u0007>t7\u000f\u001e:vGR|'\u000f\u0003\u0005\u0002\u0006\u0005}e\u0011AA\u0004\u0011!\t\u0019,a(\u0007\u0002\u0005U\u0016a\u00029be\u0006l7o]\u000b\u0003\u0003o\u0003b!a\u0003\u0002\u0012\u0005%\u0001FBAYq\u0006mV0\t\u0002\u0002>\u0006ARk]3!AB\f'/Y7MSN$8\u000f\u0019\u0011j]N$X-\u00193\t\u0011\u0005\u0005\u0017q\u0014D\u0001\u0003k\u000b!\u0002]1sC6d\u0015n\u001d;t\u0011\u001d\t)-a(\u0007\u0002!\u000b\u0011\"[:WCJ\f'oZ:\t\u000f\u0005%\u0017q\u0014D\u0001A\u0006Q!/\u001a;ve:$\u0016\u0010]3\t\u0011\u00055\u0017q\u0014D\u0001\u0003\u000f\t!\"\u001a=dKB$\u0018n\u001c8t\u0011\u0019\t)g\bC\t\u0011\"1\u00111[\u0010\u0005\u0002!\u000b\u0001\"[:N_\u0012,H.\u001a\u0005\b\u0003/|B\u0011AAm\u0003!\t7/T8ek2,WCAAn!\rq\u0012Q\u001c\u0003\b\u0003?\u0004!\u0011AAq\u00051iu\u000eZ;mKNKXNY8m#\r9\u00121\u001d\n\u0007\u0003K\f9/!\t\u0007\u000bq\u0001\u0001!a9\u0011\u0007y\tIOB\u0006\u0002l\u0002\u0001\n1!\u0001\u0002n\u0006m'aD'pIVdWmU=nE>d\u0017\t]5\u0014\u000b\u0005%(\"!\f\t\r=\tI\u000f\"\u0001\u0011\u0011\u001d\t\u00190!;\u0007\u0002\u0015\n1\"\\8ek2,7\t\\1tg\"9\u00111[Au\t\u000bB\u0005\u0002CAl\u0003S$)%!7\t\r\u0005mx\u0004\"\u0001I\u0003\u001dI7o\u00117bgNDa!a@ \t\u0003A\u0015!D5t\u001b>$W\u000f\\3DY\u0006\u001c8\u000fC\u0004\u0003\u0004}!\tA!\u0002\u0002\u000f\u0005\u001c8\t\\1tgV\u0011!q\u0001\t\u0004=\t%Aa\u0002B\u0006\u0001\t\u0005!Q\u0002\u0002\f\u00072\f7o]*z[\n|G.E\u0002\u0018\u0005\u001f\u0011RA!\u0005\u0003\u0014=3Q\u0001\b\u0001\u0001\u0005\u001f\u00012A\bB\u000b\r-\u00119\u0002\u0001I\u0001\u0004\u0003\u0011IBa\u0002\u0003\u001d\rc\u0017m]:Ts6\u0014w\u000e\\!qSN!!Q\u0003\u0006V\u0011\u0019y!Q\u0003C\u0001!!9\u00111 B\u000b\t\u000bB\u0005\u0002\u0003B\u0002\u0005+!)E!\u0002\t\u000f\t\r\"Q\u0003D\u0001\u0011\u0006Y\u0011n\u001d)sS6LG/\u001b<f\u0011\u001d\u00119C!\u0006\u0007\u0002!\u000b\u0011\"[:Ok6,'/[2\t\u000f\t-\"Q\u0003D\u0001\u0011\u0006\u0019\u0012n\u001d#fe&4X\r\u001a,bYV,7\t\\1tg\"9!q\u0006B\u000b\r\u0003A\u0015aB5t)J\f\u0017\u000e\u001e\u0005\b\u0005g\u0011)B\"\u0001I\u0003=I7/\u00112tiJ\f7\r^\"mCN\u001c\b&\u0002B\u0019qnl\bb\u0002B\u001d\u0005+1\t\u0001S\u0001\fSN\u001c\u0015m]3DY\u0006\u001c8\u000fC\u0004\u0003>\tUa\u0011\u0001%\u0002\u0011%\u001c8+Z1mK\u0012D\u0001B!\u0011\u0003\u0016\u0019\u0005!1I\u0001\u0016W:|wO\u001c#je\u0016\u001cGoU;cG2\f7o]3t+\t\u0011)\u0005\u0005\u00039\u0005\u000f2\u0013b\u0001B%{\t\u00191+\u001a;\t\u0011\t5#Q\u0003D\u0001\u0003\u000f\t1BY1tK\u000ec\u0017m]:fg\"9!\u0011\u000bB\u000b\r\u0003)\u0013AB7pIVdW\rC\u0004\u0003V\tUa\u0011\u00011\u0002\u0011M,GN\u001a+za\u0016DqA!\u0017\u0003\u0016\u0019\u0005\u0001-\u0001\u0006uQ&\u001c\bK]3gSbD\u0001B!\u0018\u0003\u0016\u0019\u0005!qL\u0001\fgV\u0004XM\u001d)sK\u001aL\u0007\u0010F\u0002b\u0005CBqAa\u0019\u0003\\\u0001\u0007\u0011-\u0001\u0005tkB,'\u000f\u001e9f\u0011!\t)A!\u0006\u0007\u0002\u0005\u001d\u0001b\u0002B5\u0005+1\t!J\u0001\u0013aJLW.\u0019:z\u0007>t7\u000f\u001e:vGR|'\u000fC\u0004\u0003n}1\tAa\u001c\u0002\u001d\u0005\u001c8o\\2jCR,GMR5mKV\u0011!\u0011\u000f\t\u0005\u0005g\u0012I(\u0004\u0002\u0003v)\u0019!q\u000f\u0003\u0002\u0005%|\u0017\u0002\u0002B>\u0005k\u0012A\"\u00112tiJ\f7\r\u001e$jY\u0016DcAa\u001by\u0005\u007fj\u0018E\u0001BA\u0003u)6/\u001a\u0011aa>\u001chf]8ve\u000e,gFZ5mK\u0002\u0004\u0013N\\:uK\u0006$\u0007b\u0002BC?\u0019\u0005!qQ\u0001\fC:tw\u000e^1uS>t7/\u0006\u0002\u0003\nB1\u00111BA\t\u0005\u0017\u00032A\bBG\u0013\u0011\u0011yI!%\u0003\u0015\u0005sgn\u001c;bi&|g.C\u0002\u0003\u0014\n\u00111\"\u00118o_R\fG/[8og\"1!qS\u0010\u0007\u0002\u0015\nqbY8na\u0006t\u0017n\u001c8Ts6\u0014w\u000e\u001c\u0015\u0007\u0005+C(1T?\"\u0005\tu\u0015aQ+tK\u0002\u00027m\\7qC:LwN\u001c1!S:\u001cH/Z1eY\u0001\u0012W\u000f\u001e\u0011cK^\f'/\u001a\u0011pM\u0002\u0002xn]:jE2,\u0007e\u00195b]\u001e,7\u000fI5oA\t,\u0007.\u0019<j_JDaA!) \r\u0003)\u0013!C2p[B\fg.[8o\u0011\u001d\u0011)k\bD\u0001\u0005O\u000bq\u0002^=qKNKwM\\1ukJ,\u0017J\u001c\u000b\u0004C\n%\u0006B\u00026\u0003$\u0002\u0007\u0011\rC\u0004\u0003.~1\tAa,\u0002\r%tgm\\%o)\r\t'\u0011\u0017\u0005\u0007U\n-\u0006\u0019A1\t\r\tUvD\"\u0001a\u00035!\u0018\u0010]3TS\u001et\u0017\r^;sK\"1!\u0011X\u0010\u0007\u0002\u0001\fA!\u001b8g_\"9!QX\u0010\u0007\u0002\u0005\u001d\u0011\u0001F1mY>3XM\u001d:jI\u0012,gnU=nE>d7\u000f\u000b\u0004\u0003<b\u0014\t-`\u0011\u0003\u0005\u0007\fq#V:fA\u0001|g/\u001a:sS\u0012,7\u000f\u0019\u0011j]N$X-\u00193\t\u000f\t\u001dwD\"\u0001\u0002\b\u0005IqN^3se&$Wm\u001d\u0005\b\u0005\u0017|b\u0011AA\u0004\u00031\tG\u000e^3s]\u0006$\u0018N^3t\u0011\u0019\u0011ym\bD\u0001\u0011\u0006Y\u0011n]*z]RDW\r^5d\u0011\u0019\u0011\u0019n\bD\u0001\u0011\u0006A\u0012n]%na2,W.\u001a8uCRLwN\\!si&4\u0017m\u0019;\t\r\t]wD\"\u0001I\u00035I7\u000f\u0015:jm\u0006$X\r\u00165jg\"1!1\\\u0010\u0007\u0002!\u000b\u0011\"[:Qe&4\u0018\r^3\t\r\t}wD\"\u0001I\u0003=I7\u000f\u0015:pi\u0016\u001cG/\u001a3UQ&\u001c\bB\u0002Br?\u0019\u0005\u0001*A\u0006jgB\u0013x\u000e^3di\u0016$\u0007B\u0002Bt?\u0019\u0005\u0001*\u0001\u0005jgB+(\r\\5d\u0011\u0019\u0011Yo\bD\u0001K\u0005i\u0001O]5wCR,w+\u001b;iS:DaAa< \r\u0003A\u0015!C5t!\u0006\u001c7.Y4f\u0011\u0019\u0011\u0019p\bD\u0001\u0011\u0006q\u0011n\u001d)bG.\fw-Z\"mCN\u001c\bB\u0002B|?\u0019\u0005\u0001*\u0001\u0005jgN#\u0018\r^5d\u0011\u0019\u0011Yp\bD\u0001\u0011\u00069\u0011n\u001d$j]\u0006d\u0007B\u0002B\u0000?\u0019\u0005\u0001*\u0001\u0006jg\u0006\u00137\u000f\u001e:bGRDaaa\u0001 \r\u0003A\u0015AE5t\u0003\n\u001cHO]1di>3XM\u001d:jI\u0016Daaa\u0002 \r\u0003A\u0015aB5t\u001b\u0006\u001c'o\u001c\u0005\u0007\u0007\u0017yb\u0011\u0001%\u0002\u0017%\u001c\b+\u0019:b[\u0016$XM\u001d\u0005\u0007\u0007\u001fyb\u0011\u0001%\u0002\u001b%\u001c8\u000b]3dS\u0006d\u0017N_3e\u0011\u0019\u0019\u0019b\bD\u0001\u0011\u00061\u0011n\u001d&bm\u0006Daaa\u0006 \r\u0003A\u0015AC5t\u00136\u0004H.[2ji\"911D\u0010\u0007\u0002\ru\u0011AB8s\u000b2\u001cX\rF\u0002'\u0007?A\u0011b!\t\u0004\u001a\u0011\u0005\raa\t\u0002\u0007\u0005dG\u000f\u0005\u0003\f\u0007K1\u0013bAB\u0014\r\tAAHY=oC6,g\bC\u0004\u0004,}1\ta!\f\u0002\r\u0019LG\u000e^3s)\r13q\u0006\u0005\t\u0007c\u0019I\u00031\u0001\u00044\u0005!1m\u001c8e!\u0015Y1Q\u0007\u0014J\u0013\r\u00199D\u0002\u0002\n\rVt7\r^5p]FBqaa\u000f \r\u0003\u0019i$A\u0002nCB$2AJB \u0011!\u0019\te!\u000fA\u0002\r\r\u0013!\u00014\u0011\u000b-\u0019)D\n\u0014\t\u000f\r\u001dsD\"\u0001\u0004J\u0005A1/^2i)\"\fG\u000fF\u0002'\u0007\u0017B\u0001b!\r\u0004F\u0001\u000711\u0007\u0005\t\u0007\u001f\u0002!\u0019!D\u0001K\u0005Aaj\\*z[\n|G\u000e\u0005\u0003\u0004T\rUS\"\u0001\u0002\n\u0007\r]#A\u0001\u0005V]&4XM]:f\u0001")
public interface Symbols {
    public SymbolApi NoSymbol();

    public interface SymbolApi {
        public SymbolApi owner();

        public Names.NameApi name();

        public String fullName();

        public Position pos();

        public boolean isType();

        public TypeSymbolApi asType();

        public boolean isTerm();

        public TermSymbolApi asTerm();

        public boolean isMethod();

        public boolean isConstructor();

        public MethodSymbolApi asMethod();

        public boolean isOverloadedMethod();

        public boolean isModule();

        public ModuleSymbolApi asModule();

        public boolean isClass();

        public boolean isModuleClass();

        public ClassSymbolApi asClass();

        public AbstractFile associatedFile();

        public List<Annotations.AnnotationApi> annotations();

        public SymbolApi companionSymbol();

        public SymbolApi companion();

        public Types.TypeApi typeSignatureIn(Types.TypeApi var1);

        public Types.TypeApi infoIn(Types.TypeApi var1);

        public Types.TypeApi typeSignature();

        public Types.TypeApi info();

        public List<SymbolApi> allOverriddenSymbols();

        public List<SymbolApi> overrides();

        public List<SymbolApi> alternatives();

        public boolean isSynthetic();

        public boolean isImplementationArtifact();

        public boolean isPrivateThis();

        public boolean isPrivate();

        public boolean isProtectedThis();

        public boolean isProtected();

        public boolean isPublic();

        public SymbolApi privateWithin();

        public boolean isPackage();

        public boolean isPackageClass();

        public boolean isStatic();

        public boolean isFinal();

        public boolean isAbstract();

        public boolean isAbstractOverride();

        public boolean isMacro();

        public boolean isParameter();

        public boolean isSpecialized();

        public boolean isJava();

        public boolean isImplicit();

        public SymbolApi orElse(Function0<SymbolApi> var1);

        public SymbolApi filter(Function1<SymbolApi, Object> var1);

        public SymbolApi map(Function1<SymbolApi, SymbolApi> var1);

        public SymbolApi suchThat(Function1<SymbolApi, Object> var1);

        public /* synthetic */ Symbols scala$reflect$api$Symbols$SymbolApi$$$outer();
    }

    public interface TermSymbolApi
    extends SymbolApi {
        @Override
        public boolean isTerm();

        @Override
        public TermSymbolApi asTerm();

        public boolean isVal();

        public boolean isStable();

        public boolean isVar();

        public boolean isAccessor();

        public boolean isGetter();

        public boolean isSetter();

        public boolean isOverloaded();

        public boolean isLazy();

        @Override
        public boolean isOverloadedMethod();

        public SymbolApi accessed();

        public SymbolApi getter();

        public SymbolApi setter();

        public boolean isParamAccessor();

        public boolean isCaseAccessor();

        public boolean isParamWithDefault();

        public boolean isByNameParam();

        public /* synthetic */ Symbols scala$reflect$api$Symbols$TermSymbolApi$$$outer();
    }

    public interface TypeSymbolApi
    extends SymbolApi {
        public Types.TypeApi toTypeConstructor();

        public Types.TypeApi toTypeIn(Types.TypeApi var1);

        public Types.TypeApi toType();

        @Override
        public boolean isType();

        @Override
        public TypeSymbolApi asType();

        public boolean isContravariant();

        public boolean isCovariant();

        public boolean isAliasType();

        public boolean isAbstractType();

        public boolean isExistential();

        public List<SymbolApi> typeParams();

        public /* synthetic */ Symbols scala$reflect$api$Symbols$TypeSymbolApi$$$outer();
    }

    public interface ClassSymbolApi
    extends TypeSymbolApi {
        @Override
        public boolean isClass();

        @Override
        public ClassSymbolApi asClass();

        public boolean isPrimitive();

        public boolean isNumeric();

        public boolean isDerivedValueClass();

        public boolean isTrait();

        public boolean isAbstractClass();

        public boolean isCaseClass();

        public boolean isSealed();

        public Set<SymbolApi> knownDirectSubclasses();

        public List<SymbolApi> baseClasses();

        public SymbolApi module();

        public Types.TypeApi selfType();

        public Types.TypeApi thisPrefix();

        public Types.TypeApi superPrefix(Types.TypeApi var1);

        @Override
        public List<SymbolApi> typeParams();

        public SymbolApi primaryConstructor();

        public /* synthetic */ Symbols scala$reflect$api$Symbols$ClassSymbolApi$$$outer();
    }

    public interface MethodSymbolApi
    extends TermSymbolApi {
        @Override
        public boolean isMethod();

        @Override
        public MethodSymbolApi asMethod();

        public boolean isPrimaryConstructor();

        public List<SymbolApi> typeParams();

        public List<List<SymbolApi>> paramss();

        public List<List<SymbolApi>> paramLists();

        public boolean isVarargs();

        public Types.TypeApi returnType();

        public List<SymbolApi> exceptions();

        public /* synthetic */ Symbols scala$reflect$api$Symbols$MethodSymbolApi$$$outer();
    }

    public interface ModuleSymbolApi
    extends TermSymbolApi {
        public SymbolApi moduleClass();

        @Override
        public boolean isModule();

        @Override
        public ModuleSymbolApi asModule();

        public /* synthetic */ Symbols scala$reflect$api$Symbols$ModuleSymbolApi$$$outer();
    }
}

