package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.StoryNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Model.Story;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.PostRepository;
import org.example.StreamLine.Repository.StoryRepository;
import org.example.StreamLine.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService implements StoryServiceInterface {

    private StoryRepository storyRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public StoryService(StoryRepository storyRepository, UserRepository userRepository, PostRepository postRepository) {
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Story> getStoriesByUser(Integer userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);

        if(user != null) {
            return storyRepository.findByUserId(userId);
        } else {
            throw new UserNotFoundException("User with id " + userId + " doesn't exist");
        }

    }

    @Override
    public Story createStory(Story story) throws UserNotFoundException, PostNotFoundException {
        User user = userRepository.findById(story.getUser().getId()).orElse(null);
        Post post = postRepository.findById(story.getPost().getId()).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + story.getUser().getId() + " doesn't exist");
        }

        if(post == null) {
            throw new PostNotFoundException("Post with id " + story.getPost().getId() + " doesn't exist");
        }

        return storyRepository.save(story);
    }

    @Override
    public String deleteStoryById(Integer storyId) throws StoryNotFoundException {
        Story story = storyRepository.findById(storyId).orElse(null);

        if(story != null) {
            storyRepository.deleteById(storyId);
            return "The story has been deleted";
        } else {
            throw new StoryNotFoundException("Story wit id " + storyId + " doesn't exist");
        }


    }
}
