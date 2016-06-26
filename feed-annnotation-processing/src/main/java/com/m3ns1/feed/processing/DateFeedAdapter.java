/**
 * 
 */
package com.m3ns1.feed.processing;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * An Xml Date adatper which will be used to marshal the date objects in your feed. The marshalled date will be in the format:
 * <code>E, dd MMM yyyy H:mm:ss Z</code>
 * @author m3ns1
 *
 */
public class DateFeedAdapter extends XmlAdapter<String, Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy H:mm:ss Z"); // Sat, 07 Sep 2002 0:00:01 GMT
	
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

	public String getDateFormatString() {
		return dateFormat.toPattern();
	}
	
	public void setDateFormatString(String format) {
		dateFormat = new SimpleDateFormat(format);
	}
}
