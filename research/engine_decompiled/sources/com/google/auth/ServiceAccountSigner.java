/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth;

import java.util.Objects;

public interface ServiceAccountSigner {
    public String getAccount();

    public byte[] sign(byte[] var1);

    public static class SigningException
    extends RuntimeException {
        private static final long serialVersionUID = -6503954300538947223L;

        public SigningException(String message, Exception cause) {
            super(message, cause);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SigningException)) {
                return false;
            }
            SigningException other = (SigningException)obj;
            return Objects.equals(this.getCause(), other.getCause()) && Objects.equals(this.getMessage(), other.getMessage());
        }

        public int hashCode() {
            return Objects.hash(this.getMessage(), this.getCause());
        }
    }
}

