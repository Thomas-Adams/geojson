package org.adams.geo.gisweb.config;

public interface ReferenceSystems {

	public static final String SRID_3044 = "3044";

	public static final Integer SRID_3044_NUMBER = 3044;

	public static final Integer DEFAULT_SRID = 3044;

	public static final String MULTIPOLYGON_COLUMN_DEFINITION = "geometry(MultiPolygon,3044)";


	public static final String SRID_4326 = "4326";

	public static final Integer SRID_4326_NUMBER = 4326;

	public static final String MULTIPOLYGON_COLUMN_DEFINITION_4326 = "geometry(MultiPolygon,4326)";

}
