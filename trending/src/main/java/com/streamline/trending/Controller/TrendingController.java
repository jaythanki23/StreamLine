package com.streamline.trending.Controller;

import com.streamline.trending.Model.Post;
import com.streamline.trending.Utils.TrendingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trending")
public class TrendingController {

    @Autowired
    TrendingUtil trendingUtil;


    @GetMapping
    @Cacheable(value = "posts")
    public List<Post> getTrendingPosts() {
        System.out.println("Using service");
        return trendingUtil.selectRandomPosts();
    }

}
