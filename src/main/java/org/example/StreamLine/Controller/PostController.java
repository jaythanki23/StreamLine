package org.example.StreamLine.Controller;

import org.example.StreamLine.Model.Post;
import org.example.StreamLine.Model.User;
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

	private final String FOLDER_PATH = "E:\\Social\\images\\";
	
	public PostController(PostServiceInterface postService, UserService userService) {
		super();
		this.postService = postService;
		this.userService = userService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<List<Post>> getAllPost(@PathVariable("id") Integer userId) {
		return new ResponseEntity<List<Post>>(postService.getPostByUser(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Post>> getPostWithPagination(@RequestParam( value = "page",defaultValue = "0", required = false) Integer pageNumber,
															@RequestParam( value = "size", defaultValue = "1", required = false) Integer pageSize) {
		List<Post> allPost = postService.getPostWithPagination(pageNumber, pageSize);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	
	@PostMapping() 
	public ResponseEntity<Post> createPost(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "description") String description, @RequestParam(value = "userId") Integer userId) throws IOException {

		User user = userService.getUserById(userId);

		if(!file.isEmpty()) {
			String filePath = FOLDER_PATH+file.getOriginalFilename();
			file.transferTo(new File(filePath));
			Post post = new Post(user, description, file.getOriginalFilename(), file.getContentType(), filePath);
			return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.OK);
		} else {
			Post post = new Post(user, description);
			return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.OK);
		}
	}

	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePostById(@PathVariable("id") Integer postId) {
		return new ResponseEntity<String>(postService.deletePostById(postId), HttpStatus.OK);
	}
}
