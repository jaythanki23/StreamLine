package org.example.StreamLine.Service;

import org.example.StreamLine.Exceptions.UserAlreadyExistsException;
import org.example.StreamLine.Exceptions.UserNotFoundException;
import org.example.StreamLine.Model.User;
import org.example.StreamLine.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserServiceInterface {
	
	private UserRepository userRepository;
	
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) throws UserNotFoundException {
		User user = userRepository.findById(id).orElse(null);

		if(user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}
	}
	
	public User createUser(User user) throws UserAlreadyExistsException {
		User dbUser = userRepository.findByEmail(user.getEmail());
		if(dbUser == null) {
			return userRepository.save(user);
		} else {
			throw new UserAlreadyExistsException("User already exists");
		}


	}
	
	public User updateUser(Integer id, User user) throws UserNotFoundException {
		User dbUser = userRepository.findById(id).orElse(null);

		if(dbUser != null) {
			if(Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())) {
				dbUser.setUserName(user.getUserName());
			}

			if(Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
				dbUser.setFirstName(user.getFirstName());
			}

			if(Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
				dbUser.setLastName(user.getLastName());
			}

			if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
				dbUser.setEmail(user.getEmail());
			}

			return userRepository.save(dbUser);
		} else {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}
		


	}
	
	public String deleteUserById(Integer id) throws UserNotFoundException {
		User user = userRepository.findById(id).orElse(null);
		if(user != null) {
			userRepository.deleteById(id);
			return "The user has been removed";
		} else {
			throw new UserNotFoundException("User with id " + id + " doesn't exist");
		}

	}

}
