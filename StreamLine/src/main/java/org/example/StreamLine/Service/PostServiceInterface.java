package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Post;

import java.util.List;

public interface PostServiceInterface {
	List<Post> getPostByUser(Integer userId) throws UserNotFoundException;

	List<Post> getPostWithPagination(Integer pageNumber, Integer pageSize);

	List<Post> getAllPosts();

	Post getPost(Integer id) throws PostNotFoundException;

	Post createPost(Post post);
	
	String deletePostById(Integer id) throws PostNotFoundException;
}
