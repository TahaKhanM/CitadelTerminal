/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.parallel;

import scala.Function0;
import scala.collection.parallel.Task;
import scala.collection.parallel.Tasks;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001I3q!\u0001\u0002\u0011\u0002\u0007\u0005\u0011BA\u000bFq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yiR\u000b7o[:\u000b\u0005\r!\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qa\u0002\u0005\u0002\f\u00195\ta!\u0003\u0002\u000e\r\t1\u0011I\\=SK\u001a\u0004\"a\u0004\t\u000e\u0003\tI!!\u0005\u0002\u0003\u000bQ\u000b7o[:\t\u000bM\u0001A\u0011\u0001\u000b\u0002\r\u0011Jg.\u001b;%)\u0005)\u0002CA\u0006\u0017\u0013\t9bA\u0001\u0003V]&$\b\"B\r\u0001\t\u0003Q\u0012\u0001E3yK\u000e,H/[8o\u0007>tG/\u001a=u+\u0005Y\u0002C\u0001\u000f \u001b\u0005i\"B\u0001\u0010\u0007\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0003Au\u0011\u0001#\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;\t\u000f\t\u0002!\u0019!D\u00015\u0005YQM\u001c<je>tW.\u001a8u\u0011\u001d!\u0003A1A\u0005\n\u0015\na\u0001\u001a:jm\u0016\u0014X#\u0001\b\t\r\u001d\u0002\u0001\u0015!\u0003\u000f\u0003\u001d!'/\u001b<fe\u0002BQ!\u000b\u0001\u0005\u0002)\nq!\u001a=fGV$X-F\u0002,c\u0001#\"\u0001\f\u001e\u0011\u0007-is&\u0003\u0002/\r\tIa)\u001e8di&|g\u000e\r\t\u0003aEb\u0001\u0001B\u00033Q\t\u00071GA\u0001S#\t!t\u0007\u0005\u0002\fk%\u0011aG\u0002\u0002\b\u001d>$\b.\u001b8h!\tY\u0001(\u0003\u0002:\r\t\u0019\u0011I\\=\t\u000bmB\u0003\u0019\u0001\u001f\u0002\tQ\f7o\u001b\t\u0005\u001fuzs(\u0003\u0002?\u0005\t!A+Y:l!\t\u0001\u0004\tB\u0003BQ\t\u00071G\u0001\u0002Ua\")1\t\u0001C\u0001\t\u0006!R\r_3dkR,\u0017I\u001c3XC&$(+Z:vYR,2!R$L)\t1\u0005\n\u0005\u00021\u000f\u0012)!G\u0011b\u0001g!)1H\u0011a\u0001\u0013B!q\"\u0010$K!\t\u00014\nB\u0003B\u0005\n\u00071\u0007C\u0003N\u0001\u0011\u0005a*\u0001\tqCJ\fG\u000e\\3mSNlG*\u001a<fYV\tq\n\u0005\u0002\f!&\u0011\u0011K\u0002\u0002\u0004\u0013:$\b")
public interface ExecutionContextTasks
extends Tasks {
    public void scala$collection$parallel$ExecutionContextTasks$_setter_$scala$collection$parallel$ExecutionContextTasks$$driver_$eq(Tasks var1);

    public ExecutionContext executionContext();

    @Override
    public ExecutionContext environment();

    public Tasks scala$collection$parallel$ExecutionContextTasks$$driver();

    @Override
    public <R, Tp> Function0<R> execute(Task<R, Tp> var1);

    @Override
    public <R, Tp> R executeAndWaitResult(Task<R, Tp> var1);

    @Override
    public int parallelismLevel();
}

