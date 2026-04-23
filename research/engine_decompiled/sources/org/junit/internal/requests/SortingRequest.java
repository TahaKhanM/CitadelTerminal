/*
 * Decompiled with CFR 0.152.
 */
package org.junit.internal.requests;

import java.util.Comparator;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Sorter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SortingRequest
extends Request {
    private final Request fRequest;
    private final Comparator<Description> fComparator;

    public SortingRequest(Request request, Comparator<Description> comparator) {
        this.fRequest = request;
        this.fComparator = comparator;
    }

    @Override
    public Runner getRunner() {
        Runner runner = this.fRequest.getRunner();
        new Sorter(this.fComparator).apply(runner);
        return runner;
    }
}

