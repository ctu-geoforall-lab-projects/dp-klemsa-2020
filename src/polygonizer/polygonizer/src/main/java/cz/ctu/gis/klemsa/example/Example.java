package cz.ctu.gis.klemsa.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.GeometryCombiner;
import org.locationtech.jts.operation.linemerge.LineMerger;
import org.locationtech.jts.operation.overlay.OverlayOp;
import org.locationtech.jts.operation.polygonize.Polygonizer;
import org.opengis.feature.simple.SimpleFeature;

import cz.ctu.gis.klemsa.polygonize.PolygonizeOp;

public class Example {
	
	private Example() {
		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(LocalDateTime.now());

		// Load input shapefile.
		LinesLoader linesLoader = new LinesLoader("geometry/lines.shp");
		SimpleFeatureCollection simpleFeatureCollection = linesLoader.getSimpleFeatureCollection();
		
		
		// Check if *.shp contains MultiLineString.
		if (!simpleFeatureCollection.getSchema().getGeometryDescriptor().getType().getName().toString().equals("MultiLineString")) {
			throw new IOException("Input shapefile doesnt contain geometry type MultiLineString.");
		}
		
		System.out.println(LocalDateTime.now());

		// Iterate over collection.
		Collection<Geometry> geometryCollection = new ArrayList<Geometry>();
		
		SimpleFeatureIterator i = simpleFeatureCollection.features();
		while (i.hasNext()) {
			SimpleFeature feature = i.next();
			geometryCollection.add((Geometry) feature.getDefaultGeometry());			
		}
		
		
		// Union to one geometry.
		// Geometry UnionLines = GeometryCombiner.combine(geometryCollection);
		
		
		LineMerger lineMerger = new LineMerger();
		lineMerger.add(geometryCollection);
		Collection<Geometry> mergedLineStrings = lineMerger.getMergedLineStrings();
		
		System.out.println(LocalDateTime.now());

		
		// Calculate nodes.
		GeometryCombiner geometryCombiner = new GeometryCombiner(mergedLineStrings);
		Geometry noDangelsLines = geometryCombiner.combine().union();
		
		
		Polygonizer polygonizer = new Polygonizer();
		polygonizer.add(noDangelsLines);
		
		
		//System.out.println(polygonizer.getPolygons());
		System.out.println(LocalDateTime.now());
		
		//PolygonizeOp polygonizeOp = new PolygonizeOp(geometryCollection);
		System.out.println(PolygonizeOp.polygonize(geometryCollection));
		
		// Calculate lines intersections.
		// OverlayOp overlay = new OverlayOp(UnionLines, null);
		
		// System.out.println(UnionLines);
		
		

		
		
		
		
		
		
	}

}
