/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.LogName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UntypedLogName
extends LogName {
    private final String rawValue;
    private Map<String, String> valueMap;

    private UntypedLogName(String rawValue) {
        this.rawValue = Preconditions.checkNotNull(rawValue);
        this.valueMap = ImmutableMap.of("", rawValue);
    }

    public static UntypedLogName from(ResourceName resourceName) {
        return new UntypedLogName(resourceName.toString());
    }

    public static UntypedLogName parse(String formattedString) {
        return new UntypedLogName(formattedString);
    }

    public static List<UntypedLogName> parseList(List<String> formattedStrings) {
        ArrayList<UntypedLogName> list2 = new ArrayList<UntypedLogName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(UntypedLogName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<UntypedLogName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (UntypedLogName value : values) {
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
        if (o instanceof UntypedLogName) {
            UntypedLogName that = (UntypedLogName)o;
            return this.rawValue.equals(that.rawValue);
        }
        return false;
    }

    public int hashCode() {
        return this.rawValue.hashCode();
    }
}

