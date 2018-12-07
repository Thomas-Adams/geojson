/*
 * Created on 24 M�r 2018 ( Time 16:32:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.adams.geo.gisweb.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.adams.geo.gisweb.business.service.TeilschlagService;
import org.adams.geo.gisweb.data.dto.Teilschlag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Spring MVC controller for 'Teilschlag' management.
 */
@RestController
public class TeilschlagRestController {

	@Resource
	private TeilschlagService teilschlagService;

	@RequestMapping(value = "/teilschlag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Teilschlag> findAll() {
		return teilschlagService.findAll();
	}

	@RequestMapping(value = "/teilschlag/{gid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teilschlag findOne(@PathVariable("gid") final Integer gid) {
		return teilschlagService.findById(gid);
	}

	@RequestMapping(value = "/teilschlag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teilschlag create(@RequestBody final Teilschlag teilschlag) {
		return teilschlagService.create(teilschlag);
	}

	@RequestMapping(value = "/teilschlag/{gid}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Teilschlag update(@PathVariable("gid") final Integer gid, @RequestBody final Teilschlag teilschlag) {
		return teilschlagService.update(teilschlag);
	}

	@RequestMapping(value = "/teilschlag/{gid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("gid") final Integer gid) {
		teilschlagService.delete(gid);
	}

}
