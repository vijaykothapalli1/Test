package com.prowesssoft.wm2m.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prowesssoft.wm2m.entity.CurrentUser;
import com.prowesssoft.wm2m.entity.Role;
import com.prowesssoft.wm2m.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;  // Autowire UsersRepository

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        // Try to find the user and its roles from the database using email
        User user = usersRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Build user authorities
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        // Build and return custom user details
        return buildUserForAuthentication(user, authorities);
    }

    // Fill your extended User object (CurrentUser) here and return it
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        Long userId = user.getUserId(); // Get the user ID from the User entity

        String email = user.getEmail();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // If your database has more information about the user, you can set it here
        // For example:
        // String firstName = user.getFirstName();
        // String lastName = user.getLastName();

        // Create and return the extended CurrentUser object
        return new CurrentUser(userId, email, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Build user's authorities
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
}
