package org.example.StreamLine.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "comments_table")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    private User user;

    @NotNull(message = "Post cannot be null")
    @ManyToOne
    private Post post;

    @NotNull(message = "Description cannot be empty")
    private String description;

    @CreationTimestamp
    private Date creationDate;


    public Comment() {};

    public Comment(Integer id, User user, Post post, String description) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
