/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.io;

import scala.Predef$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class IOStats$ {
    public static final IOStats$ MODULE$;
    private final Statistics.Counter fileExistsCount;
    private final Statistics.Counter fileIsDirectoryCount;
    private final Statistics.Counter fileIsFileCount;

    static {
        new IOStats$();
    }

    public Statistics.Counter fileExistsCount() {
        return this.fileExistsCount;
    }

    public Statistics.Counter fileIsDirectoryCount() {
        return this.fileIsDirectoryCount;
    }

    public Statistics.Counter fileIsFileCount() {
        return this.fileIsFileCount;
    }

    private IOStats$() {
        MODULE$ = this;
        this.fileExistsCount = Statistics$.MODULE$.newCounter("# File.exists calls", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.fileIsDirectoryCount = Statistics$.MODULE$.newCounter("# File.isDirectory calls", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.fileIsFileCount = Statistics$.MODULE$.newCounter("# File.isFile calls", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
    }
}

