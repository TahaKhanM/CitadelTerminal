/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.internal.util.ScalaClassLoader;
import scala.reflect.internal.util.ScalaClassLoader$;
import scala.reflect.io.Streamable;
import scala.reflect.io.Streamable$;
import scala.reflect.io.Streamable$Bytes$class;
import scala.reflect.runtime.ReflectionUtils$;
import scala.runtime.Nothing$;
import scala.util.control.Exception$;

public abstract class ScalaClassLoader$class {
    /*
     * WARNING - void declaration
     */
    public static Object asContext(ScalaClassLoader $this, Function0 action) {
        Object r;
        ScalaClassLoader saved = ScalaClassLoader$.MODULE$.contextLoader();
        try {
            ScalaClassLoader$.MODULE$.setContext((ClassLoader)((Object)$this));
            r = action.apply();
            ScalaClassLoader$.MODULE$.setContext((ClassLoader)((Object)saved));
        }
        catch (Throwable throwable) {
            void var2_2;
            ScalaClassLoader$.MODULE$.setContext((ClassLoader)var2_2);
            throw throwable;
        }
        return r;
    }

    public static void setAsContext(ScalaClassLoader $this) {
        ScalaClassLoader$.MODULE$.setContext((ClassLoader)((Object)$this));
    }

    public static Option tryToLoadClass(ScalaClassLoader $this, String path) {
        return ScalaClassLoader$class.tryClass($this, path, false);
    }

    public static Option tryToInitializeClass(ScalaClassLoader $this, String path) {
        return ScalaClassLoader$class.tryClass($this, path, true);
    }

    private static Option tryClass(ScalaClassLoader $this, String path, boolean initialize) {
        return Exception$.MODULE$.catching((Seq<Class<?>>)Predef$.MODULE$.wrapRefArray((Object[])new Class[]{ClassNotFoundException.class, SecurityException.class})).opt(new Serializable($this, path, initialize){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ScalaClassLoader $outer;
            private final String path$1;
            private final boolean initialize$1;

            public final Class<Object> apply() {
                return Class.forName(this.path$1, this.initialize$1, (ClassLoader)((Object)this.$outer));
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.path$1 = path$1;
                this.initialize$1 = initialize$1;
            }
        });
    }

    public static Object create(ScalaClassLoader $this, String path) {
        Option option = $this.tryToInitializeClass(path);
        Option option2 = !option.isEmpty() ? new Some(option.get().newInstance()) : None$.MODULE$;
        Predef$.less.colon.less less2 = Predef$.MODULE$.$conforms();
        Option option3 = option2;
        Serializable serializable = new Serializable(option3, less2){
            public static final long serialVersionUID = 0L;
            public final Predef$.less.colon.less ev$1;

            public final A1 apply() {
                return (A1)this.ev$1.apply(null);
            }
            {
                this.ev$1 = ev$1;
            }
        };
        return !option3.isEmpty() ? option3.get() : serializable.apply();
    }

    public static byte[] classBytes(ScalaClassLoader $this, String className) {
        byte[] byArray;
        InputStream inputStream = $this.classAsStream(className);
        if (inputStream == null) {
            byArray = (byte[])Array$.MODULE$.apply(Nil$.MODULE$, ClassTag$.MODULE$.Byte());
        } else {
            Serializable serializable = new Serializable($this, inputStream){
                public static final long serialVersionUID = 0L;
                private final InputStream x1$1;

                public final InputStream apply() {
                    return this.x1$1;
                }
                {
                    this.x1$1 = x1$1;
                }
            };
            Streamable$ streamable$ = Streamable$.MODULE$;
            byArray = Streamable$Bytes$class.toByteArray(new Streamable.Bytes((Function0)((Object)serializable)){
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
            });
        }
        return byArray;
    }

    public static InputStream classAsStream(ScalaClassLoader $this, String className) {
        return ((ClassLoader)((Object)$this)).getResourceAsStream(className.endsWith(".class") ? className : new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"", ".class"})).s(Predef$.MODULE$.genericWrapArray(new Object[]{className.replace('.', '/')})));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void run(ScalaClassLoader $this, String objectName, Seq arguments) {
        Serializable serializable = new Serializable($this, objectName){
            public static final long serialVersionUID = 0L;
            public final String objectName$1;

            public final Nothing$ apply() {
                throw new ClassNotFoundException(this.objectName$1);
            }
            {
                this.objectName$1 = objectName$1;
            }
        };
        Option option = $this.tryToInitializeClass(objectName);
        if (option.isEmpty()) throw new ClassNotFoundException(serializable.objectName$1);
        Class clsToRun = option.get();
        Method method = clsToRun.getMethod("main", String[].class);
        if (!Modifier.isStatic(method.getModifiers())) throw new NoSuchMethodException(new StringBuilder().append((Object)objectName).append((Object)".main is not static").toString());
        try {
            $this.asContext(new Serializable($this, method, arguments){
                public static final long serialVersionUID = 0L;
                private final Method method$1;
                private final Seq arguments$1;

                public final Object apply() {
                    return this.method$1.invoke(null, this.arguments$1.toArray(ClassTag$.MODULE$.apply(String.class)));
                }
                {
                    this.method$1 = method$1;
                    this.arguments$1 = arguments$1;
                }
            });
            return;
        }
        catch (Throwable throwable22) {
            Serializable serializable2 = new Serializable($this){
                public static final long serialVersionUID = 0L;

                public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                    throw x1;
                }

                public final boolean isDefinedAt(Throwable x1) {
                    return true;
                }
            };
            ReflectionUtils$ reflectionUtils$ = ReflectionUtils$.MODULE$;
            Serializable catchExpr$1 = new Serializable((PartialFunction)((Object)serializable2)){
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
            Throwable throwable3 = throwable22;
            boolean bl = serializable2.isDefinedAt(ReflectionUtils$.MODULE$.unwrapThrowable(throwable3));
            if (!bl) throw throwable22;
            catchExpr$1.apply(throwable22);
        }
    }

    public static void $init$(ScalaClassLoader $this) {
    }
}

