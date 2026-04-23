/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.common.base.MoreObjects;
import com.google.logging.v2.LogEntrySourceLocation;
import java.io.Serializable;
import java.util.Objects;

public final class SourceLocation
implements Serializable {
    private static final long serialVersionUID = 8502955858162387374L;
    private final String file;
    private final Long line;
    private final String function;

    public static Builder newBuilder() {
        return new Builder();
    }

    SourceLocation(Builder builder) {
        this.file = builder.file;
        this.line = builder.line;
        this.function = builder.function;
    }

    public String getFile() {
        return this.file;
    }

    public Long getLine() {
        return this.line;
    }

    public String getFunction() {
        return this.function;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceLocation)) {
            return false;
        }
        SourceLocation other = (SourceLocation)obj;
        return Objects.equals(this.file, other.file) && Objects.equals(this.line, other.line) && Objects.equals(this.function, other.function);
    }

    public int hashCode() {
        return Objects.hash(this.file, this.line, this.function);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("file", this.file).add("line", this.line).add("function", this.function).toString();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    LogEntrySourceLocation toPb() {
        LogEntrySourceLocation.Builder builder = LogEntrySourceLocation.newBuilder();
        builder.setFile(this.file);
        builder.setLine(this.line);
        builder.setFunction(this.function);
        return builder.build();
    }

    static SourceLocation fromPb(LogEntrySourceLocation sourceLocationPb) {
        return new Builder().setFile(sourceLocationPb.getFile()).setLine(sourceLocationPb.getLine()).setFunction(sourceLocationPb.getFunction()).build();
    }

    public static final class Builder {
        private String file;
        private Long line;
        private String function;

        Builder() {
        }

        Builder(SourceLocation sourceLocation) {
            this.file = sourceLocation.file;
            this.line = sourceLocation.line;
            this.function = sourceLocation.function;
        }

        public Builder setFile(String file) {
            this.file = file;
            return this;
        }

        public Builder setLine(Long line) {
            this.line = line;
            return this;
        }

        public Builder setFunction(String function) {
            this.function = function;
            return this;
        }

        public SourceLocation build() {
            return new SourceLocation(this);
        }
    }
}

