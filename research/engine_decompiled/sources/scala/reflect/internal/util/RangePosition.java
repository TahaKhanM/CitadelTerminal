/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.OffsetPosition;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(bytes="\u0006\u0001I2A!\u0001\u0002\u0001\u0017\ti!+\u00198hKB{7/\u001b;j_:T!a\u0001\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000f!\tqA]3gY\u0016\u001cGOC\u0001\n\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0007\u0011\u00055qQ\"\u0001\u0002\n\u0005=\u0011!AD(gMN,G\u000fU8tSRLwN\u001c\u0005\t#\u0001\u0011\t\u0011)A\u0005%\u0005A1o\\;sG\u0016Le\u000e\u0005\u0002\u000e'%\u0011AC\u0001\u0002\u000b'>,(oY3GS2,\u0007\u0002\u0003\f\u0001\u0005\u0003\u0005\u000b\u0011B\f\u0002\u000fM$\u0018M\u001d;J]B\u0011\u0001$G\u0007\u0002\u0011%\u0011!\u0004\u0003\u0002\u0004\u0013:$\b\u0002\u0003\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\f\u0002\u000fA|\u0017N\u001c;J]\"Aa\u0004\u0001B\u0001B\u0003%q#A\u0003f]\u0012Le\u000eC\u0003!\u0001\u0011\u0005\u0011%\u0001\u0004=S:LGO\u0010\u000b\u0006E\r\"SE\n\t\u0003\u001b\u0001AQ!E\u0010A\u0002IAQAF\u0010A\u0002]AQ\u0001H\u0010A\u0002]AQAH\u0010A\u0002]AQ\u0001\u000b\u0001\u0005B%\nq![:SC:<W-F\u0001+!\tA2&\u0003\u0002-\u0011\t9!i\\8mK\u0006t\u0007\"\u0002\u0018\u0001\t\u0003z\u0013!B:uCJ$X#A\f\t\u000bE\u0002A\u0011I\u0018\u0002\u0007\u0015tG\r")
public class RangePosition
extends OffsetPosition {
    private final int startIn;
    private final int endIn;

    @Override
    public boolean isRange() {
        return true;
    }

    @Override
    public int start() {
        return this.startIn;
    }

    @Override
    public int end() {
        return this.endIn;
    }

    public RangePosition(SourceFile sourceIn, int startIn, int pointIn, int endIn) {
        this.startIn = startIn;
        this.endIn = endIn;
        super(sourceIn, pointIn);
    }
}

