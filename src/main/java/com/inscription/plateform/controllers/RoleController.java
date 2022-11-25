package com.inscription.plateform.controllers;


import com.inscription.plateform.dto.RoleDto;
import com.inscription.plateform.entity.Role;
import com.inscription.plateform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
