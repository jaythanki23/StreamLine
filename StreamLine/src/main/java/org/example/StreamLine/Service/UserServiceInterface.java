package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.UserAlreadyExistsException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.User;

import java.util.List;

public interface UserServiceInterface {
	// get all users
	List<User> getAllUsers();
	
	// get user by id
	User getUserById(Integer id) throws UserNotFoundException;
	
	// create user
	User createUser(User user) throws UserAlreadyExistsException;

	// update user
	User updateUser(Integer id, User user) throws UserNotFoundException;
	
	// delete user by id
	String deleteUserById(Integer id) throws UserNotFoundException;
	
	
}
