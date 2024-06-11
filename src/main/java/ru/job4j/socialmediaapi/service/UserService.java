package ru.job4j.socialmediaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.dto.UserDto;
import ru.job4j.socialmediaapi.model.User;
import ru.job4j.socialmediaapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteUserById(id) > 0L;
    }

    public boolean update(User user) {
        return userRepository.update(user.getName(), user.getEmail(), user.getPassword(), user.getId());
    }
}
