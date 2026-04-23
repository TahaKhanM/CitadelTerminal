/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.ApiFunction;
import com.google.api.core.InternalApi;
import com.google.cloud.Identity;
import com.google.cloud.Role;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;
import com.google.iam.v1.Binding;
import com.google.iam.v1.Policy;
import com.google.protobuf.ByteString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Policy
implements Serializable {
    private static final long serialVersionUID = -3348914530232544290L;
    private final Map<Role, Set<Identity>> bindings;
    private final String etag;
    private final int version;

    private Policy(Builder builder) {
        ImmutableMap.Builder bindingsBuilder = ImmutableMap.builder();
        for (Map.Entry binding : builder.bindings.entrySet()) {
            bindingsBuilder.put(binding.getKey(), ImmutableSet.copyOf((Collection)binding.getValue()));
        }
        this.bindings = bindingsBuilder.build();
        this.etag = builder.etag;
        this.version = builder.version;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public Map<Role, Set<Identity>> getBindings() {
        return this.bindings;
    }

    public String getEtag() {
        return this.etag;
    }

    public int getVersion() {
        return this.version;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("bindings", this.bindings).add("etag", this.etag).add("version", this.version).toString();
    }

    public int hashCode() {
        return Objects.hash(this.getClass(), this.bindings, this.etag, this.version);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Policy)) {
            return false;
        }
        Policy other = (Policy)obj;
        return Objects.equals(this.bindings, other.getBindings()) && Objects.equals(this.etag, other.getEtag()) && Objects.equals(this.version, other.getVersion());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<Role, Set<Identity>> bindings = new HashMap<Role, Set<Identity>>();
        private String etag;
        private int version;

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected Builder() {
        }

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected Builder(Policy policy) {
            this.setBindings(policy.bindings);
            this.setEtag(policy.etag);
            this.setVersion(policy.version);
        }

        public final Builder setBindings(Map<Role, Set<Identity>> bindings) {
            Preconditions.checkNotNull(bindings, "The provided map of bindings cannot be null.");
            for (Map.Entry<Role, Set<Identity>> binding : bindings.entrySet()) {
                Preconditions.checkNotNull(binding.getKey(), "The role cannot be null.");
                Set<Identity> identities = binding.getValue();
                Preconditions.checkNotNull(identities, "A role cannot be assigned to a null set of identities.");
                Preconditions.checkArgument(!identities.contains(null), "Null identities are not permitted.");
            }
            this.bindings.clear();
            for (Map.Entry<Role, Set<Identity>> binding : bindings.entrySet()) {
                this.bindings.put(binding.getKey(), new HashSet(binding.getValue()));
            }
            return this;
        }

        public final Builder removeRole(Role role) {
            this.bindings.remove(role);
            return this;
        }

        public final Builder addIdentity(Role role, Identity first, Identity ... others) {
            String nullIdentityMessage = "Null identities are not permitted.";
            Preconditions.checkNotNull(first, nullIdentityMessage);
            Preconditions.checkNotNull(others, nullIdentityMessage);
            for (Identity identity : others) {
                Preconditions.checkNotNull(identity, nullIdentityMessage);
            }
            LinkedHashSet<Identity> toAdd = new LinkedHashSet<Identity>();
            toAdd.add(first);
            toAdd.addAll(Arrays.asList(others));
            Set<Identity> identities = this.bindings.get(Preconditions.checkNotNull(role, "The role cannot be null."));
            if (identities == null) {
                identities = new HashSet<Identity>();
                this.bindings.put(role, identities);
            }
            identities.addAll(toAdd);
            return this;
        }

        public final Builder removeIdentity(Role role, Identity first, Identity ... others) {
            Set<Identity> identities = this.bindings.get(role);
            if (identities != null) {
                identities.remove(first);
                identities.removeAll(Arrays.asList(others));
            }
            if (identities != null && identities.isEmpty()) {
                this.bindings.remove(role);
            }
            return this;
        }

        public final Builder setEtag(String etag) {
            this.etag = etag;
            return this;
        }

        protected final Builder setVersion(int version) {
            this.version = version;
            return this;
        }

        public final Policy build() {
            return new Policy(this);
        }
    }

    public static class DefaultMarshaller
    extends Marshaller<com.google.iam.v1.Policy> {
        @Override
        protected Policy fromPb(com.google.iam.v1.Policy policyPb) {
            HashMap<Role, Set<Identity>> bindings = new HashMap<Role, Set<Identity>>();
            for (Binding bindingPb : policyPb.getBindingsList()) {
                bindings.put(Role.of(bindingPb.getRole()), ImmutableSet.copyOf(Lists.transform(bindingPb.getMembersList(), new Function<String, Identity>(){

                    @Override
                    public Identity apply(String s2) {
                        return Marshaller.IDENTITY_VALUE_OF_FUNCTION.apply(s2);
                    }
                })));
            }
            return Policy.newBuilder().setBindings(bindings).setEtag(policyPb.getEtag().isEmpty() ? null : BaseEncoding.base64().encode(policyPb.getEtag().toByteArray())).setVersion(policyPb.getVersion()).build();
        }

        @Override
        protected com.google.iam.v1.Policy toPb(Policy policy) {
            Policy.Builder policyBuilder = com.google.iam.v1.Policy.newBuilder();
            LinkedList<Binding> bindingPbList = new LinkedList<Binding>();
            for (Map.Entry<Role, Set<Identity>> binding : policy.getBindings().entrySet()) {
                Binding.Builder bindingBuilder = Binding.newBuilder();
                bindingBuilder.setRole(binding.getKey().getValue());
                bindingBuilder.addAllMembers(Lists.transform(new ArrayList(binding.getValue()), new Function<Identity, String>(){

                    @Override
                    public String apply(Identity identity) {
                        return Marshaller.IDENTITY_STR_VALUE_FUNCTION.apply(identity);
                    }
                }));
                bindingPbList.add(bindingBuilder.build());
            }
            policyBuilder.addAllBindings(bindingPbList);
            if (policy.etag != null) {
                policyBuilder.setEtag(ByteString.copyFrom(BaseEncoding.base64().decode(policy.etag)));
            }
            policyBuilder.setVersion(policy.version);
            return policyBuilder.build();
        }
    }

    public static abstract class Marshaller<T> {
        protected static final ApiFunction<String, Identity> IDENTITY_VALUE_OF_FUNCTION = new ApiFunction<String, Identity>(){

            @Override
            public Identity apply(String identityPb) {
                return Identity.valueOf(identityPb);
            }
        };
        protected static final ApiFunction<Identity, String> IDENTITY_STR_VALUE_FUNCTION = new ApiFunction<Identity, String>(){

            @Override
            public String apply(Identity identity) {
                return identity.strValue();
            }
        };

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected Marshaller() {
        }

        protected abstract Policy fromPb(T var1);

        protected abstract T toPb(Policy var1);
    }
}

