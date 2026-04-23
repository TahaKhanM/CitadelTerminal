/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.client.http;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.repackaged.com.google.common.base.Splitter;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UriTemplate {
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap<Character, CompositeOutput>();
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";

    static CompositeOutput getCompositeOutput(String propertyName) {
        CompositeOutput compositeOutput = COMPOSITE_PREFIXES.get(Character.valueOf(propertyName.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    private static Map<String, Object> getMap(Object obj) {
        LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
        for (Map.Entry<String, Object> entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value == null || Data.isNull(value)) continue;
            map2.put(entry.getKey(), value);
        }
        return map2;
    }

    public static String expand(String baseUrl, String uriTemplate, Object parameters, boolean addUnusedParamsAsQueryParams) {
        String pathUri;
        if (uriTemplate.startsWith("/")) {
            GenericUrl url = new GenericUrl(baseUrl);
            url.setRawPath(null);
            pathUri = url.build() + uriTemplate;
        } else {
            pathUri = uriTemplate.startsWith("http://") || uriTemplate.startsWith("https://") ? uriTemplate : baseUrl + uriTemplate;
        }
        return UriTemplate.expand(pathUri, parameters, addUnusedParamsAsQueryParams);
    }

    public static String expand(String pathUri, Object parameters, boolean addUnusedParamsAsQueryParams) {
        Map<String, Object> variableMap = UriTemplate.getMap(parameters);
        StringBuilder pathBuf = new StringBuilder();
        int cur = 0;
        int length = pathUri.length();
        while (cur < length) {
            int next2 = pathUri.indexOf(123, cur);
            if (next2 == -1) {
                if (cur == 0 && !addUnusedParamsAsQueryParams) {
                    return pathUri;
                }
                pathBuf.append(pathUri.substring(cur));
                break;
            }
            pathBuf.append(pathUri.substring(cur, next2));
            int close = pathUri.indexOf(125, next2 + 2);
            cur = close + 1;
            String templates = pathUri.substring(next2 + 1, close);
            CompositeOutput compositeOutput = UriTemplate.getCompositeOutput(templates);
            ListIterator<String> templateIterator = Splitter.on(',').splitToList(templates).listIterator();
            boolean isFirstParameter = true;
            while (templateIterator.hasNext()) {
                Iterator iterator2;
                String varName;
                Object value;
                String template = templateIterator.next();
                boolean containsExplodeModifier = template.endsWith("*");
                int varNameStartIndex = templateIterator.nextIndex() == 1 ? compositeOutput.getVarNameStartIndex() : 0;
                int varNameEndIndex = template.length();
                if (containsExplodeModifier) {
                    --varNameEndIndex;
                }
                if ((value = variableMap.remove(varName = template.substring(varNameStartIndex, varNameEndIndex))) == null) continue;
                if (!isFirstParameter) {
                    pathBuf.append(compositeOutput.getExplodeJoiner());
                } else {
                    pathBuf.append(compositeOutput.getOutputPrefix());
                    isFirstParameter = false;
                }
                if (value instanceof Iterator) {
                    iterator2 = (Iterator)value;
                    value = UriTemplate.getListPropertyValue(varName, iterator2, containsExplodeModifier, compositeOutput);
                } else if (value instanceof Iterable || value.getClass().isArray()) {
                    iterator2 = Types.iterableOf(value).iterator();
                    value = UriTemplate.getListPropertyValue(varName, iterator2, containsExplodeModifier, compositeOutput);
                } else if (value.getClass().isEnum()) {
                    String name = FieldInfo.of((Enum)value).getName();
                    if (name != null) {
                        if (compositeOutput.requiresVarAssignment()) {
                            value = String.format("%s=%s", varName, value);
                        }
                        value = CharEscapers.escapeUriPath(value.toString());
                    }
                } else if (!Data.isValueOfPrimitiveType(value)) {
                    Map<String, Object> map2 = UriTemplate.getMap(value);
                    value = UriTemplate.getMapPropertyValue(varName, map2, containsExplodeModifier, compositeOutput);
                } else {
                    if (compositeOutput.requiresVarAssignment()) {
                        value = String.format("%s=%s", varName, value);
                    }
                    value = compositeOutput.getReservedExpansion() ? CharEscapers.escapeUriPathWithoutReserved(value.toString()) : CharEscapers.escapeUriPath(value.toString());
                }
                pathBuf.append(value);
            }
        }
        if (addUnusedParamsAsQueryParams) {
            GenericUrl.addQueryParams(variableMap.entrySet(), pathBuf);
        }
        return pathBuf.toString();
    }

    private static String getListPropertyValue(String varName, Iterator<?> iterator2, boolean containsExplodeModifier, CompositeOutput compositeOutput) {
        String joiner;
        if (!iterator2.hasNext()) {
            return "";
        }
        StringBuilder retBuf = new StringBuilder();
        if (containsExplodeModifier) {
            joiner = compositeOutput.getExplodeJoiner();
        } else {
            joiner = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
        }
        while (iterator2.hasNext()) {
            if (containsExplodeModifier && compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
            retBuf.append(compositeOutput.getEncodedValue(iterator2.next().toString()));
            if (!iterator2.hasNext()) continue;
            retBuf.append(joiner);
        }
        return retBuf.toString();
    }

    private static String getMapPropertyValue(String varName, Map<String, Object> map2, boolean containsExplodeModifier, CompositeOutput compositeOutput) {
        String mapElementsJoiner;
        String joiner;
        if (map2.isEmpty()) {
            return "";
        }
        StringBuilder retBuf = new StringBuilder();
        if (containsExplodeModifier) {
            joiner = compositeOutput.getExplodeJoiner();
            mapElementsJoiner = "=";
        } else {
            joiner = COMPOSITE_NON_EXPLODE_JOINER;
            mapElementsJoiner = COMPOSITE_NON_EXPLODE_JOINER;
            if (compositeOutput.requiresVarAssignment()) {
                retBuf.append(CharEscapers.escapeUriPath(varName));
                retBuf.append("=");
            }
        }
        Iterator<Map.Entry<String, Object>> mapIterator = map2.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Object> entry = mapIterator.next();
            String encodedKey = compositeOutput.getEncodedValue(entry.getKey());
            String encodedValue = compositeOutput.getEncodedValue(entry.getValue().toString());
            retBuf.append(encodedKey);
            retBuf.append(mapElementsJoiner);
            retBuf.append(encodedValue);
            if (!mapIterator.hasNext()) continue;
            retBuf.append(joiner);
        }
        return retBuf.toString();
    }

    static {
        CompositeOutput.values();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum CompositeOutput {
        PLUS(Character.valueOf('+'), "", ",", false, true),
        HASH(Character.valueOf('#'), "#", ",", false, true),
        DOT(Character.valueOf('.'), ".", ".", false, false),
        FORWARD_SLASH(Character.valueOf('/'), "/", "/", false, false),
        SEMI_COLON(Character.valueOf(';'), ";", ";", true, false),
        QUERY(Character.valueOf('?'), "?", "&", true, false),
        AMP(Character.valueOf('&'), "&", "&", true, false),
        SIMPLE(null, "", ",", false, false);

        private final Character propertyPrefix;
        private final String outputPrefix;
        private final String explodeJoiner;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        private CompositeOutput(Character propertyPrefix, String outputPrefix, String explodeJoiner, boolean requiresVarAssignment, boolean reservedExpansion) {
            this.propertyPrefix = propertyPrefix;
            this.outputPrefix = Preconditions.checkNotNull(outputPrefix);
            this.explodeJoiner = Preconditions.checkNotNull(explodeJoiner);
            this.requiresVarAssignment = requiresVarAssignment;
            this.reservedExpansion = reservedExpansion;
            if (propertyPrefix != null) {
                COMPOSITE_PREFIXES.put(propertyPrefix, this);
            }
        }

        String getOutputPrefix() {
            return this.outputPrefix;
        }

        String getExplodeJoiner() {
            return this.explodeJoiner;
        }

        boolean requiresVarAssignment() {
            return this.requiresVarAssignment;
        }

        int getVarNameStartIndex() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        String getEncodedValue(String value) {
            String encodedValue = this.reservedExpansion ? CharEscapers.escapeUriPath(value) : CharEscapers.escapeUri(value);
            return encodedValue;
        }

        boolean getReservedExpansion() {
            return this.reservedExpansion;
        }
    }
}

