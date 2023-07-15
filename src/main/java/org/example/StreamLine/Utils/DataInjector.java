package org.example.StreamLine.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataInjector {
    private String[] popularTags = {"love", "sunshine", "fitness", "Ronaldo", "Messi", "Football", "NFL", "NBA", "F1", "Brady", "Rodgers", "Mahomes", "Lebron", "Curry", "Kobe", "Goggins", "Motivation", "Inspiration", "movies", "sunset", "concert", "london", "Wembley", "USA", "UK", "Mom", "Dog", "HNY"};

    public DataInjector() {
    }

    public String generateRandomWord() {
        Random rand = new Random(); // Intialize a Random Number Generator with SysTime as the seed

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

    public void generateUsers() {
        String jdbcUrl = "jdbc:postgresql://localhost:5433/stream_line?allowPublicKeyRetrieval=true&useSSL=false";
        String username = "postgres";
        String password = "postgres";

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

}
