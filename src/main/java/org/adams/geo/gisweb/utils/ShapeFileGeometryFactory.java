package org.adams.geo.gisweb.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.adams.geo.gisweb.config.ReferenceSystems;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class ShapeFileGeometryFactory extends GeometryFactory {

	private static final long serialVersionUID = 1L;

	private static final String SHAPE_FILE_NAME = "D:\\_geodata\\Goettingen\\Goettingen_Amt.shp";

	public static Geometry getGeometry() throws Exception {
		File file = new File(SHAPE_FILE_NAME);
		Map<String, Object> map = new HashMap<>();
		map.put("url", file.toURI().toURL());
		DataStore dataStore = DataStoreFinder.getDataStore(map);
		String typeName = dataStore.getTypeNames()[0];
		FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
		Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
		try (FeatureIterator<SimpleFeature> features = collection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry g = (Geometry) feature.getDefaultGeometryProperty().getValue();
				g.setSRID(ReferenceSystems.SRID_3044_NUMBER);
				return g;
			}
		}
		return null;

	}

	public static void listGeometries() throws Exception {
		File file = new File(SHAPE_FILE_NAME);
		Map<String, Object> map = new HashMap<>();
		map.put("url", file.toURI().toURL());
		DataStore dataStore = DataStoreFinder.getDataStore(map);
		String typeName = dataStore.getTypeNames()[0];
		FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
		Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
		try (FeatureIterator<SimpleFeature> features = collection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				Geometry g = (Geometry) feature.getDefaultGeometryProperty().getValue();
				g.setSRID(ReferenceSystems.SRID_3044_NUMBER);
			}
		}

	}

	public static void main(final String[] args) throws Exception {
		listGeometries();
	}

}
