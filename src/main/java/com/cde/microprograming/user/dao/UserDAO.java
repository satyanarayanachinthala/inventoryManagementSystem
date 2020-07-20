package com.cde.microprograming.user.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cde.microprograming.user.model.User;

@Repository
@Transactional
public interface UserDAO extends JpaRepository<User, Integer> {
	
	User findByUserName(String userName);

}
