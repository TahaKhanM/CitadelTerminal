/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.ApiFunction;
import com.google.cloud.StringEnumType;
import com.google.cloud.StringEnumValue;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;

public final class Identity
implements Serializable {
    private static final long serialVersionUID = -8181841964597657446L;
    private final Type type;
    private final String value;

    private Identity(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public static Identity allUsers() {
        return new Identity(Type.ALL_USERS, null);
    }

    public static Identity allAuthenticatedUsers() {
        return new Identity(Type.ALL_AUTHENTICATED_USERS, null);
    }

    public static Identity user(String email) {
        return new Identity(Type.USER, Preconditions.checkNotNull(email));
    }

    public static Identity serviceAccount(String email) {
        return new Identity(Type.SERVICE_ACCOUNT, Preconditions.checkNotNull(email));
    }

    public static Identity group(String email) {
        return new Identity(Type.GROUP, Preconditions.checkNotNull(email));
    }

    public static Identity domain(String domain) {
        return new Identity(Type.DOMAIN, Preconditions.checkNotNull(domain));
    }

    public static Identity projectOwner(String projectId) {
        return new Identity(Type.PROJECT_OWNER, Preconditions.checkNotNull(projectId));
    }

    public static Identity projectEditor(String projectId) {
        return new Identity(Type.PROJECT_EDITOR, Preconditions.checkNotNull(projectId));
    }

    public static Identity projectViewer(String projectId) {
        return new Identity(Type.PROJECT_VIEWER, Preconditions.checkNotNull(projectId));
    }

    public String toString() {
        return this.strValue();
    }

    public int hashCode() {
        return Objects.hash(this.value, this.type);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity other = (Identity)obj;
        return Objects.equals(this.value, other.getValue()) && Objects.equals(this.type, other.getType());
    }

    public String strValue() {
        String protobufString = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this.type.toString());
        if (this.value == null) {
            return protobufString;
        }
        return protobufString + ":" + this.value;
    }

    public static Identity valueOf(String identityStr) {
        String[] info2 = identityStr.split(":");
        Type type = Type.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, info2[0]));
        if (info2.length == 1) {
            return new Identity(type, null);
        }
        if (info2.length == 2) {
            return new Identity(type, info2[1]);
        }
        throw new IllegalArgumentException("Illegal identity string: \"" + identityStr + "\"");
    }

    public static final class Type
    extends StringEnumValue {
        private static final long serialVersionUID = 3809891273596003916L;
        private static final ApiFunction<String, Type> CONSTRUCTOR = new ApiFunction<String, Type>(){

            @Override
            public Type apply(String constant) {
                return new Type(constant);
            }
        };
        private static final StringEnumType<Type> type = new StringEnumType<Type>(Type.class, CONSTRUCTOR);
        public static final Type ALL_USERS = type.createAndRegister("ALL_USERS");
        public static final Type ALL_AUTHENTICATED_USERS = type.createAndRegister("ALL_AUTHENTICATED_USERS");
        public static final Type USER = type.createAndRegister("USER");
        public static final Type SERVICE_ACCOUNT = type.createAndRegister("SERVICE_ACCOUNT");
        public static final Type GROUP = type.createAndRegister("GROUP");
        public static final Type DOMAIN = type.createAndRegister("DOMAIN");
        public static final Type PROJECT_OWNER = type.createAndRegister("PROJECT_OWNER");
        public static final Type PROJECT_EDITOR = type.createAndRegister("PROJECT_EDITOR");
        public static final Type PROJECT_VIEWER = type.createAndRegister("PROJECT_VIEWER");

        private Type(String constant) {
            super(constant);
        }

        public static Type valueOfStrict(String constant) {
            return type.valueOfStrict(constant);
        }

        public static Type valueOf(String constant) {
            return type.valueOf(constant);
        }

        public static Type[] values() {
            return type.values();
        }
    }
}

