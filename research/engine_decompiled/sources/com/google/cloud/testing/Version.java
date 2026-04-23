/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.testing;

import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Version
implements Comparable<Version> {
    private static final Pattern VERSION_PATTERN = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)$");
    private final int major;
    private final int minor;
    private final int patch;

    private Version(int major, int minor, int patch2) {
        this.major = major;
        this.minor = minor;
        this.patch = patch2;
    }

    @Override
    public int compareTo(Version version) {
        int result2 = this.major - version.major;
        if (result2 == 0 && (result2 = this.minor - version.minor) == 0) {
            result2 = this.patch - version.patch;
        }
        return result2;
    }

    public String toString() {
        return String.format("%d.%d.%d", this.major, this.minor, this.patch);
    }

    public boolean equals(Object other) {
        return this == other || other instanceof Version && this.compareTo((Version)other) == 0;
    }

    public int hashCode() {
        return Objects.hash(this.major, this.minor, this.patch);
    }

    int getMajor() {
        return this.major;
    }

    int getMinor() {
        return this.minor;
    }

    int getPatch() {
        return this.patch;
    }

    static Version fromString(String version) {
        Matcher matcher = VERSION_PATTERN.matcher(Preconditions.checkNotNull(version));
        if (matcher.matches()) {
            return new Version(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)), Integer.valueOf(matcher.group(3)));
        }
        throw new IllegalArgumentException("Invalid version format");
    }
}

