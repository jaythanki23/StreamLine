package org.example.StreamLine.Repository;

import org.example.StreamLine.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void returnsSavedUser() {
        User user = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void returnsAllUsers() {
        User user1 = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");
        User user2 = new User("doe.john", "john", "doe", "johndoe@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertNotNull(users);
        assertTrue(users.size() == 2);

    }

    @Test
    public void returnsUserById() {
        User user = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");

        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(savedUser);

    }

    @Test
    public void deletesUserById() {
        User user = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");

        userRepository.save(user);

        userRepository.deleteById(user.getId());

        User savedUser = userRepository.findById(user.getId()).orElse(null);

        assertNull(savedUser);

    }

    @Test
    public void updatesUserById() {
        User user = new User("thanki.jay", "jay", "thanki", "thankijay99@gmail.com");
        user = userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).orElse(null);
        savedUser.setFirstName("jai");

        User updatedUser = userRepository.save(savedUser);

        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());

    }




}