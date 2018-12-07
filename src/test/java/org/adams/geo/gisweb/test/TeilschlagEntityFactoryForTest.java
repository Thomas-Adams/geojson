package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.util.Calendar;

import org.adams.geo.gisweb.data.entities.TeilschlagEntity;

import com.vividsolutions.jts.geom.Geometry;

public class TeilschlagEntityFactoryForTest {

	private MockValues mockValues = new MockValues();

	public TeilschlagEntity newTeilschlagEntity() {

		Integer gid = mockValues.nextInteger();

		TeilschlagEntity teilschlagEntity = new TeilschlagEntity();
		teilschlagEntity.setGid(gid);
		return teilschlagEntity;
	}

	public TeilschlagEntity createNewTeilschlagEntity() {
		Geometry g = mockValues.nextGeometry();
		TeilschlagEntity ts = new TeilschlagEntity();
		ts.setAntjahr(Long.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		ts.setFlik(mockValues.nextString(16));
		ts.setSchlagnr(BigDecimal.valueOf(mockValues.nextShort()));
		ts.setKulturcode(mockValues.nextString(50));
		ts.setGemFl(g.getArea());
		ts.setTsbez(mockValues.nextString(2));
		ts.setGeom(g);
		return ts;
	}

}
