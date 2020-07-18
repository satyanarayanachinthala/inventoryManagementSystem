package com.cde.microprograming.product.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cde.microprograming.product.model.Component;

@Repository
@Transactional
public interface ComponentDAO extends JpaRepository<Component, Integer>{

}
