package org.example.StreamLine.Controller;

import jakarta.validation.Valid;
import org.example.StreamLine.Exceptions.UserAlreadyExistsException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserServiceInterface userService;
	
	public UserController(UserServiceInterface userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) throws UserAlreadyExistsException {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer userId, @RequestBody @Valid User user) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.updateUser(userId, user), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer userId) throws UserNotFoundException {
		return new ResponseEntity<String>(userService.deleteUserById(userId), HttpStatus.OK);
	}
	
	

}
