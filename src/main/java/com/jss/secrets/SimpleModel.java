package com.jss.secrets;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SimpleModel {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}