package ru.job4j.socialmediaapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.service.PostService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
@Validated
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> get(@PathVariable("postId")
                                    @NotNull
                                    @Min(value = 1, message = "номер ресурса должен быть 1 и более")
                                    Long postId) {
        return postService.findById(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> save(@Valid @RequestBody Post post) {
        postService.save(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(post);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeById(@PathVariable("postId") @NotNull
    @Positive(message = "postId должен быть положительным числом") Long postId) {
        if (postService.deleteById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody Post post) {
        if (postService.update(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
