/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runner;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Description
implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Description EMPTY = new Description("No Tests", new Annotation[0]);
    public static final Description TEST_MECHANISM = new Description("Test mechanism", new Annotation[0]);
    private final ArrayList<Description> fChildren = new ArrayList();
    private final String fDisplayName;
    private final Annotation[] fAnnotations;

    public static Description createSuiteDescription(String name, Annotation ... annotations) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("name must have non-zero length");
        }
        return new Description(name, annotations);
    }

    public static Description createTestDescription(Class<?> clazz, String name, Annotation ... annotations) {
        return new Description(String.format("%s(%s)", name, clazz.getName()), annotations);
    }

    public static Description createTestDescription(Class<?> clazz, String name) {
        return Description.createTestDescription(clazz, name, new Annotation[0]);
    }

    public static Description createSuiteDescription(Class<?> testClass) {
        return new Description(testClass.getName(), testClass.getAnnotations());
    }

    private Description(String displayName, Annotation ... annotations) {
        this.fDisplayName = displayName;
        this.fAnnotations = annotations;
    }

    public String getDisplayName() {
        return this.fDisplayName;
    }

    public void addChild(Description description) {
        this.getChildren().add(description);
    }

    public ArrayList<Description> getChildren() {
        return this.fChildren;
    }

    public boolean isSuite() {
        return !this.isTest();
    }

    public boolean isTest() {
        return this.getChildren().isEmpty();
    }

    public int testCount() {
        if (this.isTest()) {
            return 1;
        }
        int result2 = 0;
        for (Description child : this.getChildren()) {
            result2 += child.testCount();
        }
        return result2;
    }

    public int hashCode() {
        return this.getDisplayName().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Description)) {
            return false;
        }
        Description d = (Description)obj;
        return this.getDisplayName().equals(d.getDisplayName());
    }

    public String toString() {
        return this.getDisplayName();
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public Description childlessCopy() {
        return new Description(this.fDisplayName, this.fAnnotations);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        for (Annotation each : this.fAnnotations) {
            if (!each.annotationType().equals(annotationType)) continue;
            return (T)((Annotation)annotationType.cast(each));
        }
        return null;
    }

    public Collection<Annotation> getAnnotations() {
        return Arrays.asList(this.fAnnotations);
    }

    public Class<?> getTestClass() {
        String name = this.getClassName();
        if (name == null) {
            return null;
        }
        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    public String getClassName() {
        Matcher matcher = this.methodStringMatcher();
        return matcher.matches() ? matcher.group(2) : this.toString();
    }

    public String getMethodName() {
        return this.parseMethod();
    }

    private String parseMethod() {
        Matcher matcher = this.methodStringMatcher();
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private Matcher methodStringMatcher() {
        return Pattern.compile("(.*)\\((.*)\\)").matcher(this.toString());
    }
}

