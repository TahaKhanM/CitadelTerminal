/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.DefinedPosition;
import scala.reflect.internal.util.SourceFile;

@ScalaSignature(bytes="\u0006\u0001E2A!\u0001\u0002\u0001\u0017\tqqJ\u001a4tKR\u0004vn]5uS>t'BA\u0002\u0005\u0003\u0011)H/\u001b7\u000b\u0005\u00151\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0013\u0005)1oY1mC\u000e\u00011C\u0001\u0001\r!\tia\"D\u0001\u0003\u0013\ty!AA\bEK\u001aLg.\u001a3Q_NLG/[8o\u0011!\t\u0002A!A!\u0002\u0013\u0011\u0012\u0001C:pkJ\u001cW-\u00138\u0011\u00055\u0019\u0012B\u0001\u000b\u0003\u0005)\u0019v.\u001e:dK\u001aKG.\u001a\u0005\t-\u0001\u0011\t\u0011)A\u0005/\u00059\u0001o\\5oi&s\u0007C\u0001\r\u001a\u001b\u0005A\u0011B\u0001\u000e\t\u0005\rIe\u000e\u001e\u0005\u00069\u0001!\t!H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007yy\u0002\u0005\u0005\u0002\u000e\u0001!)\u0011c\u0007a\u0001%!)ac\u0007a\u0001/!)!\u0005\u0001C!G\u00059\u0011n\u001d*b]\u001e,W#\u0001\u0013\u0011\u0005a)\u0013B\u0001\u0014\t\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u000b\u0001\u0005B%\naa]8ve\u000e,W#\u0001\n\t\u000b-\u0002A\u0011\t\u0017\u0002\u000bA|\u0017N\u001c;\u0016\u0003]AQA\f\u0001\u0005B1\nQa\u001d;beRDQ\u0001\r\u0001\u0005B1\n1!\u001a8e\u0001")
public class OffsetPosition
extends DefinedPosition {
    private final SourceFile sourceIn;
    private final int pointIn;

    @Override
    public boolean isRange() {
        return false;
    }

    @Override
    public SourceFile source() {
        return this.sourceIn;
    }

    @Override
    public int point() {
        return this.pointIn;
    }

    @Override
    public int start() {
        return this.point();
    }

    @Override
    public int end() {
        return this.point();
    }

    public OffsetPosition(SourceFile sourceIn, int pointIn) {
        this.sourceIn = sourceIn;
        this.pointIn = pointIn;
    }
}

