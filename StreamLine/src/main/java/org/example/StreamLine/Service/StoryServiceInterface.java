package org.example.StreamLine.Service;


import org.example.StreamLine.Model.Story;

import java.util.List;

public interface StoryServiceInterface {
    List<Story> getStoriesByUser(Integer userId);

    Story createStory(Story story);

    String deleteStoryById(Integer storyId);


}
