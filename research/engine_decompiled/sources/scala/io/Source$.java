/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.io.Codec$;
import scala.io.Source;
import scala.runtime.BoxedUnit;

public final class Source$ {
    public static final Source$ MODULE$;
    private final int DefaultBufSize;

    static {
        new Source$();
    }

    public int DefaultBufSize() {
        return this.DefaultBufSize;
    }

    public BufferedSource stdin() {
        return this.fromInputStream(System.in, Codec$.MODULE$.fallbackSystemCodec());
    }

    public Source fromIterable(Iterable<Object> iterable) {
        return new Source(iterable){
            private final Iterator<Object> iter;

            public Iterator<Object> iter() {
                return this.iter;
            }
            {
                this.iter = iterable$1.iterator();
            }
        }.withReset((Function0<Source>)((Object)new Serializable(iterable){
            public static final long serialVersionUID = 0L;
            private final Iterable iterable$1;

            public final Source apply() {
                return Source$.MODULE$.fromIterable(this.iterable$1);
            }
            {
                this.iterable$1 = iterable$1;
            }
        }));
    }

    public Source fromChar(char c) {
        return this.fromIterable(Predef$.MODULE$.wrapCharArray(new char[]{c}));
    }

    public Source fromChars(char[] chars) {
        return this.fromIterable(Predef$.MODULE$.wrapCharArray(chars));
    }

    public Source fromString(String s2) {
        return this.fromIterable(Predef$.MODULE$.wrapString(s2));
    }

    public BufferedSource fromFile(String name, Codec codec) {
        return this.fromFile(new File(name), codec);
    }

    public BufferedSource fromFile(String name, String enc) {
        return this.fromFile(name, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromFile(URI uri, Codec codec) {
        return this.fromFile(new File(uri), codec);
    }

    public BufferedSource fromFile(URI uri, String enc) {
        return this.fromFile(uri, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromFile(File file, Codec codec) {
        return this.fromFile(file, this.DefaultBufSize(), codec);
    }

    public BufferedSource fromFile(File file, String enc) {
        return this.fromFile(file, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromFile(File file, String enc, int bufferSize) {
        return this.fromFile(file, bufferSize, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromFile(File file, int bufferSize, Codec codec) {
        FileInputStream inputStream = new FileInputStream(file);
        return (BufferedSource)this.createBufferedSource(inputStream, bufferSize, (Function0<Source>)((Object)new Serializable(file, bufferSize, codec){
            public static final long serialVersionUID = 0L;
            private final File file$1;
            private final int bufferSize$1;
            private final Codec codec$2;

            public final BufferedSource apply() {
                return Source$.MODULE$.fromFile(this.file$1, this.bufferSize$1, this.codec$2);
            }
            {
                this.file$1 = file$1;
                this.bufferSize$1 = bufferSize$1;
                this.codec$2 = codec$2;
            }
        }), (Function0<BoxedUnit>)((Object)new Serializable(inputStream){
            public static final long serialVersionUID = 0L;
            public final FileInputStream inputStream$1;

            public final void apply() {
                this.inputStream$1.close();
            }

            public void apply$mcV$sp() {
                this.inputStream$1.close();
            }
            {
                this.inputStream$1 = inputStream$1;
            }
        }), codec).withDescription(new StringBuilder().append((Object)"file:").append((Object)file.getAbsolutePath()).toString());
    }

    public Source fromBytes(byte[] bytes2, Codec codec) {
        return this.fromString(new String(bytes2, codec.name()));
    }

    public Source fromBytes(byte[] bytes2, String enc) {
        return this.fromBytes(bytes2, Codec$.MODULE$.apply(enc));
    }

    public Source fromRawBytes(byte[] bytes2) {
        return this.fromString(new String(bytes2, Codec$.MODULE$.ISO8859().name()));
    }

    public BufferedSource fromURI(URI uri, Codec codec) {
        return this.fromFile(new File(uri), codec);
    }

    public BufferedSource fromURL(String s2, String enc) {
        return this.fromURL(s2, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromURL(String s2, Codec codec) {
        return this.fromURL(new URL(s2), codec);
    }

    public BufferedSource fromURL(URL url, String enc) {
        return this.fromURL(url, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromURL(URL url, Codec codec) {
        return this.fromInputStream(url.openStream(), codec);
    }

    public BufferedSource createBufferedSource(InputStream inputStream, int bufferSize, Function0<Source> reset, Function0<BoxedUnit> close, Codec codec) {
        Serializable resetFn = reset == null ? new Serializable(inputStream, bufferSize, (Function0)((Object)reset), close, codec){
            public static final long serialVersionUID = 0L;
            private final InputStream inputStream$2;
            private final int bufferSize$2;
            private final Function0 reset$1;
            private final Function0 close$1;
            private final Codec codec$3;

            public final BufferedSource apply() {
                return Source$.MODULE$.createBufferedSource(this.inputStream$2, this.bufferSize$2, this.reset$1, this.close$1, this.codec$3);
            }
            {
                this.inputStream$2 = inputStream$2;
                this.bufferSize$2 = bufferSize$2;
                this.reset$1 = reset$1;
                this.close$1 = close$1;
                this.codec$3 = codec$3;
            }
        } : reset;
        return (BufferedSource)new BufferedSource(inputStream, bufferSize, codec).withReset((Function0<Source>)((Object)resetFn)).withClose(close);
    }

    public int createBufferedSource$default$2() {
        return this.DefaultBufSize();
    }

    public Function0<Source> createBufferedSource$default$3() {
        return null;
    }

    public Function0<BoxedUnit> createBufferedSource$default$4() {
        return null;
    }

    public BufferedSource fromInputStream(InputStream is, String enc) {
        return this.fromInputStream(is, Codec$.MODULE$.apply(enc));
    }

    public BufferedSource fromInputStream(InputStream is, Codec codec) {
        Serializable x$2 = new Serializable(is, codec){
            public static final long serialVersionUID = 0L;
            private final InputStream is$1;
            private final Codec codec$1;

            public final BufferedSource apply() {
                return Source$.MODULE$.fromInputStream(this.is$1, this.codec$1);
            }
            {
                this.is$1 = is$1;
                this.codec$1 = codec$1;
            }
        };
        Serializable x$3 = new Serializable(is){
            public static final long serialVersionUID = 0L;
            public final InputStream is$1;

            public final void apply() {
                this.is$1.close();
            }

            public void apply$mcV$sp() {
                this.is$1.close();
            }
            {
                this.is$1 = is$1;
            }
        };
        int x$4 = this.createBufferedSource$default$2();
        return this.createBufferedSource(is, x$4, (Function0<Source>)((Object)x$2), (Function0<BoxedUnit>)((Object)x$3), codec);
    }

    private Source$() {
        MODULE$ = this;
        this.DefaultBufSize = 2048;
    }
}

