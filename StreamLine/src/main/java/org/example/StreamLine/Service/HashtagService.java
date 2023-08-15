package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.HashtagNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Model.Hashtag;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Repository.HashtagRepository;
import org.example.StreamLine.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagService implements HashtagServiceInterface {
    private HashtagRepository hashtagRepository;
    private PostRepository postRepository;

    public HashtagService(HashtagRepository hashtagRepository, PostRepository postRepository) {
        this.hashtagRepository = hashtagRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Hashtag getTagById(Integer id) throws HashtagNotFoundException {
        Hashtag tag = hashtagRepository.findById(id).orElse(null);

        if(tag != null) {
            return hashtagRepository.findById(id).orElse(null);
        } else {
            throw new HashtagNotFoundException("Hashtag with id " + id + " doesn't exist");
        }

    }

    @Override
    public List<Hashtag> getTagByPost(Integer postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElse(null);

        if(post != null) {
            return hashtagRepository.findByPostId(postId);
        } else {
            throw new PostNotFoundException("Post with id " + postId + " doesn't exist");
        }

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
