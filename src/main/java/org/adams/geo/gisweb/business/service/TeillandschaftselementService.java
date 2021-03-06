/*
 * Created on 23 M�r 2018 ( Time 23:37:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service;

import java.util.List;

import org.adams.geo.gisweb.data.dto.Teillandschaftselement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

/**
 * Business Service Interface for entity Teillandschaftselement.
 */
public interface TeillandschaftselementService {

	/**
	 * Loads an entity from the database using its Primary Key
	 *
	 * @param gid
	 * @return entity
	 */
	Teillandschaftselement findById(Integer gid);

	/**
	 * Loads all entities.
	 *
	 * @return all entities
	 */
	List<Teillandschaftselement> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 *
	 * @param entity
	 * @return entity
	 */
	Teillandschaftselement save(Teillandschaftselement entity);

	/**
	 * Updates the given entity in the database
	 *
	 * @param entity
	 * @return
	 */
	Teillandschaftselement update(Teillandschaftselement entity);

	/**
	 * Creates the given entity in the database
	 *
	 * @param entity
	 * @return
	 */
	Teillandschaftselement create(Teillandschaftselement entity);

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
	Page<Teillandschaftselement> findAll(final Predicate predicate, final Pageable pageable);

}
