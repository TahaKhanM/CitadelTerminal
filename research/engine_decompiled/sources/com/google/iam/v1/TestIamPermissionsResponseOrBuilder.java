/*
 * Decompiled with CFR 0.152.
 */
package com.google.iam.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface TestIamPermissionsResponseOrBuilder
extends MessageOrBuilder {
    public List<String> getPermissionsList();

    public int getPermissionsCount();

    public String getPermissions(int var1);

    public ByteString getPermissionsBytes(int var1);
}

