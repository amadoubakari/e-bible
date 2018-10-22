package com.sprintpay.user_authentication.models;

import java.io.Serializable;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet d'encapsuler les données ou informations liées à un utilisateur.
 * @since 15/10/2018
 */

public class User implements Serializable {

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
