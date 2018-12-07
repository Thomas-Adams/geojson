package org.adams.geo.gisweb.data.repository;

import org.adams.geo.gisweb.data.entities.TeilschlagEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Teilschlag.
 */
public interface TeilschlagRepository
		extends PagingAndSortingRepository<TeilschlagEntity, Integer>, QueryDslPredicateExecutor<TeilschlagEntity> {

}
