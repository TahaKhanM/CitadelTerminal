/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import java.io.EOFException;
import java.text.MessageFormat;
import scala.Console$;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableLike;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.io.StdIn;
import scala.runtime.BoxesRunTime;

public abstract class StdIn$class {
    public static String readLine(StdIn $this) {
        return Console$.MODULE$.in().readLine();
    }

    public static String readLine(StdIn $this, String text, Seq args) {
        Console$.MODULE$.printf(text, args);
        Console$.MODULE$.out().flush();
        return $this.readLine();
    }

    public static boolean readBoolean(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        String string2 = s2.toLowerCase();
        boolean bl = "true".equals(string2) ? true : ("t".equals(string2) ? true : ("yes".equals(string2) ? true : "y".equals(string2)));
        return bl;
    }

    public static byte readByte(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toByte();
    }

    public static short readShort(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toShort();
    }

    public static char readChar(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        return s2.charAt(0);
    }

    public static int readInt(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toInt();
    }

    public static long readLong(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toLong();
    }

    public static float readFloat(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toFloat();
    }

    public static double readDouble(StdIn $this) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps(s2).toDouble();
    }

    public static List readf(StdIn $this, String format2) {
        String s2 = $this.readLine();
        if (s2 == null) {
            throw new EOFException("Console has reached end of input");
        }
        return StdIn$class.textComponents($this, new MessageFormat(format2).parse(s2));
    }

    public static Object readf1(StdIn $this, String format2) {
        return $this.readf(format2).head();
    }

    public static Tuple2 readf2(StdIn $this, String format2) {
        List<Object> res = $this.readf(format2);
        return new Tuple2(res.head(), ((IterableLike)res.tail()).head());
    }

    public static Tuple3 readf3(StdIn $this, String format2) {
        List<Object> res = $this.readf(format2);
        return new Tuple3(res.head(), ((IterableLike)res.tail()).head(), ((IterableLike)((TraversableLike)res.tail()).tail()).head());
    }

    private static List textComponents(StdIn $this, Object[] a) {
        List res = Nil$.MODULE$;
        for (int i = a.length - 1; i >= 0; --i) {
            Object object;
            Object object2 = a[i];
            if (object2 instanceof Boolean) {
                Boolean bl = (Boolean)object2;
                object = BoxesRunTime.boxToBoolean(bl);
            } else if (object2 instanceof Byte) {
                Byte by2 = (Byte)object2;
                object = BoxesRunTime.boxToByte(by2);
            } else if (object2 instanceof Short) {
                Short s2 = (Short)object2;
                object = BoxesRunTime.boxToShort(s2);
            } else if (object2 instanceof Character) {
                Character c = (Character)object2;
                object = BoxesRunTime.boxToCharacter(c.charValue());
            } else if (object2 instanceof Integer) {
                Integer n = (Integer)object2;
                object = BoxesRunTime.boxToInteger(n);
            } else if (object2 instanceof Long) {
                Long l = (Long)object2;
                object = BoxesRunTime.boxToLong(l);
            } else if (object2 instanceof Float) {
                Float f = (Float)object2;
                object = BoxesRunTime.boxToFloat(f.floatValue());
            } else if (object2 instanceof Double) {
                Double d = (Double)object2;
                object = BoxesRunTime.boxToDouble(d);
            } else {
                object = object2;
            }
            res = res.$colon$colon(object);
        }
        return res;
    }

    public static void $init$(StdIn $this) {
    }
}

