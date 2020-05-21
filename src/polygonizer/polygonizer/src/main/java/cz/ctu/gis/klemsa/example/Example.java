package cz.ctu.gis.klemsa.example;

import java.io.IOException;
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
		
		// Calculate nodes.
		GeometryCombiner geometryCombiner = new GeometryCombiner(mergedLineStrings);
		Geometry graph = geometryCombiner.combine();
		Geometry graph1 = graph.union();
		
		Polygonizer polygonizer = new Polygonizer();
		polygonizer.add(graph1);
		
		
		
		
		System.out.println(polygonizer.getPolygons());
		
		// Calculate lines intersections.
		// OverlayOp overlay = new OverlayOp(UnionLines, null);
		
		// System.out.println(UnionLines);
		
		

		
		
		
		
		
		
	}

}
