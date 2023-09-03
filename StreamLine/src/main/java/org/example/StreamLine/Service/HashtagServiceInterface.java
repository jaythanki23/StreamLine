package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.HashtagNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Model.Hashtag;
import org.example.StreamLine.Model.Post;

import java.util.List;

public interface HashtagServiceInterface {
    // get tag
    Hashtag getTagById(Integer id) throws HashtagNotFoundException;

    // get tags by post
    List<Hashtag> getTagByPost(Integer postId) throws PostNotFoundException;

    // save tag
    Hashtag saveTag(Hashtag tag);

    // pick hashtags from a post and store them in database
    void parseHashtag(String tagString, Post post);
}
