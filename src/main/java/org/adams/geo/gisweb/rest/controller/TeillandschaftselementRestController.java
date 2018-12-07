/*
 * Created on 24 M�r 2018 ( Time 16:32:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.adams.geo.gisweb.business.service.TeillandschaftselementService;
import org.adams.geo.gisweb.data.dto.Teillandschaftselement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Spring MVC controller for 'Teillandschaftselement' management.
 */
@RestController
public class TeillandschaftselementRestController {

	@Resource
	private TeillandschaftselementService teillandschaftselementService;

	@RequestMapping(value = "/teillandschaftselement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Teillandschaftselement> findAll() {
		return teillandschaftselementService.findAll();
	}

	@RequestMapping(value = "/teillandschaftselement/{gid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teillandschaftselement findOne(@PathVariable("gid") final Integer gid) {
		return teillandschaftselementService.findById(gid);
	}

	@RequestMapping(value = "/teillandschaftselement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teillandschaftselement create(@RequestBody final Teillandschaftselement teillandschaftselement) {
		return teillandschaftselementService.create(teillandschaftselement);
	}

	@RequestMapping(value = "/teillandschaftselement/{gid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teillandschaftselement update(@PathVariable("gid") final Integer gid,
			@RequestBody final Teillandschaftselement teillandschaftselement) {
		return teillandschaftselementService.update(teillandschaftselement);
	}

	@RequestMapping(value = "/teillandschaftselement/{gid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("gid") final Integer gid) {
		teillandschaftselementService.delete(gid);
	}

}