package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.adams.geo.gisweb.data.entities.SchlagEntity;

import com.vividsolutions.jts.geom.Geometry;

public class SchlagEntityFactoryForTest {

	private MockValues mockValues = new MockValues();

	public SchlagEntity newSchlagEntity() {

		Integer gid = mockValues.nextInteger();

		SchlagEntity schlagEntity = new SchlagEntity();
		schlagEntity.setGid(gid);
		return schlagEntity;
	}

	public SchlagEntity createNewSchlagEntity() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.format(new Date());
		SchlagEntity s = new SchlagEntity();
		s.setAntjahr(Long.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		s.setFlik(mockValues.nextString(16));
		s.setSchlagnr(BigDecimal.valueOf(mockValues.nextShort()));
		s.setKulturcode(mockValues.nextString(50));
		s.setGemFl(g.getArea());
		s.setGeom(g);
		return s;
	}
}
