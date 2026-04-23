/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.testing;

import com.google.cloud.testing.BaseEmulatorHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CommandWrapper {
    private final List<String> prefix = new ArrayList<String>();
    private List<String> command;
    private String nullFilename;
    private boolean redirectOutputToNull;
    private boolean redirectErrorStream;
    private boolean redirectErrorInherit;
    private Path directory;

    private CommandWrapper() {
        if (BaseEmulatorHelper.isWindows()) {
            this.prefix.add("cmd");
            this.prefix.add("/C");
            this.nullFilename = "NUL:";
        } else {
            this.prefix.add("bash");
            this.nullFilename = "/dev/null";
        }
    }

    CommandWrapper setCommand(List<String> command) {
        this.command = new ArrayList<String>(command.size() + this.prefix.size());
        this.command.addAll(this.prefix);
        this.command.addAll(command);
        return this;
    }

    CommandWrapper setRedirectOutputToNull() {
        this.redirectOutputToNull = true;
        return this;
    }

    CommandWrapper setRedirectErrorStream() {
        this.redirectErrorStream = true;
        return this;
    }

    CommandWrapper setRedirectErrorInherit() {
        this.redirectErrorInherit = true;
        return this;
    }

    CommandWrapper setDirectory(Path directory) {
        this.directory = directory;
        return this;
    }

    ProcessBuilder getBuilder() {
        ProcessBuilder builder = new ProcessBuilder(this.command);
        if (this.redirectOutputToNull) {
            builder.redirectOutput(new File(this.nullFilename));
        }
        if (this.directory != null) {
            builder.directory(this.directory.toFile());
        }
        if (this.redirectErrorStream) {
            builder.redirectErrorStream(true);
        }
        if (this.redirectErrorInherit) {
            builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        }
        return builder;
    }

    public Process start() throws IOException {
        return this.getBuilder().start();
    }

    static CommandWrapper create() {
        return new CommandWrapper();
    }
}

