/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.pathtemplate;

import com.google.api.pathtemplate.PathTemplate;
import com.google.api.pathtemplate.ValidationException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

public class TemplatedResourceName
implements Map<String, String> {
    private static volatile Resolver resourceNameResolver = new Resolver(){

        @Override
        public <T> T resolve(Class<T> resourceType, TemplatedResourceName name, String version) {
            throw new IllegalStateException("No resource name resolver is registered in ResourceName class.");
        }
    };
    private final PathTemplate template;
    private final ImmutableMap<String, String> values;
    private final String endpoint;
    private volatile String stringRepr;

    public static void registerResourceNameResolver(Resolver resolver) {
        resourceNameResolver = resolver;
    }

    public static TemplatedResourceName create(PathTemplate template, String path) {
        Map<String, String> values = template.match(path);
        if (values == null) {
            throw new ValidationException("path '%s' does not match template '%s'", path, template);
        }
        return new TemplatedResourceName(template, values, null);
    }

    public static TemplatedResourceName create(PathTemplate template, Map<String, String> values) {
        if (!values.keySet().containsAll(template.vars())) {
            LinkedHashSet<String> unbound = Sets.newLinkedHashSet(template.vars());
            unbound.removeAll(values.keySet());
            throw new ValidationException("unbound variables: %s", unbound);
        }
        return new TemplatedResourceName(template, values, null);
    }

    @Nullable
    public static TemplatedResourceName createFromFullName(PathTemplate template, String path) {
        Map<String, String> values = template.matchFromFullName(path);
        if (values == null) {
            return null;
        }
        return new TemplatedResourceName(template, values, null);
    }

    private TemplatedResourceName(PathTemplate template, Map<String, String> values, String endpoint) {
        this.template = template;
        this.values = ImmutableMap.copyOf(values);
        this.endpoint = endpoint;
    }

    public String toString() {
        if (this.stringRepr == null) {
            this.stringRepr = this.template.instantiate(this.values);
        }
        return this.stringRepr;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TemplatedResourceName)) {
            return false;
        }
        TemplatedResourceName other = (TemplatedResourceName)obj;
        return Objects.equals(this.template, other.template) && Objects.equals(this.endpoint, other.endpoint) && Objects.equals(this.values, other.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.template, this.endpoint, this.values);
    }

    public PathTemplate template() {
        return this.template;
    }

    public boolean hasEndpoint() {
        return this.endpoint != null;
    }

    @Nullable
    public String endpoint() {
        return this.endpoint;
    }

    public TemplatedResourceName withEndpoint(String endpoint) {
        return new TemplatedResourceName(this.template, this.values, Preconditions.checkNotNull(endpoint));
    }

    public TemplatedResourceName parentName() {
        PathTemplate parentTemplate = this.template.parentTemplate();
        return new TemplatedResourceName(parentTemplate, this.values, this.endpoint);
    }

    public boolean startsWith(TemplatedResourceName parentName) {
        return this.toString().startsWith(parentName.toString());
    }

    public <T> T resolve(Class<T> resourceType, @Nullable String version) {
        Preconditions.checkArgument(this.hasEndpoint(), "Resource name must have an endpoint.");
        return resourceNameResolver.resolve(resourceType, this, version);
    }

    @Override
    public int size() {
        return this.values.size();
    }

    @Override
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.values.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return this.values.get(key);
    }

    @Override
    @Deprecated
    public String put(String key, String value) {
        return this.values.put(key, value);
    }

    @Override
    @Deprecated
    public String remove(Object key) {
        return this.values.remove(key);
    }

    @Override
    @Deprecated
    public void putAll(Map<? extends String, ? extends String> m) {
        this.values.putAll(m);
    }

    @Override
    @Deprecated
    public void clear() {
        this.values.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.values.keySet();
    }

    @Override
    public Collection<String> values() {
        return this.values.values();
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return this.values.entrySet();
    }

    public static interface Resolver {
        public <T> T resolve(Class<T> var1, TemplatedResourceName var2, @Nullable String var3);
    }
}

