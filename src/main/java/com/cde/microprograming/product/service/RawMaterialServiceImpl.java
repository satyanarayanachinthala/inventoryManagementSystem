package com.cde.microprograming.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.dao.RawMaterialDAO;
import com.cde.microprograming.product.model.RawMaterial;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RawMaterialServiceImpl.class);

	@Autowired
	RawMaterialDAO rawMaterialDAO;

	@Override
	public RawMaterial createRawMaterial(RawMaterialBO rawMaterialBO) {
		RawMaterial rawMaterial = new RawMaterial(rawMaterialBO);
		return rawMaterialDAO.save(rawMaterial);
	}

	@Override
	public RawMaterialBO getRawMaterial(int id) {
		Optional<RawMaterial> rawMaterial = rawMaterialDAO.findById(id);

		if (!rawMaterial.isPresent()) {
			LOGGER.info("no data found for given " + id);
			throw new InventoryNotFoundException("id: " + id);
		}

		RawMaterial rawMaterialData = rawMaterial.get();
		return new RawMaterialBO(rawMaterialData);
	}

	@Override
	public List<RawMaterialBO> getAllRawMaterials() {
		List<RawMaterial> rawMaterials = rawMaterialDAO.findAll();
		return rawMaterials.stream().map(rawMaterial -> new RawMaterialBO(rawMaterial)).collect(Collectors.toList());
	}

	@Override
	public void deleteRawMaterial(int id) {
		rawMaterialDAO.deleteById(id);
	}

	@Override
	public RawMaterial updateRawMaterial(RawMaterialBO rawMaterialBO) {
		Optional<RawMaterial> rawMaterialData = rawMaterialDAO.findById(rawMaterialBO.getId());
		if (!rawMaterialData.isPresent()) {
			LOGGER.info("data found for given " + rawMaterialBO.getId());
			throw new InventoryNotFoundException("data not found for " + rawMaterialBO.getId());
		}
		RawMaterial rawMaterial = new RawMaterial(rawMaterialBO);
		return rawMaterialDAO.save(rawMaterial);
	}

}
