package com.inscription.plateform.service;

import com.inscription.plateform.entity.User;
import com.inscription.plateform.exception.ResourceNotFoundException;
import com.inscription.plateform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        return userRepository.save(user);
    }

    public User updateUser(long id, User userRequest){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (userRequest.getUserName() != null) user.setUserName(userRequest.getUserName());
        if (userRequest.getRoles() != null) user.setRoles(userRequest.getRoles());
        if (userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());
        if (userRequest.getForm() != null) user.setForm(userRequest.getForm());
        if (userRequest.getFirstName() != null) user.setFirstName(userRequest.getFirstName());
        if (userRequest.getLastName() != null) user.setLastName(userRequest.getLastName());

        return userRepository.save(user);
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }

    public User getUserById(long id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

// ------------------------------------------------------------------
    public User registerDefaultUser(User user) {
      //  String password = user.getPassword();
       // user.setPassword(passwordEncoder.encode(password));

        try {
            user.setRoles(Arrays.asList(roleRepository.findByName("USER")));

        }catch (Exception e){
            System.out.println("User Not found");
        }
        return userRepository.save(user);
    }
}
