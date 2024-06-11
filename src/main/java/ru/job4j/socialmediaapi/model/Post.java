package ru.job4j.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Positive
    private Long id;

    @NotBlank(message = "Название поста не может быть пустым")
    private String title;

    @NotBlank(message = "описание должно содержать текст")
    private String description;

    private String image;

    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Positive
    private User userId;
}