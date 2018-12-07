package org.adams.geo.gisweb.data.repository;

import org.adams.geo.gisweb.data.entities.SchlagEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Schlag.
 */
public interface SchlagRepository
		extends PagingAndSortingRepository<SchlagEntity, Integer>, QueryDslPredicateExecutor<SchlagEntity> {

}
