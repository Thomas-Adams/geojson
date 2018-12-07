/*
 * Created on 24 M�r 2018 ( Time 16:32:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.adams.geo.gisweb.business.service.SchlagService;
import org.adams.geo.gisweb.data.dto.Schlag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Spring MVC controller for 'Schlag' management.
 */
@RestController
public class SchlagRestController {

	@Resource
	private SchlagService schlagService;

	@RequestMapping(value = "/schlag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Schlag> findAll() {
		return schlagService.findAll();
	}

	@RequestMapping(value = "/schlag/{gid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Schlag findOne(@PathVariable("gid") final Integer gid) {
		return schlagService.findById(gid);
	}

	@RequestMapping(value = "/schlag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Schlag create(@RequestBody final Schlag schlag) {
		return schlagService.create(schlag);
	}

	@RequestMapping(value = "/schlag/{gid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Schlag update(@PathVariable("gid") final Integer gid, @RequestBody final Schlag schlag) {
		return schlagService.update(schlag);
	}

	@RequestMapping(value = "/schlag/{gid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("gid") final Integer gid) {
		schlagService.delete(gid);
	}

}
