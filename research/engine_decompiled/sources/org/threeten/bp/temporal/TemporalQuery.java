/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.temporal;

import org.threeten.bp.temporal.TemporalAccessor;

public interface TemporalQuery<R> {
    public R queryFrom(TemporalAccessor var1);
}

