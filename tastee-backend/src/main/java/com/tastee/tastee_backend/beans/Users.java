package com.tastee.tastee_backend.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Users {

    @Id
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    
    
}
