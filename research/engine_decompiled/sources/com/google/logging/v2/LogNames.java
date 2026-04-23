/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.logging.v2.BillingLogName;
import com.google.logging.v2.FolderLogName;
import com.google.logging.v2.LogName;
import com.google.logging.v2.OrganizationLogName;
import com.google.logging.v2.ProjectLogName;
import com.google.logging.v2.UntypedLogName;

public class LogNames {
    private LogNames() {
    }

    public static LogName parse(String resourceNameString) {
        if (ProjectLogName.isParsableFrom(resourceNameString)) {
            return ProjectLogName.parse(resourceNameString);
        }
        if (OrganizationLogName.isParsableFrom(resourceNameString)) {
            return OrganizationLogName.parse(resourceNameString);
        }
        if (FolderLogName.isParsableFrom(resourceNameString)) {
            return FolderLogName.parse(resourceNameString);
        }
        if (BillingLogName.isParsableFrom(resourceNameString)) {
            return BillingLogName.parse(resourceNameString);
        }
        return UntypedLogName.parse(resourceNameString);
    }
}

