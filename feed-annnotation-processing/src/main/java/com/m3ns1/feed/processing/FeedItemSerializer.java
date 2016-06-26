/**
 * 
 */
package com.m3ns1.feed.processing;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.PubDate;
import com.m3ns1.feed.test.FeedItem;

/**
 * An Xml marshaller for your feed items
 * @author m3ns1
 *
 */
public class FeedItemSerializer extends XmlAdapter<Element, Object> {

	/**
	 * The parent document to which this item belongs to
	 */
	private Document parentDocument = null;
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public FeedItem unmarshal(Element v) throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public Element marshal(Object v) throws Exception {
		ChannelModel model = ChannelModel.getInstance();
		ItemModel im = model.getFeedItemModel(v.getClass());
		Element entry = parentDocument.createElement("item");
		DateFeedAdapter dfa = new DateFeedAdapter();
		if (im!=null) {
			String title = (String) BeanUtil.getValueFor(im.getTitle(), v);
			String desc = (String) BeanUtil.getValueFor(im.getDescription(), v);
			String link = (String) BeanUtil.getValueFor(im.getLink(), v);
			String comments = (String) BeanUtil.getValueFor(im.getComments(), v);
			String guid = (String) BeanUtil.getValueFor(im.getGuid(), v);
			Date pubDate = (Date) BeanUtil.getValueFor(im.getPubDate(), v);
			String author = (String) BeanUtil.getValueFor(im.getAuthor(), v);
			@SuppressWarnings("unchecked")
			List<String> cats = (List<String>) BeanUtil.getValueFor(im.getCategories(), v);
			String enclosure = (String) BeanUtil.getValueFor(im.getEnclosure(), v);
			String source = (String) BeanUtil.getValueFor(im.getSource(), v);

			Description annot =  im.getDescription().getAnnotation(Description.class);
			PubDate pd = im.getPubDate().getAnnotation(PubDate.class);

			Element t = parentDocument.createElement("title");
			Element d = parentDocument.createElement("description");
			Element l = parentDocument.createElement("link");
			Element c = parentDocument.createElement("comments");
			Element g = parentDocument.createElement("guid");
			Element p = parentDocument.createElement("pubDate");
			Element eauthor = parentDocument.createElement("author");
			Element eenc = parentDocument.createElement("enclosure");
			Element esource = parentDocument.createElement("source");

			t.appendChild(parentDocument.createTextNode(title));
			if (annot.cdata()) {
				d.appendChild(parentDocument.createCDATASection(desc.replaceAll("\n|\r", "<br />")));
			} else {
				d.appendChild(parentDocument.createTextNode(desc));
			}

			// set the dateformat if necessary
			if (!pd.dateFormat().equals(dfa.getDateFormatString())) {
				dfa.setDateFormatString(pd.dateFormat());
			}

			l.appendChild(parentDocument.createTextNode(link));
			c.appendChild(parentDocument.createTextNode(comments));
			g.appendChild(parentDocument.createTextNode(guid));
			p.appendChild(parentDocument.createTextNode(dfa.marshal(pubDate)));

			entry.appendChild(t);
			entry.appendChild(d);
			entry.appendChild(l);
			entry.appendChild(c);
			entry.appendChild(g);
			entry.appendChild(p);

			if (!BeanUtil.isNullOrEmpty(author)) {
				eauthor.appendChild(parentDocument.createTextNode(author));
				entry.appendChild(eauthor);
			}

			if (cats != null && !cats.isEmpty()) {
				for (String cat : cats) {
					Element ecat = parentDocument.createElement("category");
					ecat.appendChild(parentDocument.createTextNode(cat));
					entry.appendChild(ecat);
				}
			}

			if (!BeanUtil.isNullOrEmpty(enclosure)) {
				eenc.appendChild(parentDocument.createTextNode(enclosure));
				entry.appendChild(eenc);
			}

			if (!BeanUtil.isNullOrEmpty(source)) {
				esource.appendChild(parentDocument.createTextNode(source));
				entry.appendChild(esource);
			}
		}

		return entry;
	}

	/**
	 * @return the parentDocument
	 */
	public Document getParentDocument() {
		return parentDocument;
	}

	/**
	 * @param parentDocument the parentDocument to set
	 */
	public void setParentDocument(Document parentDocument) {
		this.parentDocument = parentDocument;
	}

}
