/*
 * Decompiled with CFR 0.152.
 */
package scala.math;

import java.util.Comparator;
import scala.Function1;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001\u00053q!\u0001\u0002\u0011\u0002\u0007\u0005qA\u0001\u000fM_^\u0004&/[8sSRLxJ\u001d3fe&tw-S7qY&\u001c\u0017\u000e^:\u000b\u0005\r!\u0011\u0001B7bi\"T\u0011!B\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001\u0001\u0002\u0005\u0002\n\u00155\tA!\u0003\u0002\f\t\t1\u0011I\\=SK\u001aDQ!\u0004\u0001\u0005\u00029\ta\u0001J5oSR$C#A\b\u0011\u0005%\u0001\u0012BA\t\u0005\u0005\u0011)f.\u001b;\t\u000bM\u0001A1\u0001\u000b\u0002\u000f=\u0014H-\u001a:fIV\u0011Q\u0003\b\u000b\u0003-\u0015\u00022a\u0006\r\u001b\u001b\u0005\u0011\u0011BA\r\u0003\u0005!y%\u000fZ3sS:<\u0007CA\u000e\u001d\u0019\u0001!Q!\b\nC\u0002y\u0011\u0011!Q\t\u0003?\t\u0002\"!\u0003\u0011\n\u0005\u0005\"!a\u0002(pi\"Lgn\u001a\t\u0003\u0013\rJ!\u0001\n\u0003\u0003\u0007\u0005s\u0017\u0010C\u0004'%\u0005\u0005\t9A\u0014\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003\nQiQ\u0013BA\u0015\u0005\u0005%1UO\\2uS>t\u0017\u0007E\u0002,aii\u0011\u0001\f\u0006\u0003[9\nA\u0001\\1oO*\tq&\u0001\u0003kCZ\f\u0017BA\u0019-\u0005)\u0019u.\u001c9be\u0006\u0014G.\u001a\u0005\u0006g\u0001!\u0019\u0001N\u0001\u0015G>l\u0007/\u0019:bi>\u0014Hk\\(sI\u0016\u0014\u0018N\\4\u0016\u0005UBDC\u0001\u001c:!\r9\u0002d\u000e\t\u00037a\"Q!\b\u001aC\u0002yAQA\u000f\u001aA\u0004m\n1aY7q!\rathN\u0007\u0002{)\u0011aHL\u0001\u0005kRLG.\u0003\u0002A{\tQ1i\\7qCJ\fGo\u001c:")
public interface LowPriorityOrderingImplicits {
    public <A> Ordering<A> ordered(Function1<A, Comparable<A>> var1);

    public <A> Ordering<A> comparatorToOrdering(Comparator<A> var1);
}

