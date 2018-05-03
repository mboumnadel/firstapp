package com.med.firstapp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.med.firstapp.dao.PrivilegeRepository;
import com.med.firstapp.dao.RoleRepository;
import com.med.firstapp.dao.UserRepository;
import com.med.firstapp.model.Role;
import com.med.firstapp.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PrivilegeRepository privilegeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
	public void createUser(){

    	String read = "READ_PRIVILEGE";
    	String write = "WRITE_PRIVILEGE";
    	privilegeRepo.findByName(read);
    	privilegeRepo.findByName(write);

    	Role userRole = roleRepo.findByName("ROLE_USER");
    	User user = new User();
        user.setFirstName("Simple");
        user.setLastName("User");
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("user@test.com");
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        user.setTokenExpired(false);
        userRepo.save(user);

        Role adminRole = roleRepo.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setFirstName("Powerfull");
        admin.setLastName("Admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admint@test.com");
        admin.setRoles(Arrays.asList(adminRole));
        admin.setEnabled(true);
        admin.setTokenExpired(false);
        userRepo.save(admin);
    }
}
