/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.format;

import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.format.DateTimeTextProvider;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalField;

final class SimpleDateTimeTextProvider
extends DateTimeTextProvider {
    private static final ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> CACHE = new ConcurrentHashMap<Map.Entry<TemporalField, Locale>, Object>(16, 0.75f, 2);
    private static final Comparator<Map.Entry<String, Long>> COMPARATOR = new Comparator<Map.Entry<String, Long>>(){

        @Override
        public int compare(Map.Entry<String, Long> obj1, Map.Entry<String, Long> obj2) {
            return obj2.getKey().length() - obj1.getKey().length();
        }
    };

    SimpleDateTimeTextProvider() {
    }

    @Override
    public Locale[] getAvailableLocales() {
        return DateFormatSymbols.getAvailableLocales();
    }

    @Override
    public String getText(TemporalField field2, long value, TextStyle style, Locale locale) {
        Object store = this.findStore(field2, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore)store).getText(value, style);
        }
        return null;
    }

    @Override
    public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField field2, TextStyle style, Locale locale) {
        Object store = this.findStore(field2, locale);
        if (store instanceof LocaleStore) {
            return ((LocaleStore)store).getTextIterator(style);
        }
        return null;
    }

    private Object findStore(TemporalField field2, Locale locale) {
        Map.Entry<TemporalField, Locale> key = SimpleDateTimeTextProvider.createEntry(field2, locale);
        Object store = CACHE.get(key);
        if (store == null) {
            store = this.createStore(field2, locale);
            CACHE.putIfAbsent(key, store);
            store = CACHE.get(key);
        }
        return store;
    }

    private Object createStore(TemporalField field2, Locale locale) {
        if (field2 == ChronoField.MONTH_OF_YEAR) {
            DateFormatSymbols oldSymbols = DateFormatSymbols.getInstance(locale);
            HashMap<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long, String>>();
            Long f1 = 1L;
            Long f2 = 2L;
            Long f3 = 3L;
            Long f4 = 4L;
            Long f5 = 5L;
            Long f6 = 6L;
            Long f7 = 7L;
            Long f8 = 8L;
            Long f9 = 9L;
            Long f10 = 10L;
            Long f11 = 11L;
            Long f12 = 12L;
            String[] array = oldSymbols.getMonths();
            HashMap<Long, String> map2 = new HashMap<Long, String>();
            map2.put(f1, array[0]);
            map2.put(f2, array[1]);
            map2.put(f3, array[2]);
            map2.put(f4, array[3]);
            map2.put(f5, array[4]);
            map2.put(f6, array[5]);
            map2.put(f7, array[6]);
            map2.put(f8, array[7]);
            map2.put(f9, array[8]);
            map2.put(f10, array[9]);
            map2.put(f11, array[10]);
            map2.put(f12, array[11]);
            styleMap.put(TextStyle.FULL, map2);
            map2 = new HashMap();
            map2.put(f1, array[0].substring(0, 1));
            map2.put(f2, array[1].substring(0, 1));
            map2.put(f3, array[2].substring(0, 1));
            map2.put(f4, array[3].substring(0, 1));
            map2.put(f5, array[4].substring(0, 1));
            map2.put(f6, array[5].substring(0, 1));
            map2.put(f7, array[6].substring(0, 1));
            map2.put(f8, array[7].substring(0, 1));
            map2.put(f9, array[8].substring(0, 1));
            map2.put(f10, array[9].substring(0, 1));
            map2.put(f11, array[10].substring(0, 1));
            map2.put(f12, array[11].substring(0, 1));
            styleMap.put(TextStyle.NARROW, map2);
            array = oldSymbols.getShortMonths();
            map2 = new HashMap();
            map2.put(f1, array[0]);
            map2.put(f2, array[1]);
            map2.put(f3, array[2]);
            map2.put(f4, array[3]);
            map2.put(f5, array[4]);
            map2.put(f6, array[5]);
            map2.put(f7, array[6]);
            map2.put(f8, array[7]);
            map2.put(f9, array[8]);
            map2.put(f10, array[9]);
            map2.put(f11, array[10]);
            map2.put(f12, array[11]);
            styleMap.put(TextStyle.SHORT, map2);
            return SimpleDateTimeTextProvider.createLocaleStore(styleMap);
        }
        if (field2 == ChronoField.DAY_OF_WEEK) {
            DateFormatSymbols oldSymbols = DateFormatSymbols.getInstance(locale);
            HashMap<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long, String>>();
            Long f1 = 1L;
            Long f2 = 2L;
            Long f3 = 3L;
            Long f4 = 4L;
            Long f5 = 5L;
            Long f6 = 6L;
            Long f7 = 7L;
            String[] array = oldSymbols.getWeekdays();
            HashMap<Long, String> map3 = new HashMap<Long, String>();
            map3.put(f1, array[2]);
            map3.put(f2, array[3]);
            map3.put(f3, array[4]);
            map3.put(f4, array[5]);
            map3.put(f5, array[6]);
            map3.put(f6, array[7]);
            map3.put(f7, array[1]);
            styleMap.put(TextStyle.FULL, map3);
            map3 = new HashMap();
            map3.put(f1, array[2].substring(0, 1));
            map3.put(f2, array[3].substring(0, 1));
            map3.put(f3, array[4].substring(0, 1));
            map3.put(f4, array[5].substring(0, 1));
            map3.put(f5, array[6].substring(0, 1));
            map3.put(f6, array[7].substring(0, 1));
            map3.put(f7, array[1].substring(0, 1));
            styleMap.put(TextStyle.NARROW, map3);
            array = oldSymbols.getShortWeekdays();
            map3 = new HashMap();
            map3.put(f1, array[2]);
            map3.put(f2, array[3]);
            map3.put(f3, array[4]);
            map3.put(f4, array[5]);
            map3.put(f5, array[6]);
            map3.put(f6, array[7]);
            map3.put(f7, array[1]);
            styleMap.put(TextStyle.SHORT, map3);
            return SimpleDateTimeTextProvider.createLocaleStore(styleMap);
        }
        if (field2 == ChronoField.AMPM_OF_DAY) {
            DateFormatSymbols oldSymbols = DateFormatSymbols.getInstance(locale);
            HashMap<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long, String>>();
            String[] array = oldSymbols.getAmPmStrings();
            HashMap<Long, String> map4 = new HashMap<Long, String>();
            map4.put(0L, array[0]);
            map4.put(1L, array[1]);
            styleMap.put(TextStyle.FULL, map4);
            styleMap.put(TextStyle.SHORT, map4);
            return SimpleDateTimeTextProvider.createLocaleStore(styleMap);
        }
        if (field2 == ChronoField.ERA) {
            DateFormatSymbols oldSymbols = DateFormatSymbols.getInstance(locale);
            HashMap<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long, String>>();
            String[] array = oldSymbols.getEras();
            HashMap<Long, String> map5 = new HashMap<Long, String>();
            map5.put(0L, array[0]);
            map5.put(1L, array[1]);
            styleMap.put(TextStyle.SHORT, map5);
            if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                map5 = new HashMap();
                map5.put(0L, "Before Christ");
                map5.put(1L, "Anno Domini");
                styleMap.put(TextStyle.FULL, map5);
            } else {
                styleMap.put(TextStyle.FULL, map5);
            }
            map5 = new HashMap();
            map5.put(0L, array[0].substring(0, 1));
            map5.put(1L, array[1].substring(0, 1));
            styleMap.put(TextStyle.NARROW, map5);
            return SimpleDateTimeTextProvider.createLocaleStore(styleMap);
        }
        if (field2 == IsoFields.QUARTER_OF_YEAR) {
            HashMap<TextStyle, Map<Long, String>> styleMap = new HashMap<TextStyle, Map<Long, String>>();
            HashMap<Long, String> map6 = new HashMap<Long, String>();
            map6.put(1L, "Q1");
            map6.put(2L, "Q2");
            map6.put(3L, "Q3");
            map6.put(4L, "Q4");
            styleMap.put(TextStyle.SHORT, map6);
            map6 = new HashMap();
            map6.put(1L, "1st quarter");
            map6.put(2L, "2nd quarter");
            map6.put(3L, "3rd quarter");
            map6.put(4L, "4th quarter");
            styleMap.put(TextStyle.FULL, map6);
            return SimpleDateTimeTextProvider.createLocaleStore(styleMap);
        }
        return "";
    }

    private static <A, B> Map.Entry<A, B> createEntry(A text, B field2) {
        return new AbstractMap.SimpleImmutableEntry<A, B>(text, field2);
    }

    private static LocaleStore createLocaleStore(Map<TextStyle, Map<Long, String>> valueTextMap) {
        valueTextMap.put(TextStyle.FULL_STANDALONE, valueTextMap.get((Object)TextStyle.FULL));
        valueTextMap.put(TextStyle.SHORT_STANDALONE, valueTextMap.get((Object)TextStyle.SHORT));
        if (valueTextMap.containsKey((Object)TextStyle.NARROW) && !valueTextMap.containsKey((Object)TextStyle.NARROW_STANDALONE)) {
            valueTextMap.put(TextStyle.NARROW_STANDALONE, valueTextMap.get((Object)TextStyle.NARROW));
        }
        return new LocaleStore(valueTextMap);
    }

    static final class LocaleStore {
        private final Map<TextStyle, Map<Long, String>> valueTextMap;
        private final Map<TextStyle, List<Map.Entry<String, Long>>> parsable;

        LocaleStore(Map<TextStyle, Map<Long, String>> valueTextMap) {
            this.valueTextMap = valueTextMap;
            HashMap<TextStyle, List<Map.Entry<String, Long>>> map2 = new HashMap<TextStyle, List<Map.Entry<String, Long>>>();
            ArrayList allList = new ArrayList();
            for (TextStyle style : valueTextMap.keySet()) {
                HashMap<String, Map.Entry> reverse2 = new HashMap<String, Map.Entry>();
                for (Map.Entry<Long, String> entry : valueTextMap.get((Object)style).entrySet()) {
                    if (reverse2.put(entry.getValue(), SimpleDateTimeTextProvider.createEntry(entry.getValue(), entry.getKey())) == null) continue;
                }
                ArrayList list2 = new ArrayList(reverse2.values());
                Collections.sort(list2, COMPARATOR);
                map2.put(style, list2);
                allList.addAll(list2);
                map2.put(null, allList);
            }
            Collections.sort(allList, COMPARATOR);
            this.parsable = map2;
        }

        String getText(long value, TextStyle style) {
            Map<Long, String> map2 = this.valueTextMap.get((Object)style);
            return map2 != null ? map2.get(value) : null;
        }

        Iterator<Map.Entry<String, Long>> getTextIterator(TextStyle style) {
            List<Map.Entry<String, Long>> list2 = this.parsable.get((Object)style);
            return list2 != null ? list2.iterator() : null;
        }
    }
}

