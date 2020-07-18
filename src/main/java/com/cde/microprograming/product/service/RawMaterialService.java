package com.cde.microprograming.product.service;

import java.util.List;

import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.model.RawMaterial;


public interface RawMaterialService {
	
	RawMaterial createRawMaterial(RawMaterialBO rawMaterialBO);
	
	RawMaterialBO getRawMaterial(int id);
	
	List<RawMaterialBO> getAllRawMaterials();

	void deleteRawMaterial(int id);

	RawMaterial updateRawMaterial(RawMaterialBO rawMaterialBO);

}
