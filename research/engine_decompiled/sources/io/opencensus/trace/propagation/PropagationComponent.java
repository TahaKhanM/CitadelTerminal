/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace.propagation;

import io.opencensus.trace.propagation.BinaryFormat;
import io.opencensus.trace.propagation.TextFormat;

public abstract class PropagationComponent {
    private static final PropagationComponent NOOP_PROPAGATION_COMPONENT = new NoopPropagationComponent();

    public abstract BinaryFormat getBinaryFormat();

    public abstract TextFormat getB3Format();

    public static PropagationComponent getNoopPropagationComponent() {
        return NOOP_PROPAGATION_COMPONENT;
    }

    private static final class NoopPropagationComponent
    extends PropagationComponent {
        private NoopPropagationComponent() {
        }

        @Override
        public BinaryFormat getBinaryFormat() {
            return BinaryFormat.getNoopBinaryFormat();
        }

        @Override
        public TextFormat getB3Format() {
            return TextFormat.getNoopTextFormat();
        }
    }
}

