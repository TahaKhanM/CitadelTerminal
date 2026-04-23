/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Predef$;
import scala.Serializable;
import scala.collection.GenTraversable;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.reflect.internal.util.BatchSourceFile;
import scala.reflect.internal.util.ScriptSourceFile;
import scala.reflect.io.AbstractFile;

public final class ScriptSourceFile$ {
    public static final ScriptSourceFile$ MODULE$;

    static {
        new ScriptSourceFile$();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int headerLength(char[] cs) {
        Pattern headerPattern = Pattern.compile("((?m)^(::)?!#.*|^.*/env .*)(\\r|\\n|\\r\\n)");
        GenTraversable headerStarts = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"#!", "::#!"}));
        if (!((List)headerStarts).exists(new Serializable(cs){
            public static final long serialVersionUID = 0L;
            private final char[] cs$1;

            public final boolean apply(String x$1) {
                return Predef$.MODULE$.charArrayOps(this.cs$1).startsWith(Predef$.MODULE$.wrapString(x$1));
            }
            {
                this.cs$1 = cs$1;
            }
        })) return 0;
        Matcher matcher = headerPattern.matcher(Predef$.MODULE$.charArrayOps(cs).mkString());
        if (!matcher.find()) throw new IOException("script file does not close its header with !# or ::!#");
        int n = matcher.end();
        return n;
    }

    public ScriptSourceFile apply(AbstractFile file, char[] content) {
        BatchSourceFile underlying = new BatchSourceFile(file, content);
        int headerLen = this.headerLength(content);
        ScriptSourceFile stripped = new ScriptSourceFile(underlying, (char[])Predef$.MODULE$.charArrayOps(content).drop(headerLen), headerLen);
        return stripped;
    }

    public ScriptSourceFile apply(BatchSourceFile underlying) {
        int headerLen = this.headerLength(underlying.content());
        return new ScriptSourceFile(underlying, (char[])Predef$.MODULE$.charArrayOps(underlying.content()).drop(headerLen), headerLen);
    }

    private ScriptSourceFile$() {
        MODULE$ = this;
    }
}

