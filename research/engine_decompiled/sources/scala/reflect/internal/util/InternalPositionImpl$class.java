/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.util.FakePos;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.NoSourceFile$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Position$;
import scala.reflect.internal.util.SourceFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

public abstract class InternalPositionImpl$class {
    public static Position finalPosition(Position $this) {
        return $this.source().positionInUltimateSource($this);
    }

    public static boolean isTransparent(Position $this) {
        return false;
    }

    public static boolean isOffset(Position $this) {
        return $this.isDefined() && !$this.isRange();
    }

    public static boolean isOpaqueRange(Position $this) {
        return $this.isRange() && !$this.isTransparent();
    }

    public static int pointOrElse(Position $this, int alt) {
        return $this.isDefined() ? $this.point() : alt;
    }

    public static Position makeTransparent(Position $this) {
        return $this.isOpaqueRange() ? Position$.MODULE$.transparent($this.source(), $this.start(), $this.point(), $this.end()) : $this;
    }

    public static Position withStart(Position $this, int start) {
        SourceFile x$4 = InternalPositionImpl$class.copyRange$default$1($this);
        int x$5 = InternalPositionImpl$class.copyRange$default$3($this);
        int x$6 = InternalPositionImpl$class.copyRange$default$4($this);
        return InternalPositionImpl$class.copyRange($this, x$4, start, x$5, x$6);
    }

    public static Position withPoint(Position $this, int point) {
        Position position;
        if ($this.isRange()) {
            SourceFile x$8 = InternalPositionImpl$class.copyRange$default$1($this);
            int x$9 = InternalPositionImpl$class.copyRange$default$2($this);
            int x$10 = InternalPositionImpl$class.copyRange$default$4($this);
            position = InternalPositionImpl$class.copyRange($this, x$8, x$9, point, x$10);
        } else {
            position = Position$.MODULE$.offset($this.source(), point);
        }
        return position;
    }

    public static Position withEnd(Position $this, int end) {
        SourceFile x$12 = InternalPositionImpl$class.copyRange$default$1($this);
        int x$13 = InternalPositionImpl$class.copyRange$default$2($this);
        int x$14 = InternalPositionImpl$class.copyRange$default$3($this);
        return InternalPositionImpl$class.copyRange($this, x$12, x$13, x$14, end);
    }

    public static Position withSource(Position $this, SourceFile source) {
        return InternalPositionImpl$class.copyRange($this, source, InternalPositionImpl$class.copyRange$default$2($this), InternalPositionImpl$class.copyRange$default$3($this), InternalPositionImpl$class.copyRange$default$4($this));
    }

    public static Position withShift(Position $this, int shift) {
        return Position$.MODULE$.range($this.source(), $this.start() + shift, $this.point() + shift, $this.end() + shift);
    }

    public static Position focusStart(Position $this) {
        return $this.isRange() ? InternalPositionImpl$class.asOffset($this, $this.start()) : $this;
    }

    public static Position focus(Position $this) {
        return $this.isRange() ? InternalPositionImpl$class.asOffset($this, $this.point()) : $this;
    }

    public static Position focusEnd(Position $this) {
        return $this.isRange() ? InternalPositionImpl$class.asOffset($this, $this.end()) : $this;
    }

    public static Position $bar(Position $this, Position that, Seq poses) {
        return poses.foldLeft($this.$bar(that), new Serializable($this){
            public static final long serialVersionUID = 0L;

            public final Position apply(Position x$1, Position x$2) {
                return x$1.$bar(x$2);
            }
        });
    }

    public static Position $bar(Position $this, Position that) {
        return $this.union(that);
    }

    public static Position $up(Position $this, int point) {
        return $this.withPoint(point);
    }

    public static Position $bar$up(Position $this, Position that) {
        return $this.$bar(that).$up(that.point());
    }

    public static Position $up$bar(Position $this, Position that) {
        return $this.$bar(that).$up($this.point());
    }

    public static Position union(Position $this, Position pos) {
        Position position;
        if (pos.isRange()) {
            if ($this.isRange()) {
                int n = $this.start();
                Predef$ predef$ = Predef$.MODULE$;
                int x$15 = RichInt$.MODULE$.min$extension(n, pos.start());
                int n2 = $this.end();
                Predef$ predef$2 = Predef$.MODULE$;
                int x$16 = RichInt$.MODULE$.max$extension(n2, pos.end());
                SourceFile x$17 = InternalPositionImpl$class.copyRange$default$1($this);
                int x$18 = InternalPositionImpl$class.copyRange$default$3($this);
                position = InternalPositionImpl$class.copyRange($this, x$17, x$15, x$18, x$16);
            } else {
                position = pos;
            }
        } else {
            position = $this;
        }
        return position;
    }

    public static boolean includes(Position $this, Position pos) {
        return $this.isRange() && pos.isDefined() && $this.start() <= pos.start() && pos.end() <= $this.end();
    }

    public static boolean properlyIncludes(Position $this, Position pos) {
        return $this.includes(pos) && ($this.start() < pos.start() || pos.end() < $this.end());
    }

    public static boolean precedes(Position $this, Position pos) {
        return InternalPositionImpl$class.bothDefined($this, pos) && $this.end() <= pos.start();
    }

    public static boolean properlyPrecedes(Position $this, Position pos) {
        return InternalPositionImpl$class.bothDefined($this, pos) && $this.end() < pos.start();
    }

    public static boolean sameRange(Position $this, Position pos) {
        return InternalPositionImpl$class.bothRanges($this, pos) && $this.start() == pos.start() && $this.end() == pos.end();
    }

    public static boolean overlaps(Position $this, Position pos) {
        return InternalPositionImpl$class.bothRanges($this, pos) && $this.start() < pos.end() && pos.start() < $this.end();
    }

    public static int line(Position $this) {
        return InternalPositionImpl$class.hasSource($this) ? $this.source().offsetToLine($this.point()) + 1 : 0;
    }

    public static int column(Position $this) {
        return InternalPositionImpl$class.hasSource($this) ? InternalPositionImpl$class.calculateColumn($this) : 0;
    }

    public static String lineContent(Position $this) {
        return InternalPositionImpl$class.hasSource($this) ? $this.source().lineToString($this.line() - 1) : "";
    }

    public static String lineCaret(Position $this) {
        String string2;
        if (InternalPositionImpl$class.hasSource($this)) {
            Predef$ predef$ = Predef$.MODULE$;
            string2 = new StringBuilder().append((Object)new StringOps(" ").$times($this.column() - 1)).append((Object)"^").toString();
        } else {
            string2 = "";
        }
        return string2;
    }

    public static String lineCarat(Position $this) {
        return $this.lineCaret();
    }

    public static String showError(Position $this, String msg) {
        String string2;
        Position position = $this.finalPosition();
        if (position instanceof FakePos) {
            FakePos fakePos = (FakePos)position;
            string2 = new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", " ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{fakePos.msg(), msg}));
        } else {
            string2 = NoPosition$.MODULE$.equals(position) ? msg : InternalPositionImpl$class.errorAt$1($this, position, msg);
        }
        return string2;
    }

    public static String showDebug(Position $this) {
        return $this.toString();
    }

    public static String show(Position $this) {
        return $this.isOpaqueRange() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", ":", "]"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger($this.start()), BoxesRunTime.boxToInteger($this.end())})) : ($this.isTransparent() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"<", ":", ">"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger($this.start()), BoxesRunTime.boxToInteger($this.end())})) : ($this.isDefined() ? new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"[", "]"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger($this.point())})) : "[NoPosition]"));
    }

    private static Position asOffset(Position $this, int point) {
        return Position$.MODULE$.offset($this.source(), point);
    }

    private static Position copyRange(Position $this, SourceFile source, int start, int point, int end) {
        return Position$.MODULE$.range(source, start, point, end);
    }

    private static SourceFile copyRange$default$1(Position $this) {
        return $this.source();
    }

    private static int copyRange$default$2(Position $this) {
        return $this.start();
    }

    private static int copyRange$default$3(Position $this) {
        return $this.point();
    }

    private static int copyRange$default$4(Position $this) {
        return $this.end();
    }

    private static int calculateColumn(Position $this) {
        int col = 0;
        for (int idx = $this.source().lineToOffset($this.source().offsetToLine($this.point())); idx != $this.point(); ++idx) {
            col += $this.source().content()[idx] == '\t' ? Position$.MODULE$.tabInc() - col % Position$.MODULE$.tabInc() : 1;
        }
        return col + 1;
    }

    private static boolean hasSource(Position $this) {
        return $this.source() != NoSourceFile$.MODULE$;
    }

    private static boolean bothRanges(Position $this, Position that) {
        return $this.isRange() && that.isRange();
    }

    private static boolean bothDefined(Position $this, Position that) {
        return $this.isDefined() && that.isDefined();
    }

    public static final String u$1(Position $this, int c) {
        return new StringOps("\\u%04x").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(c)}));
    }

    public static final boolean uable$1(Position $this, int c) {
        return c < 32 && c != 9 || c == 127;
    }

    private static final String escaped$1(Position $this, String s2) {
        String string2;
        Predef$ predef$ = Predef$.MODULE$;
        if (new StringOps(s2).exists((Function1<Object, Object>)((Object)new Serializable($this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ Position $outer;

            public final boolean apply(char c) {
                return InternalPositionImpl$class.uable$1(this.$outer, c);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }))) {
            StringBuilder sb = new StringBuilder();
            Predef$ predef$2 = Predef$.MODULE$;
            new StringOps(s2).foreach(new Serializable($this, sb){
                public static final long serialVersionUID = 0L;
                private final /* synthetic */ Position $outer;
                private final StringBuilder sb$1;

                public final StringBuilder apply(char c) {
                    return this.sb$1.append(InternalPositionImpl$class.uable$1(this.$outer, c) ? InternalPositionImpl$class.u$1(this.$outer, c) : BoxesRunTime.boxToCharacter(c));
                }
                {
                    if ($outer == null) {
                        throw null;
                    }
                    this.$outer = $outer;
                    this.sb$1 = sb$1;
                }
            });
            string2 = sb.toString();
        } else {
            string2 = s2;
        }
        return string2;
    }

    private static final int where$1(Position $this, Position p$1) {
        return p$1.line();
    }

    private static final String content$1(Position $this, Position p$1) {
        return InternalPositionImpl$class.escaped$1($this, p$1.lineContent());
    }

    private static final String indicator$1(Position $this, Position p$1) {
        return p$1.lineCaret();
    }

    private static final String errorAt$1(Position $this, Position p, String msg$1) {
        Integer arg$macro$4 = BoxesRunTime.boxToInteger(InternalPositionImpl$class.where$1($this, p));
        String arg$macro$6 = InternalPositionImpl$class.content$1($this, p);
        String arg$macro$7 = InternalPositionImpl$class.indicator$1($this, p);
        return new StringOps("%s: %s%n%s%n%s").format(Predef$.MODULE$.genericWrapArray(new Object[]{arg$macro$4, msg$1, arg$macro$6, arg$macro$7}));
    }

    public static void $init$(Position $this) {
    }
}

