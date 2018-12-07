package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.util.Calendar;

import org.adams.geo.gisweb.data.dto.Teilschlag;

import com.vividsolutions.jts.geom.Geometry;

public class TeilschlagFactoryForTest {

	private MockValues mockValues = new MockValues();

	public Teilschlag newTeilschlag() {

		Integer gid = mockValues.nextInteger();

		Teilschlag teilschlag = new Teilschlag();
		teilschlag.setGid(gid);
		return teilschlag;
	}

	public Teilschlag createNewTeilschlag() {
		Geometry g = mockValues.nextGeometry();
		Teilschlag ts = new Teilschlag();
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
