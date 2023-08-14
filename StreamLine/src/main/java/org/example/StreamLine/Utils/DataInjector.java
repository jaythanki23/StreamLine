package org.example.StreamLine.Utils;

import java.sql.*;

import com.github.javafaker.Faker;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Service.HashtagServiceInterface;
import org.example.StreamLine.Service.PostServiceInterface;
import org.example.StreamLine.Service.UserServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import org.example.StreamLine.Model.User;

@Component
public class DataInjector {
    private PostServiceInterface postService;
    private UserServiceInterface userService;
    private HashtagServiceInterface hashtagService;

    private String[] popularTags = {"love", "sunshine", "fitness", "Ronaldo", "Messi", "Football", "NFL", "NBA", "F1", "Brady", "Rodgers", "Mahomes", "Lebron", "Curry", "Kobe", "Goggins", "Motivation", "Inspiration", "movies", "sunset", "concert", "london", "Wembley", "USA", "UK", "Mom", "Dog", "HNY"};

    private final String jdbcUrl = "jdbc:postgresql://localhost:5433/stream_line?allowPublicKeyRetrieval=true&useSSL=false";
    private final String username = "postgres";
    private final String password = "postgres";

    public DataInjector(UserServiceInterface userService, PostServiceInterface postService, HashtagServiceInterface hashtagService) {
        this.postService = postService;
        this.userService = userService;
        this.hashtagService = hashtagService;
    }

    public String generateRandomWord() {
        Random rand = new Random();

        return popularTags[rand.nextInt(popularTags.length)];
    }

    public String generateRandomTagString() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for(int i = 0; i <= rand.nextInt(10); i++) {
            sb.append("#");
            sb.append(generateRandomWord());
        }

        return  sb.toString();
    }

    public User selectRandomUser() {
        List<User> users = userService.getAllUsers();
        Random rand = new Random();

        User user = users.get(rand.nextInt(users.size()));
        return user;
    }

    public void generateUsers() {

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            Faker faker = new Faker();

            String insertQuery = "INSERT into user_table (user_name, first_name, last_name, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                for(int i = 0; i < 100; i++) {
                    String userName = faker.name().username();
                    String firstName = userName.substring(0, userName.indexOf('.'));
                    String lastName = "";
                    if(userName.indexOf('.') == userName.length()) {
                        lastName = "Doe";
                    } else {
                        lastName = userName.substring(userName.indexOf('.')+1);
                    }

                    String email = lastName + firstName + "@gmail.com";

                    stmt.setString(1, userName);
                    stmt.setString(2, firstName);
                    stmt.setString(3, lastName);
                    stmt.setString(4, email);

                    stmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generatePosts(){
        Faker faker = new Faker();

        for(int i = 0; i < 100; i++) {
            // pick random user
            User user = selectRandomUser();

            // generate random string
            String content = faker.lorem().sentence();

            // generate random hastag string
            String hashtagString = generateRandomTagString();

            // concatenate them
            content += hashtagString;

            Post post = new Post(user, content);

            postService.createPost(post);

            hashtagService.parseHashtag(hashtagString, post);

        }

    }

}
