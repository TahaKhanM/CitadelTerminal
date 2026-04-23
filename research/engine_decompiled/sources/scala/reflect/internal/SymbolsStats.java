/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolsStats$;
import scala.reflect.internal.util.Statistics;

@ScalaSignature(bytes="\u0006\u0001Q:Q!\u0001\u0002\t\u0002%\tAbU=nE>d7o\u0015;biNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u0019MKXNY8mgN#\u0018\r^:\u0014\u0005-q\u0001CA\b\u0011\u001b\u00051\u0011BA\t\u0007\u0005\u0019\te.\u001f*fM\")1c\u0003C\u0001)\u00051A(\u001b8jiz\"\u0012!\u0003\u0005\b--\u0011\r\u0011\"\u0001\u0018\u0003=!\u0018\u0010]3Ts6\u0014w\u000e\\\"pk:$X#\u0001\r\u0011\u0005eybB\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\u0003\u0003\u0011)H/\u001b7\n\u0005yY\u0012AC*uCRL7\u000f^5dg&\u0011\u0001%\t\u0002\b\u0007>,h\u000e^3s\u0015\tq2\u0004\u0003\u0004$\u0017\u0001\u0006I\u0001G\u0001\u0011if\u0004XmU=nE>d7i\\;oi\u0002Bq!J\u0006C\u0002\u0013\u0005q#\u0001\tdY\u0006\u001c8oU=nE>d7i\\;oi\"1qe\u0003Q\u0001\na\t\u0011c\u00197bgN\u001c\u00160\u001c2pY\u000e{WO\u001c;!\u0011\u001dI3B1A\u0005\u0002]\t!B\u001a7bON\u001cu.\u001e8u\u0011\u0019Y3\u0002)A\u00051\u0005Ya\r\\1hg\u000e{WO\u001c;!\u0011\u001di3B1A\u0005\u0002]\t!b\\<oKJ\u001cu.\u001e8u\u0011\u0019y3\u0002)A\u00051\u0005Yqn\u001e8fe\u000e{WO\u001c;!\u0011\u001d\t4B1A\u0005\u0002]\t\u0011B\\1nK\u000e{WO\u001c;\t\rMZ\u0001\u0015!\u0003\u0019\u0003)q\u0017-\\3D_VtG\u000f\t")
public final class SymbolsStats {
    public static Statistics.Counter nameCount() {
        return SymbolsStats$.MODULE$.nameCount();
    }

    public static Statistics.Counter ownerCount() {
        return SymbolsStats$.MODULE$.ownerCount();
    }

    public static Statistics.Counter flagsCount() {
        return SymbolsStats$.MODULE$.flagsCount();
    }

    public static Statistics.Counter classSymbolCount() {
        return SymbolsStats$.MODULE$.classSymbolCount();
    }

    public static Statistics.Counter typeSymbolCount() {
        return SymbolsStats$.MODULE$.typeSymbolCount();
    }
}

