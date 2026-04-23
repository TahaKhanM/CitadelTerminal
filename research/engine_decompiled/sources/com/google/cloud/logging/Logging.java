/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.api.core.ApiFuture;
import com.google.api.gax.paging.AsyncPage;
import com.google.api.gax.paging.Page;
import com.google.cloud.MonitoredResource;
import com.google.cloud.MonitoredResourceDescriptor;
import com.google.cloud.Service;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Metric;
import com.google.cloud.logging.MetricInfo;
import com.google.cloud.logging.Option;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.Sink;
import com.google.cloud.logging.SinkInfo;
import com.google.cloud.logging.Synchronicity;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public interface Logging
extends AutoCloseable,
Service<LoggingOptions> {
    public void setWriteSynchronicity(Synchronicity var1);

    public Synchronicity getWriteSynchronicity();

    public void setFlushSeverity(Severity var1);

    public Severity getFlushSeverity();

    public Sink create(SinkInfo var1);

    public ApiFuture<Sink> createAsync(SinkInfo var1);

    public Sink update(SinkInfo var1);

    public ApiFuture<Sink> updateAsync(SinkInfo var1);

    public Sink getSink(String var1);

    public ApiFuture<Sink> getSinkAsync(String var1);

    public Page<Sink> listSinks(ListOption ... var1);

    public ApiFuture<AsyncPage<Sink>> listSinksAsync(ListOption ... var1);

    public boolean deleteSink(String var1);

    public ApiFuture<Boolean> deleteSinkAsync(String var1);

    public boolean deleteLog(String var1);

    public ApiFuture<Boolean> deleteLogAsync(String var1);

    public Page<MonitoredResourceDescriptor> listMonitoredResourceDescriptors(ListOption ... var1);

    public ApiFuture<AsyncPage<MonitoredResourceDescriptor>> listMonitoredResourceDescriptorsAsync(ListOption ... var1);

    public Metric create(MetricInfo var1);

    public ApiFuture<Metric> createAsync(MetricInfo var1);

    public Metric update(MetricInfo var1);

    public ApiFuture<Metric> updateAsync(MetricInfo var1);

    public Metric getMetric(String var1);

    public ApiFuture<Metric> getMetricAsync(String var1);

    public Page<Metric> listMetrics(ListOption ... var1);

    public ApiFuture<AsyncPage<Metric>> listMetricsAsync(ListOption ... var1);

    public boolean deleteMetric(String var1);

    public ApiFuture<Boolean> deleteMetricAsync(String var1);

    public void flush();

    public void write(Iterable<LogEntry> var1, WriteOption ... var2);

    public Page<LogEntry> listLogEntries(EntryListOption ... var1);

    public ApiFuture<AsyncPage<LogEntry>> listLogEntriesAsync(EntryListOption ... var1);

    public static final class EntryListOption
    extends Option {
        private static final long serialVersionUID = -1561159676386917050L;

        private EntryListOption(Option.OptionType option, Object value) {
            super(option, value);
        }

        public static EntryListOption pageSize(int pageSize) {
            return new EntryListOption(ListOption.OptionType.PAGE_SIZE, pageSize);
        }

        public static EntryListOption pageToken(String pageToken) {
            return new EntryListOption(ListOption.OptionType.PAGE_TOKEN, pageToken);
        }

        public static EntryListOption sortOrder(SortingField field2, SortingOrder order) {
            return new EntryListOption(OptionType.ORDER_BY, field2.selector() + ' ' + order.selector());
        }

        public static EntryListOption filter(String filter2) {
            return new EntryListOption(OptionType.FILTER, filter2);
        }

        static enum OptionType implements Option.OptionType
        {
            ORDER_BY,
            FILTER;


            <T> T get(Map<Option.OptionType, ?> options) {
                return (T)options.get(this);
            }
        }
    }

    public static enum SortingOrder {
        DESCENDING("desc"),
        ASCENDING("asc");

        private final String selector;

        private SortingOrder(String selector) {
            this.selector = selector;
        }

        String selector() {
            return this.selector;
        }
    }

    public static enum SortingField {
        TIMESTAMP;


        String selector() {
            return this.name().toLowerCase();
        }
    }

    public static final class WriteOption
    extends Option {
        private static final long serialVersionUID = 715900132268584612L;

        private WriteOption(OptionType option, Object value) {
            super(option, value);
        }

        public static WriteOption logName(String logName) {
            return new WriteOption(OptionType.LOG_NAME, (Object)logName);
        }

        public static WriteOption resource(MonitoredResource resource) {
            return new WriteOption(OptionType.RESOURCE, (Object)resource);
        }

        public static WriteOption labels(Map<String, String> labels) {
            return new WriteOption(OptionType.LABELS, ImmutableMap.copyOf(labels));
        }

        static enum OptionType implements Option.OptionType
        {
            LOG_NAME,
            RESOURCE,
            LABELS;


            <T> T get(Map<Option.OptionType, ?> options) {
                return (T)options.get(this);
            }
        }
    }

    public static final class ListOption
    extends Option {
        private static final long serialVersionUID = -6857294816115909271L;

        private ListOption(OptionType option, Object value) {
            super(option, value);
        }

        public static ListOption pageSize(int pageSize) {
            return new ListOption(OptionType.PAGE_SIZE, (Object)pageSize);
        }

        public static ListOption pageToken(String pageToken) {
            return new ListOption(OptionType.PAGE_TOKEN, (Object)pageToken);
        }

        static enum OptionType implements Option.OptionType
        {
            PAGE_SIZE,
            PAGE_TOKEN;


            <T> T get(Map<Option.OptionType, ?> options) {
                return (T)options.get(this);
            }
        }
    }
}

