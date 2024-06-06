package ru.job4j.socialmediaapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByUserId(int userId);

    List<Post> findByCreatedBetween(LocalDateTime startDate, LocalDateTime finishDate);

    List<Post> findByOrderByCreatedDesc(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("""
            update Post p set p.title = :title, p.description = :description 
            where p.id = :id
            """)
    int updateTitleAndDescription(@Param("title") String title, @Param("description") String description, @Param("id") Long id);

    @Query("""
            update Post p set p.image = null where p.id = :id
            """)
    int updateImage(@Param("id") Long id);

    @Query("""
            delete from Post p where p.id = :id
            """)
    boolean deletePostById(@Param("id") Long id);

    @Query("""
            select p from Post p 
            join Subscriber s on s.subscriber = :sub 
            where s.subscribed = p.user.id and s.status = false 
            order by p.created desc
            """)
    Page<Post> findAllPostBySubscribedAndOrderByCreatedDesc(@Param("sub") User subscriber, Pageable pageable);

    @Query("""
            update Post p set p.title = :t, p.description = :d, p.image = :im where p.id = :id
            """)
    boolean update(@Param("t") String title, @Param("d") String description, @Param("im") String image, @Param("id") Long id);
}
