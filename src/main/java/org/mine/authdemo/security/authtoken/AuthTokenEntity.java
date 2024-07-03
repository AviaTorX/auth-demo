package org.mine.authdemo.security.authtoken;

import jakarta.persistence.*;
import lombok.Data;
import org.mine.authdemo.users.UserEntity;

import java.util.UUID;

@Data
@Entity(name="auth_tokens")
public class AuthTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token;

    @ManyToOne
    private UserEntity user;
}
