package org.adams.geo.gisweb.geojson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.adams.geo.gisweb.config.ReferenceSystems;
import org.adams.geo.gisweb.data.dto.Feldblock;
import org.adams.geo.gisweb.exceptions.InvalidSchemaException;
import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.GeometryBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Component
public class FeldblockGeoJsonSerializer extends JsonSerializer<Feldblock> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeldblockGeoJsonSerializer.class);

	private FeldblockGeoJsonSerializer() {
		// Do nothing here
	}

	public static String toGeoJson(final Feldblock feldblock) {
		SimpleFeatureType schema = null;
		try {
			schema = DataUtilities.createType("FELDBLOCK", "geom:MultiPolygon:srid=" + ReferenceSystems.SRID_3044
					+ ",gid:Integer,flik:String,flaeche:Double,bnk:String,stand:String,antJahr:Integer,bnkTxt:String");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		new GeometryBuilder(geometryFactory);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(feldblock.getGeom());
		featureBuilder.add(feldblock.getGid());
		featureBuilder.add(feldblock.getFlik());
		featureBuilder.add(feldblock.getFlaeche());
		featureBuilder.add(feldblock.getBnk());
		featureBuilder.add(feldblock.getStand());
		featureBuilder.add(feldblock.getAntJahr());
		featureBuilder.add(feldblock.getBnkTxt());

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

	public static String toGeoJsonArray(final List<Feldblock> feldblocks) {
		StringBuilder buffer = new StringBuilder();
		String start = "[";
		String end = "]";
		String comma = ",";
		int i = 0;
		buffer.append(start);
		for (Feldblock feldblock : feldblocks) {
			if (i > 0) {
				buffer.append(comma);
			}
			buffer.append(toGeoJson(feldblock));
			i++;
		}
		buffer.append(end);
		return buffer.toString();
	}

	@Override
	public void serialize(Feldblock value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

		SimpleFeatureType schema = null;
		try {
			schema = DataUtilities.createType("FELDBLOCK", "geom:MultiPolygon:srid=" + ReferenceSystems.SRID_3044
					+ ",gid:Integer,flik:String,flaeche:Double,bnk:String,stand:String,antJahr:Integer,bnkTxt:String");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		new GeometryBuilder(geometryFactory);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(value.getGeom());
		featureBuilder.add(value.getGid());
		featureBuilder.add(value.getFlik());
		featureBuilder.add(value.getFlaeche());
		featureBuilder.add(value.getBnk());
		featureBuilder.add(value.getStand());
		featureBuilder.add(value.getAntJahr());
		featureBuilder.add(value.getBnkTxt());

		SimpleFeature feature = featureBuilder.buildFeature(null);
		FeatureJSON fjson = new FeatureJSON();

		StringWriter writer = new StringWriter();
		try {
			fjson.writeFeature(feature, writer);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		gen.writeRaw(writer.toString());
	}
}
