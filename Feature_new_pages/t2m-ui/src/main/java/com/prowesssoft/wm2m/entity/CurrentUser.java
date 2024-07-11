package com.prowesssoft.wm2m.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private Long userId; // New property to store user ID

    public CurrentUser(Long userId, String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    // Getter method for additional properties like userId
    public Long getUserId() {
        return userId;
    }

    // Override the getUsername method to return the username as before
    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
