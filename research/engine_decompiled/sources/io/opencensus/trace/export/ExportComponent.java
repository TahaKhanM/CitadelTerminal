/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.export;

import io.opencensus.trace.export.RunningSpanStore;
import io.opencensus.trace.export.SampledSpanStore;
import io.opencensus.trace.export.SpanExporter;

public abstract class ExportComponent {
    public static ExportComponent newNoopExportComponent() {
        return new NoopExportComponent();
    }

    public abstract SpanExporter getSpanExporter();

    public abstract RunningSpanStore getRunningSpanStore();

    public abstract SampledSpanStore getSampledSpanStore();

    private static final class NoopExportComponent
    extends ExportComponent {
        private final SampledSpanStore noopSampledSpanStore = SampledSpanStore.newNoopSampledSpanStore();

        private NoopExportComponent() {
        }

        @Override
        public SpanExporter getSpanExporter() {
            return SpanExporter.getNoopSpanExporter();
        }

        @Override
        public RunningSpanStore getRunningSpanStore() {
            return RunningSpanStore.getNoopRunningSpanStore();
        }

        @Override
        public SampledSpanStore getSampledSpanStore() {
            return this.noopSampledSpanStore;
        }
    }
}

