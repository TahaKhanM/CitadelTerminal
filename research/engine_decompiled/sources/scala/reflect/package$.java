/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import java.lang.reflect.AccessibleObject;
import scala.reflect.ClassManifestFactory$;
import scala.reflect.ClassTag;
import scala.reflect.ManifestFactory$;

public final class package$ {
    public static final package$ MODULE$;
    private final ClassManifestFactory$ ClassManifest;
    private final ManifestFactory$ Manifest;

    static {
        new package$();
    }

    public ClassManifestFactory$ ClassManifest() {
        return this.ClassManifest;
    }

    public ManifestFactory$ Manifest() {
        return this.Manifest;
    }

    public <T> ClassTag<T> classTag(ClassTag<T> ctag) {
        return ctag;
    }

    public <T extends AccessibleObject> T ensureAccessible(T m) {
        if (!m.isAccessible()) {
            try {
                m.setAccessible(true);
            }
            catch (SecurityException securityException) {}
        }
        return m;
    }

    private package$() {
        MODULE$ = this;
        this.ClassManifest = ClassManifestFactory$.MODULE$;
        this.Manifest = ManifestFactory$.MODULE$;
    }
}

