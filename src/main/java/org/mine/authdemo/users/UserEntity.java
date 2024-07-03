package org.mine.authdemo.users;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(unique=true, nullable=false)
    private String email;
}
