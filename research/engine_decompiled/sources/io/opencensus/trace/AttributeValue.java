/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.base.Preconditions;
import io.opencensus.common.Function;
import io.opencensus.trace.AutoValue_AttributeValue_AttributeValueBoolean;
import io.opencensus.trace.AutoValue_AttributeValue_AttributeValueLong;
import io.opencensus.trace.AutoValue_AttributeValue_AttributeValueString;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class AttributeValue {
    public static AttributeValue stringAttributeValue(String stringValue) {
        return AttributeValueString.create(stringValue);
    }

    public static AttributeValue booleanAttributeValue(boolean booleanValue) {
        return AttributeValueBoolean.create(booleanValue);
    }

    public static AttributeValue longAttributeValue(long longValue) {
        return AttributeValueLong.create(longValue);
    }

    AttributeValue() {
    }

    public abstract <T> T match(Function<? super String, T> var1, Function<? super Boolean, T> var2, Function<? super Long, T> var3, Function<Object, T> var4);

    @Immutable
    static abstract class AttributeValueLong
    extends AttributeValue {
        AttributeValueLong() {
        }

        static AttributeValue create(Long stringValue) {
            return new AutoValue_AttributeValue_AttributeValueLong(Preconditions.checkNotNull(stringValue, "stringValue"));
        }

        @Override
        public final <T> T match(Function<? super String, T> stringFunction, Function<? super Boolean, T> booleanFunction, Function<? super Long, T> longFunction, Function<Object, T> defaultFunction) {
            return longFunction.apply(this.getLongValue());
        }

        abstract Long getLongValue();
    }

    @Immutable
    static abstract class AttributeValueBoolean
    extends AttributeValue {
        AttributeValueBoolean() {
        }

        static AttributeValue create(Boolean stringValue) {
            return new AutoValue_AttributeValue_AttributeValueBoolean(Preconditions.checkNotNull(stringValue, "stringValue"));
        }

        @Override
        public final <T> T match(Function<? super String, T> stringFunction, Function<? super Boolean, T> booleanFunction, Function<? super Long, T> longFunction, Function<Object, T> defaultFunction) {
            return booleanFunction.apply(this.getBooleanValue());
        }

        abstract Boolean getBooleanValue();
    }

    @Immutable
    static abstract class AttributeValueString
    extends AttributeValue {
        AttributeValueString() {
        }

        static AttributeValue create(String stringValue) {
            return new AutoValue_AttributeValue_AttributeValueString(Preconditions.checkNotNull(stringValue, "stringValue"));
        }

        @Override
        public final <T> T match(Function<? super String, T> stringFunction, Function<? super Boolean, T> booleanFunction, Function<? super Long, T> longFunction, Function<Object, T> defaultFunction) {
            return stringFunction.apply(this.getStringValue());
        }

        abstract String getStringValue();
    }
}

