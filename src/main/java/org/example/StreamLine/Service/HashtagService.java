package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Hashtag;
import org.example.StreamLine.Model.Post;
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

    @Override
    public void parseHashtag(String tagString, Post post) {
        boolean flag = false;
        String res = "";
        for(char ch: tagString.toCharArray()) {
            if(ch == '#') {
                if(flag) {
                    Hashtag tag = new Hashtag(res, post);
                    saveTag(tag);
                } else {
                    flag = true;
                }
                res = "";
                continue;
            }

            if(flag) {
                if (!Character.isLetterOrDigit(ch)) {
                    Hashtag tag = new Hashtag(res, post);
                    saveTag(tag);
                    flag = false;
                    res = "";
                    continue;
                } else {
                    res += ch;
                }
            }
        }

        if(!res.equals("")) {
            Hashtag tag = new Hashtag(res, post);
            saveTag(tag);
        }
    }
}
