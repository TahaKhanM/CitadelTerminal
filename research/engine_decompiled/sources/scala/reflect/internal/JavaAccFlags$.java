/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import scala.reflect.internal.ClassfileConstants$FlagTranslation$;
import scala.reflect.internal.JavaAccFlags;
import scala.runtime.BoxesRunTime;

public final class JavaAccFlags$ {
    public static final JavaAccFlags$ MODULE$;
    private final int Unknown;
    private final int Class;
    private final int Field;
    private final int Method;
    private final int Constructor;

    static {
        new JavaAccFlags$();
    }

    private int Unknown() {
        return this.Unknown;
    }

    private int Class() {
        return this.Class;
    }

    private int Field() {
        return this.Field;
    }

    private int Method() {
        return this.Method;
    }

    private int Constructor() {
        return this.Constructor;
    }

    private int create(int flagCarrier, int access_flags) {
        return flagCarrier << 16 | access_flags & 0xFFFF;
    }

    public int classFlags(int flags) {
        return this.create(this.Class(), flags);
    }

    public int methodFlags(int flags) {
        return this.create(this.Method(), flags);
    }

    public int fieldFlags(int flags) {
        return this.create(this.Field(), flags);
    }

    public int constructorFlags(int flags) {
        return this.create(this.Constructor(), flags);
    }

    public int apply(int access_flags) {
        return this.create(this.Unknown(), access_flags);
    }

    public int apply(Class<?> clazz) {
        return this.classFlags(clazz.getModifiers());
    }

    public int apply(Member member) {
        int n;
        if (member instanceof Constructor) {
            Constructor constructor = (Constructor)member;
            n = this.constructorFlags(constructor.getModifiers());
        } else if (member instanceof Method) {
            Method method = (Method)member;
            n = this.methodFlags(method.getModifiers());
        } else if (member instanceof Field) {
            Field field2 = (Field)member;
            n = this.fieldFlags(field2.getModifiers());
        } else {
            n = this.apply(member.getModifiers());
        }
        return n;
    }

    public final boolean scala$reflect$internal$JavaAccFlags$$has$extension(int $this, int mask) {
        return (this.scala$reflect$internal$JavaAccFlags$$flags$extension($this) & mask) != 0;
    }

    public final int scala$reflect$internal$JavaAccFlags$$flagCarrierId$extension(int $this) {
        return $this >>> 16;
    }

    public final int scala$reflect$internal$JavaAccFlags$$flags$extension(int $this) {
        return $this & 0xFFFF;
    }

    public final boolean isAbstract$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 1024);
    }

    public final boolean isAnnotation$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 8192);
    }

    public final boolean isBridge$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 64);
    }

    public final boolean isEnum$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 16384);
    }

    public final boolean isFinal$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 16);
    }

    public final boolean isInterface$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 512);
    }

    public final boolean isNative$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 256);
    }

    public final boolean isPrivate$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 2);
    }

    public final boolean isProtected$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 4);
    }

    public final boolean isPublic$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 1);
    }

    public final boolean isStatic$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 8);
    }

    public final boolean isStrictFp$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 2048);
    }

    public final boolean isSuper$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 32);
    }

    public final boolean isSynchronized$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 32);
    }

    public final boolean isSynthetic$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 4096);
    }

    public final boolean isTransient$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 128);
    }

    public final boolean isVarargs$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 128);
    }

    public final boolean isVolatile$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 64);
    }

    public final boolean hasPackageAccessBoundary$extension(int $this) {
        return !this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 3);
    }

    public final boolean isPackageProtected$extension(int $this) {
        return !this.scala$reflect$internal$JavaAccFlags$$has$extension($this, 7);
    }

    public final int toJavaFlags$extension(int $this) {
        return this.scala$reflect$internal$JavaAccFlags$$flags$extension($this);
    }

    public final long toScalaFlags$extension(int $this) {
        int n = this.scala$reflect$internal$JavaAccFlags$$flagCarrierId$extension($this);
        boolean bl = this.Method() == n ? true : this.Constructor() == n;
        long l = bl ? ClassfileConstants$FlagTranslation$.MODULE$.methodFlags(this.scala$reflect$internal$JavaAccFlags$$flags$extension($this)) : (this.Class() == n ? ClassfileConstants$FlagTranslation$.MODULE$.classFlags(this.scala$reflect$internal$JavaAccFlags$$flags$extension($this)) : ClassfileConstants$FlagTranslation$.MODULE$.fieldFlags(this.scala$reflect$internal$JavaAccFlags$$flags$extension($this)));
        return l;
    }

    public final int hashCode$extension(int $this) {
        return ((Object)BoxesRunTime.boxToInteger($this)).hashCode();
    }

    public final boolean equals$extension(int $this, Object x$1) {
        int n;
        boolean bl = x$1 instanceof JavaAccFlags;
        return bl && $this == (n = ((JavaAccFlags)x$1).coded());
    }

    private JavaAccFlags$() {
        MODULE$ = this;
        this.Unknown = 0;
        this.Class = 1;
        this.Field = 2;
        this.Method = 3;
        this.Constructor = 4;
    }
}

