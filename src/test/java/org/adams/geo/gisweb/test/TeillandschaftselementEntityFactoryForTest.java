package org.adams.geo.gisweb.test;

import java.math.BigDecimal;

import org.adams.geo.gisweb.data.entities.TeillandschaftselementEntity;

import com.vividsolutions.jts.geom.Geometry;

public class TeillandschaftselementEntityFactoryForTest {

	private MockValues mockValues = new MockValues();

	public TeillandschaftselementEntity newTeillandschaftselementEntity() {

		Integer gid = mockValues.nextInteger();

		TeillandschaftselementEntity teillandschaftselementEntity = new TeillandschaftselementEntity();
		teillandschaftselementEntity.setGid(gid);
		return teillandschaftselementEntity;
	}

	public TeillandschaftselementEntity createNewTeillandschaftselementEntity() {
		Geometry g = mockValues.nextGeometry();
		TeillandschaftselementEntity tle = new TeillandschaftselementEntity();
		tle.setNrle(BigDecimal.valueOf(mockValues.nextShort()));
		tle.setFlik(mockValues.nextString(16));
		tle.setFlek(mockValues.nextString(16));
		tle.setSchlagnr(BigDecimal.valueOf(mockValues.nextShort()));
		tle.setGeom(g);
		return tle;
	}

}
