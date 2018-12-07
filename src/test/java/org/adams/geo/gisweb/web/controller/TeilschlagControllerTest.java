package org.adams.geo.gisweb.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Services
import org.adams.geo.gisweb.business.service.TeilschlagService;
//--- Entities
import org.adams.geo.gisweb.data.dto.Teilschlag;
import org.adams.geo.gisweb.test.TeilschlagFactoryForTest;
import org.adams.geo.gisweb.web.common.Message;
import org.adams.geo.gisweb.web.common.MessageHelper;
import org.adams.geo.gisweb.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class TeilschlagControllerTest {

	@InjectMocks
	private TeilschlagController teilschlagController;
	@Mock
	private TeilschlagService teilschlagService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TeilschlagFactoryForTest teilschlagFactoryForTest = new TeilschlagFactoryForTest();

	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();

		List<Teilschlag> list = new ArrayList<>();
		when(teilschlagService.findAll()).thenReturn(list);

		// When
		String viewName = teilschlagController.list(model);

		// Then
		assertEquals("teilschlag/list", viewName);
		Map<String, ?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}

	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		// When
		String viewName = teilschlagController.formForCreate(model);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertNull(((Teilschlag) modelMap.get("teilschlag")).getGid());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teilschlag/create", modelMap.get("saveAction"));

	}

	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		Integer gid = teilschlag.getGid();
		when(teilschlagService.findById(gid)).thenReturn(teilschlag);

		// When
		String viewName = teilschlagController.formForUpdate(model, gid);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlag, modelMap.get("teilschlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teilschlag/update", modelMap.get("saveAction"));

	}

	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Teilschlag teilschlagCreated = new Teilschlag();
		when(teilschlagService.create(teilschlag)).thenReturn(teilschlagCreated);

		// When
		String viewName = teilschlagController.create(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("redirect:/teilschlag/form/" + teilschlag.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlagCreated, modelMap.get("teilschlag"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = teilschlagController.create(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlag, modelMap.get("teilschlag"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teilschlag/create", modelMap.get("saveAction"));

	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();

		Exception exception = new RuntimeException("test exception");
		when(teilschlagService.create(teilschlag)).thenThrow(exception);

		// When
		String viewName = teilschlagController.create(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlag, modelMap.get("teilschlag"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teilschlag/create", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "teilschlag.error.create", exception);

	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		Integer gid = teilschlag.getGid();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Teilschlag teilschlagSaved = new Teilschlag();
		teilschlagSaved.setGid(gid);
		when(teilschlagService.update(teilschlag)).thenReturn(teilschlagSaved);

		// When
		String viewName = teilschlagController.update(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("redirect:/teilschlag/form/" + teilschlag.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlagSaved, modelMap.get("teilschlag"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = teilschlagController.update(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlag, modelMap.get("teilschlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teilschlag/update", modelMap.get("saveAction"));

	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();

		Exception exception = new RuntimeException("test exception");
		when(teilschlagService.update(teilschlag)).thenThrow(exception);

		// When
		String viewName = teilschlagController.update(teilschlag, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("teilschlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teilschlag, modelMap.get("teilschlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teilschlag/update", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "teilschlag.error.update", exception);

	}

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		Integer gid = teilschlag.getGid();

		// When
		String viewName = teilschlagController.delete(redirectAttributes, gid);

		// Then
		verify(teilschlagService).delete(gid);
		assertEquals("redirect:/teilschlag", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Teilschlag teilschlag = teilschlagFactoryForTest.newTeilschlag();
		Integer gid = teilschlag.getGid();

		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(teilschlagService).delete(gid);

		// When
		String viewName = teilschlagController.delete(redirectAttributes, gid);

		// Then
		verify(teilschlagService).delete(gid);
		assertEquals("redirect:/teilschlag", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "teilschlag.error.delete", exception);
	}

}
