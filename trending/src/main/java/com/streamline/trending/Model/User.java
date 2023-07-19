package com.streamline.trending.Model;

import java.io.Serializable;

public record User(Integer id, String userName, String firstName, String lastName, String email) implements Serializable {
}
