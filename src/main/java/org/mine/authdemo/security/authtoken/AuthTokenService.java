package org.mine.authdemo.security.authtoken;

import org.mine.authdemo.users.UserEntity;
import org.mine.authdemo.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {
    private AuthTokenRepository authTokenRepository;
    private UsersRepository usersRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository, UsersRepository usersRepository) {
        this.authTokenRepository = authTokenRepository;
        this.usersRepository = usersRepository;
    }

    public UUID createToken(UserEntity userEntity) {
        var token = new AuthTokenEntity();
        token.setUser(userEntity);
        return authTokenRepository.save(token).getToken();
    }

    public UserEntity getUserFromToken(UUID token) {
        var authToken = authTokenRepository.findById(token).orElseThrow();

        return authToken.getUser();
    }
}
