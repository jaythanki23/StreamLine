package org.example.StreamLine.Repository;


import org.example.StreamLine.Model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    public List<Story> findByUserId(Integer userId);

    // method to delete a story after 24 hours of upload
    @Query(value = "delete from Story where CURRENT_TIMESTAMP() > date_add(creationDate, interval 24 HOUR)", nativeQuery = true)
    public void deleteStoryAfterInterval();

}
