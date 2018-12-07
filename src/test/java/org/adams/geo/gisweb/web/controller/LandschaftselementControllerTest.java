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
import org.adams.geo.gisweb.business.service.LandschaftselementService;
//--- Entities
import org.adams.geo.gisweb.data.dto.Landschaftselement;
import org.adams.geo.gisweb.test.LandschaftselementFactoryForTest;
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
public class LandschaftselementControllerTest {

	@InjectMocks
	private LandschaftselementController landschaftselementController;
	@Mock
	private LandschaftselementService landschaftselementService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private LandschaftselementFactoryForTest landschaftselementFactoryForTest = new LandschaftselementFactoryForTest();

	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();

		List<Landschaftselement> list = new ArrayList<>();
		when(landschaftselementService.findAll()).thenReturn(list);

		// When
		String viewName = landschaftselementController.list(model);

		// Then
		assertEquals("landschaftselement/list", viewName);
		Map<String, ?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}

	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		// When
		String viewName = landschaftselementController.formForCreate(model);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertNull(((Landschaftselement) modelMap.get("landschaftselement")).getGid());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/landschaftselement/create", modelMap.get("saveAction"));

	}

	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		Integer gid = landschaftselement.getGid();
		when(landschaftselementService.findById(gid)).thenReturn(landschaftselement);

		// When
		String viewName = landschaftselementController.formForUpdate(model, gid);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselement, modelMap.get("landschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/landschaftselement/update", modelMap.get("saveAction"));

	}

	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Landschaftselement landschaftselementCreated = new Landschaftselement();
		when(landschaftselementService.create(landschaftselement)).thenReturn(landschaftselementCreated);

		// When
		String viewName = landschaftselementController.create(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/landschaftselement/form/" + landschaftselement.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselementCreated, modelMap.get("landschaftselement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = landschaftselementController.create(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselement, modelMap.get("landschaftselement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/landschaftselement/create", modelMap.get("saveAction"));

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

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();

		Exception exception = new RuntimeException("test exception");
		when(landschaftselementService.create(landschaftselement)).thenThrow(exception);

		// When
		String viewName = landschaftselementController.create(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselement, modelMap.get("landschaftselement"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/landschaftselement/create", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "landschaftselement.error.create", exception);

	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		Integer gid = landschaftselement.getGid();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		Landschaftselement landschaftselementSaved = new Landschaftselement();
		landschaftselementSaved.setGid(gid);
		when(landschaftselementService.update(landschaftselement)).thenReturn(landschaftselementSaved);

		// When
		String viewName = landschaftselementController.update(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("redirect:/landschaftselement/form/" + landschaftselement.getGid(), viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselementSaved, modelMap.get("landschaftselement"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();

		givenPopulateModel();

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

		// When
		String viewName = landschaftselementController.update(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselement, modelMap.get("landschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/landschaftselement/update", modelMap.get("saveAction"));

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

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();

		Exception exception = new RuntimeException("test exception");
		when(landschaftselementService.update(landschaftselement)).thenThrow(exception);

		// When
		String viewName = landschaftselementController.update(landschaftselement, bindingResult, model,
				redirectAttributes, httpServletRequest);

		// Then
		assertEquals("landschaftselement/form", viewName);

		Map<String, ?> modelMap = model.asMap();

		assertEquals(landschaftselement, modelMap.get("landschaftselement"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/landschaftselement/update", modelMap.get("saveAction"));

		Mockito.verify(messageHelper).addException(model, "landschaftselement.error.update", exception);

	}

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		Integer gid = landschaftselement.getGid();

		// When
		String viewName = landschaftselementController.delete(redirectAttributes, gid);

		// Then
		verify(landschaftselementService).delete(gid);
		assertEquals("redirect:/landschaftselement", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS, "delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		Landschaftselement landschaftselement = landschaftselementFactoryForTest.newLandschaftselement();
		Integer gid = landschaftselement.getGid();

		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(landschaftselementService).delete(gid);

		// When
		String viewName = landschaftselementController.delete(redirectAttributes, gid);

		// Then
		verify(landschaftselementService).delete(gid);
		assertEquals("redirect:/landschaftselement", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "landschaftselement.error.delete", exception);
	}

}
