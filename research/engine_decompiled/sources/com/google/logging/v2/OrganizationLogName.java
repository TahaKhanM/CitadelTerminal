/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.LogName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizationLogName
extends LogName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("organizations/{organization}/logs/{log}");
    private volatile Map<String, String> fieldValuesMap;
    private final String organization;
    private final String log;

    public String getOrganization() {
        return this.organization;
    }

    public String getLog() {
        return this.log;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private OrganizationLogName(Builder builder) {
        this.organization = Preconditions.checkNotNull(builder.getOrganization());
        this.log = Preconditions.checkNotNull(builder.getLog());
    }

    public static OrganizationLogName of(String organization, String log) {
        return OrganizationLogName.newBuilder().setOrganization(organization).setLog(log).build();
    }

    public static String format(String organization, String log) {
        return OrganizationLogName.newBuilder().setOrganization(organization).setLog(log).build().toString();
    }

    public static OrganizationLogName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "OrganizationLogName.parse: formattedString not in valid format");
        return OrganizationLogName.of(matchMap.get("organization"), matchMap.get("log"));
    }

    public static List<OrganizationLogName> parseList(List<String> formattedStrings) {
        ArrayList<OrganizationLogName> list2 = new ArrayList<OrganizationLogName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(OrganizationLogName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<OrganizationLogName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (OrganizationLogName value : values) {
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
            OrganizationLogName organizationLogName = this;
            synchronized (organizationLogName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("organization", this.organization);
                    fieldMapBuilder.put("log", this.log);
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
        return PATH_TEMPLATE.instantiate("organization", this.organization, "log", this.log);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof OrganizationLogName) {
            OrganizationLogName that = (OrganizationLogName)o;
            return this.organization.equals(that.organization) && this.log.equals(that.log);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.organization.hashCode();
        h *= 1000003;
        return h ^= this.log.hashCode();
    }

    public static class Builder {
        private String organization;
        private String log;

        public String getOrganization() {
            return this.organization;
        }

        public String getLog() {
            return this.log;
        }

        public Builder setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        private Builder() {
        }

        private Builder(OrganizationLogName organizationLogName) {
            this.organization = organizationLogName.organization;
            this.log = organizationLogName.log;
        }

        public OrganizationLogName build() {
            return new OrganizationLogName(this);
        }
    }
}

