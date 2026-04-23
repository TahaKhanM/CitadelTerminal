/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.tags;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import io.opencensus.tags.Tag;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class TagContext {
    protected abstract Iterator<Tag> getIterator();

    public String toString() {
        return "TagContext";
    }

    public boolean equals(@Nullable Object other) {
        if (!(other instanceof TagContext)) {
            return false;
        }
        TagContext otherTags = (TagContext)other;
        Iterator<Tag> iter1 = this.getIterator();
        Iterator<Tag> iter2 = otherTags.getIterator();
        Multiset tags1 = (Multiset)((Object)(iter1 == null ? ImmutableMultiset.of() : HashMultiset.create(Lists.newArrayList(iter1))));
        Multiset tags2 = (Multiset)((Object)(iter2 == null ? ImmutableMultiset.of() : HashMultiset.create(Lists.newArrayList(iter2))));
        return tags1.equals(tags2);
    }

    public final int hashCode() {
        int hashCode = 0;
        Iterator<Tag> i = this.getIterator();
        if (i == null) {
            return hashCode;
        }
        while (i.hasNext()) {
            Tag tag = i.next();
            if (tag == null) continue;
            hashCode += tag.hashCode();
        }
        return hashCode;
    }
}

