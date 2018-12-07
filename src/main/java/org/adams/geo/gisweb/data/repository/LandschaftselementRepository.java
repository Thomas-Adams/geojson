package org.adams.geo.gisweb.data.repository;

import org.adams.geo.gisweb.data.entities.LandschaftselementEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Landschaftselement.
 */
public interface LandschaftselementRepository extends PagingAndSortingRepository<LandschaftselementEntity, Integer>,
		QueryDslPredicateExecutor<LandschaftselementEntity> {

}
