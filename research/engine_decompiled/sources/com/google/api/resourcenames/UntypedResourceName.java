/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.resourcenames;

import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class UntypedResourceName
implements ResourceName {
    private final String rawValue;
    private volatile Map<String, String> fieldValuesMap;

    private UntypedResourceName(String rawValue) {
        this.rawValue = Preconditions.checkNotNull(rawValue);
    }

    public static UntypedResourceName of(ResourceName resourceName) {
        return new UntypedResourceName(resourceName.toString());
    }

    public static UntypedResourceName parse(String formattedString) {
        return new UntypedResourceName(formattedString);
    }

    public static boolean isParsableFrom(String formattedString) {
        return formattedString != null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, String> getFieldValuesMap() {
        if (this.fieldValuesMap == null) {
            UntypedResourceName untypedResourceName = this;
            synchronized (untypedResourceName) {
                if (this.fieldValuesMap == null) {
                    this.fieldValuesMap = ImmutableMap.of("", this.rawValue);
                }
            }
        }
        return this.fieldValuesMap;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return this.getFieldValuesMap().get("");
    }

    public String toString() {
        return this.rawValue;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof UntypedResourceName) {
            UntypedResourceName that = (UntypedResourceName)o;
            return this.rawValue.equals(that.rawValue);
        }
        return false;
    }

    public int hashCode() {
        return this.rawValue.hashCode();
    }
}

