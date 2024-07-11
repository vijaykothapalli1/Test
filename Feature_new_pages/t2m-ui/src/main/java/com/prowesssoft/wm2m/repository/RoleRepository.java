package com.prowesssoft.wm2m.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowesssoft.wm2m.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}