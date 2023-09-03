package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.StoryNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Story;

import java.util.List;

public interface StoryServiceInterface {
    // get all the stories uploaded by a user
    List<Story> getStoriesByUser(Integer userId) throws UserNotFoundException;

    // upload a story
    Story createStory(Story story) throws UserNotFoundException, PostNotFoundException;

    // delete a story
    String deleteStoryById(Integer storyId) throws StoryNotFoundException;


}
