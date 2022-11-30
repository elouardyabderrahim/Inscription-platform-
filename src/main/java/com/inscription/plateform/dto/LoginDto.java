package com.inscription.plateform.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;

}
