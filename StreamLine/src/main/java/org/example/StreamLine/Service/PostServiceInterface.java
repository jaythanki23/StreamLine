package org.example.StreamLine.Service;


import org.example.StreamLine.Model.Post;

import java.util.List;

public interface PostServiceInterface {
	List<Post> getPostByUser(Integer userId);

	List<Post> getPostWithPagination(Integer pageNumber, Integer pageSize);

	List<Post> getAllPosts();

	Post getPost(Integer id);

	Post createPost(Post post);
	
	String deletePostById(Integer id);
}
