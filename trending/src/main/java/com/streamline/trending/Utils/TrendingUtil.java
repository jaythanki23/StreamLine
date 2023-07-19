package com.streamline.trending.Utils;

import com.streamline.trending.Client.PostClient;
import com.streamline.trending.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class TrendingUtil {
    @Autowired
    private PostClient postClient;

    public List<Post> getAllPosts() {
        return postClient.getAllPosts();
    }

    public List<Post> selectRandomPosts() {
        Random rand = new Random();
        List<Post> randomPosts = new ArrayList<>();
        List<Post> posts = getAllPosts();

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < 25; i++) {
            int index = rand.nextInt(posts.size());

            if(set.contains(index)) {
               while(set.contains(index)) {
                   index = rand.nextInt(posts.size());
               }
               set.add(index);
               Post post = posts.get(index);
               randomPosts.add(post);

            } else {
                set.add(index);
                randomPosts.add(posts.get(index));
            }
        }

        return randomPosts;

    }
}
