/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import scala.Function0;
import scala.Function1;
import scala.Serializable;
import scala.collection.Iterator;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.reflect.io.Streamable;
import scala.reflect.io.Streamable$Bytes$class;
import scala.reflect.io.Streamable$Chars$class;

public final class Streamable$ {
    public static final Streamable$ MODULE$;

    static {
        new Streamable$();
    }

    public <T extends Closeable, U> U closing(T stream, Function1<T, U> f) {
        try {
            return f.apply(stream);
        }
        finally {
            stream.close();
        }
    }

    public byte[] bytes(Function0<InputStream> is) {
        return new Streamable.Bytes(is){
            private final Function0 is$1;

            public long length() {
                return Streamable$Bytes$class.length(this);
            }

            public BufferedInputStream bufferedInput() {
                return Streamable$Bytes$class.bufferedInput(this);
            }

            public Iterator<Object> bytes() {
                return Streamable$Bytes$class.bytes(this);
            }

            public Iterator<Object> bytesAsInts() {
                return Streamable$Bytes$class.bytesAsInts(this);
            }

            public byte[] toByteArray() {
                return Streamable$Bytes$class.toByteArray(this);
            }

            public InputStream inputStream() {
                return (InputStream)this.is$1.apply();
            }
            {
                this.is$1 = is$1;
                Streamable$Bytes$class.$init$(this);
            }
        }.toByteArray();
    }

    public String slurp(Function0<InputStream> is, Codec codec) {
        return new Streamable.Chars(is){
            private final Function0 is$2;

            public Codec creationCodec() {
                return Streamable$Chars$class.creationCodec(this);
            }

            public BufferedSource chars(Codec codec) {
                return Streamable$Chars$class.chars(this, codec);
            }

            public Iterator<String> lines() {
                return Streamable$Chars$class.lines(this);
            }

            public Iterator<String> lines(Codec codec) {
                return Streamable$Chars$class.lines(this, codec);
            }

            public InputStreamReader reader(Codec codec) {
                return Streamable$Chars$class.reader(this, codec);
            }

            public BufferedReader bufferedReader() {
                return Streamable$Chars$class.bufferedReader(this);
            }

            public BufferedReader bufferedReader(Codec codec) {
                return Streamable$Chars$class.bufferedReader(this, codec);
            }

            public <T> T applyReader(Function1<BufferedReader, T> f) {
                return (T)Streamable$Chars$class.applyReader(this, f);
            }

            public String slurp() {
                return Streamable$Chars$class.slurp(this);
            }

            public String slurp(Codec codec) {
                return Streamable$Chars$class.slurp(this, codec);
            }

            public long length() {
                return Streamable$Bytes$class.length(this);
            }

            public BufferedInputStream bufferedInput() {
                return Streamable$Bytes$class.bufferedInput(this);
            }

            public Iterator<Object> bytes() {
                return Streamable$Bytes$class.bytes(this);
            }

            public Iterator<Object> bytesAsInts() {
                return Streamable$Bytes$class.bytesAsInts(this);
            }

            public byte[] toByteArray() {
                return Streamable$Bytes$class.toByteArray(this);
            }

            public InputStream inputStream() {
                return (InputStream)this.is$2.apply();
            }
            {
                this.is$2 = is$2;
                Streamable$Bytes$class.$init$(this);
                Streamable$Chars$class.$init$(this);
            }
        }.slurp(codec);
    }

    public String slurp(URL url, Codec codec) {
        Serializable serializable = new Serializable(url){
            public static final long serialVersionUID = 0L;
            private final URL url$1;

            public final InputStream apply() {
                return this.url$1.openStream();
            }
            {
                this.url$1 = url$1;
            }
        };
        return Streamable$Chars$class.slurp(new /* invalid duplicate definition of identical inner class */, codec);
    }

    private Streamable$() {
        MODULE$ = this;
    }
}

