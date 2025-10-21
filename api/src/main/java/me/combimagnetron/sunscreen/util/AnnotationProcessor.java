package me.combimagnetron.sunscreen.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public interface AnnotationProcessor<T extends Annotation> {

    T method(String method, Class<?> clazz);

    T field(String field, Class<?> clazz);

    T type(Class<?> clazz);

    T all(Class<?> clazz);

    static <T extends Annotation> AnnotationProcessor<T> reflection(Class<T> annotation) {
        return new ReflectionAnnotationProcessor<>(annotation);
    }

    class ReflectionAnnotationProcessor<T extends Annotation> implements AnnotationProcessor<T> {
        private final Class<T> annotationType;
        protected ReflectionAnnotationProcessor(Class<T> annotationType) {
            this.annotationType = annotationType;
        }

        @Override
        public T method(String method, Class<?> clazz) {
            Method method1 = Arrays.stream(clazz.getDeclaredMethods()).filter(streamMethod -> streamMethod.getName().equals(method)).findAny().orElseThrow();
            method1.setAccessible(true);
            return method1.getAnnotation(annotationType);
        }

        @Override
        public T field(String field, Class<?> clazz) {
            Field field1;
            try {
                 field1 = clazz.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            field1.setAccessible(true);
            return field1.getAnnotation(annotationType);
        }

        @Override
        public T type(Class<?> clazz) {
            return clazz.getAnnotation(annotationType);
        }

        @Override
        public T all(Class<?> clazz) {
            return type(clazz);
        }

    }

}
