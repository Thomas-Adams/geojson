package org.adams.geo.gisweb.integration.service;

import org.adams.geo.gisweb.business.service.LandschaftselementService;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Landschaftselement;
import org.adams.geo.gisweb.data.entities.QLandschaftselementEntity;
import org.adams.geo.gisweb.geojson.GeometryMapper;
import org.adams.geo.gisweb.geojson.LandschaftselementGeoJsonSerializer;
import org.adams.geo.gisweb.test.LandschaftselementFactoryForTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.querydsl.core.types.Predicate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringWithJNDIRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class LandschaftselementServiceIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private LandschaftselementService landschaftselementService;

	@Autowired
	private GeometryMapper geometryMapper;

	private Landschaftselement landschaftselement;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_001_findAllByPredicatePage() {

		Sort sort = new Sort("gid");
		PageRequest page = new PageRequest(0, 1, sort);
		Predicate predicate = QLandschaftselementEntity.landschaftselementEntity.gid.isNotNull();

		Page<Landschaftselement> landschaftselements = landschaftselementService.findAll(predicate, page);
		for (Landschaftselement le : landschaftselements.getContent()) {
			try {
				System.out.println(geometryMapper.writeValueAsString(landschaftselements));
				System.out.println(LandschaftselementGeoJsonSerializer.toGeoJson(le));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(landschaftselements.hasContent());
	}

	@Test
	public void test_003_createAndSave() throws Exception {
		LandschaftselementFactoryForTest factory = new LandschaftselementFactoryForTest();
		landschaftselement = factory.createNewLandschaftselement();
		landschaftselement = landschaftselementService.create(landschaftselement);
		Assert.assertNotNull(landschaftselement.getGid());
	}

	@Test
	public void test_004_findAndUpdate() throws Exception {
		LandschaftselementFactoryForTest factory = new LandschaftselementFactoryForTest();
		landschaftselement = factory.createNewLandschaftselement();
		landschaftselement = landschaftselementService.create(landschaftselement);
		landschaftselement = landschaftselementService.findById(landschaftselement.getGid());
		String stand = "31.12.1906";
		landschaftselement.setStand(stand);
		landschaftselement = landschaftselementService.update(landschaftselement);
		Assert.assertNotNull(landschaftselement.getGid());
		Assert.assertEquals(stand, landschaftselement.getStand());
	}

	@Test
	public void test_005_delete() throws Exception {
		landschaftselement = null;
		Assert.assertNull(landschaftselement);
		LandschaftselementFactoryForTest factory = new LandschaftselementFactoryForTest();
		landschaftselement = factory.createNewLandschaftselement();
		landschaftselement = landschaftselementService.create(landschaftselement);
		Assert.assertNotNull(landschaftselement);
		landschaftselementService.delete(landschaftselement.getGid());
		landschaftselement = landschaftselementService.findById(landschaftselement.getGid());
		Assert.assertNull(landschaftselement);
	}

}
