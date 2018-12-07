package org.adams.geo.gisweb.integration.service;

import org.adams.geo.gisweb.business.service.FeldblockService;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Feldblock;
import org.adams.geo.gisweb.data.entities.QFeldblockEntity;
import org.adams.geo.gisweb.geojson.FeldblockGeoJsonSerializer;
import org.adams.geo.gisweb.geojson.GeometryMapper;
import org.adams.geo.gisweb.test.FeldblockFactoryForTest;
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
public class FeldblockServiceIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private FeldblockService feldblockService;

	@Autowired
	private GeometryMapper geometryMapper;

	private Feldblock feldblock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_001_findAllByPredicatePage() {

		Sort sort = new Sort("gid");
		PageRequest page = new PageRequest(0, 1, sort);
		Predicate predicate = QFeldblockEntity.feldblockEntity.gid.isNotNull();

		Page<Feldblock> feldBlocks = feldblockService.findAll(predicate, page);
		for (Feldblock fb : feldBlocks.getContent()) {
			try {
				System.out.println(geometryMapper.writeValueAsString(feldBlocks));
				System.out.println(FeldblockGeoJsonSerializer.toGeoJson(fb));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(feldBlocks.hasContent());
	}

	@Test
	public void test_003_createAndSave() throws Exception {
		FeldblockFactoryForTest factory = new FeldblockFactoryForTest();
		feldblock = factory.createNewFeldblock();
		feldblock = feldblockService.create(feldblock);
		Assert.assertNotNull(feldblock.getGid());
	}

	@Test
	public void test_004_findAndUpdate() throws Exception {
		FeldblockFactoryForTest factory = new FeldblockFactoryForTest();
		feldblock = factory.createNewFeldblock();
		feldblock = feldblockService.create(feldblock);
		feldblock = feldblockService.findById(feldblock.getGid());
		String stand = "31.12.1906";
		feldblock.setStand(stand);
		feldblock = feldblockService.update(feldblock);
		Assert.assertNotNull(feldblock.getGid());
		Assert.assertEquals(stand, feldblock.getStand());
	}

	@Test
	public void test_005_delete() throws Exception {
		FeldblockFactoryForTest factory = new FeldblockFactoryForTest();
		feldblock = factory.createNewFeldblock();
		feldblock = feldblockService.create(feldblock);
		feldblockService.delete(feldblock.getGid());
		feldblock = feldblockService.findById(feldblock.getGid());
		Assert.assertNull(feldblock);
	}

}
