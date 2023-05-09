package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Story;
import org.example.StreamLine.Repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService implements StoryServiceInterface {

    private StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public List<Story> getStoriesByUser(Integer userId) {
        return storyRepository.findByUserId(userId);
    }

    @Override
    public Story createStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public String deleteStoryById(Integer storyId) {
        storyRepository.deleteById(storyId);
        return "The story has been deleted";
    }
}
