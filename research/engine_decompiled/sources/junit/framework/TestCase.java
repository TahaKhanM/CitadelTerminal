/*
 * Decompiled with CFR 0.152.
 */
package junit.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestResult;

public abstract class TestCase
extends Assert
implements Test {
    private String fName;

    public TestCase() {
        this.fName = null;
    }

    public TestCase(String name) {
        this.fName = name;
    }

    public int countTestCases() {
        return 1;
    }

    protected TestResult createResult() {
        return new TestResult();
    }

    public TestResult run() {
        TestResult result2 = this.createResult();
        this.run(result2);
        return result2;
    }

    public void run(TestResult result2) {
        result2.run(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void runBare() throws Throwable {
        Throwable exception = null;
        this.setUp();
        try {
            this.runTest();
        }
        catch (Throwable running) {
            exception = running;
        }
        finally {
            block13: {
                try {
                    this.tearDown();
                }
                catch (Throwable tearingDown) {
                    if (exception != null) break block13;
                    exception = tearingDown;
                }
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    protected void runTest() throws Throwable {
        TestCase.assertNotNull("TestCase.fName cannot be null", this.fName);
        Method runMethod = null;
        try {
            runMethod = this.getClass().getMethod(this.fName, null);
        }
        catch (NoSuchMethodException e) {
            TestCase.fail("Method \"" + this.fName + "\" not found");
        }
        if (!Modifier.isPublic(runMethod.getModifiers())) {
            TestCase.fail("Method \"" + this.fName + "\" should be public");
        }
        try {
            runMethod.invoke((Object)this, new Object[0]);
        }
        catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        }
        catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public String toString() {
        return this.getName() + "(" + this.getClass().getName() + ")";
    }

    public String getName() {
        return this.fName;
    }

    public void setName(String name) {
        this.fName = name;
    }
}

