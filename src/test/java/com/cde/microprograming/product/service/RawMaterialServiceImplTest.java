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
import com.cde.microprograming.product.bo.PurchasingInformationBO;
import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.dao.RawMaterialDAO;
import com.cde.microprograming.product.model.PurchasingInformation;
import com.cde.microprograming.product.model.RawMaterial;

@RunWith(MockitoJUnitRunner.class)
public class RawMaterialServiceImplTest {

	@Mock
	RawMaterialDAO rawMaterialDAO;

	@InjectMocks
	RawMaterialServiceImpl rawMaterialServiceImpl;

	RawMaterial rawMaterial;
	RawMaterialBO rawMaterialBO;

	@Before
	public void init() {
		rawMaterial = new RawMaterial();
		rawMaterial.setId(1);
		rawMaterial.setName("glass");
		rawMaterial.setQuantity(2);
		rawMaterial.setAvailableQuantity(2);
		PurchasingInformation purchasingInformation = new PurchasingInformation();
		purchasingInformation.setId(1);
		purchasingInformation.setPrice(100);
		purchasingInformation.setPurchasedFrom("data");
		purchasingInformation.setQuantity(2);
		List<PurchasingInformation> purchasingInformations = new ArrayList<PurchasingInformation>();
		purchasingInformations.add(purchasingInformation);

		rawMaterial.setPurchasingInformations(purchasingInformations);

		rawMaterialBO = new RawMaterialBO();

		rawMaterialBO.setId(1);
		rawMaterialBO.setName("glass");
		rawMaterialBO.setQuantity(2);
		rawMaterialBO.setAvailableQuantity(2);
		PurchasingInformationBO purchasingInformationBO = new PurchasingInformationBO();
		purchasingInformationBO.setId(1);
		purchasingInformationBO.setPrice(100);
		purchasingInformationBO.setPurchasedFrom("data");
		purchasingInformationBO.setQuantity(2);
		List<PurchasingInformationBO> purchasingInformationBOs = new ArrayList<PurchasingInformationBO>();
		purchasingInformationBOs.add(purchasingInformationBO);

		rawMaterialBO.setPurchasingInformations(purchasingInformationBOs);

	}

	@Test
	public void testGetRawMaterial() {
		when(rawMaterialDAO.findById(1)).thenReturn(Optional.of(rawMaterial));
		assertEquals("glass", rawMaterialServiceImpl.getRawMaterial(1).getName());
	}

	@Test
	public void testGetRawMaterialForNull() {
		when(rawMaterialDAO.findById(2)).thenReturn(Optional.empty());
		assertEquals(null, rawMaterialServiceImpl.getRawMaterial(2));
	}

	@Test
	public void testGetAllRawMaterials() {
		List<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
		rawMaterials.add(rawMaterial);
		when(rawMaterialDAO.findAll()).thenReturn(rawMaterials);
		assertEquals("glass", rawMaterialServiceImpl.getAllRawMaterials().get(0).getName());

	}

	@Test(expected = InventoryNotFoundException.class)
	public void testDeleteRawMaterialException() {
		when(rawMaterialDAO.findById(2)).thenReturn(Optional.empty());
		rawMaterialServiceImpl.deleteRawMaterial(2);
	}

	@Test
	public void testDeleteRawMaterial() {
		when(rawMaterialDAO.findById(1)).thenReturn(Optional.of(rawMaterial));
		rawMaterialServiceImpl.deleteRawMaterial(1);
		assertEquals("glass", rawMaterial.getName());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testUpdateRawMaterialException() {
		rawMaterialBO.setId(2);
		when(rawMaterialDAO.findById(2)).thenReturn(Optional.empty());
		rawMaterialServiceImpl.updateRawMaterial(rawMaterialBO);
	}

	@Test
	public void testUpdateRawMaterial() {
		when(rawMaterialDAO.findById(1)).thenReturn(Optional.of(rawMaterial));
		rawMaterialServiceImpl.updateRawMaterial(rawMaterialBO);
		assertEquals("glass", rawMaterial.getName());
	}
	
	@Test
	public void testCreateRawMaterial() {
		when(rawMaterialDAO.save(ArgumentMatchers.any(RawMaterial.class))).thenReturn(rawMaterial);
		assertEquals(1, rawMaterialServiceImpl.createRawMaterial(rawMaterialBO));
	}
}
