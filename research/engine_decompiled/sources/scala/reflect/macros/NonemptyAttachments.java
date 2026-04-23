/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.macros;

import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.macros.Attachments;

@ScalaSignature(bytes="\u0006\u0001\u00053A!\u0001\u0002\u0007\u0013\t\u0019bj\u001c8f[B$\u00180\u0011;uC\u000eDW.\u001a8ug*\u00111\u0001B\u0001\u0007[\u0006\u001c'o\\:\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u0001QC\u0001\u0006\u0015'\t\u00011\u0002\u0005\u0002\r\u001b5\t!!\u0003\u0002\u000f\u0005\tY\u0011\t\u001e;bG\"lWM\u001c;t\u0011!\u0001\u0002A!b\u0001\n\u0003\n\u0012a\u00019pgV\t!\u0003\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00041\"!\u0001)\u0012\u0005]Y\u0002C\u0001\r\u001a\u001b\u00051\u0011B\u0001\u000e\u0007\u0005\u0011qU\u000f\u001c7\u0011\u0005aa\u0012BA\u000f\u0007\u0005\r\te.\u001f\u0005\t?\u0001\u0011\t\u0011)A\u0005%\u0005!\u0001o\\:!\u0011!\t\u0003A!b\u0001\n\u0003\u0012\u0013aA1mYV\t1\u0005E\u0002%Omq!\u0001G\u0013\n\u0005\u00192\u0011A\u0002)sK\u0012,g-\u0003\u0002)S\t\u00191+\u001a;\u000b\u0005\u00192\u0001\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\u0002\t\u0005dG\u000e\t\u0005\u0006[\u0001!\tAL\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007=\u0002\u0014\u0007E\u0002\r\u0001IAQ\u0001\u0005\u0017A\u0002IAQ!\t\u0017A\u0002\r*Aa\r\u0001\u0001%\t\u0019\u0001k\\:\t\u000bU\u0002A\u0011\u0001\u001c\u0002\u000f]LG\u000f\u001b)pgR\u0011qf\u000e\u0005\u0006qQ\u0002\r!O\u0001\u0007]\u0016<\bk\\:\u0011\u0005i\u0012T\"\u0001\u0001\t\u000bq\u0002A\u0011I\u001f\u0002\u000f%\u001cX)\u001c9usV\ta\b\u0005\u0002\u0019\u007f%\u0011\u0001I\u0002\u0002\b\u0005>|G.Z1o\u0001")
public final class NonemptyAttachments<P>
extends Attachments {
    private final P pos;
    private final Set<Object> all;

    public P pos() {
        return this.pos;
    }

    @Override
    public Set<Object> all() {
        return this.all;
    }

    public NonemptyAttachments<P> withPos(P newPos) {
        return new NonemptyAttachments<P>(newPos, this.all());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public NonemptyAttachments(P pos, Set<Object> all) {
        this.pos = pos;
        this.all = all;
    }
}

