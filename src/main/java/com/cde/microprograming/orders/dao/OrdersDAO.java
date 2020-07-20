package com.cde.microprograming.orders.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cde.microprograming.orders.model.Orders;

@Repository
@Transactional
public interface OrdersDAO extends JpaRepository<Orders, Integer> {

}
