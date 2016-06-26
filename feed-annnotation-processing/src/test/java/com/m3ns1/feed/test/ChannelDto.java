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
 * @author m3ns1
 *
 */
@Feed
public class ChannelDto {

	@Title
	private String title;
	@Description
	private String description;
	@PubDate
	private Date pubDate;
	@Link
	private String link;

	@FeedItems(target = FeedItem.class)
	private List<ItemDto> items = new ArrayList<ItemDto>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

}
