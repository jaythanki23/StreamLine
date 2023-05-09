package org.example.StreamLine.Service;


import org.example.StreamLine.Model.Post;

import java.util.List;

public interface PostServiceInterface {
	List<Post> getPostByUser(Integer userId);
	List<Post> getPostWithPagination(Integer pageNumber, Integer pageSize);

	Post createPost(Post post);
	
	String deletePostById(Integer id);
}
