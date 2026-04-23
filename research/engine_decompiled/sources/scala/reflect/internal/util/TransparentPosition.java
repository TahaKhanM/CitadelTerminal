/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.RangePosition;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(bytes="\u0006\u000152A!\u0001\u0002\u0001\u0017\t\u0019BK]1ogB\f'/\u001a8u!>\u001c\u0018\u000e^5p]*\u00111\u0001B\u0001\u0005kRLGN\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001b9i\u0011AA\u0005\u0003\u001f\t\u0011QBU1oO\u0016\u0004vn]5uS>t\u0007\u0002C\t\u0001\u0005\u0003\u0005\u000b\u0011\u0002\n\u0002\u0011M|WO]2f\u0013:\u0004\"!D\n\n\u0005Q\u0011!AC*pkJ\u001cWMR5mK\"Aa\u0003\u0001B\u0001B\u0003%q#A\u0004ti\u0006\u0014H/\u00138\u0011\u0005aIR\"\u0001\u0005\n\u0005iA!aA%oi\"AA\u0004\u0001B\u0001B\u0003%q#A\u0004q_&tG/\u00138\t\u0011y\u0001!\u0011!Q\u0001\n]\tQ!\u001a8e\u0013:DQ\u0001\t\u0001\u0005\u0002\u0005\na\u0001P5oSRtD#\u0002\u0012$I\u00152\u0003CA\u0007\u0001\u0011\u0015\tr\u00041\u0001\u0013\u0011\u00151r\u00041\u0001\u0018\u0011\u0015ar\u00041\u0001\u0018\u0011\u0015qr\u00041\u0001\u0018\u0011\u0015A\u0003\u0001\"\u0011*\u00035I7\u000f\u0016:b]N\u0004\u0018M]3oiV\t!\u0006\u0005\u0002\u0019W%\u0011A\u0006\u0003\u0002\b\u0005>|G.Z1o\u0001")
public class TransparentPosition
extends RangePosition {
    @Override
    public boolean isTransparent() {
        return true;
    }

    public TransparentPosition(SourceFile sourceIn, int startIn, int pointIn, int endIn) {
        super(sourceIn, startIn, pointIn, endIn);
    }
}

