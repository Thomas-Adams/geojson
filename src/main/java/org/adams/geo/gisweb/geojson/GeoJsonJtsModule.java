package org.adams.geo.gisweb.geojson;

import org.adams.geo.gisweb.config.ReferenceSystems;

import com.bedatadriven.jackson.datatype.jts.parsers.GenericGeometryParser;
import com.bedatadriven.jackson.datatype.jts.parsers.GeometryCollectionParser;
import com.bedatadriven.jackson.datatype.jts.parsers.LineStringParser;
import com.bedatadriven.jackson.datatype.jts.parsers.MultiLineStringParser;
import com.bedatadriven.jackson.datatype.jts.parsers.MultiPointParser;
import com.bedatadriven.jackson.datatype.jts.parsers.MultiPolygonParser;
import com.bedatadriven.jackson.datatype.jts.parsers.PointParser;
import com.bedatadriven.jackson.datatype.jts.parsers.PolygonParser;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

public class GeoJsonJtsModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public GeoJsonJtsModule() {
		this(new GeometryFactory(new PrecisionModel(), ReferenceSystems.SRID_3044_NUMBER));
	}

	public GeoJsonJtsModule(final GeometryFactory geometryFactory) {
		super("GeoJsonJtsModule", new Version(1, 0, 0, null, "com.bedatadriven", "jackson-datatype-jts"));

		addSerializer(Geometry.class, new GeometrySerializer());
		GenericGeometryParser genericGeometryParser = new GenericGeometryParser(geometryFactory);
		addDeserializer(Geometry.class, new GeometryDeserializer<>(genericGeometryParser));
		addDeserializer(Point.class, new GeometryDeserializer<>(new PointParser(geometryFactory)));
		addDeserializer(MultiPoint.class, new GeometryDeserializer<>(new MultiPointParser(geometryFactory)));
		addDeserializer(LineString.class, new GeometryDeserializer<>(new LineStringParser(geometryFactory)));
		addDeserializer(MultiLineString.class, new GeometryDeserializer<>(new MultiLineStringParser(geometryFactory)));
		addDeserializer(Polygon.class, new GeometryDeserializer<>(new PolygonParser(geometryFactory)));
		addDeserializer(MultiPolygon.class, new GeometryDeserializer<>(new MultiPolygonParser(geometryFactory)));
		addDeserializer(GeometryCollection.class,
				new GeometryDeserializer<>(new GeometryCollectionParser(geometryFactory, genericGeometryParser)));
	}
}