/**
 * 
 */
package com.m3ns1.feed.processing;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * An Xml document marshaller to output the Xml document as a string
 * @author m3ns1
 *
 */
public class XmlDocSerializer extends XmlAdapter<String, Document> {

	private static final Charset CHARSET = Charset.forName("UTF-8");

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Document unmarshal(String v) throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(Document v) throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, CHARSET.displayName());
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		transformer.transform(new DOMSource(v), new StreamResult(baos));
		String output = baos.toString(CHARSET.displayName());
		return output;
	}

}
