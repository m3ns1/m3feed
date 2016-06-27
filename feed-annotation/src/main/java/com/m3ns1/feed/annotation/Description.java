/**
 * 
 */
package com.m3ns1.feed.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The description annotation of the channel or feed item
 * @author m3ns1
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Description {

	/**
	 * You can specify if the content should be as CDATA or as plain text.
	 * @return Defaults to false
	 */
	public boolean cdata() default false;
	
}
