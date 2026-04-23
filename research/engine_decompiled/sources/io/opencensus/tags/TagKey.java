/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import com.google.common.base.Preconditions;
import io.opencensus.internal.StringUtil;
import io.opencensus.tags.AutoValue_TagKey;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TagKey {
    public static final int MAX_LENGTH = 255;

    TagKey() {
    }

    public static TagKey create(String name) {
        Preconditions.checkArgument(TagKey.isValid(name));
        return new AutoValue_TagKey(name);
    }

    public abstract String getName();

    private static boolean isValid(String name) {
        return !name.isEmpty() && name.length() <= 255 && StringUtil.isPrintableString(name);
    }
}

