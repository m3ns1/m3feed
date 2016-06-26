/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.reflect.Field;

import com.m3ns1.feed.annotation.Author;
import com.m3ns1.feed.annotation.Categories;
import com.m3ns1.feed.annotation.Comments;
import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.Enclosure;
import com.m3ns1.feed.annotation.GUID;
import com.m3ns1.feed.annotation.Link;
import com.m3ns1.feed.annotation.PubDate;
import com.m3ns1.feed.annotation.Source;
import com.m3ns1.feed.annotation.Title;

/**
 * An item model representation class
 * @author m3ns1
 *
 */
public class ItemModel {

	private Class<?> itemClass = null;
	private Field title = null;
	private Field description = null;
	private Field link = null;
	private Field pubDate = null;
	private Field comments = null;
	private Field guid = null;
	private Field author = null;
	private Field categories = null;
	private Field enclosure = null;
	private Field source = null;

	/**
	 * An item model must have a valid item class which should be used to perform the model generation
	 * @param itemClass
	 */
	public ItemModel(Class<?> itemClass) {
		setItemClass(itemClass);
	}

	/**
	 * Do the introspection and set up this model
	 */
	public void introspect() {
		if (getItemClass()==null) {
			return;
		}
		setTitle(IntrospectionUtil.getAnnotatedField(itemClass, Title.class));
		setDescription(IntrospectionUtil.getAnnotatedField(itemClass, Description.class));
		setLink(IntrospectionUtil.getAnnotatedField(itemClass, Link.class));
		setPubDate(IntrospectionUtil.getAnnotatedField(itemClass, PubDate.class));
		setComments(IntrospectionUtil.getAnnotatedField(itemClass, Comments.class));
		setGuid(IntrospectionUtil.getAnnotatedField(itemClass, GUID.class));
		setAuthor(IntrospectionUtil.getAnnotatedField(itemClass, Author.class));
		setCategories(IntrospectionUtil.getAnnotatedField(itemClass, Categories.class));
		setEnclosure(IntrospectionUtil.getAnnotatedField(itemClass, Enclosure.class));
		setSource(IntrospectionUtil.getAnnotatedField(itemClass, Source.class));
		;
	}

	/**
	 * @return the itemClass
	 */
	public Class<?> getItemClass() {
		return itemClass;
	}

	/**
	 * @param itemClass the itemClass to set
	 */
	public void setItemClass(Class<?> itemClass) {
		this.itemClass = itemClass;
	}

	/**
	 * @return the title
	 */
	public Field getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(Field title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public Field getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(Field description) {
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public Field getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(Field link) {
		this.link = link;
	}

	/**
	 * @return the pubDate
	 */
	public Field getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(Field pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the comments
	 */
	public Field getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Field comments) {
		this.comments = comments;
	}

	/**
	 * @return the guid
	 */
	public Field getGuid() {
		return guid;
	}

	/**
	 * @param guid the guid to set
	 */
	public void setGuid(Field guid) {
		this.guid = guid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemModel [itemClass=" + itemClass + ", title=" + title
				+ ", description=" + description + ", link=" + link
				+ ", pubDate=" + pubDate + ", comments=" + comments + ", guid="
				+ guid + "]";
	}

	/**
	 * @return the author
	 */
	public Field getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(Field author) {
		this.author = author;
	}

	/**
	 * @return the categories
	 */
	public Field getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Field categories) {
		this.categories = categories;
	}

	/**
	 * @return the enclosure
	 */
	public Field getEnclosure() {
		return enclosure;
	}

	/**
	 * @param enclosure
	 *            the enclosure to set
	 */
	public void setEnclosure(Field enclosure) {
		this.enclosure = enclosure;
	}

	/**
	 * @return the source
	 */
	public Field getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Field source) {
		this.source = source;
	}

}
