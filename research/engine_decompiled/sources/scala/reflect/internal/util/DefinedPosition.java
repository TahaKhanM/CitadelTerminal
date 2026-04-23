/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Predef$;
import scala.StringContext;
import scala.collection.Seq$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\r3Q!\u0001\u0002\u0002\"-\u0011q\u0002R3gS:,G\rU8tSRLwN\u001c\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003\tI!a\u0004\u0002\u0003\u0011A{7/\u001b;j_:DQ!\u0005\u0001\u0005\u0002I\ta\u0001P5oSRtD#A\n\u0011\u00055\u0001\u0001\"B\u000b\u0001\t\u000b2\u0012!C5t\t\u00164\u0017N\\3e+\u00059\u0002C\u0001\r\u001a\u001b\u0005A\u0011B\u0001\u000e\t\u0005\u001d\u0011un\u001c7fC:DQ\u0001\b\u0001\u0005Bu\ta!Z9vC2\u001cHCA\f\u001f\u0011\u0015y2\u00041\u0001!\u0003\u0011!\b.\u0019;\u0011\u0005a\t\u0013B\u0001\u0012\t\u0005\r\te.\u001f\u0005\u0006I\u0001!\t%J\u0001\tQ\u0006\u001c\bnQ8eKR\ta\u0005\u0005\u0002\u0019O%\u0011\u0001\u0006\u0003\u0002\u0004\u0013:$\b\"\u0002\u0016\u0001\t\u0003Z\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00031\u0002\"!\f\u001a\u000e\u00039R!a\f\u0019\u0002\t1\fgn\u001a\u0006\u0002c\u0005!!.\u0019<b\u0013\t\u0019dF\u0001\u0004TiJLgn\u001a\u0005\u0006k\u0001!IAN\u0001\ra>Lg\u000e^'fgN\fw-Z\u000b\u0002Y!)\u0001\b\u0001C\u0005s\u0005i1-\u00198p]&\u001c\u0017\r\u001c)bi\",\u0012A\u000f\t\u0003wyr!\u0001\u0007\u001f\n\u0005uB\u0011A\u0002)sK\u0012,g-\u0003\u00024\u007f)\u0011Q\bC\u0015\u0003\u0001\u0005K!A\u0011\u0002\u0003\u001d=3gm]3u!>\u001c\u0018\u000e^5p]\u0002")
public abstract class DefinedPosition
extends Position {
    @Override
    public final boolean isDefined() {
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object that) {
        if (!(that instanceof DefinedPosition)) return false;
        DefinedPosition definedPosition = (DefinedPosition)that;
        AbstractFile abstractFile = this.source().file();
        AbstractFile abstractFile2 = definedPosition.source().file();
        if (abstractFile == null) {
            if (abstractFile2 != null) {
                return false;
            }
        } else if (!abstractFile.equals(abstractFile2)) return false;
        if (this.start() != definedPosition.start()) return false;
        if (this.point() != definedPosition.point()) return false;
        if (this.end() != definedPosition.end()) return false;
        return true;
    }

    public int hashCode() {
        return ScalaRunTime$.MODULE$.hash(Seq$.MODULE$.apply(Predef$.MODULE$.genericWrapArray(new Object[]{this.source().file(), BoxesRunTime.boxToInteger(this.start()), BoxesRunTime.boxToInteger(this.point()), BoxesRunTime.boxToInteger(this.end())})));
    }

    public String toString() {
        return this.isRange() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"RangePosition(", ", ", ", ", ", ", ")"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.canonicalPath(), BoxesRunTime.boxToInteger(this.start()), BoxesRunTime.boxToInteger(this.point()), BoxesRunTime.boxToInteger(this.end())})) : new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"source-", ",line-", ",", "", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{this.canonicalPath(), BoxesRunTime.boxToInteger(this.line()), this.pointMessage(), BoxesRunTime.boxToInteger(this.point())}));
    }

    private String pointMessage() {
        return this.point() > this.source().length() ? "out-of-bounds-" : "offset=";
    }

    private String canonicalPath() {
        return this.source().file().canonicalPath();
    }
}

