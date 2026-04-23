/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.runners.rules;

import java.lang.annotation.Annotation;
import java.util.List;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.TestClass;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum RuleFieldValidator {
    CLASS_RULE_VALIDATOR(ClassRule.class, true),
    RULE_VALIDATOR(Rule.class, false);

    private final Class<? extends Annotation> fAnnotation;
    private final boolean fOnlyStaticFields;

    private RuleFieldValidator(Class<? extends Annotation> annotation, boolean onlyStaticFields) {
        this.fAnnotation = annotation;
        this.fOnlyStaticFields = onlyStaticFields;
    }

    public void validate(TestClass target, List<Throwable> errors) {
        List<FrameworkField> fields = target.getAnnotatedFields(this.fAnnotation);
        for (FrameworkField each : fields) {
            this.validateField(each, errors);
        }
    }

    private void validateField(FrameworkField field2, List<Throwable> errors) {
        this.optionallyValidateStatic(field2, errors);
        this.validatePublic(field2, errors);
        this.validateTestRuleOrMethodRule(field2, errors);
    }

    private void optionallyValidateStatic(FrameworkField field2, List<Throwable> errors) {
        if (this.fOnlyStaticFields && !field2.isStatic()) {
            this.addError(errors, field2, "must be static.");
        }
    }

    private void validatePublic(FrameworkField field2, List<Throwable> errors) {
        if (!field2.isPublic()) {
            this.addError(errors, field2, "must be public.");
        }
    }

    private void validateTestRuleOrMethodRule(FrameworkField field2, List<Throwable> errors) {
        if (!this.isMethodRule(field2) && !this.isTestRule(field2)) {
            this.addError(errors, field2, "must implement MethodRule or TestRule.");
        }
    }

    private boolean isTestRule(FrameworkField target) {
        return TestRule.class.isAssignableFrom(target.getType());
    }

    private boolean isMethodRule(FrameworkField target) {
        return MethodRule.class.isAssignableFrom(target.getType());
    }

    private void addError(List<Throwable> errors, FrameworkField field2, String suffix) {
        String message = "The @" + this.fAnnotation.getSimpleName() + " '" + field2.getName() + "' " + suffix;
        errors.add(new Exception(message));
    }
}

