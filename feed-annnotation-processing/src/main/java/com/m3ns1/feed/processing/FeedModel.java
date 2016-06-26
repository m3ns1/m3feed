/**
 * 
 */
package com.m3ns1.feed.processing;

import java.lang.reflect.Field;

import com.m3ns1.feed.annotation.Categories;
import com.m3ns1.feed.annotation.Cloud;
import com.m3ns1.feed.annotation.Copyright;
import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.Docs;
import com.m3ns1.feed.annotation.FeedItems;
import com.m3ns1.feed.annotation.Generator;
import com.m3ns1.feed.annotation.Image;
import com.m3ns1.feed.annotation.Language;
import com.m3ns1.feed.annotation.LastBuildDate;
import com.m3ns1.feed.annotation.Link;
import com.m3ns1.feed.annotation.ManagingEditor;
import com.m3ns1.feed.annotation.PubDate;
import com.m3ns1.feed.annotation.Rating;
import com.m3ns1.feed.annotation.TTL;
import com.m3ns1.feed.annotation.Title;
import com.m3ns1.feed.annotation.WebMaster;

/**
 * The feed model class that represents a feed/channel
 * @author m3ns1
 *
 */
public class FeedModel {

	private Class<?> feedClass = null;
	private Field title = null;
	private Field description = null;
	private Field link = null;
	private Field pubDate = null;
	private Field items = null;
	private ItemModel itemModel = null;
	private Field langauge = null;
	private Field copyright = null;
	private Field managingEditor = null;
	private Field webMaster = null;
	private Field lastBuildDate = null;
	private Field categories = null;
	private Field generator = null;
	private Field docs = null;
	private Field cloud = null;
	private Field ttl = null;
	private Field image = null;
	private Field rating = null;

	/**
	 * A feed model must have a feed class
	 * @param feedClazz
	 */
	public FeedModel(Class<?> feedClazz) {
		this.feedClass = feedClazz;
	}

	/**
	 * Do the introspection and set up the model
	 */
	public void introspect() {
		if (getFeedClass()==null) {
			return;
		}
		setTitle(IntrospectionUtil.getAnnotatedField(feedClass, Title.class));
		setDescription(IntrospectionUtil.getAnnotatedField(feedClass, Description.class));
		setLink(IntrospectionUtil.getAnnotatedField(feedClass, Link.class));
		setPubDate(IntrospectionUtil.getAnnotatedField(feedClass, PubDate.class));
		setLangauge(IntrospectionUtil.getAnnotatedField(feedClass, Language.class));
		setCopyright(IntrospectionUtil.getAnnotatedField(feedClass, Copyright.class));
		setManagingEditor(IntrospectionUtil.getAnnotatedField(feedClass, ManagingEditor.class));
		setWebMaster(IntrospectionUtil.getAnnotatedField(feedClass, WebMaster.class));
		setLastBuildDate(IntrospectionUtil.getAnnotatedField(feedClass, LastBuildDate.class));
		setCategories(IntrospectionUtil.getAnnotatedField(feedClass, Categories.class));
		setGenerator(IntrospectionUtil.getAnnotatedField(feedClass, Generator.class));
		setDocs(IntrospectionUtil.getAnnotatedField(feedClass, Docs.class));
		setCloud(IntrospectionUtil.getAnnotatedField(feedClass, Cloud.class));
		setTtl(IntrospectionUtil.getAnnotatedField(feedClass, TTL.class));
		setImage(IntrospectionUtil.getAnnotatedField(feedClass, Image.class));
		setRating(IntrospectionUtil.getAnnotatedField(feedClass, Rating.class));

		setItems(IntrospectionUtil.getAnnotatedField(feedClass, FeedItems.class));
		if (getItems()!=null) {
			// setup the items model
			FeedItems feedItems = getItems().getAnnotation(FeedItems.class);
			Class<?> targetClass = feedItems.target();
			itemModel = new ItemModel(targetClass);
			itemModel.introspect();
		}
	}

	/**
	 * @return the feedClass
	 */
	public Class<?> getFeedClass() {
		return feedClass;
	}

	/**
	 * @param feedClass the feedClass to set
	 */
	public void setFeedClass(Class<?> feedClass) {
		this.feedClass = feedClass;
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
	 * @return the items
	 */
	public Field getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Field items) {
		this.items = items;
	}

	/**
	 * @return the itemModel
	 */
	public ItemModel getItemModel() {
		return itemModel;
	}

	/**
	 * @param itemModel the itemModel to set
	 */
	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedModel [feedClass=" + feedClass + ", title=" + title
				+ ", description=" + description + ", link=" + link
				+ ", pubDate=" + pubDate + ", items=" + items + ", itemModel="
				+ itemModel + "]";
	}

	/**
	 * @return the langauge
	 */
	public Field getLangauge() {
		return langauge;
	}

	/**
	 * @param langauge
	 *            the langauge to set
	 */
	public void setLangauge(Field langauge) {
		this.langauge = langauge;
	}

	/**
	 * @return the copyright
	 */
	public Field getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(Field copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the managingEditor
	 */
	public Field getManagingEditor() {
		return managingEditor;
	}

	/**
	 * @param managingEditor
	 *            the managingEditor to set
	 */
	public void setManagingEditor(Field managingEditor) {
		this.managingEditor = managingEditor;
	}

	/**
	 * @return the webMaster
	 */
	public Field getWebMaster() {
		return webMaster;
	}

	/**
	 * @param webMaster
	 *            the webMaster to set
	 */
	public void setWebMaster(Field webMaster) {
		this.webMaster = webMaster;
	}

	/**
	 * @return the lastBuildDate
	 */
	public Field getLastBuildDate() {
		return lastBuildDate;
	}

	/**
	 * @param lastBuildDate
	 *            the lastBuildDate to set
	 */
	public void setLastBuildDate(Field lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
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
	 * @return the generator
	 */
	public Field getGenerator() {
		return generator;
	}

	/**
	 * @param generator
	 *            the generator to set
	 */
	public void setGenerator(Field generator) {
		this.generator = generator;
	}

	/**
	 * @return the docs
	 */
	public Field getDocs() {
		return docs;
	}

	/**
	 * @param docs
	 *            the docs to set
	 */
	public void setDocs(Field docs) {
		this.docs = docs;
	}

	/**
	 * @return the cloud
	 */
	public Field getCloud() {
		return cloud;
	}

	/**
	 * @param cloud
	 *            the cloud to set
	 */
	public void setCloud(Field cloud) {
		this.cloud = cloud;
	}

	/**
	 * @return the ttl
	 */
	public Field getTtl() {
		return ttl;
	}

	/**
	 * @param ttl
	 *            the ttl to set
	 */
	public void setTtl(Field ttl) {
		this.ttl = ttl;
	}

	/**
	 * @return the image
	 */
	public Field getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Field image) {
		this.image = image;
	}

	/**
	 * @return the rating
	 */
	public Field getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(Field rating) {
		this.rating = rating;
	}

}
