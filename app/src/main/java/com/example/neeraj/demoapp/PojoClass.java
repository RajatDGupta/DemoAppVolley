package com.example.neeraj.demoapp;

/**
 * Created by neeraj on 6/12/17.
 */

public class PojoClass {


    private String name;

    private String email;

    private String mobile;

    public PojoClass(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
