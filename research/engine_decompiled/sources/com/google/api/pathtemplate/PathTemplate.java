/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.pathtemplate;

import com.google.api.pathtemplate.AutoValue_PathTemplate_Segment;
import com.google.api.pathtemplate.TemplatedResourceName;
import com.google.api.pathtemplate.ValidationException;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public class PathTemplate {
    public static final String HOSTNAME_VAR = "$hostname";
    private static final Pattern CUSTOM_VERB_PATTERN = Pattern.compile(":([^/*}{=]+)$");
    private static final Splitter SLASH_SPLITTER = Splitter.on('/').trimResults();
    private final ImmutableList<Segment> segments;
    private final ImmutableMap<String, Segment> bindings;
    private final boolean urlEncoding;

    public static PathTemplate create(String template) {
        return PathTemplate.create(template, true);
    }

    public static PathTemplate createWithoutUrlEncoding(String template) {
        return PathTemplate.create(template, false);
    }

    private static PathTemplate create(String template, boolean urlEncoding) {
        return new PathTemplate(PathTemplate.parseTemplate(template), urlEncoding);
    }

    private PathTemplate(Iterable<Segment> segments2, boolean urlEncoding) {
        this.segments = ImmutableList.copyOf(segments2);
        if (this.segments.isEmpty()) {
            throw new ValidationException("template cannot be empty.", new Object[0]);
        }
        LinkedHashMap<String, Segment> bindings = Maps.newLinkedHashMap();
        for (Segment seg : this.segments) {
            if (seg.kind() != SegmentKind.BINDING) continue;
            if (bindings.containsKey(seg.value())) {
                throw new ValidationException("Duplicate binding '%s'", seg.value());
            }
            bindings.put(seg.value(), seg);
        }
        this.bindings = ImmutableMap.copyOf(bindings);
        this.urlEncoding = urlEncoding;
    }

    public Set<String> vars() {
        return this.bindings.keySet();
    }

    public PathTemplate parentTemplate() {
        Segment seg;
        int i = this.segments.size();
        if ((seg = (Segment)this.segments.get(--i)).kind() == SegmentKind.END_BINDING) {
            while (i > 0 && ((Segment)this.segments.get(--i)).kind() != SegmentKind.BINDING) {
            }
        }
        if (i == 0) {
            throw new ValidationException("template does not have a parent", new Object[0]);
        }
        return new PathTemplate(this.segments.subList(0, i), this.urlEncoding);
    }

    public PathTemplate withoutVars() {
        StringBuilder result2 = new StringBuilder();
        ListIterator iterator2 = this.segments.listIterator();
        boolean start = true;
        block3: while (iterator2.hasNext()) {
            Segment seg = (Segment)iterator2.next();
            switch (seg.kind()) {
                case END_BINDING: 
                case BINDING: {
                    continue block3;
                }
            }
            if (!start) {
                result2.append(seg.separator());
            } else {
                start = false;
            }
            result2.append(seg.value());
        }
        return PathTemplate.create(result2.toString(), this.urlEncoding);
    }

    public PathTemplate subTemplate(String varName) {
        ArrayList<Segment> sub = Lists.newArrayList();
        boolean inBinding = false;
        for (Segment seg : this.segments) {
            if (seg.kind() == SegmentKind.BINDING && seg.value().equals(varName)) {
                inBinding = true;
                continue;
            }
            if (!inBinding) continue;
            if (seg.kind() == SegmentKind.END_BINDING) {
                return PathTemplate.create(PathTemplate.toSyntax(sub, true), this.urlEncoding);
            }
            sub.add(seg);
        }
        throw new ValidationException(String.format("Variable '%s' is undefined in template '%s'", varName, this.toRawString()), new Object[0]);
    }

    public boolean endsWithLiteral() {
        return ((Segment)this.segments.get(this.segments.size() - 1)).kind() == SegmentKind.LITERAL;
    }

    public boolean endsWithCustomVerb() {
        return ((Segment)this.segments.get(this.segments.size() - 1)).kind() == SegmentKind.CUSTOM_VERB;
    }

    public TemplatedResourceName parse(String path) {
        return TemplatedResourceName.create(this, path);
    }

    @Nullable
    public String singleVar() {
        if (this.bindings.size() == 1) {
            return (String)((Map.Entry)((ImmutableSet)this.bindings.entrySet()).iterator().next()).getKey();
        }
        return null;
    }

    public void validate(String path, String exceptionMessagePrefix) {
        if (!this.matches(path)) {
            throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", exceptionMessagePrefix, path, this.toString()), new Object[0]);
        }
    }

    public Map<String, String> validatedMatch(String path, String exceptionMessagePrefix) {
        Map<String, String> matchMap = this.match(path);
        if (matchMap == null) {
            throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", exceptionMessagePrefix, path, this.toString()), new Object[0]);
        }
        return matchMap;
    }

    public boolean matches(String path) {
        return this.match(path) != null;
    }

    @Nullable
    public Map<String, String> match(String path) {
        return this.match(path, false);
    }

    @Nullable
    public Map<String, String> matchFromFullName(String path) {
        return this.match(path, true);
    }

    private Map<String, String> match(String path, boolean forceHostName) {
        boolean withHostName;
        Segment last2 = (Segment)this.segments.get(this.segments.size() - 1);
        if (last2.kind() == SegmentKind.CUSTOM_VERB) {
            Matcher matcher = CUSTOM_VERB_PATTERN.matcher(path);
            if (!matcher.find() || !this.decodeUrl(matcher.group(1)).equals(last2.value())) {
                return null;
            }
            path = path.substring(0, matcher.start(0));
        }
        if (withHostName = path.startsWith("//")) {
            path = path.substring(2);
        }
        List<String> input2 = SLASH_SPLITTER.splitToList(path);
        int inPos = 0;
        LinkedHashMap<String, String> values = Maps.newLinkedHashMap();
        if (withHostName || forceHostName) {
            if (input2.isEmpty()) {
                return null;
            }
            String hostName = input2.get(inPos++);
            if (withHostName) {
                hostName = "//" + hostName;
            }
            values.put(HOSTNAME_VAR, hostName);
        }
        if (!this.match(input2, inPos, this.segments, 0, values)) {
            return null;
        }
        return ImmutableMap.copyOf(values);
    }

    private boolean match(List<String> input2, int inPos, List<Segment> segments2, int segPos, Map<String, String> values) {
        String currentVar = null;
        block8: while (segPos < segments2.size()) {
            Segment seg = segments2.get(segPos++);
            switch (seg.kind()) {
                case END_BINDING: {
                    currentVar = null;
                    continue block8;
                }
                case BINDING: {
                    currentVar = seg.value();
                    continue block8;
                }
                case CUSTOM_VERB: {
                    continue block8;
                }
            }
            if (inPos >= input2.size()) {
                return false;
            }
            String next2 = this.decodeUrl(input2.get(inPos++));
            if (seg.kind() == SegmentKind.LITERAL && !seg.value().equals(next2)) {
                return false;
            }
            if (currentVar != null) {
                String current = values.get(currentVar);
                if (current == null) {
                    values.put(currentVar, next2);
                } else {
                    values.put(currentVar, current + "/" + next2);
                }
            }
            if (seg.kind() != SegmentKind.PATH_WILDCARD) continue;
            int segsToMatch = 0;
            block9: for (int i = segPos; i < segments2.size(); ++i) {
                switch (segments2.get(i).kind()) {
                    case END_BINDING: 
                    case BINDING: {
                        continue block9;
                    }
                    default: {
                        ++segsToMatch;
                    }
                }
            }
            int available = input2.size() - inPos - segsToMatch;
            while (available-- > 0) {
                values.put(currentVar, values.get(currentVar) + "/" + this.decodeUrl(input2.get(inPos++)));
            }
        }
        return inPos == input2.size();
    }

    public String instantiate(Map<String, String> values) {
        return this.instantiate(values, false);
    }

    public String instantiate(String ... keysAndValues) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            builder.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        return this.instantiate(builder.build());
    }

    public String instantiatePartial(Map<String, String> values) {
        return this.instantiate(values, true);
    }

    private String instantiate(Map<String, String> values, boolean allowPartial) {
        StringBuilder result2 = new StringBuilder();
        if (values.containsKey(HOSTNAME_VAR)) {
            result2.append(values.get(HOSTNAME_VAR));
            result2.append('/');
        }
        boolean continueLast = true;
        boolean skip = false;
        ListIterator iterator2 = this.segments.listIterator();
        block4: while (iterator2.hasNext()) {
            Segment seg = (Segment)iterator2.next();
            if (!skip && !continueLast) {
                result2.append(seg.separator());
            }
            continueLast = false;
            switch (seg.kind()) {
                case BINDING: {
                    String var = seg.value();
                    String value = values.get(seg.value());
                    if (value == null) {
                        if (!allowPartial) {
                            throw new ValidationException(String.format("Unbound variable '%s'. Bindings: %s", var, values), new Object[0]);
                        }
                        if (var.startsWith("$")) {
                            result2.append(((Segment)iterator2.next()).value());
                            iterator2.next();
                            continue block4;
                        }
                        result2.append('{');
                        result2.append(seg.value());
                        result2.append('=');
                        continueLast = true;
                        continue block4;
                    }
                    Segment next2 = (Segment)iterator2.next();
                    Segment nextNext = (Segment)iterator2.next();
                    boolean pathEscape = next2.kind() == SegmentKind.PATH_WILDCARD || nextNext.kind() != SegmentKind.END_BINDING;
                    PathTemplate.restore(iterator2, iterator2.nextIndex() - 2);
                    if (!pathEscape) {
                        result2.append(this.encodeUrl(value));
                    } else {
                        boolean first = true;
                        for (String subSeg : SLASH_SPLITTER.split(value)) {
                            if (!first) {
                                result2.append('/');
                            }
                            first = false;
                            result2.append(this.encodeUrl(subSeg));
                        }
                    }
                    skip = true;
                    continue block4;
                }
                case END_BINDING: {
                    if (!skip) {
                        result2.append('}');
                    }
                    skip = false;
                    continue block4;
                }
            }
            if (skip) continue;
            result2.append(seg.value());
        }
        return result2.toString();
    }

    public String encode(String ... values) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        int i = 0;
        for (String value : values) {
            builder.put("$" + i++, value);
        }
        return this.instantiate(builder.build());
    }

    public List<String> decode(String path) {
        Map<String, String> match = this.match(path);
        if (match == null) {
            throw new IllegalArgumentException(String.format("template '%s' does not match '%s'", this, path));
        }
        ArrayList<String> result2 = Lists.newArrayList();
        for (Map.Entry<String, String> entry : match.entrySet()) {
            String key = entry.getKey();
            if (!key.startsWith("$")) {
                throw new IllegalArgumentException("template must not contain named bindings");
            }
            int i = Integer.parseInt(key.substring(1));
            while (result2.size() <= i) {
                result2.add("");
            }
            result2.set(i, entry.getValue());
        }
        return ImmutableList.copyOf(result2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static ImmutableList<Segment> parseTemplate(String template) {
        if (template.startsWith("/")) {
            template = template.substring(1);
        }
        Matcher matcher = CUSTOM_VERB_PATTERN.matcher(template);
        String customVerb = null;
        if (matcher.find()) {
            customVerb = matcher.group(1);
            template = template.substring(0, matcher.start(0));
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        String varName = null;
        int freeWildcardCounter = 0;
        int pathWildCardBound = 0;
        for (String seg : Splitter.on('/').trimResults().split(template)) {
            boolean bindingEnds;
            boolean bindingStarts = seg.startsWith("{");
            boolean implicitWildcard = false;
            if (bindingStarts) {
                if (varName != null) {
                    throw new ValidationException("parse error: nested binding in '%s'", template);
                }
                int i = (seg = seg.substring(1)).indexOf(61);
                if (i <= 0) {
                    if (!seg.endsWith("}")) throw new ValidationException("parse error: invalid binding syntax in '%s'", template);
                    implicitWildcard = true;
                    varName = seg.substring(0, seg.length() - 1).trim();
                    seg = seg.substring(seg.length() - 1).trim();
                } else {
                    varName = seg.substring(0, i).trim();
                    seg = seg.substring(i + 1).trim();
                }
                builder.add(Segment.create(SegmentKind.BINDING, varName));
            }
            if (bindingEnds = seg.endsWith("}")) {
                seg = seg.substring(0, seg.length() - 1).trim();
            }
            switch (seg) {
                case "**": 
                case "*": {
                    Segment wildcard;
                    if ("**".equals(seg)) {
                        ++pathWildCardBound;
                    }
                    Segment segment = wildcard = seg.length() == 2 ? Segment.PATH_WILDCARD : Segment.WILDCARD;
                    if (varName == null) {
                        builder.add(Segment.create(SegmentKind.BINDING, "$" + freeWildcardCounter));
                        ++freeWildcardCounter;
                        builder.add(wildcard);
                        builder.add(Segment.END_BINDING);
                        break;
                    }
                    builder.add(wildcard);
                    break;
                }
                case "": {
                    if (bindingEnds) break;
                    throw new ValidationException("parse error: empty segment not allowed in '%s'", template);
                }
                default: {
                    builder.add(Segment.create(SegmentKind.LITERAL, seg));
                }
            }
            if (bindingEnds) {
                varName = null;
                if (implicitWildcard) {
                    builder.add(Segment.WILDCARD);
                }
                builder.add(Segment.END_BINDING);
            }
            if (pathWildCardBound <= 1) continue;
            throw new ValidationException("parse error: pattern must not contain more than one path wildcard ('**') in '%s'", template);
        }
        if (customVerb == null) return builder.build();
        builder.add(Segment.create(SegmentKind.CUSTOM_VERB, customVerb));
        return builder.build();
    }

    private String encodeUrl(String text) {
        if (this.urlEncoding) {
            try {
                return URLEncoder.encode(text, "UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
            }
        }
        String INVALID_CHAR = "/";
        if (text.contains("/")) {
            throw new ValidationException("Invalid character \"/\" in path section \"" + text + "\".", new Object[0]);
        }
        return text;
    }

    private String decodeUrl(String url) {
        if (this.urlEncoding) {
            try {
                return URLDecoder.decode(url, "UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
            }
        }
        return url;
    }

    private static boolean peek(ListIterator<Segment> segments2, SegmentKind ... kinds) {
        int start = segments2.nextIndex();
        boolean success = false;
        for (SegmentKind kind : kinds) {
            if (segments2.hasNext() && segments2.next().kind() == kind) continue;
            success = false;
            break;
        }
        if (success) {
            return true;
        }
        PathTemplate.restore(segments2, start);
        return false;
    }

    private static void restore(ListIterator<?> segments2, int index) {
        while (segments2.nextIndex() > index) {
            segments2.previous();
        }
    }

    public String toString() {
        return PathTemplate.toSyntax(this.segments, true);
    }

    public String toRawString() {
        return PathTemplate.toSyntax(this.segments, false);
    }

    private static String toSyntax(List<Segment> segments2, boolean pretty) {
        StringBuilder result2 = new StringBuilder();
        boolean continueLast = true;
        ListIterator<Segment> iterator2 = segments2.listIterator();
        block4: while (iterator2.hasNext()) {
            Segment seg = iterator2.next();
            if (!continueLast) {
                result2.append(seg.separator());
            }
            continueLast = false;
            switch (seg.kind()) {
                case BINDING: {
                    if (pretty && seg.value().startsWith("$")) {
                        seg = iterator2.next();
                        result2.append(seg.value());
                        iterator2.next();
                        continue block4;
                    }
                    result2.append('{');
                    result2.append(seg.value());
                    if (pretty && PathTemplate.peek(iterator2, SegmentKind.WILDCARD, SegmentKind.END_BINDING)) {
                        result2.append('}');
                        continue block4;
                    }
                    result2.append('=');
                    continueLast = true;
                    continue block4;
                }
                case END_BINDING: {
                    result2.append('}');
                    continue block4;
                }
            }
            result2.append(seg.value());
        }
        return result2.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PathTemplate)) {
            return false;
        }
        PathTemplate other = (PathTemplate)obj;
        return Objects.equals(this.segments, other.segments);
    }

    public int hashCode() {
        return this.segments.hashCode();
    }

    static abstract class Segment {
        private static final Segment WILDCARD = Segment.create(SegmentKind.WILDCARD, "*");
        private static final Segment PATH_WILDCARD = Segment.create(SegmentKind.PATH_WILDCARD, "**");
        private static final Segment END_BINDING = Segment.create(SegmentKind.END_BINDING, "");

        Segment() {
        }

        private static Segment create(SegmentKind kind, String value) {
            return new AutoValue_PathTemplate_Segment(kind, value);
        }

        abstract SegmentKind kind();

        abstract String value();

        boolean isAnyWildcard() {
            return this.kind() == SegmentKind.WILDCARD || this.kind() == SegmentKind.PATH_WILDCARD;
        }

        String separator() {
            switch (this.kind()) {
                case CUSTOM_VERB: {
                    return ":";
                }
                case END_BINDING: {
                    return "";
                }
            }
            return "/";
        }
    }

    static enum SegmentKind {
        LITERAL,
        CUSTOM_VERB,
        WILDCARD,
        PATH_WILDCARD,
        BINDING,
        END_BINDING;

    }
}

