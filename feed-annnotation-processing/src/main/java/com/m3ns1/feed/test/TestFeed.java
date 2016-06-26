/**
 * 
 */
package com.m3ns1.feed.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.Feed;
import com.m3ns1.feed.annotation.FeedItems;
import com.m3ns1.feed.annotation.Link;
import com.m3ns1.feed.annotation.PubDate;
import com.m3ns1.feed.annotation.Title;

/**
 * Test feed
 * @author m3ns1
 *
 */
@Feed
public class TestFeed {

	@Title
	private String title;
	@Description
	private String description;
	@PubDate
	private Date pubDate;
	@Link
	private String link;

	@FeedItems(target=FeedItem.class)
	private List<FeedItem> items = new ArrayList<FeedItem>();

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}
	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the items
	 */
	public List<FeedItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<FeedItem> items) {
		this.items = items;
	}

}
