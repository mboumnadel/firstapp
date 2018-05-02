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
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        userRepo.save(user);

        Role adminRole = roleRepo.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("test@test.com");
        admin.setRoles(Arrays.asList(adminRole));
        admin.setEnabled(true);
        userRepo.save(admin);
    }
}
