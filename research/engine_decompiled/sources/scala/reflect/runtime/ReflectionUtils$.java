/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.runtime;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URLClassLoader;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$any2stringadd$;
import scala.Serializable;
import scala.StringContext;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.ArrayOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.internal.Names;
import scala.reflect.internal.util.AbstractFileClassLoader;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.NoAbstractFile$;
import scala.runtime.EmptyMethodCache;
import scala.runtime.MethodCache;
import scala.runtime.ScalaRunTime$;
import scala.util.Properties$;

public final class ReflectionUtils$ {
    public static final ReflectionUtils$ MODULE$;
    private static Class[] reflParams$Cache1;
    private static volatile SoftReference reflPoly$Cache1;

    static {
        reflParams$Cache1 = new Class[0];
        reflPoly$Cache1 = new SoftReference<EmptyMethodCache>(new EmptyMethodCache());
        new ReflectionUtils$();
    }

    public static Method reflMethod$Method1(Class x$1) {
        Method method1;
        MethodCache methodCache1 = (MethodCache)reflPoly$Cache1.get();
        if (methodCache1 == null) {
            methodCache1 = new EmptyMethodCache();
            reflPoly$Cache1 = new SoftReference<MethodCache>(methodCache1);
        }
        if ((method1 = methodCache1.find(x$1)) != null) {
            return method1;
        }
        method1 = ScalaRunTime$.MODULE$.ensureAccessible(x$1.getMethod("root", reflParams$Cache1));
        reflPoly$Cache1 = new SoftReference<MethodCache>(methodCache1.add(x$1, method1));
        return method1;
    }

    public Throwable unwrapThrowable(Throwable x) {
        boolean bl;
        while ((bl = x instanceof InvocationTargetException ? true : (x instanceof ExceptionInInitializerError ? true : (x instanceof UndeclaredThrowableException ? true : (x instanceof ClassNotFoundException ? true : x instanceof NoClassDefFoundError)))) && x.getCause() != null) {
            x = x.getCause();
        }
        return x;
    }

    public <T> PartialFunction<Throwable, T> unwrapHandler(PartialFunction<Throwable, T> pf) {
        return new Serializable(pf){
            public static final long serialVersionUID = 0L;
            public final PartialFunction pf$1;

            public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                Object object = this.pf$1.isDefinedAt(ReflectionUtils$.MODULE$.unwrapThrowable(x1)) ? this.pf$1.apply(ReflectionUtils$.MODULE$.unwrapThrowable(x1)) : function1.apply(x1);
                return object;
            }

            public final boolean isDefinedAt(Throwable x1) {
                boolean bl = this.pf$1.isDefinedAt(ReflectionUtils$.MODULE$.unwrapThrowable(x1));
                return bl;
            }
            {
                this.pf$1 = pf$1;
            }
        };
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String show(ClassLoader cl) {
        if (cl == null) {
            if (cl != null) throw new MatchError(cl);
            Predef$ predef$ = Predef$.MODULE$;
            return new StringOps("primordial classloader with boot classpath [%s]").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.inferClasspath$1(cl)}));
        }
        Predef$ predef$ = Predef$.MODULE$;
        return new StringOps("%s of type %s with classpath [%s] and parent being %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{cl, cl.getClass(), this.inferClasspath$1(cl), this.show(cl.getParent())}));
    }

    public Object staticSingletonInstance(ClassLoader cl, String className) {
        String name = className.endsWith("$") ? className : new StringBuilder().append((Object)className).append((Object)"$").toString();
        Class<?> clazz = Class.forName(name, true, cl);
        return this.staticSingletonInstance(clazz);
    }

    public Object staticSingletonInstance(Class<?> clazz) {
        return clazz.getField("MODULE$").get(null);
    }

    /*
     * WARNING - void declaration
     */
    public Object innerSingletonInstance(Object outer, String className) {
        void var5_3;
        String accessorName = className.endsWith("$") ? className.substring(0, className.length() - 1) : className;
        Option option = this.scala$reflect$runtime$ReflectionUtils$$singletonAccessor$1(outer.getClass(), accessorName);
        if (!option.isEmpty()) {
            Method accessor = (Method)option.get();
            accessor.setAccessible(true);
            return accessor.invoke(outer, new Object[0]);
        }
        throw new NoSuchMethodException(new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{outer.getClass().getName(), var5_3})));
    }

    public boolean isTraitImplementation(String fileName) {
        return fileName.endsWith("$class.class");
    }

    public boolean scalacShouldntLoadClassfile(String fileName) {
        return this.isTraitImplementation(fileName);
    }

    public boolean scalacShouldntLoadClass(Names.Name name) {
        return this.scalacShouldntLoadClassfile(Predef$any2stringadd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(name), ".class"));
    }

    public AbstractFile associatedFile(Class<?> clazz) {
        return NoAbstractFile$.MODULE$;
    }

    private final boolean isAbstractFileClassLoader$1(Class clazz) {
        while (clazz != null) {
            Class clazz2 = clazz;
            if (clazz2 != null && clazz2.equals(AbstractFileClassLoader.class)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final String inferClasspath$1(ClassLoader cl) {
        if (cl instanceof URLClassLoader) {
            URLClassLoader uRLClassLoader = (URLClassLoader)cl;
            Object[] objectArray = uRLClassLoader.getURLs();
            Predef$ predef$ = Predef$.MODULE$;
            return new ArrayOps.ofRef<Object>(objectArray).mkString(",");
        }
        if (cl != null && this.isAbstractFileClassLoader$1(cl.getClass())) {
            try {
                return ((AbstractFile)ReflectionUtils$.reflMethod$Method1(cl.getClass()).invoke((Object)cl, new Object[0])).canonicalPath();
            }
            catch (InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getCause();
            }
        }
        if (cl != null) {
            return "<unknown>";
        }
        Serializable loadBootCp = new Serializable(){
            public static final long serialVersionUID = 0L;

            public final Option<String> apply(String flavor) {
                return Properties$.MODULE$.propOrNone(new StringBuilder().append((Object)flavor).append((Object)".boot.class.path").toString());
            }
        };
        String string3 = "sun";
        Option option = Properties$.MODULE$.propOrNone(new StringBuilder().append((Object)string3).append((Object)".boot.class.path").toString());
        Option option2 = !option.isEmpty() ? option : (Option)loadBootCp.apply("java");
        if (option2.isEmpty()) return "<unknown>";
        String string2 = option2.get();
        return string2;
    }

    public final Option scala$reflect$runtime$ReflectionUtils$$singletonAccessor$1(Class clazz, String accessorName$1) {
        Option option;
        if (clazz == null) {
            option = None$.MODULE$;
        } else {
            Object[] objectArray = clazz.getDeclaredMethods();
            Predef$ predef$ = Predef$.MODULE$;
            Option<Method> declaredAccessor = new ArrayOps.ofRef<Object>(objectArray).find(new Serializable(accessorName$1){
                public static final long serialVersionUID = 0L;
                private final String accessorName$1;

                public final boolean apply(Method x$1) {
                    String string2 = x$1.getName();
                    String string3 = this.accessorName$1;
                    return !(string2 != null ? !string2.equals(string3) : string3 != null);
                }
                {
                    this.accessorName$1 = accessorName$1;
                }
            });
            option = !declaredAccessor.isEmpty() ? declaredAccessor : this.scala$reflect$runtime$ReflectionUtils$$singletonAccessor$1(clazz.getSuperclass(), accessorName$1);
        }
        return option;
    }

    private ReflectionUtils$() {
        MODULE$ = this;
    }
}

