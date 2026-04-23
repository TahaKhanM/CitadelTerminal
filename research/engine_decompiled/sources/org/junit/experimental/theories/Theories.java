/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.theories;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.PotentialAssignment;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.internal.Assignments;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Theories
extends BlockJUnit4ClassRunner {
    public Theories(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void collectInitializationErrors(List<Throwable> errors) {
        super.collectInitializationErrors(errors);
        this.validateDataPointFields(errors);
    }

    private void validateDataPointFields(List<Throwable> errors) {
        Field[] fields;
        for (Field each : fields = this.getTestClass().getJavaClass().getDeclaredFields()) {
            if (each.getAnnotation(DataPoint.class) == null || Modifier.isStatic(each.getModifiers())) continue;
            errors.add(new Error("DataPoint field " + each.getName() + " must be static"));
        }
    }

    @Override
    protected void validateConstructor(List<Throwable> errors) {
        this.validateOnlyOneConstructor(errors);
    }

    @Override
    protected void validateTestMethods(List<Throwable> errors) {
        for (FrameworkMethod each : this.computeTestMethods()) {
            if (each.getAnnotation(Theory.class) != null) {
                each.validatePublicVoid(false, errors);
                continue;
            }
            each.validatePublicVoidNoArg(false, errors);
        }
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testMethods = super.computeTestMethods();
        List<FrameworkMethod> theoryMethods = this.getTestClass().getAnnotatedMethods(Theory.class);
        testMethods.removeAll(theoryMethods);
        testMethods.addAll(theoryMethods);
        return testMethods;
    }

    @Override
    public Statement methodBlock(FrameworkMethod method) {
        return new TheoryAnchor(method, this.getTestClass());
    }

    public static class TheoryAnchor
    extends Statement {
        private int successes = 0;
        private FrameworkMethod fTestMethod;
        private TestClass fTestClass;
        private List<AssumptionViolatedException> fInvalidParameters = new ArrayList<AssumptionViolatedException>();

        public TheoryAnchor(FrameworkMethod method, TestClass testClass) {
            this.fTestMethod = method;
            this.fTestClass = testClass;
        }

        private TestClass getTestClass() {
            return this.fTestClass;
        }

        public void evaluate() throws Throwable {
            this.runWithAssignment(Assignments.allUnassigned(this.fTestMethod.getMethod(), this.getTestClass()));
            if (this.successes == 0) {
                Assert.fail("Never found parameters that satisfied method assumptions.  Violated assumptions: " + this.fInvalidParameters);
            }
        }

        protected void runWithAssignment(Assignments parameterAssignment) throws Throwable {
            if (!parameterAssignment.isComplete()) {
                this.runWithIncompleteAssignment(parameterAssignment);
            } else {
                this.runWithCompleteAssignment(parameterAssignment);
            }
        }

        protected void runWithIncompleteAssignment(Assignments incomplete) throws InstantiationException, IllegalAccessException, Throwable {
            for (PotentialAssignment source : incomplete.potentialsForNextUnassigned()) {
                this.runWithAssignment(incomplete.assignNext(source));
            }
        }

        protected void runWithCompleteAssignment(final Assignments complete2) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Throwable {
            new BlockJUnit4ClassRunner(this.getTestClass().getJavaClass()){

                @Override
                protected void collectInitializationErrors(List<Throwable> errors) {
                }

                @Override
                public Statement methodBlock(FrameworkMethod method) {
                    final Statement statement = super.methodBlock(method);
                    return new Statement(){

                        public void evaluate() throws Throwable {
                            try {
                                statement.evaluate();
                                TheoryAnchor.this.handleDataPointSuccess();
                            }
                            catch (AssumptionViolatedException e) {
                                TheoryAnchor.this.handleAssumptionViolation(e);
                            }
                            catch (Throwable e) {
                                TheoryAnchor.this.reportParameterizedError(e, complete2.getArgumentStrings(TheoryAnchor.this.nullsOk()));
                            }
                        }
                    };
                }

                @Override
                protected Statement methodInvoker(FrameworkMethod method, Object test) {
                    return TheoryAnchor.this.methodCompletesWithParameters(method, complete2, test);
                }

                @Override
                public Object createTest() throws Exception {
                    return this.getTestClass().getOnlyConstructor().newInstance(complete2.getConstructorArguments(TheoryAnchor.this.nullsOk()));
                }
            }.methodBlock(this.fTestMethod).evaluate();
        }

        private Statement methodCompletesWithParameters(final FrameworkMethod method, final Assignments complete2, final Object freshInstance) {
            return new Statement(){

                public void evaluate() throws Throwable {
                    try {
                        Object[] values = complete2.getMethodArguments(TheoryAnchor.this.nullsOk());
                        method.invokeExplosively(freshInstance, values);
                    }
                    catch (PotentialAssignment.CouldNotGenerateValueException couldNotGenerateValueException) {
                        // empty catch block
                    }
                }
            };
        }

        protected void handleAssumptionViolation(AssumptionViolatedException e) {
            this.fInvalidParameters.add(e);
        }

        protected void reportParameterizedError(Throwable e, Object ... params2) throws Throwable {
            if (params2.length == 0) {
                throw e;
            }
            throw new ParameterizedAssertionError(e, this.fTestMethod.getName(), params2);
        }

        private boolean nullsOk() {
            Theory annotation = this.fTestMethod.getMethod().getAnnotation(Theory.class);
            if (annotation == null) {
                return false;
            }
            return annotation.nullsAccepted();
        }

        protected void handleDataPointSuccess() {
            ++this.successes;
        }
    }
}

