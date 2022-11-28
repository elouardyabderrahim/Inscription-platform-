package com.inscription.plateform.controllers;


import com.inscription.plateform.entity.User;
import com.inscription.plateform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserConroller {


    private UserService service;
    @Autowired
    public UserConroller(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String Home() {
        return ("<h1>Welcom to Inscription Platform<h1>");
    }


    @PostMapping("/singup")

    public String register(@RequestBody User user) {
        System.out.println(user);
        service.registerDefaultUser(user);
        try {

        }catch ( Exception e) {

            System.out.println("Nice Work");
        };

        return "register success";
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {

        User newUser = service.findById(id);
        if (user.getFirstName() != null) newUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) newUser.setLastName(user.getLastName());
        if (user.getUserName() != null) newUser.setUserName(user.getUserName());
        if (user.getEmail() != null) newUser.setEmail(user.getEmail());
        if (user.getPassword() != null) newUser.setPassword(user.getPassword());
        service.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    // Get All Users
    @GetMapping
//	@PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    //Select User by Id
    @GetMapping("/{id}")

    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    //Delete user by id

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        User users = service.findById(id);
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
