package cz.ctu.gis.klemsa.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;

import cz.ctu.gis.klemsa.polygonize.PolygonizeOp;

public class Example {
	
	private Example() {
		
	}
	
	public static void main(String[] args) throws IOException {
		// Load input shapefile.
		Loader linesLoader = new Loader("geometry/lines.shp");
		SimpleFeatureCollection simpleFeatureCollection = linesLoader.getSimpleFeatureCollection();
		
		
		// Iterate over collection.
		Collection<Geometry> geometryCollection = new ArrayList<Geometry>();
		
		SimpleFeatureIterator i = simpleFeatureCollection.features();
		while (i.hasNext()) {
			SimpleFeature feature = i.next();
			geometryCollection.add((Geometry) feature.getDefaultGeometry());			
		}
		
		// Polygonize operation.
		PolygonizeOp polygonizeOp = new PolygonizeOp(geometryCollection);
		System.out.println(polygonizeOp.polygonize());
	}

}
