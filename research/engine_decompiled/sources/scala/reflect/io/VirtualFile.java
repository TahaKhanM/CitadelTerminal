/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import scala.Array$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.package$;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.NoAbstractFile$;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u00055b\u0001B\u0001\u0003\u0001%\u00111BV5siV\fGNR5mK*\u00111\u0001B\u0001\u0003S>T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u0005\u0011\u0011BA\u0007\u0003\u00051\t%m\u001d;sC\u000e$h)\u001b7f\u0011!y\u0001A!b\u0001\n\u0003\u0001\u0012\u0001\u00028b[\u0016,\u0012!\u0005\t\u0003%Yq!a\u0005\u000b\u000e\u0003\u0019I!!\u0006\u0004\u0002\rA\u0013X\rZ3g\u0013\t9\u0002D\u0001\u0004TiJLgn\u001a\u0006\u0003+\u0019A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I!E\u0001\u0006]\u0006lW\r\t\u0005\t9\u0001\u0011)\u0019!C!!\u0005!\u0001/\u0019;i\u0011!q\u0002A!A!\u0002\u0013\t\u0012!\u00029bi\"\u0004\u0003\"\u0002\u0011\u0001\t\u0003\t\u0013A\u0002\u001fj]&$h\bF\u0002#G\u0011\u0002\"a\u0003\u0001\t\u000b=y\u0002\u0019A\t\t\u000bqy\u0002\u0019A\t\t\u000b\u0001\u0002A\u0011\u0001\u0014\u0015\u0005\t:\u0003\"B\b&\u0001\u0004\t\u0002\"B\u0015\u0001\t\u0003R\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003-\u0002\"a\u0005\u0017\n\u000552!aA%oi\")q\u0006\u0001C!a\u00051Q-];bYN$\"!\r\u001b\u0011\u0005M\u0011\u0014BA\u001a\u0007\u0005\u001d\u0011un\u001c7fC:DQ!\u000e\u0018A\u0002Y\nA\u0001\u001e5biB\u00111cN\u0005\u0003q\u0019\u00111!\u00118z\u0011\u001dQ\u0004\u00011A\u0005\nm\nqaY8oi\u0016tG/F\u0001=!\r\u0019RhP\u0005\u0003}\u0019\u0011Q!\u0011:sCf\u0004\"a\u0005!\n\u0005\u00053!\u0001\u0002\"zi\u0016Dqa\u0011\u0001A\u0002\u0013%A)A\u0006d_:$XM\u001c;`I\u0015\fHCA#I!\t\u0019b)\u0003\u0002H\r\t!QK\\5u\u0011\u001dI%)!AA\u0002q\n1\u0001\u001f\u00132\u0011\u0019Y\u0005\u0001)Q\u0005y\u0005A1m\u001c8uK:$\b\u0005C\u0003N\u0001\u0011\u0005a*\u0001\u0005bEN|G.\u001e;f+\u0005\u0011\u0003\"\u0002)\u0001\t\u0003\t\u0016\u0001\u00024jY\u0016,\u0012A\u0015\t\u0003'^k\u0011\u0001\u0016\u0006\u0003\u0007US\u0011AV\u0001\u0005U\u00064\u0018-\u0003\u0002Y)\n!a)\u001b7f\u0011\u0015Q\u0006\u0001\"\u0011\\\u0003)\u0019\u0018N_3PaRLwN\\\u000b\u00029B\u00191#X\u0016\n\u0005y3!AB(qi&|g\u000eC\u0003a\u0001\u0011\u0005\u0011-A\u0003j]B,H/F\u0001c!\t\u00196-\u0003\u0002e)\nY\u0011J\u001c9viN#(/Z1n\u0011\u00151\u0007\u0001\"\u0011h\u0003\u0019yW\u000f\u001e9viV\t\u0001\u000e\u0005\u0002TS&\u0011!\u000e\u0016\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\u0006Y\u0002!\t!\\\u0001\nG>tG/Y5oKJ,\u0012A\u0003\u0005\u0006_\u0002!\t\u0001]\u0001\fSN$\u0015N]3di>\u0014\u00180F\u00012\u0011\u0015\u0011\b\u0001\"\u0011q\u0003%I7OV5siV\fG\u000eC\u0003u\u0001\u0011\u0005Q/\u0001\u0007mCN$Xj\u001c3jM&,G-F\u0001w!\t\u0019r/\u0003\u0002y\r\t!Aj\u001c8h\u0011\u0015Q\b\u0001\"\u0001|\u0003!IG/\u001a:bi>\u0014X#\u0001?\u0011\tu\f\tA\u0003\b\u0003'yL!a \u0004\u0002\u000fA\f7m[1hK&!\u00111AA\u0003\u0005!IE/\u001a:bi>\u0014(BA@\u0007\u0011\u001d\tI\u0001\u0001C\u0001\u0003\u0017\taa\u0019:fCR,G#A#\t\u000f\u0005=\u0001\u0001\"\u0001\u0002\f\u00051A-\u001a7fi\u0016Dq!a\u0005\u0001\t\u0003\t)\"\u0001\u0006m_>\\W\u000f\u001d(b[\u0016$RACA\f\u00033AaaDA\t\u0001\u0004\t\u0002bBA\u000e\u0003#\u0001\r!M\u0001\nI&\u0014Xm\u0019;pefDq!a\b\u0001\t\u0003\t\t#A\nm_>\\W\u000f\u001d(b[\u0016,fn\u00195fG.,G\r\u0006\u0004\u0002$\u0005%\u00121\u0006\t\u0004'\u0005\u0015\u0012bAA\u0014\r\t9aj\u001c;iS:<\u0007BB\b\u0002\u001e\u0001\u0007\u0011\u0003C\u0004\u0002\u001c\u0005u\u0001\u0019A\u0019")
public class VirtualFile
extends AbstractFile {
    private final String name;
    private final String path;
    private byte[] scala$reflect$io$VirtualFile$$content;

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String path() {
        return this.path;
    }

    public int hashCode() {
        return this.path().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        boolean bl;
        if (that instanceof VirtualFile) {
            VirtualFile virtualFile = (VirtualFile)that;
            String string2 = virtualFile.path();
            String string3 = this.path();
            bl = !(string2 != null ? !string2.equals(string3) : string3 != null);
        } else {
            bl = false;
        }
        return bl;
    }

    private byte[] scala$reflect$io$VirtualFile$$content() {
        return this.scala$reflect$io$VirtualFile$$content;
    }

    public void scala$reflect$io$VirtualFile$$content_$eq(byte[] x$1) {
        this.scala$reflect$io$VirtualFile$$content = x$1;
    }

    @Override
    public VirtualFile absolute() {
        return this;
    }

    @Override
    public File file() {
        return null;
    }

    @Override
    public Option<Object> sizeOption() {
        return new Some<Object>(BoxesRunTime.boxToInteger(this.scala$reflect$io$VirtualFile$$content().length));
    }

    @Override
    public InputStream input() {
        return new ByteArrayInputStream(this.scala$reflect$io$VirtualFile$$content());
    }

    @Override
    public OutputStream output() {
        return new ByteArrayOutputStream(this){
            private final /* synthetic */ VirtualFile $outer;

            public void close() {
                super.close();
                this.$outer.scala$reflect$io$VirtualFile$$content_$eq(this.toByteArray());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    @Override
    public AbstractFile container() {
        return NoAbstractFile$.MODULE$;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isVirtual() {
        return true;
    }

    @Override
    public long lastModified() {
        return 0L;
    }

    @Override
    public Iterator<AbstractFile> iterator() {
        boolean bl = this.isDirectory();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"not a directory '").append(this).append((Object)"'").toString()).toString());
        }
        return package$.MODULE$.Iterator().empty();
    }

    @Override
    public void create() {
        throw this.unsupported();
    }

    @Override
    public void delete() {
        throw this.unsupported();
    }

    @Override
    public AbstractFile lookupName(String name, boolean directory) {
        boolean bl = this.isDirectory();
        Predef$ predef$ = Predef$.MODULE$;
        if (!bl) {
            throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append((Object)new StringBuilder().append((Object)"not a directory '").append(this).append((Object)"'").toString()).toString());
        }
        return null;
    }

    public Nothing$ lookupNameUnchecked(String name, boolean directory) {
        return this.unsupported();
    }

    public VirtualFile(String name, String path) {
        this.name = name;
        this.path = path;
        this.scala$reflect$io$VirtualFile$$content = Array$.MODULE$.emptyByteArray();
    }

    public VirtualFile(String name) {
        this(name, name);
    }
}

