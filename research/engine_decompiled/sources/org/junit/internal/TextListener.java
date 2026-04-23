/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.List;
import org.junit.internal.JUnitSystem;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class TextListener
extends RunListener {
    private final PrintStream fWriter;

    public TextListener(JUnitSystem system) {
        this(system.out());
    }

    public TextListener(PrintStream writer) {
        this.fWriter = writer;
    }

    public void testRunFinished(Result result2) {
        this.printHeader(result2.getRunTime());
        this.printFailures(result2);
        this.printFooter(result2);
    }

    public void testStarted(Description description) {
        this.fWriter.append('.');
    }

    public void testFailure(Failure failure) {
        this.fWriter.append('E');
    }

    public void testIgnored(Description description) {
        this.fWriter.append('I');
    }

    private PrintStream getWriter() {
        return this.fWriter;
    }

    protected void printHeader(long runTime) {
        this.getWriter().println();
        this.getWriter().println("Time: " + this.elapsedTimeAsString(runTime));
    }

    protected void printFailures(Result result2) {
        List<Failure> failures = result2.getFailures();
        if (failures.size() == 0) {
            return;
        }
        if (failures.size() == 1) {
            this.getWriter().println("There was " + failures.size() + " failure:");
        } else {
            this.getWriter().println("There were " + failures.size() + " failures:");
        }
        int i = 1;
        for (Failure each : failures) {
            this.printFailure(each, "" + i++);
        }
    }

    protected void printFailure(Failure each, String prefix) {
        this.getWriter().println(prefix + ") " + each.getTestHeader());
        this.getWriter().print(each.getTrace());
    }

    protected void printFooter(Result result2) {
        if (result2.wasSuccessful()) {
            this.getWriter().println();
            this.getWriter().print("OK");
            this.getWriter().println(" (" + result2.getRunCount() + " test" + (result2.getRunCount() == 1 ? "" : "s") + ")");
        } else {
            this.getWriter().println();
            this.getWriter().println("FAILURES!!!");
            this.getWriter().println("Tests run: " + result2.getRunCount() + ",  Failures: " + result2.getFailureCount());
        }
        this.getWriter().println();
    }

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double)runTime / 1000.0);
    }
}

