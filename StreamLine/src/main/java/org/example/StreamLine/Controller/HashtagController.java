package org.example.StreamLine.Controller;

import jakarta.validation.Valid;
import org.example.StreamLine.Exceptions.HashtagNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Model.Hashtag;
import org.example.StreamLine.Service.HashtagServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class HashtagController {
    private HashtagServiceInterface hashtagService;

    public HashtagController(HashtagServiceInterface hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hashtag> getTagById(@PathVariable("id") Integer id) throws HashtagNotFoundException {
        return new ResponseEntity<Hashtag>(hashtagService.getTagById(id), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Hashtag>> getTagByPost(@PathVariable("id") Integer id) throws PostNotFoundException {
        return new ResponseEntity<List<Hashtag>>(hashtagService.getTagByPost(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Hashtag> saveTag(@RequestBody @Valid Hashtag tag) {
        return new ResponseEntity<Hashtag>(hashtagService.saveTag(tag), HttpStatus.CREATED);
    }
}
