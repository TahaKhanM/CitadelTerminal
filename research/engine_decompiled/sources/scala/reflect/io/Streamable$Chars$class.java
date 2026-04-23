/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import scala.Function1;
import scala.Predef$;
import scala.collection.Iterator;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.io.Codec$;
import scala.io.Source$;
import scala.reflect.io.Streamable;

public abstract class Streamable$Chars$class {
    public static Codec creationCodec(Streamable.Chars $this) {
        Codec codec = Codec$.MODULE$.fallbackSystemCodec();
        Predef$ predef$ = Predef$.MODULE$;
        return codec;
    }

    public static BufferedSource chars(Streamable.Chars $this, Codec codec) {
        return Source$.MODULE$.fromInputStream($this.inputStream(), codec);
    }

    public static Iterator lines(Streamable.Chars $this) {
        return $this.lines($this.creationCodec());
    }

    public static Iterator lines(Streamable.Chars $this, Codec codec) {
        return $this.chars(codec).getLines();
    }

    public static InputStreamReader reader(Streamable.Chars $this, Codec codec) {
        return new InputStreamReader($this.inputStream(), codec.charSet());
    }

    public static BufferedReader bufferedReader(Streamable.Chars $this) {
        return $this.bufferedReader($this.creationCodec());
    }

    public static BufferedReader bufferedReader(Streamable.Chars $this, Codec codec) {
        return new BufferedReader($this.reader(codec));
    }

    /*
     * WARNING - void declaration
     */
    public static Object applyReader(Streamable.Chars $this, Function1 f) {
        Object r;
        BufferedReader in = $this.bufferedReader();
        try {
            r = f.apply(in);
        }
        catch (Throwable throwable) {
            void var2_2;
            var2_2.close();
            throw throwable;
        }
        in.close();
        return r;
    }

    public static String slurp(Streamable.Chars $this) {
        return $this.slurp($this.creationCodec());
    }

    /*
     * WARNING - void declaration
     */
    public static String slurp(Streamable.Chars $this, Codec codec) {
        String string2;
        BufferedSource src = $this.chars(codec);
        try {
            string2 = src.mkString();
        }
        catch (Throwable throwable) {
            void var2_2;
            var2_2.close();
            throw throwable;
        }
        src.close();
        return string2;
    }

    public static void $init$(Streamable.Chars $this) {
    }
}

