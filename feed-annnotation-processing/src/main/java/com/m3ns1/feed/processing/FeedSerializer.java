/**
 * 
 */
package com.m3ns1.feed.processing;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.m3ns1.feed.annotation.Description;
import com.m3ns1.feed.annotation.LastBuildDate;
import com.m3ns1.feed.annotation.PubDate;

/**
 * An Xml marshaller for your feeds
 * 
 * @author m3ns1
 *
 */
public class FeedSerializer extends XmlAdapter<String, Object> {

	private ChannelModel channelModel = ChannelModel.getInstance();

	public FeedSerializer() {
	}

	/**
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Object unmarshal(String v) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(Object v) throws Exception {
		FeedModel fm = channelModel.getFeedModel(v.getClass());
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element rss = doc.createElement("rss");
		rss.setAttribute("version", "2.0");
		Element channel = doc.createElement("channel");
		DateFeedAdapter dfa = new DateFeedAdapter();
		if (fm != null) {
			String titleOfFeed = (String) BeanUtil.getValueFor(fm.getTitle(), v);
			String description = (String) BeanUtil.getValueFor(fm.getDescription(), v);
			String link = (String) BeanUtil.getValueFor(fm.getLink(), v);
			Date pubDate = (Date) BeanUtil.getValueFor(fm.getPubDate(), v);

			String lang = (String) BeanUtil.getValueFor(fm.getLangauge(), v);
			String copyright = (String) BeanUtil.getValueFor(fm.getCopyright(), v);
			String me = (String) BeanUtil.getValueFor(fm.getManagingEditor(), v);
			String wm = (String) BeanUtil.getValueFor(fm.getWebMaster(), v);
			Date lbd = (Date) BeanUtil.getValueFor(fm.getLastBuildDate(), v);
			@SuppressWarnings("unchecked")
			List<String> categories = (List<String>) BeanUtil.getValueFor(fm.getCategories(), v);
			String generator = (String) BeanUtil.getValueFor(fm.getGenerator(), v);
			String docs = (String) BeanUtil.getValueFor(fm.getDocs(), v);
			String cloud = (String) BeanUtil.getValueFor(fm.getCloud(), v);
			String ttl = (String) BeanUtil.getValueFor(fm.getTtl(), v);
			String image = (String) BeanUtil.getValueFor(fm.getImage(), v);
			String rating = (String) BeanUtil.getValueFor(fm.getRating(), v);

			@SuppressWarnings("unchecked")
			List<Object> feedItems = (List<Object>) BeanUtil.getValueFor(fm.getItems(), v);

			Element t = doc.createElement("title");
			Element l = doc.createElement("link");
			Element d = doc.createElement("description");
			Element elang = doc.createElement("language");
			Element ecopy = doc.createElement("copyright");
			Element eme = doc.createElement("managingEditor");
			Element ewm = doc.createElement("webMaster");
			Element elbd = doc.createElement("lastBuildDate");
			Element egen = doc.createElement("generator");
			Element edocs = doc.createElement("docs");
			Element ecloud = doc.createElement("cloud");
			Element ettl = doc.createElement("ttl");
			Element eimg = doc.createElement("image");
			Element erating = doc.createElement("rating");

			Description annot = fm.getDescription().getAnnotation(Description.class);
			PubDate pd = fm.getPubDate().getAnnotation(PubDate.class);

			Element p = doc.createElement("pubDate");

			t.appendChild(doc.createTextNode(titleOfFeed));
			l.appendChild(doc.createTextNode(link));
			if (annot.cdata()) {
				d.appendChild(doc.createCDATASection(description.replaceAll("\r|\n", "<br />")));
			} else {
				d.appendChild(doc.createTextNode(description));
			}

			elang.appendChild(doc.createTextNode(lang));
			ecopy.appendChild(doc.createTextNode(copyright));
			eme.appendChild(doc.createTextNode(me));
			ewm.appendChild(doc.createTextNode(wm));

			// set the custom date format if necessary
			if (!pd.dateFormat().equals(dfa.getDateFormatString())) {
				dfa.setDateFormatString(pd.dateFormat());
			}
			p.appendChild(doc.createTextNode(dfa.marshal(pubDate)));

			channel.appendChild(t);
			channel.appendChild(l);
			channel.appendChild(d);
			channel.appendChild(p);
			if (!BeanUtil.isNullOrEmpty(lang)) {
				channel.appendChild(elang);
			}
			if (!BeanUtil.isNullOrEmpty(copyright)) {
				channel.appendChild(ecopy);
			}
			if (!BeanUtil.isNullOrEmpty(me)) {
				channel.appendChild(eme);
			}
			if (!BeanUtil.isNullOrEmpty(wm)) {
				channel.appendChild(ewm);
			}
			if (lbd != null) {
				LastBuildDate lbdf = fm.getLastBuildDate().getAnnotation(LastBuildDate.class);
				// set the custom format
				if (!lbdf.dateFormat().equals(dfa.getDateFormatString())) {
					dfa.setDateFormatString(lbdf.dateFormat());
				}
				elbd.appendChild(doc.createTextNode(dfa.marshal(lbd)));
				channel.appendChild(elbd);
			}

			if (categories != null && !categories.isEmpty()) {
				for (String cat : categories) {
					Element ecat = doc.createElement("category");
					ecat.appendChild(doc.createTextNode(cat));
					channel.appendChild(ecat);
				}
			}
			if (!BeanUtil.isNullOrEmpty(generator)) {
				egen.appendChild(doc.createTextNode(generator));
				channel.appendChild(egen);
			}

			if (!BeanUtil.isNullOrEmpty(docs)) {
				edocs.appendChild(doc.createElement(docs));
				channel.appendChild(edocs);
			}
			if (!BeanUtil.isNullOrEmpty(cloud)) {
				ecloud.appendChild(doc.createTextNode(cloud));
				channel.appendChild(ecloud);
			}

			if (!BeanUtil.isNullOrEmpty(ttl)) {
				ettl.appendChild(doc.createTextNode(ttl));
				channel.appendChild(ettl);
			}

			if (!BeanUtil.isNullOrEmpty(image)) {
				eimg.appendChild(doc.createTextNode(image));
				channel.appendChild(eimg);
			}

			if (!BeanUtil.isNullOrEmpty(rating)) {
				erating.appendChild(doc.createTextNode(rating));
				channel.appendChild(erating);
			}

			for (Object item : feedItems) {
				FeedItemSerializer fis = new FeedItemSerializer();
				fis.setParentDocument(doc);
				channel.appendChild(fis.marshal(item));
			}
		}
		rss.appendChild(channel);
		doc.appendChild(rss);

		return new XmlDocSerializer().marshal(doc);
	}

}
