/*
 * Decompiled with CFR 0.152.
 */
package scala.sys.process;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.runtime.BoxedUnit;
import scala.runtime.Null$;
import scala.sys.package$;
import scala.sys.process.BasicIO$Uncloseable$;
import scala.sys.process.ProcessIO;
import scala.sys.process.ProcessLogger;
import scala.sys.process.processInternal$;

public final class BasicIO$ {
    public static final BasicIO$ MODULE$;
    private final int BufferSize;
    private final String Newline;

    static {
        new BasicIO$();
    }

    public final int BufferSize() {
        return 8192;
    }

    public final String Newline() {
        return this.Newline;
    }

    public ProcessIO apply(boolean withIn, Function1<String, BoxedUnit> output, Option<ProcessLogger> log) {
        return new ProcessIO((Function1<OutputStream, BoxedUnit>)((Object)new Serializable(withIn){
            public static final long serialVersionUID = 0L;
            private final boolean connect$1;

            public final void apply(OutputStream outputToProcess) {
                if (this.connect$1) {
                    BasicIO$.MODULE$.connectToIn(outputToProcess);
                }
                outputToProcess.close();
            }
            {
                this.connect$1 = connect$1;
            }
        }), (Function1<InputStream, BoxedUnit>)((Object)new Serializable(output){
            public static final long serialVersionUID = 0L;
            private final Function1 processLine$1;

            /*
             * WARNING - void declaration
             */
            public final void apply(InputStream in) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                try {
                    BasicIO$.MODULE$.processLinesFully(this.processLine$1, (Function0<String>)((Object)new Serializable(this, reader){
                        public static final long serialVersionUID = 0L;
                        private final BufferedReader reader$1;

                        public final String apply() {
                            return this.reader$1.readLine();
                        }
                        {
                            this.reader$1 = reader$1;
                        }
                    }));
                }
                catch (Throwable throwable) {
                    void var2_2;
                    var2_2.close();
                    throw throwable;
                }
                reader.close();
            }
            {
                this.processLine$1 = processLine$1;
            }
        }), this.getErr(log));
    }

    public ProcessIO apply(boolean withIn, StringBuffer buffer, Option<ProcessLogger> log) {
        return new ProcessIO(this.input(withIn), this.processFully(buffer), this.getErr(log));
    }

    public ProcessIO apply(boolean withIn, ProcessLogger log) {
        return new ProcessIO(this.input(withIn), this.processOutFully(log), this.processErrFully(log));
    }

    public Function1<InputStream, BoxedUnit> getErr(Option<ProcessLogger> log) {
        block4: {
            Function1<InputStream, BoxedUnit> function1;
            block3: {
                block2: {
                    if (!(log instanceof Some)) break block2;
                    Some some = (Some)log;
                    function1 = this.processErrFully((ProcessLogger)some.x());
                    break block3;
                }
                if (!None$.MODULE$.equals(log)) break block4;
                function1 = this.toStdErr();
            }
            return function1;
        }
        throw new MatchError(log);
    }

    private Function1<InputStream, BoxedUnit> processErrFully(ProcessLogger log) {
        Serializable serializable = new Serializable(log){
            public static final long serialVersionUID = 0L;
            private final ProcessLogger log$1;

            public final void apply(String x$1) {
                this.log$1.err((Function0<String>)((Object)new Serializable(this, x$1){
                    public static final long serialVersionUID = 0L;
                    private final String x$1$1;

                    public final String apply() {
                        return this.x$1$1;
                    }
                    {
                        this.x$1$1 = x$1$1;
                    }
                }));
            }
            {
                this.log$1 = log$1;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    private Function1<InputStream, BoxedUnit> processOutFully(ProcessLogger log) {
        Serializable serializable = new Serializable(log){
            public static final long serialVersionUID = 0L;
            private final ProcessLogger log$2;

            public final void apply(String x$2) {
                this.log$2.out((Function0<String>)((Object)new Serializable(this, x$2){
                    public static final long serialVersionUID = 0L;
                    private final String x$2$1;

                    public final String apply() {
                        return this.x$2$1;
                    }
                    {
                        this.x$2$1 = x$2$1;
                    }
                }));
            }
            {
                this.log$2 = log$2;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    public void close(Closeable c) {
        try {
            c.close();
        }
        catch (IOException iOException) {}
    }

    public Function1<InputStream, BoxedUnit> processFully(Appendable buffer) {
        Serializable serializable = new Serializable(buffer){
            public static final long serialVersionUID = 0L;
            private final Appendable buffer$1;

            public final void apply(String line) {
                this.buffer$1.append(line);
                this.buffer$1.append(BasicIO$.MODULE$.Newline());
            }
            {
                this.buffer$1 = buffer$1;
            }
        };
        return new /* invalid duplicate definition of identical inner class */;
    }

    public Function1<InputStream, BoxedUnit> processFully(Function1<String, BoxedUnit> processLine) {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public void processLinesFully(Function1<String, BoxedUnit> processLine, Function0<String> readLine) {
        this.readFully$1(processLine, readLine);
    }

    public void connectToIn(OutputStream o) {
        this.transferFully(BasicIO$Uncloseable$.MODULE$.protect(scala.sys.process.package$.MODULE$.stdin()), o);
    }

    public Function1<OutputStream, BoxedUnit> input(boolean connect) {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public ProcessIO standard(boolean connectInput) {
        Serializable serializable = new /* invalid duplicate definition of identical inner class */;
        return new ProcessIO((Function1<OutputStream, BoxedUnit>)((Object)serializable), (Function1<InputStream, BoxedUnit>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply(InputStream in) {
                BasicIO$.MODULE$.transferFully(in, scala.sys.process.package$.MODULE$.stdout());
            }
        }), (Function1<InputStream, BoxedUnit>)((Object)new Serializable(){
            public static final long serialVersionUID = 0L;

            public final void apply(InputStream in) {
                BasicIO$.MODULE$.transferFully(in, scala.sys.process.package$.MODULE$.stderr());
            }
        }));
    }

    public ProcessIO standard(Function1<OutputStream, BoxedUnit> in) {
        return new ProcessIO(in, this.toStdOut(), this.toStdErr());
    }

    public Function1<InputStream, BoxedUnit> toStdErr() {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public Function1<InputStream, BoxedUnit> toStdOut() {
        return new /* invalid duplicate definition of identical inner class */;
    }

    public void transferFully(InputStream in, OutputStream out) {
        Throwable throwable2;
        block2: {
            try {
                this.transferFullyImpl(in, out);
            }
            catch (Throwable throwable2) {
                Serializable serializable = new Serializable(){
                    public static final long serialVersionUID = 0L;

                    public final void apply() {
                    }

                    public void apply$mcV$sp() {
                    }
                };
                processInternal$ processInternal$2 = processInternal$.MODULE$;
                Serializable catchExpr$1 = new Serializable((Function0)((Object)serializable)){
                    public static final long serialVersionUID = 0L;
                    private final Function0 handler$1;

                    public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        Object object = x1 instanceof InterruptedException ? this.handler$1.apply() : function1.apply(x1);
                        return (B1)object;
                    }

                    public final boolean isDefinedAt(Throwable x1) {
                        boolean bl = x1 instanceof InterruptedException;
                        return bl;
                    }
                    {
                        this.handler$1 = handler$1;
                    }
                };
                Throwable throwable3 = throwable2;
                boolean bl = throwable3 instanceof InterruptedException;
                if (!bl) break block2;
                catchExpr$1.apply(throwable2);
            }
            return;
        }
        throw throwable2;
    }

    private Function1<String, BoxedUnit> appendLine(Appendable buffer) {
        return new /* invalid duplicate definition of identical inner class */;
    }

    private void transferFullyImpl(InputStream in, OutputStream out) {
        byte[] buffer = new byte[8192];
        this.loop$1(in, out, buffer);
        in.close();
    }

    private final boolean working$1() {
        return !Thread.currentThread().isInterrupted();
    }

    private final Null$ halting$1() {
        Thread.currentThread().interrupt();
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final void readFully$1(Function1 processLine$2, Function0 readLine$1) {
        while (true) {
            String line;
            String string2;
            BoxedUnit boxedUnit;
            if (!this.working$1()) {
                boxedUnit = BoxedUnit.UNIT;
                return;
            }
            try {
                string2 = (String)readLine$1.apply();
            }
            catch (Throwable throwable) {
                if (throwable instanceof InterruptedException) {
                    this.halting$1();
                } else {
                    if (!(throwable instanceof IOException)) throw throwable;
                    if (this.working$1()) throw throwable;
                    this.halting$1();
                }
                string2 = null;
            }
            if ((line = string2) == null) {
                boxedUnit = BoxedUnit.UNIT;
                return;
            }
            processLine$2.apply(line);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final void loop$1(InputStream in$2, OutputStream out$2, byte[] buffer$2) {
        BoxedUnit boxedUnit;
        boolean bl;
        boolean available;
        do {
            int byteCount;
            if ((byteCount = in$2.read(buffer$2)) <= 0) {
                boxedUnit = BoxedUnit.UNIT;
                return;
            }
            out$2.write(buffer$2, 0, byteCount);
            try {
                out$2.flush();
                bl = true;
            }
            catch (IOException iOException) {
                bl = false;
            }
        } while (available = bl);
        boxedUnit = BoxedUnit.UNIT;
    }

    private BasicIO$() {
        MODULE$ = this;
        this.Newline = (String)package$.MODULE$.props().apply("line.separator");
    }
}

