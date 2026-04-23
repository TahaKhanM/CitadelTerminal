/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Option;
import scala.Predef$;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.DeprecatedPosition;
import scala.reflect.internal.util.DeprecatedPosition$class;
import scala.reflect.internal.util.InternalPositionImpl;
import scala.reflect.internal.util.InternalPositionImpl$class;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.Position$;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.macros.Attachments;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u0005ua\u0001B\u0001\u0003\u0001-\u0011\u0001\u0002U8tSRLwN\u001c\u0006\u0003\u0007\u0011\tA!\u001e;jY*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7\u0001A\n\u0006\u00011\u0011rc\u0007\t\u0003\u001bAi\u0011A\u0004\u0006\u0003\u001f\u0019\ta!\\1de>\u001c\u0018BA\t\u000f\u0005-\tE\u000f^1dQ6,g\u000e^:\u0011\u0005M1R\"\u0001\u000b\u000b\u0005U1\u0011aA1qS&\u0011\u0011\u0001\u0006\t\u00031ei\u0011AA\u0005\u00035\t\u0011A#\u00138uKJt\u0017\r\u001c)pg&$\u0018n\u001c8J[Bd\u0007C\u0001\r\u001d\u0013\ti\"A\u0001\nEKB\u0014XmY1uK\u0012\u0004vn]5uS>t\u0007\"B\u0010\u0001\t\u0003\u0001\u0013A\u0002\u001fj]&$h\bF\u0001\"!\tA\u0002!\u0002\u0003$\u0001\u0001\t#a\u0001)pg\")Q\u0005\u0001C\u0001M\u0005\u0019\u0001o\\:\u0016\u0003\u0005BQ\u0001\u000b\u0001\u0005\u0002%\nqa^5uQB{7\u000f\u0006\u0002+aI\u00111\u0006\u0004\u0004\u0005Y\u0001\u0001!F\u0001\u0007=e\u00164\u0017N\\3nK:$h(\u0002\u0003$W\u0001r\u0003CA\u0018#\u001b\u0005\u0001\u0001\"B\u0019(\u0001\u0004\t\u0013A\u00028foB{7\u000fC\u00034\u0001\u0011EA'\u0001\u0003gC&dGCA\u001b:!\t1t'D\u0001\t\u0013\tA\u0004BA\u0004O_RD\u0017N\\4\t\u000bi\u0012\u0004\u0019A\u001e\u0002\t]D\u0017\r\u001e\t\u0003y}r!AN\u001f\n\u0005yB\u0011A\u0002)sK\u0012,g-\u0003\u0002A\u0003\n11\u000b\u001e:j]\u001eT!A\u0010\u0005\t\u000b\r\u0003A\u0011\u0001#\u0002\u0013%\u001cH)\u001a4j]\u0016$W#A#\u0011\u0005Y2\u0015BA$\t\u0005\u001d\u0011un\u001c7fC:DQ!\u0013\u0001\u0005\u0002\u0011\u000bq![:SC:<W\rC\u0003L\u0001\u0011\u0005A*\u0001\u0004t_V\u00148-Z\u000b\u0002\u001bB\u0011\u0001DT\u0005\u0003\u001f\n\u0011!bU8ve\u000e,g)\u001b7f\u0011\u0015\t\u0006\u0001\"\u0001S\u0003\u0015\u0019H/\u0019:u+\u0005\u0019\u0006C\u0001\u001cU\u0013\t)\u0006BA\u0002J]RDQa\u0016\u0001\u0005\u0002I\u000bQ\u0001]8j]RDQ!\u0017\u0001\u0005\u0002I\u000b1!\u001a8e\u000f\u0015Y&\u0001#\u0001]\u0003!\u0001vn]5uS>t\u0007C\u0001\r^\r\u0015\t!\u0001#\u0001_'\tiv\f\u0005\u00027A&\u0011\u0011\r\u0003\u0002\u0007\u0003:L(+\u001a4\t\u000b}iF\u0011A2\u0015\u0003qCq!Z/C\u0002\u0013\u0005!+\u0001\u0004uC\nLen\u0019\u0005\u0007Ov\u0003\u000b\u0011B*\u0002\u000fQ\f'-\u00138dA!)\u0011.\u0018C\u0005U\u0006Aa/\u00197jI\u0006$X-\u0006\u0002l]R\u0011A.\u001d\t\u0003[:d\u0001\u0001B\u0003pQ\n\u0007\u0001OA\u0001U#\t)\u0014\u0005C\u0003&Q\u0002\u0007A\u000eC\u0003t;\u0012\u0005A/A\u0007g_Jl\u0017\r^'fgN\fw-\u001a\u000b\u0005wU<\u0018\u0010C\u0003we\u0002\u0007\u0011%A\u0003q_NLe\u000eC\u0003ye\u0002\u00071(A\u0002ng\u001eDQA\u001f:A\u0002\u0015\u000b1b\u001d5peR,gNR5mK\")A0\u0018C\u0001{\u00061qN\u001a4tKR$2!\t@\u0000\u0011\u0015Y5\u00101\u0001N\u0011\u001596\u00101\u0001T\u0011\u001d\t\u0019!\u0018C\u0001\u0003\u000b\tQA]1oO\u0016$\u0012\"IA\u0004\u0003\u0013\tY!!\u0004\t\r-\u000b\t\u00011\u0001N\u0011\u0019\t\u0016\u0011\u0001a\u0001'\"1q+!\u0001A\u0002MCa!WA\u0001\u0001\u0004\u0019\u0006bBA\t;\u0012\u0005\u00111C\u0001\fiJ\fgn\u001d9be\u0016tG\u000fF\u0005\"\u0003+\t9\"!\u0007\u0002\u001c!11*a\u0004A\u00025Ca!UA\b\u0001\u0004\u0019\u0006BB,\u0002\u0010\u0001\u00071\u000b\u0003\u0004Z\u0003\u001f\u0001\ra\u0015")
public class Position
extends Attachments
implements scala.reflect.api.Position,
InternalPositionImpl,
DeprecatedPosition {
    public static Position transparent(SourceFile sourceFile, int n, int n2, int n3) {
        return Position$.MODULE$.transparent(sourceFile, n, n2, n3);
    }

    public static Position range(SourceFile sourceFile, int n, int n2, int n3) {
        return Position$.MODULE$.range(sourceFile, n, n2, n3);
    }

    public static String formatMessage(Position position, String string2, boolean bl) {
        return Position$.MODULE$.formatMessage(position, string2, bl);
    }

    public static int tabInc() {
        return Position$.MODULE$.tabInc();
    }

    @Override
    public Option<Object> offset() {
        return DeprecatedPosition$class.offset(this);
    }

    @Override
    public Position toSingleLine() {
        return DeprecatedPosition$class.toSingleLine(this);
    }

    @Override
    public int safeLine() {
        return DeprecatedPosition$class.safeLine(this);
    }

    @Override
    public String dbgString() {
        return DeprecatedPosition$class.dbgString(this);
    }

    @Override
    public Position inUltimateSource(SourceFile source) {
        return DeprecatedPosition$class.inUltimateSource(this, source);
    }

    @Override
    public Tuple2<String, String> lineWithCarat(int maxWidth2) {
        return DeprecatedPosition$class.lineWithCarat(this, maxWidth2);
    }

    @Override
    public Position withSource(SourceFile source, int shift) {
        return DeprecatedPosition$class.withSource(this, source, shift);
    }

    @Override
    public int startOrPoint() {
        return DeprecatedPosition$class.startOrPoint(this);
    }

    @Override
    public int endOrPoint() {
        return DeprecatedPosition$class.endOrPoint(this);
    }

    @Override
    public Position finalPosition() {
        return InternalPositionImpl$class.finalPosition(this);
    }

    @Override
    public boolean isTransparent() {
        return InternalPositionImpl$class.isTransparent(this);
    }

    @Override
    public boolean isOffset() {
        return InternalPositionImpl$class.isOffset(this);
    }

    @Override
    public boolean isOpaqueRange() {
        return InternalPositionImpl$class.isOpaqueRange(this);
    }

    @Override
    public int pointOrElse(int alt) {
        return InternalPositionImpl$class.pointOrElse(this, alt);
    }

    @Override
    public Position makeTransparent() {
        return InternalPositionImpl$class.makeTransparent(this);
    }

    @Override
    public Position withStart(int start) {
        return InternalPositionImpl$class.withStart(this, start);
    }

    @Override
    public Position withPoint(int point) {
        return InternalPositionImpl$class.withPoint(this, point);
    }

    @Override
    public Position withEnd(int end) {
        return InternalPositionImpl$class.withEnd(this, end);
    }

    @Override
    public Position withSource(SourceFile source) {
        return InternalPositionImpl$class.withSource(this, source);
    }

    @Override
    public Position withShift(int shift) {
        return InternalPositionImpl$class.withShift(this, shift);
    }

    @Override
    public Position focusStart() {
        return InternalPositionImpl$class.focusStart(this);
    }

    @Override
    public Position focus() {
        return InternalPositionImpl$class.focus(this);
    }

    @Override
    public Position focusEnd() {
        return InternalPositionImpl$class.focusEnd(this);
    }

    @Override
    public Position $bar(Position that, Seq<Position> poses) {
        return InternalPositionImpl$class.$bar(this, that, poses);
    }

    @Override
    public Position $bar(Position that) {
        return InternalPositionImpl$class.$bar(this, that);
    }

    @Override
    public Position $up(int point) {
        return InternalPositionImpl$class.$up(this, point);
    }

    @Override
    public Position $bar$up(Position that) {
        return InternalPositionImpl$class.$bar$up(this, that);
    }

    @Override
    public Position $up$bar(Position that) {
        return InternalPositionImpl$class.$up$bar(this, that);
    }

    @Override
    public Position union(Position pos) {
        return InternalPositionImpl$class.union(this, pos);
    }

    @Override
    public boolean includes(Position pos) {
        return InternalPositionImpl$class.includes(this, pos);
    }

    @Override
    public boolean properlyIncludes(Position pos) {
        return InternalPositionImpl$class.properlyIncludes(this, pos);
    }

    @Override
    public boolean precedes(Position pos) {
        return InternalPositionImpl$class.precedes(this, pos);
    }

    @Override
    public boolean properlyPrecedes(Position pos) {
        return InternalPositionImpl$class.properlyPrecedes(this, pos);
    }

    @Override
    public boolean sameRange(Position pos) {
        return InternalPositionImpl$class.sameRange(this, pos);
    }

    @Override
    public boolean overlaps(Position pos) {
        return InternalPositionImpl$class.overlaps(this, pos);
    }

    @Override
    public int line() {
        return InternalPositionImpl$class.line(this);
    }

    @Override
    public int column() {
        return InternalPositionImpl$class.column(this);
    }

    @Override
    public String lineContent() {
        return InternalPositionImpl$class.lineContent(this);
    }

    @Override
    public String lineCaret() {
        return InternalPositionImpl$class.lineCaret(this);
    }

    @Override
    public String lineCarat() {
        return InternalPositionImpl$class.lineCarat(this);
    }

    @Override
    public String showError(String msg) {
        return InternalPositionImpl$class.showError(this, msg);
    }

    @Override
    public String showDebug() {
        return InternalPositionImpl$class.showDebug(this);
    }

    @Override
    public String show() {
        return InternalPositionImpl$class.show(this);
    }

    @Override
    public Position pos() {
        return this;
    }

    public Attachments withPos(Position newPos) {
        return newPos;
    }

    public Nothing$ fail(String what) {
        throw new UnsupportedOperationException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"Position.", " on ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{what, this})));
    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public boolean isRange() {
        return false;
    }

    @Override
    public SourceFile source() {
        return NoSourceFile$.MODULE$;
    }

    @Override
    public int start() {
        throw this.fail("start");
    }

    @Override
    public int point() {
        throw this.fail("point");
    }

    @Override
    public int end() {
        throw this.fail("end");
    }

    public Position() {
        InternalPositionImpl$class.$init$(this);
        DeprecatedPosition$class.$init$(this);
    }
}

