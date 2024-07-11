package com.prowesssoft.wm2m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowesssoft.wm2m.entity.Project;
import com.prowesssoft.wm2m.entity.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {
	List<ProjectStatus> findAllByOrderByProjectStatusIdDesc();
	List<ProjectStatus> findByRequestIdOrderByProjectStatusIdDesc(String requestId);
	List<ProjectStatus> findByRequestId(String requestId);
}
