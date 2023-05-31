package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Hashtag;

import java.util.List;

public interface HashtagServiceInterface {
    // get tag
    Hashtag getTagById(Integer id);

    // get tags by post
    List<Hashtag> getTagByPost(Integer postId);

    // save tag
    Hashtag saveTag(Hashtag tag);
}
