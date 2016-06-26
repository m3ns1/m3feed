/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.m3ns1.feed.annotation.FeedItems;

/**
 * A feed item finder for your feeds
 * @author m3ns1
 * 
 */
public class FeedItemsFinder {

	private Set<Class<?>> feeds = new HashSet<Class<?>>();
	private Map<Object, List<Object>> feedItems = new HashMap<Object, List<Object>>();

	/**
	 * Get all feed items for the set feed classes
	 */
	public void lookup() {
		for (Class<?> feed : feeds) {
			// iterate over all feeds and get the their feed items
			feedItems.put(feed, findItems(feed));
		}
	}

	/**
	 * Find the items in the argument object
	 * @param obj
	 * @return
	 */
	protected List<Object> findItems(Class<?> obj) {
		List<Object> items = new LinkedList<Object>();
		if (obj == null)
			return items;
		Field[] fields = obj.getDeclaredFields();
		if (fields == null || fields.length == 0)
			return items;
		for (Field field : fields) {
			if (hasAnnotation(field, FeedItems.class)) {
				// the field is annotated with feeditems
				Class<?> feeditems = field.getType();
				items.add(feeditems);
			}
		}
		return items;
	}

	/**
	 * Get the value of the annotated field in the argument object
	 * @param obj
	 * @param field
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected Object getAnnotatedObject(Object obj, Field field)
			throws IllegalArgumentException, IllegalAccessException {
		return field.get(obj);
	}

	/**
	 * Check if the field has the argument annotation
	 * @param field
	 * @param annotation
	 * @return
	 */
	protected boolean hasAnnotation(Field field,
			Class<? extends Annotation> annotation) {
		return field.getAnnotation(annotation) != null;
	}

	/**
	 * @return the feeds
	 */
	public Set<Class<?>> getFeeds() {
		return feeds;
	}

	/**
	 * @param feeds
	 *            the feeds to set
	 */
	public void setFeeds(Set<Class<?>> feeds) {
		this.feeds = feeds;
	}

	/**
	 * @return the feedItems
	 */
	public Map<Object, List<Object>> getFeedItems() {
		return feedItems;
	}

	/**
	 * @param feedItems
	 *            the feedItems to set
	 */
	public void setFeedItems(Map<Object, List<Object>> feedItems) {
		this.feedItems = feedItems;
	}

}
