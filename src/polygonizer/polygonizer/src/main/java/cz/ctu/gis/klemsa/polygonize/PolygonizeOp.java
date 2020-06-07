package cz.ctu.gis.klemsa.polygonize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.util.GeometryExtracter;
import org.locationtech.jts.operation.polygonize.Polygonizer;

/**
 * Polygonize a <code>Collection</code> of {@link Geometry}s or a single Geometry 
 * (which may be a {@link GeoometryCollection}) together.
 * Only the {@link LineString}s will be included to polygonization.
 * {@link LineString}s may contain dangels. These will be added as part of the polygonization.
 * 
 * @author Tomas Klemsa
 *
 */
public class PolygonizeOp 
{
	
	private List lines = new ArrayList();
	
	private GeometryFactory geomFact = null;

	/**
	 * Constructs a polygons from a {@link Collection} 
	 * of {@link Geometry}s.
	 * 
	 * @param geoms a collection of geometries
	 * @param geomFact the geometry factory to use if the collection is empty
	 */
	public PolygonizeOp(Collection geoms, GeometryFactory geomFact)
	{
		this.geomFact = geomFact;
		extract(geoms);
	}
	
	/**
	 * Constructs a polygons from a {@link Collection} 
	 * of {@link Geometry}s.
	 * 
	 * @param geoms a collection of geometries
	 * @return the union of the geometries, 
	 * or <code>null</code> if the input is empty
	 */
	public static Collection polygonize(Collection geoms)
	{
		PolygonizeOp op = new PolygonizeOp(geoms);
		return op.polygonize();
	}
	
	
	/**
	 * Constructs a polygons from a {@link Collection} 
	 * of {@link Geometry}s.
	 * 
	 * If no input geometries were provided but a {@link GeometryFactory} was provided, 
	 * an empty {@link GeometryCollection} is returned.
     *
	 * @param geoms a collection of geometries
	 * @param geomFact the geometry factory to use if the collection is empty
	 * @return the union of the geometries,
	 * or an empty GEOMETRYCOLLECTION
	 */
	public static Collection polygonize(Collection geoms, GeometryFactory geomFact)
	{
		PolygonizeOp op = new PolygonizeOp(geoms, geomFact);
		return op.polygonize();
	}
	
	/**
	 * Constructs a polygons from a {@link Geometry}
	 * (which may be a {@link GeometryCollection}).
	 * 
	 * @param geom a geometry to union
	 * @return the union of the elements of the geometry
	 * or an empty GEOMETRYCOLLECTION
	 */
	public static Collection polygonize(Geometry geom)
	{
		PolygonizeOp op = new PolygonizeOp(geom);
		return op.polygonize();
	}
	
	/**
	 * Constructs a polygons from a {@link Collection} 
	 * of {@link Geometry}s, using the {@link GeometryFactory}
	 * of the input geometries.
	 * 
	 * @param geoms a collection of geometries
	 */
	public PolygonizeOp(Collection geoms)
	{
		extract(geoms);
	}
	
	/**
	 * Constructs a polygons from a {@link Geometry}
	 * (which may be a {@link GeometryCollection}).
	 * @param geom
	 */
	public PolygonizeOp(Geometry geom)
	{
		extract(geom);
	}
	
	private void extract(Collection geoms)
	{
		for (Iterator i = geoms.iterator(); i.hasNext();) {
			Geometry geom = (Geometry) i.next();
			extract(geom);
		}
	}
	
	private void extract(Geometry geom)
	{
		if (geomFact == null)
			geomFact = geom.getFactory();

		// Extract only lineStrings.
		GeometryExtracter.extract(geom, LineString.class, lines);
	}

	/**
	 * Gets the list of polygons formed by the polygonization.
	 * @return a collection of {@link Polygon}s
	 */
	public Collection polygonize()
	{
		Polygonizer polygonizer = new Polygonizer();
		Geometry lineGeom = geomFact.buildGeometry(lines);
		
		// Calculate nodes.
		Geometry unionLines = lineGeom.union();
		System.out.println(unionLines);
		
		// Calculate polygons.
		polygonizer.add(unionLines);
			
		return polygonizer.getPolygons();
	}
}
















