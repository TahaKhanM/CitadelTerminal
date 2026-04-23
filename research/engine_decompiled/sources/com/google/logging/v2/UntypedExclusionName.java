/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ExclusionName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UntypedExclusionName
extends ExclusionName {
    private final String rawValue;
    private Map<String, String> valueMap;

    private UntypedExclusionName(String rawValue) {
        this.rawValue = Preconditions.checkNotNull(rawValue);
        this.valueMap = ImmutableMap.of("", rawValue);
    }

    public static UntypedExclusionName from(ResourceName resourceName) {
        return new UntypedExclusionName(resourceName.toString());
    }

    public static UntypedExclusionName parse(String formattedString) {
        return new UntypedExclusionName(formattedString);
    }

    public static List<UntypedExclusionName> parseList(List<String> formattedStrings) {
        ArrayList<UntypedExclusionName> list2 = new ArrayList<UntypedExclusionName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(UntypedExclusionName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<UntypedExclusionName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (UntypedExclusionName value : values) {
            if (value == null) {
                list2.add("");
                continue;
            }
            list2.add(value.toString());
        }
        return list2;
    }

    public static boolean isParsableFrom(String formattedString) {
        return true;
    }

    @Override
    public Map<String, String> getFieldValuesMap() {
        return this.valueMap;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return this.valueMap.get(fieldName);
    }

    public String toString() {
        return this.rawValue;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof UntypedExclusionName) {
            UntypedExclusionName that = (UntypedExclusionName)o;
            return this.rawValue.equals(that.rawValue);
        }
        return false;
    }

    public int hashCode() {
        return this.rawValue.hashCode();
    }
}

