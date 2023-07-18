package org.example.StreamLine.Service;

import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements PostServiceInterface {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public List<Post> getPostByUser(Integer userId) {
		return postRepository.findByUserId(userId);
	}

	@Override
	public List<Post> getPostWithPagination(Integer pageNumber, Integer pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepository.findAll(page);
		return pagePost.getContent();
	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post getPost(Integer id) {
		return postRepository.findById(id).orElse(null);
	}

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public String deletePostById(Integer id) {
		postRepository.deleteById(id);
		return "The Post has been deleted";
	}

}
