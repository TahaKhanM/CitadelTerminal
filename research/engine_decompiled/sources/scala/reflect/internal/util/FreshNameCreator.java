/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import scala.Predef$;
import scala.StringContext;
import scala.reflect.NameTransformer$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.FreshNameCreator$;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u000113A!\u0001\u0002\u0001\u0017\t\u0001bI]3tQ:\u000bW.Z\"sK\u0006$xN\u001d\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003!I!a\u0004\u0005\u0003\r\u0005s\u0017PU3g\u0011!\t\u0002A!A!\u0002\u0013\u0011\u0012!D2sK\u0006$xN\u001d)sK\u001aL\u0007\u0010\u0005\u0002\u0014-9\u0011Q\u0002F\u0005\u0003+!\ta\u0001\u0015:fI\u00164\u0017BA\f\u0019\u0005\u0019\u0019FO]5oO*\u0011Q\u0003\u0003\u0005\u00065\u0001!\taG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005qq\u0002CA\u000f\u0001\u001b\u0005\u0011\u0001bB\t\u001a!\u0003\u0005\rA\u0005\u0005\bA\u0001\u0011\r\u0011\"\u0005\"\u0003!\u0019w.\u001e8uKJ\u001cX#\u0001\u0012\u0011\t\rJ#cK\u0007\u0002I)\u0011QEJ\u0001\u000bG>t7-\u001e:sK:$(BA\u0002(\u0015\u0005A\u0013\u0001\u00026bm\u0006L!A\u000b\u0013\u0003#\r{gnY;se\u0016tG\u000fS1tQ6\u000b\u0007\u000f\u0005\u0002-_5\tQF\u0003\u0002/I\u00051\u0011\r^8nS\u000eL!\u0001M\u0017\u0003\u0015\u0005#x.\\5d\u0019>tw\r\u0003\u00043\u0001\u0001\u0006IAI\u0001\nG>,h\u000e^3sg\u0002BQ\u0001\u000e\u0001\u0005\u0002U\nqA\\3x\u001d\u0006lW\r\u0006\u0002\u0013m!)qg\ra\u0001%\u00051\u0001O]3gSb<q!\u000f\u0002\u0002\u0002#\u0005!(\u0001\tGe\u0016\u001c\bNT1nK\u000e\u0013X-\u0019;peB\u0011Qd\u000f\u0004\b\u0003\t\t\t\u0011#\u0001='\tYD\u0002C\u0003\u001bw\u0011\u0005a\bF\u0001;\u0011\u001d\u00015(%A\u0005\u0002\u0005\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#\u0001\"+\u0005I\u00195&\u0001#\u0011\u0005\u0015SU\"\u0001$\u000b\u0005\u001dC\u0015!C;oG\",7m[3e\u0015\tI\u0005\"\u0001\u0006b]:|G/\u0019;j_:L!a\u0013$\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r")
public class FreshNameCreator {
    private final String creatorPrefix;
    private final ConcurrentHashMap<String, AtomicLong> counters;

    public static String $lessinit$greater$default$1() {
        return FreshNameCreator$.MODULE$.$lessinit$greater$default$1();
    }

    public ConcurrentHashMap<String, AtomicLong> counters() {
        return this.counters;
    }

    public String newName(String prefix) {
        String safePrefix = NameTransformer$.MODULE$.encode(prefix);
        this.counters().putIfAbsent(safePrefix, new AtomicLong(0L));
        long idx = this.counters().get(safePrefix).incrementAndGet();
        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", "", "", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.creatorPrefix, safePrefix, BoxesRunTime.boxToLong(idx)}));
    }

    public FreshNameCreator(String creatorPrefix) {
        this.creatorPrefix = creatorPrefix;
        this.counters = new ConcurrentHashMap();
    }
}

