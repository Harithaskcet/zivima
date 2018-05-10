package com.example.admin.zivima;

/**
 * Created by Admin on 19-04-2018.
 */

public class User {
    private String username="";
    private String college="";
    private String phone_num="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getCollege() {

        return college;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public User()
    {

    }

}
