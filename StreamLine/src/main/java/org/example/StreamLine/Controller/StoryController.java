package org.example.StreamLine.Controller;

import org.example.StreamLine.Model.Story;
import org.example.StreamLine.Service.StoryService;
import org.example.StreamLine.Service.StoryServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {

    private StoryServiceInterface storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Story>> getStoriesByUser(@PathVariable("id") Integer userId) {
        return new ResponseEntity<List<Story>>(storyService.getStoriesByUser(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        return new ResponseEntity<Story>(storyService.createStory(story), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStoryById(@PathVariable("id") Integer storyId) {
        return new ResponseEntity<String>(storyService.deleteStoryById(storyId), HttpStatus.OK);
    }


}
