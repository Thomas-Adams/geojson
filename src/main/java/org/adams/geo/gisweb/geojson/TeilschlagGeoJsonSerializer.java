package org.adams.geo.gisweb.geojson;

import java.io.IOException;
import java.io.StringWriter;

import org.adams.geo.gisweb.config.ReferenceSystems;
import org.adams.geo.gisweb.data.dto.Teilschlag;
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

public class TeilschlagGeoJsonSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeilschlagGeoJsonSerializer.class);

	private TeilschlagGeoJsonSerializer() {
		// Do nothing here
	}

	public static String toGeoJson(final Teilschlag teilschlag) {
		SimpleFeatureType schema = null;
		try {
			schema = DataUtilities.createType("TEILSCHLAG", "geom:MultiPolygon:srid=" + ReferenceSystems.SRID_3044
					+ ",gid:Integer,flIk:String,schlagnr:java.math.BigDecimal,tsbez:String,kulturcode:String,antjahr:java.math.BigDecimal,gemFl:Double");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(teilschlag.getGeom());
		featureBuilder.add(teilschlag.getGid());
		featureBuilder.add(teilschlag.getFlik());
		featureBuilder.add(teilschlag.getSchlagnr());
		featureBuilder.add(teilschlag.getTsbez());
		featureBuilder.add(teilschlag.getKulturcode());
		featureBuilder.add(teilschlag.getAntjahr());
		featureBuilder.add(teilschlag.getGemFl());

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
