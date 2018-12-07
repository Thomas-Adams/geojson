package org.adams.geo.gisweb.geojson;

import java.io.IOException;
import java.io.StringWriter;

import org.adams.geo.gisweb.config.ReferenceSystems;
import org.adams.geo.gisweb.data.dto.Schlag;
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

public class SchlagGeoJsonSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchlagGeoJsonSerializer.class);

	private SchlagGeoJsonSerializer() {
		// Do nothing here
	}

	public static String toGeoJson(final Schlag schlag) {
		SimpleFeatureType schema = null;

		try {
			schema = DataUtilities.createType("SCHLAG", "geom:MultiPolygon:srid=" + ReferenceSystems.SRID_3044
					+ ",gid:Integer,flik:String,kulturcode:String,schlagnr:java.math.BigDecimal,antjahr:java.math.BigDecimal,gemFl:Double");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(schlag.getGeom());
		featureBuilder.add(schlag.getGid());
		featureBuilder.add(schlag.getFlik());
		featureBuilder.add(schlag.getKulturcode());
		featureBuilder.add(schlag.getSchlagnr());
		featureBuilder.add(schlag.getAntjahr());
		featureBuilder.add(schlag.getGemFl());

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
