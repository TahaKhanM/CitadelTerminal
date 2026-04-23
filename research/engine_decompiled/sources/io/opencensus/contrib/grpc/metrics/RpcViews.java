/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.contrib.grpc.metrics;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import io.opencensus.contrib.grpc.metrics.RpcViewConstants;
import io.opencensus.stats.Stats;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewManager;

public final class RpcViews {
    @VisibleForTesting
    static final ImmutableSet<View> RPC_CUMULATIVE_VIEWS_SET = ImmutableSet.of(RpcViewConstants.RPC_CLIENT_ERROR_COUNT_VIEW, RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_VIEW, new View[]{RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_VIEW, RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_VIEW, RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_VIEW, RpcViewConstants.RPC_CLIENT_STARTED_COUNT_CUMULATIVE_VIEW, RpcViewConstants.RPC_CLIENT_FINISHED_COUNT_CUMULATIVE_VIEW, RpcViewConstants.RPC_SERVER_ERROR_COUNT_VIEW, RpcViewConstants.RPC_SERVER_SERVER_LATENCY_VIEW, RpcViewConstants.RPC_SERVER_SERVER_ELAPSED_TIME_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_BYTES_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_COUNT_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_VIEW, RpcViewConstants.RPC_SERVER_STARTED_COUNT_CUMULATIVE_VIEW, RpcViewConstants.RPC_SERVER_FINISHED_COUNT_CUMULATIVE_VIEW});
    @VisibleForTesting
    static final ImmutableSet<View> RPC_INTERVAL_VIEWS_SET = ImmutableSet.of(RpcViewConstants.RPC_CLIENT_ERROR_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_MINUTE_VIEW, new View[]{RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_STARTED_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_FINISHED_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_ERROR_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_SERVER_LATENCY_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_SERVER_ELAPSED_TIME_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_STARTED_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_SERVER_FINISHED_COUNT_MINUTE_VIEW, RpcViewConstants.RPC_CLIENT_ERROR_COUNT_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_BYTES_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_BYTES_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_REQUEST_COUNT_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_RESPONSE_COUNT_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_SERVER_ELAPSED_TIME_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_STARTED_COUNT_HOUR_VIEW, RpcViewConstants.RPC_CLIENT_FINISHED_COUNT_HOUR_VIEW, RpcViewConstants.RPC_SERVER_ERROR_COUNT_HOUR_VIEW, RpcViewConstants.RPC_SERVER_SERVER_LATENCY_HOUR_VIEW, RpcViewConstants.RPC_SERVER_SERVER_ELAPSED_TIME_HOUR_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_BYTES_HOUR_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_BYTES_HOUR_VIEW, RpcViewConstants.RPC_SERVER_REQUEST_COUNT_HOUR_VIEW, RpcViewConstants.RPC_SERVER_RESPONSE_COUNT_HOUR_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES_HOUR_VIEW, RpcViewConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES_HOUR_VIEW, RpcViewConstants.RPC_SERVER_STARTED_COUNT_HOUR_VIEW, RpcViewConstants.RPC_SERVER_FINISHED_COUNT_HOUR_VIEW});

    public static void registerAllCumulativeViews() {
        RpcViews.registerAllCumulativeViews(Stats.getViewManager());
    }

    @VisibleForTesting
    static void registerAllCumulativeViews(ViewManager viewManager) {
        for (View view : RPC_CUMULATIVE_VIEWS_SET) {
            viewManager.registerView(view);
        }
    }

    public static void registerAllIntervalViews() {
        RpcViews.registerAllIntervalViews(Stats.getViewManager());
    }

    @VisibleForTesting
    static void registerAllIntervalViews(ViewManager viewManager) {
        for (View view : RPC_INTERVAL_VIEWS_SET) {
            viewManager.registerView(view);
        }
    }

    public static void registerAllViews() {
        RpcViews.registerAllViews(Stats.getViewManager());
    }

    @VisibleForTesting
    static void registerAllViews(ViewManager viewManager) {
        RpcViews.registerAllCumulativeViews(viewManager);
        RpcViews.registerAllIntervalViews(viewManager);
    }

    private RpcViews() {
    }
}

