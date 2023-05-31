package org.example.StreamLine.Model;

import jakarta.persistence.*;
import org.example.StreamLine.Model.Post;

@Entity
@Table(name = "hastag_table")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tag;

    @ManyToOne
    @JoinColumn(
            name="tag_id",
            referencedColumnName = "id"
    )
    private Post post;

    public Hashtag() {
    }

    public Hashtag(String tag, Post post) {
        this.tag = tag;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
