package com.example.bancuoi.Model;

public class Users {
    public int User_Id;
    public String userName;
    public String password;

    public Users(int user_Id, String userName, String password) {
        User_Id = user_Id;
        this.userName = userName;
        this.password = password;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
