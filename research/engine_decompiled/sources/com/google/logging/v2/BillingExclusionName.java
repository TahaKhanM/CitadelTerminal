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

public class BillingExclusionName
extends ExclusionName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("billingAccounts/{billing_account}/exclusions/{exclusion}");
    private volatile Map<String, String> fieldValuesMap;
    private final String billingAccount;
    private final String exclusion;

    public String getBillingAccount() {
        return this.billingAccount;
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

    private BillingExclusionName(Builder builder) {
        this.billingAccount = Preconditions.checkNotNull(builder.getBillingAccount());
        this.exclusion = Preconditions.checkNotNull(builder.getExclusion());
    }

    public static BillingExclusionName of(String billingAccount, String exclusion) {
        return BillingExclusionName.newBuilder().setBillingAccount(billingAccount).setExclusion(exclusion).build();
    }

    public static String format(String billingAccount, String exclusion) {
        return BillingExclusionName.newBuilder().setBillingAccount(billingAccount).setExclusion(exclusion).build().toString();
    }

    public static BillingExclusionName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "BillingExclusionName.parse: formattedString not in valid format");
        return BillingExclusionName.of(matchMap.get("billing_account"), matchMap.get("exclusion"));
    }

    public static List<BillingExclusionName> parseList(List<String> formattedStrings) {
        ArrayList<BillingExclusionName> list2 = new ArrayList<BillingExclusionName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(BillingExclusionName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<BillingExclusionName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (BillingExclusionName value : values) {
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
            BillingExclusionName billingExclusionName = this;
            synchronized (billingExclusionName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("billingAccount", this.billingAccount);
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
        return PATH_TEMPLATE.instantiate("billing_account", this.billingAccount, "exclusion", this.exclusion);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BillingExclusionName) {
            BillingExclusionName that = (BillingExclusionName)o;
            return this.billingAccount.equals(that.billingAccount) && this.exclusion.equals(that.exclusion);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.billingAccount.hashCode();
        h *= 1000003;
        return h ^= this.exclusion.hashCode();
    }

    public static class Builder {
        private String billingAccount;
        private String exclusion;

        public String getBillingAccount() {
            return this.billingAccount;
        }

        public String getExclusion() {
            return this.exclusion;
        }

        public Builder setBillingAccount(String billingAccount) {
            this.billingAccount = billingAccount;
            return this;
        }

        public Builder setExclusion(String exclusion) {
            this.exclusion = exclusion;
            return this;
        }

        private Builder() {
        }

        private Builder(BillingExclusionName billingExclusionName) {
            this.billingAccount = billingExclusionName.billingAccount;
            this.exclusion = billingExclusionName.exclusion;
        }

        public BillingExclusionName build() {
            return new BillingExclusionName(this);
        }
    }
}

