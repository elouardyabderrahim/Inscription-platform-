package com.inscription.plateform.dto;

import com.inscription.plateform.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import java.util.Collection;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastname;
    private String userName;
    private String email;
    private String password;

    private Collection<Role> roles;

}
