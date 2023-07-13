package com.streamline.userservice.Service;

import com.streamline.userservice.Model.User;
import com.streamline.userservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        User dbUser = userRepository.findById(id).get();

        if(Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
            dbUser.setUserName(user.getUserName());
        }

        if(Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            dbUser.setFirstName(user.getFirstName());
        }

        if(Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            dbUser.setLastName(user.getLastName());
        }

        if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            dbUser.setEmail(user.getEmail());
        }

        return userRepository.save(dbUser);

    }

    public String deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return "The user has been removed";
    }

}
