package com.example.admin.zivima;

/**
 * Created by Admin on 08-04-2018.
 */

public class Upload {
    public String name;
    public String url;
    public String username;
    public String college;
    public String phone;
    public String email;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public Upload(String name,String url,String username,String college,String phone,String email){
        this.name=name;
        this.url=url;
        this.username=username;
        this.college=college;
        this.phone=phone;
        this.email=email;
    }
    public String getUsername(){
        return username;
    }
    public String getCollege() {
        return college;
    }
    public String getPhone() {
        return phone;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getUrl() {
        return url;
    }
}
