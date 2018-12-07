package org.adams.geo.gisweb.data.repository;

import org.adams.geo.gisweb.data.entities.FeldblockEntity;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Feldblock.
 */
public interface FeldblockRepository
		extends PagingAndSortingRepository<FeldblockEntity, Integer>, QueryDslPredicateExecutor<FeldblockEntity> {

}
