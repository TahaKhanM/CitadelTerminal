/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runners;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Parameterized
extends Suite {
    private final ArrayList<Runner> runners = new ArrayList();

    public Parameterized(Class<?> klass) throws Throwable {
        super(klass, Collections.<Runner>emptyList());
        List<Object[]> parametersList = this.getParametersList(this.getTestClass());
        for (int i = 0; i < parametersList.size(); ++i) {
            this.runners.add(new TestClassRunnerForParameters(this.getTestClass().getJavaClass(), parametersList, i));
        }
    }

    @Override
    protected List<Runner> getChildren() {
        return this.runners;
    }

    private List<Object[]> getParametersList(TestClass klass) throws Throwable {
        return (List)this.getParametersMethod(klass).invokeExplosively(null, new Object[0]);
    }

    private FrameworkMethod getParametersMethod(TestClass testClass) throws Exception {
        List<FrameworkMethod> methods = testClass.getAnnotatedMethods(Parameters.class);
        for (FrameworkMethod each : methods) {
            int modifiers = each.getMethod().getModifiers();
            if (!Modifier.isStatic(modifiers) || !Modifier.isPublic(modifiers)) continue;
            return each;
        }
        throw new Exception("No public static parameters method on class " + testClass.getName());
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class TestClassRunnerForParameters
    extends BlockJUnit4ClassRunner {
        private final int fParameterSetNumber;
        private final List<Object[]> fParameterList;

        TestClassRunnerForParameters(Class<?> type, List<Object[]> parameterList, int i) throws InitializationError {
            super(type);
            this.fParameterList = parameterList;
            this.fParameterSetNumber = i;
        }

        @Override
        public Object createTest() throws Exception {
            return this.getTestClass().getOnlyConstructor().newInstance(this.computeParams());
        }

        private Object[] computeParams() throws Exception {
            try {
                return this.fParameterList.get(this.fParameterSetNumber);
            }
            catch (ClassCastException e) {
                throw new Exception(String.format("%s.%s() must return a Collection of arrays.", this.getTestClass().getName(), Parameterized.this.getParametersMethod(this.getTestClass()).getName()));
            }
        }

        @Override
        protected String getName() {
            return String.format("[%s]", this.fParameterSetNumber);
        }

        @Override
        protected String testName(FrameworkMethod method) {
            return String.format("%s[%s]", method.getName(), this.fParameterSetNumber);
        }

        @Override
        protected void validateConstructor(List<Throwable> errors) {
            this.validateOnlyOneConstructor(errors);
        }

        @Override
        protected Statement classBlock(RunNotifier notifier) {
            return this.childrenInvoker(notifier);
        }

        @Override
        protected Annotation[] getRunnerAnnotations() {
            return new Annotation[0];
        }
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.METHOD})
    public static @interface Parameters {
    }
}

