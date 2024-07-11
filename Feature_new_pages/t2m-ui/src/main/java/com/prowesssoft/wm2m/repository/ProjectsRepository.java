package com.prowesssoft.wm2m.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prowesssoft.wm2m.entity.Project;
import com.prowesssoft.wm2m.res.ApiResponse;

public interface ProjectsRepository extends JpaRepository<Project, Long> {
	
	Project findByRequestId(String requestId);
	List<Project> findAllByOrderByProjectIdDesc();
	
    @Modifying
    @Transactional
    @Query("Update Project t SET t.currentStatus=:currentStatus WHERE t.requestId=:requestId")
    public void updateProjectStatus(@Param("requestId") String requestId,@Param("currentStatus") String currentStatus);
	
 
}