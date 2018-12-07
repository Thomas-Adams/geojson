/*
 * Created on 23 M�r 2018 ( Time 23:37:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.adams.geo.gisweb.data.dto.Feldblock;
import org.adams.geo.gisweb.data.entities.FeldblockEntity;
import org.adams.geo.gisweb.test.MockValues;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class FeldblockServiceMapperTest {

	private FeldblockServiceMapper feldblockServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();

	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Before
	public void before() {
		feldblockServiceMapper = new FeldblockServiceMapper();
		feldblockServiceMapper.setModelMapper(modelMapper);
	}

	/**
	 * Mapping from 'FeldblockEntity' to 'Feldblock'
	 *
	 * @param feldblockEntity
	 */
	@Test
	public void testMapFeldblockEntityToFeldblock() {
		// Given
		FeldblockEntity feldblockEntity = new FeldblockEntity();
		feldblockEntity.setFlik(mockValues.nextString(16));
		feldblockEntity.setStand(mockValues.nextString(10));
		feldblockEntity.setAntJahr(mockValues.nextShort());
		feldblockEntity.setBnk(mockValues.nextString(6));
		feldblockEntity.setBnkTxt(mockValues.nextString(50));
		feldblockEntity.setFlaeche(mockValues.nextDouble());
		feldblockEntity.setGeom(mockValues.nextGeometry());

		// When
		Feldblock feldblock = feldblockServiceMapper.mapFeldblockEntityToFeldblock(feldblockEntity);

		// Then
		assertEquals(feldblockEntity.getFlik(), feldblock.getFlik());
		assertEquals(feldblockEntity.getStand(), feldblock.getStand());
		assertEquals(feldblockEntity.getAntJahr(), feldblock.getAntJahr());
		assertEquals(feldblockEntity.getBnk(), feldblock.getBnk());
		assertEquals(feldblockEntity.getBnkTxt(), feldblock.getBnkTxt());
		assertEquals(feldblockEntity.getFlaeche(), feldblock.getFlaeche());
		assertEquals(feldblockEntity.getGeom(), feldblock.getGeom());
	}

	/**
	 * Test : Mapping from 'Feldblock' to 'FeldblockEntity'
	 */
	@Test
	public void testMapFeldblockToFeldblockEntity() {
		// Given
		Feldblock feldblock = new Feldblock();
		feldblock.setFlik(mockValues.nextString(16));
		feldblock.setStand(mockValues.nextString(10));
		feldblock.setAntJahr(mockValues.nextShort());
		feldblock.setBnk(mockValues.nextString(6));
		feldblock.setBnkTxt(mockValues.nextString(50));
		feldblock.setFlaeche(mockValues.nextDouble());
		feldblock.setGeom(mockValues.nextGeometry());

		FeldblockEntity feldblockEntity = new FeldblockEntity();

		// When
		feldblockServiceMapper.mapFeldblockToFeldblockEntity(feldblock, feldblockEntity);

		// Then
		assertEquals(feldblock.getFlik(), feldblockEntity.getFlik());
		assertEquals(feldblock.getStand(), feldblockEntity.getStand());
		assertEquals(feldblock.getAntJahr(), feldblockEntity.getAntJahr());
		assertEquals(feldblock.getBnk(), feldblockEntity.getBnk());
		assertEquals(feldblock.getBnkTxt(), feldblockEntity.getBnkTxt());
		assertEquals(feldblock.getFlaeche(), feldblockEntity.getFlaeche());
		assertEquals(feldblock.getGeom(), feldblockEntity.getGeom());
	}

}