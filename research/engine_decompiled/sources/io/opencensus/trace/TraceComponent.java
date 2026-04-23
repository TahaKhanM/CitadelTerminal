/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import io.opencensus.common.Clock;
import io.opencensus.internal.ZeroTimeClock;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.export.ExportComponent;
import io.opencensus.trace.propagation.PropagationComponent;

public abstract class TraceComponent {
    public abstract Tracer getTracer();

    public abstract PropagationComponent getPropagationComponent();

    public abstract Clock getClock();

    public abstract ExportComponent getExportComponent();

    public abstract TraceConfig getTraceConfig();

    static TraceComponent newNoopTraceComponent() {
        return new NoopTraceComponent();
    }

    private static final class NoopTraceComponent
    extends TraceComponent {
        private final ExportComponent noopExportComponent = ExportComponent.newNoopExportComponent();

        @Override
        public Tracer getTracer() {
            return Tracer.getNoopTracer();
        }

        @Override
        public PropagationComponent getPropagationComponent() {
            return PropagationComponent.getNoopPropagationComponent();
        }

        @Override
        public Clock getClock() {
            return ZeroTimeClock.getInstance();
        }

        @Override
        public ExportComponent getExportComponent() {
            return this.noopExportComponent;
        }

        @Override
        public TraceConfig getTraceConfig() {
            return TraceConfig.getNoopTraceConfig();
        }

        private NoopTraceComponent() {
        }
    }
}

