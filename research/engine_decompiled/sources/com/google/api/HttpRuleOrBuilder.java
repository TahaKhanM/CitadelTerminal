/*
 * Decompiled with CFR 0.152.
 */
package com.google.api;

import com.google.api.CustomHttpPattern;
import com.google.api.CustomHttpPatternOrBuilder;
import com.google.api.HttpRule;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface HttpRuleOrBuilder
extends MessageOrBuilder {
    public String getSelector();

    public ByteString getSelectorBytes();

    public String getGet();

    public ByteString getGetBytes();

    public String getPut();

    public ByteString getPutBytes();

    public String getPost();

    public ByteString getPostBytes();

    public String getDelete();

    public ByteString getDeleteBytes();

    public String getPatch();

    public ByteString getPatchBytes();

    public boolean hasCustom();

    public CustomHttpPattern getCustom();

    public CustomHttpPatternOrBuilder getCustomOrBuilder();

    public String getBody();

    public ByteString getBodyBytes();

    public List<HttpRule> getAdditionalBindingsList();

    public HttpRule getAdditionalBindings(int var1);

    public int getAdditionalBindingsCount();

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList();

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int var1);

    public HttpRule.PatternCase getPatternCase();
}

