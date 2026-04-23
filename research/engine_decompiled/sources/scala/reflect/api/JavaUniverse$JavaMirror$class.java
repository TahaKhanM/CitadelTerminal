/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.Predef$;
import scala.StringContext;
import scala.reflect.api.JavaUniverse;
import scala.reflect.runtime.ReflectionUtils$;

public abstract class JavaUniverse$JavaMirror$class {
    public static String toString(JavaUniverse.JavaMirror $this) {
        return new StringContext(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"JavaMirror with ", ""})).s(Predef$.MODULE$.genericWrapArray(new Object[]{ReflectionUtils$.MODULE$.show($this.classLoader())}));
    }

    public static void $init$(JavaUniverse.JavaMirror $this) {
    }
}

