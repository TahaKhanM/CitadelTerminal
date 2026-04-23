/*
 * Decompiled with CFR 0.152.
 */
package junit.textui;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResultPrinter
implements TestListener {
    PrintStream fWriter;
    int fColumn = 0;

    public ResultPrinter(PrintStream writer) {
        this.fWriter = writer;
    }

    synchronized void print(TestResult result2, long runTime) {
        this.printHeader(runTime);
        this.printErrors(result2);
        this.printFailures(result2);
        this.printFooter(result2);
    }

    void printWaitPrompt() {
        this.getWriter().println();
        this.getWriter().println("<RETURN> to continue");
    }

    protected void printHeader(long runTime) {
        this.getWriter().println();
        this.getWriter().println("Time: " + this.elapsedTimeAsString(runTime));
    }

    protected void printErrors(TestResult result2) {
        this.printDefects(result2.errors(), result2.errorCount(), "error");
    }

    protected void printFailures(TestResult result2) {
        this.printDefects(result2.failures(), result2.failureCount(), "failure");
    }

    protected void printDefects(Enumeration<TestFailure> booBoos, int count2, String type) {
        if (count2 == 0) {
            return;
        }
        if (count2 == 1) {
            this.getWriter().println("There was " + count2 + " " + type + ":");
        } else {
            this.getWriter().println("There were " + count2 + " " + type + "s:");
        }
        int i = 1;
        while (booBoos.hasMoreElements()) {
            this.printDefect(booBoos.nextElement(), i);
            ++i;
        }
    }

    public void printDefect(TestFailure booBoo, int count2) {
        this.printDefectHeader(booBoo, count2);
        this.printDefectTrace(booBoo);
    }

    protected void printDefectHeader(TestFailure booBoo, int count2) {
        this.getWriter().print(count2 + ") " + booBoo.failedTest());
    }

    protected void printDefectTrace(TestFailure booBoo) {
        this.getWriter().print(BaseTestRunner.getFilteredTrace(booBoo.trace()));
    }

    protected void printFooter(TestResult result2) {
        if (result2.wasSuccessful()) {
            this.getWriter().println();
            this.getWriter().print("OK");
            this.getWriter().println(" (" + result2.runCount() + " test" + (result2.runCount() == 1 ? "" : "s") + ")");
        } else {
            this.getWriter().println();
            this.getWriter().println("FAILURES!!!");
            this.getWriter().println("Tests run: " + result2.runCount() + ",  Failures: " + result2.failureCount() + ",  Errors: " + result2.errorCount());
        }
        this.getWriter().println();
    }

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double)runTime / 1000.0);
    }

    public PrintStream getWriter() {
        return this.fWriter;
    }

    @Override
    public void addError(Test test, Throwable t) {
        this.getWriter().print("E");
    }

    @Override
    public void addFailure(Test test, AssertionFailedError t) {
        this.getWriter().print("F");
    }

    @Override
    public void endTest(Test test) {
    }

    @Override
    public void startTest(Test test) {
        this.getWriter().print(".");
        if (this.fColumn++ >= 40) {
            this.getWriter().println();
            this.fColumn = 0;
        }
    }
}

