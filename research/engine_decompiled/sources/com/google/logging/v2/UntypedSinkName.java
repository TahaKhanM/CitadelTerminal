/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.SinkName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UntypedSinkName
extends SinkName {
    private final String rawValue;
    private Map<String, String> valueMap;

    private UntypedSinkName(String rawValue) {
        this.rawValue = Preconditions.checkNotNull(rawValue);
        this.valueMap = ImmutableMap.of("", rawValue);
    }

    public static UntypedSinkName from(ResourceName resourceName) {
        return new UntypedSinkName(resourceName.toString());
    }

    public static UntypedSinkName parse(String formattedString) {
        return new UntypedSinkName(formattedString);
    }

    public static List<UntypedSinkName> parseList(List<String> formattedStrings) {
        ArrayList<UntypedSinkName> list2 = new ArrayList<UntypedSinkName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(UntypedSinkName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<UntypedSinkName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (UntypedSinkName value : values) {
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
        if (o instanceof UntypedSinkName) {
            UntypedSinkName that = (UntypedSinkName)o;
            return this.rawValue.equals(that.rawValue);
        }
        return false;
    }

    public int hashCode() {
        return this.rawValue.hashCode();
    }
}

