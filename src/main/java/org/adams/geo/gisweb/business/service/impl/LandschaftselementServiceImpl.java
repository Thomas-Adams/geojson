/*
 * Created on 23 M�r 2018 ( Time 23:37:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.adams.geo.gisweb.business.service.LandschaftselementService;
import org.adams.geo.gisweb.business.service.mapping.LandschaftselementServiceMapper;
import org.adams.geo.gisweb.data.dto.Landschaftselement;
import org.adams.geo.gisweb.data.entities.LandschaftselementEntity;
import org.adams.geo.gisweb.data.repository.LandschaftselementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

/**
 * Implementation of LandschaftselementService
 */
@Component
@Transactional
public class LandschaftselementServiceImpl implements LandschaftselementService {

	@Resource
	private LandschaftselementRepository landschaftselementRepository;

	@Resource
	private LandschaftselementServiceMapper landschaftselementServiceMapper;

	@Override
	public Landschaftselement findById(final Integer gid) {
		LandschaftselementEntity landschaftselementEntity = landschaftselementRepository.findOne(gid);
		return landschaftselementServiceMapper
				.mapLandschaftselementEntityToLandschaftselement(landschaftselementEntity);
	}

	@Override
	public List<Landschaftselement> findAll() {
		Iterable<LandschaftselementEntity> entities = landschaftselementRepository.findAll();
		List<Landschaftselement> beans = new ArrayList<>();
		for (LandschaftselementEntity landschaftselementEntity : entities) {
			beans.add(landschaftselementServiceMapper
					.mapLandschaftselementEntityToLandschaftselement(landschaftselementEntity));
		}
		return beans;
	}

	@Override
	public Landschaftselement save(final Landschaftselement landschaftselement) {
		return update(landschaftselement);
	}

	@Override
	public Landschaftselement create(final Landschaftselement landschaftselement) {
		if (landschaftselement.getGid() != null) {
			LandschaftselementEntity landschaftselementEntity = landschaftselementRepository
					.findOne(landschaftselement.getGid());
			if (landschaftselementEntity != null) {
				throw new IllegalStateException("already.exists");
			}
		}
		LandschaftselementEntity landschaftselementEntity = new LandschaftselementEntity();
		landschaftselementServiceMapper.mapLandschaftselementToLandschaftselementEntity(landschaftselement,
				landschaftselementEntity);
		LandschaftselementEntity landschaftselementEntitySaved = landschaftselementRepository
				.save(landschaftselementEntity);
		return landschaftselementServiceMapper
				.mapLandschaftselementEntityToLandschaftselement(landschaftselementEntitySaved);
	}

	@Override
	public Landschaftselement update(final Landschaftselement landschaftselement) {
		LandschaftselementEntity landschaftselementEntity = landschaftselementRepository
				.findOne(landschaftselement.getGid());
		landschaftselementServiceMapper.mapLandschaftselementToLandschaftselementEntity(landschaftselement,
				landschaftselementEntity);
		LandschaftselementEntity landschaftselementEntitySaved = landschaftselementRepository
				.save(landschaftselementEntity);
		return landschaftselementServiceMapper
				.mapLandschaftselementEntityToLandschaftselement(landschaftselementEntitySaved);
	}

	@Override
	public Page<Landschaftselement> findAll(final Predicate predicate, final Pageable pageable) {
		Page<LandschaftselementEntity> entities = landschaftselementRepository.findAll(predicate, pageable);
		List<Landschaftselement> beans = new ArrayList<>();
		for (LandschaftselementEntity landschaftselementEntity : entities) {
			beans.add(landschaftselementServiceMapper
					.mapLandschaftselementEntityToLandschaftselement(landschaftselementEntity));
		}
		return new PageImpl<>(beans);
	}

	@Override
	public void delete(final Integer gid) {
		landschaftselementRepository.delete(gid);
	}

	public LandschaftselementRepository getLandschaftselementJpaRepository() {
		return landschaftselementRepository;
	}

	public void setLandschaftselementJpaRepository(final LandschaftselementRepository landschaftselementRepository) {
		this.landschaftselementRepository = landschaftselementRepository;
	}

	public LandschaftselementServiceMapper getLandschaftselementServiceMapper() {
		return landschaftselementServiceMapper;
	}

	public void setLandschaftselementServiceMapper(
			final LandschaftselementServiceMapper landschaftselementServiceMapper) {
		this.landschaftselementServiceMapper = landschaftselementServiceMapper;
	}

}
