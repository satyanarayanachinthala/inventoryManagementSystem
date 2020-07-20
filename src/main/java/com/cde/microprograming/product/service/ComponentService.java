package com.cde.microprograming.product.service;

import java.util.List;

import com.cde.microprograming.product.bo.ComponentBO;

public interface ComponentService {

	int createComponent(ComponentBO componentBO);

	ComponentBO getComponent(int id);

	List<ComponentBO> getAllComponents();

	void deleteComponent(int id);

	void updateComponent(ComponentBO componentBO);

}
