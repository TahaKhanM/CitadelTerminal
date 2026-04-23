/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.BillingSinkName;
import com.google.logging.v2.FolderSinkName;
import com.google.logging.v2.OrganizationSinkName;
import com.google.logging.v2.ProjectSinkName;
import com.google.logging.v2.SinkName;
import com.google.logging.v2.UntypedSinkName;

public class SinkNames {
    private SinkNames() {
    }

    public static SinkName parse(String resourceNameString) {
        if (ProjectSinkName.isParsableFrom(resourceNameString)) {
            return ProjectSinkName.parse(resourceNameString);
        }
        if (OrganizationSinkName.isParsableFrom(resourceNameString)) {
            return OrganizationSinkName.parse(resourceNameString);
        }
        if (FolderSinkName.isParsableFrom(resourceNameString)) {
            return FolderSinkName.parse(resourceNameString);
        }
        if (BillingSinkName.isParsableFrom(resourceNameString)) {
            return BillingSinkName.parse(resourceNameString);
        }
        return UntypedSinkName.parse(resourceNameString);
    }
}

