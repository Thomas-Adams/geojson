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
import org.adams.geo.gisweb.business.service.SchlagService;
import org.adams.geo.gisweb.data.dto.Schlag;
import org.adams.geo.gisweb.test.SchlagFactoryForTest;
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
public class SchlagControllerTest {

	@InjectMocks
	private SchlagController schlagController;
	@Mock
	private SchlagService schlagService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private SchlagFactoryForTest schlagFactoryForTest = new SchlagFactoryForTest();

	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();

		List<Schlag> list = new ArrayList<Schlag>();
		when(schlagService.findAll()).thenReturn(list);

		// When
		String viewName = schlagController.list(model);

		// Then
		assertEquals("schlag/list", viewName);
		Map<String, ?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}

	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		// When
		String viewName = schlagController.formForCreate(model);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertNull(((Schlag) modelMap.get("schlag")).getGid());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/schlag/create", modelMap.get("saveAction"));

	}

	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Schlag schlag = schlagFactoryForTest.newSchlag();
		Integer gid = schlag.getGid();
		when(schlagService.findById(gid)).thenReturn(schlag);

		// When
		String viewName = schlagController.formForUpdate(model, gid);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlag, (Schlag) modelMap.get("schlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/schlag/update", modelMap.get("saveAction"));

	}

	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();

		Schlag schlag = schlagFactoryForTest.newSchlag();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Schlag schlagCreated = new Schlag();
		when(schlagService.create(schlag)).thenReturn(schlagCreated);

		// When
		String viewName = schlagController.create(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/schlag/form/" + schlag.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlagCreated, (Schlag) modelMap.get("schlag"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Schlag schlag = schlagFactoryForTest.newSchlag();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = schlagController.create(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlag, (Schlag) modelMap.get("schlag"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/schlag/create", modelMap.get("saveAction"));

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

		Schlag schlag = schlagFactoryForTest.newSchlag();

		Exception exception = new RuntimeException("test exception");
		when(schlagService.create(schlag)).thenThrow(exception);

		// When
		String viewName = schlagController.create(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlag, (Schlag) modelMap.get("schlag"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/schlag/create", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "schlag.error.create", exception);

	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();

		Schlag schlag = schlagFactoryForTest.newSchlag();
		Integer gid = schlag.getGid();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Schlag schlagSaved = new Schlag();
		schlagSaved.setGid(gid);
		when(schlagService.update(schlag)).thenReturn(schlagSaved);

		// When
		String viewName = schlagController.update(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/schlag/form/" + schlag.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlagSaved, (Schlag) modelMap.get("schlag"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Schlag schlag = schlagFactoryForTest.newSchlag();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = schlagController.update(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlag, (Schlag) modelMap.get("schlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/schlag/update", modelMap.get("saveAction"));

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

		Schlag schlag = schlagFactoryForTest.newSchlag();

		Exception exception = new RuntimeException("test exception");
		when(schlagService.update(schlag)).thenThrow(exception);

		// When
		String viewName = schlagController.update(schlag, bindingResult, model, redirectAttributes, httpServletRequest);

		// Then
		assertEquals("schlag/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(schlag, (Schlag) modelMap.get("schlag"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/schlag/update", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "schlag.error.update", exception);

	}

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Schlag schlag = schlagFactoryForTest.newSchlag();
		Integer gid = schlag.getGid();

		// When
		String viewName = schlagController.delete(redirectAttributes, gid);

		// Then
		verify(schlagService).delete(gid);
		assertEquals("redirect:/schlag", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Schlag schlag = schlagFactoryForTest.newSchlag();
		Integer gid = schlag.getGid();

		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(schlagService).delete(gid);

		// When
		String viewName = schlagController.delete(redirectAttributes, gid);

		// Then
		verify(schlagService).delete(gid);
		assertEquals("redirect:/schlag", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "schlag.error.delete", exception);
	}

}
