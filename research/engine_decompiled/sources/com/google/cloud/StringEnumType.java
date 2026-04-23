/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.ApiFunction;
import com.google.api.core.InternalApi;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@InternalApi
public class StringEnumType<EnumT> {
    private final Class<EnumT> clazz;
    private final ApiFunction<String, EnumT> constructor;
    private final Map<String, EnumT> knownValues = new LinkedHashMap<String, EnumT>();

    public StringEnumType(Class<EnumT> clazz, ApiFunction<String, EnumT> constructor) {
        this.clazz = Preconditions.checkNotNull(clazz);
        this.constructor = Preconditions.checkNotNull(constructor);
    }

    public EnumT createAndRegister(String constant) {
        EnumT instance = this.constructor.apply(constant);
        this.knownValues.put(constant, instance);
        return instance;
    }

    public EnumT valueOfStrict(String constant) {
        EnumT value = this.knownValues.get(constant);
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException("Constant \"" + constant + "\" not found for enum \"" + this.clazz.getName() + "\"");
    }

    public EnumT valueOf(String constant) {
        if (constant == null || constant.isEmpty()) {
            throw new IllegalArgumentException("Empty enum constants not allowed.");
        }
        EnumT value = this.knownValues.get(constant);
        if (value != null) {
            return value;
        }
        return this.constructor.apply(constant);
    }

    public EnumT[] values() {
        Collection<EnumT> valueCollection = this.knownValues.values();
        Object[] valueArray = (Object[])Array.newInstance(this.clazz, valueCollection.size());
        int i = 0;
        for (EnumT enumV : valueCollection) {
            valueArray[i] = enumV;
            ++i;
        }
        return valueArray;
    }
}

