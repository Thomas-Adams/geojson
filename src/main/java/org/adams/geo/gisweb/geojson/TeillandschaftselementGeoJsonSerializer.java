package org.adams.geo.gisweb.geojson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.adams.geo.gisweb.config.ReferenceSystems;
import org.adams.geo.gisweb.data.dto.Teillandschaftselement;
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

public class TeillandschaftselementGeoJsonSerializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeillandschaftselementGeoJsonSerializer.class);

	private TeillandschaftselementGeoJsonSerializer() {
		// Do nothing here
	}

	public static String toGeoJson(final Teillandschaftselement teillandschaftselement) {
		SimpleFeatureType schema = null;
		try {
			schema = DataUtilities.createType("TEILLANDSCHAFTSELEMENT",
					"geom:MultiPolygon:srid=" + ReferenceSystems.SRID_3044
							+ ",gid:Integer,flik:String,flek:String,nrle:java.math.BigDecimal,schlagnr:java.math.BigDecimal");
		} catch (SchemaException e) {
			LOGGER.error(e.getMessage());
			throw new InvalidSchemaException(e);
		}
		new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER);
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(schema);
		featureBuilder.add(teillandschaftselement.getGeom());
		featureBuilder.add(teillandschaftselement.getGid());
		featureBuilder.add(teillandschaftselement.getFlik());
		featureBuilder.add(teillandschaftselement.getFlek());
		featureBuilder.add(teillandschaftselement.getNrle());
		featureBuilder.add(teillandschaftselement.getSchlagnr());

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

	public static String toGeoJsonArray(final List<Teillandschaftselement> teillandschaftselements) {
		StringBuilder buffer = new StringBuilder();
		String start = "[";
		String end = "]";
		String comma = ",";
		int i = 0;
		buffer.append(start);
		for (Teillandschaftselement teillandschaftselement : teillandschaftselements) {
			if (i > 0) {
				buffer.append(comma);
			}
			buffer.append(toGeoJson(teillandschaftselement));
			i++;
		}
		buffer.append(end);
		return buffer.toString();
	}

}
