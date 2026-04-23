/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.io.StdIn;
import scala.io.StdIn$class;

public final class StdIn$
implements StdIn {
    public static final StdIn$ MODULE$;

    static {
        new StdIn$();
    }

    @Override
    public String readLine() {
        return StdIn$class.readLine(this);
    }

    @Override
    public String readLine(String text, Seq<Object> args) {
        return StdIn$class.readLine(this, text, args);
    }

    @Override
    public boolean readBoolean() {
        return StdIn$class.readBoolean(this);
    }

    @Override
    public byte readByte() {
        return StdIn$class.readByte(this);
    }

    @Override
    public short readShort() {
        return StdIn$class.readShort(this);
    }

    @Override
    public char readChar() {
        return StdIn$class.readChar(this);
    }

    @Override
    public int readInt() {
        return StdIn$class.readInt(this);
    }

    @Override
    public long readLong() {
        return StdIn$class.readLong(this);
    }

    @Override
    public float readFloat() {
        return StdIn$class.readFloat(this);
    }

    @Override
    public double readDouble() {
        return StdIn$class.readDouble(this);
    }

    @Override
    public List<Object> readf(String format2) {
        return StdIn$class.readf(this, format2);
    }

    @Override
    public Object readf1(String format2) {
        return StdIn$class.readf1(this, format2);
    }

    @Override
    public Tuple2<Object, Object> readf2(String format2) {
        return StdIn$class.readf2(this, format2);
    }

    @Override
    public Tuple3<Object, Object, Object> readf3(String format2) {
        return StdIn$class.readf3(this, format2);
    }

    private StdIn$() {
        MODULE$ = this;
        StdIn$class.$init$(this);
    }
}

