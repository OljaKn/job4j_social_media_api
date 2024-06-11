package ru.job4j.socialmediaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Positive
    private Long id;

    @NotBlank(message = "имя пользователя должно быть заполнено")
    private String name;

    @NotBlank(message = "email пользователя должен быть заполнен")
    private String email;

    @NotBlank(message = "password пользователя должен быть заполнен")
    private String password;
}