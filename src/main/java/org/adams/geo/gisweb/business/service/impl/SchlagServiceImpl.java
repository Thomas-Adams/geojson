/*
 * Created on 23 M�r 2018 ( Time 23:37:27 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.adams.geo.gisweb.business.service.SchlagService;
import org.adams.geo.gisweb.business.service.mapping.SchlagServiceMapper;
import org.adams.geo.gisweb.data.dto.Schlag;
import org.adams.geo.gisweb.data.entities.SchlagEntity;
import org.adams.geo.gisweb.data.repository.SchlagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

/**
 * Implementation of SchlagService
 */
@Component
@Transactional
public class SchlagServiceImpl implements SchlagService {

	@Resource
	private SchlagRepository schlagRepository;

	@Resource
	private SchlagServiceMapper schlagServiceMapper;

	@Override
	public Schlag findById(final Integer gid) {
		SchlagEntity schlagEntity = schlagRepository.findOne(gid);
		return schlagServiceMapper.mapSchlagEntityToSchlag(schlagEntity);
	}

	@Override
	public List<Schlag> findAll() {
		Iterable<SchlagEntity> entities = schlagRepository.findAll();
		List<Schlag> beans = new ArrayList<>();
		for (SchlagEntity schlagEntity : entities) {
			beans.add(schlagServiceMapper.mapSchlagEntityToSchlag(schlagEntity));
		}
		return beans;
	}

	@Override
	public Schlag save(final Schlag schlag) {
		return update(schlag);
	}

	@Override
	public Schlag create(final Schlag schlag) {
		if (schlag.getGid() != null) {
			SchlagEntity schlagEntity = schlagRepository.findOne(schlag.getGid());
			if (schlagEntity != null) {
				throw new IllegalStateException("already.exists");
			}
		}
		SchlagEntity schlagEntity = new SchlagEntity();
		schlagServiceMapper.mapSchlagToSchlagEntity(schlag, schlagEntity);
		SchlagEntity schlagEntitySaved = schlagRepository.save(schlagEntity);
		return schlagServiceMapper.mapSchlagEntityToSchlag(schlagEntitySaved);
	}

	@Override
	public Schlag update(final Schlag schlag) {
		SchlagEntity schlagEntity = schlagRepository.findOne(schlag.getGid());
		schlagServiceMapper.mapSchlagToSchlagEntity(schlag, schlagEntity);
		SchlagEntity schlagEntitySaved = schlagRepository.save(schlagEntity);
		return schlagServiceMapper.mapSchlagEntityToSchlag(schlagEntitySaved);
	}

	@Override
	public Page<Schlag> findAll(final Predicate predicate, final Pageable pageable) {
		Page<SchlagEntity> entities = schlagRepository.findAll(predicate, pageable);
		List<Schlag> beans = new ArrayList<>();
		for (SchlagEntity schlagEntity : entities) {
			beans.add(schlagServiceMapper.mapSchlagEntityToSchlag(schlagEntity));
		}
		return new PageImpl<>(beans);
	}

	@Override
	public void delete(final Integer gid) {
		schlagRepository.delete(gid);
	}

	public SchlagRepository getSchlagJpaRepository() {
		return schlagRepository;
	}

	public void setSchlagJpaRepository(final SchlagRepository schlagRepository) {
		this.schlagRepository = schlagRepository;
	}

	public SchlagServiceMapper getSchlagServiceMapper() {
		return schlagServiceMapper;
	}

	public void setSchlagServiceMapper(final SchlagServiceMapper schlagServiceMapper) {
		this.schlagServiceMapper = schlagServiceMapper;
	}

}