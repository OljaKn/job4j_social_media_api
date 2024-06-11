package ru.job4j.socialmediaapi.dto;

import org.mapstruct.Mapper;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

@Mapper
public class UserMapper {
    public static UserDto getUserDro(User user, List<Post> posts) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getName());
        userDto.setPosts(posts);
        return userDto;
    }
}
