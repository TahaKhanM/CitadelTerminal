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

public class BillingSinkName
extends SinkName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("billingAccounts/{billing_account}/sinks/{sink}");
    private volatile Map<String, String> fieldValuesMap;
    private final String billingAccount;
    private final String sink;

    public String getBillingAccount() {
        return this.billingAccount;
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

    private BillingSinkName(Builder builder) {
        this.billingAccount = Preconditions.checkNotNull(builder.getBillingAccount());
        this.sink = Preconditions.checkNotNull(builder.getSink());
    }

    public static BillingSinkName of(String billingAccount, String sink) {
        return BillingSinkName.newBuilder().setBillingAccount(billingAccount).setSink(sink).build();
    }

    public static String format(String billingAccount, String sink) {
        return BillingSinkName.newBuilder().setBillingAccount(billingAccount).setSink(sink).build().toString();
    }

    public static BillingSinkName parse(String formattedString) {
        if (formattedString.isEmpty()) {
            return null;
        }
        Map<String, String> matchMap = PATH_TEMPLATE.validatedMatch(formattedString, "BillingSinkName.parse: formattedString not in valid format");
        return BillingSinkName.of(matchMap.get("billing_account"), matchMap.get("sink"));
    }

    public static List<BillingSinkName> parseList(List<String> formattedStrings) {
        ArrayList<BillingSinkName> list2 = new ArrayList<BillingSinkName>(formattedStrings.size());
        for (String formattedString : formattedStrings) {
            list2.add(BillingSinkName.parse(formattedString));
        }
        return list2;
    }

    public static List<String> toStringList(List<BillingSinkName> values) {
        ArrayList<String> list2 = new ArrayList<String>(values.size());
        for (BillingSinkName value : values) {
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
            BillingSinkName billingSinkName = this;
            synchronized (billingSinkName) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder<String, String> fieldMapBuilder = ImmutableMap.builder();
                    fieldMapBuilder.put("billingAccount", this.billingAccount);
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
        return PATH_TEMPLATE.instantiate("billing_account", this.billingAccount, "sink", this.sink);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof BillingSinkName) {
            BillingSinkName that = (BillingSinkName)o;
            return this.billingAccount.equals(that.billingAccount) && this.sink.equals(that.sink);
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.billingAccount.hashCode();
        h *= 1000003;
        return h ^= this.sink.hashCode();
    }

    public static class Builder {
        private String billingAccount;
        private String sink;

        public String getBillingAccount() {
            return this.billingAccount;
        }

        public String getSink() {
            return this.sink;
        }

        public Builder setBillingAccount(String billingAccount) {
            this.billingAccount = billingAccount;
            return this;
        }

        public Builder setSink(String sink) {
            this.sink = sink;
            return this;
        }

        private Builder() {
        }

        private Builder(BillingSinkName billingSinkName) {
            this.billingAccount = billingSinkName.billingAccount;
            this.sink = billingSinkName.sink;
        }

        public BillingSinkName build() {
            return new BillingSinkName(this);
        }
    }
}

