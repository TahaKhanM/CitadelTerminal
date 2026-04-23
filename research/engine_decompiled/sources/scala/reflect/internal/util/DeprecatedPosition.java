/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(bytes="\u0006\u0001=4\u0011\"\u0001\u0002\u0011\u0002\u0007\u0005!A\u0003\u0016\u0003%\u0011+\u0007O]3dCR,G\rU8tSRLwN\u001c\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7C\u0001\u0001\f!\taQ\"D\u0001\t\u0013\tq\u0001B\u0001\u0004B]f\u0014VM\u001a\u0005\u0006!\u0001!\tAE\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t1\u0003\u0005\u0002\r)%\u0011Q\u0003\u0003\u0002\u0005+:LG\u000fC\u0003\u0018\u0001\u0011\u0005\u0001$\u0001\u0004pM\u001a\u001cX\r^\u000b\u00023A\u0019AB\u0007\u000f\n\u0005mA!AB(qi&|g\u000e\u0005\u0002\r;%\u0011a\u0004\u0003\u0002\u0004\u0013:$\b\u0006\u0002\f!G\u0015\u0002\"\u0001D\u0011\n\u0005\tB!A\u00033faJ,7-\u0019;fI\u0006\nA%A\u0006vg\u0016\u0004\u0003\r]8j]R\u0004\u0017%\u0001\u0014\u0002\u000bIr\u0013H\f\u0019\t\u000b!\u0002A\u0011A\u0015\u0002\u0019Q|7+\u001b8hY\u0016d\u0015N\\3\u0016\u0003)\u0002\"a\u000b\u0017\u000e\u0003\tI!!\f\u0002\u0003\u0011A{7/\u001b;j_:DCa\n\u00110c\u0005\n\u0001'A\u0006vg\u0016\u0004\u0003MZ8dkN\u0004\u0017%\u0001\u001a\u0002\rIr\u0013'\r\u00181\u0011\u0015!\u0004\u0001\"\u00016\u0003!\u0019\u0018MZ3MS:,W#\u0001\u000f)\tM\u0002s'M\u0011\u0002q\u0005QQo]3!A2Lg.\u001a1\t\u000bi\u0002A\u0011A\u001e\u0002\u0013\u0011\u0014wm\u0015;sS:<W#\u0001\u001f\u0011\u0005u\u0002eB\u0001\u0007?\u0013\ty\u0004\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0003\n\u0013aa\u0015;sS:<'BA \tQ\u0011I\u0004\u0005R\u0019\"\u0003\u0015\u000bq\"^:fA\u0001\u001c\bn\\<EK\n,x\r\u0019\u0005\u0006\u000f\u0002!\t\u0001S\u0001\u0011S:,F\u000e^5nCR,7k\\;sG\u0016$\"AK%\t\u000b)3\u0005\u0019A&\u0002\rM|WO]2f!\tYC*\u0003\u0002N\u0005\tQ1k\\;sG\u00164\u0015\u000e\\3)\t\u0019\u0003s*M\u0011\u0002!\u0006\u0019Ro]3!A\u001aLg.\u00197Q_NLG/[8oA\")!\u000b\u0001C\u0001'\u0006iA.\u001b8f/&$\bnQ1sCR$\"\u0001V,\u0011\t1)F\bP\u0005\u0003-\"\u0011a\u0001V;qY\u0016\u0014\u0004\"\u0002-R\u0001\u0004a\u0012\u0001C7bq^KG\r\u001e5)\tE\u0003#,M\u0011\u00027\u0006yQo]3!A2Lg.Z\"be\u0016$\b\rC\u0003^\u0001\u0011\u0005a,\u0001\u0006xSRD7k\\;sG\u0016$2AK0a\u0011\u0015QE\f1\u0001L\u0011\u0015\tG\f1\u0001\u001d\u0003\u0015\u0019\b.\u001b4uQ\u0011a\u0006eY\u0019\"\u0003\u0011\f\u0001&V:fA\u0001<\u0018\u000e\u001e5T_V\u00148-\u001a\u0015t_V\u00148-Z\u0015aA\u0005tG\r\t1xSRD7\u000b[5gi\u0002DQA\u001a\u0001\u0005\u0002U\nAb\u001d;beR|%\u000fU8j]RDC!\u001a\u0011ic\u0005\n\u0011.A\nVg\u0016\u0004\u0003m\u001d;beR\u0004\u0007%\u001b8ti\u0016\fG\rC\u0003l\u0001\u0011\u0005Q'\u0001\u0006f]\u0012|%\u000fU8j]RDCA\u001b\u0011nc\u0005\na.A\tVg\u0016\u0004\u0003-\u001a8eA\u0002Jgn\u001d;fC\u0012\u0004")
public interface DeprecatedPosition {
    public Option<Object> offset();

    public Position toSingleLine();

    public int safeLine();

    public String dbgString();

    public Position inUltimateSource(SourceFile var1);

    public Tuple2<String, String> lineWithCarat(int var1);

    public Position withSource(SourceFile var1, int var2);

    public int startOrPoint();

    public int endOrPoint();
}

