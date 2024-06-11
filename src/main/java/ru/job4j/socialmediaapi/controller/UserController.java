package ru.job4j.socialmediaapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.service.UserService;
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId")
                                        @NotNull
                                    @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                    Long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        userService.save(user);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeById(@PathVariable("userId") @Positive(message = "userId должен быть положительным") long userId) {
        if (userService.deleteById(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody User user) {
        if (userService.update(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
