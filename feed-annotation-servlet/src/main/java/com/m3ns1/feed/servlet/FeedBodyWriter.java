/**
 * 
 */
package com.m3ns1.feed.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.m3ns1.feed.processing.ChannelModel;
import com.m3ns1.feed.processing.FeedModel;
import com.m3ns1.feed.processing.FeedSerializer;

/**
 * A message body writer implementatino for the feeds
 * @author m3ns1
 *
 */
@Provider
@Produces(value={"application/rss+xml"})
public class FeedBodyWriter implements MessageBodyWriter<Object> {

	private ChannelModel cm = ChannelModel.getInstance();

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object, java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	@Override
	public long getSize(Object arg0, Class<?> clazz, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#isWriteable(java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType)
	 */
	@Override
	public boolean isWriteable(Class<?> clazz, Type arg1, Annotation[] arg2,
			MediaType mediaType) {
		if (cm.getFeedModel(clazz)!=null && mediaType.getType().equals("application") && mediaType.getSubtype().equals("rss+xml")) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object, java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[], javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap, java.io.OutputStream)
	 */
	@Override
	public void writeTo(Object arg0, Class<?> clazz, Type arg2,
			Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream out)
					throws IOException, WebApplicationException {
		FeedModel fm = cm.getFeedModel(clazz);
		if (fm!=null) {
			FeedSerializer serializer = new FeedSerializer();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, Charset.forName("UTF-8")));
			try {
				writer.write(serializer.marshal(arg0));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// flush the buffered writer
				writer.flush();
			}
		}
	}

}
