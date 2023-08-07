package org.example.StreamLine.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="posts_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne()
	@JoinColumn(
			name="user_id",
			referencedColumnName = "id"
	)
	private User user;

	private String description;

	@CreationTimestamp
	private Date creationDate;

	private String fileName;

	private String fileType;

	private String filePath;

	public Post() {

	}

	public Post(User user, String description, String fileName, String fileType, String filePath) {
		this.user = user;
		this.description = description;
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
	}

	public Post(User user, String description) {
		this.user = user;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}