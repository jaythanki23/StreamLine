package org.example.StreamLine.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Service.PostServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private PostServiceInterface postService;

    final private String url = "http://localhost:9200/dbserver1.public.hashtag_table/_search";

    public SearchController(PostServiceInterface postService) {
        this.postService = postService;
    }

    @GetMapping("{tag}")
    public ResponseEntity<List<Post>> getPostsByTag(@PathVariable("tag") String tag) throws JsonProcessingException {
        List<Integer> ids = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        String s = String.format("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\"after.tag\": \"%s\"}\n" +
                "  },\n" +
                "  \"_source\": [\"after.tag\", \"after.tag_id\"]\n" +
                "}", tag);

        ObjectMapper objectMapper = new ObjectMapper();


        HashMap map = WebClient.create(url).post()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(objectMapper.readValue(s, Map.class)), Map.class)
                .retrieve()
                .bodyToMono(HashMap.class).
                block();

        HashMap<String, Object> hits = (HashMap<String, Object>) map.get("hits");
        List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) hits.get("hits");

        // store all the post's ids
        for(HashMap<String, Object> hit: list) {
            HashMap<String, Object> source = (HashMap<String, Object>) hit.get("_source");
            HashMap<String, Object> after = (HashMap<String, Object>) source.get("after");
            ids.add((Integer) after.get("tag_id"));
        }

        // store all posts in a list
        for(int id: ids) {
            posts.add(postService.getPost(id));
        }

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


}
