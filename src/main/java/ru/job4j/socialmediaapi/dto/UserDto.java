package ru.job4j.socialmediaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.job4j.socialmediaapi.model.Post;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDto {
    @EqualsAndHashCode.Include
    @Positive
    private long userId;
    @NotBlank(message = "username не может быть пустым")
    @Length(min = 4,
            max = 15,
            message = "username должно быть не менее 4 и не более 15 символов")
    private String username;
    private List<Post> posts = new ArrayList<>();
}
