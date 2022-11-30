package com.inscription.plateform.controllers;

import com.inscription.plateform.dto.UserDto;
import com.inscription.plateform.entity.User;
import com.inscription.plateform.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserConroller {

    @Autowired
    private ModelMapper modelMapper;

    private UserService userService;

    @Autowired
    public UserConroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String Home() {
        return ("<h1>Welcom to Inscription Platform<h1>");
    }

    /*@PostMapping("/singup")
    public String register(@RequestBody User user) {
        System.out.println(user);
        userService.registerDefaultUser(user);
        try {

        }catch ( Exception e) {
            System.out.println("Nice Work");
        };
        return "register success";
    }*/

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.createUser(userRequest);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {

        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.updateUser(id, userRequest);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
//	@PostAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getAllUser() {
        return userService.getAllUser().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
    }

    //Delete user by id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }


}
