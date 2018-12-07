package org.adams.geo.gisweb.geojson;

import java.io.IOException;
import java.io.StringWriter;

import org.adams.geo.gisweb.config.ReferenceSystems;
import org.adams.geo.gisweb.data.dto.Landschaftselement;
import org.adams.geo.gisweb.exceptions.InvalidSchemaException;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class LandschaftselementGeoJsonSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(LandschaftselementGeoJsonSerializer.class);

	private LandschaftselementGeoJsonSerializer() {
		// Do nothing here
	}

	public static String toGeoJson(final Landschaftselement landschaftselement) {
		SimpleFeatureType schema = null;
		try {
			schema = DataUtilities.createType("LANDSCHAFTSELEMENT", "geom:MultiPolygon:srid="
					+ ReferenceSystems.SRID_3044
					+ ",gid:Integer,flek:String,typ:Integer,typTxt:String,flaeche:java.math.BigDecimal,ccRelevan:Integer,stand:String");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(landschaftselement.getGeom());
		featureBuilder.add(landschaftselement.getGid());
		featureBuilder.add(landschaftselement.getFlek());
		featureBuilder.add(landschaftselement.getTyp());
		featureBuilder.add(landschaftselement.getTypTxt());
		featureBuilder.add(landschaftselement.getFlaeche());
		featureBuilder.add(landschaftselement.getCcRelevan());
		featureBuilder.add(landschaftselement.getStand());

		SimpleFeature feature = featureBuilder.buildFeature(null);
		FeatureJSON fjson = new FeatureJSON();
		StringWriter writer = new StringWriter();
		try {
			fjson.writeFeature(feature, writer);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		return writer.toString();
	}
}
