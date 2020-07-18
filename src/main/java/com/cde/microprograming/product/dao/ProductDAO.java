package com.cde.microprograming.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cde.microprograming.product.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

}
