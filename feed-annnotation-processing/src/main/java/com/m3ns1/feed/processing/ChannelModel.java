/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.m3ns1.feed.annotation.FeedItems;

/**
 * This model class holds information about the feed models that were found. The class
 * is a singleton to have easy access from your entire application
 * @author m3ns1
 *
 */
public class ChannelModel  {

	/**
	 * Get the singleton. The singleton is created in a thread-safe way and you don't have
	 * to care about it. just invoke this method ;-)
	 * @return
	 */
	public static ChannelModel getInstance() {
		return instance;
	}
	
	/**
	 * the instance variable
	 */
	private static ChannelModel instance = new ChannelModel();
	
	/**
	 * The models of your feeds
	 */
	private Set<FeedModel> feedModels = new HashSet<FeedModel>();
	
	/**
	 * Singleton pattern
	 */
	private ChannelModel() {}
	
	/**
	 * Check if there's a feed model for the argument class
	 * @param clazz
	 * @return
	 */
	public boolean hasFeedModel(Class<?> clazz) {
		return getFeedModel(clazz)!=null;
	}
	
	/**
	 * Get the feed model if found otherwise null
	 * @param clazz
	 * @return
	 */
	public FeedModel getFeedModel(Class<?> clazz) {
		for (FeedModel fm : getFeedModels()) {
			if (clazz.isAssignableFrom(fm.getFeedClass())) {
				return fm;
			}
		}
		return null;
	}
	
	/**
	 * Get the feed item model for the argument class if found otherwise it will 
	 * return null
	 * @param clazz
	 * @return
	 */
	public ItemModel getFeedItemModel(Class<?> clazz) {
		for (FeedModel fm : getFeedModels()) {
			Field items = fm.getItems();
			FeedItems annot = items.getAnnotation(FeedItems.class);
			if (annot.target().isAssignableFrom(clazz)) {
				return fm.getItemModel();
			}
		}
		return null;
	}
	
	/**
	 * Get all feed models
	 * @return the feedModels
	 */
	public Set<FeedModel> getFeedModels() {
		return feedModels;
	}

	/**
	 * Set the models
	 * @param feedModels the feedModels to set
	 */
	public void setFeedModels(Set<FeedModel> feedModels) {
		this.feedModels = feedModels;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedModels == null) ? 0 : feedModels.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelModel other = (ChannelModel) obj;
		if (feedModels == null) {
			if (other.feedModels != null)
				return false;
		} else if (!feedModels.equals(other.feedModels))
			return false;
		return true;
	}
}
