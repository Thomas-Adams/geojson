package org.adams.geo.gisweb.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adams.geo.gisweb.data.dto.Feldblock;

import com.vividsolutions.jts.geom.Geometry;

public class FeldblockFactoryForTest {

	private MockValues mockValues = new MockValues();

	public Feldblock newFeldblock() {

		Integer gid = mockValues.nextInteger();

		Feldblock feldblock = new Feldblock();
		feldblock.setGid(gid);
		return feldblock;
	}

	public Feldblock createNewFeldblock() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String stand = dateFormat.format(new Date());
		Feldblock feldblock = new Feldblock();
		feldblock.setAntJahr(mockValues.nextShort());
		feldblock.setBnk(mockValues.nextString(6));
		feldblock.setBnkTxt(mockValues.nextString(50));
		feldblock.setGeom(g);
		feldblock.setFlaeche(g.getArea());
		feldblock.setFlik(mockValues.nextString(16));
		feldblock.setStand(stand);
		return feldblock;
	}

}
