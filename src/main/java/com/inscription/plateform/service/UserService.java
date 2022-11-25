package com.inscription.plateform.service;

import com.inscription.plateform.entity.User;
import com.inscription.plateform.repository.RoleRepository;
import com.inscription.plateform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UserService  implements AppService <User>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
    //private PasswordEncoder passwordEncoder;


    @Override
    public void save(User user) {
        String password = user.getPassword();
       // Employee.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User registerDefaultUser(User userArg) {

        String userName = userArg.getUserName();
        User user = userRepository.findByUserName(userName);
        if (user == null) {

//            String password = user.getPassword();
           // user.setPassword(passwordEncoder.encode(password));

            user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
            return userRepository.save(user);
        } else {

            throw new RuntimeException("Username invalid");
        }
    }
}
