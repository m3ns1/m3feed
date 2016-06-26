/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A bean utility class
 * @author m3ns1
 *
 */
public class BeanUtil {

	/**
	 * Find the getter method for this field in the argument class object
	 * @param field
	 * @param clazz
	 * @return
	 */
	public static Method findGetter(Field field, Class<?> clazz) {
		if (field==null) {
			return null;
		}
		String name = field.getName();
		String getter = getGetterFor(name, clazz);
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().startsWith(getter)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Get the value for this field in the argument instance
	 * @param field
	 * @param v
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object getValueFor(Field field, Object v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getter = BeanUtil.findGetter(field, v.getClass());
		if (getter == null) {
			return null;
		}
		return getter.invoke(v, (Object[]) null);
	}

	/**
	 * Get the setter method name of the argument field name and type. If the type is a boolean it will return
	 * the method name isPropertyName
	 * @param name
	 * @param type
	 * @return
	 */
	protected static String getGetterFor(String name, Class<?> type) {
		if (name==null) {
			return null;
		}
		String prefix = "get";
		if (type.isPrimitive()) {
			if (Boolean.TYPE.isAssignableFrom(type)) {
				prefix = "is";
			}
		}
		if (Boolean.class.equals(type)) {
			prefix = "is";
		}
		return String.format("%s%S%s", prefix, Character.toUpperCase(name.charAt(0)), name.substring(1));
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

}
