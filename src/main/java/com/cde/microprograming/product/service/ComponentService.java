package com.cde.microprograming.product.service;

import java.util.List;

import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.model.Component;

public interface ComponentService {

	Component createComponent(ComponentBO componentBO);

	ComponentBO getComponent(int id);

	List<ComponentBO> getAllComponents();

	void deleteComponent(int id);

	Component updateComponent(ComponentBO componentBO);

}
