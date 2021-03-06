/*
 * Created on 23 M�r 2018 ( Time 23:37:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service;

import java.util.List;

import org.adams.geo.gisweb.data.dto.Landschaftselement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

/**
 * Business Service Interface for entity Landschaftselement.
 */
public interface LandschaftselementService {

	/**
	 * Loads an entity from the database using its Primary Key
	 *
	 * @param gid
	 * @return entity
	 */
	Landschaftselement findById(Integer gid);

	/**
	 * Loads all entities.
	 *
	 * @return all entities
	 */
	List<Landschaftselement> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 *
	 * @param entity
	 * @return entity
	 */
	Landschaftselement save(Landschaftselement entity);

	/**
	 * Updates the given entity in the database
	 *
	 * @param entity
	 * @return
	 */
	Landschaftselement update(Landschaftselement entity);

	/**
	 * Creates the given entity in the database
	 *
	 * @param entity
	 * @return
	 */
	Landschaftselement create(Landschaftselement entity);

	/**
	 * Deletes an entity using its Primary Key
	 *
	 * @param gid
	 */
	void delete(Integer gid);

	/**
	 * Gets a page of entities fulfilling the predicate.
	 * 
	 * @param predicate
	 * @param pageable
	 * @return
	 */
	Page<Landschaftselement> findAll(final Predicate predicate, final Pageable pageable);

}
