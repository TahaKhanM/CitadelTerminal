/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ParentName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizationName
extends ParentName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("organizations/{organization}");
    private volatile Map<String, String> fieldValuesMap;
    private final String organization;

    public String getOrganization() {
        return this.organization;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private OrganizationName(Builder builder) {
        this.organization = Preconditions.checkNotNull(builder.getOrganization());
    }

    public static OrganizationName of(String organization) {
        return OrganizationName.newBuilder().setOrganization(organization).build();
    }

    public static String format(String organization) {
        return OrganizationName.newBuilder().setOrganization(organization).build().toString();
    }

    public static OrganizationName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "OrganizationName.parse: formattedString not in valid format");
        return OrganizationName.of(matchMap.get("organization"));
    }

    public static List<OrganizationName> parseList(List<String> formattedStrings) {
        ArrayList<OrganizationName> list2 = new ArrayList<OrganizationName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(OrganizationName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<OrganizationName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (OrganizationName value : values) {
            if (value == null) {
                list2.add("");
                continue;
            }
            list2.add(value.toString());
        }
        return list2;
    }

    public static boolean isParsableFrom(String formattedString) {
        return PATH_TEMPLATE.matches(formattedString);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, String> getFieldValuesMap() {
        if (this.fieldValuesMap == null) {
            OrganizationName organizationName = this;
            synchronized (organizationName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("organization", this.organization);
                    this.fieldValuesMap = fieldMapBuilder.build();
                }
            }
        }
        return this.fieldValuesMap;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return this.getFieldValuesMap().get(fieldName);
    }

    public String toString() {
        return PATH_TEMPLATE.instantiate("organization", this.organization);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof OrganizationName) {
            OrganizationName that = (OrganizationName)o;
            return this.organization.equals(that.organization);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.organization.hashCode();
    }

    public static class Builder {
        private String organization;

        public String getOrganization() {
            return this.organization;
        }

        public Builder setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        private Builder() {
        }

        private Builder(OrganizationName organizationName) {
            this.organization = organizationName.organization;
        }

        public OrganizationName build() {
            return new OrganizationName(this);
        }
    }
}

