/*
 * Created on 23 M�r 2018 ( Time 23:37:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.adams.geo.gisweb.business.service.mapping.TeilschlagServiceMapper;
import org.adams.geo.gisweb.context.junit4.SpringWithJNDIRunner;
import org.adams.geo.gisweb.data.dto.Teilschlag;
import org.adams.geo.gisweb.data.entities.TeilschlagEntity;
import org.adams.geo.gisweb.data.repository.TeilschlagRepository;
import org.adams.geo.gisweb.test.MockValues;
import org.adams.geo.gisweb.test.TeilschlagEntityFactoryForTest;
import org.adams.geo.gisweb.test.TeilschlagFactoryForTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Test : Implementation of TeilschlagService
 */
@RunWith(SpringWithJNDIRunner.class)
public class TeilschlagServiceImplTest {

	@InjectMocks
	private TeilschlagServiceImpl teilschlagService;
	@Mock
	private TeilschlagRepository teilschlagJpaRepository;
	@Mock
	private TeilschlagServiceMapper teilschlagServiceMapper;

	private TeilschlagFactoryForTest teilschlagFactoryForTest = new TeilschlagFactoryForTest();

	private TeilschlagEntityFactoryForTest teilschlagEntityFactoryForTest = new TeilschlagEntityFactoryForTest();

	private MockValues mockValues = new MockValues();

	@Test
	public void findById() {
		// Given
		Integer gid = mockValues.nextInteger();

		TeilschlagEntity teilschlagEntity = teilschlagJpaRepository.findOne(gid);

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		when(teilschlagServiceMapper.mapTeilschlagEntityToTeilschlag(teilschlagEntity)).thenReturn(teilschlag);

		// When
		Teilschlag teilschlagFound = teilschlagService.findById(gid);

		// Then
		assertEquals(teilschlag.getGid(), teilschlagFound.getGid());
	}

	@Test
	public void findAll() {
		// Given
		List<TeilschlagEntity> teilschlagEntitys = new ArrayList<>();
		TeilschlagEntity teilschlagEntity1 = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		teilschlagEntitys.add(teilschlagEntity1);
		TeilschlagEntity teilschlagEntity2 = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		teilschlagEntitys.add(teilschlagEntity2);
		when(teilschlagJpaRepository.findAll()).thenReturn(teilschlagEntitys);

		Teilschlag teilschlag1 = teilschlagFactoryForTest.newTeilschlag();
		when(teilschlagServiceMapper.mapTeilschlagEntityToTeilschlag(teilschlagEntity1)).thenReturn(teilschlag1);
		Teilschlag teilschlag2 = teilschlagFactoryForTest.newTeilschlag();
		when(teilschlagServiceMapper.mapTeilschlagEntityToTeilschlag(teilschlagEntity2)).thenReturn(teilschlag2);

		// When
		List<Teilschlag> teilschlagsFounds = teilschlagService.findAll();

		// Then
		assertTrue(teilschlag1 == teilschlagsFounds.get(0));
		assertTrue(teilschlag2 == teilschlagsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();

		TeilschlagEntity teilschlagEntity = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		when(teilschlagJpaRepository.findOne(teilschlag.getGid())).thenReturn(null);

		teilschlagEntity = new TeilschlagEntity();
		teilschlagServiceMapper.mapTeilschlagToTeilschlagEntity(teilschlag, teilschlagEntity);
		TeilschlagEntity teilschlagEntitySaved = teilschlagJpaRepository.save(teilschlagEntity);

		Teilschlag teilschlagSaved = teilschlagFactoryForTest.newTeilschlag();
		when(teilschlagServiceMapper.mapTeilschlagEntityToTeilschlag(teilschlagEntitySaved))
				.thenReturn(teilschlagSaved);

		// When
		Teilschlag teilschlagResult = teilschlagService.create(teilschlag);

		// Then
		assertTrue(teilschlagResult == teilschlagSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();

		TeilschlagEntity teilschlagEntity = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		when(teilschlagJpaRepository.findOne(teilschlag.getGid())).thenReturn(teilschlagEntity);

		// When
		Exception exception = null;
		try {
			teilschlagService.create(teilschlag);
		} catch (Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();

		TeilschlagEntity teilschlagEntity = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		when(teilschlagJpaRepository.findOne(teilschlag.getGid())).thenReturn(teilschlagEntity);

		TeilschlagEntity teilschlagEntitySaved = teilschlagEntityFactoryForTest.newTeilschlagEntity();
		when(teilschlagJpaRepository.save(teilschlagEntity)).thenReturn(teilschlagEntitySaved);

		Teilschlag teilschlagSaved = teilschlagFactoryForTest.newTeilschlag();
		when(teilschlagServiceMapper.mapTeilschlagEntityToTeilschlag(teilschlagEntitySaved))
				.thenReturn(teilschlagSaved);

		// When
		Teilschlag teilschlagResult = teilschlagService.update(teilschlag);

		// Then
		verify(teilschlagServiceMapper).mapTeilschlagToTeilschlagEntity(teilschlag, teilschlagEntity);
		assertTrue(teilschlagResult == teilschlagSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer gid = mockValues.nextInteger();

		// When
		teilschlagService.delete(gid);

		// Then
		verify(teilschlagJpaRepository).delete(gid);

	}

}