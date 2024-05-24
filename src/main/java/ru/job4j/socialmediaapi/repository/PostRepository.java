package ru.job4j.socialmediaapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.Post;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByUserId(int userId);

    List<Post> findByCreatedBetween(LocalDateTime startDate, LocalDateTime finishDate);

    List<Post> findByOrderByCreatedDesc(Pageable pageable);
}
