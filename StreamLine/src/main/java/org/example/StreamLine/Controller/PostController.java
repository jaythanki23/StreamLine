package org.example.StreamLine.Controller;

import org.example.StreamLine.Exceptions.FileOrDescriptionNotFoundException;
import org.example.StreamLine.Exceptions.PostNotFoundException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Service.HashtagServiceInterface;
import org.example.StreamLine.Service.PostServiceInterface;
import org.example.StreamLine.Service.UserService;
import org.example.StreamLine.Service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {
	
	private PostServiceInterface postService;
	private UserServiceInterface userService;
	private HashtagServiceInterface hashtagService;

	private final String FOLDER_PATH = "E:\\Social\\images\\";
	
	public PostController(PostServiceInterface postService, UserService userService, HashtagServiceInterface hashtagService) {
		super();
		this.postService = postService;
		this.userService = userService;
		this.hashtagService = hashtagService;
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Post>> getAllPostsByUser(@PathVariable("id") Integer userId) throws UserNotFoundException {
		return new ResponseEntity<List<Post>>(postService.getPostByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") Integer id) throws PostNotFoundException {
		return new ResponseEntity<Post>(postService.getPost(id), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Post>> getAllPosts() {
		return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Post>> getPostWithPagination(@RequestParam( value = "page",defaultValue = "0", required = false) Integer pageNumber,
															@RequestParam( value = "size", defaultValue = "1", required = false) Integer pageSize) {
		List<Post> allPost = postService.getPostWithPagination(pageNumber, pageSize);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	
	@PostMapping() 
	public ResponseEntity<Post> createPost(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "description") String description, @RequestParam(value = "userId") Integer userId) throws IOException, UserNotFoundException, FileOrDescriptionNotFoundException {

		if((description.equals("") || description.equals(null)) && file.isEmpty()) {
			throw new FileOrDescriptionNotFoundException("Please include either a file or a description");
		}

		User user = userService.getUserById(userId);
		Post post = new Post();

		if(!file.isEmpty()) {
			String filePath = FOLDER_PATH+file.getOriginalFilename();
			file.transferTo(new File(filePath));
			post.setUser(user);
			post.setDescription(description);
			post.setFileName(file.getOriginalFilename());
			post.setFileType(file.getContentType());
			post.setFilePath(filePath);
		} else {
			post.setUser(user);
			post.setDescription(description);
		}

		postService.createPost(post);

		hashtagService.parseHashtag(post.getDescription(), post);

		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePostById(@PathVariable("id") Integer postId) throws PostNotFoundException {
		return new ResponseEntity<String>(postService.deletePostById(postId), HttpStatus.OK);
	}
}
