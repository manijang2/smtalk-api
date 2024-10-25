package me.smtalk.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true)
    @NotNull
    @Size(min = 1)
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Builder
    private User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}