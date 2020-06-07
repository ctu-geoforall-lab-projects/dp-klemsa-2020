package cz.ctu.gis.klemsa.example;

import java.io.IOException;

import org.geotools.data.DataUtilities;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;

public class Loader {
	
	private final SimpleFeatureCollection simpleFeatureCollection;
	
	/**
	 * 
	 * @param inputResource
	 * @throws IOException
	 */
	public Loader(String inputResource) throws IOException {
		FileDataStore fileDataStore;
		fileDataStore = FileDataStoreFinder.getDataStore(getClass().getClassLoader().getResource(inputResource));
	    SimpleFeatureSource simpleFeatureSource = fileDataStore.getFeatureSource();
	    simpleFeatureCollection = DataUtilities.collection(simpleFeatureSource.getFeatures());
	}
	
	/**
	 * 
	 * @return
	 */
	public SimpleFeatureCollection getSimpleFeatureCollection() {
		return this.simpleFeatureCollection;
	}
}
