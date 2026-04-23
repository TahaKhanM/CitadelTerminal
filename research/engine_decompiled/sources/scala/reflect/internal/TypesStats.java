/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.TypesStats$;
import scala.reflect.internal.util.Statistics;

@ScalaSignature(bytes="\u0006\u0001\u0005%q!B\u0001\u0003\u0011\u0003I\u0011A\u0003+za\u0016\u001c8\u000b^1ug*\u00111\u0001B\u0001\tS:$XM\u001d8bY*\u0011QAB\u0001\be\u00164G.Z2u\u0015\u00059\u0011!B:dC2\f7\u0001\u0001\t\u0003\u0015-i\u0011A\u0001\u0004\u0006\u0019\tA\t!\u0004\u0002\u000b)f\u0004Xm]*uCR\u001c8CA\u0006\u000f!\ty\u0001#D\u0001\u0007\u0013\t\tbA\u0001\u0004B]f\u0014VM\u001a\u0005\u0006'-!\t\u0001F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%AqAF\u0006C\u0002\u0013\u0005q#\u0001\u0007sC^$\u0016\u0010]3D_VtG/F\u0001\u0019!\tIrD\u0004\u0002\u001b;5\t1D\u0003\u0002\u001d\u0005\u0005!Q\u000f^5m\u0013\tq2$\u0001\u0006Ti\u0006$\u0018n\u001d;jGNL!\u0001I\u0011\u0003\u000f\r{WO\u001c;fe*\u0011ad\u0007\u0005\u0007G-\u0001\u000b\u0011\u0002\r\u0002\u001bI\fw\u000fV=qK\u000e{WO\u001c;!\u0011\u001d)3B1A\u0005\u0002]\tAb];cif\u0004XmQ8v]RDaaJ\u0006!\u0002\u0013A\u0012!D:vERL\b/Z\"pk:$\b\u0005C\u0004*\u0017\t\u0007I\u0011A\f\u0002\u001bM\fW.\u001a;za\u0016\u001cu.\u001e8u\u0011\u0019Y3\u0002)A\u00051\u0005q1/Y7fif\u0004XmQ8v]R\u0004\u0003bB\u0017\f\u0005\u0004%\taF\u0001\tYV\u00147i\\;oi\"1qf\u0003Q\u0001\na\t\u0011\u0002\\;c\u0007>,h\u000e\u001e\u0011\t\u000fEZ!\u0019!C\u0001/\u0005qa.Z:uK\u0012dUOY\"pk:$\bBB\u001a\fA\u0003%\u0001$A\boKN$X\r\u001a'vE\u000e{WO\u001c;!\u0011\u001d)4B1A\u0005\u0002]\tqBZ5oI6+WNY3s\u0007>,h\u000e\u001e\u0005\u0007o-\u0001\u000b\u0011\u0002\r\u0002!\u0019Lg\u000eZ'f[\n,'oQ8v]R\u0004\u0003bB\u001d\f\u0005\u0004%\taF\u0001\u0011M&tG-T3nE\u0016\u00148oQ8v]RDaaO\u0006!\u0002\u0013A\u0012!\u00054j]\u0012lU-\u001c2feN\u001cu.\u001e8uA!9Qh\u0003b\u0001\n\u0003q\u0014!\u00048p\u001b\u0016l'-\u001a:D_VtG/F\u0001@!\tI\u0002)\u0003\u0002BC\tQ1+\u001e2D_VtG/\u001a:\t\r\r[\u0001\u0015!\u0003@\u00039qw.T3nE\u0016\u00148i\\;oi\u0002Bq!R\u0006C\u0002\u0013\u0005a(A\bnk2$X*Z7cKJ\u001cu.\u001e8u\u0011\u001995\u0002)A\u0005\u007f\u0005\u0001R.\u001e7u\u001b\u0016l'-\u001a:D_VtG\u000f\t\u0005\b\u0013.\u0011\r\u0011\"\u0001K\u0003)!\u0018\u0010]3s\u001d\u0006twn]\u000b\u0002\u0017B\u0011\u0011\u0004T\u0005\u0003\u001b\u0006\u0012Q\u0001V5nKJDaaT\u0006!\u0002\u0013Y\u0015a\u0003;za\u0016\u0014h*\u00198pg\u0002Bq!U\u0006C\u0002\u0013\u0005!+\u0001\u0005mk\nt\u0015M\\8t+\u0005\u0019\u0006CA\rU\u0013\t)\u0016E\u0001\bTi\u0006\u001c7.\u00192mKRKW.\u001a:\t\r][\u0001\u0015!\u0003T\u0003%aWO\u0019(b]>\u001c\b\u0005C\u0004Z\u0017\t\u0007I\u0011\u0001*\u0002\u0019M,(\r^=qK:\u000bgn\\:\t\rm[\u0001\u0015!\u0003T\u00035\u0019XO\u0019;za\u0016t\u0015M\\8tA!9Ql\u0003b\u0001\n\u0003\u0011\u0016a\u00044j]\u0012lU-\u001c2fe:\u000bgn\\:\t\r}[\u0001\u0015!\u0003T\u0003A1\u0017N\u001c3NK6\u0014WM\u001d(b]>\u001c\b\u0005C\u0004b\u0017\t\u0007I\u0011\u0001*\u0002!\u0019Lg\u000eZ'f[\n,'o\u001d(b]>\u001c\bBB2\fA\u0003%1+A\tgS:$W*Z7cKJ\u001ch*\u00198pg\u0002Bq!Z\u0006C\u0002\u0013\u0005!+A\bbgN+WM\u001c$s_6t\u0015M\\8t\u0011\u001997\u0002)A\u0005'\u0006\u0001\u0012m]*fK:4%o\\7OC:|7\u000f\t\u0005\bS.\u0011\r\u0011\"\u0001S\u0003A\u0011\u0017m]3UsB,7+Z9OC:|7\u000f\u0003\u0004l\u0017\u0001\u0006IaU\u0001\u0012E\u0006\u001cX\rV=qKN+\u0017OT1o_N\u0004\u0003bB7\f\u0005\u0004%\tAU\u0001\u0011E\u0006\u001cXm\u00117bgN,7OT1o_NDaa\\\u0006!\u0002\u0013\u0019\u0016!\u00052bg\u0016\u001cE.Y:tKNt\u0015M\\8tA!9\u0011o\u0003b\u0001\n\u0003q\u0014\u0001G2p[B|WO\u001c3CCN,G+\u001f9f'\u0016\f8i\\;oi\"11o\u0003Q\u0001\n}\n\u0011dY8na>,h\u000e\u001a\"bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8uA!9Qo\u0003b\u0001\n\u0003q\u0014a\u0006;za\u0016\u0014XM\u001a\"bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8u\u0011\u001998\u0002)A\u0005\u007f\u0005AB/\u001f9fe\u00164')Y:f)f\u0004XmU3r\u0007>,h\u000e\u001e\u0011\t\u000fe\\!\u0019!C\u0001}\u0005I2/\u001b8hY\u0016$xN\u001c\"bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8u\u0011\u0019Y8\u0002)A\u0005\u007f\u0005Q2/\u001b8hY\u0016$xN\u001c\"bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8uA!9Qp\u0003b\u0001\n\u0003q\u0018\u0001\u0004;za\u0016|\u0005o]*uC\u000e\\W#A@\u0011\u0007e\t\t!C\u0002\u0002\u0004\u0005\u0012!\u0002V5nKJ\u001cF/Y2l\u0011\u001d\t9a\u0003Q\u0001\n}\fQ\u0002^=qK>\u00038o\u0015;bG.\u0004\u0003")
public final class TypesStats {
    public static Statistics.TimerStack typeOpsStack() {
        return TypesStats$.MODULE$.typeOpsStack();
    }

    public static Statistics.SubCounter singletonBaseTypeSeqCount() {
        return TypesStats$.MODULE$.singletonBaseTypeSeqCount();
    }

    public static Statistics.SubCounter typerefBaseTypeSeqCount() {
        return TypesStats$.MODULE$.typerefBaseTypeSeqCount();
    }

    public static Statistics.SubCounter compoundBaseTypeSeqCount() {
        return TypesStats$.MODULE$.compoundBaseTypeSeqCount();
    }

    public static Statistics.StackableTimer baseClassesNanos() {
        return TypesStats$.MODULE$.baseClassesNanos();
    }

    public static Statistics.StackableTimer baseTypeSeqNanos() {
        return TypesStats$.MODULE$.baseTypeSeqNanos();
    }

    public static Statistics.StackableTimer asSeenFromNanos() {
        return TypesStats$.MODULE$.asSeenFromNanos();
    }

    public static Statistics.StackableTimer findMembersNanos() {
        return TypesStats$.MODULE$.findMembersNanos();
    }

    public static Statistics.StackableTimer findMemberNanos() {
        return TypesStats$.MODULE$.findMemberNanos();
    }

    public static Statistics.StackableTimer subtypeNanos() {
        return TypesStats$.MODULE$.subtypeNanos();
    }

    public static Statistics.StackableTimer lubNanos() {
        return TypesStats$.MODULE$.lubNanos();
    }

    public static Statistics.Timer typerNanos() {
        return TypesStats$.MODULE$.typerNanos();
    }

    public static Statistics.SubCounter multMemberCount() {
        return TypesStats$.MODULE$.multMemberCount();
    }

    public static Statistics.SubCounter noMemberCount() {
        return TypesStats$.MODULE$.noMemberCount();
    }

    public static Statistics.Counter findMembersCount() {
        return TypesStats$.MODULE$.findMembersCount();
    }

    public static Statistics.Counter findMemberCount() {
        return TypesStats$.MODULE$.findMemberCount();
    }

    public static Statistics.Counter nestedLubCount() {
        return TypesStats$.MODULE$.nestedLubCount();
    }

    public static Statistics.Counter lubCount() {
        return TypesStats$.MODULE$.lubCount();
    }

    public static Statistics.Counter sametypeCount() {
        return TypesStats$.MODULE$.sametypeCount();
    }

    public static Statistics.Counter subtypeCount() {
        return TypesStats$.MODULE$.subtypeCount();
    }

    public static Statistics.Counter rawTypeCount() {
        return TypesStats$.MODULE$.rawTypeCount();
    }
}

