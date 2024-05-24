package ru.job4j.socialmediaapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmediaapi.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("""
    SELECT u FROM User u 
    WHERE u.login = :login AND u.password = :password
    """)
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("""
    SELECT u From User u 
    join Subscriber s on s.subscribed = u.id 
    WHERE s.subscribed = :subscribed AND s.status = false
    """)
    List<User> findAllSubscribed(@Param("subscribed") User subscribed);

    @Query("""
    SELECT u From User u 
    join Subscriber s on s.subscriber = u.id 
    WHERE s.subscriber = :subscriber AND s.status = true
    """)
    List<User> findAllSubscriber(@Param("subscriber") User subscriber);
}
