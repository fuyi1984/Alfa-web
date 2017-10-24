package com.alfa.web.model;

public class User {

    private String username;
    private String password;
    private String nickname;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){

    }

    public User(String username, String password, String nickname, String email) {
        super();
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
