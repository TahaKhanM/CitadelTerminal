/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Predef;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeq;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.io.StdIn$;

public abstract class DeprecatedPredef$class {
    public static Object any2ArrowAssoc(Predef$ $this, Object x) {
        return x;
    }

    public static Object any2Ensuring(Predef$ $this, Object x) {
        return x;
    }

    public static Object any2stringfmt(Predef$ $this, Object x) {
        return x;
    }

    public static Throwable exceptionWrapper(Predef$ $this, Throwable exc) {
        return exc;
    }

    public static CharSequence seqToCharSequence(Predef$ $this, IndexedSeq xs) {
        return new Predef.SeqCharSequence(xs);
    }

    public static CharSequence arrayToCharSequence(Predef$ $this, char[] xs) {
        return new Predef.ArrayCharSequence(xs);
    }

    public static String readLine(Predef$ $this) {
        return StdIn$.MODULE$.readLine();
    }

    public static String readLine(Predef$ $this, String text, Seq args) {
        return StdIn$.MODULE$.readLine(text, args);
    }

    public static boolean readBoolean(Predef$ $this) {
        return StdIn$.MODULE$.readBoolean();
    }

    public static byte readByte(Predef$ $this) {
        return StdIn$.MODULE$.readByte();
    }

    public static short readShort(Predef$ $this) {
        return StdIn$.MODULE$.readShort();
    }

    public static char readChar(Predef$ $this) {
        return StdIn$.MODULE$.readChar();
    }

    public static int readInt(Predef$ $this) {
        return StdIn$.MODULE$.readInt();
    }

    public static long readLong(Predef$ $this) {
        return StdIn$.MODULE$.readLong();
    }

    public static float readFloat(Predef$ $this) {
        return StdIn$.MODULE$.readFloat();
    }

    public static double readDouble(Predef$ $this) {
        return StdIn$.MODULE$.readDouble();
    }

    public static List readf(Predef$ $this, String format2) {
        return StdIn$.MODULE$.readf(format2);
    }

    public static Object readf1(Predef$ $this, String format2) {
        return StdIn$.MODULE$.readf1(format2);
    }

    public static Tuple2 readf2(Predef$ $this, String format2) {
        return StdIn$.MODULE$.readf2(format2);
    }

    public static Tuple3 readf3(Predef$ $this, String format2) {
        return StdIn$.MODULE$.readf3(format2);
    }

    public static void $init$(Predef$ $this) {
    }
}

