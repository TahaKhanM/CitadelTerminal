/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMember;
import org.junit.runners.model.FrameworkMethod;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TestClass {
    private final Class<?> fClass;
    private Map<Class<?>, List<FrameworkMethod>> fMethodsForAnnotations = new HashMap();
    private Map<Class<?>, List<FrameworkField>> fFieldsForAnnotations = new HashMap();

    public TestClass(Class<?> klass) {
        this.fClass = klass;
        if (klass != null && klass.getConstructors().length > 1) {
            throw new IllegalArgumentException("Test class can only have one constructor");
        }
        for (Class<?> eachClass : this.getSuperClasses(this.fClass)) {
            for (Method method : eachClass.getDeclaredMethods()) {
                this.addToAnnotationLists(new FrameworkMethod(method), this.fMethodsForAnnotations);
            }
            for (AccessibleObject accessibleObject : eachClass.getDeclaredFields()) {
                this.addToAnnotationLists(new FrameworkField((Field)accessibleObject), this.fFieldsForAnnotations);
            }
        }
    }

    private <T extends FrameworkMember<T>> void addToAnnotationLists(T member, Map<Class<?>, List<T>> map2) {
        for (Annotation each : ((FrameworkMember)member).getAnnotations()) {
            Class<? extends Annotation> type = each.annotationType();
            List<T> members = this.getAnnotatedMembers(map2, type);
            if (((FrameworkMember)member).isShadowedBy(members)) {
                return;
            }
            if (this.runsTopToBottom(type)) {
                members.add(0, member);
                continue;
            }
            members.add(member);
        }
    }

    public List<FrameworkMethod> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
        return this.getAnnotatedMembers(this.fMethodsForAnnotations, annotationClass);
    }

    public List<FrameworkField> getAnnotatedFields(Class<? extends Annotation> annotationClass) {
        return this.getAnnotatedMembers(this.fFieldsForAnnotations, annotationClass);
    }

    private <T> List<T> getAnnotatedMembers(Map<Class<?>, List<T>> map2, Class<? extends Annotation> type) {
        if (!map2.containsKey(type)) {
            map2.put(type, new ArrayList());
        }
        return map2.get(type);
    }

    private boolean runsTopToBottom(Class<? extends Annotation> annotation) {
        return annotation.equals(Before.class) || annotation.equals(BeforeClass.class);
    }

    private List<Class<?>> getSuperClasses(Class<?> testClass) {
        ArrayList results = new ArrayList();
        for (Class<?> current = testClass; current != null; current = current.getSuperclass()) {
            results.add(current);
        }
        return results;
    }

    public Class<?> getJavaClass() {
        return this.fClass;
    }

    public String getName() {
        if (this.fClass == null) {
            return "null";
        }
        return this.fClass.getName();
    }

    public Constructor<?> getOnlyConstructor() {
        Constructor<?>[] constructors = this.fClass.getConstructors();
        Assert.assertEquals(1L, constructors.length);
        return constructors[0];
    }

    public Annotation[] getAnnotations() {
        if (this.fClass == null) {
            return new Annotation[0];
        }
        return this.fClass.getAnnotations();
    }

    public <T> List<T> getAnnotatedFieldValues(Object test, Class<? extends Annotation> annotationClass, Class<T> valueClass) {
        ArrayList<T> results = new ArrayList<T>();
        for (FrameworkField each : this.getAnnotatedFields(annotationClass)) {
            try {
                Object fieldValue = each.get(test);
                if (!valueClass.isInstance(fieldValue)) continue;
                results.add(valueClass.cast(fieldValue));
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("How did getFields return a field we couldn't access?", e);
            }
        }
        return results;
    }

    public boolean isANonStaticInnerClass() {
        return this.fClass.isMemberClass() && !Modifier.isStatic(this.fClass.getModifiers());
    }
}

