/**
 * 
 */
package com.m3ns1.feed.servlet;

import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.m3ns1.feed.processing.ChannelModel;
import com.m3ns1.feed.processing.FeedFinder;
import com.m3ns1.feed.processing.FeedModel;

/**
 * The servlet to lookup your feed classes in the passed packages (FEED_ENTITY_PACKAGES)
 * @author m3ns1
 *
 */
public class FeedAnnotationServletProvider extends HttpServlet {

	public static final String FEED_ENTITY_PACKAGES = "FEED_ENTITY_PACKAGES";
	
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		String packages = config.getInitParameter(FEED_ENTITY_PACKAGES);
		StringTokenizer tokenizer = new StringTokenizer(packages, ",");
		FeedFinder finder = new FeedFinder();
		while (tokenizer.hasMoreElements()) {
			String pkg = tokenizer.nextToken();
			if (finder.getLookupPackages().contains(pkg)) continue;
			finder.getLookupPackages().add(pkg);
		}
		finder.lookup();
		ChannelModel model = ChannelModel.getInstance();
		for (Class<?> clazz : finder.getFoundClasses()) {
			FeedModel fm = new FeedModel(clazz);
			fm.introspect();
			model.getFeedModels().add(fm);
		}
		super.init(config);
	}
	
}
