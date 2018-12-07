package org.adams.geo.gisweb.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adams.geo.gisweb.data.entities.FeldblockEntity;

import com.vividsolutions.jts.geom.Geometry;

public class FeldblockEntityFactoryForTest {

	private MockValues mockValues = new MockValues();

	public FeldblockEntity newFeldblockEntity() {

		Integer gid = mockValues.nextInteger();

		FeldblockEntity feldblockEntity = new FeldblockEntity();
		feldblockEntity.setGid(gid);
		return feldblockEntity;
	}

	public FeldblockEntity createNewFeldblockEntity() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String stand = dateFormat.format(new Date());
		FeldblockEntity feldblock = new FeldblockEntity();
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
