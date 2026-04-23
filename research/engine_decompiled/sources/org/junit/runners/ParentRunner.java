/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.rules.RuleFieldValidator;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sortable;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ParentRunner<T>
extends Runner
implements Filterable,
Sortable {
    private final TestClass fTestClass;
    private Sorter fSorter = Sorter.NULL;
    private List<T> fFilteredChildren = null;
    private RunnerScheduler fScheduler = new RunnerScheduler(){

        public void schedule(Runnable childStatement) {
            childStatement.run();
        }

        public void finished() {
        }
    };

    protected ParentRunner(Class<?> testClass) throws InitializationError {
        this.fTestClass = new TestClass(testClass);
        this.validate();
    }

    protected abstract List<T> getChildren();

    protected abstract Description describeChild(T var1);

    protected abstract void runChild(T var1, RunNotifier var2);

    protected void collectInitializationErrors(List<Throwable> errors) {
        this.validatePublicVoidNoArgMethods(BeforeClass.class, true, errors);
        this.validatePublicVoidNoArgMethods(AfterClass.class, true, errors);
        this.validateClassRules(errors);
    }

    protected void validatePublicVoidNoArgMethods(Class<? extends Annotation> annotation, boolean isStatic, List<Throwable> errors) {
        List<FrameworkMethod> methods = this.getTestClass().getAnnotatedMethods(annotation);
        for (FrameworkMethod eachTestMethod : methods) {
            eachTestMethod.validatePublicVoidNoArg(isStatic, errors);
        }
    }

    private void validateClassRules(List<Throwable> errors) {
        RuleFieldValidator.CLASS_RULE_VALIDATOR.validate(this.getTestClass(), errors);
    }

    protected Statement classBlock(RunNotifier notifier) {
        Statement statement = this.childrenInvoker(notifier);
        statement = this.withBeforeClasses(statement);
        statement = this.withAfterClasses(statement);
        statement = this.withClassRules(statement);
        return statement;
    }

    protected Statement withBeforeClasses(Statement statement) {
        List<FrameworkMethod> befores = this.fTestClass.getAnnotatedMethods(BeforeClass.class);
        return befores.isEmpty() ? statement : new RunBefores(statement, befores, null);
    }

    protected Statement withAfterClasses(Statement statement) {
        List<FrameworkMethod> afters = this.fTestClass.getAnnotatedMethods(AfterClass.class);
        return afters.isEmpty() ? statement : new RunAfters(statement, afters, null);
    }

    private Statement withClassRules(Statement statement) {
        List<TestRule> classRules = this.classRules();
        return classRules.isEmpty() ? statement : new RunRules(statement, classRules, this.getDescription());
    }

    protected List<TestRule> classRules() {
        return this.fTestClass.getAnnotatedFieldValues(null, ClassRule.class, TestRule.class);
    }

    protected Statement childrenInvoker(final RunNotifier notifier) {
        return new Statement(){

            public void evaluate() {
                ParentRunner.this.runChildren(notifier);
            }
        };
    }

    private void runChildren(final RunNotifier notifier) {
        for (final T each : this.getFilteredChildren()) {
            this.fScheduler.schedule(new Runnable(){

                public void run() {
                    ParentRunner.this.runChild(each, notifier);
                }
            });
        }
        this.fScheduler.finished();
    }

    protected String getName() {
        return this.fTestClass.getName();
    }

    public final TestClass getTestClass() {
        return this.fTestClass;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected final void runLeaf(Statement statement, Description description, RunNotifier notifier) {
        EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
        eachNotifier.fireTestStarted();
        try {
            statement.evaluate();
        }
        catch (AssumptionViolatedException e) {
            eachNotifier.addFailedAssumption(e);
        }
        catch (Throwable e) {
            eachNotifier.addFailure(e);
        }
        finally {
            eachNotifier.fireTestFinished();
        }
    }

    protected Annotation[] getRunnerAnnotations() {
        return this.fTestClass.getAnnotations();
    }

    @Override
    public Description getDescription() {
        Description description = Description.createSuiteDescription(this.getName(), this.getRunnerAnnotations());
        for (T child : this.getFilteredChildren()) {
            description.addChild(this.describeChild(child));
        }
        return description;
    }

    @Override
    public void run(RunNotifier notifier) {
        EachTestNotifier testNotifier = new EachTestNotifier(notifier, this.getDescription());
        try {
            Statement statement = this.classBlock(notifier);
            statement.evaluate();
        }
        catch (AssumptionViolatedException e) {
            testNotifier.fireTestIgnored();
        }
        catch (StoppedByUserException e) {
            throw e;
        }
        catch (Throwable e) {
            testNotifier.addFailure(e);
        }
    }

    @Override
    public void filter(Filter filter2) throws NoTestsRemainException {
        Iterator<T> iter2 = this.getFilteredChildren().iterator();
        while (iter2.hasNext()) {
            T each = iter2.next();
            if (this.shouldRun(filter2, each)) {
                try {
                    filter2.apply(each);
                }
                catch (NoTestsRemainException e) {
                    iter2.remove();
                }
                continue;
            }
            iter2.remove();
        }
        if (this.getFilteredChildren().isEmpty()) {
            throw new NoTestsRemainException();
        }
    }

    @Override
    public void sort(Sorter sorter) {
        this.fSorter = sorter;
        for (T each : this.getFilteredChildren()) {
            this.sortChild(each);
        }
        Collections.sort(this.getFilteredChildren(), this.comparator());
    }

    private void validate() throws InitializationError {
        ArrayList<Throwable> errors = new ArrayList<Throwable>();
        this.collectInitializationErrors(errors);
        if (!errors.isEmpty()) {
            throw new InitializationError(errors);
        }
    }

    private List<T> getFilteredChildren() {
        if (this.fFilteredChildren == null) {
            this.fFilteredChildren = new ArrayList<T>(this.getChildren());
        }
        return this.fFilteredChildren;
    }

    private void sortChild(T child) {
        this.fSorter.apply(child);
    }

    private boolean shouldRun(Filter filter2, T each) {
        return filter2.shouldRun(this.describeChild(each));
    }

    private Comparator<? super T> comparator() {
        return new Comparator<T>(){

            @Override
            public int compare(T o1, T o2) {
                return ParentRunner.this.fSorter.compare(ParentRunner.this.describeChild(o1), ParentRunner.this.describeChild(o2));
            }
        };
    }

    public void setScheduler(RunnerScheduler scheduler) {
        this.fScheduler = scheduler;
    }
}

