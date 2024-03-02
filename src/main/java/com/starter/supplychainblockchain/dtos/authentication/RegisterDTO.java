package com.starter.supplychainblockchain.dtos.authentication;

public class RegisterDTO {
    private String username;
    private String name;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userrname) {
        this.username = userrname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
