package com.inscription.plateform.controllers;


import com.inscription.plateform.dto.RoleDto;
import com.inscription.plateform.entity.Role;
import com.inscription.plateform.entity.User;
import com.inscription.plateform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;



    @PostMapping
//	@PostAuthorize("hasAuthority('ADMIN')")
    public Role saveRole(@RequestBody Role role) {
        roleService.save(role);
        return role;
    }

    @PostMapping(path = "/roleToUser")

    public void addRoleToUser(@RequestBody RoleDto roleUserForm) {

        roleService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }


    @DeleteMapping(path = "/removeRole")
    public  ResponseEntity<String> removeRoleToUser(@RequestBody RoleDto roleUserForm){
        roleService.removeRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
        return new ResponseEntity<String>("Role deleted successfully", HttpStatus.OK);
    }


}
