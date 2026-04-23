/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import com.google.common.base.Preconditions;
import io.opencensus.internal.StringUtil;
import io.opencensus.tags.AutoValue_TagValue;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TagValue {
    public static final int MAX_LENGTH = 255;

    TagValue() {
    }

    public static TagValue create(String value) {
        Preconditions.checkArgument(TagValue.isValid(value));
        return new AutoValue_TagValue(value);
    }

    public abstract String asString();

    private static boolean isValid(String value) {
        return value.length() <= 255 && StringUtil.isPrintableString(value);
    }
}

