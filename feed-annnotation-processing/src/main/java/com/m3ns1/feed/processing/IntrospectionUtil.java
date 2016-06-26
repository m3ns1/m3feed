/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * An introspection utility class
 * @author m3ns1
 *
 */
public class IntrospectionUtil {

	/**
	 * Get the field that has the annotation set. The method is implemented in a recursive way to perform full search (until {@link Object})
	 * @param clazz
	 * @param annotation
	 * @return
	 */
	public static Field getAnnotatedField(Class<?> clazz, Class<? extends Annotation> annotation) {
		if (clazz==null) return null;
		if (annotation==null) return null;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(annotation)) {
				return field;
			}
		}
		Class<?> superclazz = clazz.getSuperclass();
		if (superclazz.equals(Object.class)) {
			return null;
		}
		return getAnnotatedField(superclazz, annotation);
	}
	
}
