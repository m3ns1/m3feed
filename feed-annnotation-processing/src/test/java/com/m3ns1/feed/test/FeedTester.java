/**
 * 
 */
package com.m3ns1.feed.test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.m3ns1.feed.processing.ChannelModel;
import com.m3ns1.feed.processing.FeedFinder;
import com.m3ns1.feed.processing.FeedModel;
import com.m3ns1.feed.processing.FeedSerializer;

/**
 * @author m3ns1
 *
 */
public class FeedTester {

	private static ChannelModel model;

	@BeforeClass
	public static void setup() {
		System.out.println("Setup...");
		FeedFinder finder = new FeedFinder();
		finder.getLookupPackages().add("com.m3ns1.feed.test");
		finder.lookup();
		model = ChannelModel.getInstance();
		for (Class<?> clazz : finder.getFoundClasses()) {
			FeedModel fm = new FeedModel(clazz);
			fm.introspect();
			model.getFeedModels().add(fm);
			System.out.println("Adding fm: " + fm.getTitle());
		}
	}

	@Test
	public void test() throws IOException {
		ChannelDto channel = new ChannelDto();
		channel.setTitle("Test");
		channel.setDescription("Some description");
		channel.setLink("Link to the channel");
		channel.setPubDate(new Date());
		ItemDto item = new ItemDto();
		item.setComments("http://blog.m3ns1.com/comments123");
		item.setDescription("How to use the feed annotation framework");
		item.setGuid("http://blog.m3ns1.com/guid/article_0001");
		item.setLink("http://blog.m3ns1.com/article_0001");
		item.setPubDate(new Date());
		item.setTitle("Annotation-driven Feeds");
		channel.getItems().add(item);
		// setup the channel and its items
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FeedSerializer serializer = new FeedSerializer();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, Charset.forName("UTF-8")));
		try {
			// write the channel to the output stream
			writer.write(serializer.marshal(channel));
			writer.flush();
			System.out.println("Generated feed: " + out.toString());
		} catch (Exception e) {
			// some exception handling
		} finally {
			// flush the buffered writer
			writer.flush();
		}
	}

}
