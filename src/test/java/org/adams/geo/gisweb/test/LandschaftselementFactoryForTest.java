package org.adams.geo.gisweb.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.adams.geo.gisweb.data.dto.Landschaftselement;

import com.vividsolutions.jts.geom.Geometry;

public class LandschaftselementFactoryForTest {

	private MockValues mockValues = new MockValues();

	public Landschaftselement newLandschaftselement() {

		Integer gid = mockValues.nextInteger();

		Landschaftselement landschaftselement = new Landschaftselement();
		landschaftselement.setGid(gid);
		return landschaftselement;
	}

	public Landschaftselement createNewLandschaftselement() {
		Geometry g = mockValues.nextGeometry();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String stand = dateFormat.format(new Date());
		Landschaftselement le = new Landschaftselement();
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
