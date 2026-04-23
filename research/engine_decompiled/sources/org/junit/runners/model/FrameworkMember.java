/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners.model;

import java.lang.annotation.Annotation;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
abstract class FrameworkMember<T extends FrameworkMember<T>> {
    FrameworkMember() {
    }

    abstract Annotation[] getAnnotations();

    abstract boolean isShadowedBy(T var1);

    boolean isShadowedBy(List<T> members) {
        for (FrameworkMember each : members) {
            if (!this.isShadowedBy(each)) continue;
            return true;
        }
        return false;
    }
}

