/*
 * Created on 23 M�r 2018 ( Time 23:37:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.mapping;

import org.adams.geo.gisweb.data.dto.Feldblock;
import org.adams.geo.gisweb.data.entities.FeldblockEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class FeldblockServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;

	/**
	 * Constructor.
	 */
	public FeldblockServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'FeldblockEntity' to 'Feldblock'
	 * 
	 * @param feldblockEntity
	 */
	public Feldblock mapFeldblockEntityToFeldblock(final FeldblockEntity feldblockEntity) {
		if (feldblockEntity == null) {
			return null;
		}

		// --- Generic mapping
		Feldblock feldblock = map(feldblockEntity, Feldblock.class);

		return feldblock;
	}

	/**
	 * Mapping from 'Feldblock' to 'FeldblockEntity'
	 * 
	 * @param feldblock
	 * @param feldblockEntity
	 */
	public void mapFeldblockToFeldblockEntity(final Feldblock feldblock, final FeldblockEntity feldblockEntity) {
		if (feldblock == null) {
			return;
		}

		// --- Generic mapping
		map(feldblock, feldblockEntity);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}