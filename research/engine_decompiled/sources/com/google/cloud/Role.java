/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;

public final class Role
implements Serializable {
    private static final long serialVersionUID = -7779252712160972508L;
    private static final String ROLE_PREFIX = "roles/";
    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Role viewer() {
        return Role.of("viewer");
    }

    public static Role editor() {
        return Role.of("editor");
    }

    public static Role owner() {
        return Role.of("owner");
    }

    public static Role of(String value) {
        Preconditions.checkNotNull(value);
        if (!value.contains("/")) {
            value = ROLE_PREFIX + value;
        }
        return new Role(value);
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    public String toString() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return obj instanceof Role && Objects.equals(this.value, ((Role)obj).getValue());
    }
}

