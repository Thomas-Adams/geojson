package org.adams.geo.gisweb.integration.service;

import org.adams.geo.gisweb.business.service.SchlagService;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Schlag;
import org.adams.geo.gisweb.data.entities.QSchlagEntity;
import org.adams.geo.gisweb.geojson.GeometryMapper;
import org.adams.geo.gisweb.geojson.SchlagGeoJsonSerializer;
import org.adams.geo.gisweb.test.SchlagFactoryForTest;
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
public class SchlagServiceIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private SchlagService schlagService;

	@Autowired
	private GeometryMapper geometryMapper;

	private Schlag schlag;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_001_findAllByPredicatePage() {

		Sort sort = new Sort("gid");
		PageRequest page = new PageRequest(0, 1, sort);
		Predicate predicate = QSchlagEntity.schlagEntity.gid.isNotNull();

		Page<Schlag> schlags = schlagService.findAll(predicate, page);
		for (Schlag s : schlags.getContent()) {
			try {
				System.out.println(geometryMapper.writeValueAsString(schlags));
				System.out.println(SchlagGeoJsonSerializer.toGeoJson(s));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(schlags.hasContent());
	}

	@Test
	public void test_003_createAndSave() throws Exception {
		SchlagFactoryForTest factory = new SchlagFactoryForTest();
		schlag = factory.createNewSchlag();
		schlag = schlagService.create(schlag);
		Assert.assertNotNull(schlag.getGid());
	}

	@Test
	public void test_004_findAndUpdate() throws Exception {
		SchlagFactoryForTest factory = new SchlagFactoryForTest();
		schlag = factory.createNewSchlag();
		schlag = schlagService.create(schlag);
		schlag = schlagService.findById(schlag.getGid());
		String kulturcode = "CODE";
		schlag.setKulturcode(kulturcode);
		schlag = schlagService.update(schlag);
		Assert.assertNotNull(schlag.getGid());
		Assert.assertEquals(kulturcode, schlag.getKulturcode());
	}

	@Test
	public void test_005_delete() throws Exception {
		schlag = null;
		Assert.assertNull(schlag);
		SchlagFactoryForTest factory = new SchlagFactoryForTest();
		schlag = factory.createNewSchlag();
		schlag = schlagService.create(schlag);
		Assert.assertNotNull(schlag);
		schlagService.delete(schlag.getGid());
		schlag = schlagService.findById(schlag.getGid());
		Assert.assertNull(schlag);
	}

}
