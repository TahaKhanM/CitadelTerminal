/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.BillingName;
import com.google.logging.v2.FolderName;
import com.google.logging.v2.OrganizationName;
import com.google.logging.v2.ParentName;
import com.google.logging.v2.ProjectName;
import com.google.logging.v2.UntypedParentName;

public class ParentNames {
    private ParentNames() {
    }

    public static ParentName parse(String resourceNameString) {
        if (ProjectName.isParsableFrom(resourceNameString)) {
            return ProjectName.parse(resourceNameString);
        }
        if (OrganizationName.isParsableFrom(resourceNameString)) {
            return OrganizationName.parse(resourceNameString);
        }
        if (FolderName.isParsableFrom(resourceNameString)) {
            return FolderName.parse(resourceNameString);
        }
        if (BillingName.isParsableFrom(resourceNameString)) {
            return BillingName.parse(resourceNameString);
        }
        return UntypedParentName.parse(resourceNameString);
    }
}

