package org.example.StreamLine.Service;


import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Post;

import java.util.List;

public interface PostServiceInterface {
	// find a post by user
	List<Post> getPostByUser(Integer userId) throws UserNotFoundException;

	// get posts based on page size
	List<Post> getPostWithPagination(Integer pageNumber, Integer pageSize);

	// get all posts
	List<Post> getAllPosts();

	// get a post based on its id
	Post getPost(Integer id) throws PostNotFoundException;

	// upload a post
	Post createPost(Post post);

	// delete a posts
	String deletePostById(Integer id) throws PostNotFoundException;
}
