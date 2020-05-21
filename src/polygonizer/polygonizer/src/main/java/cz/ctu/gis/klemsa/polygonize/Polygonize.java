package cz.ctu.gis.klemsa.polygonize;

import java.util.Collection;
import java.util.Vector;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.simple.SimpleFeature;

public class Polygonize {
	
	/**
	 * 
	 */
	private final SimpleFeatureCollection lines;
	
	/**
	 * 
	 * @param lines
	 */
	public Polygonize(SimpleFeatureCollection lines) {
		this.lines = lines;
	}
	
	
	/**
	 * 
	 */
	public void process() {
		// Iterate over collection.
		SimpleFeatureIterator i = lines.features();
		while (i.hasNext()) {
			SimpleFeature feature = i.next();
			
			
			System.out.println(feature.getFeatureType());
			
		}
	}
	
}
