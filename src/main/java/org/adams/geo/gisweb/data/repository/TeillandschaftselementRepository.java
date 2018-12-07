package org.adams.geo.gisweb.data.repository;

import org.adams.geo.gisweb.data.entities.TeillandschaftselementEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Teillandschaftselement.
 */
public interface TeillandschaftselementRepository
		extends PagingAndSortingRepository<TeillandschaftselementEntity, Integer>,
		QueryDslPredicateExecutor<TeillandschaftselementEntity> {

}
