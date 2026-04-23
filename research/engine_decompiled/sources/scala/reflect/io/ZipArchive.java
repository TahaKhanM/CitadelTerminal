/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.File;
import scala.reflect.io.FileZipArchive;
import scala.reflect.io.URLZipArchive;
import scala.reflect.io.VirtualFile;
import scala.reflect.io.ZipArchive$;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u00055w!B\u0001\u0003\u0011\u0003I\u0011A\u0003.ja\u0006\u00138\r[5wK*\u00111\u0001B\u0001\u0003S>T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)YQ\"\u0001\u0002\u0007\u000b1\u0011\u0001\u0012A\u0007\u0003\u0015iK\u0007/\u0011:dQ&4Xm\u0005\u0002\f\u001dA\u0011q\u0002E\u0007\u0002\r%\u0011\u0011C\u0002\u0002\u0007\u0003:L(+\u001a4\t\u000bMYA\u0011\u0001\u000b\u0002\rqJg.\u001b;?)\u0005I\u0001\"\u0002\f\f\t\u00039\u0012\u0001\u00034s_64\u0015\u000e\\3\u0015\u0005aY\u0002C\u0001\u0006\u001a\u0013\tQ\"A\u0001\bGS2,',\u001b9Be\u000eD\u0017N^3\t\u000bq)\u0002\u0019A\u000f\u0002\t\u0019LG.\u001a\t\u0003\u0015yI!a\b\u0002\u0003\t\u0019KG.\u001a\u0005\u0006--!\t!\t\u000b\u00031\tBQ\u0001\b\u0011A\u0002\r\u0002\"\u0001\n\u0015\u000e\u0003\u0015R!a\u0001\u0014\u000b\u0003\u001d\nAA[1wC&\u0011q$\n\u0005\u0006U-!\taK\u0001\bMJ|W.\u0016*M)\tas\u0006\u0005\u0002\u000b[%\u0011aF\u0001\u0002\u000e+Jc%,\u001b9Be\u000eD\u0017N^3\t\u000bAJ\u0003\u0019A\u0019\u0002\u0007U\u0014H\u000e\u0005\u00023k5\t1G\u0003\u00025M\u0005\u0019a.\u001a;\n\u0005Y\u001a$aA+S\u0019\")\u0001h\u0003C\u0001s\u0005yaM]8n\u001b\u0006t\u0017NZ3tiV\u0013F\n\u0006\u0002;{A\u0011!bO\u0005\u0003y\t\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016DQ\u0001M\u001cA\u0002EBQaP\u0006\u0005\n\u0001\u000bq\u0001Z5s\u001d\u0006lW\r\u0006\u0002B\u0011B\u0011!)\u0012\b\u0003\u001f\rK!\u0001\u0012\u0004\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\t\u001aAQ!\u0013 A\u0002\u0005\u000bA\u0001]1uQ\")1j\u0003C\u0005\u0019\u0006A!-Y:f\u001d\u0006lW\r\u0006\u0002B\u001b\")\u0011J\u0013a\u0001\u0003\")qj\u0003C\u0005!\u0006I1\u000f\u001d7jiB\u000bG\u000f\u001b\u000b\u0004\u0003F\u001b\u0006\"\u0002*O\u0001\u0004\t\u0015!\u00029bi\"\u0004\u0004\"\u0002+O\u0001\u0004)\u0016!\u00024s_:$\bCA\bW\u0013\t9fAA\u0004C_>dW-\u00198\u0007\u000b1\u0011\u0011\u0011A-\u0014\u0007aS$\f\u0005\u0002\u00107&\u0011AL\u0002\u0002\u0007\u000bF,\u0018\r\\:\t\u0011qA&Q1A\u0005By+\u0012a\t\u0005\tAb\u0013\t\u0011)A\u0005G\u0005)a-\u001b7fA!)1\u0003\u0017C\u0001ER\u00111\r\u001a\t\u0003\u0015aCQ\u0001H1A\u0002\rBQA\u001a-\u0005B\u001d\f\u0001#\u001e8eKJd\u00170\u001b8h'>,(oY3\u0016\u0003!\u00042aD5;\u0013\tQgA\u0001\u0003T_6,\u0007\"\u00027Y\t\u0003i\u0017aC5t\t&\u0014Xm\u0019;pef,\u0012!\u0016\u0005\u0006_b#\t\u0001]\u0001\u000bY>|7.\u001e9OC6,GcA9umB\u0011qB]\u0005\u0003g\u001a\u0011qAT8uQ&tw\rC\u0003v]\u0002\u0007\u0011)\u0001\u0003oC6,\u0007\"B<o\u0001\u0004)\u0016!\u00033je\u0016\u001cGo\u001c:z\u0011\u0015I\b\f\"\u0001{\u0003Mawn\\6va:\u000bW.Z+oG\",7m[3e)\r\t8\u0010 \u0005\u0006kb\u0004\r!\u0011\u0005\u0006ob\u0004\r!\u0016\u0005\u0006}b#\ta`\u0001\u0007GJ,\u0017\r^3\u0015\u0003EDa!a\u0001Y\t\u0003y\u0018A\u00023fY\u0016$X\rC\u0004\u0002\ba#\t!!\u0003\u0002\r=,H\u000f];u+\u0005\t\bbBA\u00071\u0012\u0005\u0011\u0011B\u0001\nG>tG/Y5oKJDq!!\u0005Y\t\u0003\tI!\u0001\u0005bEN|G.\u001e;f\r\u001d\t)\u0002WA\u0011\u0003/\u0011Q!\u00128uef\u001cB!a\u0005\u0002\u001aA\u0019!\"a\u0007\n\u0007\u0005u!AA\u0006WSJ$X/\u00197GS2,\u0007bC%\u0002\u0014\t\u0005\t\u0015!\u0003B\u0003CI1!SA\u000e\u0011\u001d\u0019\u00121\u0003C\u0001\u0003K!B!a\n\u0002,A!\u0011\u0011FA\n\u001b\u0005A\u0006BB%\u0002$\u0001\u0007\u0011\t\u0003\u0005\u00020\u0005MA\u0011AA\u0019\u0003)9W\r^!sG\"Lg/Z\u000b\u0003\u0003g\u0001B!!\u000e\u0002@5\u0011\u0011q\u0007\u0006\u0005\u0003s\tY$A\u0002{SBT1!!\u0010'\u0003\u0011)H/\u001b7\n\t\u0005\u0005\u0013q\u0007\u0002\b5&\u0004h)\u001b7f\u0011\u00191\u00171\u0003C!O\"A\u0011qIA\n\t\u0003\nI%\u0001\u0005u_N#(/\u001b8h)\t\tY\u0005\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\tFJ\u0001\u0005Y\u0006tw-C\u0002G\u0003\u001fJc!a\u0005\u0002X\u0005ucaBA-\u0003'\u0001\u00111\f\u0002\u000ey1|7-\u00197!G\"LG\u000e\u001a \u0014\t\u0005]\u0013q\u0005\u0004\u0007\u0003?B\u0006!!\u0019\u0003\u0011\u0011K'/\u00128uef\u001cB!!\u0018\u0002(!Y\u0011*!\u0018\u0003\u0002\u0003\u0006I!QA\u0011\u0011\u001d\u0019\u0012Q\fC\u0001\u0003O\"B!!\u001b\u0002lA!\u0011\u0011FA/\u0011\u0019I\u0015Q\ra\u0001\u0003\"Q\u0011qNA/\u0005\u0004%\t!!\u001d\u0002\u000f\u0015tGO]5fgV\u0011\u00111\u000f\t\t\u0003k\ny(a\u0013\u0002(5\u0011\u0011q\u000f\u0006\u0005\u0003s\nY(A\u0004nkR\f'\r\\3\u000b\u0007\u0005ud!\u0001\u0006d_2dWm\u0019;j_:LA!!!\u0002x\t9\u0001*Y:i\u001b\u0006\u0004\b\"CAC\u0003;\u0002\u000b\u0011BA:\u0003!)g\u000e\u001e:jKN\u0004\u0003B\u00027\u0002^\u0011\u0005S\u000e\u0003\u0005\u0002\f\u0006uC\u0011IAG\u0003!IG/\u001a:bi>\u0014XCAAH!\u0019\t\t*a&\u0002(9\u0019q\"a%\n\u0007\u0005Ue!A\u0004qC\u000e\\\u0017mZ3\n\t\u0005e\u00151\u0014\u0002\t\u0013R,'/\u0019;pe*\u0019\u0011Q\u0013\u0004\t\u000f=\fi\u0006\"\u0011\u0002 R1\u0011qEAQ\u0003GCa!^AO\u0001\u0004\t\u0005BB<\u0002\u001e\u0002\u0007Q\u000bC\u0004\u0002(b#\t\"!+\u0002\u0013\u0015t7/\u001e:f\t&\u0014H\u0003CA5\u0003W\u000b),a.\t\u0011\u00055\u0016Q\u0015a\u0001\u0003_\u000bA\u0001Z5sgB9\u0011QOAY\u0003\u0006%\u0014\u0002BAZ\u0003o\u00121!T1q\u0011\u0019I\u0015Q\u0015a\u0001\u0003\"A\u0011\u0011XAS\u0001\u0004\tY,\u0001\u0005{SB,e\u000e\u001e:z!\u0011\t)$!0\n\t\u0005}\u0016q\u0007\u0002\t5&\u0004XI\u001c;ss\"9\u00111\u0019-\u0005\u0012\u0005\u0015\u0017AB4fi\u0012K'\u000f\u0006\u0004\u0002j\u0005\u001d\u0017\u0011\u001a\u0005\t\u0003[\u000b\t\r1\u0001\u00020\"A\u00111ZAa\u0001\u0004\tY,A\u0003f]R\u0014\u0018\u0010")
public abstract class ZipArchive
extends AbstractFile {
    private final java.io.File file;

    public static AbstractFile fromManifestURL(URL uRL) {
        return ZipArchive$.MODULE$.fromManifestURL(uRL);
    }

    public static URLZipArchive fromURL(URL uRL) {
        return ZipArchive$.MODULE$.fromURL(uRL);
    }

    public static FileZipArchive fromFile(java.io.File file) {
        return ZipArchive$.MODULE$.fromFile(file);
    }

    public static FileZipArchive fromFile(File file) {
        return ZipArchive$.MODULE$.fromFile(file);
    }

    @Override
    public java.io.File file() {
        return this.file;
    }

    public Some<AbstractFile> underlyingSource() {
        return new Some<AbstractFile>(this);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public Nothing$ lookupName(String name, boolean directory) {
        return this.unsupported();
    }

    public Nothing$ lookupNameUnchecked(String name, boolean directory) {
        return this.unsupported();
    }

    public Nothing$ create() {
        return this.unsupported();
    }

    public Nothing$ delete() {
        return this.unsupported();
    }

    public Nothing$ output() {
        return this.unsupported();
    }

    public Nothing$ container() {
        return this.unsupported();
    }

    public Nothing$ absolute() {
        return this.unsupported();
    }

    public DirEntry ensureDir(Map<String, DirEntry> dirs2, String path, ZipEntry zipEntry) {
        Option option;
        block4: {
            DirEntry dirEntry;
            block3: {
                block2: {
                    option = dirs2.get(path);
                    if (!(option instanceof Some)) break block2;
                    Some some = (Some)option;
                    dirEntry = (DirEntry)some.x();
                    break block3;
                }
                if (!None$.MODULE$.equals(option)) break block4;
                DirEntry parent = this.ensureDir(dirs2, ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$dirName(path), null);
                DirEntry dir = new DirEntry(this, path);
                parent.entries().update(ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$baseName(path), dir);
                dirs2.update(path, dir);
                dirEntry = dir;
            }
            return dirEntry;
        }
        throw new MatchError(option);
    }

    public DirEntry getDir(Map<String, DirEntry> dirs2, ZipEntry entry) {
        return entry.isDirectory() ? this.ensureDir(dirs2, entry.getName(), entry) : this.ensureDir(dirs2, ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$dirName(entry.getName()), null);
    }

    public ZipArchive(java.io.File file) {
        this.file = file;
    }

    public abstract class Entry
    extends VirtualFile {
        public ZipFile getArchive() {
            return null;
        }

        public Some<AbstractFile> underlyingSource() {
            return new Some<AbstractFile>(this.scala$reflect$io$ZipArchive$Entry$$$outer());
        }

        @Override
        public String toString() {
            return new StringBuilder().append((Object)this.scala$reflect$io$ZipArchive$Entry$$$outer().path()).append((Object)"(").append((Object)super.path()).append((Object)")").toString();
        }

        public /* synthetic */ ZipArchive scala$reflect$io$ZipArchive$Entry$$$outer() {
            return ZipArchive.this;
        }

        public Entry(String path) {
            if (ZipArchive.this == null) {
                throw null;
            }
            super(ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$baseName(path), path);
        }
    }

    public class DirEntry
    extends Entry {
        private final HashMap<String, Entry> entries = (HashMap)HashMap$.MODULE$.apply(Nil$.MODULE$);

        public HashMap<String, Entry> entries() {
            return this.entries;
        }

        @Override
        public boolean isDirectory() {
            return true;
        }

        @Override
        public Iterator<Entry> iterator() {
            return this.entries().valuesIterator();
        }

        @Override
        public Entry lookupName(String name, boolean directory) {
            return directory ? this.entries().apply(new StringBuilder().append((Object)name).append((Object)"/").toString()) : this.entries().apply(name);
        }

        public /* synthetic */ ZipArchive scala$reflect$io$ZipArchive$DirEntry$$$outer() {
            return this.$outer;
        }

        public DirEntry(ZipArchive $outer, String path) {
            super(path);
        }
    }
}

