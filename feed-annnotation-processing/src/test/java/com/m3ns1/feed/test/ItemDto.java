/**
 * 
 */
package com.m3ns1.feed.test;

import java.util.Date;

import com.m3ns1.feed.annotation.Comments;
import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.GUID;
import com.m3ns1.feed.annotation.Item;
import com.m3ns1.feed.annotation.Link;
import com.m3ns1.feed.annotation.PubDate;
import com.m3ns1.feed.annotation.Title;

/**
 * @author m3ns1
 *
 */
@Item
public class ItemDto {
	@Title
	private String title;
	@Description(cdata = true)
	private String description;
	@Link
	private String link;
	@PubDate
	private Date pubDate;
	@Comments
	private String comments;
	@GUID
	private String guid;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate
	 *            the pubDate to set
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
}
