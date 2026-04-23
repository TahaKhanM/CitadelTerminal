/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.ExtensionLite;
import com.google.protobuf.ExtensionRegistryFactory;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
    private static volatile boolean eagerlyParseMessageSets = false;
    static final String EXTENSION_CLASS_NAME = "com.google.protobuf.Extension";
    private static final Class<?> extensionClass = ExtensionRegistryLite.resolveExtensionClass();
    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

    static Class<?> resolveExtensionClass() {
        try {
            return Class.forName(EXTENSION_CLASS_NAME);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean isEagerlyParseMessageSets() {
        return eagerlyParseMessageSets;
    }

    public static void setEagerlyParseMessageSets(boolean isEagerlyParse) {
        eagerlyParseMessageSets = isEagerlyParse;
    }

    public static ExtensionRegistryLite newInstance() {
        return ExtensionRegistryFactory.create();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return ExtensionRegistryFactory.createEmpty();
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingTypeDefaultInstance, int fieldNumber) {
        return this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
    }

    public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> extension2) {
        this.extensionsByNumber.put(new ObjectIntPair(extension2.getContainingTypeDefaultInstance(), extension2.getNumber()), extension2);
    }

    public final void add(ExtensionLite<?, ?> extension2) {
        if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extension2.getClass())) {
            this.add((GeneratedMessageLite.GeneratedExtension)extension2);
        }
        if (ExtensionRegistryFactory.isFullRegistry(this)) {
            try {
                this.getClass().getMethod("add", extensionClass).invoke((Object)this, extension2);
            }
            catch (Exception e) {
                throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", extension2), e);
            }
        }
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite other) {
        this.extensionsByNumber = other == EMPTY_REGISTRY_LITE ? Collections.emptyMap() : Collections.unmodifiableMap(other.extensionsByNumber);
    }

    ExtensionRegistryLite(boolean empty) {
        this.extensionsByNumber = Collections.emptyMap();
    }

    private static final class ObjectIntPair {
        private final Object object;
        private final int number;

        ObjectIntPair(Object object, int number) {
            this.object = object;
            this.number = number;
        }

        public int hashCode() {
            return System.identityHashCode(this.object) * 65535 + this.number;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair other = (ObjectIntPair)obj;
            return this.object == other.object && this.number == other.number;
        }
    }
}

