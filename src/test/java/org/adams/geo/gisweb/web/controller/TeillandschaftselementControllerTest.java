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
import org.adams.geo.gisweb.business.service.TeillandschaftselementService;
//--- Entities
import org.adams.geo.gisweb.data.dto.Teillandschaftselement;
import org.adams.geo.gisweb.test.TeillandschaftselementFactoryForTest;
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
public class TeillandschaftselementControllerTest {

	@InjectMocks
	private TeillandschaftselementController teillandschaftselementController;
	@Mock
	private TeillandschaftselementService teillandschaftselementService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TeillandschaftselementFactoryForTest teillandschaftselementFactoryForTest = new TeillandschaftselementFactoryForTest();

	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();

		List<Teillandschaftselement> list = new ArrayList<>();
		when(teillandschaftselementService.findAll()).thenReturn(list);

		// When
		String viewName = teillandschaftselementController.list(model);

		// Then
		assertEquals("teillandschaftselement/list", viewName);
		Map<String, ?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}

	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		// When
		String viewName = teillandschaftselementController.formForCreate(model);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertNull(((Teillandschaftselement) modelMap.get("teillandschaftselement")).getGid());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/create", modelMap.get("saveAction"));

	}

	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		Integer gid = teillandschaftselement.getGid();
		when(teillandschaftselementService.findById(gid)).thenReturn(teillandschaftselement);

		// When
		String viewName = teillandschaftselementController.formForUpdate(model, gid);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselement, modelMap.get("teillandschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/update", modelMap.get("saveAction"));

	}

	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Teillandschaftselement teillandschaftselementCreated = new Teillandschaftselement();
		when(teillandschaftselementService.create(teillandschaftselement)).thenReturn(teillandschaftselementCreated);

		// When
		String viewName = teillandschaftselementController.create(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/teillandschaftselement/form/" + teillandschaftselement.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselementCreated, modelMap.get("teillandschaftselement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = teillandschaftselementController.create(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselement, modelMap.get("teillandschaftselement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/create", modelMap.get("saveAction"));

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

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();

		Exception exception = new RuntimeException("test exception");
		when(teillandschaftselementService.create(teillandschaftselement)).thenThrow(exception);

		// When
		String viewName = teillandschaftselementController.create(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselement, modelMap.get("teillandschaftselement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/create", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "teillandschaftselement.error.create", exception);

	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		Integer gid = teillandschaftselement.getGid();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Teillandschaftselement teillandschaftselementSaved = new Teillandschaftselement();
		teillandschaftselementSaved.setGid(gid);
		when(teillandschaftselementService.update(teillandschaftselement)).thenReturn(teillandschaftselementSaved);

		// When
		String viewName = teillandschaftselementController.update(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/teillandschaftselement/form/" + teillandschaftselement.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselementSaved, modelMap.get("teillandschaftselement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = teillandschaftselementController.update(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselement, modelMap.get("teillandschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/update", modelMap.get("saveAction"));

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

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();

		Exception exception = new RuntimeException("test exception");
		when(teillandschaftselementService.update(teillandschaftselement)).thenThrow(exception);

		// When
		String viewName = teillandschaftselementController.update(teillandschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("teillandschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(teillandschaftselement, modelMap.get("teillandschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/teillandschaftselement/update", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "teillandschaftselement.error.update", exception);

	}

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		Integer gid = teillandschaftselement.getGid();

		// When
		String viewName = teillandschaftselementController.delete(redirectAttributes, gid);

		// Then
		verify(teillandschaftselementService).delete(gid);
		assertEquals("redirect:/teillandschaftselement", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Teillandschaftselement teillandschaftselement = teillandschaftselementFactoryForTest
				.newTeillandschaftselement();
		Integer gid = teillandschaftselement.getGid();

		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(teillandschaftselementService).delete(gid);

		// When
		String viewName = teillandschaftselementController.delete(redirectAttributes, gid);

		// Then
		verify(teillandschaftselementService).delete(gid);
		assertEquals("redirect:/teillandschaftselement", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "teillandschaftselement.error.delete",
				exception);
	}

}
