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
import org.adams.geo.gisweb.business.service.FeldblockService;
//--- Entities
import org.adams.geo.gisweb.data.dto.Feldblock;
import org.adams.geo.gisweb.test.FeldblockFactoryForTest;
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
public class FeldblockControllerTest {

	@InjectMocks
	private FeldblockController feldblockController;
	@Mock
	private FeldblockService feldblockService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private FeldblockFactoryForTest feldblockFactoryForTest = new FeldblockFactoryForTest();

	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();

		List<Feldblock> list = new ArrayList<>();
		when(feldblockService.findAll()).thenReturn(list);

		// When
		String viewName = feldblockController.list(model);

		// Then
		assertEquals("feldblock/list", viewName);
		Map<String, ?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}

	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		// When
		String viewName = feldblockController.formForCreate(model);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertNull(((Feldblock) modelMap.get("feldblock")).getGid());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/feldblock/create", modelMap.get("saveAction"));

	}

	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		Integer gid = feldblock.getGid();
		when(feldblockService.findById(gid)).thenReturn(feldblock);

		// When
		String viewName = feldblockController.formForUpdate(model, gid);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblock, modelMap.get("feldblock"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/feldblock/update", modelMap.get("saveAction"));

	}

	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Feldblock feldblockCreated = new Feldblock();
		when(feldblockService.create(feldblock)).thenReturn(feldblockCreated);

		// When
		String viewName = feldblockController.create(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("redirect:/feldblock/form/" + feldblock.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblockCreated, modelMap.get("feldblock"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = feldblockController.create(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblock, modelMap.get("feldblock"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/feldblock/create", modelMap.get("saveAction"));

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

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();

		Exception exception = new RuntimeException("test exception");
		when(feldblockService.create(feldblock)).thenThrow(exception);

		// When
		String viewName = feldblockController.create(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblock, modelMap.get("feldblock"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/feldblock/create", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "feldblock.error.create", exception);

	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		Integer gid = feldblock.getGid();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Feldblock feldblockSaved = new Feldblock();
		feldblockSaved.setGid(gid);
		when(feldblockService.update(feldblock)).thenReturn(feldblockSaved);

		// When
		String viewName = feldblockController.update(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("redirect:/feldblock/form/" + feldblock.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblockSaved, modelMap.get("feldblock"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = feldblockController.update(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblock, modelMap.get("feldblock"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/feldblock/update", modelMap.get("saveAction"));

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

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();

		Exception exception = new RuntimeException("test exception");
		when(feldblockService.update(feldblock)).thenThrow(exception);

		// When
		String viewName = feldblockController.update(feldblock, bindingResult, model, redirectAttributes,
				httpServletRequest);

		// Then
		assertEquals("feldblock/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(feldblock, modelMap.get("feldblock"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/feldblock/update", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "feldblock.error.update", exception);

	}

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		Integer gid = feldblock.getGid();

		// When
		String viewName = feldblockController.delete(redirectAttributes, gid);

		// Then
		verify(feldblockService).delete(gid);
		assertEquals("redirect:/feldblock", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Feldblock feldblock = feldblockFactoryForTest.newFeldblock();
		Integer gid = feldblock.getGid();

		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(feldblockService).delete(gid);

		// When
		String viewName = feldblockController.delete(redirectAttributes, gid);

		// Then
		verify(feldblockService).delete(gid);
		assertEquals("redirect:/feldblock", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "feldblock.error.delete", exception);
	}

}
