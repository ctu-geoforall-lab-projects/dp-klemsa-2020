package cz.ctu.gis.klemsa.example;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.simple.SimpleFeature;


public class Example {
	
	private Example() {
		
	}
	
	public static void main(String[] args) throws IOException {
		
		// Load input shapefile.
		LinesLoader linesLoader = new LinesLoader("geometry/lines.shp");
		SimpleFeatureCollection simpleFeatureCollection = linesLoader.getSimpleFeatureCollection();
		
		
		// Check if *.shp contains MultiLineString.
		if (!simpleFeatureCollection.getSchema().getGeometryDescriptor().getType().getName().toString().equals("MultiLineString")) {
			throw new IOException("Input shapefile doesnt contain geometry type MultiLineString.");
		}
		
		

		// Iterate over collection.
		SimpleFeatureIterator i = simpleFeatureCollection.features();
		while (i.hasNext()) {
			SimpleFeature feature = i.next();
			
			
			System.out.println(feature.getFeatureType());
			
		}

		
		
		
	}

}
