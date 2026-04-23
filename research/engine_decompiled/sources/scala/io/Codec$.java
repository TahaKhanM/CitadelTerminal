/*
 * Decompiled with CFR 0.152.
 */
package scala.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import scala.io.Codec;
import scala.io.LowPriorityCodecImplicits;
import scala.io.LowPriorityCodecImplicits$class;
import scala.util.Properties$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class Codec$
implements LowPriorityCodecImplicits {
    public static final Codec$ MODULE$;
    private final Codec ISO8859;
    private final Codec UTF8;
    private final Codec fallbackSystemCodec;
    private volatile boolean bitmap$0;

    static {
        new Codec$();
    }

    private Codec fallbackSystemCodec$lzycompute() {
        Codec$ codec$ = this;
        synchronized (codec$) {
            if (!this.bitmap$0) {
                this.fallbackSystemCodec = LowPriorityCodecImplicits$class.fallbackSystemCodec(this);
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.fallbackSystemCodec;
        }
    }

    @Override
    public Codec fallbackSystemCodec() {
        return this.bitmap$0 ? this.fallbackSystemCodec : this.fallbackSystemCodec$lzycompute();
    }

    public final Codec ISO8859() {
        return this.ISO8859;
    }

    public final Codec UTF8() {
        return this.UTF8;
    }

    public Codec defaultCharsetCodec() {
        return this.apply(Charset.defaultCharset());
    }

    public Codec fileEncodingCodec() {
        return this.apply(Properties$.MODULE$.encodingString());
    }

    public Codec default() {
        return this.defaultCharsetCodec();
    }

    public Codec apply(String encoding) {
        return new Codec(Charset.forName(encoding));
    }

    public Codec apply(Charset charSet) {
        return new Codec(charSet);
    }

    public Codec apply(CharsetDecoder decoder) {
        return new Codec(decoder, decoder){
            private final CharsetDecoder _decoder$1;

            public CharsetDecoder decoder() {
                return this._decoder$1;
            }
            {
                this._decoder$1 = _decoder$1;
                super(decoder$1.charset());
            }
        };
    }

    public char[] fromUTF8(byte[] bytes2) {
        return this.fromUTF8(bytes2, 0, bytes2.length);
    }

    public char[] fromUTF8(byte[] bytes2, int offset, int len) {
        ByteBuffer bbuffer = ByteBuffer.wrap(bytes2, offset, len);
        CharBuffer cbuffer = this.UTF8().charSet().decode(bbuffer);
        char[] chars = new char[cbuffer.remaining()];
        cbuffer.get(chars);
        return chars;
    }

    public byte[] toUTF8(CharSequence cs) {
        CharBuffer cbuffer = CharBuffer.wrap(cs, 0, cs.length());
        ByteBuffer bbuffer = this.UTF8().charSet().encode(cbuffer);
        byte[] bytes2 = new byte[bbuffer.remaining()];
        bbuffer.get(bytes2);
        return bytes2;
    }

    public byte[] toUTF8(char[] chars, int offset, int len) {
        CharBuffer cbuffer = CharBuffer.wrap(chars, offset, len);
        ByteBuffer bbuffer = this.UTF8().charSet().encode(cbuffer);
        byte[] bytes2 = new byte[bbuffer.remaining()];
        bbuffer.get(bytes2);
        return bytes2;
    }

    public Codec string2codec(String s2) {
        return this.apply(s2);
    }

    public Codec charset2codec(Charset c) {
        return this.apply(c);
    }

    public Codec decoder2codec(CharsetDecoder cd) {
        return this.apply(cd);
    }

    private Codec$() {
        MODULE$ = this;
        LowPriorityCodecImplicits$class.$init$(this);
        this.ISO8859 = new Codec(Charset.forName("ISO-8859-1"));
        this.UTF8 = new Codec(Charset.forName("UTF-8"));
    }
}

