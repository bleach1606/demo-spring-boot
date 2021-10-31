package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class CusBeanUtils {

    private CusBeanUtils() {
    }

    public static void refine(Object source, Function<Object, Object> refineFn) {
        refine(source, source, refineFn, (String) null);
    }

    public static void refine(Object source, Object target, Function<Object, Object> refineFn, String... ignoreProperties) {
        refine(source, target, refineFn, null, ignoreProperties);
    }

    public static void refine(Object source, Function<Object, Object> refineFn, @Nullable Class<?> editable,
                              @Nullable String... ignoreProperties) {
        refine(source, source, refineFn, editable, ignoreProperties);
    }

    public static <T> void refine(T source, Supplier<T> supplier, Function<Object, Object> refineFn, @Nullable Class<?> editable,
                                  @Nullable String... ignoreProperties) throws BeansException {
        refine(source, supplier.get(), refineFn, editable, ignoreProperties);
    }

    public static void refine(Object source, Object target, Function<Object, Object> refineFn, @Nullable Class<?> editable,
                              @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            value = refineFn.apply(value);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (IgnoredPropertyException ignored) {
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    public static Object refineDefaultValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String && ((String) value).isEmpty()) {
            return null;
        } else if (value instanceof Float) {
            return (Float) value == -1f ? null : value;
        } else if (value instanceof Double) {
            return (Double) value == -1 ? null : value;
        } else if (value instanceof Byte) {
            return (Byte) value == -1 ? null : value;
        } else if (value instanceof Short) {
            return (Short) value == -1 ? null : value;
        } else if (value instanceof Integer) {
            return (Integer) value == -1 ? null : value;
        } else if (value instanceof Long) {
            return (Long) value == -1 ? null : value;
        }
        return value;
    }

    public static Object trimString(Object value) {
        if (value instanceof String) {
            String str = ((String) value).trim();
            return str.isEmpty() ? null : str;
        }
        return value;
    }

    public static Object copyNonNull(Object value) {
        if (value == null) {
            throw new IgnoredPropertyException();
        }
        return value;
    }

    static class IgnoredPropertyException extends RuntimeException {
    }
}
