/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.InternalApi;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface FieldSelector {
    public String getSelector();

    @InternalApi
    public static class Helper {
        private static final String[] EMPTY_FIELDS = new String[0];
        private static final Function<FieldSelector, String> FIELD_TO_STRING_FUNCTION = new Function<FieldSelector, String>(){

            @Override
            public String apply(FieldSelector fieldSelector) {
                return fieldSelector.getSelector();
            }
        };

        private Helper() {
        }

        private static String selector(List<? extends FieldSelector> required, FieldSelector[] others, String ... extraResourceFields) {
            HashSet<String> fieldStrings = Sets.newHashSetWithExpectedSize(required.size() + others.length);
            fieldStrings.addAll(Lists.transform(required, FIELD_TO_STRING_FUNCTION));
            fieldStrings.addAll(Lists.transform(Arrays.asList(others), FIELD_TO_STRING_FUNCTION));
            fieldStrings.addAll(Arrays.asList(extraResourceFields));
            return Joiner.on(',').join(fieldStrings);
        }

        public static String selector(List<? extends FieldSelector> required, FieldSelector ... others) {
            return Helper.selector(required, others, new String[0]);
        }

        public static String listSelector(String containerName, List<? extends FieldSelector> required, FieldSelector ... others) {
            return "nextPageToken," + containerName + '(' + Helper.selector(required, others) + ')';
        }

        public static String listSelector(String containerName, List<? extends FieldSelector> required, FieldSelector[] others, String ... extraResourceFields) {
            return Helper.listSelector(EMPTY_FIELDS, containerName, required, others, extraResourceFields);
        }

        public static String listSelector(String[] topLevelFields, String containerName, List<? extends FieldSelector> required, FieldSelector[] others, String ... extraResourceFields) {
            HashSet<String> topLevelStrings = Sets.newHashSetWithExpectedSize(topLevelFields.length + 1);
            topLevelStrings.addAll(Lists.asList("nextPageToken", topLevelFields));
            return Joiner.on(',').join(topLevelStrings) + "," + containerName + '(' + Helper.selector(required, others, extraResourceFields) + ')';
        }
    }
}

