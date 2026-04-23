/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dbaB\u0001\u0003!\u0003\r\ta\u0003\u0002\n'R\u0014\u0018N\\4PaNT!a\u0001\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055qQ\"\u0001\u0005\n\u0005=A!AB!osJ+g\rC\u0003\u0012\u0001\u0011\u0005!#\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011Q\u0002F\u0005\u0003+!\u0011A!\u00168ji\")q\u0003\u0001C\u00011\u00051q.Z7qif$\"!\u0007\u0014\u0011\u0007iir$D\u0001\u001c\u0015\ta\u0002\"\u0001\u0006d_2dWm\u0019;j_:L!AH\u000e\u0003\u0007M+\u0017\u000f\u0005\u0002!G9\u0011Q\"I\u0005\u0003E!\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0013&\u0005\u0019\u0019FO]5oO*\u0011!\u0005\u0003\u0005\u0006OY\u0001\r\u0001K\u0001\u0003qN\u00042!D\u0015 \u0013\tQ\u0003B\u0001\u0006=e\u0016\u0004X-\u0019;fIzBQ\u0001\f\u0001\u0005\u00025\nQa\u001c6pS:$\"a\b\u0018\t\u000b\u001dZ\u0003\u0019\u0001\u0015\t\u000bA\u0002A\u0011A\u0019\u0002'1|gnZ3ti\u000e{W.\\8o!J,g-\u001b=\u0015\u0005}\u0011\u0004\"B\u00140\u0001\u0004\u0019\u0004c\u0001\u001b8?9\u0011Q\"N\u0005\u0003m!\tq\u0001]1dW\u0006<W-\u0003\u00029s\t!A*[:u\u0015\t1\u0004\u0002C\u0003<\u0001\u0011\u0005A(A\tue&lGK]1jY&twm\u00159bG\u0016$\"aH\u001f\t\u000byR\u0004\u0019A\u0010\u0002\u0003MDQ\u0001\u0011\u0001\u0005\u0002\u0005\u000bA\u0003\u001e:j[\u0006cG\u000e\u0016:bS2LgnZ*qC\u000e,GCA\u0010C\u0011\u0015qt\b1\u0001 \u0011\u0015!\u0005\u0001\"\u0001F\u0003%!WmY8na>\u001cX\rF\u00024\r\"CQaR\"A\u0002}\t1a\u001d;s\u0011\u0015I5\t1\u0001K\u0003\r\u0019X\r\u001d\t\u0003\u001b-K!\u0001\u0014\u0005\u0003\t\rC\u0017M\u001d\u0005\u0006\u001d\u0002!\taT\u0001\u0006o>\u0014Hm\u001d\u000b\u0003gACQaR'A\u0002}AQA\u0015\u0001\u0005\u0002M\u000b!b\u001d9mSR<\u0006.\u001a:f)\u0011!&lW2\u0011\u00075)v+\u0003\u0002W\u0011\t1q\n\u001d;j_:\u0004B!\u0004- ?%\u0011\u0011\f\u0003\u0002\u0007)V\u0004H.\u001a\u001a\t\u000b\u001d\u000b\u0006\u0019A\u0010\t\u000bq\u000b\u0006\u0019A/\u0002\u0003\u0019\u0004B!\u00040KA&\u0011q\f\u0003\u0002\n\rVt7\r^5p]F\u0002\"!D1\n\u0005\tD!a\u0002\"p_2,\u0017M\u001c\u0005\bIF\u0003\n\u00111\u0001a\u0003-!w\u000e\u0012:pa&sG-\u001a=\t\u000b\u0019\u0004A\u0011A4\u0002\u000fM\u0004H.\u001b;BiR!A\u000b[5o\u0011\u00159U\r1\u0001 \u0011\u0015QW\r1\u0001l\u0003\rIG\r\u001f\t\u0003\u001b1L!!\u001c\u0005\u0003\u0007%sG\u000fC\u0004eKB\u0005\t\u0019\u00011\t\u000bA\u0004A\u0011A9\u0002+\r|WO\u001c;FY\u0016lWM\u001c;t\u0003N\u001cFO]5oOR\u0019qD\u001d;\t\u000bM|\u0007\u0019A6\u0002\u00039DQ!^8A\u0002}\t\u0001\"\u001a7f[\u0016tGo\u001d\u0005\u0006o\u0002!\t\u0001_\u0001\u000eG>,h\u000e^!t'R\u0014\u0018N\\4\u0015\u0005}I\b\"B:w\u0001\u0004Y\u0007bB>\u0001#\u0003%\t\u0001`\u0001\u0015gBd\u0017\u000e^,iKJ,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003uT#\u0001\u0019@,\u0003}\u0004B!!\u0001\u0002\f5\u0011\u00111\u0001\u0006\u0005\u0003\u000b\t9!A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011\u0002\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u000e\u0005\r!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"A\u0011\u0011\u0003\u0001\u0012\u0002\u0013\u0005A0A\tta2LG/\u0011;%I\u00164\u0017-\u001e7uIM:q!!\u0006\u0003\u0011\u0003\t9\"A\u0005TiJLgnZ(qgB!\u0011\u0011DA\u000e\u001b\u0005\u0011aAB\u0001\u0003\u0011\u0003\tibE\u0003\u0002\u001c1\ty\u0002E\u0002\u0002\u001a\u0001A\u0001\"a\t\u0002\u001c\u0011\u0005\u0011QE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005]\u0001")
public interface StringOps {
    public Seq<String> oempty(Seq<String> var1);

    public String ojoin(Seq<String> var1);

    public String longestCommonPrefix(List<String> var1);

    public String trimTrailingSpace(String var1);

    public String trimAllTrailingSpace(String var1);

    public List<String> decompose(String var1, char var2);

    public List<String> words(String var1);

    public Option<Tuple2<String, String>> splitWhere(String var1, Function1<Object, Object> var2, boolean var3);

    public boolean splitWhere$default$3();

    public Option<Tuple2<String, String>> splitAt(String var1, int var2, boolean var3);

    public boolean splitAt$default$3();

    public String countElementsAsString(int var1, String var2);

    public String countAsString(int var1);
}

