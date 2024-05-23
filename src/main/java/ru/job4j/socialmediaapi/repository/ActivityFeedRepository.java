package ru.job4j.socialmediaapi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmediaapi.model.ActivityFeed;

public interface ActivityFeedRepository extends CrudRepository<ActivityFeed, Long> {
}
