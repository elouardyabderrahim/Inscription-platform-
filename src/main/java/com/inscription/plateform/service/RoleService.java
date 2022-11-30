package com.inscription.plateform.service;


import com.inscription.plateform.entity.Role;
import com.inscription.plateform.entity.User;
import com.inscription.plateform.repository.RoleRepository;
import com.inscription.plateform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;




    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new RuntimeException("User not found");
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new RuntimeException("Role not found");
        user.getRoles().add(role);
        userRepository.save(user);

    }

    public void removeRoleToUser(String username, String roleName) {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new RuntimeException("User not found");
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new RuntimeException("Role not found");
        user.getRoles().remove(role);
        userRepository.save(user);

    }

    public void save(Role role) {
        roleRepository.save(role);

    }
}
