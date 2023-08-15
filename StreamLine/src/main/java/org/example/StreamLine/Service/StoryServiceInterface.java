package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.StoryNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Story;

import java.util.List;

public interface StoryServiceInterface {
    List<Story> getStoriesByUser(Integer userId) throws UserNotFoundException;

    Story createStory(Story story) throws UserNotFoundException, PostNotFoundException;

    String deleteStoryById(Integer storyId) throws StoryNotFoundException;


}
