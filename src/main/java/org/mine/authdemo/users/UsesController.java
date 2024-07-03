package org.mine.authdemo.users;

import org.mine.authdemo.users.dtos.CreateUserDto;
import org.mine.authdemo.users.dtos.LoginUserDto;
import org.mine.authdemo.users.dtos.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsesController {

    private UsersService usersService;

    public UsesController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto request) {
        UserResponseDto createdUser = usersService.createUser(request);
        return ResponseEntity.created(URI.create("/users/"+createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody LoginUserDto request) {
        UserResponseDto loggedInUser = usersService.verifyUser(request);

        return ResponseEntity.ok(loggedInUser);
    }
}
