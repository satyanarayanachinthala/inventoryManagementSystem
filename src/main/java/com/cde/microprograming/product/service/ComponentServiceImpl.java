package com.cde.microprograming.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.dao.ComponentDAO;
import com.cde.microprograming.product.model.Component;

@Service
public class ComponentServiceImpl implements ComponentService {

	@Autowired
	ComponentDAO componentDAO;

	@Override
	public int createComponent(ComponentBO componentBO) {
		Component component = new Component(componentBO);
		return componentDAO.save(component).getId();
	}

	@Override
	public ComponentBO getComponent(int id) {
		Optional<Component> component = componentDAO.findById(id);
		if (!component.isPresent())
			throw new InventoryNotFoundException("data not found: " + id);

		Component componentdata = component.get();
		return new ComponentBO(componentdata);
	}

	@Override
	public List<ComponentBO> getAllComponents() {
		List<Component> component = componentDAO.findAll();
		return component.stream().map(ComponentBO::new).collect(Collectors.toList());
	}

	@Override
	public void deleteComponent(int id) {
		Optional<Component> component = componentDAO.findById(id);
		if (!component.isPresent())
			throw new InventoryNotFoundException("data not found: " + id);
		componentDAO.deleteById(id);
	}

	@Override
	public void updateComponent(ComponentBO componentBO) {
		Optional<Component> componentData = componentDAO.findById(componentBO.getId());
		if (!componentData.isPresent()) {
			throw new InventoryNotFoundException("data not found " + componentBO.getId());
		}
		Component component = new Component(componentBO);
		componentDAO.save(component);
	}

}
