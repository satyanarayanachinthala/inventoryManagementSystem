package com.cde.microprograming.product.service;

import java.util.List;

import com.cde.microprograming.product.bo.RawMaterialBO;

public interface RawMaterialService {

	/**
	 * Creates raw material in the database
	 * 
	 * @param rawMaterialBO
	 * @return
	 */
	int createRawMaterial(RawMaterialBO rawMaterialBO);

	/**
	 * Fetches raw material for given id
	 * 
	 * @param id
	 * @return
	 */
	RawMaterialBO getRawMaterial(int id);

	List<RawMaterialBO> getAllRawMaterials();

	void deleteRawMaterial(int id);

	void updateRawMaterial(RawMaterialBO rawMaterialBO);

}
