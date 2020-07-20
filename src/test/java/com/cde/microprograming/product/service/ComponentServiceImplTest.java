package com.cde.microprograming.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.bo.PurchasingInformationBO;
import com.cde.microprograming.product.dao.ComponentDAO;
import com.cde.microprograming.product.model.Component;
import com.cde.microprograming.product.model.PurchasingInformation;

@RunWith(MockitoJUnitRunner.class)
public class ComponentServiceImplTest {

	@Mock
	ComponentDAO componentDAO;

	@InjectMocks
	ComponentServiceImpl componentServiceImpl;

	Component component;

	ComponentBO componentBO;

	@Before
	public void init() {
		component = new Component();
		component.setId(1);
		component.setName("computer");
		component.setQuantity(2);
		component.setAvailableQuantity(2);
		component.setUser(1);
		PurchasingInformation purchasingInformation = new PurchasingInformation();
		purchasingInformation.setId(1);
		purchasingInformation.setPrice(100);
		purchasingInformation.setPurchasedFrom("data");
		purchasingInformation.setQuantity(2);
		List<PurchasingInformation> purchasingInformations = new ArrayList<PurchasingInformation>();
		purchasingInformations.add(purchasingInformation);
		component.setPurchasingInformations(purchasingInformations);

		componentBO = new ComponentBO();
		componentBO.setId(1);
		componentBO.setName("computer");
		componentBO.setQuantity(2);
		componentBO.setAvailableQuantity(2);
		componentBO.setUser(1);
		PurchasingInformationBO purchasingInformationBO = new PurchasingInformationBO();
		purchasingInformationBO.setId(1);
		purchasingInformationBO.setPrice(100);
		purchasingInformationBO.setPurchasedFrom("data");
		purchasingInformationBO.setQuantity(2);
		List<PurchasingInformationBO> purchasingInformationBOs = new ArrayList<PurchasingInformationBO>();
		purchasingInformationBOs.add(purchasingInformationBO);
		componentBO.setPurchasingInformations(purchasingInformationBOs);

	}

	@Test
	public void testGetComponent() {
		when(componentDAO.findById(1)).thenReturn(Optional.of(component));
		assertEquals("computer", componentServiceImpl.getComponent(1).getName());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testGetComponentException() {
		when(componentDAO.findById(2)).thenReturn(Optional.empty());
		componentServiceImpl.getComponent(2);
	}

	@Test
	public void testGetAllComponents() {
		List<Component> components = new ArrayList<Component>();
		components.add(component);
		when(componentDAO.findAll()).thenReturn(components);
		assertEquals("computer", componentServiceImpl.getAllComponents().get(0).getName());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testDeleteComponentException() {
		when(componentDAO.findById(2)).thenReturn(Optional.empty());
		componentServiceImpl.deleteComponent(2);
	}

	@Test
	public void testDeleteComponent() {
		when(componentDAO.findById(1)).thenReturn(Optional.of(component));
		componentServiceImpl.deleteComponent(1);
		assertEquals("computer", component.getName());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testUpdateComponentException() {
		componentBO.setId(2);
		when(componentDAO.findById(2)).thenReturn(Optional.empty());
		componentServiceImpl.updateComponent(componentBO);
	}

	@Test
	public void testUpdateComponent() {
		when(componentDAO.findById(1)).thenReturn(Optional.of(component));
		componentServiceImpl.updateComponent(componentBO);
		assertEquals("computer", component.getName());
	}

	@Test
	public void testcreateComponent() {
		when(componentDAO.save(ArgumentMatchers.any(Component.class))).thenReturn(component);
		assertEquals(1, componentServiceImpl.createComponent(componentBO));
	}
}
