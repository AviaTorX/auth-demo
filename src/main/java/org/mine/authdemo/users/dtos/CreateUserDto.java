package org.mine.authdemo.users.dtos;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;

    private String password;

    private String email;
}
