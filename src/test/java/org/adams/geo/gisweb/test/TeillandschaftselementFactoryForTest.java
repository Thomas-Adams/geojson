package org.adams.geo.gisweb.test;

import java.math.BigDecimal;

import org.adams.geo.gisweb.data.dto.Teillandschaftselement;

import com.vividsolutions.jts.geom.Geometry;

public class TeillandschaftselementFactoryForTest {

	private MockValues mockValues = new MockValues();

	public Teillandschaftselement newTeillandschaftselement() {

		Integer gid = mockValues.nextInteger();

		Teillandschaftselement teillandschaftselement = new Teillandschaftselement();
		teillandschaftselement.setGid(gid);
		return teillandschaftselement;
	}

	public Teillandschaftselement createNewTeillandschaftselement() {
		Geometry g = mockValues.nextGeometry();
		Teillandschaftselement tle = new Teillandschaftselement();
		tle.setNrle(BigDecimal.valueOf(mockValues.nextShort()));
		tle.setFlik(mockValues.nextString(16));
		tle.setFlek(mockValues.nextString(16));
		tle.setSchlagnr(BigDecimal.valueOf(mockValues.nextShort()));
		tle.setGeom(g);
		return tle;
	}

}
