/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class AbstractFileComparator
implements Comparator<File> {
    AbstractFileComparator() {
    }

    public File[] sort(File ... files2) {
        if (files2 != null) {
            Arrays.sort(files2, this);
        }
        return files2;
    }

    public List<File> sort(List<File> files2) {
        if (files2 != null) {
            Collections.sort(files2, this);
        }
        return files2;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}

