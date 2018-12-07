package org.adams.geo.gisweb.integration.service;

import java.math.BigDecimal;

import org.adams.geo.gisweb.business.service.TeillandschaftselementService;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Teillandschaftselement;
import org.adams.geo.gisweb.data.entities.QTeillandschaftselementEntity;
import org.adams.geo.gisweb.geojson.GeometryMapper;
import org.adams.geo.gisweb.geojson.TeillandschaftselementGeoJsonSerializer;
import org.adams.geo.gisweb.test.TeillandschaftselementFactoryForTest;
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
public class TeillandschaftselementServiceIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TeillandschaftselementService teillandschaftselementService;

	@Autowired
	private GeometryMapper geometryMapper;

	private Teillandschaftselement teillandschaftselement;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_001_findAllByPredicatePage() {

		Sort sort = new Sort("gid");
		PageRequest page = new PageRequest(0, 1, sort);
		Predicate predicate = QTeillandschaftselementEntity.teillandschaftselementEntity.gid.isNotNull();

		Page<Teillandschaftselement> teillandschaftselements = teillandschaftselementService.findAll(predicate, page);
		for (Teillandschaftselement tle : teillandschaftselements.getContent()) {
			try {
				System.out.println(geometryMapper.writeValueAsString(teillandschaftselements));
				System.out.println(TeillandschaftselementGeoJsonSerializer.toGeoJson(tle));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(teillandschaftselements.hasContent());
	}

	@Test
	public void test_003_createAndSave() throws Exception {
		TeillandschaftselementFactoryForTest factory = new TeillandschaftselementFactoryForTest();
		teillandschaftselement = factory.createNewTeillandschaftselement();
		teillandschaftselement = teillandschaftselementService.create(teillandschaftselement);
		Assert.assertNotNull(teillandschaftselement.getGid());
	}

	@Test
	public void test_004_findAndUpdate() throws Exception {
		TeillandschaftselementFactoryForTest factory = new TeillandschaftselementFactoryForTest();
		teillandschaftselement = factory.createNewTeillandschaftselement();
		teillandschaftselement = teillandschaftselementService.create(teillandschaftselement);
		teillandschaftselement = teillandschaftselementService.findById(teillandschaftselement.getGid());
		teillandschaftselement.setNrle(BigDecimal.TEN);
		teillandschaftselement = teillandschaftselementService.update(teillandschaftselement);
		Assert.assertNotNull(teillandschaftselement.getGid());
		Assert.assertEquals(BigDecimal.TEN, teillandschaftselement.getNrle());
	}

	@Test
	public void test_005_delete() throws Exception {
		teillandschaftselement = null;
		Assert.assertNull(teillandschaftselement);
		TeillandschaftselementFactoryForTest factory = new TeillandschaftselementFactoryForTest();
		teillandschaftselement = factory.createNewTeillandschaftselement();
		teillandschaftselement = teillandschaftselementService.create(teillandschaftselement);
		Assert.assertNotNull(teillandschaftselement);
		teillandschaftselementService.delete(teillandschaftselement.getGid());
		teillandschaftselement = teillandschaftselementService.findById(teillandschaftselement.getGid());
		Assert.assertNull(teillandschaftselement);
	}

}
