package com.poc.vertx.starter;

public class UserService {

  public UserModel createUser(String name, String email) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }
    
    return new UserModel(1, name, email);
  }
}