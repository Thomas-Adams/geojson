package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.adams.geo.gisweb.data.dto.Schlag;

import com.vividsolutions.jts.geom.Geometry;

public class SchlagFactoryForTest {

	private MockValues mockValues = new MockValues();

	public Schlag newSchlag() {

		Integer gid = mockValues.nextInteger();

		Schlag schlag = new Schlag();
		schlag.setGid(gid);
		return schlag;
	}

	public Schlag createNewSchlag() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.format(new Date());
		Schlag s = new Schlag();
		s.setAntjahr(Long.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		s.setFlik(mockValues.nextString(16));
		s.setSchlagnr(BigDecimal.valueOf(mockValues.nextShort()));
		s.setKulturcode(mockValues.nextString(50));
		s.setGemFl(g.getArea());
		s.setGeom(g);
		return s;
	}

}
