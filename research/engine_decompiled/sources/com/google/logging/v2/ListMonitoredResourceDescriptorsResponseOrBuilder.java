/*
 * Decompiled with CFR 0.152.
 */
package com.google.logging.v2;

import com.google.api.MonitoredResourceDescriptor;
import com.google.api.MonitoredResourceDescriptorOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ListMonitoredResourceDescriptorsResponseOrBuilder
extends MessageOrBuilder {
    public List<MonitoredResourceDescriptor> getResourceDescriptorsList();

    public MonitoredResourceDescriptor getResourceDescriptors(int var1);

    public int getResourceDescriptorsCount();

    public List<? extends MonitoredResourceDescriptorOrBuilder> getResourceDescriptorsOrBuilderList();

    public MonitoredResourceDescriptorOrBuilder getResourceDescriptorsOrBuilder(int var1);

    public String getNextPageToken();

    public ByteString getNextPageTokenBytes();
}

