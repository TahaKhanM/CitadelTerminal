/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.runners.model.FrameworkMember;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FrameworkField
extends FrameworkMember<FrameworkField> {
    private final Field fField;

    FrameworkField(Field field2) {
        this.fField = field2;
    }

    public String getName() {
        return this.getField().getName();
    }

    @Override
    public Annotation[] getAnnotations() {
        return this.fField.getAnnotations();
    }

    public boolean isPublic() {
        int modifiers = this.fField.getModifiers();
        return Modifier.isPublic(modifiers);
    }

    @Override
    public boolean isShadowedBy(FrameworkField otherMember) {
        return otherMember.getName().equals(this.getName());
    }

    public boolean isStatic() {
        int modifiers = this.fField.getModifiers();
        return Modifier.isStatic(modifiers);
    }

    public Field getField() {
        return this.fField;
    }

    public Class<?> getType() {
        return this.fField.getType();
    }

    public Object get(Object target) throws IllegalArgumentException, IllegalAccessException {
        return this.fField.get(target);
    }
}

