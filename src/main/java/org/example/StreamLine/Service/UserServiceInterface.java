package org.example.StreamLine.Service;

import org.example.StreamLine.Model.User;

import java.util.List;

public interface UserServiceInterface {
	// get all users
	List<User> getAllUsers();
	
	// get user by id
	User getUserById(Integer id);
	
	// create user
	User createUser(User user);

	// update user
	User updateUser(Integer id, User user);
	
	// delete user by id
	String deleteUserById(Integer id);
	
	
}
