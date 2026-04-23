/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.common.base.Preconditions;
import java.io.Serializable;

public abstract class StringEnumValue
implements Serializable {
    private static final long serialVersionUID = 1501809419544297884L;
    private final String constant;

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected StringEnumValue(String constant) {
        this.constant = Preconditions.checkNotNull(constant);
    }

    public String name() {
        return this.constant;
    }

    public String toString() {
        return this.constant;
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (!this.getClass().equals(that.getClass())) {
            return false;
        }
        StringEnumValue thatEnumValue = (StringEnumValue)that;
        return this.constant.equals(thatEnumValue.constant);
    }

    public int hashCode() {
        return this.constant.hashCode();
    }
}

