/**
 * 
 */
package com.m3ns1.feed.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The pubDate element
 * @author m3ns1
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PubDate {
//Thu, 20 Dec 2012 23:04:11 +0100
	
	/**
	 * You can specify an alternative date format
	 * @return default is "E, dd M yyyy HH:mm.ss Z"
	 */
	public String dateFormat() default "E, dd M yyyy HH:mm.ss Z";
	
}
