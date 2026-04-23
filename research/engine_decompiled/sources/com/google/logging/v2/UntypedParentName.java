/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ParentName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UntypedParentName
extends ParentName {
    private final String rawValue;
    private Map<String, String> valueMap;

    private UntypedParentName(String rawValue) {
        this.rawValue = Preconditions.checkNotNull(rawValue);
        this.valueMap = ImmutableMap.of("", rawValue);
    }

    public static UntypedParentName from(ResourceName resourceName) {
        return new UntypedParentName(resourceName.toString());
    }

    public static UntypedParentName parse(String formattedString) {
        return new UntypedParentName(formattedString);
    }

    public static List<UntypedParentName> parseList(List<String> formattedStrings) {
        ArrayList<UntypedParentName> list2 = new ArrayList<UntypedParentName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(UntypedParentName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<UntypedParentName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (UntypedParentName value : values) {
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
        if (o instanceof UntypedParentName) {
            UntypedParentName that = (UntypedParentName)o;
            return this.rawValue.equals(that.rawValue);
        }
        return false;
    }

    public int hashCode() {
        return this.rawValue.hashCode();
    }
}

