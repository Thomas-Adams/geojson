package org.adams.geo.gisweb.integration.service;

import org.adams.geo.gisweb.business.service.TeilschlagService;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Teilschlag;
import org.adams.geo.gisweb.data.entities.QTeilschlagEntity;
import org.adams.geo.gisweb.geojson.GeometryMapper;
import org.adams.geo.gisweb.geojson.TeilschlagGeoJsonSerializer;
import org.adams.geo.gisweb.test.TeilschlagFactoryForTest;
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
public class TeilschlagServiceIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TeilschlagService teilschlagService;

	@Autowired
	private GeometryMapper geometryMapper;

	private Teilschlag teilschlag;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_001_findAllByPredicatePage() {

		Sort sort = new Sort("gid");
		PageRequest page = new PageRequest(0, 1, sort);
		Predicate predicate = QTeilschlagEntity.teilschlagEntity.gid.isNotNull();

		Page<Teilschlag> teilschlags = teilschlagService.findAll(predicate, page);
		for (Teilschlag le : teilschlags.getContent()) {
			try {
				System.out.println(geometryMapper.writeValueAsString(teilschlags));
				System.out.println(TeilschlagGeoJsonSerializer.toGeoJson(le));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(teilschlags.hasContent());
	}

	@Test
	public void test_003_createAndSave() throws Exception {
		TeilschlagFactoryForTest factory = new TeilschlagFactoryForTest();
		teilschlag = factory.createNewTeilschlag();
		teilschlag = teilschlagService.create(teilschlag);
		Assert.assertNotNull(teilschlag.getGid());
	}

	@Test
	public void test_004_findAndUpdate() throws Exception {
		TeilschlagFactoryForTest factory = new TeilschlagFactoryForTest();
		teilschlag = factory.createNewTeilschlag();
		teilschlag = teilschlagService.create(teilschlag);
		teilschlag = teilschlagService.findById(teilschlag.getGid());
		String kulturcode = "CODE";
		teilschlag.setKulturcode(kulturcode);
		teilschlag = teilschlagService.update(teilschlag);
		Assert.assertNotNull(teilschlag.getGid());
		Assert.assertEquals(kulturcode, teilschlag.getKulturcode());
	}

	@Test
	public void test_005_delete() throws Exception {
		teilschlag = null;
		Assert.assertNull(teilschlag);
		TeilschlagFactoryForTest factory = new TeilschlagFactoryForTest();
		teilschlag = factory.createNewTeilschlag();
		teilschlag = teilschlagService.create(teilschlag);
		Assert.assertNotNull(teilschlag);
		teilschlagService.delete(teilschlag.getGid());
		teilschlag = teilschlagService.findById(teilschlag.getGid());
		Assert.assertNull(teilschlag);
	}

}
