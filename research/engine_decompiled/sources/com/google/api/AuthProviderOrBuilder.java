/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AuthProviderOrBuilder
extends MessageOrBuilder {
    public String getId();

    public ByteString getIdBytes();

    public String getIssuer();

    public ByteString getIssuerBytes();

    public String getJwksUri();

    public ByteString getJwksUriBytes();

    public String getAudiences();

    public ByteString getAudiencesBytes();

    public String getAuthorizationUrl();

    public ByteString getAuthorizationUrlBytes();
}

