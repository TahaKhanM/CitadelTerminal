/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.BillingExclusionName;
import com.google.logging.v2.ExclusionName;
import com.google.logging.v2.FolderExclusionName;
import com.google.logging.v2.OrganizationExclusionName;
import com.google.logging.v2.ProjectExclusionName;
import com.google.logging.v2.UntypedExclusionName;

public class ExclusionNames {
    private ExclusionNames() {
    }

    public static ExclusionName parse(String resourceNameString) {
        if (ProjectExclusionName.isParsableFrom(resourceNameString)) {
            return ProjectExclusionName.parse(resourceNameString);
        }
        if (OrganizationExclusionName.isParsableFrom(resourceNameString)) {
            return OrganizationExclusionName.parse(resourceNameString);
        }
        if (FolderExclusionName.isParsableFrom(resourceNameString)) {
            return FolderExclusionName.parse(resourceNameString);
        }
        if (BillingExclusionName.isParsableFrom(resourceNameString)) {
            return BillingExclusionName.parse(resourceNameString);
        }
        return UntypedExclusionName.parse(resourceNameString);
    }
}

