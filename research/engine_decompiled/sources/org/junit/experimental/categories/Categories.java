/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.categories;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.experimental.categories.Category;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Categories
extends Suite {
    public Categories(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
        try {
            this.filter(new CategoryFilter(this.getIncludedCategory(klass), this.getExcludedCategory(klass)));
        }
        catch (NoTestsRemainException e) {
            throw new InitializationError(e);
        }
        this.assertNoCategorizedDescendentsOfUncategorizeableParents(this.getDescription());
    }

    private Class<?> getIncludedCategory(Class<?> klass) {
        IncludeCategory annotation = klass.getAnnotation(IncludeCategory.class);
        return annotation == null ? null : annotation.value();
    }

    private Class<?> getExcludedCategory(Class<?> klass) {
        ExcludeCategory annotation = klass.getAnnotation(ExcludeCategory.class);
        return annotation == null ? null : annotation.value();
    }

    private void assertNoCategorizedDescendentsOfUncategorizeableParents(Description description) throws InitializationError {
        if (!Categories.canHaveCategorizedChildren(description)) {
            this.assertNoDescendantsHaveCategoryAnnotations(description);
        }
        for (Description each : description.getChildren()) {
            this.assertNoCategorizedDescendentsOfUncategorizeableParents(each);
        }
    }

    private void assertNoDescendantsHaveCategoryAnnotations(Description description) throws InitializationError {
        for (Description each : description.getChildren()) {
            if (each.getAnnotation(Category.class) != null) {
                throw new InitializationError("Category annotations on Parameterized classes are not supported on individual methods.");
            }
            this.assertNoDescendantsHaveCategoryAnnotations(each);
        }
    }

    private static boolean canHaveCategorizedChildren(Description description) {
        for (Description each : description.getChildren()) {
            if (each.getTestClass() != null) continue;
            return false;
        }
        return true;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class CategoryFilter
    extends Filter {
        private final Class<?> fIncluded;
        private final Class<?> fExcluded;

        public static CategoryFilter include(Class<?> categoryType) {
            return new CategoryFilter(categoryType, null);
        }

        public CategoryFilter(Class<?> includedCategory, Class<?> excludedCategory) {
            this.fIncluded = includedCategory;
            this.fExcluded = excludedCategory;
        }

        @Override
        public String describe() {
            return "category " + this.fIncluded;
        }

        @Override
        public boolean shouldRun(Description description) {
            if (this.hasCorrectCategoryAnnotation(description)) {
                return true;
            }
            for (Description each : description.getChildren()) {
                if (!this.shouldRun(each)) continue;
                return true;
            }
            return false;
        }

        private boolean hasCorrectCategoryAnnotation(Description description) {
            List<Class<?>> categories = this.categories(description);
            if (categories.isEmpty()) {
                return this.fIncluded == null;
            }
            for (Class<?> each : categories) {
                if (this.fExcluded == null || !this.fExcluded.isAssignableFrom(each)) continue;
                return false;
            }
            for (Class<?> each : categories) {
                if (this.fIncluded != null && !this.fIncluded.isAssignableFrom(each)) continue;
                return true;
            }
            return false;
        }

        private List<Class<?>> categories(Description description) {
            ArrayList categories = new ArrayList();
            categories.addAll(Arrays.asList(this.directCategories(description)));
            categories.addAll(Arrays.asList(this.directCategories(this.parentDescription(description))));
            return categories;
        }

        private Description parentDescription(Description description) {
            Class<?> testClass = description.getTestClass();
            if (testClass == null) {
                return null;
            }
            return Description.createSuiteDescription(testClass);
        }

        private Class<?>[] directCategories(Description description) {
            if (description == null) {
                return new Class[0];
            }
            Category annotation = description.getAnnotation(Category.class);
            if (annotation == null) {
                return new Class[0];
            }
            return annotation.value();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface ExcludeCategory {
        public Class<?> value();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface IncludeCategory {
        public Class<?> value();
    }
}

