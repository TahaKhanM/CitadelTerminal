/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Predef$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map$;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.AbstractFileClassLoader$;
import scala.reflect.internal.util.ScalaClassLoader;
import scala.reflect.internal.util.ScalaClassLoader$class;
import scala.reflect.io.AbstractFile;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;

@ScalaSignature(bytes="\u0006\u0001\u0005\rc\u0001B\u0001\u0003\u0001-\u0011q#\u00112tiJ\f7\r\u001e$jY\u0016\u001cE.Y:t\u0019>\fG-\u001a:\u000b\u0005\r!\u0011\u0001B;uS2T!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u000fI,g\r\\3di*\t\u0011\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001aA\u0003\u0005\u0002\u000e%5\taB\u0003\u0002\u0010!\u0005!A.\u00198h\u0015\u0005\t\u0012\u0001\u00026bm\u0006L!a\u0005\b\u0003\u0017\rc\u0017m]:M_\u0006$WM\u001d\t\u0003+Yi\u0011AA\u0005\u0003/\t\u0011\u0001cU2bY\u0006\u001cE.Y:t\u0019>\fG-\u001a:\t\u0011e\u0001!Q1A\u0005\u0002i\tAA]8piV\t1\u0004\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\r\u0005\u0011\u0011n\\\u0005\u0003Au\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016D\u0001B\t\u0001\u0003\u0002\u0003\u0006IaG\u0001\u0006e>|G\u000f\t\u0005\tI\u0001\u0011\t\u0011)A\u0005\u0019\u00051\u0001/\u0019:f]RDQA\n\u0001\u0005\u0002\u001d\na\u0001P5oSRtDc\u0001\u0015*UA\u0011Q\u0003\u0001\u0005\u00063\u0015\u0002\ra\u0007\u0005\u0006I\u0015\u0002\r\u0001\u0004\u0005\u0006Y\u0001!\t\"L\u0001\u0010G2\f7o\u001d(b[\u0016$v\u000eU1uQR\u0011aF\u000e\t\u0003_Mr!\u0001M\u0019\u000e\u0003!I!A\r\u0005\u0002\rA\u0013X\rZ3g\u0013\t!TG\u0001\u0004TiJLgn\u001a\u0006\u0003e!AQaN\u0016A\u00029\nAA\\1nK\")\u0011\b\u0001C\tu\u0005\u0001b-\u001b8e\u0003\n\u001cHO]1di\u001aKG.\u001a\u000b\u00037mBQa\u000e\u001dA\u00029BQ!\u0010\u0001\u0005\u0012y\nQ\u0002Z5s\u001d\u0006lW\rV8QCRDGC\u0001\u0018@\u0011\u00159D\b1\u0001/\u0011\u0015\t\u0005\u0001\"\u0005C\u0003=1\u0017N\u001c3BEN$(/Y2u\t&\u0014HCA\u000eD\u0011\u00159\u0004\t1\u0001/\u0011\u0015)\u0005\u0001\"\u0015G\u0003%1\u0017N\u001c3DY\u0006\u001c8\u000f\u0006\u0002H-B\u0012\u0001*\u0014\t\u0004_%[\u0015B\u0001&6\u0005\u0015\u0019E.Y:t!\taU\n\u0004\u0001\u0005\u00139#\u0015\u0011!A\u0001\u0006\u0003y%aA0%cE\u0011\u0001k\u0015\t\u0003aEK!A\u0015\u0005\u0003\u000f9{G\u000f[5oOB\u0011\u0001\u0007V\u0005\u0003+\"\u00111!\u00118z\u0011\u00159D\t1\u0001/\u0011\u0015A\u0006\u0001\"\u0015Z\u000311\u0017N\u001c3SKN|WO]2f)\tQ\u0006\r\u0005\u0002\\=6\tAL\u0003\u0002^!\u0005\u0019a.\u001a;\n\u0005}c&aA+S\u0019\")qg\u0016a\u0001]!)!\r\u0001C)G\u0006ia-\u001b8e%\u0016\u001cx.\u001e:dKN$\"\u0001Z5\u0011\u0007\u0015<',D\u0001g\u0015\t\u0019\u0001#\u0003\u0002iM\nYQI\\;nKJ\fG/[8o\u0011\u00159\u0014\r1\u0001/\u0011!Y\u0007\u0001#b\u0001\n\u0003a\u0017\u0001\u00059s_R,7\r^5p]\u0012{W.Y5o+\u0005i\u0007C\u00018r\u001b\u0005y'B\u00019\u0011\u0003!\u0019XmY;sSRL\u0018B\u0001:p\u0005A\u0001&o\u001c;fGRLwN\u001c#p[\u0006Lg\u000e\u0003\u0005u\u0001!\u0005\t\u0015)\u0003n\u0003E\u0001(o\u001c;fGRLwN\u001c#p[\u0006Lg\u000e\t\u0005\bm\u0002\u0011\r\u0011\"\u0003x\u0003!\u0001\u0018mY6bO\u0016\u001cX#\u0001=\u0011\u000beth&!\u0001\u000e\u0003iT!a\u001f?\u0002\u000f5,H/\u00192mK*\u0011Q\u0010C\u0001\u000bG>dG.Z2uS>t\u0017BA@{\u0005\ri\u0015\r\u001d\t\u0004\u001b\u0005\r\u0011bAA\u0003\u001d\t9\u0001+Y2lC\u001e,\u0007bBA\u0005\u0001\u0001\u0006I\u0001_\u0001\na\u0006\u001c7.Y4fg\u0002Bq!!\u0004\u0001\t\u0003\ny!A\u0007eK\u001aLg.\u001a)bG.\fw-\u001a\u000b\u0013\u0003\u0003\t\t\"a\u0005\u0002\u0018\u0005m\u0011qDA\u0012\u0003O\tY\u0003\u0003\u00048\u0003\u0017\u0001\rA\f\u0005\b\u0003+\tY\u00011\u0001/\u0003%\u0019\b/Z2USRdW\rC\u0004\u0002\u001a\u0005-\u0001\u0019\u0001\u0018\u0002\u0017M\u0004Xm\u0019,feNLwN\u001c\u0005\b\u0003;\tY\u00011\u0001/\u0003)\u0019\b/Z2WK:$wN\u001d\u0005\b\u0003C\tY\u00011\u0001/\u0003%IW\u000e\u001d7USRdW\rC\u0004\u0002&\u0005-\u0001\u0019\u0001\u0018\u0002\u0017%l\u0007\u000f\u001c,feNLwN\u001c\u0005\b\u0003S\tY\u00011\u0001/\u0003)IW\u000e\u001d7WK:$wN\u001d\u0005\b\u0003[\tY\u00011\u0001[\u0003!\u0019X-\u00197CCN,\u0007bBA\u0019\u0001\u0011\u0005\u00131G\u0001\u000bO\u0016$\b+Y2lC\u001e,G\u0003BA\u0001\u0003kAaaNA\u0018\u0001\u0004q\u0003bBA\u001d\u0001\u0011\u0005\u00131H\u0001\fO\u0016$\b+Y2lC\u001e,7\u000f\u0006\u0002\u0002>A)\u0001'a\u0010\u0002\u0002%\u0019\u0011\u0011\t\u0005\u0003\u000b\u0005\u0013(/Y=")
public class AbstractFileClassLoader
extends ClassLoader
implements ScalaClassLoader {
    private final AbstractFile root;
    private ProtectionDomain protectionDomain;
    private final Map<String, Package> packages;
    private volatile boolean bitmap$0;

    /*
     * Unable to fully structure code
     */
    private ProtectionDomain protectionDomain$lzycompute() {
        var6_1 = this;
        synchronized (var6_1) {
            block7: {
                if (this.bitmap$0) break block7;
                cl = Thread.currentThread().getContextClassLoader();
                resource = cl.getResource("scala/runtime/package.class");
                if (resource == null) ** GOTO lbl-1000
                v0 = resource.getProtocol();
                if (v0 != null && v0.equals("jar")) {
                    s = resource.getPath();
                    n = s.lastIndexOf(33);
                    if (n < 0) {
                        v1 = null;
                    } else {
                        path = s.substring(0, n);
                        v1 = new ProtectionDomain(new CodeSource(new URL(path), null), null, this, null);
                    }
                } else lbl-1000:
                // 2 sources

                {
                    v1 = null;
                }
                this.protectionDomain = v1;
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.protectionDomain;
        }
    }

    @Override
    public <T> T asContext(Function0<T> action) {
        return (T)ScalaClassLoader$class.asContext(this, action);
    }

    @Override
    public void setAsContext() {
        ScalaClassLoader$class.setAsContext(this);
    }

    @Override
    public <T> Option<Class<T>> tryToLoadClass(String path) {
        return ScalaClassLoader$class.tryToLoadClass(this, path);
    }

    @Override
    public <T> Option<Class<T>> tryToInitializeClass(String path) {
        return ScalaClassLoader$class.tryToInitializeClass(this, path);
    }

    @Override
    public Object create(String path) {
        return ScalaClassLoader$class.create(this, path);
    }

    @Override
    public byte[] classBytes(String className) {
        return ScalaClassLoader$class.classBytes(this, className);
    }

    @Override
    public InputStream classAsStream(String className) {
        return ScalaClassLoader$class.classAsStream(this, className);
    }

    @Override
    public void run(String objectName, Seq<String> arguments) {
        ScalaClassLoader$class.run(this, objectName, arguments);
    }

    public AbstractFile root() {
        return this.root;
    }

    public String classNameToPath(String name) {
        return name.endsWith(".class") ? name : new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".class"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{name.replace('.', '/')}));
    }

    public AbstractFile findAbstractFile(String name) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            AbstractFile abstractFile;
            Object object = new Object();
            try {
                ObjectRef<AbstractFile> file = ObjectRef.create(this.root());
                Predef$ predef$ = Predef$.MODULE$;
                String[] pathParts = new StringOps(name).split('/');
                Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])pathParts).init()).foreach(new Serializable(this, file, object){
                    public static final long serialVersionUID = 0L;
                    private final ObjectRef file$1;
                    private final Object nonLocalReturnKey1$1;

                    public final void apply(String dirPart) {
                        this.file$1.elem = ((AbstractFile)this.file$1.elem).lookupName(dirPart, true);
                        if ((AbstractFile)this.file$1.elem == null) {
                            throw new NonLocalReturnControl<Object>(this.nonLocalReturnKey1$1, null);
                        }
                    }
                    {
                        this.file$1 = file$1;
                        this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
                    }
                });
                AbstractFile abstractFile2 = ((AbstractFile)file.elem).lookupName((String)Predef$.MODULE$.refArrayOps((Object[])pathParts).last(), false);
                AbstractFile abstractFile3 = abstractFile2 == null ? null : abstractFile2;
                abstractFile = abstractFile3;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                abstractFile = (AbstractFile)nonLocalReturnControl.value();
            }
            return abstractFile;
        }
        throw nonLocalReturnControl;
    }

    public String dirNameToPath(String name) {
        return name.replace('.', '/');
    }

    public AbstractFile findAbstractDir(String name) {
        NonLocalReturnControl nonLocalReturnControl;
        block2: {
            AbstractFile abstractFile;
            Object object = new Object();
            try {
                ObjectRef<AbstractFile> file = ObjectRef.create(this.root());
                String string2 = this.dirNameToPath(name);
                Predef$ predef$ = Predef$.MODULE$;
                String[] pathParts = new StringOps(string2).split('/');
                Predef$.MODULE$.refArrayOps((Object[])pathParts).foreach(new Serializable(this, file, object){
                    public static final long serialVersionUID = 0L;
                    private final ObjectRef file$2;
                    private final Object nonLocalReturnKey2$1;

                    public final void apply(String dirPart) {
                        this.file$2.elem = ((AbstractFile)this.file$2.elem).lookupName(dirPart, true);
                        if ((AbstractFile)this.file$2.elem == null) {
                            throw new NonLocalReturnControl<Object>(this.nonLocalReturnKey2$1, null);
                        }
                    }
                    {
                        this.file$2 = file$2;
                        this.nonLocalReturnKey2$1 = nonLocalReturnKey2$1;
                    }
                });
                abstractFile = (AbstractFile)file.elem;
            }
            catch (NonLocalReturnControl nonLocalReturnControl2) {
                nonLocalReturnControl = nonLocalReturnControl2;
                if (nonLocalReturnControl2.key() != object) break block2;
                abstractFile = (AbstractFile)nonLocalReturnControl.value();
            }
            return abstractFile;
        }
        throw nonLocalReturnControl;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] bytes2 = this.classBytes(name);
        if (bytes2.length == 0) {
            throw new ClassNotFoundException(name);
        }
        return this.defineClass(name, bytes2, 0, bytes2.length, this.protectionDomain());
    }

    @Override
    public URL findResource(String name) {
        AbstractFile abstractFile = this.findAbstractFile(name);
        URL uRL = abstractFile == null ? null : new URL(null, new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"memory:", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{abstractFile.path()})), new URLStreamHandler(this, abstractFile){
            public final AbstractFile x1$1;

            public URLConnection openConnection(URL url) {
                return new URLConnection(this, url){
                    private final /* synthetic */ $anon$1 $outer;

                    public void connect() {
                    }

                    public InputStream getInputStream() {
                        return this.$outer.x1$1.input();
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        super(url$1);
                    }
                };
            }
            {
                this.x1$1 = x1$1;
            }
        });
        return uRL;
    }

    @Override
    public Enumeration<URL> findResources(String name) {
        URL uRL = this.findResource(name);
        Enumeration<Object> enumeration = uRL == null ? Collections.enumeration(Collections.emptyList()) : Collections.enumeration(Collections.singleton(uRL));
        return enumeration;
    }

    public ProtectionDomain protectionDomain() {
        return this.bitmap$0 ? this.protectionDomain : this.protectionDomain$lzycompute();
    }

    private Map<String, Package> packages() {
        return this.packages;
    }

    @Override
    public Package definePackage(String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion, String implVendor, URL sealBase) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Package getPackage(String name) {
        AbstractFile abstractFile = this.findAbstractDir(name);
        Package package_ = abstractFile == null ? super.getPackage(name) : this.packages().getOrElseUpdate(name, (Function0<Package>)((Object)new Serializable(this, name){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ AbstractFileClassLoader $outer;
            private final String name$1;

            public final Package apply() {
                Constructor<T> ctor = Package.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class, ClassLoader.class);
                ctor.setAccessible(true);
                return (Package)ctor.newInstance(this.name$1, null, null, null, null, null, null, null, this.$outer);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.name$1 = name$1;
            }
        }));
        return package_;
    }

    @Override
    public Package[] getPackages() {
        return (Package[])this.root().iterator().filter((Function1<AbstractFile, Object>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;

            public final boolean apply(AbstractFile x$1) {
                return x$1.isDirectory();
            }
        })).map(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ AbstractFileClassLoader $outer;

            public final Package apply(AbstractFile dir) {
                return this.$outer.getPackage(dir.name());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }).toArray(ClassTag$.MODULE$.apply(Package.class));
    }

    public AbstractFileClassLoader(AbstractFile root, ClassLoader parent) {
        this.root = root;
        super(parent);
        ScalaClassLoader$class.$init$(this);
        this.packages = (Map)Map$.MODULE$.apply(Nil$.MODULE$);
    }
}

