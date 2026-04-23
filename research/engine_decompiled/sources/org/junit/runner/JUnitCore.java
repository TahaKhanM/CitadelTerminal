/*
 * Decompiled with CFR 0.152.
 */
package org.junit.runner;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import junit.framework.Test;
import junit.runner.Version;
import org.junit.internal.JUnitSystem;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.Computer;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JUnitCore {
    private RunNotifier fNotifier = new RunNotifier();

    public static void main(String ... args) {
        JUnitCore.runMainAndExit(new RealSystem(), args);
    }

    public static void runMainAndExit(JUnitSystem system, String ... args) {
        Result result2 = new JUnitCore().runMain(system, args);
        system.exit(result2.wasSuccessful() ? 0 : 1);
    }

    public static Result runClasses(Computer computer, Class<?> ... classes) {
        return new JUnitCore().run(computer, classes);
    }

    public static Result runClasses(Class<?> ... classes) {
        return new JUnitCore().run(JUnitCore.defaultComputer(), classes);
    }

    public Result runMain(JUnitSystem system, String ... args) {
        system.out().println("JUnit version " + Version.id());
        ArrayList classes = new ArrayList();
        ArrayList<Failure> missingClasses = new ArrayList<Failure>();
        for (String string2 : args) {
            try {
                classes.add(Class.forName(string2));
            }
            catch (ClassNotFoundException e) {
                system.out().println("Could not find class: " + string2);
                Description description = Description.createSuiteDescription(string2, new Annotation[0]);
                Failure failure = new Failure(description, e);
                missingClasses.add(failure);
            }
        }
        TextListener listener = new TextListener(system);
        this.addListener(listener);
        Result result2 = this.run(classes.toArray(new Class[0]));
        for (Failure failure : missingClasses) {
            result2.getFailures().add(failure);
        }
        return result2;
    }

    public String getVersion() {
        return Version.id();
    }

    public Result run(Class<?> ... classes) {
        return this.run(Request.classes(JUnitCore.defaultComputer(), classes));
    }

    public Result run(Computer computer, Class<?> ... classes) {
        return this.run(Request.classes(computer, classes));
    }

    public Result run(Request request) {
        return this.run(request.getRunner());
    }

    public Result run(Test test) {
        return this.run(new JUnit38ClassRunner(test));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Result run(Runner runner) {
        Result result2 = new Result();
        RunListener listener = result2.createListener();
        this.fNotifier.addFirstListener(listener);
        try {
            this.fNotifier.fireTestRunStarted(runner.getDescription());
            runner.run(this.fNotifier);
            this.fNotifier.fireTestRunFinished(result2);
        }
        finally {
            this.removeListener(listener);
        }
        return result2;
    }

    public void addListener(RunListener listener) {
        this.fNotifier.addListener(listener);
    }

    public void removeListener(RunListener listener) {
        this.fNotifier.removeListener(listener);
    }

    static Computer defaultComputer() {
        return new Computer();
    }
}

