/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.ipfilter;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.ipfilter.AbstractRemoteAddressFilter;
import io.grpc.netty.shaded.io.netty.handler.ipfilter.IpFilterRule;
import io.grpc.netty.shaded.io.netty.handler.ipfilter.IpFilterRuleType;
import java.net.InetSocketAddress;

@ChannelHandler.Sharable
public class RuleBasedIpFilter
extends AbstractRemoteAddressFilter<InetSocketAddress> {
    private final IpFilterRule[] rules;

    public RuleBasedIpFilter(IpFilterRule ... rules) {
        if (rules == null) {
            throw new NullPointerException("rules");
        }
        this.rules = rules;
    }

    @Override
    protected boolean accept(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) throws Exception {
        for (IpFilterRule rule : this.rules) {
            if (rule == null) break;
            if (!rule.matches(remoteAddress)) continue;
            return rule.ruleType() == IpFilterRuleType.ACCEPT;
        }
        return true;
    }
}

