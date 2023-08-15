package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.UserAlreadyExistsException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void init() {
        user = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");
    }

    @Test
    public void serviceCreatesUser() throws UserAlreadyExistsException {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser);
    }

    @Test
    public void serviceGetsAllUsers() {
        User user1 = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");
        User user2 = new User("doe.john", "john", "doe", "johndoe@gmail.com");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> usersList = userService.getAllUsers();

        assertNotNull(usersList);

    }

    @Test
    public void serviceGetUserById() throws UserNotFoundException {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        User savedUser = userService.getUserById(1);
        assertNotNull(savedUser);
    }

    @Test
    public void serviceDeleteUserById() {
        assertAll(() -> userService.deleteUserById(1));
    }

}