package com.yee.quiz.model;

public class User {
    private String id;
    private String category;
    private String username;
    private String password;

    public User() {}

    public User(String id, String category, String username, String password) {
        this.id = id;
        this.category = category;
        this.username = username;
        this.password = password;
    }

    // getter and setter
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}