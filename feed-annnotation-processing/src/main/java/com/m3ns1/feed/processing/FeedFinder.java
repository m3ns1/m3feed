/**
 * 
 */
package com.m3ns1.feed.processing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.m3ns1.feed.annotation.Feed;

/**
 * Feed finder class to introspect your classes
 * @author m3ns1
 * 
 */
public class FeedFinder {

	private List<String> lookupPackages = new ArrayList<String>();

	protected Set<Class<?>> foundClasses = new HashSet<Class<?>>();

	/**
	 * Lookup all Feed classes in the given packages. You can get the list of
	 * all found classes with {@link #getFoundClasses()}
	 */
	public void lookup() {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());
		for (String pkg : getLookupPackages()) {
			Reflections reflections = new Reflections(
					new ConfigurationBuilder()
							.setScanners(new SubTypesScanner(false), // don't
																		// exclude
																		// object
																		// class
									new ResourcesScanner())
							.setUrls(
									ClasspathHelper.forClassLoader(classLoadersList
											.toArray(new ClassLoader[0])))
							.filterInputsBy(
									new FilterBuilder().include(FilterBuilder
											.prefix(pkg))));
			Set<Class<?>> clazzes = reflections.getSubTypesOf(Object.class);
			filterFeedAnnotated(clazzes);
			foundClasses.addAll(clazzes);
		}
	}

	/**
	 * Filter the classes to those which are annotated with {@link Feed}
	 * @param clazzes
	 */
	protected void filterFeedAnnotated(Set<Class<?>> clazzes) {
		Iterator<Class<?>> it = clazzes.iterator();
		List<Class<?>> toremove = new LinkedList<Class<?>>();
		for (;it.hasNext();) {
			Class<?> clazz = it.next();
			if (!isFeedAnnotated(clazz)) {
				toremove.add(clazz);
			}
		}
		for (Class<?> clazz : toremove) {
			clazzes.remove(clazz);
		}
	}

	/**
	 * Check if the argument class has the {@link Feed} annotation
	 * @param clazz
	 * @return
	 */
	protected boolean isFeedAnnotated(Class<?> clazz) {
		if (clazz == null)
			return false;
		return clazz.getAnnotation(Feed.class) != null;
	}

	/**
	 * @return the lookupPackages
	 */
	public List<String> getLookupPackages() {
		return lookupPackages;
	}

	/**
	 * @param lookupPackages
	 *            the lookupPackages to set
	 */
	public void setLookupPackages(List<String> lookupPackages) {
		this.lookupPackages = lookupPackages;
	}

	/**
	 * @return the foundClasses
	 */
	public Set<Class<?>> getFoundClasses() {
		return foundClasses;
	}

	/**
	 * @param foundClasses
	 *            the foundClasses to set
	 */
	public void setFoundClasses(Set<Class<?>> foundClasses) {
		this.foundClasses = foundClasses;
	}

}
