package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.PostRepository;
import org.example.StreamLine.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements PostServiceInterface {

	private PostRepository postRepository;
	private UserRepository userRepository;

	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public List<Post> getPostByUser(Integer userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);

		if(user != null) {
			return postRepository.findByUserId(userId);
		} else {
			throw new UserNotFoundException("User with id " + userId + " doesn't exist");
		}

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
	public Post getPost(Integer id) throws PostNotFoundException {
		Post post = postRepository.findById(id).orElse(null);

		if(post != null) {
			return post;
		} else {
			throw new PostNotFoundException("Post with id " + id + " doesn't exist");
		}
	}

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public String deletePostById(Integer id) throws PostNotFoundException {
		Post post = postRepository.findById(id).orElse(null);

		if(post != null) {
			postRepository.deleteById(id);
			return "The Post has been deleted";
		} else {
			throw new PostNotFoundException("Post with id " + id + " doesn't exist");
		}


	}

}
