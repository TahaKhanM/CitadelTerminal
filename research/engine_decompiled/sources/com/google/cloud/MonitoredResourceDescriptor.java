/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.LabelDescriptor;
import com.google.api.MonitoredResourceDescriptor;
import com.google.api.core.ApiFunction;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MonitoredResourceDescriptor
implements Serializable {
    private static final long serialVersionUID = -3702077512777687441L;
    public static final ApiFunction<com.google.api.MonitoredResourceDescriptor, MonitoredResourceDescriptor> FROM_PB_FUNCTION = new ApiFunction<com.google.api.MonitoredResourceDescriptor, MonitoredResourceDescriptor>(){

        @Override
        public MonitoredResourceDescriptor apply(com.google.api.MonitoredResourceDescriptor pb) {
            return MonitoredResourceDescriptor.fromPb(pb);
        }
    };
    private final String type;
    private final String name;
    private final String displayName;
    private final String description;
    private final List<LabelDescriptor> labels;

    MonitoredResourceDescriptor(Builder builder) {
        this.type = Preconditions.checkNotNull(builder.type);
        this.name = builder.name;
        this.displayName = builder.displayName;
        this.description = builder.description;
        this.labels = Preconditions.checkNotNull(builder.labels);
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public List<LabelDescriptor> getLabels() {
        return this.labels;
    }

    public final int hashCode() {
        return Objects.hash(this.type, this.name, this.displayName, this.description, this.labels);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(MonitoredResourceDescriptor.class)) {
            return false;
        }
        MonitoredResourceDescriptor other = (MonitoredResourceDescriptor)obj;
        return Objects.equals(this.type, other.type) && Objects.equals(this.name, other.name) && Objects.equals(this.displayName, other.displayName) && Objects.equals(this.description, other.description) && Objects.equals(this.labels, other.labels);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", this.type).add("name", this.name).add("displayName", this.displayName).add("description", this.description).add("labels", this.labels).toString();
    }

    public com.google.api.MonitoredResourceDescriptor toPb() {
        MonitoredResourceDescriptor.Builder builder = com.google.api.MonitoredResourceDescriptor.newBuilder().setType(this.type).addAllLabels(Iterables.transform(this.labels, LabelDescriptor.TO_PB_FUNCTION));
        if (this.name != null) {
            builder.setName(this.name);
        }
        if (this.displayName != null) {
            builder.setDisplayName(this.displayName);
        }
        if (this.description != null) {
            builder.setDescription(this.description);
        }
        return builder.build();
    }

    static Builder newBuilder(String type) {
        return new Builder(type);
    }

    public static MonitoredResourceDescriptor fromPb(com.google.api.MonitoredResourceDescriptor descriptorPb) {
        Builder builder = MonitoredResourceDescriptor.newBuilder(descriptorPb.getType());
        if (descriptorPb.getName() != null && !descriptorPb.getName().equals("")) {
            builder.setName(descriptorPb.getName());
        }
        if (descriptorPb.getDisplayName() != null && !descriptorPb.getDisplayName().equals("")) {
            builder.setDisplayName(descriptorPb.getDisplayName());
        }
        if (descriptorPb.getDescription() != null && !descriptorPb.getDescription().equals("")) {
            builder.setDescription(descriptorPb.getDescription());
        }
        builder.setLabels(Lists.transform(descriptorPb.getLabelsList(), LabelDescriptor.FROM_PB_FUNCTION));
        return builder.build();
    }

    static class Builder {
        private final String type;
        private String name;
        private String displayName;
        private String description;
        private List<LabelDescriptor> labels = new ArrayList<LabelDescriptor>();

        Builder(String type) {
            this.type = type;
        }

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        Builder setLabels(List<LabelDescriptor> labels) {
            this.labels = labels;
            return this;
        }

        MonitoredResourceDescriptor build() {
            return new MonitoredResourceDescriptor(this);
        }
    }

    public static class LabelDescriptor
    implements Serializable {
        private static final long serialVersionUID = -2811608103754481777L;
        private static final Function<com.google.api.LabelDescriptor, LabelDescriptor> FROM_PB_FUNCTION = new Function<com.google.api.LabelDescriptor, LabelDescriptor>(){

            @Override
            public LabelDescriptor apply(com.google.api.LabelDescriptor descriptorPb) {
                return LabelDescriptor.fromPb(descriptorPb);
            }
        };
        private static final Function<LabelDescriptor, com.google.api.LabelDescriptor> TO_PB_FUNCTION = new Function<LabelDescriptor, com.google.api.LabelDescriptor>(){

            @Override
            public com.google.api.LabelDescriptor apply(LabelDescriptor descriptor) {
                return descriptor.toPb();
            }
        };
        private final String key;
        private final ValueType valueType;
        private final String description;

        LabelDescriptor(String key, ValueType valueType, String description) {
            this.key = Preconditions.checkNotNull(key);
            this.valueType = Preconditions.checkNotNull(valueType);
            this.description = description;
        }

        public String getKey() {
            return this.key;
        }

        public ValueType getValueType() {
            return this.valueType;
        }

        public String getDescription() {
            return this.description;
        }

        public final int hashCode() {
            return Objects.hash(new Object[]{this.key, this.valueType, this.description});
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !obj.getClass().equals(LabelDescriptor.class)) {
                return false;
            }
            LabelDescriptor other = (LabelDescriptor)obj;
            return Objects.equals(this.key, other.key) && Objects.equals((Object)this.valueType, (Object)other.valueType) && Objects.equals(this.description, other.description);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("key", this.key).add("valueType", (Object)this.valueType).add("description", this.description).toString();
        }

        com.google.api.LabelDescriptor toPb() {
            LabelDescriptor.Builder builder = com.google.api.LabelDescriptor.newBuilder().setKey(this.key).setValueType(this.valueType.toPb());
            if (this.description != null) {
                builder.setDescription(this.description);
            }
            return builder.build();
        }

        static LabelDescriptor fromPb(com.google.api.LabelDescriptor descriptorPb) {
            String description = null;
            if (descriptorPb.getDescription() != null && !descriptorPb.getDescription().equals("")) {
                description = descriptorPb.getDescription();
            }
            return new LabelDescriptor(descriptorPb.getKey(), ValueType.fromPb(descriptorPb.getValueType()), description);
        }

        public static enum ValueType {
            STRING(LabelDescriptor.ValueType.STRING),
            BOOL(LabelDescriptor.ValueType.BOOL),
            INT64(LabelDescriptor.ValueType.INT64);

            private LabelDescriptor.ValueType typePb;

            private ValueType(LabelDescriptor.ValueType typePb) {
                this.typePb = typePb;
            }

            LabelDescriptor.ValueType toPb() {
                return this.typePb;
            }

            static ValueType fromPb(LabelDescriptor.ValueType typePb) {
                switch (typePb) {
                    case STRING: {
                        return STRING;
                    }
                    case BOOL: {
                        return BOOL;
                    }
                    case INT64: {
                        return INT64;
                    }
                }
                throw new IllegalArgumentException("Unrecognized label type");
            }
        }
    }
}

