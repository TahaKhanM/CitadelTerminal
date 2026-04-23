/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;

@ScalaSignature(bytes="\u0006\u000114Q!\u0001\u0002\u0002\u0002%\u0011\u0001BU3q_J$XM\u001d\u0006\u0003\u0007\u0011\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u0019\tqA]3gY\u0016\u001cGOC\u0001\b\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-aQ\"\u0001\u0004\n\u000551!AB!osJ+g\rC\u0003\u0010\u0001\u0011\u0005\u0001#\u0001\u0004=S:LGO\u0010\u000b\u0002#A\u0011!\u0003A\u0007\u0002\u0005!)A\u0003\u0001D\t+\u0005)\u0011N\u001c4paQ)a#G\u0011+oA\u00111bF\u0005\u00031\u0019\u0011A!\u00168ji\")!d\u0005a\u00017\u0005\u0019\u0001o\\:\u0011\u0005qyR\"A\u000f\u000b\u0005y\u0011\u0011\u0001B;uS2L!\u0001I\u000f\u0003\u0011A{7/\u001b;j_:DQAI\nA\u0002\r\n1!\\:h!\t!sE\u0004\u0002\fK%\u0011aEB\u0001\u0007!J,G-\u001a4\n\u0005!J#AB*ue&twM\u0003\u0002'\r!)1f\u0005a\u0001Y\u0005A1/\u001a<fe&$\u0018\u0010\u0005\u0002.]5\t\u0001\u0001B\u00030\u0001\t\u0005\u0001G\u0001\u0005TKZ,'/\u001b;z#\t\tD\u0007\u0005\u0002\fe%\u00111G\u0002\u0002\b\u001d>$\b.\u001b8h!\tYQ'\u0003\u00027\r\t\u0019\u0011I\\=\t\u000ba\u001a\u0002\u0019A\u001d\u0002\u000b\u0019|'oY3\u0011\u0005-Q\u0014BA\u001e\u0007\u0005\u001d\u0011un\u001c7fC:DQ!\u0010\u0001\u0005\u0002y\nA!Z2i_R\u0019ac\u0010!\t\u000bia\u0004\u0019A\u000e\t\u000b\tb\u0004\u0019A\u0012\t\u000b\t\u0003A\u0011A\"\u0002\u000f]\f'O\\5oOR\u0019a\u0003R#\t\u000bi\t\u0005\u0019A\u000e\t\u000b\t\n\u0005\u0019A\u0012\t\u000b\u001d\u0003A\u0011\u0001%\u0002\u000b\u0015\u0014(o\u001c:\u0015\u0007YI%\nC\u0003\u001b\r\u0002\u00071\u0004C\u0003#\r\u0002\u00071\u0005C\u0004M\u0001\t\u0007i\u0011A'\u0002\t%seiT\u000b\u0002Y!9q\n\u0001b\u0001\u000e\u0003i\u0015aB,B%:Kej\u0012\u0005\b#\u0002\u0011\rQ\"\u0001N\u0003\u0015)%KU(S\u0011\u0015\u0019\u0006A\"\u0001U\u0003\u0015\u0019w.\u001e8u)\t)\u0006\f\u0005\u0002\f-&\u0011qK\u0002\u0002\u0004\u0013:$\b\"B\u0016S\u0001\u0004a\u0003\"\u0002.\u0001\r\u0003Y\u0016A\u0003:fg\u0016$8i\\;oiR\u0011a\u0003\u0018\u0005\u0006We\u0003\r\u0001\f\u0005\u0006=\u0002!\taX\u0001\u000bKJ\u0014xN]\"pk:$X#A+\t\u000b\u0005\u0004A\u0011A0\u0002\u0019]\f'O\\5oO\u000e{WO\u001c;\t\u000b\r\u0004A\u0011\u00013\u0002\u0013!\f7/\u0012:s_J\u001cX#A\u001d\t\u000b\u0019\u0004A\u0011\u00013\u0002\u0017!\f7oV1s]&twm\u001d\u0005\u0006Q\u0002!\t![\u0001\u0006e\u0016\u001cX\r\u001e\u000b\u0002-!)1\u000e\u0001C\u0001S\u0006)a\r\\;tQ\u0002")
public abstract class Reporter {
    public abstract void info0(Position var1, String var2, Object var3, boolean var4);

    public void echo(Position pos, String msg) {
        this.info0(pos, msg, this.INFO(), true);
    }

    public void warning(Position pos, String msg) {
        this.info0(pos, msg, this.WARNING(), false);
    }

    public void error(Position pos, String msg) {
        this.info0(pos, msg, this.ERROR(), false);
    }

    public abstract Object INFO();

    public abstract Object WARNING();

    public abstract Object ERROR();

    public abstract int count(Object var1);

    public abstract void resetCount(Object var1);

    public int errorCount() {
        return this.count(this.ERROR());
    }

    public int warningCount() {
        return this.count(this.WARNING());
    }

    public boolean hasErrors() {
        return this.count(this.ERROR()) > 0;
    }

    public boolean hasWarnings() {
        return this.count(this.WARNING()) > 0;
    }

    public void reset() {
        this.resetCount(this.INFO());
        this.resetCount(this.WARNING());
        this.resetCount(this.ERROR());
    }

    public void flush() {
    }
}

