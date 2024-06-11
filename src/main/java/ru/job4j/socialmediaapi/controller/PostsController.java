package ru.job4j.socialmediaapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.service.PostService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {
    @Autowired
    private PostService postService;

    @GetMapping("/usersId")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getListUserDto(@RequestBody List<Long> usersId) {
    return postService.findByUsersIdAllPosts(usersId);
    }
}
