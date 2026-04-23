/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.rules.RuleFieldValidator;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.internal.runners.statements.Fail;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.rules.MethodRule;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BlockJUnit4ClassRunner
extends ParentRunner<FrameworkMethod> {
    public BlockJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        Description description = this.describeChild(method);
        if (method.getAnnotation(Ignore.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
            this.runLeaf(this.methodBlock(method), description, notifier);
        }
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        return Description.createTestDescription(this.getTestClass().getJavaClass(), this.testName(method), method.getAnnotations());
    }

    @Override
    protected List<FrameworkMethod> getChildren() {
        return this.computeTestMethods();
    }

    protected List<FrameworkMethod> computeTestMethods() {
        return this.getTestClass().getAnnotatedMethods(Test.class);
    }

    @Override
    protected void collectInitializationErrors(List<Throwable> errors) {
        super.collectInitializationErrors(errors);
        this.validateNoNonStaticInnerClass(errors);
        this.validateConstructor(errors);
        this.validateInstanceMethods(errors);
        this.validateFields(errors);
    }

    protected void validateNoNonStaticInnerClass(List<Throwable> errors) {
        if (this.getTestClass().isANonStaticInnerClass()) {
            String gripe = "The inner class " + this.getTestClass().getName() + " is not static.";
            errors.add(new Exception(gripe));
        }
    }

    protected void validateConstructor(List<Throwable> errors) {
        this.validateOnlyOneConstructor(errors);
        this.validateZeroArgConstructor(errors);
    }

    protected void validateOnlyOneConstructor(List<Throwable> errors) {
        if (!this.hasOneConstructor()) {
            String gripe = "Test class should have exactly one public constructor";
            errors.add(new Exception(gripe));
        }
    }

    protected void validateZeroArgConstructor(List<Throwable> errors) {
        if (!this.getTestClass().isANonStaticInnerClass() && this.hasOneConstructor() && this.getTestClass().getOnlyConstructor().getParameterTypes().length != 0) {
            String gripe = "Test class should have exactly one public zero-argument constructor";
            errors.add(new Exception(gripe));
        }
    }

    private boolean hasOneConstructor() {
        return this.getTestClass().getJavaClass().getConstructors().length == 1;
    }

    @Deprecated
    protected void validateInstanceMethods(List<Throwable> errors) {
        this.validatePublicVoidNoArgMethods(After.class, false, errors);
        this.validatePublicVoidNoArgMethods(Before.class, false, errors);
        this.validateTestMethods(errors);
        if (this.computeTestMethods().size() == 0) {
            errors.add(new Exception("No runnable methods"));
        }
    }

    private void validateFields(List<Throwable> errors) {
        RuleFieldValidator.RULE_VALIDATOR.validate(this.getTestClass(), errors);
    }

    protected void validateTestMethods(List<Throwable> errors) {
        this.validatePublicVoidNoArgMethods(Test.class, false, errors);
    }

    protected Object createTest() throws Exception {
        return this.getTestClass().getOnlyConstructor().newInstance(new Object[0]);
    }

    protected String testName(FrameworkMethod method) {
        return method.getName();
    }

    protected Statement methodBlock(FrameworkMethod method) {
        Object test;
        try {
            test = new ReflectiveCallable(){

                protected Object runReflectiveCall() throws Throwable {
                    return BlockJUnit4ClassRunner.this.createTest();
                }
            }.run();
        }
        catch (Throwable e) {
            return new Fail(e);
        }
        Statement statement = this.methodInvoker(method, test);
        statement = this.possiblyExpectingExceptions(method, test, statement);
        statement = this.withPotentialTimeout(method, test, statement);
        statement = this.withBefores(method, test, statement);
        statement = this.withAfters(method, test, statement);
        statement = this.withRules(method, test, statement);
        return statement;
    }

    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return new InvokeMethod(method, test);
    }

    @Deprecated
    protected Statement possiblyExpectingExceptions(FrameworkMethod method, Object test, Statement next2) {
        Test annotation = method.getAnnotation(Test.class);
        return this.expectsException(annotation) ? new ExpectException(next2, this.getExpectedException(annotation)) : next2;
    }

    @Deprecated
    protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next2) {
        long timeout = this.getTimeout(method.getAnnotation(Test.class));
        return timeout > 0L ? new FailOnTimeout(next2, timeout) : next2;
    }

    @Deprecated
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        List<FrameworkMethod> befores = this.getTestClass().getAnnotatedMethods(Before.class);
        return befores.isEmpty() ? statement : new RunBefores(statement, befores, target);
    }

    @Deprecated
    protected Statement withAfters(FrameworkMethod method, Object target, Statement statement) {
        List<FrameworkMethod> afters = this.getTestClass().getAnnotatedMethods(After.class);
        return afters.isEmpty() ? statement : new RunAfters(statement, afters, target);
    }

    private Statement withRules(FrameworkMethod method, Object target, Statement statement) {
        Statement result2 = statement;
        result2 = this.withMethodRules(method, target, result2);
        result2 = this.withTestRules(method, target, result2);
        return result2;
    }

    private Statement withMethodRules(FrameworkMethod method, Object target, Statement result2) {
        List<TestRule> testRules = this.getTestRules(target);
        for (MethodRule each : this.getMethodRules(target)) {
            if (testRules.contains(each)) continue;
            result2 = each.apply(result2, method, target);
        }
        return result2;
    }

    private List<MethodRule> getMethodRules(Object target) {
        return this.rules(target);
    }

    @Deprecated
    protected List<MethodRule> rules(Object target) {
        return this.getTestClass().getAnnotatedFieldValues(target, Rule.class, MethodRule.class);
    }

    private Statement withTestRules(FrameworkMethod method, Object target, Statement statement) {
        List<TestRule> testRules = this.getTestRules(target);
        return testRules.isEmpty() ? statement : new RunRules(statement, testRules, this.describeChild(method));
    }

    protected List<TestRule> getTestRules(Object target) {
        return this.getTestClass().getAnnotatedFieldValues(target, Rule.class, TestRule.class);
    }

    private Class<? extends Throwable> getExpectedException(Test annotation) {
        if (annotation == null || annotation.expected() == Test.None.class) {
            return null;
        }
        return annotation.expected();
    }

    private boolean expectsException(Test annotation) {
        return this.getExpectedException(annotation) != null;
    }

    private long getTimeout(Test annotation) {
        if (annotation == null) {
            return 0L;
        }
        return annotation.timeout();
    }
}

