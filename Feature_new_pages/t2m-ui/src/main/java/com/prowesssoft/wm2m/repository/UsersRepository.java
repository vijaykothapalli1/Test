package com.prowesssoft.wm2m.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowesssoft.wm2m.entity.User;

public interface UsersRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);




}
