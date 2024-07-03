package org.mine.authdemo.users;

import org.mine.authdemo.security.authtoken.AuthTokenService;
import org.mine.authdemo.security.jwt.JwtService;
import org.mine.authdemo.users.dtos.CreateUserDto;
import org.mine.authdemo.users.dtos.LoginUserDto;
import org.mine.authdemo.users.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthTokenService authTokenService;
    private JwtService jwtService;

    public UsersService(JwtService jwtService, AuthTokenService authTokenService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UsersRepository usersRepository) {
        this.jwtService = jwtService;
        this.authTokenService = authTokenService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
    }

    public UserResponseDto createUser(CreateUserDto request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity savedUser = usersRepository.save(user);

        UserResponseDto response = modelMapper.map(savedUser, UserResponseDto.class);

        response.setToken(jwtService.createJwtToken(savedUser.getUsername()));

        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto request) {
        UserEntity user = usersRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        UserResponseDto response = modelMapper.map(user, UserResponseDto.class);


        response.setToken(jwtService.createJwtToken(user.getUsername()));

        return response;
    }

    public UserResponseDto findUserByUsername(String username) {
        UserEntity user = usersRepository.findByUsername(username);
        var response = modelMapper.map(user, UserResponseDto.class);

        return response;
    }
}
