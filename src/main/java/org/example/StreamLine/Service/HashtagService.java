package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Hashtag;
import org.example.StreamLine.Repository.HashtagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService implements HashtagServiceInterface {
    private HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public Hashtag getTagById(Integer id) {
        return hashtagRepository.findById(id).orElse(null);
    }

    @Override
    public List<Hashtag> getTagByPost(Integer postId) {
        return hashtagRepository.findByPostId(postId);
    }

    @Override
    public Hashtag saveTag(Hashtag tag) {
        return hashtagRepository.save(tag);
    }
}
