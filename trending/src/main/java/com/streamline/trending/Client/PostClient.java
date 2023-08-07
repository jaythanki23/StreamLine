package com.streamline.trending.Client;

import com.streamline.trending.Model.Post;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.io.Serializable;
import java.util.List;

@HttpExchange
public interface PostClient {

    @GetExchange("/api/post/all")
    public List<Post> getAllPosts();

}
