/*
 * Decompiled with CFR 0.152.
 */
package org.junit.experimental.theories.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AllMembersSupplier
extends ParameterSupplier {
    private final TestClass fClass;

    public AllMembersSupplier(TestClass type) {
        this.fClass = type;
    }

    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
        ArrayList<PotentialAssignment> list2 = new ArrayList<PotentialAssignment>();
        this.addFields(sig, list2);
        this.addSinglePointMethods(sig, list2);
        this.addMultiPointMethods(list2);
        return list2;
    }

    private void addMultiPointMethods(List<PotentialAssignment> list2) {
        for (FrameworkMethod dataPointsMethod : this.fClass.getAnnotatedMethods(DataPoints.class)) {
            try {
                this.addArrayValues(dataPointsMethod.getName(), list2, dataPointsMethod.invokeExplosively(null, new Object[0]));
            }
            catch (Throwable e) {}
        }
    }

    private void addSinglePointMethods(ParameterSignature sig, List<PotentialAssignment> list2) {
        for (FrameworkMethod dataPointMethod : this.fClass.getAnnotatedMethods(DataPoint.class)) {
            Class<?> type;
            if (!dataPointMethod.producesType(type = sig.getType())) continue;
            list2.add(new MethodParameterValue(dataPointMethod));
        }
    }

    private void addFields(ParameterSignature sig, List<PotentialAssignment> list2) {
        for (Field field2 : this.fClass.getJavaClass().getFields()) {
            if (!Modifier.isStatic(field2.getModifiers())) continue;
            Class<?> type = field2.getType();
            if (sig.canAcceptArrayType(type) && field2.getAnnotation(DataPoints.class) != null) {
                this.addArrayValues(field2.getName(), list2, this.getStaticFieldValue(field2));
                continue;
            }
            if (!sig.canAcceptType(type) || field2.getAnnotation(DataPoint.class) == null) continue;
            list2.add(PotentialAssignment.forValue(field2.getName(), this.getStaticFieldValue(field2)));
        }
    }

    private void addArrayValues(String name, List<PotentialAssignment> list2, Object array) {
        for (int i = 0; i < Array.getLength(array); ++i) {
            list2.add(PotentialAssignment.forValue(name + "[" + i + "]", Array.get(array, i)));
        }
    }

    private Object getStaticFieldValue(Field field2) {
        try {
            return field2.get(null);
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException("unexpected: field from getClass doesn't exist on object");
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("unexpected: getFields returned an inaccessible field");
        }
    }

    static class MethodParameterValue
    extends PotentialAssignment {
        private final FrameworkMethod fMethod;

        private MethodParameterValue(FrameworkMethod dataPointMethod) {
            this.fMethod = dataPointMethod;
        }

        public Object getValue() throws PotentialAssignment.CouldNotGenerateValueException {
            try {
                return this.fMethod.invokeExplosively(null, new Object[0]);
            }
            catch (IllegalArgumentException e) {
                throw new RuntimeException("unexpected: argument length is checked");
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("unexpected: getMethods returned an inaccessible method");
            }
            catch (Throwable e) {
                throw new PotentialAssignment.CouldNotGenerateValueException();
            }
        }

        public String getDescription() throws PotentialAssignment.CouldNotGenerateValueException {
            return this.fMethod.getName();
        }
    }
}

