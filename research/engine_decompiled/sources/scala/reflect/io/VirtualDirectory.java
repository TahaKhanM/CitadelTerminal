/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.VirtualFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.Null$;
import scala.sys.package$;

@ScalaSignature(bytes="\u0006\u0001q4A!\u0001\u0002\u0001\u0013\t\u0001b+\u001b:uk\u0006dG)\u001b:fGR|'/\u001f\u0006\u0003\u0007\u0011\t!![8\u000b\u0005\u00151\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u000f\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0003\u0013\ti!A\u0001\u0007BEN$(/Y2u\r&dW\r\u0003\u0005\u0010\u0001\t\u0015\r\u0011\"\u0001\u0011\u0003\u0011q\u0017-\\3\u0016\u0003E\u0001\"A\u0005\f\u000f\u0005M!R\"\u0001\u0004\n\u0005U1\u0011A\u0002)sK\u0012,g-\u0003\u0002\u00181\t11\u000b\u001e:j]\u001eT!!\u0006\u0004\t\u0011i\u0001!\u0011!Q\u0001\nE\tQA\\1nK\u0002B\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006I!H\u0001\u000f[\u0006L(-Z\"p]R\f\u0017N\\3s!\r\u0019b\u0004I\u0005\u0003?\u0019\u0011aa\u00149uS>t\u0007CA\u0006\u0001\u0011\u0015\u0011\u0003\u0001\"\u0001$\u0003\u0019a\u0014N\\5u}Q\u0019\u0001\u0005J\u0013\t\u000b=\t\u0003\u0019A\t\t\u000bq\t\u0003\u0019A\u000f\t\u000b\u001d\u0002A\u0011\u0001\t\u0002\tA\fG\u000f\u001b\u0005\u0006S\u0001!\tAK\u0001\tC\n\u001cx\u000e\\;uKV\t\u0001\u0005C\u0003-\u0001\u0011\u0005!&A\u0005d_:$\u0018-\u001b8fe\")a\u0006\u0001C\u0001_\u0005Y\u0011n\u001d#je\u0016\u001cGo\u001c:z+\u0005\u0001\u0004CA\n2\u0013\t\u0011dAA\u0004C_>dW-\u00198\t\u000bQ\u0002A\u0011I\u0018\u0002\u0013%\u001ch+\u001b:uk\u0006d\u0007b\u0002\u001c\u0001\u0005\u0004%\taN\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0002qA\u00111#O\u0005\u0003u\u0019\u0011A\u0001T8oO\"1A\b\u0001Q\u0001\na\nQ\u0002\\1ti6{G-\u001b4jK\u0012\u0004\u0003\"\u0002 \u0001\t\u0003z\u0014\u0001\u00024jY\u0016,\u0012\u0001\u0011\t\u0003'\u0005K!A\u0011\u0004\u0003\t9+H\u000e\u001c\u0005\u0006\t\u0002!\t%R\u0001\u0006S:\u0004X\u000f^\u000b\u0002\rB\u00111cR\u0005\u0003\u0011\u001a\u0011qAT8uQ&tw\rC\u0003K\u0001\u0011\u0005S)\u0001\u0004pkR\u0004X\u000f\u001e\u0005\u0006\u0019\u0002!\t!T\u0001\u0007GJ,\u0017\r^3\u0015\u00039\u0003\"aE(\n\u0005A3!\u0001B+oSRDQA\u0015\u0001\u0005\u00025\u000ba\u0001Z3mKR,\u0007\"\u0002+\u0001\t\u0003)\u0016a\u00057p_.,\bOT1nKVs7\r[3dW\u0016$Gc\u0001\u0006W/\")qb\u0015a\u0001#!)\u0001l\u0015a\u0001a\u0005IA-\u001b:fGR|'/\u001f\u0005\b5\u0002\u0011\r\u0011\"\u0003\\\u0003\u00151\u0017\u000e\\3t+\u0005a\u0006\u0003B/c#)i\u0011A\u0018\u0006\u0003?\u0002\fq!\\;uC\ndWM\u0003\u0002b\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\rt&aA'ba\"1Q\r\u0001Q\u0001\nq\u000baAZ5mKN\u0004\u0003\"B4\u0001\t\u0003A\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0003%\u00042A[6\u000b\u001b\u0005\u0001\u0017B\u00017a\u0005!IE/\u001a:bi>\u0014\b\"\u00028\u0001\t\u0003z\u0017A\u00037p_.,\bOT1nKR\u0019!\u0002]9\t\u000b=i\u0007\u0019A\t\t\u000bak\u0007\u0019\u0001\u0019\t\u000bM\u0004A\u0011\t;\u0002\u0013\u0019LG.\u001a(b[\u0016$GC\u0001\u0006v\u0011\u0015y!\u000f1\u0001\u0012\u0011\u00159\b\u0001\"\u0011y\u0003E\u0019XO\u00193je\u0016\u001cGo\u001c:z\u001d\u0006lW\r\u001a\u000b\u0003\u0015eDQa\u0004<A\u0002EAQa\u001f\u0001\u0005\u00025\u000bQa\u00197fCJ\u0004")
public class VirtualDirectory
extends AbstractFile {
    private final String name;
    private final Option<VirtualDirectory> maybeContainer;
    private final long lastModified;
    private final Map<String, AbstractFile> scala$reflect$io$VirtualDirectory$$files;

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String path() {
        Option<VirtualDirectory> option;
        block4: {
            String string2;
            block3: {
                block2: {
                    option = this.maybeContainer;
                    if (!None$.MODULE$.equals(option)) break block2;
                    string2 = this.name();
                    break block3;
                }
                if (!(option instanceof Some)) break block4;
                Some some = (Some)option;
                string2 = new StringBuilder().append((Object)((VirtualDirectory)some.x()).path()).append(BoxesRunTime.boxToCharacter('/')).append((Object)this.name()).toString();
            }
            return string2;
        }
        throw new MatchError(option);
    }

    @Override
    public VirtualDirectory absolute() {
        return this;
    }

    @Override
    public VirtualDirectory container() {
        return this.maybeContainer.get();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isVirtual() {
        return true;
    }

    @Override
    public long lastModified() {
        return this.lastModified;
    }

    public Null$ file() {
        return null;
    }

    public Nothing$ input() {
        return package$.MODULE$.error("directories cannot be read");
    }

    public Nothing$ output() {
        return package$.MODULE$.error("directories cannot be written");
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
    public AbstractFile lookupNameUnchecked(String name, boolean directory) {
        throw this.unsupported();
    }

    public Map<String, AbstractFile> scala$reflect$io$VirtualDirectory$$files() {
        return this.scala$reflect$io$VirtualDirectory$$files;
    }

    @Override
    public Iterator<AbstractFile> iterator() {
        return this.scala$reflect$io$VirtualDirectory$$files().values().toList().iterator();
    }

    @Override
    public AbstractFile lookupName(String name, boolean directory) {
        Serializable serializable = new Serializable(this, directory){
            public static final long serialVersionUID = 0L;
            public final boolean directory$1;

            public final boolean apply(AbstractFile x$1) {
                return x$1.isDirectory() == this.directory$1;
            }
            {
                this.directory$1 = directory$1;
            }
        };
        Predef$.less.colon.less less2 = Predef$.MODULE$.$conforms();
        None$ none$ = this.scala$reflect$io$VirtualDirectory$$files().get(name);
        None$ none$2 = !((Option)none$).isEmpty() && !(((AbstractFile)((Option)none$).get()).isDirectory() == serializable.directory$1) ? None$.MODULE$ : none$;
        Serializable serializable2 = new Serializable((Option)none$2, less2){
            public static final long serialVersionUID = 0L;
            public final Predef$.less.colon.less ev$1;

            public final A1 apply() {
                return (A1)this.ev$1.apply(null);
            }
            {
                this.ev$1 = ev$1;
            }
        };
        return (AbstractFile)(!((Option)none$2).isEmpty() ? ((Option)none$2).get() : serializable2.apply());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public AbstractFile fileNamed(String name) {
        AbstractFile abstractFile;
        Option<AbstractFile> option = Option$.MODULE$.apply(this.lookupName(name, false));
        if (!option.isEmpty()) {
            abstractFile = option.get();
        } else {
            void var3_3;
            VirtualFile newFile1 = new VirtualFile(name, new StringBuilder().append((Object)this.path()).append(BoxesRunTime.boxToCharacter('/')).append((Object)name).toString());
            this.scala$reflect$io$VirtualDirectory$$files().update(name, newFile1);
            abstractFile = var3_3;
        }
        return abstractFile;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public AbstractFile subdirectoryNamed(String name) {
        AbstractFile abstractFile;
        Option<AbstractFile> option = Option$.MODULE$.apply(this.lookupName(name, true));
        if (!option.isEmpty()) {
            abstractFile = option.get();
        } else {
            void var3_3;
            VirtualDirectory dir1 = new VirtualDirectory(name, new Some<VirtualDirectory>(this));
            this.scala$reflect$io$VirtualDirectory$$files().update(name, dir1);
            abstractFile = var3_3;
        }
        return abstractFile;
    }

    public void clear() {
        this.scala$reflect$io$VirtualDirectory$$files().clear();
    }

    public VirtualDirectory(String name, Option<VirtualDirectory> maybeContainer) {
        this.name = name;
        this.maybeContainer = maybeContainer;
        this.lastModified = System.currentTimeMillis();
        this.scala$reflect$io$VirtualDirectory$$files = Map$.MODULE$.empty();
    }
}

