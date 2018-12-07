package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.adams.geo.gisweb.data.entities.LandschaftselementEntity;

import com.vividsolutions.jts.geom.Geometry;

public class LandschaftselementEntityFactoryForTest {

	private MockValues mockValues = new MockValues();

	public LandschaftselementEntity newLandschaftselementEntity() {

		Integer gid = mockValues.nextInteger();

		LandschaftselementEntity landschaftselementEntity = new LandschaftselementEntity();
		landschaftselementEntity.setGid(gid);
		return landschaftselementEntity;
	}

	public LandschaftselementEntity createNewLandschaftselementEntityt() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String stand = dateFormat.format(new Date());
		LandschaftselementEntity le = new LandschaftselementEntity();
		le.setCcRelevan(mockValues.nextShort());
		le.setFlaeche(Double.valueOf(g.getArea()));
		le.setFlek(mockValues.nextString(50));
		le.setGeom(g);
		le.setTyp(mockValues.nextShort());
		le.setTypTxt(mockValues.nextString(254));
		le.setStand(stand);
		return le;
	}

}
