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

public class BillingName
extends ParentName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("billingAccounts/{billing_account}");
    private volatile Map<String, String> fieldValuesMap;
    private final String billingAccount;

    public String getBillingAccount() {
        return this.billingAccount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private BillingName(Builder builder) {
        this.billingAccount = Preconditions.checkNotNull(builder.getBillingAccount());
    }

    public static BillingName of(String billingAccount) {
        return BillingName.newBuilder().setBillingAccount(billingAccount).build();
    }

    public static String format(String billingAccount) {
        return BillingName.newBuilder().setBillingAccount(billingAccount).build().toString();
    }

    public static BillingName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "BillingName.parse: formattedString not in valid format");
        return BillingName.of(matchMap.get("billing_account"));
    }

    public static List<BillingName> parseList(List<String> formattedStrings) {
        ArrayList<BillingName> list2 = new ArrayList<BillingName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(BillingName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<BillingName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (BillingName value : values) {
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
            BillingName billingName = this;
            synchronized (billingName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("billingAccount", this.billingAccount);
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
        return PATH_TEMPLATE.instantiate("billing_account", this.billingAccount);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BillingName) {
            BillingName that = (BillingName)o;
            return this.billingAccount.equals(that.billingAccount);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        return h ^= this.billingAccount.hashCode();
    }

    public static class Builder {
        private String billingAccount;

        public String getBillingAccount() {
            return this.billingAccount;
        }

        public Builder setBillingAccount(String billingAccount) {
            this.billingAccount = billingAccount;
            return this;
        }

        private Builder() {
        }

        private Builder(BillingName billingName) {
            this.billingAccount = billingName.billingAccount;
        }

        public BillingName build() {
            return new BillingName(this);
        }
    }
}

