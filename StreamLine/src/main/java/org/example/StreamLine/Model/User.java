package org.example.StreamLine.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_table")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Username cannot be empty")
	private String userName;

	@NotNull(message = "Firstname cannot be empty")
	private String firstName;

	@NotNull(message = "Lastname cannot be empty")
	private String lastName;

	@NotNull(message = "Email cannot be empty")
	@Email(message = "Email is invalid")
	private String email;
	
	public User() {
		
	}
	
	public User(Integer id, String userName, String firstName, String lastName, String email) {
		this.setId(id);
		this.setUserName(userName);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
	}

	public User(String userName, String firstName, String lastName, String email) {
		this.setUserName(userName);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
