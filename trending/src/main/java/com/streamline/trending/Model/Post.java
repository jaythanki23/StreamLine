package com.streamline.trending.Model;

import java.io.Serializable;
import java.util.Date;

public record Post(Integer id, User user, String description, Date creationDate, String fileName, String fileType, String filePath) implements Serializable {
}
