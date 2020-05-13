package example;

import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class CreateTypeExample {
	
    public static void main(String[] args) throws Exception {
    	// https://docs.geotools.org/stable/userguide/library/referencing/crs.html
    	
    	CoordinateReferenceSystem sourceCRS = CRS.decode("404000");
    	System.out.println(sourceCRS);
    }
}