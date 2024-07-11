package com.prowesssoft.wm2m.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

    @Column(name = "created_dt")
    private Date createdDt;

    @Column(name = "updated_dt")
    private Date updatedDt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    // Getter and setter methods for the fields...

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy() {
        UserDetails userDetails = getCurrentUserDetails();
        this.createdBy = getUserId(userDetails);
    }

    public void setUpdatedBy() {
        UserDetails userDetails = getCurrentUserDetails();
        this.updatedBy = getUserId(userDetails);
    }

    private Long getUserId(UserDetails userDetails) {
        if (userDetails != null && userDetails instanceof CurrentUser) {
            return Long.valueOf(((CurrentUser) userDetails).getUsername());
        }
        return null;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    private UserDetails getCurrentUserDetails() {
        try {
            return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            logger.error("Error getting current user details: {}", e.getMessage());
            return null;
        }
    }
}
