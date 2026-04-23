/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.stats;

import io.opencensus.stats.View;
import io.opencensus.stats.ViewData;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class ViewManager {
    public abstract void registerView(View var1);

    @Nullable
    public abstract ViewData getView(View.Name var1);

    public abstract Set<View> getAllExportedViews();
}

