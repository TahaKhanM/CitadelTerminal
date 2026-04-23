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

public class BillingLogName
extends LogName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("billingAccounts/{billing_account}/logs/{log}");
    private volatile Map<String, String> fieldValuesMap;
    private final String billingAccount;
    private final String log;

    public String getBillingAccount() {
        return this.billingAccount;
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

    private BillingLogName(Builder builder) {
        this.billingAccount = Preconditions.checkNotNull(builder.getBillingAccount());
        this.log = Preconditions.checkNotNull(builder.getLog());
    }

    public static BillingLogName of(String billingAccount, String log) {
        return BillingLogName.newBuilder().setBillingAccount(billingAccount).setLog(log).build();
    }

    public static String format(String billingAccount, String log) {
        return BillingLogName.newBuilder().setBillingAccount(billingAccount).setLog(log).build().toString();
    }

    public static BillingLogName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "BillingLogName.parse: formattedString not in valid format");
        return BillingLogName.of(matchMap.get("billing_account"), matchMap.get("log"));
    }

    public static List<BillingLogName> parseList(List<String> formattedStrings) {
        ArrayList<BillingLogName> list2 = new ArrayList<BillingLogName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(BillingLogName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<BillingLogName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (BillingLogName value : values) {
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
            BillingLogName billingLogName = this;
            synchronized (billingLogName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("billingAccount", this.billingAccount);
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
        return PATH_TEMPLATE.instantiate("billing_account", this.billingAccount, "log", this.log);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BillingLogName) {
            BillingLogName that = (BillingLogName)o;
            return this.billingAccount.equals(that.billingAccount) && this.log.equals(that.log);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.billingAccount.hashCode();
        h *= 1000003;
        return h ^= this.log.hashCode();
    }

    public static class Builder {
        private String billingAccount;
        private String log;

        public String getBillingAccount() {
            return this.billingAccount;
        }

        public String getLog() {
            return this.log;
        }

        public Builder setBillingAccount(String billingAccount) {
            this.billingAccount = billingAccount;
            return this;
        }

        public Builder setLog(String log) {
            this.log = log;
            return this;
        }

        private Builder() {
        }

        private Builder(BillingLogName billingLogName) {
            this.billingAccount = billingLogName.billingAccount;
            this.log = billingLogName.log;
        }

        public BillingLogName build() {
            return new BillingLogName(this);
        }
    }
}

