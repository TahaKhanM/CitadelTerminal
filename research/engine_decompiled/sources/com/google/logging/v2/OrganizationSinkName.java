/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.pathtemplate.PathTemplate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.SinkName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizationSinkName
extends SinkName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("organizations/{organization}/sinks/{sink}");
    private volatile Map<String, String> fieldValuesMap;
    private final String organization;
    private final String sink;

    public String getOrganization() {
        return this.organization;
    }

    public String getSink() {
        return this.sink;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private OrganizationSinkName(Builder builder) {
        this.organization = Preconditions.checkNotNull(builder.getOrganization());
        this.sink = Preconditions.checkNotNull(builder.getSink());
    }

    public static OrganizationSinkName of(String organization, String sink) {
        return OrganizationSinkName.newBuilder().setOrganization(organization).setSink(sink).build();
    }

    public static String format(String organization, String sink) {
        return OrganizationSinkName.newBuilder().setOrganization(organization).setSink(sink).build().toString();
    }

    public static OrganizationSinkName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "OrganizationSinkName.parse: formattedString not in valid format");
        return OrganizationSinkName.of(matchMap.get("organization"), matchMap.get("sink"));
    }

    public static List<OrganizationSinkName> parseList(List<String> formattedStrings) {
        ArrayList<OrganizationSinkName> list2 = new ArrayList<OrganizationSinkName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(OrganizationSinkName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<OrganizationSinkName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (OrganizationSinkName value : values) {
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
            OrganizationSinkName organizationSinkName = this;
            synchronized (organizationSinkName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("organization", this.organization);
                    fieldMapBuilder.put("sink", this.sink);
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
        return PATH_TEMPLATE.instantiate("organization", this.organization, "sink", this.sink);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof OrganizationSinkName) {
            OrganizationSinkName that = (OrganizationSinkName)o;
            return this.organization.equals(that.organization) && this.sink.equals(that.sink);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.organization.hashCode();
        h *= 1000003;
        return h ^= this.sink.hashCode();
    }

    public static class Builder {
        private String organization;
        private String sink;

        public String getOrganization() {
            return this.organization;
        }

        public String getSink() {
            return this.sink;
        }

        public Builder setOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public Builder setSink(String sink) {
            this.sink = sink;
            return this;
        }

        private Builder() {
        }

        private Builder(OrganizationSinkName organizationSinkName) {
            this.organization = organizationSinkName.organization;
            this.sink = organizationSinkName.sink;
        }

        public OrganizationSinkName build() {
            return new OrganizationSinkName(this);
        }
    }
}

