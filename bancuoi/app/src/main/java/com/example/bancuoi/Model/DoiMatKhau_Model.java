package com.example.bancuoi.Model;

public class DoiMatKhau_Model {
    private int user_id ;
    private  String NewPassword;

    public DoiMatKhau_Model(int user_id, String newPassword) {
        this.user_id = user_id;
        NewPassword = newPassword;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}
