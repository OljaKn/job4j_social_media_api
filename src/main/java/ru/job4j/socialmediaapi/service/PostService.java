package ru.job4j.socialmediaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;
import ru.job4j.socialmediaapi.dto.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(long postId) {
        return postRepository.findById(postId);
    }

    public boolean deleteById(Long id) {
        return postRepository.deletePostById(id);
    }

    public boolean update(Post post) {
        return postRepository.update(post.getTitle(), post.getDescription(), post.getImage(), post.getId());
    }

    public List<UserDto> findByUsersIdAllPosts(List<Long> usersId) {
            List<UserDto> result = new ArrayList<>();
            for (Long userId : usersId) {
                Optional<User> user = userRepository.findById(userId);
                if (user.isPresent()) {
                    List<Post> posts = postRepository.findByUserId(Math.toIntExact(userId));
                    result.add(UserMapper.getUserDro(user.get(), posts));
                }
            }
            return result;
        }
}

   /* @Transactional
    public Post createPost(User author, String title, String description, String image) {
        Post post = new Post();
        post.setAuthor(author);
        post.setTitle(title);
        post.setDescription(description);
        post.setImage(image);
        post.setCreated(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, String title, String description) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Пост не найден"));
        post.setTitle(title);
        post.setDescription(description);
        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Optional<Object> findById(Long postId) {
        return Optional.of(postRepository.findById(postId));
    }*/
