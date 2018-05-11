package com.med.firstapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.UserRepository;
import com.med.firstapp.model.Privilege;
import com.med.firstapp.model.Role;
import com.med.firstapp.model.User;

@Transactional
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public AppUserDetailsService(){

    	System.out.println("********** AppUserDetailsService Constructor ");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	System.out.println("*********** loadUserByUsername called with " + username);

        User user = userRepo.findByUsername(username);
        if (user == null) {
        	throw new UsernameNotFoundException(username);
        }

        System.out.println("********** loadUserByUsername will return user ************* ");

        return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),
          user.isEnabled(),
          !user.isTokenExpired(),
          true,
          true,
          getAuthorities(user.getRoles())
          );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();

        for (Role role : roles) {
        	privileges.add(role.getName());

            for (Privilege privilege : role.getPrivileges()) {
                privileges.add(privilege.getName());
            }
        }
        return privileges;
    }

}
//In Spring, our Privilege is referred to as Role, and also as a (granted) authority