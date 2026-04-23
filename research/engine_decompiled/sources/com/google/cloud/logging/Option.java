/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;

abstract class Option
implements Serializable {
    private static final long serialVersionUID = -2326245820305140225L;
    private final OptionType optionType;
    private final Object value;

    Option(OptionType optionType, Object value) {
        this.optionType = Preconditions.checkNotNull(optionType);
        this.value = value;
    }

    <T extends OptionType> T getOptionType() {
        return (T)this.optionType;
    }

    Object getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Option)) {
            return false;
        }
        Option other = (Option)obj;
        return Objects.equals(this.optionType, other.optionType) && Objects.equals(this.value, other.value);
    }

    public int hashCode() {
        return Objects.hash(this.optionType, this.value);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.optionType.name()).add("value", this.value).toString();
    }

    static interface OptionType {
        public String name();
    }
}

