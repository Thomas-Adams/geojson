/*
 * Created on 23 M�r 2018 ( Time 23:37:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.mapping;

import org.adams.geo.gisweb.data.dto.Schlag;
import org.adams.geo.gisweb.data.entities.SchlagEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class SchlagServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;

	/**
	 * Constructor.
	 */
	public SchlagServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'SchlagEntity' to 'Schlag'
	 * 
	 * @param schlagEntity
	 */
	public Schlag mapSchlagEntityToSchlag(final SchlagEntity schlagEntity) {
		if (schlagEntity == null) {
			return null;
		}

		// --- Generic mapping
		Schlag schlag = map(schlagEntity, Schlag.class);

		return schlag;
	}

	/**
	 * Mapping from 'Schlag' to 'SchlagEntity'
	 * 
	 * @param schlag
	 * @param schlagEntity
	 */
	public void mapSchlagToSchlagEntity(final Schlag schlag, final SchlagEntity schlagEntity) {
		if (schlag == null) {
			return;
		}

		// --- Generic mapping
		map(schlag, schlagEntity);

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