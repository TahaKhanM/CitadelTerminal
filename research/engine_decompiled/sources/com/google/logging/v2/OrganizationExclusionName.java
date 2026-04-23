/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.ExclusionName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizationExclusionName
extends ExclusionName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("organizations/{organization}/exclusions/{exclusion}");
    private volatile Map<String, String> fieldValuesMap;
    private final String organization;
    private final String exclusion;

    public String getOrganization() {
        return this.organization;
    }

    public String getExclusion() {
        return this.exclusion;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private OrganizationExclusionName(Builder builder) {
        this.organization = Preconditions.checkNotNull(builder.getOrganization());
        this.exclusion = Preconditions.checkNotNull(builder.getExclusion());
    }

    public static OrganizationExclusionName of(String organization, String exclusion) {
        return OrganizationExclusionName.newBuilder().setOrganization(organization).setExclusion(exclusion).build();
    }

    public static String format(String organization, String exclusion) {
        return OrganizationExclusionName.newBuilder().setOrganization(organization).setExclusion(exclusion).build().toString();
    }

    public static OrganizationExclusionName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "OrganizationExclusionName.parse: formattedString not in valid format");
        return OrganizationExclusionName.of(matchMap.get("organization"), matchMap.get("exclusion"));
    }

    public static List<OrganizationExclusionName> parseList(List<String> formattedStrings) {
        ArrayList<OrganizationExclusionName> list2 = new ArrayList<OrganizationExclusionName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(OrganizationExclusionName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<OrganizationExclusionName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (OrganizationExclusionName value : values) {
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
            OrganizationExclusionName organizationExclusionName = this;
            synchronized (organizationExclusionName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("organization", this.organization);
                    fieldMapBuilder.put("exclusion", this.exclusion);
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
        return PATH_TEMPLATE.instantiate("organization", this.organization, "exclusion", this.exclusion);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof OrganizationExclusionName) {
            OrganizationExclusionName that = (OrganizationExclusionName)o;
            return this.organization.equals(that.organization) && this.exclusion.equals(that.exclusion);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.organization.hashCode();
        h *= 1000003;
        return h ^= this.exclusion.hashCode();
    }

    public static class Builder {
        private String organization;
        private String exclusion;

        public String getOrganization() {
            return this.organization;
        }

        public String getExclusion() {
            return this.exclusion;
        }

        public Builder setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public Builder setExclusion(String exclusion) {
            this.exclusion = exclusion;
            return this;
        }

        private Builder() {
        }

        private Builder(OrganizationExclusionName organizationExclusionName) {
            this.organization = organizationExclusionName.organization;
            this.exclusion = organizationExclusionName.exclusion;
        }

        public OrganizationExclusionName build() {
            return new OrganizationExclusionName(this);
        }
    }
}

