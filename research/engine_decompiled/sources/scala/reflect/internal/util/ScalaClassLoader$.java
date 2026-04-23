/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import scala.Function0;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Predef$;
import scala.collection.Seq;
import scala.reflect.internal.util.ScalaClassLoader;
import scala.reflect.internal.util.ScalaClassLoader$class;

public final class ScalaClassLoader$ {
    public static final ScalaClassLoader$ MODULE$;

    static {
        new ScalaClassLoader$();
    }

    public ScalaClassLoader apply(ClassLoader cl) {
        ScalaClassLoader scalaClassLoader;
        if (cl instanceof ScalaClassLoader) {
            ScalaClassLoader scalaClassLoader2;
            scalaClassLoader = scalaClassLoader2 = (ScalaClassLoader)((Object)cl);
        } else if (cl instanceof URLClassLoader) {
            URLClassLoader uRLClassLoader = (URLClassLoader)cl;
            scalaClassLoader = new ScalaClassLoader.URLClassLoader(Predef$.MODULE$.refArrayOps((Object[])uRLClassLoader.getURLs()).toSeq(), uRLClassLoader.getParent());
        } else {
            scalaClassLoader = new ScalaClassLoader(cl){

                public <T> T asContext(Function0<T> action) {
                    return (T)ScalaClassLoader$class.asContext(this, action);
                }

                public void setAsContext() {
                    ScalaClassLoader$class.setAsContext(this);
                }

                public <T> Option<Class<T>> tryToLoadClass(String path) {
                    return ScalaClassLoader$class.tryToLoadClass(this, path);
                }

                public <T> Option<Class<T>> tryToInitializeClass(String path) {
                    return ScalaClassLoader$class.tryToInitializeClass(this, path);
                }

                public Object create(String path) {
                    return ScalaClassLoader$class.create(this, path);
                }

                public byte[] classBytes(String className) {
                    return ScalaClassLoader$class.classBytes(this, className);
                }

                public InputStream classAsStream(String className) {
                    return ScalaClassLoader$class.classAsStream(this, className);
                }

                public void run(String objectName, Seq<String> arguments) {
                    ScalaClassLoader$class.run(this, objectName, arguments);
                }
                {
                    ScalaClassLoader$class.$init$(this);
                }
            };
        }
        return scalaClassLoader;
    }

    public ScalaClassLoader contextLoader() {
        return this.apply(Thread.currentThread().getContextClassLoader());
    }

    public ScalaClassLoader appLoader() {
        return this.apply(ClassLoader.getSystemClassLoader());
    }

    public void setContext(ClassLoader cl) {
        Thread.currentThread().setContextClassLoader(cl);
    }

    /*
     * WARNING - void declaration
     */
    public <T> T savingContextLoader(Function0<T> body2) {
        T t;
        ScalaClassLoader saved = this.contextLoader();
        try {
            t = body2.apply();
            this.setContext((ClassLoader)((Object)saved));
        }
        catch (Throwable throwable) {
            void var2_2;
            this.setContext((ClassLoader)var2_2);
            throw throwable;
        }
        return t;
    }

    public ScalaClassLoader.URLClassLoader fromURLs(Seq<URL> urls, ClassLoader parent) {
        return new ScalaClassLoader.URLClassLoader(urls, parent);
    }

    public ClassLoader fromURLs$default$2() {
        return null;
    }

    public boolean classExists(Seq<URL> urls, String name) {
        return this.fromURLs(urls, this.fromURLs$default$2()).tryToLoadClass(name).isDefined();
    }

    public Option<URL> originOfClass(Class<?> x) {
        Option option;
        Option<CodeSource> option2 = Option$.MODULE$.apply(x.getProtectionDomain().getCodeSource());
        if (!option2.isEmpty()) {
            CodeSource codeSource = option2.get();
            option = Option$.MODULE$.apply(codeSource.getLocation());
        } else {
            option = None$.MODULE$;
        }
        return option;
    }

    private ScalaClassLoader$() {
        MODULE$ = this;
    }
}

